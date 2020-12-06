package com.ximedes.aoc.day06

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.assertTrue
import com.ximedes.aoc.util.getClasspathFile
import com.ximedes.aoc.util.records
import com.ximedes.aoc.util.remove

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 6", "load input")
    val file = getClasspathFile("/input-6.txt")

    sw.aboutTo("solve part 1")
    val sumAnyYes = file.records("\n\n").sumAnyCounts()
    println("Day 06 part 1 solution: ${sumAnyYes}")
    assertTrue(sumAnyYes == 6532L)

    sw.aboutTo("solve part 2")
    val sumAllYes = file.records("\n\n").sumAllCounts()
    println("Day 06 part 2 solution: ${sumAllYes}")
    assertTrue(3427 == sumAllYes)

    println(sw.stop().getMessage())
}

fun Sequence<String>.sumAnyCounts() = map { it.remove("\n").chars().distinct().count() }.sum()

fun Sequence<String>.sumAllCounts() = map { it.allYes().length }.sum()

fun String.allYes(): String =
        trim().split("\n")
                .reduce { accumulator, record ->
                    accumulator.filter { record.contains(it) }
                }
