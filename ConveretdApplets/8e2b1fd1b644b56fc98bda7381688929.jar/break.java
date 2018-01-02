import java.awt.print.PageFormat;
import java.awt.Graphics;
import java.awt.print.Printable;

// 
// Decompiled by Procyon v0.5.30
// 

public class break implements Printable
{
    private final d f;
    private boolean kra;
    
    public break(final d f, final boolean kra) {
        this.f = f;
        this.kra = kra;
    }
    
    public int print(final Graphics graphics, final PageFormat pageFormat, final int n) {
        if (n >= 1) {
            return 1;
        }
        synchronized (this.f) {
            final int n2 = (int)pageFormat.getImageableWidth();
            int n3 = (int)pageFormat.getImageableHeight();
            if (n3 > n2 && this.kra) {
                n3 /= 2;
            }
            this.f.a(graphics, (int)pageFormat.getImageableX(), (int)pageFormat.getImageableY(), n2, n3);
        }
        this.f.repaint();
        return 0;
    }
}
