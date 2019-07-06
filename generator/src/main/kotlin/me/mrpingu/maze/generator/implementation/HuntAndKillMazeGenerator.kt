package me.mrpingu.maze.generator.implementation

import me.mrpingu.maze.generator.MazeGenerator.Companion.CLOSED
import me.mrpingu.maze.generator.MazeModel
import me.mrpingu.maze.generator.extension.Coordinates
import me.mrpingu.maze.generator.extension.IntMatrix
import me.mrpingu.maze.generator.extension.get
import kotlin.random.Random

abstract class HuntAndKillMazeGenerator(override val random: Random, mazeModel: MazeModel): NeighbourBasedMazeGenerator, MazeModel by mazeModel {
	
	override fun generate(width: Int, height: Int): IntMatrix {
		val matrix = createMatrix(width, height)
		
		val cellIterator = cellIterator(matrix)
		
		fun newBeginning(): Coordinates? {
			for (newCell in cellIterator) {
				if (matrix[newCell] == CLOSED) {
					val neighbour = randomOpenNeighbourCell(matrix, newCell)
					
					if (neighbour != null) {
						matrix open newCell
						matrix open wallBetween(newCell, neighbour)
						
						return newCell
					}
				}
			}
			
			return null
		}
		
		var cell = firstCell()
		
		matrix open cell
		
		while (true) {
			val neighbour = randomClosedNeighbourCell(matrix, cell)
			
			cell = if (neighbour != null) {
				matrix open neighbour
				matrix open wallBetween(cell, neighbour)
				
				neighbour
			} else newBeginning() ?: break
		}
		
		return matrix
	}
}
