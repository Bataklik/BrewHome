package com.example.brewhome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.brewhome.components.BeerCard
import com.example.brewhome.viewmodel.BeerViewModel

@Composable
fun DiscoverScreen(viewModel:BeerViewModel,modifier: Modifier = Modifier) {
    val beers = viewModel.beersState.value
    print(beers)
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
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 15.dp),
            columns = GridCells.Adaptive(minSize = 200.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)

        ) {
            item {
                BeerCard(
                    name = "Punk IPA 2007 - 2010",
                    imageUrl = "https://images.punkapi.com/v2/39.png",
                    tagline = "Post Modern Classic. Spiky. Tropical. Hoppy."
                )
            }
            item {
                BeerCard(
                    name = "Black Hammer",
                    imageUrl = "https://images.punkapi.com/v2/32.png",
                    tagline = "Our Ruthless IPA With A Dark Malt Twist."
                )
            }
            item {
                BeerCard(
                    name = "AB:08",
                    imageUrl = "https://images.punkapi.com/v2/9.png",
                    tagline = "Deconstructed Blonde Imperial Stout."
                )
            }
            item {
                BeerCard(
                    name = "Kohatu - IPA Is Dead",
                    imageUrl = "https://images.punkapi.com/v2/2.png",
                    tagline = "Single Hop India Pale Ale."
                )
            }
        }
    }
}
