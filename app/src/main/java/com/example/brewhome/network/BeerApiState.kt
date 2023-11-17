package com.example.brewhome.data

sealed interface BeerApiState {
    object Error : BeerApiState
    object Loading : BeerApiState
    data class success(val tasks: List<Beer>) : BeerApiState
}

