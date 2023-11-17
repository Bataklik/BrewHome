package com.example.brewhome.components

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
fun BottomAppBar(goDiscover: () -> Unit, goCategory: () -> Unit, goFavorite: () -> Unit) {
    androidx.compose.material3.BottomAppBar(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.primary,
        content = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = goDiscover) {
                    Icon(
                        painter = painterResource(id = R.drawable.explore_24px),
                        contentDescription = null
                    )
                };
                IconButton(onClick = goCategory) {
                    Icon(
                        painter = painterResource(id = R.drawable.category_24px),
                        contentDescription = null
                    )
                };
                IconButton(onClick = goFavorite) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite_24px),
                        contentDescription = null
                    )
                };
            }
        }
    )
}