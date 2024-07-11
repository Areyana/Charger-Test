package com.areyana.charger.view.charges

import com.areyana.charger.domain.model.Charge
import com.areyana.charger.domain.model.City
import com.areyana.mvi.Intent
import com.areyana.mvi.MviProcessor
import com.areyana.mvi.SingleEvent
import com.areyana.mvi.State

class ChargesProcessor: MviProcessor<ChargesState, ChargesIntent, ChargesSingleEvent>() {
    override fun initialState(): ChargesState = ChargesState.Loading

    override val reducer: Reducer<ChargesState, ChargesIntent> = ChargesReducer()

    override suspend fun handleIntent(intent: ChargesIntent, state: ChargesState): ChargesIntent? = when(intent) {
        ChargesIntent.Error -> TODO()
    }

    internal class ChargesReducer : Reducer<ChargesState, ChargesIntent> {
        override fun reduce(state: ChargesState, intent: ChargesIntent) = when (intent) {
            ChargesIntent.Error -> ChargesState.Error
        }

    }
}

sealed interface ChargesIntent : Intent {
    data object Error : ChargesIntent
}

sealed interface ChargesSingleEvent : SingleEvent {

}

sealed interface ChargesState : State {
    data object Loading : ChargesState
    data object Error: ChargesState
    data class Idle(val cities: Map<City, List<Charge>>): ChargesState
}