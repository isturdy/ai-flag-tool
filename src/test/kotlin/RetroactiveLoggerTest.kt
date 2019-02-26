package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.combat.ShipwideAIFlags.AIFlags
import com.github.isturdy.aiflagtool.fakes.FakeShipAPI
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class RetroactiveLoggerTest {
    @Test
    fun getMessagesForTwoAdvances() {
        val target1 = FakeShipAPI("t1")
        val target2 = FakeShipAPI("t2")
        val ships = listOf(FakeShipAPI("s1"))
        ships[0].shipTarget = target1
        val t0 = 0.0f
        val t1 = 0.355f
        val t2 = 0.5f
        val retroactiveLogger = RetroactiveLogger()

        retroactiveLogger.advance(t1 - t0, ships)
        ships[0].shipTarget = target2
        retroactiveLogger.advance(t2 - t1, ships)

        val messages = retroactiveLogger.getMessagesFor(ships[0])
        assertEquals(2, messages.size)
        assertEquals("(0.1s ago) Target: t1; Maneuver target: null; Flags: ", messages[0])
        assertEquals("(0.0s ago) New target: t2", messages[1])
    }

    @Test
    fun noMessageIfNoChange() {
        val target1 = FakeShipAPI("t1")
        val ships = listOf(FakeShipAPI("s1"))
        ships[0].shipTarget = target1
        ships[0].aiFlags.setFlag(AIFlags.PURSUING)
        val retroactiveLogger = RetroactiveLogger()

        retroactiveLogger.advance(1.0f, ships)
        retroactiveLogger.advance(1.0f, ships)

        val messages = retroactiveLogger.getMessagesFor(ships[0])
        assertEquals(1, messages.size)
    }
}