package com.example.brewhome.data

import android.content.Context
import androidx.room.Room
import com.example.brewhome.data.database.FavoriteBeerDatabase
import com.example.brewhome.network.BeerApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface BeerContainer {
    val beerRepository: BeerRepository
    val favoriteBeerRepository: FavoriteBeerRepository
}

class DefaultBeerContainer(applicationContext: Context) : BeerContainer {
    private val _baseUrl = "https://api.punkapi.com/v2/"
    private val json: Json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            json.asConverterFactory(
                "application/json"
                    .toMediaType()
            )
        )
        .baseUrl(_baseUrl)
        .build()

    private val retrofitService: BeerApiService by lazy {
        retrofit.create(BeerApiService::class.java)
    }
    private val favoriteBeerDatabase: FavoriteBeerDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            FavoriteBeerDatabase::class.java,
            "favoriteBeers_db"
        ).build()
    }

    override val beerRepository: BeerRepository by lazy {
        ApiBeerRepository(retrofitService)
    }
    override val favoriteBeerRepository: FavoriteBeerRepository by lazy {
        CachingFavoriteBeerRepository(favoriteBeerDatabase.favoriteBeerDao(), retrofitService)
    }
}