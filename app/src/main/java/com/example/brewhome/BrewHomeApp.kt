package com.example.brewhome

import Screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.brewhome.layout.AppBar
import com.example.brewhome.layout.BottomAppBar
import com.example.brewhome.ui.screens.BeerDetailScreen
import com.example.brewhome.ui.screens.DiscoverScreen
import com.example.brewhome.ui.screens.SearchScreen
import com.example.brewhome.viewmodel.BeerViewModel

@Composable
fun BrewHomeApp(
    beerViewModel: BeerViewModel = viewModel(factory = BeerViewModel.Factory),
    openSheet: suspend () -> Unit
) {
    val navController = rememberNavController()
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
    val goToDetail = { beerId: Int ->
        beerViewModel
            .getBeerById(beerId)
        navController
            .navigate(Screen.BeerDetail(beerId).route)
    }
    val getBeerById = { beerId: Int -> beerViewModel.getBeerById(beerId) }

    Scaffold(
        topBar = {
            AppBar(
                openSheet = { openSheet() },
                navigateUp = navigateUp,
                canNavigateBack = canNavigateBack,
                currentScreenTitle = currentScreenTitle
            )

        },
        bottomBar = {
            BottomAppBar({
                goToDiscover()
            }) {
                goToSearch()
            }
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
                composable(Screen.BeerDetail().route) { backStackEntry ->
                    val beerId = backStackEntry.arguments?.getInt("beerId") ?: -1
                    BeerDetailScreen(
                        beerDetailApiState = beerViewModel.beerDetailApiState,
                    )
                }
                composable(Screen.Search.route) {
                    SearchScreen()
                }
            }
        }
    }
}


