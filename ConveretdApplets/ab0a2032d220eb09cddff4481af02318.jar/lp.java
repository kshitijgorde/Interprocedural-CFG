import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class lp
{
    public static final int UFb = 0;
    public static final int VFb = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final double WFb = 0.35;
    public static final int XFb = 160;
    public static final int YFb = 0;
    public static final int ZFb = 1;
    public static final int _Gb = 2;
    public static final int aGb = 0;
    public static final int bGb = 1;
    public static final int cGb = 2;
    private boolean dGb;
    private boolean eGb;
    private boolean fGb;
    private boolean gGb;
    private int hGb;
    private g iGb;
    private rp jGb;
    private int kGb;
    private double lGb;
    private double mGb;
    private double nGb;
    private double oGb;
    private String pGb;
    private String qGb;
    private boolean rGb;
    private boolean sGb;
    
    public lp(final g iGb) {
        this.dGb = false;
        this.eGb = false;
        this.fGb = true;
        this.gGb = true;
        this.hGb = 0;
        this.lGb = -1.0;
        this.mGb = -1.0;
        this.nGb = -1.0;
        this.oGb = -1.0;
        this.pGb = null;
        this.qGb = "dd-MMM-yyyy";
        this.rGb = true;
        this.sGb = false;
        this.iGb = iGb;
        this.jGb = new rp(4, iGb.b());
        this.p();
        this.kGb = 0;
    }
    
    public synchronized void P(final int kGb) {
        this.kGb = kGb;
    }
    
    public synchronized void w(final String qGb) {
        this.qGb = qGb;
        this.jGb.o.n(this.qGb);
    }
    
    public void R(final int n) {
        synchronized (this.jGb) {
            final boolean eGb = this.eGb;
            this.V();
            if (n == 1) {
                this.jGb.tEb[0].e(1);
                this.jGb.uEb[0].e(1);
                this.jGb.tEb[0].j(false);
                this.jGb.uEb[0].j(false);
                this.jGb.z(true);
                this.eGb = false;
            }
            else if (n == 2) {
                this.jGb.tEb[0].e(1);
                this.jGb.uEb[0].e(1);
                this.jGb.tEb[0].j(false);
                this.jGb.uEb[0].j(false);
                this.jGb.z(false);
                this.eGb = false;
            }
            else if (n == 3) {
                this.jGb.tEb[0].e(0);
                this.jGb.uEb[0].e(0);
                this.jGb.tEb[0].j(false);
                this.jGb.uEb[0].j(false);
                this.jGb.z(true);
                this.eGb = false;
            }
            else if (n == 4) {
                this.jGb.tEb[0].e(0);
                this.jGb.uEb[0].e(0);
                this.jGb.tEb[0].j(false);
                this.jGb.uEb[0].j(false);
                this.jGb.z(false);
                this.eGb = false;
            }
            else if (n == 5) {
                this.jGb.tEb[0].e(0);
                this.jGb.uEb[0].e(0);
                this.jGb.tEb[0].j(true);
                this.jGb.uEb[0].j(true);
                this.jGb.z(true);
                this.eGb = true;
            }
            if (this.eGb || this.eGb != eGb) {
                this.dGb = true;
            }
            if (this.eGb != eGb) {
                this.jGb.N(0);
            }
            this.W();
        }
    }
    
    public synchronized void e(final int n, final int n2) {
        synchronized (this.jGb) {
            this.V();
            this.jGb.T(n);
            if (this.rGb) {
                this.p();
                this.rGb = false;
            }
            this.R(n2);
            this.W();
        }
    }
    
    public void p() {
        this.jGb.b(Color.black);
        this.jGb.f((Color)null);
        this.jGb.g(Color.gray);
        this.jGb.o.c(2);
        this.jGb.o.n(3);
        this.jGb.o.e(2);
        this.jGb.uEb[0].g(2);
        this.jGb.tEb[0].g(2);
        this.jGb.o.setColor(Color.white);
        this.jGb.o.x.e(false);
        this.jGb.o.x.d(false);
        this.jGb.o.x.setColor(Color.white);
        this.jGb.o.n(this.qGb);
        for (int i = 0; i < this.jGb.H(); ++i) {
            this.jGb._(i).e(false);
            this.jGb._(i).d(false);
            this.jGb._(i).a(Color.white);
            this.jGb._(i).b(Color.black);
            this.jGb._(i).setColor(Color.yellow);
            this.jGb.tEb[i].setColor(Color.white);
            this.jGb.tEb[i].x.e(false);
            this.jGb.tEb[i].x.d(false);
            this.jGb.tEb[i].x.setColor(Color.white);
            this.jGb.uEb[i].setColor(Color.white);
            this.jGb.uEb[i].x.e(false);
            this.jGb.uEb[i].x.d(false);
            this.jGb.uEb[i].x.setColor(Color.white);
            this.jGb.uEb[i].c(2);
            this.jGb.tEb[i].c(2);
            this.jGb.uEb[i].n(3);
            this.jGb.tEb[i].n(3);
        }
        this.q();
        this.r();
    }
    
    private void r() {
        final String a = this.iGb.tGb.a("AxisLabelFontName");
        final int _ = this.iGb.tGb._("AxisLabelFontSize");
        final int a2 = this.iGb.tGb.a("AxisLabelFontStyle");
        this.jGb.o.x.setFont(this._(this.jGb.o.x.getFont(), a, _, a2));
        for (int i = 0; i < this.jGb.H(); ++i) {
            this.jGb.uEb[i].x.setFont(this._(this.jGb.uEb[i].x.getFont(), a, _, a2));
            this.jGb.tEb[i].x.setFont(this._(this.jGb.tEb[i].x.getFont(), a, _, a2));
        }
        final String a3 = this.iGb.tGb.a("ChartTitleFontName");
        final int _2 = this.iGb.tGb._("ChartTitleFontSize");
        final int a4 = this.iGb.tGb.a("ChartTitleFontStyle");
        for (int j = 0; j < this.jGb.H(); ++j) {
            this.jGb._(j).setFont(this._(this.jGb._(j).getFont(), a3, _2, a4));
        }
        this.jGb.aFb.setFont(this._(this.jGb.aFb.getFont(), this.iGb.tGb.a("InfoLineFontName"), this.iGb.tGb._("InfoLineFontSize"), this.iGb.tGb.a("InfoLineFontStyle")));
        this.jGb.bFb.setFont(this._(this.jGb.bFb.getFont(), this.iGb.tGb.a("LegendFontName"), this.iGb.tGb._("LegendFontSize"), this.iGb.tGb.a("LegendFontStyle")));
    }
    
    private void q() {
        final Color[] _ = this.iGb._("ChartBg");
        if (_ != null) {
            this.jGb.b(_[0]);
        }
        final Color[] _2 = this.iGb._("ChartFg");
        if (_2 != null) {
            this.jGb.f(_2[0]);
        }
        final Color[] _3 = this.iGb._("ChartTopLeftFrame");
        if (_3 != null) {
            this.jGb.d(_3[0]);
        }
        final Color[] _4 = this.iGb._("ChartBottomRightFrame");
        if (_4 != null) {
            this.jGb.e(_4[0]);
        }
        final Color[] _5 = this.iGb._("Grid");
        if (_5 != null) {
            this.jGb.g(_5[0]);
        }
        final Color[] _6 = this.iGb._("ProgressBar");
        if (_6 != null) {
            this.jGb.h(_6[0]);
        }
        final Color[] _7 = this.iGb._("Crosshair");
        if (_7 != null) {
            this.jGb.j(_7[0]);
        }
        final Color[] _8 = this.iGb._("InfoLine");
        if (_8 != null) {
            this.jGb.aFb.setColor(_8[0]);
        }
        final Color[] _9 = this.iGb._("InfoLineBg");
        this.jGb.aFb.e(_9 != null);
        if (_9 != null) {
            this.jGb.aFb.b(_9[0]);
        }
        final Color[] _10 = this.iGb._("InfoLineFrame");
        this.jGb.aFb.d(_10 != null);
        if (_10 != null) {
            this.jGb.aFb.a(_10[0]);
        }
        final Color[] _11 = this.iGb._("Axis");
        if (_11 != null) {
            this.jGb.o.setColor(_11[0]);
        }
        final Color[] _12 = this.iGb._("AxisLabel");
        if (_12 != null) {
            this.jGb.o.x.setColor(_12[0]);
        }
        final Color[] _13 = this.iGb._("AxisLabelBg");
        this.jGb.o.x.e(_13 != null);
        if (_13 != null) {
            this.jGb.o.x.b(_13[0]);
        }
        final Color[] _14 = this.iGb._("AxisLabelFrame");
        this.jGb.o.x.d(_14 != null);
        if (_14 != null) {
            this.jGb.o.x.a(_14[0]);
        }
        for (int i = 0; i < this.jGb.H(); ++i) {
            final Color[] _15 = this.iGb._("ChartTitle");
            if (_15 != null) {
                this.jGb._(i).setColor(_15[0]);
            }
            final Color[] _16 = this.iGb._("ChartTitleBg");
            this.jGb._(i).e(_16 != null);
            if (_16 != null) {
                this.jGb._(i).b(_16[0]);
            }
            final Color[] _17 = this.iGb._("ChartTitleFrame");
            this.jGb._(i).d(_17 != null);
            if (_17 != null) {
                this.jGb._(i).a(_17[0]);
            }
            final Color[] _18 = this.iGb._("Axis");
            if (_18 != null) {
                this.jGb.tEb[i].setColor(_18[0]);
                this.jGb.uEb[i].setColor(_18[0]);
            }
            final Color[] _19 = this.iGb._("AxisLabel");
            if (_19 != null) {
                this.jGb.tEb[i].x.setColor(_19[0]);
                this.jGb.uEb[i].x.setColor(_19[0]);
            }
            final Color[] _20 = this.iGb._("AxisLabelBg");
            this.jGb.tEb[i].x.e(_20 != null);
            this.jGb.uEb[i].x.e(_20 != null);
            if (_20 != null) {
                this.jGb.tEb[i].x.b(_20[0]);
                this.jGb.uEb[i].x.b(_20[0]);
            }
            final Color[] _21 = this.iGb._("AxisLabelFrame");
            this.jGb.tEb[i].x.d(_21 != null);
            this.jGb.uEb[i].x.d(_21 != null);
            if (_21 != null) {
                this.jGb.tEb[i].x.a(_21[0]);
                this.jGb.uEb[i].x.a(_21[0]);
            }
        }
    }
    
    public void _a() {
        final Color[] _ = this.iGb._("Crosshair");
        if (_ != null) {
            this.jGb.j(_[0]);
        }
        final Color[] _2 = this.iGb._("Grid");
        if (_2 != null) {
            this.jGb.g(_2[0]);
        }
    }
    
    public void V() {
        this.jGb.A(true);
    }
    
    public void W() {
        this.jGb.A(false);
    }
    
    public rp a() {
        return this.jGb;
    }
    
    public synchronized void a() {
        this.jGb.ca();
    }
    
    private void s() {
        this.lGb = -1.0;
        this.mGb = -1.0;
        this.nGb = -1.0;
        this.oGb = -1.0;
    }
    
    public synchronized void b() {
        this.dGb = false;
        final double[] h = this.iGb._().h();
        final double[] i = this.iGb._().i();
        final double[] j = this.iGb._().j();
        final double[] k = this.iGb._().k();
        final double[] l = this.iGb._().l();
        double[] m = this.iGb._().m();
        double[] n = this.iGb._().n();
        if (h == null || i == null || j == null || k == null || l == null || m == null || n == null || !this.iGb._().h()) {
            for (int n2 = 0; n2 < this.jGb.i(); ++n2) {
                this.jGb.U(n2);
                this.jGb.b(n2, "");
            }
            if (!this.iGb._().h() || this.iGb._().i() == null || this.iGb._().i().length() == 0) {
                this.jGb.b(0, this.iGb._().getMessage());
            }
            else {
                this.jGb.b(0, this.iGb.b()._("msgNoDataAvailable"));
            }
            return;
        }
        if (this.iGb._().n() && (this.pGb == null || this.pGb != this.iGb._().i())) {
            this.s();
        }
        this.pGb = this.iGb._().i();
        boolean b = true;
        for (int n3 = 0; n3 < n.length; ++n3) {
            if (m[n3] > 0.0) {
                b = false;
            }
        }
        if (b) {
            m = null;
        }
        boolean b2 = true;
        for (int n4 = 0; n4 < n.length; ++n4) {
            if (n[n4] > 0.0) {
                b2 = false;
            }
        }
        if (b2) {
            n = null;
        }
        this.sGb = (this.hGb == 1 || (this.hGb == 0 && (m != null || n != null)));
        double[] c = null;
        double[] ha = null;
        double[] ia = null;
        double[] o = null;
        double[] p = null;
        if (this.iGb._().h() && this.iGb._().k() != null && this.iGb._().k().length() > 0) {
            c = this.iGb._().c();
            ha = this.iGb._().ha();
            ia = this.iGb._().ia();
            o = this.iGb._().o();
            p = this.iGb._().p();
        }
        synchronized (this.jGb) {
            this.V();
            this.jGb.ea();
            int n5 = 1;
            if (this.sGb) {
                ++n5;
            }
            if (this.iGb.b() != null) {
                ++n5;
            }
            if (this.iGb._() != null) {
                ++n5;
            }
            if (n5 != this.jGb.i()) {
                this.jGb.T(n5);
            }
            if (this.iGb._().n()) {
                this.jGb.o.d(2);
            }
            else {
                this.jGb.o.d(1);
            }
            this.jGb.U(0);
            if (this.jGb.i() > 0) {
                this.jGb.uEb[0].g(this.iGb._().I());
                this.jGb.tEb[0].g(this.iGb._().I());
            }
            final String[] k2 = this.iGb._().k();
            for (int n6 = 0; n6 < k2.length; ++n6) {
                final double[][] b3 = this.iGb._().b(k2[n6]);
                if (b3 != null) {
                    final int[] b4 = this.iGb.b("ExtraPrice");
                    int n7 = 0;
                    if (b4 != null && b4.length > 0) {
                        n7 = b4[n6 % b4.length];
                    }
                    final hq hq = new hq(b3[0].length, n7);
                    hq.setName(k2[n6]);
                    final Color[] _ = this.iGb._("ExtraPrice");
                    if (_ != null && _.length > 0) {
                        hq.setColor(_[n6 % _.length]);
                    }
                    else {
                        hq.setColor(Color.red);
                    }
                    for (int n8 = 0; n8 < b3[0].length; ++n8) {
                        hq._(b3[0][n8], b3[1][n8]);
                    }
                    this.jGb.a(0, hq);
                }
            }
            o[] b5 = null;
            if (!this.eGb) {
                b5 = this.iGb.b();
                if (b5 != null) {
                    for (int n9 = 0; n9 < b5.length; ++n9) {
                        if (!(b5[n9] instanceof Ro)) {
                            this._(0, h, b5[n9]);
                        }
                    }
                }
            }
            if (this.kGb == 1) {
                final wq wq = new wq(h.length);
                final Color[] _2 = this.iGb._("Price");
                if (_2 != null) {
                    wq.setColor(_2[0]);
                }
                else {
                    wq.setColor(Color.yellow);
                }
                wq.setName(this.iGb._().i());
                wq.m(this.iGb.b()._("strDate"));
                wq.a(0, "O");
                wq.a(1, "H");
                wq.a(2, "L");
                wq.a(3, "C");
                for (int n10 = 0; n10 < h.length; ++n10) {
                    wq.a(h[n10], i[n10], j[n10], k[n10], l[n10]);
                }
                this.jGb.a(0, wq);
            }
            else if (this.kGb == 2) {
                final xq xq = new xq(h.length);
                final Color[] _3 = this.iGb._("Price");
                if (_3 != null) {
                    xq.setColor(_3[0]);
                }
                else {
                    xq.setColor(Color.yellow);
                }
                xq.setName(this.iGb._().i());
                xq.m(this.iGb.b()._("strDate"));
                xq.a(0, "O");
                xq.a(1, "H");
                xq.a(2, "L");
                xq.a(3, "C");
                for (int n11 = 0; n11 < h.length; ++n11) {
                    xq.a(h[n11], i[n11], j[n11], k[n11], l[n11]);
                }
                this.jGb.a(0, xq);
            }
            else {
                final int[] b6 = this.iGb.b("Price");
                int n12 = 0;
                if (b6 != null) {
                    n12 = b6[0];
                }
                final hq hq2 = new hq(h.length, n12);
                final Color[] _4 = this.iGb._("Price");
                if (_4 != null) {
                    hq2.setColor(_4[0]);
                }
                else {
                    hq2.setColor(Color.yellow);
                }
                hq2.setName(this.iGb._().i());
                hq2.m(this.iGb.b()._("strDate"));
                hq2.a(0, this.iGb.b()._("strPrice"));
                for (int n13 = 0; n13 < h.length; ++n13) {
                    hq2._(h[n13], l[n13]);
                }
                this.jGb.a(0, hq2);
            }
            if (this.iGb._().h() && this.iGb._().k() != null && this.iGb._().k().length() > 0 && c != null && ha != null && ia != null && o != null && p != null) {
                if (this.kGb == 1) {
                    final wq wq2 = new wq(c.length);
                    final Color[] _5 = this.iGb._("BgPrice");
                    if (_5 != null) {
                        wq2.setColor(_5[0]);
                    }
                    else {
                        wq2.setColor(Color.lightGray);
                    }
                    wq2.setName(this.iGb._().k());
                    wq2.m(this.iGb.b()._("strDate"));
                    wq2.a(0, "O");
                    wq2.a(1, "H");
                    wq2.a(2, "L");
                    wq2.a(3, "C");
                    for (int n14 = 0; n14 < c.length; ++n14) {
                        wq2.a(c[n14], ha[n14], ia[n14], o[n14], p[n14]);
                    }
                    this.jGb.b(0, 0, wq2);
                }
                else if (this.kGb == 2) {
                    final xq xq2 = new xq(c.length);
                    final Color[] _6 = this.iGb._("BgPrice");
                    if (_6 != null) {
                        xq2.setColor(_6[0]);
                    }
                    else {
                        xq2.setColor(Color.gray);
                    }
                    xq2.setName(this.iGb._().k());
                    xq2.m(this.iGb.b()._("strDate"));
                    xq2.a(0, "O");
                    xq2.a(1, "H");
                    xq2.a(2, "L");
                    xq2.a(3, "C");
                    for (int n15 = 0; n15 < c.length; ++n15) {
                        xq2.a(c[n15], ha[n15], ia[n15], o[n15], p[n15]);
                    }
                    this.jGb.b(0, 0, xq2);
                }
                else {
                    final int[] b7 = this.iGb.b("BgPrice");
                    int n16 = 0;
                    if (b7 != null) {
                        n16 = b7[0];
                    }
                    final hq hq3 = new hq(c.length, n16);
                    final Color[] _7 = this.iGb._("BgPrice");
                    if (_7 != null) {
                        hq3.setColor(_7[0]);
                    }
                    else {
                        hq3.setColor(Color.gray);
                    }
                    hq3.setName(this.iGb._().k());
                    hq3.m(this.iGb.b()._("strDate"));
                    hq3.a(0, this.iGb.b()._("strPrice"));
                    for (int n17 = 0; n17 < c.length; ++n17) {
                        hq3._(c[n17], p[n17]);
                    }
                    this.jGb.b(0, 0, hq3);
                }
            }
            if (this.jGb.i() > 1 && this.sGb) {
                this.jGb.U(1);
                if (m != null) {
                    final yq yq = new yq(h.length);
                    final Color[] _8 = this.iGb._("Volume");
                    if (_8 != null) {
                        yq.setColor(_8[0]);
                    }
                    else {
                        yq.setColor(Color.green);
                    }
                    yq.a(0, "V");
                    this.jGb.tEb[1].c(0);
                    for (int n18 = 0; n18 < h.length; ++n18) {
                        yq._(h[n18], m[n18]);
                    }
                    this.jGb.a(1, yq);
                    if (b5 != null) {
                        for (int n19 = 0; n19 < b5.length; ++n19) {
                            if (b5[n19] instanceof Ro) {
                                this._(1, h, b5[n19]);
                            }
                        }
                    }
                }
                if (n != null) {
                    final int[] b8 = this.iGb.b("OpenInterest");
                    int n20 = 0;
                    if (b8 != null) {
                        n20 = b8[0];
                    }
                    final hq hq4 = new hq(h.length, n20);
                    final Color[] _9 = this.iGb._("OpenInterest");
                    if (_9 != null) {
                        hq4.setColor(_9[0]);
                    }
                    else {
                        hq4.setColor(Color.blue);
                    }
                    hq4.a(0, "OI");
                    for (int n21 = 0; n21 < h.length; ++n21) {
                        hq4._(h[n21], n[n21]);
                    }
                    if (m != null) {
                        this.jGb.b(1, 0, hq4);
                    }
                    else {
                        this.jGb.tEb[1].k(true);
                        this.jGb.tEb[1].c(2);
                        this.jGb.b(1, 1, hq4);
                    }
                }
                if (this.jGb.a(1) == 0) {
                    final yq yq2 = new yq(h.length);
                    final Color[] _10 = this.iGb._("Volume");
                    if (_10 != null) {
                        yq2.setColor(_10[0]);
                    }
                    else {
                        yq2.setColor(Color.green);
                    }
                    this.jGb.tEb[1].c(0);
                    for (int n22 = 0; n22 < h.length; ++n22) {
                        yq2._(h[n22], 0.0);
                    }
                    this.jGb.a(1, yq2);
                }
            }
            final o b9 = this.iGb.b();
            final o _11 = this.iGb._();
            int n23;
            int n24;
            if (this.sGb) {
                n23 = 2;
                n24 = 3;
            }
            else {
                n23 = 1;
                n24 = 2;
            }
            if (this.jGb.i() > n23) {
                this.jGb.U(n23);
                if (b9 != null) {
                    this.jGb.tEb[n23].k(true);
                    this.jGb.tEb[n23].c(2);
                    this._(n23, h, b9);
                }
                else if (_11 != null) {
                    this.jGb.tEb[n23].k(true);
                    this.jGb.tEb[n23].c(2);
                    this._(n23, h, _11);
                }
            }
            if (this.jGb.i() > n24) {
                this.jGb.U(n24);
                if (b9 != null && _11 != null) {
                    this.jGb.tEb[n24].k(true);
                    this.jGb.tEb[n24].c(2);
                    this._(n24, h, _11);
                }
            }
            this.jGb.b(0, "" + this.iGb._().i());
            if (this.jGb.i() > 1 && this.sGb) {
                if (n != null && m != null) {
                    this.jGb.b(1, this.iGb.b()._("strOpenInterest") + ", " + this.iGb.b()._("strVolume"));
                }
                else if (m != null) {
                    this.jGb.b(1, this.iGb.b()._("strVolume"));
                }
                else if (n != null) {
                    this.jGb.b(1, this.iGb.b()._("strOpenInterest"));
                }
                else {
                    this.jGb.b(1, "");
                }
            }
            if (this.jGb.i() > n23) {
                if (b9 != null) {
                    this.jGb.b(n23, b9.l());
                }
                else if (_11 != null) {
                    this.jGb.b(n23, _11.l());
                }
            }
            if (this.jGb.i() > n24 && b9 != null && _11 != null) {
                this.jGb.b(n24, _11.l());
            }
            this.t();
            if (this.jGb.i() > 1 && this.sGb && this.jGb.a(1) > 0) {
                final fq a = this.jGb.a(1, 0);
                if (a != null && a instanceof yq) {
                    this.jGb.tEb[1].k(false);
                    this.jGb.tEb[1]._(0.0, a.a());
                }
            }
            final Color[] _12 = this.iGb._("TrendLine");
            if (_12 != null) {
                this.jGb.i(_12[0]);
            }
            final int[] b10 = this.iGb.b("TrendLine");
            if (b10 != null) {
                this.jGb.X(b10[0]);
            }
            if (this.eGb) {
                this.u();
            }
            this.jGb.fa();
            this.W();
        }
    }
    
    private void u() {
        for (int i = 0; i < this.jGb.a(0); ++i) {
            this._(this.jGb.a(0, i));
        }
        for (int j = 0; j < this.jGb.a(0, 0); ++j) {
            this._(this.jGb._(0, 0, j));
        }
    }
    
    private void _(final fq fq) {
        if (fq == null) {
            return;
        }
        final int b = fq.b();
        if (b > fq._()) {
            return;
        }
        fq.P();
        final double a = fq.a(b, fq.g() - 1);
        if (a != 0.0) {
            for (int i = 0; i < fq.g(); ++i) {
                for (int j = 0; j <= fq.f(); ++j) {
                    fq.b(j, i, 100.0 * (fq.a(j, i) - a) / a);
                }
            }
        }
    }
    
    public static int a(final double[] array, final double n) {
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
    
    public synchronized void O(final int n) {
        fq a = null;
        if (this.jGb.i() > 0 && this.jGb.a(0) > 0) {
            a = this.jGb.a(0, this.jGb.a(0) - 1);
        }
        if (a == null) {
            return;
        }
        synchronized (this.jGb) {
            this.V();
            this.jGb.ea();
            final double[] f = this.jGb.f();
            if (f == null) {
                return;
            }
            final double[] g = this.jGb.g();
            if (g == null) {
                return;
            }
            final int n2 = 0;
            final int n3 = f.length - 1;
            int n4 = a(f, g[0]);
            if (n4 < 0) {
                n4 = Math.max(-n4 - 1, 0);
            }
            int n5 = a(f, g[1]);
            if (n5 < 0) {
                n5 = Math.min(-n5 - 1, f.length - 1);
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
            final double n8 = f[n4];
            final double n9 = f[n5];
            this.D(n5 >= n3);
            this.jGb.n(n8, n9);
            if (this.jGb.i() > 1 && this.sGb && this.jGb.a(1) > 0) {
                final fq a2 = this.jGb.a(1, 0);
                if (a2 != null && a2 instanceof yq) {
                    this.jGb.tEb[1].k(false);
                    this.jGb.tEb[1]._(0.0, a2.a());
                }
            }
            if (this.iGb._().n()) {
                if (this.nGb == n8 && this.oGb == n9) {
                    Toolkit.getDefaultToolkit().beep();
                }
                this.nGb = n8;
                this.oGb = n9;
            }
            else {
                if (this.lGb == n8 && this.mGb == n9) {
                    Toolkit.getDefaultToolkit().beep();
                }
                this.lGb = n8;
                this.mGb = n9;
            }
            if (this.eGb) {
                this.dGb = true;
                this.jGb.N(0);
            }
            this.jGb.fa();
            this.W();
        }
    }
    
    private void t() {
        double n;
        double n2;
        if (this.iGb._().n()) {
            n = this.nGb;
            n2 = this.oGb;
        }
        else {
            n = this.lGb;
            n2 = this.mGb;
        }
        fq a = null;
        if (this.jGb.i() > 0 && this.jGb.a(0) > 0) {
            a = this.jGb.a(0, this.jGb.a(0) - 1);
        }
        if (a == null) {
            return;
        }
        final double[] f = this.jGb.f();
        if (f == null) {
            return;
        }
        final int n3 = 0;
        final int n4 = f.length - 1;
        final double n5 = f[n3];
        final double n6 = f[n4];
        double n7;
        if (this.iGb._().n()) {
            n7 = n5;
        }
        else {
            n7 = n6 - 160.0;
        }
        if (n7 < n5) {
            n7 = n5;
        }
        if (n == -1.0 || n2 == -1.0 || n > n6 || n2 < n5) {
            n = n7;
            n2 = n6;
        }
        else if (this.i() && n2 < n6) {
            n2 = n6;
        }
        this.jGb.B(true);
        this.jGb.n(n, n2);
        if (this.jGb.g() == null) {
            final double e = a.e();
            final double f2 = a.f();
            this.jGb.B(true);
            this.jGb.n(e, f2);
        }
        if (this.jGb.i() > 1 && this.sGb && this.jGb.a(1) > 0) {
            final fq a2 = this.jGb.a(1, 0);
            if (a2 != null && a2 instanceof yq) {
                this.jGb.tEb[1].k(false);
                this.jGb.tEb[1]._(0.0, a2.a());
            }
        }
    }
    
    public void repaint() {
        if (this.dGb) {
            this.b();
        }
        this.W();
        this.jGb.da();
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
    
    private void _(final int n, final double[] array, final o o) {
        boolean b = false;
        for (int i = o.J() - 1; i >= 0; --i) {
            final int[] b2 = this.iGb.b("Indicator");
            int n2 = 0;
            if (b2 != null && i < b2.length) {
                n2 = b2[i];
            }
            final int[] b3 = this.iGb.b(o.toString());
            if (b3 != null && i < b3.length) {
                n2 = b3[i];
            }
            fq fq;
            if (o instanceof No) {
                fq = new iq(array.length, n2);
            }
            else if (i == 2 && o instanceof extends) {
                fq = new yq(array.length);
            }
            else {
                fq = new hq(array.length, n2);
            }
            final Color[] _ = this.iGb._("Indicator");
            if (_ != null) {
                b = true;
                if (i < _.length) {
                    fq.setColor(_[i]);
                }
                else {
                    fq.setColor(_[0]);
                }
            }
            else {
                fq.setColor(Color.lightGray);
            }
            final Color[] _2 = this.iGb._("IndicatorSpecificLine");
            if (_2 != null) {
                fq._(_2[0]);
            }
            final Color[] _3 = this.iGb._(o.toString());
            if (_3 != null) {
                if (i < _3.length) {
                    fq.setColor(_3[i]);
                }
                else {
                    fq.setColor(_3[0]);
                }
            }
            else if (!b) {
                fq.setColor(Color.lightGray);
            }
            if (i == 0) {
                final String[] l = this.iGb.l();
                boolean b4 = true;
                for (int j = 0; j < l.length; ++j) {
                    if (l[j].equals(o.toString())) {
                        b4 = false;
                    }
                }
                if (b4) {
                    fq.a(0, o.toString());
                }
            }
            fq.setName(o.toString());
            fq.m(this.iGb.b()._("strDate"));
            final double[] _4 = o._(i);
            final double[] array2 = { 0.0 };
            if (_4 != null) {
                for (int n3 = 0; n3 < array.length && n3 < _4.length; ++n3) {
                    array2[0] = _4[n3];
                    fq.a(array[n3], array2);
                }
                fq.l(o._(i));
                this.jGb.a(n, fq);
                if (i == 0 && n > 0) {
                    final byte[] _5 = o._();
                    if (_5 != null) {
                        final Color[] _6 = this.iGb._("BuySignal");
                        Color darker;
                        if (_6 != null && _6.length > 0) {
                            darker = _6[0];
                        }
                        else {
                            darker = Color.green.darker();
                        }
                        final Color[] _7 = this.iGb._("SellSignal");
                        Color red;
                        if (_7 != null && _7.length > 0) {
                            red = _7[0];
                        }
                        else {
                            red = Color.red;
                        }
                        final kq kq = new kq(array.length, darker, red);
                        for (int n4 = 0; n4 < array.length && n4 < _4.length && n4 < _5.length; ++n4) {
                            kq.a(array[n4], _4[n4], _5[n4]);
                        }
                        kq.l(0);
                        this.jGb.a(n, kq);
                    }
                }
            }
            if (o.K() > 0) {
                final double[] array3 = new double[o.K()];
                for (int k = 0; k < array3.length; ++k) {
                    array3[k] = o.f(k);
                }
                fq.a(array3);
            }
        }
    }
    
    private boolean i() {
        if (this.iGb._().n()) {
            return this.fGb;
        }
        return this.gGb;
    }
    
    private void D(final boolean b) {
        if (this.iGb._().n()) {
            this.fGb = b;
        }
        else {
            this.gGb = b;
        }
    }
    
    public void Q(final int hGb) {
        this.hGb = hGb;
    }
}
