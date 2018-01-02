import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Image;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class Method0 extends TriangleCreator implements ImageObserver
{
    Kaleidoscope applet;
    KaleidoscopeControl control;
    int x;
    int y;
    int ox;
    int oy;
    int iw;
    int ih;
    int tw;
    int th;
    Image triangleImage;
    Image image;
    int step;
    int interval;
    int counter;
    volatile boolean imageReadError;
    
    Method0(final KaleidoscopeControl control, final Kaleidoscope applet) {
        this.applet = applet;
        this.control = control;
        this.init();
    }
    
    synchronized void init() {
        this.tw = this.control.getTriangleSize();
        this.th = (int)(Math.sqrt(3.0) * this.tw / 2.0);
        try {
            this.image = this.applet.getImage(this.control.getImageURL());
        }
        catch (Exception ex) {
            this.applet.imageLoadError();
            return;
        }
        if (this.image == null) {
            this.applet.imageLoadError();
            return;
        }
        this.imageReadError = false;
        while (true) {
            final int width = this.image.getWidth(this);
            this.iw = width;
            if (width >= 0 || this.imageReadError) {
                break;
            }
            try {
                this.wait();
            }
            catch (InterruptedException ex2) {}
        }
        if (this.imageReadError) {
            this.applet.imageLoadError();
            return;
        }
        this.ih = this.image.getHeight(this);
        if (this.ih < this.th - 10 || this.iw < this.tw - 10) {
            this.applet.imageProcessError();
            return;
        }
        this.x = (int)(((Math.random() - 0.5) * 0.6 + 0.5) * (this.iw - this.tw));
        this.y = (int)(((Math.random() - 0.5) * 0.6 + 0.5) * (this.ih - this.th));
        this.interval = 10;
        this.counter = 0;
        this.step = 5;
        do {
            this.ox = (int)((Math.random() - 0.5) * 2.0 * this.step);
            this.oy = (int)((Math.random() - 0.5) * 2.0 * this.step);
        } while (this.ox == 0 && this.oy == 0);
    }
    
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x40) != 0x0) {
            this.imageReadError = true;
        }
        this.notifyAll();
        return true;
    }
    
    public boolean next() {
        if (this.counter++ >= this.interval) {
            this.counter = 0;
            do {
                this.ox = (int)((Math.random() - 0.5) * 2.0 * this.step);
                this.oy = (int)((Math.random() - 0.5) * 2.0 * this.step);
            } while (this.ox == 0 && this.oy == 0);
        }
        this.x += this.ox;
        this.y += this.oy;
        if (this.x < 0 || this.x + this.tw >= this.iw - 1 || this.y < 0 || this.y + this.th >= this.ih - 1) {
            this.ox = -this.ox;
            this.oy = -this.oy;
            this.x += 2 * this.ox;
            this.y += 2 * this.oy;
        }
        this.triangleImage = this.applet.createImage(new FilteredImageSource(this.image.getSource(), new CropImageFilter(this.x, this.y, this.tw, this.th)));
        return this.triangleImage != null;
    }
    
    public Image getImage() {
        return this.triangleImage;
    }
    
    public int getSize() {
        return this.tw;
    }
}
