package com.greentea.devtoolkit.config;

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
	public final Formatting formatting;

	FormattingColor() {
		this.name = Util.enumToFlatLowerCase(this);
		this.formatting = Formatting.byName(this.name);
	}

	@Override
	public String toString() {
		return name;
	}
}