package com.bataklik.brewhome.fake

import com.bataklik.brewhome.data.FavoriteBeerRepository
import com.bataklik.brewhome.data.database.DbFavoriteBeer
import com.bataklik.brewhome.fake.data.FakeFavoriteDataSource
import com.bataklik.brewhome.model.Beer
import kotlinx.coroutines.flow.Flow

class FakeFavoriteBeerRepository : FavoriteBeerRepository {
    override suspend fun insertFavoriteBeer(favBeer: Beer) {
        TODO("Not yet implemented")
    }

    override suspend fun deletefavoriteBeer(favBeer: DbFavoriteBeer) {
        TODO("Not yet implemented")
    }

    override fun getFavoriteBeers(): Flow<List<Beer>> {
        return FakeFavoriteDataSource
            .beers
    }

    override fun getFavoriteBeerById(beerId: Int): Beer? {
        return FakeFavoriteDataSource
            .beer(beerId)
    }

    override fun isBeerInFavorites(beerId: Int): Boolean {
        TODO("Not yet implemented")
    }
}