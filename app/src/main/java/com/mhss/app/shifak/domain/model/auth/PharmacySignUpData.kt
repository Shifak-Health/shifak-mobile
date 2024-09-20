package com.mhss.app.shifak.domain.model.auth

data class PharmacySignUpData(
    val pharmacyName: String,
    val hotline: String,
    val businessNumber: String,
    val taxNumber: String,
    val email: String,
    val password: String,
    val passwordConf: String
)