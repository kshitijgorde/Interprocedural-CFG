// 
// Decompiled by Procyon v0.5.30
// 

package a;

public abstract class ba implements aq, cG, Cloneable
{
    public int q;
    public int w;
    public int e;
    public int r;
    public String e;
    private long[] q;
    private int t;
    
    public ba(final int q, final String e) {
        this.q = new long[] { 0L, 0L };
        this.t = 0;
        this.q = q;
        this.e = e;
        if (a.q == 1) {
            this.w = 0;
        }
    }
    
    public final int q() {
        return this.q;
    }
    
    public final void e(final int q) {
        this.q = q;
    }
    
    public String getName() {
        return this.e;
    }
    
    public final int w() {
        return this.e;
    }
    
    public final void b_(final int n) {
        this.e = (n & 0xFFFFFF);
    }
    
    public final int e() {
        return this.r;
    }
    
    public final void w(final int r) {
        if (r == -16777216) {
            this.r = 0;
            return;
        }
        this.r = r;
    }
    
    public final int r() {
        return this.w;
    }
    
    public final void r(int w) {
        if (w == -1) {
            w = 0;
        }
        this.w = w;
    }
    
    public final int t() {
        return this.t;
    }
    
    public final void t(final int t) {
        this.t = t;
    }
    
    public final boolean q(int n) {
        if (a.q == 0 && n == 44) {
            return false;
        }
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 127, inclusive: " + n);
        }
        final long n2 = (n < 64) ? (1L << n) : (1L << n - 64);
        n = ((n >= 64) ? 1 : 0);
        return (this.q[n] & n2) != 0x0L;
    }
    
    public final void q(int n, final boolean b) {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 127, inclusive: " + n);
        }
        final long n2 = (n < 64) ? (1L << n) : (1L << n - 64);
        n = ((n >= 64) ? 1 : 0);
        if (b) {
            final long[] q = this.q;
            final int n3 = n;
            q[n3] |= n2;
            return;
        }
        final long[] q2 = this.q;
        final int n4 = n;
        q2[n4] &= ~n2;
    }
    
    public void q(final long n) {
        this.q[0] = n;
    }
    
    public final void q(final long[] array) {
        this.q(array[0]);
        this.q[1] = array[1];
    }
    
    public final long q() {
        return this.q[0];
    }
    
    public final long[] q() {
        return this.q;
    }
    
    public String toString() {
        return this.e + " (ID=" + this.q + ")";
    }
    
    public int q(final aq aq, final String s) {
        if ("name".equals(s) && aq instanceof ba) {
            return this.getName().toLowerCase().compareTo(((ba)aq).getName().toLowerCase());
        }
        if ("ID".equals(s) && aq instanceof ba) {
            return this.q - ((ba)aq).q;
        }
        return 0;
    }
    
    public boolean q(final String s, final Object o) {
        return true;
    }
    
    public Object q(final String s) {
        if ("name".equals(s)) {
            return this.getName();
        }
        if ("ID".equals(s)) {
            return new Integer(this.q);
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
}
