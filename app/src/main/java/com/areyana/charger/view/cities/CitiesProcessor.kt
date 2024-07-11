package com.areyana.charger.view.cities

import com.areyana.mvi.Intent
import com.areyana.mvi.MviProcessor
import com.areyana.mvi.SingleEvent
import com.areyana.mvi.State

class CitiesProcessor: MviProcessor<CitiesState, CitiesIntent, CitiesSingleEvent>() {
    override fun initialState(): CitiesState = CitiesState.Loading

    override val reducer: Reducer<CitiesState, CitiesIntent> = CitiesReducer()

    override suspend fun handleIntent(intent: CitiesIntent, state: CitiesState): CitiesIntent? = when(intent) {
        CitiesIntent.Error -> TODO()
    }

    internal class CitiesReducer : Reducer<CitiesState, CitiesIntent> {
        override fun reduce(state: CitiesState, intent: CitiesIntent) = when (intent) {
            CitiesIntent.Error -> TODO()
        }

    }
}

sealed interface CitiesIntent : Intent {
    data object Error : CitiesIntent
}

sealed interface CitiesSingleEvent : SingleEvent {

}

sealed interface CitiesState : State {
    data object Loading : CitiesState
    data class Idle(val test: String): CitiesState
}