package br.com.samuel.barbershopapplication.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.formatCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(this).replace("R$", "").trim()
}