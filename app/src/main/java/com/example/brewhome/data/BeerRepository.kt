package com.example.brewhome.data

import com.example.brewhome.model.Beer
import com.example.brewhome.model.BeerDetail
import com.example.brewhome.network.BeerApiService
import com.example.brewhome.network.asDomainObject
import com.example.brewhome.network.asDomainObjects

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
            .asDomainObjects()
    }

    override suspend fun getBeers(page: Int, perPage: Int): List<Beer> {
        return beerApiService
            .getBeers(page = page, perPage = perPage)
            .asDomainObjects()
    }

    override suspend fun getRandomBeer(): List<Beer> {
        return beerApiService
            .getRandomBeer()
            .asDomainObjects()
    }

    override suspend fun getBeerById(beerId: Int): BeerDetail {
        return beerApiService
            .getBeerById(beerId)[0]
            .asDomainObject()
    }
}