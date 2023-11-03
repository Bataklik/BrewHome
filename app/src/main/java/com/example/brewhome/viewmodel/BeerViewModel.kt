package com.example.brewhome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewhome.data.Beer
import com.example.brewhome.network.BeerRepository
import kotlinx.coroutines.launch

class BeerViewModel : ViewModel() {
    private val repository = BeerRepository()

    val beersState = mutableStateOf<List<Beer>>(emptyList())

    init {
        fetchBeers()
    }

    private fun fetchBeers() {
        viewModelScope.launch {
            val beers = repository.getBeers()
            if (beers != null) {
                beersState.value = beers
            }
        }
    }
}