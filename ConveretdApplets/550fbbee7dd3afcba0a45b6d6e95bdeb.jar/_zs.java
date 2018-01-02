import java.util.Enumeration;
import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zs extends _zd
{
    public Vector p;
    public String p;
    public String d;
    public String a;
    public double p;
    public int p;
    public int d;
    public int a;
    public int n;
    public double d;
    private int v;
    private int i;
    private int l;
    private int b;
    public double a;
    public double n;
    public double v;
    
    public final synchronized Point p(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n > super.d - 1 || n2 > super.a - 1 || super.n == null || super.a == null) {
            return null;
        }
        final int n3 = super.n[n * 2] + super.b;
        final int n4 = super.n[n * 2 + 1];
        final int n5 = super.a[n * 2] + super.b;
        final int n6 = super.a[n * 2 + 1];
        final int n7 = (n3 - n5) / super.a;
        final int n8 = (n4 - n6) / super.a;
        final int n9 = n5 + (n3 - (n5 + n7 * super.a));
        final int n10 = n6 + (n4 - (n6 + n8 * super.a));
        final int n11 = n9 + n7 * n2;
        final int n12 = n10 + n8 * n2;
        int i = n11 >> 16;
        final int n13 = n12 >> 16;
        while (i < 0) {
            i += super.n + 1;
        }
        while (i >= super.n + 1) {
            i -= super.n + 1;
        }
        return new Point(i, n13);
    }
    
    public final void n() {
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            final _zh zh = elements.nextElement();
            zh.d();
            zh.d();
        }
    }
    
    public final int[] p() {
        return super.p;
    }
    
    private final void p(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12) {
        final int a = this.a();
        final int n13 = (n6 - n4 << 16) / (n10 - n8);
        final int n14 = (n7 - n5 << 16) / (n11 - n9);
        int n15 = n5 << 16;
        for (int i = n9; i < n11; ++i) {
            int n16 = i * n + n8;
            int n17 = n4 << 16;
            for (int j = n8; j < n10; ++j) {
                array[n16] = super.p[(n15 >> 16) * a + (n17 >> 16)];
                ++n16;
                n17 += n13;
            }
            n15 += n14;
        }
    }
    
    public _zs(final String p3, final String d, final double p4) {
        this.p = 1;
        this.d = 0;
        this.a = 1;
        this.p = new Vector();
        this.p = p3;
        this.d = d;
        this.p = p4;
    }
    
    public final void d() {
        super.p = null;
    }
    
    public final void p(final String s, final String s2, final double n, final double n2, final PixScreen pixScreen, final String s3, final int n3, final int n4) {
        this.p.addElement(new _zu(s, s2, this, pixScreen, n, n2, s3, n3, n4));
    }
    
    public final void p(final String s, final String s2, final double n, final double n2, final PixScreen pixScreen, final String s3, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9) {
        this.p.addElement(new _zp(s, s2, this, pixScreen, n, n2, s3, n3, n4, n5, n6, n7, n8, n9));
    }
    
    public final int d() {
        return super.v + 1;
    }
    
    public final int a() {
        return super.n + 1;
    }
    
    public final synchronized int p(final int[] array, final int n, final double n2, final double n3, final double n4) {
        switch (this.a) {
            case 1: {
                return super.p(array, n, n2, n3, n4);
            }
            case 3: {
                this.p(array, n);
                break;
            }
        }
        return 0;
    }
    
    public final void a() {
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().v = 30;
        }
    }
    
    public final _zh p(final int n, final int n2) {
        final Point p2 = this.p(n, n2);
        if (p2 == null) {
            return null;
        }
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            final _zh zh = elements.nextElement();
            if (zh != null && zh.p(p2.x, p2.y)) {
                return zh;
            }
        }
        return null;
    }
    
    public final synchronized double p() {
        switch (this.a) {
            case 1: {
                return super.p();
            }
            case 2: {
                if (super.n == 0 || super.d == 0) {
                    return 0.0;
                }
                final double d = this.d();
                if (super.n < super.d) {
                    return d / 2.0;
                }
                if (super.p) {
                    return d / this.a() * super.d / 2.0;
                }
                return 0.0;
            }
            default: {
                return 0.0;
            }
        }
    }
    
    public final void p() {
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().p();
        }
    }
    
    private final void p(final int[] array, final int n) {
        if (this.p == 0) {
            return;
        }
        final double n2 = this.d() / this.p;
        final int n3 = (int)(n2 + 0.5);
        final int a = this.a();
        if (a <= super.d && n3 <= super.a) {
            this.v = (super.d - a) / 2;
            this.i = (super.a - n3) / 2;
            this.l = this.v + a;
            this.b = this.i + n3;
        }
        else if (a / n3 < super.d / super.a) {
            int n4 = (int)(super.a / n3 * a + 0.5);
            if (n4 == 0) {
                n4 = 1;
            }
            this.i = 0;
            this.b = super.a;
            this.v = (super.d - n4) / 2;
            this.l = this.v + n4;
        }
        else {
            int n5 = (int)(super.d / a * n3 + 0.5);
            if (n5 == 0) {
                n5 = 1;
            }
            this.v = 0;
            this.l = super.d;
            this.i = (super.a - n5) / 2;
            this.b = this.i + n5;
        }
        this.p(array, super.d, super.a, n, 0, (int)(n2 * this.d + 0.5), this.a(), (int)(n2 * (this.d + 1) + 0.5), this.v, this.i, this.l, this.b, 0);
    }
}
