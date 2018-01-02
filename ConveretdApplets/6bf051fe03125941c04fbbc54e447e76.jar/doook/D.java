// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public abstract class D implements aV, az, Cloneable
{
    public boolean a;
    private int t;
    private String b;
    private long a;
    public int u;
    public int d;
    public int v;
    public int w;
    public String c;
    
    public int e() {
        return this.t;
    }
    
    public void k(final int t) {
        this.t = t;
    }
    
    public String d() {
        return this.b;
    }
    
    public void b(final String b) {
        this.b = b;
    }
    
    public final boolean a(final int n) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        return (this.a & 1L << n) != 0x0L;
    }
    
    public void a(final int n, final boolean b) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        final long n2 = 1L << n;
        if (b) {
            this.a |= n2;
        }
        else {
            this.a &= ~n2;
        }
    }
    
    public void a(final long a) {
        this.a = a;
    }
    
    public long a() {
        return this.a;
    }
    
    public String toString() {
        return this.b + " (ID=" + this.t + ")";
    }
    
    public int a(final az az, final String s) {
        if ("name".equals(s) && az instanceof D) {
            if (!(az instanceof a)) {
                return this.d().toLowerCase().compareTo(((D)az).d().toLowerCase());
            }
            if (((a)this).a == ((a)az).a) {
                return this.d().toLowerCase().compareTo(((D)az).d().toLowerCase());
            }
            return (((a)this).a > ((a)az).a) ? 1 : -1;
        }
        else {
            if ("ID".equals(s) && az instanceof D) {
                return this.e() - ((D)az).e();
            }
            return 0;
        }
    }
    
    public Object a(final String s) {
        if ("name".equals(s)) {
            return this.d();
        }
        if ("ID".equals(s)) {
            return new Integer(this.e());
        }
        return null;
    }
    
    public String a(final String s) {
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
    
    public D(final int n, final String s) {
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.a = true;
        this.a = 0L;
        this.k(n);
        this.b(s);
        this.d = ah.a.getRGB();
    }
}
