// 
// Decompiled by Procyon v0.5.30
// 

package viewer.cards;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public class Card_ZoomCanvas extends Canvas
{
    private Image img;
    private double zoom;
    private String message;
    private Image offScreen;
    private Graphics g;
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.paintOffScreen(), 0, 0, this);
    }
    
    public void setMassage(final String message) {
        this.message = message;
        this.repaint();
    }
    
    public void zoom(final int n) {
        this.zoom = n / 100.0;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private Image paintOffScreen() {
        final int width = this.size().width;
        final int height = this.size().height;
        this.offScreen = this.createImage(width, height);
        this.g = this.offScreen.getGraphics();
        if (this.img != null) {
            final int n = (int)(this.img.getWidth(this) * this.zoom);
            final int n2 = (int)(this.img.getHeight(this) * this.zoom);
            this.g.drawImage(this.img, (width - n) / 2, (height - n2) / 2, n, n2, this);
        }
        else {
            this.g.setColor(Color.white);
            this.g.drawString(this.message, 30, 40);
        }
        return this.offScreen;
    }
    
    public Card_ZoomCanvas() {
        this.zoom = 1.0;
        this.message = "Please wait! Loading image files.";
    }
    
    public void setImage(final Image img) {
        this.img = img;
        this.zoom = 1.0;
    }
}
