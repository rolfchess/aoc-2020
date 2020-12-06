package com.ximedes.aoc.day06

import junit.framework.TestCase

class CustomCustomsKtTest : TestCase() {
    fun testsumCounts() {
        val sumCounts = """abc

a
b
c

ab
ac

a
a
a
a

b""".split("\n\n").asSequence().sumCounts()
        assertEquals(11, sumCounts)

    }
}