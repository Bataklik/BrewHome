package com.example.brewhome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.brewhome.components.BeerCard
import com.example.brewhome.data.BeerViewModelState
import com.example.brewhome.viewmodel.BeerViewModel

@Composable
fun DiscoverScreen(
    beerViewModel: BeerViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyGridState()
    val beerViewModelState by beerViewModel.uiState.collectAsState()
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
            verticalArrangement = Arrangement.spacedBy(5.dp),
            state = listState

        ) {
            items(beerViewModelState.currentBeers) {
                BeerCard(
                    navController,
                    imageUrl = it.image_url,
                    name = it.name,
                    tagline = it.tagline,
                    firstBrewed = it.first_brewed
                )
            }
        }
    }
}
