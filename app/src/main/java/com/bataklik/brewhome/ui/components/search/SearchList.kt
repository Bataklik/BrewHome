package com.bataklik.brewhome.ui.components.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.R
import com.bataklik.brewhome.layout.components.NoItemsCard
import com.bataklik.brewhome.network.BeerSearchApiState
import com.bataklik.brewhome.ui.components.discover.DiscoverBeerCard

@Composable
fun SearchList(
    modifier: Modifier,
    listState: LazyListState,
    beerSearchApiState: BeerSearchApiState,
    goToDetail: (Int) -> Unit
) {
    val searchTitle = "Search for any beer"
    val searchDescription =
        "Explore a vast array of beers from around the world, encompassing various styles, flavors, and origins.."

    // Error messages
    val searchErrorTitle = "Failed to fetch search results"
    val searchErrorDescription =
        "An error occurred while trying to search for beers. Please try again later."

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        state = listState
    ) {
        when (beerSearchApiState) {
            is BeerSearchApiState.ErrorBeers -> {
                item {
                    NoItemsCard(
                        modifier = modifier,
                        painterIcon = painterResource(id = R.drawable.search_24px),
                        titleText = searchErrorTitle,
                        descriptionText = searchErrorDescription
                    )
                }
            }

            is BeerSearchApiState.LoadingBeers -> {
                item {
                    NoItemsCard(
                        modifier = modifier,
                        painterIcon = painterResource(id = R.drawable.search_24px),
                        titleText = searchTitle,
                        descriptionText = searchDescription
                    )
                }
            }

            is BeerSearchApiState.SuccessSearchBeers -> {
                items(beerSearchApiState.searchBeer) { beer ->
                    DiscoverBeerCard(
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