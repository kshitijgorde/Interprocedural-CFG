// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;

public final class ds
{
    private int[] a;
    private int a;
    private int b;
    
    public ds(final Image image) {
        this.a = image.getWidth(null);
        this.b = image.getHeight(null);
        this.a = new int[this.a * this.b];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.a, this.b, this.a, 0, this.a);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public ds(final int[] a, final int a2, final int b) {
        this.a = a2;
        this.b = b;
        this.a = a;
    }
    
    private ds(final int a, final int b) {
        this.a = a;
        this.b = b;
        this.a = new int[a * b];
    }
    
    public final ds a(final int n) {
        int n2 = 0;
        final ds ds = new ds(this.b, this.a);
        for (int i = 0; i < this.b; ++i) {
            int n3 = this.b - i - 1;
            for (int j = 0; j < this.a; ++j) {
                ds.a[n3] = this.a[n2];
                ++n2;
                n3 += this.b;
            }
        }
        return ds;
    }
    
    public final Image a() {
        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(this.a, this.b, this.a, 0, this.a));
    }
}
