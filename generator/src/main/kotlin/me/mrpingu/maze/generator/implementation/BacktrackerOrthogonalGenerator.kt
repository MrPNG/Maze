package me.mrpingu.maze.generator.implementation

import kotlin.random.Random

class BacktrackerOrthogonalGenerator(override val random: Random = Random.Default): BacktrackerGenerator, OrthogonalGenerator
