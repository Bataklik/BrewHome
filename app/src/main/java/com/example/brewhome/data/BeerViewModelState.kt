package com.example.brewhome.data

data class BeerViewModelState(
    val currentBeers: List<Beer>,
    val currentBeerById: BeerDetail?,
    val favoriteBeers: List<Beer>
)
