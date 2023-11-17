package com.example.brewhome.network

import com.example.brewhome.data.Beer

sealed interface BeerApiState {
    object ErrorBeers : BeerApiState
    object LoadingBeers : BeerApiState
    data class SuccessBeers(val beers: List<Beer>) : BeerApiState
}

