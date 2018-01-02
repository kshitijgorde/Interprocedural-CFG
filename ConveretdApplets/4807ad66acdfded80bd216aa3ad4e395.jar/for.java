import java.awt.Dimension;
import java.awt.Label;

// 
// Decompiled by Procyon v0.5.30
// 

class for extends Label
{
    private int width;
    final Ticker xa;
    
    for(final Ticker xa, final String s, final int n, final int width) {
        super(s, n);
        this.xa = xa;
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
