import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class interface
{
    public static int b(final char c, final int n, final Font font) {
        return (n - getFontMetrics(font).charWidth(c)) / 2;
    }
    
    public static int b(final char c, final int n, final Font font, final int n2) {
        return b(c, n, font) + n2;
    }
    
    public static int _(final char c, final int n, final Font font) {
        final FontMetrics fontMetrics = getFontMetrics(font);
        int n2 = n / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
        if (!Character.isLowerCase(c)) {
            ++n2;
        }
        return n2;
    }
    
    public static int _(final char c, final int n, final Font font, final int n2) {
        return _(c, n, font) + n2;
    }
    
    private static FontMetrics getFontMetrics(final Font font) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font);
    }
}
