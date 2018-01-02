// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;

public class AnimatedTextPool
{
    public void fadeIn(final int n, final int n2, final Color color, final Color color2, final Graphics graphics, final int n3, final String s, final int n4) {
        int n5 = n4 * 100 / n3;
        if (n5 > 100 || n5 < 0) {
            n5 = 100;
        }
        graphics.setColor(this.averageColor(color, n5, color2));
        graphics.drawString(s, n, n2);
    }
    
    public void fadeOut(final int n, final int n2, final Color color, final Color color2, final Graphics graphics, final int n3, final String s, final int n4) {
        int n5 = n4 * 100 / n3;
        if (n5 > 100 || n5 < 0) {
            n5 = 100;
        }
        graphics.setColor(this.averageColor(color, 100 - n5, color2));
        graphics.drawString(s, n, n2);
    }
    
    public int getMaxCharWidth(final FontMetrics fontMetrics) {
        final int[] widths = fontMetrics.getWidths();
        int n = 0;
        for (int i = 0; i < widths.length; ++i) {
            if (n < widths[i]) {
                n = widths[i];
            }
        }
        return n;
    }
    
    public Color averageColor(final Color color, final int n, final Color color2) {
        final int n2 = 100 - n;
        return new Color(Math.min((color.getRed() * n + color2.getRed() * n2) / 100, 255), Math.min((color.getGreen() * n + color2.getGreen() * n2) / 100, 255), Math.min((color.getBlue() * n + color2.getBlue() * n2) / 100, 255));
    }
    
    protected Color randomColor() {
        return new Color((int)(Math.random() * 255.0), (int)(Math.random() * 255.0), (int)(Math.random() * 255.0));
    }
}
