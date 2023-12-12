package com.example.brewhome

import com.example.brewhome.data.ApiBeerRepository
import com.example.brewhome.data.BeerRepository
import com.example.brewhome.fake.FakeBeerApiService
import com.example.brewhome.fake.data.FakeBeerDataSource
import com.example.brewhome.network.asBeerObject
import com.example.brewhome.network.asBeerObjects
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BeerRepositoryTests {
    lateinit var repo: BeerRepository

    @Before
    fun setup() {
        repo = ApiBeerRepository(FakeBeerApiService())
    }

    @Test
    fun getBeers_veryBeersList() = runTest {
        val expected = FakeBeerDataSource.beers.asBeerObjects()
        val actual = repo.getBeers()
        assertEquals(expected, actual)
    }

    @Test
    fun getBeer_byId_veryBeer() = runTest {
        val expected = FakeBeerDataSource
            .beerDetail
            .asBeerObject()
        val actual = repo.getBeerById(1)
        assertEquals(expected, actual)
    }
}