import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class GunSprite extends BitmapSprite implements Moveable, Intersect
{
    protected int vx;
    protected int vy;
    SpacedInvaders si;
    
    public GunSprite(final Image image, final SpacedInvaders si) {
        super(image, (Applet)si);
        this.si = si;
    }
    
    public void setPosition(final int locx, final int locy) {
        super.locx = locx;
        super.locy = locy;
    }
    
    public void setVelocity(final int n, final int n2) {
    }
    
    public void updatePosition() {
    }
    
    public void hit() {
        ((Sprite)this).suspend();
        this.si.shipIsDead();
    }
    
    public boolean intersect(final int n, final int n2, final int n3, final int n4) {
        return ((Sprite)this).visible && n3 >= super.locx && super.locx + super.width >= n && n4 >= super.locy + 10 && super.locy + (super.height + 10) >= n2;
    }
}
