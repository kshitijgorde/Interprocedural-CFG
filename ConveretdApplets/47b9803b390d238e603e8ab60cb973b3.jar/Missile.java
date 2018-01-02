import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Missile extends Thing
{
    private boolean paintme;
    private int X1;
    private int Y1;
    private int X2;
    private int Y2;
    private int V;
    private float m;
    private float b;
    private float x;
    private float y;
    private boolean replicate;
    
    Missile(final MissileCommando parent, final Color color, final int x1, final int y1, final int x2, final int y2, final int v) {
        this.paintme = false;
        this.replicate = false;
        super.parent = parent;
        super.X = this.X1;
        super.Y = this.Y1;
        super.color = color;
        this.X1 = x1;
        this.Y1 = y1;
        this.X2 = x2;
        this.Y2 = y2;
        this.V = v;
        this.start();
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        this.m = (this.Y2 - this.Y1) / (this.X2 - this.X1);
        this.b = this.Y1 - this.m * this.X1;
        this.x = this.X1;
        this.y = this.Y1;
        this.replicate = (Math.random() > 0.9);
        if (this.replicate) {
            super.color = Color.magenta;
        }
        while (this.y <= this.Y2) {
            if (this.replicate && this.y > this.Y1 + 5 * this.V && Math.random() > 0.75) {
                for (int i = 0; i < 3; ++i) {
                    super.parent.createMissile(super.X, super.Y, this.V);
                }
                break;
            }
            this.paintme = true;
            super.parent.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        this.paintme = true;
        super.parent.repaint();
    }
    
    public boolean hit(final Missile missile) {
        return missile.X == super.X && missile.Y == super.Y;
    }
    
    public void explode() {
        this.stop();
    }
    
    public void paint(final Graphics graphics) {
        if (this.paintme) {
            graphics.setColor(Color.lightGray);
            graphics.drawLine(this.X1, this.Y1, super.X, super.Y);
            this.y += this.V;
            this.x = (this.y - this.b) / this.m;
            super.X = (int)this.x;
            super.Y = (int)this.y;
            graphics.setColor(super.color);
            graphics.drawLine(this.X1, this.Y1, super.X, super.Y);
            this.paintme = false;
        }
    }
    
    public void erase(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.drawLine(this.X1, this.Y1, super.X, super.Y);
    }
}
