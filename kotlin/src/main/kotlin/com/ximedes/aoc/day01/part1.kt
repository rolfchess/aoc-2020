package com.ximedes.aoc.day01

import com.ximedes.aoc.util.getResourceAsText

fun main() {
    getResourceAsText("/input-1.txt").forEachLine {
        print("${it}\n")
    }
}