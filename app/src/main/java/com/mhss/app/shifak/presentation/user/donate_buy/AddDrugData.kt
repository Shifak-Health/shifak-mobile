package com.mhss.app.shifak.presentation.user.donate_buy

import android.net.Uri

data class AddDrugData(
    val name: String,
    val price: Double?,
    val qty: Int?,
    val productionDate: Long,
    val expiryDate: Long,
    val isDonated: Boolean,
    val isExpired: Boolean,
    val drugTypeId: Int = 1,
    val pharmacyBranchId: Int? = null,
    val description: String = "",
    val images: List<Uri>? = null,
    val lat: Double? = null,
    val lng: Double? = null
)