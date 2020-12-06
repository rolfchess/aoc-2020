package com.ximedes.aoc.util

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

fun getClasspathFile(path: String) = File(object {}.javaClass.getResource(path).file)

fun File.forEachLineIndexed(charset: Charset = Charsets.UTF_8, action: (line: String) -> Unit): Unit {
    // Note: close is called at forEachLine
    BufferedReader(InputStreamReader(FileInputStream(this), charset)).forEachLine(action)
}

fun File.records(seperator: String = "\n"): Sequence<String> = readText().split(seperator).asSequence()



