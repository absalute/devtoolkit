package com.absalute.devtoolkit.config;

import com.absalute.devtoolkit.DevToolkit;
import com.absalute.devtoolkit.config.advanced_tooltips.AdvancedTooltipsConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = DevToolkit.MOD_ID)
public class ModConfig implements ConfigData {
	@ConfigEntry.Gui.CollapsibleObject
	public AdvancedTooltipsConfig advancedTooltips = new AdvancedTooltipsConfig();
}
