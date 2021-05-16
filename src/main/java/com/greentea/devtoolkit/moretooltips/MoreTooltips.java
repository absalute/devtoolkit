package com.greentea.devtoolkit.moretooltips;

import com.greentea.devtoolkit.config.MoreTooltipsConfig;
import com.greentea.devtoolkit.util.Util;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.greentea.devtoolkit.config.MoreTooltipsConfig.*;

public final class MoreTooltips {
    private static final String TOOLTIP_PREFIX = "tooltip.devtoolkit.moreTooltips.";

    private static final String TRANSLATION_KEY = TOOLTIP_PREFIX + "translationKey";
    private static final String RARITY = TOOLTIP_PREFIX + "rarity";
    private static final String STACKABLE = TOOLTIP_PREFIX + "stackable";
    private static final String NOT_STACKABLE = TOOLTIP_PREFIX + "notStackable";
    private static final String MAX_COUNT = TOOLTIP_PREFIX + "maxCount";
    private static final String FIREPROOF = TOOLTIP_PREFIX + "fireproof";
    private static final String NOT_FIREPROOF = TOOLTIP_PREFIX + "notFireproof";
    private static final String DAMAGEABLE = TOOLTIP_PREFIX + "damageable";
    private static final String NOT_DAMAGEABLE = TOOLTIP_PREFIX + "notDamageable";
    private static final String REPAIR_COST = TOOLTIP_PREFIX + "repairCost";
    private static final String ENCHANTABLE = TOOLTIP_PREFIX + "enchantable";
    private static final String NOT_ENCHANTABLE = TOOLTIP_PREFIX + "notEnchantable";
    private static final String ENCHANTABILITY = TOOLTIP_PREFIX + "enchantability";
    private static final String HUNGER = TOOLTIP_PREFIX + "hunger";
    private static final String SATURATION_MODIFIER = TOOLTIP_PREFIX + "saturationModifier";
    private static final String MEAT = TOOLTIP_PREFIX + "meat";
    private static final String NOT_MEAT = TOOLTIP_PREFIX + "notMeat";
    private static final String ALWAYS_EDIBLE = TOOLTIP_PREFIX + "alwaysEdible";
    private static final String NOT_ALWAYS_EDIBLE = TOOLTIP_PREFIX + "notAlwaysEdible";
    private static final String SNACK = TOOLTIP_PREFIX + "snack";
    private static final String NOT_SNACK = TOOLTIP_PREFIX + "notSnack";

    private static final TranslatableText NO_NBT_TEXT = new TranslatableText(TOOLTIP_PREFIX + "emptyNbt");

    private static MoreTooltipsConfig config;
    private static boolean initialized = false;

    private MoreTooltips() {
    }

    public static void init(MoreTooltipsConfig configData) {
        if (initialized) throw new RuntimeException("Cannot init multiple times");
        config = configData;
        ItemTooltipCallback.EVENT.register(MoreTooltips::appendTooltips);
        initialized = true;
    }

    private static void appendTooltips(ItemStack stack, TooltipContext context, List<Text> tooltip) {
        if (config.isTooltipsShown()) {
            List<Text> newTooltip = new ArrayList<>(15);
            insertGeneralTooltip(config.getGeneral(), stack, context, newTooltip);
            insertFoodTooltip(config.getFood(), stack, context, newTooltip);
            insertNbtTooltip(config.getNbt(), stack, context, newTooltip);
            insertTooltips(tooltip, newTooltip);
        }
    }

    private static void insertGeneralTooltip(General general, ItemStack stack, TooltipContext context, List<Text> tooltip) {
        General.Rarity rarity = general.getRarity();
        Item item = stack.getItem();
        boolean isDamageable = stack.isDamageable();
        boolean isEnchantable = stack.isEnchantable();
        addTooltip(tooltip, context, general.getTranslationKey(), TRANSLATION_KEY, stack.getTranslationKey());
        addTooltip(tooltip, context, rarity, () -> getRarityText(rarity, stack));
        addTooltip(tooltip, context, general.getStackable(), stack.isStackable() ? STACKABLE : NOT_STACKABLE);
        addTooltip(tooltip, context, general.getMaxCount(), MAX_COUNT, stack.getMaxCount());
        addTooltip(tooltip, context, general.getFireproof(), item.isFireproof() ? FIREPROOF : NOT_FIREPROOF);
        addTooltip(tooltip, context, general.getDamageable(), isDamageable ? DAMAGEABLE : NOT_DAMAGEABLE);
        if (isDamageable) addTooltip(tooltip, context, general.getRepairCost(), REPAIR_COST, stack.getRepairCost());
        addTooltip(tooltip, context, general.getEnchantable(), isEnchantable ? ENCHANTABLE : NOT_ENCHANTABLE);
        if (isEnchantable)
            addTooltip(tooltip, context, general.getEnchantability(), ENCHANTABILITY, item.getEnchantability());
    }

