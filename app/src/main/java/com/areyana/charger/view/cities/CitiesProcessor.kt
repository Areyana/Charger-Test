package com.areyana.charger.view.cities

import androidx.lifecycle.viewModelScope
import com.areyana.charger.domain.ChargeCitiesInteractor
import com.areyana.charger.domain.model.ChargeCity
import com.areyana.mvi.Intent
import com.areyana.mvi.MviProcessor
import com.areyana.mvi.SingleEvent
import com.areyana.mvi.State
import kotlinx.coroutines.launch
import timber.log.Timber

class CitiesProcessor(private val chargeCitiesInteractor: ChargeCitiesInteractor): MviProcessor<CitiesState, CitiesIntent, CitiesSingleEvent>() {
    override fun initialState(): CitiesState = CitiesState.Loading

    override val reducer: Reducer<CitiesState, CitiesIntent> = CitiesReducer()

    init {
        viewModelScope.launch {
            chargeCitiesInteractor.loadChargeCities().fold(
                onSuccess = {
                    sendIntent(CitiesIntent.NewCities(it))
                },
                onFailure = {
                    Timber.tag("[App]").e(it)
                    sendIntent(CitiesIntent.Error)
                }
            )
        }
    }

    override suspend fun handleIntent(intent: CitiesIntent, state: CitiesState): CitiesIntent? = when(intent) {
        CitiesIntent.Error, is CitiesIntent.NewCities -> null
    }

    internal class CitiesReducer : Reducer<CitiesState, CitiesIntent> {
        override fun reduce(state: CitiesState, intent: CitiesIntent) = when (intent) {
            CitiesIntent.Error -> CitiesState.Error
            is CitiesIntent.NewCities -> CitiesState.Idle(intent.cities)
        }

    }
}

sealed interface CitiesIntent : Intent {
    data class NewCities(val cities: List<ChargeCity>): CitiesIntent
    data object Error : CitiesIntent
}

sealed interface CitiesSingleEvent : SingleEvent {

}

sealed interface CitiesState : State {
    data object Error: CitiesState
    data object Loading : CitiesState
    data class Idle(val cities: List<ChargeCity>): CitiesState
}