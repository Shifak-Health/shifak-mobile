package com.mhss.app.shifak.data.remote.user.model

import com.mhss.app.shifak.data.remote.pharmacy.toPharmacy
import com.mhss.app.shifak.domain.model.drug.Drug
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrugDto(
    @SerialName("id")
    val id: Int,
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
    val lng: Double? = null,
    @SerialName("description")
    val description: String? = null,
)

fun DrugDto.toDrug(): Drug {
    return Drug(
        id = id,
        name = name,
        description = description ?: "",
        price = price.toDoubleOrNull() ?: 0.0,
        qty = qty,
        productionDate = productionDate.toLongOrNull() ?: 0L,
        expiryDate = expiryDate.toLongOrNull() ?: 0L,
        drugType = drugType.toDrugType(),
        pharmacies = emptyList(), // TOdo
        user = null,
        isDonated = isDonated == 1,
        image = images?.firstOrNull(),
        pharmacy = pharmacyBranch?.pharmacy?.toPharmacy()
    )
}