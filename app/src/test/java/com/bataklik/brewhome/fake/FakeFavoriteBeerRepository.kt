package com.bataklik.brewhome.fake

import com.bataklik.brewhome.data.FavoriteBeerRepository
import com.bataklik.brewhome.data.database.DbFavoriteBeer
import com.bataklik.brewhome.fake.data.FakeFavoriteDataSource
import com.bataklik.brewhome.model.Beer
import kotlinx.coroutines.flow.Flow

/**
 * De nep repository voor de favoriete bieren.
 * @see FavoriteBeerRepository
 */
class FakeFavoriteBeerRepository : FavoriteBeerRepository {


    override suspend fun insertFavoriteBeer(favBeer: Beer) {
    }

    override suspend fun deletefavoriteBeer(favBeer: DbFavoriteBeer) {
    }

    override fun getFavoriteBeers(): Flow<List<Beer>> {
        return FakeFavoriteDataSource
            .BEERS
    }

    override fun getFavoriteBeerById(beerId: Int): Beer? {
        return FakeFavoriteDataSource
            .BEER_BY_ID(beerId)
    }

    override fun isBeerInFavorites(beerId: Int): Boolean {
        TODO("Not yet implemented")
    }
}