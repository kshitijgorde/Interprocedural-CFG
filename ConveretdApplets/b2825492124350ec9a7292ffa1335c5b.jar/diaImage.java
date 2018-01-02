import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.PixelGrabber;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class diaImage extends Canvas
{
    int width;
    int height;
    Image image;
    int[] data;
    
    diaImage(final Image image, final int width, final int height) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.data = new int[width * height];
        this.resize(width, height);
        this.grabPixels();
    }
    
    public int blue(final int n, final int n2) {
        return this.data[n * this.width + n2] & 0xFF;
    }
    
    public int get(final int n, final int n2) {
        return this.data[n * this.width + n2];
    }
    
    public Insets getInsets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.image, 0, 0, this.width, this.height, this.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public int green(final int n, final int n2) {
        return (this.data[n * this.width + n2] & 0xFF00) >> 8;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        if (this.image != null) {
            graphics.drawImage(this.image, 0, 0, this);
        }
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public int red(final int n, final int n2) {
        return (this.data[n * this.width + n2] & 0xFF0000) >> 16;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
