package com.mhss.app.shifak.data.remote.assistant.model

import com.mhss.app.shifak.data.remote.assistant.AssistantConstants.MESSAGE_MODEL_TYPE
import com.mhss.app.shifak.data.remote.assistant.AssistantConstants.MESSAGE_USER_TYPE
import com.mhss.app.shifak.domain.model.assistant.AiMessage
import com.mhss.app.shifak.domain.model.assistant.AiMessageType
import kotlinx.serialization.Serializable

@Serializable
data class AssistantApiResponse(
    val choices: List<Choice>? = null,
    val error: AssistantApiError? = null
)

@Serializable
data class Choice(
    val message: AiMessageDto
)

@Serializable
data class AssistantApiError(
    val message: String
)

fun AiMessageDto.toAiMessage() = AiMessage(
    content = content,
    type = if (role == MESSAGE_USER_TYPE) AiMessageType.USER else AiMessageType.MODEL
)

val AiMessageType.apiRole
    get() = if (this == AiMessageType.USER) MESSAGE_USER_TYPE else MESSAGE_MODEL_TYPE