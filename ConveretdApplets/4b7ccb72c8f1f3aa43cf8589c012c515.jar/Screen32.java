import java.awt.image.PixelGrabber;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Screen32
{
    public int[] a;
    public int[] b;
    public int c;
    public int d;
    public int e;
    public int f;
    private int g;
    
    public final int a() {
        return this.g;
    }
    
    public Screen32(final int c, final int d) {
        this.c = c;
        this.d = d;
        this.g = c * d;
        this.f = d >> 1;
        this.e = c >> 1;
        this.a = new int[this.g];
        this.b = new int[this.d];
        int n = 0;
        for (int i = 0; i < this.d; ++i) {
            this.b[i] = n;
            n += this.c;
        }
    }
    
    public final int b() {
        return this.d;
    }
    
    public final int[] c() {
        return this.a;
    }
    
    public final void a(final Image image) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.c, this.d, this.a, 0, this.c);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public final void a(final Screen32 screen32) {
        for (int i = 0; i < this.g; ++i) {
            this.a[i] = screen32.a[i];
        }
    }
    
    public final void a(final int n) {
        for (int i = 0; i < this.g; ++i) {
            this.a[i] = n;
        }
    }
    
    public final int d() {
        return this.c;
    }
}
