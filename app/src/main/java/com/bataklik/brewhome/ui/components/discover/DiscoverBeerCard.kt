package com.bataklik.brewhome.ui.components.discover

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bataklik.brewhome.R

@Composable
fun DiscoverBeerCard(
    goToDetail: (beerId: Int) -> Unit,
    imageUrl: String,
    name: String,
    tagline: String,
    firstBrewed: String,
    beerId: Int
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .clickable {
                goToDetail(beerId)
            }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BeerImage(imageUrl)
            BeerTextFields(name, tagline, firstBrewed)
        }
    }
}

@Composable
private fun BeerTextFields(
    name: String,
    tagline: String,
    firstBrewed: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            BeerTitleField(name)
            BeerTaglineField(tagline)
            BeerDateField(firstBrewed)
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Right arrow",
            tint = MaterialTheme.colorScheme.secondary,
        )
    }

}

@Composable
private fun BeerTaglineField(tagline: String) {
    val beerTagline = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.secondary,
    )
    Text(
        text = tagline,
        style = beerTagline,
        modifier = Modifier
            .padding(5.dp),
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}

@Composable
private fun BeerTitleField(name: String) {
    val beerTitle = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.secondary
    )
    Text(
        text = name,
        style = beerTitle,
        modifier = Modifier
            .padding(5.dp),
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun BeerImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentScale = ContentScale.Fit,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.homebrew),
        error = painterResource(id = R.drawable.homebrew),
        modifier = Modifier
            .width(width = 125.dp)
            .padding(all = 5.dp)
    )
}

@Composable
private fun BeerDateField(date: String) {
    val beerDateText = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.secondary
    )
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.local_cafe_24px),
            contentDescription = "First brewed",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = date,
            style = beerDateText,
            fontSize = 14.sp
        )

    }
}