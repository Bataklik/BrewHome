package com.bataklik.brewhome

import com.bataklik.brewhome.fake.FakeBeerRepository
import com.bataklik.brewhome.fake.FakeFavoriteBeerRepository
import com.bataklik.brewhome.viewmodel.BeerViewModel
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

