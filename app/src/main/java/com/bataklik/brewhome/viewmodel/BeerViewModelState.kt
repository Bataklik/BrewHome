package com.bataklik.brewhome.viewmodel

import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail

data class BeerViewModelState(
    val currentBeers: List<Beer>,
    val currentBeerById: BeerDetail?,
    val favoriteBeers: List<Beer>,
)
