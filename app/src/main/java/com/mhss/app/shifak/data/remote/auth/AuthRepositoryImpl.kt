package com.mhss.app.shifak.data.remote.auth

import com.mhss.app.shifak.domain.repository.auth.AuthRepository
import org.koin.core.annotation.Single

@Single
class AuthRepositoryImpl: AuthRepository {

    override suspend fun login(email: String, password: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        phone: String,
    ): String {
        TODO("Not yet implemented")
    }
}