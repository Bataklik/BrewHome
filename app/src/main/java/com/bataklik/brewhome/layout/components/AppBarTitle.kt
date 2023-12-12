package com.bataklik.brewhome.layout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.R


/**
 * Composable functie die de titel en het logo in de app-balk weergeeft.
 * @param modifier Aanvullende opmaakinstellingen voor de titel.
 * @param currentScreenTitle De huidige titel van het scherm.
 */
@Composable
fun AppBarTitle(modifier: Modifier, currentScreenTitle: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.homebrew),
            contentDescription = "BrewHome Logo",
            modifier = modifier.size(40.dp)
        )
        Text(currentScreenTitle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.semantics { contentDescription = "txtCurrentScreenTitle" })
    }
}