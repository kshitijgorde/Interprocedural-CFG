// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;

public class NF12Util
{
    public static Color getColor(final Color color, final int n) {
        return getColor(color.getRed(), color.getGreen(), color.getBlue(), n);
    }
    
    public static Color getColor(final int n, final int n2) {
        return getColor(n >> 16 & 0xFF, n >> 8 & 0xFF, n >> 0 & 0xFF, n2);
    }
    
    public static Color getColor(final int n, final int n2, final int n3, final int n4) {
        return new Color(n, n2, n3, n4);
    }
    
    public static int getAlpha(final Color color) {
        return color.getAlpha();
    }
    
    public static boolean isAntiAliased(final Graphics graphics) {
        return graphics instanceof Graphics2D && ((Graphics2D)graphics).getRenderingHint(RenderingHints.KEY_ANTIALIASING) == RenderingHints.VALUE_ANTIALIAS_ON;
    }
}
