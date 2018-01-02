import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class tovImage extends Canvas
{
    int width;
    int height;
    Image image;
    int[] data;
    
    tovImage(final Image image, final int width, final int height) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.data = new int[width * height];
        this.resize(width, height);
        this.grabPixels();
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Insets getInsets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public int red(final int n, final int n2) {
        return (this.data[n * this.width + n2] & 0xFF0000) >> 16;
    }
    
    public int green(final int n, final int n2) {
        return (this.data[n * this.width + n2] & 0xFF00) >> 8;
    }
    
    public int blue(final int n, final int n2) {
        return this.data[n * this.width + n2] & 0xFF;
    }
    
    public void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.image, 0, 0, this.width, this.height, this.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.image != null) {
            graphics.drawImage(this.image, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
