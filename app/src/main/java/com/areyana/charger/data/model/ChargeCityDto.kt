package com.areyana.charger.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChargeCityDto(
    @SerialName("city") val city: String,
    @SerialName("charger") val charge: ChargeDto
)