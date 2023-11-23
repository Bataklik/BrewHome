package com.example.brewhome.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.brewhome.ui.components.favorites.FavoritesHeader
import com.example.brewhome.ui.components.favorites.FavoritesList

@Composable
fun FavoritesSheet(closeSheet: suspend () -> Unit) {
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FavoritesHeader(closeSheet)
        FavoritesList(listState)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
fun FavoritesSheetPreview() {
    FavoritesSheet(closeSheet = { })
}