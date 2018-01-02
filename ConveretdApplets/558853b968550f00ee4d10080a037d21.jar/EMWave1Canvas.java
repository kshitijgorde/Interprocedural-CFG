import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class EMWave1Canvas extends Canvas
{
    EMWave1Frame pg;
    
    EMWave1Canvas(final EMWave1Frame pg) {
        this.pg = pg;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(300, 400);
    }
    
    public void update(final Graphics graphics) {
        this.pg.updateEMWave1(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.pg.updateEMWave1(graphics);
    }
}
