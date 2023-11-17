package com.example.brewhome.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.brewhome.components.CloseButton

@Composable
fun FavoritesSheet(closeSheet: suspend () -> Unit) {
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        FavoritesHeader(closeSheet)
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            state = listState
        ){

        }
    }
}

@Composable
private fun FavoritesHeader(closeSheet: suspend () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Favorites", style = MaterialTheme.typography.titleLarge)
        CloseButton(closeSheet)

    }
}

@Preview
@Composable
fun FavoritesSheetPreview() {
    FavoritesSheet(closeSheet = { })
}