package com.mhss.app.shifak.data.remote.auth

import com.mhss.app.shifak.data.remote.NetworkConstants.SHIFAK_BASE_URL
import com.mhss.app.shifak.data.remote.auth.model.LoginRequestBody
import com.mhss.app.shifak.data.remote.auth.model.LoginResponse
import com.mhss.app.shifak.data.remote.auth.model.SignUpResponse
import com.mhss.app.shifak.data.remote.auth.model.SignUpRequestBody
import com.mhss.app.shifak.data.remote.auth.model.toLoginRequestBody
import com.mhss.app.shifak.data.remote.auth.model.toRequestBody
import com.mhss.app.shifak.domain.model.auth.LoginData
import com.mhss.app.shifak.domain.model.auth.SignUpData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class AuthApi(
    private val client: HttpClient,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext
) {

    suspend fun login(body: LoginData): LoginResponse {
        return withContext(ioDispatcher) {
            client.post(SHIFAK_BASE_URL) {
                url {
                    appendPathSegments("api", "login")
                }
                contentType(ContentType.Application.Json)
                setBody(body.toLoginRequestBody())
            }.body<LoginResponse>()
        }
    }

    suspend fun signUp(
        body: SignUpData
    ): SignUpResponse {
        return withContext(ioDispatcher) {
            client.post(SHIFAK_BASE_URL) {
                url {
                    appendPathSegments("api", "register")
                }
                contentType(ContentType.Application.Json)
                setBody(body.toRequestBody())
            }.body<SignUpResponse>()
        }
    }

}