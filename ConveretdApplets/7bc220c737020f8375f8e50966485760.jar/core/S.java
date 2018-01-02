// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import Z.Z;

public final class S
{
    public final int a;
    public final int b;
    private final byte[] c;
    private final byte[] d;
    private final byte[] e;
    
    public S(final Z z) {
        this.a = z.a;
        this.b = z.b;
        final int length = z.c.length;
        this.c = new byte[length];
        this.d = new byte[length];
        this.e = new byte[length];
        for (int i = 0; i < length; ++i) {
            final O o = new O(z.c[i]);
            this.c[i] = o.a;
            this.d[i] = o.b;
            this.e[i] = o.c;
        }
    }
    
    public final void a(int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            final int n6 = 256 - n4;
            n = (n4 * n >> 8) + ((this.c[n5] & 0xFF) * n6 >> 8);
            this.c[n5] = (byte)n;
            n = (n4 * n2 >> 8) + ((this.d[n5] & 0xFF) * n6 >> 8);
            this.d[n5] = (byte)n;
            n = (n4 * n3 >> 8) + ((this.e[n5] & 0xFF) * n6 >> 8);
            this.e[n5] = (byte)n;
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
    }
    
    static final byte[] a(final S s) {
        return s.c;
    }
    
    static final byte[] b(final S s) {
        return s.d;
    }
    
    static final byte[] c(final S s) {
        return s.e;
    }
    
    public S() {
    }
    
    public static final int[] a(final Image image) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        return array;
    }
}
