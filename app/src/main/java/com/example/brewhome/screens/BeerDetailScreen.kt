package com.example.brewhome.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.brewhome.viewmodel.BeerViewModel

@Composable
fun BeerDetailScreen(beerViewModel: BeerViewModel?, beerId: Int) {
    if (beerViewModel != null) {
        // TODO bier ophalen via ID
        val beer = beerViewModel
            .getBeerById(beerId)

        print("")
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Beer Detail Screen")
                Text(text = "Beer ID: $beerId")
            }
        }
    } else {
        ErrorScreen()
    }
}

@Composable
fun ErrorScreen() {
    Text(text = "An error occurred")
}


@Preview(showBackground = true)
@Composable
fun BeerDetailScreenPreview() {
    BeerDetailScreen(null, beerId = 192)
}

