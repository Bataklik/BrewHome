package com.example.brewhome.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView


private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    surface = BackgroundColor,
    onPrimary = TextColor,
    onSecondary = TextColor,
    onBackground = TextColor,
    onSurface = TextColor,
    onError = BackgroundColor,
)


@Composable
fun BrewHomeTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    LocalView.current

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}