package com.example.brewhome.ui.components.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.brewhome.R

@Composable
fun FavoritesBeerItem(beer: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            AsyncImage(
                model = "https://images.punkapi.com/v2/182.png",
                contentScale = ContentScale.Fit,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.homebrew),
                error = painterResource(id = R.drawable.homebrew),
                modifier = Modifier
                    .width(width = 40.dp)
                    .padding(all = 5.dp)
            )
            Column {
                Text(
                    text = "All Day Long - Prototype Challenge",
                    style = TextStyle(textDecoration = TextDecoration.Underline)
                )
                Text(text = "ABV: 2.6")
                Text(text = "EBC: 42")
                Text(text = "pH: 2.2")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.heart_minus_24px),
                    contentDescription = "Right arrow",
                    tint = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
    }
}