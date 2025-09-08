package ru.korndev.netologyapp.utils

import kotlin.math.floor

class Converter {
    companion object {
        fun converter(num: Int): String {
            val result = when (num) {
                in 0..999 -> "$num"
                in 1_000..1_099 -> "%.${0}f".format(floor(num/1000.0 * 10) / 10) + "K"
                in 1100..9_999 -> "%.1f".format(floor(num / 1_000.0 * 10) / 10) + "K"  //1.1К - 9.9K
                in 10_000..999_999 -> "%.${0}f".format(num/1_000.0) + "K"
                else -> {
                    if (num >= 1_000_000) "%.${1}f".format(num/1_000_000.0)+ "М" else "error"  //1.1М
                }
            }
            return result
        }
    }
}