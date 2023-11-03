package com.example.brewhome.network

import com.example.brewhome.data.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface BeerApiService {
    @GET("beers/random")
    suspend fun getRandomBeer(): Response<List<Beer>>

    @GET("beers/{beerId}")
    suspend fun getBeerById(@Path("beerId") beerId: Int): Response<Beer>

    @GET("beers")
    suspend fun getBeers(): Response<List<Beer>>
}