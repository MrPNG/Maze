package me.mrpingu.maze.generator

import me.mrpingu.maze.generator.ElementType.*
import me.mrpingu.maze.generator.extension.Coordinates
import me.mrpingu.maze.generator.extension.IntMatrix
import kotlin.random.Random

interface MazeGenerator: MazeModel {
	
	val random: Random
	
	fun generate(length1: Int, length2: Int): IntMatrix
	
	companion object {
		
		const val OPEN = 1
		const val CLOSED = 0
	}
}

interface MazeModel {
	
	fun createMatrix(dimension1: Int, dimension2: Int): IntMatrix
	
	fun countCells(matrix: IntMatrix): Int
	
	fun cellIterator(matrix: IntMatrix): Iterator<Coordinates>
	
	fun elementType(coordinates: Coordinates): ElementType
	
	fun firstCell(): Coordinates
	
	fun isCell(coordinates: Coordinates) = elementType(coordinates) == CELL
	
	fun isColumn(coordinates: Coordinates) = elementType(coordinates) == COLUMN
	
	fun isWall(coordinates: Coordinates) = elementType(coordinates) == WALL
	
	fun neighbourCells(matrix: IntMatrix, coordinates: Coordinates): Array<Coordinates?>
	
	fun neighbourColumns(matrix: IntMatrix, coordinates: Coordinates): Array<Coordinates?>
	
	fun neighbourWalls(matrix: IntMatrix, coordinates: Coordinates): Array<Coordinates?>
	
	fun wallBetween(cell1: Coordinates, cell2: Coordinates): Coordinates
	
	fun closeCell(matrix: IntMatrix, cell: Coordinates)
	
	fun closeWall(matrix: IntMatrix, wall: Coordinates)
	
	fun openCell(matrix: IntMatrix, cell: Coordinates)
	
	fun openWall(matrix: IntMatrix, wall: Coordinates)
	
	fun validateDimensions(length1: Int, length2: Int): Boolean
	
	infix fun IntMatrix.open(coordinates: Coordinates) = if (isCell(coordinates)) openCell(this, coordinates) else openWall(this, coordinates)
}
