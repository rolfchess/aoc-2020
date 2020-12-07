package com.ximedes.aoc.day07

import junit.framework.TestCase

class HandyHaversacksKtTest : TestCase() {
    override fun setUp() {
        bagMap.clear()
    }

    fun testRegex() {
        addBagRule("light red bags contain 1 bright white bag, 2 muted yellow bags.")

        assertEquals(1, getBag("light red").contains["bright white"])
        assertEquals(2, getBag("light red").contains["muted yellow"])

        assertEquals(0, getBag("bright white").contains.size)
        assertEquals(0, getBag("muted yellow").contains.size)
        assertEquals(3, bagMap.size)

        addBagRule("dark orange bags contain 3 bright white bags, 4 muted yellow bags.")
        assertEquals(4, bagMap.size)
        assertEquals(2, getBag("bright white").containedBy.size)

        addBagRule("faded blue bags contain no other bags.")
        assertEquals(5, bagMap.size)
        assertEquals(0, getBag("faded blue").contains.size)

    }

    fun testtestExampleInput() {
        """light red bags contain 1 bright white bag, 2 muted yellow bags.
            |dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            |bright white bags contain 1 shiny gold bag.
            |muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            |shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            |dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            |vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            |faded blue bags contain no other bags.
            |dotted black bags contain no other bags.""".trimMargin()
                .split("\n")
                .forEach {
                    addBagRule(it)
                }
        assertEquals(9, bagMap.size)
        assertEquals(4, getBag("shiny gold").canBeContainedWith().size)
        assertEquals(126, getBag("shiny gold").hasToContain())
    }

    fun testHasToContain() {
        """shiny gold bags contain 2 dark red bags.
            |dark red bags contain 2 dark orange bags.
            |dark orange bags contain 2 dark yellow bags.
            |dark yellow bags contain 2 dark green bags.
            |dark green bags contain 2 dark blue bags.
            |dark blue bags contain 2 dark violet bags.
            |dark violet bags contain no other bags.""".trimMargin()
                .split("\n")
                .forEach {
                    addBagRule(it)
                }
        assertEquals(126, getBag("shiny gold").hasToContain())
    }

}