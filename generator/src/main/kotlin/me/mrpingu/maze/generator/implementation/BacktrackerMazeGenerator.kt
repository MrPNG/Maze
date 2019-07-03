package me.mrpingu.maze.generator.implementation

import me.mrpingu.maze.generator.MazeAlgorithm
import me.mrpingu.maze.generator.MazeAlgorithm.Companion.CLOSED
import me.mrpingu.maze.generator.MazeModel
import me.mrpingu.maze.generator.extension.Coordinates
import me.mrpingu.maze.generator.extension.IntMatrix
import me.mrpingu.maze.generator.extension.get
import me.mrpingu.maze.generator.extension.stackOf
import kotlin.random.Random

abstract class BacktrackerMazeGenerator(override val random: Random, mazeModel: MazeModel): MazeAlgorithm, MazeModel by mazeModel {
	
	private fun randomClosedNeighbour(matrix: IntMatrix, cell: Coordinates) =
			neighbourCells(matrix, cell)
				.filterNotNull()
				.filter { matrix[it] == CLOSED }
				.let { if (it.size <= 1) it.firstOrNull() else it[random.nextInt(it.size)] }
	
	override fun generate(width: Int, height: Int): IntMatrix {
		if (!validateDimensions(width, height)) throw IllegalArgumentException()
		
		val maze = createMatrix(width, height)
		
		var cell = 1 to 1
		
		openCell(maze, cell)
		
		val stack = stackOf<Coordinates>()
		
		do {
			val neighbour = randomClosedNeighbour(maze, cell)
			
			cell = if (neighbour != null) {
				stack.push(cell)
				
				openCell(maze, neighbour)
				openWall(maze, wallBetween(cell, neighbour))
				
				neighbour
			} else stack.pop()
		} while (stack.isNotEmpty())
		
		return maze
	}
}
