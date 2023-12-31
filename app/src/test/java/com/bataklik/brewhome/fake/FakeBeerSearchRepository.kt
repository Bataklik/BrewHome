package com.bataklik.brewhome.fake

import com.bataklik.brewhome.data.BeerSearchRepository
import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.network.asBeerObjects

/**
 * De nep repository voor het zoeken van bieren.
 * @see BeerSearchRepository
 */
class FakeBeerSearchRepository : BeerSearchRepository {
    override suspend fun getBeerByName(beerName: String): List<Beer> {
        return FakeBeerDataSource
            .BEER_BY_NAME
            .asBeerObjects()
    }
}