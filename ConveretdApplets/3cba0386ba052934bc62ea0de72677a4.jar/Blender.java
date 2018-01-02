import java.awt.Color;
import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Blender extends RGBImageFilter
{
    private int r;
    private int g;
    private int b;
    private int alpha;
    
    public Blender(final Color color, final int n) {
        super.canFilterIndexColorModel = true;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.alpha = n + 1;
    }
    
    public static Color blendColors(final Color color, final Color color2, int n) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int red2 = color2.getRed();
        final int green2 = color2.getGreen();
        final int blue2 = color2.getBlue();
        ++n;
        return new Color(red + (red2 - red) * n / 256, green + (green2 - green) * n / 256, blue + (blue2 - blue) * n / 256);
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 & 0xFF;
        final int n5 = (n3 & 0xFF0000) >> 16;
        final int n6 = (n3 & 0xFF00) >> 8;
        return (n3 & 0xFF000000) | n5 + (this.r - n5) * this.alpha / 256 << 16 | n6 + (this.g - n6) * this.alpha / 256 << 8 | n4 + (this.b - n4) * this.alpha / 256;
    }
}
