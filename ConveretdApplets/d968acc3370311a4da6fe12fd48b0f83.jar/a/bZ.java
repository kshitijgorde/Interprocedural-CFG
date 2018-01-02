// 
// Decompiled by Procyon v0.5.30
// 

package a;

public abstract class bZ implements aF, eo, Cloneable
{
    public boolean q;
    public int r;
    public int t;
    public int y;
    public int u;
    public int i;
    public String t;
    private long[] q;
    private int q;
    
    public bZ(final int r, final String t) {
        this.i = 0;
        this.q = new long[] { 0L, 0L };
        this.q = 0;
        this.q = true;
        this.r = r;
        this.t = t;
        if (a.q == 1) {
            this.t = 0;
        }
    }
    
    public final int q() {
        return this.r;
    }
    
    public final void e(final int r) {
        this.r = r;
    }
    
    public String getName() {
        return this.t;
    }
    
    public final void a_(final String t) {
        this.t = t;
    }
    
    public final int r() {
        return this.y;
    }
    
    public final void r(final int n) {
        this.y = (n & 0xFFFFFF);
    }
    
    public final int t() {
        return this.u;
    }
    
    public final void t(final int u) {
        if (u == -16777216) {
            this.u = 0;
            return;
        }
        this.u = u;
    }
    
    public final int y() {
        return this.t;
    }
    
    public final void y(int t) {
        if (t == -1) {
            t = 0;
        }
        this.t = t;
    }
    
    public final int u() {
        return this.q;
    }
    
    public final void u(final int q) {
        this.q = q;
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
    
    public final void i(final int n) {
        this.q(n, true);
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
    
    public final void o(final int n) {
        this.q(n, false);
    }
    
    public String toString() {
        return this.t + " (ID=" + this.r + ")";
    }
    
    public int q(final aF af, final String s) {
        if ("name".equals(s) && af instanceof bZ) {
            return this.getName().toLowerCase().compareTo(((bZ)af).getName().toLowerCase());
        }
        if ("ID".equals(s) && af instanceof bZ) {
            return this.r - ((bZ)af).r;
        }
        return 0;
    }
    
    public final int q(final bZ bz) {
        if (this.r != bz.r) {
            return this.r - bz.r;
        }
        final int compareTo;
        if ((compareTo = this.getName().compareTo(bz.getName())) != 0) {
            return compareTo;
        }
        if (this.q[0] != bz.q[0]) {
            return 1;
        }
        if (this.q[1] != bz.q[1]) {
            return 1;
        }
        if (this.t != bz.t) {
            return this.t - bz.t;
        }
        if (this.y != bz.y) {
            return this.y - bz.y;
        }
        if (this.u != bz.u) {
            return this.u - bz.u;
        }
        if (this.i != bz.i) {
            return this.i - bz.i;
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
            return this.getName();
        }
        if ("ID".equals(s)) {
            return new Integer(this.r);
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
