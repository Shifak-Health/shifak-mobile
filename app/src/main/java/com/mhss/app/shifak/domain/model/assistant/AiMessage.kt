package com.mhss.app.shifak.domain.model.assistant

data class AiMessage(
    val content: String,
    val type: AiMessageType
)
enum class AiMessageType {
    USER,
    MODEL
}
