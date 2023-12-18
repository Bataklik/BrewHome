package com.bataklik.brewhome.fake

import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.network.ApiBeer
import com.bataklik.brewhome.network.ApiBeerDetail
import com.bataklik.brewhome.network.BeerApiService

class FakeBeerApiService : BeerApiService {
    override suspend fun getRandomBeer(): List<ApiBeer> {
        TODO("Not yet implemented")
    }

    override suspend fun getBeerById(beerId: Int): List<ApiBeerDetail> {
        return listOf(
            FakeBeerDataSource
                .BEER_DETAIL
        )
    }

    override suspend fun getBeerByName(beerName: String): List<ApiBeer> {
        TODO("Not yet implemented")
    }

    override suspend fun getBeers(): List<ApiBeer> {
        return FakeBeerDataSource
            .BEERS
    }

    override suspend fun getBeers(page: Int, perPage: Int): List<ApiBeer> {
        TODO("Not yet implemented")
    }
}