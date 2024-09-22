package com.mhss.app.shifak.presentation.user.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.presentation.auth.login.LoginScreen
import com.mhss.app.shifak.presentation.auth.login.LoginUiState
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme

@Composable
fun UserProfileScreen(
    state: UserProfileUiState,
    onEvent: (UserProfileEvent) -> Unit
) {
    Column(
        Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        LaunchedEffect(state.signedOut) {
            if (state.signedOut) onEvent(UserProfileEvent.Navigate(Screen.AccountTypeScreen))
        }
        ProfileScreenHeader(
            name = state.fullName,
            onEdit = { /* TODO */ },
            modifier = Modifier
                .navigationBarsPadding()
                .padding(vertical = 16.dp,)
        )
        ProfileClickableItem(
            text = stringResource(R.string.sign_out),
            onClick = {
                onEvent(UserProfileEvent.SignOut)
            }
        )

    }
}

@Preview(locale = "ar")
@Composable
private fun UserProfileScreenPreview() {
    ShifakTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            UserProfileScreen(
                UserProfileUiState(fullName = "محمد صلاح"),
                {}
            )
        }
    }
}