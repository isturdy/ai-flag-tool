package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.GameState
import com.fs.starfarer.api.Global
import com.fs.starfarer.api.combat.*
import com.fs.starfarer.api.input.InputEventAPI
import data.scripts.util.MagicRender
import org.apache.log4j.Logger
import org.lwjgl.util.vector.Vector2f
import java.awt.Color

class AiFlagToolCombatPlugin : BaseEveryFrameCombatPlugin() {
    private var engine: CombatEngineAPI? = null
    private var retroactiveLogger = if (AiFlagTool.SETTINGS.enableRetroactiveLogger) RetroactiveLogger() else null
    private var flagOverrides = mutableMapOf<Pair<ShipAPI, ShipwideAIFlags.AIFlags>, Boolean>()

    private var focusShip: ShipAPI? = null
    private var lastFlags: List<ShipwideAIFlags.AIFlags> = listOf()
    private var flagTracker: FlagTracker? = null

    private var enabled = true
    private var time = 0.0f

    companion object {
        @JvmField
        val LOGGER: Logger = Global.getLogger(AiFlagToolCombatPlugin::class.java)

        // Flags we never display
        private val BLOCKED_FLAGS = setOf(ShipwideAIFlags.AIFlags.MANEUVER_TARGET)
        val FLAGS = ShipwideAIFlags.AIFlags.values().filter { flag -> flag !in BLOCKED_FLAGS }

        var INSTANCE: AiFlagToolCombatPlugin? = null
    }

    override fun init(engine: CombatEngineAPI?) {
        if (engine == null) return
        this.engine = engine
        INSTANCE = this
    }

    override fun advance(amount: Float, events: List<InputEventAPI>?) {
        if (Global.getCurrentState() != GameState.COMBAT) return
        val engine = this.engine
        engine ?: return
        if (!engine.isPaused) time += amount

        retroactiveLogger?.advance(amount, engine.ships.filter { ship -> ship.ai != null })

        for ((key, state) in flagOverrides) {
            val (ship, flag) = key
            if (!ship.isAlive) {
                flagOverrides.remove(key)
                continue
            }
            if (ship.ai == null) continue
            if (state) ship.aiFlags.setFlag(flag) else ship.aiFlags.removeFlag(flag)
        }

        var newFocusShip = focusShip ?: engine.playerShip  // This fallback shouldn't trigger past the first run.
        if (events != null) {
            for (event in events) {
                if (AiFlagTool.SETTINGS.displayKey.matchesEvent(event)) {
                    enabled = !enabled
                } else if (AiFlagTool.SETTINGS.focusKey.matchesEvent(event)) {
                    newFocusShip = engine.playerShip.shipTarget ?: engine.playerShip
                    newFocusShip?.let {
                        for (message in retroactiveLogger?.getMessagesFor(it) ?: listOf()) {
                            engine.combatUI.addMessage(1, *message)
                        }
                    }
                    enabled = true
                }
            }
        }

        check (newFocusShip != null)
        if (newFocusShip.ai == null) return

        val flags = newFocusShip.aiFlags
        val setFlags = FLAGS.filter { flag -> flags.hasFlag(flag) }

        if (newFocusShip != focusShip) {
            lastFlags = setFlags
            flagTracker?.dispose()
            flagTracker = FlagTracker(newFocusShip)
            focusShip = newFocusShip
        }

        flagTracker?.update(time)

        val newFlags = setFlags - lastFlags
        val droppedFlags = lastFlags - setFlags
        lastFlags = setFlags

        if (!enabled) return

        if (newFlags.isNotEmpty()) LOGGER.debug("New flags: ${newFlags.joinToString(", ")}")
        if (droppedFlags.isNotEmpty()) LOGGER.debug("Dropped flags: ${droppedFlags.joinToString(", ")}")

        for (flag in droppedFlags) {
            engine.addFloatingText(newFocusShip.location, flag.toString(), 15.0f, Color.RED, newFocusShip, 0.0f, 0.0f)
        }
        for (flag in newFlags) {
            engine.addFloatingText(newFocusShip.location, flag.toString(), 15.0f, Color.GREEN, newFocusShip, 0.0f, 0.0f)
        }

        val shipTarget = newFocusShip.shipTarget
        if (shipTarget != null && shipTarget is CombatEntityAPI) {
            MagicRender.singleframe(
                Global.getSettings().getSprite("graphics/warroom/waypoint.png"),
                shipTarget.location,
                Vector2f(64.0f, 64.0f),
                0.0f,
                Color.RED,
                false
            )
        }

        val maneuverTarget = flags.getCustom(ShipwideAIFlags.AIFlags.MANEUVER_TARGET)
        if (maneuverTarget != null && maneuverTarget is CombatEntityAPI) {
            MagicRender.singleframe(
                Global.getSettings().getSprite("graphics/warroom/waypoint.png"),
                maneuverTarget.location,
                Vector2f(32.0f, 32.0f),
                0.0f,
                Color.BLUE,
                false
            )
        } else if (maneuverTarget != null) {
            LOGGER.warn("MANEUVER_TARGET not a CombatEntityAPI: $maneuverTarget")
        }

        if (newFocusShip.mouseTarget != null) {
            MagicRender.singleframe(
                Global.getSettings().getSprite("graphics/warroom/waypoint.png"),
                newFocusShip.mouseTarget,
                Vector2f(16.0f, 16.0f),
                0.0f,
                Color.GREEN,
                false
            )
        }
    }

    override fun renderInUICoords(viewport: ViewportAPI?) {
        flagTracker?.render()
    }

    fun overrideFlag(flag: ShipwideAIFlags.AIFlags, state: Boolean, persist: Boolean) {
        val ship = focusShip ?: throw IllegalStateException()
        engine?.combatUI?.addMessage(0, "Forcing $flag $state for ${ship.name}")
        if (persist) {
            flagOverrides[Pair(ship, flag)] = state
        } else {
            ship.aiFlags.setFlag(flag)
        }
    }

    fun resetFlag(flag: ShipwideAIFlags.AIFlags) {
        val ship = focusShip ?: throw IllegalStateException()
        engine?.combatUI?.addMessage(0, "Resetting $flag for ${ship.name}")
        flagOverrides.remove(Pair(ship, flag))
    }
}