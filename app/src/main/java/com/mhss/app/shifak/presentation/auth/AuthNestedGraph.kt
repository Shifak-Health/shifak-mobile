package com.mhss.app.shifak.presentation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mhss.app.shifak.presentation.common.Screen

fun NavGraphBuilder.authNestedGraph(navController: NavHostController) {
    navigation<Screen.AuthGraph>(
        startDestination = Screen.AccountTypeScreen
    ) {
        composable<Screen.AccountTypeScreen> {
            AccountTypeScreen(
                onNavigate = { accountType ->
                    navController.navigate(Screen.AuthScreen(accountType))
                }
            )
        }
        composable<Screen.AuthScreen> { backStackEntry ->
            val accountType =
                backStackEntry.toRoute<Screen.AuthScreen>().accountType
            AuthScreen(
                onLogin = {
                    navController.navigate(Screen.LoginScreen(accountType))
                },
                onSignUp = {
                    navController.navigate(
                        when (accountType) {
                            AccountType.USER -> Screen.UserSignUpScreen
                            AccountType.PHARMACY -> Screen.PharmacySignUpScreen
                        }
                    )
                }
            )
        }
        composable<Screen.LoginScreen> { backStackEntry ->
            val accountType =
                backStackEntry.toRoute<Screen.AuthScreen>().accountType
            LoginScreen(
                onLoginClick = { loginData ->
                    // TODO: Implement login
                    navController.navigate(Screen.UserGraph) {
                        popUpTo(Screen.UserGraph) {
                            inclusive = true
                        }
                    }
                },
                onCreateAccountClick = {
                    navController.navigate(
                        when (accountType) {
                            AccountType.USER -> Screen.UserSignUpScreen
                            AccountType.PHARMACY -> Screen.PharmacySignUpScreen
                        }
                    ) {
                        popUpTo(Screen.LoginScreen(accountType)) {
                            inclusive = true
                        }
                    }
                },
                onForgotPasswordClick = {
                    // Todo
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }

        composable<Screen.UserSignUpScreen> {
            UserSignUpScreen(
                onSignUpClick = { signUpData ->
                    // Todo
                },
                onLoginInsteadClick = {
                    navController.navigate(Screen.LoginScreen) {
                        popUpTo(Screen.LoginScreen) {
                            inclusive = true
                        }
                    }
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }

        composable<Screen.PharmacySignUpScreen> {
            PharmacySignUpScreen(
                onSignUpClick = { signUpData ->
                    // Todo
                },
                onLoginInsteadClick = {
                    navController.navigate(Screen.LoginScreen) {
                        popUpTo(Screen.LoginScreen) {
                            inclusive = true
                        }
                    }
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}