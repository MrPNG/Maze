package me.mrpingu.maze.generator.implementation

import me.mrpingu.maze.generator.MazeGenerator
import me.mrpingu.maze.generator.MazeGenerator.Companion.CLOSED
import me.mrpingu.maze.generator.MazeGenerator.Companion.OPEN
import me.mrpingu.maze.generator.extension.Coordinates
import me.mrpingu.maze.generator.extension.IntMatrix
import me.mrpingu.maze.generator.extension.chooseNullable
import me.mrpingu.maze.generator.extension.get

interface NeighbourBasedMazeGenerator: MazeGenerator {
	
	fun closedNeighbourCells(matrix: IntMatrix, cell: Coordinates) = neighbourCells(matrix, cell).filterNotNull().filter { matrix[it] == CLOSED }
	
	fun openNeighbourCells(matrix: IntMatrix, cell: Coordinates) = neighbourCells(matrix, cell).filterNotNull().filter { matrix[it] == OPEN }
	
	fun randomClosedNeighbourCell(matrix: IntMatrix, cell: Coordinates) =
			closedNeighbourCells(matrix, cell).chooseNullable { if (it.size > 1) it.random(random) else it.firstOrNull() }
	
	fun randomOpenNeighbourCell(matrix: IntMatrix, cell: Coordinates) =
			openNeighbourCells(matrix, cell).chooseNullable { if (it.size > 1) it.random(random) else it.firstOrNull() }
}