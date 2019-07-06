package me.mrpingu.maze

import me.mrpingu.maze.generator.MazeGraphics
import me.mrpingu.maze.generator.implementation.OrthogonalHuntAndKillMazeGenerator
import java.io.File
import javax.imageio.ImageIO

fun main() {
	val width = 100
	val height = 100
	val cellSize = 4
	
	val maze = OrthogonalHuntAndKillMazeGenerator().generate(width, height)
	
	ImageIO.write(MazeGraphics.imageFromOrthogonalMaze(maze, cellSize), "PNG", File("./maze.png"))
}
