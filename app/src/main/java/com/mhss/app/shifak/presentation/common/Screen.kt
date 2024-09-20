package com.mhss.app.shifak.presentation.common

import com.mhss.app.shifak.presentation.auth.AccountType
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object OnboardingScreen : Screen()

    @Serializable
    data object AccountTypeScreen : Screen()

    @Serializable
    data class AuthScreen(val accountType: AccountType) : Screen()

    @Serializable
    data class LoginScreen(val accountType: AccountType) : Screen()

    @Serializable
    data object UserSignUpScreen : Screen()

    @Serializable
    data object PharmacySignUpScreen : Screen()

}