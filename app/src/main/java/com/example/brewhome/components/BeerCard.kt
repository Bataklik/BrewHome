package com.example.brewhome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.brewhome.R

@Composable
fun BeerCard(imageUrl: String, name: String, tagline: String) {
    val titleWithUnderlineStyle = MaterialTheme.typography.titleMedium.copy(
        textDecoration = TextDecoration.Underline
    )
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 200.dp, height = 180.dp)
            .padding(horizontal = 5.dp, vertical = 5.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            AsyncImage(
                model = imageUrl,
                contentScale = ContentScale.Fit,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.homebrew),
                error = painterResource(id = R.drawable.homebrew),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = name,
                    style = titleWithUnderlineStyle,
                    modifier = Modifier
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = tagline,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(5.dp)
                        .width(150.dp),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}