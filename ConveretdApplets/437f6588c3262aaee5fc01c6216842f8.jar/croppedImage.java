import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.PixelGrabber;

// 
// Decompiled by Procyon v0.5.30
// 

public class croppedImage
{
    int wid;
    int hgt;
    int pixels;
    int[] buf;
    PixelGrabber pg;
    
    public croppedImage(final Image image) {
        image.flush();
        this.wid = image.getWidth(null);
        this.hgt = image.getHeight(null);
        this.pixels = this.wid * this.hgt;
        this.buf = new int[this.pixels + this.wid + 1];
        this.pg = new PixelGrabber(image, 0, 0, this.wid, this.hgt, this.buf, 0, this.wid);
        try {
            this.pg.grabPixels();
        }
        catch (Exception ex) {}
    }
    
    public void invertImage() {
        for (int i = 0; i < this.hgt; ++i) {
            for (int j = 0; j < this.wid / 2; ++j) {
                final int n = j + i * this.wid;
                final int n2 = this.wid - j + i * this.wid;
                final int n3 = this.buf[n];
                this.buf[n] = this.buf[n2];
                this.buf[n2] = n3;
            }
        }
    }
    
    public MemoryImageSource getCrop(final int n, final int n2, final int n3, final int n4) {
        return new MemoryImageSource(n3, n4, this.buf, this.wid * n2 + n, this.wid);
    }
}
