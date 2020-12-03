package com.ximedes.aoc.util


class CharMap(map: List<String>)
    : AocMap<Char>(map.map { str -> str.toCharArray().toMutableList() }.toMutableList())

open class AocMap<T>(map: List<List<T>>) {
    val tmap = map.map { it.toMutableList() }.toMutableList()
    val width = tmap[0].size
    val height = tmap.size
    fun get(x: Int, y: Int) = tmap[y % height][x % width]
    fun set(x: Int, y: Int, value: T) {
        tmap[y % height][x % width] = value
    }
}