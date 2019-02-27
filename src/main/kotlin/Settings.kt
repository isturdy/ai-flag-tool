package com.github.isturdy.aiflagtool

import org.apache.log4j.Level
import org.json.JSONObject

data class Settings(val json: JSONObject) {
    val ENABLE_RETROACTIVE_LOGGER = json.optBoolean("enable_retroactive_logger", true)
    val LOG_LEVEL = Level.toLevel(json.optString("log_level", "WARN"))!!
}