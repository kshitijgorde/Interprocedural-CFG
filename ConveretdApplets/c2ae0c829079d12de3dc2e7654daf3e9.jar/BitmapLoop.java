import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class BitmapLoop extends BitmapSprite implements Moveable
{
    protected Image[] images;
    protected int currentImage;
    protected boolean foreground;
    protected boolean background;
    protected int vx;
    protected int vy;
    
    public BitmapLoop(final int n, final int n2, final Image image, final Image[] images, final Applet applet) {
        super(n, n2, image, applet);
        this.images = images;
        this.currentImage = 0;
        if (this.images.length == 0) {
            this.foreground = false;
        }
        else {
            this.foreground = true;
        }
        if (this.images == null || this.images.length == 0) {
            this.foreground = false;
        }
        else {
            this.foreground = true;
            if (!this.background) {
                super.width = this.images[0].getWidth(applet);
                super.height = this.images[0].getHeight(applet);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (((Sprite)this).visible && this.foreground) {
            graphics.drawImage(this.images[this.currentImage], super.locx, super.locy, super.applet);
        }
    }
    
    public void setPosition(final int locx, final int locy) {
        super.locx = locx;
        super.locy = locy;
    }
    
    public void setVelocity(final int vx, final int vy) {
        this.vx = vx;
        this.vy = vy;
    }
    
    public void updatePosition() {
        super.locx += this.vx;
        super.locy += this.vy;
        if (((Sprite)this).active && this.foreground) {
            this.currentImage = (this.currentImage + 1) % this.images.length;
        }
    }
}
