import java.awt.Color;
import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTImageFilter extends RGBImageFilter
{
    private Color color;
    
    BMTImageFilter(final Color color) {
        this.color = color;
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 >> 16 & 0xFF;
        final int n5 = n3 >> 8 & 0xFF;
        final int n6 = n3 >> 0 & 0xFF;
        if (n4 != n5 | n4 != n6) {
            return n3;
        }
        return 0xFF000000 | ((this.color.getRed() + n4) / 2 & 0xFF) << 16 | ((this.color.getGreen() + n5) / 2 & 0xFF) << 8 | ((this.color.getBlue() + n6) / 2 & 0xFF) << 0;
    }
}
