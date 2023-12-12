package com.example.brewhome.layout.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.brewhome.R

/**
 * Composable functie die een terugknop in de app-balk weergeeft.
 * @param navigateUp Een functie die wordt aangeroepen bij het klikken op de terugknop om terug te navigeren.
 */
@Composable
fun AppBarBackButton(navigateUp: () -> Unit) {
    AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
        IconButton(onClick = { navigateUp() }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_24px),
                contentDescription = null
            )
        };
    }
}