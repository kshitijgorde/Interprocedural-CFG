// 
// Decompiled by Procyon v0.5.30
// 

package a;

public abstract class aK implements aX, bV, Cloneable
{
    public int a;
    public int s;
    public int d;
    public int f;
    public String o;
    protected long w;
    private int q;
    
    public aK(final int a, final String o) {
        this.q = 0;
        this.w = 0L;
        this.a = a;
        this.o = o;
        if (cs.q == 1) {
            this.s = 0;
        }
    }
    
    public final int q() {
        return this.a;
    }
    
    public final void b_(final int a) {
        this.a = a;
    }
    
    public final String w() {
        return this.o;
    }
    
    public final void y(final int d) {
        if (d == -16777216) {
            this.d = 0;
            return;
        }
        this.d = d;
    }
    
    public final void u(final int f) {
        if (f == -16777216) {
            this.f = 0;
            return;
        }
        this.f = f;
    }
    
    public final int w() {
        return this.s;
    }
    
    public final void i(int s) {
        if (s == -1) {
            s = 0;
        }
        this.s = s;
    }
    
    public final int e() {
        return this.q;
    }
    
    public final void o(final int q) {
        this.q = q;
    }
    
    public boolean q(final int n) {
        if (cs.q == 0) {
            return this.w(n);
        }
        if (cs.q != 1) {
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
        return this.o + " (ID=" + this.a + ")";
    }
    
    public int q(final aX ax, final String s) {
        if ("name".equals(s) && ax instanceof aK) {
            return this.o.toLowerCase().compareTo(((aK)ax).o.toLowerCase());
        }
        if ("ID".equals(s) && ax instanceof aK) {
            return this.a - ((aK)ax).a;
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
            return new Integer(this.a);
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
