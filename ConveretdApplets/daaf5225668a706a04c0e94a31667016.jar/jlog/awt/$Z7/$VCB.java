// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

import java.awt.image.RGBImageFilter;

class $VCB extends RGBImageFilter
{
    public $VCB() {
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = (n3 >> 16 & 0xFF) / 2;
        final int n5 = (n3 >> 8 & 0xFF) / 2;
        final int n6 = (n3 & 0xFF) / 2;
        final int max = Math.max((n4 + n5 + n6) / 3, 128);
        return (n3 & 0xFF000000) | n4 + max << 16 | n5 + max << 8 | n6 + max;
    }
}
