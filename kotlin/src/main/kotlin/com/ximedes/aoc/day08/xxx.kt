package com.ximedes.aoc.day08

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.getClasspathFile

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 8", "load input")
    val file = getClasspathFile("/input-8.txt")

    sw.aboutTo("solve part 1")
//    file.forEachLine {
//        addBagRule(it)
//    }
//    val containgColors = getBag("shiny gold").canBeContainedWith().size
//    println("Day 08 part 1 solution: ${containgColors}")
//    assertTrue(containgColors == 248)
//
//    sw.aboutTo("solve part 2")
//    val hasToContain = getBag("shiny gold").hasToContain()
//    println("Day 08 part 2 solution: ${hasToContain}")
//    assertTrue(hasToContain == 2978L)
    println(sw.stop().getMessage())
}

