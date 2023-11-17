package com.example.brewhome.network

import com.example.brewhome.data.BeerDetail

sealed interface BeerDetailApiState {
    object ErrorBeer : BeerDetailApiState
    object LoadingBeer : BeerDetailApiState
    data class SuccessBeer(val beer: BeerDetail) : BeerDetailApiState
}