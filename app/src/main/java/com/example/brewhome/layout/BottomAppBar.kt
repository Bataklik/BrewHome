package com.example.brewhome.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.brewhome.R

@Composable
fun BottomAppBar(goDiscover: () -> Unit,  goSearch: () -> Unit) {
    androidx.compose.material3.BottomAppBar(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.primary,
        content = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = goDiscover) {
                    Icon(
                        painter = painterResource(id = R.drawable.explore_24px),
                        contentDescription = null
                    )
                };
                IconButton(onClick = goSearch) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_24px),
                        contentDescription = null
                    )
                };
            }
        }
    )
}