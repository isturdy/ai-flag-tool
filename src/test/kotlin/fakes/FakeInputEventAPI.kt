package com.github.isturdy.aiflagtool.fakes

import com.fs.starfarer.api.input.InputEventAPI
import com.fs.starfarer.api.input.InputEventClass
import com.fs.starfarer.api.input.InputEventType

class FakeInputEventAPI(private val type: InputEventType,
                        private var consumed: Boolean = false,
                        private val altDown: Boolean = false,
                        private val ctrlDown: Boolean = false,
                        private val shiftDown: Boolean = false,
                        private val value: Int = -1) : InputEventAPI {
    override fun consume() {
        consumed = true
    }

    override fun getDX(): Int {
        TODO("not implemented")
    }

    override fun getDY(): Int {
        TODO("not implemented")
    }

    override fun getEventChar(): Char {
        TODO("not implemented")
    }

    override fun getEventClass(): InputEventClass {
        TODO("not implemented")
    }

    override fun getEventType(): InputEventType {
        TODO("not implemented")
    }

    override fun getEventValue(): Int {
        return value
    }

    override fun getX(): Int {
        TODO("not implemented")
    }

    override fun getY(): Int {
        TODO("not implemented")
    }

    override fun isAltDown(): Boolean {
        return altDown
    }

    override fun isConsumed(): Boolean {
        return consumed
    }

    override fun isCtrlDown(): Boolean {
        return ctrlDown
    }

    override fun isDoubleClick(): Boolean {
        TODO("not implemented")
    }

    override fun isKeyDownEvent(): Boolean {
        return type == InputEventType.KEY_DOWN
    }

    override fun isKeyUpEvent(): Boolean {
        return type == InputEventType.KEY_UP
    }

    override fun isKeyboardEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isLMBDownEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isLMBEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isLMBUpEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isModifierKey(): Boolean {
        TODO("not implemented")
    }

    override fun isMouseDownEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isMouseEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isMouseMoveEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isMouseScrollEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isMouseUpEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isRMBDownEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isRMBEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isRMBUpEvent(): Boolean {
        TODO("not implemented")
    }

    override fun isRepeat(): Boolean {
        TODO("not implemented")
    }

    override fun isShiftDown(): Boolean {
        return shiftDown
    }

    override fun isUnmodified(): Boolean {
        return !(altDown || ctrlDown || shiftDown)
    }

    override fun logEvent() {
        TODO("not implemented")
    }
}