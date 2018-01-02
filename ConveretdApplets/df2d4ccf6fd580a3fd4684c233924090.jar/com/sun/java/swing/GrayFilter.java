// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.swing;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import java.awt.image.RGBImageFilter;

public class GrayFilter extends RGBImageFilter
{
    private boolean brighter;
    private int percent;
    
    public GrayFilter(final boolean b, final int p) {
        this.brighter = b;
        this.percent = p;
        super.canFilterIndexColorModel = true;
    }
    
    public static Image createDisabledImage(final Image i) {
        final GrayFilter filter = new GrayFilter(true, 50);
        final ImageProducer prod = new FilteredImageSource(i.getSource(), filter);
        final Image grayImage = Toolkit.getDefaultToolkit().createImage(prod);
        return grayImage;
    }
    
    public int filterRGB(final int x, final int y, final int rgb) {
        int gray = (int)((0.3 * (rgb >> 16 & 0xFF) + 0.59 * (rgb >> 8 & 0xFF) + 0.11 * (rgb & 0xFF)) / 3.0);
        if (this.brighter) {
            gray = 255 - (255 - gray) * (100 - this.percent) / 100;
        }
        else {
            gray = gray * (100 - this.percent) / 100;
        }
        if (gray < 0) {
            gray = 0;
        }
        if (gray > 255) {
            gray = 255;
        }
        return (rgb & 0xFF000000) | gray << 16 | gray << 8 | gray << 0;
    }
}
