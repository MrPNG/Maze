package me.mrpingu.maze.generator

import io.reactivex.Observable
import me.mrpingu.maze.generator.extension.*
import java.awt.Color.BLACK
import java.awt.Color.WHITE
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.JPanel

const val mazeWidth = 49
const val mazeHeight = 49

fun main(args: Array<String>) {
	val maze = RectBacktrackerGenerator.generate(mazeWidth, mazeHeight)
	val mazeObservable = RectBacktrackerGenerator.generateObservable(mazeWidth, mazeHeight)
	
	//printMaze(maze)
	//render(maze)
	renderObservable(mazeObservable)
}

fun printMaze(maze: IntMatrix) {
	maze.forEach {
		it.forEach { value ->
			print(value.toString().replace("0", "▓").replace("1", "░"))
		}
		
		println()
	}
}

const val cellSize = 16

fun render(maze: IntMatrix) {
	JFrame().run {
		contentPane = MazePanel(maze)
		
		pack()
		
		defaultCloseOperation = EXIT_ON_CLOSE
		isVisible = true
	}
}

fun renderObservable(maze: Observable<Coordinate>) {
	JFrame().run {
		contentPane = MazePanelObservable(maze)
		
		pack()
		
		defaultCloseOperation = EXIT_ON_CLOSE
		isVisible = true
	}
}

class MazePanel(val maze: IntMatrix): JPanel() {
	
	init {
		preferredSize = mazeWidth * cellSize by mazeHeight * cellSize
	}
	
	override fun paintComponent(graphics: Graphics?) {
		maze.forEachIndexed { y, array ->
			array.forEachIndexed { x, value ->
				graphics?.run {
					color = if (value == 0) BLACK else WHITE
					
					fillRect(x * cellSize, y * cellSize, cellSize, cellSize)
				}
			}
		}
	}
}

class MazePanelObservable(maze: Observable<Coordinate>): JPanel() {
	
	private var currentX: Int = 0
	private var currentY: Int = 0
	
	init {
		preferredSize = mazeWidth * cellSize by mazeHeight * cellSize
		
		maze.subscribe {
			currentX = it.first
			currentY = it.second
			
			println(it)
			
			invalidate()
		}
	}
	
	override fun paintComponent(g: Graphics?) {
		graphics?.run {
			if (currentX == 0 && currentY == 0) {
				color = BLACK
				
				fillRect(0, 0, width, height)
			} else {
				color = WHITE
				
				fillRect(currentX * cellSize, currentY * cellSize, cellSize, cellSize)
			}
		}
	}
}
