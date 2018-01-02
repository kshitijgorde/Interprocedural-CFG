import java.util.Enumeration;
import java.awt.Color;
import java.util.Properties;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class l
{
    public d a;
    public l b;
    public Vector c;
    public Vector d;
    public y e;
    public Vector f;
    public Vector g;
    public y h;
    public Vector i;
    public y j;
    public boolean k;
    public Hashtable l;
    public m m;
    
    public void a(final Properties properties) {
        if (this.m != null) {
            this.m.a(properties);
        }
    }
    
    public l(final d a) {
        this.c = new Vector(3);
        this.d = new Vector();
        this.f = new Vector();
        this.g = new Vector();
        this.i = new Vector();
        this.k = true;
        this.l = new Hashtable();
        this.a = a;
        this.m = new m(a);
    }
    
    public l(final l b) {
        this.c = new Vector(3);
        this.d = new Vector();
        this.f = new Vector();
        this.g = new Vector();
        this.i = new Vector();
        this.k = true;
        this.l = new Hashtable();
        this.a = b.a;
        this.b = b;
        this.b.c.addElement(this);
        this.m = new m(this.b.m);
    }
    
    public void a(final int n) {
        this.m.c(new Integer(n));
    }
    
    public int a() {
        return this.l().m.i();
    }
    
    public void a(final Double n) {
        if (this.b == null) {
            this.m.a(n);
        }
        else {
            this.b.a(n);
        }
    }
    
    public void b(final Double n) {
        if (this.b == null) {
            this.m.b(n);
        }
        else {
            this.b.b(n);
        }
    }
    
    public void a(final int n, final Color color) {
        switch (n) {
            case 0: {
                this.m.b(color);
                break;
            }
            case 1: {
                this.m.c(color);
                break;
            }
            case 2: {
                this.m.d(color);
                break;
            }
        }
    }
    
    public Color b(final int n) {
        Color color = null;
        switch (n) {
            case 0: {
                color = this.l().m.f();
                break;
            }
            case 1: {
                color = this.l().m.g();
                break;
            }
            case 2: {
                color = this.l().m.h();
                break;
            }
        }
        return color;
    }
    
    public void a(final y y) {
        if (this.b == null) {
            if (!this.d.contains(y)) {
                this.a(this.d, y);
            }
        }
        else {
            this.b.a(y);
        }
    }
    
    public void b(final y e) {
        if (this.b == null) {
            if (e == null || this.d.contains(e)) {
                this.e = e;
            }
        }
        else {
            this.b.b(e);
        }
    }
    
    public final y b() {
        return (this.b == null) ? this.e : this.b.b();
    }
    
    public String c() {
        String s = "";
        if (this.b() != null) {
            s = this.b().a();
            switch (this.k()) {
                case 0: {
                    s += " (BAL)";
                    break;
                }
                case 1: {
                    s += " (ACT)";
                    break;
                }
                case 2: {
                    s += " (FIX)";
                    break;
                }
            }
        }
        return s;
    }
    
    public final Color a(final Number n) {
        return this.l().m.a(n);
    }
    
    public final Double[] c(final int n) {
        return this.l().m.a(n);
    }
    
    public final Color d(final int n) {
        return this.l().m.b(n);
    }
    
    public void c(final y y) {
        if (!this.g.contains(y)) {
            this.a(this.g, y);
            if (this.b != null) {
                this.b.c(y);
            }
        }
    }
    
    public void d(final y h) {
        if (h == null || this.g.contains(h)) {
            this.h = h;
            for (int i = 0; i < this.c.size(); ++i) {
                ((l)this.c.elementAt(i)).d(h);
            }
            if (this.b != null) {
                this.b.g();
            }
        }
    }
    
    public y d() {
        return this.h;
    }
    
    public final void e(final y y) {
        if (!this.i.contains(y)) {
            this.a(this.i, y);
            if (this.b != null) {
                this.b.e(y);
            }
        }
    }
    
    public final void f(final y j) {
        if (j == null || this.i.contains(j)) {
            this.j = j;
            for (int i = 0; i < this.c.size(); ++i) {
                ((l)this.c.elementAt(i)).f(j);
            }
            if (this.b != null) {
                this.b.h();
            }
        }
    }
    
    public final y e() {
        return this.j;
    }
    
    public final void a(final boolean k) {
        this.k = k;
        for (int i = 0; i < this.c.size(); ++i) {
            ((l)this.c.elementAt(i)).a(k);
        }
        if (this.b != null) {
            this.b.h();
        }
    }
    
    public final boolean f() {
        return this.k;
    }
    
    public void a(final y y, final l l) {
        if (this.b == null) {
            if (!this.l.containsKey(y)) {
                this.l.put(y, l);
            }
        }
        else {
            this.b.a(y, l);
        }
    }
    
    private l l() {
        return this.g(this.b());
    }
    
    private final l g(final y y) {
        l g;
        if (this.b == null) {
            g = this.l.get(y);
            if (g == null) {
                g = this;
            }
        }
        else {
            g = this.b.g(y);
        }
        return g;
    }
    
    private void a(final Vector vector, final y y) {
        int i;
        for (i = 0; i < vector.size(); ++i) {
            if (y.a().compareTo(vector.elementAt(i).a()) < 0) {
                vector.insertElementAt(y, i);
                break;
            }
        }
        if (i >= vector.size()) {
            vector.addElement(y);
        }
    }
    
    public final void g() {
        y h = this.h;
        for (int i = 0; i < this.c.size(); ++i) {
            final y d = this.c.elementAt(i).d();
            if (i == 0) {
                h = d;
            }
            else if (d != null) {
                if (d != null || h != null) {
                    if (d == null || h == null || !d.equals(h)) {
                        h = null;
                        break;
                    }
                }
            }
        }
        this.h = h;
    }
    
    public final void h() {
        y j = this.j;
        for (int i = 0; i < this.c.size(); ++i) {
            final y e = this.c.elementAt(i).e();
            if (i == 0) {
                j = e;
            }
            else if (e != null) {
                if (e != null || j != null) {
                    if (e == null || j == null || !e.equals(j)) {
                        j = null;
                        break;
                    }
                }
            }
        }
        this.j = j;
        Boolean b = new Boolean(this.k);
        for (int k = 0; k < this.c.size(); ++k) {
            final Boolean b2 = new Boolean(this.c.elementAt(k).f());
            if (k == 0) {
                b = b2;
            }
            else if (b2 != null) {
                if (b2 != null || b != null) {
                    if (b2 == null || b == null || !b2.equals(b)) {
                        b = null;
                        break;
                    }
                }
            }
        }
        if (b != null) {
            this.k = b;
        }
    }
    
    public int a(final int n, final int n2) {
        return this.l().m.a(n, n2);
    }
    
    public int b(final int n, final int n2) {
        return this.l().m.b(n, n2);
    }
    
    public int c(final int n, final int n2) {
        return this.l().m.c(n, n2);
    }
    
    public final void i() {
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); ++i) {
                ((l)this.c.elementAt(i)).i();
            }
            this.c.removeAllElements();
            this.c = null;
        }
        if (this.e != null) {
            this.e.c();
            this.e = null;
        }
        if (this.d != null) {
            for (int j = 0; j < this.d.size(); ++j) {
                ((y)this.d.elementAt(j)).c();
            }
            this.d.removeAllElements();
            this.d = null;
        }
        if (this.h != null) {
            this.h.c();
            this.h = null;
        }
        if (this.g != null) {
            for (int k = 0; k < this.g.size(); ++k) {
                ((y)this.g.elementAt(k)).c();
            }
            this.g.removeAllElements();
            this.g = null;
        }
        if (this.j != null) {
            this.j.c();
            this.j = null;
        }
        if (this.i != null) {
            for (int l = 0; l < this.i.size(); ++l) {
                ((y)this.i.elementAt(l)).c();
            }
            this.i.removeAllElements();
            this.i = null;
        }
        if (this.m != null) {
            this.m.n();
            this.m = null;
        }
        if (this.l != null) {
            final Enumeration<l> elements = this.l.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().i();
            }
            this.l.clear();
            this.l = null;
        }
        this.b = null;
        this.a = null;
    }
    
    public void a(final Integer n) {
        this.m.a(n);
    }
    
    public int j() {
        return this.l().m.d();
    }
    
    public void b(final Integer n) {
        this.m.b(n);
    }
    
    public int k() {
        return this.l().m.e();
    }
    
    public void a(final Boolean b) {
        this.m.a(b);
    }
    
    public void c(final Double n) {
        this.m.c(n);
    }
    
    public void d(final Double n) {
        this.m.d(n);
    }
    
    public void e(final Double n) {
        this.m.e(n);
    }
}
