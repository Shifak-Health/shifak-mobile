package com.mhss.app.shifak.domain.model.drug

import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import com.mhss.app.shifak.domain.model.user.User

data class Drug(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val qty: Int,
    val productionDate: Long,
    val expiryDate: Long,
    val drugType: DrugType,
    val pharmacies: List<Pharmacy>?,
    val user: User?,
    val isValid: Boolean,
    val isAvailable: Boolean,
    val isDonated: Boolean,
    val updatedAt: String,
    val image: String? = null,
    val components: List<DrugComponent>? = null
)
