// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class q implements I, Cloneable
{
    private static final cU[] f;
    private aj g;
    private String h;
    private String i;
    private cU[] j;
    private byte[] k;
    private cK l;
    private boolean m;
    long a;
    int b;
    boolean c;
    boolean d;
    boolean e;
    
    public q(final aj g, final String h, final String s, final cU[] array, final byte[] k, final cK l, final boolean m) {
        this.a = 0L;
        this.b = 0;
        this.c = false;
        this.d = false;
        this.e = false;
        this.g = g;
        this.h = h;
        this.b(s);
        this.a(array);
        this.k = k;
        this.l = l;
        this.m = m;
    }
    
    public final aj a() {
        return this.g;
    }
    
    public final void a(final aj g) {
        this.g = g;
    }
    
    public final String b() {
        return this.h;
    }
    
    public final void a(final String h) {
        this.h = h;
    }
    
    public final String c() {
        return this.i;
    }
    
    public final void b(String i) {
        if (i != null && i.trim().length() > 0) {
            if ((i = i.trim()).charAt(0) != '/' && !i.equals("*") && !this.h.equals("CONNECT")) {
                final String s = i;
                char char1 = '\0';
                for (int n = 0; n < s.length() && (char1 = s.charAt(n)) != ':' && char1 != '/' && char1 != '?' && char1 != '#'; ++n) {}
                if (char1 != ':') {
                    i = "/" + i;
                }
            }
            this.i = i;
            return;
        }
        this.i = "/";
    }
    
    public final cU[] d() {
        return this.j;
    }
    
    public final void a(final cU[] j) {
        if (j != null) {
            this.j = j;
            return;
        }
        this.j = q.f;
    }
    
    public final byte[] e() {
        return this.k;
    }
    
    public final void a(final byte[] k) {
        this.k = k;
    }
    
    public final cK f() {
        return this.l;
    }
    
    public final void a(final cK ck) {
        this.l = null;
    }
    
    public final boolean g() {
        return this.m;
    }
    
    public final Object clone() {
        q q;
        try {
            q = (q)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.toString());
        }
        q.j = new cU[this.j.length];
        System.arraycopy(this.j, 0, q.j, 0, this.j.length);
        return q;
    }
    
    public final void a(final q q) {
        this.g = q.g;
        this.h = q.h;
        this.i = q.i;
        this.j = q.j;
        this.k = q.k;
        this.l = q.l;
        this.m = q.m;
        this.a = q.a;
        this.b = q.b;
        this.c = q.c;
        this.d = q.d;
        this.e = q.e;
    }
    
    public final String toString() {
        return this.getClass().getName() + ": " + this.h + " " + this.i;
    }
    
    static {
        f = new cU[0];
    }
}
