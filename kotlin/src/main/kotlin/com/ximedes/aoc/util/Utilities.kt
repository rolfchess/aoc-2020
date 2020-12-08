package com.ximedes.aoc.util

fun assertTrue(check: Boolean) {
    if (!check) error("Assert failed!")
}

// These two Shamelessly stolen from Joris
fun <T> Sequence<T>.without(element: T): Sequence<T> = this.filter { it != element }
fun <T> List<T>.without(element: T): List<T> = this.filter { it != element }

fun String.containsAll(strings: List<String>) =
        strings.map { if (contains(it)) 1 else 0 }.sum() == strings.size

fun String.containsOne(strings: List<String>) =
        strings.map { if (contains(it)) 1 else 0 }.sum() == 1

fun String.containsAny(strings: List<String>) =
        strings.map { if (contains(it)) 1 else 0 }.sum() >= 1

fun String.startsWithAny(vararg strings: String): Boolean {
    strings.forEach {
        if (startsWith(it)) return true
    }.let {
        return false
    }
}

fun Int.between(a: Int, b: Int) = (a..b).contains(this)

fun pow(a: Int, b: Int) = Math.pow(a.toDouble(), b.toDouble()).toInt()

fun String.binaryStringToDecimal(high: Char = '1'): Int {
    var result = 0
    for (i in 0..length - 1) {
        if (this[length - 1 - i] == high) result += pow(2, i)
    }
    return result
}

fun String.remove(text:String) = replace(text, "")

// Replaces second with first and first with second (flipping)
fun String.flipPlace(first:String, second:String) =
        replace(first, "*flipplace*")
                .replace(second, first)
                .replace("*flipplace*", second)
