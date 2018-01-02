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
    
    public int filterRGB(final int n, final int n2, final int backPixel) {
        if (n == 0 && n2 == 0) {
            this.backPixel = backPixel;
        }
        if (backPixel == this.backPixel) {
            return this.backPixel;
        }
        final int n3 = backPixel & 0xFF0000;
        final int n4 = backPixel & 0xFF00;
        final int n5 = backPixel & 0xFF;
        int n6 = 0;
        if (n3 > this.redThreshhold) {
            ++n6;
        }
        if (n4 > this.greenThreshhold) {
            ++n6;
        }
        if (n5 > this.blueThreshhold) {
            ++n6;
        }
        return (n6 >= 3) ? this.backPixel : (SystemColor.controlShadow.getRGB() | 0xFF000000);
    }
}
