package com.example.brewhome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.ui.Modifier
import com.example.brewhome.layout.Scaffold
import com.example.brewhome.screens.FavoritesSheet
import com.example.brewhome.ui.theme.BrewHomeTheme
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val timber = Timber
            .plant(Timber.DebugTree())
        super.onCreate(savedInstanceState)
        setContent {
            val sheetState = rememberStandardBottomSheetState(
                initialValue = SheetValue.Hidden,
                skipHiddenState = false,
            )

            suspend fun openSheet() = run {
                coroutineScope {
                    sheetState.expand()
                }
            }

            suspend fun closeSheet() = run {
                coroutineScope {
                    sheetState.hide()
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
                    BottomSheetScaffold(
                        sheetContent = { FavoritesSheet(closeSheet = { closeSheet() }) },
                        sheetSwipeEnabled = false,
                        scaffoldState = scaffoldState
                    ) {
                        Scaffold(openSheet = { openSheet() })
                    }
                }
            }
        }


    }
}