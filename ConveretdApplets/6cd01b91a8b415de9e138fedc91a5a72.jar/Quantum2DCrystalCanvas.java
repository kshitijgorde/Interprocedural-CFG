import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Quantum2DCrystalCanvas extends Canvas
{
    Quantum2DCrystalFrame pg;
    
    Quantum2DCrystalCanvas(final Quantum2DCrystalFrame pg) {
        this.pg = pg;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(300, 400);
    }
    
    public void update(final Graphics graphics) {
        this.pg.updateQuantum2DCrystal(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.pg.updateQuantum2DCrystal(graphics);
    }
}
