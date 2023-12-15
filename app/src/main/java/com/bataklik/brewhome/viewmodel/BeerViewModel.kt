package com.bataklik.brewhome.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bataklik.brewhome.BeerApplication
import com.bataklik.brewhome.data.BeerRepository
import com.bataklik.brewhome.data.FavoriteBeerRepository
import com.bataklik.brewhome.data.database.asDbFavoriteBeer
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail
import com.bataklik.brewhome.model.asBeer
import com.bataklik.brewhome.network.BeerApiState
import com.bataklik.brewhome.network.BeerDetailApiState
import com.bataklik.brewhome.network.BeerSearchApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException

/**
 * ViewModel voor biergegevens en API-oproepen.
 * @param beerRepository Repository voor biergegevens van de API.
 * @param favoriteBeerRepository Repository voor favoriete bieren.
 */
class BeerViewModel(
    private val beerRepository: BeerRepository,
    private val favoriteBeerRepository: FavoriteBeerRepository
) : ViewModel() {
    // Staat van de UI-gegevens
    private val _beersState =
        MutableStateFlow(BeerViewModelState(emptyList(), null, listOf()))
    val uiState: StateFlow<BeerViewModelState> = _beersState
        .asStateFlow()

    // Staat van de lijst met favoriete bieren
    lateinit var uiListState: StateFlow<List<Beer>>

    // Staat van de API-oproepen
    var beerApiState: BeerApiState by mutableStateOf(BeerApiState.LoadingBeers)
        private set
    var beerDetailApiState: BeerDetailApiState by mutableStateOf(BeerDetailApiState.LoadingBeer)
        private set
    var beerSeachApiState: BeerSearchApiState by mutableStateOf(BeerSearchApiState.LoadingBeers)
        private set

    /**
     * Initialiseert de ViewModel en haalt de lijst met bieren op van de API.
     */
    init {
        getApiBeers()
    }

    /**
     * Haalt lijst met bieren op van de API en werkt [beerApiState] bij.
     * @throws IOException Bij problemen met de API-oproep.
     */
    private fun getApiBeers() {
        viewModelScope.launch {
            beerApiState = try {
                uiListState = favoriteBeerRepository
                    .getFavoriteBeers()
                    .stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
                        initialValue = listOf()
                    )

                Timber.i("Favorite Beers: $uiListState")

                val randomPage = (1..10).random()
                val resultBeers = beerRepository
                    .getBeers(randomPage, 20)
                BeerApiState.SuccessBeers(resultBeers)
            } catch (e: IOException) {
                Timber.i("Failed to use api: $e")
                BeerApiState.ErrorBeers
            }
        }
    }

    /**
     * Zoekt bieren op basis van [beerName] en werkt [BeerSearchApiState] bij.
     * Deze functie start een coroutine binnen de [viewModelScope] om de API-oproep uit te voeren. Als de oproep succesvol is,
     * wordt [BeerSearchApiState.SuccessSearchBeers] met de opgehaalde bieren teruggegeven. Bij een fout wordt
     * [BeerSearchApiState.ErrorBeers] ingesteld.
     * @param beerName Naam van het bier om op te zoeken.
     * @see beerRepository
     * @see BeerSearchApiState
     * @throws IOException Bij problemen met de API-oproep.
     */
    fun getSeachApiBeers(beerName: String) {
        viewModelScope.launch {
            beerSeachApiState = try {
                if (beerName.isEmpty()) {
                    BeerSearchApiState.SuccessSearchBeers(listOf())
                }
                val resultBeers = beerRepository
                    .getBeerByName(beerName = beerName)
                BeerSearchApiState
                    .SuccessSearchBeers(resultBeers)
            } catch (e: Exception) {
                Timber.i("Failed to use api: $e")
                BeerSearchApiState.ErrorBeers
            }
        }
    }

    /**
     * Haalt een bier op van de API op basis van [beerId] en werkt [beerDetailApiState] bij.
     * @throws IOException Bij problemen met de API-oproep.
     */
    fun getBeerById(beerId: Int) {
        viewModelScope.launch {
            beerDetailApiState = try {
                val apiBeer = beerRepository
                    .getBeerById(beerId)
                setCurrentBeer(apiBeer)
                BeerDetailApiState.SuccessBeer(apiBeer)
            } catch (e: IOException) {
                Timber.i("Failed to use api: $e")
                BeerDetailApiState.ErrorBeer
            }
        }
    }

    /**
     * Stelt het huidige bier in binnen de [uiState].
     * @param beer Het bier dat moet worden ingesteld als het huidige bier.
     */
    private fun setCurrentBeer(beer: BeerDetail) {
        _beersState.update {
            it.copy(currentBeerById = beer)
        }
    }

    /**
     * Voegt huidige bier toe aan favorieten.
     */
    fun addBeerToFavorites() {
        val beer = _beersState.value.currentBeerById
        if (beer != null) {
            viewModelScope.launch {
                favoriteBeerRepository
                    .insertFavoriteBeer(beer.asBeer())
            }
        }
    }

    /**
     * Verwijdert bier met [id] uit favorieten.
     * @param id Het unieke identificatienummer van het te verwijderen bier.
     */
    fun deleteFromFavoriteBeers(id: Int) {
        viewModelScope.launch {
            val toDeleteBeer = beerRepository
                .getBeerById(id)
                .asDbFavoriteBeer()

            favoriteBeerRepository
                .deletefavoriteBeer(toDeleteBeer)
        }

    }

    /**
     * Controleert of bier met [routeId] in favorieten staat.
     * @param routeId Het unieke identificatienummer van het te controleren bier.
     */
    suspend fun isBeerInFavorites(routeId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            favoriteBeerRepository.isBeerInFavorites(routeId)
        }
    }

    /**
     * Companion object voor [BeerViewModel] om een [ViewModelProvider.Factory] te verstrekken.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY]) as BeerApplication
                val beerRepository = application.container.beerRepository
                val favoriteBeerRepository = application.container.favoriteBeerRepository
                BeerViewModel(beerRepository, favoriteBeerRepository)
            }
        }
    }
}

