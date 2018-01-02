import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class BitmapSprite extends Sprite
{
    protected int locx;
    protected int locy;
    protected int width;
    protected int height;
    protected Image image;
    protected Applet applet;
    
    public void paint(final Graphics g) {
        if (super.visible) {
            g.drawImage(this.image, this.locx, this.locy, this.applet);
        }
    }
    
    public void update() {
    }
    
    public void setSize(final int w, final int h) {
        this.width = w;
        this.height = h;
    }
    
    public BitmapSprite(final Image i, final Applet a) {
        this.locx = 0;
        this.locy = 0;
        this.image = i;
        this.applet = a;
        this.restore();
    }
    
    public BitmapSprite(final int x, final int y, final Image i, final Applet a) {
        this.locx = x;
        this.locy = y;
        this.image = i;
        this.applet = a;
        this.restore();
    }
}
