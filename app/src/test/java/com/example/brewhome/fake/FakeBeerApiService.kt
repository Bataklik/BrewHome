package com.example.brewhome.fake

import com.example.brewhome.fake.data.FakeBeerDataSource
import com.example.brewhome.network.ApiBeer
import com.example.brewhome.network.ApiBeerDetail
import com.example.brewhome.network.BeerApiService

class FakeBeerApiService : BeerApiService {
    override suspend fun getRandomBeer(): List<ApiBeer> {
        TODO("Not yet implemented")
    }

    override suspend fun getBeerById(beerId: Int): List<ApiBeerDetail> {
        return listOf(
            FakeBeerDataSource
                .beerDetail
        )
    }

    override suspend fun getBeers(): List<ApiBeer> {
        return FakeBeerDataSource
            .beers
    }

    override suspend fun getBeers(page: Int, perPage: Int): List<ApiBeer> {
        TODO("Not yet implemented")
    }
}