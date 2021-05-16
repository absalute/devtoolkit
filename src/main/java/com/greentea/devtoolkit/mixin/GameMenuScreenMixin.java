package com.greentea.devtoolkit.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
//    private static final TranslatableText OPEN_EDITOR_BUTTON_TEXT = new TranslatableText("devtoolkit.mceditor.gui.openEditor");

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgets()V")
    private void addOpenEditorButton(CallbackInfo info) {
//        int lastButtonY = this.buttons.stream().mapToInt(button -> button.y).max().orElseGet(() -> this.height);
//        this.addButton(new ButtonWidget(this.width / 2 - 102, lastButtonY + 24, 204, 20,
//                OPEN_EDITOR_BUTTON_TEXT, (buttonWidget) -> McEditorScreen.toggle()));
    }
}