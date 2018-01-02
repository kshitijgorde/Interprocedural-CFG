import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ImagePanel extends Panel
{
    Image im;
    int \u013d;
    int \u013e;
    
    ImagePanel(final Image im, final int \u013e, final int \u013e2) {
        this.im = im;
        this.\u013d = \u013e;
        this.\u013e = \u013e2;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return super.mouseMove(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.im, 0, 0, this.\u013d, this.\u013e, this);
    }
}
