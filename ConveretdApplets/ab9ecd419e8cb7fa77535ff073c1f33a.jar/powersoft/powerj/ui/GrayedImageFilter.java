// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.ui;

import java.awt.image.RGBImageFilter;

public class GrayedImageFilter extends RGBImageFilter
{
    static final double FILTER_RED = 0.3;
    static final double FILTER_GREEN = 0.59;
    static final double FILTER_BLUE = 0.11;
    
    public GrayedImageFilter() {
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int x, final int y, final int rgb) {
        final int a = rgb & 0xFF000000;
        int r = rgb & 0xFF0000;
        int g = rgb & 0xFF00;
        int b = rgb & 0xFF;
        r >>= 16;
        g >>= 8;
        b = b;
        final int n = (int)(r * 0.3 + g * 0.59 + b * 0.11);
        return a | n << 16 | n << 8 | n;
    }
}
