package com.areyana.charger.view.cities

import androidx.lifecycle.viewModelScope
import com.areyana.charger.domain.ChargeCitiesInteractor
import com.areyana.charger.domain.model.ChargeCity
import com.areyana.mvi.Intent
import com.areyana.mvi.MviProcessor
import com.areyana.mvi.SingleEvent
import com.areyana.mvi.State
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class CitiesProcessor(private val chargeCitiesInteractor: ChargeCitiesInteractor): MviProcessor<CitiesState, CitiesIntent, CitiesSingleEvent>() {
    override fun initialState(): CitiesState = CitiesState.Loading

    override val reducer: Reducer<CitiesState, CitiesIntent> = CitiesReducer()

    init {
        viewModelScope.launch {
            chargeCitiesInteractor.loadChargeCities().fold(
                onSuccess = {
                    startCollectingCharges()
                    Timber.tag("[App]").i("ChargeCitiesInteractor::loadChargeCities success")
                },
                onFailure = {
                    Timber.tag("[App]").e("ChargeCitiesInteractor::loadChargeCities failure: ${it.stackTraceToString()}")
                    sendIntent(CitiesIntent.Error)
                }
            )
        }
    }

    private fun startCollectingCharges() {
        viewModelScope.launch {
            chargeCitiesInteractor.chargeCities.collectLatest {
                sendIntent(CitiesIntent.NewCities(it))
            }
        }
    }

    override suspend fun handleIntent(intent: CitiesIntent, state: CitiesState): CitiesIntent? = when(intent) {
        is CitiesIntent.NewCities, is CitiesIntent.ChangeSelectedCity, CitiesIntent.Error -> null
    }

    internal class CitiesReducer : Reducer<CitiesState, CitiesIntent> {
        override fun reduce(state: CitiesState, intent: CitiesIntent) = when (intent) {
            is CitiesIntent.NewCities -> CitiesState.Idle(intent.cities)
            is CitiesIntent.ChangeSelectedCity -> (state as? CitiesState.Idle)?.copy(selectedCity = intent.city)
                ?: CitiesState.Error
            CitiesIntent.Error -> CitiesState.Error
        }

    }
}

sealed interface CitiesIntent : Intent {
    data class NewCities(val cities: List<ChargeCity>): CitiesIntent
    data class ChangeSelectedCity(val city: ChargeCity?): CitiesIntent
    data object Error: CitiesIntent
}

sealed interface CitiesSingleEvent : SingleEvent {

}

sealed interface CitiesState : State {
    data object Error: CitiesState
    data object Loading : CitiesState
    data class Idle(val cities: List<ChargeCity>, val selectedCity: ChargeCity? = null): CitiesState
}