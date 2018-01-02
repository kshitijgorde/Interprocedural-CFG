import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class EulerCanvas extends Canvas
{
    EulerFrame pg;
    
    EulerCanvas(final EulerFrame pg) {
        this.pg = pg;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(300, 400);
    }
    
    public void update(final Graphics graphics) {
        this.pg.updateEuler(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.pg.updateEuler(graphics);
    }
}
