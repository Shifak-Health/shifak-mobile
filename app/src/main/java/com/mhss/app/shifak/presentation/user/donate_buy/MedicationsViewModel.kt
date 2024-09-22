package com.mhss.app.shifak.presentation.user.donate_buy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.data.remote.user.UserApi
import com.mhss.app.shifak.data.remote.user.model.toDrug
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.auth.LoginUseCase
import com.mhss.app.shifak.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import com.mhss.app.shifak.domain.use_case.preferences.SaveEncryptedPreferenceUseCase
import com.mhss.app.shifak.presentation.auth.login.LoginScreenEvent
import com.mhss.app.shifak.presentation.auth.login.LoginUiState
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import com.mhss.app.shifak.util.UserType
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MedicationsViewModel(
    private val api: UserApi,
    private val getEncryptedPreference: GetEncryptedPreferenceUseCase
) : ViewModel() {

    var state by mutableStateOf(MedicationsUiState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                loading = true
            )
            try {
                val token = getEncryptedPreference(stringPreferencesKey(TOKEN_KEY), "")
                val medications = api.getAllDrugs(token, false, false)
                state = state.copy(
                    loading = false,
                    medications = medications.drugs.map { it.toDrug() },
                    error = null
                )
            } catch (e: Exception) {
                e.printStackTrace()
                state = state.copy(
                    loading = false,
                    error = e.message
                )
            }

        }
    }

    fun onEvent(event: GetMedicationsEvent) {
        when (event) {
            GetMedicationsEvent.NavigateUp -> {}
        }
    }

}