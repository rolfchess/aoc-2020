package com.ximedes.aoc.day02

import junit.framework.TestCase

class PasswordPhilosophyKtTest : TestCase() {
    fun testtestSinglePassword() {
        assertTrue(isCorrectPassword("1-3 a: abcde"))
        assertFalse(isCorrectPassword("1-3 b: cdefg"))
        assertTrue(isCorrectPassword("2-9 c: ccccccccc"))
        assertTrue(isCorrectPassword("2-9 c: cc"))
        assertFalse(isCorrectPassword("2-9 c: c"))
        assertFalse(isCorrectPassword("2-9 c: cccccccccc"))
    }


    fun testtestSinglePassword2() {
        assertTrue(isCorrectPassword2("1-3 a: abcde"))
        assertFalse(isCorrectPassword2("1-3 b: cdefg"))
        assertFalse(isCorrectPassword2("2-9 c: ccccccccc"))
    }
}