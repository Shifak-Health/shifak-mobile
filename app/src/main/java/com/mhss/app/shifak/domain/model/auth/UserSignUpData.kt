package com.mhss.app.shifak.domain.model.auth

import com.mhss.app.shifak.util.Gender

data class UserSignUpData(
    val fullName: String,
    val phone: String,
    val nationalId: String,
    val email: String,
    val password: String,
    val passwordConf: String,
    val birthDate: Long,
    val gender: Gender
)
