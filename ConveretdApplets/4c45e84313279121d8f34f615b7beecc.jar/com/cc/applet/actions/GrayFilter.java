// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet.actions;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.RGBImageFilter;

public class GrayFilter extends RGBImageFilter
{
    private boolean A;
    private int B;
    
    public static Image A(final Image image) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new GrayFilter(true, 50)));
    }
    
    public GrayFilter(final boolean a, final int b) {
        this.A = a;
        this.B = b;
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = (int)((0.3 * (n3 >> 16 & 0xFF) + 0.59 * (n3 >> 8 & 0xFF) + 0.11 * (n3 & 0xFF)) / 3.0);
        int n5;
        if (this.A) {
            n5 = 255 - (255 - n4) * (100 - this.B) / 100;
        }
        else {
            n5 = n4 * (100 - this.B) / 100;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n5 > 255) {
            n5 = 255;
        }
        return (n3 & 0xFF000000) | n5 << 16 | n5 << 8 | n5 << 0;
    }
}
