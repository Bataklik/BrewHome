package com.bataklik.brewhome.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bataklik.brewhome.BeerApplication
import com.bataklik.brewhome.data.BeerSearchRepository
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.network.BeerSearchApiState
import com.bataklik.brewhome.viewmodel.states.BeerSearchViewModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

/**
 * ViewModel voor het zoeken van bieren.
 * @param beerSearchRepository De repository voor het zoeken van bieren.
 * @see BeerSearchRepository
 */
class BeerSearchViewModel(private val beerSearchRepository: BeerSearchRepository) : ViewModel() {

    // Staat van de UI-gegevens
    private val _beerSearchState =
        MutableStateFlow(BeerSearchViewModelState(emptyList()))
    val beerSearchState: StateFlow<BeerSearchViewModelState> = _beerSearchState
        .asStateFlow()
    var beerSeachApiState: BeerSearchApiState by mutableStateOf(BeerSearchApiState.LoadingBeers)
        private set


    /**
     * Stelt de gezochte bieren lijst in binnen de [_beerSearchState] .
     * @param beers De lijst van bieren.
     */
    private fun setBeersInState(beers: List<Beer>) {
        _beerSearchState
            .update {
                it.copy(searchBeers = beers)
            }
    }

    /**
     * Zoekt bieren op basis van [beerName] en werkt [BeerSearchApiState] bij.
     * Deze functie start een coroutine binnen de [viewModelScope] om de API-oproep uit te voeren. Als de oproep succesvol is,
     * wordt [BeerSearchApiState.SuccessSearchBeers] met de opgehaalde bieren teruggegeven. Bij een fout wordt
     * [BeerSearchApiState.ErrorBeers] ingesteld.
     * @param beerName Naam van het bier om op te zoeken.
     * @see beerSeachApiState
     * @see BeerSearchApiState
     * @throws IOException Bij problemen met de API-oproep.
     */
    fun getSeachApiBeers(beerName: String) {
        viewModelScope.launch {
            beerSeachApiState = try {
                if (beerName.isEmpty()) {
                    BeerSearchApiState.SuccessSearchBeers(listOf())
                }
                val resultBeers = beerSearchRepository
                    .getBeerByName(beerName = beerName)
                setBeersInState(resultBeers)
                BeerSearchApiState
                    .SuccessSearchBeers(resultBeers)
            } catch (e: Exception) {
                Timber.i("Failed to use api: $e")
                BeerSearchApiState.ErrorBeers
            }
        }
    }

    /**
     * Companion object voor [BeerSearchViewModel] om een [ViewModelProvider.Factory] te verstrekken.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as BeerApplication
                val beerSearchRepository = application.container.beerSearchRepository
                BeerSearchViewModel(beerSearchRepository)
            }
        }
    }
}
