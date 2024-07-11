package com.areyana.charger.domain.model

data class ChargeCity(
    val city: City,
    val charges: List<Charge>
)