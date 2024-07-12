package com.areyana.design_system.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.areyana.charger.core.designsystem.R

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = stringResource(R.string.something_went_wrong), style = MaterialTheme.typography.headlineSmall)
    }
}