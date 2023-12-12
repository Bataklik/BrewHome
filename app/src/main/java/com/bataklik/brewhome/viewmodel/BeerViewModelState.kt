package com.bataklik.brewhome.viewmodel

import com.bataklik.brewhome.model.BeerDetail
import com.bataklik.brewhome.model.Beer

data class BeerViewModelState(
    val currentBeers: List<Beer>,
    val currentBeerById: BeerDetail?,
    val favoriteBeers: List<Beer>,
)
