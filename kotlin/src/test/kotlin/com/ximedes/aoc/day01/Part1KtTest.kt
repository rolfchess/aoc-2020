package com.ximedes.aoc.day01

import junit.framework.TestCase

class Part1KtTest : TestCase() {

    fun testPairPart1() {
        val ints = setOf(
        1721,
        979,
        366,
        299,
        675,
        1456
        )

        val (x,y) = find2020Pair(ints)
        assertEquals(514579, x*y)
    }

    fun testPairPart2() {
        val ints = setOf(
                1721,
                979,
                366,
                299,
                675,
                1456
        )

        val (x,y,z) = findTriplet(ints)
        assertEquals(241861950, x*y*z)
    }

    fun testName() {
        val x = 2
        val y = 2
        assertTrue(x==y)
        assertFalse(x!=y)
    }
}