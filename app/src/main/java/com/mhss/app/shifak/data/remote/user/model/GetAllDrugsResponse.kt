package com.mhss.app.shifak.data.remote.user.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllDrugsResponse(
    @SerialName("data")
    val drugs: List<DrugDto>
)