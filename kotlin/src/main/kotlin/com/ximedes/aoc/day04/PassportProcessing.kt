package com.ximedes.aoc.day04

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.getClasspathFile

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 4", "load input")
    val file = getClasspathFile("/input-4.txt")

    sw.aboutTo("solve part 1")
    val passports = file.readText().getPassports()
    val validCount = countValidPassports(passports)
    println("Day 04 part 1 solution: ${validCount}")

    sw.aboutTo("solve part 2")
    println("Day 04 part 2 solution: YYY")

    println(sw.stop().getMessage())
}

val requiredFields = listOf("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")

fun String.getPassports() = split(Regex("""\n{2}"""))

fun countValidPassports(lines: List<String>) =
        lines.map { if (it.isValidPassport()) 1 else 0 }.sum()

fun String.isValidPassport() = containsAll(requiredFields)

fun String.containsAll(strings: List<String>) =
        strings.map{if (contains(it)) 1 else 0 }.sum() == strings.size


