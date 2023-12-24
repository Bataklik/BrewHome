package com.bataklik.brewhome.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.model.BeerDetail
import com.bataklik.brewhome.network.BeerDetailApiState
import com.bataklik.brewhome.ui.components.beerDetail.BeerAbv
import com.bataklik.brewhome.ui.components.beerDetail.BeerAcidity
import com.bataklik.brewhome.ui.components.beerDetail.BeerDescription
import com.bataklik.brewhome.ui.components.beerDetail.BeerDetailHeader
import com.bataklik.brewhome.ui.components.beerDetail.BeerEbc
import com.bataklik.brewhome.ui.components.beerDetail.BeerFirstBrew
import com.bataklik.brewhome.ui.components.beerDetail.BeerSRM
import com.bataklik.brewhome.ui.components.beerDetail.BeerTagline
import com.bataklik.brewhome.ui.components.beerDetail.BeerTitle

@Composable
fun BeerDetailScreen(
    beerDetailApiState: BeerDetailApiState,
    addBeerToFavorites: () -> Unit,
    isBeerInFavorites: (Int) -> Boolean,
) {
    when (beerDetailApiState) {
        is BeerDetailApiState.ErrorBeer -> {
            BeerErrorScreen()
        }

        is BeerDetailApiState.LoadingBeer -> {
            BeerLoadingScreen()
        }

        is BeerDetailApiState.SuccessBeer -> {
            val currentBeer = beerDetailApiState
                .beer
            val beerFavorite = isBeerInFavorites(currentBeer.id)
            BeerSuccessScreen(
                currentBeer = currentBeer,
                addBeerToFavorites = addBeerToFavorites,
                isBeerInFavorites = beerFavorite
            )
        }

    }
}

@Composable
private fun BeerLoadingScreen() {
    Text(text = "Loading...")
}

@Composable
private fun BeerSuccessScreen(
    currentBeer: BeerDetail,
    addBeerToFavorites: () -> Unit,
    isBeerInFavorites: Boolean,
) {


    Column(
        modifier = Modifier.fillMaxHeight(),
    ) {
        BeerDetailHeader(currentBeer.imageUrl)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(1) {
                BeerTitle(currentBeer.name, addBeerToFavorites, isBeerInFavorites)
                BeerTagline(currentBeer.tagline)
                BeerFirstBrew(currentBeer.firstBrewed)
                BeerAbv(currentBeer.abv)
                BeerEbc(currentBeer.ebc)
                BeerAcidity(currentBeer.ph)
                BeerSRM(currentBeer.srm)
                BeerDescription(currentBeer.description)
            }
        }
    }
}


@Composable
fun BeerErrorScreen() {
    Text(text = "An error occurred")
}

@Preview(showBackground = true)
@Composable
fun BeerDetailScreenPreview() {
    //BeerDetailScreen(BeerViewModel())
}

