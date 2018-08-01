package me.mrpingu.maze.generator

fun main(args: Array<String>) {
	printMaze(RectBacktrackerGenerator.generate(9, 9))
}

fun printMaze(maze: Array<IntArray>) {
	for (i in 0 until maze.size) {
		for (j in 0 until maze[i].size)
			print(maze[i][j].toString().replace("0", "▓").replace("1", "░") + " ")
		
		println(" ")
	}
}
