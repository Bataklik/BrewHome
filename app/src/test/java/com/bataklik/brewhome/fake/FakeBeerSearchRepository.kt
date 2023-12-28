package com.bataklik.brewhome.fake

import com.bataklik.brewhome.data.BeerSearchRepository
import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.network.asBeerObjects

class FakeBeerSearchRepository : BeerSearchRepository {
    override suspend fun getBeerByName(beerName: String): List<Beer> {
        return FakeBeerDataSource
            .BEER_BY_NAME
            .asBeerObjects()
    }
}