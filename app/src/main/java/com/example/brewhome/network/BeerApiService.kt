package com.example.brewhome.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface BeerApiService {
    @GET("beers/random")
    suspend fun getRandomBeer(): List<ApiBeer>

    @GET("beers/{beerId}")
    suspend fun getBeerById(@Path("beerId") beerId: Int): List<ApiBeerDetail>

    @GET("beers ")
    suspend fun getBeers(): List<ApiBeer>
}
