import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class af extends RGBImageFilter
{
    public double a;
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 >> 16 & 0xFF;
        final int n5 = n3 >> 8 & 0xFF;
        final int n6 = n3 & 0xFF;
        int n7 = (int)(n4 * this.a);
        int n8 = (int)(n5 * this.a);
        int n9 = (int)(n6 * this.a);
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
    
    public af(final double a) {
        this.a = a;
        super.canFilterIndexColorModel = true;
    }
}
