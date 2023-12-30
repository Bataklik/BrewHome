package com.bataklik.brewhome

import com.bataklik.brewhome.fake.FakeBeerRepository
import com.bataklik.brewhome.fake.FakeBeerSearchRepository
import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.network.asBeerObjects
import com.bataklik.brewhome.viewmodel.BeerSearchViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * De testklasse voor de BeerSearchViewModel.
 * @property beerViewModelTest De viewmodel die getest wordt.
 * @see BeerSearchViewModel
 */
class BeerSearchViewModelTests {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var beerViewModelTest: BeerSearchViewModel

    @Before
    fun setup() {
        beerViewModelTest = BeerSearchViewModel(FakeBeerSearchRepository())
    }

    /**
     * Controleert of de lijst met bieren van de zoekterm juist is.
     */
    @Test
    fun searchBeer_verifyBeer() = runTest {
        val searchTerm = "Dog"
        val expected =  FakeBeerDataSource
            .BEER_BY_NAME
            .asBeerObjects()
        beerViewModelTest
            .getSeachApiBeers(searchTerm)
        val actual = beerViewModelTest
            .beerSearchState
            .value
            .searchBeers

        Assert.assertEquals(expected,actual)
    }
}