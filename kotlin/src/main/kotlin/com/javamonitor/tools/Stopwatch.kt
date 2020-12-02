package com.javamonitor.tools

import kotlin.math.round

/**
 * A simple stopwatch timer to trace slow method calls.
 * This is a faster and leaner alternative to [org.springframework.util.StopWatch],
 * please note the differences in usage and output.
 *
 * @author Kees Jan Koster &lt;kjkoster@kjkoster.org&gt;
 */
class Stopwatch(name: String, aboutTo: String = "", val logLevel: LogLevel = LogLevel.DEBUG) {
    private val start: Long
    private var lastTime: Long

    private val texts = mutableListOf<String>()
    private val times = mutableListOf<Long>()

    /**
     * Start a new stopwatch, with a custom name.
     *
     * @param name The name of this com.javamonitor.tools.Stopwatch
     */
    init {
        start = System.nanoTime()
        lastTime = start

        if (aboutTo.isEmpty()) {
            texts.add("entering ${name} took ")
        } else {
            texts.add("${name}: ${aboutTo} took ")
        }
    }

    enum class LogLevel {
        TRACE, INFO, DEBUG, WARN, ERROR
    }

    /**
     * Start a new stopwatch, specifying the class we work for.
     *
     * @param clazz The class we work for.
     */
    constructor(clazz: Class<*>) : this(clazz.name) {}

    /**
     * Mark the time of the operation that we are about to perform.
     *
     * @param operation The operation we are about to perform.
     */
    fun aboutTo(operation: String) {
        val now = System.nanoTime()
        val timeDiff = now - lastTime
        lastTime = now
        times.add(timeDiff)
        texts.add("${operation} took ")
    }

    /**
     * Stop the stopwatch, logging the events in case the time was longer than
     * the specified threshold time value. This method is typically invoked in a
     * finally block.
     *
     * @param thresholdMillis The threshold above which we print the events.
     */
    fun stop(thresholdMillis: Long) {
        stop()
        val total = (lastTime - start).toDouble()/1_000_000
        if (total > thresholdMillis) {
            when (logLevel) {
//                LogLevel.TRACE -> logger.trace(message.toString())
//                LogLevel.INFO -> logger.info(message.toString())
//                LogLevel.WARN -> logger.warn(message.toString())
//                LogLevel.ERROR -> logger.error(message.toString())
//                LogLevel.DEBUG -> logger.debug(message.toString())
//                else -> logger.debug(message.toString())
            }
        }
    }

    fun stop() : Stopwatch {
        val now = System.nanoTime()
        val timeDiff = now - lastTime
        lastTime = now
        times.add(timeDiff)

        val total = lastTime - start
        texts.add(" Total: ")
        times.add(total)

        return this
    }

    fun getMessage():String {
        val message = StringBuilder()
        (texts zip times).forEach {
            message.append(it.first)
            message.append("%.2fms".format(it.second.toDouble()/1_000_000))
            message.append("; ")
        }
        return message.toString().substring(0, message.length-2) + "."
    }
}