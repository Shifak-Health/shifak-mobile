package com.mhss.app.shifak.domain.model.pharmacy

import com.mhss.app.shifak.domain.model.drug.Drug

data class Pharmacy(
    val id: Int,
    val name: String,
    val hotline: String,
    val order: Int,
    val isActive: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val branches: List<PharmacyBranch>? = null,
    val drugs: List<Drug>? = null,
    val logoUrl: String? = null,
)
