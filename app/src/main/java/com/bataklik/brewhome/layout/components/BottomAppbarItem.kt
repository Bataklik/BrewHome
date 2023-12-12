package com.example.brewhome.layout.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

/**
 * Composable functie die een item in de onderste navigatiebalk weergeeft.
 * @param modifier Aanvullende opmaakinstellingen voor het item.
 * @param goToAction Een functie die wordt aangeroepen bij het klikken op het item om te navigeren.
 * @param contentDescriptionIB De beschrijving van het Icon Button.
 * @param contentDescriptionI De beschrijving van het Icon.
 * @param painter De resource-ID van het icoon dat in het item wordt weergegeven.
 */
@Composable
fun BottomAppbarItem(
    modifier: Modifier, goToAction: () -> Unit,
    contentDescriptionIB: String,
    contentDescriptionI: String, painter: Int
) {
    IconButton(
        onClick = goToAction,
        modifier = modifier
            .semantics { contentDescription = contentDescriptionIB }
    ) {
        Icon(
            painter = painterResource(id = painter),
            contentDescription = contentDescriptionI
        )
    };
}