package com.example.brewhome

import com.example.brewhome.fake.FakeBeerRepository
import com.example.brewhome.fake.FakeFavoriteBeerRepository
import com.example.brewhome.viewmodel.BeerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class BeerViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var beerViewModelTest: BeerViewModel

    @Before
    fun setup() {
        beerViewModelTest = BeerViewModel(FakeBeerRepository(), FakeFavoriteBeerRepository())
    }


}

class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}