package com.bataklik.brewhome.viewmodel.states

import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail

/**
 * De state van de BeerViewModel.
 * @param currentBeers De lijst met bieren.
 * @param currentBeerById Het bier met het opgegeven id.
 * @param favoriteBeers De lijst met favoriete bieren.
 */
data class BeerViewModelState(
    val currentBeers: List<Beer>,
    val currentBeerById: BeerDetail?,
    val favoriteBeers: List<Beer>,
)
