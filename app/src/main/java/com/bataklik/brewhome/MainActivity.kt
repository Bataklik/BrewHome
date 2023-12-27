package com.bataklik.brewhome

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
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
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        Timber
            .plant(Timber.DebugTree())
        setContent {
            BrewHomeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    val isLandscape = windowSize.heightSizeClass == WindowHeightSizeClass.Compact
                    BrewHomeApp(isLandscape = isLandscape)
                }
            }
        }
    }
}

