package com.bataklik.brewhome.utils

import androidx.compose.ui.res.stringResource
import com.bataklik.brewhome.R

/**
 * De klasse die de schermen van de app bevat.
 * @property route De route van het scherm.
 */
sealed class Screen(val route: String) {
    object Discover : Screen("screenDiscover") {
        override fun toString(): String {
            return "Discover"
        }
    }

    object Search : Screen("screenSearch"){
        override fun toString(): String {
            return "Search"
        }
    }
    object BeerDetail: Screen("screenBeerDetail"){
        override fun toString(): String {
            return "Beer detail"
        }
    }
}
