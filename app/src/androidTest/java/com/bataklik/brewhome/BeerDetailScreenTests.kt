package com.bataklik.brewhome

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.bataklik.brewhome.fake.FakeBeerDataSource
import com.bataklik.brewhome.fake.FakeBeerDataSource.BEER
import com.bataklik.brewhome.fake.FakeBeerDataSource.IS_FAVORITE_TRUE
import com.bataklik.brewhome.network.BeerDetailApiState
import com.bataklik.brewhome.ui.screens.BeerDetailScreen
import com.bataklik.brewhome.ui.theme.BrewHomeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BeerDetailScreenTests {
    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            BrewHomeTheme {
                BeerDetailScreen(
                    beerDetailApiState = BeerDetailApiState
                        .SuccessBeer(BEER),
                    addBeerToFavorites = {  },
                    isBeerInFavorites = IS_FAVORITE_TRUE
                )
            }
        }
    }

    @Test
    fun verifyBeerDetailScreenTitle() {
        rule.onNodeWithContentDescription("txtBeerDetailTitle")
            .assertTextEquals(FakeBeerDataSource.BEER_NAME_ONE)
    }

    @Test
    fun verifyBeerDetailTagline() {
        rule.onNodeWithContentDescription("txtBeerDetailTagline")
            .assertTextEquals(FakeBeerDataSource.BEER_TAGLINE_ONE)
    }

    @Test
    fun verifyBeerDetailDescription() {
        rule.onNodeWithContentDescription("txtBeerDetailDescription")
            .assertTextEquals(FakeBeerDataSource.BEER_DESCRIPTION_ONE)
    }

    @Test
    fun verifyBeerDetailFirstBrew() {
        rule.onNodeWithContentDescription("txtBeerDetailFirstBrew")
            .assertTextEquals(FakeBeerDataSource.BEER_FIRST_BREW_ONE)
    }
}