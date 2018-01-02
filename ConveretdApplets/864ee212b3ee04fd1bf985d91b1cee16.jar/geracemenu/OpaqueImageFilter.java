// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class OpaqueImageFilter extends RGBImageFilter
{
    protected int colorRGB;
    
    public int filterRGB(final int n, final int n2, final int n3) {
        return ((int)(((n3 & 0xFF0000) >> 16) * 0.3 + ((n3 & 0xFF00) >> 8) * 0.59 + (n3 & 0xFF) * 0.11) < 128) ? this.colorRGB : 16777215;
    }
    
    public OpaqueImageFilter(final Color color) {
        super.canFilterIndexColorModel = true;
        this.colorRGB = color.getRGB();
    }
}
