import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bullet extends Thread
{
    int x;
    int y;
    static Color col;
    static int radius;
    static Graphics g;
    static int ammo;
    
    Bullet(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void run() {
        Bullet.g.setColor(Bullet.col);
        Bullet.g.fillOval(this.x - Bullet.radius, this.y - Bullet.radius, 2 * Bullet.radius, 2 * Bullet.radius);
        try {
            Thread.sleep(250L);
        }
        catch (Exception ex) {}
        Bullet.g.setColor(Color.white);
        Bullet.g.fillOval(this.x - Bullet.radius, this.y - Bullet.radius, 2 * Bullet.radius, 2 * Bullet.radius);
        this.stop();
    }
    
    static {
        Bullet.col = Color.magenta;
        Bullet.radius = 3;
    }
}
