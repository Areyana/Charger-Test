package com.areyana.charger.domain

import com.areyana.charger.domain.model.ChargeCity

interface ChargeCitiesInteractor {
    suspend fun loadChargeCities(): Result<List<ChargeCity>>
}