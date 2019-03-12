package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.input.InputEventAPI
import org.lwjgl.input.Keyboard

class KeyCombination(string: String) {
    companion object {
        private const val ALT_CHAR = "A"
        private const val CTRL_CHAR = "C"
        private const val SHIFT_CHAR = "S"
        private val ALL_MODIFIERS = setOf(ALT_CHAR, CTRL_CHAR, SHIFT_CHAR)
    }

    val key: Int
    val alt: Boolean
    val ctrl: Boolean
    val shift: Boolean

    init {
        val parts = string.split("-")
        val keyString = parts.last().toUpperCase()
        val modifiers = parts.dropLast(1)
        key = Keyboard.getKeyIndex(keyString)
        if (key == Keyboard.KEY_NONE) {
            throw IllegalArgumentException("Key name '$keyString' invalid (from key combination string \"$string\"")
        }

        for (modifier in modifiers) {
            if (modifier !in ALL_MODIFIERS) {
                throw IllegalArgumentException("Modifier '$modifier' invalid (from key combination string \"$string\"")
            }
        }
        alt = ALT_CHAR in modifiers
        ctrl = CTRL_CHAR in modifiers
        shift = SHIFT_CHAR in modifiers
    }

    fun matchesEvent(event: InputEventAPI): Boolean {
        if (event.isConsumed || !event.isKeyDownEvent) return false
        if (event.eventValue != key) return false
        if (event.isAltDown != alt) return false
        if (event.isCtrlDown != ctrl) return false
        if (event.isShiftDown != shift) return false
        return true
    }
}