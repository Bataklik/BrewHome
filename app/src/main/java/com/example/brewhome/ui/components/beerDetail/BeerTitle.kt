package com.example.brewhome.ui.components.beerDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.brewhome.R

@Composable
fun BeerTitle(
    beerTitle: String,
    addBeerToFavorites: () -> Unit,
    beerFavorite: () -> Boolean,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        BeerTitleText(beerTitle)
        Spacer(modifier = Modifier.width(5.dp))
        BeerFavorite(addBeerToFavorites, beerFavorite)
    }
}

@Composable
private fun BeerTitleText(beerTitle: String) {
    Text(
        text = beerTitle,
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun BeerFavorite(addBeerToFavorites: () -> Unit, beerFavorite: () -> Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        BeerFavoriteLabel()
        FavoriteIcon(addBeerToFavorites, beerFavorite)
    }
}

@Composable
private fun BeerFavoriteLabel() {
    Text(
        text = "Favorite: ",
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun FavoriteIcon(addBeerToFavorites: () -> Unit, isBeerInFavorites: () -> Boolean) {
    var isFavorite by remember { mutableStateOf(false) }
    val heartPlusIcon = painterResource(id = R.drawable.heart_plus_24px)
    val heartCheckIcon = painterResource(id = R.drawable.heart_check_24px)

    // Net hetzelfde als UseEffect van React
    LaunchedEffect(isFavorite) {
        isFavorite = isBeerInFavorites()
    }
    IconButton(onClick = {
        if (!isFavorite) {
            isFavorite = true
            addBeerToFavorites()
        }
    }) {
        Icon(
            painter = if (!isFavorite) heartPlusIcon else heartCheckIcon,
            tint = if (!isFavorite) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.tertiary,
            contentDescription = if (!isFavorite) "Unfavorite" else "Favorite"
        )
    }
}
