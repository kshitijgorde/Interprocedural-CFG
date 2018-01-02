// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.Hashtable;

public class ExtendedFont
{
    private static final Hashtable FontMetricsCache;
    FontMetrics fm;
    Color color;
    boolean underline;
    
    protected static synchronized FontMetrics getFontMetrics(final Font font) {
        if (font == null) {
            throw new NullPointerException("getFontMetrics(): null font");
        }
        FontMetrics fontMetrics = ExtendedFont.FontMetricsCache.get(font);
        if (fontMetrics == null) {
            fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
            ExtendedFont.FontMetricsCache.put(font, fontMetrics);
        }
        return fontMetrics;
    }
    
    public String getFamily() {
        return this.getFont().getFamily();
    }
    
    public String getName() {
        return this.getFont().getName();
    }
    
    public int getStyle() {
        return this.getFont().getStyle();
    }
    
    public int getSize() {
        return this.getFont().getSize();
    }
    
    public boolean isPlain() {
        return this.getFont().isPlain();
    }
    
    public boolean isBold() {
        return this.getFont().isBold();
    }
    
    public boolean isItalic() {
        return this.getFont().isItalic();
    }
    
    public boolean isUnderline() {
        return this.underline;
    }
    
    public Font getFont() {
        return this.fm.getFont();
    }
    
    public FontMetrics getFontMetrics() {
        return this.fm;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public ExtendedFont cloneFont(final Color color) {
        return new ExtendedFont(this.getFont(), color);
    }
    
    public ExtendedFont cloneFont(final boolean b) {
        return new ExtendedFont(this.getFont(), b);
    }
    
    public ExtendedFont(final String s, final int n, final int n2) {
        this(s, n, n2, null, false);
    }
    
    public ExtendedFont(final String s, final int n, final int n2, final Color color) {
        this(s, n, n2, color, false);
    }
    
    public ExtendedFont(final String s, final int n, final int n2, final boolean b) {
        this(s, n, n2, null, b);
    }
    
    public ExtendedFont(final String s, final int n, final int n2, final Color color, final boolean underline) {
        if (s == null) {
            throw new NullPointerException("ExtendedFont(): null name");
        }
        this.fm = getFontMetrics(new Font(s, n, n2));
        this.color = color;
        this.underline = underline;
    }
    
    public ExtendedFont(final Font font) {
        this(font, null, false);
    }
    
    public ExtendedFont(final Font font, final Color color) {
        this(font, color, false);
    }
    
    public ExtendedFont(final Font font, final boolean b) {
        this(font, null, b);
    }
    
    public ExtendedFont(final Font font, final Color color, final boolean underline) {
        if (font == null) {
            throw new NullPointerException("ExtendedFont(): null font");
        }
        this.fm = getFontMetrics(font);
        this.color = color;
        this.underline = underline;
    }
    
    static {
        FontMetricsCache = new Hashtable();
    }
}
