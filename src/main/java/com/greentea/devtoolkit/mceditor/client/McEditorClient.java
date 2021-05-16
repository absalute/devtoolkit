package com.greentea.devtoolkit.mceditor.client;

import com.greentea.devtoolkit.mceditor.gui.McEditorScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;

/**
 * this class will be removed due to merging module with main class
 */
@Deprecated
public class McEditorClient implements ClientModInitializer {
    private static KeyBinding openEditorKey;

    private static void openEditorCallback(MinecraftClient client) {
        if (openEditorKey.wasPressed()) {
            McEditorScreen.toggle(client);
        }
    }

    @Override
    public void onInitializeClient() {
        openEditorKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.devtoolkit.mceditor.openEditor",
                GLFW.GLFW_KEY_Y,
                "category.devtoolkit.mceditor.general"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(McEditorClient::openEditorCallback);
    }
}