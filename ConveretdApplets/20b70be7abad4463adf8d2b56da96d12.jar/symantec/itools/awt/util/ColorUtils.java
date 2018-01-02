// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt.util;

import symantec.itools.util.GeneralUtils;
import java.awt.Color;

public class ColorUtils
{
    public static Color darken(final int r, final int g, final int b, final double percent) throws IllegalArgumentException {
        GeneralUtils.checkValidPercent(percent);
        return new Color(Math.max((int)(r * (1.0 - percent)), 0), Math.max((int)(g * (1.0 - percent)), 0), Math.max((int)(b * (1.0 - percent)), 0));
    }
    
    public static Color darken(final Color c, final double percent) throws IllegalArgumentException {
        GeneralUtils.checkValidPercent(percent);
        final int r = c.getRed();
        final int g = c.getGreen();
        final int b = c.getBlue();
        return darken(r, g, b, percent);
    }
    
    public static Color lighten(final int r, final int g, final int b, final double percent) throws IllegalArgumentException {
        GeneralUtils.checkValidPercent(percent);
        final int r2 = r + (int)((255 - r) * percent);
        final int g2 = g + (int)((255 - g) * percent);
        final int b2 = b + (int)((255 - b) * percent);
        return new Color(r2, g2, b2);
    }
    
    public static Color lighten(final Color c, final double percent) throws IllegalArgumentException {
        GeneralUtils.checkValidPercent(percent);
        final int r = c.getRed();
        final int g = c.getGreen();
        final int b = c.getBlue();
        return lighten(r, g, b, percent);
    }
    
    public static Color fade(final Color from, final Color to, final double percent) throws IllegalArgumentException {
        GeneralUtils.checkValidPercent(percent);
        final int from_r = from.getRed();
        final int from_g = from.getGreen();
        final int from_b = from.getBlue();
        final int to_r = to.getRed();
        final int to_g = to.getGreen();
        final int to_b = to.getBlue();
        int r;
        if (from_r > to_r) {
            r = to_r + (int)((from_r - to_r) * (1.0 - percent));
        }
        else {
            r = to_r - (int)((to_r - from_r) * (1.0 - percent));
        }
        int g;
        if (from_g > to_r) {
            g = to_g + (int)((from_g - to_g) * (1.0 - percent));
        }
        else {
            g = to_g - (int)((to_g - from_g) * (1.0 - percent));
        }
        int b;
        if (from_b > to_b) {
            b = to_b + (int)((from_b - to_b) * (1.0 - percent));
        }
        else {
            b = to_b - (int)((to_b - from_b) * (1.0 - percent));
        }
        return new Color(r, g, b);
    }
    
    public static double lightness(final Color c) {
        if (c == null) {
            return 0.0;
        }
        final double r = c.getRed();
        final double g = c.getGreen();
        final double b = c.getBlue();
        final double max = Math.max(r, Math.max(g, b)) / 255.0 / 2.0;
        final double min = Math.min(r, Math.min(g, b)) / 255.0 / 2.0;
        return max + min;
    }
    
    public static Color calculateHilightColor(final Color c) {
        if (c == null) {
            return null;
        }
        final double lightness = lightness(c);
        return (lightness >= 0.9) ? darken(c, 0.1) : ((lightness <= 0.2) ? lighten(c, 0.6000000000000001) : lighten(c, 0.6000000000000001));
    }
    
    public static Color calculateShadowColor(final Color c) {
        if (c == null) {
            return null;
        }
        final double lightness = lightness(c);
        return (lightness >= 0.9) ? darken(c, 0.25) : ((lightness <= 0.2) ? lighten(c, 0.2) : darken(c, 0.25));
    }
}
