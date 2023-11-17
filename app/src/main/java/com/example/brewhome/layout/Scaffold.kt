package com.example.brewhome.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.brewhome.screens.BeerDetailScreen
import com.example.brewhome.screens.DiscoverScreen
import com.example.brewhome.screens.SearchScreen
import com.example.brewhome.screens.SplashScreen
import com.example.brewhome.viewmodel.BeerViewModel

// https://dribbble.com/shots/11441772-Beer-App-Product-Explorations
@Composable
fun Scaffold(
    beerViewModel: BeerViewModel = viewModel(),
    openSheet: suspend () -> Unit
) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()


    fun goBack() = navController.navigateUp()
    androidx.compose.material3.Scaffold(
        topBar = {
            if (currentBackStack?.destination?.route != Destinations.Splash.name) {
                AppBar(
                    openSheet = { openSheet() },
                    previous = navController.previousBackStackEntry,
                    currentBackStack = navController.currentBackStackEntry?.destination?.route
                ) { goBack() }
            }
        },
        bottomBar = {
            if (currentBackStack?.destination?.route != Destinations.Splash.name) {
                BottomAppBar({
                    navController.navigate(Destinations.Discover.name)
                }, {
                    navController.navigate(Destinations.Search.name)
                })
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
                startDestination = Destinations.Splash.name
            ) {

                composable(Destinations.Splash.name) {
                    SplashScreen(navController)
                }
                composable(Destinations.Discover.name) {
                    DiscoverScreen(
                        beerViewModel,
                        navController = navController,
                    )
                }
                /*
                composable(Destinations.Category.name) {
                                    CategoryScreen()
                                }
                 */
                composable(Destinations.Search.name) {
                    SearchScreen()
                }

                composable("${Destinations.BeerDetail.name}/{beerId}") { backStackEntry ->
                    BeerDetailScreen(beerViewModel)
                }
            }
        }
    }
}


