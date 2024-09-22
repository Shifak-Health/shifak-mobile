package com.mhss.app.shifak.util

import com.mhss.app.shifak.R

enum class Gender(val stringRes: Int, val networkValue: String) {
    MALE(R.string.male, "male"),
    FEMALE(R.string.female, "female")
}

enum class UserType(val networkValue: String) {
    USER("user"),
    PHARMACY("pharmacy")
}