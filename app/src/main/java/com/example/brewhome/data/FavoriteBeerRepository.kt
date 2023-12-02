package com.example.brewhome.data

import com.example.brewhome.data.database.DbFavoriteBeer
import com.example.brewhome.data.database.FavoriteBeerDao
import com.example.brewhome.data.database.asDomainBeer
import com.example.brewhome.data.database.asDomainFavoriteBeers
import com.example.brewhome.model.Beer
import com.example.brewhome.model.asDbFavoriteBeer
import com.example.brewhome.network.BeerApiService
import com.example.brewhome.network.asDomainObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

interface FavoriteBeerRepository {
    suspend fun insertFavoriteBeer(favBeer: Beer)
    suspend fun deletefavoriteBeer(favBeer: DbFavoriteBeer)
    fun getFavoriteBeers(): Flow<List<Beer>>
    fun getFavoriteBeerById(routeId: Int): Beer
    fun isBeerInFavorites(routeId:Int):Boolean
}

class CachingFavoriteBeerRepository(
    private val favoriteBeerDao: FavoriteBeerDao,
    private val beerApiService: BeerApiService
) : FavoriteBeerRepository {
    override suspend fun insertFavoriteBeer(favBeer: Beer) {
        favoriteBeerDao
            .insertFavoriteBeer(favBeer.asDbFavoriteBeer())
    }

    override suspend fun deletefavoriteBeer(favBeer: DbFavoriteBeer) {
        favoriteBeerDao
            .deleteFavoriteBeer(favBeer)
    }

    override fun getFavoriteBeers(): Flow<List<Beer>> {
        return favoriteBeerDao
            .getFavoriteBeers()
            .map {
                val transformedList = it.asDomainFavoriteBeers()
                Timber.d("Transformed List: $transformedList")
                it.asDomainFavoriteBeers() }
    }

    override fun getFavoriteBeerById(routeId: Int): Beer {
        return favoriteBeerDao
            .getFavoriteBeerById(routeId)
            .asDomainBeer()
    }

    override fun isBeerInFavorites(routeId: Int): Boolean {
        return favoriteBeerDao
            .isBeerInFavorites(routeId)
    }

    private suspend fun refresh() {
        beerApiService
            .getBeers()
            .forEach {
                favoriteBeerDao.insertFavoriteBeer(it.asDomainObject().asDbFavoriteBeer())
            }
    }
}