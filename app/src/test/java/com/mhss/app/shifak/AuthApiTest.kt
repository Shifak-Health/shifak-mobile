package com.mhss.app.shifak

import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.domain.model.auth.SignUpData
import com.mhss.app.shifak.util.Gender
import com.mhss.app.shifak.util.UserType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AuthApiTest {

    private val fakeAuthApi = FakeAuthApi()

    @Test
    fun `test login success`() = runTest {
        fakeAuthApi.shouldReturnError = false

        val loginData = LoginData("username", "password")
        val response = fakeAuthApi.login(loginData)

        assertEquals(true, response.token.isNotEmpty())
        assertEquals("fakeToken", response.token)
    }

    @Test
    fun `test signUp success`() = runTest {
        // Set up successful signup scenario
        fakeAuthApi.shouldReturnError = false

        val signUpData = SignUpData("name", "email",
            nationalId = "",
            email = "",
            password = "",
            passwordConf = "",
            birthDate = 0,
            gender = Gender.MALE,
            type = UserType.USER,
            pharmacyName = null,
            pharmacyHotline = null,
            pharmacyLogo = null,
            acceptsExpired = null
        )

        val response = fakeAuthApi.signUp(signUpData)

        assertEquals(true, response.token.isNotEmpty())
    }

    @Test
    fun `test login error`() = runTest {
        // Set up error scenario
        fakeAuthApi.shouldReturnError = true

        val loginData = LoginData("username", "password")
        val response = fakeAuthApi.login(loginData)

        assertEquals(true, response.token.isEmpty())
    }
}