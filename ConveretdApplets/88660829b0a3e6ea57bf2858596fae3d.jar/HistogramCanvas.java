import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class HistogramCanvas extends Canvas
{
    Gas pg;
    
    HistogramCanvas(final Gas pg) {
        this.pg = pg;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(125, 50);
    }
    
    public void update(final Graphics graphics) {
        this.pg.updateHistogram(graphics);
    }
}
