package com.bataklik.brewhome

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.bataklik.brewhome.fake.FakeBeerDataSource.BEERS
import com.bataklik.brewhome.network.BeerApiState
import com.bataklik.brewhome.ui.screens.DiscoverScreen
import com.bataklik.brewhome.ui.theme.BrewHomeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * De testklasse voor het Discover-scherm.
 * @property rule De regel die de test uitvoert.
 * @see DiscoverScreen
 */
class DiscoverScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            BrewHomeTheme {
                DiscoverScreen(
                    beerApiState = BeerApiState
                        .SuccessBeers(BEERS),
                    goToDetail = {})
            }
        }
    }

    /**
     * Controleert of de titel van het Discover-scherm
     */
    @Test
    fun verifyDiscoverScreenTitle() {
        rule.onNodeWithContentDescription("txtDiscover")
            .assertIsDisplayed()
            .assertTextContains(
                value = "Products",
                substring = false,
                ignoreCase = false
            )
    }

    /**
     * Controleren of de lijst met bieren wordt
     * weergegeven op het Discover-scherm.
     */
    @Test
    fun verifyDiscoverScreenInitialBeers() {
        BEERS.forEachIndexed { _, item ->
            rule.onNode(hasText(item.name))
                .assertIsDisplayed()
        }
    }
}