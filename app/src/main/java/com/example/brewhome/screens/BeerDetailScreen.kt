package com.example.brewhome.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.brewhome.R
import com.example.brewhome.data.BeerDetail
import com.example.brewhome.network.BeerDetailApiState
import com.example.brewhome.viewmodel.BeerViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BeerDetailScreen(beerViewModel: BeerViewModel) {

    when (val beerDetailApiState = beerViewModel.beerDetailApiState) {

        is BeerDetailApiState.ErrorBeer -> {
            BeerErrorScreen()
        }

        is BeerDetailApiState.LoadingBeer -> {
            BeerLoadingScreen()
        }

        is BeerDetailApiState.SuccessBeer -> {
            val currentBeer = beerDetailApiState.beer
            BeerSuccessScreen(currentBeer)
        }
    }
}

@Composable
private fun BeerLoadingScreen() {
    Text(text = "Loading...")
}

@Composable
private fun BeerSuccessScreen(currentBeer: BeerDetail) {
    Column(
        modifier = Modifier.fillMaxHeight(),
    ) {
        BeerDetailHeader(currentBeer.imageUrl)
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = currentBeer.name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        tint = MaterialTheme.colorScheme.tertiary,
                        contentDescription = "Favorite"
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "First brewed: ",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = currentBeer.firstBrewed,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun BeerDetailHeader(imageUrl: String) {
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


@Composable
fun BeerErrorScreen() {
    Text(text = "An error occurred")
}


@Preview(showBackground = true)
@Composable
fun BeerDetailScreenPreview() {
    BeerDetailScreen(BeerViewModel())
}

