package com.areyana.charger.view.charges

import androidx.lifecycle.viewModelScope
import com.areyana.charger.domain.ChargeCitiesInteractor
import com.areyana.charger.domain.model.ChargeCity
import com.areyana.mvi.Intent
import com.areyana.mvi.MviProcessor
import com.areyana.mvi.SingleEvent
import com.areyana.mvi.State
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ChargesProcessor(private val chargeCitiesInteractor: ChargeCitiesInteractor, private val chargesArg: ChargesArg): MviProcessor<ChargesState, ChargesIntent, ChargesSingleEvent>() {
    override fun initialState(): ChargesState = ChargesState.Loading

    override val reducer: Reducer<ChargesState, ChargesIntent> = ChargesReducer()

    init {
        viewModelScope.launch {
            chargeCitiesInteractor.chargeCities.map {
                it.firstOrNull { item -> item.city == chargesArg.city }
            }.collectLatest {
                if (it != null) {
                    sendIntent(ChargesIntent.NewChargeCity(city = it))
                } else {
                    sendIntent(ChargesIntent.Error)
                }
            }
        }
    }

    override suspend fun handleIntent(intent: ChargesIntent, state: ChargesState): ChargesIntent? = when(intent) {
        ChargesIntent.Error, is ChargesIntent.NewChargeCity -> null
    }

    internal class ChargesReducer : Reducer<ChargesState, ChargesIntent> {
        override fun reduce(state: ChargesState, intent: ChargesIntent) = when (intent) {
            ChargesIntent.Error -> ChargesState.Error
            is ChargesIntent.NewChargeCity -> intent.city.let { ChargesState.Idle(it.copy(charges = it.charges.sortedBy { charge -> charge.name })) }
        }

    }
}

sealed interface ChargesIntent : Intent {
    data object Error: ChargesIntent
    data class NewChargeCity(val city: ChargeCity): ChargesIntent
}

sealed interface ChargesSingleEvent : SingleEvent {

}

sealed interface ChargesState : State {
    data object Error: ChargesState
    data object Loading : ChargesState
    data class Idle(val chargeCity: ChargeCity): ChargesState
}