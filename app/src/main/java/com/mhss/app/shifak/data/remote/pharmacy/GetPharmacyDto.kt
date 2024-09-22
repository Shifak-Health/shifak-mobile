package com.mhss.app.shifak.data.remote.pharmacy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPharmacyDto(
    @SerialName("data")
    val pharmacies: PharmacyDto
)