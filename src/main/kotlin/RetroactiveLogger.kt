package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.Global
import com.fs.starfarer.api.combat.CombatEntityAPI
import com.fs.starfarer.api.combat.ShipAPI
import com.fs.starfarer.api.combat.ShipwideAIFlags.AIFlags
import java.awt.Color

private val DATA_COLOR = Color.YELLOW
private val TEXT_COLOR = Color.WHITE

private fun wrapData(data: String): Array<Any> {
    return arrayOf(DATA_COLOR, data, TEXT_COLOR)
}

private fun wrapFlags(flags: Set<AIFlags>): Array<Any> {
    return flags.flatMap { flag -> listOf(*wrapData(flag.toString()), ", ") }.dropLast(1).toTypedArray()
}

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

    fun toMessage(): Array<Any> {
        return arrayOf(
            " Target: ", *wrapData(shipTarget.toString()),
            "; Maneuver target: ", *wrapData(maneuverTarget.toString()),
            "; Flags: ", *wrapFlags(setFlags)
        )
    }

    // Describes the changes in this relative to 'base'.
    fun formatDiff(base: AiInfo): Array<Any> {
        val components = mutableListOf<List<Any>>()
        if (base.shipTarget != shipTarget) {
            components.add(listOf(" New target: ", *wrapData(shipTarget.toString())))
        }
        if (base.maneuverTarget != maneuverTarget) {
            if (components.isNotEmpty()) components.add(listOf(";"))
            components.add(listOf(" New maneuver target: ", *wrapData(maneuverTarget.toString())))
        }
        if (base.setFlags != setFlags) {
            val newFlags = setFlags - base.setFlags
            val droppedFlags = base.setFlags - setFlags
            if (newFlags.isNotEmpty()) {
                if (components.isNotEmpty()) components.add(listOf(";"))
                components.add(listOf(" New flags: ", *wrapFlags(newFlags)))
            }
            if (droppedFlags.isNotEmpty()) {
                if (components.isNotEmpty()) components.add(listOf(";"))
                components.add(listOf(" Dropped flags: ", *wrapFlags(droppedFlags)))
            }
        }
        return components.flatten().toTypedArray()
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

    fun getMessagesFor(ship: ShipAPI): List<Array<Any>> {
        val buffer = data[ship]
        buffer ?: return listOf()

        val messages = mutableListOf<Array<Any>>()
        var last: AiInfo? = null
        for (info in buffer) {
            val update = if (last == null) info.second.toMessage() else info.second.formatDiff(last)
            messages.add(arrayOf("(", *wrapData("%.1f".format(time - info.first)), "s ago)", *update))
            last = info.second
        }
        return messages
    }
}