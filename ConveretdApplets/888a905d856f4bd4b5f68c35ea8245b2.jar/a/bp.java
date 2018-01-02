// 
// Decompiled by Procyon v0.5.30
// 

package a;

public abstract class bp implements bJ, dh, Cloneable
{
    public boolean t;
    public int s;
    public int d;
    public int f;
    public int g;
    public int h;
    public String a;
    protected long e;
    private int q;
    
    public bp(final int s, final String a) {
        this.h = 0;
        this.q = 0;
        this.t = true;
        this.e = 0L;
        this.s = s;
        this.a = a;
        if (dN.q == 1) {
            this.d = 0;
        }
    }
    
    public final int q() {
        return this.s;
    }
    
    public final void u(final int s) {
        this.s = s;
    }
    
    public final String w() {
        return this.a;
    }
    
    public final void i(final int f) {
        if (f == -16777216) {
            this.f = 0;
            return;
        }
        this.f = f;
    }
    
    public final void o(final int g) {
        if (g == -16777216) {
            this.g = 0;
            return;
        }
        this.g = g;
    }
    
    public final int w() {
        return this.d;
    }
    
    public final void p(int d) {
        if (d == -1) {
            d = 0;
        }
        this.d = d;
    }
    
    public final int e() {
        return this.q;
    }
    
    public final void a(final int q) {
        this.q = q;
    }
    
    public boolean q(final int n) {
        if (dN.q == 0) {
            return this.w(n);
        }
        if (dN.q != 1) {
            return false;
        }
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        return (this.e & 1L << n) != 0x0L;
    }
    
    public final void q(final int n, final boolean b) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        final long n2 = 1L << n;
        if (b) {
            this.e |= n2;
            return;
        }
        this.e &= ~n2;
    }
    
    public final void s(final int n) {
        this.q(n, true);
    }
    
    public void q(final long e) {
        this.e = e;
    }
    
    public final long w() {
        return this.e;
    }
    
    public final void d(final int n) {
        this.q(n, false);
    }
    
    public String toString() {
        return this.a + " (ID=" + this.s + ")";
    }
    
    public int q(final bJ bj, final String s) {
        if ("name".equals(s) && bj instanceof bp) {
            return this.a.toLowerCase().compareTo(((bp)bj).a.toLowerCase());
        }
        if ("ID".equals(s) && bj instanceof bp) {
            return this.s - ((bp)bj).s;
        }
        return 0;
    }
    
    public final int q(final bp bp) {
        if (this.s != bp.s) {
            return this.s - bp.s;
        }
        final int compareTo;
        if ((compareTo = this.a.compareTo(bp.a)) != 0) {
            return compareTo;
        }
        if (this.e != bp.e) {
            return 1;
        }
        if (this.d != bp.d) {
            return this.d - bp.d;
        }
        if (this.f != bp.f) {
            return this.f - bp.f;
        }
        if (this.g != bp.g) {
            return this.g - bp.g;
        }
        if (this.h != bp.h) {
            return this.h - bp.h;
        }
        return 0;
    }
    
    public static boolean q(final Object o, final Object o2) {
        return (o == null && o2 != null) || (o != null && o2 == null);
    }
    
    public static boolean w(final Object o, final Object o2) {
        return o == null || o2 == null;
    }
    
    public boolean q(final String s, final Object o) {
        return true;
    }
    
    public Object q(final String s) {
        if ("name".equals(s)) {
            return this.a;
        }
        if ("ID".equals(s)) {
            return new Integer(this.s);
        }
        return null;
    }
    
    public String q() {
        return null;
    }
    
    public synchronized Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    protected final boolean w(final int n) {
        if (n == 44) {
            return false;
        }
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        return (this.e & 1L << n) != 0x0L;
    }
}
