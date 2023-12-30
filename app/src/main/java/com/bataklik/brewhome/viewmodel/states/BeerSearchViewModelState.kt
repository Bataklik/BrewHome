package com.bataklik.brewhome.viewmodel.states

import com.bataklik.brewhome.model.Beer

/**
 * De state van de BeerSearchViewModel.
 * @param searchBeers De lijst met bieren die zijn gevonden met de zoekterm.
 */
data class BeerSearchViewModelState(
    val searchBeers: List<Beer>,
)