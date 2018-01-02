import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class MysterySprite extends BitmapSprite implements Intersect
{
    int locx;
    int locy;
    Image image;
    SpacedInvaders si;
    int hitCount;
    Image explosion2;
    
    public MysterySprite(final Image image, final Image explosion2, final SpacedInvaders si) {
        super(image, (Applet)si);
        this.image = image;
        this.si = si;
        this.hitCount = 1;
        this.explosion2 = explosion2;
        ((Sprite)this).suspend();
    }
    
    public void setPosition(final int locx, final int locy) {
        this.locx = locx;
        this.locy = locy;
    }
    
    public void paint(final Graphics graphics) {
        if (((Sprite)this).visible) {
            graphics.drawImage(this.image, this.locx, this.locy, (ImageObserver)this.si);
        }
        else if (this.hitCount > 0) {
            graphics.drawImage(this.explosion2, this.locx, this.locy, (ImageObserver)this.si);
            --this.hitCount;
        }
    }
    
    public boolean intersect(final int n, final int n2, final int n3, final int n4) {
        return ((Sprite)this).visible && n3 >= this.locx && this.locx + super.width >= n && n4 >= this.locy + 10 && this.locy + (super.height + 10) >= n2;
    }
    
    public void hit() {
        ((Sprite)this).suspend();
        this.hitCount = 15;
        this.si.incScore(100);
    }
    
    public void reset() {
        if (!((Sprite)this).active && this.hitCount == 0) {
            ((Sprite)this).restore();
            this.locx = -60;
            this.hitCount = 15;
        }
    }
}
