// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import java.awt.Color;

public class ColorTools
{
    public static Color setAlpha(final Color color, final int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    
    public static Color setAlpha(final Color color, double alpha) {
        alpha = Math.min(alpha, 1.0);
        alpha = Math.max(alpha, 0.0);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)alpha);
    }
}
