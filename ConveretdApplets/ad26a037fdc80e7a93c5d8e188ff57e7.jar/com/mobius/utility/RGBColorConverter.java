// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.utility;

import java.util.StringTokenizer;
import java.awt.Color;

public class RGBColorConverter
{
    public static final Color DEFAULT_COLOR;
    
    public static Color getColorFromString(final String s) {
        return getColorFromString(s, RGBColorConverter.DEFAULT_COLOR);
    }
    
    public static Color getColorFromString(final String s, final Color color) {
        if (s == null) {
            throw new IllegalArgumentException("The color argument can't be null");
        }
        Color color2 = color;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " \t\n\r\f,;");
        try {
            color2 = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        catch (NumberFormatException ex) {}
        return color2;
    }
    
    static {
        DEFAULT_COLOR = new Color(255, 255, 255);
    }
}
