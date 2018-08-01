package me.mrpingu.maze.generator.extension

import java.util.*

fun <T> stackOf() = Stack<T>()

fun <T> stackOf(element: T) = Stack<T>().apply { add(element) }

fun <T> stackOf(vararg elements: T) = Stack<T>().apply { addAll(elements) }
