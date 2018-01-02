// 
// Decompiled by Procyon v0.5.30
// 

package gfx;

import java.awt.Color;

public class Colors
{
    public static Color getAWTColor(final String s) {
        if (s == null) {
            return Color.gray;
        }
        final Object[] array = { "white", Color.white, "black", Color.black, "lgray", Color.lightGray, "gray", Color.gray, "dgray", Color.darkGray, "dred", new Color(128, 0, 0), "red", Color.red, "bred", new Color(255, 115, 90), "lred", new Color(255, 128, 128), "orange", Color.orange, "brown", new Color(128, 128, 0), "yellow", Color.yellow, "dgreen", new Color(0, 128, 0), "green", Color.green, "lgreen", new Color(90, 255, 90), "cyan", new Color(0, 200, 255), "dblue", new Color(0, 0, 128), "blue", Color.blue, "lblue", new Color(90, 90, 255), "pink", new Color(255, 0, 255), "purple", new Color(170, 0, 170) };
        for (int n = array.length / 2, i = 0; i < n; ++i) {
            if (s.equals(array[i * 2])) {
                return (Color)array[i * 2 + 1];
            }
        }
        return Color.gray;
    }
}
