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


fun countTrees(map: List<String>, slope: Int, step: Int = 1): Int {
    var count = 0
    map.forEachIndexed { i, s ->
        if ((i % step == 0) && hasTree(s, ((i / step) * slope) + 1)) count++
    }
    return count
}

fun hasTree(line: String, index: Int) = (line[(index - 1) % line.length] == '#')
