import java.applet.Applet;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class BitmapNode extends BitmapSprite
{
    protected Image[] imgArray;
    protected int imgPosition;
    protected int imgStatus;
    
    public void paint(final Graphics g) {
        if (super.visible) {
            g.drawImage(this.imgArray[this.imgStatus], super.locx, super.locy, super.applet);
        }
    }
    
    public boolean inside(final int x, final int y) {
        return x >= super.locx && y >= super.locy && x <= super.locx + super.width && y <= super.locy + super.height;
    }
    
    public int getStatus() {
        return this.imgStatus;
    }
    
    public int getPosition() {
        return this.imgPosition;
    }
    
    public void setStatus(final int s) {
        this.imgStatus = s;
    }
    
    public void setPosition(final int p) {
        this.imgPosition = p;
    }
    
    public BitmapNode(final int x, final int y, final Image[] iArray, final Applet a) {
        super(x, y, null, a);
        this.imgArray = iArray;
    }
}
