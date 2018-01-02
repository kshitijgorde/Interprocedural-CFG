import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class UFO extends BitmapLoop implements Intersect, Statics
{
    int min_x;
    int max_x;
    int max_y;
    Intersect[][] theShield;
    SpacedInvaders si;
    UFOMissile missile;
    double missileFreq;
    Image explosion;
    int hitcount;
    
    public UFO(final Image[] array, final Image explosion, final int max_x, final int max_y, final SpacedInvaders si) {
        super(0, 0, null, array, (Applet)si);
        this.max_x = max_x;
        this.min_x = 0;
        this.max_y = max_y;
        super.currentImage = 0;
        this.si = si;
        this.explosion = explosion;
        this.missile = new UFOMissile(2, 20, Color.red, 15, 199, 500);
        this.hitcount = 5;
    }
    
    public void hit() {
        ((Sprite)this).suspend();
        this.si.incScore(10);
        this.si.deadUFOCount();
    }
    
    public void update() {
        if (Math.random() > this.missileFreq && !((Sprite)this.missile).active && ((Sprite)this).active) {
            this.missile.init(super.locx, super.locy);
        }
        this.missile.update();
        if (((Sprite)this).active) {
            for (int i = 0; i < 80; ++i) {
                for (int j = 0; j < 10; ++j) {
                    if (this.theShield[j][i].intersect(super.locx, super.locy, super.locx + super.width, super.locy + super.height)) {
                        this.theShield[j][i].hit();
                        break;
                    }
                }
            }
        }
    }
    
    public void setFreq(final double missileFreq) {
        this.missileFreq = missileFreq;
    }
    
    public void setHitCount() {
        this.hitcount = 5;
    }
    
    public void setTarget(final Intersect[][] theShield) {
        this.theShield = theShield;
    }
    
    public void paint(final Graphics graphics) {
        if (((Sprite)this).visible) {
            graphics.drawImage(super.images[super.currentImage], super.locx, super.locy, super.applet);
        }
        else if (this.hitcount > 0) {
            graphics.drawImage(this.explosion, super.locx - 10, super.locy - 10, super.applet);
            --this.hitcount;
        }
    }
    
    public boolean intersect(final int n, final int n2, final int n3, final int n4) {
        return ((Sprite)this).visible && n3 >= super.locx && super.locx + super.width >= n && n4 >= super.locy && super.locy + super.height >= n2;
    }
}
