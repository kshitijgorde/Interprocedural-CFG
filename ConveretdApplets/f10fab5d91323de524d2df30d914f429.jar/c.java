import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

class c extends Button
{
    public c(final String s) {
        super(s);
    }
    
    public Dimension getPreferredSize() {
        final boolean a = d.a;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n2;
        int n = n2 = fontMetrics.getHeight() + 2;
        final int n3 = 20;
        if (!a) {
            if (n2 < n3) {
                n = 19;
            }
            fontMetrics.stringWidth(this.getLabel());
        }
        final int n5;
        int n4 = n5 = n2 + n3;
        if (a || n5 > 120) {
            n4 = n5;
        }
        return new Dimension(n4, n);
    }
}
