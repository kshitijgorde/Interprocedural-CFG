import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class nea
{
    public static final int ab = 0;
    public static final int bb = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final double cb = 0.35;
    public static final int db = 10;
    public static final int eb = 11;
    public static final int fb = 12;
    public static final int gb = 13;
    public static final int hb = 14;
    public static final int ib = 15;
    public static final int jb = 16;
    public static final int kb = 17;
    public static final int lb = 0;
    public static final int mb = -1;
    public static final int nb = 160;
    public static final int ob = 0;
    public static final int pb = 1;
    public static final int qb = 2;
    public static final int rb = 0;
    public static final int sb = 1;
    public static final int tb = 2;
    private boolean _;
    private boolean a;
    private boolean b;
    private boolean c;
    private int d;
    private int Ta;
    private final while e;
    private final d f;
    private int Ra;
    private double g;
    private double h;
    private double i;
    private double j;
    private String k;
    private String l;
    private String[] m;
    private boolean n;
    private boolean o;
    private boolean p;
    
    public nea(final while e) {
        this._ = false;
        this.a = false;
        this.b = true;
        this.c = true;
        this.d = 0;
        this.Ta = 12;
        this.g = -1.0;
        this.h = -1.0;
        this.i = -1.0;
        this.j = -1.0;
        this.k = null;
        this.l = "dd-MMM-yyyy";
        this.n = true;
        this.o = false;
        this.p = false;
        this.e = e;
        this.f = new d(4, e.a());
        this.m = new String[this.f.m()];
        this.c();
        this.Ra = 0;
    }
    
    public synchronized void e(final int ra) {
        this.Ra = ra;
    }
    
    public synchronized void j(final String l) {
        this.l = l;
        this.f.q.k(this.l);
    }
    
    public void g(final int n) {
        synchronized (this.f) {
            final boolean a = this.a;
            this.d();
            if (n == 1) {
                this.f.r[0].h(1);
                this.f.s[0].h(1);
                this.f.r[0].c(false);
                this.f.s[0].c(false);
                this.f.d(true);
                this.a = false;
            }
            else if (n == 2) {
                this.f.r[0].h(1);
                this.f.s[0].h(1);
                this.f.r[0].c(false);
                this.f.s[0].c(false);
                this.f.d(false);
                this.a = false;
            }
            else if (n == 3) {
                this.f.r[0].h(0);
                this.f.s[0].h(0);
                this.f.r[0].c(false);
                this.f.s[0].c(false);
                this.f.d(true);
                this.a = false;
            }
            else if (n == 4) {
                this.f.r[0].h(0);
                this.f.s[0].h(0);
                this.f.r[0].c(false);
                this.f.s[0].c(false);
                this.f.d(false);
                this.a = false;
            }
            else if (n == 5) {
                this.f.r[0].h(0);
                this.f.s[0].h(0);
                this.f.r[0].c(true);
                this.f.s[0].c(true);
                this.f.d(true);
                this.a = true;
            }
            if (this.a || this.a != a) {
                this._ = true;
            }
            if (this.a != a) {
                this.f.i(0);
            }
            this.e();
        }
    }
    
    public synchronized void _(final int n, final int n2) {
        synchronized (this.f) {
            this.d();
            this.f.j(n);
            if (this.n) {
                this.c();
                this.n = false;
            }
            this.g(n2);
            this.e();
        }
    }
    
    public void c() {
        this.f.a(Color.black);
        this.f.c(null);
        this.f.d(Color.gray);
        this.f.q.k(2);
        this.f.q.l(3);
        this.f.q.h(2);
        this.f.s[0].m(2);
        this.f.r[0].m(2);
        this.f.q.setColor(Color.white);
        this.f.q.t.e(false);
        this.f.q.t.f(false);
        this.f.q.t.setColor(Color.white);
        this.f.q.k(this.l);
        for (int i = 0; i < this.f.m(); ++i) {
            this.f.b(i).e(false);
            this.f.b(i).f(false);
            this.f.b(i).e(Color.white);
            this.f.b(i).a(Color.black);
            this.f.b(i).setColor(Color.yellow);
            this.f.r[i].setColor(Color.white);
            this.f.r[i].t.e(false);
            this.f.r[i].t.f(false);
            this.f.r[i].t.setColor(Color.white);
            this.f.s[i].setColor(Color.white);
            this.f.s[i].t.e(false);
            this.f.s[i].t.f(false);
            this.f.s[i].t.setColor(Color.white);
            this.f.s[i].k(2);
            this.f.r[i].k(2);
            this.f.s[i].l(3);
            this.f.r[i].l(3);
        }
        this.f();
        this.g();
    }
    
    private void g() {
        final String _ = this.e.u._("AxisLabelFontName");
        final int _2 = this.e.u._("AxisLabelFontSize");
        final int a = this.e.u.a("AxisLabelFontStyle");
        this.f.q.t.setFont(this.a(this.f.q.t.getFont(), _, _2, a));
        for (int i = 0; i < this.f.m(); ++i) {
            this.f.s[i].t.setFont(this.a(this.f.s[i].t.getFont(), _, _2, a));
            this.f.r[i].t.setFont(this.a(this.f.r[i].t.getFont(), _, _2, a));
        }
        final String _3 = this.e.u._("ChartTitleFontName");
        final int _4 = this.e.u._("ChartTitleFontSize");
        final int a2 = this.e.u.a("ChartTitleFontStyle");
        for (int j = 0; j < this.f.m(); ++j) {
            this.f.b(j).setFont(this.a(this.f.b(j).getFont(), _3, _4, a2));
        }
        this.f.v.setFont(this.a(this.f.v.getFont(), this.e.u._("InfoLineFontName"), this.e.u._("InfoLineFontSize"), this.e.u.a("InfoLineFontStyle")));
        this.f.w.setFont(this.a(this.f.w.getFont(), this.e.u._("LegendFontName"), this.e.u._("LegendFontSize"), this.e.u.a("LegendFontStyle")));
    }
    
    private void f() {
        final Color[] b = this.e.b("ChartBg");
        if (b != null) {
            this.f.a(b[0]);
        }
        final Color[] b2 = this.e.b("ChartFg");
        if (b2 != null) {
            this.f.c(b2[0]);
        }
        final Color[] b3 = this.e.b("ChartTopLeftFrame");
        if (b3 != null) {
            this.f.f(b3[0]);
        }
        final Color[] b4 = this.e.b("ChartBottomRightFrame");
        if (b4 != null) {
            this.f.g(b4[0]);
        }
        final Color[] b5 = this.e.b("Grid");
        if (b5 != null) {
            this.f.d(b5[0]);
        }
        final Color[] b6 = this.e.b("ProgressBar");
        if (b6 != null) {
            this.f.h(b6[0]);
        }
        final Color[] b7 = this.e.b("Crosshair");
        if (b7 != null) {
            this.f.i(b7[0]);
        }
        final Color[] b8 = this.e.b("InfoLine");
        if (b8 != null) {
            this.f.v.setColor(b8[0]);
        }
        final Color[] b9 = this.e.b("InfoLineBg");
        this.f.v.e(b9 != null);
        if (b9 != null) {
            this.f.v.a(b9[0]);
        }
        final Color[] b10 = this.e.b("InfoLineFrame");
        this.f.v.f(b10 != null);
        if (b10 != null) {
            this.f.v.e(b10[0]);
        }
        final Color[] b11 = this.e.b("Axis");
        if (b11 != null) {
            this.f.q.setColor(b11[0]);
        }
        final Color[] b12 = this.e.b("AxisLabel");
        if (b12 != null) {
            this.f.q.t.setColor(b12[0]);
        }
        final Color[] b13 = this.e.b("AxisLabelBg");
        this.f.q.t.e(b13 != null);
        if (b13 != null) {
            this.f.q.t.a(b13[0]);
        }
        final Color[] b14 = this.e.b("AxisLabelFrame");
        this.f.q.t.f(b14 != null);
        if (b14 != null) {
            this.f.q.t.e(b14[0]);
        }
        for (int i = 0; i < this.f.m(); ++i) {
            final Color[] b15 = this.e.b("ChartTitle");
            if (b15 != null) {
                this.f.b(i).setColor(b15[0]);
            }
            final Color[] b16 = this.e.b("ChartTitleBg");
            this.f.b(i).e(b16 != null);
            if (b16 != null) {
                this.f.b(i).a(b16[0]);
            }
            final Color[] b17 = this.e.b("ChartTitleFrame");
            this.f.b(i).f(b17 != null);
            if (b17 != null) {
                this.f.b(i).e(b17[0]);
            }
            final Color[] b18 = this.e.b("Axis");
            if (b18 != null) {
                this.f.r[i].setColor(b18[0]);
                this.f.s[i].setColor(b18[0]);
            }
            final Color[] b19 = this.e.b("AxisLabel");
            if (b19 != null) {
                this.f.r[i].t.setColor(b19[0]);
                this.f.s[i].t.setColor(b19[0]);
            }
            final Color[] b20 = this.e.b("AxisLabelBg");
            this.f.r[i].t.e(b20 != null);
            this.f.s[i].t.e(b20 != null);
            if (b20 != null) {
                this.f.r[i].t.a(b20[0]);
                this.f.s[i].t.a(b20[0]);
            }
            final Color[] b21 = this.e.b("AxisLabelFrame");
            this.f.r[i].t.f(b21 != null);
            this.f.s[i].t.f(b21 != null);
            if (b21 != null) {
                this.f.r[i].t.e(b21[0]);
                this.f.s[i].t.e(b21[0]);
            }
        }
    }
    
    public void m() {
        final Color[] b = this.e.b("Crosshair");
        if (b != null) {
            this.f.i(b[0]);
        }
        final Color[] b2 = this.e.b("Grid");
        if (b2 != null) {
            this.f.d(b2[0]);
        }
    }
    
    public void d() {
        this.f.g(true);
    }
    
    public void e() {
        this.f.g(false);
    }
    
    public d b() {
        return this.f;
    }
    
    public synchronized void a() {
        this.f.t();
    }
    
    private void u() {
        this.g = -1.0;
        this.h = -1.0;
        this.i = -1.0;
        this.j = -1.0;
    }
    
    public synchronized void h(final boolean o) {
        this.o = o;
    }
    
    public synchronized void b() {
        this._ = false;
        final double[] a = this.e._().a();
        final double[] b = this.e._().b();
        final double[] _ = this.e._()._();
        final double[] g = this.e._().g();
        final double[] h = this.e._().h();
        double[] i = this.e._().i();
        double[] j = this.e._().j();
        if (a == null || b == null || _ == null || g == null || h == null || i == null || j == null || !this.e._().m()) {
            for (int k = 0; k < this.f.n(); ++k) {
                this.f.n(k);
                this.f._(k, "");
            }
            if (!this.e._().m() || this.e._()._() == null || this.e._()._().length() == 0) {
                this.f._(0, this.e._().getMessage());
            }
            else {
                this.f._(0, this.e.a().a("msgNoDataAvailable"));
            }
            return;
        }
        if ((this.e._().j() && this.Ta != -1) || ((this.e._().a() || this.Ta != -1) && (this.k == null || this.k != this.e._()._()))) {
            this.u();
        }
        this.k = this.e._()._();
        boolean b2 = true;
        for (int l = 0; l < j.length; ++l) {
            if (i[l] > 0.0) {
                b2 = false;
            }
        }
        if (b2) {
            i = null;
        }
        boolean b3 = true;
        for (int n = 0; n < j.length; ++n) {
            if (j[n] > 0.0) {
                b3 = false;
            }
        }
        if (b3) {
            j = null;
        }
        this.p = (this.d == 1 || (this.d == 0 && (i != null || j != null)));
        double[] m = null;
        double[] l2 = null;
        double[] m2 = null;
        double[] n2 = null;
        double[] c = null;
        if (this.e._().m() && this.e._().n() != null && this.e._().n().length() > 0) {
            m = this.e._().k();
            l2 = this.e._().l();
            m2 = this.e._().m();
            n2 = this.e._().n();
            c = this.e._().c();
        }
        synchronized (this.f) {
            this.d();
            this.f.v();
            int n3 = 1;
            if (this.p) {
                ++n3;
            }
            if (this.e._() != null) {
                ++n3;
            }
            if (this.e.a() != null) {
                ++n3;
            }
            if (n3 != this.f.n()) {
                this.f.j(n3);
            }
            if (this.e._().a()) {
                this.f.q.u(2);
            }
            else {
                this.f.q.u(1);
            }
            this.f.n(0);
            if (this.f.n() > 0) {
                this.f.s[0].m(this.e._().c());
                this.f.r[0].m(this.e._().c());
            }
            final String[] m3 = this.e._().m();
            for (int n4 = 0; n4 < m3.length; ++n4) {
                final double[][] a2 = this.e._().a(m3[n4]);
                if (a2 != null) {
                    final int[] _2 = this.e._("ExtraPrice");
                    int n5 = 0;
                    if (_2 != null && _2.length > 0) {
                        n5 = _2[n4 % _2.length];
                    }
                    final s s = new s(a2[0].length, n5);
                    s.setName(m3[n4]);
                    final Color[] b4 = this.e.b("ExtraPrice");
                    if (b4 != null && b4.length > 0) {
                        s.setColor(b4[n4 % b4.length]);
                    }
                    else {
                        s.setColor(Color.red);
                    }
                    for (int n6 = 0; n6 < a2[0].length; ++n6) {
                        s.a(a2[0][n6], a2[1][n6]);
                    }
                    this.f._(0, s);
                }
            }
            public[] a3 = null;
            if (!this.a) {
                a3 = this.e.a();
                if (a3 != null) {
                    for (int n7 = 0; n7 < a3.length; ++n7) {
                        if (!(a3[n7] instanceof eea)) {
                            this.b(0, a, a3[n7]);
                        }
                    }
                }
            }
            if (this.Ra == 1) {
                final Yea yea = new Yea(a.length);
                final Color[] b5 = this.e.b("Price");
                if (b5 != null) {
                    yea.setColor(b5[0]);
                }
                else {
                    yea.setColor(Color.yellow);
                }
                yea.setName(this.e._()._());
                yea.q(this.e.a().a("strDate"));
                yea.a(0, "O");
                yea.a(1, "H");
                yea.a(2, "L");
                yea.a(3, "C");
                for (int n8 = 0; n8 < a.length; ++n8) {
                    yea.a(a[n8], b[n8], _[n8], g[n8], h[n8]);
                }
                this.f._(0, yea);
            }
            else if (this.Ra == 2) {
                final Zea zea = new Zea(a.length);
                final Color[] b6 = this.e.b("Price");
                if (b6 != null) {
                    zea.setColor(b6[0]);
                }
                else {
                    zea.setColor(Color.yellow);
                }
                final Color[] b7 = this.e.b("BullishCandle");
                if (b7 != null) {
                    zea._(b7[0]);
                }
                else {
                    zea._(Color.green.darker());
                }
                final Color[] b8 = this.e.b("BearishCandle");
                if (b8 != null) {
                    zea.b(b8[0]);
                }
                else {
                    zea.b(Color.red);
                }
                zea.setName(this.e._()._());
                zea.q(this.e.a().a("strDate"));
                zea.a(0, "O");
                zea.a(1, "H");
                zea.a(2, "L");
                zea.a(3, "C");
                for (int n9 = 0; n9 < a.length; ++n9) {
                    zea.a(a[n9], b[n9], _[n9], g[n9], h[n9]);
                }
                this.f._(0, zea);
            }
            else {
                final int[] _3 = this.e._("Price");
                int n10 = 0;
                if (_3 != null) {
                    n10 = _3[0];
                }
                final s s2 = new s(a.length, n10);
                final Color[] b9 = this.e.b("Price");
                if (b9 != null) {
                    s2.setColor(b9[0]);
                }
                else {
                    s2.setColor(Color.yellow);
                }
                s2.setName(this.e._()._());
                s2.q(this.e.a().a("strDate"));
                s2.a(0, this.e.a().a("strPriceTitle"));
                for (int n11 = 0; n11 < a.length; ++n11) {
                    s2.a(a[n11], h[n11]);
                }
                this.f._(0, s2);
            }
            if (this.e._().m() && this.e._().n() != null && this.e._().n().length() > 0 && m != null && l2 != null && m2 != null && n2 != null && c != null) {
                if (this.Ra == 1) {
                    final Yea yea2 = new Yea(m.length);
                    final Color[] b10 = this.e.b("BgPrice");
                    if (b10 != null) {
                        yea2.setColor(b10[0]);
                    }
                    else {
                        yea2.setColor(Color.lightGray);
                    }
                    yea2.setName(this.e._().n());
                    yea2.q(this.e.a().a("strDate"));
                    yea2.a(0, "O");
                    yea2.a(1, "H");
                    yea2.a(2, "L");
                    yea2.a(3, "C");
                    for (int n12 = 0; n12 < m.length; ++n12) {
                        yea2.a(m[n12], l2[n12], m2[n12], n2[n12], c[n12]);
                    }
                    this.f._(0, 0, yea2);
                }
                else if (this.Ra == 2) {
                    final Zea zea2 = new Zea(m.length);
                    final Color[] b11 = this.e.b("BgPrice");
                    if (b11 != null) {
                        zea2.setColor(b11[0]);
                        zea2._(b11[0]);
                        zea2.b(b11[0]);
                    }
                    else {
                        zea2.setColor(Color.gray);
                        zea2._(Color.gray);
                        zea2.b(Color.gray);
                    }
                    zea2.setName(this.e._().n());
                    zea2.q(this.e.a().a("strDate"));
                    zea2.a(0, "O");
                    zea2.a(1, "H");
                    zea2.a(2, "L");
                    zea2.a(3, "C");
                    for (int n13 = 0; n13 < m.length; ++n13) {
                        zea2.a(m[n13], l2[n13], m2[n13], n2[n13], c[n13]);
                    }
                    this.f._(0, 0, zea2);
                }
                else {
                    final int[] _4 = this.e._("BgPrice");
                    int n14 = 0;
                    if (_4 != null) {
                        n14 = _4[0];
                    }
                    final s s3 = new s(m.length, n14);
                    final Color[] b12 = this.e.b("BgPrice");
                    if (b12 != null) {
                        s3.setColor(b12[0]);
                    }
                    else {
                        s3.setColor(Color.gray);
                    }
                    s3.setName(this.e._().n());
                    s3.q(this.e.a().a("strDate"));
                    s3.a(0, this.e.a().a("strPriceTitle"));
                    for (int n15 = 0; n15 < m.length; ++n15) {
                        s3.a(m[n15], c[n15]);
                    }
                    this.f._(0, 0, s3);
                }
            }
            if (this.f.n() > 1 && this.p) {
                this.f.n(1);
                if ((this.m[1] == null || !this.m[1].equals("VOLUME")) && !this.o) {
                    this.f.i(1);
                }
                this.m[1] = "VOLUME";
                if (i != null) {
                    final _fa fa = new _fa(a.length);
                    final Color[] b13 = this.e.b("Volume");
                    if (b13 != null) {
                        fa.setColor(b13[0]);
                    }
                    else {
                        fa.setColor(Color.green);
                    }
                    fa.a(0, "V");
                    this.f.r[1].k(0);
                    for (int n16 = 0; n16 < a.length; ++n16) {
                        fa.a(a[n16], i[n16]);
                    }
                    this.f._(1, fa);
                    if (a3 != null) {
                        for (int n17 = 0; n17 < a3.length; ++n17) {
                            if (a3[n17] instanceof eea) {
                                this.b(1, a, a3[n17]);
                            }
                        }
                    }
                }
                if (j != null) {
                    final int[] _5 = this.e._("OpenInterest");
                    int n18 = 0;
                    if (_5 != null) {
                        n18 = _5[0];
                    }
                    final s s4 = new s(a.length, n18);
                    final Color[] b14 = this.e.b("OpenInterest");
                    if (b14 != null) {
                        s4.setColor(b14[0]);
                    }
                    else {
                        s4.setColor(Color.blue);
                    }
                    s4.a(0, "OI");
                    for (int n19 = 0; n19 < a.length; ++n19) {
                        s4.a(a[n19], j[n19]);
                    }
                    if (i != null) {
                        this.f._(1, 0, s4);
                    }
                    else {
                        this.f.r[1].i(true);
                        this.f.r[1].k(2);
                        this.f._(1, 1, s4);
                    }
                }
                if (this.f.a(1) == 0) {
                    final _fa fa2 = new _fa(a.length);
                    final Color[] b15 = this.e.b("Volume");
                    if (b15 != null) {
                        fa2.setColor(b15[0]);
                    }
                    else {
                        fa2.setColor(Color.green);
                    }
                    this.f.r[1].k(0);
                    for (int n20 = 0; n20 < a.length; ++n20) {
                        fa2.a(a[n20], 0.0);
                    }
                    this.f._(1, fa2);
                }
            }
            final public _6 = this.e._();
            final public a4 = this.e.a();
            int n21;
            int n22;
            if (this.p) {
                n21 = 2;
                n22 = 3;
            }
            else {
                n21 = 1;
                n22 = 2;
            }
            if (this.f.n() > n21) {
                this.f.n(n21);
                if (_6 != null) {
                    if ((this.m[n21] == null || !this.m[n21].equals(_6.toString())) && !this.o) {
                        this.f.i(n21);
                    }
                    this.m[n21] = _6.toString();
                    this.f.r[n21].i(true);
                    this.f.r[n21].k(2);
                    this.b(n21, a, _6);
                }
                else if (a4 != null) {
                    if ((this.m[n21] == null || !this.m[n21].equals(a4.toString())) && !this.o) {
                        this.f.i(n21);
                    }
                    this.m[n21] = a4.toString();
                    this.f.r[n21].i(true);
                    this.f.r[n21].k(2);
                    this.b(n21, a, a4);
                }
            }
            if (this.f.n() > n22) {
                this.f.n(n22);
                if (_6 != null && a4 != null) {
                    if ((this.m[n22] == null || !this.m[n22].equals(a4.toString())) && !this.o) {
                        this.f.i(n22);
                    }
                    this.m[n22] = a4.toString();
                    this.f.r[n22].i(true);
                    this.f.r[n22].k(2);
                    this.b(n22, a, a4);
                }
            }
            this.f._(0, "" + this.e._()._());
            if (this.f.n() > 1 && this.p) {
                if (j != null && i != null) {
                    this.f._(1, this.e.a().a("strOpenInterest") + ", " + this.e.a().a("strVolume"));
                }
                else if (i != null) {
                    this.f._(1, this.e.a().a("strVolume"));
                }
                else if (j != null) {
                    this.f._(1, this.e.a().a("strOpenInterest"));
                }
                else {
                    this.f._(1, "");
                }
            }
            if (this.f.n() > n21) {
                if (_6 != null) {
                    this.f._(n21, _6.c());
                }
                else if (a4 != null) {
                    this.f._(n21, a4.c());
                }
            }
            if (this.f.n() > n22 && _6 != null && a4 != null) {
                this.f._(n22, a4.c());
            }
            this.w();
            if (this.f.n() > 1 && this.p && this.f.a(1) > 0) {
                final p _7 = this.f._(1, 0);
                if (_7 != null && _7 instanceof _fa) {
                    this.f.r[1].i(false);
                    this.f.r[1]._(0.0, _7.a());
                }
            }
            final Color[] b16 = this.e.b("TrendLine");
            if (b16 != null) {
                this.f.j(b16[0]);
            }
            final int[] _8 = this.e._("TrendLine");
            if (_8 != null) {
                this.f.v(_8[0]);
            }
            if (this.a) {
                this.x();
            }
            this.f.y();
            this.e();
        }
    }
    
    private void x() {
        for (int i = 0; i < this.f.a(0); ++i) {
            this.a(this.f._(0, i));
        }
        for (int j = 0; j < this.f.b(0, 0); ++j) {
            this.a(this.f._(0, 0, j));
        }
    }
    
    private void a(final p p) {
        if (p == null) {
            return;
        }
        final int b = p.b();
        if (b > p._()) {
            return;
        }
        p.z();
        final double a = p.a(b, p.d() - 1);
        if (a != 0.0) {
            for (int i = 0; i < p.d(); ++i) {
                for (int j = 0; j <= p.H(); ++j) {
                    p.b(j, i, 100.0 * (p.a(j, i) - a) / a);
                }
            }
        }
    }
    
    public static int b(final double[] array, final double n) {
        int i = 0;
        int n2 = array.length - 1;
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final double n4 = array[n3];
            if (n4 < n) {
                i = n3 + 1;
            }
            else {
                if (n4 <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return -(i + 1);
    }
    
    private double _(final double n, final double n2) {
        double n3 = n2;
        if (this.Ta != 0 && this.Ta != -1) {
            switch (this.Ta) {
                case 10: {
                    final throw throw1 = new throw(n);
                    throw1.w(-1);
                    n3 = throw1.l();
                    break;
                }
                case 11: {
                    final throw throw2 = new throw(n);
                    throw2.w(-3);
                    n3 = throw2.l();
                    break;
                }
                case 12: {
                    final throw throw3 = new throw(n);
                    throw3.w(-6);
                    n3 = throw3.l();
                    break;
                }
                case 13: {
                    final throw throw4 = new throw(n);
                    throw4.w(-12);
                    n3 = throw4.l();
                    break;
                }
                case 14: {
                    final throw throw5 = new throw(n);
                    throw5.w(-24);
                    n3 = throw5.l();
                    break;
                }
                case 15: {
                    final throw throw6 = new throw(n);
                    throw6.w(-36);
                    n3 = throw6.l();
                    break;
                }
                case 16: {
                    final throw throw7 = new throw(n);
                    throw7.w(-60);
                    n3 = throw7.l();
                    break;
                }
                case 17: {
                    final throw throw8 = new throw(n);
                    throw8.w(-120);
                    n3 = throw8.l();
                    break;
                }
            }
        }
        return n3;
    }
    
    public synchronized int l() {
        return this.Ta;
    }
    
    public synchronized void c(final int ta) {
        this.Ta = ta;
        p _ = null;
        if (this.f.n() > 0 && this.f.a(0) > 0) {
            _ = this.f._(0, this.f.a(0) - 1);
        }
        if (_ == null) {
            return;
        }
        final boolean a = this.e._().a();
        synchronized (this.f) {
            this.d();
            this.f.v();
            final double[] d = this.f.d();
            if (d == null || d.length == 0) {
                return;
            }
            final double n = d[d.length - 1];
            final double _2 = this._(n, d[0]);
            this.j(true);
            this.f.n(_2, n);
            if (this.f.n() > 1 && this.p && this.f.a(1) > 0) {
                final p _3 = this.f._(1, 0);
                if (_3 != null && _3 instanceof _fa) {
                    this.f.r[1].i(false);
                    this.f.r[1]._(0.0, _3.a());
                }
            }
            if (a) {
                this.i = _2;
                this.j = n;
            }
            else {
                this.g = _2;
                this.h = n;
            }
            if (this.a) {
                this._ = true;
                this.f.i(0);
            }
            this.f.y();
            this.e();
        }
    }
    
    public synchronized void d(final int n) {
        p _ = null;
        if (this.f.n() > 0 && this.f.a(0) > 0) {
            _ = this.f._(0, this.f.a(0) - 1);
        }
        if (_ == null) {
            return;
        }
        synchronized (this.f) {
            this.d();
            this.f.v();
            final double[] d = this.f.d();
            if (d == null) {
                return;
            }
            final double[] e = this.f.e();
            if (e == null) {
                return;
            }
            final int n2 = 0;
            final int n3 = d.length - 1;
            int n4 = b(d, e[0]);
            if (n4 < 0) {
                n4 = Math.max(-n4 - 1, 0);
            }
            int n5 = b(d, e[1]);
            if (n5 < 0) {
                n5 = Math.min(-n5 - 1, d.length - 1);
            }
            int max = Math.max(1, (int)((n5 - n4) * 0.35));
            switch (n) {
                case 2: {
                    final int n6 = n4 - n2;
                    if (max > n6) {
                        max = n6;
                    }
                    n4 -= max;
                    n5 -= max;
                    break;
                }
                case 3: {
                    final int n7 = n3 - n5;
                    if (max > n7) {
                        max = n7;
                    }
                    n4 += max;
                    n5 += max;
                    break;
                }
                case 0: {
                    this.Ta = -1;
                    final int max2 = Math.max(2, max);
                    n4 -= max2 / 2;
                    n5 += max2 / 2;
                    if (n4 < n2) {
                        n4 = n2;
                    }
                    if (n5 > n3) {
                        n5 = n3;
                        break;
                    }
                    break;
                }
                case 1: {
                    this.Ta = -1;
                    if (n5 >= n3 - 1) {
                        n4 += max;
                    }
                    else if (n4 == n2) {
                        n5 -= max;
                    }
                    else if (n4 != n5) {
                        final int max3 = Math.max(2, max);
                        n4 += max3 / 2;
                        n5 -= max3 / 2;
                    }
                    if (n4 < n2) {
                        n4 = n2;
                    }
                    if (n5 > n3) {
                        n5 = n3;
                    }
                    if (n4 > n5) {
                        n4 = n5;
                        break;
                    }
                    break;
                }
            }
            if (n4 < 0) {
                n4 = 0;
            }
            if (n5 < 0) {
                n5 = 0;
            }
            final double n8 = d[n4];
            final double n9 = d[n5];
            this.j(n5 >= n3);
            this.f.n(n8, n9);
            if (this.f.n() > 1 && this.p && this.f.a(1) > 0) {
                final p _2 = this.f._(1, 0);
                if (_2 != null && _2 instanceof _fa) {
                    this.f.r[1].i(false);
                    this.f.r[1]._(0.0, _2.a());
                }
            }
            if (this.e._().a()) {
                if (this.i == n8 && this.j == n9) {
                    Toolkit.getDefaultToolkit().beep();
                }
                this.i = n8;
                this.j = n9;
            }
            else {
                if (this.g == n8 && this.h == n9) {
                    Toolkit.getDefaultToolkit().beep();
                }
                this.g = n8;
                this.h = n9;
            }
            if (this.a) {
                this._ = true;
                this.f.i(0);
            }
            this.f.y();
            this.e();
        }
    }
    
    private void w() {
        double n;
        double n2;
        if (this.e._().a()) {
            n = this.i;
            n2 = this.j;
        }
        else {
            n = this.g;
            n2 = this.h;
        }
        p _ = null;
        if (this.f.n() > 0 && this.f.a(0) > 0) {
            _ = this.f._(0, this.f.a(0) - 1);
        }
        if (_ == null) {
            return;
        }
        final double[] d = this.f.d();
        if (d == null) {
            return;
        }
        final int n3 = 0;
        final int n4 = d.length - 1;
        final double n5 = d[n3];
        final double n6 = d[n4];
        if (n == -1.0 || n2 == -1.0 || n > n6 || n2 < n5) {
            n2 = n6;
            double n7;
            if (this.e._().a()) {
                n7 = this._(n2, n5);
            }
            else if (this.Ta == -1) {
                n7 = n6 - 160.0;
            }
            else {
                n7 = this._(n2, n5);
            }
            if (n7 < n5) {
                n7 = n5;
            }
            n = n7;
        }
        else if (this.n() && n2 < n6) {
            n2 = n6;
        }
        this.f.k(true);
        this.f.n(n, n2);
        if (this.f.e() == null) {
            final double m = _.m();
            final double _2 = this._(m, _.n());
            this.f.k(true);
            this.f.n(_2, m);
        }
        if (this.f.n() > 1 && this.p && this.f.a(1) > 0) {
            final p _3 = this.f._(1, 0);
            if (_3 != null && _3 instanceof _fa) {
                this.f.r[1].i(false);
                this.f.r[1]._(0.0, _3.a());
            }
        }
    }
    
    public void repaint() {
        if (this._) {
            this.b();
        }
        this.e();
        this.f.A();
    }
    
    private Font a(final Font font, String name, int size, int style) {
        if (name == null || name.length() == 0 || name.equals("null")) {
            name = font.getName();
        }
        if (size < 0) {
            size = font.getSize();
        }
        if (style < 0) {
            style = font.getStyle();
        }
        return new Font(name, style, size);
    }
    
    private void b(final int n, final double[] array, final public public1) {
        boolean b = false;
        for (int i = public1.I() - 1; i >= 0; --i) {
            final int[] _ = this.e._("Indicator");
            int n2 = 0;
            if (_ != null && i < _.length) {
                n2 = _[i];
            }
            final int[] _2 = this.e._(public1.toString());
            if (_2 != null && i < _2.length) {
                n2 = _2[i];
            }
            p p3;
            if (public1 instanceof aea) {
                p3 = new t(array.length, n2);
            }
            else if (i == 2 && public1 instanceof yda) {
                p3 = new _fa(array.length);
            }
            else {
                p3 = new s(array.length, n2);
            }
            final Color[] b2 = this.e.b("Indicator");
            if (b2 != null) {
                b = true;
                if (i < b2.length) {
                    p3.setColor(b2[i]);
                }
                else {
                    p3.setColor(b2[0]);
                }
            }
            else {
                p3.setColor(Color.lightGray);
            }
            final Color[] b3 = this.e.b("IndicatorSpecificLine");
            if (b3 != null) {
                p3.k(b3[0]);
            }
            final Color[] b4 = this.e.b(public1.toString());
            if (b4 != null) {
                if (i < b4.length) {
                    p3.setColor(b4[i]);
                }
                else {
                    p3.setColor(b4[0]);
                }
            }
            else if (!b) {
                p3.setColor(Color.lightGray);
            }
            if (i == 0) {
                final String[] n3 = this.e.n();
                boolean b5 = true;
                for (int j = 0; j < n3.length; ++j) {
                    if (n3[j].equals(public1.toString())) {
                        b5 = false;
                    }
                }
                if (b5) {
                    p3.a(0, public1.toString());
                }
            }
            p3.setName(public1.toString());
            p3.q(this.e.a().a("strDate"));
            final double[] a = public1.a(i);
            final double[] array2 = { 0.0 };
            if (a != null) {
                for (int n4 = 0; n4 < array.length && n4 < a.length; ++n4) {
                    array2[0] = a[n4];
                    p3.b(array[n4], array2);
                }
                p3.x(public1.b(i));
                this.f._(n, p3);
                if (i == 0 && n > 0) {
                    final byte[] b6 = public1.b();
                    if (b6 != null) {
                        final Color[] b7 = this.e.b("BuySignal");
                        Color darker;
                        if (b7 != null && b7.length > 0) {
                            darker = b7[0];
                        }
                        else {
                            darker = Color.green.darker();
                        }
                        final Color[] b8 = this.e.b("SellSignal");
                        Color red;
                        if (b8 != null && b8.length > 0) {
                            red = b8[0];
                        }
                        else {
                            red = Color.red;
                        }
                        final v v = new v(array.length, darker, red);
                        for (int n5 = 0; n5 < array.length && n5 < a.length && n5 < b6.length; ++n5) {
                            v.b(array[n5], a[n5], b6[n5]);
                        }
                        v.x(0);
                        this.f._(n, v);
                    }
                }
            }
            if (public1.J() > 0) {
                final double[] array3 = new double[public1.J()];
                for (int k = 0; k < array3.length; ++k) {
                    array3[k] = public1.b(k);
                }
                p3.a(array3);
            }
        }
    }
    
    private boolean n() {
        if (this.e._().a()) {
            return this.b;
        }
        return this.c;
    }
    
    private void j(final boolean b) {
        if (this.e._().a()) {
            this.b = b;
        }
        else {
            this.c = b;
        }
    }
    
    public void f(final int d) {
        this.d = d;
    }
    
    public synchronized int j() {
        return this.Ra;
    }
}
