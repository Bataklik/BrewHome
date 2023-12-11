package com.example.brewhome.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room Database-klasse die de lokale database voor favoriete bieren vertegenwoordigt.
 * @property entities Een array van [DbFavoriteBeer]-klassen die deze database bevat.
 * @property version Het versienummer van de database.
 */
@Database(entities = [DbFavoriteBeer::class], version = 1)
abstract class FavoriteBeerDatabase : RoomDatabase() {
    /**
     * Abstracte methode om een DAO-interface te verkrijgen voor interactie met de favoriete bieren in de database.
     * @return Een instantie van [FavoriteBeerDao] voor database-interacties met favoriete bieren.
     */
    abstract fun favoriteBeerDao(): FavoriteBeerDao
}
