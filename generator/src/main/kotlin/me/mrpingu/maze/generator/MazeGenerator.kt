package me.mrpingu.maze.generator

import me.mrpingu.maze.generator.ElementType.*
import me.mrpingu.maze.generator.extension.Coordinates
import me.mrpingu.maze.generator.extension.IntMatrix
import kotlin.random.Random

interface MazeGenerator {
	
	val random: Random
	
	fun createMatrix(width: Int, height: Int): IntMatrix
	
	fun cellCount(matrix: IntMatrix): Int
	
	fun generate(width: Int, height: Int): IntMatrix
	
	fun elementType(coordinates: Coordinates): ElementType
	
	fun isCell(coordinates: Coordinates) = elementType(coordinates) == CELL
	
	fun isColumn(coordinates: Coordinates) = elementType(coordinates) == COLUMN
	
	fun isWall(coordinates: Coordinates) = elementType(coordinates) == WALL
	
	fun neighbourCells(matrix: IntMatrix, coordinates: Coordinates): Array<Pair<Int, Int>?>
	
	fun wallBetween(cell1: Coordinates, cell2: Coordinates): Coordinates
	
	fun closeCell(matrix: IntMatrix, cell: Coordinates)
	
	fun closeWall(matrix: IntMatrix, wall: Coordinates)
	
	fun openCell(matrix: IntMatrix, cell: Coordinates)
	
	fun openWall(matrix: IntMatrix, wall: Coordinates)
	
	fun validateDimensions(width: Int, height: Int): Boolean
	
	companion object {
		
		const val OPEN = 1
		const val CLOSED = 0
	}
}

interface MazeAlgorithm

interface MazeShape
