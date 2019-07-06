package me.mrpingu.maze.generator.implementation

import kotlin.random.Random

class OrthogonalHuntAndKillMazeGenerator(override val random: Random = Random.Default): HuntAndKillMazeGenerator(random, OrthogonalMazeModel)
