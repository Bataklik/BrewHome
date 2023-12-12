package com.example.brewhome.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.brewhome.BeerApplication
import com.example.brewhome.data.BeerRepository
import com.example.brewhome.data.FavoriteBeerRepository
import com.example.brewhome.data.database.asDbFavoriteBeer
import com.example.brewhome.model.Beer
import com.example.brewhome.model.BeerDetail
import com.example.brewhome.model.asBeer
import com.example.brewhome.network.BeerApiState
import com.example.brewhome.network.BeerDetailApiState
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

class BeerViewModel(
    private val beerRepository: BeerRepository,
    private val favoriteBeerRepository: FavoriteBeerRepository
) : ViewModel() {
    private val _beersState =
        MutableStateFlow(BeerViewModelState(emptyList(), null, listOf()))
    val uiState: StateFlow<BeerViewModelState> = _beersState
        .asStateFlow()
    lateinit var uiListState: StateFlow<List<Beer>>

    var beerApiState: BeerApiState by mutableStateOf(BeerApiState.LoadingBeers)
        private set
    var beerDetailApiState: BeerDetailApiState by mutableStateOf(BeerDetailApiState.LoadingBeer)
        private set

    init {
        getApiBeers()
    }

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
                print(uiListState.value)

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

    private fun setCurrentBeer(beer: BeerDetail) {
        _beersState.update {
            it.copy(currentBeerById = beer)
        }
    }

    fun addBeerToFavorites() {
        val beer = _beersState.value.currentBeerById
        if (beer != null) {
            viewModelScope.launch {
                favoriteBeerRepository
                    .insertFavoriteBeer(beer.asBeer())
            }
        }
    }

    fun deleteFromFavoriteBeers(id: Int) {
        viewModelScope.launch {
            val toDeleteBeer = beerRepository
                .getBeerById(id)
                .asDbFavoriteBeer()

            favoriteBeerRepository
                .deletefavoriteBeer(toDeleteBeer)
        }

    }

    fun getFavoriteBeer() {
        val beer = _beersState.value.currentBeerById
        if (beer != null) {
            viewModelScope.launch {
                favoriteBeerRepository
                    .getFavoriteBeerById(beer.id)
            }
        }
    }

    suspend fun isBeerInFavorites(routeId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            favoriteBeerRepository.isBeerInFavorites(routeId)
        }
    }

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

