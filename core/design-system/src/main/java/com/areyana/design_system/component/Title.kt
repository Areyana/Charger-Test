package com.areyana.design_system.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun Title(modifier: Modifier = Modifier, @StringRes title: Int) {
    Text(
        modifier = modifier.padding(top = 16.dp, start = 16.dp),
        text = stringResource(id = title),
        style = MaterialTheme.typography.headlineLarge,
    )
}