package me.mrpingu.maze.generator.extension

import java.awt.Dimension
import java.util.*

typealias Matrix<T> = Array<Array<T>>

typealias IntMatrix = Array<IntArray>

typealias Coordinate = Pair<Int, Int>

infix fun Int.by(y: Int) = Dimension(this, y)

fun <T> stackOf() = Stack<T>()

fun <T> stackOf(element: T) = Stack<T>().apply { add(element) }

fun <T> stackOf(vararg elements: T) = Stack<T>().apply { addAll(elements) }
