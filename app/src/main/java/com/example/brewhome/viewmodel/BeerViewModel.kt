package com.example.brewhome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewhome.data.Beer
import com.example.brewhome.data.BeerViewModelState
import com.example.brewhome.network.BeerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BeerViewModel : ViewModel() {
    private val repository = BeerRepository()
    private val _beersState = MutableStateFlow(BeerViewModelState(emptyList(), null))
    val uiState: StateFlow<BeerViewModelState> = _beersState.asStateFlow()

    init {
        fetchBeers()
    }

    private fun fetchBeers() {
        viewModelScope.launch {
            val beers = repository.getBeers() ?: emptyList()
            _beersState.update { currentState ->
                currentState.copy(
                    currentBeers = beers
                )
            }
        }
    }

    fun getBeerById(beerId: Int) {
        viewModelScope.launch {
            val beer = repository.getBeerById(beerId)
            _beersState.update { currentState ->
                currentState.copy(
                    currentBeerById = beer
                )
            }
        }
    }
}