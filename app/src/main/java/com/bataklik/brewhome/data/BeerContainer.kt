package com.bataklik.brewhome.data

import android.content.Context
import androidx.room.Room
import com.bataklik.brewhome.data.database.FavoriteBeerDatabase
import com.bataklik.brewhome.network.BeerApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Interface die de functionaliteiten definieert die beschikbaar moeten zijn in een [BeerContainer].
 * Een [BeerContainer] biedt toegang tot repositories voor het ophalen van bierinformatie en favoriete bieren.
 */
interface BeerContainer {
    val beerRepository: BeerRepository
    val favoriteBeerRepository: FavoriteBeerRepository
    val beerSearchRepository:BeerSearchRepository
}

/**
 * Standaard implementatie van [BeerContainer], die de benodigde afhankelijkheden en instanties beheert.
 * @param applicationContext De context van de toepassing.
 */
class DefaultBeerContainer(applicationContext: Context) : BeerContainer {
    /**
     * Basis-URL van de Beer API
     */
    private val _baseUrl = "https://api.punkapi.com/v2/"

    /**
     * JSON-serializatieconfiguratie
     */
    private val json: Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    /**
     * Retrofit-instantie voor het maken van HTTP-verzoeken
     */
    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(
            json.asConverterFactory(
                "application/json"
                    .toMediaType()
            )
        )
        .baseUrl(_baseUrl)
        .build()

    /**
     * Service-interface voor het uitvoeren van Beer API-verzoeken
     */
    private val retrofitService: BeerApiService by lazy {
        retrofit.create(BeerApiService::class.java)
    }

    /**
     * Database voor het opslaan van favoriete bieren
     */
    private val favoriteBeerDatabase: FavoriteBeerDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            FavoriteBeerDatabase::class.java,
            "favoriteBeers_db"
        ).build()
    }

    /**
     * Implementatie van [BeerRepository] voor het ophalen van bierinformatie via de Beer API
     */
    override val beerRepository: BeerRepository by lazy {
        ApiBeerRepository(retrofitService)
    }



    /**
     * Implementatie van [FavoriteBeerRepository] voor het beheren van favoriete bieren
     */
    override val favoriteBeerRepository: FavoriteBeerRepository by lazy {
        CachingFavoriteBeerRepository(favoriteBeerDatabase.favoriteBeerDao())
    }

    /**
     * Implementatie van [BeerSearchRepository] voor het ophalen van bierinformatie via de Beer API
     */
    override val beerSearchRepository: BeerSearchRepository by lazy{
        ApiBeerSearchRepository(retrofitService)
    }
}