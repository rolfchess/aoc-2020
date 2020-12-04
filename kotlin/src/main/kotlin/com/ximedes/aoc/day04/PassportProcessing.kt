package com.ximedes.aoc.day04

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.assertTrue
import com.ximedes.aoc.util.getClasspathFile

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 4", "load input")
    val file = getClasspathFile("/input-4.txt")

    sw.aboutTo("solve part 1")
    val passports = file.readText().getPassports().filter { it.containsRequiredFields() }
    println("Day 04 part 1 solution: ${passports.count()}")
    assertTrue(passports.count() == 192)

    sw.aboutTo("solve part 2")
    val validPassports = passports.filter { it.isValidPassport() }
    println("Day 04 part 2 solution: ${validPassports.size}")
    assertTrue(validPassports.count() == 101)

    println(sw.stop().getMessage())
}

fun String.getPassports() = split("\n\n").map { it.toPassport() }

data class Passport(val fields: Map<String, String>) {
    fun containsRequiredFields() =
            fields.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))

    fun isValidPassport() =
            // Fastest assert first
            fields["byr"]!!.toInt() in 1920..2002
                    && fields["iyr"]!!.toInt() in 2010..2020
                    && fields["eyr"]!!.toInt() in 2020..2030
                    && fields["hcl"]!!.matches("""#[0-9a-f]{6}""".toRegex())
                    && fields["ecl"]!!.matches("""amb|blu|brn|gry|grn|hzl|oth""".toRegex())
                    && fields["pid"]!!.matches("""[0-9]{9}""".toRegex())
                    && validHeight()

    fun validHeight(): Boolean {
        val matches = """(\d+)(cm|in)""".toRegex().matchEntire(fields["hgt"]!!)
        return matches != null && matches.groupValues.isNotEmpty() && when (matches.groupValues[2]) {
            "cm" -> matches.groupValues[1].toInt() in 150..193
            "in" -> matches.groupValues[1].toInt() in 59..96
            else -> error("Do not know how to handle ${fields["hgt"]}")
        }
    }
}

fun String.toPassport() = Passport(
        split("""[\n| ]""".toRegex())
                .map {
                    val keyvalue = it.split(":")
                    Pair(keyvalue.first(), keyvalue.last())
                }.toMap()
)