import java.util.Vector;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.awt.image.IndexColorModel;

// 
// Decompiled by Procyon v0.5.30
// 

public class s
{
    private int n;
    private int[] o;
    private int[] p;
    private int q;
    private int r;
    private IndexColorModel s;
    private static String t = "\ude92\ude88";
    
    public s(final Image image, final int q, final int r, final IndexColorModel s) {
        this.s = s;
        this.q = q;
        this.r = r;
        this.o = new int[q * r];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, q, r, this.o, 0, q);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println(String.valueOf(this.getClass().getName()) + s.t + ex.getMessage());
        }
        for (int i = 0; i < this.o.length; ++i) {
            if ((this.o[i] & 0xFF) < 128) {
                this.o[i] = 15;
            }
            else {
                this.o[i] = 14;
            }
        }
        this.p = new int[this.o.length];
        System.arraycopy(this.o, 0, this.p, 0, this.o.length);
    }
    
    public void clear() {
        System.arraycopy(this.p, 0, this.o, 0, this.o.length);
    }
    
    public ImageProducer a() {
        return new MemoryImageSource(this.q, this.r, this.s, this.o, 0, this.q);
    }
    
    public void _(int n, final int n2) {
        final Vector vector = new Vector<int[]>();
        final Vector vector2 = new Vector<int[]>();
        int n3 = 0;
        int n4 = 0;
        final boolean b = n2 > 0;
        final boolean b2 = n2 < this.r - 1;
        int n5;
        for (n5 = n2 * this.q + n; n >= 0 && this.o[n5] != 15 && this.o[n5] != this.n; --n, --n5) {}
        ++n;
        ++n5;
        while (n < this.q && this.p[n5] != 15 && this.o[n5] != this.n) {
            this.o[n5] = this.n;
            if (b) {
                if (n3 != 0) {
                    if (this.p[n5 - this.q] == 15 || this.o[n5 - this.q] == this.n) {
                        n3 = 0;
                    }
                }
                else if (this.p[n5 - this.q] != 15 && this.o[n5 - this.q] != this.n) {
                    vector.addElement(new int[] { n });
                    n3 = 1;
                }
            }
            if (b2) {
                if (n4 != 0) {
                    if (this.p[n5 + this.q] == 15 || this.o[n5 + this.q] == this.n) {
                        n4 = 0;
                    }
                }
                else if (this.p[n5 + this.q] != 15 && this.o[n5 + this.q] != this.n) {
                    vector2.addElement(new int[] { n });
                    n4 = 1;
                }
            }
            ++n5;
            ++n;
        }
        for (int size = vector.size(), i = 0; i < size; ++i) {
            this._(vector.elementAt(i)[0], n2 - 1);
        }
        for (int size2 = vector2.size(), j = 0; j < size2; ++j) {
            this._(vector2.elementAt(j)[0], n2 + 1);
        }
    }
    
    public void a(final int n) {
        this.n = n;
    }
    
    public ImageProducer b(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = (int)Math.sqrt((n3 - n) * (n3 - n) + (n4 - n2) * (n4 - n2));
        if (n6 == 0) {
            ++n6;
        }
        int n7 = n3 << 8;
        int n8 = n4 << 8;
        final int n9 = n5 / 2 - ((n5 < 10) ? 1 : 2);
        final int n10 = n9 * ((n << 8) - n7) / n6;
        final int n11 = n9 * ((n2 << 8) - n8) / n6;
        this.b(n7 >> 8, n8 >> 8, n5);
        while (Math.abs((n7 >> 8) - n) > n9 || Math.abs((n8 >> 8) - n2) > n9) {
            this.b(n7 >> 8, n8 >> 8, n5);
            n7 += n10;
            n8 += n11;
        }
        return this.a();
    }
    
    private void b(final int n, final int n2, final int n3) {
        int i = 0;
        int n4 = 0;
        while (i < n3) {
            for (int j = 0; j < n3; ++j, ++n4) {
                if (!this.b(n4, n3)) {
                    final int n5 = (n2 + i) * this.q + (n + j);
                    if (this._(n5, n, j) && this.o[n5] != 15) {
                        this.o[n5] = this.n;
                    }
                }
            }
            ++i;
        }
    }
    
    private boolean _(final int n, final int n2, final int n3) {
        return n >= 0 && n < this.o.length && n2 + n3 < this.q && n2 + n3 >= 0;
    }
    
    private boolean b(final int n, final int n2) {
        final int n3 = n2 * n2;
        if (n2 < 10) {
            return n == 0 || n == n2 - 1 || n == n3 - n2 || n == n3 - 1;
        }
        final int n4 = n2 - 1;
        return n == 0 || n == 1 || n == n4 || n == n4 - 1 || n == n2 || n == n2 + n4 || n == n3 - 2 * n2 || n == n3 - (n2 + 1) || n == n3 - n2 || n == n3 - n4 || n == n3 - 2 || n == n3 - 1;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFDEA8);
        }
        return new String(array);
    }
    
    static {
        s.t = _(s.t);
    }
}
