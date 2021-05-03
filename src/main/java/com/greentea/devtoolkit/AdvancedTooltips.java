package com.greentea.devtoolkit;

import com.greentea.devtoolkit.config.advancedtooltips.AdvancedTooltipsConfig;
import com.greentea.devtoolkit.config.advancedtooltips.ShowOptions;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.*;
import java.util.stream.Collectors;

public class AdvancedTooltips {
	private static final String tooltipPrefix = "tooltip.devtoolkit.advanced_tooltips.";

	public static void init(AdvancedTooltipsConfig config) {
		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, list) -> appendTooltip(itemStack, tooltipContext, list, config));
	}

	private static void appendTooltip(ItemStack stack, TooltipContext context, List<Text> lines, AdvancedTooltipsConfig config) {
		if (config.isTooltipsShown()) {
			Options options = new Options(stack, context, config.isDebugEnabled());
			lines.add(LiteralText.EMPTY);
			appendRarityTooltip(options, lines, config.getRarity());
			if (config.isAddSeparators()) lines.add(LiteralText.EMPTY);
			appendGeneralTooltip(options, lines, config.getGeneral());
			if (config.isAddSeparators()) lines.add(LiteralText.EMPTY);
			appendFoodTooltip(options, lines, config.getFood());
			if (config.isAddSeparators()) lines.add(LiteralText.EMPTY);
			appendNBTTooltip(options, lines, config.getNbt());
		}
	}

	private static void appendRarityTooltip(Options options, List<Text> lines, AdvancedTooltipsConfig.RarityConfig settings) {
		ShowOptions rarity = settings.getRarity();
		Formatting rarityColor = rarity.getColor();
		Rarity stackRarity = options.stack.getRarity();
		Rarity itemRarity = options.stack.getItem().getRarity(ItemStack.EMPTY);
		String stackRarityName = stackRarity.toString().toLowerCase(Locale.ROOT);
		String itemRarityName = itemRarity.toString().toLowerCase(Locale.ROOT);

		rarity.getTooltip(options.context, options.debugEnabled, settings.isRarityColorShown(),
				new TranslatableText(tooltipPrefix + "rarity").formatted(rarityColor)
						.append(new LiteralText(stackRarityName).formatted(stackRarity.formatting))
						.append(new LiteralText(" (").formatted(rarityColor))
						.append(new LiteralText(itemRarityName).formatted(itemRarity.formatting))
						.append(new LiteralText(")").formatted(rarityColor)),
				new TranslatableText(tooltipPrefix + "rarity").formatted(rarityColor)
						.append(new LiteralText(stackRarityName + " (" + itemRarityName + ")").formatted(rarityColor)))
				.ifPresent(lines::add);
	}

	private static void appendGeneralTooltip(Options options, List<Text> lines, AdvancedTooltipsConfig.GeneralConfig settings) {
		ShowOptions translationKey = settings.getTranslationKey();
		ShowOptions stackable = settings.getStackable();
		ShowOptions maxCount = settings.getMaxCount();
		ShowOptions fireproof = settings.getFireproof();
		ShowOptions damageable = settings.getDamageable();
		ShowOptions repairCost = settings.getRepairCost();
		ShowOptions enchantable = settings.getEnchantable();
		ShowOptions enchantability = settings.getEnchantability();

		Formatting fireproofColor = fireproof.getColor();
		Formatting stackableColor = stackable.getColor();
		Formatting damageableColor = damageable.getColor();
		Formatting enchantableColor = enchantable.getColor();

		Arrays.asList(
				translationKey.getTooltip(options.context, options.debugEnabled,
						new LiteralText(options.stack.getTranslationKey()).formatted(translationKey.getColor())),
				stackable.getTooltip(options.context, options.debugEnabled, options.stack.isStackable(),
						new TranslatableText(tooltipPrefix + "stackable").formatted(stackableColor),
						new TranslatableText(tooltipPrefix + "not_stackable").formatted(stackableColor)),
				maxCount.getTooltip(options.context, options.debugEnabled, options.stack.isStackable(),
						new TranslatableText(tooltipPrefix + "max_count", options.stack.getMaxCount()).formatted(maxCount.getColor())),
				fireproof.getTooltip(options.context, options.debugEnabled, options.stack.getItem().isFireproof(),
						new TranslatableText(tooltipPrefix + "fireproof").formatted(fireproofColor),
						new TranslatableText(tooltipPrefix + "not_fireproof").formatted(fireproofColor)),
				damageable.getTooltip(options.context, options.debugEnabled, options.stack.isDamageable(),
						new TranslatableText(tooltipPrefix + "damageable").formatted(damageableColor),
						new TranslatableText(tooltipPrefix + "not_damageable").formatted(damageableColor)),
				repairCost.getTooltip(options.context, options.debugEnabled,
						new TranslatableText(tooltipPrefix + "repair_cost", options.stack.getRepairCost()).formatted(repairCost.getColor())),
				enchantable.getTooltip(options.context, options.debugEnabled, options.stack.isEnchantable(),
						new TranslatableText(tooltipPrefix + "enchantable").formatted(enchantableColor),
						new TranslatableText(tooltipPrefix + "not_enchantable").formatted(enchantableColor)),
				enchantability.getTooltip(options.context, options.debugEnabled,
						new TranslatableText(tooltipPrefix + "enchantability", options.stack.getItem().getEnchantability()).formatted(enchantability.getColor()))
		).forEach((line) -> line.ifPresent(lines::add));
	}

	private static void appendFoodTooltip(Options options, List<Text> lines, AdvancedTooltipsConfig.FoodConfig settings) {
		if (options.stack.getItem().isFood()) {
			FoodComponent foodComponent = Objects.requireNonNull(options.stack.getItem().getFoodComponent());

			ShowOptions hunger = settings.getHunger();
			ShowOptions saturationModifier = settings.getSaturationModifier();
			ShowOptions meat = settings.getMeat();
			ShowOptions alwaysEdible = settings.getAlwaysEdible();
			ShowOptions snack = settings.getSnack();

			Formatting meatColor = meat.getColor();
			Formatting alwaysEdibleColor = alwaysEdible.getColor();
			Formatting snackColor = snack.getColor();

			Arrays.asList(
			hunger.getTooltip(options.context, options.debugEnabled,
					new TranslatableText(tooltipPrefix + "hunger", foodComponent.getHunger()).formatted(hunger.getColor())),
			saturationModifier.getTooltip(options.context, options.debugEnabled,
					new TranslatableText(tooltipPrefix + "saturation_modifier", foodComponent.getSaturationModifier()).formatted(saturationModifier.getColor())),
			meat.getTooltip(options.context, options.debugEnabled, foodComponent.isMeat(),
					new TranslatableText(tooltipPrefix + "meat").formatted(meatColor),
					new TranslatableText(tooltipPrefix + "not_meat").formatted(meatColor)),
			alwaysEdible.getTooltip(options.context, options.debugEnabled, foodComponent.isAlwaysEdible(),
					new TranslatableText(tooltipPrefix + "always_edible").formatted(alwaysEdibleColor),
					new TranslatableText(tooltipPrefix + "not_always_edible").formatted(alwaysEdibleColor)),
			snack.getTooltip(options.context, options.debugEnabled, foodComponent.isSnack(),
					new TranslatableText(tooltipPrefix + "snack").formatted(snackColor),
					new TranslatableText(tooltipPrefix + "not_snack").formatted(snackColor))
			).forEach(line -> line.ifPresent(lines::add));
		}
	}

	private static void appendNBTTooltip(Options options, List<Text> lines, AdvancedTooltipsConfig.NBTConfig settings) {
		ShowOptions showTag = settings.getTag();
		if (showTag.isTooltipShown(options.context, options.debugEnabled)) {
			Formatting tagColor = showTag.getColor();
			if (options.stack.hasTag()) {
				lines.add(new TranslatableText(tooltipPrefix + "nbt").formatted(tagColor));
				CompoundTag tag = Objects.requireNonNull(options.stack.getTag());
				// TODO: syntax highlight ?and json tree formatting / generating?
				for (String string : tag.toString().split("\n"))
					lines.addAll(splitByLineLength(string, settings.getMaxLineSize()).stream()
							.map(line -> new LiteralText(line).formatted(tagColor)).collect(Collectors.toList()));
			} else lines.add(new TranslatableText(tooltipPrefix + "empty_nbt").formatted(tagColor));
		}
	}

	private static List<String> splitByLineLength(String string, int lineLength) {
		List<String> lines = new ArrayList<>();
		int end;
		while (string.length() > 0) {
			end = Math.min(string.length(), lineLength);
			lines.add(string.substring(0, end));
			string = string.substring(end);
		}
		return lines;
	}

	private static class Options {
		final ItemStack stack;
		final TooltipContext context;
		final boolean debugEnabled;

		public Options(ItemStack stack, TooltipContext context, boolean debugEnabled) {
			this.stack = stack;
			this.context = context;
			this.debugEnabled = debugEnabled;
		}
	}
}