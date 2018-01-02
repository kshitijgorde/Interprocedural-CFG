import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zy
{
    public String p;
    public String d;
    public String a;
    public String n;
    public _zj p;
    public _zv p;
    public double p;
    public double d;
    public double a;
    public double n;
    public double v;
    public double i;
    public int p;
    public int d;
    public int a;
    public int[] p;
    public Vector p;
    public int n;
    public boolean p;
    public static i p;
    
    public final synchronized void a() {
        if (this.n != null) {
            this.d();
            return;
        }
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            final _zh zh = elements.nextElement();
            zh.v();
            zh.p();
        }
    }
    
    public final synchronized int[] p() {
        return this.p;
    }
    
    public final synchronized double p(final double n) {
        if (this.p != null) {
            return this.p.p(n);
        }
        return 0.0;
    }
    
    public final synchronized void d() {
        if (_zy.p == null) {
            _zy.p = new i();
        }
        _zy.p.p(this.p, this.d, this.a);
    }
    
    public final synchronized void p() {
        if (_zy.p == null) {
            _zy.p = new i();
        }
        _zy.p.d(this.p, this.d, this.a);
    }
    
    public final synchronized double p() {
        if (this.p != null) {
            return this.p.d();
        }
        return 360.0;
    }
    
    public _zy(final int p10, final String p11, final String d, final double n, final double v, final double i, final double p12, final double d2, final double a, final String a2) {
        this.n = 1;
        this.p = false;
        this.p = new Vector();
        this.p = p10;
        this.p = p11;
        this.d = d;
        this.n = n;
        this.v = v;
        this.i = i;
        this.p = p12;
        this.d = d2;
        this.a = a;
        this.a = a2;
        this.p = new _zj();
    }
    
    public final synchronized void i() {
        this.p = null;
        if (this.p != null) {
            this.p.p();
        }
        if (_zy.p != null) {
            _zy.p.p();
        }
        if (this.p != null) {
            this.p.p();
        }
    }
    
    public final void p(final String s, final String s2, final double n, final double n2, final PixScreen pixScreen, final String s3, final int n3, final int n4, final int n5) {
        final _zu zu = new _zu(s, s2, this, pixScreen, n, n2, s3, n3, n4);
        this.p.addElement(zu);
        if (n4 == 4) {
            zu.d = true;
            zu.p(s2, n5);
            zu.a();
        }
    }
    
    public final void p(final String s, final String s2, final double n, final double n2, final PixScreen pixScreen, final String s3, final int n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9) {
        this.p.addElement(new _zp(s, s2, this, pixScreen, n, n2, s3, n3, n4, n5, n6, n7, n8, n9));
    }
    
    public final synchronized int p() {
        return this.a;
    }
    
    public final synchronized int d() {
        return this.d;
    }
    
    public final synchronized void p(final boolean d) {
        if (this.p == 3) {
            this.p.d = d;
            return;
        }
        if (this.p != null) {
            this.p.p(d);
        }
    }
    
    public final synchronized int p(final int[] array, final double n, final double n2, final double n3, final PixScreen pixScreen) {
        final int p5 = this.p.p(array, n, n2, n3);
        if (this.p()) {
            this.p(array, this.p.p, this.p.d, n, n2, n3, !pixScreen.au);
        }
        return p5;
    }
    
    public final synchronized void p(final int[] array, final int n, final int n2, final double n3, final double n4, final double n5, final boolean b) {
        if (this.n != null) {
            return;
        }
        d.p(this.p.d);
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            final _zh zh = elements.nextElement();
            zh.v();
            zh.p(n3, n4, n5, this.p.a(), array, n, n2, this.p.p, b && zh.i == 30);
        }
    }
    
    public final synchronized void n() {
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().i = 30;
        }
    }
    
    public final synchronized _zh p(final int n) {
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            final _zh zh = elements.nextElement();
            if (zh.dd == n) {
                return zh;
            }
        }
        return null;
    }
    
    public final synchronized boolean p() {
        return this.p == 5 || this.p == 4;
    }
    
    public final synchronized double d() {
        if (this.p != null) {
            return this.p.n();
        }
        return 60.0;
    }
    
    public final synchronized double p(final int n, final int n2) {
        if (this.p != null) {
            return this.p.p(n, n2);
        }
        return 60.0;
    }
    
    public final synchronized double d(final double n) {
        if (this.p != null) {
            return this.p.d(n);
        }
        return 0.0;
    }
    
    public final synchronized void p(final byte[] array, final int n, final int n2) {
        if (n != this.d || n2 != this.a || array == null) {
            return;
        }
        for (int n3 = n * n2, i = 0; i < n3; ++i) {
            this.p[i] |= array[i] << 24;
        }
    }
    
    public final synchronized void p(final int[] array, final int n, final int n2, final int t, final int u, final double d, final double n3, final double n4) {
        this.d = n;
        this.a = n2;
        this.p = array;
        if (this.p != 3) {
            switch (this.p) {
                case 2: {
                    this.p = new _zq();
                    break;
                }
                case 6: {
                    this.p = new _zg();
                    break;
                }
                case 4: {
                    this.p = new _zz();
                    break;
                }
                case 5: {
                    this.p = new n();
                    break;
                }
                default: {
                    return;
                }
            }
            this.p.p(array, n, n2, t, u, d, n3, n4);
            return;
        }
        this.p.s = n2;
        this.p.r = n;
        this.p.p = array;
        this.p.u = u;
        this.p.t = t;
        this.p.d = d;
    }
    
    public final synchronized boolean a() {
        return this.p != null && this.p.d();
    }
    
    public final synchronized double p(final double n, final double n2) {
        if (this.p != null) {
            return this.p.p(n, n2);
        }
        return 0.0;
    }
    
    public final synchronized boolean d() {
        return this.p != null && this.p.a();
    }
    
    public final synchronized void v() {
        if (this.n != null) {
            this.p();
            return;
        }
        final Enumeration<_zh> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a();
        }
    }
}
