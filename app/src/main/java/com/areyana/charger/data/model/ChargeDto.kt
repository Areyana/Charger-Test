package com.areyana.charger.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChargeDto(
    @SerialName("busy") val isBusy: Boolean,
    @SerialName("name") val name: String,
    @SerialName("address") val address: String
)