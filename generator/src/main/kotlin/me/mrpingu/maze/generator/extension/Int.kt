package me.mrpingu.maze.generator.extension

import java.awt.Dimension

infix fun Int.by(y: Int) = Dimension(this, y)
