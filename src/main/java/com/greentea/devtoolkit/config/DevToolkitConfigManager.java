package com.greentea.devtoolkit.config;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greentea.devtoolkit.DevToolkit;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public final class DevToolkitConfigManager {
    private static File configFile;
    private static DevToolkitConfig configData;
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public static DevToolkitConfig loadConfig() {
        if (configData == null) {
            configData = new DevToolkitConfig();
            load();
        }
        return configData;
    }

    public static void saveConfig(DevToolkitConfig newConfig) {
        configData = newConfig;
        save();
    }

    private static void prepareConfigFile() {
        if (configFile == null)
            configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), DevToolkit.MOD_ID + ".json");
    }

    private static void load() {
        prepareConfigFile();
        try {
            if (!configFile.exists()) save();
            if (configFile.exists()) {
                BufferedReader configReader = new BufferedReader(new FileReader(configFile));
                DevToolkitConfig newConfig = GSON.fromJson(configReader, DevToolkitConfig.class);
                if (newConfig != null) configData = newConfig;
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Config file could not be found", e);
        }
    }

    private static void save() {
        prepareConfigFile();
        String serializedConfig = GSON.toJson(configData);
        try (FileWriter configWriter = new FileWriter(configFile)) {
            configWriter.write(serializedConfig);
        } catch (IOException e) {
            LOGGER.error("Config file could not be saved", e);
        }
    }
}