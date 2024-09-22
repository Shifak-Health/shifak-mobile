package com.mhss.app.shifak.presentation.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.auth.LoginUseCase
import com.mhss.app.shifak.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import com.mhss.app.shifak.util.UserType
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val login: LoginUseCase,
    private val saveEncryptedPreference: GetEncryptedPreferenceUseCase,
    private val userType: UserType
) : ViewModel() {

    var state by mutableStateOf(LoginUiState(userType = userType))
        private set

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.Login -> viewModelScope.launch {
                state = state.copy(loading = true, error = null)
                try {
                    val token = login(event.loginData.copy(type = userType)).token
                    saveEncryptedPreference(stringPreferencesKey(TOKEN_KEY), token)
                    state = state.copy(loading = false, done = true)
                } catch (e: Exception) {
                    e.printStackTrace()
                    state = state.copy(loading = false, error = e.localizedMessage)
                }
            }
            else -> Unit
        }
    }

}