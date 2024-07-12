package com.areyana.charger.view.cities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.areyana.charger.R
import com.areyana.charger.domain.model.ChargeCity
import com.areyana.design_system.component.ChargerBackground
import com.areyana.design_system.component.ErrorScreen
import com.areyana.design_system.component.Title
import com.areyana.design_system.component.noRippleClickable
import org.koin.androidx.compose.koinViewModel


@Composable
fun CitiesRoute(
    modifier: Modifier = Modifier,
    mvi: CitiesProcessor = koinViewModel(),
    onNavigateToCharges: (ChargeCity) -> Unit
) {
    val uiState by mvi.viewState.collectAsStateWithLifecycle()

    ChargerBackground {
        Scaffold { innerPadding ->
            Box(
                modifier
                    .consumeWindowInsets(innerPadding)
            ) {
                when (val state = uiState) {
                    is CitiesState.Idle -> CitiesScreen(modifier,
                        state, onNavigateToCharges, onSelectCity = {
                        mvi.sendIntent(CitiesIntent.ChangeSelectedCity(it))
                    })
                    CitiesState.Loading -> CitiesLoadingScreen(modifier)
                    CitiesState.Error -> ErrorScreen(modifier)
                }
            }
        }
    }
}

@Composable
private fun CitiesScreen(modifier: Modifier, state: CitiesState.Idle,
                         onNavigateToCharges: (ChargeCity) -> Unit, onSelectCity: (ChargeCity?) -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
        ) {
            item {
                Title(title = R.string.cities)
                Spacer(modifier = Modifier.height(16.dp))
            }
            itemsIndexed(state.cities.toList()) { index, item ->
                CityItem(chargeCity = item, isSelected = state.selectedCity?.city == item.city) { newSelectedCity ->
                    val newCity = if (state.selectedCity?.city == newSelectedCity.city) {
                        null
                    } else {
                        newSelectedCity
                    }
                    onSelectCity.invoke(newCity)
                }
                if (index == state.cities.size - 1) {
                    Spacer(modifier = Modifier.height(96.dp))
                } else {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        ConfirmButton(modifier = Modifier.align(Alignment.BottomCenter), isEnabled = state.selectedCity != null) {
            state.selectedCity?.let {
                onNavigateToCharges.invoke(it)
            }
        }
    }
}

@Composable
private fun CityItem(
    modifier: Modifier = Modifier,
    chargeCity: ChargeCity,
    isSelected: Boolean,
    onCitySelected: (ChargeCity) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(32.dp)
            )
            .padding(all = 16.dp)
            .noRippleClickable {
                onCitySelected.invoke(chargeCity)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = chargeCity.city,
            style = MaterialTheme.typography.bodyLarge
        )
        Checkbox(checked = isSelected, onCheckedChange = {
            onCitySelected.invoke(chargeCity)
        })
    }
}

@Composable
private fun ConfirmButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onConfirm: () -> Unit
) {
    Button(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .padding(bottom = 24.dp)
        .height(64.dp),
        enabled = isEnabled,
        onClick = {
            onConfirm.invoke()
        }) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(R.string.confirm),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun CitiesLoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(modifier = Modifier.align(Alignment.Center), text = stringResource(R.string.loading), style = MaterialTheme.typography.headlineMedium)
    }
}