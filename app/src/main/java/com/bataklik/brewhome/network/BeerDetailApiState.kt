package com.bataklik.brewhome.network

import com.bataklik.brewhome.model.BeerDetail

/**
 * Sealed interface die de verschillende staten vertegenwoordigt bij het ophalen van gedetailleerde bierinformatie
 * via de Beer API. Deze interface wordt gebruikt om de status van het API-verzoek aan te geven.
 */
sealed interface BeerDetailApiState {

    /**
     * Object dat de staat vertegenwoordigt wanneer er een fout optreedt bij het ophalen van gedetailleerde bierinformatie.
     */
    object ErrorBeer : BeerDetailApiState

    /**
     * Object dat de staat vertegenwoordigt wanneer het ophalen van gedetailleerde bierinformatie bezig is.
     */
    object LoadingBeer : BeerDetailApiState

    /**
     * Data class die de staat vertegenwoordigt wanneer het ophalen van gedetailleerde bierinformatie succesvol is.
     *
     * @property beer Een [BeerDetail] object dat de gedetailleerde informatie van het opgehaalde bier bevat.
     */
    data class SuccessBeer(val beer: BeerDetail) : BeerDetailApiState
}
