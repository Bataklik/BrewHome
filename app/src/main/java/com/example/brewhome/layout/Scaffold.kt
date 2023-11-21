package com.example.brewhome.layout

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
import com.example.brewhome.Destinations
import com.example.brewhome.ui.screens.BeerDetailScreen
import com.example.brewhome.ui.screens.DiscoverScreen
import com.example.brewhome.ui.screens.SearchScreen
import com.example.brewhome.viewmodel.BeerViewModel

// https://dribbble.com/shots/11441772-Beer-App-Product-Explorations
@Composable
fun Scaffold(
    beerViewModel: BeerViewModel = viewModel(factory = BeerViewModel.Factory),
    openSheet: suspend () -> Unit
) {
    val navController = rememberNavController()
    val canNavigateBack = navController.previousBackStackEntry != null
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenTitle = when (backStackEntry?.destination?.route) {
        Screen.Discover.route -> Screen.Discover.toString()
        Screen.Search.route -> Screen.Search.toString()
        Screen.BeerDetail().route -> Screen.BeerDetail().toString()
        else -> Screen.Discover.toString()
    }

    val navigateUp: () -> Unit = { navController.navigateUp() }
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
                navController.navigate(Destinations.Discover.name)
            }, {
                navController.navigate(Destinations.Search.name)
            })
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            NavHost(
                navController = navController,
                startDestination = Destinations.Discover.name
            ) {

                composable(Screen.Discover.route) {
                    DiscoverScreen(
                        beerApiState = beerViewModel.beerApiState,
                        goToDetail = goToDetail
                    )
                }

                composable(Screen.Search.route) {
                    SearchScreen()
                }

                composable(Screen.BeerDetail().route) { backStackEntry ->
                    val beerId = backStackEntry.arguments?.getInt("beerId") ?: -1
                    BeerDetailScreen(
                        beerDetailApiState = beerViewModel.beerDetailApiState,
                    )
                }
            }
        }
    }
}


