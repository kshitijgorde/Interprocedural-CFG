import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ax extends z implements h
{
    c a;
    Vector b;
    t c;
    az d;
    private boolean e;
    private boolean f;
    private boolean g;
    String h;
    String i;
    String j;
    String k;
    Vector l;
    int m;
    a0[] n;
    a0 p;
    boolean q;
    h r;
    Object s;
    String t;
    b u;
    Vector v;
    Vector w;
    ax x;
    ax y;
    long z;
    boolean aa;
    f ab;
    f ac;
    int ad;
    public int ae;
    int af;
    int ag;
    long ah;
    boolean ai;
    int aj;
    boolean ak;
    int al;
    int am;
    boolean an;
    boolean ao;
    static final as ap;
    static final as aq;
    
    void a(final ar ar) {
        ar.d("StyleSheet");
    }
    
    public void a(final c a) {
        this.a = a;
        this.a.c = this;
    }
    
    public c a() {
        return this.a;
    }
    
    public void a(final t t) {
    }
    
    public ax() {
        this.l = new Vector(1, 5);
        this.q = false;
        this.r = null;
        this.s = null;
        this.v = new Vector();
        this.w = new Vector();
        this.z = 0L;
        this.aa = false;
        this.ab = null;
        this.ad = 0;
        this.ae = 0;
        this.ai = false;
        this.aj = -1;
        this.ak = false;
        this.al = -16777216;
        this.am = 0;
        this.an = false;
        this.ao = false;
        this.a(new c());
    }
    
    ax(final blaze3d blaze3d, final az d, final b u, final String t, final int n, final af af, final int n2) {
        super(blaze3d, u.b, d, n, af, n2);
        this.l = new Vector(1, 5);
        this.q = false;
        this.r = null;
        this.s = null;
        this.v = new Vector();
        this.w = new Vector();
        this.z = 0L;
        this.aa = false;
        this.ab = null;
        this.ad = 0;
        this.ae = 0;
        this.ai = false;
        this.aj = -1;
        this.ak = false;
        this.al = -16777216;
        this.am = 0;
        this.an = false;
        this.ao = false;
        this.a(new c());
        this.u = u;
        this.t = t;
        this.d = d;
        this.af = d.x;
        this.e = !d.s;
        this.ak = d.t;
        this.ai = this.ak;
        this.am = d.u;
        this.an = d.v;
        this.h = d.d;
        this.q = d.w;
        this.p = d.a;
        this.ac = new f(d.c);
        this.g = false;
        this.f = true;
        if (this.u.p().b(this.h, false).a == 0) {
            this.u.p().a(this.d.d, new as(d.e), false);
            this.i = d.e;
        }
        if (this.q) {
            this.c(d.e);
            if (!c.l) {
                return;
            }
        }
        this.a(d.e);
    }
    
    void a(final int n, final String s, final String s2) {
        if (this.af == 1) {
            int n2 = 0;
            while (true) {
                Label_0036: {
                    if (!c.l) {
                        break Label_0036;
                    }
                    this.a(s.charAt(n2), s2.charAt(n2));
                    ++n2;
                }
                if (n2 < s.length()) {
                    continue;
                }
                break;
            }
        }
    }
    
    void a(final int n, final boolean b) {
        if ((n & 0x2) != 0x0 && super.f != 3) {
            final Point b2 = this.b(super.a.t, super.a.u);
            this.ag = this.c(b2.x, b2.y);
            if (this.ag >= 0 && this.ag < this.n.length) {
                final a0 a0 = this.n[this.ag];
                if (!a0.p.equals("")) {
                    super.a.a(a0.p, a0.m);
                    return;
                }
            }
            super.a.as.a(this);
            super.a.as.a(this.ag, this.ag);
            this.a9();
            super.f = 3;
        }
        if ((n & 0x9) != 0x0 && super.f == 3) {
            final Point b3 = this.b(super.a.t, super.a.u);
            super.a.as.a(this.ag, this.c(b3.x, b3.y));
            this.a9();
        }
        if ((n & 0x4) != 0x0 && super.f == 3) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.ah < 300L) {
                super.a.as.a(this.b(this.ag, false), this.b(this.ag, true));
                this.a9();
            }
            this.ah = currentTimeMillis;
            super.f = 1;
        }
    }
    
    boolean d() {
        final boolean l = c.l;
        if (this.f) {
            this.g = false;
            int n = 0;
            boolean b = false;
            while (true) {
                while (true) {
                    Label_0047: {
                        if (!l) {
                            break Label_0047;
                        }
                        Label_0044: {
                            if (this.n[n].p.equals("")) {
                                break Label_0044;
                            }
                            final ax ax = this;
                            ax.g = b;
                        }
                        ++n;
                    }
                    if (n < this.n.length) {
                        continue;
                    }
                    break;
                }
                final ax ax = this;
                b = false;
                if (l) {
                    continue;
                }
                break;
            }
            this.f = b;
        }
        return this.ah() || this.g;
    }
    
    int c() {
        final Point b = this.b(super.a.t, super.a.u);
        final int c = this.c(b.x, b.y);
        if (c >= 0 && c < this.n.length && !this.n[c].p.equals("")) {
            return 12;
        }
        return 2;
    }
    
    int h() {
        return this.j.length();
    }
    
    void a(final boolean b, final ag ag) {
        final boolean l = c.l;
        if (!this.h.equals("")) {
            final String string = this.u.p().b(this.h, false).toString();
            if (!string.equals(this.i)) {
                Label_0076: {
                    if (this.q || this.r != null) {
                        this.c(string);
                        if (!l) {
                            break Label_0076;
                        }
                    }
                    this.a(string);
                }
                this.i = string;
            }
        }
        Label_0169: {
            if (super.s && super.a.v) {
                if (this.af != 1) {
                    break Label_0169;
                }
                final long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.z <= 400L) {
                    break Label_0169;
                }
                this.aa = !this.aa;
                this.z = currentTimeMillis;
                this.k();
                if (!l) {
                    break Label_0169;
                }
            }
            if (this.aa) {
                this.aa = false;
                this.k();
            }
        }
        super.a(b, ag);
    }
    
    void i() {
        if (super.z != null) {
            return;
        }
        super.aa.removeAllElements();
        int d = -1;
        int e = -1;
        if (super.s) {
            d = super.a.as.d;
            e = super.a.as.e;
        }
        this.d.a(this, d, e);
        if (super.s && super.a.as.d == super.a.as.e && this.aa) {
            this.d.a(this);
        }
    }
    
    void a(final e e) {
        e.a(super.l);
        this.u();
    }
    
    public void u() {
        if (this.t != null && this.u.f(this.t) == this.a) {
            this.u.e(this.t);
        }
        if (this.x != null) {
            this.x.y = this.y;
        }
        Label_0090: {
            if (this.y != null) {
                this.y.x = this.x;
                if (!c.l) {
                    break Label_0090;
                }
            }
            this.u.ad = this.x;
        }
        this.a.c = null;
        this.c = null;
    }
    
    f f() {
        if (this.ab == null) {
            this.ap();
        }
        return this.ab;
    }
    
    f g() {
        if (this.ab == null) {
            this.ap();
        }
        this.j();
        return super.j.a(this.ab);
    }
    
    boolean a(final Point point, final boolean b) {
        return this.g().b(point);
    }
    
    void a(final String j) {
        final boolean l = c.l;
        this.j = j;
        this.m = j.length();
        this.n = new a0[this.m];
        if (this.p.g == null) {
            this.p.g = this.d.b.m;
        }
        int n = 0;
        while (true) {
            while (true) {
                Label_0074: {
                    if (!l) {
                        break Label_0074;
                    }
                    final ax ax = this;
                    ax.n[n] = this.p;
                    ++n;
                }
                if (n < this.m) {
                    continue;
                }
                break;
            }
            this.f = true;
            this.ap();
            final ax ax = this;
            if (!l) {
                if (this.ad > this.ax() - 1) {
                    this.f(this.ax());
                }
                if (super.s) {
                    super.a.as.a(super.a.as.d, super.a.as.e);
                }
                this.k();
                this.ap();
                return;
            }
            continue;
        }
    }
    
    public int ab() {
        return this.j.length();
    }
    
    public b af() {
        return this.u.af;
    }
    
    public boolean ah() {
        return this.e;
    }
    
    public void l(final float n) {
        if (!Float.isNaN(n)) {
            this.b(n != 0.0f);
        }
    }
    
    public float ak() {
        return this.l();
    }
    
    public void n(final float n) {
        this.a(n);
    }
    
    public float al() {
        return this.n();
    }
    
    public void p(final float n) {
        this.c(n);
    }
    
    public float am() {
        return this.m();
    }
    
    public void q(final float n) {
        this.b(n);
    }
    
    void ap() {
        final boolean l = c.l;
        if (this.ab == null) {
            this.ab = new f(this.ac);
        }
        this.a8();
        final int n = this.ac.c - this.ac.a;
        final int n2 = this.ac.d - this.ac.b;
        int n3 = 0;
        int n4 = 0;
        if (this.d.c != 0 && !this.d.b) {
            n3 = this.a2() + this.d.k + this.d.l - n + 80;
            if (n3 != 0) {
                Label_0180: {
                    switch (this.d.c) {
                        case 1: {
                            if (l) {
                                break Label_0180;
                            }
                            break;
                        }
                        case 2: {
                            this.ab.a = this.ac.a - n3 / 2;
                            if (l) {
                                break Label_0180;
                            }
                            break;
                        }
                        case 3: {
                            this.ab.a = this.ac.a - n3;
                            break;
                        }
                    }
                }
            }
        }
        this.ab.c = this.ab.a + n + n3;
        this.a8();
        if (this.d.c != 0) {
            n4 = this.a3() - n2 / 20 + 4;
        }
        this.ab.d = this.ab.b + n2 + n4 * 20;
    }
    
    public void c(final String s) {
        this.k = s;
        Label_0042: {
            if (this.q || this.r != null) {
                this.a(this.j = s, "parseHTML");
                if (!c.l) {
                    break Label_0042;
                }
            }
            this.d(s);
        }
        this.k();
    }
    
    public int av() {
        return Math.min(this.v.size(), this.ad + this.a7());
    }
    
    public int ax() {
        final boolean l = c.l;
        final int n = this.ab.d - this.ab.b;
        int n2 = 0;
        int n3 = this.v.size() - 1;
        int n4;
        int n5;
        while (true) {
            Label_0049: {
                if (!l) {
                    break Label_0049;
                }
                n2 += this.h(n3);
                --n3;
            }
            if (n3 < 0) {
                goto Label_0063;
            }
            n4 = n2;
            n5 = n;
            if (!l && n4 <= n5) {
                continue;
            }
            break;
        }
        if (n4 > n5) {
            ++n3;
        }
        return n3 + 2;
    }
    
    public void f(final int n) {
        if (n > 0 && n <= this.ax() && this.ad != n - 1) {
            this.ad = n - 1;
            this.a(ax.aq);
            this.k();
        }
    }
    
    private void a(final as as) {
        final au au = new au();
        au.a(this.a());
        this.a().c(as, au, false);
        this.a(as, au);
    }
    
    public void d(final String i) {
        if (i == null) {
            return;
        }
        if (this.r != null) {
            this.c(i);
            if (!c.l) {
                return;
            }
        }
        this.u.p().a(this.d.d, new as(i), false);
        this.a(this.i = i);
    }
    
    int a2() {
        final boolean l = c.l;
        if (this.m == 0) {
            return 0;
        }
        int n = 0;
        this.d.a(this.n[0], this, -1);
        final int size = this.w.size();
        int n2 = 0;
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0075: {
                    if (!l) {
                        break Label_0075;
                    }
                    final int intValue;
                    n2 = (intValue = this.w.elementAt(n3));
                    final int n4;
                    if (n4 > n) {
                        n = n2;
                    }
                    ++n3;
                }
                if (n3 < size) {
                    continue;
                }
                break;
            }
            final int n4 = n;
            if (!l) {
                return n4;
            }
            continue;
        }
    }
    
    public int a3() {
        final boolean l = c.l;
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0023: {
                    if (!l) {
                        break Label_0023;
                    }
                    final int n3 = n + this.h(n2);
                    final int n4;
                    n = n4;
                    ++n2;
                }
                if (n2 < this.v.size()) {
                    continue;
                }
                break;
            }
            final int n4 = n / 20;
            if (!l) {
                return n4;
            }
            continue;
        }
    }
    
    public String a4() {
        if (this.af == 0) {
            return "dynamic";
        }
        if (this.af == 1) {
            return "input";
        }
        return "";
    }
    
    public void e(final String s) {
        Label_0034: {
            if (s.compareTo("dynamic") == 0) {
                this.af = 0;
                if (!c.l) {
                    break Label_0034;
                }
            }
            if (s.compareTo("input") == 0) {
                this.af = 1;
            }
        }
        this.k();
    }
    
    public String toString() {
        return this.u.toString() + "." + this.t;
    }
    
    private void a(final String s, final String s2) {
        try {
            final Class<?> forName = Class.forName(blaze3d.b.gc("HTMLFormatter"));
            final Class[] array = { this.getClass() };
            forName.getMethod(blaze3d.b.gp(forName.getName(), s2, new String[] { array[0].getName() }), (Class[])array).invoke(null, this);
        }
        catch (Exception ex) {}
    }
    
    public void f(final String s) {
        int d = 0;
        if (super.s) {
            d = super.a.as.d;
            final int e = super.a.as.e;
        }
        this.d(this.j.substring(0, super.a.as.d) + s + this.j.substring(super.a.as.e, this.j.length()));
        final int n = d + s.length();
        super.a.as.a(n, n);
    }
    
    int a(final int n, final int[] array) {
        final int intValue = this.v.elementAt(n);
        final int n2 = (n < this.v.size() - 1) ? this.v.elementAt(n + 1) : this.j.length();
        int l = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = intValue;
        int n6 = 0;
        while (true) {
            Label_0135: {
                if (n5 >= n2) {
                    n6 = n3;
                    if (!c.l) {
                        break;
                    }
                }
                else {
                    final ao g = this.n[n5].g;
                    if (g == null) {
                        break Label_0135;
                    }
                    l = this.n[n5].l;
                    final int n7;
                    n4 = (n7 = l * g.b / g.k);
                }
                if (n6 > n3) {
                    n3 = n4;
                    array[0] = l;
                }
            }
            ++n5;
        }
        return n6;
    }
    
    int b(final int n, final int[] array) {
        final int intValue = this.v.elementAt(n);
        final int n2 = (n < this.v.size() - 1) ? this.v.elementAt(n + 1) : this.j.length();
        int l = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = intValue;
        int n6 = 0;
        while (true) {
            Label_0135: {
                if (n5 >= n2) {
                    n6 = n3;
                    if (!c.l) {
                        break;
                    }
                }
                else {
                    final ao g = this.n[n5].g;
                    if (g == null) {
                        break Label_0135;
                    }
                    l = this.n[n5].l;
                    final int n7;
                    n4 = (n7 = l * g.c / g.k);
                }
                if (n6 > n3) {
                    n3 = n4;
                    array[0] = l;
                }
            }
            ++n5;
        }
        return n6;
    }
    
    int h(final int n) {
        final boolean l = c.l;
        final int intValue = this.v.elementAt(n);
        final int n2 = (n < this.v.size() - 1) ? this.v.elementAt(n + 1) : this.j.length();
        int n3 = 0;
        int n4 = intValue;
        int j = 0;
    Block_8:
        while (true) {
            a0 p = null;
            Label_0101: {
                if (n4 == n2) {
                    p = this.p;
                    if (!l) {
                        break Label_0101;
                    }
                }
                p = this.n[n4];
            }
            final ao g = p.g;
            if (g != null) {
                final int i = p.l;
                int n5 = i * (g.c + g.b) / g.k;
                if (n != this.ad) {
                    n5 += p.i;
                }
                if (i < 360) {
                    n5 = (n5 + 10) / 20 * 20;
                }
                if (n5 > n3) {
                    n3 = n5;
                }
            }
            ++n4;
            while (j >= n2) {
                j = n3;
                if (!l) {
                    break Block_8;
                }
            }
        }
        return j;
    }
    
    int a7() {
        final boolean l = c.l;
        final int n = this.ab.d - this.ab.b;
        int n2 = 0;
        int ad = this.ad;
        while (true) {
        Label_0063:
            while (true) {
                Label_0052: {
                    if (!l) {
                        break Label_0052;
                    }
                    n2 += this.h(ad);
                    final int n3;
                    if (n3 > n) {
                        break Label_0063;
                    }
                    ++ad;
                }
                if (ad < this.v.size()) {
                    continue;
                }
                break;
            }
            final int n3 = ad - this.ad;
            if (!l) {
                return n3;
            }
            continue;
        }
    }
    
    void a8() {
        final boolean l = c.l;
        this.v.removeAllElements();
        this.w.removeAllElements();
        int n = 0;
        int n3 = 0;
        int n4 = 0;
        Label_0177: {
            if (this.n != null && this.n.length > 0) {
                n = 0;
                int n2 = 0;
                final Integer[] array = { null };
                final int length = this.j.length();
                this.v.addElement(new Integer(0));
                do {
                    n = this.d.a(this, n, array);
                    if (n >= length) {
                        break;
                    }
                    n3 = n;
                    n4 = n2;
                    if (l) {
                        break Label_0177;
                    }
                    if (n3 == n4) {
                        break;
                    }
                    this.w.addElement(array[0]);
                    this.v.addElement(new Integer(n));
                    n2 = n;
                } while (!l);
                this.w.addElement(array[0]);
            }
            n = 10;
            if (this.j.length() != 0) {
                n = this.j.charAt(this.j.length() - 1);
            }
            final int af = this.af;
        }
        if (n3 == n4 && (n == 10 || n == 13)) {
            this.v.addElement(new Integer(this.j.length()));
            this.w.addElement(new Integer(0));
        }
    }
    
    void a(final char c, final char c2) {
        final boolean l = c.l;
        final int size = this.v.size();
        final int length = this.j.length();
        final String j = this.j;
        boolean b = true;
        Label_0826: {
            Label_0697: {
                switch (c2) {
                    case '\b': {
                        if (super.a.as.d != super.a.as.e) {
                            this.f("");
                            if (!l) {
                                break Label_0826;
                            }
                        }
                        if (super.a.as.e == 0) {
                            break Label_0826;
                        }
                        super.a.as.a(super.a.as.e - 1, super.a.as.e);
                        this.f("");
                        if (l) {
                            break Label_0697;
                        }
                        break Label_0826;
                    }
                    case '\u007f': {
                        if (super.a.as.d != super.a.as.e) {
                            this.f("");
                            if (!l) {
                                break Label_0826;
                            }
                        }
                        if (super.a.as.e == length) {
                            break Label_0826;
                        }
                        super.a.as.a(super.a.as.e, super.a.as.e + 1);
                        this.f("");
                        if (l) {
                            break Label_0697;
                        }
                        break Label_0826;
                    }
                    case '$': {
                        final int intValue = this.v.elementAt(this.ba());
                        super.a.as.a(intValue, intValue);
                        if (l) {
                            break Label_0697;
                        }
                        break Label_0826;
                    }
                    case '#': {
                        final int ba = this.ba();
                        int intValue2 = 0;
                        Label_0374: {
                            if (ba < size - 1) {
                                intValue2 = this.v.elementAt(ba + 1);
                                if (!l) {
                                    break Label_0374;
                                }
                            }
                            intValue2 = length;
                        }
                        super.a.as.a(intValue2, intValue2);
                        if (l) {
                            break Label_0697;
                        }
                        break Label_0826;
                    }
                    case '&': {
                        final int ba2 = this.ba();
                        int intValue3 = 0;
                        if (ba2 > 0) {
                            intValue3 = this.v.elementAt(ba2 - 1);
                        }
                        final int intValue4 = this.v.elementAt(ba2);
                        final int n = super.a.as.e - intValue4;
                        int n2 = 0;
                        Label_0486: {
                            if (n < intValue4 - intValue3) {
                                n2 = intValue3 + n;
                                if (!l) {
                                    break Label_0486;
                                }
                            }
                            n2 = intValue4 - 1;
                        }
                        super.a.as.a(n2, n2);
                        if (l) {
                            break Label_0697;
                        }
                        break Label_0826;
                    }
                    case '(': {
                        final int ba3 = this.ba();
                        int intValue5 = 0;
                        if (ba3 < this.v.size() - 1) {
                            intValue5 = this.v.elementAt(ba3 + 1);
                        }
                        final int intValue6 = this.v.elementAt(ba3);
                        final int n3 = super.a.as.e - intValue6;
                        int n4 = 0;
                        Label_0639: {
                            if (n3 < intValue5 - intValue6) {
                                n4 = intValue5 + n3;
                                if (!l) {
                                    break Label_0639;
                                }
                            }
                            if (ba3 < size - 2) {
                                n4 = this.v.elementAt(ba3 + 2) - 1;
                                if (!l) {
                                    break Label_0639;
                                }
                            }
                            n4 = length;
                        }
                        super.a.as.a(n4, n4);
                        if (l) {
                            break Label_0697;
                        }
                        break Label_0826;
                    }
                    case '%': {
                        super.a.as.a(super.a.as.e - 1, super.a.as.e - 1);
                        if (l) {
                            break Label_0697;
                        }
                        break Label_0826;
                    }
                    case '\'': {
                        super.a.as.a(super.a.as.e + 1, super.a.as.e + 1);
                        if (l) {
                            break;
                        }
                        break Label_0826;
                    }
                }
            }
            if (this.am != 0 && this.am <= length) {
                return;
            }
            if ((c == '\n' || c == '\r') && !this.d.r) {
                return;
            }
            if (!Character.isISOControl(c) || c == '\n' || c == '\r') {
                this.f("" + c);
                if (!l) {
                    break Label_0826;
                }
            }
            b = false;
        }
        this.a9();
        if (!j.equals(this.j)) {
            this.a(ax.ap);
        }
        if (b) {
            this.k();
        }
    }
    
    void a9() {
        final int ba = this.ba();
        final int a7 = this.a7();
        if (ba >= this.ad + a7 - 1) {
            this.f(ba - a7 + 2);
        }
        if (ba < this.ad) {
            this.f(ba + 1);
        }
    }
    
    int ba() {
        final boolean l = c.l;
        int ad = this.ad;
        final int size = this.v.size();
        int e = 0;
        int intValue = 0;
        while (true) {
            Label_0026: {
                if (!l) {
                    break Label_0026;
                }
                ++ad;
            }
            if (ad < size) {
                e = super.a.as.e;
                intValue = this.v.elementAt(ad);
                if (l) {
                    return e - intValue;
                }
                if (e >= intValue) {
                    continue;
                }
            }
            break;
        }
        if (size > 0 && super.a.as.e > 0 && ad < size && Character.isWhitespace(this.j.charAt(super.a.as.e - 1)) && this.v.elementAt(ad) == super.a.as.e) {
            ++ad;
        }
        return e - intValue;
    }
    
    int c(final int n, final int n2) {
        final boolean l = c.l;
        if (this.j.length() == 0) {
            return 0;
        }
        int i = this.i(n2);
        if (i < 0) {
            i = 0;
        }
        final int intValue = this.v.elementAt(i);
        final int n3 = (i < this.v.size() - 1) ? this.v.elementAt(i + 1) : this.j.length();
        a0 p2 = null;
        Label_0118: {
            if (intValue < this.j.length()) {
                p2 = this.n[intValue];
                if (!l) {
                    break Label_0118;
                }
            }
            p2 = this.p;
        }
        int n4 = this.ab.a + 40 + p2.j + p2.c;
        final int n5 = this.ab.c - p2.k - 40;
        int n6 = 0;
        int n7 = intValue;
        int n9;
        int n10;
        while (true) {
            Label_0261: {
                if (!l) {
                    break Label_0261;
                }
                final a0 a0 = this.n[n7];
                final ao g = a0.g;
                if (g != null && g.g.length != 0) {
                    final int n8 = g.g[this.j.charAt(n7)];
                    if (n8 > -1) {
                        n6 = g.h[n8] * a0.l / g.k;
                        n4 += n6;
                    }
                }
                ++n7;
            }
            if (n4 >= n5) {
                goto Label_0289;
            }
            n9 = n4;
            n10 = n * 20;
            if (!l && (n9 < n10 && n7 < n3)) {
                continue;
            }
            break;
        }
        if ((n9 < n10 && (n7 != n3 || n3 == this.j.length())) || n7 == intValue) {
            return n7;
        }
        return n7 - 1;
    }
    
    int i(final int n) {
        final boolean l = c.l;
        int n2 = 0;
        int ad = this.ad;
        int n3;
        int n4;
        while (true) {
            Label_0032: {
                if (!l) {
                    break Label_0032;
                }
                n2 += this.h(ad);
                ++ad;
            }
            if (ad >= this.v.size()) {
                goto Label_0056;
            }
            n3 = n2;
            n4 = n * 20;
            if (!l && n3 < n4) {
                continue;
            }
            break;
        }
        return n3 - n4;
    }
    
    int b(int n, final boolean b) {
        final boolean l = c.l;
        final int n2 = b ? -1 : 1;
        int n3 = 0;
        int length = 0;
    Label_0073:
        while (true) {
            Label_0052: {
                if (!l) {
                    break Label_0052;
                }
                final char char1 = this.j.charAt(n);
                if (Character.isWhitespace(char1)) {
                    break Label_0073;
                }
                if (char1 == '-') {
                    break Label_0073;
                }
                n += n2;
            }
            if (n > -1) {
                n3 = n;
                length = this.j.length();
                if (l) {
                    return n3 + length;
                }
                if (n3 < length) {
                    continue;
                }
            }
            break;
        }
        if (!b) {
            return n;
        }
        return n3 + length;
    }
    
    private void a(final as as, final au au) {
        if (this.b == null) {
            return;
        }
        int n = 0;
        while (true) {
            Label_0041: {
                if (!c.l) {
                    break Label_0041;
                }
                ((c)this.b.elementAt(n)).c(as, au, false);
                ++n;
            }
            if (n >= this.b.size()) {
                return;
            }
            continue;
        }
    }
    
    static {
        ap = new as("onChanged");
        aq = new as("onScroller");
    }
}
