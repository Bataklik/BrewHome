package com.bataklik.brewhome.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface die de endpoints van de Beer API definieert voor het ophalen van bierinformatie.
 * De interface maakt gebruik van Retrofit voor het uitvoeren van HTTP-verzoeken.
 * @see retrofit2.http.GET
 * @see retrofit2.http.Path
 * @see retrofit2.http.Query
 */
interface BeerApiService {

    /**
     * Haal een willekeurig bier op van de API.
     * @return Een lijst met [ApiBeer] objecten die de informatie van het willekeurige bier bevatten.
     */
    @GET("beers/random")
    suspend fun getRandomBeer(): List<ApiBeer>

    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-ID.
     * @param beerId Het unieke ID van het bier.
     * @return Een lijst met [ApiBeerDetail] object die de gedetailleerde informatie van het bier bevatten.
     */
    @GET("beers/{beerId}")
    suspend fun getBeerById(@Path("beerId") beerId: Int): List<ApiBeerDetail>

    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-NAAM.
     * @param beerName Het NAAM van het bier.
     * @return Een lijst met [ApiBeer] object die de gedetailleerde informatie van het bier bevatten.
     */
    @GET("beers")
    suspend fun getBeerByName(@Query("beer_name") beerName: String): List<ApiBeer>

    /**
     * Haal een lijst op van alle beschikbare bieren van de API.
     * @return Een lijst met [ApiBeer] objecten die de informatie van de beschikbare bieren bevatten.
     */
    @GET("beers")
    suspend fun getBeers(): List<ApiBeer>

}
