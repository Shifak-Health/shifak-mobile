package com.mhss.app.shifak.presentation.common

import com.mhss.app.shifak.presentation.auth.AccountType
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object AuthGraph : Screen()

    @Serializable
    data object UserGraph : Screen()

    @Serializable
    data object PharmacyGraph : Screen()

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

    @Serializable
    data object UserHomeScreen : Screen()

    @Serializable
    data object UserMainScreen : Screen()

    @Serializable
    data object SmartAssistantScreen : Screen()

    @Serializable
    data object AddMedicationScreen : Screen()

    @Serializable
    data object MedicationsScreen : Screen()

    @Serializable
    data object UserProfileScreen : Screen()

    @Serializable
    data object TransactionsScreen : Screen()

    @Serializable
    data object FAQScreen : Screen()

}