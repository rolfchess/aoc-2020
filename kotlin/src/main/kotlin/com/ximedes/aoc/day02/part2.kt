package com.ximedes.aoc.day02

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.getResourceAsText

fun main() {
    val sw = Stopwatch("Day 2", "load input")

    val ints = mutableSetOf<Int>()
    getResourceAsText("/input-2.txt").forEachLine {
        ints.add(it.toInt())
    }

    sw.aboutTo("solve part 1")
    println("Day 02 part 1 solution: XXX")

    sw.aboutTo("solve part 2")
    println("Day 02 part 1 solution: YYY")

    println(sw.stop().getMessage())
}
