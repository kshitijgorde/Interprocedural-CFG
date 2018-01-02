import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Quantum1DCrystalCanvas extends Canvas
{
    Quantum1DCrystalFrame pg;
    
    Quantum1DCrystalCanvas(final Quantum1DCrystalFrame pg) {
        this.pg = pg;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(300, 400);
    }
    
    public void update(final Graphics graphics) {
        this.pg.updateQuantum1DCrystal(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.pg.updateQuantum1DCrystal(graphics);
    }
}
