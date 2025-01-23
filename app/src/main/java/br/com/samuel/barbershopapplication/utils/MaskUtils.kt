package br.com.samuel.barbershopapplication.utils

import java.text.NumberFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Double.formatCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(this).replace("R$", "").trim()
}

fun formatTime(time: String): String {
    val dateTimeWithSeconds = DateTimeFormatter.ofPattern("HH:mm:ss")
    val dateTimeWithoutSeconds = DateTimeFormatter.ofPattern("HH:mm")
    val timeParsed = LocalTime.parse(time, dateTimeWithSeconds)
    val timeWithoutSeconds = timeParsed.format(dateTimeWithoutSeconds)
    return timeWithoutSeconds
}