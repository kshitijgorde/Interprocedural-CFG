import java.awt.Toolkit;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class ColorUtils
{
    static double dblMaxColours;
    
    static Color darker(final Color c, final double factor) {
        return new Color(Math.max((int)(c.getRed() * factor), 0), Math.max((int)(c.getGreen() * factor), 0), Math.max((int)(c.getBlue() * factor), 0));
    }
    
    static Color brighter(final Color c, final double factor) {
        return new Color(Math.min((int)(c.getRed() * (1.0 / factor)), 255), Math.min((int)(c.getGreen() * (1.0 / factor)), 255), Math.min((int)(c.getBlue() * (1.0 / factor)), 255));
    }
    
    static Color ChooseSimilarColour(final int lowerR, final int higherR, final int lowerG, final int higherG, final int lowerB, final int higherB) {
        final int red = lowerR + (int)(Math.random() * (higherR - lowerR));
        final int green = lowerG + (int)(Math.random() * (higherG - lowerG));
        final int blue = lowerB + (int)(Math.random() * (higherB - lowerB));
        final Color c1 = new Color(red, green, blue);
        return c1;
    }
    
    static Color ChooseColour(final int lowerRGB, final int higherRGB) {
        final int red = lowerRGB + (int)(Math.random() * higherRGB);
        final int green = lowerRGB + (int)(Math.random() * higherRGB);
        final int blue = lowerRGB + (int)(Math.random() * higherRGB);
        final Color c1 = new Color(red, green, blue);
        return c1;
    }
    
    static double MaxColours() {
        return ColorUtils.dblMaxColours = Math.pow(2.0, Toolkit.getDefaultToolkit().getColorModel().getPixelSize());
    }
}
