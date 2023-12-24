package com.bataklik.brewhome.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bataklik.brewhome.R
import com.bataklik.brewhome.layout.components.BottomAppbarItem

/**
 * Composable functie die de onderste navigatiebalk (BottomAppBar) weergeeft.
 * @param modifier Aanvullende opmaakinstellingen voor de BottomAppBar.
 * @param goDiscover Een functie die wordt aangeroepen bij het klikken op de "Discover" knop om naar het ontdekkingscherm te navigeren.
 * @param goSearch Een functie die wordt aangeroepen bij het klikken op de "Search" knop om naar het zoekscherm te navigeren.
 */
@Composable
fun BottomBar(modifier: Modifier = Modifier, goDiscover: () -> Unit, goSearch: () -> Unit) {
    BottomAppBar(
        containerColor = MaterialTheme
            .colorScheme
            .tertiary,
        contentColor = MaterialTheme
            .colorScheme
            .primary,
        content = {
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomAppbarItem(
                    modifier = modifier,
                    goToAction = goDiscover,
                    contentDescriptionIB = "btnGoDiscover",
                    contentDescriptionI = "goDiscoverIcon",
                    painter = R.drawable.explore_24px
                )
                BottomAppbarItem(
                    modifier = modifier,
                    goToAction = goSearch,
                    contentDescriptionIB = "btnGoSearch",
                    contentDescriptionI = "goSearchIcon",
                    painter = R.drawable.search_24px
                )
            }
        }
    )
}

