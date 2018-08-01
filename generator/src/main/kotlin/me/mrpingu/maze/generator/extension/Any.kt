package me.mrpingu.maze.generator.extension

import java.util.*

typealias Matrix<T> = Array<Array<T>>

typealias IntMatrix = Array<IntArray>

typealias Coordinate = Pair<Int, Int>

fun <T> stackOf() = Stack<T>()

fun <T> stackOf(element: T) = Stack<T>().apply { add(element) }

fun <T> stackOf(vararg elements: T) = Stack<T>().apply { addAll(elements) }
