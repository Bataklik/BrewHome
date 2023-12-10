package com.example.brewhome.layout

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.brewhome.model.Beer
import com.example.brewhome.ui.screens.FavoritesSheet

/**
 * Composable functie die een Bottom Sheet weergeeft met favoriete bieren en de belangrijkste inhoud.
 * @param closeSheet Een suspend functie dat wordt uitgevoerd bij het sluiten van het sheet.
 * @param scaffoldState De toestand van het BottomSheetScaffold.
 * @param favoriteBeers De staat van de lijst met favoriete bieren.
 * @param deleteFromFavoriteBeers Een functie om een bier te verwijderen uit de lijst met favoriete bieren.
 * @param isBeerInFavorites Een functie die controleert of een bier in de lijst met favoriete bieren staat.
 * @param content De hoofdinhoud van het scherm.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheet(
    closeSheet: suspend () -> Unit,
    scaffoldState: BottomSheetScaffoldState,
    favoriteBeers: State<List<Beer>>,
    deleteFromFavoriteBeers: (Int) -> Unit,
    isBeerInFavorites: (Int) -> Boolean,
    content: @Composable () -> Unit,
) {
    BottomSheetScaffold(
        sheetContent = {
            FavoritesSheet(
                closeSheet = { closeSheet() },
                favoriteBeers = favoriteBeers.value,
                deleteFromFavoriteBeers = deleteFromFavoriteBeers,
                isBeerInFavorites = isBeerInFavorites
            )
        },
        sheetSwipeEnabled = false,
        scaffoldState = scaffoldState
    ) {
        content()
    }
}