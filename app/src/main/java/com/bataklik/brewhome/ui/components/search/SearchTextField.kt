package com.bataklik.brewhome.ui.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
    modifier: Modifier,
    onSearchAction: (text: String) -> Unit
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme
                .tertiary,
            unfocusedTextColor = MaterialTheme.colorScheme
                .primary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme
                .primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme
                .onPrimary,
            focusedPlaceholderColor = MaterialTheme.colorScheme
                .primary,
            focusedContainerColor = MaterialTheme.colorScheme
                .tertiary,
            focusedTextColor = MaterialTheme.colorScheme
                .primary,
            focusedLeadingIconColor = MaterialTheme.colorScheme
                .onPrimary
        ),
        placeholder = { Text("Search a beer") },
        leadingIcon = {
            Icon(Icons.Rounded.Search, contentDescription = "SeachIcon")
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        value = searchText,
        onValueChange = { text: String ->
            searchText = text
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSearchAction(searchText)
                keyboardController?.hide()
            }
        ),
    )
}