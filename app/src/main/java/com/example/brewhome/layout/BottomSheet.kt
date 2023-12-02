package com.example.brewhome.layout

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.brewhome.model.Beer
import com.example.brewhome.ui.screens.FavoritesSheet

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
                isBeerInFavorites
            )
        },
        sheetSwipeEnabled = false,
        scaffoldState = scaffoldState
    ) {
        content()
    }
}