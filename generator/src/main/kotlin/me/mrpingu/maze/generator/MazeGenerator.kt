package me.mrpingu.maze.generator

import me.mrpingu.maze.generator.extension.Coordinate
import me.mrpingu.maze.generator.extension.IntMatrix

interface MazeGenerator {
	
	fun baseMatrix(width: Int, height: Int): IntMatrix
	
	fun cellCount(maze: IntMatrix): Int
	
	fun generate(width: Int, height: Int): IntMatrix
	
	fun neighbours(maze: IntMatrix, cell: Coordinate): Array<Coordinate?>
	
	fun openCell(maze: IntMatrix, cell: Coordinate)
	
	fun openWall(maze: IntMatrix, wall: Coordinate)
	
	fun validateDimensions(width: Int, height: Int): Boolean
	
	fun validateDimensions(maze: IntMatrix): Boolean
	
	fun wall(cell: Coordinate, otherCell: Coordinate): Coordinate
}
