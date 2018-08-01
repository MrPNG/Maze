package me.mrpingu.maze.generator

interface MazeGenerator {
	
	fun cellCount(maze: Array<IntArray>): Int
	
	fun generate(width: Int, height: Int): Array<IntArray>
	
	fun neighbours(maze: Array<IntArray>, cell: Pair<Int, Int>): Array<Pair<Int, Int>?>
	
	fun openCell(maze: Array<IntArray>, cell: Pair<Int, Int>)
	
	fun openWall(maze: Array<IntArray>, wall: Pair<Int, Int>)
	
	fun validateDimensions(width: Int, height: Int): Boolean
	
	fun validateDimensions(maze: Array<IntArray>): Boolean
	
	fun wall(cell: Pair<Int, Int>, otherCell: Pair<Int, Int>): Pair<Int, Int>
}