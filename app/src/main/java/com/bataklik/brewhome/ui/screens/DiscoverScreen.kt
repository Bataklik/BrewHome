package com.example.brewhome.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.brewhome.network.BeerApiState
import com.example.brewhome.ui.components.discover.DiscoverBeerList
import com.example.brewhome.ui.components.discover.DiscoverTitle

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    beerApiState: BeerApiState,
    goToDetail: (beerId: Int) -> Unit,
) {
    val listState = rememberLazyListState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        DiscoverTitle()
        DiscoverBeerList(listState, beerApiState, goToDetail)
    }
}

