import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Container;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class custombutton extends Canvas
{
    Container pappy;
    Image image;
    Dimension minSize;
    int w;
    int h;
    int DOWN;
    int UP;
    int buttonstatus;
    
    public custombutton(final Image image, final Container pappy, final int w, final int h) {
        this.DOWN = 1;
        this.buttonstatus = this.UP;
        this.image = image;
        this.pappy = pappy;
        this.w = w;
        this.h = h;
        this.minSize = new Dimension(this.w, this.h);
    }
    
    public Dimension preferredSize() {
        return this.minSize;
    }
    
    public synchronized Dimension minimumSize() {
        return this.minSize;
    }
    
    public void setImage(final Image image) {
        this.image = image;
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.buttonstatus = this.DOWN;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.buttonstatus = this.UP;
        this.repaint();
        return false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.buttonstatus == this.UP) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(0, 0, this.size().width - 2, this.size().height - 2, true);
        }
        else {
            graphics.setColor(this.getBackground());
            graphics.fillRect(1, 1, this.size().width, this.size().height);
        }
        if (this.image != null) {
            graphics.drawImage(this.image, (this.size().width - this.image.getWidth(this)) / 2, (this.size().height - this.image.getHeight(this)) / 2, this);
        }
    }
}
