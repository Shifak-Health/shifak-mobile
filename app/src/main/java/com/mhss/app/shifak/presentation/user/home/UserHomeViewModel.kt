package com.mhss.app.shifak.presentation.user.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.drug.DrugType
import com.mhss.app.shifak.domain.model.pharmacy.Pharmacy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserHomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        UserHomeUiState(
            location = "الدقهلية",
            exploreMedications = listOf(
                Drug(
                    id = 1,
                    name = "بانادول اكسترا",
                    description = "",
                    price = 43.0,
                    qty = 2,
                    productionDate = 0,
                    expiryDate = 0,
                    drugType = DrugType(
                        id = 8181,
                        name = "gm",
                        unit = "gm"
                    ),
                    pharmacies = listOf(),
                    user = null,
                    isValid = false,
                    isAvailable = false,
                    isDonated = false,
                    updatedAt = "doming",
                    image = null,
                    components = listOf()
                ),
                Drug(
                    id = 2,
                    name = "Panadol cold & flu",
                    description = "",
                    price = 40.0,
                    qty = 2,
                    productionDate = 0,
                    expiryDate = 0,
                    drugType = DrugType(
                        id = 8181,
                        name = "gm",
                        unit = "gm"
                    ),
                    pharmacies = listOf(),
                    user = null,
                    isValid = false,
                    isAvailable = false,
                    isDonated = true,
                    updatedAt = "doming",
                    image = null,
                    components = listOf()
                ),
                Drug(
                    id = 3,
                    name = "Panadol",
                    description = "",
                    price = 40.0,
                    qty = 2,
                    productionDate = 0,
                    expiryDate = 0,
                    drugType = DrugType(
                        id = 8181,
                        name = "gm",
                        unit = "gm"
                    ),
                    pharmacies = listOf(),
                    user = null,
                    isValid = false,
                    isAvailable = false,
                    isDonated = true,
                    updatedAt = "doming",
                    image = null,
                    components = listOf()

                )
            ),
            pharmaciesNearby = listOf(
                Pharmacy(
                    id = 1,
                    name = "صيدلية الدواء",
                    hotline = "",
                    order = 1,
                    isActive = true,
                    logoUrl = "https://www.al-dawaa.com/media/media/logo/stores/2/logo_720_.png"
                ),
                Pharmacy(
                    id = 2,
                    name = "صيدلية العزبي",
                    hotline = "",
                    order = 1,
                    isActive = true,
                    logoUrl = null

                )
            ),
            hasNotifications = true
        )
    )
    val uiState = _uiState.asStateFlow()
}