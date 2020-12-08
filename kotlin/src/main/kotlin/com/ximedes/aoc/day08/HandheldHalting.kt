package com.ximedes.aoc.day08

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.assertTrue
import com.ximedes.aoc.util.flipPlace
import com.ximedes.aoc.util.getClasspathFile
import com.ximedes.aoc.util.startsWithAny

// :warning: *Day 4:  Toboggan Trajectory* solution thread - here be spoilers! :warning:

fun main() {
    val sw = Stopwatch("Day 8", "load input")
    val file = getClasspathFile("/input-8.txt")
    val program = file.readLines()

    sw.aboutTo("solve part 1")
    val handheld = HandHeld(program).run()
    println("Day 08 part 1 solution: ${handheld.accumulator}")
    assertTrue(1816 == handheld.accumulator)
    assertTrue(handheld.crashed)

    sw.aboutTo("solve part 2")
    var pathedProgram = patchProgram(handheld, program)
    val patchedHandHeld = HandHeld(pathedProgram).run()
    println("Day 08 part 2 solution: ${patchedHandHeld.accumulator}")
    assertTrue(1149 == patchedHandHeld.accumulator) // too high

    println(sw.stop().getMessage())
}

private fun patchProgram(handheld: HandHeld, program: List<String>): List<String> {
    val nopsjmps = handheld.executed.filter {
        program[it].startsWithAny("nop", "jmp")
    }.reversed()

    for (it in nopsjmps) {
        val pathedProgram = patchInstruction(program, it)
        val patchedHandHeld = HandHeld(pathedProgram).run()
        if (!patchedHandHeld.crashed) {
            return pathedProgram
        }
    }
    error("Was not able to patch the program")
}

private fun patchInstruction(program: List<String>, it: Int): List<String> {
    val patched = program.toMutableList()
    patched[it] = program[it].flipPlace("nop", "jmp")
    return patched
}

class HandHeld(val instructions: List<String>) {
    var accumulator = 0
    var instructionpointer = 0
    val executed = mutableSetOf<Int>()
    var crashed = false;

    fun run(): HandHeld {
        while (!crashed && (instructionpointer < instructions.size)) {
            if (executed.contains(instructionpointer)) {
                crashed = true
                return this
            }
            execute(instructions[instructionpointer])
        }
        return this
    }

    private fun execute(instruction: String) {
        val (opcode, operand) = instruction.split(" ")
        executed.add(instructionpointer)
        when (opcode) {
            "acc" -> {
                accumulator += operand.toInt()
                instructionpointer++
            }
            "jmp" -> instructionpointer += operand.toInt()
            "nop" -> instructionpointer++
            else -> error("Incorrect instruction '${instructions[instructionpointer]}'")
        }
    }
}
