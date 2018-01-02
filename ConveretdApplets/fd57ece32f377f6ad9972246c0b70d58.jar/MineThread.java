import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class MineThread extends Thread
{
    mine m;
    Graphics g;
    int delay;
    
    public void init(final mine m, final Graphics g, final int delay) {
        this.m = m;
        this.g = g;
        this.delay = delay;
    }
    
    public void run() {
        try {
            Thread.sleep(this.delay);
        }
        catch (Exception ex) {}
        this.g.setColor(Color.black);
        this.g.fillOval(this.m.x, this.m.y, 25, 25);
    }
}
