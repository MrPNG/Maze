package me.mrpingu.maze.generator

import me.mrpingu.maze.generator.extension.IntMatrix
import java.awt.image.BufferedImage
import javax.swing.*

val width = 99
val height = 15

fun main(args: Array<String>) {
	render(RectBacktrackerGenerator.generate(width, height))
}

fun printMaze(maze: IntMatrix) {
	for (i in 0 until maze.size) {
		for (j in 0 until maze[i].size)
			print(maze[i][j].toString().replace("0", "▓").replace("1", "░"))
		
		println()
	}
}

const val cellSize = 5

fun render(maze: IntMatrix) {
	val imageWidth = width * cellSize
	val imageHeight = height * cellSize
	
	val bufferedImage = BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_BYTE_BINARY)
	
	maze.forEachIndexed { y, array ->
		array.forEachIndexed { x, value ->
			bufferedImage.setRGB(x * cellSize + 1, y * cellSize + 1, value)
		}
	}
	
	JFrame().apply {
		setSize(imageWidth, imageHeight)
		
		add(JLabel(ImageIcon(bufferedImage)))
		
		pack()
		isVisible = true
	}
}
