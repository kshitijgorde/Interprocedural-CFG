import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class Water extends Applet
{
    int xPos;
    int yPos;
    int width;
    int height;
    int[] pixels;
    double nWaves;
    double percent;
    double offset;
    double offinc;
    boolean filter;
    int aBright;
    int rBright;
    int bBright;
    int gBright;
    double pie;
    double[] cos;
    
    Water(final int xPos, final int yPos, final int width, final int height, final double nWaves, final double percent, final double offinc, final boolean filter, final int aBright, final int rBright, final int gBright, final int bBright) {
        this.pie = 3.14159265359;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.nWaves = nWaves;
        this.percent = percent;
        this.offinc = offinc;
        this.filter = filter;
        this.aBright = aBright;
        this.rBright = rBright;
        this.gBright = gBright;
        this.bBright = bBright;
        this.cos = new double[360];
        this.pixels = new int[width * height];
        this.offset = 0.0;
        for (int i = 0; i < 360; ++i) {
            this.cos[i] = Math.cos(i * (this.pie / 180.0));
        }
    }
    
    void draw(final Graphics graphics, final Image image) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, this.xPos, this.yPos - this.height - 2, this.width, this.height, this.pixels, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        final int[] pixels = this.pixels;
        final int n = this.width * this.height;
        this.pixels = new int[n];
        double offset = this.offset;
        final double n2 = 360.0 * this.nWaves / this.height;
        final double n3 = this.percent * this.height / 2.0 / 100.0;
        int n4 = 0;
        for (int i = 0; i < this.width * this.height; i += this.width) {
            final int n5 = Math.max(Math.min((int)(this.cos[(int)(offset % 360.0)] * n3 + n4), this.height - 1), 0) * this.width;
            ++n4;
            System.arraycopy(pixels, n5, this.pixels, n - this.width - i, this.width);
            offset += n2;
        }
        if (this.filter) {
            for (int j = 0; j < n; ++j) {
                final int n6 = this.pixels[j];
                this.pixels[j] = (Math.min(255, ((n6 & 0xFF000000) >> 24) * this.aBright / 100) << 24) + (Math.min(255, ((n6 & 0xFF0000) >> 16) * this.rBright / 100) << 16) + (Math.min(255, ((n6 & 0xFF00) >> 8) * this.gBright / 100) << 8) + Math.min(255, (n6 & 0xFF) * this.bBright / 100);
            }
        }
        graphics.drawImage(this.createImage(new MemoryImageSource(this.width, this.height, this.pixels, 0, this.width)), this.xPos, this.yPos, this);
        this.offset += this.offinc;
    }
}
