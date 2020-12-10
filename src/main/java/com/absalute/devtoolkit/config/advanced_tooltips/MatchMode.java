package com.absalute.devtoolkit.config.advanced_tooltips;

import com.absalute.devtoolkit.config.Util;

public enum MatchMode {
	NONE,
	MATCH,
	NOT_MATCH;

	private final String name;

	MatchMode() {
		this.name = Util.enumToLowerCamelCase(this);
	}

	public boolean check(boolean matched) {
		switch (this) {
			case NONE:
				return true;
			case MATCH:
				return matched;
			case NOT_MATCH:
				return !matched;
			default:
				return false;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
