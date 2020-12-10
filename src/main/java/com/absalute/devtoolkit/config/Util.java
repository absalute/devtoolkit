package com.absalute.devtoolkit.config;

import java.util.Locale;

public class Util {
	private Util() {}

	public static <T extends Enum<T>> String enumToLowerCamelCase(Enum<T> enumEntry) {
		String[] words = enumEntry.name().split("_");
		words[0] = words[0].toLowerCase(Locale.ROOT);
		for (int i = 1; i < words.length; i++)
			words[i] = words[i].charAt(0) + words[i].toLowerCase(Locale.ROOT).substring(1);
		return String.join("", words);
	}

	public static <T extends Enum<T>> String enumToLowerSnakeCase(Enum<T> enumEntry) {
		return enumEntry.name().toLowerCase(Locale.ROOT);
	}

	public static <T extends Enum<T>> String enumToFlatLowerCase(Enum<T> enumEntry) {
		return enumEntry.name().toLowerCase(Locale.ROOT).replaceAll("([^\\w\\d]|_)", "");
	}
}
