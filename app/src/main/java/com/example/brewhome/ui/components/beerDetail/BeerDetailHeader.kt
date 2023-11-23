package com.example.brewhome.ui.components.beerDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.brewhome.R

@Composable
 fun BeerDetailHeader(imageUrl: String) {
    Row(
        modifier = Modifier
            .background(color = Color(0xFFF5F5F5))
            .border(width = 20.dp, color = Color.White)
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.homebrew),
            error = painterResource(id = R.drawable.homebrew),
            modifier = Modifier
                .width(width = 85.dp)
                .padding(all = 5.dp)
        )
    }
}