package com.mhss.app.shifak.domain.use_case.auth

import com.mhss.app.shifak.data.remote.auth.AuthApi
import com.mhss.app.shifak.domain.model.auth.SignUpData
import org.koin.core.annotation.Single


@Single
class SignUpUseCase(
    private val api: AuthApi
) {
    suspend operator fun invoke(signUpData: SignUpData) = api.signUp(signUpData)
}