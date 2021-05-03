package com.greentea.devtoolkit;

import com.greentea.devtoolkit.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class DevToolkit implements ClientModInitializer {
	public static final String MOD_ID = "devtoolkit";

	@Override
	public void onInitializeClient() {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
		AdvancedTooltips.init(config.advancedTooltips);
	}
}