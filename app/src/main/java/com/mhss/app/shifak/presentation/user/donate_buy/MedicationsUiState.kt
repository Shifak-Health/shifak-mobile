package com.mhss.app.shifak.presentation.user.donate_buy

import com.mhss.app.shifak.domain.model.drug.Drug

data class MedicationsUiState(
    val loading: Boolean = false,
    val medications: List<Drug> = emptyList(),
    val error: String? = null
)
