package com.areyana.charger.data

import com.areyana.charger.data.model.ChargeCityDto

interface ChargeCitiesRepository {
    suspend fun loadChargeCities(): Result<List<ChargeCityDto>>
}