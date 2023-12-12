package com.example.brewhome

import com.example.brewhome.fake.FakeBeerRepository
import com.example.brewhome.fake.FakeFavoriteBeerRepository
import com.example.brewhome.viewmodel.BeerViewModel
import org.junit.Before
import org.junit.Rule


class BeerViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var beerViewModelTest: BeerViewModel

    @Before
    fun setup() {
        beerViewModelTest = BeerViewModel(FakeBeerRepository(), FakeFavoriteBeerRepository())
    }
}

