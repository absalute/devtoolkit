package com.greentea.devtoolkit;

import com.greentea.devtoolkit.config.DevToolkitConfig;
import com.greentea.devtoolkit.config.DevToolkitConfigManager;
import com.greentea.devtoolkit.moretooltips.MoreTooltips;
import net.fabricmc.api.ClientModInitializer;

public class DevToolkit implements ClientModInitializer {
	public static final String MOD_ID = "devtoolkit";

	@Override
	public void onInitializeClient() {
        DevToolkitConfig config = DevToolkitConfigManager.loadConfig();
        MoreTooltips.init(config.getMoreTooltips());
	}
}