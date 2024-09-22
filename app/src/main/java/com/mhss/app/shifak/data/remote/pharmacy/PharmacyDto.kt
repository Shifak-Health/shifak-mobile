package com.mhss.app.shifak.data.remote.pharmacy

import com.mhss.app.shifak.data.remote.user.model.PharmacyBranchDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyDto(
    @SerialName("branches")
    val branches: List<PharmacyBranchDto>,
    @SerialName("hotline")
    val hotline: String,
    @SerialName("id")
    val id: Int,
    @SerialName("is_accept_expired")
    val acceptsExpired: Int,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("logo")
    val logo: String? = null
)