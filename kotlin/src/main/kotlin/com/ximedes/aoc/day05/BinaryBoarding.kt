package com.ximedes.aoc.day05

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.assertTrue
import com.ximedes.aoc.util.binaryStringToDecimal
import com.ximedes.aoc.util.getClasspathFile

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 5", "load input")
    val file = getClasspathFile("/input-5.txt")

    sw.aboutTo("solve part 1")
    val sortedSeats = file.readLines().map { it.seatId() }.sorted()
    val maxId = sortedSeats.last()
    println("Day 05 part 1 solution: ${maxId}")
    assertTrue(maxId == 951)

    sw.aboutTo("solve part 2")
    val firstFreeSeat = (sortedSeats.first()..sortedSeats.last())
            .filter { !sortedSeats.contains(it) }.first()
    println("Day 05 part 2 solution: ${firstFreeSeat}")
    assertTrue(firstFreeSeat == 653)

    println(sw.stop().getMessage())
}

fun String.seatId() = (substring(0, 7).binaryStringToDecimal('B') * 8) + substring(7, 10).binaryStringToDecimal('R')


// Backup implementation, not needed in puzzle
fun findRow(path: String, rows: IntRange): IntRange =
        if (path.isEmpty() || rows.first == rows.last) rows else
            when (path[0]) {
                'F' -> findRow(path.substring(1), rows.first..(rows.last - ((rows.last - rows.first) / 2) - 1))
                'B' -> findRow(path.substring(1), (rows.first + ((rows.last - rows.first) / 2) + 1)..rows.last)
                else
                -> error("don't know how to handle ${path}.")
            }