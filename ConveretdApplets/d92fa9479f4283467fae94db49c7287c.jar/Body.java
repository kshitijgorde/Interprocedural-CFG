import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Body
{
    Image img;
    int x;
    int y;
    
    public Body(final int x, final int y, final Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.img, this.x, this.y, this.x + 12, this.y + 12, 0, 0, 12, 12, null);
    }
    
    public synchronized void move(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}
