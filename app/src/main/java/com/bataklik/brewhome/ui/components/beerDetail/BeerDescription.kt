package com.bataklik.brewhome.ui.components.beerDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun BeerDescription(beerDescription: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Description", color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = beerDescription,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
                .semantics { contentDescription = "txtBeerDetailDescription"  },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}