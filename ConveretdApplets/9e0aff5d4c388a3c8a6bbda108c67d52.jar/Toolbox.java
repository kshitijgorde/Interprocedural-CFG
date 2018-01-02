import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Toolbox
{
    static int[] getStringWidth(final Graphics g, final String[] str) {
        final int[] widths = new int[str.length];
        final Font ft = g.getFont();
        for (int i = 0; i < str.length; ++i) {
            final Rectangle2D b = ft.getStringBounds(str[i], ((Graphics2D)g).getFontRenderContext());
            widths[i] = (int)b.getWidth();
        }
        return widths;
    }
    
    static Color getNextColor(final Color current, final Color last, final double tolerance, final double fadeInc) {
        if (current == last) {
            return current;
        }
        final double bgRed = last.getRed();
        final double bgGreen = last.getGreen();
        final double bgBlue = last.getBlue();
        double red = current.getRed();
        double green = current.getGreen();
        double blue = current.getBlue();
        final double low = 1.1;
        final double high = 0.9;
        if (Math.abs(red - bgRed) / 255.0 < tolerance && Math.abs(green - bgGreen) / 255.0 < tolerance && Math.abs(blue - bgBlue) / 255.0 < tolerance) {
            return last;
        }
        if (red > bgRed) {
            double diff = red - bgRed;
            diff = Math.ceil(diff * fadeInc);
            red -= diff;
        }
        else if (red < bgRed) {
            double diff = bgRed - red;
            diff = Math.ceil(diff * fadeInc);
            red += diff;
        }
        if (green > bgGreen) {
            double diff = green - bgGreen;
            diff = Math.ceil(diff * fadeInc);
            green -= diff;
        }
        else if (green < bgGreen) {
            double diff = bgGreen - green;
            diff = Math.ceil(diff * fadeInc);
            green += diff;
        }
        if (blue > bgBlue) {
            double diff = blue - bgBlue;
            diff = Math.ceil(diff * fadeInc);
            blue -= diff;
        }
        else if (blue < bgBlue) {
            double diff = bgBlue - blue;
            diff = Math.ceil(diff * fadeInc);
            blue += diff;
        }
        return new Color((int)red, (int)green, (int)blue);
    }
}
