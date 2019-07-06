package me.mrpingu.maze.generator.implementation

import me.mrpingu.maze.generator.MazeModel
import me.mrpingu.maze.generator.extension.Coordinates
import me.mrpingu.maze.generator.extension.IntMatrix
import me.mrpingu.maze.generator.extension.stackOf
import kotlin.random.Random

abstract class BacktrackerMazeGenerator(override val random: Random, mazeModel: MazeModel): NeighbourBasedMazeGenerator, MazeModel by mazeModel {
	
	override fun generate(width: Int, height: Int): IntMatrix {
		val matrix = createMatrix(width, height)
		
		var cell = firstCell()
		
		matrix open cell
		
		val stack = stackOf<Coordinates>()
		
		do {
			val neighbour = randomClosedNeighbourCell(matrix, cell)
			
			cell = if (neighbour != null) {
				stack.push(cell)
				
				matrix open neighbour
				matrix open wallBetween(cell, neighbour)
				
				neighbour
			} else stack.pop()
		} while (stack.isNotEmpty())
		
		return matrix
	}
}
