package com.areyana.charger.domain

import com.areyana.charger.domain.model.ChargeCity
import kotlinx.coroutines.flow.StateFlow

interface ChargeCitiesInteractor {
    suspend fun loadChargeCities(): Result<Unit>
    val chargeCities: StateFlow<List<ChargeCity>>
}