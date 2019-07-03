package me.mrpingu.maze.generator.implementation

import me.mrpingu.maze.generator.ElementType.*
import me.mrpingu.maze.generator.MazeAlgorithm.Companion.CLOSED
import me.mrpingu.maze.generator.MazeAlgorithm.Companion.OPEN
import me.mrpingu.maze.generator.MazeModel
import me.mrpingu.maze.generator.extension.Coordinates
import me.mrpingu.maze.generator.extension.IntMatrix
import me.mrpingu.maze.generator.extension.x
import me.mrpingu.maze.generator.extension.y

object OrthogonalMazeModel: MazeModel {
	
	override fun createMatrix(width: Int, height: Int) = IntMatrix(width, height)
	
	override fun cellCount(matrix: IntMatrix): Int {
		val width = matrix.size
		val height = matrix[0].size
		
		return ((width - 1) / 2) * ((height - 1) / 2)
	}
	
	override fun elementType(coordinates: Coordinates) =
			when {
				coordinates.x % 2 != 0 && coordinates.y % 2 != 0 -> CELL
				coordinates.x % 2 == 0 && coordinates.y % 2 == 0 -> COLUMN
				else                                             -> WALL
			}
	
	override fun neighbourCells(matrix: IntMatrix, coordinates: Coordinates) =
			when (elementType(coordinates)) {
				CELL   -> {
					val width = matrix.size
					val height = matrix[0].size
					
					val (x, y) = coordinates
					
					arrayOf(if (x != width - 2) (x + 2) to y else null, if (y != 1) x to (y - 2) else null,
							if (x != 1) (x - 2) to y else null, if (y != height - 2) x to (y + 2) else null)
					
				}
				COLUMN -> TODO()
				WALL   -> TODO()
			}
	
	override fun wallBetween(cell1: Coordinates, cell2: Coordinates): Coordinates {
		require(cell1 != cell2) { "Coordinates must be different" }
		
		val (x1, y1) = cell1
		val (x2, y2) = cell2
		
		return when {
			x1 == x2 -> if (y1 < y2) x1 to (y1 + 1) else x1 to (y1 - 1)
			y1 == y2 -> if (x1 < x2) (x1 + 1) to y1 else (x1 - 1) to y1
			else     -> throw IllegalArgumentException("Cells must be in the same row or the same column")
		}
	}
	
	override fun closeCell(matrix: IntMatrix, cell: Coordinates) {
		require(isCell(cell)) { "$cell is not a cell" }
		
		matrix[cell.x][cell.y] = CLOSED
	}
	
	override fun closeWall(matrix: IntMatrix, wall: Coordinates) {
		require(isWall(wall)) { "$wall is not a wall" }
		
		matrix[wall.x][wall.y] = CLOSED
	}
	
	override fun openCell(matrix: IntMatrix, cell: Coordinates) {
		require(isCell(cell)) { "$cell is not a cell" }
		
		matrix[cell.x][cell.y] = OPEN
	}
	
	override fun openWall(matrix: IntMatrix, wall: Coordinates) {
		require(isWall(wall)) { "$wall is not a wall" }
		
		matrix[wall.x][wall.y] = OPEN
	}
	
	override fun validateDimensions(width: Int, height: Int) = width >= 3 && height >= 3 && (width - 1) % 2 == 0 && (height - 1) % 2 == 0
}
