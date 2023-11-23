package com.example.brewhome.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeerApiService {
    @GET("beers/random")
    suspend fun getRandomBeer(): List<ApiBeer>

    @GET("beers/{beerId}")
    suspend fun getBeerById(@Path("beerId") beerId: Int): List<ApiBeerDetail>

    @GET("beers")
    suspend fun getBeers(): List<ApiBeer>

    @GET("beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<ApiBeer>
}
