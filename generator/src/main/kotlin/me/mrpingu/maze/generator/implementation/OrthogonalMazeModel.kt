package me.mrpingu.maze.generator.implementation

import me.mrpingu.maze.generator.ElementType
import me.mrpingu.maze.generator.ElementType.*
import me.mrpingu.maze.generator.MazeGenerator.Companion.CLOSED
import me.mrpingu.maze.generator.MazeGenerator.Companion.OPEN
import me.mrpingu.maze.generator.MazeModel
import me.mrpingu.maze.generator.extension.*
import kotlin.math.min

object OrthogonalMazeModel: MazeModel {
	
	override fun createMatrix(width: Int, height: Int) = IntMatrix(width * 2 + 1, height * 2 + 1)
	
	override fun countCells(matrix: IntMatrix): Int {
		val width = matrix.width
		val height = matrix.height
		
		return ((width - 1) / 2) * ((height - 1) / 2)
	}
	
	override fun cellIterator(matrix: IntMatrix) = OrthogonalCellIterator(matrix)
	
	override fun firstCell() = 1 to 1
	
	override fun elementType(coordinates: Coordinates): ElementType {
		val (x, y) = coordinates
		
		return when {
			x % 2 != 0 && y % 2 != 0 -> CELL
			x % 2 == 0 && y % 2 == 0 -> COLUMN
			else                     -> WALL
		}
	}
	
	override fun neighbourCells(matrix: IntMatrix, coordinates: Coordinates) =
			when (elementType(coordinates)) {
				CELL   -> {
					val width = matrix.width
					val height = matrix.height
					
					val (x, y) = coordinates
					
					arrayOf(if (x != width - 2) (x + 2) to y else null, if (y != 1) x to (y - 2) else null,
							if (x != 1) (x - 2) to y else null, if (y != height - 2) x to (y + 2) else null)
					
				}
				COLUMN -> arrayOfNulls(4)
				WALL   -> {
					val width = matrix.width
					val height = matrix.height
					
					val (x, y) = coordinates
					
					arrayOf(if (y % 2 != 0 && x != width - 1) (x + 1) to y else null, if (y % 2 == 0 && y != 0) x to (y - 1) else null,
							if (y % 2 != 0 && x != 0) (x - 1) to y else null, if (y % 2 == 0 && y != height - 1) x to (y + 1) else null)
				}
			}
	
	override fun neighbourColumns(matrix: IntMatrix, coordinates: Coordinates) =
			when (elementType(coordinates)) {
				CELL   -> arrayOfNulls(4)
				COLUMN -> arrayOfNulls(4)
				WALL   -> {
					val (x, y) = coordinates
					
					arrayOf(if (y % 2 == 0) (x + 1) to y else null, if (y % 2 != 0) x to (y - 1) else null,
							if (y % 2 == 0) (x - 1) to y else null, if (y % 2 != 0) x to (y + 1) else null)
				}
			}
	
	override fun neighbourWalls(matrix: IntMatrix, coordinates: Coordinates) =
			when (elementType(coordinates)) {
				CELL   -> {
					val width = matrix.width
					val height = matrix.height
					
					val (x, y) = coordinates
					
					arrayOf(if (x != width - 2) (x + 2) to y else null, if (y != 1) x to (y - 2) else null,
							if (x != 1) (x - 2) to y else null, if (y != height - 2) x to (y + 2) else null)
					
				}
				COLUMN -> arrayOfNulls(4)
				WALL   -> {
					val width = matrix.width
					val height = matrix.height
					
					val (x, y) = coordinates
					
					arrayOf(if (y % 2 != 0 && x != width - 1) (x + 1) to y else null, if (y % 2 == 0 && y != 0) x to (y - 1) else null,
							if (y % 2 != 0 && x != 0) (x - 1) to y else null, if (y % 2 == 0 && y != height - 1) x to (y + 1) else null)
				}
			}
	
	override fun wallBetween(cell1: Coordinates, cell2: Coordinates): Coordinates {
		val (x1, y1) = cell1
		val (x2, y2) = cell2
		
		return if (x1 == x2) x1 to (min(y1, y2) + 1) else (min(x1, x2) + 1) to y1
	}
	
	override fun closeCell(matrix: IntMatrix, cell: Coordinates) {
		matrix[cell] = CLOSED
	}
	
	override fun closeWall(matrix: IntMatrix, wall: Coordinates) {
		matrix[wall] = CLOSED
	}
	
	override fun openCell(matrix: IntMatrix, cell: Coordinates) {
		matrix[cell] = OPEN
	}
	
	override fun openWall(matrix: IntMatrix, wall: Coordinates) {
		matrix[wall] = OPEN
	}
	
	override fun validateDimensions(width: Int, height: Int) = width >= 3 && height >= 3 && width % 2 != 0 && height % 2 != 0
	
	class OrthogonalCellIterator(matrix: IntMatrix): Iterator<Coordinates> {
		
		private val maxX = matrix.width - 2
		private val maxY = matrix.height - 2
		
		private var x = 1
		private var y = 1
		
		override fun hasNext() = x != maxX || y != maxY
		
		override fun next(): Coordinates {
			require(hasNext()) { "No more cells left" }
			
			if (x == maxX) {
				x = 1
				y += 2
			} else x += 2
			
			return x to y
		}
	}
}
