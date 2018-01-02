import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class u extends m
{
    public int a;
    int b;
    af c;
    af d;
    Vector e;
    Vector f;
    ar[] g;
    ar[] h;
    
    u() {
        this.b = 0;
        this.e = new Vector();
        this.f = new Vector();
        super.a = 6;
    }
    
    int a(final x x) {
        super.d = x.e();
        this.b(x.e());
        x.a(this, new af[65536]);
        return super.d;
    }
    
    final void b(final int a) {
        this.a = a;
        this.g = new ar[this.a];
        this.h = new ar[this.a];
    }
    
    final void a(final int n, final String s) {
        this.f.addElement(s.toLowerCase());
        this.e.addElement(new Integer(n));
    }
    
    final int b(final String s) {
        final int index = this.f.indexOf(s.toLowerCase());
        if (index == -1) {
            return -1;
        }
        return (int)this.e.elementAt(index);
    }
    
    final void a(final int n, final ar c) {
        final boolean l = c.l;
        if (this.g[n] == null) {
            this.g[n] = c;
            if (!l) {
                return;
            }
        }
        ar ar = this.g[n];
        ar ar2 = null;
        while (true) {
            while (true) {
                Label_0043: {
                    if (!l) {
                        break Label_0043;
                    }
                    final ar c2 = ar.c;
                    ar = ar2;
                }
                if (ar.c != null) {
                    continue;
                }
                break;
            }
            ar2 = ar;
            if (l) {
                continue;
            }
            break;
        }
        ar2.c = c;
    }
    
    final void b(final int n, final ar c) {
        final boolean l = c.l;
        if (this.h[n] == null) {
            this.h[n] = c;
            if (!l) {
                return;
            }
        }
        ar ar = this.h[n];
        ar ar2 = null;
        while (true) {
            while (true) {
                Label_0043: {
                    if (!l) {
                        break Label_0043;
                    }
                    final ar c2 = ar.c;
                    ar = ar2;
                }
                if (ar.c != null) {
                    continue;
                }
                break;
            }
            ar2 = ar;
            if (l) {
                continue;
            }
            break;
        }
        ar2.c = c;
    }
    
    void f() {
        this.b = this.a;
    }
    
    void c(final int n) {
        while (this.b != this.a && this.b <= n) {
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
    }
    
    void a(final b b, final int n) {
        ar ar = this.h[n];
        while (true) {
            Label_0029: {
                if (!c.l) {
                    break Label_0029;
                }
                final ar c = ar.c;
                ar.b(b, null);
                ar = c;
            }
            if (ar == null) {
                this.h[n] = null;
                return;
            }
            continue;
        }
    }
    
    void a(final af d) {
        Label_0026: {
            if (this.d != null) {
                this.d.i = d;
                if (!c.l) {
                    break Label_0026;
                }
            }
            this.c = d;
        }
        d.i = null;
        d.h = this.d;
        this.d = d;
    }
}
