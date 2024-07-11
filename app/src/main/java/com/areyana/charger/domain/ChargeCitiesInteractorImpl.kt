package com.areyana.charger.domain

import com.areyana.charger.data.ChargeCitiesRepository
import com.areyana.charger.data.model.ChargeDto
import com.areyana.charger.domain.model.Charge
import com.areyana.charger.domain.model.ChargeCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChargeCitiesInteractorImpl(private val chargeCitiesRepository: ChargeCitiesRepository) :
    ChargeCitiesInteractor {

    override val chargeCities: StateFlow<List<ChargeCity>>
        get() = _chargeCities

    private val _chargeCities: MutableStateFlow<List<ChargeCity>> = MutableStateFlow(listOf())
    override suspend fun loadChargeCities(): Result<Unit> =
        chargeCitiesRepository.loadChargeCities().mapCatching {
            val result = it.groupBy(
                keySelector = { item -> item.city },
                valueTransform = { item -> item.charge.toDomain() })
                .map { entry ->
                    ChargeCity(entry.key, entry.value)
                }
            _chargeCities.value = result
        }

    private companion object {
        fun ChargeDto.toDomain(): Charge = Charge(
            isBusy = isBusy,
            name = name,
            address = address
        )
    }
}