package com.example.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

fun Int.toDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.toLong()
    return DATE + formatter.format(calendar.time)
}

private const val DATE = "Data: "
