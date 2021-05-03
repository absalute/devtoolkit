package com.greentea.devtoolkit.config.advancedtooltips;

import com.greentea.devtoolkit.config.Util;
import net.minecraft.client.item.TooltipContext;

public enum ExtendedMode {
	NONE,
	EXTENDED,
	NOT_EXTENDED;

	private final String name;

	ExtendedMode() {
		this.name = Util.enumToLowerCamelCase(this);
	}

	public boolean check(TooltipContext context) {
		boolean isAdvanced = context.isAdvanced();
		switch (this) {
			case NONE:
				return true;
			case EXTENDED:
				return isAdvanced;
			case NOT_EXTENDED:
				return !isAdvanced;
			default:
				return false;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}