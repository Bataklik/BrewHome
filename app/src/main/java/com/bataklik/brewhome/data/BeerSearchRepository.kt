package com.bataklik.brewhome.data

import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail
import com.bataklik.brewhome.network.BeerApiService
import com.bataklik.brewhome.network.asBeerObjects

interface BeerSearchRepository{
    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-ID.
     * @param beerName De naam van het bier.
     * @return Een ljst met [BeerDetail] objecten dat de gedetailleerde informatie van het opgehaalde bier bevat.
     */
    suspend fun getBeerByName(beerName: String): List<Beer>
}

class ApiBeerSearchRepository(private val beerApiService: BeerApiService):BeerSearchRepository{
    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-ID.
     * @param beerName Het unieke ID van het bier.
     * @return Een lijst [Beer] objecten dat de gedetailleerde informatie van het opgehaalde bier bevat.
     */
    override suspend fun getBeerByName(beerName: String): List<Beer> {
        return beerApiService
            .getBeerByName(beerName)
            .asBeerObjects()
    }
}