package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.Global
import com.fs.starfarer.api.combat.ShipAPI
import com.fs.starfarer.api.combat.ShipwideAIFlags
import com.fs.starfarer.api.combat.ShipwideAIFlags.AIFlags
import com.github.isturdy.aiflagtool.AiFlagToolCombatPlugin.Companion.FLAGS
import org.lazywizard.lazylib.ui.LazyFont
import java.awt.Color

private const val MAX_WIDTH = 400.0f
private const val MARGIN_X = 25.0f
private const val MARGIN_Y = 200.0f
private const val VERTICAL_SPACING = 10.0f

private class FlagData(private val flag: AIFlags, flags: ShipwideAIFlags, forceDisplay: Boolean) {
    private var set = flags.hasFlag(flag)
    private var lastChangedTime: Float? = null
    private var text: LazyFont.DrawableString = AiFlagTool.FONT.createText(baseColor = color(), maxWidth = MAX_WIDTH)
    private var shouldDisplay = set || forceDisplay

    fun update(time: Float, flags: ShipwideAIFlags) {
        if (set != flags.hasFlag(flag)) {
            shouldDisplay = true
            lastChangedTime = time
            set = !set
            text.baseColor = color()
        }
        if (!shouldDisplay) return

        val customDataString = when (val customData = flags.getCustom(flag)) {
            is Float -> ": ${"%.1f".format(customData)}"
            is ShipAPI -> ": ${customData.name}"
            else -> ""
        }
        val setDurationInverse = lastChangedTime?.minus(time)
        val lastSetString =
            if (setDurationInverse == null) ""
            else " (for ${"%.1f".format(-setDurationInverse)}s)"
        var string = "$flag$customDataString$lastSetString"
        if (text.font.calcWidth(string, text.fontSize) > text.maxWidth) {
            string = "$flag$customDataString\n$lastSetString"
        }
        text.text = string
    }

    // Returns the height of the drawn area.
    fun draw(x: Float, y: Float): Float {
        if (!shouldDisplay) return 0.0f
        text.draw(x, y)
        return VERTICAL_SPACING + text.height
    }

    fun dispose() {
        text.dispose()
    }

    private fun color(): Color {
        return if (set) Color.GREEN else Color.RED
    }
}

class FlagTracker(ship: ShipAPI) {
    companion object {
        // Common flags we always keep up to avoid rapid changes when switching ships.
        private val DEFAULT_FLAGS =
            setOf(
                AIFlags.BACKING_OFF,
                AIFlags.DO_NOT_PURSUE,
                AIFlags.DO_NOT_USE_SHIELDS,
                AIFlags.PREFER_LEFT_BROADSIDE,
                AIFlags.PREFER_RIGHT_BROADSIDE,
                AIFlags.PURSUING,
                AIFlags.SAFE_FROM_DANGER_TIME,
                AIFlags.SAFE_VENT
            )
    }

    private val flags = ship.aiFlags
    private val flagData = FLAGS.map { FlagData(it, flags, it in DEFAULT_FLAGS) }

    fun update(time: Float) {
        flagData.forEach { it.update(time, flags) }
    }

    fun render() {
        val displayX = Global.getSettings().screenWidth - MAX_WIDTH - MARGIN_X
        var displayY = Global.getSettings().screenHeight - MARGIN_Y
        flagData.forEach { displayY -= it.draw(displayX, displayY) }
    }

    fun dispose() {
        flagData.forEach { it.dispose() }
    }
}