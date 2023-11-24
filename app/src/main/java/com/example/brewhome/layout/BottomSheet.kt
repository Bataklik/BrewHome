package com.example.brewhome.layout

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.brewhome.ui.screens.FavoritesSheet

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheet(
    closeSheet: suspend () -> Unit,
    scaffoldState: BottomSheetScaffoldState,
    content: @Composable () -> Unit
) {
    BottomSheetScaffold(
        sheetContent = { FavoritesSheet(closeSheet = { closeSheet() }) },
        sheetSwipeEnabled = false,
        scaffoldState = scaffoldState
    ) {
        content()
    }
}