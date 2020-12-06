package com.ximedes.aoc.day06

import junit.framework.TestCase

class CustomCustomsKtTest : TestCase() {
    private val testInput = """abc
    |
    |a
    |b
    |c
    |
    |ab
    |ac
    |
    |a
    |a
    |a
    |a
    |
    |b""".trimMargin()

    fun testsumCounts() {
        val sumAnyCounts = testInput.split("\n\n").asSequence().sumAnyCounts()
        assertEquals(11, sumAnyCounts)

        val sumAllCounts = testInput.split("\n\n").asSequence().sumAllCounts()
        assertEquals(6, sumAllCounts)
    }

    fun testallYes() {
        assertEquals("abc", "abc".allYes())
        assertEquals("a", "ab\nac\n".allYes())
        assertEquals("a", "ab\nac\n".allYes())
    }
}