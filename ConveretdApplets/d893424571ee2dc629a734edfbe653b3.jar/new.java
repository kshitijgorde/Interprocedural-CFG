import java.awt.Dimension;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class new extends Label
{
    private int width;
    final Ticker ja;
    
    new(final Ticker ja, final String s, final int n, final int width) {
        super(s, n);
        this.ja = ja;
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
