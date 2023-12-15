package com.bataklik.brewhome.data

import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.BeerDetail
import com.bataklik.brewhome.network.BeerApiService
import com.bataklik.brewhome.network.asBeerObject
import com.bataklik.brewhome.network.asBeerObjects

/**
 * Repository-interface die de functies definieert voor het ophalen van bierinformatie van de Beer API.
 * Bevat methoden zoals het ophalen van alle bieren, paginering van bieren, een willekeurig bier, en gedetailleerde informatie over een specifiek bier.
 */
interface BeerRepository {
    /**
     * Haal een lijst op van alle beschikbare bieren van de Beer API.
     * @return Een lijst met [Beer] objecten die de informatie van de beschikbare bieren bevatten.
     */
    suspend fun getBeers(): List<Beer>
    /**
     * Haal een paginering van bieren op van de Beer API.
     * @param page De paginanummer van de resultaten.
     * @param perPage Het aantal resultaten per pagina.
     * @return Een lijst met [Beer] objecten die de informatie van de opgegeven paginering bevatten.
     */
    suspend fun getBeers(
        page: Int,
        perPage: Int
    ): List<Beer>

    /**
     * Haal een willekeurig bier op van de Beer API.
     * @return Een lijst met [Beer] objecten die de informatie van het willekeurige bier bevatten.
     */
    suspend fun getRandomBeer(): List<Beer>
    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-ID.
     * @param beerId Het unieke ID van het bier.
     * @return Een [BeerDetail] object dat de gedetailleerde informatie van het opgehaalde bier bevat.
     */
    suspend fun getBeerById(beerId: Int): BeerDetail

    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-ID.
     * @param beerName De naam van het bier.
     * @return Een ljst met [BeerDetail] objecten dat de gedetailleerde informatie van het opgehaalde bier bevat.
     */
    suspend fun getBeerByName(beerName: String): List<Beer>

}

/**
 * Implementatie van [BeerRepository] die gebruikmaakt van [BeerApiService] voor het ophalen van bierinformatie.
 * @property beerApiService De service-interface voor het uitvoeren van Beer API-verzoeken.
 */
class ApiBeerRepository(private val beerApiService: BeerApiService) : BeerRepository {

    /**
     * Haal een lijst op van alle beschikbare bieren van de Beer API.
     * @return Een lijst met [Beer] objecten die de informatie van de beschikbare bieren bevatten.
     */
    override suspend fun getBeers(): List<Beer> {
        return beerApiService
            .getBeers()
            .asBeerObjects()
    }

    /**
     * Haal een paginering van bieren op van de Beer API.
     * @param page De paginanummer van de resultaten.
     * @param perPage Het aantal resultaten per pagina.
     * @return Een lijst met [Beer] objecten die de informatie van de opgegeven paginering bevatten.
     */
    override suspend fun getBeers(page: Int, perPage: Int): List<Beer> {
        return beerApiService
            .getBeers(page = page, perPage = perPage)
            .asBeerObjects()
    }


    /**
     * Haal een willekeurig bier op van de Beer API.
     * @return Een lijst met [Beer] objecten die de informatie van het willekeurige bier bevatten.
     */
    override suspend fun getRandomBeer(): List<Beer> {
        return beerApiService
            .getRandomBeer()
            .asBeerObjects()
    }

    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-ID.
     * @param beerId Het unieke ID van het bier.
     * @return Een [BeerDetail] object dat de gedetailleerde informatie van het opgehaalde bier bevat.
     */
    override suspend fun getBeerById(beerId: Int): BeerDetail {
        return beerApiService
            .getBeerById(beerId)[0]
            .asBeerObject()
    }
    /**
     * Haal gedetailleerde informatie op over een specifiek bier aan de hand van het opgegeven bier-ID.
     * @param beerName Het unieke ID van het bier.
     * @return Een lijst [Beer] objecten dat de gedetailleerde informatie van het opgehaalde bier bevat.
     */
    override suspend fun getBeerByName(beerName: String): List<Beer> {
        return beerApiService
            .getBeerByName(beerName)
            .asBeerObjects()
    }


}