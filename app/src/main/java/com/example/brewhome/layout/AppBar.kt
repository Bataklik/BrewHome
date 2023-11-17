package com.example.brewhome.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.example.brewhome.R
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(
    openSheet: suspend () -> Unit,
    previous: NavBackStackEntry?,
    currentBackStack: String?,
    goBack: () -> Boolean,
) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        modifier = Modifier
            .height(70.dp)
            .padding(vertical = 5.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.secondary
        ),
        navigationIcon = {
            if (currentBackStack != null) {
                if (previous != null && currentBackStack.contains("BeerDetail")) {
                    AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
                        IconButton(onClick = { goBack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_back_24px),
                                contentDescription = null
                            )
                        };
                    }
                }
            }
        },
        actions = {
            Button(onClick = {
                coroutineScope.launch {
                    openSheet()
                }
            }) {
                Row(verticalAlignment = CenterVertically) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        tint = MaterialTheme.colorScheme.tertiary,
                        painter = painterResource(id = R.drawable.favorite_24px),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(width = 4.dp))
                    Text(
                        text = "Favorites",
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                }

            }

        },

        title = {
            Row(
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.homebrew),
                    contentDescription = "BrewHome Logo",
                    modifier = Modifier.size(40.dp)
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

