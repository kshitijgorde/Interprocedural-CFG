import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Explosion extends Thing
{
    private boolean paintme;
    private int S;
    public int size;
    private int scale;
    
    Explosion(final MissileCommando parent, final Color color, final int x, final int y, final int s) {
        this.paintme = false;
        super.parent = parent;
        super.X = x;
        super.Y = y;
        super.color = color;
        this.S = s;
        this.start();
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        this.scale = 10;
        this.size = 1;
        do {
            this.paintme = true;
            super.parent.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        } while (this.size <= this.S);
        this.scale = -10;
        do {
            this.paintme = true;
            super.parent.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex2) {}
        } while (this.size >= 0);
        this.paintme = true;
        super.parent.repaint();
    }
    
    public boolean hit(final Missile missile) {
        return missile.X >= super.X - this.size / 2 && missile.X <= super.X + this.size / 2 && missile.Y >= super.Y - this.size / 2 && missile.Y <= super.Y + this.size / 2;
    }
    
    public void explode() {
    }
    
    public void paint(final Graphics graphics) {
        if (this.paintme) {
            if (this.scale < 0) {
                graphics.setColor(Color.lightGray);
                graphics.fillOval(super.X - this.size / 2, super.Y - this.size / 2, this.size, this.size);
            }
            this.size += this.scale;
            graphics.setColor(super.color);
            graphics.fillOval(super.X - this.size / 2, super.Y - this.size / 2, this.size, this.size);
            this.paintme = false;
        }
    }
    
    public void erase(final Graphics graphics) {
    }
}
