package com.bataklik.brewhome.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.model.Beer
import com.bataklik.brewhome.ui.components.favorites.FavoritesHeader
import com.bataklik.brewhome.ui.components.favorites.FavoritesList
import com.bataklik.brewhome.ui.theme.BrewHomeTheme

@Composable
fun FavoritesSheet(
    closeSheet: suspend () -> Unit,
    favoriteBeers: List<Beer>,
    deleteFromFavoriteBeers: (Int) -> Unit,
    isBeerInFavorites: (Int) -> Boolean
) {
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment
            .CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(10.dp)
    ) {
        FavoritesHeader(closeSheet)
        FavoritesList(
            listState = listState,
            favoriteBeers = favoriteBeers,
            deleteFromFavoriteBeers = deleteFromFavoriteBeers,
            isBeerInFavorites = isBeerInFavorites
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
fun FavoritesSheetPreview() {
    BrewHomeTheme {
        FavoritesSheet(closeSheet = { },
            favoriteBeers = listOf(),
            deleteFromFavoriteBeers = { },
            isBeerInFavorites = { true })
    }
}