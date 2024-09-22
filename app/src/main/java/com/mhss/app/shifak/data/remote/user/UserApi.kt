package com.mhss.app.shifak.data.remote.user

import com.mhss.app.shifak.data.remote.NetworkConstants.SHIFAK_BASE_URL
import com.mhss.app.shifak.data.remote.pharmacy.GetAllPharmaciesDto
import com.mhss.app.shifak.data.remote.user.model.AddDrugRequestBody
import com.mhss.app.shifak.data.remote.user.model.DataMessageResponse
import com.mhss.app.shifak.data.remote.user.model.DrugDto
import com.mhss.app.shifak.data.remote.user.model.GetAllDrugsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import io.ktor.http.parameters
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import kotlin.coroutines.CoroutineContext

@Single
class UserApi(
    private val client: HttpClient,
    @Named("ioDispatcher") private val ioDispatcher: CoroutineContext,
) {

    suspend fun getAllDrugs(
        token: String,
        fromUser: Boolean,
        donatedOnly: Boolean,
        name: String? = null,
    ): GetAllDrugsResponse {
        return withContext(ioDispatcher) {
            client.get(SHIFAK_BASE_URL) {
                url {
                    appendPathSegments("user", "drugs")
                    parameters {
                        append("my_drugs", fromUser.toString())
                        append("is_donated", donatedOnly.toString())
                        name?.let {
                            append("search", it)
                        }
                    }
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }.body<GetAllDrugsResponse>()
        }
    }

    suspend fun addDrug(
        token: String,
        body: AddDrugRequestBody,
    ): DataMessageResponse {
        return withContext(ioDispatcher) {
            client.post(SHIFAK_BASE_URL) {
                url {
                    appendPathSegments("user", "drugs")
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
                setBody(body)
            }.body<DataMessageResponse>()
        }
    }

    suspend fun getAllPharmacies(token: String): GetAllPharmaciesDto {
        return withContext(ioDispatcher) {
            client.get(SHIFAK_BASE_URL) {
                url {
                    appendPathSegments("api", "pharmacy", "pharmacies")
                }
                contentType(ContentType.Application.Json)
                bearerAuth(token)
            }.body<GetAllPharmaciesDto>()
        }
    }

}