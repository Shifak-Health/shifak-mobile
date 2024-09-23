package com.mhss.app.shifak.presentation.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.data.remote.pharmacy.toPharmacy
import com.mhss.app.shifak.data.remote.user.UserApi
import com.mhss.app.shifak.data.remote.user.model.toDrug
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserHomeViewModel(
    val getEncryptedPreference: GetEncryptedPreferenceUseCase,
    val api: UserApi
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserHomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val token = getEncryptedPreference(stringPreferencesKey(TOKEN_KEY), "")
            launch {
                try {
                    _uiState.update { it.copy(medicationsLoading = true) }
                    val medications = api.getAllDrugs(token, false, false)
                    _uiState.update {
                        it.copy(
                            medicationsLoading = false,
                            exploreMedications = medications.drugs.map { it.toDrug() }
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _uiState.update {
                        it.copy(error = e.localizedMessage, medicationsLoading = false)
                    }
                }
            }
            launch {
                try {
                    _uiState.update { it.copy(pharmaciesLoading = true) }
                    val pharmacies = api.getAllPharmacies(token)
                    _uiState.update {
                        it.copy(
                            pharmaciesLoading = false,
                            pharmacies = pharmacies.pharmacies.map { it.toPharmacy() }
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _uiState.update {
                        it.copy(error = e.localizedMessage, pharmaciesLoading = false)
                    }
                }
            }
        }
    }
}