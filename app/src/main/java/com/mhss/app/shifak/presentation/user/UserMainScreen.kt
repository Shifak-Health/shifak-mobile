package com.mhss.app.shifak.presentation.user

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.user.donate_buy.TransactionsScreen
import com.mhss.app.shifak.presentation.user.home.UserHomeScreen
import com.mhss.app.shifak.presentation.user.home.UserHomeViewModel
import com.mhss.app.shifak.presentation.user.profile.UserProfileScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserMainScreen(
    navController: NavHostController
) {
    val navBarNavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                val navBackStackEntry by navBarNavHostController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                userMainScreenRoutes.forEach { route ->
                    NavigationBarItem(
                        icon = { Icon(painterResource(route.iconRes), null) },
                        label = { Text(stringResource(route.titleRes)) },
                        selected = currentDestination?.hierarchy?.any {
                            it.hasRoute(route.route::class)
                        } == true,
                        onClick = {
                            navBarNavHostController.navigate(route.route) {
                                popUpTo(navBarNavHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navBarNavHostController, startDestination = Screen.UserHomeScreen, Modifier.padding(innerPadding)) {
            composable<Screen.UserHomeScreen> {
                val viewModel = koinViewModel<UserHomeViewModel>()
                val uiState by viewModel.uiState.collectAsState()
                UserHomeScreen(
                    state = uiState,
                    onNavigate = {
                        navController.navigate(it)
                    },
                    onEvent = {
                        // TODO: Handle events
                    }
                )
            }
            composable<Screen.TransactionsScreen> {
                TransactionsScreen()
            }
            composable<Screen.UserProfileScreen> {
                UserProfileScreen()
            }
        }
    }
}

data class MainScreenRoute<T: Any>(val titleRes: Int, val iconRes: Int, val route: T)

val userMainScreenRoutes = listOf(
    MainScreenRoute(
        titleRes = R.string.home,
        iconRes = R.drawable.ic_home,
        route = Screen.UserHomeScreen
    ),
    MainScreenRoute(
        titleRes = R.string.transactions,
        iconRes = R.drawable.ic_transactions,
        route = Screen.TransactionsScreen
    ),
    MainScreenRoute(
        titleRes = R.string.profile,
        iconRes = R.drawable.ic_profile,
        route = Screen.UserProfileScreen
    )
)