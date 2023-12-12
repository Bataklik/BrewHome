package com.bataklik.brewhome.fake

import com.bataklik.brewhome.data.BeerRepository
import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail
import com.bataklik.brewhome.network.asBeerObject
import com.bataklik.brewhome.network.asBeerObjects

class FakeBeerRepository : BeerRepository {
    override suspend fun getBeers(): List<Beer> {
        return FakeBeerDataSource
            .beers.asBeerObjects()
    }

    override suspend fun getBeers(page: Int, perPage: Int): List<Beer> {
        return FakeBeerDataSource
            .beers.asBeerObjects()
    }

    override suspend fun getRandomBeer(): List<Beer> {
        return listOf(
            FakeBeerDataSource.beers.random()
                .asBeerObject()
        )
    }

    override suspend fun getBeerById(beerId: Int): BeerDetail {
        return FakeBeerDataSource
            .beerDetail
            .asBeerObject()
    }
}