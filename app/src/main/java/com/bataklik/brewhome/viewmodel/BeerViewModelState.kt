package com.example.brewhome.viewmodel

import com.example.brewhome.model.BeerDetail
import com.example.brewhome.model.Beer

data class BeerViewModelState(
    val currentBeers: List<Beer>,
    val currentBeerById: BeerDetail?,
    val favoriteBeers: List<Beer>,
)
