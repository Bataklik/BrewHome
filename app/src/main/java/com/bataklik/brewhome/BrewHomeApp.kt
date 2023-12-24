package com.bataklik.brewhome

import Screen
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.bataklik.brewhome.layout.AppBar
import com.bataklik.brewhome.layout.BottomBar
import com.bataklik.brewhome.layout.BottomSheet
import com.bataklik.brewhome.ui.screens.BeerDetailScreen
import com.bataklik.brewhome.ui.screens.DiscoverScreen
import com.bataklik.brewhome.ui.screens.SearchScreen
import com.bataklik.brewhome.viewmodel.BeerViewModel
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrewHomeApp(
    navController: NavHostController = rememberNavController(),
    beerViewModel: BeerViewModel = viewModel(factory = BeerViewModel.Factory),
    isLandscape: Boolean = false,
) {
    // region Behandeling van BottomSheet
    /**
     * SheetState wordt gebruikt om de [BottomSheet] te tonen.
     */
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Hidden,
        skipHiddenState = false,
    )

    /**
     * Opent de [BottomSheet].
     */
    val openSheet = suspend {
        run {
            sheetState.expand()
        }
    }

    /**
     * Sluit de [BottomSheet].
     */
    val closeSheet = suspend {
        run {
            sheetState.hide()
        }
    }

    /**
     * ScaffoldState wordt gebruikt
     * om de [BottomSheet] te tonen.
     */
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState,
    )
    // endregion

    // region Behandeling van Navigation
    /**
     * Haal de huidige bestemmingsroute op uit de backstack.
     */
    val backStackEntry by navController.currentBackStackEntryAsState()

    /**
     * Geeft de titel van het huidige scherm terug.
     * @return [String] de titel van het huidige scherm.
     */
    val currentScreenTitle = when (backStackEntry?.destination?.route) {
        Screen.Discover.route -> Screen.Discover.toString()
        Screen.Search.route -> Screen.Search.toString()
        Screen.BeerDetail.route -> Screen.BeerDetail.toString()
        else -> Screen.Discover.toString()
    }

    /**
     * Controleer of er een vorig scherm in de backstack staat.
     * @return [Boolean] true als er een vorig scherm in de backstack staat, anders false.
     */
    val canNavigateBack = {
        navController.previousBackStackEntry != null
    }
    // endregion

    // region Schermnavigatie acties
    /**
     * Navigeer naar het vorige scherm in de applicatie.
     */
    val navigateUp: () -> Unit = { navController.navigateUp() }

    /**
     * Navigeer naar het "Discover"-scherm en
     * verwijder alle tussenliggende schermen,
     * inclusief "Search", uit de backstack.
     */
    val goToDiscover = {
        if (navController.currentBackStackEntry?.destination?.route != Screen.Discover.route) {
            navController.navigate(Screen.Discover.route) {
                popUpTo(Screen.Search.route) {
                    inclusive = true
                }
            }
        }
    }

    /**
     *  Navigeer naar het "Search"-scherm en
     *  verwijder alle tussenliggende schermen,
     *  inclusief "Discover", uit de backstack.
     */
    val goToSearch = {
        if (navController.currentBackStackEntry?.destination?.route != Screen.Search.route) {
            navController.navigate(Screen.Search.route) {
                popUpTo(Screen.Discover.route) {
                    inclusive = true
                }
            }
        }
    }

    /**
     * Navigeer naar het "BeerDetail"-scherm en
     * toon informatie over het geselecteerde bier.
     */
    val goToDetail = { beerId: Int ->
        if (navController.currentBackStackEntry?.destination?.route != Screen.BeerDetail.route) {
            beerViewModel
                .getBeerById(beerId)
            navController
                .navigate(Screen.BeerDetail.route)
        }
    }
    // endregion

    // region Behandeling van Favorite beers
    /**
     * Haal de lijst met favoriete bieren op uit de lokale database.
     */
    val favoriteBeers = beerViewModel
        .uiListState
        .collectAsState()

    /**
     * Voeg het bier toe aan de lokale
     * lijst met favoriete database.
     */
    val addBeerToFavorites = {
        beerViewModel
            .addBeerToFavorites()
    }

    /**
     * Verwijder het bier uit de lokale database.
     */
    val deleteBeerFromFavorites = { id: Int ->
        beerViewModel
            .deleteFromFavoriteBeers(id)
    }

    /**
     * Controleer of het bier in de lokale database staat.
     * @return true als het bier in de lokale database staat, anders false.
     */
    val isBeerInFavorites = { id: Int -> runBlocking { beerViewModel.isBeerInFavorites(id) } }
    // endregion

    /**
     * Zoekt bieren op basis van de naam van het bier.
     */
    val searchBeersByName = { beerName: String ->
        beerViewModel
            .getSeachApiBeers(beerName)
    }

    BottomSheet(
        isLandscape = isLandscape,
        closeSheet = closeSheet,
        scaffoldState = scaffoldState,
        favoriteBeers = favoriteBeers,
        deleteFromFavoriteBeers = deleteBeerFromFavorites,
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
                BottomBar(
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
                    composable(Screen.BeerDetail.route) {
                        BeerDetailScreen(
                            beerDetailApiState = beerViewModel.beerDetailApiState,
                            addBeerToFavorites = { addBeerToFavorites() },
                            isBeerInFavorites = isBeerInFavorites
                        )
                    }

                    composable(Screen.Search.route) {
                        SearchScreen(
                            beerSearchApiState = beerViewModel.beerSeachApiState,
                            getBeersByName = searchBeersByName,
                            goToDetail = goToDetail
                        )
                    }
                }
            }
        }
    }
}
