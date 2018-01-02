import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.image.ImageConsumer;

// 
// Decompiled by Procyon v0.5.30
// 

public class g implements ImageConsumer
{
    public ImageProducer a;
    public int b;
    public int c;
    public int d;
    public int e;
    public ColorModel f;
    public byte[] g;
    public int[] h;
    public int i;
    public int j;
    public boolean k;
    public int l;
    public final int m = 48;
    public final int n;
    
    public void setHints(final int n) {
    }
    
    private void b() {
        final int n = this.d * this.e;
        final int[] h = new int[n];
        if (this.g != null) {
            for (int i = 0; i < n; ++i) {
                h[i] = this.f.getRGB(this.g[i] & 0xFF);
            }
        }
        else if (this.h != null) {
            for (int j = 0; j < n; ++j) {
                h[j] = this.f.getRGB(this.h[j]);
            }
        }
        this.g = null;
        this.h = h;
        this.j = this.d;
        this.i = 0;
        this.f = ColorModel.getRGBdefault();
    }
    
    public synchronized int a() {
        return this.l;
    }
    
    public synchronized boolean a(final long n) throws InterruptedException {
        if ((this.l & this.n) != 0x0) {
            return (this.l & 0x30) != 0x0;
        }
        final long n2 = n + System.currentTimeMillis();
        if (!this.k) {
            this.k = true;
            this.l &= 0xFFFFFF7F;
            this.a.startProduction(this);
        }
        while (this.k) {
            if (n != 0L) {
                if (n2 - System.currentTimeMillis() <= 0L) {
                    break;
                }
            }
            this.wait(0L);
        }
        return (this.l & 0x30) != 0x0;
    }
    
    public g(final ImageProducer a, final int b, final int c, final int d, final int e, final int[] h, final int i, final int j) {
        this.n = 112;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.i = i;
        this.j = j;
        this.h = h;
        this.f = ColorModel.getRGBdefault();
    }
    
    public void setPixels(int n, int n2, int n3, int n4, final ColorModel f, final byte[] array, int n5, final int n6) {
        if (n2 < this.c) {
            final int n7 = this.c - n2;
            if (n7 >= n4) {
                return;
            }
            n5 += n6 * n7;
            n2 += n7;
            n4 -= n7;
        }
        if (n2 + n4 > this.c + this.e) {
            n4 = this.c + this.e - n2;
            if (n4 <= 0) {
                return;
            }
        }
        if (n < this.b) {
            final int n8 = this.b - n;
            if (n8 >= n3) {
                return;
            }
            n5 += n8;
            n += n8;
            n3 -= n8;
        }
        if (n + n3 > this.b + this.d) {
            n3 = this.b + this.d - n;
            if (n3 <= 0) {
                return;
            }
        }
        int n9 = this.i + (n2 - this.c) * this.j + (n - this.b);
        if (this.h == null) {
            if (this.g == null) {
                this.g = new byte[this.d * this.e];
                this.j = this.d;
                this.i = 0;
                this.f = f;
            }
            else if (this.f != f) {
                this.b();
            }
            if (this.g != null) {
                for (int i = n4; i > 0; --i) {
                    System.arraycopy(array, n5, this.g, n9, n3);
                    n5 += n6;
                    n9 += this.j;
                }
            }
        }
        if (this.h != null) {
            final int n10 = this.j - n3;
            final int n11 = n6 - n3;
            for (int j = n4; j > 0; --j) {
                for (int k = n3; k > 0; --k) {
                    this.h[n9++] = f.getRGB(array[n5++] & 0xFF);
                }
                n5 += n11;
                n9 += n10;
            }
        }
        this.l |= 0x8;
    }
    
    public void setPixels(int n, int n2, int n3, int n4, final ColorModel f, final int[] array, int n5, final int n6) {
        if (n2 < this.c) {
            final int n7 = this.c - n2;
            if (n7 >= n4) {
                return;
            }
            n5 += n6 * n7;
            n2 += n7;
            n4 -= n7;
        }
        if (n2 + n4 > this.c + this.e) {
            n4 = this.c + this.e - n2;
            if (n4 <= 0) {
                return;
            }
        }
        if (n < this.b) {
            final int n8 = this.b - n;
            if (n8 >= n3) {
                return;
            }
            n5 += n8;
            n += n8;
            n3 -= n8;
        }
        if (n + n3 > this.b + this.d) {
            n3 = this.b + this.d - n;
            if (n3 <= 0) {
                return;
            }
        }
        if (this.h == null) {
            if (this.g == null) {
                this.h = new int[this.d * this.e];
                this.j = this.d;
                this.i = 0;
                this.f = f;
            }
            else {
                this.b();
            }
        }
        int n9 = this.i + (n2 - this.c) * this.j + (n - this.b);
        if (this.f == f) {
            for (int i = n4; i > 0; --i) {
                System.arraycopy(array, n5, this.h, n9, n3);
                n5 += n6;
                n9 += this.j;
            }
        }
        else {
            if (this.f != ColorModel.getRGBdefault()) {
                this.b();
            }
            final int n10 = this.j - n3;
            final int n11 = n6 - n3;
            for (int j = n4; j > 0; --j) {
                for (int k = n3; k > 0; --k) {
                    this.h[n9++] = f.getRGB(array[n5++]);
                }
                n5 += n11;
                n9 += n10;
            }
        }
        this.l |= 0x8;
    }
    
    public void setDimensions(final int n, final int n2) {
        if (this.d < 0) {
            this.d = n - this.b;
        }
        if (this.e < 0) {
            this.e = n2 - this.c;
        }
        if (this.d <= 0 || this.e <= 0) {
            this.imageComplete(3);
        }
        else if (this.h == null && this.f == ColorModel.getRGBdefault()) {
            this.h = new int[this.d * this.e];
            this.j = this.d;
            this.i = 0;
        }
        this.l |= 0x3;
    }
    
    public void setProperties(final Hashtable hashtable) {
    }
    
    public synchronized void imageComplete(final int n) {
        this.k = false;
        switch (n) {
            default: {
                this.l |= 0xC0;
                break;
            }
            case 4: {
                this.l |= 0x80;
                break;
            }
            case 3: {
                this.l |= 0x20;
                break;
            }
            case 2: {
                this.l |= 0x10;
                break;
            }
        }
        this.a.removeConsumer(this);
        this.notifyAll();
    }
    
    public void setColorModel(final ColorModel colorModel) {
    }
}
