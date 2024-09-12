package com.mhss.app.shifak.domain.model.user

data class FAQ(
    val id: Int,
    val question: String,
    val answer: String,
    val order: Int,
    val isAvailable: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
