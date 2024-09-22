package com.mhss.app.shifak.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun now(): Long {
    return Clock.System.now().toEpochMilliseconds()
}

fun Long.formattedForNetwork(): String {
    val localDateTime = Instant.fromEpochMilliseconds(this).toLocalDateTime(
        TimeZone.currentSystemDefault()
    )

    return DateTimeFormatter
        .ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        .format(localDateTime.toJavaLocalDateTime())
}