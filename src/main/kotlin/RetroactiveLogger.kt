package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.Global
import com.fs.starfarer.api.combat.CombatEntityAPI
import com.fs.starfarer.api.combat.ShipAPI
import com.fs.starfarer.api.combat.ShipwideAIFlags.AIFlags

private data class AiInfo(
    var shipTarget: CombatEntityAPI?,
    var maneuverTarget: CombatEntityAPI?,
    var setFlags: Set<AIFlags>
) {
    constructor(ship: ShipAPI) : this(
        ship.shipTarget,
        ship.aiFlags.getCustom(AIFlags.MANEUVER_TARGET) as? CombatEntityAPI,
        AiFlagToolCombatPlugin.FLAGS.filter { flag -> ship.aiFlags.hasFlag(flag) }.toSet()
    )

    override fun toString(): String {
        return "Target: $shipTarget; Maneuver target: $maneuverTarget; Flags: ${setFlags.joinToString(", ")}"
    }

    // Describes the changes in this relative to 'base'.
    fun formatDiff(base: AiInfo): String {
        val components = mutableListOf<String>()
        if (base.shipTarget != shipTarget) {
            components.add("New target: $shipTarget")
        }
        if (base.maneuverTarget != maneuverTarget) {
            components.add("New maneuver target: $maneuverTarget")
        }
        if (base.setFlags != setFlags) {
            val newFlags = setFlags - base.setFlags
            val droppedFlags = base.setFlags - setFlags
            if (newFlags.isNotEmpty()) {
                components.add("New flags: ${newFlags.joinToString(", ")}")
            }
            if (droppedFlags.isNotEmpty()) {
                components.add("Dropped flags: ${droppedFlags.joinToString(", ")}")
            }
        }
        return components.joinToString("; ")
    }
}

internal class RetroactiveLogger {
    companion object {
        val LOGGER = Global.getLogger(RetroactiveLogger::class.java)!!
        private const val HISTORY_LENGTH = 8
    }

    private val data = mutableMapOf<ShipAPI, RingBuffer<Pair<Float, AiInfo>>>()
    private var time = 0.0f

    fun advance(amount: Float, ships: List<ShipAPI>) {
        time += amount
        for (ship in ships) {
            if (!data.contains(ship)) {
                data[ship] = RingBuffer(HISTORY_LENGTH)
                data[ship]!!.add(Pair(time, AiInfo(ship)))
            } else {
                val buffer = data[ship]!!
                val info = AiInfo(ship)
                if (buffer.peek().second != info) {
                    buffer.add(Pair(time, info))
                }
            }
        }
    }

    fun getMessagesFor(ship: ShipAPI): List<String> {
        val buffer = data[ship]
        buffer ?: return listOf()

        val messages = mutableListOf<String>()
        var last: AiInfo? = null
        for (info in buffer) {
            LOGGER.debug(info)
            val update = if (last == null) info.second.toString() else info.second.formatDiff(last)
            LOGGER.debug(update)
            messages.add("(${"%.1f".format(time - info.first)}s ago) $update")
            last = info.second
        }
        return messages
    }
}