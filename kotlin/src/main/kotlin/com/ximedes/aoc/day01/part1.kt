package com.ximedes.aoc.day01

import com.ximedes.aoc.util.getResourceAsText

fun main() {
    val ints = mutableSetOf<Int>()
    getResourceAsText("/input-1.txt").forEachLine {
        ints.add(it.toInt())
    }

    val (x, y) = find2020Pair(ints)
    println("Puzzle 1 solution: ${x * y}")

    val (a, b, c) = findTriplet(ints)
    println("Puzzle 2 solution: ${a * b * c}")
}

fun find2020Pair(numbers: Set<Int>): Pair<Int, Int> {
    numbers.forEach {
        if (numbers.contains(2020 - it)) return Pair(it, 2020 - it)
    }
    error("incorrect puzzle input")
}

fun findTriplet(numbers: Set<Int>): Triple<Int, Int, Int> {
    val sorted = numbers.sorted().filter { it < 2020 }
    sorted.forEach { x ->
        sorted.filter { (it != x) && (it + x) < 2020 }.forEach { y ->
            sorted.find { z ->
                (z != x) && (z != y) && (x + y + z == 2020)
            }?.also { z -> return Triple(x, y, z) }
        }
    }
    error("incorrect puzzle input")
}