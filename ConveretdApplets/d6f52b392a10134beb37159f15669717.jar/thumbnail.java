import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class thumbnail extends Frame
{
    BufferedImage b;
    Insets ins;
    
    thumbnail(final BufferedImage b) {
        this.ins = this.getInsets();
        this.setTitle("Thumbnail");
        this.b = b;
    }
    
    void resetsize() {
        this.ins = this.getInsets();
        this.setSize(this.b.getWidth() + this.ins.left + this.ins.right, this.b.getHeight() + this.ins.top + this.ins.bottom);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.b, this.ins.left, this.ins.top, this);
    }
}
