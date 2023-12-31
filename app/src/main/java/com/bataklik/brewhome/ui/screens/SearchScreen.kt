package com.bataklik.brewhome.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bataklik.brewhome.network.BeerSearchApiState
import com.bataklik.brewhome.ui.components.search.SearchList
import com.bataklik.brewhome.ui.components.search.SearchTextField
import com.bataklik.brewhome.ui.theme.BrewHomeTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    beerSearchApiState: BeerSearchApiState,
    goToDetail: (Int) -> Unit,
    getBeersByName: (String) -> Unit,
) {
    val listState = rememberLazyListState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        SearchTextField(
            modifier,
            onSearchAction = getBeersByName
        )
        SearchList(
            modifier = modifier,
            listState = listState,
            beerSearchApiState = beerSearchApiState,
            goToDetail = goToDetail
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SearcScreenPreview() {
    BrewHomeTheme {
        SearchScreen(
            beerSearchApiState = BeerSearchApiState.SuccessSearchBeers(emptyList()),
            goToDetail = {},
            getBeersByName = {}
        )
    }
}
