package com.areyana.charger.domain

import com.areyana.charger.data.ChargeCitiesRepository
import com.areyana.charger.data.model.ChargeDto
import com.areyana.charger.domain.model.Charge
import com.areyana.charger.domain.model.City

class ChargeCitiesInteractorImpl(private val chargeCitiesRepository: ChargeCitiesRepository) :
    ChargeCitiesInteractor {
    override suspend fun loadChargeCities(): Result<Map<City, List<Charge>>> =
        chargeCitiesRepository.loadChargeCities().mapCatching {
            it.groupBy(
                keySelector = { item -> City(item.city) },
                valueTransform = { item -> item.charge.toDomain() })
        }

    private companion object {
        fun ChargeDto.toDomain(): Charge = Charge(
            isBusy = isBusy,
            name = name,
            address = address
        )
    }
}