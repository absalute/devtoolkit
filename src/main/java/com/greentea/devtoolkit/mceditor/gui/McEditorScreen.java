package com.greentea.devtoolkit.mceditor.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class McEditorScreen extends Screen {
    private static final Logger LOGGER = LogManager.getLogger();
//    private static final Identifier BACKGROUND_TEXTURE = new Identifier(DevToolkit.MOD_ID, "textures/gui/editor/background.png");
    private static McEditorScreen instance;
    protected MinecraftClient client;

    private TextFieldWidget textField;

    private McEditorScreen() {
        super(new TranslatableText("devtoolkit.mceditor.gui.title"));
    }

    public static Screen getInstance() {
        if (instance == null) instance = new McEditorScreen();
        return instance;
    }

    public static void toggle(MinecraftClient client) {
        client.openScreen(client.currentScreen instanceof McEditorScreen ?
                new GameMenuScreen(true) : McEditorScreen.getInstance());
    }

    public static void toggle() {
        toggle(MinecraftClient.getInstance());
    }

    @Override
    public void init(MinecraftClient client, int width, int height) {
        if (client == null) client = MinecraftClient.getInstance();
        super.init(client, width, height);
        int j = height / 2;
        int k = 24;
        this.addButton(new ButtonWidget(width / 2 - 50, j - k - 10, 100, 20,
                new TranslatableText("devtoolkit.editor.gui.test"), (buttonWidget) -> toggle(this.client)));

        this.client.keyboard.setRepeatEvents(true);
        this.textField = new TextFieldWidget(this.textRenderer, 4, this.height - 12,
                this.width - 4, 12, new TranslatableText("chat.editBox"));
        this.textField.setDrawsBackground(false);
        this.textField.setMaxLength(256);
        this.textField.setChangedListener(this::onTextFieldUpdate);
        this.children.add(this.textField);
    }

    @Override
    public void tick() {
        this.textField.tick();
    }

    private void onTextFieldUpdate(String fieldText) {
        LOGGER.info(() -> "text field updated with: " + fieldText);
    }

//    @Override
//    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
//        if (super.keyPressed(keyCode, scanCode, modifiers)) return true;
//        else if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
//            this.client.openScreen(null);
//            return true;
//        } else if (keyCode != GLFW.GLFW_KEY_ENTER && keyCode != GLFW.GLFW_KEY_KP_ENTER) {}
//        return false;
//    }

// @Override
// protected void insertText(String text, boolean override) {
//     LOGGER.info(() -> String.format("text inserted%s: %s", override ? " and overridden" : "", text));
// }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        this.setFocused(this.textField);
        this.textField.setTextFieldFocused(true);
        fill(matrices, 2, this.height - 14, this.width - 2, this.height - 2, this.client.options.getTextBackgroundColor(Integer.MIN_VALUE));
        this.textField.render(matrices, mouseX, mouseY, delta);
        Style style = this.client.inGameHud.getChatHud().getText(mouseX, mouseY);
        if (style != null && style.getHoverEvent() != null) {
            this.renderTextHoverEffect(matrices, style, mouseX, mouseY);
        }
    }
}