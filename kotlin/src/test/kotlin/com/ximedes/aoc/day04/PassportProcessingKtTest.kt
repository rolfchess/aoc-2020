package com.ximedes.aoc.day04

import com.ximedes.aoc.util.containsAll
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

        val passports = input.getPassports()
        assertEquals(4, passports.size)

        val validPassports = passports.withAllFields()
        assertEquals(2, validPassports.size)


    }

    fun testcontainsAll() {
        assertTrue("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd".containsAll(listOf("ecl:", "pid:", "eyr:", "hcl:")))
        assertFalse("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd".containsAll(listOf("ecl:", "pid:", "eyr:", "hcl:", "bla:")))
    }

    fun testValidPassport() {
        println(
        """hcl:#fffffd ecl:hzl
pid:658716289 byr:2001 hgt:154cm cid:234 eyr:2031 iyr:2010""".toPassport().fields.keys.toString())


        assertTrue("""hcl:#fffffd ecl:hzl
pid:658716289 byr:2001 hgt:154cm cid:234 eyr:2031 iyr:2010""".toPassport().containsRequiredFields())

        assertTrue("""hcl:#fffffd ecl:hzl
pid:658716289 byr:2001 hgt:154cm eyr:2031 iyr:2010""".toPassport().containsRequiredFields())

        assertFalse(""" ecl:hzl
pid:658716289 byr:2001 hgt:154cm cid:234 eyr:2031 iyr:2010""".toPassport().containsRequiredFields())

    }

    fun testHeightRegex() {
        val toRegex = """(\d+)(cm|in)""".toRegex()
        assertEquals(45, toRegex.find("45in")!!.groupValues[1].toInt())
        assertEquals("in", toRegex.find("45in")!!.groupValues[2])
        assertEquals(null, toRegex.find("45"))
    }

    fun testInvalidPassports() {
        val invalidPassports = """eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946

hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

hgt:59cm ecl:zzz
eyr:2038 hcl:74454a iyr:2023
pid:3556412378 byr:2007"""

        val validPassports = invalidPassports.getPassports().withAllFields().filter { it.validPassport() }
        assertEquals(0, validPassports.size)
    }

    fun testValidPassports() {
        val invalidPassports = """pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f

eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022

iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"""

        val validPassports = invalidPassports.getPassports().withAllFields().filter { it.validPassport() }
        assertEquals(4, validPassports.size)
    }
}
