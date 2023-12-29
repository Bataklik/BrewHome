package com.bataklik.brewhome.data

import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail
import com.bataklik.brewhome.network.BeerApiService
import com.bataklik.brewhome.network.asBeerObjects

interface BeerSearchRepository{
    /**
     * Haal informatie op over een specifieke [Beer]-objecten aan de hand van het opgegeven [beerName].
     * @param beerName De naam van het bier.
     * @return Een lijst met [Beer]-objecten van het opgehaalde bier bevat.
     */
    suspend fun getBeerByName(beerName: String): List<Beer>
}

class ApiBeerSearchRepository(private val beerApiService: BeerApiService):BeerSearchRepository{
    /**
     * Haal informatie op over een specifieke [Beer]-objecten aan de hand van het opgegeven [beerName].
     * @param beerName Het beer NAAM van de bieren.
     * @return Een lijst [Beer]-objecten van het opgehaalde bieren bevat.
     */
    override suspend fun getBeerByName(beerName: String): List<Beer> {
        return beerApiService
            .getBeerByName(beerName)
            .asBeerObjects()
    }
}