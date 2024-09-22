package com.mhss.app.shifak.domain.model.drug

data class DrugType(
    val id: Int,
    val name: String,
    val unit: String
)

val drugTypesMap = hashMapOf(
    1 to DrugType(1, "أقراص", "قرص"),
    2 to DrugType(2, "كبسولات", "كبسولة"),
    3 to DrugType(3, "شراب", "مل"),
    4 to DrugType(4, "حقن", "مل"),
    5 to DrugType(5, "كريم", "جرام"),
    6 to DrugType(6, "مرهم", "جرام"),
    7 to DrugType(7, "بخاخ", "رشات"),
    8 to DrugType(8, "محلول", "مل"),
    9 to DrugType(9, "قطرات", "قطرة"),
    10 to DrugType(10, "تحاميل", "تحميلة"),
    11 to DrugType(11, "لاصقات", "لاصقة")
)
