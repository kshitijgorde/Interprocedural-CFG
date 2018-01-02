import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class mimdesc2 extends Canvas
{
    int width;
    int height;
    Image image;
    Image imageNew;
    int[] data;
    int[] dataNew;
    
    mimdesc2(final Image image, final int width, final int height) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.data = new int[width * height];
        this.dataNew = new int[width * height];
        this.grabPixels();
        this.makeGrey();
    }
    
    public int blue(final int n, final int n2) {
        return this.data[n * this.width + n2] & 0xFF;
    }
    
    public int get(final int n, final int n2) {
        return this.data[n * this.width + n2];
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
    
    public void makeGrey() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                final int n = (61 * this.red(i, j) + 24 * this.green(i, j) + 15 * this.blue(i, j)) / 100;
                this.dataNew[i * this.width + j] = new Color(n, n, n).getRGB();
            }
        }
        this.imageNew = this.createImage(new MemoryImageSource(this.width, this.height, this.dataNew, 0, this.width));
    }
    
    public int red(final int n, final int n2) {
        return (this.data[n * this.width + n2] & 0xFF0000) >> 16;
    }
}
