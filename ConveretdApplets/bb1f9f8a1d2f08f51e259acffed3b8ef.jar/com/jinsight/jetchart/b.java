// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

class b extends RGBImageFilter
{
    private int a;
    private int b;
    
    public int filterRGB(final int n, final int n2, final int n3) {
        if (n3 != this.b) {
            return (n3 & 0xFFFFFF) | this.a << 24;
        }
        return n3;
    }
    
    public b(final Color color) {
        this.a = 0;
        super.canFilterIndexColorModel = true;
        this.b = color.getRGB();
    }
}
