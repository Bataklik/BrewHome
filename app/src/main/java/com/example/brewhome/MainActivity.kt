package com.example.brewhome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.brewhome.layout.BottomSheet
import com.example.brewhome.ui.theme.BrewHomeTheme
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        Timber
            .plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)
        setContent {
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

            BrewHomeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomSheet(closeSheet, scaffoldState) {
                        BrewHomeApp(openSheet = openSheet)
                    }
                }
            }
        }

    }
}