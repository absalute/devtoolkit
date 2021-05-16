package com.greentea.devtoolkit.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private Util() {
    }

    public static Text formattingAsText(FormattingColor formattingColor) {
        return formattingColor.getText();
    }
    public static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static List<Text> compoundAsText(CompoundTag tag, int lineLength, Formatting formatting) {
        List<Text> result = new ArrayList<>();
        String text = tag.toString();
        for (int i = 0, length = text.length(); i < length; i += lineLength)
            result.add(new LiteralText(text.substring(i, Math.min(i + lineLength, length))).formatted(formatting));
        return result;
    }
}