package com.practicum.homeaccounting.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.homeaccounting.R
import com.practicum.homeaccounting.ui.theme.HomeAccountingTheme

@Composable
fun MainScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.name_main_screen),
            style = MaterialTheme.typography.headlineMedium
        )
        Button(
            onClick = onNavigateBack
        ) {
            Text(
                text = stringResource(R.string.back_button_home_screen)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HomeAccountingTheme {
        MainScreen(onNavigateBack = {})
    }
}
