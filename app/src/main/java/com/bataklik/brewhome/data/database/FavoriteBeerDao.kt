package com.bataklik.brewhome.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface voor interactie met de lokale Room-database voor favoriete bieren.
 * Bevat methoden voor het invoegen, verwijderen en ophalen van favoriete bieren, evenals het controleren of een bier in de favorietenlijst staat.
 */
@Dao
interface FavoriteBeerDao {

    /**
     * Voeg een favoriet bier toe aan de database.
     * @param favBeer Het [DbFavoriteBeer]-object dat moet worden toegevoegd aan de favorietenlijst.
     */
    @Insert
    suspend fun insertFavoriteBeer(favBeer: DbFavoriteBeer)

    /**
     * Verwijder een favoriet bier uit de database.
     * @param favBeer Het [DbFavoriteBeer]-object dat moet worden verwijderd uit de favorietenlijst.
     */
    @Delete
    suspend fun deleteFavoriteBeer(favBeer: DbFavoriteBeer)

    /**
     * Haal een stroom van alle favoriete bieren op, gesorteerd op naam.
     * @return Een [Flow] van lijsten met [DbFavoriteBeer]-objecten die de favoriete bieren vertegenwoordigen.
     */
    @Query("SELECT * FROM DbFavoriteBeer ORDER BY name ASC")
    fun getFavoriteBeers(): Flow<List<DbFavoriteBeer>>

    /**
     * Haal een favoriet bier op aan de hand van het opgegeven bier-ID.
     * @param beerId Het unieke ID van het favoriete bier.
     * @return Een [DbFavoriteBeer]-object dat het favoriete bier vertegenwoordigt.
     */
    @Query("SELECT * FROM DbFavoriteBeer WHERE id = :beerId")
    fun getFavoriteBeerById(beerId: Int): DbFavoriteBeer

    /**
     * Controleer of een bier zich in de lijst met favoriete bieren bevindt.
     * @param beerId Het unieke ID van het bier.
     * @return true als het bier in de favorietenlijst staat, anders false.
     */
    @Query("SELECT EXISTS (SELECT 1 FROM DbFavoriteBeer WHERE id = :beerId LIMIT 1)")
    fun isBeerInFavorites(beerId: Int): Boolean
}
