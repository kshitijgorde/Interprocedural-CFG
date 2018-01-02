// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public abstract class an implements R, a, Cloneable
{
    public int g;
    public String c;
    public long a;
    public int h;
    public boolean c;
    public long[] a;
    
    public final int a() {
        return this.g;
    }
    
    public final void a(final int g) {
        this.g = g;
    }
    
    public final boolean a(final int n) {
        if (this.c) {
            if (n < 0 || n > 127) {
                throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 127, inclusive: " + n);
            }
            final long n2 = (n >= 64) ? (1L << n - 64) : (1L << n);
            if (n < 64) {
                return (this.a[0] & n2) != 0x0L;
            }
            return (this.a[1] & n2) != 0x0L;
        }
        else {
            if (n < 0 || n > 63) {
                throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
            }
            return (this.a & 1L << n) != 0x0L;
        }
    }
    
    public final void a(final int n, final boolean b) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        final long n2 = 1L << n;
        if (b) {
            this.a |= n2;
            return;
        }
        this.a &= ~n2;
    }
    
    public String toString() {
        return this.c + " (ID=" + this.g + ")";
    }
    
    public int a(final a a, final String s) {
        if ("name".equals(s) && a instanceof an) {
            return this.c.toLowerCase().compareTo(((an)a).c.toLowerCase());
        }
        if ("ID".equals(s) && a instanceof an) {
            return this.g - ((an)a).g;
        }
        return 0;
    }
    
    public boolean a(final String s, final Object o) {
        return true;
    }
    
    public Object a(final String s) {
        if ("name".equals(s)) {
            return this.c;
        }
        if ("ID".equals(s)) {
            return new Integer(this.g);
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
    
    public final void a(final long[] array) {
        this.a[0] = array[0];
        this.a[1] = array[1];
    }
    
    public an(final int g, final String c) {
        this.a = new long[] { 0L, 0L };
        this.a = 0L;
        this.h = 0;
        this.g = g;
        this.c = c;
        this.c = false;
    }
}
