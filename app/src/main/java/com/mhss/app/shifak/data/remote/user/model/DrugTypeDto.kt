package com.mhss.app.shifak.data.remote.user.model

import com.mhss.app.shifak.domain.model.drug.DrugType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrugTypeDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("unit")
    val unit: String
)

fun DrugTypeDto.toDrugType(): DrugType {
    return DrugType(
        id = id,
        name = name,
        unit = unit
    )
}