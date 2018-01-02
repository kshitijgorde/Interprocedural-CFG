// 
// Decompiled by Procyon v0.5.30
// 

package com.jafar.ui;

import java.awt.image.RGBImageFilter;

class DisableImageFilter extends RGBImageFilter
{
    public DisableImageFilter() {
        super.canFilterIndexColorModel = false;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        if ((n % 2 ^ n2 % 2) == 0x1) {
            return n3 & 0xFFFFFF;
        }
        return n3;
    }
}
