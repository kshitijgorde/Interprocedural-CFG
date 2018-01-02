import java.util.Vector;
import java.util.Hashtable;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class t extends u
{
    blaze3d a;
    x b;
    URL c;
    String d;
    int e;
    f f;
    int g;
    int h;
    boolean i;
    boolean j;
    ak k;
    Object l;
    public ao m;
    m[] n;
    Hashtable p;
    t q;
    Vector r;
    
    t(final blaze3d a, final String d) {
        this.l = new Object();
        this.n = new m[64];
        this.p = new Hashtable(10, 0.5f);
        this.r = new Vector(10, 10);
        this.a = a;
        this.d = d;
        try {
            this.c = new URL(a.a1, this.d);
        }
        catch (Exception ex) {}
        this.b = new x(a, this);
        this.k = new ak(false);
        super.b = this;
        this.j = false;
        this.e = 0;
    }
    
    void b() {
        ++this.e;
        if (this.j) {
            return;
        }
        synchronized (this.l) {
            this.b.a(this.c);
            this.j = true;
        }
    }
    
    void c() {
        final boolean l = c.l;
        --this.e;
        if (this.e != 0) {
            return;
        }
        this.b.j = true;
        synchronized (this.l) {
            this.j = false;
            this.b.j = false;
            super.c = null;
            super.d = null;
            super.g = null;
            super.h = null;
            super.b = -1;
            this.m = null;
            int n = 0;
        Label_0107_Outer:
            while (true) {
                Label_0138: {
                    if (!l) {
                        break Label_0138;
                    }
                    m m = this.n[n & 0x3F];
                    while (true) {
                        while (true) {
                            Label_0116: {
                                if (!l) {
                                    break Label_0116;
                                }
                                final m c = m.c;
                                final m i = c;
                                m.a();
                                m = i;
                            }
                            if (m != null) {
                                continue Label_0107_Outer;
                            }
                            break;
                        }
                        final m c = this;
                        if (l) {
                            continue;
                        }
                        break;
                    }
                    this.n[n & 0x3F] = null;
                    ++n;
                }
                if (n < 64) {
                    continue;
                }
                break;
            }
            int n2 = 0;
            while (true) {
                while (true) {
                    Label_0168: {
                        if (!l) {
                            break Label_0168;
                        }
                        ((v)this.r.elementAt(n2)).a();
                        ++n2;
                    }
                    if (n2 < this.r.size()) {
                        continue;
                    }
                    break;
                }
                this.r.removeAllElements();
                this.p.clear();
                this.a.a(this);
                super.a();
                this.n = null;
                this.b.a();
                this.b = null;
                this.q = null;
                if (l) {
                    continue;
                }
                break;
            }
        }
        // monitorexit(this.l)
    }
    
    void d() {
        this.e = 1;
        this.c();
    }
    
    synchronized m a(final int n) {
        final boolean l = c.l;
        m m = this.n[n & 0x3F];
        int d = 0;
        int n2 = 0;
        Label_0049: {
            while (true) {
                Label_0023: {
                    if (!l) {
                        break Label_0023;
                    }
                    m = m.c;
                }
                if (m != null) {
                    d = m.d;
                    n2 = n;
                    if (l) {
                        break Label_0049;
                    }
                    if (d != n) {
                        continue;
                    }
                }
                break;
            }
            if (m == null) {
                return m;
            }
            final int a = m.a;
            n2 = 17;
        }
        if (d == n2) {
            m = ((a1)m).a;
        }
        return m;
    }
    
    synchronized m a(final String s) {
        if (s == null) {
            return null;
        }
        return this.p.get(s.toLowerCase());
    }
    
    void a(final String s, final m m) {
        this.p.put(s.toLowerCase(), m);
    }
    
    void a(final t t) {
        final boolean l = c.l;
        int n = 0;
        while (true) {
            Label_0066: {
                if (!l) {
                    break Label_0066;
                }
                m c = this.n[n & 0x3F];
                while (true) {
                    Label_0059: {
                        if (!l) {
                            break Label_0059;
                        }
                        if (c.e != null && t.a(c.e) == null) {
                            t.a(c.e, c);
                        }
                        c = c.c;
                    }
                    if (c != null) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n >= 64) {
                return;
            }
            continue;
        }
    }
    
    synchronized void a(final int d, final m m) {
        if (m != null) {
            m.c = this.n[d & 0x3F];
            this.n[d & 0x3F] = m;
            m.d = d;
            m.b = this;
            if (m.e != null) {
                this.a(m.e, m);
            }
        }
    }
}
