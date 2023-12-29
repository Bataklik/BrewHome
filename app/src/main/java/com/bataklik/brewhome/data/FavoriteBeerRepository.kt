package com.bataklik.brewhome.data

import com.bataklik.brewhome.data.database.DbFavoriteBeer
import com.bataklik.brewhome.data.database.FavoriteBeerDao
import com.bataklik.brewhome.data.database.asDomainBeer
import com.bataklik.brewhome.data.database.asDomainFavoriteBeers
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.model.asDbFavoriteBeer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

/**
 * Repository-interface voor het beheren van favoriete bieren in de lokale database met behulp van Room.
 * Biedt methoden voor het toevoegen, verwijderen en ophalen van favoriete [Beer]-objecten, evenals controle of een [Beer]-object in de favorietenlijst staat.
 */
interface FavoriteBeerRepository {
    /**
     * Voeg een [Beer] toe aan de lijst met favoriete bieren.
     * @param favBeer Het [Beer]-object dat moet worden toegevoegd aan de favorietenlijst.
     */
    suspend fun insertFavoriteBeer(favBeer: Beer)

    /**
     * Verwijder een favoriet [Beer] uit de lijst met favoriete [DbFavoriteBeer]-objecten.
     * @param favBeer Het [DbFavoriteBeer]-object dat moet worden verwijderd uit de favorietenlijst.
     */
    suspend fun deletefavoriteBeer(favBeer: DbFavoriteBeer)

    /**
     * Haal een stroom van alle favoriete [Beer]-objecten op.
     * @return Een [Flow] van lijsten met [Beer]-objecten die de favoriete bieren vertegenwoordigen.
     */
    fun getFavoriteBeers(): Flow<List<Beer>>

    /**
     * Haal een favoriet [Beer]-object op aan de hand van het opgegeven bier-ID.
     * @param beerId Het unieke ID van het favoriete bier.
     * @return Een [Beer]-object dat het favoriete bier vertegenwoordigt, of null als het niet gevonden is.
     */
    fun getFavoriteBeerById(beerId: Int): Beer?

    /**
     * Controleer of een bier zich in de lijst met favoriete [Beer]-objecten bevindt.
     * @param beerId Het unieke ID van het bier.
     * @return true als het [Beer]-object in de favorietenlijst staat, anders false.
     */
    fun isBeerInFavorites(beerId: Int): Boolean
}

/**
 * Implementatie van [FavoriteBeerRepository] die lokale database-interacties afhandelt met Room en indien nodig de Beer API raadpleegt.
 * @property favoriteBeerDao DAO voor interactie met de lokale database.
 */
class CachingFavoriteBeerRepository(
    private val favoriteBeerDao: FavoriteBeerDao,
) : FavoriteBeerRepository {

    /**
     * Voeg een [Beer]-object toe aan de lijst met favoriete bieren.
     * @param favBeer Het [Beer]-object dat moet worden toegevoegd aan de favorietenlijst.
     */
    override suspend fun insertFavoriteBeer(favBeer: Beer) {
        favoriteBeerDao
            .insertFavoriteBeer(favBeer.asDbFavoriteBeer())
    }

    /**
     * Verwijder een favoriet [Beer] uit de lijst met favoriete [DbFavoriteBeer]-objecten.
     * @param favBeer Het [DbFavoriteBeer]-object dat moet worden verwijderd uit de favorietenlijst.
     */
    override suspend fun deletefavoriteBeer(favBeer: DbFavoriteBeer) {
        favoriteBeerDao
            .deleteFavoriteBeer(favBeer)
    }

    /**
     * Haal een stroom van alle favoriete [Beer]-objecten op.
     * @return Een [Flow] van lijsten met [Beer]-objecten die de favoriete bieren vertegenwoordigen.
     */
    override fun getFavoriteBeers(): Flow<List<Beer>> {
        return favoriteBeerDao
            .getFavoriteBeers()
            .map {
                val transformedList = it.asDomainFavoriteBeers()
                Timber.d("Transformed List: $transformedList")
                it.asDomainFavoriteBeers()
            }
    }

    /**
     * Haal een favoriet bier op aan de hand van het opgegeven bier-ID.
     * @param beerId Het unieke ID van het favoriete bier.
     * @return Een [Beer]-object dat het favoriete bier vertegenwoordigt, of null als het niet gevonden is.
     */
    override fun getFavoriteBeerById(beerId: Int): Beer {
        return favoriteBeerDao
            .getFavoriteBeerById(beerId)
            .asDomainBeer()
    }

    /**
     * Controleer of een bier zich in de lijst met favoriete bieren bevindt.
     * @param beerId Het unieke ID van het bier.
     * @return true als het bier in de favorietenlijst staat, anders false.
     */
    override fun isBeerInFavorites(beerId: Int): Boolean {
        return favoriteBeerDao
            .isBeerInFavorites(beerId)
    }

}