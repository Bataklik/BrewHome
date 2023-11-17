package com.example.brewhome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.brewhome.Destinations
import com.example.brewhome.components.BeerCard
import com.example.brewhome.network.BeerApiState
import com.example.brewhome.viewmodel.BeerViewModel

@Composable
fun DiscoverScreen(
    beerViewModel: BeerViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    fun goTeDetail(beerId: Int) {
        beerViewModel.getBeerById(beerId)
        navController.navigate("${Destinations.BeerDetail.name}/${beerId}")
    }

    val listState = rememberLazyListState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = "Products",
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            state = listState
        ) {
            when (val beerApiState = beerViewModel.beerApiState) {
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
                            name = beer.name,
                            tagline = beer.tagline,
                            firstBrewed = beer.firstBrewed,
                            imageUrl = beer.imageUrl,
                            goTeDetail = {
                                goTeDetail(beerId = beer.id)
                            }
                        )
                    }
                }

            }
        }
    }
}
