package me.mrpingu.maze.generator.implementation

import kotlin.random.Random

class OrthogonalBacktrackerMazeGenerator(override val random: Random = Random.Default): BacktrackerMazeGenerator(random, OrthogonalMazeModel)
