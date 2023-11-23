package com.example.brewhome.ui.components.discover

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DiscoverTitle(title: String = "Products") {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.tertiary,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}