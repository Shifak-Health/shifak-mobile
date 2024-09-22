package com.mhss.app.shifak.data.remote.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("message")
    val message: String,
    @SerialName("token")
    val token: String,
    @SerialName("user")
    val user: UserDto
)