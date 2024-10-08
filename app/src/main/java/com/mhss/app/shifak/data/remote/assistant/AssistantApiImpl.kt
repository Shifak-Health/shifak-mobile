package com.mhss.app.shifak.data.remote.assistant

import com.mhss.app.shifak.data.remote.NetworkConstants
import com.mhss.app.shifak.data.remote.assistant.model.AssistantApiResponse
import com.mhss.app.shifak.data.remote.assistant.model.toAssistantRequestBody
import com.mhss.app.shifak.domain.model.assistant.AiMessage
import com.mhss.app.shifak.domain.model.assistant.AiMessageType
import com.mhss.app.shifak.domain.model.assistant.NetworkResult
import com.mhss.app.shifak.domain.repository.assistant.AssistantApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
@Single
class AssistantApiImpl(
    private val client: HttpClient,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) : AssistantApi {

    override suspend fun sendMessage(
        messages: List<AiMessage>,
        token: String
    ): NetworkResult<AiMessage> {
        return withContext(ioDispatcher) {
            val result = client.post(NetworkConstants.SHIFAK_BASE_URL) {
                url {
                    appendPathSegments("api", "chat")
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(messages.toAssistantRequestBody())
            }.body<AssistantApiResponse>()
            NetworkResult.Success(
                AiMessage(
                    content = result.text,
                    type = AiMessageType.MODEL
                )
            )
        }
    }

}