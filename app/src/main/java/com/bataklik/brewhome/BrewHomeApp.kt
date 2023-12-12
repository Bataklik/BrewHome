package com.example.brewhome

import Screen
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.brewhome.layout.AppBar
import com.example.brewhome.layout.BottomAppBar
import com.example.brewhome.layout.BottomSheet
import com.example.brewhome.ui.screens.BeerDetailScreen
import com.example.brewhome.ui.screens.DiscoverScreen
import com.example.brewhome.ui.screens.SearchScreen
import com.example.brewhome.viewmodel.BeerViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrewHomeApp(
    navController: NavHostController = rememberNavController(),
    beerViewModel: BeerViewModel = viewModel(factory = BeerViewModel.Factory),
) {
    // region Behandeling van BottomSheet
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Hidden,
        skipHiddenState = false,
    )
    val openSheet = suspend {
        run {
            coroutineScope {
                sheetState.expand()
            }
        }
    }
    val closeSheet = suspend {
        run {
            coroutineScope {
                sheetState.hide()
            }
        }
    }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState,
    )
    // endregion

    // region Behandeling van Navigation
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenTitle = when (backStackEntry?.destination?.route) {
        Screen.Discover.route -> Screen.Discover.toString()
        Screen.Search.route -> Screen.Search.toString()
        Screen.BeerDetail().route -> Screen.BeerDetail().toString()
        else -> Screen.Discover.toString()
    }
    val canNavigateBack = {
        print(navController.previousBackStackEntry)
        print(navController.currentBackStack)
        navController.previousBackStackEntry != null
    }
    // endregion

    // region Schermnavigatie acties
    /**
     * Navigeer naar het vorige scherm in de applicatie.
     */
    val navigateUp: () -> Unit = { navController.navigateUp() }

    /***
     * Navigeer naar het "Discover"-scherm en
     * verwijder alle tussenliggende schermen,
     * inclusief "Search", uit de backstack.
     */
    val goToDiscover = {
        navController.navigate(Screen.Discover.route) {
            popUpTo(Screen.Search.route) {
                inclusive = true
            }
        }
    }

    /***
     *  Navigeer naar het "Search"-scherm en
     *  verwijder alle tussenliggende schermen,
     *  inclusief "Discover", uit de backstack.
     */
    val goToSearch = {
        navController.navigate(Screen.Search.route) {
            popUpTo(Screen.Discover.route) {
                inclusive = true
            }
        }
    }

    /**
     * Navigeer naar het "BeerDetail"-scherm en
     * toon informatie over het geselecteerde bier.
     * @param beerId Het ID van het geselecteerde bier.
     */
    val goToDetail = { beerId: Int ->
        beerViewModel
            .getBeerById(beerId)
        navController
            .navigate(Screen.BeerDetail(beerId).route)
    }
    // endregion

    // region Behandeling van Favorite beers
    val favoriteBeers = beerViewModel.uiListState.collectAsState()

    val addBeerToFavorites = {
        beerViewModel.addBeerToFavorites()
    }

    val deleteBeerFromFavorites = { id: Int ->
        beerViewModel.deleteFromFavoriteBeers(id)
    }

    val isBeerInFavorites = { id: Int -> runBlocking { beerViewModel.isBeerInFavorites(id) } }
    // endregion

    BottomSheet(
        closeSheet,
        scaffoldState,
        favoriteBeers,
        deleteBeerFromFavorites,
        isBeerInFavorites
    ) {
        Scaffold(
            topBar = {
                AppBar(
                    openSheet = { openSheet() },
                    navigateUp = navigateUp,
                    canNavigateBack = canNavigateBack,
                    currentScreenTitle = currentScreenTitle,
                )
            },
            bottomBar = {
                BottomAppBar(
                    goDiscover = goToDiscover,
                    goSearch = goToSearch
                )
            },

            ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Discover.route
                ) {
                    composable(Screen.Discover.route) {
                        DiscoverScreen(
                            beerApiState = beerViewModel.beerApiState,
                            goToDetail = goToDetail
                        )
                    }
                    composable(Screen.BeerDetail().route) {
                        BeerDetailScreen(
                            beerDetailApiState = beerViewModel.beerDetailApiState,
                            addBeerToFavorites = { addBeerToFavorites() },
                            isBeerInFavorites = isBeerInFavorites
                        )
                    }
                    composable(Screen.Search.route) {
                        SearchScreen()
                    }
                }
            }
        }
    }
}