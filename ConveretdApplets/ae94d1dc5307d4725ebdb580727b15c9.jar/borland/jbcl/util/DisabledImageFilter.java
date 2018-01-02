// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.awt.SystemColor;
import java.awt.image.RGBImageFilter;

class DisabledImageFilter extends RGBImageFilter
{
    int backPixel;
    int redThreshhold;
    int greenThreshhold;
    int blueThreshhold;
    
    DisabledImageFilter() {
        this.redThreshhold = 12582912;
        this.greenThreshhold = 49152;
        this.blueThreshhold = 192;
        super.canFilterIndexColorModel = false;
    }
    
    DisabledImageFilter(final int redThreshhold, final int greenThreshhold, final int blueThreshhold) {
        this.redThreshhold = 12582912;
        this.greenThreshhold = 49152;
        this.blueThreshhold = 192;
        super.canFilterIndexColorModel = false;
        this.redThreshhold = redThreshhold;
        this.greenThreshhold = greenThreshhold;
        this.blueThreshhold = blueThreshhold;
    }
    
    public int filterRGB(final int x, final int y, final int rgb) {
        if (x == 0 && y == 0) {
            this.backPixel = rgb;
        }
        if (rgb == this.backPixel) {
            return this.backPixel;
        }
        final int redElement = rgb & 0xFF0000;
        final int greenElement = rgb & 0xFF00;
        final int blueElement = rgb & 0xFF;
        int count = 0;
        if (redElement > this.redThreshhold) {
            ++count;
        }
        if (greenElement > this.greenThreshhold) {
            ++count;
        }
        if (blueElement > this.blueThreshhold) {
            ++count;
        }
        return (count >= 3) ? this.backPixel : (SystemColor.controlShadow.getRGB() | 0xFF000000);
    }
}
