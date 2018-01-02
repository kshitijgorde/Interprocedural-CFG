import java.util.Enumeration;
import java.awt.Font;
import java.util.Properties;
import java.util.Hashtable;
import java.util.Vector;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class s
{
    public d a;
    public String b;
    public String c;
    public s d;
    public int e;
    public l f;
    public k g;
    public boolean h;
    public boolean i;
    public boolean j;
    public String k;
    public String l;
    public URL m;
    public URL n;
    public Vector o;
    public int p;
    public Vector q;
    public Hashtable r;
    public Vector s;
    public Vector t;
    public String u;
    public int v;
    public Vector w;
    public Vector x;
    public String y;
    public String z;
    public String aa;
    public Vector ab;
    public Vector ac;
    public Hashtable ad;
    public boolean ae;
    public boolean af;
    public t ag;
    public String ah;
    public boolean ai;
    public String aj;
    public int ak;
    public boolean al;
    
    public s(final d a, final Properties properties, final String b, final String c) {
        this.e = -1;
        this.j = false;
        this.l = null;
        this.o = new Vector();
        this.q = new Vector();
        this.r = new Hashtable();
        this.ac = new Vector();
        this.ad = new Hashtable();
        this.a = a;
        this.b = b;
        this.c = c;
        (this.f = new l(this.a)).a(this.a.k());
        (this.g = new k(this.a, properties)).a(this.a.k());
        this.o = new Vector(3);
    }
    
    public s(final s d, final int e) {
        this.e = -1;
        this.j = false;
        this.l = null;
        this.o = new Vector();
        this.q = new Vector();
        this.r = new Hashtable();
        this.ac = new Vector();
        this.ad = new Hashtable();
        this.a = d.a;
        this.d = d;
        this.e = e;
        (this.f = new l(this.d.f)).a(this.a.k());
    }
    
    public final String a() {
        return (this.d == null) ? this.b : this.d.a();
    }
    
    public final String b() {
        return (this.d == null) ? this.c : this.d.b();
    }
    
    public final k c() {
        return (this.d == null) ? this.g : this.d.c();
    }
    
    public void a(final boolean h) {
        if (this.d == null) {
            this.h = h;
        }
        else {
            this.d.a(h);
        }
    }
    
    public void a(final String k) {
        if (this.d == null) {
            this.i = (this.k == null || k == null || !this.k.equals(k));
            this.k = k;
        }
        else {
            this.d.a(k);
        }
    }
    
    public boolean d() {
        return (this.d == null) ? this.i : this.d.d();
    }
    
    public void b(final String l) {
        this.l = l;
    }
    
    public String b(final boolean b) {
        String l;
        if ((l = this.l) == null) {
            if (b) {
                l = ((this.d == null) ? d.b : ("Submap " + (this.e + 1)));
            }
            else {
                l = "";
            }
        }
        return l;
    }
    
    public void a(final URL m) {
        if (this.d == null) {
            this.m = m;
        }
        else {
            this.d.a(m);
        }
    }
    
    public void b(final URL n) {
        if (this.d == null) {
            this.n = n;
        }
        else {
            this.d.b(n);
        }
    }
    
    public URL e() {
        return (this.d == null) ? this.n : this.d.e();
    }
    
    public void a(final int n) {
        for (int i = this.o.size(); i < n; ++i) {
            this.o.addElement(new s(this, i));
        }
    }
    
    public int f() {
        return this.o.size();
    }
    
    public s b(final int n) {
        return this.o.elementAt(n);
    }
    
    public void c(final int p) {
        if (this.d == null) {
            for (int i = 0; i < this.f(); ++i) {
                this.b(i).c(p);
            }
        }
        else {
            this.p = p;
        }
    }
    
    public int g() {
        int n;
        if (this.d == null) {
            n = 1;
            for (int i = 0; i < this.f(); ++i) {
                n = Math.max(n, this.b(i).g());
            }
        }
        else {
            n = this.p;
        }
        return n;
    }
    
    public void a(final y y) {
        if (!this.q.contains(y.f)) {
            this.q.addElement(y.f);
            this.r.put(y.f, y);
            if (this.d != null) {
                this.d.a(y);
            }
        }
    }
    
    public int h() {
        return this.q.size();
    }
    
    public y d(final int n) {
        return this.c(this.q.elementAt(n));
    }
    
    public y c(final String s) {
        return this.r.get(s);
    }
    
    public void a(final String s, final Font font) {
        if (this.s == null) {
            this.s = new Vector(1);
            this.t = new Vector(1);
        }
        this.s.addElement(s);
        this.t.addElement(font);
    }
    
    public Vector i() {
        return this.s;
    }
    
    public Vector j() {
        return this.t;
    }
    
    public void a(final String u, final int v) {
        this.u = u;
        this.v = v;
    }
    
    public String k() {
        return this.u;
    }
    
    public final void b(final String s, final int n) {
        if (this.w == null) {
            this.w = new Vector(1);
            this.x = new Vector(1);
        }
        this.w.addElement(s);
        this.x.addElement(new Integer(n));
    }
    
    public Vector l() {
        return this.w;
    }
    
    public int e(final int n) {
        return (n == 0) ? this.v : this.x.elementAt(n - 1);
    }
    
    public final void a(final String y, final String z, String aa) {
        this.z = z;
        if (z == null) {
            aa = null;
        }
        this.aa = aa;
        this.y = y;
        if (this.c().ad && this.m() != null && this.n() != null) {
            if (this.ab == null) {
                this.ab = new Vector(1);
            }
            this.ab.insertElementAt(new ab(this.m(), this.n(), this.o()), 0);
        }
    }
    
    public String m() {
        return this.y;
    }
    
    public String n() {
        return this.z;
    }
    
    public String o() {
        return this.aa;
    }
    
    public void b(final String s, final String s2, final String s3) {
        if (this.ab == null) {
            this.ab = new Vector(1);
        }
        this.ab.addElement(new ab(s, s2, s3));
    }
    
    public Vector p() {
        return this.ab;
    }
    
    public void d(final String s) {
        if (!this.ac.contains(s)) {
            this.ac.addElement(s);
            this.ad.put(s, new aa(this.a, this.e, this, s));
        }
    }
    
    public int q() {
        int size;
        if (this.d == null) {
            size = 0;
            for (int i = 0; i < this.f(); ++i) {
                size += this.b(i).q();
            }
        }
        else {
            size = this.ac.size();
        }
        return size;
    }
    
    public aa f(final int n) {
        return this.e(this.ac.elementAt(n));
    }
    
    public aa e(final String s) {
        return this.ad.get(s);
    }
    
    public synchronized void r() {
        if (this.d == null) {
            for (int i = 0; i < this.f(); ++i) {
                this.b(i).r();
            }
        }
        else {
            final y d;
            if ((d = this.f.d()) != null) {
                this.a(this.ac, 0, this.ac.size() - 1, d, true, this.f.e(), this.f.f());
            }
            else {
                this.s();
            }
        }
    }
    
    public synchronized void s() {
        if (this.d == null) {
            for (int i = 0; i < this.f(); ++i) {
                this.b(i).s();
            }
        }
        else if (this.f.d() != null) {
            this.r();
        }
        else {
            final y e;
            if ((e = this.f.e()) != null) {
                this.a(this.ac, 0, this.ac.size() - 1, e, this.f.f(), null, true);
            }
        }
    }
    
    private void a(final Vector vector, final int n, final int n2, final y y, final boolean b, final y y2, final boolean b2) {
        if (n < n2) {
            final int b3 = this.b(vector, n, n2, y, b, y2, b2);
            this.a(vector, n, b3 - 1, y, b, y2, b2);
            this.a(vector, b3 + 1, n2, y, b, y2, b2);
        }
    }
    
    private int b(final Vector vector, final int n, int n2, final y y, final boolean b, final y y2, final boolean b2) {
        int n3 = n - 1;
        final aa aa = this.ad.get(vector.elementAt(n2));
        while (true) {
            if (this.a(this.ad.get(vector.elementAt(++n3)), aa, y, b, y2, b2) < 0) {
                continue;
            }
            while (this.a(aa, this.ad.get(vector.elementAt(--n2)), y, b, y2, b2) < 0 && n != n2) {}
            if (n3 >= n2) {
                break;
            }
            final String s = vector.elementAt(n3);
            vector.setElementAt(vector.elementAt(n2), n3);
            vector.setElementAt(s, n2);
        }
        final String s2 = vector.elementAt(n3);
        vector.setElementAt(vector.elementAt(n2), n3);
        vector.setElementAt(s2, n2);
        return n3;
    }
    
    private int a(final aa aa, final aa aa2, final y y, final boolean b, final y y2, final boolean b2) {
        final int a = y.a(aa.b(y.f), aa2.b(y.f));
        int n;
        if (a == 0 && y2 != null) {
            n = (b2 ? 1 : -1) * y2.a(aa.b(y2.f), aa2.b(y2.f));
        }
        else {
            n = (b ? 1 : -1) * a;
        }
        return n;
    }
    
    public Vector a(Vector a) {
        if (this.d == null) {
            a = null;
        }
        else {
            a = this.a.d().a(a, this, this.p(), null);
        }
        return a;
    }
    
    public void t() {
        if (this.d == null) {
            boolean b = true;
            double min = Double.MAX_VALUE;
            double n = Double.MIN_VALUE;
            final y b2;
            if ((b2 = this.f.b()) != null) {
                for (int i = 0; i < this.f(); ++i) {
                    final s b3 = this.b(i);
                    for (int j = 0; j < b3.q(); ++j) {
                        final Object c = b3.f(j).c(b2.f);
                        if (c != null && c instanceof Number) {
                            b = false;
                            min = Math.min(min, ((Number)c).doubleValue());
                            n = Math.max(n, ((Number)c).doubleValue());
                        }
                    }
                }
            }
            if (b) {
                this.f.a((Double)null);
                this.f.b((Double)null);
            }
            else {
                this.f.a(new Double(min));
                this.f.b(new Double(n));
            }
        }
    }
    
    public void u() {
        if (this.d == null) {
            this.c().a(new Integer(this.a.i().a(this, this.c().a(this.q()))));
        }
    }
    
    public void c(final boolean al) {
        this.al = al;
        if (al) {
            this.k = null;
        }
        if (this.d == null) {
            for (int i = 0; i < this.f(); ++i) {
                this.b(i).c(al);
            }
        }
        else if (al) {
            this.ac.removeAllElements();
            this.ad.clear();
        }
    }
    
    public boolean v() {
        return this.al;
    }
    
    public boolean a(final int n, final int n2) {
        return this.a(this.ac.elementAt(n), this.ac.elementAt(n2));
    }
    
    public boolean a(final String s, final String s2) {
        boolean equals = true;
        final y d;
        if ((d = this.f.d()) != null) {
            equals = d.h.a(this.e(s).b(d.f)).equals(d.h.a(this.e(s2).b(d.f)));
        }
        return equals;
    }
    
    public void w() {
        this.z = null;
        this.aa = null;
        this.u = null;
        this.v = 0;
        this.w = null;
        this.x = null;
        this.s = null;
        this.t = null;
        this.ab = null;
        for (int i = 0; i < this.f(); ++i) {
            this.b(i).w();
        }
    }
    
    public void a(final int ak, final String aj) {
        if (this.d == null) {
            this.ai = true;
            this.ak = ak;
            this.aj = aj;
        }
        else {
            this.d.a(ak, aj);
        }
    }
    
    public boolean x() {
        return (this.d != null) ? this.d.x() : (this.ai && this.aj != null);
    }
    
    public void b(final int ak, final String aj) {
        if (this.d == null) {
            this.ai = false;
            this.ak = ak;
            this.aj = aj;
        }
        else {
            this.d.b(ak, aj);
        }
    }
    
    public boolean y() {
        return (this.d != null) ? this.d.y() : (!this.ai && this.aj != null);
    }
    
    public int z() {
        return (this.d == null) ? this.ak : this.d.z();
    }
    
    public String aa() {
        return (this.d == null) ? this.aj : this.d.aa();
    }
    
    public aa ab() {
        return (this.d == null) ? ((this.aj == null) ? null : this.b(this.ak).e(this.aj)) : this.d.ab();
    }
    
    public void ac() {
        if (this.d == null) {
            this.aj = null;
        }
        else {
            this.d.ac();
        }
    }
    
    public boolean ad() {
        return (this.d == null) ? (this.aj == null) : this.d.ad();
    }
    
    public void d(final boolean ae) {
        this.ae = ae;
    }
    
    public void e(final boolean af) {
        this.af = af;
    }
    
    public void ae() {
        if (this.f != null) {
            this.f.i();
            this.f = null;
        }
        if (this.ac != null) {
            this.ac.removeAllElements();
            this.ac = null;
        }
        if (this.ad != null) {
            final Enumeration keys = this.ad.keys();
            while (keys != null && keys.hasMoreElements()) {
                final Object nextElement;
                final Object value;
                if ((nextElement = keys.nextElement()) != null && (value = this.ad.get(nextElement)) instanceof aa) {
                    ((aa)value).a();
                }
            }
            this.ad.clear();
            this.ad = null;
        }
        if (this.t != null) {
            this.t.removeAllElements();
            this.t = null;
        }
        if (this.s != null) {
            this.s.removeAllElements();
            this.s = null;
        }
        this.z = null;
        this.aa = null;
        if (this.q != null) {
            this.q.removeAllElements();
            this.q = null;
        }
        if (this.r != null) {
            final Enumeration keys2 = this.r.keys();
            while (keys2 != null && keys2.hasMoreElements()) {
                final Object nextElement2;
                final Object value2;
                if ((nextElement2 = keys2.nextElement()) != null && (value2 = this.r.get(nextElement2)) instanceof y) {
                    ((y)value2).c();
                }
            }
            this.r.clear();
            this.r = null;
        }
        this.u = null;
        if (this.w != null) {
            this.w.removeAllElements();
            this.w = null;
        }
        if (this.x != null) {
            this.x.removeAllElements();
            this.x = null;
        }
        this.b = null;
        this.m = null;
        this.k = null;
        if (this.g != null) {
            this.g.e();
            this.g = null;
        }
        if (this.ab != null) {
            for (int i = 0; i < this.ab.size(); ++i) {
                ((ab)this.ab.elementAt(i)).c();
            }
            this.ab.removeAllElements();
            this.ab = null;
        }
        this.d = null;
        this.aj = null;
        this.ah = null;
        if (this.o != null) {
            for (int j = 0; j < this.o.size(); ++j) {
                ((s)this.o.elementAt(j)).ae();
            }
            this.o.removeAllElements();
            this.o = null;
        }
        this.l = null;
        this.a = null;
    }
    
    public void a(final t ag) {
        if (ag == null) {
            return;
        }
        if (this.d == null) {
            this.ag = ag;
        }
        else {
            this.d.a(ag);
        }
    }
    
    public t af() {
        if (this.d != null) {
            this.ag = this.d.af();
        }
        return this.ag;
    }
}
