package com.example.brewhome.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBeerDao {
    @Insert
    suspend fun insertFavoriteBeer(favBeer: DbFavoriteBeer)

    @Delete
    suspend fun deleteFavoriteBeer(favBeer: DbFavoriteBeer)

    @Query("SELECT * FROM DbFavoriteBeer ORDER BY name ASC")
    fun getFavoriteBeers(): Flow<List<DbFavoriteBeer>>

    @Query("SELECT * FROM DbFavoriteBeer WHERE id = :routeId")
    fun getFavoriteBeerById(routeId:Int): DbFavoriteBeer

    @Query("SELECT EXISTS (SELECT 1 FROM DbFavoriteBeer WHERE id = :beerId LIMIT 1)")
    fun isBeerInFavorites(beerId: Int): Boolean
}