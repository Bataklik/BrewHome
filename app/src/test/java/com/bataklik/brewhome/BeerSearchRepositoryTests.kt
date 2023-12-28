package com.bataklik.brewhome

import com.bataklik.brewhome.data.ApiBeerSearchRepository
import com.bataklik.brewhome.data.BeerSearchRepository
import com.bataklik.brewhome.fake.FakeBeerApiService
import com.bataklik.brewhome.fake.data.FakeBeerDataSource
import com.bataklik.brewhome.network.asBeerObjects
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BeerSearchRepositoryTests {
    private lateinit var repo: BeerSearchRepository

    @Before
    fun setup() {
        repo = ApiBeerSearchRepository(FakeBeerApiService())
    }

    @Test
    fun getBeer_ByName_verifyBeer() = runTest {
        val searchTerm = "Artois"
        val expected = FakeBeerDataSource
            .BEER_BY_NAME
            .asBeerObjects()
        val actual = repo.getBeerByName(searchTerm)
        Assert.assertEquals(expected, actual)
    }
}