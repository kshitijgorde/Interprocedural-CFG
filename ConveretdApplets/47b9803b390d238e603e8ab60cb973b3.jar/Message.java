import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Message extends Thing
{
    private String message;
    private int blinks;
    private int delay;
    private int blinkCount;
    
    Message(final MissileCommando parent, final String message, final int x, final int y, final int blinks, final int delay) {
        super.parent = parent;
        this.message = message;
        super.X = x;
        super.Y = y;
        this.blinks = blinks;
        this.delay = delay;
        super.color = Color.black;
        this.start();
    }
    
    Message(final MissileCommando missileCommando, final String s, final int n, final int n2, final int n3) {
        this(missileCommando, s, n, n2, n3, 500);
    }
    
    Message(final MissileCommando missileCommando, final String s, final int n, final int n2) {
        this(missileCommando, s, n, n2, 3, 500);
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        this.blinkCount = 0;
        while (this.blinkCount < 2 * this.blinks) {
            super.parent.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            ++this.blinkCount;
        }
        super.parent.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.blinkCount % 2 == 0) {
            graphics.setColor(super.color);
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        graphics.drawString(this.message, super.X, super.Y);
    }
    
    public void erase(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.drawString(this.message, super.X, super.Y);
    }
    
    public boolean hit(final Missile missile) {
        return false;
    }
    
    public void explode() {
    }
}
