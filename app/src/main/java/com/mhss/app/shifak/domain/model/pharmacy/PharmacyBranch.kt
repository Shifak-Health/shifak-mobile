package com.mhss.app.shifak.domain.model.pharmacy

import com.mhss.app.shifak.domain.model.drug.Drug

data class PharmacyBranch(
    val id: Int,
    val phone: String,
    val address: String,
    val commercialRegistrationNumber: String,
    val taxNumber: String,
    val lat: Double?,
    val lng: Double?,
    val drugs: List<Drug>?,
    val pharmacy: Pharmacy,
    val isOpen: Boolean
)
