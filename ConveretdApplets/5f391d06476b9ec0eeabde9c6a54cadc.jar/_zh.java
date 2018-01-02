import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
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
    public int i;
    protected static PixScreen p;
    public static _zo p;
    private _zy p;
    private int l;
    private int b;
    private double a;
    private double n;
    private double v;
    public int c;
    public int e;
    public int f;
    public int[] p;
    protected static PixelGrabber p;
    protected static int[] d;
    protected int[] a;
    protected int g;
    protected int h;
    protected int j;
    protected int k;
    protected int m;
    protected int o;
    protected int q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    protected int w;
    protected int x;
    protected boolean p;
    public Image p;
    protected int[] n;
    public int y;
    public int z;
    public int dp;
    public String a;
    public boolean d;
    protected boolean a;
    public int[] v;
    protected double i;
    public int dd;
    public static d p;
    
    public final boolean p() {
        if (this.p == null || _zh.p == null || this.a == null) {
            return false;
        }
        if (this.p.p == 5 || this.p.p == 4) {
            return true;
        }
        final int[] p = this.p.p();
        final int d = this.p.d();
        final int p2 = this.p.p();
        int r = 0;
        int s = 0;
        int t = 0;
        int u = 0;
        int w = 0;
        int x = 0;
        int n = 0;
        final int q = 24;
        final int n2 = 24;
        final int g = 0;
        final int h = 0;
        final int j = g + q;
        int k = h + n2;
        int m = this.d() - q / 2;
        int o = this.p() - n2 / 2;
        int n3 = o + n2;
        if (this.d) {
            r = 0;
            s = 0;
            t = r + this.y;
            u = s + this.z;
            w = m + q + 5;
            x = o;
            final int n4 = w + this.y;
            n = x + this.z;
        }
        if (o < 0) {
            o = 0;
            n3 = o + n2;
        }
        if (this.d && x < 0) {
            x = 0;
            n = x + this.z;
        }
        if (o >= p2) {
            return true;
        }
        if (x >= p2) {
            return true;
        }
        if (n3 >= p2) {
            k -= n3 - p2;
        }
        if (n >= p2) {
            u -= n - p2;
        }
        if (k < h || j < g) {
            return false;
        }
        if (_zh.d == null) {
            return false;
        }
        if (_zh.p.nx && _zh.p.nz == null) {
            return false;
        }
        if (this.p) {
            for (int i = 0; i < k - h; ++i) {
                final int n5 = (o + i) * d;
                final int n6 = (h + i) * q + g;
                for (int l = g; l < j; ++l) {
                    if (m + l < 0) {
                        m += d;
                    }
                    if (m + l > d - 1) {
                        m -= d;
                    }
                    this.a[n6 + l] = p[n5 + l + m];
                }
            }
            if (this.d) {
                for (int n7 = 0; n7 < this.z; ++n7) {
                    final int n8 = (x + n7) * d;
                    final int n9 = (s + n7) * this.y + r;
                    for (int n10 = r; n10 < t; ++n10) {
                        if (w + n10 < 0) {
                            w += d;
                        }
                        if (w + n10 > d - 1) {
                            w -= d;
                        }
                        this.n[n9 + n10] = p[n8 + n10 + w];
                    }
                }
                this.r = r;
                this.s = s;
                this.t = t;
                this.u = u;
                this.w = w;
                this.x = x;
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
        final int n11 = this.dd << 24;
        for (int n12 = 0; n12 < k - h; ++n12) {
            final int n13 = (o + n12) * d;
            final int n14 = (h + n12) * q + g + 576 * this.f;
            final int n15 = (h + n12) * q + g + 576 * this.p[this.f];
            final int n16 = (h + n12) * q + g;
            for (int n17 = g; n17 < j; ++n17) {
                long n18;
                if (_zh.p.nx) {
                    n18 = _zh.p.nz[n15 + n17];
                }
                else {
                    n18 = _zh.d[n15 + n17];
                }
                if (n18 != 0L) {
                    int n19;
                    if (_zh.p.nx) {
                        n19 = _zh.p.nz[n14 + n17];
                    }
                    else {
                        n19 = _zh.d[n14 + n17];
                    }
                    int n25;
                    if (_zh.p.ay == 0) {
                        if (n18 != 255L) {
                            final long n20 = (n19 & 0xFF) * n18;
                            final long n21 = ((n19 & 0xFF00) >> 8) * n18;
                            final long n22 = ((n19 & 0xFF0000) >> 16) * n18;
                            final long n23 = 255L - n18;
                            final int n24 = this.a[n16 + n17];
                            n25 = (int)(((n24 & 0xFF) * n23 + n20 & 0xFF00L) >> 8 | (((n24 & 0xFF00) >> 8) * n23 + n21 & 0xFF00L) | (((n24 & 0xFF0000) >> 16) * n23 + n22 & 0xFF00L) << 8 | n11);
                        }
                        else {
                            n25 = ((n19 & 0xFFFFFF) | n11);
                        }
                    }
                    else {
                        final int n26 = this.a[n16 + n17];
                        long n27 = (n26 & 0xFF) + (n19 & 0xFF) / 2;
                        long n28 = ((n26 & 0xFF00) >> 8) + ((n19 & 0xFF00) >> 8) / 2;
                        long n29 = ((n26 & 0xFF0000) >> 16) + ((n19 & 0xFF0000) >> 16) / 2;
                        if (n27 > 255L) {
                            n27 = 255L;
                        }
                        if (n28 > 255L) {
                            n28 = 255L;
                        }
                        if (n29 > 255L) {
                            n29 = 255L;
                        }
                        n25 = (int)(n27 | n28 << 8 | n29 << 16 | n11);
                    }
                    if (m + n17 < 0) {
                        m += d;
                    }
                    if (m + n17 > d - 1) {
                        m -= d;
                    }
                    p[n13 + m + n17] = n25;
                }
            }
        }
        if (this.d && this.a) {
            for (int n30 = 0; n30 < u - s; ++n30) {
                final int n31 = (x + n30) * d;
                final int n32 = (s + n30) * this.y + r;
                for (int n33 = r; n33 < t; ++n33) {
                    final int n34 = this.v[n32 + n33];
                    if (w + n33 < 0) {
                        w += d;
                    }
                    if (w + n33 > d - 1) {
                        w -= d;
                    }
                    if (_zh.p.ay == 0) {
                        p[n31 + w + n33] = n34;
                    }
                    else {
                        final int n35 = this.n[n32 + n33];
                        final double n36 = 0.4;
                        long n37 = (int)((n35 & 0xFF) * n36 + (n34 & 0xFF) * (1.0 - n36));
                        long n38 = (int)(((n35 & 0xFF00) >> 8) * n36 + ((n34 & 0xFF00) >> 8) * (1.0 - n36));
                        long n39 = (int)(((n35 & 0xFF0000) >> 16) * n36 + ((n34 & 0xFF0000) >> 16) * (1.0 - n36));
                        if (n37 > 255L) {
                            n37 = 255L;
                        }
                        if (n38 > 255L) {
                            n38 = 255L;
                        }
                        if (n39 > 255L) {
                            n39 = 255L;
                        }
                        p[n31 + m + n33] = (int)(n37 | n38 << 8 | n39 << 16 | n11);
                    }
                }
            }
        }
        if (!this.a) {
            this.a();
        }
        return true;
    }
    
    public final int p() {
        int n3;
        if (_zh.p.ao == 3) {
            final double n = this.p.p() / this.p.p.p;
            final int n2 = (int)((n + 0.5) / 2.0) - (int)(this.p * this.n + 0.5);
            this.v = (int)(this.d / this.i);
            n3 = n2 + (int)(n + 0.5) * this.v;
        }
        else {
            n3 = (int)(this.b / 2 - Math.tan(this.p * 3.141592653589793 / 180.0) * this.v);
            if (n3 >= this.b) {
                n3 = this.b - 1;
            }
        }
        return n3;
    }
    
    public final void i() {
    }
    
    public final void n() {
        if (_zh.p != null) {
            if (this.d && this.a) {
                _zh.p.d(null);
                return;
            }
            _zh.p.d(this.d);
        }
    }
    
    public final boolean a() {
        if (this.p == null) {
            return false;
        }
        final int d = this.p.d();
        final int[] p = this.p.p();
        if (this.d) {
            for (int i = 0; i < this.u - this.s; ++i) {
                final int n = (this.x + i) * d;
                final int n2 = (this.s + i) * this.y + this.r;
                for (int j = this.r; j < this.t; ++j) {
                    if (this.w + j < 0) {
                        this.w += d;
                    }
                    if (this.w + j > d - 1) {
                        this.w -= d;
                    }
                    p[n + this.w + j] = this.n[n2 + j];
                }
            }
        }
        return true;
    }
    
    public _zh(final String p10, final String d, final _zy p11, final PixScreen p12, final double d2, final double p13, final int n, final int e, final int n2, final int n3) {
        this.p = 0.0;
        this.d = 0.0;
        this.p = true;
        this.a = true;
        this.p = p10;
        this.d = d;
        this.p = p11;
        this.c = n;
        this.e = e;
        (this.p = new int[2])[0] = n2;
        this.p[1] = n3;
        this.f = n;
        _zh.p = p12;
        this.i = 30;
        this.a = new int[576];
        this.d = d2;
        this.p = p13;
        this.p = null;
        this.n = null;
        this.y = 0;
        this.z = 0;
        this.d = false;
        this.dp = 16;
        this.dd = p11.n++;
        if (this.p.p() && _zh.p == null) {
            _zh.p = new d();
            _zh.p.v = -5636096;
            _zh.p.i = 1;
        }
        this.c();
    }
    
    public final void p(final int n) {
        boolean b = false;
        final int i = this.i;
        Label_0211: {
            switch (this.i) {
                case 30: {
                    switch (n) {
                        case 20: {
                            this.n();
                            this.i = 20;
                            break Label_0211;
                        }
                    }
                    break;
                }
                case 10: {
                    switch (n) {
                        case 10: {
                            if (this.d) {
                                this.a = !this.a;
                            }
                            b = true;
                            this.i = 20;
                            this.n();
                            break Label_0211;
                        }
                        case 30: {
                            this.l();
                            this.i = 30;
                            break Label_0211;
                        }
                    }
                    break;
                }
                case 20: {
                    switch (n) {
                        case 0: {
                            this.i();
                            this.i = 10;
                            break Label_0211;
                        }
                        case 30: {
                            this.l();
                            this.i = 30;
                            break Label_0211;
                        }
                    }
                    break;
                }
                default: {
                    return;
                }
            }
        }
        if (i != this.i) {
            if (this.i == 10) {
                this.f = this.e;
            }
            else {
                this.f = this.c;
            }
            if (this.i == 30 && !_zh.p.au) {
                this.d();
            }
            else {
                this.d();
                this.p();
            }
        }
        if (b) {
            this.p();
        }
    }
    
    public final void d() {
        final int p = this.d() - 12;
        final int a = this.p() - 12;
        this.p = p;
        this.d = p + 24;
        this.n = a + 24;
        this.a = a;
    }
    
    protected static final void b() {
        if (_zh.p == null) {
            _zh.p = new _zo(_zh.p);
            final Image p = _zh.p.p("hs.gif", 76, 73, -62);
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
                return;
            }
            _zh.d = (int[])_zh.p.getPixels();
            if (_zh.d == null) {}
            int i = 0;
            while (i < (i = 1152) + 1152) {
                _zh.d[i] = (_zh.d[i] & 0xFF00) >> 8;
                ++i;
            }
        }
    }
    
    public final void c() {
        if (_zh.p.ao == 3) {
            this.i = this.p.p() / this.p.p.p;
            this.a = this.p.d() / this.i;
            this.n = this.p.p() / this.p.p.p / 180.0;
        }
        else {
            this.l = this.p.d();
            this.b = this.p.p();
            if (this.b < 1) {
                return;
            }
            final double n = this.p.p() / 180.0 * 3.141592653589793;
            this.a = this.l / (n / 3.141592653589793 * 180.0);
            this.v = this.l / n;
        }
        if (_zh.p == null) {
            b();
        }
        if (this.d) {
            this.a();
            return;
        }
        this.d();
    }
    
    public final void p(final String a, final int dp) {
        this.a = a;
        this.dp = dp;
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
    
    public final int d() {
        return (int)(this.d * this.a + 0.5);
    }
    
    public final void p(final double n, final double n2, final double n3, final double n4, final int[] array, final int n5, final int n6, final boolean b, final boolean b2) {
        this.v();
        if (this.d && this.a && this.v != null) {
            _zh.p.l = 0;
            _zh.p.i = 0;
            _zh.p.d(this.y / 24.0, this.z / 24.0, 1.0);
            _zh.p.p(_zh.p.p[2].p + 0.07, 0.0, 0.0);
            _zh.p.p(this.d, this.p);
            _zh.p.p(n, n2, n3, n4, this.v, this.y, this.z, array, n5, n6, null, b, 0, false);
            this.v();
        }
        if (this.p.p() && _zh.p != null) {
            _zh.p.i = this.f;
            _zh.p.l = this.p[this.f];
            _zh.p.p(this.d, this.p);
            _zh.p.p(n, n2, n3, n4, _zh.p.nx ? _zh.p.nz : _zh.d, 24, 24, array, n5, n6, _zh.p.nx ? _zh.p.nz : _zh.d, b, this.dd, b2);
        }
    }
    
    public final void v() {
        _zh.p.p[0].p(-0.05, -0.05, 1.0, 0.0, 1.0);
        _zh.p.p[1].p(-0.05, 0.05, 1.0, 0.0, 0.0);
        _zh.p.p[2].p(0.05, 0.05, 1.0, 1.0, 0.0);
        _zh.p.p[3].p(0.05, -0.05, 1.0, 1.0, 1.0);
    }
    
    public final void a() {
        final int n = this.y / 2;
        final int n2 = this.z / 2;
        final int p = this.d() - n;
        final int a = this.p() - n2;
        this.p = p;
        this.d = p + this.y;
        this.n = a + this.z;
        this.a = a;
    }
    
    public final boolean d() {
        if (this.p == null) {
            return false;
        }
        if (this.p.p == 5 || this.p.p == 4) {
            return true;
        }
        final int d = this.p.d();
        final int[] p = this.p.p();
        for (int i = 0; i < this.k - this.h; ++i) {
            final int n = (this.o + i) * d;
            final int n2 = (this.h + i) * this.q + this.g;
            for (int j = this.g; j < this.j; ++j) {
                if (this.m + j < 0) {
                    this.m += d;
                }
                if (this.m + j > d - 1) {
                    this.m -= d;
                }
                p[n + this.m + j] = ((this.a[n2 + j] & 0xFFFFFF) | (p[n + this.m + j] & 0xFF000000));
            }
        }
        if (this.d) {
            this.a();
        }
        return this.p = true;
    }
    
    public final void l() {
        if (_zh.p != null) {
            _zh.p.d(null);
        }
    }
    
    public void p() {
    }
    
    public final void e() {
        final int n2;
        final int n = n2 = 6;
        final Font font = _zh.p.getFont();
        final Font font2 = new Font(font.getName(), font.getStyle(), this.dp);
        this.y = Toolkit.getDefaultToolkit().getFontMetrics(font2).stringWidth(this.a) + n;
        this.z = this.dp + n2;
        final Image image = _zh.p.createImage(this.y, this.z);
        final Graphics graphics = image.getGraphics();
        graphics.setFont(new Font(font2.getName(), font2.getStyle(), font2.getSize()));
        graphics.setColor(Color.yellow);
        graphics.fillRect(0, 0, this.y, this.z);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.y - 1, this.z - 1);
        graphics.drawString(this.a, n / 2, this.z - n2 + 1);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.y, this.z, true);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.out.println("Error in grabbing label pixels ");
            return;
        }
        this.v = (int[])pixelGrabber.getPixels();
        if (this.v == null) {}
        this.n = new int[this.y * this.z];
    }
}
