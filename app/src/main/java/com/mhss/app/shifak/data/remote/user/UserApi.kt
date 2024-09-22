package com.mhss.app.shifak.data.remote.user

import com.mhss.app.shifak.data.remote.NetworkConstants.SHIFAK_BASE_URL
import com.mhss.app.shifak.data.remote.pharmacy.GetAllPharmaciesDto
import com.mhss.app.shifak.data.remote.user.model.AddDrugRequestBody
import com.mhss.app.shifak.data.remote.user.model.DataMessageResponse
import com.mhss.app.shifak.data.remote.user.model.GetAllDrugsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
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
                    appendPathSegments("api", "user", "drugs")
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
            client.submitFormWithBinaryData(
                SHIFAK_BASE_URL,
                formData = formData {
                    append("drug_type_id", body.drugTypeId.toString())
                    body.pharmacyBranchId?.let { append("pharmacy_branch_id", it.toString()) }
                    append("name", body.name)
                    append("price", body.price.toString())
                    append("qty", body.qty.toString())
                    body.productionDate?.let { append("production_date", it) }
                    body.expiryDate?.let { append("expiry_date", it) }
                    append("is_donated", body.isDonated.toString())
                    append("is_expired", body.isExpired.toString())
                    append("description", body.description)
                    body.lat?.let { append("lat", it.toString()) }
                    body.lng?.let { append("lng", it.toString()) }
                    body.images?.forEachIndexed{ i, it ->
                        append("images[$i]", it.bytes, Headers.build {
                            append(HttpHeaders.ContentType, it.mimeType)
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=${it.name}"
                            )
                        })
                    }
                }
            ) {
                url {
                    appendPathSegments("api", "user", "drugs")
                }
                bearerAuth(token)
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