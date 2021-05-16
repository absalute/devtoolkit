package com.greentea.devtoolkit.mixin;

import com.greentea.devtoolkit.DevToolkit;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    private static final Identifier CREEPER_ICON_TEXTURE = new Identifier(DevToolkit.MOD_ID, "textures/gui/title/creeper.png");
//    private static final TranslatableText OPEN_EDITOR_BUTTON_TEXT = new TranslatableText("devtoolkit.mceditor.gui.openEditor");
    @Mutable
    @Shadow
    @Final
    private boolean isMinceraft;

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgetsNormal(II)V")
    private void addButtons(int y, int spacingY, CallbackInfo info) {
//        int buttonsCount = this.buttons.size();
//        AbstractButtonWidget lastButton = this.buttons.get(buttonsCount - 1);
//        lastButton.setWidth(lastButton.getWidth() / 2 - 2);
//        ButtonWidget openEditorButton = new ButtonWidget(this.width / 2 + 2, y + spacingY,
//                98, 20, OPEN_EDITOR_BUTTON_TEXT, (buttonWidget) -> McEditorScreen.toggle());
        ButtonWidget switchMinceraftButton = new TexturedButtonWidget(this.width / 2 + 128, this.height / 4 + 132,
                20, 20, 0, 0, 20, CREEPER_ICON_TEXTURE, 32, 64,
                (buttonWidget) -> {
                    ((TitleScreenAccessor) this).setMinceraft(this.isMinceraft);
                    this.isMinceraft = !this.isMinceraft;
                });
//        this.addButton(openEditorButton);
        this.addButton(switchMinceraftButton);
    }
}