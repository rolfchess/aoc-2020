package com.ximedes.aoc.day01

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.getClasspathFile

fun main() {
    val sw = Stopwatch("Day 1", "load input")
    val ints = mutableSetOf<Int>()
    getClasspathFile("/input-1.txt").forEachLine {
        ints.add(it.toInt())
    }

    sw.aboutTo("find pair")
    val (x, y) = find2020Pair(ints)
    println("Day 01 part 1 solution: ${x * y}")

    sw.aboutTo("find triplet (foreach version)")
    val (a, b, c) = findTriplet(ints)
    println("Day 02 part 2 solution: ${a * b * c}")

    sw.aboutTo("find triplet (indexed version)")
    val (d, e, f) = Loops(ints).find()
    println("Day 02 part 2 solution: ${d * e * f}")

    println(sw.stop().getMessage())
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

class Loops(numbers: Set<Int>) {
    val sorted = numbers.sorted().filter { it < 2020 }
    fun find():Triple<Int,Int,Int> {
        for (x in 0..sorted.size) {
            for (y in (x + 1)..sorted.size) {
                for (z in (y + 1)..sorted.size) {
                    if (sorted[x] + sorted[y] + sorted[z] == 2020) return Triple(sorted[x], sorted[y], sorted[z])
                }
            }
        }
        error("incorrect puzzle input")
    }
}