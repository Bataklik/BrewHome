package com.example.brewhome.data

import com.example.brewhome.model.Beer
import com.example.brewhome.model.BeerDetail
import com.example.brewhome.network.BeerApiService
import com.example.brewhome.network.asBeerObject
import com.example.brewhome.network.asBeerObjects

interface BeerRepository {
    suspend fun getBeers(): List<Beer>
    suspend fun getBeers(
        page: Int,
        perPage: Int
    ): List<Beer>

    suspend fun getRandomBeer(): List<Beer>
    suspend fun getBeerById(beerId: Int): BeerDetail

}

class ApiBeerRepository(private val beerApiService: BeerApiService) : BeerRepository {
    override suspend fun getBeers(): List<Beer> {
        return beerApiService
            .getBeers()
            .asBeerObjects()
    }

    override suspend fun getBeers(page: Int, perPage: Int): List<Beer> {
        return beerApiService
            .getBeers(page = page, perPage = perPage)
            .asBeerObjects()
    }

    override suspend fun getRandomBeer(): List<Beer> {
        return beerApiService
            .getRandomBeer()
            .asBeerObjects()
    }

    override suspend fun getBeerById(beerId: Int): BeerDetail {
        return beerApiService
            .getBeerById(beerId)[0]
            .asBeerObject()
    }
}