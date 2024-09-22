package com.mhss.app.shifak.domain.use_case.auth

import com.mhss.app.shifak.data.remote.auth.AuthApi
import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.domain.model.preferences.PrefsKey
import com.mhss.app.shifak.domain.repository.preferences.PreferencesRepository
import org.koin.core.annotation.Single

@Single
class LoginUseCase(
    private val api: AuthApi
) {
    suspend operator fun invoke(loginData: LoginData) = api.login(loginData)
}