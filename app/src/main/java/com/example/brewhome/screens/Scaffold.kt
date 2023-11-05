package com.example.brewhome.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.brewhome.components.BottomAppBar
import com.example.brewhome.components.TopAppBar
import com.example.brewhome.Destinations
import com.example.brewhome.viewmodel.BeerViewModel

// https://dribbble.com/shots/11441772-Beer-App-Product-Explorations
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(
    beerViewModel: BeerViewModel = viewModel(),
) {
    val navHostController = rememberNavController()
    val currentBackStack by navHostController.currentBackStackEntryAsState()


    androidx.compose.material3.Scaffold(
        topBar = {
            if (currentBackStack?.destination?.route != Destinations.Splash.name) {
                TopAppBar()
            }
        },
        bottomBar = {
            if (currentBackStack?.destination?.route != Destinations.Splash.name) {
                BottomAppBar({
                    navHostController.navigate(Destinations.Discover.name)
                }, {
                    navHostController.navigate(Destinations.Category.name)
                }, {
                    navHostController.navigate(Destinations.Favorite.name)
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
                navController = navHostController,
                startDestination = Destinations.Splash.name
            ) {
                composable(Destinations.Splash.name) {
                    SplashScreen(navHostController)
                }
                composable(Destinations.Discover.name) {
                    DiscoverScreen(beerViewModel,navController = navHostController)
                }
                composable(Destinations.Category.name) {
                    CategoryScreen()
                }
                composable(Destinations.Favorite.name) {
                    FavoriteScreen()
                }
                composable("${Destinations.BeerDetail.name}/{beerId}") { backStackEntry ->
                    val arguments = requireNotNull(backStackEntry.arguments)
                    BeerDetailScreen(beerViewModel,beerId = 192)
                }
            }
        }
    }
}

