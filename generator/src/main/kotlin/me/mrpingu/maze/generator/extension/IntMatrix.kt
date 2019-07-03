package me.mrpingu.maze.generator.extension

operator fun IntMatrix.get(coordinates: Coordinates) = this[coordinates.x][coordinates.y]
