package com.mhss.app.shifak.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhss.app.shifak.R
import com.mhss.app.shifak.domain.model.auth.UserSignUpData
import com.mhss.app.shifak.presentation.common.MainButton
import com.mhss.app.shifak.presentation.common.MainTextField
import com.mhss.app.shifak.presentation.common.MainTopAppBar
import com.mhss.app.shifak.presentation.ui.theme.PrimaryColor
import com.mhss.app.shifak.presentation.ui.theme.SecondaryColor
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme

@Composable
fun UserSignUpScreen(
    onSignUpClick: (UserSignUpData) -> Unit,
    onNavigateUp: () -> Unit,
    onLoginInsteadClick: () -> Unit,
) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConf by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var nationalId by rememberSaveable { mutableStateOf("") }
    var isPasswordHidden by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.create_new_account),
                onNavigateUp = onNavigateUp,
                contentColor = Color.White
            )
        },
        modifier = Modifier.background(
            Brush.verticalGradient(
                listOf(PrimaryColor, SecondaryColor)
            )
        ),
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 48.dp, topStart = 48.dp))
                    .background(Color.White)
                    .padding(start = 32.dp, end = 32.dp, top = 43.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                MainTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    hint = stringResource(id = R.string.full_name),
                    keyboardType = KeyboardType.Text
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    hint = stringResource(id = R.string.phone_number),
                    keyboardType = KeyboardType.Phone
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = nationalId,
                    onValueChange = { nationalId = it },
                    hint = stringResource(id = R.string.national_id),
                    keyboardType = KeyboardType.Number
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = email,
                    onValueChange = { email = it },
                    hint = stringResource(id = R.string.email),
                    keyboardType = KeyboardType.Email
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = password,
                    onValueChange = { password = it },
                    hint = stringResource(id = R.string.password),
                    keyboardType = KeyboardType.Password,
                    hiddenText = isPasswordHidden,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordHidden = !isPasswordHidden }) {
                            Icon(
                                painter = if (isPasswordHidden) painterResource(R.drawable.ic_password_hidden)
                                else painterResource(R.drawable.ic_password_visible),
                                contentDescription = "Show/Hide password"
                            )
                        }
                    }
                )
                Spacer(Modifier.height(18.dp))
                MainTextField(
                    value = passwordConf,
                    onValueChange = { passwordConf = it },
                    hint = stringResource(id = R.string.confirm_password),
                    keyboardType = KeyboardType.Password,
                    hiddenText = isPasswordHidden,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordHidden = !isPasswordHidden }) {
                            Icon(
                                painter = if (isPasswordHidden) painterResource(R.drawable.ic_password_hidden)
                                else painterResource(R.drawable.ic_password_visible),
                                contentDescription = "Show/Hide password"
                            )
                        }
                    }
                )
                Spacer(Modifier.height(36.dp))

                MainButton(
                    text = stringResource(id = R.string.sign_up),
                    onClick = {
                        onSignUpClick(
                            UserSignUpData(
                                fullName = fullName,
                                phone = phoneNumber,
                                nationalId = nationalId,
                                email = email,
                                password = password,
                                passwordConf = passwordConf
                            )
                        )
                    },
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.already_have_account),
                        color = Color.Gray
                    )
                    TextButton(onClick = onLoginInsteadClick) {
                        Text(
                            text = stringResource(id = R.string.login),
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_7_PRO, locale = "ar")
@Composable
private fun UserSignUpScreenPreview() {
    ShifakTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            UserSignUpScreen(
                onSignUpClick = {},
                onNavigateUp = {},
                onLoginInsteadClick = {}
            )
        }
    }
}
