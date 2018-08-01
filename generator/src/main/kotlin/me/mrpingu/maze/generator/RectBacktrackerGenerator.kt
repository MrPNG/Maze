package me.mrpingu.maze.generator

import me.mrpingu.maze.generator.extension.stackOf
import java.util.*

object Main {
	
	private val random = Random()
	
	fun generate(width: Int, height: Int): Array<IntArray> {
		if (!validateDimensions(width, height)) throw IllegalArgumentException()
		
		val maze = Array(width) { IntArray(height) { 0 } }
		
		val cellCount = cellCount(maze)
		var visited = 0
		
		var cell = 1 to 1
		openCell(maze, cell)
		
		val stack = stackOf<Pair<Int, Int>>()
		
		println(cellCount)
		
		while (visited < cellCount) {
			val neighbour = randomClosedNeighbour(maze, cell)
			
			if (neighbour != null) {
				stack.push(cell)
				
				val wall = wall(cell, neighbour)
				openWall(maze, wall)
				openCell(maze, neighbour)
				
				cell = neighbour
				
				visited++
				
				println(visited)
			} else if (stack.isNotEmpty()) cell = stack.pop()
		}
		
		return maze
	}
	
	fun validateDimensions(width: Int, height: Int) =
			width >= 3 && height >= 3 || width - 1 % 2 == 0 || height - 1 % 2 == 0
	
	fun validateDimensions(maze: Array<IntArray>) = maze.isNotEmpty() && validateDimensions(maze.size, maze[0].size)
	
	fun cellCount(maze: Array<IntArray>): Int {
		if (!validateDimensions(maze)) throw IllegalArgumentException()
		
		val width = maze.size
		val height = maze[0].size
		
		return ((width - 1) / 2) * ((height - 1) / 2)
	}
	
	fun neighbours(maze: Array<IntArray>, cell: Pair<Int, Int>): Array<Pair<Int, Int>?> {
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
	
	fun randomClosedNeighbour(maze: Array<IntArray>, cell: Pair<Int, Int>) =
			neighbours(maze, cell)
					.filterNotNull()
					.filter { maze[it.first][it.second] == 0 }
					.let { if (it.size <= 1) it.firstOrNull() else it[random.nextInt(it.size)] }
	
	fun wall(cell: Pair<Int, Int>, otherCell: Pair<Int, Int>): Pair<Int, Int> {
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
	
	fun openCell(maze: Array<IntArray>, cell: Pair<Int, Int>) {
		maze[cell.first][cell.second] = 1
	}
	
	fun openWall(maze: Array<IntArray>, wall: Pair<Int, Int>) {
		maze[wall.first][wall.second] = 1
	}
	
	fun printMaze(maze: Array<IntArray>) {
		for (i in 0 until maze.size) {
			for (j in 0 until maze[i].size)
				print(maze[i][j].toString() + " ")
			
			println(" ")
		}
	}
}
