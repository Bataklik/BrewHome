package com.example.brewhome.ui.screens

import android.annotation.SuppressLint
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
import com.example.brewhome.model.BeerDetail
import com.example.brewhome.network.BeerDetailApiState
import com.example.brewhome.ui.components.beerDetail.BeerAbv
import com.example.brewhome.ui.components.beerDetail.BeerAcidity
import com.example.brewhome.ui.components.beerDetail.BeerDescription
import com.example.brewhome.ui.components.beerDetail.BeerDetailHeader
import com.example.brewhome.ui.components.beerDetail.BeerEbc
import com.example.brewhome.ui.components.beerDetail.BeerFirstBrew
import com.example.brewhome.ui.components.beerDetail.BeerSRM
import com.example.brewhome.ui.components.beerDetail.BeerTagline
import com.example.brewhome.ui.components.beerDetail.BeerTitle

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BeerDetailScreen(
    beerDetailApiState: BeerDetailApiState,
) {
    when (beerDetailApiState) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp,vertical=10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            item {
                BeerTitle(currentBeer.name)
            }
            item {
                BeerTagline(currentBeer.tagline)
            }
            item {
                BeerFirstBrew(currentBeer.firstBrewed)
            }
            item {
                BeerAbv(currentBeer.abv)
            }
            item {
                BeerEbc(currentBeer.ebc)
            }
            item {
                BeerAcidity(currentBeer.ph)
            }
            item {
                BeerSRM(currentBeer.srm)
            }
            item {
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

