package com.greentea.devtoolkit.config;

import com.greentea.devtoolkit.DevToolkit;
import com.greentea.devtoolkit.config.advancedtooltips.AdvancedTooltipsConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = DevToolkit.MOD_ID)
public class ModConfig implements ConfigData {
	@ConfigEntry.Gui.CollapsibleObject
	public AdvancedTooltipsConfig advancedTooltips = new AdvancedTooltipsConfig();
}