// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

import java.awt.image.RGBImageFilter;

public class O extends RGBImageFilter
{
    public O() {
        this.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, int n3) {
        final int n4 = n3 & 0xFF000000;
        n3 = ((n3 >> 16 & 0xFF) + (n3 >> 8 & 0xFF) + (n3 & 0xFF)) / 3;
        return n4 | n3 << 16 | n3 << 8 | n3;
    }
    
    public String toString() {
        return "Colors/Grayscale";
    }
}
