package me.mrpingu.maze

import me.mrpingu.maze.generator.MazeGraphics
import me.mrpingu.maze.generator.implementation.BacktrackerOrthogonalGenerator

fun main() {
	val maze = BacktrackerOrthogonalGenerator().generate(79, 39)
	
	MazeGraphics.printOrthogonalMaze(maze)
	MazeGraphics.showOrthogonalMaze(maze)
}