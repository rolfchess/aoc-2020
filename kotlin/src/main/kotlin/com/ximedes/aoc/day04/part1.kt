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
