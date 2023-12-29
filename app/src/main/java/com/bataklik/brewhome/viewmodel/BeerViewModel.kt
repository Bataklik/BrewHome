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
 * [ViewModel] voor [Beer]-gegevens en API-oproepen.
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
    val beerState: StateFlow<BeerViewModelState> = _beersState
        .asStateFlow()

    // Staat van de lijst met favoriete bieren
    lateinit var uiListState: StateFlow<List<Beer>>

    // Staat van de API-oproepen
    var beerApiState: BeerApiState by mutableStateOf(BeerApiState.LoadingBeers)
        private set
    var beerDetailApiState: BeerDetailApiState by mutableStateOf(BeerDetailApiState.LoadingBeer)
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
                /*                val randomPage = (1..10)
                                    .random()*/
                val resultBeers = beerRepository
                    .getBeers()
                setBeersInState(resultBeers)
                BeerApiState
                    .SuccessBeers(resultBeers)
            } catch (e: IOException) {
                BeerApiState
                    .ErrorBeers
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
                val beerDetail = beerRepository
                    .getBeerById(beerId)
                setBeerInState(beerDetail)
                BeerDetailApiState
                    .SuccessBeer(beerDetail)
            } catch (e: IOException) {
                Timber.i("Failed to use api: $e")
                BeerDetailApiState.ErrorBeer
            }
        }
    }

    /**
     * Stelt het huidige bier in binnen de [beerState].
     * @param beer Het bier dat moet worden ingesteld als het huidige bier.
     */
    private fun setBeerInState(beer: BeerDetail) {
        _beersState.update {
            it.copy(currentBeerById = beer)
        }
    }
    /**
     * Stelt de bieren lijst in binnen de [beerState] .
     * @param beers De lijst van bieren.
     */
    private fun setBeersInState(beers: List<Beer>) {
        _beersState
            .update {
                it.copy(currentBeers = beers)
            }
    }

    /**
     * Voegt huidige bier toe aan favorieten.
     */
    fun addBeerToFavorites() {
        val beer = _beersState
            .value
            .currentBeerById
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
     * Controleert of [Beer] met [beerId] in favorieten staat.
     * @param beerId Het unieke identificatienummer van het te controleren bier.
     */
    suspend fun isBeerInFavorites(beerId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            favoriteBeerRepository.isBeerInFavorites(beerId)
        }
    }

    /**
     * [Companion] object voor [BeerViewModel] om een [ViewModelProvider.Factory] te verstrekken.
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

