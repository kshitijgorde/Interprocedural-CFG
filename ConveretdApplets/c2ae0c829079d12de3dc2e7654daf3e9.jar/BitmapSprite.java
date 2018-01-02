import java.awt.Graphics;
import java.awt.image.ImageObserver;
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
    Image image;
    Applet applet;
    
    public BitmapSprite(final Image image, final Applet applet) {
        this.locx = 0;
        this.locy = 0;
        this.image = image;
        this.applet = applet;
        if (this.image != null) {
            this.width = this.image.getWidth(applet);
            this.height = this.image.getHeight(applet);
        }
        this.restore();
    }
    
    public BitmapSprite(final int locx, final int locy, final Image image, final Applet applet) {
        this.locx = locx;
        this.locy = locy;
        this.image = image;
        this.applet = applet;
        if (this.image != null) {
            this.width = this.image.getWidth(applet);
            this.height = this.image.getHeight(applet);
        }
        this.restore();
    }
    
    public void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public void update() {
    }
    
    public void paint(final Graphics graphics) {
        if (super.visible) {
            graphics.drawImage(this.image, this.locx, this.locy, this.applet);
        }
    }
}
