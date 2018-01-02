// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public abstract class cF implements aI, aU, Cloneable
{
    public boolean a;
    private int h;
    private String d;
    private long q;
    public int aN;
    public int d;
    public String ai;
    public String aj;
    public String ak;
    public boolean av;
    public boolean aw;
    public int aO;
    public String b;
    public String al;
    
    public int h() {
        return this.h;
    }
    
    public void l(final int h) {
        this.h = h;
    }
    
    public String f() {
        return this.d;
    }
    
    public void d(final String d) {
        this.d = d;
    }
    
    public final boolean d(final int n) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        return (this.q & 1L << n) != 0x0L;
    }
    
    public void a(final int n, final boolean b) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        final long n2 = 1L << n;
        if (b) {
            this.q |= n2;
        }
        else {
            this.q &= ~n2;
        }
    }
    
    public void n(final int n) {
        this.a(n, true);
    }
    
    public void a(final long q) {
        this.q = q;
    }
    
    public long d() {
        return this.q;
    }
    
    public void h(final int n) {
        this.a(n, false);
    }
    
    public String toString() {
        return this.d + " (ID=" + this.h + ")";
    }
    
    public int a(final aU au, final String s) {
        if ("name".equals(s) && au instanceof cF) {
            if (!(au instanceof ab)) {
                return this.f().toLowerCase().compareTo(((cF)au).f().toLowerCase());
            }
            if (((ab)au).a == null || !((ab)au).a.av) {
                if (((ab)this).an == ((ab)au).an) {
                    return this.f().toLowerCase().compareTo(((cF)au).f().toLowerCase());
                }
                return (((ab)this).an > ((ab)au).an) ? 1 : -1;
            }
            else {
                final int n = ((ab)this).e + ((ab)this).f;
                final int n2 = ((ab)au).e + ((ab)au).f;
                if (n == n2) {
                    return this.f().toLowerCase().compareTo(((cF)au).f().toLowerCase());
                }
                return (n < n2) ? 1 : -1;
            }
        }
        else {
            if ("ID".equals(s) && au instanceof cF) {
                return this.h() - ((cF)au).h();
            }
            return 0;
        }
    }
    
    public boolean a(final String s, final Object o) {
        return true;
    }
    
    public Object a(final String s) {
        if ("name".equals(s)) {
            return this.f();
        }
        if ("ID".equals(s)) {
            return new Integer(this.h());
        }
        return null;
    }
    
    public String c(final String s) {
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
    
    public cF(final int n, final String s) {
        this.aN = 0;
        this.av = false;
        this.aw = false;
        this.aO = 0;
        this.a = true;
        this.q = 0L;
        this.l(n);
        this.d(s);
        this.d = bR.a.getRGB();
    }
}
