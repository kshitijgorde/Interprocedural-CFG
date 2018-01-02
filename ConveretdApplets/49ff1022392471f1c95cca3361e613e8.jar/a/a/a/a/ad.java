// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ad extends a8
{
    public int i;
    public int t;
    public int h;
    public int g;
    public int s;
    public int j;
    public boolean m;
    int[] p;
    public boolean u;
    public boolean o;
    public int f;
    public boolean n;
    boolean k;
    boolean q;
    ae goto;
    boolean l;
    protected static char[] r;
    
    static {
        ad.r = new char[] { 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    ad() {
        this.i = 0;
        this.t = 0;
        this.h = 0;
        this.g = 0;
        this.s = 0;
        this.j = 0;
        this.m = false;
        this.p = null;
        this.u = false;
        this.o = false;
        this.f = 0;
        this.n = false;
        this.k = true;
        this.q = true;
        this.goto = null;
        this.l = false;
        super.byte = ad.r;
    }
    
    public void a(final a2 a2, final ac ac, final ae ae) {
    }
    
    protected void do() {
        float n = this.i;
        while (n > 1.0f) {
            n /= 2.0f;
            if (n != (int)n) {
                this.f = 0;
                return;
            }
            ++this.f;
        }
    }
    
    void if(final long n) {
        super.else = true;
    }
    
    public long a(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.i || n2 >= this.t) {
            return 0L;
        }
        return this.p[n + n2 * this.i];
    }
    
    public void for() {
    }
    
    public void a(final boolean k) {
        this.k = k;
    }
    
    public void do(final boolean q) {
        this.q = q;
    }
    
    public void if() {
        if (this.goto != null) {
            this.goto.a();
        }
        this.goto = null;
        this.p = null;
    }
    
    public void if(final boolean b) {
    }
    
    public ad a(final ad ad, final ae ae) {
        return null;
    }
    
    public void int() {
    }
}
