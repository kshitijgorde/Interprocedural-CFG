import java.awt.image.ColorModel;

// 
// Decompiled by Procyon v0.5.30
// 

final class WImage$1 extends ColorModel
{
    WImage$1(final int n) {
        super(n);
    }
    
    public final int getRGB(final int n) {
        final int n2 = n & 0xFF000000;
        final int n3 = n >> 24 & 0xFF;
        if (n3 == 0) {
            return 0;
        }
        return n2 | ((n >> 16 & 0xFF) * 255 / n3 & 0xFF) << 16 | ((n >> 8 & 0xFF) * 255 / n3 & 0xFF) << 8 | ((n & 0xFF) * 255 / n3 & 0xFF);
    }
    
    public final int getRed(final int n) {
        final int alpha = this.getAlpha(n);
        if (alpha == 0) {
            return 0;
        }
        return (n >> 16 & 0xFF) * 255 / alpha & 0xFF;
    }
    
    public final int getGreen(final int n) {
        final int alpha = this.getAlpha(n);
        if (alpha == 0) {
            return 0;
        }
        return (n >> 8 & 0xFF) * 255 / alpha & 0xFF;
    }
    
    public final int getAlpha(final int n) {
        return n >> 24 & 0xFF;
    }
    
    public final int getBlue(final int n) {
        final int alpha = this.getAlpha(n);
        if (alpha == 0) {
            return 0;
        }
        return (n & 0xFF) * 255 / alpha & 0xFF;
    }
}
