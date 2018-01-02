import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class Pic extends Applet
{
    int xPos;
    int yPos;
    Image pict;
    
    Pic(final int xPos, final int yPos, final Image pict) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.pict = pict;
    }
    
    void draw(final Graphics graphics) {
        graphics.drawImage(this.pict, this.xPos, this.yPos, this);
    }
}
