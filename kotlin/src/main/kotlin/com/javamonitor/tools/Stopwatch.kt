package com.javamonitor.tools

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
    private val message = StringBuilder()

    /**
     * Start a new stopwatch, with a custom name.
     *
     * @param name The name of this com.javamonitor.tools.Stopwatch
     */
    init {
        start = System.currentTimeMillis()
        lastTime = start

        if (aboutTo.isEmpty()) {
            message.append("entering ").append(name).append(" took ")
        } else {
            message.append(name).append(":").append(aboutTo).append(" took ")
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
        val now = System.currentTimeMillis()
        val timeDiff = now - lastTime
        lastTime = now
        message.append(timeDiff).append("; ").append(operation)
            .append(" took ")
    }

    /**
     * Stop the stopwatch, logging the events in case the time was longer than
     * the specified threshold time value. This method is typically invoked in a
     * finally block.
     *
     * @param thresholdMillis The threshold above which we print the events.
     */
    fun stop(thresholdMillis: Long) {

        val now = System.currentTimeMillis()
        val timeDiff = now - lastTime
        lastTime = now
        val total = now - start
        if (total > thresholdMillis) {
            message.append(timeDiff).append(". Total: ").append(total).append("mS.")
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
        val now = System.currentTimeMillis()
        val timeDiff = now - lastTime
        lastTime = now
        val total = lastTime - start
        message.append(timeDiff).append(". Total: ").append(total).append("ms.")
        return this
    }

    fun getMessage():String {
        return message.toString()
    }
}