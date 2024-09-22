package com.mhss.app.shifak.data.remote.user.model

import com.mhss.app.shifak.presentation.user.donate_buy.FileInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddDrugRequestBody(
    @SerialName("drug_type_id")
    val drugTypeId: Int,
    @SerialName("pharmacy_branch_id")
    val pharmacyBranchId: Int?,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Double?,
    @SerialName("qty")
    val qty: Int?,
    @SerialName("production_date")
    val productionDate: String?,
    @SerialName("expiry_date")
    val expiryDate: String?,
    @SerialName("is_donated")
    val isDonated: Int,
    @SerialName("is_expired")
    val isExpired: Int,
    @SerialName("description")
    val description: String = "",
    @SerialName("images")
    val images: List<FileInfo>? = null,
    @SerialName("lat")
    val lat: Double? = null,
    @SerialName("lng")
    val lng: Double? = null
)