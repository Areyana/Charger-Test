package com.areyana.charger.domain

import com.areyana.charger.domain.model.Charge
import com.areyana.charger.domain.model.City

interface ChargeCitiesInteractor {
    suspend fun loadChargeCities(): Result<Map<City, List<Charge>>>
}