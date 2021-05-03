package com.greentea.devtoolkit.config.advancedtooltips;

import com.greentea.devtoolkit.config.Util;
import net.minecraft.client.gui.screen.Screen;

public enum Key {
	NONE,
	ALT,
	SHIFT,
	CONTROL;

	private final String name;

	Key() {
		this.name = Util.enumToLowerCamelCase(this);
	}

	public boolean check() {
		switch (this) {
			case NONE:
				return true;
			case ALT:
				return Screen.hasAltDown();
			case SHIFT:
				return Screen.hasShiftDown();
			case CONTROL:
				return Screen.hasControlDown();
			default:
				return false;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}