package com.example.brewhome.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.brewhome.Destinations
import com.example.brewhome.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val image = painterResource(R.drawable.homebrew)
        Image(painter = image, contentDescription = "BrewHome Logo")
        Text(
            text = "BrewHome",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = "Your taste, your beer!",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }

    LaunchedEffect(key1 = true) {
        delay(3000L)  // Vertraging van 3 seconden
        navController.navigate(Destinations.Discover.name) {
            popUpTo("splashScreenRoute") {
                inclusive = true
            } // Verwijder de splash screen route uit de backstack
        }
    }
}