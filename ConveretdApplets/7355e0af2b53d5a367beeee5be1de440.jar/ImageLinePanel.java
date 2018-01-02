import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageLinePanel extends Panel
{
    Image im;
    Color \u00c7;
    int \u00c8;
    int \u00c9;
    
    ImageLinePanel(final Image im, final Color \u00e7, final int \u00e8, final int \u00e9) {
        this.im = im;
        this.\u00c7 = \u00e7;
        this.\u00c8 = \u00e8;
        this.\u00c9 = \u00e9;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.im, 0, 0, this);
        graphics.setColor(this.\u00c7);
        graphics.drawRect(0, 0, this.\u00c8, this.\u00c9);
    }
}
