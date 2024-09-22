package com.mhss.app.shifak.data.remote.assistant.model

import com.mhss.app.shifak.data.remote.assistant.AssistantConstants
import com.mhss.app.shifak.data.remote.assistant.systemMessage
import com.mhss.app.shifak.domain.model.assistant.AiMessage
import kotlinx.serialization.Serializable

@Serializable
data class AssistantMessageRequestBody(
    val messages: List<AiMessageDto>
)

@Serializable
data class AiMessageDto(
    val content: String,
    val role: String
)

fun List<AiMessage>.toAssistantRequestBody(): AssistantMessageRequestBody {
    return AssistantMessageRequestBody(
        messages = listOf(
            AiMessageDto(
                content = systemMessage,
                role = AssistantConstants.MESSAGE_SYSTEM_TYPE
            )
        ) + map {
            AiMessageDto(
                content = it.content,
                role = it.type.apiRole
            )
        }
    )
}