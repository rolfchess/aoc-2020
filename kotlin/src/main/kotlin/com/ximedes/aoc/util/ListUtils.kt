package com.ximedes.aoc.util

// Shamelessly stolen from Joris
fun <T> Sequence<T>.without(element: T): Sequence<T> = this.filter { it != element }
fun <T> List<T>.without(element: T): List<T> = this.filter { it != element }

fun String.containsAll(strings: List<String>) =
        strings.map { if (contains(it)) 1 else 0 }.sum() == strings.size

fun String.containsOne(strings: List<String>) =
        strings.map { if (contains(it)) 1 else 0 }.sum() == 1

fun String.containsAny(strings: List<String>) =
        strings.map { if (contains(it)) 1 else 0 }.sum() >= 1

fun Int.between(a: Int, b: Int) = compareTo(a) >= 0 && compareTo(b) <= 0
