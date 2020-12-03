package com.ximedes.aoc.day03

import junit.framework.TestCase

class TobogganTrajectoryKtTest : TestCase() {
    val map = """..##.......
                    |#...#...#..
                    |.#....#..#.
                    |..#.#...#.#
                    |.#...##..#.
                    |..#.##.....
                    |.#.#.#....#
                    |.#........#
                    |#.##...#...
                    |#...##....#
                    |.#..#...#.#""".trimMargin()

    fun testFindTree() {
        assertFalse(hasTree("#...#...#..", 4))
        assertTrue(hasTree(".#....#..#.", 7))
    }

    fun testMap() {
        val slope = 3
        var count = countTrees(map.lines(), slope)
        assertEquals(7, count)
    }

    fun testMap2() {
        assertEquals(2, countTrees(map.lines(), 1, 1))
        assertEquals(7, countTrees(map.lines(), 3, 1))
        assertEquals(3, countTrees(map.lines(), 5, 1))
        assertEquals(4, countTrees(map.lines(), 7, 1))
        assertEquals(2, countTrees(map.lines(), 1, 2))
    }

}