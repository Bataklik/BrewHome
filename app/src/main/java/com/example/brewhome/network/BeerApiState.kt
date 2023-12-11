package com.example.brewhome.network

import com.example.brewhome.model.Beer

/**
 * Sealed interface die de verschillende staten vertegenwoordigt bij het ophalen van bierinformatie via de Beer API.
 * Deze interface wordt gebruikt om de status van het API-verzoek aan te geven.
 */
sealed interface BeerApiState {
    /**
     * Object dat de staat vertegenwoordigt wanneer er een fout optreedt bij het ophalen van bierinformatie.
     */
    object ErrorBeers : BeerApiState
    /**
     * Object dat de staat vertegenwoordigt wanneer het ophalen van bierinformatie bezig is.
     */
    object LoadingBeers : BeerApiState
    /**
     * Data class die de staat vertegenwoordigt wanneer het ophalen van bierinformatie succesvol is.
     *
     * @property beers Een lijst met [Beer] objecten die de informatie van de opgehaalde bieren bevatten.
     */
    data class SuccessBeers(val beers: List<Beer>) : BeerApiState
}

