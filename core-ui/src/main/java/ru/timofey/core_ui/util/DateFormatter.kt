package ru.timofey.core_ui.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDateRange(start: LocalDate?, end: LocalDate?): String {
    if (start == null && end == null) return ""

    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())

    return when {
        start != null && end != null ->
            "${start.toJavaLocalDate().format(formatter)} — ${end.toJavaLocalDate().format(formatter)}"
        start != null ->
            start.toJavaLocalDate().format(formatter)
        else ->
            end!!.toJavaLocalDate().format(formatter)
    }
}
