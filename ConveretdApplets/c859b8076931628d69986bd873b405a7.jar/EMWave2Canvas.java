import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class EMWave2Canvas extends Canvas
{
    EMWave2Frame pg;
    
    EMWave2Canvas(final EMWave2Frame pg) {
        this.pg = pg;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(300, 400);
    }
    
    public void update(final Graphics graphics) {
        this.pg.updateEMWave2(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.pg.updateEMWave2(graphics);
    }
}
