package com.mhss.app.shifak.data.remote.user.model

import com.mhss.app.shifak.data.remote.pharmacy.PharmacyDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyBranchDto(
    @SerialName("address")
    val address: String? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String? = null,
    @SerialName("pharmacy")
    val pharmacy: PharmacyDto? = null,
)