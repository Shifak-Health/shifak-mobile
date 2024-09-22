package com.mhss.app.shifak.domain.repository.auth

interface AuthRepository {

    suspend fun login(email: String, password: String): String
    suspend fun signUp(email: String, password: String, name: String, phone: String): String
}