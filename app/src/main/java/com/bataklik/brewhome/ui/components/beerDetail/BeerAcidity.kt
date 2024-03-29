package com.bataklik.brewhome.ui.components.beerDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun BeerAcidity(beerPh: Double?) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),

        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "- Acidity:",
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "$beerPh pH",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}