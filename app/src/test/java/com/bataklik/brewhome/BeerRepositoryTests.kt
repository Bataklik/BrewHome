package com.bataklik.brewhome

import com.bataklik.brewhome.data.ApiBeerRepository
import com.bataklik.brewhome.data.BeerRepository
import com.bataklik.brewhome.fake.FakeBeerApiService
import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.network.asBeerObject
import com.bataklik.brewhome.network.asBeerObjects
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * De testklasse voor de BeerRepository.
 * @property repo De repository die getest wordt.
 * @see BeerRepository
 */
class BeerRepositoryTests {
    private lateinit var repo: BeerRepository

    @Before
    fun setup() {
        repo = ApiBeerRepository(FakeBeerApiService())
    }

    /**
     * Controleert of de lijst met bieren juist is.
     */
    @Test
    fun getBeers_veryBeersList() = runTest {
        val expected = FakeBeerDataSource
            .BEERS
            .asBeerObjects()
        val actual = repo
            .getBeers()
        assertEquals(expected, actual)
    }

    /**
     * Controleert of het bier met de juiste id wordt opgehaald.
     */
    @Test
    fun getBeer_byId_veryBeer() = runTest {
        val expected = FakeBeerDataSource
            .BEER_DETAIL
            .asBeerObject()
        val actual = repo.getBeerById(1)
        assertEquals(expected, actual)
    }
}