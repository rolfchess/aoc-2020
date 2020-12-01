package com.ximedes.aoc.util

// Shamelessly stolen from Joris
fun <T> Sequence<T>.without(element: T): Sequence<T> = this.filter { it != element }
fun <T> List<T>.without(element: T): List<T> = this.filter { it != element }