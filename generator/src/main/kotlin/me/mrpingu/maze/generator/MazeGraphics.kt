package me.mrpingu.maze.generator

import me.mrpingu.maze.generator.MazeAlgorithm.Companion.CLOSED
import me.mrpingu.maze.generator.MazeAlgorithm.Companion.OPEN
import me.mrpingu.maze.generator.MazeGraphics.drawOrthogonalMaze
import me.mrpingu.maze.generator.extension.IntMatrix
import me.mrpingu.maze.generator.extension.addMouseClickListener
import me.mrpingu.maze.generator.extension.by
import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.JPanel

const val cellSize = 16

object MazeGraphics {
	
	fun drawOrthogonalMaze(matrix: IntMatrix, graphics: Graphics2D) {
		matrix.forEachIndexed { y, array ->
			array.forEachIndexed { x, value ->
				graphics.run {
					color = if (value == CLOSED) BLACK else WHITE
					
					fillRect(x * cellSize, y * cellSize, cellSize, cellSize)
				}
			}
		}
	}
	
	fun printOrthogonalMaze(matrix: IntMatrix) {
		matrix.forEach { row ->
			row.forEach { value -> print("$value".replace("$CLOSED", "▓").replace("$OPEN", "░")) }
			
			println()
		}
	}
	
	fun showOrthogonalMaze(matrix: IntMatrix) {
		JFrame().run {
			defaultCloseOperation = EXIT_ON_CLOSE
			isResizable = false
			isUndecorated = true
			
			contentPane = OrthogonalMazePanel(matrix)
			
			pack()
			isVisible = true
		}
	}
}

class OrthogonalMazePanel(private val matrix: IntMatrix): JPanel() {
	
	init {
		preferredSize = matrix[0].size * cellSize by matrix.size * cellSize
		
		addMouseClickListener { Runtime.getRuntime().exit(0) }
	}
	
	override fun paintComponent(graphics: Graphics) = drawOrthogonalMaze(matrix, graphics as Graphics2D)
}

