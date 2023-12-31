package com.bataklik.brewhome.fake

import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.network.ApiBeer
import com.bataklik.brewhome.network.ApiBeerDetail
import com.bataklik.brewhome.network.BeerApiService

class FakeBeerApiService : BeerApiService {
    override suspend fun getRandomBeer(): List<ApiBeer> {
        TODO("Not yet implemented")
    }

    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-ID.
     */
    override suspend fun getBeerById(beerId: Int): List<ApiBeerDetail> {
        return listOf(
            FakeBeerDataSource
                .BEER_DETAIL
        )
    }


    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-NAAM.
     */
    override suspend fun getBeerByName(beerName: String): List<ApiBeer> {
        return FakeBeerDataSource
            .BEER_BY_NAME
    }

    /**
     * Haal een lijst op van alle beschikbare bieren van de API.
     */
    override suspend fun getBeers(): List<ApiBeer> {
        return FakeBeerDataSource
            .BEERS
    }
}