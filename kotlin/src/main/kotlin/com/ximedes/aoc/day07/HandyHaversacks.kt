package com.ximedes.aoc.day07

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.assertTrue
import com.ximedes.aoc.util.getClasspathFile

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 7", "load input")
    val file = getClasspathFile("/input-7.txt")

    sw.aboutTo("solve part 1")
    file.forEachLine {
        addBagRule(it)
    }
    val containgColors = getBag("shiny gold").canBeContainedWith().size
    println("Day 07 part 1 solution: ${containgColors}")
    assertTrue(containgColors == 246)

    sw.aboutTo("solve part 2")
    val hasToContain = getBag("shiny gold").hasToContain()
    println("Day 07 part 2 solution: ${hasToContain}")
    assertTrue(hasToContain == 2976L)
    println(sw.stop().getMessage())
}

fun addBagRule(bagRule: String) {
    val (bagName, contains) = bagRule.split(" bags contain ")
    val bag = getBag(bagName)
    contains.split(",").forEach { it ->
        """(\d+) (.*) bag.*""".toRegex().find(it)?.groupValues?.let {
            bag.contains[it[2]] = it[1].toInt()
            getBag(it[2]).containedBy.add(bag.name)
        }
    }
}

val bagMap = mutableMapOf<String, Bag>()

fun getBag(bagName: String) = bagMap.computeIfAbsent(bagName) { Bag(bagName) }

class Bag(val name: String) {
    val contains = mutableMapOf<String, Int>()
    val containedBy = mutableSetOf<String>()

    fun hasToContain(): Long =
            contains.values.sum() + contains.entries.map {
                it.value * getBag(it.key).hasToContain()
            }.sum()

    fun canBeContainedWith(): Set<String> = containedBy + containedBy.flatMap {
        getBag(it).canBeContainedWith()
    }
}