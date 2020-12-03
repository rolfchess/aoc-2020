package com.ximedes.aoc.day03

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.getClasspathFile


fun main() {
    val sw = Stopwatch("Day 3", "load input")
    val mapLines = getClasspathFile("/input-3.txt").readLines()

    sw.aboutTo("solve part 1")
    val count = countTrees(mapLines, 3)
    println("Day 03 part 1 solution: ${count}")

    sw.aboutTo("solve part 2")
    val count2 =
            countTrees(mapLines, 1, 1) *
                    countTrees(mapLines, 3, 1) *
                    countTrees(mapLines, 5, 1) *
                    countTrees(mapLines, 7, 1) *
                    countTrees(mapLines, 1, 2)
    println("Day 03 part 2 solution: ${count2}")
    println(sw.stop().getMessage())
}

fun countTrees(map: List<String>, stepRight: Int, stepDown: Int = 1): Long {
    var count = 0L
    var x = 0
    map.forEachIndexed { y, line ->
        if (y % stepDown == 0) {
            if (hasTree(line, x)) count++
            x += stepRight
        }
    }
    return count
}

fun hasTree(line: String, index: Int) = (line[(index) % line.length] == '#')
