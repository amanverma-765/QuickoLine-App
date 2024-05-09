package org.quickoline.features.tabs.home.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(modifier: Modifier = Modifier) {

    Scaffold(

        topBar = {
            TopAppBar(title = { Text(text = "ahsjjahshjha") })
        }

    ) { padding ->

        LazyColumn(
            modifier = modifier,
            contentPadding = padding
        ) {

            item {
                Text(text = "Home Screen")
            }
        }
    }

}