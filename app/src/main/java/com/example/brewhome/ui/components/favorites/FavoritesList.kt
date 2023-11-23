package com.example.brewhome.ui.components.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesList(listState: LazyListState) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        state = listState
    ) {
        items(items = listOf("Stella", "Heineken")) { beer ->
            BeerFavoriteItem(beer = beer)
        }
    }
}


@Composable
fun BeerFavoriteItem(beer: String) {
    Row {
        Column {
            Text(text = beer)
            Text(text = "ABV: 6%")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
fun FavoritesListPreview() {
    val listState = rememberLazyListState()
    FavoritesList(listState = listState)
}

