package com.mhss.app.shifak.data.remote.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddDrugRequestBody(
    @SerialName("drug_type_id")
    val drugTypeId: DrugTypeDto,
    @SerialName("pharmacy_branch_id")
    val pharmacyBranchId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Double,
    @SerialName("qty")
    val qty: Int,
    @SerialName("production_date")
    val productionDate: String,
    @SerialName("expiry_date")
    val expiryDate: String,
    @SerialName("is_donated")
    val isDonated: Int,
    @SerialName("is_expired")
    val isExpired: Int,
    @SerialName("images")
    val images: List<ByteArray>? = null,
    @SerialName("lat")
    val lat: Double? = null,
    @SerialName("lng")
    val lng: Double? = null
)