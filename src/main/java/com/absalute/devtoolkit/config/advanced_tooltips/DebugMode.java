package com.absalute.devtoolkit.config.advanced_tooltips;

import com.absalute.devtoolkit.config.Util;

public enum DebugMode {
	NONE,
	DEBUG,
	NOT_DEBUG;

	private final String name;

	DebugMode() {
		this.name = Util.enumToLowerCamelCase(this);
	}

	public boolean check(boolean isDebugEnabled) {
		switch (this) {
			case NONE:
				return true;
			case DEBUG:
				return isDebugEnabled;
			case NOT_DEBUG:
				return !isDebugEnabled;
			default:
				return false;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
