package com.example.brewhome.ui.components.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.brewhome.model.Beer

@Composable
fun FavoritesList(
    listState: LazyListState,
    favoriteBeers: List<Beer>,
    deleteFromFavoriteBeers: (Int) -> Unit,
    isBeerInFavorites: (Int) -> Boolean
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        items(items = favoriteBeers) { beer ->
            val isFavorite = isBeerInFavorites(beer.id)
            FavoritesBeerItem(
                beer = beer,
                isFavorite = isFavorite,
                deleteFromFavoriteBeers = deleteFromFavoriteBeers
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
fun FavoritesListPreview() {
    val listState = rememberLazyListState()
    FavoritesList(
        listState = listState,
        favoriteBeers = listOf(),
        deleteFromFavoriteBeers = { },
        isBeerInFavorites = { true }
    )
}

