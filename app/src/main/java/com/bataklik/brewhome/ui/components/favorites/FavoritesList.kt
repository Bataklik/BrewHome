package com.bataklik.brewhome.ui.components.favorites

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.R
import com.bataklik.brewhome.layout.components.NoItemsCard
import com.bataklik.brewhome.model.Beer

@Composable
fun FavoritesList(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    favoriteBeers: List<Beer>,
    deleteFromFavoriteBeers: (Int) -> Unit,
) {

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        if (favoriteBeers.isEmpty())
            item {
                NoItemsCard(
                    modifier,
                    painterIcon = painterResource(id = R.drawable.favorite_24px),
                    titleText = "No Favorite Beers Yet",
                    descriptionText = "Looks like you haven't added any beers to your favorites. Start browsing and find some beers you like!"
                )
            }
        else
            items(items = favoriteBeers) { beer ->
                //val isFavorite = isBeerInFavorites(beer.id)
                FavoritesBeerItem(
                    beer = beer,
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
    )
}

