package com.greentea.devtoolkit.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public final class DevToolkitConfig {
    private static final String CONFIG_PREFIX = "config.devtoolkit.";
    private static final String CATEGORY_PREFIX = CONFIG_PREFIX + "category.";
    private static final Text CONFIG_TITLE = new TranslatableText(CONFIG_PREFIX + "title");
    private static final Text MORE_TOOLTIPS_TITLE = new TranslatableText(CATEGORY_PREFIX + "moreTooltips.title");

    private final MoreTooltipsConfig moreTooltips = new MoreTooltipsConfig();

    public ConfigBuilder getConfigBuilder() {
        ConfigBuilder builder = ConfigBuilder.create().setTitle(CONFIG_TITLE).setSavingRunnable(this::saveConfig);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        moreTooltips.addConfigEntries(builder.getOrCreateCategory(MORE_TOOLTIPS_TITLE), entryBuilder);
        return builder;
    }

    public MoreTooltipsConfig getMoreTooltips() {
        return moreTooltips;
    }

    private void saveConfig() {
        DevToolkitConfigManager.saveConfig(this);
    }
}