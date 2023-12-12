package com.bataklik.brewhome.layout

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.layout.components.AppBarBackButton
import com.bataklik.brewhome.layout.components.AppBarFavoritesButton
import com.bataklik.brewhome.layout.components.AppBarTitle

/**
 * Composable functie die de bovenste navigatiebalk (AppBar) weergeeft.
 * @param modifier Aanvullende opmaakinstellingen voor de AppBar.
 * @param openSheet Een suspend blok dat wordt uitgevoerd wanneer de sheet wordt geopend.
 * @param navigateUp Een functie die wordt aangeroepen bij het klikken op de navigatieknop.
 * @param canNavigateBack Een functie die bepaalt of er terug genavigeerd kan worden.
 * @param currentScreenTitle De titel van het huidige scherm.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(
    modifier: Modifier = Modifier,
    openSheet: suspend () -> Unit,
    navigateUp: () -> Unit,
    canNavigateBack: () -> Boolean,
    currentScreenTitle: String,
) {
    val coroutineScope = rememberCoroutineScope()
    TopAppBar(
        modifier = modifier
            .height(70.dp)
            .padding(vertical = 5.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.secondary
        ),
        navigationIcon = {
            if (canNavigateBack()) {
                AppBarBackButton(navigateUp)
            }
        },
        actions = {
            AppBarFavoritesButton(coroutineScope, openSheet, modifier)
        },

        title = {
            AppBarTitle(modifier, currentScreenTitle)
        },
    )
}

