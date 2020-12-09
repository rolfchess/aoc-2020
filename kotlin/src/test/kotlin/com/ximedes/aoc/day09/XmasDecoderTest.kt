package com.ximedes.aoc.day09

import junit.framework.TestCase

class XmasDecoderTest : TestCase() {
    fun testtestExampleInput() {
        val ints = """35
            |20
            |15
            |25
            |47
            |40
            |62
            |55
            |65
            |95
            |102
            |117
            |150
            |182
            |127
            |219
            |299
            |277
            |309
            |576""".trimMargin().lines().map { it.toLong() }

        val xmasDecoder = XmasDecoder(5)
        val find = ints.find { !xmasDecoder.add(it) }
        assertEquals(127L, find)

        val findSumRange = xmasDecoder.findSumRange(127L)
        assertEquals(62,findSumRange)
    }
}