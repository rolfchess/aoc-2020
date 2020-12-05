package com.ximedes.aoc.day05

import com.ximedes.aoc.util.binaryStringToDecimal
import junit.framework.TestCase

class BinaryBoardingKtTest : TestCase() {
    fun testFindRow() {
        // Selection (and stop)
        assertEquals(0..63, findRow("F", 0..127))
        assertEquals(64..127, findRow("B", 0..127))

        assertEquals(32..63, findRow("FB", 0..127))
        assertEquals(32..47, findRow("FBF", 0..127))
        assertEquals(40..47, findRow("FBFB", 0..127))
        assertEquals(44..47, findRow("FBFBB", 0..127))
        assertEquals(44..45, findRow("FBFBBF", 0..127))
        assertEquals(44..44, findRow("FBFBBFF", 0..127))
        assertEquals(44..44, findRow("FBFBBFFLRL", 0..127))

        // Stop criterea
        assertEquals(0..0, findRow("FX", 0..1))
        assertEquals(1..1, findRow("BX", 0..1))

        assertEquals(44..44, findRow("FBFBBFF", 0..127))
    }

    fun testalternativeNumbering() {
        assertEquals(44, 0b0101100)
        assertEquals(5, 0b101)

        assertEquals(44, "0101100".binaryStringToDecimal())
        assertEquals(44, "0B0BB00".binaryStringToDecimal('B'))

        assertEquals(5, "RLR".binaryStringToDecimal('R'))
    }

    fun testSeatId(){
        assertEquals("BFFFBBF","BFFFBBFRRR".substring(0,7))
        assertEquals("RRR","BFFFBBFRRR".substring(7,10))

        assertEquals(70, "BFFFBBF".binaryStringToDecimal('B'))
        assertEquals(7, "RRR".binaryStringToDecimal('R'))
        assertEquals(567, (70*8)+7)

        assertEquals(567, "BFFFBBFRRR".seatId())
        assertEquals(119, "FFFBBBFRRR".seatId())
        assertEquals(820, "BBFFBBFRLL".seatId())
    }

}