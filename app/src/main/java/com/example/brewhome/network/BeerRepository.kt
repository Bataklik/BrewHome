package com.example.brewhome.network

import com.example.brewhome.data.Beer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BeerRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(BeerApiService::class.java)

    suspend fun getRandomBeer(): Beer? {
        val response = service.getRandomBeer()
        if (response.isSuccessful) {
            return response.body()?.firstOrNull()
        }
        return null
    }
    suspend fun getBeerById(beerId: Int): Beer? {
        val response = service.getBeerById(beerId)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getBeers(): List<Beer>? {
        val response = service.getBeers()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}
