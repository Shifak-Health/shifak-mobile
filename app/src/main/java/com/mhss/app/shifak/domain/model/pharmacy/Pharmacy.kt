package com.mhss.app.shifak.domain.model.pharmacy

data class Pharmacy(
    val id: Int,
    val name: String,
    val hotline: String,
    val isActive: Boolean,
    val logoUrl: String? = null
)
