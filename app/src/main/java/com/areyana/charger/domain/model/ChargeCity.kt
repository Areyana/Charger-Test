package com.areyana.charger.domain.model

data class ChargeCity(
    val city: String,
    val charges: List<Charge>
)