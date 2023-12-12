package com.bataklik.brewhome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bataklik.brewhome.ui.theme.BrewHomeTheme
import timber.log.Timber

/**
 * @author Bataklik (Burak)
 * Email: burak.balci@student.hogent.be
 * Copyright. All rights reserved
 * deze klasse is de hoofdactiviteit van dit project
 */
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        Timber
            .plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)
        setContent {
            BrewHomeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BrewHomeApp()
                }
            }
        }

    }
}