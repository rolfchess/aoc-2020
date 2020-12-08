package com.ximedes.aoc.day08

import com.javamonitor.tools.Stopwatch
import com.ximedes.aoc.util.assertTrue
import com.ximedes.aoc.util.flipPlace
import com.ximedes.aoc.util.getClasspathFile

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
        program[it].startsWith("nop") || program[it].startsWith("jmp")
    }.reversed()

    var pathedProgram = program
    for (it in nopsjmps) {
        pathedProgram = patchInstruction(program, it)
        val patchedHandHeld = HandHeld(pathedProgram).run()
        if (!patchedHandHeld.crashed) {
            break;
        }
    }
    return pathedProgram
}

private fun patchInstruction(program: List<String>, it: Int): List<String> {
    val patched = program.toMutableList()
    val toPatch = program[it]
    patched[it] = toPatch.flipPlace("nop", "jmp")
    return patched
}

class HandHeld(val instructions: List<String>) {
    var accumulator = 0
    var instructionpointer = 0
    val executed = mutableSetOf<Int>()
    var crashed = false;

    fun run(): HandHeld {
        while (!crashed) {
            // Normal exit
            if (instructionpointer >= instructions.size) return this

            if (executed.contains(instructionpointer)) {
                crashed = true
            } else {
                execute(instructions[instructionpointer])
            }
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
