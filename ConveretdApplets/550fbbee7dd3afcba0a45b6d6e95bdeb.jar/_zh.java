import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zh implements ImageObserver
{
    public String p;
    public String d;
    protected double p;
    protected double d;
    public int p;
    public int d;
    public int a;
    public int n;
    public int v;
    protected static PixScreen p;
    private _zs p;
    private int i;
    private int l;
    private double a;
    private double n;
    public int b;
    public int c;
    public int e;
    public int f;
    protected static PixelGrabber p;
    protected static int[] p;
    protected int[] d;
    protected int g;
    protected int h;
    protected int j;
    protected int k;
    protected int m;
    protected int o;
    protected int q;
    protected boolean p;
    
    public final boolean d() {
        if (this.p == null || _zh.p == null || this.d == null) {
            return false;
        }
        final int[] p = this.p.p();
        final int a = this.p.a();
        final int d = this.p.d();
        final int q = 24;
        final int n = 24;
        final int g = 0;
        final int h = 0;
        final int j = g + q;
        int k = h + n;
        int m = this.p() - q / 2;
        int o = this.d() - n / 2;
        int n2 = o + n;
        if (o < 0) {
            o = 0;
            n2 = o + n;
        }
        if (o >= d) {
            return true;
        }
        if (n2 >= d) {
            k -= n2 - d;
        }
        if (k < h || j < g) {
            return false;
        }
        if (_zh.p == null) {
            return false;
        }
        if (this.p) {
            for (int i = 0; i < k - h; ++i) {
                final int n3 = (o + i) * a;
                final int n4 = (h + i) * q + g;
                for (int l = g; l < j; ++l) {
                    if (m + l < 0) {
                        m += a;
                    }
                    if (m + l > a - 1) {
                        m -= a;
                    }
                    this.d[n4 + l] = p[n3 + l + m];
                }
            }
            this.g = g;
            this.h = h;
            this.j = j;
            this.k = k;
            this.m = m;
            this.o = o;
            this.q = q;
            this.p = false;
        }
        for (int n5 = 0; n5 < k - h; ++n5) {
            final int n6 = (o + n5) * a;
            final int n7 = (h + n5) * q + g + 576 * this.f;
            final int n8 = (h + n5) * q + g;
            for (int n9 = g; n9 < j; ++n9) {
                final long n10 = _zh.p[n8 + n9];
                if (n10 != -65281L) {
                    int n11 = _zh.p[n7 + n9];
                    if (_zh.p.aw == 0) {
                        if (n10 != -1L) {
                            final long n12 = (n10 & 0xFF00L) >> 8;
                            final long n13 = (n11 & 0xFF) * n12;
                            final long n14 = ((n11 & 0xFF00) >> 8) * n12;
                            final long n15 = ((n11 & 0xFF0000) >> 16) * n12;
                            final long n16 = 255L - n12;
                            final int n17 = this.d[n8 + n9];
                            n11 = (int)(((n17 & 0xFF) * n16 + n13 & 0xFF00L) >> 8 | (((n17 & 0xFF00) >> 8) * n16 + n14 & 0xFF00L) | (((n17 & 0xFF0000) >> 16) * n16 + n15 & 0xFF00L) << 8 | 0xFFFFFFFFFF000000L);
                        }
                    }
                    else {
                        final int n18 = this.d[n8 + n9];
                        long n19 = (n18 & 0xFF) + (n11 & 0xFF);
                        long n20 = ((n18 & 0xFF00) >> 8) + ((n11 & 0xFF00) >> 8) / 2;
                        long n21 = ((n18 & 0xFF0000) >> 16) + ((n11 & 0xFF0000) >> 16) / 2;
                        if (n19 > 255L) {
                            n19 = 255L;
                        }
                        if (n20 > 255L) {
                            n20 = 255L;
                        }
                        if (n21 > 255L) {
                            n21 = 255L;
                        }
                        n11 = (int)(n19 | n20 << 8 | n21 << 16 | 0xFFFFFFFFFF000000L);
                    }
                    if (m + n9 < 0) {
                        m += a;
                    }
                    if (m + n9 > a - 1) {
                        m -= a;
                    }
                    p[n6 + m + n9] = n11;
                }
            }
        }
        return true;
    }
    
    public final boolean p(int n, final int n2) {
        boolean b = n > this.p && n < this.d && n2 < this.n && n2 > this.a;
        if (!b) {
            if (n > this.i - 24) {
                n -= this.i;
                if (n > this.p && n < this.d && n2 < this.n && n2 > this.a) {
                    b = true;
                }
            }
            if (n < 24) {
                n += this.i;
                if (n > this.p && n < this.d && n2 < this.n && n2 > this.a) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    public final int d() {
        int n = (int)(this.l / 2 - Math.tan(this.p * 3.14159265359 / 180.0) * this.n);
        if (n >= this.l) {
            n = this.l - 1;
        }
        return n;
    }
    
    public final void a() {
    }
    
    public final void v() {
        if (_zh.p != null) {
            _zh.p.a(this.d);
        }
    }
    
    public _zh(final String p9, final String d, final _zs p10, final PixScreen p11, final double d2, final double p12, final int n, final int c, final int e) {
        this.p = 0.0;
        this.d = 0.0;
        this.p = true;
        this.p = p9;
        this.d = d;
        this.p = p10;
        this.b = n;
        this.c = c;
        this.e = e;
        this.f = n;
        _zh.p = p11;
        this.v = 30;
        this.d = new int[576];
        this.d = d2;
        this.p = p12;
        this.d();
    }
    
    public final void p(final int n) {
        boolean b = false;
        final int v = this.v;
        Label_0191: {
            switch (this.v) {
                case 30: {
                    switch (n) {
                        case 20: {
                            this.v();
                            this.v = 20;
                            break Label_0191;
                        }
                    }
                    break;
                }
                case 10: {
                    switch (n) {
                        case 10: {
                            b = true;
                            this.v = 20;
                            break Label_0191;
                        }
                        case 30: {
                            this.i();
                            this.v = 30;
                            break Label_0191;
                        }
                    }
                    break;
                }
                case 20: {
                    switch (n) {
                        case 0: {
                            this.a();
                            this.v = 10;
                            break Label_0191;
                        }
                        case 30: {
                            this.i();
                            this.v = 30;
                            break Label_0191;
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("currentState not defined!");
                    return;
                }
            }
        }
        if (v != this.v) {
            if (this.v == 10) {
                this.f = this.c;
            }
            else {
                this.f = this.b;
            }
            if (this.v == 30 && !_zh.p.as) {
                this.p();
            }
            else {
                this.d();
            }
        }
        if (b) {
            this.n();
        }
    }
    
    public final void l() {
        final int p = this.p() - 12;
        final int a = this.d() - 12;
        this.p = p;
        this.d = p + 24;
        this.n = a + 24;
        this.a = a;
    }
    
    protected static final void p() {
        if (_zh.p == null) {
            final Image p = _zh.p.p("hs.gif", -3, -85, 11);
            final MediaTracker mediaTracker = new MediaTracker(_zh.p);
            mediaTracker.addImage(p, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
            _zh.p = new PixelGrabber(p, 0, 0, -1, -1, true);
            try {
                _zh.p.grabPixels();
            }
            catch (InterruptedException ex2) {
                System.out.println("error: grabpixel");
                return;
            }
            _zh.p = (int[])_zh.p.getPixels();
            if (_zh.p == null) {
                System.out.println("Loading hotspot failed");
            }
        }
    }
    
    public final void d() {
        this.i = this.p.a();
        this.l = this.p.d();
        if (this.l < 1) {
            return;
        }
        this.a = this.i / (this.p.p / 3.14159265359 * 180.0);
        this.n = this.p.i;
        if (_zh.p == null) {
            p();
        }
        this.l();
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
    
    public final int p() {
        return (int)(this.d * this.a + 0.5);
    }
    
    public final boolean p() {
        if (this.p == null) {
            return false;
        }
        final int a = this.p.a();
        final int[] p = this.p.p();
        for (int i = 0; i < this.k - this.h; ++i) {
            final int n = (this.o + i) * a;
            final int n2 = (this.h + i) * this.q + this.g;
            for (int j = this.g; j < this.j; ++j) {
                if (this.m + j < 0) {
                    this.m += a;
                }
                if (this.m + j > a - 1) {
                    this.m -= a;
                }
                p[n + this.m + j] = this.d[n2 + j];
            }
        }
        return this.p = true;
    }
    
    public final void i() {
        if (_zh.p != null) {
            _zh.p.a(null);
        }
    }
    
    public void n() {
    }
}
