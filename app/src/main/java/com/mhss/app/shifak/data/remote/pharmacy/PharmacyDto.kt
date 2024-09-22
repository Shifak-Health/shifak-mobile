package com.mhss.app.shifak.data.remote.pharmacy

import com.mhss.app.shifak.data.remote.user.model.PharmacyBranchDto
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyDto(

    @SerialName("hotline")
    val hotline: String,
    @SerialName("id")
    val id: Int,
    @SerialName("is_accept_expired")
    val acceptsExpired: Int = 0,
    @SerialName("is_active")
    val isActive: Boolean = true,
    @SerialName("name")
    val name: String,
    @SerialName("logo")
    val logo: String? = null,
    @SerialName("branches")
    val branches: List<PharmacyBranchDto>? = null,
)

fun PharmacyDto.toPharmacy(): Pharmacy {
    return Pharmacy(
        id = id,
        name = name,
        isActive = isActive,
        hotline = hotline,
        logoUrl = logo
    )
}