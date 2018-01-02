import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Target extends Thread
{
    static Image duck_left;
    static Image duck_right;
    static Image[] duckFallLeft;
    static Image[] duckFallRight;
    static Graphics g;
    static int masterKey;
    int x;
    int y;
    int key;
    String s;
    static int delay;
    static int step;
    boolean dir;
    boolean alive;
    
    Target(final int y, final boolean dir) {
        this.alive = true;
        if (dir) {
            this.x = -45;
        }
        else {
            this.x = 299;
        }
        this.y = y;
        this.dir = dir;
        this.key = Target.masterKey;
        ShootingGallery.targetHashtable.put(new Integer(this.key).toString(), this);
        ShootingGallery.keyHashtable.put(new Integer(this.key), new Integer(this.key));
        ++Target.masterKey;
    }
    
    public void run() {
        while (this.alive) {
            if (this.dir) {
                while (this.x < 300) {
                    Target.g.drawImage(Target.duck_right, this.x, this.y, new ShootingGallery());
                    try {
                        Thread.sleep(Target.delay);
                    }
                    catch (Exception ex) {}
                    Target.g.setColor(Color.white);
                    Target.g.fillRect(this.x, this.y, Target.step, 39);
                    this.x += Target.step;
                }
            }
            else {
                while (this.x > -45) {
                    Target.g.drawImage(Target.duck_left, this.x, this.y, new ShootingGallery());
                    try {
                        Thread.sleep(Target.delay);
                    }
                    catch (Exception ex2) {}
                    Target.g.setColor(Color.white);
                    Target.g.fillRect(this.x + 45, this.y, Target.step, 39);
                    this.x -= Target.step;
                }
            }
            this.dir = !this.dir;
        }
    }
    
    public void fall() {
        this.alive = false;
        this.stop();
        ShootingGallery.targetHashtable.remove(new Integer(this.key).toString());
        ShootingGallery.keyHashtable.remove(new Integer(this.key));
        ShootingGallery.score += 100;
        if (!this.dir) {
            for (int i = 0; i < 4; ++i) {
                try {
                    Thread.sleep(200L);
                }
                catch (Exception ex) {}
                Target.g.drawImage(Target.duckFallLeft[i], this.x, this.y, new ShootingGallery());
            }
        }
        else {
            for (int j = 0; j < 4; ++j) {
                try {
                    Thread.sleep(200L);
                }
                catch (Exception ex2) {}
                Target.g.drawImage(Target.duckFallRight[j], this.x, this.y, new ShootingGallery());
            }
        }
        Target.g.setColor(Color.white);
        Target.g.fillRect(this.x, this.y, 47, 39);
    }
    
    static {
        Target.duckFallLeft = new Image[4];
        Target.duckFallRight = new Image[4];
        Target.delay = 15;
        Target.step = 1;
    }
}
