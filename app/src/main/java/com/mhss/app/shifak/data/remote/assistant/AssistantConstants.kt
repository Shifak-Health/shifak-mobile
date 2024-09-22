package com.mhss.app.shifak.data.remote.assistant

object AssistantConstants {
    const val ASSISTANT_BASE_URL = "https://api.openai.com/v1"

    const val MESSAGE_USER_TYPE = "user"
    const val MESSAGE_MODEL_TYPE = "assistant"
    const val MESSAGE_SYSTEM_TYPE = "system"

}

val systemMessage = """
    You are a personal AI assistant.
    You help users with their requests and provide detailed explanations if needed.
    Users might attach notes, tasks, or calendar events. Use this attached data as a context for your response.
""".trimIndent()