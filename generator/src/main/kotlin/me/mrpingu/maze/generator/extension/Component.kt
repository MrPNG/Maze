package me.mrpingu.maze.generator.extension

import java.awt.Component
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

fun Component.addMouseClickListener(onMouseClicked: (MouseEvent) -> Unit) = addMouseListener(MouseClickListener(onMouseClicked))

private class MouseClickListener(private val onMouseClicked: (MouseEvent) -> Unit): MouseListener by EmptyMouseListener() {
	
	override fun mouseClicked(mouseEvent: MouseEvent) = onMouseClicked(mouseEvent)
}

private class EmptyMouseListener: MouseListener {
	
	override fun mouseEntered(mouseEvent: MouseEvent) {}
	
	override fun mouseExited(mouseEvent: MouseEvent) {}
	
	override fun mousePressed(mouseEvent: MouseEvent) {}
	
	override fun mouseReleased(mouseEvent: MouseEvent) {}
	
	override fun mouseClicked(mouseEvent: MouseEvent) {}
}
