package com.mhss.app.shifak.data.remote.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrugDto(
    @SerialName("id")
    val id: Int,
    @SerialName("description")
    val description: String,
    @SerialName("drug_type")
    val drugType: DrugTypeDto,
    @SerialName("expiry_date")
    val expiryDate: String,
    @SerialName("is_donated")
    val isDonated: Int,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: String,
    @SerialName("production_date")
    val productionDate: String,
    @SerialName("qty")
    val qty: Int,
    @SerialName("pharmacy_branch")
    val pharmacyBranch: PharmacyBranchDto? = null,
    @SerialName("images")
    val images: List<String>? = null,
    @SerialName("lat")
    val lat: Double? = null,
    @SerialName("lng")
    val lng: Double? = null
)