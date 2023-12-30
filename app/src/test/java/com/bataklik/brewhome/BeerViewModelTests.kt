package com.bataklik.brewhome

import com.bataklik.brewhome.fake.FakeBeerRepository
import com.bataklik.brewhome.fake.FakeFavoriteBeerRepository
import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.network.asBeerObject
import com.bataklik.brewhome.network.asBeerObjects
import com.bataklik.brewhome.viewmodel.BeerViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * De testklasse voor de BeerViewModel.
 * @property beerViewModelTest De viewmodel die getest wordt.
 * @see BeerViewModel
 */
class BeerViewModelTests {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var beerViewModelTest: BeerViewModel

    @Before
    fun setup() {
        beerViewModelTest = BeerViewModel(FakeBeerRepository(), FakeFavoriteBeerRepository())
    }

    /**
     * Controleert of de lijst met bieren juist is.
     */
    @Test
    fun getBeers_verifyBeersList() = runTest {
        val expected = FakeBeerDataSource
            .BEERS
            .asBeerObjects()
        val actual = beerViewModelTest
            .beerState
            .value
            .currentBeers
        assertEquals(expected, actual)
    }

    /**
     * Controleert of het bier met de juiste id wordt opgehaald.
     */
    @Test
    fun getBeer_verifyBeer() = runTest {
        val expected = FakeBeerDataSource
            .BEER_DETAIL
            .asBeerObject()
        beerViewModelTest
            .getBeerById(1)
        val actual = beerViewModelTest
            .beerState
            .value
            .currentBeerById
        assertEquals(expected, actual)
    }

}

