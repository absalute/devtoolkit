package com.greentea.devtoolkit.config.advancedtooltips;

import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

public class AdvancedTooltipsConfig {
	boolean showTooltips = true;
	boolean enableDebug = false;
	@ConfigEntry.Gui.Tooltip
	boolean addNewLineSeparators = false;

	@ConfigEntry.Gui.CollapsibleObject
	RarityConfig rarity = new RarityConfig();

	@ConfigEntry.Gui.CollapsibleObject
	GeneralConfig general = new GeneralConfig();

	@ConfigEntry.Gui.CollapsibleObject
	FoodConfig food = new FoodConfig();

	@ConfigEntry.Gui.CollapsibleObject
	NBTConfig nbt = new NBTConfig();

	public boolean isTooltipsShown() {
		return showTooltips;
	}

	public boolean isDebugEnabled() {
		return enableDebug;
	}

	public boolean isAddSeparators() {
		return addNewLineSeparators;
	}

	public GeneralConfig getGeneral() {
		return general;
	}

	public RarityConfig getRarity() {
		return rarity;
	}

	public FoodConfig getFood() {
		return food;
	}

	public NBTConfig getNbt() {
		return nbt;
	}

	public static class RarityConfig {
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions rarity = new ShowOptions(Key.CONTROL, MatchMode.NONE, ExtendedMode.EXTENDED, DebugMode.NONE);
		boolean showRarityColor = true;
		public ShowOptions getRarity() {
			return rarity;
		}

		public boolean isRarityColorShown() {
			return showRarityColor;
		}

	}

	public static class GeneralConfig {
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions translationKey = new ShowOptions(Key.ALT, MatchMode.NONE, ExtendedMode.EXTENDED, DebugMode.NONE);
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions stackable = new ShowOptions(MatchMode.MATCH);
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions maxCount = new ShowOptions(Key.ALT, MatchMode.MATCH, ExtendedMode.EXTENDED, DebugMode.NONE);
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions fireproof = new ShowOptions(Key.ALT, MatchMode.MATCH, ExtendedMode.EXTENDED, DebugMode.NONE);
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions damageable = new ShowOptions(Key.NONE, MatchMode.MATCH, ExtendedMode.EXTENDED, DebugMode.NONE);
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions repairCost = new ShowOptions(Key.ALT);
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions enchantable = new ShowOptions(Key.NONE, MatchMode.MATCH, ExtendedMode.EXTENDED, DebugMode.NONE);
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions enchantability = new ShowOptions(Key.ALT);

		public ShowOptions getMaxCount() {
			return maxCount;
		}

		public ShowOptions getTranslationKey() {
			return translationKey;
		}

		public ShowOptions getFireproof() {
			return fireproof;
		}

		public ShowOptions getStackable() {
			return stackable;
		}

		public ShowOptions getDamageable() {
			return damageable;
		}

		public ShowOptions getRepairCost() {
			return repairCost;
		}

		public ShowOptions getEnchantable() {
			return enchantable;
		}

		public ShowOptions getEnchantability() {
			return enchantability;
		}
	}

	public static class FoodConfig {
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions hunger = new ShowOptions(Key.ALT);
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions saturationModifier = new ShowOptions(Key.ALT);
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions meat = new ShowOptions(MatchMode.MATCH);
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions alwaysEdible = new ShowOptions(MatchMode.MATCH);
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions snack = new ShowOptions(MatchMode.MATCH);

		public ShowOptions getHunger() {
			return hunger;
		}

		public ShowOptions getSaturationModifier() {
			return saturationModifier;
		}

		public ShowOptions getMeat() {
			return meat;
		}

		public ShowOptions getAlwaysEdible() {
			return alwaysEdible;
		}

		public ShowOptions getSnack() {
			return snack;
		}
	}

	public static class NBTConfig {
		@ConfigEntry.Gui.CollapsibleObject
		ShowOptions tag = new ShowOptions(Key.CONTROL, MatchMode.MATCH, ExtendedMode.EXTENDED, DebugMode.NONE);
		@ConfigEntry.BoundedDiscrete(min = 10, max = 512) // out of memory protection
		int maxLineSize = 50;

		public ShowOptions getTag() {
			return tag;
		}

		public int getMaxLineSize() {
			return maxLineSize;
		}
	}
}