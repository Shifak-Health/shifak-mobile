package com.mhss.app.shifak.presentation.user.home

import androidx.compose.runtime.Stable
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy

@Stable
data class UserHomeUiState(
    val location: String = "الدقهلية",
    val hasNotifications: Boolean = false,
    val exploreMedications: List<Drug> = emptyList(),
    val pharmacies: List<Pharmacy> = emptyList(),
    val pharmaciesLoading: Boolean = false,
    val medicationsLoading: Boolean = false,
    val error: String? = ""
)
