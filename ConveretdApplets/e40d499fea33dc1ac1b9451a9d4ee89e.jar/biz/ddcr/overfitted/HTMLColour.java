// 
// Decompiled by Procyon v0.5.30
// 

package biz.ddcr.overfitted;

import java.util.HashMap;
import java.awt.Color;
import java.util.Map;

public final class HTMLColour
{
    private static Map colorNames;
    
    public static Color decode(final String color) {
        Color c = null;
        if (color != null && !color.isEmpty()) {
            c = HTMLColour.colorNames.get(color.trim().toLowerCase());
            if (null == c) {
                try {
                    c = Color.decode(color.trim());
                }
                catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Unknown colour code given", e);
                }
            }
        }
        return c;
    }
    
    public static Integer integerValue(final String color) {
        return new Integer(decode(color).getRGB());
    }
    
    public static String encodeRGB(final Color color) {
        return (color == null) ? null : ("#" + Integer.toHexString(color.getRGB()).substring(2).toUpperCase());
    }
    
    static {
        (HTMLColour.colorNames = new HashMap()).put("black", new Color(0));
        HTMLColour.colorNames.put("green", new Color(32768));
        HTMLColour.colorNames.put("silver", new Color(12632256));
        HTMLColour.colorNames.put("lime", new Color(65280));
        HTMLColour.colorNames.put("gray", new Color(8421504));
        HTMLColour.colorNames.put("olive", new Color(8421376));
        HTMLColour.colorNames.put("white", new Color(16777215));
        HTMLColour.colorNames.put("yellow", new Color(16776960));
        HTMLColour.colorNames.put("maroon", new Color(8388608));
        HTMLColour.colorNames.put("navy", new Color(128));
        HTMLColour.colorNames.put("red", new Color(16711680));
        HTMLColour.colorNames.put("blue", new Color(255));
        HTMLColour.colorNames.put("purple", new Color(8388736));
        HTMLColour.colorNames.put("teal", new Color(32896));
        HTMLColour.colorNames.put("fuchsia", new Color(16711935));
        HTMLColour.colorNames.put("aqua", new Color(65535));
    }
}
