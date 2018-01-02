import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class t extends RGBImageFilter
{
    private int a;
    
    public t() {
        this.a = -5263441;
        this.canFilterIndexColorModel = true;
    }
    
    public t(final int a) {
        this();
        this.a = a;
    }
    
    public final int filterRGB(final int n, final int n2, final int n3) {
        return n3 & this.a;
    }
}
