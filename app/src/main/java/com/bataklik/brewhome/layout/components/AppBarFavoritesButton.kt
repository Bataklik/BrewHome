package com.bataklik.brewhome.layout.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bataklik.brewhome.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Composable functie die een knop weergeeft voor het openen van het favorietenblad in de app-balk.
 * @param coroutineScope De scope voor het lanceren van coroutines.
 * @param openSheet Een suspend functie dat wordt uitgevoerd bij het openen van het sheet.
 * @param modifier Aanvullende opmaakinstellingen voor de knop.
 */
@Composable
fun AppBarFavoritesButton(
    modifier: Modifier,
    coroutineScope: CoroutineScope,
    openSheet: suspend () -> Unit,
) {
    Button(onClick = {
        coroutineScope.launch {
            openSheet()
        }
    }, content = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = modifier.size(28.dp),
                tint = MaterialTheme.colorScheme.tertiary,
                painter = painterResource(id = R.drawable.favorite_24px),
                contentDescription = null
            )
            Spacer(modifier = modifier.width(width = 4.dp))
            Text(
                text = "Favorites",
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    })
}