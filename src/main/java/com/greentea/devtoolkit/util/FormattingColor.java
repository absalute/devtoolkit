package com.greentea.devtoolkit.util;

import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@SuppressWarnings("unused")
public enum FormattingColor {
	BLACK,
	DARK_BLUE,
	DARK_GREEN,
	DARK_AQUA,
	DARK_RED,
	DARK_PURPLE,
	GOLD,
	GRAY,
	DARK_GRAY,
	BLUE,
	GREEN,
	AQUA,
	RED,
	LIGHT_PURPLE,
	YELLOW,
	WHITE;

	private final String name;
	private final Formatting formatting;
	private final Text text;

	FormattingColor() {
	    name = name();
		formatting = Formatting.byName(name);
		text = new LiteralText(name).formatted(formatting);
	}

	@Override
	public String toString() {
		return name;
	}

    public Formatting getFormatting() {
        return formatting;
    }

    public Text getText() {
        return text;
    }
}