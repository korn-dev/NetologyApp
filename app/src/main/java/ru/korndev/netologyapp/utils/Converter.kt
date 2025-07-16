package ru.korndev.netologyapp.utils

class Converter {
    fun converter(num: Int): String {
        val result = when (num) {
            in 0..999 -> "$num"
            in 1_000..1_099 -> "1K"
            in 1100..9_999 -> "1.1K"
            in 10_000..999_999 -> "1K"
            else -> { if (num >= 1_000_000) "1.0M" else "error" }
        }
        return result
    }
}