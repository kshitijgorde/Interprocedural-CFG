// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class c extends RGBImageFilter
{
    private int a;
    private int b;
    
    public c(final Color color, final Color color2) {
        super.canFilterIndexColorModel = true;
        this.a = (color.getRGB() & 0xFFFFFF);
        this.b = (color2.getRGB() & 0xFFFFFF);
    }
    
    public int filterRGB(final int n, final int n2, int n3) {
        if ((n3 & 0xFFFFFF) == this.a) {
            n3 = ((n3 & 0xFF000000) | this.b);
        }
        return n3;
    }
}
