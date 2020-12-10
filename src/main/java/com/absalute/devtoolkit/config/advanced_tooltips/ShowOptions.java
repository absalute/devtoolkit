package com.absalute.devtoolkit.config.advanced_tooltips;

import com.absalute.devtoolkit.config.FormattingColor;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ShowOptions {
	boolean show;
	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	Key key;
	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	MatchMode match;
	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	ExtendedMode extended;
	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	DebugMode debug;
	FormattingColor color;

	public boolean isTooltipShown(TooltipContext context, boolean debugEnabled) {
		return show && key.check() && extended.check(context) && debug.check(debugEnabled);
	}

	public Optional<Text> getTooltip(TooltipContext context, boolean debugEnabled, Text onMatch) {
		return getTooltip(context, debugEnabled, true, onMatch);
	}

	public Optional<Text> getTooltip(TooltipContext context, boolean debugEnabled, boolean matched, Text onMatch) {
		return getTooltip(context, debugEnabled, matched, onMatch, null);
	}

	public Optional<Text> getTooltip(TooltipContext context, boolean debugEnabled, boolean matched, Text onMatch, Text onNotMatch) {
		if (isTooltipShown(context, debugEnabled)) return Optional.ofNullable(matched ? onMatch : onNotMatch);
		return Optional.empty();
	}

	public Formatting getColor() {
		return color.formatting;
	}

	public ShowOptions(Key key) {
		this(key, MatchMode.MATCH, ExtendedMode.NONE, DebugMode.NONE);
	}

	public ShowOptions(MatchMode match) {
		this(Key.NONE, match, ExtendedMode.NONE, DebugMode.NONE);
	}

	public ShowOptions(ExtendedMode extended) {
		this(Key.NONE, MatchMode.MATCH, extended, DebugMode.NONE);
	}

	public ShowOptions(DebugMode debug) {
		this(Key.NONE, MatchMode.MATCH, ExtendedMode.NONE, debug);
	}

	public ShowOptions(Key key, MatchMode match, ExtendedMode extended, DebugMode debug) {
		this(true, key, match, extended, debug, FormattingColor.DARK_GRAY);
	}

	public ShowOptions(boolean show, Key key, MatchMode match, ExtendedMode extended, DebugMode debug, FormattingColor color) {
		this.show = show;
		this.key = key;
		this.match = match;
		this.extended = extended;
		this.debug = debug;
		this.color = color;
	}
}
