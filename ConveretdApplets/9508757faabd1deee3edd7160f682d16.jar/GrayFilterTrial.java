import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class GrayFilterTrial extends RGBImageFilter
{
    public GrayFilterTrial() {
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        return (n3 & 0xFF000000) | (((n3 & 0xFF0000) + 25165824) / 3 & 0xFF0000) | (((n3 & 0xFF00) + 98304) / 3 & 0xFF00) | (((n3 & 0xFF) + 384) / 3 & 0xFF);
    }
}
