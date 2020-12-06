package com.ximedes.aoc.day06

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.getClasspathFile
import com.ximedes.aoc.util.records
import com.ximedes.aoc.util.remove

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 6", "load input")
    val file = getClasspathFile("/input-6.txt")

    sw.aboutTo("solve part 1")
    val sumCounts = file.records("\n\n").sumCounts()
    println("Day 06 part 1 solution: ${sumCounts}")
//    assertTrue(false)
//
//    sw.aboutTo("solve part 2")
//    println("Day 05 part 2 solution: YYY")
//    assertTrue(false)

    println(sw.stop().getMessage())
}

fun Sequence<String>.sumCounts() = map { it.remove("\n").chars().distinct().count() }.sum()
