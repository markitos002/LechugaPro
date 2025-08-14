package eu.villacristina.lechugapro.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.math.abs

/**
 * Utilidad para formatear importes con símbolo peso y separador de miles con punto.
 * Ejemplos: $ 1.000, $ 5.000, $ 20.000
 */
object CurrencyFormatter {
    private val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = '.'
        decimalSeparator = ','
    }
    private val df = DecimalFormat("#,###").apply {
        decimalFormatSymbols = symbols
        minimumFractionDigits = 0
        maximumFractionDigits = 0
        isGroupingUsed = true
    }

    fun formatPeso(value: Double, spaceAfterSymbol: Boolean = true): String {
        val sign = if (value < 0) "-" else ""
        val formatted = df.format(abs(value))
        val symbol = if (spaceAfterSymbol) "$ " else "$"
        return sign + symbol + formatted
    }
}
