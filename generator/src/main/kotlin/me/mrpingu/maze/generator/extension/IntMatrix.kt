package me.mrpingu.maze.generator.extension

val IntMatrix.width
	get() = this[0].size

val IntMatrix.height
	get() = size

operator fun IntMatrix.get(coordinates: Coordinates) = this[coordinates.y][coordinates.x]

operator fun IntMatrix.set(coordinates: Coordinates, state: Int) {
	this[coordinates.y][coordinates.x] = state
}
