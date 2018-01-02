import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class i implements h
{
    c a;
    t b;
    String c;
    a2 d;
    a6 e;
    a7 f;
    int g;
    bb[] h;
    float[] i;
    Vector j;
    Vector k;
    int l;
    a9[] m;
    j[] n;
    float[][][] p;
    a8[] q;
    int r;
    int s;
    int t;
    
    public void a(final c a) {
        this.a = a;
    }
    
    public c a() {
        return this.a;
    }
    
    public void a(final t b) {
        if (this.b == null) {
            this.b = b;
        }
    }
    
    public i() {
        this.j = new Vector();
        this.k = new Vector();
        this.s = -1;
        this.t = 0;
    }
    
    public i(final String s, final b b) {
        this.j = new Vector();
        this.k = new Vector();
        this.s = -1;
        this.t = 0;
        this.a(b, s);
    }
    
    void b() {
        final boolean l = c.l;
        this.c();
        int n = 0;
        while (true) {
            while (true) {
                Label_0033: {
                    if (!l) {
                        break Label_0033;
                    }
                    final Object o = this.k.elementAt(n);
                    ((j)o).g();
                    ++n;
                }
                if (n < this.k.size()) {
                    continue;
                }
                break;
            }
            this.k.removeAllElements();
            this.k = null;
            this.b = null;
            Object o;
            final c c = (c)(o = this.a);
            if (!l) {
                c.c = null;
                return;
            }
            continue;
        }
    }
    
    public void a(final b b, final String c) {
        if (b != null && this.b == b.c && this.c == c) {
            return;
        }
        this.c();
        this.c = c;
        if (b != null) {
            this.b = b.c;
        }
        this.d();
    }
    
    void c() {
        final boolean l = c.l;
        this.h();
        int n = 0;
        while (true) {
            while (true) {
                Label_0024: {
                    if (!l) {
                        break Label_0024;
                    }
                    final i i = this;
                    i.n[n] = null;
                    ++n;
                }
                if (n < this.l) {
                    continue;
                }
                break;
            }
            this.l = 0;
            this.m = null;
            this.n = null;
            this.c = "";
            final i i = this;
            if (!l) {
                if (this.d != null) {
                    this.d.p.removeElement(this);
                    this.d = null;
                }
                this.e = null;
                this.f = null;
                this.q = null;
                this.h = null;
                this.d();
                return;
            }
            continue;
        }
    }
    
    void d() {
        blaze3d.a();
        this.s = blaze3d.c;
    }
    
    int e() {
        final boolean l = c.l;
        if (this.b == null) {
            return this.s;
        }
        if (this.c != null && this.d == null) {
            final m a = this.b.a(this.c);
            if (a != null && a.a == 13) {
                this.d = (a2)a;
                this.d.p.addElement(this);
                this.f();
            }
        }
        int n = 0;
        while (true) {
            while (true) {
                Label_0118: {
                    if (!l) {
                        break Label_0118;
                    }
                    final i i = this;
                    final j j = i.n[n];
                    if (j != null) {
                        final int d = j.d();
                        if (d > this.s) {
                            this.s = d;
                        }
                    }
                    ++n;
                }
                if (n < this.l) {
                    continue;
                }
                break;
            }
            final i i = this;
            if (!l) {
                return this.s;
            }
            continue;
        }
    }
    
    void f() {
        final boolean l = c.l;
        if (!this.d.k) {
            this.d.b();
        }
        this.e = this.d.l;
        this.f = this.d.m;
        this.r = this.d.c;
        this.q = this.d.n;
        this.g = this.d.d;
        this.l = this.d.j.size();
        this.m = new a9[this.l];
        this.n = new j[this.l];
        this.p = new float[this.l][][];
        int n = 0;
        while (true) {
            while (true) {
                Label_0224: {
                    if (!l) {
                        break Label_0224;
                    }
                    final Object element = this.d.j.elementAt(n);
                    final a9 a9 = (a9)element;
                    this.m[n] = a9;
                    final int index = this.j.indexOf(a9.a);
                    if (index != -1) {
                        (this.n[n] = (j)this.k.elementAt(index)).a(this, n);
                        this.f.b(a9);
                        if (a9.f != null) {
                            this.f.a(a9);
                        }
                    }
                    ++n;
                }
                if (n < this.l) {
                    continue;
                }
                break;
            }
            this.i = new float[3 * this.g];
            this.h = new bb[this.d.b];
            final Object element = this;
            if (!l) {
                this.i();
                return;
            }
            continue;
        }
    }
    
    void a(final boolean b) {
        if (b) {
            this.g();
            if (!c.l) {
                return;
            }
        }
        this.h();
    }
    
    void g() {
        int n = 0;
        while (true) {
            Label_0031: {
                if (!c.l) {
                    break Label_0031;
                }
                if (this.n[n] != null) {
                    this.n[n].a(this, n);
                }
                ++n;
            }
            if (n >= this.l) {
                return;
            }
            continue;
        }
    }
    
    void h() {
        int n = 0;
        while (true) {
            Label_0039: {
                if (!c.l) {
                    break Label_0039;
                }
                if (this.n[n] != null) {
                    this.n[n].a(this, this.m[n].a);
                }
                ++n;
            }
            if (n >= this.l) {
                return;
            }
            continue;
        }
    }
    
    void i() {
        final boolean l = c.l;
        int n = 0;
        while (true) {
            Label_0027: {
                if (!l) {
                    break Label_0027;
                }
                this.a(this.m[n]);
                ++n;
            }
            if (n < this.l) {
                continue;
            }
            break;
        }
        int n2 = 0;
        while (true) {
            Label_0057: {
                if (!l) {
                    break Label_0057;
                }
                this.b(this.m[n2]);
                ++n2;
            }
            if (n2 >= this.l) {
                return;
            }
            continue;
        }
    }
    
    void a(final a9 a9) {
        final float[] a10 = this.e.a;
        final int[] a11 = this.f.a;
        int n = 0;
        while (true) {
            Label_0151: {
                if (!c.l) {
                    break Label_0151;
                }
                final int a12 = a9.e[n].a;
                final int n2 = a12 * 9;
                final int n3 = a11[n2];
                final int n4 = a11[n2 + 1];
                final int n5 = a11[n2 + 2];
                if (this.h[a12] == null) {
                    this.h[a12] = new bb();
                }
                bb.a(a10[n3], a10[n3 + 1], a10[n3 + 2], a10[n5], a10[n5 + 1], a10[n5 + 2], a10[n4], a10[n4 + 1], a10[n4 + 2], this.h[a12]);
                ++n;
            }
            if (n >= a9.b) {
                return;
            }
            continue;
        }
    }
    
    void b(final a9 a9) {
        final boolean l = c.l;
        final bb bb = new bb();
        final int h = a9.h;
    Label_0080_Outer:
        for (int i = 0; i < a9.g; ++i) {
            bb.a = 0.0f;
            bb.b = 0.0f;
            bb.c = 0.0f;
            final int[] array = a9.j[i];
            final int n = array[0];
            int n2 = 1;
            while (true) {
                while (true) {
                    Label_0083: {
                        if (!l) {
                            break Label_0083;
                        }
                        bb.a(this.h[array[n2]]);
                        ++n2;
                    }
                    if (n2 < n) {
                        continue Label_0080_Outer;
                    }
                    break;
                }
                bb.b();
                final int n3 = h + i * 3;
                this.i[n3] = bb.a;
                this.i[n3 + 1] = bb.b;
                this.i[n3 + 2] = bb.c;
                if (l) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void a(String substring, final j j) {
        final boolean l = c.l;
        if (substring.endsWith("triangleSet")) {
            substring = substring.substring(0, substring.length() - 11);
        }
        if (this.d != null) {
            ba ba = null;
            j i = null;
            int k = 0;
            while (true) {
                while (k < this.l) {
                    ba = this.m[k];
                    if (ba.a.equals(substring)) {
                        i = this.n[k];
                        if (i == j) {
                            return;
                        }
                        if (i != null) {
                            i.a(this, ba.a);
                            break;
                        }
                        break;
                    }
                    else {
                        ++k;
                    }
                }
                if (l) {
                    continue;
                }
                break;
            }
            if (k != this.l) {
                (this.n[k] = j).a(this, k);
            }
        }
        final int index = this.j.indexOf(substring);
        if (index == -1) {
            this.j.addElement(substring);
            this.k.addElement(j);
            j.f();
            if (!l) {
                return;
            }
        }
        final j m = this.k.elementAt(index);
        if (m != j) {
            m.g();
            this.k.setElementAt(j, index);
            j.f();
        }
    }
    
    void j() {
        ++this.t;
    }
    
    void k() {
        --this.t;
        if (this.t == 0) {
            this.b();
        }
    }
}
