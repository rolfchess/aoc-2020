package com.ximedes.aoc.util

import java.io.File

fun getResourceAsText(path: String) = File(object {}.javaClass.getResource(path).file)
