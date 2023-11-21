package com.example.brewhome.network

import com.example.brewhome.model.Beer

sealed interface BeerApiState {
    object ErrorBeers : BeerApiState
    object LoadingBeers : BeerApiState
    data class SuccessBeers(val beers: List<Beer>) : BeerApiState
}

