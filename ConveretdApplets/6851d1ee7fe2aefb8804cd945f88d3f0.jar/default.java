import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class default
{
    public static final int Cna = 0;
    public static final int Dna = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final double Ena = 0.35;
    public static final int Fna = 10;
    public static final int Gna = 11;
    public static final int Hna = 12;
    public static final int Ina = 13;
    public static final int Jna = 14;
    public static final int Kna = 15;
    public static final int Lna = 16;
    public static final int Mna = 17;
    public static final int Nna = 0;
    public static final int Ona = -1;
    public static final int Pna = 160;
    public static final int Qna = 0;
    public static final int Rna = 1;
    public static final int Sna = 2;
    public static final int Tna = 0;
    public static final int Una = 1;
    public static final int Vna = 2;
    private boolean Wna;
    private boolean Xna;
    private boolean Yna;
    private boolean Zna;
    private int _oa;
    private int Za;
    private final break kb;
    private final continue Zja;
    private int Xa;
    private double aoa;
    private double boa;
    private double coa;
    private double doa;
    private String eoa;
    private String foa;
    private String[] goa;
    private int hoa;
    private int ioa;
    private int joa;
    private int koa;
    private int loa;
    private int moa;
    private boolean noa;
    private boolean ooa;
    private boolean poa;
    private boolean qoa;
    
    public default(final break kb) {
        this.Wna = false;
        this.Xna = false;
        this.Yna = true;
        this.Zna = true;
        this._oa = 0;
        this.Za = 12;
        this.aoa = -1.0;
        this.boa = -1.0;
        this.coa = -1.0;
        this.doa = -1.0;
        this.eoa = null;
        this.foa = "dd-MMM-yyyy";
        this.hoa = 6;
        this.ioa = 6;
        this.joa = 5;
        this.koa = 0;
        this.loa = 1;
        this.moa = 0;
        this.noa = false;
        this.ooa = true;
        this.poa = false;
        this.qoa = false;
        this.kb = kb;
        this.Zja = new continue(4, kb.a());
        this.goa = new String[this.Zja.N()];
        this.w();
        this.Xa = 0;
    }
    
    public synchronized void c(final int xa) {
        this.Xa = xa;
    }
    
    public synchronized void B(final String foa) {
        this.foa = foa;
        this.Zja.rka.z(this.foa);
    }
    
    public void e(final int n) {
        synchronized (this.Zja) {
            final boolean xna = this.Xna;
            this.x();
            if (n == 1) {
                this.Zja.roa[0].E(1);
                this.Zja.soa[0].E(1);
                this.Zja.roa[0].m(false);
                this.Zja.soa[0].m(false);
                this.Zja.x(true);
                this.Xna = false;
            }
            else if (n == 2) {
                this.Zja.roa[0].E(1);
                this.Zja.soa[0].E(1);
                this.Zja.roa[0].m(false);
                this.Zja.soa[0].m(false);
                this.Zja.x(false);
                this.Xna = false;
            }
            else if (n == 3) {
                this.Zja.roa[0].E(0);
                this.Zja.soa[0].E(0);
                this.Zja.roa[0].m(false);
                this.Zja.soa[0].m(false);
                this.Zja.x(true);
                this.Xna = false;
            }
            else if (n == 4) {
                this.Zja.roa[0].E(0);
                this.Zja.soa[0].E(0);
                this.Zja.roa[0].m(false);
                this.Zja.soa[0].m(false);
                this.Zja.x(false);
                this.Xna = false;
            }
            else if (n == 5) {
                this.Zja.roa[0].E(0);
                this.Zja.soa[0].E(0);
                this.Zja.roa[0].m(true);
                this.Zja.soa[0].m(true);
                this.Zja.x(true);
                this.Xna = true;
            }
            if (this.Xna || this.Xna != xna) {
                this.Wna = true;
            }
            if (this.Xna != xna) {
                this.Zja.M(0);
            }
            this.y();
        }
    }
    
    public synchronized void _(final int n, final int n2) {
        synchronized (this.Zja) {
            this.x();
            this.Zja.N(n);
            if (this.ooa) {
                this.w();
                this.ooa = false;
            }
            this.e(n2);
            this.y();
        }
    }
    
    public void w() {
        this.Zja.g(Color.black);
        this.Zja.h(null);
        this.Zja.i(Color.gray);
        this.Zja.rka.C(2);
        this.Zja.rka.B(3);
        this.Zja.rka.E(2);
        this.Zja.soa[0].G(2);
        this.Zja.roa[0].G(2);
        this.Zja.rka.setColor(Color.white);
        this.Zja.rka.Pma.h(false);
        this.Zja.rka.Pma.i(false);
        this.Zja.rka.Pma.setColor(Color.white);
        this.Zja.rka.z(this.foa);
        for (int i = 0; i < this.Zja.N(); ++i) {
            this.Zja.a(i).h(false);
            this.Zja.a(i).i(false);
            this.Zja.a(i).f(Color.white);
            this.Zja.a(i).g(Color.black);
            this.Zja.a(i).setColor(Color.yellow);
            this.Zja.roa[i].setColor(Color.white);
            this.Zja.roa[i].Pma.h(false);
            this.Zja.roa[i].Pma.i(false);
            this.Zja.roa[i].Pma.setColor(Color.white);
            this.Zja.soa[i].setColor(Color.white);
            this.Zja.soa[i].Pma.h(false);
            this.Zja.soa[i].Pma.i(false);
            this.Zja.soa[i].Pma.setColor(Color.white);
            this.Zja.soa[i].C(2);
            this.Zja.roa[i].C(2);
            this.Zja.soa[i].B(3);
            this.Zja.roa[i].B(3);
        }
        this.z();
        this.A();
    }
    
    private void A() {
        final String a = this.kb.toa.a("AxisLabelFontName");
        final int b = this.kb.toa.b("AxisLabelFontSize");
        final int k = this.kb.toa.k("AxisLabelFontStyle");
        this.Zja.rka.Pma.setFont(this._(this.Zja.rka.Pma.getFont(), a, b, k));
        for (int i = 0; i < this.Zja.N(); ++i) {
            this.Zja.soa[i].Pma.setFont(this._(this.Zja.soa[i].Pma.getFont(), a, b, k));
            this.Zja.roa[i].Pma.setFont(this._(this.Zja.roa[i].Pma.getFont(), a, b, k));
        }
        final String a2 = this.kb.toa.a("ChartTitleFontName");
        final int b2 = this.kb.toa.b("ChartTitleFontSize");
        final int j = this.kb.toa.k("ChartTitleFontStyle");
        for (int l = 0; l < this.Zja.N(); ++l) {
            this.Zja.a(l).setFont(this._(this.Zja.a(l).getFont(), a2, b2, j));
        }
        this.Zja.uoa.setFont(this._(this.Zja.uoa.getFont(), this.kb.toa.a("InfoLineFontName"), this.kb.toa.b("InfoLineFontSize"), this.kb.toa.k("InfoLineFontStyle")));
        this.Zja.voa.setFont(this._(this.Zja.voa.getFont(), this.kb.toa.a("LegendFontName"), this.kb.toa.b("LegendFontSize"), this.kb.toa.k("LegendFontStyle")));
        this.Zja.woa.setFont(this._(this.Zja.woa.getFont(), this.kb.toa.a("FooterFontName"), this.kb.toa.b("FooterFontSize"), this.kb.toa.k("FooterFontStyle")));
    }
    
    private void z() {
        final Color[] a = this.kb.a("ChartBg");
        if (a != null) {
            this.Zja.g(a[0]);
        }
        final Color[] a2 = this.kb.a("ChartFg");
        if (a2 != null) {
            this.Zja.h(a2[0]);
        }
        final Color[] a3 = this.kb.a("ChartFrame");
        if (a3 != null) {
            this.Zja.f(a3[0]);
        }
        final Color[] a4 = this.kb.a("ChartTopLeftFrame");
        if (a4 != null) {
            this.Zja.j(a4[0]);
        }
        final Color[] a5 = this.kb.a("ChartBottomRightFrame");
        if (a5 != null) {
            this.Zja.k(a5[0]);
        }
        final Color[] a6 = this.kb.a("Grid");
        if (a6 != null) {
            this.Zja.i(a6[0]);
        }
        final Color[] a7 = this.kb.a("ProgressBar");
        if (a7 != null) {
            this.Zja.l(a7[0]);
        }
        final Color[] a8 = this.kb.a("Crosshair");
        if (a8 != null) {
            this.Zja.m(a8[0]);
        }
        final Color[] a9 = this.kb.a("InfoLine");
        if (a9 != null) {
            this.Zja.uoa.setColor(a9[0]);
        }
        final Color[] a10 = this.kb.a("InfoLineBg");
        this.Zja.uoa.h(a10 != null);
        if (a10 != null) {
            this.Zja.uoa.g(a10[0]);
        }
        final Color[] a11 = this.kb.a("InfoLineFrame");
        this.Zja.uoa.i(a11 != null);
        if (a11 != null) {
            this.Zja.uoa.f(a11[0]);
        }
        final Color[] a12 = this.kb.a("Axis");
        if (a12 != null) {
            this.Zja.rka.setColor(a12[0]);
        }
        final Color[] a13 = this.kb.a("AxisLabel");
        if (a13 != null) {
            this.Zja.rka.Pma.setColor(a13[0]);
        }
        final Color[] a14 = this.kb.a("AxisLabelBg");
        this.Zja.rka.Pma.h(a14 != null);
        if (a14 != null) {
            this.Zja.rka.Pma.g(a14[0]);
        }
        final Color[] a15 = this.kb.a("AxisLabelFrame");
        this.Zja.rka.Pma.i(a15 != null);
        if (a15 != null) {
            this.Zja.rka.Pma.f(a15[0]);
        }
        final Color[] a16 = this.kb.a("FooterText");
        if (a16 != null) {
            this.Zja.woa.setColor(a16[0]);
        }
        final Color[] a17 = this.kb.a("FooterTextBg");
        this.Zja.woa.h(a17 != null);
        if (a17 != null) {
            this.Zja.woa.g(a17[0]);
        }
        final Color[] a18 = this.kb.a("FooterTextFrame");
        this.Zja.woa.i(a18 != null);
        if (a18 != null) {
            this.Zja.woa.f(a18[0]);
        }
        for (int i = 0; i < this.Zja.N(); ++i) {
            final Color[] a19 = this.kb.a("ChartTitle");
            if (a19 != null) {
                this.Zja.a(i).setColor(a19[0]);
            }
            final Color[] a20 = this.kb.a("ChartTitleBg");
            this.Zja.a(i).h(a20 != null);
            if (a20 != null) {
                this.Zja.a(i).g(a20[0]);
            }
            final Color[] a21 = this.kb.a("ChartTitleFrame");
            this.Zja.a(i).i(a21 != null);
            if (a21 != null) {
                this.Zja.a(i).f(a21[0]);
            }
            final Color[] a22 = this.kb.a("Axis");
            if (a22 != null) {
                this.Zja.roa[i].setColor(a22[0]);
                this.Zja.soa[i].setColor(a22[0]);
            }
            final Color[] a23 = this.kb.a("AxisLabel");
            if (a23 != null) {
                this.Zja.roa[i].Pma.setColor(a23[0]);
                this.Zja.soa[i].Pma.setColor(a23[0]);
            }
            final Color[] a24 = this.kb.a("AxisLabelBg");
            this.Zja.roa[i].Pma.h(a24 != null);
            this.Zja.soa[i].Pma.h(a24 != null);
            if (a24 != null) {
                this.Zja.roa[i].Pma.g(a24[0]);
                this.Zja.soa[i].Pma.g(a24[0]);
            }
            final Color[] a25 = this.kb.a("AxisLabelFrame");
            this.Zja.roa[i].Pma.i(a25 != null);
            this.Zja.soa[i].Pma.i(a25 != null);
            if (a25 != null) {
                this.Zja.roa[i].Pma.f(a25[0]);
                this.Zja.soa[i].Pma.f(a25[0]);
            }
        }
    }
    
    public void m() {
        final Color[] a = this.kb.a("Crosshair");
        if (a != null) {
            this.Zja.m(a[0]);
        }
        final Color[] a2 = this.kb.a("Grid");
        if (a2 != null) {
            this.Zja.i(a2[0]);
        }
    }
    
    public void x() {
        this.Zja.y(true);
    }
    
    public void y() {
        this.Zja.y(false);
    }
    
    public continue _() {
        return this.Zja;
    }
    
    public synchronized void b() {
        this.Zja.B();
    }
    
    private void C() {
        this.aoa = -1.0;
        this.boa = -1.0;
        this.coa = -1.0;
        this.doa = -1.0;
    }
    
    public synchronized void z(final boolean poa) {
        this.poa = poa;
    }
    
    public synchronized void _() {
        this.Wna = false;
        final double[] y = this.kb._().Y();
        final double[] g = this.kb._().g();
        final double[] a = this.kb._().a();
        final double[] b = this.kb._().b();
        final double[] _ = this.kb._()._();
        double[] f = this.kb._().f();
        double[] h = this.kb._().h();
        if (y == null || g == null || a == null || b == null || _ == null || f == null || h == null || !this.kb._().R()) {
            for (int i = 0; i < this.Zja.ea(); ++i) {
                this.Zja.O(i);
                this.Zja.a(i, "");
            }
            if (!this.kb._().R() || this.kb._()._() == null || this.kb._()._().length() == 0) {
                this.Zja.a(0, this.kb._().getMessage());
            }
            else {
                this.Zja.a(0, this.kb.a().b("msgNoDataAvailable"));
            }
            return;
        }
        if ((this.kb._().h() && this.Za != -1) || ((this.kb._().b() || this.Za != -1) && (this.eoa == null || this.eoa != this.kb._()._()))) {
            this.C();
        }
        this.eoa = this.kb._()._();
        boolean b2 = true;
        for (int j = 0; j < h.length; ++j) {
            if (f[j] > 0.0) {
                b2 = false;
            }
        }
        if (b2) {
            f = null;
        }
        boolean b3 = true;
        for (int k = 0; k < h.length; ++k) {
            if (h[k] > 0.0) {
                b3 = false;
            }
        }
        if (b3) {
            h = null;
        }
        this.qoa = (this._oa == 1 || (this._oa == 0 && (f != null || h != null)));
        double[] z = null;
        double[] a2 = null;
        double[] aa = null;
        double[] ba = null;
        double[] ca = null;
        if (this.kb._().R() && this.kb._().g() != null && this.kb._().g().length() > 0) {
            z = this.kb._().Z();
            a2 = this.kb._()._a();
            aa = this.kb._().aa();
            ba = this.kb._().ba();
            ca = this.kb._().ca();
        }
        synchronized (this.Zja) {
            this.x();
            this.Zja.D();
            int n = 1;
            if (this.qoa) {
                ++n;
            }
            if (this.kb.a() != null) {
                ++n;
            }
            if (this.kb.b() != null) {
                ++n;
            }
            if (n != this.Zja.ea()) {
                this.Zja.N(n);
            }
            if (this.kb._().b()) {
                this.Zja.rka.D(2);
            }
            else {
                this.Zja.rka.D(1);
            }
            this.Zja.O(0);
            if (this.Zja.ea() > 0) {
                this.Zja.soa[0].G(this.kb._().O());
                this.Zja.roa[0].G(this.kb._().O());
            }
            final String[] c = this.kb._().c();
            for (int l = 0; l < c.length; ++l) {
                final double[][] _2 = this.kb._()._(c[l]);
                if (_2 != null) {
                    final int[] _3 = this.kb._("ExtraPrice");
                    int n2 = 0;
                    if (_3 != null && _3.length > 0) {
                        n2 = _3[l % _3.length];
                    }
                    final import import1 = new import(_2[0].length, n2);
                    import1.setName(c[l]);
                    final Color[] a3 = this.kb.a("ExtraPrice");
                    if (a3 != null && a3.length > 0) {
                        import1.setColor(a3[l % a3.length]);
                    }
                    else {
                        import1.setColor(Color.red);
                    }
                    for (int n3 = 0; n3 < _2[0].length; ++n3) {
                        import1.a(_2[0][n3], _2[1][n3]);
                    }
                    this.Zja.b(0, import1);
                }
            }
            implements[] a4 = null;
            if (!this.Xna) {
                a4 = this.kb.a();
                if (a4 != null) {
                    for (int n4 = 0; n4 < a4.length; ++n4) {
                        if (!(a4[n4] instanceof interface)) {
                            this._(0, y, a4[n4]);
                        }
                    }
                }
            }
            if (!this.Xna) {
                this._(y, this.kb._().d(), a, b, _);
            }
            if (this.Xa == 1) {
                final native native1 = new native(y.length, this.koa, this.hoa);
                final Color[] a5 = this.kb.a("Price");
                if (a5 != null) {
                    native1.setColor(a5[0]);
                }
                else {
                    native1.setColor(Color.yellow);
                }
                native1.setName(this.kb._()._());
                native1.y(this.kb.a().b("strDate"));
                native1._(0, "O");
                native1._(1, "H");
                native1._(2, "L");
                native1._(3, "C");
                for (int n5 = 0; n5 < y.length; ++n5) {
                    native1._(y[n5], g[n5], a[n5], b[n5], _[n5]);
                }
                this.Zja.b(0, native1);
            }
            else if (this.Xa == 2) {
                final new new1 = new new(y.length, this.loa, this.ioa);
                final Color[] a6 = this.kb.a("Price");
                if (a6 != null) {
                    new1.setColor(a6[0]);
                }
                else {
                    new1.setColor(Color.yellow);
                }
                final Color[] a7 = this.kb.a("BullishCandle");
                if (a7 != null) {
                    new1.b(a7[0]);
                }
                else {
                    new1.b(Color.green.darker());
                }
                final Color[] a8 = this.kb.a("BearishCandle");
                if (a8 != null) {
                    new1.a(a8[0]);
                }
                else {
                    new1.a(Color.red);
                }
                new1.setName(this.kb._()._());
                new1.y(this.kb.a().b("strDate"));
                new1._(0, "O");
                new1._(1, "H");
                new1._(2, "L");
                new1._(3, "C");
                for (int n6 = 0; n6 < y.length; ++n6) {
                    new1._(y[n6], g[n6], a[n6], b[n6], _[n6]);
                }
                this.Zja.b(0, new1);
            }
            else {
                final int[] _4 = this.kb._("Price");
                int n7 = 0;
                if (_4 != null) {
                    n7 = _4[0];
                }
                final import import2 = new import(y.length, n7);
                final Color[] a9 = this.kb.a("Price");
                if (a9 != null) {
                    import2.setColor(a9[0]);
                }
                else {
                    import2.setColor(Color.yellow);
                }
                import2.setName(this.kb._()._());
                import2.y(this.kb.a().b("strDate"));
                import2._(0, this.kb.a().b("strPriceTitle"));
                for (int n8 = 0; n8 < y.length; ++n8) {
                    import2.a(y[n8], _[n8]);
                }
                this.Zja.b(0, import2);
            }
            if (this.kb._().R() && this.kb._().g() != null && this.kb._().g().length() > 0 && z != null && a2 != null && aa != null && ba != null && ca != null) {
                if (this.Xa == 1) {
                    final native native2 = new native(z.length, this.koa, this.hoa);
                    final Color[] a10 = this.kb.a("BgPrice");
                    if (a10 != null) {
                        native2.setColor(a10[0]);
                    }
                    else {
                        native2.setColor(Color.lightGray);
                    }
                    native2.setName(this.kb._().g());
                    native2.y(this.kb.a().b("strDate"));
                    native2._(0, "O");
                    native2._(1, "H");
                    native2._(2, "L");
                    native2._(3, "C");
                    for (int n9 = 0; n9 < z.length; ++n9) {
                        native2._(z[n9], a2[n9], aa[n9], ba[n9], ca[n9]);
                    }
                    this.Zja.b(0, 0, native2);
                }
                else if (this.Xa == 2) {
                    final new new2 = new new(z.length, this.loa, this.ioa);
                    final Color[] a11 = this.kb.a("BgPrice");
                    if (a11 != null) {
                        new2.setColor(a11[0]);
                        new2.b(a11[0]);
                        new2.a(a11[0]);
                    }
                    else {
                        new2.setColor(Color.gray);
                        new2.b(Color.gray);
                        new2.a(Color.gray);
                    }
                    new2.setName(this.kb._().g());
                    new2.y(this.kb.a().b("strDate"));
                    new2._(0, "O");
                    new2._(1, "H");
                    new2._(2, "L");
                    new2._(3, "C");
                    for (int n10 = 0; n10 < z.length; ++n10) {
                        new2._(z[n10], a2[n10], aa[n10], ba[n10], ca[n10]);
                    }
                    this.Zja.b(0, 0, new2);
                }
                else {
                    final int[] _5 = this.kb._("BgPrice");
                    int n11 = 0;
                    if (_5 != null) {
                        n11 = _5[0];
                    }
                    final import import3 = new import(z.length, n11);
                    final Color[] a12 = this.kb.a("BgPrice");
                    if (a12 != null) {
                        import3.setColor(a12[0]);
                    }
                    else {
                        import3.setColor(Color.gray);
                    }
                    import3.setName(this.kb._().g());
                    import3.y(this.kb.a().b("strDate"));
                    import3._(0, this.kb.a().b("strPriceTitle"));
                    for (int n12 = 0; n12 < z.length; ++n12) {
                        import3.a(z[n12], ca[n12]);
                    }
                    this.Zja.b(0, 0, import3);
                }
            }
            if (this.Zja.ea() > 1 && this.qoa) {
                this.Zja.O(1);
                if ((this.goa[1] == null || !this.goa[1].equals("VOLUME")) && !this.poa) {
                    this.Zja.M(1);
                }
                this.goa[1] = "VOLUME";
                if (f != null) {
                    final null null = new null(y.length, this.moa, this.joa, this.noa);
                    final Color[] a13 = this.kb.a("Volume");
                    if (a13 != null) {
                        null.setColor(a13[0]);
                    }
                    else {
                        null.setColor(Color.green);
                    }
                    null._(0, "V");
                    this.Zja.roa[1].C(0);
                    for (int n13 = 0; n13 < y.length; ++n13) {
                        null.a(y[n13], f[n13]);
                    }
                    this.Zja.b(1, null);
                    if (a4 != null) {
                        for (int n14 = 0; n14 < a4.length; ++n14) {
                            if (a4[n14] instanceof interface) {
                                this._(1, y, a4[n14]);
                            }
                        }
                    }
                }
                if (h != null) {
                    final int[] _6 = this.kb._("OpenInterest");
                    int n15 = 0;
                    if (_6 != null) {
                        n15 = _6[0];
                    }
                    final import import4 = new import(y.length, n15);
                    final Color[] a14 = this.kb.a("OpenInterest");
                    if (a14 != null) {
                        import4.setColor(a14[0]);
                    }
                    else {
                        import4.setColor(Color.blue);
                    }
                    import4._(0, "OI");
                    for (int n16 = 0; n16 < y.length; ++n16) {
                        import4.a(y[n16], h[n16]);
                    }
                    if (f != null) {
                        this.Zja.b(1, 0, import4);
                    }
                    else {
                        this.Zja.roa[1].n(true);
                        this.Zja.roa[1].C(2);
                        this.Zja.b(1, 1, import4);
                    }
                }
                if (this.Zja.a(1) == 0) {
                    final null null2 = new null(y.length, this.moa, this.joa, this.noa);
                    final Color[] a15 = this.kb.a("Volume");
                    if (a15 != null) {
                        null2.setColor(a15[0]);
                    }
                    else {
                        null2.setColor(Color.green);
                    }
                    this.Zja.roa[1].C(0);
                    for (int n17 = 0; n17 < y.length; ++n17) {
                        null2.a(y[n17], 0.0);
                    }
                    this.Zja.b(1, null2);
                }
            }
            final implements a16 = this.kb.a();
            final implements b4 = this.kb.b();
            int n18;
            int n19;
            if (this.qoa) {
                n18 = 2;
                n19 = 3;
            }
            else {
                n18 = 1;
                n19 = 2;
            }
            if (this.Zja.ea() > n18) {
                this.Zja.O(n18);
                if (a16 != null) {
                    if ((this.goa[n18] == null || !this.goa[n18].equals(a16.toString())) && !this.poa) {
                        this.Zja.M(n18);
                    }
                    this.goa[n18] = a16.toString();
                    this.Zja.roa[n18].n(true);
                    this.Zja.roa[n18].C(2);
                    this._(n18, y, a16);
                }
                else if (b4 != null) {
                    if ((this.goa[n18] == null || !this.goa[n18].equals(b4.toString())) && !this.poa) {
                        this.Zja.M(n18);
                    }
                    this.goa[n18] = b4.toString();
                    this.Zja.roa[n18].n(true);
                    this.Zja.roa[n18].C(2);
                    this._(n18, y, b4);
                }
            }
            if (this.Zja.ea() > n19) {
                this.Zja.O(n19);
                if (a16 != null && b4 != null) {
                    if ((this.goa[n19] == null || !this.goa[n19].equals(b4.toString())) && !this.poa) {
                        this.Zja.M(n19);
                    }
                    this.goa[n19] = b4.toString();
                    this.Zja.roa[n19].n(true);
                    this.Zja.roa[n19].C(2);
                    this._(n19, y, b4);
                }
            }
            this.Zja.a(0, "" + this.kb._()._());
            if (this.Zja.ea() > 1 && this.qoa) {
                if (h != null && f != null) {
                    this.Zja.a(1, this.kb.a().b("strOpenInterest") + ", " + this.kb.a().b("strVolume"));
                }
                else if (f != null) {
                    this.Zja.a(1, this.kb.a().b("strVolume"));
                }
                else if (h != null) {
                    this.Zja.a(1, this.kb.a().b("strOpenInterest"));
                }
                else {
                    this.Zja.a(1, "");
                }
            }
            if (this.Zja.ea() > n18) {
                if (a16 != null) {
                    this.Zja.a(n18, a16.I());
                }
                else if (b4 != null) {
                    this.Zja.a(n18, b4.I());
                }
            }
            if (this.Zja.ea() > n19 && a16 != null && b4 != null) {
                this.Zja.a(n19, b4.I());
            }
            this.E();
            if (this.Zja.ea() > 1 && this.qoa && this.Zja.a(1) > 0) {
                final instanceof a17 = this.Zja.a(1, 0);
                if (a17 != null && a17 instanceof null) {
                    this.Zja.roa[1].n(false);
                    this.Zja.roa[1].j(0.0, a17.n());
                }
            }
            final Color[] a18 = this.kb.a("TrendLine");
            if (a18 != null) {
                this.Zja.n(a18[0]);
            }
            final int[] _7 = this.kb._("TrendLine");
            if (_7 != null) {
                this.Zja.P(_7[0]);
            }
            if (this.Xna) {
                this.F();
            }
            this.Zja.G();
            this.y();
        }
    }
    
    private void F() {
        for (int i = 0; i < this.Zja.a(0); ++i) {
            this._(this.Zja.a(0, i));
        }
        for (int j = 0; j < this.Zja.b(0, 0); ++j) {
            this._(this.Zja._(0, 0, j));
        }
    }
    
    private void _(final instanceof instanceof1) {
        if (instanceof1 == null) {
            return;
        }
        final int u = instanceof1.u();
        if (u > instanceof1.v()) {
            return;
        }
        instanceof1.s();
        final double b = instanceof1.b(u, instanceof1.B() - 1);
        if (b != 0.0) {
            for (int i = 0; i < instanceof1.B(); ++i) {
                for (int j = 0; j <= instanceof1.A(); ++j) {
                    instanceof1.a(j, i, 100.0 * (instanceof1.b(j, i) - b) / b);
                }
            }
        }
    }
    
    public static int _(final double[] array, final double n) {
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
    
    private double b(final double n, final double n2) {
        double n3 = n2;
        if (this.Za != 0 && this.Za != -1) {
            switch (this.Za) {
                case 10: {
                    final r r = new r(n);
                    r.Q(-1);
                    n3 = r._();
                    break;
                }
                case 11: {
                    final r r2 = new r(n);
                    r2.Q(-3);
                    n3 = r2._();
                    break;
                }
                case 12: {
                    final r r3 = new r(n);
                    r3.Q(-6);
                    n3 = r3._();
                    break;
                }
                case 13: {
                    final r r4 = new r(n);
                    r4.Q(-12);
                    n3 = r4._();
                    break;
                }
                case 14: {
                    final r r5 = new r(n);
                    r5.Q(-24);
                    n3 = r5._();
                    break;
                }
                case 15: {
                    final r r6 = new r(n);
                    r6.Q(-36);
                    n3 = r6._();
                    break;
                }
                case 16: {
                    final r r7 = new r(n);
                    r7.Q(-60);
                    n3 = r7._();
                    break;
                }
                case 17: {
                    final r r8 = new r(n);
                    r8.Q(-120);
                    n3 = r8._();
                    break;
                }
            }
        }
        return n3;
    }
    
    public synchronized int k() {
        return this.Za;
    }
    
    public synchronized void m(final int za) {
        this.Za = za;
        instanceof a = null;
        if (this.Zja.ea() > 0 && this.Zja.a(0) > 0) {
            a = this.Zja.a(0, this.Zja.a(0) - 1);
        }
        if (a == null) {
            return;
        }
        final boolean b = this.kb._().b();
        synchronized (this.Zja) {
            this.x();
            this.Zja.D();
            final double[] da = this.Zja.da();
            if (da == null || da.length == 0) {
                return;
            }
            final double n = da[da.length - 1];
            final double b2 = this.b(n, da[0]);
            this.A(true);
            this.Zja.l(b2, n);
            if (this.Zja.ea() > 1 && this.qoa && this.Zja.a(1) > 0) {
                final instanceof a2 = this.Zja.a(1, 0);
                if (a2 != null && a2 instanceof null) {
                    this.Zja.roa[1].n(false);
                    this.Zja.roa[1].j(0.0, a2.n());
                }
            }
            if (b) {
                this.coa = b2;
                this.doa = n;
            }
            else {
                this.aoa = b2;
                this.boa = n;
            }
            if (this.Xna) {
                this.Wna = true;
                this.Zja.M(0);
            }
            this.Zja.G();
            this.y();
        }
    }
    
    public synchronized void n(final int n) {
        instanceof a = null;
        if (this.Zja.ea() > 0 && this.Zja.a(0) > 0) {
            a = this.Zja.a(0, this.Zja.a(0) - 1);
        }
        if (a == null) {
            return;
        }
        synchronized (this.Zja) {
            this.x();
            this.Zja.D();
            final double[] da = this.Zja.da();
            if (da == null) {
                return;
            }
            final double[] ea = this.Zja.ea();
            if (ea == null) {
                return;
            }
            final int n2 = 0;
            final int n3 = da.length - 1;
            int n4 = _(da, ea[0]);
            if (n4 < 0) {
                n4 = Math.max(-n4 - 1, 0);
            }
            int n5 = _(da, ea[1]);
            if (n5 < 0) {
                n5 = Math.min(-n5 - 1, da.length - 1);
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
                    this.Za = -1;
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
                    this.Za = -1;
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
            final double n8 = da[n4];
            final double n9 = da[n5];
            this.A(n5 >= n3);
            this.Zja.l(n8, n9);
            if (this.Zja.ea() > 1 && this.qoa && this.Zja.a(1) > 0) {
                final instanceof a2 = this.Zja.a(1, 0);
                if (a2 != null && a2 instanceof null) {
                    this.Zja.roa[1].n(false);
                    this.Zja.roa[1].j(0.0, a2.n());
                }
            }
            if (this.kb._().b()) {
                if (this.coa == n8 && this.doa == n9) {
                    Toolkit.getDefaultToolkit().beep();
                }
                this.coa = n8;
                this.doa = n9;
            }
            else {
                if (this.aoa == n8 && this.boa == n9) {
                    Toolkit.getDefaultToolkit().beep();
                }
                this.aoa = n8;
                this.boa = n9;
            }
            if (this.Xna) {
                this.Wna = true;
                this.Zja.M(0);
            }
            this.Zja.G();
            this.y();
        }
    }
    
    private void E() {
        double n;
        double n2;
        if (this.kb._().b()) {
            n = this.coa;
            n2 = this.doa;
        }
        else {
            n = this.aoa;
            n2 = this.boa;
        }
        instanceof a = null;
        if (this.Zja.ea() > 0 && this.Zja.a(0) > 0) {
            a = this.Zja.a(0, this.Zja.a(0) - 1);
        }
        if (a == null) {
            return;
        }
        final double[] da = this.Zja.da();
        if (da == null) {
            return;
        }
        final int n3 = 0;
        final int n4 = da.length - 1;
        final double n5 = da[n3];
        final double n6 = da[n4];
        if (n == -1.0 || n2 == -1.0 || n > n6 || n2 < n5) {
            n2 = n6;
            double n7;
            if (this.kb._().b()) {
                n7 = this.b(n2, n5);
            }
            else if (this.Za == -1) {
                n7 = n6 - 160.0;
            }
            else {
                n7 = this.b(n2, n5);
            }
            if (n7 < n5) {
                n7 = n5;
            }
            n = n7;
        }
        else if (this.S() && n2 < n6) {
            n2 = n6;
        }
        this.Zja.B(true);
        this.Zja.l(n, n2);
        if (this.Zja.ea() == null) {
            final double d = a.d();
            final double b = this.b(d, a.c());
            this.Zja.B(true);
            this.Zja.l(b, d);
        }
        if (this.Zja.ea() > 1 && this.qoa && this.Zja.a(1) > 0) {
            final instanceof a2 = this.Zja.a(1, 0);
            if (a2 != null && a2 instanceof null) {
                this.Zja.roa[1].n(false);
                this.Zja.roa[1].j(0.0, a2.n());
            }
        }
    }
    
    public void repaint() {
        if (this.Wna) {
            this._();
        }
        this.y();
        this.Zja.H();
    }
    
    private Font _(final Font font, String name, int size, int style) {
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
    
    private void _(final int n, final double[] array, final implements implements1) {
        int n2 = 0;
        if (implements1 instanceof package) {
            final int[] array2 = { 1, 2, 3, 4, 5 };
            final String[] array3 = { "PivotPoints", "R1", "S1", "R2", "S2" };
            for (int i = 0; i < array2.length; ++i) {
                final int n3 = array2[i];
                final String name = array3[i];
                final int[] _ = this.kb._("Indicator");
                int n4 = 0;
                if (_ != null && _.length > 0) {
                    n4 = _[0];
                }
                final int[] _2 = this.kb._(name);
                if (_2 != null && _2.length > 0) {
                    n4 = _2[0];
                }
                final private private1 = new private(array.length, n4);
                final Color[] a = this.kb.a("Indicator");
                if (a != null) {
                    n2 = 1;
                    private1.setColor(a[0]);
                }
                else {
                    private1.setColor(Color.lightGray);
                }
                final Color[] a2 = this.kb.a(name);
                if (a2 != null) {
                    private1.setColor(a2[0]);
                }
                else if (n2 == 0) {
                    private1.setColor(Color.lightGray);
                }
                private1.setName(name);
                final double[] b = implements1.b(0);
                final double[] b2 = implements1.b(n3);
                if (b != null && b2 != null) {
                    boolean b3 = false;
                    for (int j = 0; j < b2.length; ++j) {
                        if (b2[j] > 0.0) {
                            b3 = true;
                            break;
                        }
                    }
                    if (b3) {
                        for (int n5 = 0; n5 < array.length && n5 < b2.length; ++n5) {
                            private1._(array[n5], b[n5], b2[n5]);
                        }
                        private1.w(implements1._(n3));
                        this.Zja.b(n, private1);
                    }
                }
            }
        }
        else {
            for (int k = implements1.m() - 1; k >= 0; --k) {
                final int[] _3 = this.kb._("Indicator");
                int n6 = 0;
                if (_3 != null && k < _3.length) {
                    n6 = _3[k];
                }
                final int[] _4 = this.kb._(implements1.toString());
                if (_4 != null && k < _4.length) {
                    n6 = _4[k];
                }
                instanceof instanceof1;
                if (implements1 instanceof protected) {
                    instanceof1 = new public(array.length, n6);
                }
                else if ((k == 2 && implements1 instanceof return) || implements1 instanceof static || implements1 instanceof super) {
                    instanceof1 = new null(array.length, this.moa, this.joa, this.noa);
                }
                else {
                    instanceof1 = new import(array.length, n6);
                }
                final Color[] a3 = this.kb.a("Indicator");
                if (a3 != null) {
                    n2 = 1;
                    if (k < a3.length) {
                        instanceof1.setColor(a3[k]);
                    }
                    else {
                        instanceof1.setColor(a3[0]);
                    }
                }
                else {
                    instanceof1.setColor(Color.lightGray);
                }
                final Color[] a4 = this.kb.a("IndicatorSpecificLine");
                if (a4 != null) {
                    instanceof1.e(a4[0]);
                }
                final Color[] a5 = this.kb.a(implements1.toString());
                if (a5 != null) {
                    if (k < a5.length) {
                        instanceof1.setColor(a5[k]);
                    }
                    else {
                        instanceof1.setColor(a5[0]);
                    }
                }
                else if (n2 == 0) {
                    instanceof1.setColor(Color.lightGray);
                }
                if (k == 0) {
                    final String[] e = this.kb.e();
                    boolean b4 = true;
                    for (int l = 0; l < e.length; ++l) {
                        if (e[l].equals(implements1.toString())) {
                            b4 = false;
                        }
                    }
                    if (b4) {
                        instanceof1._(0, implements1.toString());
                    }
                }
                instanceof1.setName(implements1.toString());
                instanceof1.y(this.kb.a().b("strDate"));
                final double[] b5 = implements1.b(k);
                final double[] array4 = { 0.0 };
                if (b5 != null) {
                    for (int n7 = 0; n7 < array.length && n7 < b5.length; ++n7) {
                        array4[0] = b5[n7];
                        instanceof1.b(array[n7], array4);
                    }
                    instanceof1.w(implements1._(k));
                    this.Zja.b(n, instanceof1);
                    if (k == 0 && n > 0) {
                        final byte[] _5 = implements1._();
                        if (_5 != null) {
                            final Color[] a6 = this.kb.a("BuySignal");
                            Color darker;
                            if (a6 != null && a6.length > 0) {
                                darker = a6[0];
                            }
                            else {
                                darker = Color.green.darker();
                            }
                            final Color[] a7 = this.kb.a("SellSignal");
                            Color red;
                            if (a7 != null && a7.length > 0) {
                                red = a7[0];
                            }
                            else {
                                red = Color.red;
                            }
                            final switch switch1 = new switch(array.length, darker, red);
                            for (int n8 = 0; n8 < array.length && n8 < b5.length && n8 < _5.length; ++n8) {
                                switch1._(array[n8], b5[n8], _5[n8]);
                            }
                            switch1.w(0);
                            this.Zja.b(n, switch1);
                        }
                    }
                }
                if (implements1.n() > 0) {
                    final double[] array5 = new double[implements1.n()];
                    for (int n9 = 0; n9 < array5.length; ++n9) {
                        array5[n9] = implements1._(n9);
                    }
                    instanceof1.a(array5);
                }
            }
        }
    }
    
    private void _(final double[] array, final String[] array2, final double[] array3, final double[] array4, final double[] array5) {
        if (array == null || array2 == null || array3 == null || array4 == null || array5 == null) {
            return;
        }
        final synchronized synchronized1 = new synchronized(array.length);
        int n = -1;
        for (int i = 0; i < array2.length; ++i) {
            int id = 0;
            double n2 = array5[i];
            double n3 = 0.0;
            if (array2[i] != null) {
                if (n == -1) {
                    n = i;
                }
                final this this2 = new this(array2[i]);
                id = this2.id;
                if (id != 0) {
                    n3 = this2.xa.getRGB();
                    if (this.Xa == 0) {
                        n2 = array5[i];
                    }
                    else if (id == 1) {
                        n2 = array4[i];
                    }
                    else if (id == 2) {
                        n2 = array3[i];
                    }
                    else {
                        n2 = array5[i];
                    }
                }
            }
            synchronized1.a(array[i], id, n2, n3);
        }
        if (n >= 0) {
            synchronized1.w(n);
            this.Zja.b(0, synchronized1);
        }
    }
    
    private boolean S() {
        if (this.kb._().b()) {
            return this.Yna;
        }
        return this.Zna;
    }
    
    private void A(final boolean b) {
        if (this.kb._().b()) {
            this.Yna = b;
        }
        else {
            this.Zna = b;
        }
    }
    
    public void d(final int oa) {
        this._oa = oa;
    }
    
    public synchronized int i() {
        return this.Xa;
    }
    
    public void R(final int hoa) {
        this.hoa = hoa;
    }
    
    public void S(final int ioa) {
        this.ioa = ioa;
    }
    
    public void T(final int joa) {
        this.joa = joa;
    }
    
    public void U(final int koa) {
        this.koa = koa;
    }
    
    public void V(final int loa) {
        this.loa = loa;
    }
    
    public void W(final int moa) {
        this.moa = moa;
    }
    
    public void C(final boolean noa) {
        this.noa = noa;
    }
    
    public int P() {
        return this.hoa;
    }
    
    public int Q() {
        return this.ioa;
    }
    
    public int R() {
        return this.joa;
    }
    
    public int S() {
        return this.koa;
    }
    
    public int T() {
        return this.loa;
    }
    
    public int U() {
        return this.moa;
    }
}
