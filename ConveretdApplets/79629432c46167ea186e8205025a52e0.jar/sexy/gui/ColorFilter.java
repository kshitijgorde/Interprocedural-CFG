// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.image.RGBImageFilter;

public class ColorFilter extends RGBImageFilter
{
    int mR;
    int mG;
    int mB;
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 >> 16 & 0xFF;
        final int n5 = n3 >> 8 & 0xFF;
        final int n6 = n3 & 0xFF;
        int n7 = n4 * this.mR >> 8;
        int n8 = n5 * this.mG >> 8;
        int n9 = n6 * this.mB >> 8;
        if (n7 > 255) {
            n7 = 255;
        }
        if (n8 > 255) {
            n8 = 255;
        }
        if (n9 > 255) {
            n9 = 255;
        }
        return (n3 & 0xFF000000) | n7 << 16 | n8 << 8 | n9;
    }
    
    public ColorFilter(final int mr, final int mg, final int mb) {
        this.mR = mr;
        this.mG = mg;
        this.mB = mb;
        super.canFilterIndexColorModel = true;
    }
}
