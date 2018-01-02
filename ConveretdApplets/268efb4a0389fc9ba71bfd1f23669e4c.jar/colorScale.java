import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

class colorScale extends RGBImageFilter
{
    int currentStep;
    int totalStep;
    
    public colorScale(final int thisStep, final int total, final boolean thisIndex) {
        this.currentStep = thisStep;
        this.totalStep = total;
        super.canFilterIndexColorModel = thisIndex;
    }
    
    public int filterRGB(final int x, final int y, final int rgb) {
        int red = (rgb & 0xFF0000) >> 16;
        int green = (rgb & 0xFF00) >> 8;
        int blue = rgb & 0xFF;
        final int gray = (red + green + blue) / 3;
        red = gray + (red - gray) / this.totalStep * this.currentStep;
        green = gray + (green - gray) / this.totalStep * this.currentStep;
        blue = gray + (blue - gray) / this.totalStep * this.currentStep;
        if (red > 255) {
            red = 255;
        }
        if (green > 255) {
            green = 255;
        }
        if (blue > 255) {
            blue = 255;
        }
        if (red < 0) {
            red = 0;
        }
        if (green < 0) {
            green = 0;
        }
        if (blue < 0) {
            blue = 0;
        }
        return 0xFF000000 | red << 16 | green << 8 | blue;
    }
}
