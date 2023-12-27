package com.bataklik.brewhome.ui.components.discover

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.R
import com.bataklik.brewhome.layout.components.NoItemsCard
import com.bataklik.brewhome.network.BeerApiState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DiscoverBeerList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    beerApiState: BeerApiState,
    goToDetail: (beerId: Int) -> Unit
) {
    AnimatedContent(
        label = "beerApiState",
        targetState = beerApiState,
        transitionSpec = {
            slideIntoContainer(
                animationSpec = tween(500, easing = EaseIn),
                towards = Up
            ).togetherWith(
                slideOutOfContainer(
                    animationSpec = tween(500, easing = EaseOut),
                    towards = Down
                )
            )
        }
    ) { targetState ->
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .semantics { contentDescription = "lstDiscoverBeers" },
            verticalArrangement = Arrangement.spacedBy(5.dp),
            state = listState
        ) {
            when (targetState) {
                is BeerApiState.ErrorBeers -> {
                    item {
                        NoItemsCard(
                            modifier = modifier,
                            painterIcon = painterResource(id = R.drawable.warning_24px),
                            titleText = "Error: No Beers",
                            descriptionText = "Oops! It seems like an oversight occurred. There are currently no beers in your dashboard."
                        )
                    }
                }

                is BeerApiState.LoadingBeers -> {
                    item {
                        Text(text = "Loading the API...")
                    }
                }

                is BeerApiState.SuccessBeers -> {
                    items(targetState.beers) { beer ->
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
}