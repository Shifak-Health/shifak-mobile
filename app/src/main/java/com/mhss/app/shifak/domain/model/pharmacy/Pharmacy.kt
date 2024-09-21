package com.mhss.app.shifak.domain.model.pharmacy

data class Pharmacy(
    val id: Int,
    val name: String,
    val hotline: String,
    val order: Int,
    val isActive: Boolean,
    val logoUrl: String? = null
)
