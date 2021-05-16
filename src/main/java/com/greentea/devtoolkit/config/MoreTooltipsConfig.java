package com.greentea.devtoolkit.config;

import com.greentea.devtoolkit.util.FormattingColor;
import com.greentea.devtoolkit.util.Util;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder.TopCellElementBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class MoreTooltipsConfig {
    private static final String CONFIG_PREFIX = "config.devtoolkit.moreTooltips.";

    private static final String TOOLTIP_PREFIX = CONFIG_PREFIX + "tooltip.";
    private static final String GENERAL_PREFIX = CONFIG_PREFIX + "general.";
    private static final String FOOD_PREFIX = CONFIG_PREFIX + "food.";
    private static final String NBT_PREFIX = CONFIG_PREFIX + "nbt.";

    private static final Text GENERAL_SUBCATEGORY = new TranslatableText(CONFIG_PREFIX + "subcategory.general.title");
    private static final Text FOOD_SUBCATEGORY = new TranslatableText(CONFIG_PREFIX + "subcategory.food.title");
    private static final Text NBT_SUBCATEGORY = new TranslatableText(CONFIG_PREFIX + "subcategory.nbt.title");

    private static final Text GENERAL_TOOLTIP = new TranslatableText(GENERAL_PREFIX + "tooltip");
    private static final Text FOOD_TOOLTIP = new TranslatableText(FOOD_PREFIX + "tooltip");
    private static final Text NBT_TOOLTIP = new TranslatableText(NBT_PREFIX + "tooltip");

    private static final Text SHOW_TOOLTIPS_FIELD = new TranslatableText(CONFIG_PREFIX + "showTooltips");
    private static final Text TOOLTIP_SHOWN_FIELD = new TranslatableText(TOOLTIP_PREFIX + "show");
    private static final Text TOOLTIP_COLOR_FIELD = new TranslatableText(TOOLTIP_PREFIX + "color");
    private static final Text TOOLTIP_ALT_FIELD = new TranslatableText(TOOLTIP_PREFIX + "altRequired");
    private static final Text TOOLTIP_CONTROL_FIELD = new TranslatableText(TOOLTIP_PREFIX + "controlRequired");
    private static final Text TOOLTIP_SHIFT_FIELD = new TranslatableText(TOOLTIP_PREFIX + "shiftRequired");
    private static final Text TOOLTIP_POSITION_FIELD = new TranslatableText(TOOLTIP_PREFIX + "position");
    private static final Text TOOLTIP_POSITION_TOOLTIP = new TranslatableText(TOOLTIP_PREFIX + "position.tooltip");
    private static final Text TOOLTIP_POSITION_WARNING = new TranslatableText(TOOLTIP_PREFIX + "position.warning").formatted(Formatting.RED);
    private static final Text TOOLTIP_ADVANCED_FIELD = new TranslatableText(TOOLTIP_PREFIX + "advanced");
    private static final Text TOOLTIP_ADVANCED_TOOLTIP = new TranslatableText(TOOLTIP_PREFIX + "advanced.tooltip");

    private static final List<Integer> TOOLTIP_POSITIONS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14);

    private final General general = new General();
    private final Food food = new Food();
    private final NBT nbt = new NBT();
    private boolean showTooltips = true;

    public void addConfigEntries(ConfigCategory category, ConfigEntryBuilder entryBuilder) {
        category.addEntry(entryBuilder.startBooleanToggle(SHOW_TOOLTIPS_FIELD, showTooltips)
                .setDefaultValue(true)
                .setSaveConsumer(value -> showTooltips = value)
                .build());
        category.addEntry(general.createConfigEntries(entryBuilder.startSubCategory(GENERAL_SUBCATEGORY)
                .setTooltip(GENERAL_TOOLTIP), entryBuilder)
                .build());
        category.addEntry(food.createConfigEntries(entryBuilder.startSubCategory(FOOD_SUBCATEGORY)
                .setTooltip(FOOD_TOOLTIP), entryBuilder)
                .build());
        category.addEntry(nbt.createConfigEntries(entryBuilder.startSubCategory(NBT_SUBCATEGORY)
                .setTooltip(NBT_TOOLTIP), entryBuilder)
                .build());
    }

    public boolean isTooltipsShown() {
        return showTooltips;
    }

    public General getGeneral() {
        return general;
    }

    public Food getFood() {
        return food;
    }

    public NBT getNbt() {
        return nbt;
    }

    public static final class General extends ConfigEntry {
        private static final Text TRANSLATION_KEY_OPTION = new TranslatableText(GENERAL_PREFIX + "translationKey");
        private static final Text RARITY_OPTION = new TranslatableText(GENERAL_PREFIX + "rarity");
        private static final Text STACKABLE_OPTION = new TranslatableText(GENERAL_PREFIX + "stackable");
        private static final Text MAX_COUNT_OPTION = new TranslatableText(GENERAL_PREFIX + "maxCount");
        private static final Text FIREPROOF_OPTION = new TranslatableText(GENERAL_PREFIX + "fireproof");
        private static final Text DAMAGEABLE_OPTION = new TranslatableText(GENERAL_PREFIX + "damageable");
        private static final Text REPAIR_COST_OPTION = new TranslatableText(GENERAL_PREFIX + "repairCost");
        private static final Text ENCHANTABLE_OPTION = new TranslatableText(GENERAL_PREFIX + "enchantable");
        private static final Text ENCHANTABILITY_OPTION = new TranslatableText(GENERAL_PREFIX + "enchantability");

        private final TooltipOption translationKey = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 0;
            }
        };
        private final Rarity rarity = new Rarity();
        private final TooltipOption stackable = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 2;
            }
        };
        private final TooltipOption maxCount = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 3;
            }
        };
        private final TooltipOption fireproof = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 4;
            }
        };
        private final TooltipOption damageable = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 5;
            }
        };
        private final TooltipOption repairCost = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 6;
            }
        };
        private final TooltipOption enchantable = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 7;
            }
        };
        private final TooltipOption enchantability = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 8;
            }
        };

        public TooltipOption getTranslationKey() {
            return translationKey;
        }

        public Rarity getRarity() {
            return rarity;
        }

        public TooltipOption getStackable() {
            return stackable;
        }

        public TooltipOption getMaxCount() {
            return maxCount;
        }

        public TooltipOption getFireproof() {
            return fireproof;
        }

        public TooltipOption getDamageable() {
            return damageable;
        }

        public TooltipOption getRepairCost() {
            return repairCost;
        }

        public TooltipOption getEnchantable() {
            return enchantable;
        }

        public TooltipOption getEnchantability() {
            return enchantability;
        }

        @Override
        protected void addConfigEntries(SubCategoryBuilder general, ConfigEntryBuilder entryBuilder) {
            general.add(translationKey.createConfigEntries(entryBuilder.startSubCategory(TRANSLATION_KEY_OPTION), entryBuilder).build());
            general.add(rarity.createConfigEntries(entryBuilder.startSubCategory(RARITY_OPTION), entryBuilder).build());
            general.add(stackable.createConfigEntries(entryBuilder.startSubCategory(STACKABLE_OPTION), entryBuilder).build());
            general.add(maxCount.createConfigEntries(entryBuilder.startSubCategory(MAX_COUNT_OPTION), entryBuilder).build());
            general.add(fireproof.createConfigEntries(entryBuilder.startSubCategory(FIREPROOF_OPTION), entryBuilder).build());
            general.add(damageable.createConfigEntries(entryBuilder.startSubCategory(DAMAGEABLE_OPTION), entryBuilder).build());
            general.add(repairCost.createConfigEntries(entryBuilder.startSubCategory(REPAIR_COST_OPTION), entryBuilder).build());
            general.add(enchantable.createConfigEntries(entryBuilder.startSubCategory(ENCHANTABLE_OPTION), entryBuilder).build());
            general.add(enchantability.createConfigEntries(entryBuilder.startSubCategory(ENCHANTABILITY_OPTION), entryBuilder).build());
        }

        public static final class Rarity extends TooltipOption {
            private static final Text SHOW_RARITY_COLOR_FIELD = new TranslatableText(GENERAL_PREFIX + "rarity.showRarityColor");
            private static final Text SHOW_RARITY_COLOR_TOOLTIP = new TranslatableText(GENERAL_PREFIX + "rarity.showRarityColor.tooltip");

            private boolean showRarityColor;

            public Rarity() {
                super();
                showRarityColor = defaultShowRarityColor();
            }

            @Override
            protected void addConfigEntries(SubCategoryBuilder category, ConfigEntryBuilder entryBuilder) {
                addShowEntry(category, entryBuilder);
                addColorEntry(category, entryBuilder);
                category.add(entryBuilder.startBooleanToggle(SHOW_RARITY_COLOR_FIELD, showRarityColor)
                        .setDefaultValue(defaultShowRarityColor())
                        .setTooltip(SHOW_RARITY_COLOR_TOOLTIP)
                        .setSaveConsumer(this::setShowRarityColor)
                        .build());
                addRequiredEntry(category, entryBuilder);
                addPositionEntry(category, entryBuilder);
            }

            @Override
            protected int defaultPosition() {
                return 1;
            }

            private boolean defaultShowRarityColor() {
                return true;
            }

            private void setShowRarityColor(boolean value) {
                showRarityColor = value;
            }

            public boolean isRarityColorShown() {
                return showRarityColor;
            }
        }
    }

    public static final class Food extends ConfigEntry {
        private static final Text HUNGER_OPTION = new TranslatableText(FOOD_PREFIX + "hunger");
        private static final Text SATURATION_MODIFIER_OPTION = new TranslatableText(FOOD_PREFIX + "saturationModifier");
        private static final Text MEAT_OPTION = new TranslatableText(FOOD_PREFIX + "meat");
        private static final Text ALWAYS_EDIBLE_OPTION = new TranslatableText(FOOD_PREFIX + "alwaysEdible");
        private static final Text SNACK_OPTION = new TranslatableText(FOOD_PREFIX + "snack");

        private final TooltipOption hunger = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 9;
            }
        };
        private final TooltipOption saturationModifier = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 10;
            }
        };
        private final TooltipOption meat = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 11;
            }
        };
        private final TooltipOption alwaysEdible = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 12;
            }
        };
        private final TooltipOption snack = new TooltipOption() {
            @Override
            protected int defaultPosition() {
                return 13;
            }
        };

        public TooltipOption getHunger() {
            return hunger;
        }

        public TooltipOption getSaturationModifier() {
            return saturationModifier;
        }

        public TooltipOption getMeat() {
            return meat;
        }

        public TooltipOption getAlwaysEdible() {
            return alwaysEdible;
        }

        public TooltipOption getSnack() {
            return snack;
        }

        @Override
        protected void addConfigEntries(SubCategoryBuilder food, ConfigEntryBuilder entryBuilder) {
            food.add(hunger.createConfigEntries(entryBuilder.startSubCategory(HUNGER_OPTION), entryBuilder).build());
            food.add(saturationModifier.createConfigEntries(entryBuilder.startSubCategory(SATURATION_MODIFIER_OPTION), entryBuilder).build());
            food.add(meat.createConfigEntries(entryBuilder.startSubCategory(MEAT_OPTION), entryBuilder).build());
            food.add(alwaysEdible.createConfigEntries(entryBuilder.startSubCategory(ALWAYS_EDIBLE_OPTION), entryBuilder).build());
            food.add(snack.createConfigEntries(entryBuilder.startSubCategory(SNACK_OPTION), entryBuilder).build());
        }
    }

    public static final class NBT extends TooltipOption {
        private static final Text MAX_LINE_LENGTH_FIELD = new TranslatableText(NBT_PREFIX + "maxLineLength");
        private static final Text MAX_LINE_LENGTH_TOOLTIP = new TranslatableText(NBT_PREFIX + "maxLineLength.tooltip");

        private int maxLineLength;

        public NBT() {
            super();
            maxLineLength = defaultMaxLineLength();
        }

        @Override
        protected void addConfigEntries(SubCategoryBuilder category, ConfigEntryBuilder entryBuilder) {
            addShowEntry(category, entryBuilder);
            addColorEntry(category, entryBuilder);
            category.add(entryBuilder.startIntField(MAX_LINE_LENGTH_FIELD, maxLineLength)
                    .setDefaultValue(defaultMaxLineLength())
                    .setMin(lowestMaxLineLength())
                    .setMax(highestMaxLineLength())
                    .setTooltip(MAX_LINE_LENGTH_TOOLTIP)
                    .setSaveConsumer(this::setMaxLineLength)
                    .build());
            addRequiredEntry(category, entryBuilder);
            addPositionEntry(category, entryBuilder);
        }

        @Override
        protected int defaultPosition() {
            return 14;
        }

        private int defaultMaxLineLength() {
            return 50;
        }

        private int lowestMaxLineLength() {
            return 20;
        }

        private int highestMaxLineLength() {
            return 100;
        }

        public int getMaxLineLength() {
            return maxLineLength;
        }

        private void setMaxLineLength(int value) {
            maxLineLength = value;
        }
    }

    private static abstract class ConfigEntry {
        protected final SubCategoryBuilder createConfigEntries(SubCategoryBuilder category, ConfigEntryBuilder entryBuilder) {
            addConfigEntries(category, entryBuilder);
            return category;
        }

        protected abstract void addConfigEntries(SubCategoryBuilder category, ConfigEntryBuilder entryBuilder);
    }

    public static abstract class TooltipOption extends ConfigEntry {
        private boolean show;
        private FormattingColor color;
        private boolean requireAlt;
        private boolean requireControl;
        private boolean requireShift;
        private boolean requireAdvanced;
        private int position;

        public TooltipOption() {
            show = defaultShow();
            color = defaultColor();
            requireAlt = defaultAlt();
            requireControl = defaultControl();
            requireShift = defaultShift();
            requireAdvanced = defaultAdvanced();
            position = defaultPosition();
        }

        public final boolean isShown() {
            return show;
        }

        public final Formatting getColor() {
            return color.getFormatting();
        }

        private void setColor(FormattingColor value) {
            color = value;
        }

        public final boolean isAltRequired() {
            return requireAlt;
        }

        public final boolean isControlRequired() {
            return requireControl;
        }

        public final boolean isShiftRequired() {
            return requireShift;
        }

        public final boolean isAdvancedRequired() {
            return requireAdvanced;
        }

        public final int getPosition() {
            return position;
        }

        private void setPosition(int value) {
            position = value;
        }

        private void setShow(boolean value) {
            show = value;
        }

        private void setRequireAlt(boolean value) {
            requireAlt = value;
        }

        private void setRequireControl(boolean value) {
            requireControl = value;
        }

        private void setRequireShift(boolean value) {
            requireShift = value;
        }

        private void setRequireAdvanced(boolean value) {
            requireAdvanced = value;
        }

        @Override
        protected void addConfigEntries(SubCategoryBuilder category, ConfigEntryBuilder entryBuilder) {
            addShowEntry(category, entryBuilder);
            addColorEntry(category, entryBuilder);
            addRequiredEntry(category, entryBuilder);
            addPositionEntry(category, entryBuilder);
        }

        protected void addShowEntry(SubCategoryBuilder subCategory, ConfigEntryBuilder entryBuilder) {
            subCategory.add(entryBuilder.startBooleanToggle(TOOLTIP_SHOWN_FIELD, show)
                    .setDefaultValue(defaultShow())
                    .setSaveConsumer(this::setShow)
                    .build());
        }

        protected void addColorEntry(SubCategoryBuilder subCategory, ConfigEntryBuilder entryBuilder) {
            subCategory.add(entryBuilder.startDropdownMenu(TOOLTIP_COLOR_FIELD, TopCellElementBuilder.of(color, FormattingColor::valueOf, Util::formattingAsText))
                    .setDefaultValue(defaultColor())
                    .setSelections(Arrays.asList(FormattingColor.values()))
                    .setTooltipSupplier(value -> Optional.of(new Text[]{value.getText()}))
                    .setSuggestionMode(false)
                    .setSaveConsumer(this::setColor)
                    .build());
        }

        protected void addRequiredEntry(SubCategoryBuilder subCategory, ConfigEntryBuilder entryBuilder) {
            subCategory.add(entryBuilder.startBooleanToggle(TOOLTIP_ALT_FIELD, requireAlt)
                    .setDefaultValue(defaultAlt())
                    .setSaveConsumer(this::setRequireAlt)
                    .build());
            subCategory.add(entryBuilder.startBooleanToggle(TOOLTIP_CONTROL_FIELD, requireControl)
                    .setDefaultValue(defaultControl())
                    .setSaveConsumer(this::setRequireControl)
                    .build());
            subCategory.add(entryBuilder.startBooleanToggle(TOOLTIP_SHIFT_FIELD, requireShift)
                    .setDefaultValue(defaultShift())
                    .setSaveConsumer(this::setRequireShift)
                    .build());
            subCategory.add(entryBuilder.startBooleanToggle(TOOLTIP_ADVANCED_FIELD, requireAdvanced)
                    .setDefaultValue(defaultAdvanced())
                    .setTooltip(TOOLTIP_ADVANCED_TOOLTIP)
                    .setSaveConsumer(this::setRequireAdvanced)
                    .build());
        }

        protected void addPositionEntry(SubCategoryBuilder subCategory, ConfigEntryBuilder entryBuilder) {
            subCategory.add(entryBuilder.startDropdownMenu(TOOLTIP_POSITION_FIELD, TopCellElementBuilder.of(position, Util::tryParseInt))
                    .setTooltip(TOOLTIP_POSITION_TOOLTIP, TOOLTIP_POSITION_WARNING)
                    .setDefaultValue(defaultPosition())
                    .setSuggestionMode(false)
                    .setSelections(MoreTooltipsConfig.TOOLTIP_POSITIONS)
                    .setSaveConsumer(this::setPosition)
                    .build());
        }

        protected boolean defaultShow() {
            return false;
        }

        protected FormattingColor defaultColor() {
            return FormattingColor.DARK_GRAY;
        }

        protected boolean defaultAlt() {
            return false;
        }

        protected boolean defaultControl() {
            return false;
        }

        protected boolean defaultShift() {
            return false;
        }

        protected boolean defaultAdvanced() {
            return false;
        }

        protected abstract int defaultPosition();
    }
}