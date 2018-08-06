package me.mrpingu.maze.generator

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import me.mrpingu.maze.generator.extension.*
import java.util.*

object RectBacktrackerGenerator: MazeGenerator {
	
	private val random = Random()
	
	override fun baseMatrix(width: Int, height: Int) = Array(height) { IntArray(width) }
	
	override fun cellCount(maze: IntMatrix): Int {
		if (!validateDimensions(maze)) throw IllegalArgumentException()
		
		val width = maze.size
		val height = maze[0].size
		
		return ((width - 1) / 2) * ((height - 1) / 2)
	}
	
	override fun generate(width: Int, height: Int): IntMatrix {
		if (!validateDimensions(width, height)) throw IllegalArgumentException()
		
		val maze = baseMatrix(width, height)
		
		var cell = 1 to 1
		openCell(maze, cell)
		
		val stack = stackOf<Coordinate>()
		
		do {
			val neighbour = randomClosedNeighbour(maze, cell)
			
			cell = if (neighbour != null) {
				stack.push(cell)
				
				val wall = wall(cell, neighbour)
				openWall(maze, wall)
				openCell(maze, neighbour)
				
				neighbour
			} else stack.pop()
		} while (stack.isNotEmpty())
		
		return maze
	}
	
	fun generateObservable(width: Int, height: Int): Observable<Coordinate> {
		if (!validateDimensions(width, height)) throw IllegalArgumentException()
		
		val maze = baseMatrix(width, height)
		val mazeList = arrayListOf<Coordinate>()
		
		var cell = 1 to 1
		openCell(maze, cell)
		
		val stack = stackOf<Coordinate>()
		
		do {
			val neighbour = randomClosedNeighbour(maze, cell)
			
			cell = if (neighbour != null) {
				stack.push(cell)
				
				val wall = wall(cell, neighbour)
				openWall(maze, wall)
				openCell(maze, neighbour)
				
				mazeList += cell
				
				neighbour
			} else stack.pop()
		} while (stack.isNotEmpty())
		
		return mazeList.toObservable()
	}
	
	override fun neighbours(maze: IntMatrix, cell: Coordinate): Array<Coordinate?> {
		if (!validateDimensions(maze)) throw IllegalArgumentException()
		
		val width = maze.size
		val height = maze[0].size
		
		val x = cell.first
		val y = cell.second
		
		return arrayOf(
				if (x != width - 2) (x + 2) to y else null,
				if (y != 1) x to (y - 2) else null,
				if (x != 1) (x - 2) to y else null,
				if (y != height - 2) x to (y + 2) else null)
	}
	
	override fun openCell(maze: IntMatrix, cell: Coordinate) {
		maze[cell.first][cell.second] = 1
	}
	
	override fun openWall(maze: IntMatrix, wall: Coordinate) {
		maze[wall.first][wall.second] = 1
	}
	
	override fun validateDimensions(width: Int, height: Int) =
			width >= 3 && height >= 3 && (width - 1) % 2 == 0 && (height - 1) % 2 == 0
	
	override fun validateDimensions(maze: IntMatrix) =
			maze.isNotEmpty() && validateDimensions(maze.size, maze[0].size)
	
	override fun wall(cell: Coordinate, otherCell: Coordinate): Coordinate {
		if (cell == otherCell) throw IllegalArgumentException()
		
		val x = cell.first
		val y = cell.second
		
		val otherX = otherCell.first
		val otherY = otherCell.second
		
		return when {
			x == otherX -> if (y < otherY) x to (y + 1) else x to (y - 1)
			y == otherY -> if (x < otherX) (x + 1) to y else (x - 1) to y
			else        -> throw IllegalArgumentException()
		}
	}
	
	private fun randomClosedNeighbour(maze: IntMatrix, cell: Coordinate) =
			neighbours(maze, cell)
					.filterNotNull()
					.filter { maze[it.first][it.second] == 0 }
					.let { if (it.size <= 1) it.firstOrNull() else it[random.nextInt(it.size)] }
}
