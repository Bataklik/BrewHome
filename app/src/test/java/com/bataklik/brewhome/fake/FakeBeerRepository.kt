package com.example.brewhome.fake

import com.example.brewhome.data.BeerRepository
import com.example.brewhome.fake.data.FakeBeerDataSource
import com.example.brewhome.model.Beer
import com.example.brewhome.model.BeerDetail
import com.example.brewhome.network.asBeerObject
import com.example.brewhome.network.asBeerObjects

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