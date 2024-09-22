package com.mhss.app.shifak.data.remote.pharmacy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllPharmaciesDto(
    @SerialName("data") val pharmacies: List<PharmacyDto>
)