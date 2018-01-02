// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public abstract class U implements a, am, Cloneable
{
    public boolean a;
    public int i;
    public String d;
    public long b;
    public int j;
    public boolean b;
    public long[] a;
    
    public final int a() {
        return this.i;
    }
    
    public final void a(final int i) {
        this.i = i;
    }
    
    public final boolean a(final int n) {
        if (this.b) {
            return this.b(n);
        }
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        return (this.b & 1L << n) != 0x0L;
    }
    
    public final void a(final int n, final boolean b) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        final long n2 = 1L << n;
        if (b) {
            this.b |= n2;
            return;
        }
        this.b &= ~n2;
    }
    
    public final void b(final int n) {
        if (this.b) {
            this.b(n, true);
            return;
        }
        this.a(n, true);
    }
    
    public final void c(final int n) {
        if (this.b) {
            this.b(n, false);
            return;
        }
        this.a(n, false);
    }
    
    public String toString() {
        return this.d + " (ID=" + this.i + ")";
    }
    
    public int a(final a a, final String s) {
        if ("name".equals(s) && a instanceof U) {
            return this.d.toLowerCase().compareTo(((U)a).d.toLowerCase());
        }
        if ("ID".equals(s) && a instanceof U) {
            return this.i - ((U)a).i;
        }
        return 0;
    }
    
    public boolean a(final String s, final Object o) {
        return true;
    }
    
    public Object a(final String s) {
        if ("name".equals(s)) {
            return this.d;
        }
        if ("ID".equals(s)) {
            return new Integer(this.i);
        }
        return null;
    }
    
    public String a() {
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
    
    public final boolean b(final int n) {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 127, inclusive: " + n);
        }
        final long n2 = (n >= 64) ? (1L << n - 64) : (1L << n);
        if (n < 64) {
            return (this.a[0] & n2) != 0x0L;
        }
        return (this.a[1] & n2) != 0x0L;
    }
    
    public final void b(final int n, final boolean b) {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 127, inclusive: " + n);
        }
        final long n2 = (n >= 64) ? (1L << n - 64) : (1L << n);
        if (n < 64) {
            if (b) {
                final long[] a = this.a;
                final int n3 = 0;
                a[n3] |= n2;
                return;
            }
            final long[] a2 = this.a;
            final int n4 = 0;
            a2[n4] &= ~n2;
        }
        else {
            if (b) {
                final long[] a3 = this.a;
                final int n5 = 1;
                a3[n5] |= n2;
                return;
            }
            final long[] a4 = this.a;
            final int n6 = 1;
            a4[n6] &= ~n2;
        }
    }
    
    public final void a(final long[] array) {
        this.a[0] = array[0];
        this.a[1] = array[1];
    }
    
    public U(final int i, final String d) {
        this.a = new long[] { 0L, 0L };
        this.a = true;
        this.b = 0L;
        this.j = 0;
        this.i = i;
        this.d = d;
        this.b = false;
    }
}
