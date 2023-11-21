package com.example.brewhome.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.brewhome.network.BeerApiState
import com.example.brewhome.ui.components.BeerCard

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

@Composable
private fun DiscoverBeerList(
    listState: LazyListState,
    beerApiState: BeerApiState,
    goToDetail: (beerId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        state = listState
    ) {
        when (beerApiState) {
            is BeerApiState.ErrorBeers -> {
                item {
                    Box {
                        Text("Failed to load the API")
                    }
                }
            }

            is BeerApiState.LoadingBeers -> {
                item {
                    Text(text = "Loading the API...")
                }
            }

            is BeerApiState.SuccessBeers -> {
                items(beerApiState.beers) { beer ->
                    BeerCard(
                        beerId = beer.id,
                        name = beer.name,
                        tagline = beer.tagline,
                        firstBrewed = beer.firstBrewed,
                        imageUrl = beer.imageUrl,
                        goToDetail = goToDetail
                    )
                }
            }
        }
    }
}

@Composable
private fun DiscoverTitle(title: String = "Products") {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.tertiary,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}
