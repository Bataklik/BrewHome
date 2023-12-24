package com.bataklik.brewhome.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.ui.screens.FavoritesSheet

/**
 * Composable functie die een Bottom Sheet weergeeft met favoriete bieren en de belangrijkste inhoud.
 * @param closeSheet Een suspend functie dat wordt uitgevoerd bij het sluiten van het sheet.
 * @param scaffoldState De toestand van het BottomSheetScaffold.
 * @param favoriteBeers De staat van de lijst met favoriete bieren.
 * @param deleteFromFavoriteBeers Een functie om een bier te verwijderen uit de lijst met favoriete bieren.
 * @param content De hoofdinhoud van het scherm.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheet(
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    closeSheet: suspend () -> Unit,
    scaffoldState: BottomSheetScaffoldState,
    favoriteBeers: State<List<Beer>>,
    deleteFromFavoriteBeers: (Int) -> Unit,
    content: @Composable () -> Unit,
) {
    BottomSheetScaffold(
        sheetSwipeEnabled = false,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
                FavoritesSheet(
                    modifier = modifier,
                    closeSheet = { closeSheet() },
                    favoriteBeers = favoriteBeers.value,
                    deleteFromFavoriteBeers = deleteFromFavoriteBeers,
                    isLandscape = isLandscape,
                )
            }
        },
    ) {
        content()
    }
}