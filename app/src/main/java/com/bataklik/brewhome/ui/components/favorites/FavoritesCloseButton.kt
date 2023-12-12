package com.example.brewhome.ui.components.favorites

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.brewhome.R
import kotlinx.coroutines.launch

@Composable
fun FavoritesCloseButton(
    onClose: suspend () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Button(onClick = {
        coroutineScope.launch {
            onClose()
        }
    }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(28.dp),
                tint = MaterialTheme.colorScheme.tertiary,
                painter = painterResource(id = R.drawable.close_24px),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(width = 4.dp))
            Text(
                text = "Close",
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

    }
}