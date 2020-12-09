package com.ximedes.aoc.day09

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.assertTrue
import com.ximedes.aoc.util.getClasspathFile
import java.util.*

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 9", "load input")
    val file = getClasspathFile("/input-9.txt")
    val data = file.readLines().map { it.toLong() }

    sw.aboutTo("solve part 1")
    val xmasDecoder = XmasDecoder()
    val firstError = data.first { !xmasDecoder.add(it) }
    println("Day 09 part 1 solution: ${firstError}")
    assertTrue(105950735L == firstError)

    sw.aboutTo("solve part 2")
    val findSumRange = xmasDecoder.findSumRange(firstError)
    println("Day 09 part 2 solution: $findSumRange")
    assertTrue(13826915L == findSumRange) // too high

    println(sw.stop().getMessage())
}


class XmasDecoder(val preambleSize: Int = 25) {
    val nums = LinkedList<Long>()

    fun add(num: Long): Boolean {
        if (!isValid(num)) return false
        nums.add(num)
        return true
    }

    fun isValid(num: Long): Boolean {
        if (nums.size < preambleSize) return true

        val preamble = nums.subList(nums.size - preambleSize, nums.size)
        preamble.forEach {
            if (preamble.contains(num - it)) return true
        }
        return false
    }

    fun findSumRange(sum: Long): Long {
        var start = 0
        var end = 0
        while (true) {
            val subList = nums.subList(start, end)
            when (subList.sum().compareTo(sum)) {
                -1 -> end += 1
                1 -> start += 1
                0 -> return (subList.minOrNull()!!) + (subList.maxOrNull()!!)
                else -> error("index out of range ${start}, ${end}.")
            }
        }
    }
}