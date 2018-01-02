import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Component;
import java.util.Vector;
import java.awt.Image;
import sexy.gui.SexyApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class k
{
    public l a;
    public SexyApplet b;
    public Image c;
    public j d;
    public boolean e;
    public int f;
    public int g;
    public l h;
    public Vector i;
    public boolean j;
    public l k;
    public l l;
    public int m;
    public l n;
    public l o;
    public int p;
    public int q;
    public boolean r;
    public int s;
    public int t;
    public Component u;
    public m v;
    public j w;
    
    public Image a() {
        if (this.f != this.s || this.g != this.t) {
            this.f = this.s;
            this.g = this.t;
            final int[] c = new int[this.f * this.g];
            this.w = new j();
            this.w.c = c;
            this.w.a = this.f;
            this.w.b = this.g;
            this.w.a(false, false);
            this.v = new m(this.f, this.g, new DirectColorModel(32, 16711680, 65280, 255), c);
            this.c = this.b.createImage(this.v);
            if (this.d != null) {
                this.d = null;
            }
        }
        return this.c;
    }
    
    public void a(final l o) {
        this.o = o;
        if (this.n != null && this.a(this.n, this.o)) {
            this.n.d();
            this.n = null;
        }
        if (this.l != null && this.a(this.l, this.o)) {
            this.l.a(this.p - this.l.a, this.q - this.l.b, this.m);
            this.l = null;
        }
    }
    
    public void b(final l l) {
        if (this.n == l) {
            final l n = this.n;
            this.n = null;
            n.d();
        }
        if (this.l == l) {
            final l i = this.l;
            this.l = null;
            i.a(this.p - i.a, this.q - i.b, this.m);
        }
        if (this.k == l) {
            final l k = this.k;
            this.k = null;
            k.e();
        }
        if (this.o == l) {
            this.o = null;
        }
    }
    
    public l a(final int n, final int n2) {
        int n3 = 0;
        int i = this.i.size() - 1;
        while (i >= 0) {
            final l l = this.i.elementAt(i);
            if (n3 == 0 && l.f && l.g && l.f().contains(n, n2)) {
                if (!l.i) {
                    return l;
                }
                return null;
            }
            else {
                if (l == this.o) {
                    n3 = 1;
                }
                --i;
            }
        }
        return null;
    }
    
    public void b() {
        if (this.c != null) {
            this.b.a(this.c);
            this.c = null;
        }
    }
    
    public void b(final int s, final int t) {
        this.s = s;
        this.t = t;
    }
    
    public void c() {
        final Enumeration<l> elements = this.i.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().e = true;
        }
    }
    
    public boolean a(final l l, final l i) {
        if (i == null || !this.i.contains(i)) {
            return false;
        }
        for (int j = 0; j < this.i.size(); ++j) {
            if (this.i.elementAt(j) == i) {
                return false;
            }
            if (this.i.elementAt(j) == l) {
                return true;
            }
        }
        return false;
    }
    
    public void c(final l l) {
        this.i.addElement(l);
        l.b(this);
        this.i(l);
    }
    
    public void d(final l l) {
        this.i.removeElement(l);
        this.i.addElement(l);
    }
    
    public void b(final l l, final l i) {
        this.i.removeElement(l);
        this.i.insertElementAt(l, this.i.indexOf(i) + 1);
    }
    
    public boolean a(final int n, final boolean b, final boolean b2) {
        synchronized (this.b) {
            if (n == 9 && b2) {
                if (this.a != null) {
                    this.a.a(n, b, b2);
                }
                // monitorexit(this.b)
                return true;
            }
            if (this.k != null && !this.a(this.k, this.o)) {
                this.k.a(n, b, b2);
            }
        }
        // monitorexit(this.b)
        return true;
    }
    
    public void e(final l k) {
        if (this.k != null) {
            this.k.e();
        }
        this.k = k;
        if (this.j && this.k != null) {
            this.k.i();
        }
    }
    
    public void d() {
        if (this.j) {
            this.j = false;
            if (this.k != null) {
                this.k.e();
            }
        }
    }
    
    public void f(final l l) {
        this.b(l);
        this.g(l);
        this.i.removeElement(l);
        l.a(this);
    }
    
    public void e() {
        if (this.h != null) {
            this.f(this.h);
            this.h = null;
        }
    }
    
    public boolean c(final int n, final int n2) {
        synchronized (this.b) {
            this.r = false;
            if (this.n != null) {
                this.n.d();
                this.n = null;
            }
        }
        // monitorexit(this.b)
        return true;
    }
    
    public void a(final Graphics graphics) {
        if (this.c != null) {
            graphics.drawImage(this.c, 0, 0, null);
        }
    }
    
    public k(final SexyApplet b, final Component u) {
        this.i = new Vector();
        this.j = true;
        this.b = b;
        this.u = u;
    }
    
    public void a(final l l, final boolean i) {
        if (i) {
            this.b(l);
        }
        l.i = i;
        l.j();
        if (!i && l.a(this.p, this.q)) {
            this.d(this.p, this.q);
        }
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        synchronized (this.b) {
            if (this.l != null) {
                final l l = this.l;
                this.l = null;
                l.a(n - l.a, n2 - l.b, n3);
            }
            this.d(n, n2);
        }
        // monitorexit(this.b)
        return true;
    }
    
    public synchronized void f() {
        final Enumeration<l> elements = this.i.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().b();
        }
    }
    
    public boolean b(final int n, final boolean b, final boolean b2) {
        synchronized (this.b) {
            if (n == 9 && b2) {
                // monitorexit(this.b)
                return true;
            }
            if (this.k != null && !this.a(this.k, this.o)) {
                this.k.b(n, b, b2);
            }
        }
        // monitorexit(this.b)
        return true;
    }
    
    public void g(final l l) {
        l.e = true;
        if (l.h) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.i.size(); ++i) {
            if (this.i.elementAt(i) == l) {
                n = i;
                break;
            }
        }
        for (int j = n - 1; j >= 0; --j) {
            final l k = this.i.elementAt(j);
            if (k.f && !k.r && !k.s && k.a(l.a, l.b) && k.a(l.a + l.c - 1, l.b + l.d - 1)) {
                k.j();
                break;
            }
            if (k.a(l)) {
                this.i(k);
            }
        }
        for (int n2 = n + 1; n2 < this.i.size(); ++n2) {
            final l m = this.i.elementAt(n2);
            if (m.a(l)) {
                this.i(m);
            }
        }
    }
    
    public void g() {
        if (!this.j) {
            this.j = true;
            if (this.k != null) {
                this.k.i();
            }
        }
    }
    
    public void finalize() {
        synchronized (this.b) {
            this.b();
        }
        // monitorexit(this.b)
    }
    
    public void h(final l l) {
        this.i.removeElement(l);
        this.i.insertElementAt(l, 0);
    }
    
    public void d(final int p2, final int q) {
        this.p = p2;
        this.q = q;
        final l a = this.a(p2, q);
        if (a != this.n) {
            if (this.n != null) {
                this.n.d();
            }
            if ((this.n = a) != null) {
                a.a();
            }
        }
        if (a != null) {
            a.e(p2 - a.a, q - a.b);
        }
    }
    
    public boolean b(final int n, final int n2, final int n3) {
        synchronized (this.b) {
            this.d(n, n2);
            if (this.h != null && !this.h.a(n, n2)) {
                this.e();
            }
            final l a = this.a(n, n2);
            if (this.l != null && this.l != a) {
                this.l.a(n - this.l.a, n2 - this.l.b, this.m);
            }
            this.l = a;
            if (n3 < 0) {
                this.m = -1;
            }
            else {
                this.m = 1;
            }
            if (a != null) {
                if (a.h()) {
                    this.e(a);
                }
                a.b(n - a.a, n2 - a.b, n3);
            }
        }
        // monitorexit(this.b)
        return true;
    }
    
    public void i(final l l) {
        if (l.e) {
            return;
        }
        l.e = true;
        if (l.h) {
            return;
        }
        if (l.s) {
            this.g(l);
            return;
        }
        boolean b = false;
        final Enumeration<l> elements = this.i.elements();
        while (elements.hasMoreElements()) {
            final l i = elements.nextElement();
            if (i.equals(l)) {
                b = true;
            }
            else {
                if (!b || !i.a(l)) {
                    continue;
                }
                this.i(i);
            }
        }
    }
    
    public void h() {
        this.a();
        if (this.c == null) {
            return;
        }
        boolean b = false;
        int n = 0;
        boolean e = false;
        boolean b2 = false;
        for (int i = 0; i < this.i.size(); ++i) {
            final l l = this.i.elementAt(i);
            if (l.e) {
                ++n;
            }
            if (l.h) {
                e = true;
                if (l.e) {
                    b2 = true;
                }
            }
        }
        final n n2 = new n(this.w);
        if (n > 0 || this.e != e) {
            n n3;
            if (e) {
                if (this.d == null) {
                    (this.d = new j()).a(this.f, this.g);
                    this.d.a(false, false);
                }
                n3 = new n(this.d);
            }
            else {
                n3 = n2;
            }
            for (int j = 0; j < this.i.size(); ++j) {
                final l k = this.i.elementAt(j);
                if (k.h) {
                    break;
                }
                if ((k.e || this.e != e) && k.f) {
                    final n n4 = new n(n3);
                    n4.a(k.a, k.b, k.c, k.d);
                    n4.a(k.a, k.b);
                    k.a(n4);
                    n4.a(-k.a, -k.b);
                    ++n;
                    b = true;
                    k.e = false;
                }
            }
        }
        if (e && (n > 0 || b2 || !this.e)) {
            n2.a(this.d, 0, 0);
            boolean b3 = false;
            for (int n5 = 0; n5 < this.i.size(); ++n5) {
                final l m = this.i.elementAt(n5);
                if (m.f && m.h) {
                    b3 = true;
                }
                if (b3) {
                    final n n6 = new n(n2);
                    n6.a(m.a, m.b, m.c, m.d);
                    n6.a(m.a, m.b);
                    m.a(n6);
                    n6.a(-m.a, -m.b);
                    ++n;
                    b = true;
                    m.e = false;
                }
            }
        }
        this.e = e;
        if (b) {
            this.v.a();
            this.u.repaint();
        }
    }
    
    public boolean e(final int p2, final int q) {
        synchronized (this.b) {
            this.p = p2;
            this.q = q;
            if (this.n != null && this.n != this.l) {
                this.n.d();
                this.n = null;
            }
            if (this.l != null) {
                this.l.d(p2 - this.l.a, q - this.l.b);
                if (this.l.a(p2, q)) {
                    if (this.n == null) {
                        (this.n = this.l).a();
                    }
                }
                else if (this.n != null) {
                    this.n.d();
                    this.n = null;
                }
            }
        }
        // monitorexit(this.b)
        return true;
    }
    
    public boolean f(final int n, final int n2) {
        synchronized (this.b) {
            this.r = true;
            this.d(n, n2);
        }
        // monitorexit(this.b)
        return true;
    }
}
