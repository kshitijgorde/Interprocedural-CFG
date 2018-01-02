// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public abstract class bn implements bk, s, Cloneable
{
    public boolean ac;
    private int e;
    private String b;
    private long d;
    public int ag;
    public int ah;
    public int ai;
    public int aj;
    public String Q;
    
    public int b() {
        return this.e;
    }
    
    public void d(final int e) {
        this.e = e;
    }
    
    public String g() {
        return this.b;
    }
    
    public void f(final String b) {
        this.b = b;
    }
    
    public final boolean c(final int n) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        return (this.d & 1L << n) != 0x0L;
    }
    
    public void a(final int n, final boolean b) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        final long n2 = 1L << n;
        if (b) {
            this.d |= n2;
        }
        else {
            this.d &= ~n2;
        }
    }
    
    public void a(final long d) {
        this.d = d;
    }
    
    public long d() {
        return this.d;
    }
    
    public String toString() {
        return this.b + " (ID=" + this.e + ")";
    }
    
    public int a(final bk bk, final String s) {
        if ("name".equals(s) && bk instanceof bn) {
            if (!(bk instanceof aq)) {
                return this.g().toLowerCase().compareTo(((bn)bk).g().toLowerCase());
            }
            if (((aq)this).a == ((aq)bk).a) {
                return this.g().toLowerCase().compareTo(((bn)bk).g().toLowerCase());
            }
            return (((aq)this).a > ((aq)bk).a) ? 1 : -1;
        }
        else {
            if ("ID".equals(s) && bk instanceof bn) {
                return this.b() - ((bn)bk).b();
            }
            return 0;
        }
    }
    
    public Object a(final String s) {
        if ("name".equals(s)) {
            return this.g();
        }
        if ("ID".equals(s)) {
            return new Integer(this.b());
        }
        return null;
    }
    
    public String b(final String s) {
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
    
    public bn(final int n, final String s) {
        this.ag = 0;
        this.ai = 0;
        this.aj = 0;
        this.ac = true;
        this.d = 0L;
        this.d(n);
        this.f(s);
        this.ah = aH.c.getRGB();
    }
}
