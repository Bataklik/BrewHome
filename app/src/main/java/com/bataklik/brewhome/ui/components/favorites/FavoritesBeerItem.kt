package com.example.brewhome.ui.components.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.brewhome.R
import com.example.brewhome.model.Beer

@Composable
fun FavoritesBeerItem(
    modifier: Modifier = Modifier,
    beer: Beer,
    deleteFromFavoriteBeers: (Int) -> Unit,
    isFavorite: Boolean
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(vertical = 2.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BeerImage(modifier, beer.imageUrl)
                Column(modifier = modifier.padding(vertical = 2.dp)) {
                    BeerTitle(beer.name)
                    Spacer(modifier = modifier.padding(vertical = 2.dp))
                    BeerTagline(beer.tagline)
                    BeerAbv(beer.abv)
                }
                BeerUnfavoriteButton(deleteFromFavoriteBeers, beer.id)
            }
        }
    }
}

@Composable
private fun BeerUnfavoriteButton(
    deleteFromFavoriteBeers: (Int) -> Unit,
    beerId: Int
) {
    IconButton(onClick = { deleteFromFavoriteBeers(beerId) }) {
        Icon(
            painter = painterResource(R.drawable.heart_minus_24px),
            contentDescription = "Unfavorite button",
            tint = MaterialTheme.colorScheme.tertiary,
        )
    }
}

@Composable
private fun BeerAbv(beerAbv: Double) {
    Text(
        text = "ABV: $beerAbv",
        style = TextStyle(
            fontStyle = FontStyle.Italic
        )
    )
}

@Composable
private fun BeerTagline(beerTagline: String) {
    Text(
        text = beerTagline,
        maxLines = 1,
        style = TextStyle(
            fontStyle = FontStyle.Italic
        ),
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun BeerTitle(beerName: String) {
    Text(
        text = beerName,
        maxLines = 2,
        style = TextStyle(
            textDecoration = TextDecoration.Underline,
            fontSize = 18.sp
        ),
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun BeerImage(
    modifier: Modifier,
    beerImageUrl: String
) {
    AsyncImage(
        model = beerImageUrl,
        contentScale = ContentScale.Fit,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.homebrew),
        error = painterResource(id = R.drawable.homebrew),
        modifier = modifier
            .width(width = 40.dp)
            .height(height = 120.dp)
            .padding(all = 5.dp)
    )
}