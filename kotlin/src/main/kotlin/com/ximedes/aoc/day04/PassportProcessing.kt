package com.ximedes.aoc.day04

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.*

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 4", "load input")
    val file = getClasspathFile("/input-4.txt")

    sw.aboutTo("solve part 1")
    val passports = file.readText().getPassports().withAllFields()
    println("Day 04 part 1 solution: ${passports.count()}")
    assertTrue(passports.count() == 192)

    sw.aboutTo("solve part 2")
    val validPassports = passports.filter { it.validPassport() }
    println("Day 04 part 2 solution: ${validPassports.size}")
    assertTrue(passports.count() == 101)

    println(sw.stop().getMessage())
}

fun String.getPassports() = split(Regex("""\n{2}""")).map { it.toPassport() }

fun List<Passport>.withAllFields() = filter { it.containsRequiredFields() }

val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

data class Passport(val fields: Map<String, String>) {
    fun containsRequiredFields() = fields.keys.toString().containsAll(requiredFields)

    fun validPassport() =
            fields["byr"]!!.toInt().between(1920, 2002)
                    && fields["iyr"]!!.toInt().between(2010, 2020)
                    && fields["eyr"]!!.toInt().between(2020, 2030)
                    && fields["hcl"]!!.matches("""#[0-9a-f]{6}""".toRegex())
                    && fields["ecl"]!!.containsOne(listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth"))
                    && fields["pid"]!!.matches("""[0-9]{9}""".toRegex())
                    && validHeight()

    fun validHeight(): Boolean {
        val matchEntire = """(\d+)(cm|in)""".toRegex().matchEntire(fields["hgt"]!!)
        return matchEntire != null && matchEntire.groupValues.isNotEmpty() && when (matchEntire.groupValues[2]) {
            "cm" -> matchEntire.groupValues[1].toInt().between(150, 193)
            "in" -> matchEntire.groupValues[1].toInt().between(59, 96)
            else -> error("Do not know how to handle ${matchEntire.groupValues[2]}")
        }
    }
}

fun String.toPassport() =
        Passport(
                split("""[\n| ]""".toRegex())
                        .map {
                            val keyvalue = it.split(":")
                            Pair(keyvalue.first(), keyvalue.last())
                        }.toMap()
        )