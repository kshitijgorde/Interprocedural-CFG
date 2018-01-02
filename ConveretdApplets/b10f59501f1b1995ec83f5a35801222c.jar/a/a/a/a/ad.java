// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ad extends a8
{
    public int j;
    public int u;
    public int i;
    public int h;
    public int t;
    public int k;
    public boolean n;
    int[] q;
    public boolean v;
    public boolean p;
    public int g;
    public boolean o;
    boolean l;
    boolean r;
    boolean m;
    protected static char[] s;
    
    static {
        ad.s = new char[] { 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    ad() {
        this.j = 0;
        this.u = 0;
        this.i = 0;
        this.h = 0;
        this.t = 0;
        this.k = 0;
        this.n = false;
        this.q = null;
        this.v = false;
        this.p = false;
        this.g = 0;
        this.o = false;
        this.l = true;
        this.r = true;
        this.m = false;
        super.case = ad.s;
    }
    
    public void a(final a2 a2, final ac ac, final ae ae) {
    }
    
    protected void do() {
        float n = this.j;
        while (n > 1.0f) {
            n /= 2.0f;
            if (n != (int)n) {
                this.g = 0;
                return;
            }
            ++this.g;
        }
    }
    
    void if(final long n) {
        super.goto = true;
    }
    
    public long a(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.j || n2 >= this.u) {
            return 0L;
        }
        return this.q[n + n2 * this.j];
    }
    
    public void for() {
    }
    
    public void a(final boolean l) {
        this.l = l;
    }
    
    public void do(final boolean r) {
        this.r = r;
    }
    
    public void if() {
        if (super.long != null) {
            super.long.a();
        }
        super.long = null;
        this.q = null;
    }
    
    public void if(final boolean b) {
    }
    
    public ad a(final ad ad, final ae ae) {
        return null;
    }
    
    public void int() {
    }
}
