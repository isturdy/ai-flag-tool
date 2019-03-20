package com.github.isturdy.aiflagtool;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.lazywizard.lazylib.ui.FontException;
import org.lazywizard.lazylib.ui.LazyFont;

import java.io.IOException;

public final class AiFlagTool extends BaseModPlugin {
    @NotNull
    public static final Logger LOGGER = Global.getLogger(AiFlagTool.class);
    public static final String MOD_ID = "ai_flag_tool";
    @NotNull
    public static Settings SETTINGS = new Settings(new JSONObject());
    public static LazyFont FONT;

    private static final String SETTINGS_FILE = "ai_flag_tool_settings.json";

    @Override
    public void onApplicationLoad() throws FontException, IOException, JSONException {
        if (!Global.getSettings().getModManager().isModEnabled("lw_lazylib")) {
            throw new RuntimeException("Quick Tournament requires LazyLib!"
                    + "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=5444");
        }

        if (!Global.getSettings().getModManager().isModEnabled("MagicLib")) {
            throw new RuntimeException("Quick Tournament requires MagicLib!"
                    + "\nGet it at http://fractalsoftworks.com/forum/index.php?topic=13718");
        }

        SETTINGS = new Settings(Global.getSettings().getMergedJSONForMod(SETTINGS_FILE, MOD_ID));
        LOGGER.info("AI Flag Tool settings: " + SETTINGS.toString());
        setLogLevel(SETTINGS.getLogLevel());
        FONT = LazyFont.loadFont("graphics/fonts/insignia15LTaa.fnt");
    }

    private static void setLogLevel(Level level) {
        AiFlagTool.LOGGER.setLevel(level);
        AiFlagToolCombatPlugin.LOGGER.setLevel(level);
        RetroactiveLogger.LOGGER.setLevel(level);
    }
}