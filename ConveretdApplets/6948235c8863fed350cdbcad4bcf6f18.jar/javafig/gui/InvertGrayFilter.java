// 
// Decompiled by Procyon v0.5.30
// 

package javafig.gui;

import java.awt.image.RGBImageFilter;

public class InvertGrayFilter extends RGBImageFilter
{
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 & 0xFF000000;
        final int n5 = 255 - ((n3 & 0xFF) + (n3 & 0xFF) + (n3 & 0xFF)) / 3;
        return n4 | n5 << 16 | n5 << 8 | n5;
    }
    
    public InvertGrayFilter() {
        super.canFilterIndexColorModel = true;
    }
}
