import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class FadeFilter extends RGBImageFilter
{
    private int av;
    
    public FadeFilter(final int av) {
        super.canFilterIndexColorModel = true;
        this.av = av;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        return (n3 & 0xFFFFFF) + (this.av << 24);
    }
}
