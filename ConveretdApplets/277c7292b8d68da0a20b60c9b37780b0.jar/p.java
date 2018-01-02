import java.awt.Dimension;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class p extends Label
{
    private int width;
    final Ticker x;
    
    p(final Ticker x, final String s, final int n, final int width) {
        super(s, n);
        this.x = x;
        this.width = width;
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (this.width < 0) {
            return preferredSize;
        }
        preferredSize.width = this.width;
        return preferredSize;
    }
}
