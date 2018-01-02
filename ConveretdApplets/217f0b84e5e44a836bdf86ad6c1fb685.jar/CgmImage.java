import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class CgmImage extends CgmPrimitive implements ImageObserver
{
    Image img;
    boolean rendered;
    boolean standalone;
    CgmViewApplet applet;
    double norm;
    
    CgmImage(final Image img, final CgmViewApplet applet) {
        this.img = null;
        this.rendered = true;
        this.standalone = true;
        this.norm = 1.0;
        this.img = img;
        super.Width = 1.0;
        super.Height = 1.0;
        this.applet = applet;
    }
    
    CgmImage(final Image img, final CgmViewApplet applet, final double x, final double y, final double n, final double n2) {
        this.img = null;
        this.rendered = true;
        this.standalone = true;
        this.norm = 1.0;
        super.x = x;
        super.y = y;
        super.Width = n - x;
        super.Height = Math.abs(y - n2);
        this.img = img;
        this.applet = applet;
        this.standalone = false;
    }
    
    final void draw(final Graphics graphics, final double n, final double n2, final boolean b) {
        final int width = this.img.getWidth(this);
        final int height = this.img.getHeight(this);
        this.norm = 1.0 / Math.max(width, height);
        if (this.standalone) {
            super.Width = width * this.norm;
            super.Height = height * this.norm;
        }
        final int width2 = this.applet.Width;
        final int height2 = this.applet.Height;
        final int canvasX = this.applet.canvasX;
        final int canvasY = this.applet.canvasY;
        final double n3 = this.applet.canvasWidth;
        final double n4 = this.applet.canvasHeight;
        final double n5 = super.Width * n;
        final double n6 = super.Height * n2;
        final double max = Math.max(0.0, (n3 - n5) / 2.0);
        final double max2 = Math.max(0.0, (n4 - n6) / 2.0);
        super.x = max * this.norm;
        super.y = max2 * this.norm;
        if (b) {
            final double max3 = Math.max(max, canvasX);
            final double max4 = Math.max(max2, canvasY);
            final double min = Math.min(n5, width2);
            final double min2 = Math.min(n6, height2);
            final double min3 = Math.min(this.applet.origX, Math.max(0.0, max3 - max));
            final double min4 = Math.min(this.applet.origY, Math.max(0.0, max4 - max2));
            final double n7 = (max3 - max - min3) / (n * this.norm);
            final double n8 = (max4 - max2 - min4) / (n2 * this.norm);
            final double min5 = Math.min(min / (n * this.norm), width - n7);
            final double min6 = Math.min(min2 / (n2 * this.norm), height - n8);
            final double n9 = max3 - min3;
            final double n10 = max4 - min4;
            graphics.drawImage(this.img, (int)n9, (int)n10, (int)(n9 + min), (int)(n10 + min2), (int)n7, (int)n8, (int)(n7 + min5), (int)(n8 + min6), this);
            while (!this.rendered) {
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    final boolean find(final double n, final double n2) {
        return super.visibility != 0 && n >= super.x && n2 >= super.y && n <= super.x + super.Width && n2 <= super.y + super.Height && this.testPixel(this.img, (int)((n - super.x) / this.norm), (int)((n2 - super.y) / this.norm));
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.rendered = ((n & 0x20) != 0x0);
        return (n & 0x60) == 0x0;
    }
    
    final void move(final double n, final double n2) {
    }
    
    final boolean testPixel(final Image image, final int n, final int n2) {
        final int[] array = { 0 };
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, 1, 1, array, 0, 1);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return false;
        }
        return (pixelGrabber.getStatus() & 0x80) == 0x0 && (array[0] >> 24 & 0xFF) > 127;
    }
}
