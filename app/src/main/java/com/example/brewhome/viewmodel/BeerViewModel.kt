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
import com.example.brewhome.network.BeerApiState
import com.example.brewhome.network.BeerDetailApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class BeerViewModel(private val beerRepository: BeerRepository) : ViewModel() {
    private val _beersState = MutableStateFlow(BeerViewModelState(emptyList(), null, listOf()))
    val uiState: StateFlow<BeerViewModelState> = _beersState.asStateFlow()

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
                BeerDetailApiState.SuccessBeer(apiBeer)
            } catch (e: IOException) {
                Timber.i("Failed to use api: $e")
                BeerDetailApiState.ErrorBeer
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY]) as BeerApplication
                val beerRepository = application.container.beerRepository
                BeerViewModel(beerRepository)
            }
        }
    }
}

