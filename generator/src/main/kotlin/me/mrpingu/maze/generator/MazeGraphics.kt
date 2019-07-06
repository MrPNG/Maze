package me.mrpingu.maze.generator

import me.mrpingu.maze.generator.MazeGenerator.Companion.CLOSED
import me.mrpingu.maze.generator.MazeGenerator.Companion.OPEN
import me.mrpingu.maze.generator.MazeGraphics.drawOrthogonalMaze
import me.mrpingu.maze.generator.extension.*
import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.JPanel

object MazeGraphics {
	
	fun drawOrthogonalMaze(graphics: Graphics2D, matrix: IntMatrix, cellSize: Int) {
		matrix.forEachIndexed { y, array ->
			array.forEachIndexed { x, value ->
				graphics.run {
					color = if (value == CLOSED) BLACK else WHITE
					
					fillRect(x * cellSize, y * cellSize, cellSize, cellSize)
				}
			}
		}
	}
	
	fun imageFromOrthogonalMaze(matrix: IntMatrix, cellSize: Int): BufferedImage {
		val width = matrix.width
		val height = matrix.height
		
		val image = BufferedImage(width * cellSize, height * cellSize, BufferedImage.TYPE_BYTE_BINARY)
		
		drawOrthogonalMaze(image.createGraphics(), matrix, cellSize)
		
		return image
	}
	
	fun printOrthogonalMaze(matrix: IntMatrix) {
		matrix.forEach { row ->
			row.forEach { value -> print("$value".replace("$CLOSED", "▓").replace("$OPEN", "░")) }
			
			println()
		}
	}
	
	fun showOrthogonalMaze(matrix: IntMatrix, cellSize: Int) {
		JFrame().run {
			defaultCloseOperation = EXIT_ON_CLOSE
			isResizable = false
			isUndecorated = true
			
			contentPane = OrthogonalMazePanel(matrix, cellSize)
			
			pack()
			isVisible = true
		}
	}
}

class OrthogonalMazePanel(private val matrix: IntMatrix, private val cellSize: Int): JPanel() {
	
	init {
		preferredSize = matrix.width * cellSize by matrix.height * cellSize
		
		addMouseClickListener { Runtime.getRuntime().exit(0) }
	}
	
	override fun paintComponent(graphics: Graphics) = drawOrthogonalMaze(graphics as Graphics2D, matrix, cellSize)
}

