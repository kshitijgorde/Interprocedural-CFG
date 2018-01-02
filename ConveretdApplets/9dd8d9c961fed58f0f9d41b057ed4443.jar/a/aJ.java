// 
// Decompiled by Procyon v0.5.30
// 

package a;

public abstract class aJ implements aY, bX, Cloneable
{
    public int s;
    public int d;
    public int f;
    public int g;
    public String o;
    protected long w;
    private int q;
    
    public aJ(final int s, final String o) {
        this.q = 0;
        this.w = 0L;
        this.s = s;
        this.o = o;
        if (cu.q == 1) {
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
        return this.o;
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
        if (cu.q == 0) {
            return this.w(n);
        }
        if (cu.q != 1) {
            return false;
        }
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        return (this.w & 1L << n) != 0x0L;
    }
    
    public final void q(final int n, final boolean b) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        final long n2 = 1L << n;
        if (b) {
            this.w |= n2;
            return;
        }
        this.w &= ~n2;
    }
    
    public void q(final long w) {
        this.w = w;
    }
    
    public final long q() {
        return this.w;
    }
    
    public String toString() {
        return this.o + " (ID=" + this.s + ")";
    }
    
    public int q(final aY ay, final String s) {
        if ("name".equals(s) && ay instanceof aJ) {
            return this.o.toLowerCase().compareTo(((aJ)ay).o.toLowerCase());
        }
        if ("ID".equals(s) && ay instanceof aJ) {
            return this.s - ((aJ)ay).s;
        }
        return 0;
    }
    
    public boolean q(final String s, final Object o) {
        return true;
    }
    
    public Object q(final String s) {
        if ("name".equals(s)) {
            return this.o;
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
        return (this.w & 1L << n) != 0x0L;
    }
}
