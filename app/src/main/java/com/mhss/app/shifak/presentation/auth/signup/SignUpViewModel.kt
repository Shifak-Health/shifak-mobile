package com.mhss.app.shifak.presentation.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhss.app.shifak.domain.model.preferences.stringPreferencesKey
import com.mhss.app.shifak.domain.use_case.auth.SignUpUseCase
import com.mhss.app.shifak.domain.use_case.preferences.SaveEncryptedPreferenceUseCase
import com.mhss.app.shifak.util.PrefsConstants.TOKEN_KEY
import com.mhss.app.shifak.util.UserType
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpViewModel(
    private val signUp: SignUpUseCase,
    private val saveEncryptedPreference: SaveEncryptedPreferenceUseCase,
    userType: UserType
) : ViewModel() {

    var state by mutableStateOf(SignUpUiState(userType = userType))
        private set

    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.SignUp -> viewModelScope.launch {
                state = state.copy(loading = true, error = null)
                try {
                    val token = signUp(event.signUpData).token
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