import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tail
{
    Image img;
    int x;
    int y;
    int dir;
    
    public Tail(final int x, final int y, final Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.img, this.x, this.y, this.x + 12, this.y + 12, this.dir, 0, this.dir + 12, 12, null);
    }
    
    public synchronized void move(final int x, final int y, final int xBefore, final int yBefore) {
        this.x = x;
        this.y = y;
        if (x < xBefore) {
            this.dir = 24;
        }
        if (x > xBefore) {
            this.dir = 12;
        }
        if (y < yBefore) {
            this.dir = 48;
        }
        if (y > yBefore) {
            this.dir = 36;
        }
    }
    
    public int getDir() {
        return this.dir;
    }
}
