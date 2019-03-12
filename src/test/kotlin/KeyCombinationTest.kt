package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.input.InputEventType
import com.github.isturdy.aiflagtool.fakes.FakeInputEventAPI
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.lwjgl.input.Keyboard
import kotlin.test.assertFailsWith

class KeyCombinationTest {
    @Test
    fun singleKeyParsing() {
        val combinationA = KeyCombination("a")
        assertEquals(Keyboard.KEY_A, combinationA.key)
        assertEquals(false, combinationA.alt)
        assertEquals(false, combinationA.ctrl)
        assertEquals(false, combinationA.shift)

        val combination1 = KeyCombination("1")
        assertEquals(Keyboard.KEY_1, combination1.key)
        assertEquals(false, combination1.alt)
        assertEquals(false, combination1.ctrl)
        assertEquals(false, combination1.shift)

        val combinationTab = KeyCombination("tab")
        assertEquals(Keyboard.KEY_TAB, combinationTab.key)
        assertEquals(false, combinationTab.alt)
        assertEquals(false, combinationTab.ctrl)
        assertEquals(false, combinationTab.shift)
    }

    @Test
    fun modifierParsing() {
        val combinationA = KeyCombination("C-a")
        assertEquals(Keyboard.KEY_A, combinationA.key)
        assertEquals(false, combinationA.alt)
        assertEquals(true, combinationA.ctrl)
        assertEquals(false, combinationA.shift)

        val combination1 = KeyCombination("A-S-1")
        assertEquals(Keyboard.KEY_1, combination1.key)
        assertEquals(true, combination1.alt)
        assertEquals(false, combination1.ctrl)
        assertEquals(true, combination1.shift)
    }

    @Test
    fun matching() {
        val combinationA = KeyCombination("C-a")
        val eventA = FakeInputEventAPI(InputEventType.KEY_DOWN, value = Keyboard.KEY_A, ctrlDown = true)
        assertEquals(true, combinationA.matchesEvent(eventA))
        eventA.consume()
        assertEquals(false, combinationA.matchesEvent(eventA))
        val eventA1 = FakeInputEventAPI(InputEventType.KEY_DOWN, value = Keyboard.KEY_B, ctrlDown = true)
        assertEquals(false, combinationA.matchesEvent(eventA1))
        val eventA2 = FakeInputEventAPI(InputEventType.KEY_DOWN, value = Keyboard.KEY_A)
        assertEquals(false, combinationA.matchesEvent(eventA2))
        val eventA3 =
            FakeInputEventAPI(InputEventType.KEY_DOWN, value = Keyboard.KEY_A, ctrlDown = true, altDown = true)
        assertEquals(false, combinationA.matchesEvent(eventA3))
    }

    @Test
    fun invalidStrings() {
        assertFailsWith(IllegalArgumentException::class) { KeyCombination("foo") }
        assertFailsWith(IllegalArgumentException::class) { KeyCombination("M-a") }
    }
}