import java.awt.print.PageFormat;
import java.awt.Graphics;
import java.awt.print.Printable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Oh implements Printable
{
    private final continue Zja;
    private boolean _ka;
    
    public Oh(final continue zja, final boolean ka) {
        this.Zja = zja;
        this._ka = ka;
    }
    
    public int print(final Graphics graphics, final PageFormat pageFormat, final int n) {
        if (n >= 1) {
            return 1;
        }
        synchronized (this.Zja) {
            final int n2 = (int)pageFormat.getImageableWidth();
            int n3 = (int)pageFormat.getImageableHeight();
            if (n3 > n2 && this._ka) {
                n3 /= 2;
            }
            this.Zja.b(graphics, (int)pageFormat.getImageableX(), (int)pageFormat.getImageableY(), n2, n3);
        }
        this.Zja.repaint();
        return 0;
    }
}