    private static void insertFoodTooltip(Food food, ItemStack stack, TooltipContext context, List<Text> tooltip) {
        Item item = stack.getItem();
        FoodComponent foodComponent = item.getFoodComponent();
        if (foodComponent != null) {
            addTooltip(tooltip, context, food.getHunger(), HUNGER, foodComponent.getHunger());
            addTooltip(tooltip, context, food.getSaturationModifier(), SATURATION_MODIFIER, foodComponent.getSaturationModifier());
            addTooltip(tooltip, context, food.getMeat(), foodComponent.isMeat() ? MEAT : NOT_MEAT);
            addTooltip(tooltip, context, food.getAlwaysEdible(), foodComponent.isAlwaysEdible() ? ALWAYS_EDIBLE : NOT_ALWAYS_EDIBLE);
            addTooltip(tooltip, context, food.getSnack(), foodComponent.isSnack() ? SNACK : NOT_SNACK);
        }
    }

    private static void insertNbtTooltip(NBT nbt, ItemStack stack, TooltipContext context, List<Text> tooltip) {
        CompoundTag tag = stack.getTag();
        if (tag == null) addTooltip(tooltip, context, nbt, NO_NBT_TEXT.formatted(nbt.getColor()));
        else if (isOptionShown(nbt, context))
            insertTooltips(tooltip, Util.compoundAsText(tag, nbt.getMaxLineLength(), nbt.getColor()), nbt.getPosition());
    }

    private static Text getRarityText(General.Rarity rarity, ItemStack stack) {
        Rarity stackRarity = stack.getRarity();
        Rarity itemRarity = stack.getItem().getRarity(ItemStack.EMPTY);
        MutableText stackRarityText = new LiteralText(stackRarity.toString().toLowerCase());
        MutableText itemRarityText = new LiteralText(itemRarity.toString().toLowerCase());
        if (rarity.isRarityColorShown()) {
            stackRarityText = stackRarityText.formatted(stackRarity.formatting);
            itemRarityText = itemRarityText.formatted(itemRarity.formatting);
        }
        return new TranslatableText(RARITY, stackRarityText, itemRarityText).formatted(rarity.getColor());
    }

    private static void addTooltip(List<Text> tooltip, int position, Text text) {
        int size = tooltip.size();
        if (size < position) for (int i = 0, end = position - size; i <= end; i++) tooltip.add(null);
        tooltip.add(position, text);
    }

    private static void addTooltip(List<Text> tooltip, TooltipOption option, Text text) {
        addTooltip(tooltip, option.getPosition(), text);
    }

    private static void addTooltip(List<Text> tooltip, TooltipContext context, TooltipOption option, Text text) {
        if (isOptionShown(option, context)) addTooltip(tooltip, option, text);
    }

    private static void addTooltip(List<Text> tooltip, TooltipContext context, TooltipOption option, Supplier<Text> textSupplier) {
        if (isOptionShown(option, context)) addTooltip(tooltip, option, textSupplier.get());
    }

    private static void addTooltip(List<Text> tooltip, TooltipContext context, TooltipOption option, String translationKey, Object... format) {
        addTooltip(tooltip, context, option, () -> new TranslatableText(translationKey, format).formatted(option.getColor()));
    }

    private static void insertTooltips(List<Text> tooltip, List<Text> newTooltip, int startIndex) {
        for (int i = newTooltip.size() - 1; i >= 0; i--) {
            Text line = newTooltip.get(i);
            if (line != null) addTooltip(tooltip, startIndex, line);
        }
    }

    private static void insertTooltips(List<Text> tooltip, List<Text> newTooltip) {
        for (Text tooltipLine : newTooltip) if (tooltipLine != null) tooltip.add(tooltipLine);
    }

    private static boolean isOptionShown(TooltipOption option, TooltipContext context) {
        if (option.isShown()) {
            if (option.isAltRequired() && !Screen.hasAltDown()) return false;
            if (option.isControlRequired() && !Screen.hasControlDown()) return false;
            if (option.isShiftRequired() && !Screen.hasShiftDown()) return false;
            return !option.isAdvancedRequired() || context.isAdvanced();
        }
        return false;
    }
}