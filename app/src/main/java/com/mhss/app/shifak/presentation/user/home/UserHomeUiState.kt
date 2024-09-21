package com.mhss.app.shifak.presentation.user.home

import androidx.compose.runtime.Stable
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy

@Stable
data class UserHomeUiState(
    val location: String = "",
    val hasNotifications: Boolean = false,
    val exploreMedications: List<Drug> = emptyList(),
    val pharmaciesNearby: List<Pharmacy> = emptyList()
)
