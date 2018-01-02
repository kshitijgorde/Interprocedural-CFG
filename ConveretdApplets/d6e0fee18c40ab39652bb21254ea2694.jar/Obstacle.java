import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class Obstacle
{
    public Rectangle r;
    Graphics g;
    
    public void paint(final Graphics gr) {
        (this.g = gr).setColor(Color.lightGray);
        this.g.draw3DRect(this.r.x, this.r.y, this.r.width, this.r.height, true);
    }
    
    public Obstacle(final int x, final int y, final int w, final int h) {
        this.r = new Rectangle(x, y, w, h);
    }
}
