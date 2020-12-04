package com.ximedes.aoc.day04

import junit.framework.TestCase

class PassportProcessingKtTest : TestCase() {


    fun testInput() {
        val input = """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                |byr:1937 iyr:2017 cid:147 hgt:183cm
                |
                |iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
                |hcl:#cfa07d byr:1929
                |
                |hcl:#ae17e1 iyr:2013
                |eyr:2024
                |ecl:brn pid:760753108 byr:1931
                |hgt:179cm
                |
                |hcl:#cfa07d eyr:2025 pid:166559648
                |iyr:2011 ecl:brn hgt:59in""".trimMargin()

        assertEquals(0, countValidPassports(input.lines()))

        val passports = input.getPassports()
        assertEquals(4, passports.size)
        assertEquals(2, countValidPassports(passports))
    }

    fun testcontainsAll() {
        assertTrue("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd".containsAll(listOf("ecl:", "pid:", "eyr:", "hcl:")))
        assertFalse("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd".containsAll(listOf("ecl:", "pid:", "eyr:", "hcl:", "bla:")))
    }

    fun testValidPassport() {

        assertTrue("""hcl:#fffffd ecl:hzl
pid:658716289 byr:2001 hgt:154cm cid:234 eyr:2031 iyr:2010""".isValidPassport())

        assertTrue("""hcl:#fffffd ecl:hzl
pid:658716289 byr:2001 hgt:154cm eyr:2031 iyr:2010""".isValidPassport())

        assertFalse(""" ecl:hzl
pid:658716289 byr:2001 hgt:154cm cid:234 eyr:2031 iyr:2010""".isValidPassport())

    }
}
