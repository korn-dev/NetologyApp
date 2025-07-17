package ru.korndev.netologyapp.utils

import android.text.Editable

class Converter {
    companion object {
        fun converter(num: Int): String {
            val result = when (num) {
                in 0..999 -> "$num"
                in 1_000..1_099 -> "1K"
                in 1100..9_999 -> "%.${1}f".format( num/1_000.0) + "K"  //1.1К
                in 10_000..999_999 -> "%.${0}f".format(num/1_000.0) + "K"
                else -> {
                    if (num >= 1_000_000) "%.${1}f".format(num/1_000_000.0)+ "М" else "error"  //1.1М
                }
            }
            return result
        }
    }
}