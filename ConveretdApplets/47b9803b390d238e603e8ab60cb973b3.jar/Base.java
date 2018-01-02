import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Base extends Thing
{
    private int W;
    private int H;
    
    Base(final MissileCommando parent, final Color color, final int x, final int y, final int w, final int h) {
        super.parent = parent;
        super.X = x;
        super.Y = y;
        super.color = color;
        this.W = w;
        this.H = h;
        this.start();
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        this.waitToDie();
    }
    
    public synchronized void waitToDie() {
        try {
            this.wait();
        }
        catch (InterruptedException ex) {}
    }
    
    public synchronized void timeToDie() {
        this.notify();
    }
    
    public boolean hit(final Missile missile) {
        return missile.X >= super.X && missile.X <= super.X + this.W && missile.Y >= super.Y && missile.Y <= super.Y + this.H;
    }
    
    public void explode() {
        this.timeToDie();
        this.stop();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(super.color);
        graphics.fillRect(super.X, super.Y, this.W, this.H);
    }
    
    public void erase(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.fillRect(super.X, super.Y, this.W, this.H);
    }
}
