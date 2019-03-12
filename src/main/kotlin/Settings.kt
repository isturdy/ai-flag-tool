package com.github.isturdy.aiflagtool

import org.apache.log4j.Level
import org.json.JSONObject

data class Settings(private val json: JSONObject) {
    val enableRetroactiveLogger = json.optBoolean("enable_retroactive_logger", true)
    val logLevel = Level.toLevel(json.optString("log_level", "WARN"))!!
}