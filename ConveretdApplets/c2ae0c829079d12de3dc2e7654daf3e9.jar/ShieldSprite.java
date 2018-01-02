import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class ShieldSprite extends BitmapSprite implements Intersect
{
    int count;
    
    public ShieldSprite(final Image image, final Applet applet, final int locx, final int locy) {
        super(image, applet);
        super.locx = locx;
        super.locy = locy;
        this.count = 0;
    }
    
    public void hit() {
        ((Sprite)this).suspend();
    }
    
    public boolean intersect(final int n, final int n2, final int n3, final int n4) {
        return ((Sprite)this).visible && n3 >= super.locx && super.locx + super.width >= n && n4 >= super.locy && super.locy + super.height >= n2;
    }
}
