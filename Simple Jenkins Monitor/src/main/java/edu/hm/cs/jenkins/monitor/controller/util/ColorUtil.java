package edu.hm.cs.jenkins.monitor.controller.util;

import android.graphics.Color;

/**
 * Defines global accessible colors.
 *
 * @author Tobias Wochinger
 */
public final class ColorUtil {

    public static final String TRANSPARENCY = "#40";

    public static final int LIGHT_BLUE = getColorWithTransparency("729FCF");

    public static final int LIGHT_RED = getColorWithTransparency("ef2929");

    public static final int LIGHT_GREY = getColorWithTransparency("ababab");

    public static final int LIGHT_YELLOW = getColorWithTransparency("fce94f");

    private ColorUtil() {
        //not needed
    }

    private static int getColorWithTransparency(final String color) {
        return Color.parseColor(TRANSPARENCY + color);
    }
}