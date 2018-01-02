import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class nj extends Canvas
{
    private int width;
    
    public nj(final int width) {
        this.width = width;
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        final Dimension minimumSize = super.getMinimumSize();
        minimumSize.width = this.width;
        return minimumSize;
    }
}
