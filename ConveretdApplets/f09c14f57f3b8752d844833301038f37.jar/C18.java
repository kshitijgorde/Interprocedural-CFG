import java.io.IOException;
import java.io.InputStream;
import java.awt.Label;
import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class C18
{
    Hashtable m;
    boolean[] n;
    C46 o;
    C48 p;
    Hashtable q;
    Hashtable r;
    Vector s;
    URL t;
    Vector u;
    C17 v;
    Label w;
    
    public Hashtable a() {
        return this.m;
    }
    
    public void b(final Integer n, final C32 c32) {
        this.m.put(n, c32);
        final String b = c32.b();
        if (b != null && this.r != null && this.r.get(b) != null) {
            System.out.println("LAYER OFF>>>>" + b);
            c32.a();
            if (n > this.n.length) {
                final boolean[] n2 = new boolean[this.n.length * 2];
                for (int i = 0; i < n2.length; ++i) {
                    n2[i] = true;
                }
                for (int j = 0; j < this.n.length; ++j) {
                    n2[j] = this.n[j];
                }
                this.n = n2;
            }
            this.n[n] = false;
        }
    }
    
    public void c(final C48 p) {
        this.p = p;
    }
    
    public C18(final InputStream inputStream, final URL t) throws IOException {
        this.t = t;
        this.s = new Vector();
        this.m = new Hashtable();
        this.q = new Hashtable();
        this.u = new Vector();
    }
    
    public Vector d() {
        return this.s;
    }
    
    public Hashtable e() {
        return this.q;
    }
    
    public Vector f() {
        return this.u;
    }
    
    protected void g() {
        if (this.p != null) {
            this.p.R();
        }
    }
    
    protected void i(final C35 c35) {
        if (c35 instanceof C28 && this.n != null) {
            final int g = ((C28)c35).g();
            if (this.n.length > g && !this.n[g]) {
                ((C28)c35).h(false);
            }
        }
        if (this.o != null) {
            this.o.O(c35);
        }
    }
    
    public abstract void j(final C46 p0) throws IOException;
    
    public C17 k() {
        return this.v;
    }
    
    public void l(final Hashtable r) {
        this.r = r;
        this.n = new boolean[100];
        for (int i = 0; i < this.n.length; ++i) {
            this.n[i] = true;
        }
    }
}
