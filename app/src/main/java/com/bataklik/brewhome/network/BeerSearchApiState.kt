package com.bataklik.brewhome.network

import com.bataklik.brewhome.model.Beer

/**
 * Sealed interface die de verschillende staten vertegenwoordigt bij het ophalen van gezochte bierinformatie via de Beer API.
 * Deze interface wordt gebruikt om de status van het API-verzoek aan te geven.
 */
sealed interface BeerSearchApiState {
    /**
     * Object dat de staat vertegenwoordigt wanneer er een fout optreedt bij het ophalen van bierinformatie.
     */
    object ErrorBeers : BeerSearchApiState

    /**
     * Object dat de staat vertegenwoordigt wanneer het ophalen van bierinformatie bezig is.
     */
    object LoadingBeers : BeerSearchApiState

    /**
     * Data class die de staat vertegenwoordigt wanneer het ophalen van bierinformatie succesvol is.
     *
     * @property searchBeer Een lijst met [Beer] objecten die de informatie van de opgehaalde bieren bevatten.
     */
    data class SuccessSearchBeers(val searchBeer: List<Beer> = listOf()) : BeerSearchApiState
}