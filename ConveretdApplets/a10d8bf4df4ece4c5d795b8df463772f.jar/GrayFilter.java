import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class GrayFilter extends RGBImageFilter
{
    private int darkness;
    
    public GrayFilter() {
        this.darkness = -5263441;
        super.canFilterIndexColorModel = true;
    }
    
    public GrayFilter(final int darkness) {
        this();
        this.darkness = darkness;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        return n3 & this.darkness;
    }
}
