package com.bataklik.brewhome.viewmodel.states

import com.bataklik.brewhome.model.Beer

data class BeerSearchViewModelState(
    val searchBeers: List<Beer>,
)