package com.github.isturdy.aiflagtool

import com.fs.starfarer.api.BaseModPlugin
import com.fs.starfarer.api.Global
import org.json.JSONObject
import org.apache.log4j.Level
import org.apache.log4j.Logger
import java.lang.RuntimeException

class AiFlagTool : BaseModPlugin() {
    companion object {
        val LOGGER: Logger = Global.getLogger(AiFlagTool::class.java)
        const val SETTINGS_FILE: String = "ai_flag_tool_settings.json"
        var SETTINGS: Settings = Settings(JSONObject())
    }

    override fun onApplicationLoad() {
        if (!Global.getSettings().modManager.isModEnabled("MagicLib")) {
            throw RuntimeException(
                "AI Flag Tool requires MagicLib! Get it at http://fractalsoftworks.com/forum/index.php?topic=13718"
            )
        }
        SETTINGS = Settings(Global.getSettings().loadJSON(SETTINGS_FILE))
        LOGGER.info("AI Flag Tool settings: $SETTINGS")
        setLogLevel(SETTINGS.LOG_LEVEL)
    }

    private fun setLogLevel(level: Level) {
        AiFlagTool.LOGGER.level = level
        AiFlagToolCombatPlugin.LOGGER.level = level
        RetroactiveLogger.LOGGER.level = level
    }
}