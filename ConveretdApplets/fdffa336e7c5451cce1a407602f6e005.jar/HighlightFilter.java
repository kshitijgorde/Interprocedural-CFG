import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class HighlightFilter extends RGBImageFilter
{
    boolean brighter;
    int percent;
    
    public HighlightFilter(final boolean brighter, final int percent) {
        this.brighter = brighter;
        this.percent = percent;
        super.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 >> 16 & 0xFF;
        final int n5 = n3 >> 8 & 0xFF;
        final int n6 = n3 & 0xFF;
        int n7;
        int n8;
        int n9;
        if (this.brighter) {
            n7 = 255 - (255 - n4) * (100 - this.percent) / 100;
            n8 = 255 - (255 - n5) * (100 - this.percent) / 100;
            n9 = 255 - (255 - n6) * (100 - this.percent) / 100;
        }
        else {
            n7 = n4 * (100 - this.percent) / 100;
            n8 = n5 * (100 - this.percent) / 100;
            n9 = n6 * (100 - this.percent) / 100;
        }
        if (n7 < 0) {
            n7 = 0;
        }
        if (n7 > 255) {
            n7 = 255;
        }
        if (n8 < 0) {
            n8 = 0;
        }
        if (n8 > 255) {
            n8 = 255;
        }
        if (n9 < 0) {
            n9 = 0;
        }
        if (n9 > 255) {
            n9 = 255;
        }
        return (n3 & 0xFF000000) | n7 << 16 | n8 << 8 | n9;
    }
}
