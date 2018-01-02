// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class PlayerColorFilter extends RGBImageFilter
{
    private Color color;
    
    PlayerColorFilter(final Color color) {
        this.color = color;
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 >> 24 & 0xFF;
        final int n5 = n3 >> 16 & 0xFF;
        final int n6 = n3 >> 8 & 0xFF;
        final int n7 = n3 >> 0 & 0xFF;
        if (n5 != n6 | n5 != n7) {
            return n3;
        }
        return (n4 & 0xFF) << 24 | ((this.color.getRed() + n5) / 2 & 0xFF) << 16 | ((this.color.getGreen() + n6) / 2 & 0xFF) << 8 | ((this.color.getBlue() + n7) / 2 & 0xFF) << 0;
    }
}
