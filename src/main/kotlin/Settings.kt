package com.github.isturdy.aiflagtool

import org.apache.log4j.Level
import org.json.JSONObject

data class Settings(private val json: JSONObject) {
    val displayKey = KeyCombination(json.optString("display_key", "A-f"))
    val focusKey = KeyCombination(json.optString("focus_key", "C-f"))
    val enableRetroactiveLogger = json.optBoolean("enable_retroactive_logger", true)
    val logLevel = Level.toLevel(json.optString("log_level", "WARN"))!!
}