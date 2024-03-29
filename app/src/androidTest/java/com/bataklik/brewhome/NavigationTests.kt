package com.bataklik.brewhome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.bataklik.brewhome.ui.theme.BrewHomeTheme
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * De testklasse voor de navigatie.
 * @property rule De regel die de test uitvoert.
 * @see BrewHomeApp
 */
class NavigationTests {
    @get:Rule
    val rule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        rule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController
                .navigatorProvider
                .addNavigator(ComposeNavigator())
            BrewHomeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme
                        .colorScheme
                        .background
                ) {
                    BrewHomeApp(navController)
                }
            }
        }
    }

    /**
     * Controleert of de start scherm juiste titel heeft
     */
    @Test
    fun verifyStartDestination() {
        Thread
            .sleep(2000)
        rule
            .onNodeWithContentDescription("txtCurrentScreenTitle")
            .assertIsDisplayed()
            .assertTextEquals("Discover")
        val expected = 2
        val actual = navController
            .backStack
            .size
        Assert.assertEquals(
            expected, actual
        )
    }

    /**
     * Controleert of de detail scherm juiste titel heeft
     */
    @Test
    fun verifyDetailDestination() {
        rule
            .onAllNodesWithContentDescription("crdDiscoverBeer")
            .onFirst()
            .performClick()

        rule
            .onNodeWithContentDescription("txtCurrentScreenTitle")
            .assertIsDisplayed()
            .assertTextContains(
                value = "Beer detail",
                substring = false,
                ignoreCase = false
            )
        val expected = 3
        val actual = navController
            .backStack
            .size
        Assert.assertEquals(
            expected, actual
        )
    }

    /**
     * Controleert of de zoek scherm juiste titel heeft
     */
    @Test
    fun verifySearchDestination() {
        rule
            .onNodeWithContentDescription("btnGoSearch")
            .performClick()
        rule
            .onNodeWithContentDescription("txtCurrentScreenTitle")
            .assertIsDisplayed()
            .assertTextEquals("Search")
        val expected = 2
        val actual = navController
            .backStack
            .size
        Assert.assertEquals(
            expected, actual
        )
    }

    /**
     * Controleert of de favorieten scherm juiste titel heeft
     */
    @Test
    fun verifyFavoriteDestination() {
        Thread
            .sleep(2000)
        rule
            .onNodeWithContentDescription("btnGoFavorite")
            .performClick()
        rule
            .onNodeWithContentDescription("txtFavoriteScreenTitle")
            .assertTextContains(
                "Favorites",
                ignoreCase = false,
                substring = false
            )
        val expected = 2
        val actual = navController
            .backStack
            .size
        Assert.assertEquals(
            expected, actual
        )
    }
}