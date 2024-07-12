package com.areyana.charger.view.charges

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.areyana.charger.R
import com.areyana.charger.domain.model.Charge
import com.areyana.design_system.ChargerIcons
import com.areyana.design_system.component.ChargerBackground
import com.areyana.design_system.component.ErrorScreen
import com.areyana.design_system.component.Title
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChargesRoute(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    mvi: ChargesProcessor = koinViewModel()
) {
    val uiState by mvi.viewState.collectAsStateWithLifecycle()

    ChargerBackground {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            onBack.invoke()
                        }) {
                            Icon(ChargerIcons.Back, ChargerIcons.Back.name)
                        }
                    },
                    title = {}
                )
            }
        ) { innerPadding ->
            Box(
                modifier
                    .padding(innerPadding)
            ) {
                when (val state = uiState) {
                    is ChargesState.Idle -> ChargesScreen(modifier = modifier, state = state)
                    ChargesState.Loading -> { /* pass */ }
                    ChargesState.Error -> ErrorScreen(modifier = modifier)
                }
            }
        }
    }
}

@Composable
private fun ChargesScreen(modifier: Modifier, state: ChargesState.Idle) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Title(title = R.string.charges)
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(state.chargeCity.charges) {
                ChargeItem(charge = it)
            }
        }
    }
}

@Composable
private fun ChargeItem(
    modifier: Modifier = Modifier,
    charge: Charge,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(1.dp, if (charge.isBusy) Color.Red else Color.Green,  shape = RoundedCornerShape(32.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(32.dp)
            )
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = charge.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = charge.address,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}