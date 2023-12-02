package com.example.brewhome.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbFavoriteBeer::class], version = 1)
abstract class FavoriteBeerDatabase : RoomDatabase() {
    abstract fun favoriteBeerDao(): FavoriteBeerDao
}