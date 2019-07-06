package me.mrpingu.maze.generator.extension

inline fun <T> Collection<T>.choose(predicate: (Collection<T>) -> T) = predicate(this)

inline fun <T> Collection<T>.chooseNullable(predicate: (Collection<T>) -> T?) = predicate(this)
