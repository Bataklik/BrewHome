package com.example.brewhome.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.brewhome.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar() {
    CenterAlignedTopAppBar(
        modifier = Modifier.height(70.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.secondary
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.homebrew),
                    contentDescription = "BrewHome Logo",
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    "BrewHome",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
    )
}