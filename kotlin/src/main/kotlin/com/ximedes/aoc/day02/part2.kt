package com.ximedes.aoc.day02

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.getResourceAsText

val lineRegex = """(\d+)-(\d+) (.): (\w+)""".toRegex()

fun main() {
    val sw = Stopwatch("Day 2", "load input")

    val resourceAsText = getResourceAsText("/input-2.txt")

    sw.aboutTo("solve part 1")
    var count = 0
    resourceAsText.forEachLine {
        if (isCorrectPassword(it)) count++
    }
    println("Day 02 part 1 solution: ${count}")

    sw.aboutTo("solve part 2")
    var count2 = 0
    resourceAsText.forEachLine {
        if (isCorrectPassword2(it)) count2++
    }
    println("Day 02 part 2 solution: ${count2}")

    println(sw.stop().getMessage())
}

fun isCorrectPassword(line:String):Boolean {
    val split = lineRegex.find(line)
    val min = split!!.groupValues.get(1).toInt()
    val max = split!!.groupValues.get(2).toInt()
    val char = split!!.groupValues.get(3)[0]
    val password = split!!.groupValues.get(4)

    var occurrences = 0
    for(i in 0..password.length-1) {
        if (password[i] == char) occurrences++
    }

//    val occurrences = password.filter { c -> c == char }.length
    return min <= occurrences && max >= occurrences
}

fun isCorrectPassword2(line:String):Boolean {
    val split = lineRegex.find(line)
    val first = split!!.groupValues.get(1).toInt()
    val second = split!!.groupValues.get(2).toInt()
    val char = split!!.groupValues.get(3).toCharArray()[0]

    val password = split!!.groupValues.get(4)

    return (password[first-1] == char) != (password[second-1] == char)
}
