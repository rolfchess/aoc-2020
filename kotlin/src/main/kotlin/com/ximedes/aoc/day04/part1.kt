package com.ximedes.aoc.day04

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.getClasspathFile


fun main() {
    val sw = Stopwatch("Day 4", "load input")
    val file  = getClasspathFile("/input-4.txt")

    sw.aboutTo("solve part 1")
    println("Day 02 part 1 solution: XXX")

    sw.aboutTo("solve part 2")
    println("Day 02 part 2 solution: YYY")

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
