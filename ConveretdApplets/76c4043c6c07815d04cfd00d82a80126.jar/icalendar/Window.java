// 
// Decompiled by Procyon v0.5.30
// 

package icalendar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

public class Window extends Canvas implements ImageObserver
{
    Image image_;
    
    public Window(final Image image_) {
        this.image_ = image_;
    }
    
    public Image getImage() {
        return this.image_;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.paint(this.getGraphics());
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.image_, 0, 0, this.getSize().width, this.getSize().height, this);
    }
}
