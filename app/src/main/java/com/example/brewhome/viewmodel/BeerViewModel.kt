package com.example.brewhome.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewhome.data.BeerViewModelState
import com.example.brewhome.network.BeerApi.beerService
import com.example.brewhome.network.BeerApiState
import com.example.brewhome.network.BeerDetailApiState
import com.example.brewhome.network.asDomainObject
import com.example.brewhome.network.asDomainObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class BeerViewModel : ViewModel() {
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
                val apiBeers = beerService.getBeers()
                BeerApiState.SuccessBeers(apiBeers.asDomainObjects())
            } catch (e: IOException) {
                Timber.i("Failed to use api: $e")
                BeerApiState.ErrorBeers
            }
        }
    }


    fun getBeerById(beerId: Int) {
        viewModelScope.launch {
            beerDetailApiState = try {
                val apiBeer = beerService.getBeerById(beerId)[0]
                BeerDetailApiState.SuccessBeer(apiBeer.asDomainObject())
            } catch (e: IOException) {
                Timber.i("Failed to use api: $e")
                BeerDetailApiState.ErrorBeer
            }
        }
    }
}

