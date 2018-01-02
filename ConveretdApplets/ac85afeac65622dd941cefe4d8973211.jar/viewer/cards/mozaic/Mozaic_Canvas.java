// 
// Decompiled by Procyon v0.5.30
// 

package viewer.cards.mozaic;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import viewer.ImageViewerApplet;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

class Mozaic_Canvas extends Canvas
{
    private Image offScreen;
    private boolean loaded;
    private Object frame;
    private int width;
    private int height;
    private int n;
    
    public void invalidate() {
        super.invalidate();
        if (this.offScreen != null) {
            this.offScreen.flush();
            this.offScreen = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.frame == null) {
            this.frame = this.getParent();
            while (!(this.frame instanceof Frame)) {
                this.frame = ((Component)this.frame).getParent();
            }
        }
        this.offScreen = this.createImage(this.width, this.height);
        final Graphics graphics2 = this.offScreen.getGraphics();
        if (graphics2.drawImage(ImageViewerApplet.imageList[this.n], 0, 0, this) || this.loaded) {
            graphics.drawImage(ImageViewerApplet.imageList[this.n], 0, 0, this.width, this.height, null);
        }
        else {
            graphics.setColor(Color.black);
            graphics.setFont(new Font("Verdana", 0, 10));
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            graphics.drawString("Loading...", this.width / 2 - fontMetrics.stringWidth("Loading...") / 2, this.height / 2 + fontMetrics.getHeight() / 2 - fontMetrics.getMaxDescent());
        }
        graphics2.dispose();
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n == 32) {
            this.loaded = true;
            this.repaint();
            return false;
        }
        return true;
    }
    
    Mozaic_Canvas(final int n) {
        this.n = n;
        this.width = 75;
        this.height = 65;
        this.resize(this.width, this.height);
        this.setBackground(Color.white);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.frame != null) {
                    ((Frame)this.frame).setCursor(12);
                    break;
                }
                break;
            }
            case 505: {
                if (this.frame != null) {
                    ((Frame)this.frame).setCursor(0);
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void removeNotify() {
        super.removeNotify();
        if (this.offScreen != null) {
            this.offScreen.flush();
            this.offScreen = null;
        }
    }
}
