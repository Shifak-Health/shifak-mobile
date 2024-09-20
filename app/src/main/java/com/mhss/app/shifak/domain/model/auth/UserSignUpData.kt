package com.mhss.app.shifak.domain.model.auth

data class UserSignUpData(
    val fullName: String,
    val phone: String,
    val nationalId: String,
    val email: String,
    val password: String,
    val passwordConf: String
)
