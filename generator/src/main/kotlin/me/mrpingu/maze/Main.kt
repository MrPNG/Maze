package me.mrpingu.maze

import me.mrpingu.maze.generator.MazeGraphics
import me.mrpingu.maze.generator.implementation.OrthogonalBacktrackerMazeGenerator

fun main() {
	val maze = OrthogonalBacktrackerMazeGenerator().generate(79, 39)
	
	MazeGraphics.printOrthogonalMaze(maze)
	MazeGraphics.showOrthogonalMaze(maze)
}