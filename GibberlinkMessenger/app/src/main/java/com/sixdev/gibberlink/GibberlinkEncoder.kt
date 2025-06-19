package com.sixdev.gibberlink

object GibberlinkEncoder {
    private val map = mapOf(
        'a' to 'Ɑ', 'b' to 'β', 'c' to 'ↄ', 'd' to '∂', 'e' to 'Σ', 'f' to 'Ƒ', 'g' to 'Ϭ',
        'h' to 'Ή', 'i' to 'ι', 'j' to 'ʖ', 'k' to 'Ϗ', 'l' to 'Ⱡ', 'm' to 'ϻ', 'n' to 'Л',
        'o' to 'Θ', 'p' to 'Ҏ', 'q' to 'Ϙ', 'r' to 'Я', 's' to 'Ƨ', 't' to '†', 'u' to 'Ʊ',
        'v' to 'Ѵ', 'w' to 'Ш', 'x' to 'χ', 'y' to 'Ϥ', 'z' to 'Ƶ'
    )

    fun encode(input: String): String {
        return input.map { map[it.lowercaseChar()] ?: it }.joinToString("")
    }

    fun decode(input: String): String {
        val reverseMap = map.entries.associate { it.value to it.key }
        return input.map { reverseMap[it] ?: it }.joinToString("")
    }
}
