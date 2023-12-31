package com.bataklik.brewhome

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.bataklik.brewhome.fake.FakeBeerDataSource
import com.bataklik.brewhome.network.BeerSearchApiState
import com.bataklik.brewhome.ui.screens.SearchScreen
import com.bataklik.brewhome.ui.theme.BrewHomeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchScreenTests {

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            BrewHomeTheme {
                SearchScreen(
                    beerSearchApiState = BeerSearchApiState
                        .SuccessSearchBeers(FakeBeerDataSource.BEERS),
                    goToDetail = {},
                    getBeersByName = {})
            }
        }
    }

    /**
     * Controleert of de zoekbalk juiste placeholder heeft
     */
    @Test
    fun verifySearchScreenTextField() {
        rule.onNodeWithContentDescription("txtfSearch")
            .assertExists()
            .assertTextContains("Search a beer")
    }
}