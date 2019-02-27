package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.combat.ShipwideAIFlags.AIFlags
import com.github.isturdy.aiflagtool.fakes.FakeShipAPI
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color

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
        assertEquals(
            listOf(
                "(",
                Color.YELLOW,
                "0.1",
                Color.WHITE,
                "s ago)",
                " Target: ",
                Color.YELLOW,
                "t1",
                Color.WHITE,
                "; Maneuver target: ",
                Color.YELLOW,
                "null",
                Color.WHITE,
                "; Flags: "
            ), messages[0].toList()
        )
        assertEquals(
            listOf(
                "(",
                Color.YELLOW,
                "0.0",
                Color.WHITE,
                "s ago)",
                " New target: ",
                Color.YELLOW,
                "t2",
                Color.WHITE
            ), messages[1].toList()
        )
    }

    @Test
    fun noMessageIfNoChange() {
        val target1 = FakeShipAPI("t1")
        val ships = listOf(FakeShipAPI("s1"))
        ships[0].aiFlags.setFlag(AIFlags.PURSUING)
        val retroactiveLogger = RetroactiveLogger()

        retroactiveLogger.advance(1.0f, ships)
        assertEquals(1, retroactiveLogger.getMessagesFor(ships[0]).size)
        ships[0].shipTarget = target1
        retroactiveLogger.advance(1.0f, ships)
        assertEquals(2, retroactiveLogger.getMessagesFor(ships[0]).size)
        retroactiveLogger.advance(1.0f, ships)
        assertEquals(2, retroactiveLogger.getMessagesFor(ships[0]).size)
    }
}