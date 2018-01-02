import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class FontUtils
{
    protected static final Font I;
    public boolean forceTextAntiAlias;
    private Font currentFont;
    
    public FontUtils() {
        this.forceTextAntiAlias = false;
        this.currentFont = FontUtils.I;
    }
    
    public final void drawString(final Graphics graphics, final String s, final int n, final int n2) {
        if (s != null) {
            this.drawChars(graphics, s.toCharArray(), 0, s.length(), n, n2);
        }
    }
    
    public final int width(final FontMetricsProvider fontMetricsProvider, final Font font, final String s) {
        return this.charWidth(fontMetricsProvider, font, s.toCharArray(), 0, s.length());
    }
    
    public final int charWidth(final FontMetricsProvider fontMetricsProvider, final Font font, final char[] array, final int n, final int n2) {
        int n3 = 0;
        final FontMetrics fontMetrics = fontMetricsProvider.getFontMetrics(font);
        for (int i = n; i < n + n2; ++i) {
            n3 += fontMetrics.charWidth(array[i]);
        }
        return n3;
    }
    
    public final void drawChars(final Graphics graphics, final char[] array, final int n, final int n2, int n3, final int n4) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (OmegaSystem.versio_java >= 2) {
            IsGraphics2D.pushAntialiasing(graphics);
            IsGraphics2D.setTextAntialiasing(graphics, fontMetrics.getFont(), this.forceTextAntiAlias);
        }
        for (int i = n; i < n + n2; ++i) {
            graphics.drawChars(array, i, 1, n3, n4);
            n3 += fontMetrics.charWidth(array[i]);
        }
        if (OmegaSystem.versio_java >= 2) {
            IsGraphics2D.popAntialiasing(graphics);
        }
    }
    
    public final void drawHorizontalStretchy(final Graphics graphics, final int n, final int n2, final int n3, final char c) {
    }
    
    public final void drawVerticalStretchy(final Graphics graphics, final int n, final int n2, final int n3, final char c) {
    }
    
    public static final boolean isAlwaysText(final char c) {
        return (c >= '\u0900' && c < '\u097f') || (c >= '\u0590' && c <= '\u05ff');
    }
    
    public static final boolean isRTL(final char c) {
        return (c >= '\u0600' && c <= '\u06ff') || (c >= '\u0590' && c <= '\u05ff');
    }
    
    public final Font getCurrentFont(final int n, final AbstractBox abstractBox) {
        return this.currentFont;
    }
    
    public final Font getDefaultFont(final int n, final AbstractBox abstractBox) {
        return FontUtils.I;
    }
    
    public final void setCurrentFont(final int n, final Font currentFont) {
        this.currentFont = currentFont;
    }
    
    public final void setFontSize(final int n) {
        this.currentFont = new Font(this.currentFont.getName(), this.currentFont.getStyle(), n);
    }
    
    public final int[] computeMetrics(final FontMetricsProvider fontMetricsProvider, final Font font, final String s) {
        return null;
    }
    
    static {
        I = new Font("SansSerif", 1, 16);
    }
}
