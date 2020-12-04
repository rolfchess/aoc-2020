package com.ximedes.aoc.util

import junit.framework.TestCase

class ListUtilsKtTest : TestCase() {
    fun testBetween() {
        assertFalse(4.between(5,7))
        assertTrue(5.between(5,7))
        assertTrue(6.between(5,7))
        assertFalse(8.between(5,7))
    }
}