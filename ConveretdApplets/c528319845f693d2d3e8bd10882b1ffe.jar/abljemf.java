import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.Enumeration;
import ABLjemsty.EmuRange;
import java.awt.AWTEvent;
import ABLjemsty.StyleEventArg;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import ABLwidgets.utils;
import ABLwidgets.menu_item;
import java.awt.Window;
import java.awt.FlowLayout;
import ABLjemsty.EmuButton;
import java.awt.Point;
import ABLwidgets.new_font;
import ABLjemsty.Styler;
import ABLwidgets.font_metrics;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import ABLjemsty.EmuPanel;
import ABLjemsty.EmuRect;
import java.awt.Rectangle;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.awt.List;
import ABLwidgets.edge;
import java.awt.Event;
import java.awt.Component;
import ABLjemsty.jemScrollPanel;
import ABLjemsty.jemTabPanel;
import ABLjemsty.EmuRangeVector;
import java.util.Vector;
import java.awt.Insets;
import ABLjemsty.CommandStatusPanel;
import ABLjemsty.jemQuadrantPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.MenuItem;
import ABLwidgets.backgroundable;
import ABLjemsty.EmuTarget;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class abljemf extends Frame implements Runnable, EmuTarget, backgroundable
{
    public abljemtf a;
    public abljempu b;
    public int c;
    public abljempu d;
    public char[] e;
    public String[] f;
    public String[] g;
    public MenuItem[] h;
    abljema i;
    abljem j;
    Object k;
    boolean l;
    String m;
    Font n;
    Color o;
    int p;
    int q;
    int r;
    int s;
    int t;
    int u;
    String v;
    int w;
    int x;
    Font y;
    boolean z;
    int aa;
    int ab;
    int ac;
    boolean ad;
    boolean ae;
    int af;
    int[] ag;
    String[] ah;
    Dimension[] ai;
    Dimension[] aj;
    Dimension[] ak;
    int al;
    int am;
    Font an;
    Font ao;
    jemQuadrantPanel ap;
    CommandStatusPanel aq;
    Insets ar;
    int as;
    int at;
    int au;
    int av;
    int aw;
    int ax;
    int ay;
    int az;
    boolean a0;
    int a1;
    int a2;
    int a3;
    int a4;
    int a5;
    int a6;
    int a7;
    int a8;
    boolean a9;
    int ba;
    int bb;
    int bc;
    int bd;
    int be;
    int bf;
    int bg;
    int bh;
    int bi;
    String bj;
    String bk;
    boolean bl;
    boolean bm;
    char bn;
    Vector bo;
    Vector bp;
    int bq;
    String br;
    boolean bs;
    boolean bt;
    Color bu;
    Color bv;
    Color bw;
    Color bx;
    Color by;
    Color bz;
    Color b0;
    Color[] b1;
    Color[] b2;
    Color[] b3;
    Color b4;
    Color b5;
    Color[] b6;
    Color[] b7;
    Color[] b8;
    boolean[] b9;
    boolean ca;
    boolean cb;
    boolean cc;
    String cd;
    EmuRangeVector ce;
    boolean cf;
    Color cg;
    Color ch;
    Color ci;
    Color cj;
    char ck;
    Color cl;
    Color cm;
    Color cn;
    Color co;
    char cp;
    jemTabPanel cq;
    jemTabPanel cr;
    jemTabPanel cs;
    jemTabPanel ct;
    jemTabPanel cu;
    abljempu cv;
    jemTabPanel cw;
    jemTabPanel cx;
    String cy;
    jemScrollPanel[] cz;
    boolean c0;
    int c1;
    Component c2;
    Component c3;
    boolean c4;
    Event c5;
    Event c6;
    abljembx c7;
    jemTabPanel c8;
    edge c9;
    Color da;
    Insets db;
    Color dc;
    int dd;
    edge de;
    jemQuadrantPanel[] df;
    jemQuadrantPanel dg;
    jemQuadrantPanel dh;
    jemQuadrantPanel di;
    int dj;
    int dk;
    int dl;
    int dm;
    boolean dn;
    boolean do;
    int dp;
    int dq;
    int dr;
    int ds;
    int dt;
    int du;
    int dv;
    int dw;
    int dx;
    int dy;
    int dz;
    int d0;
    abljemtc d1;
    Dimension d2;
    int d3;
    int d4;
    Insets d5;
    boolean d6;
    boolean d7;
    boolean d8;
    abljemtf d9;
    boolean ea;
    abljemob eb;
    boolean ec;
    boolean ed;
    boolean ee;
    boolean ef;
    boolean eg;
    boolean eh;
    boolean ei;
    int ej;
    int ek;
    int el;
    int em;
    int en;
    int eo;
    boolean ep;
    byte[] eq;
    boolean er;
    abljemtf es;
    int et;
    int eu;
    int ev;
    boolean ew;
    boolean ex;
    boolean ey;
    abljemml ez;
    abljemta e0;
    char e1;
    Event e2;
    Component e3;
    abljemd e4;
    Dimension e5;
    abljemd e6;
    boolean e7;
    private boolean e8;
    private boolean e9;
    private int fa;
    private int fb;
    private Component fc;
    private Component fd;
    private abljemfb fe;
    private abljempu ff;
    private abljemd fg;
    private List fh;
    private List fi;
    private Panel fj;
    private boolean fk;
    private abljemgt fl;
    private Button fm;
    private Button fn;
    private Button fo;
    private Label fp;
    private MenuItem fq;
    private Rectangle fr;
    private boolean fs;
    private boolean ft;
    private abljemd fu;
    private Button fv;
    private abljemd fw;
    private Button fx;
    private Button fy;
    private abljemd fz;
    private Button f0;
    private Button f1;
    private abljemgt f2;
    private Label f3;
    private String f4;
    private byte[] f5;
    private abljemp f6;
    private abljempu f7;
    private Button f8;
    private Button f9;
    private Button ga;
    private Button gb;
    private Button gc;
    private Button gd;
    private Button ge;
    private Button gf;
    private Button gg;
    private Button gh;
    private Button gi;
    private MenuItem gj;
    private MenuItem gk;
    private MenuItem gl;
    private MenuItem gm;
    private abljempu gn;
    private int go;
    private int gp;
    private int[] gq;
    private int[] gr;
    private int[] gs;
    private abljemtf[] gt;
    private EmuRect[] gu;
    private EmuPanel[] gv;
    private char[] gw;
    private int[] gx;
    private int[] gy;
    private int gz;
    private int g0;
    private int g1;
    private MenuItem g2;
    private MenuItem g3;
    private MenuItem g4;
    private MenuItem g5;
    private MenuItem g6;
    private MenuItem g7;
    private MenuItem g8;
    private MenuItem g9;
    private MenuItem ha;
    private MenuItem hb;
    private MenuItem hc;
    private MenuItem hd;
    private MenuItem he;
    private MenuItem hf;
    private MenuItem hg;
    private MenuItem hh;
    private abljempu hi;
    private boolean hj;
    private MenuItem hk;
    private MenuItem hl;
    private boolean hm;
    private boolean hn;
    private int ho;
    private int hp;
    private int hq;
    private int[] hr;
    private Color[] hs;
    private Rectangle[] ht;
    private Rectangle[] hu;
    private Rectangle[] hv;
    private int hw;
    private int hx;
    private int hy;
    private int[] hz;
    private int[] h0;
    private int[] h1;
    private String[] h2;
    private int h3;
    private int h4;
    private int h5;
    private int h6;
    private int h7;
    private int h8;
    private int h9;
    private Image ia;
    private Graphics ib;
    private boolean ic;
    private boolean id;
    private boolean ie;
    private String if;
    
    public abljemf(final String m, final abljema i) {
        super(m);
        this.k = new Object();
        this.l = false;
        this.r = 10;
        this.s = 60;
        this.w = 24;
        this.x = 80;
        this.z = true;
        this.ad = false;
        this.ae = false;
        this.a0 = false;
        this.bk = "";
        this.bl = false;
        this.bm = false;
        this.bn = '0';
        this.br = "Underline";
        this.bs = true;
        this.bt = false;
        this.ca = true;
        this.cb = false;
        this.cf = false;
        this.c0 = false;
        this.c4 = false;
        this.c5 = new Event(null, 0L, 401, 0, 0, 127, 0);
        this.c6 = new Event(null, 0L, 401, 0, 0, 127, 2);
        this.dl = 4;
        this.dm = 23;
        this.dn = false;
        this.do = false;
        this.dp = 4;
        this.dq = 22;
        this.dr = 22;
        this.ds = 8;
        this.dt = 8;
        this.du = -1;
        this.d6 = false;
        this.d7 = false;
        this.d8 = false;
        this.ea = true;
        this.ec = false;
        this.ed = false;
        this.ee = false;
        this.ef = false;
        this.eg = false;
        this.eh = false;
        this.ei = false;
        this.ep = true;
        this.er = false;
        this.ew = false;
        this.ex = false;
        this.ey = false;
        this.e1 = ' ';
        this.e7 = false;
        this.e8 = false;
        this.e9 = false;
        this.fk = false;
        this.go = -1;
        this.gp = -1;
        this.gz = 100;
        this.hj = false;
        this.hm = false;
        this.hn = false;
        this.hz = new int[200];
        this.h0 = new int[200];
        this.h1 = new int[200];
        this.h2 = new String[200];
        this.ic = false;
        this.id = false;
        this.ie = false;
        this.if = "\"'";
        if (abljemeh.a()) {
            this.enableEvents(abljemeh.b());
        }
        this.i = i;
        this.m = m;
        if (this.i.i == 0) {
            this.e0 = new abljemta(this.i);
        }
        else {
            if (this.i.j.q == null) {
                this.i.j.q = new abljemta(this.i);
            }
            this.e0 = this.i.j.q;
        }
        this.ez = this.e0.c;
        this.setLayout(null);
        this.hide();
        if (this.i.i > 0 && this.i.j.hv) {
            this.setResizable(false);
        }
        if (this.ea) {
            this.setCursor(3);
        }
    }
    
    public void a(final abljem j) {
        this.j = j;
        if (j.a("early_fraaddnfy", true)) {
            this.addNotify();
        }
        this.do = j.a("emuimg_minus1", this.do);
        this.n = this.i.j.g8.l.getFont();
        this.o = this.i.j.g8.getBackground();
        if (this.i.x == null) {
            this.v = j.a("font_name", (String)null);
        }
        else {
            this.v = this.i.x;
        }
        if (this.v == null) {
            this.v = "Courier";
        }
        if (this.i.i == 0 && j.a("list_fixed_fonts", false)) {
            final String[] fontList = this.getToolkit().getFontList();
            final int length = fontList.length;
            abljem.d("Available fixed-width Java font names:");
            for (int i = 0; i < length; ++i) {
                final font_metrics a = font_metrics.a(this.getFontMetrics(new Font(fontList[i], 0, 20)), this.i.fd);
                if (a.a('i') == a.a('W')) {
                    abljem.d("    " + fontList[i]);
                }
            }
            if (this.v != null) {
                abljem.d("Using font name: " + this.v);
            }
        }
        this.bq = 0;
        if (this.i.i == 0) {
            this.b(j.a("gui_type", '0'));
            this.bt = (j.a("gui_underline_type", '0') != '0');
        }
        this.z();
        this.eq = new byte[Styler.de];
        for (int k = 0; k < this.eq.length; ++k) {
            this.eq[k] = 95;
        }
        this.d5 = new Insets(this.dm, this.dl, this.dm - 2, this.dl);
        this.az();
        int d3;
        int n;
        if (this.p > 640) {
            this.a4 = 13;
            this.a3 = 11;
            d3 = 678;
            n = 520;
            if (this.i.by) {
                this.i.bz = -3;
            }
        }
        else {
            this.a4 = 12;
            this.a3 = 10;
            d3 = 582;
            n = 440;
            if (this.i.by) {
                this.i.bz = -2;
            }
        }
        this.d4 = n - 50;
        this.d3 = d3;
        final int dl = this.dl;
        final int dm = this.dm;
        if (this.i.b0) {
            this.dv = dl;
            this.dw = dm;
        }
        else {
            this.dx = dl;
            this.dy = dm;
        }
        this.bz = new Color(85, 85, 85);
        this.bx = new Color(255, 255, 255);
        this.b0 = new Color(85, 85, 85);
        this.by = new Color(255, 255, 255);
        this.a4 = j.a("screen_fontsize", this.a4);
        this.a3 = j.a("input_fontsize", this.a3);
        this.g(this.a4);
        this.b3 = new Color[32];
        this.b2 = new Color[32];
        this.b1 = new Color[32];
        this.b9 = new boolean[32];
        this.b6 = new Color[2];
        this.b7 = new Color[2];
        this.ac();
        this.br = j.a("column_separators", this.br);
        if (this.br.length() > 0) {
            this.bs = false;
            switch (this.br.charAt(0)) {
                case 'U':
                case 'u': {
                    this.bs = true;
                    break;
                }
            }
        }
        this.ca = j.a("bold_highintensity", this.ca);
        this.y = new_font.a(this.v, this.bq, this.a4);
        this.an = new_font.a(this.v, 1, this.a4);
        this.ao = new_font.a(this.v, this.bq, this.a3);
        this.setFont(this.y);
        if (this.getBackground() != this.bx) {
            this.setBackground(this.bx);
        }
        this.hr = new int[100];
        this.hs = new Color[100];
        this.ht = new Rectangle[100];
        this.hu = new Rectangle[100];
        this.hv = new Rectangle[100];
        (this.eb = new abljemob(this.i, 0, null, 0)).hide();
        if (!this.i.cf) {
            this.g2 = new MenuItem("Attn");
            this.g3 = new MenuItem("SysRq");
            this.g4 = new MenuItem("Enter");
            this.g5 = new MenuItem("Field Exit");
            this.g6 = new MenuItem("Host Print");
            this.ha = new MenuItem("Help");
            this.hf = new MenuItem("-");
            this.hb = new MenuItem("Home");
            this.hc = new MenuItem("Newline");
            this.hd = new MenuItem("Text Mode");
            (this.he = new MenuItem("Insert")).disable();
            this.g9 = new MenuItem("Save Settings");
            this.hh = new MenuItem("-");
            this.g7 = new MenuItem("Macro");
            this.hg = new MenuItem("-");
            this.g8 = new MenuItem("Available Files");
            this.au();
            if (this.f7 != null) {
                this.add(this.f7);
                this.e3 = this.f7;
            }
            this.ao();
            this.ap();
            if (this.d != null) {
                this.add(this.d);
            }
        }
        this.f8 = this.r("Attn");
        this.f9 = this.r("SysRq");
        this.ga = this.r("Enter");
        this.gb = this.r("Host Print");
        this.gc = this.r("Available Files");
        this.gd = this.r("Help");
        this.ge = this.r("Home");
        this.gf = this.r("Newline");
        this.gg = this.r("Text Mode");
        (this.gh = this.r("Insert")).hide();
        (this.gi = this.r(" ")).hide();
        final String upperCase = j.a("disable_keys", "").toUpperCase();
        if (upperCase.indexOf("ATN") >= 0) {
            this.ew = true;
            this.f8.disable();
            if (this.f7 != null) {
                this.g2.disable();
            }
        }
        if (upperCase.indexOf("SRQ") >= 0) {
            this.ex = true;
            this.f9.disable();
            if (this.f7 != null) {
                this.g3.disable();
            }
        }
        if (upperCase.indexOf("PRT") >= 0) {
            this.ey = true;
            this.gb.disable();
            if (this.f7 != null) {
                this.g6.disable();
            }
        }
        this.aj();
        this.add(this.hi);
        this.f6 = new abljemp(this.i, this.i.el);
        this.bb();
        this.add(this.f6);
        this.reshape(10, 10, d3, n);
        (this.e6 = new abljemd(this.i, this, "F10", false)).resize(10, 10);
    }
    
    public void a() {
        if (!this.e8) {
            this.df = new jemQuadrantPanel[10];
            this.gq = new int[this.gz];
            this.gr = new int[this.gz];
            this.gs = new int[this.gz];
            this.gt = new abljemtf[this.gz];
            this.gu = new EmuRect[this.gz];
            this.gv = new EmuPanel[this.gz];
            this.gw = new char[this.gz];
            for (int i = 0; i < this.gz; ++i) {
                this.gw[i] = ' ';
            }
            this.gx = new int[this.gz];
            this.gy = new int[this.gz];
            this.e8 = true;
        }
        this.c = 0;
        this.gp = -1;
        this.i.fv = -1;
    }
    
    public void addNotify() {
        super.addNotify();
        final Insets insets = this.insets();
        this.dl = insets.left;
        this.dm = insets.top;
    }
    
    public Graphics getGraphics() {
        Graphics graphics = super.getGraphics();
        if (graphics == null && this.i.j.g8 != null) {
            graphics = this.i.j.g8.getGraphics();
        }
        if (graphics == null) {
            abljem.d("No level " + this.i.i + " or base Graphics");
        }
        return graphics;
    }
    
    private boolean a(final font_metrics font_metrics) {
        return font_metrics.a('i') == font_metrics.a('W');
    }
    
    private void af() {
        this.i.j.be = !this.i.j.be;
    }
    
    private void ag() {
        if (this.bj == null && !this.i.j.be) {
            return;
        }
        synchronized (this.k) {
            final boolean be = this.i.j.be;
            String s = "";
            final String s2 = " @ ";
            final String title = this.getTitle();
            int n = title.indexOf(s2);
            boolean b = false;
            if (be) {
                Point a = null;
                final int n2 = (this.i.e5 - 1) * this.i.es + (this.i.e6 - 1);
                s = String.valueOf(s2) + this.i.e5 + "," + this.i.e6;
                if (this.i.i > 0) {
                    a = this.i.a(this.i.e5, this.i.e6);
                }
                if (a != null) {
                    s = String.valueOf(s) + " = " + a.x + "," + a.y;
                }
                for (int i = this.i.fw - 1; i >= 0; --i) {
                    if (n2 >= this.i.fl[i] && n2 < this.i.fl[i] + this.i.fk[i]) {
                        s = String.valueOf(s) + " #" + Integer.toHexString(this.i.fo[i]).toUpperCase();
                        break;
                    }
                }
                if (!title.endsWith(s)) {
                    b = true;
                }
            }
            else if (n >= 0) {
                b = true;
            }
            if (b) {
                if (n < 0) {
                    n = title.length();
                }
                this.setTitle(this.bj = String.valueOf(title.substring(0, n)) + s);
            }
            if (!be) {
                this.bj = null;
            }
        }
        // monitorexit(this.k)
    }
    
    public void b() {
        if (this.c8 != null) {
            this.c8.b(this.i.j.ag);
        }
    }
    
    public void a(final String s) {
        this.setTitle(String.valueOf(this.m) + s);
    }
    
    public void b(final String bk) {
        this.bk = bk;
        String string = bk;
        final String ah = this.i.j.ah;
        if (ah != null) {
            string = String.valueOf(string) + ah;
        }
        this.setTitle(string);
    }
    
    public void c(final String s) {
        if (this.bl) {
            this.b(s);
        }
        this.bl = false;
    }
    
    public void c() {
        this.b(this.m);
    }
    
    private void b(final int af, final boolean b) {
        this.af = af;
        this.ag = abljema.a((int[])(b ? this.ag : null), af);
        this.ah = abljema.a((String[])(b ? this.ah : null), af);
        this.ak = abljema.a((Dimension[])(b ? this.ak : null), af);
        this.ai = abljema.a((Dimension[])(b ? this.ai : null), af);
        this.aj = abljema.a((Dimension[])(b ? this.aj : null), af);
    }
    
    private void a(final Graphics graphics, final int n, final int n2) {
        synchronized (this.k) {
            this.b(graphics, n, n2);
        }
        // monitorexit(this.k)
    }
    
    private void b(final Graphics graphics, int n, final int n2) {
        final Font y = this.y;
        if (graphics == null) {
            abljem.d("load dfs, with no Graphics context");
            return;
        }
        this.b(41, false);
        this.al = this.w;
        this.am = this.x;
        int n3 = 0;
        if (this.df != null) {
            for (int i = 1; i <= 2; ++i) {
                final jemQuadrantPanel jemQuadrantPanel = this.df[i];
                if (jemQuadrantPanel != null && (i != 2 || jemQuadrantPanel != this.df[1])) {
                    n3 += jemQuadrantPanel.d();
                }
            }
        }
        int j = 0;
        while (j < this.af) {
            if (this.ag[j] == 0) {
                this.ag[j] = ((j == 0) ? ((this.p > 640) ? 13 : 12) : (j + 2));
                this.ah[j] = this.v;
                this.y = new_font.a(this.ah[j], 0, this.ag[j]);
                this.z = this.a(font_metrics.a(this.getFontMetrics(this.y), this.i.fd));
            }
            this.ai[j] = this.a(graphics, j, this.w, this.x);
            if (j > 0 && this.t >= 2 && this.t <= 3 && this.i.i == 1 && n3 > 0 && this.ai[j].width > n3) {
                final int n4 = j - 1;
                if (this.ag[0] > this.ag[n4]) {
                    this.ag[0] = this.ag[n4];
                    this.ah[0] = this.ah[n4];
                    this.ak[0] = this.ak[n4];
                    this.ai[0] = this.ai[n4];
                    this.aj[0] = this.aj[n4];
                }
                this.b(j, true);
                if (this.af == 1) {
                    abljem.d(String.valueOf(this.ah[0]) + " font did not scale down to fit");
                }
                if (this.ab >= this.af) {
                    this.ab = 0;
                }
                if (this.ac >= this.af) {
                    this.ac = 0;
                    break;
                }
                break;
            }
            else {
                if (this.i.i > 0) {
                    this.aj[j] = this.ai[j];
                }
                else {
                    this.aj[j] = ((j == 0) ? this.ai[0] : this.a(graphics, j, 27, 132));
                }
                this.ak[j] = this.ai[j];
                ++j;
            }
        }
        if (n <= 0 && n2 > 0 && n2 != this.ag[0]) {
            for (int k = 1; k < this.af; ++k) {
                if (n2 == this.ag[k]) {
                    this.f(k);
                    break;
                }
            }
        }
        if (n <= 0 || n > this.p - this.r) {
            n = this.p - this.r;
        }
        if (this.ai[0].width > n || this.ai[0].height > this.q - this.s) {
            int n5;
            for (n5 = this.af - 1; n5 > 0 && (this.ai[n5].width >= n || this.ai[n5].height >= this.q - this.s); --n5) {}
            this.f(n5);
        }
        int n6 = this.aa * 3 / 5;
        if (this.aa == 12) {
            n6 = 7;
        }
        if (this.aa >= 13 && this.aa <= 14) {
            n6 = 9;
        }
        if (this.aa >= 15 && this.aa <= 18) {
            n6 = 10;
        }
        if (this.aa >= 19 && this.aa <= 20) {
            n6 = 12;
        }
        this.ab = this.af - 1;
        while (this.ab > 0 && this.ag[this.ab] != this.aa) {
            --this.ab;
        }
        this.ac = this.af - 1;
        while (this.ac > 0 && this.ag[this.ac] != n6) {
            --this.ac;
        }
        if (this.t != 1 && this.i.i == 1) {
            this.y = new_font.a(this.ah[this.ab], 0, this.ag[this.ab]);
        }
        else {
            this.y = y;
        }
        if (this.y != null) {
            this.a(graphics, this.w, this.x, true);
        }
    }
    
    private Dimension a(final Graphics graphics, final int n, final int n2, final boolean b) {
        if (graphics == null) {
            abljem.d("jem error 33");
            return new Dimension(400, 300);
        }
        if (this.ao != null) {
            final font_metrics a = font_metrics.a(graphics.getFontMetrics(this.ao), this.i.fd);
            this.a3 = this.ao.getSize();
            this.a2 = a.g * 6 / 4;
            this.a1 = a.a('w');
        }
        return this.a(graphics, this.y, true, n, n2, b);
    }
    
    private Dimension a(final Graphics graphics, final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.af) {
            return new Dimension(0, 0);
        }
        return this.a(graphics, new_font.a(this.ah[n], 0, this.ag[n]), false, n2, n3, true);
    }
    
    private Dimension a(final Graphics graphics, final Font font, final boolean b, final int n, final int n2, final boolean b2) {
        final font_metrics a = font_metrics.a(graphics.getFontMetrics(font), this.i.fd);
        font_metrics a2;
        if (this.ca) {
            a2 = font_metrics.a(graphics.getFontMetrics(new_font.a(font.getName(), 1, font.getSize())), this.i.fd);
        }
        else {
            a2 = a;
        }
        final boolean a3 = this.a(a);
        final int f = abljema.f(a.g, a2.g);
        final int size = font.getSize();
        int ba = 0;
        if (this.u == 1) {
            if (size > 12) {
                ba = 2;
            }
        }
        else {
            ba = 4;
        }
        if (!a3 && this.ap != null) {
            ba += this.ap.z;
        }
        final int a4 = f + ba;
        char char1 = 'w';
        if (!a3 && this.ap != null && this.ap.v != null && this.ap.v.length() > 0) {
            char1 = this.ap.v.charAt(0);
        }
        final int a5 = a.a(char1);
        final int a6 = a2.a(char1);
        final int f2 = abljema.f(a5, a6);
        final boolean a7 = a5 == a6;
        if (b && !this.a0) {
            this.a4 = size;
            this.a6 = a4;
            this.a7 = f2;
            this.a9 = a7;
            this.ba = ba;
            this.a8 = a.e;
            this.a5 = a.f;
        }
        if (!b2) {
            return null;
        }
        int n3 = 0;
        final int n4 = (this.i.i == 0) ? f2 : (f2 / 2 + a4 / 2);
        final int bb = n4 + this.as + this.ax + ((this.ar == null) ? 0 : this.ar.left);
        int bc = a4 / 2 + this.at + this.ay + ((this.ar == null) ? 0 : this.ar.top);
        if (this.ap != null) {
            bc -= this.ap.t;
        }
        final int bf = a4 / 4;
        int bg = this.az - 2 * f2 - f2 % 2;
        int bh = bg + (f2 + 1) / 2;
        if (bg < 0) {
            bg = 0;
        }
        if (bh < 0) {
            bh = 0;
        }
        final int n5 = bb + f2 * n2 + bh + n4;
        int n6 = bc + a4 * n + a4 / 2;
        if (this.aq == null) {
            n3 = 0;
            n6 += bf + a4;
        }
        final int au = this.au;
        int be = this.av + this.aw;
        if (this.aq != null) {
            n3 = this.aq.n;
            be += n3;
        }
        final int n7 = n5 + ((this.ar == null) ? 0 : this.ar.right) + au;
        int n8 = n6 + ((this.ar == null) ? 0 : this.ar.bottom) + be;
        if (this.aq != null) {
            n8 += a4 / 2;
        }
        if (b) {
            this.bb = bb;
            this.bc = bc;
            this.bd = au;
            this.be = be;
            this.bf = bf;
            this.bg = bg;
            this.bh = bh;
            this.bi = n3;
        }
        return new Dimension(n7, n8);
    }
    
    private void f(final int n) {
        if (n > 0 && n < this.ag.length) {
            this.g(this.ag[n]);
            this.ag[0] = this.ag[n];
            this.ah[0] = this.ah[n];
            this.ai[0] = this.ai[n];
            this.aj[0] = this.aj[n];
        }
    }
    
    private void g(final int aa) {
        this.aa = aa;
        switch (this.i.j.es) {
            case 80: {
                this.fa = this.aa;
                break;
            }
            case 132: {
                this.fb = this.aa;
                break;
            }
        }
    }
    
    public void d() {
        switch (this.i.j.es) {
            case 80: {
                this.aa = this.fa;
                break;
            }
            case 132: {
                this.aa = this.fb;
                break;
            }
        }
    }
    
    public boolean e() {
        if (this.aa == 0) {
            return false;
        }
        switch (this.i.j.es) {
            case 80: {
                return this.aa == this.fa;
            }
            case 132: {
                return this.aa == this.fb;
            }
            default: {
                return false;
            }
        }
    }
    
    private void ah() {
        this.ho = 0;
        this.hp = 0;
        this.hq = 0;
        this.hn = false;
        if (this.i.bu) {
            for (int i = 0; i < this.i.e1; ++i) {
                this.i.ez[i].av = 0;
            }
        }
        for (int j = 0; j < this.i.fw; ++j) {
            this.i.fm[j] = 0;
        }
        for (int k = 0; k < this.i.ga; ++k) {
            this.i.f1[k].hide();
        }
        this.c0 = false;
        final Component[] components = this.getComponents();
        for (int l = 0; l < components.length; ++l) {
            Component component;
            try {
                component = components[l];
            }
            catch (Throwable t) {
                continue;
            }
            if (component instanceof abljemtf) {
                component.hide();
                ((abljemtf)component).ax = 0;
            }
            else if (component instanceof abljemob) {
                component.hide();
                ((abljemob)component).h = 0;
            }
            else if (component instanceof abljemfb) {
                component.hide();
                ((abljemfb)component).h = 0;
            }
            else if (component instanceof abljemcp) {
                component.hide();
                ((abljemcp)component).h = 0;
            }
            else if (component instanceof EmuButton) {
                component.hide();
                ((EmuButton)component).e = 0;
            }
            else if (component instanceof jemTabPanel) {
                component.hide();
                ((jemTabPanel)component).g = 0;
            }
            else if (component instanceof jemScrollPanel) {
                component.hide();
                ((jemScrollPanel)component).g = 0;
            }
            if (component == this.cv) {
                component.hide();
                ((abljempu)component).g = 0;
            }
        }
        this.en = 0;
        if (abljemgt.br != null) {
            abljemgt.br.hide();
        }
        if (this.f7 != null) {
            this.f7.hide();
        }
        if (this.d != null) {
            this.d.hide();
        }
        if (this.b != null) {
            try {
                this.b.hide();
            }
            catch (Throwable t2) {}
        }
    }
    
    public void f() {
        this.d((String)null);
    }
    
    public void d(final String s) {
        synchronized (this.k) {
            this.k(s);
        }
        // monitorexit(this.k)
    }
    
    private void k(final String s) {
        this.a(true);
        if (s == null && !this.i.j.ap) {
            this.requestFocus();
        }
        this.i.ey = 0;
        this.i.e1 = 0;
        this.i.fw = 0;
        this.i.e2 = 0;
        this.i.ga = 0;
        this.i.ep = false;
        this.i.er = false;
        this.ho = 0;
        this.hp = 0;
        this.hq = 0;
        this.hn = false;
        this.hm = false;
        this.d9 = null;
        this.es = null;
        this.er = false;
        this.i.e4 = null;
        this.i.fy = null;
        this.bo = null;
        this.ab();
        this.en = 0;
        this.z();
        this.cy = null;
        if (this.cz != null) {
            for (int i = 0; i < this.cz.length; ++i) {
                this.cz[i].g = 0;
            }
        }
        this.c7 = null;
        this.c0 = false;
        final Component[] components = this.getComponents();
        for (int j = 0; j < components.length; ++j) {
            try {
                final Component component = components[j];
                if (component == this.cv) {
                    this.remove(component);
                    this.cv = null;
                }
                else {
                    if (!(component instanceof Panel) && !(component instanceof abljempu)) {
                        this.remove(component);
                    }
                    if (component instanceof abljempu && ((abljempu)component).k != this) {
                        this.remove(component);
                    }
                    if (component instanceof jemTabPanel && s != null) {
                        ((jemTabPanel)component).a();
                    }
                }
            }
            catch (Throwable t) {}
        }
        this.add(this.i.fx = new abljemcp(this.i));
        abljema.b(this.i);
        this.i.e5 = 1;
        this.i.e6 = 1;
        this.i.fa = false;
        this.eg = false;
        this.ei = false;
        this.b();
        if (s != null) {
            this.l = true;
            this.g();
            this.i.c(s);
            this.repaint();
        }
        if (this.i.co || this.i.cp) {
            System.gc();
        }
    }
    
    public void g() {
        try {
            this.c2.handleEvent(this.c6);
        }
        catch (Throwable t) {}
    }
    
    public void a(final String s, final char e1) {
        synchronized (this.e0.e) {
            if (s == null || s.indexOf(this.e1) >= 0) {
                this.e1 = e1;
            }
        }
        // monitorexit(this.e0.e)
    }
    
    private void ai() {
        (this.e4 = new abljemd(this.i, this, "Functions", false)).setLayout(new FlowLayout());
        this.e4.setBackground(this.o);
        this.e4.setFont(this.n);
        this.e4.add(this.f8);
        this.e4.add(this.f9);
        this.e4.add(this.ga);
        this.e4.add(this.gb);
        if (!this.i.j.hs) {
            this.e4.add(this.gc);
        }
        this.e4.add(this.gd);
        this.e4.add(this.ge);
        this.e4.add(this.gf);
        this.e4.add(this.gg);
        this.e4.add(this.gh);
        this.e4.resize(180, 160);
        this.e4.layout();
    }
    
    private void aj() {
        (this.hi = new abljempu(this.i, this, 0)).setBackground(this.o);
        this.hi.setFont(this.n);
        this.hk = new MenuItem("Copy");
        this.hl = new MenuItem("Cancel");
        this.hi.a(this.hk);
        this.hi.a(this.hl);
    }
    
    public void a(final Component component) {
        if (component != null && component instanceof abljemfb) {
            if (this.i.en != null && abljema.a(this.i.en, 0, "Macro")) {
                this.i("");
            }
            this.fe = (abljemfb)component;
        }
        if (this.e0.h) {
            this.am();
        }
        else {
            this.b(component);
        }
    }
    
    public void b(final Component component) {
        final Font h = this.h();
        this.getFont();
        try {
            if (this.ff == null) {
                (this.ff = new abljempu(this.i, this, 0)).setBackground(this.o);
                this.ff.setFont(this.n);
                this.fq = new MenuItem("Record");
            }
            this.ff.a = ((component instanceof abljemfb) ? null : component);
            this.ff.setFont(h);
            this.ff.a();
            this.ff.a(this.fq);
            this.ez.a(this.ff);
            if (this.fe == null) {
                this.ff.j = 7;
                this.ff.a(this.h6 + this.dv + this.dx, this.h7 + this.dw + this.dy);
            }
            else {
                final Rectangle bounds = this.fe.bounds();
                this.ff.j = 9;
                this.ff.a(bounds.x + bounds.width, bounds.y - 4);
            }
        }
        catch (Exception ex) {
            abljem.d(ex.toString());
        }
    }
    
    public Font h() {
        return this.n;
    }
    
    private void a(final abljempu abljempu, final int n, final int n2) {
        final Font h = this.h();
        this.getFont();
        try {
            if (abljempu == null) {
                return;
            }
            if (abljempu == this.f7) {
                this.aw();
            }
            abljempu.setFont(h);
            abljempu.a((Graphics)null);
            if (abljempu == this.gn) {
                this.ar();
            }
            else {
                this.i();
            }
            abljempu.a(n, n2);
        }
        catch (Exception ex) {
            abljem.d(ex.toString());
        }
    }
    
    private void ak() {
        this.i.e5 = this.i.e7;
        this.i.e6 = this.i.e8;
        this.eh = true;
    }
    
    private void a(final MenuItem menuItem) {
        if (this.e1 != ' ') {
            abljem.d("Macro playback ignored, already in typeahead mode");
            return;
        }
        this.e0.a(menuItem);
        this.a(null, 'P');
    }
    
    private void al() {
        this.e0.d();
        if (this.fe != null) {
            this.fe.a();
        }
    }
    
    private void am() {
        this.e0.e();
        Rectangle bounds = null;
        if (this.fe != null) {
            bounds = this.fe.bounds();
            this.fe.b();
        }
        this.a(bounds);
    }
    
    private boolean an() {
        try {
            if (this.f7 != null && this.f7.isVisible()) {
                return true;
            }
        }
        catch (Throwable t) {}
        try {
            if (this.gn != null && this.gn.isVisible()) {
                return true;
            }
        }
        catch (Throwable t2) {}
        try {
            if (this.d != null && this.d.isVisible()) {
                return true;
            }
        }
        catch (Throwable t3) {}
        try {
            if (this.hi != null && this.hi.isVisible()) {
                return true;
            }
        }
        catch (Throwable t4) {}
        try {
            if (abljemgt.br != null && abljemgt.br.isVisible()) {
                return true;
            }
        }
        catch (Throwable t5) {}
        try {
            if (this.ff != null && this.ff.isVisible()) {
                return true;
            }
        }
        catch (Throwable t6) {}
        try {
            if (this.b != null && this.b.isVisible()) {
                return true;
            }
        }
        catch (Throwable t7) {}
        return false;
    }
    
    private void ao() {
        if (this.i.cf) {
            return;
        }
        (this.gn = new abljempu(this.i, this, 0)).setBackground(this.o);
        this.gn.setFont(this.n);
    }
    
    private void ap() {
        if (this.i.cf) {
            return;
        }
        (this.d = new abljempu(this.i, this, 0)).setBackground(this.o);
        this.d.setFont(this.n);
        this.d.j = 6;
        this.gj = new MenuItem("Help");
        this.gk = new MenuItem("-");
        this.gm = new MenuItem("Page Down");
        this.gl = new MenuItem("Page Up");
        this.d.a(this.gl);
        this.d.a(this.gm);
        this.d.a(this.gk);
        this.d.a(this.gj);
    }
    
    private int a(final int n, final int n2) {
        int n3;
        for (n3 = 0; n3 < this.c && (n != this.gq[n3] || n2 < this.gr[n3] || n2 > this.gs[n3]); ++n3) {}
        if (n3 >= this.c) {
            return -1;
        }
        return n3;
    }
    
    private boolean a(final Event event, final String s) {
        return this.a(this.cu, event, s);
    }
    
    private boolean b(final Event event, final String s) {
        return this.a(this.cv, event, s);
    }
    
    private boolean a(final Component component, final Event event, final String s) {
        if (component == null) {
            return false;
        }
        for (int i = 0; i < this.i.e1; ++i) {
            if (this.i.ez[i].p == component) {
                this.i.ez[i].c(s);
                this.g("ENT");
                return true;
            }
        }
        return false;
    }
    
    private boolean c(final Event event, final String s) {
        final String[] e = this.e(s);
        if (e[0].length() == 0 || this.m(e[0])) {
            this.d(event, e[1]);
            return true;
        }
        if (this.cw != null) {
            this.cw.b();
            this.cw.repaint();
        }
        return false;
    }
    
    private Vector aq() {
        final Vector<Integer> vector = new Vector<Integer>();
        for (int i = 0; i < this.c; ++i) {
            if (this.gw[i] == 'S') {
                vector.addElement(new Integer(i));
            }
        }
        return vector;
    }
    
    private boolean a(final Vector vector) {
        if (vector == null || vector.size() < 1) {
            return true;
        }
        final int intValue = vector.elementAt(0);
        return this.gv[intValue].z != 1 || this.h(intValue);
    }
    
    private boolean h(int n) {
        final EmuRect emuRect = this.gu[n];
        if (emuRect.m != null && emuRect.m.size() > 0) {
            final String[] e = this.e(emuRect.m.a().c);
            for (int n2 = n; n2 >= 0 && this.gu[n2] == emuRect; --n2) {
                n = n2;
                if (this.gt[n2] != null) {
                    break;
                }
            }
            final abljemtf abljemtf = this.gt[n];
            if (abljemtf != null && e[0].length() > 0 && this.gu[n] == emuRect) {
                if (this.b(n, 0) > 0) {
                    this.repaint();
                }
                abljemtf.b(e[0]);
                return this.l(e[1]);
            }
        }
        return true;
    }
    
    private boolean l(String s) {
        boolean b = false;
        if (s == null) {
            return true;
        }
        if (s.startsWith("CMC:")) {
            s = s.substring(4);
            if (s.startsWith(" + E")) {
                b = true;
                s = s.substring(2);
            }
            this.e0.b(s);
            if (!b) {
                this.a(null, 'P');
            }
            return b;
        }
        return true;
    }
    
    private boolean i(final int n) {
        final EmuRect emuRect = this.gu[n];
        if (emuRect.m != null && emuRect.m.size() == 1) {
            final String[] e = this.e(emuRect.m.a().c);
            for (int i = 0; i < this.c; ++i) {
                if (this.gw[i] == 'S') {
                    final abljemtf abljemtf = this.gt[i];
                    if (abljemtf != null && this.gu[i] == emuRect) {
                        abljemtf.b(e[0]);
                    }
                }
            }
            if (this.gn != null && this.gn.isVisible()) {
                this.gn.hide();
            }
            if (this.l(e[1])) {
                this.i.e();
            }
            return true;
        }
        return false;
    }
    
    public void a(final EmuPanel emuPanel, final boolean b) {
        this.ec = true;
        for (int i = 0; i < this.c; ++i) {
            if (this.gv[i] == emuPanel && this.gt[i] != null) {
                this.a(i, b ? 'S' : ' ');
            }
        }
        this.ec = false;
        this.repaint();
    }
    
    public void a(final abljemtf abljemtf, final boolean b) {
        for (int i = 0; i < this.c; ++i) {
            if (this.gt[i] == abljemtf && b != (this.gw[i] == 'S')) {
                this.a(i, b ? 'S' : ' ');
                if (!this.ec) {
                    this.repaint();
                }
            }
        }
    }
    
    private int b(final int n, int n2) {
        int n3 = 0;
        if (n < 0) {
            int n4;
            if (n2 >= 0) {
                n4 = n3 + this.at();
            }
            else {
                n4 = n3 + this.j(-1);
            }
            return n4;
        }
        if (n < 0 || n >= this.c) {
            return n3;
        }
        if (n2 >= 0 && this.go >= 0 && this.gu[n] != this.gu[this.go]) {
            n3 += this.at();
        }
        if (n2 > 0 && (n2 & 0x4) != 0x0) {
            n2 -= 4;
        }
        switch (n2) {
            case 0: {
                ++n3;
                final jemTabPanel ac;
                if ((ac = this.gv[n].ac) != null && ac.f == '6') {
                    this.gp = -1;
                }
                else {
                    this.at();
                }
                this.a(this.go = n, 'S');
                break;
            }
            case 1: {
                final int go = this.go;
                ++n3;
                this.at();
                this.go = ((go < 0) ? n : go);
                for (int i = Math.min(this.go, n); i <= Math.max(this.go, n); ++i) {
                    this.b(i, 'S');
                }
                this.a(n, 'S');
                this.a(this.go, 'S');
                break;
            }
            case 2: {
                ++n3;
                if (this.gw[n] == 'S') {
                    this.a(n, ' ');
                    this.j(n);
                    break;
                }
                if (this.go < 0) {
                    this.go = n;
                }
                this.j(-1);
                this.a(n, 'S');
                break;
            }
            case -1: {
                if (!this.i.j.ax && this.gn != null && this.gn.isVisible()) {
                    return n3;
                }
                if (this.an() && (!this.i.j.ax || !this.gn.isVisible())) {
                    return n3 + this.j(-1);
                }
                if (this.go >= 0 && !this.i.j.ax) {
                    n3 += this.j(-1);
                    break;
                }
                n3 += this.j(n);
                break;
            }
        }
        return n3;
    }
    
    private int j(final int gp) {
        int n = 0;
        if (gp >= 0 && this.go >= 0 && this.gu[gp] != this.gu[this.go]) {
            if (this.a(this.gp, ' ') != null) {
                ++n;
            }
            this.gp = -1;
            return n;
        }
        if (gp == this.gp) {
            return n;
        }
        if (this.a(this.gp, ' ') != null) {
            ++n;
        }
        this.gp = -1;
        if (gp >= 0 && this.gw[gp] == ' ' && this.gv[gp].u != null) {
            this.a(this.gp = gp, 'T');
            ++n;
        }
        return n;
    }
    
    private abljemtf a(int n, final char c) {
        if (n < 0 || n >= this.c) {
            return null;
        }
        for (int n2 = n; n2 >= 0 && this.gu[n2] == this.gu[n]; --n2) {
            n = n2;
            if (this.gt[n2] != null) {
                break;
            }
        }
        if (this.gt[n] == null) {
            return null;
        }
        if (c != '\0') {
            this.b(n, c);
            for (int n3 = n + 1; n3 < this.c && this.gu[n3] == this.gu[n] && this.gt[n3] == null; ++n3) {
                this.b(n3, c);
            }
        }
        return this.gt[n];
    }
    
    private void ar() {
        if (this.gn == null) {
            return;
        }
        this.gn.a();
        final int n = (this.gp >= 0) ? this.gp : this.go;
        if (n < 0 || n > this.c) {
            return;
        }
        final EmuRect emuRect = this.gu[n];
        if (emuRect.m == null || emuRect.m.size() == 0) {
            return;
        }
        for (menu_item menu_item = emuRect.m.a(); menu_item != null; menu_item = emuRect.m.b()) {
            this.gn.a(new abljempi(menu_item));
        }
    }
    
    public void i() {
        if (this.as() > 0) {
            this.repaint();
        }
    }
    
    private int as() {
        return 0 + this.at() + this.k(-1);
    }
    
    private int at() {
        int n = 0;
        if (this.go >= 0 || this.gp >= 0) {
            ++n;
        }
        this.go = -1;
        this.gp = -1;
        for (int i = 0; i < this.c; ++i) {
            this.b(i, ' ');
        }
        return n;
    }
    
    private void b(final int n, final char c) {
        final abljemtf abljemtf = this.gt[n];
        this.gw[n] = c;
        if (abljemtf != null) {
            switch (c) {
                case 'S': {
                    abljemtf.a(true);
                    break;
                }
                case ' ': {
                    abljemtf.a(false);
                    break;
                }
            }
        }
    }
    
    private int k(final int fv) {
        final int n = 0;
        if (fv == this.i.fv) {
            return n;
        }
        final int n2 = n + this.c(false);
        this.i.fv = fv;
        return n2 + this.c(true);
    }
    
    private int c(final boolean b) {
        Color color = null;
        Color color2 = null;
        Color color3 = null;
        Color color4 = null;
        int n = 48;
        if (this.i.fv < 0 || this.i.fv >= this.i.fw) {
            return 0;
        }
        if (this.i.fu[this.i.fv] == null) {
            return 0;
        }
        switch (this.i.fu[this.i.fv].f) {
            case 'L': {
                color = this.ch;
                color2 = this.cg;
                color3 = this.cj;
                color4 = this.ci;
                n = this.ck;
                break;
            }
            case 'M': {
                color = this.cm;
                color2 = this.cl;
                color3 = this.co;
                color4 = this.cn;
                n = this.cp;
                break;
            }
        }
        if (b) {
            this.i.ft[this.i.fv] = color4;
            this.i.fs[this.i.fv] = ((color3 == null) ? this.i.fr[this.i.fv] : color3);
            if (n > 48) {
                this.i.fp[this.i.fv] = '2';
            }
        }
        else {
            this.i.ft[this.i.fv] = color2;
            this.i.fs[this.i.fv] = ((color == null) ? this.i.fr[this.i.fv] : color);
            if (n > 48) {
                this.i.fp[this.i.fv] = (char)((n == 50) ? 50 : 48);
            }
        }
        return 1;
    }
    
    private int c(final int n, final int n2) {
        int n3;
        for (n3 = 0; n3 < this.i.fw && (n != this.i.fi[n3] || n2 < this.i.fj[n3] || n2 >= this.i.fj[n3] + this.i.fk[n3]); ++n3) {}
        if (n3 >= this.i.fw) {
            return -1;
        }
        return n3;
    }
    
    private boolean m(final String s) {
        boolean b = false;
        if (s != null && s.length() > 0) {
            for (int i = 0; i < this.c; ++i) {
                if (this.gw[i] == 'S' && this.gt[i] != null && this.gt[i].ae >= s.length()) {
                    this.gt[i].b(s);
                    b = true;
                }
            }
        }
        this.i();
        return b;
    }
    
    private void au() {
        if (this.i.cf) {
            return;
        }
        (this.f7 = new abljempu(this.i, this, 0)).setBackground(this.o);
        this.f7.setFont(this.n);
        this.av();
    }
    
    private void av() {
        if (this.f7 == null) {
            return;
        }
        this.f7.a();
        try {
            if (this.e != null) {
                for (int i = 0; i < this.e.length; ++i) {
                    if (this.e[i] == '1') {
                        this.f7.a(this.h[i]);
                    }
                }
            }
        }
        catch (Throwable t) {}
        if (!this.i.a1) {
            this.f7.a(this.g2);
            this.f7.a(this.g3);
            this.f7.a(this.g4);
            this.f7.a(this.g6);
            this.f7.a(this.ha);
            this.f7.a(this.hf);
            this.f7.a(this.hb);
            this.f7.a(this.hc);
            if (this.i.i == 0) {
                this.f7.a(this.hd);
            }
        }
    }
    
    private void aw() {
        if (this.f7 == null) {
            return;
        }
        this.av();
        if (!this.i.a1) {
            if (this.he.isEnabled()) {
                this.f7.a(this.he);
            }
            this.g7.setLabel(this.e0.h ? "Stop Recording Macro" : "Macro");
            this.f7.a(this.hh);
            this.f7.a(this.g7);
        }
        if (!this.i.j.hs) {
            if (this.f7.w > 0) {
                this.f7.a(this.hg);
            }
            this.f7.a(this.g8);
        }
    }
    
    public String j() {
        String b = null;
        for (int i = 0; i < this.i.e1; ++i) {
            final abljemtf abljemtf = this.i.ez[i];
            if (abljemtf.s != null) {
                b = utils.b(b, abljemtf.s, utils.a(abljemtf.av));
            }
        }
        return b;
    }
    
    public void run() {
        final int n = 100;
        Object o = null;
        long n2 = 0L;
        this.ai();
        if (this.e3 == null) {
            this.e3 = this.e4;
        }
        while (this.i.j.az) {
            this.ag();
            final Dimension size = this.getSize();
            if (!this.a(size, this.d2)) {
                this.d2 = size;
                this.repaint();
            }
            if (this.i.ev && !this.i.ew) {
                this.i.ev = false;
                if (this.i.j.a1) {
                    this.repaint();
                }
                else {
                    if (this.i.e2 < 1 && !this.i.fx.k) {
                        this.i.fx.a();
                    }
                    this.o();
                }
            }
            if (this.ic && !this.i.ev && !this.i.ew && !this.e7) {
                this.ic = false;
                this.repaint();
            }
            if (this.fg != null && this.fg.isVisible()) {
                try {
                    this.bg();
                }
                catch (Exception ex2) {}
            }
            while (this.e1 == 'P') {
                try {
                    final Event f = this.e0.f();
                    if (f == null) {
                        this.a(null, ' ');
                    }
                    else {
                        f.arg = this.e0.e;
                        if (this.c4) {
                            f.target = this.c3;
                            this.c3.handleEvent(f);
                        }
                        else if (this.i.bu) {
                            this.handleEvent(f);
                        }
                        else {
                            this.d(f);
                        }
                    }
                }
                catch (Exception ex) {
                    abljem.d("jem error 34 - " + ex.toString());
                    this.e1 = ' ';
                }
            }
            if (this.i.di && !this.e7) {
                final Rectangle a8 = this.a8();
                if (a8 != null) {
                    if (o == null) {
                        o = a8;
                        if (n2 == 0L) {
                            n2 = System.currentTimeMillis();
                        }
                    }
                    if (this.fr != null && !a8.equals(this.fr)) {
                        final long currentTimeMillis = System.currentTimeMillis();
                        if (!a8.equals(o)) {
                            n2 = System.currentTimeMillis();
                        }
                        if (currentTimeMillis - n2 > 1000L) {
                            this.a9();
                        }
                        o = a8;
                    }
                }
            }
            if (!abljema.a((long)n)) {
                return;
            }
        }
    }
    
    private void d(final Event event) {
        abljemtf p = null;
        for (int n = 0; n < 100 && !this.eg; ++n) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        if (!this.eg) {
            abljem.d("Playback aborted - initial cursor not set");
            this.e0.d.removeAllElements();
            return;
        }
        for (int i = 0; i < this.i.e1; ++i) {
            p = this.i.ez[i];
            if (p.a) {
                break;
            }
            p = null;
        }
        if (p == null) {
            p = this.p(27);
            if (p != null && !p.a) {
                this.a(null, 'F');
                this.e0.a(event);
                if (p.a) {
                    this.a(null, 'P');
                }
                else {
                    p.requestFocus();
                }
                return;
            }
        }
        if (p == null) {
            this.handleEvent(event);
            return;
        }
        p.a(event);
    }
    
    public void a(final EmuPanel emuPanel, final EmuRect emuRect) {
        boolean b = false;
        int n = 0;
        if (emuRect.d < 1 || emuRect.d > 100) {
            return;
        }
        if (emuRect.n != null && emuRect.n.length != emuRect.d) {
            abljem.d("Rectangle " + emuRect.a + "," + emuRect.b + " " + emuRect.c + "x" + emuRect.d + " but subfile flags length=" + emuRect.n.length);
            emuRect.n = null;
        }
        if (emuRect.n == null) {
            emuRect.n = new char[emuRect.d];
            for (int i = 0; i < emuRect.d; ++i) {
                emuRect.n[i] = 'B';
            }
            b = true;
        }
        if (emuRect.m == null || emuRect.m.size() == 0) {
            return;
        }
        if (b) {
            for (int j = 0; j < emuRect.d; ++j) {
                emuRect.n[j] = ' ';
            }
        }
        for (int n2 = 0; this.c < this.gz && n2 < emuRect.d; ++n2) {
            final int n3 = emuRect.g + n2;
            this.gq[this.c] = n3;
            this.gr[this.c] = emuRect.h;
            this.gs[this.c] = emuRect.h + emuRect.c - 1;
            this.gt[this.c] = null;
            abljemtf abljemtf = null;
            abljemtf abljemtf2 = null;
            abljemtf abljemtf3 = null;
            for (int k = 0; k < this.i.e1; ++k) {
                final abljemtf abljemtf4 = this.i.ez[k];
                if (abljemtf4.ab == n3 && abljemtf4.ac >= emuRect.h && abljemtf4.ac <= this.gs[this.c]) {
                    if (abljemtf4.ae < 3) {
                        abljemtf3 = abljemtf4;
                        break;
                    }
                    if (abljemtf4.ae == 3 && abljemtf == null) {
                        abljemtf = abljemtf4;
                    }
                    else {
                        abljemtf2 = abljemtf4;
                    }
                }
            }
            abljemtf abljemtf5 = abljemtf3;
            if (abljemtf5 == null && abljemtf2 == null) {
                abljemtf5 = abljemtf;
            }
            if (abljemtf5 != null) {
                this.gt[this.c] = abljemtf5;
                if (abljemtf5.w && abljemtf5.av.length() > 0) {
                    abljemtf5.c("");
                }
                if (emuPanel.ac != null && emuPanel.ac.f == '6') {
                    abljemtf5.d();
                }
            }
            if (b) {
                if (this.gt[this.c] != null) {
                    emuRect.n[n2] = 'B';
                }
                else if (n2 > 0 && emuRect.n[n2 - 1] != ' ') {
                    emuRect.n[n2] = 'C';
                }
            }
            switch (emuRect.n[n2]) {
                case 'C': {
                    this.gt[this.c] = null;
                    break;
                }
                case 'B': {
                    n = ((this.gt[this.c] != null) ? 1 : 0);
                    break;
                }
                default: {
                    n = 0;
                    break;
                }
            }
            if (n != 0) {
                this.gu[this.c] = emuRect;
                this.gv[this.c] = emuPanel;
                this.gw[this.c] = ' ';
                this.gx[this.c] = emuPanel.w;
                this.gy[this.c] = emuPanel.x;
                ++this.c;
            }
        }
        if (b) {
            for (int n4 = this.c - 1; n4 >= 0 && this.gt[n4] == null; --n4, --this.c) {
                int n5;
                int n6;
                for (n5 = (this.gq[n4] - 1) * this.i.es + (this.gr[n4] - 1), n6 = 0; n6 < emuRect.c && this.i.el[n5] == 32; ++n6, ++n5) {}
                if (n6 < emuRect.c) {
                    break;
                }
            }
        }
    }
    
    public void k() {
        this.as = ((this.db == null) ? 0 : this.db.left) + this.dd;
        this.at = ((this.db == null) ? 0 : this.db.top) + this.dd;
        this.au = ((this.db == null) ? 0 : this.db.right) + this.dd;
        this.av = ((this.db == null) ? 0 : this.db.bottom) + this.dd;
        this.aw = ((this.cx == null) ? 0 : this.cx.size().height);
        this.dh = this.df[1];
        this.di = this.df[2];
        this.dg = this.df[4];
        if (this.dh != null) {
            this.dh.move(this.dl + this.as, this.dm + this.at);
        }
        if (this.dg != null && this.dg != this.dh) {
            this.dg.move(this.dl + this.as, this.dm + ((this.dh == null) ? 0 : this.dh.e()) + this.at);
        }
        if (this.di != null && this.di != this.dh) {
            this.di.move(this.dl + ((this.dh == null) ? 0 : this.dh.d()) + this.as, this.dm + this.at);
        }
        this.dj = ((this.dh == null) ? 0 : this.dh.d()) + ((this.di == null || this.di == this.dh) ? 0 : this.di.d()) + this.as + this.au;
        this.dk = ((this.dh == null) ? 0 : this.dh.e()) + ((this.dg == null || this.dg == this.dh) ? 0 : this.dg.e()) + this.at + this.av + this.aw + ((this.aq == null) ? 0 : this.aq.n);
        this.ax = ((this.dg == null) ? 0 : this.dg.d());
        this.ay = ((this.di == null) ? 0 : this.di.e());
        if (this.dh != null && this.dh != this.dg && this.dh != this.di && this.ax < this.dh.d() && this.ay < this.dh.e()) {
            if (this.dg != null) {
                this.ax = this.dh.d();
            }
            if (this.di != null) {
                this.ay = this.dh.e();
            }
        }
        if (this.cq != null) {
            this.ay += this.cq.size().height;
        }
        if (this.cr != null) {
            this.ay += this.cr.size().height;
        }
        if (this.cs != null) {
            this.ay += this.cs.size().height;
        }
        if (this.ct != null) {
            this.ay += this.ct.size().height;
        }
        if (!this.ad || this.i.i != 1) {
            this.ax();
        }
        if (!this.a5()) {
            this.f(true);
        }
    }
    
    private void ax() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            abljem.d("No graphics environment for resize_style");
            return;
        }
        int n = 0;
        if (!this.a5()) {
            n = ((this.di == null) ? 0 : (this.dj + 10));
        }
        int size = 0;
        if (this.i.i > 1 && this.i.j.o[1] != null) {
            size = this.i.j.o[1].fb.y.getSize();
        }
        this.a(graphics, n, size);
        if (!this.a5()) {
            if (this.i.i > 1) {
                this.ab = 0;
                this.ac = 0;
            }
            this.ef = false;
            this.d3 = this.ai[this.ab].width;
            this.d4 = this.ai[(this.t == 1) ? this.ac : this.ab].height;
            int dj = this.dj;
            int dk = this.dk;
            if (dj > this.p - this.r) {
                dj = this.p - this.r;
            }
            if (dk > this.q - this.s) {
                dk = this.q - this.s;
            }
            if (this.d3 < dj) {
                this.d3 = dj;
            }
            if (this.d4 < dk) {
                this.d4 = dk;
            }
            this.d(this.d3, this.d4);
            if (this.i.i > 1) {
                try {
                    int i;
                    for (i = this.i.i - 1; i >= 0; --i) {
                        final abljema abljema = this.i.j.o[i];
                        if (abljema != null && abljema.fb.isVisible()) {
                            break;
                        }
                    }
                    if (i >= 0) {
                        final Rectangle bounds = this.i.j.o[i].fb.bounds();
                        final Rectangle bounds2 = this.bounds();
                        int n2 = bounds.x + (bounds.width - bounds2.width) / 2;
                        int n3 = bounds.y + (bounds.height - bounds2.height) / 2;
                        if (n2 + bounds2.width > this.p) {
                            n2 = this.p - bounds2.width;
                        }
                        if (n3 + bounds2.height > this.q) {
                            n3 = this.p - bounds2.height;
                        }
                        if (n2 < 0) {
                            n2 = 0;
                        }
                        if (n3 < 0) {
                            n3 = 0;
                        }
                        this.move(n2, n3);
                    }
                }
                catch (Throwable t) {}
            }
        }
    }
    
    public void l() {
        this.f(true);
    }
    
    public void a(final boolean ew) {
        synchronized (this.k) {
            this.i.ew = ew;
        }
        // monitorexit(this.k)
    }
    
    public Image m() {
        if (this.ap == null || this.ap.o == null) {
            return null;
        }
        return this.ia;
    }
    
    public Image n() {
        return this.ia;
    }
    
    public void o() {
        if (this.dz != this.d3 || this.d0 != this.d4) {
            this.dz = this.d3;
            this.d0 = this.d4;
            this.repaint(0, 0, this.d3 - 1 + 50, this.d4 - 1 + 50);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.i.cj) {
            this.paint(graphics);
        }
        else {
            super.update(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        synchronized (this.k) {
            this.a(graphics);
        }
        // monitorexit(this.k)
    }
    
    private void a(Graphics ib) {
        int h = 0;
        int i = 0;
        final Graphics graphics = ib;
        int n = 0;
        this.ic = true;
        if (this.i.cj && (this.ia == null || this.p > this.h8 || this.q > this.h9)) {
            final int n2 = this.p + 100;
            final int n3 = this.q + 100;
            this.h8 = this.p;
            this.h9 = this.q;
            try {
                this.ia = this.createImage(n2, n3);
            }
            catch (Throwable t2) {
                this.ia = null;
            }
            if (this.ia == null) {
                try {
                    this.ia = this.createImage(n2, n3);
                }
                catch (Throwable t3) {
                    this.ia = null;
                }
            }
            if (this.ia == null) {
                abljem.d("Image buffer create failed " + n2 + "x" + n3);
            }
            if (this.ia != null) {
                try {
                    this.ib = this.ia.getGraphics();
                }
                catch (Exception ex) {
                    abljem.d("Image buffer getGraphics failed - " + ex);
                    this.ia = null;
                }
            }
            if (this.ia == null) {
                this.i.cj = false;
            }
        }
        this.dz = 0;
        this.d0 = 0;
        if (this.af == 0) {
            this.a(ib, 0, 0);
        }
        if (this.hj && !this.hi.isVisible() && this.f6.isVisible()) {
            this.f6.hide();
            this.hj = false;
        }
        if (!this.dn) {
            this.dn = true;
            final Insets insets = this.insets();
            int left = insets.left;
            if (left < 0) {
                left = -left;
            }
            int top = insets.top;
            if (top < 0) {
                top = -top;
            }
            if (left > 0 && top > 0 && left != this.dl && top != this.dm) {
                abljem.d("Border inset changed from " + this.dl + "," + this.dm + " to " + left + "," + top);
                this.dl = left;
                this.dm = top;
                if (this.i.b0) {
                    this.dv = this.dl;
                    this.dw = this.dm;
                }
                else {
                    this.dx = this.dl;
                    this.dy = this.dm;
                }
            }
        }
        if (this.i.ev || this.i.ew) {
            if (this.d3 > 0 && !this.ed) {
                if (!this.bx.equals(this.getBackground())) {
                    this.setBackground(this.bx);
                }
                this.a(ib, this.bx, 0, 0, this.d3 - 1, this.d4 - 1);
                this.ed = true;
            }
            return;
        }
        final Dimension a1 = this.a1();
        if (a1.width <= 0 || a1.height <= 0) {
            return;
        }
        if (this.e5 != null && !this.a(this.e5, a1) && !this.e7) {
            boolean b = false;
            if (this.a(this.e5) || this.a(a1)) {
                b = true;
            }
            this.a2();
            if (b || !this.i.br) {
                this.b(a1);
                this.f(true);
            }
        }
        this.e5 = a1;
        if (this.i.j.a1) {
            this.ic = false;
            this.a(ib, a1);
            if (!this.hn) {
                this.a9();
            }
            this.hn = true;
            return;
        }
        if ((this.ic || this.e7) && !this.id) {
            final Rectangle bounds = this.bounds();
            if (!abljemcb.a(ib, 0, 0, bounds.width, bounds.height)) {
                if (!this.id) {
                    abljem.d("setClip failed - paint may be incomplete");
                }
                this.id = true;
            }
        }
        if (this.e7) {
            this.a(ib, a1, this.bounds());
            return;
        }
        this.ic = false;
        this.ed = false;
        if (!this.hm) {
            this.i.p();
            this.hm = true;
        }
        if (this.t != 1 && this.i.i == 1) {
            if (this.i.et <= this.w && this.i.es <= this.x) {
                if (this.y.getSize() != this.aa) {
                    this.ab = this.af - 1;
                    while (this.ab > 0 && this.ag[this.ab] != this.aa) {
                        --this.ab;
                    }
                    this.a4();
                }
            }
            else {
                if (this.i.et != this.al || this.i.es != this.am) {
                    this.al = this.i.et;
                    this.am = this.i.es;
                    for (int j = 0; j < this.af; ++j) {
                        this.ak[j] = this.a(ib, j, this.al, this.am);
                    }
                }
                this.ab = this.af - 1;
                while (this.ab > 0) {
                    if (this.ag[this.ab] == this.aa) {
                        break;
                    }
                    --this.ab;
                }
                while (this.ab > 0 && (this.ak[this.ab].height > this.d4 || this.ak[this.ab].width > this.d3)) {
                    --this.ab;
                }
                if (this.ab == 0) {
                    this.ab = 1;
                    while (this.ab + 1 < this.af && this.ak[this.ab].equals(this.ak[this.ab + 1])) {
                        ++this.ab;
                    }
                }
                this.a4();
            }
        }
        ib.setFont(this.y);
        ib.setColor(this.bz);
        if (this.getBackground() != this.bx) {
            this.setBackground(this.bx);
        }
        if (this.i.cj) {
            this.ib.setClip(ib.getClip());
            ib = this.ib;
            ib.setFont(this.y);
            ib.setColor(this.bz);
        }
        if (this.i.eg != null) {
            h = this.i.eg.getWidth(null);
            i = this.i.eg.getHeight(null);
        }
        if ((h < 1 || i < 1) && this.i.j.cr != null) {
            h = this.i.j.cr.getWidth(null);
            i = this.i.j.cr.getHeight(null);
        }
        if (!this.ee) {
            this.f(true);
            this.ee = true;
        }
        this.a(ib, this.bx, 0, 0, this.d3 - 1 + 50, this.d4 - 1 + 50);
        final int bc = this.bc;
        try {
            if (this.db != null) {
                if (this.db.top > 0) {
                    this.a(ib, this.da, this.dl, this.dm, this.d3, this.db.top);
                }
                if (this.db.bottom > 0) {
                    this.a(ib, this.da, this.dl, this.d4 - this.bi - this.db.bottom + this.dm, this.d3, this.db.bottom);
                }
                if (this.db.left > 0) {
                    this.a(ib, this.da, this.dl, this.dm, this.db.left, this.d4 - this.bi);
                }
                if (this.db.right > 0) {
                    this.a(ib, this.da, this.d3 - this.db.right + this.dl, this.dm, this.db.right, this.d4 - this.bi);
                }
                if (this.c9.j > 0 && this.db.left > 1 && this.db.right > 1 && this.db.top > 1 && this.db.bottom > 1) {
                    this.a(ib, this.c9, this.db.left - 1 + this.dl, this.db.top - 1 + this.dm, this.d3 - this.db.right + this.dl, this.d4 - this.bi - this.db.bottom + this.dm);
                }
            }
            if (this.dd >= 0) {
                final int n4 = this.as - this.dd;
                final int n5 = this.au - this.dd;
                final int n6 = this.at - this.dd;
                final int n7 = this.av - this.dd;
                this.a(ib, this.dc, this.dl + n4, this.dm + n6, this.d3 - n4 - n5, this.dd);
                this.a(ib, this.dc, this.dl + n4, this.d4 - this.bi - n7 - this.dd + this.dm, this.d3 - n4 - n5, this.dd);
                this.a(ib, this.dc, this.dl + n4, this.dm + n6, this.dd, this.d4 - this.bi - n6 - n7);
                this.a(ib, this.dc, this.d3 - this.au + this.dl, this.dm + n6, this.dd, this.d4 - this.bi - n6 - n7);
            }
            if (this.ar != null && this.ar.left > 0 && this.ar.right > 0 && this.ar.top > 0 && this.ar.bottom > 0) {
                this.a(ib, this.de, this.as + this.ax + this.ar.left - 1 + this.dl, this.at + this.ay + this.ar.top - 1 + this.dm, this.d3 - this.ar.right - this.au + this.dl, this.d4 - this.bi - this.ar.bottom - this.av + this.dm);
            }
            if (this.ap != null && this.ap.o != null) {
                int n8 = this.as + this.ax + ((this.ar == null) ? 0 : this.ar.left) + this.dl;
                int n9 = this.at + this.ay + ((this.ar == null) ? 0 : this.ar.top) + this.dm;
                int n10 = this.d3 - ((this.ar == null) ? 0 : this.ar.right) - this.au + this.dl;
                int n11 = this.d4 - this.bi - ((this.ar == null) ? 0 : this.ar.bottom) - this.av + this.dm;
                int n12 = 0;
                if (this.cq != null) {
                    if (this.cq.j == '1') {
                        n12 = 1;
                    }
                    if (n12 != 0) {
                        n9 -= this.cq.size().height;
                    }
                }
                if (this.cs != null) {
                    if (this.cs.j == '1') {
                        n12 = 1;
                    }
                    if (n12 != 0) {
                        n9 -= this.cs.size().height;
                    }
                }
                if (this.cr != null) {
                    if (this.cr.j == '1') {
                        n12 = 1;
                    }
                    if (n12 != 0) {
                        n9 -= this.cr.size().height;
                    }
                }
                if (this.ct != null) {
                    if (this.ct.j == '1') {
                        n12 = 1;
                    }
                    if (n12 != 0) {
                        n9 -= this.ct.size().height;
                    }
                }
                if (this.do) {
                    --n8;
                    --n9;
                    if (n12 == 0) {
                        ++n8;
                        ++n9;
                        --n10;
                        --n11;
                    }
                }
                final int n13 = n10 - n8 + 1;
                final int n14 = n11 - n9 + 1;
                final Point a2 = this.ap.a(n8, n9, n8, n9, n13, n14);
                if (!abljemcb.a(ib, n8, n9, n13, n14)) {
                    if (!this.ie) {
                        abljem.d("setClip failed - GUIStyle background image may overwrite other elements");
                    }
                    this.ie = true;
                }
                ib.drawImage(this.ap.o, a2.x, a2.y, this.bx, this);
                final Rectangle bounds2 = this.bounds();
                abljemcb.a(ib, 0, 0, bounds2.width, bounds2.height);
            }
            if (this.cq != null && this.cq.isVisible() && this.cq.g == 0) {
                this.cq.repaint();
            }
            if (this.cr != null && this.cr.isVisible() && this.cr.g == 0) {
                this.cr.repaint();
            }
            if (this.cs != null && this.cs.isVisible() && this.cs.g == 0) {
                this.cs.repaint();
            }
            if (this.ct != null && this.ct.isVisible() && this.ct.g == 0) {
                this.ct.repaint();
            }
            if (this.cx != null && this.cx.isVisible() && this.cx.g == 0) {
                this.cx.repaint();
            }
            if (this.cz != null && !this.c0) {
                this.c0 = true;
                final int n15 = this.cz[0].b + this.cz[0].c - 1;
                for (EmuPanel emuPanel = this.ap.ao.a(); emuPanel != null; emuPanel = this.ap.ao.b()) {
                    emuPanel.af = ((emuPanel.b + emuPanel.c - 1 == n15) ? this.bh : 0);
                    for (EmuRect emuRect = emuPanel.l.a(); emuRect != null; emuRect = emuPanel.l.b()) {
                        emuRect.o = ((emuPanel.ag && emuRect.i == 'H') ? this.bg : 0);
                    }
                }
            }
            for (EmuPanel b2 = (this.ap == null) ? null : this.ap.ao.a(); b2 != null; b2 = this.ap.ao.b()) {
                int n16 = 0;
                int n17 = 0;
                final int f = (b2.b - 1) * this.a7 + this.bb - this.a7 / 2 + this.dv;
                int e = this.bc + (b2.a - 1) * this.a6 + this.dw;
                int n18 = b2.c * this.a7 + this.a7 + b2.af;
                int n19 = b2.d * this.a6 + this.a6 / 2;
                if (b2.a == 1 && (this.cq != null || this.cs != null || this.cr != null || this.ct != null)) {
                    e -= this.a6 / 2;
                    n19 += this.a6 / 2;
                }
                if (b2.a == 1 && this.ap != null) {
                    e += this.ap.t;
                    n19 -= this.ap.t;
                }
                if (e + n19 > n) {
                    n = e + n19;
                }
                if (b2.k != '1') {
                    this.a(ib, b2.m, f, e, n18, n19);
                }
                b2.e = e;
                b2.f = f;
                b2.g = e + n19 - 1;
                b2.h = f + n18 - 1;
                final edge ad = b2.ad;
                int n20 = 0;
                int n21 = 0;
                if (this.a7 > 4) {
                    n20 = 1;
                }
                if (this.a7 > 5) {
                    n20 = 2;
                }
                if (this.a7 > 6) {
                    n20 = 99;
                }
                if (ad.j < 1 && ad.a != null) {
                    --n18;
                    --n19;
                    this.a(ib, b2.ad.a);
                    if (b2.ad.b > 0 && n20 > 0) {
                        n21 = 1;
                        ib.drawRect(f, e, n18, n19);
                    }
                    if (b2.ad.b > 1 && n20 > 1) {
                        n21 = 2;
                        ib.drawRect(f + 1, e + 1, n18 - 2, n19 - 2);
                    }
                    if (b2.ad.b > 2 && n20 > 2) {
                        n21 = 3;
                        ib.drawRect(f + 2, e + 2, n18 - 4, n19 - 4);
                    }
                }
                if (ad.j > 0 && n20 > 1) {
                    final int n22 = f + 1;
                    final int n23 = e + 1;
                    final int n24 = f + n18 - 1 - 1;
                    final int n25 = e + n19 - 1 - 1;
                    n21 = 2;
                    this.a(ib, ad, n22, n23, n24, n25);
                }
                final EmuPanel emuPanel2 = b2;
                emuPanel2.e += n21;
                final EmuPanel emuPanel3 = b2;
                emuPanel3.f += n21;
                final EmuPanel emuPanel4 = b2;
                emuPanel4.g -= n21;
                final EmuPanel emuPanel5 = b2;
                emuPanel5.h -= n21;
                for (EmuRect emuRect2 = b2.l.a(); emuRect2 != null; emuRect2 = b2.l.b()) {
                    if (emuRect2.i == 'H') {
                        final int n26 = (emuRect2.h - 1) * this.a7 + this.bb - this.a7 / 2 + this.dv + this.a7 / 3;
                        final int n27 = this.bc + (emuRect2.g - 1) * this.a6 + this.dw + this.a5;
                        int n28 = emuRect2.c * this.a7 + this.a7 + emuRect2.o - 2 * (this.a7 / 3);
                        n28 += 2;
                        final int n29 = emuRect2.d * this.a6;
                        final int n30 = b2.b + b2.c - (emuRect2.h + emuRect2.c);
                        if (n30 > 0) {
                            n28 += n30 * this.a7;
                        }
                        if (b2.k != '1') {
                            this.a(ib, b2.n, n26, n27, n28, n29);
                        }
                        n16 = n26;
                        n17 = n27 + n29 - 1;
                    }
                    else if (emuRect2.i == 'S') {
                        final int[] array = new int[emuRect2.d];
                        int n31 = 0;
                        if (b2.ac != null && b2.ac.f == '6' && this.c7 == null) {
                            (this.c7 = new abljembx(this.i, null, emuRect2.g - 1, 0)).a(b2);
                            this.c7.setBackground(b2.n);
                            this.c7.hide();
                            this.add(this.c7);
                        }
                        int n32;
                        int n33;
                        if (n16 == 0 && n17 == 0) {
                            n32 = (emuRect2.h - 1) * this.a7 + this.bb + this.dv;
                            n33 = this.bc + (emuRect2.g - 1) * this.a6 + this.dw + this.a5 - 1;
                        }
                        else {
                            n32 = n16;
                            n33 = n17;
                        }
                        final int n34 = (emuRect2.h + emuRect2.c - 1) * this.a7 + this.bb + this.dv - n32;
                        int n35 = (b2.ae - 1) * this.a7;
                        if (n35 < 0) {
                            n35 = 0;
                        }
                        final int n36 = n32 + n35;
                        final int n37 = n34 - n35;
                        if (this.a6 > 12) {
                            ++n33;
                        }
                        int n38 = 0;
                        for (int k = 0; k < emuRect2.d; ++k, n33 += this.a6) {
                            if (emuRect2.n[k] == 'B') {
                                array[n38++] = n33;
                            }
                            if (emuRect2.n[k] != ' ') {
                                n31 = n33 + this.a6;
                            }
                        }
                        if (b2.t != null) {
                            for (int l = 0; l < n38; l += 2) {
                                final int n39 = l + 1;
                                this.a(ib, b2.t, n36, array[l], n37, ((n39 < n38) ? array[n39] : n31) - array[l]);
                            }
                        }
                        if (b2.t == null && this.a6 > 8 && n37 > n35) {
                            this.a(ib, b2.r);
                            for (int n40 = 0; n40 < n38; ++n40) {
                                ib.drawLine(n36, array[n40], n36 + n37 - 1, array[n40]);
                            }
                        }
                    }
                }
            }
            for (int n41 = 0; n41 < this.c; ++n41) {
                if (this.gw[n41] != ' ') {
                    final int n42 = (this.gr[n41] - 1) * this.a7 + this.bb + this.dv;
                    final int n43 = this.bc + (this.gq[n41] - 1) * this.a6 + this.dw + this.a5;
                    final int n44 = (this.gs[n41] - this.gr[n41] + 1) * this.a7;
                    final int a3 = this.a6;
                    final Color color = (this.gw[n41] == 'T') ? this.gv[n41].u : this.gv[n41].s;
                    if (color == null) {
                        if (this.gv[n41].m.equals(Color.white)) {
                            ib.setColor(Color.black);
                        }
                        else {
                            ib.setColor(Color.white);
                        }
                    }
                    this.a(ib, color, n42, n43, n44, a3);
                }
            }
        }
        catch (Throwable t) {
            abljem.d("GUIStyle background fill error=" + t);
            t.printStackTrace();
        }
        if (this.i.bu) {
            for (int n45 = 0; n45 < this.i.e1; ++n45) {
                final abljemtf abljemtf = this.i.ez[n45];
                if (abljemtf.av == 0) {
                    abljemtf.av = (abljemtf.ac - 1) * this.a7 + this.bb;
                    abljemtf.aw = this.bc + abljemtf.ab * this.a6;
                }
            }
        }
        int n49;
        for (int n46 = 0; n46 < this.i.fw; n46 = n49 - 1, ++n46) {
            if (this.es != null && this.et == this.i.fl[n46] - 1) {
                this.a(ib, n46);
            }
            if (this.i.fm[n46] == 0) {
                this.i.fm[n46] = (this.i.fj[n46] - 1) * this.a7 + this.bb;
                this.i.fn[n46] = this.bc + this.i.fi[n46] * this.a6;
            }
            final int n47 = this.i.fl[n46];
            int n48 = this.i.fk[n46];
            n49 = n46 + 1;
            if (this.i.fo[n46] == 32 && this.i.fs[n46] == null && this.i.ft[n46] == null) {
                while (n49 < this.i.fw && this.i.fo[n49] == 32 && this.i.fp[n49] == this.i.fp[n46] && this.i.fs[n49] == null && this.i.ft[n49] == null && this.i.fl[n49] == n47 + n48 + 1) {
                    n48 += this.i.fk[n49++] + 1;
                }
            }
            Boolean b3 = null;
            final Font font = this.i.fq[n46];
            if (this.ap != null && this.ap.r == '1') {
                b3 = new Boolean(font != null && font.isBold());
            }
            this.a(ib, this.i.fo[n46], this.i.fp[n46], b3, this.i.fs[n46], this.i.ft[n46], this.i.fj[n46], this.i.fl[n46], n48, this.i.fm[n46], this.i.fn[n46]);
        }
        if (this.i.fx != null && this.i.fx.k && this.i.fx.h == 0) {
            this.i.fx.a();
        }
        if (!this.hn) {
            this.a9();
        }
        if (!this.hn) {
            this.i.j();
        }
        this.hn = true;
        if (this.hp > 0) {
            this.a(ib, this.hp, 'A');
        }
        if (this.hq > 0) {
            this.a(ib, this.hq, 'O');
        }
        ib.setFont(this.y);
        if (this.i.cj) {
            this.b(ib);
            ib = graphics;
        }
        final Vector vector = new Vector<CommandStatusPanel>();
        final int countComponents = this.countComponents();
        final Component[] array2 = new Component[countComponents];
        for (int n50 = 0; n50 < countComponents; ++n50) {
            try {
                array2[n50] = this.getComponent(n50);
            }
            catch (Throwable t4) {}
        }
        for (final Component component : array2) {
            if (component != null) {
                if (component != this.c7) {
                    if (component == this.aq) {
                        this.aq.a(this.dl, this.d4 - this.bi + this.dm, this.d3, this.bb, this.i.es * this.a7);
                        if (this.aq.isVisible()) {
                            this.aq.repaint();
                        }
                        else {
                            vector.addElement(this.aq);
                        }
                    }
                    else if (component == this.cq || component == this.cs || component == this.cr || component == this.ct) {
                        final jemTabPanel jemTabPanel = (jemTabPanel)component;
                        if (jemTabPanel.g == 0) {
                            final EmuPanel m = jemTabPanel.k;
                            jemTabPanel.setFont(this.y);
                            jemTabPanel.e = this.dv + this.bb - this.a7 / 2 + jemTabPanel.j * this.a7;
                            jemTabPanel.f = this.dm + this.at + this.ay - jemTabPanel.size().height;
                            if (this.cs != null && jemTabPanel == this.cq) {
                                final jemTabPanel jemTabPanel2 = jemTabPanel;
                                jemTabPanel2.f -= this.cs.size().height;
                            }
                            if (this.cr != null && (jemTabPanel == this.cq || jemTabPanel == this.cs)) {
                                final jemTabPanel jemTabPanel3 = jemTabPanel;
                                jemTabPanel3.f -= this.cr.size().height;
                            }
                            if (this.ct != null && (jemTabPanel == this.cq || jemTabPanel == this.cs || jemTabPanel == this.cr)) {
                                final jemTabPanel jemTabPanel4 = jemTabPanel;
                                jemTabPanel4.f -= this.ct.size().height;
                            }
                            jemTabPanel.g = jemTabPanel.size().width;
                            jemTabPanel.h = jemTabPanel.size().height;
                            if ((jemTabPanel.f == '1' || jemTabPanel.g == '6') && m != null) {
                                jemTabPanel.a(new Dimension((m.c - jemTabPanel.j) * this.a7, jemTabPanel.h));
                            }
                            jemTabPanel.reshape(jemTabPanel.e, jemTabPanel.f, jemTabPanel.g, jemTabPanel.h);
                            if (jemTabPanel.isVisible()) {
                                jemTabPanel.repaint();
                            }
                            else {
                                vector.addElement((CommandStatusPanel)jemTabPanel);
                            }
                        }
                    }
                    else if (component == this.cu) {
                        final jemTabPanel jemTabPanel5 = (jemTabPanel)component;
                        if (jemTabPanel5.g == 0) {
                            final EmuPanel k2 = jemTabPanel5.k;
                            jemTabPanel5.a(this.y, this.y, this.a7);
                            jemTabPanel5.c = this.i.es;
                            jemTabPanel5.e = this.dv + this.bb;
                            jemTabPanel5.f = (jemTabPanel5.a - 1) * this.a6 + this.a5 + this.bc + this.dm;
                            jemTabPanel5.g = jemTabPanel5.c * this.a7;
                            jemTabPanel5.h = jemTabPanel5.d * this.a6;
                            if (k2 != null) {
                                jemTabPanel5.g = (k2.c - jemTabPanel5.j) * this.a7 + this.a7 + k2.af;
                            }
                            if (!this.z && this.ap != null) {
                                final jemTabPanel jemTabPanel6 = jemTabPanel5;
                                jemTabPanel6.h -= this.ap.z * jemTabPanel5.d;
                                final jemTabPanel jemTabPanel7 = jemTabPanel5;
                                jemTabPanel7.f += this.ap.z;
                            }
                            jemTabPanel5.reshape(jemTabPanel5.e, jemTabPanel5.f, jemTabPanel5.g, jemTabPanel5.h);
                            if (jemTabPanel5.isVisible()) {
                                jemTabPanel5.repaint();
                            }
                            else {
                                vector.addElement((CommandStatusPanel)jemTabPanel5);
                            }
                        }
                    }
                    else if (component == this.cw) {
                        final jemTabPanel jemTabPanel8 = (jemTabPanel)component;
                        if (jemTabPanel8.g == 0) {
                            final EmuPanel k3 = jemTabPanel8.k;
                            jemTabPanel8.c = this.i.es;
                            jemTabPanel8.e = this.dv + this.bb - this.a7 / 2 + jemTabPanel8.j * this.a7;
                            jemTabPanel8.f = this.bc + (jemTabPanel8.a - 1) * this.a6 + this.dm;
                            jemTabPanel8.g = jemTabPanel8.size().width;
                            if (k3 != null) {
                                jemTabPanel8.g = (k3.c - jemTabPanel8.j) * this.a7 + this.a7 + k3.af;
                            }
                            jemTabPanel8.h = this.a6 * jemTabPanel8.d;
                            if (jemTabPanel8.f == '3' || jemTabPanel8.f == '6') {
                                jemTabPanel8.h = jemTabPanel8.size().height;
                                final jemTabPanel jemTabPanel9 = jemTabPanel8;
                                jemTabPanel9.f -= jemTabPanel8.h - this.a6;
                            }
                            jemTabPanel8.reshape(jemTabPanel8.e, jemTabPanel8.f, jemTabPanel8.g, jemTabPanel8.h);
                            if (jemTabPanel8.isVisible()) {
                                jemTabPanel8.repaint();
                            }
                            else {
                                vector.addElement((CommandStatusPanel)jemTabPanel8);
                            }
                        }
                    }
                    else if (component == this.cx) {
                        final jemTabPanel jemTabPanel10 = (jemTabPanel)component;
                        if (jemTabPanel10.g == 0) {
                            final EmuPanel k4 = jemTabPanel10.k;
                            jemTabPanel10.e = this.dv + this.bb - this.a7 / 2 + jemTabPanel10.j * this.a7;
                            jemTabPanel10.f = n + this.a6 / 2;
                            if (this.aq == null) {
                                final jemTabPanel jemTabPanel11 = jemTabPanel10;
                                jemTabPanel11.f += this.bf + this.dw - this.a8 + this.a6;
                            }
                            if (jemTabPanel10.l == '1') {
                                jemTabPanel10.f = this.d4 - this.bi + this.dm - jemTabPanel10.size().height - 3;
                            }
                            jemTabPanel10.g = jemTabPanel10.size().width;
                            if (k4 != null) {
                                jemTabPanel10.g = (k4.c - jemTabPanel10.j) * this.a7 + this.a7 + k4.af;
                            }
                            jemTabPanel10.h = jemTabPanel10.size().height;
                            jemTabPanel10.reshape(jemTabPanel10.e, jemTabPanel10.f, jemTabPanel10.g, jemTabPanel10.h);
                            if (jemTabPanel10.isVisible()) {
                                jemTabPanel10.repaint();
                            }
                            else {
                                vector.addElement((CommandStatusPanel)jemTabPanel10);
                            }
                        }
                    }
                    if (component instanceof jemScrollPanel) {
                        final jemScrollPanel jemScrollPanel = (jemScrollPanel)component;
                        if (jemScrollPanel.g == 0) {
                            jemScrollPanel.e = (jemScrollPanel.b - 1) * this.a7 + this.bb + this.dl + this.a7 / 2;
                            jemScrollPanel.f = this.bc + (jemScrollPanel.a - 1) * this.a6 + this.dm + this.a5;
                            jemScrollPanel.g = this.a7 * jemScrollPanel.c;
                            jemScrollPanel.h = this.a6 * jemScrollPanel.d - this.a5;
                            final jemScrollPanel jemScrollPanel2 = jemScrollPanel;
                            jemScrollPanel2.h += this.a5;
                            final jemScrollPanel jemScrollPanel3 = jemScrollPanel;
                            ++jemScrollPanel3.e;
                            jemScrollPanel.move(jemScrollPanel.e, jemScrollPanel.f);
                            jemScrollPanel.setSize(jemScrollPanel.getPreferredSize().width, jemScrollPanel.h);
                            if (jemScrollPanel.isVisible()) {
                                jemScrollPanel.repaint();
                            }
                            else {
                                vector.addElement((CommandStatusPanel)jemScrollPanel);
                            }
                        }
                    }
                    else if (component instanceof abljemtf) {
                        Font font2 = this.y;
                        final abljemtf abljemtf2 = (abljemtf)component;
                        if (abljemtf2.ax == 0) {
                            int a4 = this.a7;
                            abljemtf2.av = (abljemtf2.ac - 1) * this.a7 - a4 / 2 + this.bb + this.dl;
                            abljemtf2.aw = (abljemtf2.ab - 1) * this.a6 + this.a5 + this.bc + this.dm;
                            final int ax = abljemtf2.af * this.a7 + a4;
                            if (this.i.bx) {
                                abljemtf2.ax = ax;
                            }
                            else {
                                abljemtf2.ax = abljemtf2.af * this.a7;
                                if (abljemtf2.af == 1 && this.a4 < 15) {
                                    final abljemtf abljemtf3 = abljemtf2;
                                    abljemtf3.ax += this.a7 * 3 / 2 + this.i.bz;
                                }
                                else {
                                    final abljemtf abljemtf4 = abljemtf2;
                                    abljemtf4.ax += this.a7 * 5 / 2;
                                }
                                if (abljemtf2.af == 1 && this.a4 == 15) {
                                    final abljemtf abljemtf5 = abljemtf2;
                                    ++abljemtf5.ax;
                                }
                                if (abljemtf2.af == 2 && this.a4 == 12) {
                                    final abljemtf abljemtf6 = abljemtf2;
                                    abljemtf6.ax += 2;
                                }
                                final abljemtf abljemtf7 = abljemtf2;
                                abljemtf7.av -= (abljemtf2.ax - ax) / 2;
                            }
                            abljemtf2.ay = this.a6;
                            if (this.u == 1) {
                                if (this.a4 < 13) {
                                    final abljemtf abljemtf8 = abljemtf2;
                                    abljemtf8.aw -= 2;
                                    final abljemtf abljemtf9 = abljemtf2;
                                    abljemtf9.ay += 4;
                                }
                                else {
                                    final abljemtf abljemtf10 = abljemtf2;
                                    --abljemtf10.aw;
                                    final abljemtf abljemtf11 = abljemtf2;
                                    abljemtf11.ay += 2;
                                }
                            }
                            else if (!this.z && this.ap != null) {
                                final abljemtf abljemtf12 = abljemtf2;
                                abljemtf12.ay -= this.ap.z;
                                final abljemtf abljemtf13 = abljemtf2;
                                abljemtf13.aw += this.ap.z;
                            }
                            if (abljemtf2.z != null) {
                                final int n52;
                                if ((n52 = 1 + abljemtf2.z.e - abljemtf2.aw) > 0) {
                                    final abljemtf abljemtf14 = abljemtf2;
                                    abljemtf14.ay -= n52;
                                    final abljemtf abljemtf15 = abljemtf2;
                                    abljemtf15.aw += n52;
                                    font2 = this.ao;
                                }
                                final int n53;
                                if ((n53 = 1 + abljemtf2.z.f - abljemtf2.av) > 0) {
                                    final abljemtf abljemtf16 = abljemtf2;
                                    abljemtf16.ax -= n53;
                                    final abljemtf abljemtf17 = abljemtf2;
                                    abljemtf17.av += n53;
                                    a4 -= n53;
                                }
                                final int n54;
                                if ((n54 = 1 + abljemtf2.aw + abljemtf2.ay - 1 - abljemtf2.z.g) > 0) {
                                    final abljemtf abljemtf18 = abljemtf2;
                                    abljemtf18.ay -= n54;
                                    font2 = this.ao;
                                }
                                final int n55;
                                if ((n55 = 1 + abljemtf2.av + abljemtf2.ax - 1 - abljemtf2.z.h) > 0) {
                                    final abljemtf abljemtf19 = abljemtf2;
                                    abljemtf19.ax -= n55;
                                    a4 -= n55;
                                }
                            }
                            abljemtf2.a(abljemtf2.av, abljemtf2.aw, abljemtf2.ax, abljemtf2.ay, this.a7, this.a6);
                            if (this.i.cm) {
                                abljemtf2.ao = a4;
                            }
                            abljemtf2.a(font2);
                            if (abljemtf2.ak != 80) {
                                vector.addElement((CommandStatusPanel)abljemtf2);
                            }
                        }
                    }
                    else if (component instanceof abljemob) {
                        final abljemob abljemob = (abljemob)component;
                        if (abljemob.h == 0) {
                            abljemob.f = (abljemob.d - 1 + 2) * this.a7 + this.bb - this.a7 - 2;
                            if (abljemob.m != 1) {
                                final abljemob abljemob2 = abljemob;
                                abljemob2.f -= this.a7 * (abljemob.m - 1);
                                if (abljemob.f < this.bb) {
                                    abljemob.f = this.bb;
                                }
                            }
                            if (this.a4 <= 8) {
                                final abljemob abljemob3 = abljemob;
                                ++abljemob3.f;
                            }
                            if (this.a4 <= 5) {
                                final abljemob abljemob4 = abljemob;
                                ++abljemob4.f;
                            }
                            if (this.a4 <= 4) {
                                final abljemob abljemob5 = abljemob;
                                ++abljemob5.f;
                            }
                            final int n56 = (!this.z && this.ap != null) ? this.ap.z : 0;
                            abljemob.g = (abljemob.c - 1) * this.a6 + this.a5 + this.bc + this.dm;
                            final abljemob abljemob6 = abljemob;
                            abljemob6.g += n56;
                            abljemob.h = h;
                            abljemob.i = i;
                            if (abljemob.i > this.a6 && this.i.ef != '0') {
                                abljemob.h = abljemob.h * this.a6 / abljemob.i;
                                abljemob.i = this.a6;
                            }
                            final abljemob abljemob7 = abljemob;
                            abljemob7.f += this.a7 - abljemob.h;
                            final abljemob abljemob8 = abljemob;
                            abljemob8.g += (this.a6 - n56 - abljemob.i) / 2;
                            if (abljemob.e == 4) {
                                final abljemob abljemob9 = abljemob;
                                abljemob9.f -= this.a7;
                            }
                            abljemob.reshape(abljemob.f, abljemob.g, abljemob.h, abljemob.i);
                            vector.addElement((CommandStatusPanel)abljemob);
                        }
                    }
                    else if (component instanceof EmuButton) {
                        final EmuButton emuButton = (EmuButton)component;
                        if (emuButton.e == 0) {
                            int a5 = this.a7;
                            int a6 = this.a6;
                            final int n57 = (!this.z && this.ap != null) ? this.ap.z : 0;
                            try {
                                final int width = emuButton.g.getWidth(null);
                                if (width > 0) {
                                    a5 = width;
                                }
                                final int height = emuButton.g.getHeight(null);
                                if (height > 0) {
                                    a6 = height;
                                }
                            }
                            catch (Throwable t5) {}
                            emuButton.c = (emuButton.b - 1) * this.a7 + this.bb + this.dl;
                            emuButton.d = (emuButton.a - 1) * this.a6 + this.a5 + this.bc + this.dm;
                            final EmuButton emuButton2 = emuButton;
                            emuButton2.d += n57;
                            emuButton.e = a5;
                            emuButton.f = a6;
                            if (emuButton.f > this.a6) {
                                emuButton.e = emuButton.e * this.a6 / emuButton.f;
                                emuButton.f = this.a6;
                            }
                            final EmuButton emuButton3 = emuButton;
                            emuButton3.c += (this.a7 - emuButton.e) / 2;
                            final EmuButton emuButton4 = emuButton;
                            emuButton4.d += (this.a6 - n57 - emuButton.f) / 2;
                            emuButton.reshape(emuButton.c, emuButton.d, emuButton.e, emuButton.f);
                            vector.addElement((CommandStatusPanel)emuButton);
                        }
                    }
                }
            }
        }
        if (this.c7 != null && this.c7.c != 0) {
            vector.addElement((CommandStatusPanel)this.c7);
        }
        for (final Component component2 : array2) {
            if (component2 != null && component2 instanceof abljemfb) {
                final abljemfb abljemfb = (abljemfb)component2;
                if (abljemfb.h == 0) {
                    abljemfb.setFont(this.ao);
                    if (abljemfb.e > ' ' && abljemfb.k != null) {
                        final abljemtf k5 = abljemfb.k;
                        abljemfb.h = font_metrics.a(this.getFontMetrics(abljemfb.getFont()), this.i.fd).a(abljemfb.getLabel()) + this.a7;
                        abljemfb.g = k5.aw;
                        abljemfb.i = k5.getSize().height;
                        if (abljemfb.e == 'L') {
                            abljemfb.f = k5.av - abljemfb.h - 1;
                            final abljemfb abljemfb2 = abljemfb;
                            ++abljemfb2.g;
                            final abljemfb abljemfb3 = abljemfb;
                            abljemfb3.i -= 2;
                        }
                        else {
                            abljemfb.f = k5.av + k5.ax + 1;
                            final abljemfb abljemfb4 = abljemfb;
                            --abljemfb4.i;
                        }
                        abljemfb.d = abljemfb.h / this.a7;
                        abljemfb.b = k5.ab;
                        if (abljemfb.e == 'L') {
                            abljemfb.c = k5.ac - abljemfb.d;
                        }
                        else {
                            abljemfb.c = k5.ac + k5.af;
                        }
                    }
                    else {
                        abljemfb.f = this.bb + (abljemfb.c - 1) * this.a7 - 1 + this.dl;
                        abljemfb.h = this.a7 * (abljemfb.d + 1);
                        abljemfb.i = this.a6 - 1;
                        abljemfb.g = (abljemfb.b - 1) * this.a6 + this.a5 + this.bc + this.dm + 1;
                        if (!this.z && this.ap != null) {
                            final abljemfb abljemfb5 = abljemfb;
                            abljemfb5.i -= this.ap.z;
                            final abljemfb abljemfb6 = abljemfb;
                            abljemfb6.g += this.ap.z;
                        }
                        if (abljemfb.b > this.i.et) {
                            final abljemfb abljemfb7 = abljemfb;
                            abljemfb7.g += this.bf + 1;
                            final abljemfb abljemfb8 = abljemfb;
                            abljemfb8.f -= this.a7 / 2;
                        }
                    }
                    abljemfb.reshape(abljemfb.f, abljemfb.g, abljemfb.h, abljemfb.i);
                    vector.addElement((CommandStatusPanel)abljemfb);
                }
            }
        }
        this.i.fy = this.a(this.i.fy, 2000L);
        if (this.i.fy != null) {
            abljem.d("Embedded image(s) did not arrive - display may be incomplete");
            this.i.fy = null;
        }
        ib.drawImage(this.ia, 0, 0, this);
        while (vector.size() > 0) {
            ((jemTabPanel)vector.elementAt(0)).show();
            vector.removeElementAt(0);
        }
        for (int n59 = 0; n59 < this.i.ga; ++n59) {
            if (!this.i.f1[n59].isVisible() && this.i.f1[n59].a() > 0 && this.i.f1[n59].b() > 0) {
                this.i.f9[n59] = (this.i.f4[n59] - this.i.f2[n59] + 1) * this.a6;
                this.i.f8[n59] = (this.i.f5[n59] - this.i.f3[n59] + 1) * this.a7;
                this.i.f6[n59] = (this.i.f3[n59] - 1) * this.a7 + this.bb + this.dl;
                this.i.f7[n59] = this.bc + (this.i.f2[n59] - 1) * this.a6 + this.dm + this.dl;
                this.i.f1[n59].c = this.i.f8[n59];
                this.i.f1[n59].d = this.i.f9[n59];
                final int n60 = this.i.f8[n59] * 1000 / this.i.f1[n59].a();
                final int n61 = this.i.f9[n59] * 1000 / this.i.f1[n59].b();
                if (n60 >= 998 && n61 >= 998) {
                    this.i.f1[n59].d = this.i.f1[n59].b();
                    this.i.f1[n59].c = this.i.f1[n59].a();
                }
                else if (n60 < n61 - 2 || n60 > n61 + 2) {
                    if (n60 < n61) {
                        this.i.f1[n59].d = this.i.f9[n59] * n60 / n61;
                    }
                    else {
                        this.i.f1[n59].c = this.i.f8[n59] * n61 / n60;
                    }
                }
                this.i.f1[n59].reshape(this.i.f6[n59], this.i.f7[n59], this.i.f8[n59], this.i.f9[n59]);
                this.i.f1[n59].show();
            }
        }
        if (!this.i.cj) {
            this.b(ib);
        }
        if (this.i.i == 0 || this.i.i == this.i.j.p) {
            if (!this.eg) {
                this.ay();
            }
            this.a("I", 'P');
        }
        if (!this.i.ew && this.d1 == null) {
            try {
                final int n62 = this.bb + 2 + this.dl;
                final int n63 = this.bc - 2 + this.dm;
                (this.d1 = new abljemtc()).setBackground(this.bx);
                this.d1.reshape(n62, n63, 0, 0);
                this.d1.hide();
                this.add(this.d1);
                this.d1.show();
            }
            catch (Throwable t6) {}
        }
    }
    
    private MediaTracker a(final MediaTracker mediaTracker, final long n) {
        if (mediaTracker == null) {
            return null;
        }
        boolean waitForAll;
        try {
            waitForAll = mediaTracker.waitForAll(n);
        }
        catch (InterruptedException ex) {
            waitForAll = false;
        }
        if (waitForAll) {
            return null;
        }
        return mediaTracker;
    }
    
    private void a(final Graphics graphics, final Color color) {
        utils.a(graphics, color);
    }
    
    private void a(final Graphics graphics, Color white, final int n, final int n2, final int n3, final int n4) {
        if (white == null) {
            white = Color.white;
        }
        graphics.setColor(white);
        if (graphics == this.ib) {
            graphics.clearRect(n, n2, n3, n4);
            graphics.fillRect(n, n2, n3, n4);
        }
        else if (white.equals(this.bx)) {
            graphics.clearRect(n, n2, n3, n4);
        }
        else {
            graphics.fillRect(n, n2, n3, n4);
        }
    }
    
    private void ay() {
        this.o(0);
        this.eg = true;
        this.eu = this.i.e5;
        this.ev = this.i.e6;
        this.a7();
    }
    
    private void a(final Graphics graphics, final edge edge, final int n, final int n2, final int n3, final int n4) {
        utils.a(graphics, edge, n, n2, n3, n4);
    }
    
    private void a(final Graphics graphics, final int n) {
        int n2 = this.i.fi[n];
        int es = this.i.fj[n] - 1;
        if (es < 1) {
            es = this.i.es;
            --n2;
        }
        int n3 = this.i.fl[n] - 1;
        if (n3 < 0) {
            n3 = 0;
        }
        this.a(graphics, 32, '0', null, null, null, es, n3, 1, (es - 1) * this.a7 + this.bb, this.bc + n2 * this.a6);
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, int n4, final int n5, final int n6) {
        final int n7 = n3 + n4;
        Graphics graphics2 = graphics;
        if (n5 <= 0 || n6 <= 0) {
            return;
        }
        if (graphics2 == null) {
            if (this.i.cj) {
                this.repaint();
                return;
            }
            graphics2 = this.getGraphics();
        }
        int n8;
        for (n8 = 0; n8 < this.i.fw && this.i.fl[n8] <= n3; ++n8) {}
        if (n8 < this.i.fw) {
            n4 = this.i.fl[n8] - n3 - 1;
        }
        this.a(graphics2, n, '0', null, null, null, n2, n3, n4, n5, n6);
        while (n8 < this.i.fw && this.i.fl[n8] < n7) {
            int n9 = this.i.fk[n8];
            int n10 = n8 + 1;
            if (this.i.fo[n8] == 32) {
                while (n10 < this.i.fw && this.i.fo[n10] == 32 && this.i.fp[n10] == this.i.fp[n8] && this.i.fs[n10] == null && this.i.ft[n10] == null) {
                    n9 += this.i.fk[n10++] + 1;
                }
            }
            this.a(graphics2, n8);
            if (this.i.fm[n8] == 0) {
                this.i.fm[n8] = (this.i.fj[n8] - 1) * this.a7 + this.bb;
                this.i.fn[n8] = this.bc + this.i.fi[n8] * this.a6;
            }
            this.a(graphics2, this.i.fo[n8], '0', null, null, null, this.i.fj[n8], this.i.fl[n8], n9, this.i.fm[n8], this.i.fn[n8]);
            n8 = n10 - 1;
            ++n8;
        }
    }
    
    public void a(final Graphics graphics, final int n, final char c, final Boolean b, final Color color, final Color color2, final int n2, int n3, int i, int bb, int n4) {
        Graphics graphics2 = graphics;
        if (i < 1) {
            return;
        }
        if (graphics2 == null) {
            if (this.i.cj) {
                this.repaint();
                return;
            }
            graphics2 = this.getGraphics();
        }
        if ((n & 0x7) == 0x7 && !this.i.bu) {
            return;
        }
        boolean booleanValue = (n & 0x2) != 0x0 && this.ca;
        if (b != null) {
            booleanValue = b;
        }
        if (booleanValue) {
            graphics2.setFont(this.an);
        }
        else {
            graphics2.setFont(this.y);
        }
        Color b2 = this.b(n);
        Color a = this.a(n);
        if (this.i.i > 0 && color2 != null) {
            b2 = color2;
        }
        if (this.i.i > 0 && color != null) {
            a = color;
        }
        int es = this.i.es + 1 - n2;
        int n5 = 1000;
        while (i > 0) {
            if (n5-- < 0) {
                abljem.d("jem error 31");
                break;
            }
            final int n6 = bb + this.dv;
            final int n7 = n4 + this.dw;
            if (es > i) {
                es = i;
            }
            if (es >= 1) {
                int n8 = n7 - this.a6 + 2 + ((this.a4 == 12) ? 0 : 2) + 1;
                if (this.a4 <= 10) {
                    --n8;
                }
                if (this.a4 <= 8) {
                    --n8;
                }
                if (this.a4 <= 5) {
                    --n8;
                }
                final int n9 = n7 - this.a6 + this.a5;
                int n10 = -1;
                if ((this.i.i == 0 && (this.i.bu || !a.equals(this.bx))) || (this.i.i > 0 && color != null)) {
                    final int n11 = (!this.z && this.ap != null) ? this.ap.z : 0;
                    final int n12 = n9 + (this.bm ? 0 : n11);
                    final int n13 = this.a6 - (this.bm ? 0 : n11);
                    this.a(graphics2, a, n6, n12, es * this.a7, n13);
                    if (this.es != null && this.et >= n3 && this.et < n3 + es) {
                        n10 = n6 + (this.et - n3) * this.a7;
                        this.a(graphics2, this.b5, n10, n12, this.a7, 3);
                        if (n13 > 3) {
                            this.a(graphics2, this.b5, n10, n12 + 3, this.er ? (this.a7 / 3) : this.a7, n13 - 3);
                        }
                    }
                }
                if (!this.hn) {
                    this.a(n3, es, n6, n9, a);
                }
                graphics2.setColor(b2);
                boolean b3 = false;
                if ((n & 0x4) != 0x0 || (this.bs && (n & 0x38) == 0x30) || c == '2') {
                    if (this.i.bu || c == '1' || c == '2') {
                        b3 = true;
                    }
                    else if (this.i.i == 0 && this.bt) {
                        b3 = true;
                    }
                }
                if (this.b8 != null) {
                    final String trim = new String(this.i.el, 0, n3, es).trim();
                    if (trim.length() > 60) {
                        int n14;
                        for (n14 = 0; n14 < abljema.ix.length && !abljema.ix[n14].startsWith(trim); ++n14) {}
                        if (n14 < abljema.ix.length) {
                            int n15;
                            for (n15 = n3; n15 < n3 + es && this.i.el[n15] == 32; ++n15) {}
                            int n16;
                            for (n16 = n15; n16 < n3 + es && this.i.el[n16] != 32; ++n16) {}
                            final int n17 = n6 + (n15 - n3) * this.a7;
                            final int n18 = n6 + (n16 - n3) * this.a7 - 1;
                            final int n19 = n7 - this.a8 + this.a6 / 2;
                            final int n20 = n19 + 1;
                            final int n21 = n18 + this.a7;
                            if (this.b8[0] != null) {
                                graphics2.setColor(this.b8[0]);
                                graphics2.drawLine(n17, n19, n21, n19);
                            }
                            if (this.b8[1] != null) {
                                graphics2.setColor(this.b8[1]);
                                graphics2.drawLine(n17, n20, n21, n20);
                            }
                            return;
                        }
                    }
                }
                final int n22 = n7 - ((this.a4 < 10) ? 2 : 3);
                if (!this.z && es == 1 && this.i.el[n3] == 32) {
                    return;
                }
                if (this.z && (!this.i.cq || !this.a9)) {
                    this.b(graphics2, this.i.el, n3, es, n6, n22);
                    if (b3 && es <= this.eq.length) {
                        this.b(graphics2, this.eq, 0, es, n6, n22);
                    }
                }
                else if (!this.z && this.ap != null && this.ap.w == '1') {
                    this.a(graphics2, this.i.el, n3, es, n6, n22, this.a7, b3);
                }
                else {
                    final font_metrics a2 = font_metrics.a(graphics2.getFontMetrics(), this.i.fd);
                    this.a(this.i.el, n3, es, n2);
                    for (int j = 0; j < this.hw; ++j) {
                        final int n23 = this.hz[j] - n3;
                        final int n24 = this.h0[j];
                        final int n25 = this.h1[j];
                        final String s = this.h2[j];
                        int n26 = n6 + n23 * this.a7;
                        final int n27 = n22;
                        if (n25 > 0 && n25 <= s.length()) {
                            n26 = n26 + n25 * this.a7 - a2.a(s.substring(0, n25));
                        }
                        if (s.length() == 1) {
                            switch (s.charAt(0)) {
                                case '-':
                                case 'O':
                                case 'X': {
                                    n26 += (this.a7 - a2.a(s)) / 2;
                                    break;
                                }
                            }
                        }
                        graphics2.drawString(s, n26, n27);
                        if (b3) {
                            final int n28 = n26;
                            final int n29 = n26 + a2.a(s);
                            final int n30 = n27 + 2;
                            if (n29 > n28) {
                                graphics2.drawLine(n28, n30, n29, n30);
                            }
                        }
                    }
                }
                if (n10 >= 0 && !this.er) {
                    graphics2.setColor(this.b4);
                    this.b(graphics2, this.i.el, this.et, 1, n10, n22);
                }
            }
            n4 += this.a6;
            bb = this.bb;
            n3 += es;
            i -= es;
            es = this.i.es;
        }
        graphics2.setColor(this.bz);
    }
    
    public void a(final Graphics graphics, final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        final font_metrics a = font_metrics.a(graphics.getFontMetrics(), this.i.fd);
        final String s = new String(array, 0, n, n2);
        this.a(graphics, a, n5, s, n3, n4);
        if (b) {
            final int n6 = n3 + n5 * s.length();
            final int n7 = n4 + 2;
            if (n6 > n3) {
                graphics.drawLine(n3, n7, n6, n7);
            }
        }
    }
    
    private void a(final Graphics graphics, final font_metrics font_metrics, final int n, final String s, int b, final int n2) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n3 = 0;
        for (int i = 0; i <= length; ++i) {
            final char c;
            if (i == length || ((c = charArray[i]) != ' ' && !Character.isLetter(c))) {
                b = this.b(graphics, font_metrics, n, s.substring(n3, i), b, n2);
                char c2;
                while (i < length && (c2 = charArray[i]) != ' ' && !Character.isLetter(c2)) {
                    int n4 = b;
                    if ((c2 == '.' || c2 == ',') && i < length - 1 && Character.isDigit(charArray[i + 1])) {
                        n4 += (font_metrics.a('0') - font_metrics.a(c2) + 1) / 2;
                    }
                    graphics.drawChars(charArray, i, 1, n4, n2);
                    b += n;
                    ++i;
                }
                n3 = i;
            }
        }
    }
    
    private int b(final Graphics graphics, final font_metrics font_metrics, final int n, final String s, final int n2, final int n3) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        final int n4 = n * length - font_metrics.a(s);
        int n5 = n2;
        int i = 0;
        int n6 = n2;
        int n7 = 0;
        while (i < length) {
            final int n8 = n7;
            graphics.drawChars(charArray, i, 1, n6, n3);
            final int n9 = n6 + font_metrics.a(charArray[i]);
            n7 = n4 * (i + 1) / length;
            n6 = (n5 = n9 + (n7 - n8));
            ++i;
        }
        return n5;
    }
    
    private void a(final byte[] array, final int n, final int n2, final int n3) {
        this.hw = 0;
        this.hx = n - (n3 - 1);
        this.hy = this.hx + this.i.es;
        try {
            if (this.ap == null || this.ap.ac == '0') {
                this.a(array, n, n2, true);
            }
            else {
                this.a(array, n, n2, 0, false);
            }
        }
        catch (Throwable t) {
            this.hw = 0;
            t.printStackTrace();
            abljem.d("set_ssp failed - paint incomplete");
        }
    }
    
    private void a(final byte[] array, final int n, final int n2, int n3, final boolean b) {
        final int n4 = n + n2;
        int n5 = 0;
        if (n2 < 1) {
            return;
        }
        if (n3++ > 200) {
            abljem.d("set_ssp calls too deep - paint incomplete");
            return;
        }
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        final char ad = this.ap.ad;
        final char ae = this.ap.ae;
    Label_0792:
        for (int i = n; i < n4; ++i) {
            final char c = (char)array[i];
            if ((c >= '0' && c <= '9') || c == ad || c == ae || c == '-') {
                int j;
                for (j = i + 1; j < n4; ++j) {
                    final char c2 = (char)array[j];
                    if ((c2 < '0' || c2 > '9') && c2 != ad && c2 != ae && c2 != '-') {
                        break;
                    }
                }
                if (i < j) {
                    int k;
                    for (k = i; k < j; ++k) {
                        final char c3 = (char)array[k];
                        if (c3 >= '0' && c3 <= '9') {
                            break;
                        }
                    }
                    int l;
                    for (l = j; l > k; --l) {
                        final char c4 = (char)array[l - 1];
                        if (c4 >= '0' && c4 <= '9') {
                            break;
                        }
                    }
                    i = j - 1;
                    if (k < l) {
                        for (int n9 = k; n9 < l; ++n9) {
                            final char c5 = (char)array[n9];
                            if (c5 == '-') {
                                continue Label_0792;
                            }
                            if (c5 == ae) {
                                if (n5 > 0) {
                                    continue Label_0792;
                                }
                                n5 = n9;
                            }
                        }
                        n6 = -1;
                        final String[] af = this.ap.af;
                        if (af != null) {
                            for (int n10 = 0; n10 < af.length; ++n10) {
                                final String s = af[n10];
                                final int length = s.length();
                                int n11 = length - 1;
                                for (int n12 = k - 1; n11 >= 0 && ((n12 >= this.hx) ? ((char)array[n12]) : ' ') == s.charAt(n11); --n11, --n12) {}
                                if (n11 < 0) {
                                    if (n5 == 0 && length > 0 && s.charAt(length - 1) == ae) {
                                        n5 = k - 1;
                                    }
                                    n6 = k - length;
                                    break;
                                }
                            }
                        }
                        if (n6 < 0) {
                            if (k == this.hx) {
                                n6 = k;
                            }
                            else if (k > 0 && array[k - 1] == 32) {
                                n6 = k;
                            }
                        }
                        if (n6 >= 0) {
                            n7 = -1;
                            final String[] ag = this.ap.ag;
                            if (ag != null) {
                                for (int n13 = 0; n13 < ag.length; ++n13) {
                                    final String s2 = ag[n13];
                                    final int length2 = s2.length();
                                    int n14 = 0;
                                    for (int n15 = l; n14 < length2 && ((n15 < this.hy) ? ((char)array[n15]) : ' ') == s2.charAt(n14); ++n14, ++n15) {}
                                    if (n14 >= length2) {
                                        if (n5 == 0 && length2 > 0 && s2.charAt(0) == ae) {
                                            n5 = l;
                                        }
                                        n7 = l + length2;
                                        break;
                                    }
                                }
                            }
                            if (n7 < 0) {
                                if (l == this.hy) {
                                    n7 = l;
                                }
                                else if (l + 1 < array.length && array[l] == 32) {
                                    n7 = l;
                                }
                            }
                            if (n7 >= 0) {
                                if (n6 < n) {
                                    n6 = n;
                                }
                                if (n7 > n4) {
                                    n7 = n4;
                                }
                                n8 = n7 - n6;
                                if (n8 >= 1) {
                                    if (n5 == 0) {
                                        n5 = l;
                                    }
                                    if (n5 > n7) {
                                        n5 = n7;
                                    }
                                    if (n5 < n6) {
                                        n5 = n6;
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (n8 < 1) {
            this.a(array, n, n2, n3 == 1);
            return;
        }
        this.a(array, n, n6 - n, false);
        this.a(n6, n8, n5 - n6, new String(array, 0, n6, n8));
        this.a(array, n7, n4 - n7, n3, b);
    }
    
    private void a(final byte[] array, final int n, final int n2, final boolean b) {
        if (n2 < 1) {
            return;
        }
        final StringBuffer sb = new StringBuffer(new String(array, 0, n, n2));
        final String s = (this.ap != null) ? this.ap.x : null;
        final String s2 = (this.ap != null) ? this.ap.y : null;
        if (s2 != null) {
            for (int i = 0; i < n2; ++i) {
                if (sb.charAt(i) == '.' && (i <= 0 || s2.indexOf(sb.charAt(i - 1)) >= 0) && (i + 1 >= n2 || s2.indexOf(sb.charAt(i + 1)) >= 0)) {
                    sb.setCharAt(i, ' ');
                }
            }
        }
        if (s != null) {
            for (int j = 0; j < n2; ++j) {
                if (s.indexOf(sb.charAt(j)) >= 0) {
                    sb.setCharAt(j, ' ');
                }
            }
        }
        final String string = sb.toString();
        int n3 = 200;
    Label_0544:
        for (int k = 0, n4 = 0; k < n2; k = n4) {
            if (k == 0 && string.charAt(k) == ' ') {
                while (++n4 < n2 && string.charAt(n4) == ' ') {}
                if (n4 >= n2) {
                    if (b && n2 > 0) {
                        this.a(n, 1, 0, abljema.iv.substring(0, n2));
                    }
                    return;
                }
            }
            else {
                switch (string.charAt(k)) {
                    case '-':
                    case 'O':
                    case 'X': {
                        final int n5 = k + 1;
                        if (n5 < n2 && string.charAt(n5) == ' ') {
                            n4 = n5;
                            while (++n4 < n2 && string.charAt(n4) == ' ') {}
                            break;
                        }
                        break;
                    }
                }
                if (n4 == k) {
                    while (++n4 < n2) {
                        if (--n3 < 0) {
                            abljem.d("Proportional font painting looped - paint incomplete");
                            break Label_0544;
                        }
                        if (string.charAt(n4) == ' ' && n4 > k && string.charAt(n4 - 1) == ' ') {
                            while (++n4 < n2) {
                                if (string.charAt(n4) != ' ') {
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                final String substring = string.substring(k, n4);
                String trim = substring.trim();
                if (trim.length() == 0) {
                    trim = substring;
                }
                if (!this.a(n + k, n4 - k, 0, trim)) {
                    break;
                }
            }
        }
    }
    
    private boolean a(final int n, final int n2, final int n3, final String s) {
        if (this.hw >= this.hz.length) {
            abljem.d("ssp array full - paint incomplete");
            return false;
        }
        this.hz[this.hw] = n;
        this.h0[this.hw] = n2;
        this.h1[this.hw] = n3;
        this.h2[this.hw] = s;
        ++this.hw;
        return true;
    }
    
    private void b(final Graphics graphics, final byte[] array, final int n, final int n2, final int n3, final int n4) {
        if (this.a9) {
            a(graphics, array, n, n2, n3, n4);
            return;
        }
        int n5 = n;
        for (int i = n2, n6 = n3; i > 0; --i, n6 += this.a7) {
            a(graphics, array, n5, 1, n6, n4);
            ++n5;
        }
    }
    
    static void a(final Graphics graphics, final byte[] array, final int n, final int n2, final int n3, final int n4) {
        graphics.drawString(new String(array, 0, n, n2), n3, n4);
    }
    
    private void b(final int n, final int n2, final int n3, final int n4) {
        this.a0();
        this.reshape(n, n2, n3 + this.d5.left + this.d5.right, n4 + this.d5.top + this.d5.bottom);
    }
    
    private void d(final int n, final int n2) {
        if (this.ae) {
            return;
        }
        this.a0();
        this.resize(n + this.d5.left + this.d5.right, n2 + this.d5.top + this.d5.bottom);
    }
    
    private void az() {
        this.p = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.q = Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    
    private void a0() {
        final Insets insets = this.insets();
        if (insets.left == 0 && insets.right == 0 && insets.top == 0 && insets.bottom == 0) {
            return;
        }
        if (insets.top < 0) {
            insets.top = 0;
        }
        if (insets.left < 0) {
            insets.left = 0;
        }
        if (insets.bottom < 0) {
            insets.bottom = 0;
        }
        if (insets.right < 0) {
            insets.right = 0;
        }
        this.d5 = insets;
    }
    
    private Dimension a1() {
        final Rectangle p = this.p();
        return new Dimension(p.width, p.height);
    }
    
    public Rectangle p() {
        final Rectangle bounds = this.bounds();
        this.a0();
        return new Rectangle(this.d5.left, this.d5.top, bounds.width - this.d5.left - this.d5.right, bounds.height - this.d5.top - this.d5.bottom);
    }
    
    private void a2() {
        this.ab = 0;
        this.ac = 0;
        this.ah();
        if (this.e4 != null) {
            this.e4.disable();
        }
        if (this.i.g9 != null) {
            this.i.g9.disable();
        }
        if (this.dg != null) {
            this.dg.hide();
        }
        if (this.dh != null) {
            this.dh.hide();
        }
        if (this.di != null) {
            this.di.hide();
        }
        if (this.cq != null) {
            this.cq.hide();
        }
        if (this.cr != null) {
            this.cr.hide();
        }
        if (this.cs != null) {
            this.cs.hide();
        }
        if (this.ct != null) {
            this.ct.hide();
        }
        if (this.cu != null) {
            this.cu.hide();
        }
        if (this.cv != null) {
            this.cv.hide();
        }
        if (this.cw != null) {
            this.cw.hide();
        }
        if (this.cx != null) {
            this.cx.hide();
        }
        if (this.cz != null) {
            for (int i = 0; i < this.cz.length; ++i) {
                this.cz[i].hide();
            }
        }
        if (this.c7 != null) {
            this.c7.hide();
        }
        if (this.aq != null) {
            this.aq.hide();
        }
        this.e7 = true;
    }
    
    public void a(final String s, final String s2, final String s3, final String s4) {
        this.c(abljema.d(s), abljema.d(s2), abljema.d(s3), abljema.d(s4));
        this.e9 = true;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.ad = true;
        this.c(n, n2, n3, n4);
    }
    
    private void c(final int n, final int n2, final int n3, final int n4) {
        if (n3 < 20 || n4 < 20) {
            this.move(n, n2);
        }
        else {
            this.d3 = 0;
            this.reshape(n, n2, n3, n4);
            this.ee = false;
            this.ef = true;
        }
    }
    
    public void q() {
        this.a3();
    }
    
    private void a3() {
        this.d(true);
    }
    
    private void a4() {
        this.d(false);
    }
    
    private void d(final boolean b) {
        synchronized (this.k) {
            this.e(b);
        }
        // monitorexit(this.k)
    }
    
    private void e(final boolean b) {
        if (this.af == 0) {
            return;
        }
        if (this.i.i == 0 && this.i.es == 132 && this.i.et == 27) {
            this.y = new_font.a(this.ah[this.ac], 0, this.ag[this.ac]);
        }
        else {
            this.y = new_font.a(this.ah[this.ab], 0, this.ag[this.ab]);
        }
        this.an = new_font.a(this.y.getName(), 1, this.y.getSize());
        this.ao = new_font.a(this.y.getName(), 0, this.y.getSize() - 2);
        this.a(this.getGraphics(), this.i.et, this.i.es, b);
    }
    
    private void f(final boolean b) {
        if (this.ff != null && this.ff.isVisible()) {
            this.ff.hide();
        }
        if (this.fg != null && this.fg.isVisible()) {
            this.fg.hide();
        }
        if (abljemgt.br != null && abljemgt.br.isVisible()) {
            abljemgt.br.hide();
        }
        if (this.hi != null && this.hi.isVisible()) {
            this.f6.hide();
            this.hi.hide();
        }
        if (this.f6 != null && this.f6.isVisible()) {
            this.f6.hide();
        }
        if (this.dg != null) {
            this.dg.show();
        }
        if (this.dh != null) {
            this.dh.show();
        }
        if (this.di != null) {
            this.di.show();
        }
        if (this.cq != null && this.cq.location().x != 0) {
            this.cq.show();
        }
        if (this.cr != null && this.cr.location().x != 0) {
            this.cr.show();
        }
        if (this.cs != null && this.cs.location().x != 0) {
            this.cs.show();
        }
        if (this.ct != null && this.ct.location().x != 0) {
            this.ct.show();
        }
        if (this.cu != null && this.cu.location().x != 0) {
            this.cu.show();
        }
        if (this.cw != null && this.cw.location().x != 0) {
            this.cw.show();
        }
        if (this.cx != null && this.cx.location().x != 0) {
            this.cx.show();
        }
        if (this.cz != null) {
            for (int i = 0; i < this.cz.length; ++i) {
                if (this.cz[i].g != 0) {
                    this.cz[i].show();
                }
            }
        }
        if (this.c7 != null) {
            this.c7.show();
        }
        if (this.aq != null) {
            this.aq.show();
        }
        if (this.ef) {
            this.b(this.a1());
        }
        this.a3();
        this.g(this.a4);
        if (this.e4 != null) {
            this.e4.enable();
        }
        if (this.i.g9 != null) {
            this.i.g9.enable();
        }
        this.e5 = null;
        this.e7 = false;
        if (this.bounds() != null) {
            final Dimension dimension = new Dimension(this.d3, this.d4);
            final Dimension a1 = this.a1();
            this.d3 = a1.width;
            this.d4 = a1.height;
            this.az();
            if (this.i.i == 0 && !this.a5() && !this.a(dimension) && this.af > 0) {
                if (!this.i.bq) {
                    while (this.ac > 0) {
                        if (this.aj[this.ac].width <= this.ai[this.ab].width) {
                            break;
                        }
                        --this.ac;
                    }
                    while (this.ac > 0 && this.aj[this.ac].height > this.ai[this.ab].height) {
                        --this.ac;
                    }
                }
                this.d3 = Math.max((this.ab == 0) ? this.d3 : this.ai[this.ab].width, (this.ac == 0) ? this.d3 : this.aj[this.ac].width);
                this.d4 = Math.max((this.ab == 0) ? this.d4 : this.ai[this.ab].height, (this.ac == 0) ? this.d4 : this.aj[this.ac].height);
                this.b(this.bounds().x, this.bounds().y, this.d3, this.d4);
            }
        }
        if (this.di != null) {
            final int n = this.d3 - this.bd - this.di.location().x + this.dl;
            final int height = this.di.size().height;
            if (this.di.size().width != n) {
                this.di.b(n, height);
                if (this.cf) {
                    this.di.a(n, height);
                }
            }
        }
        if (this.dg != null && this.dg.location().y - this.dm + this.dg.size().height != this.d4 - this.be) {
            final int width = this.dg.size().width;
            final int n2 = this.d4 - this.be - this.dg.location().y + this.dm;
            if (this.dg.size().height != n2) {
                this.dg.b(width, n2);
                if (this.cf) {
                    this.dg.a(width, n2);
                }
            }
        }
        if (this.aq != null) {
            this.aq.a(this.dl, this.d4 - this.bi + this.dm, this.d3, this.bb, this.i.es * this.a7);
        }
        if (!b) {
            this.repaint();
        }
    }
    
    private boolean a5() {
        return this.d3 == this.p || this.d4 == this.q;
    }
    
    private boolean a(final Dimension dimension) {
        return dimension.width == this.p || dimension.height == this.q;
    }
    
    private void b(final Dimension dimension) {
        this.ab = this.a(dimension, this.ai);
        this.ac = this.a(dimension, this.aj);
        this.ef = false;
    }
    
    private void a(final Graphics graphics, final Dimension dimension) {
        final font_metrics a = font_metrics.a(graphics.getFontMetrics(this.n), this.i.fd);
        final String s = "Emulation not configured for you.";
        final String s2 = "Leave this window open to process prints.";
        final Color background = this.getBackground();
        final Color color = new Color(255 - background.getRed(), 255 - background.getGreen(), 255 - background.getBlue());
        this.a(graphics, background, 0, 0, dimension.width, dimension.height);
        graphics.setColor(color);
        graphics.setFont(this.n);
        final int a2 = a.a(s);
        final int a3 = a.a(s2);
        final int n = (dimension.width - a2) / 2;
        final int n2 = (dimension.width - a3) / 2;
        final int n3 = dimension.height / 3;
        graphics.drawString(s, n, n3);
        graphics.drawString(s2, n2, n3 + a.g * 3);
    }
    
    private void a(final Graphics graphics, final Dimension dimension, final Rectangle rectangle) {
        final font_metrics a = font_metrics.a(graphics.getFontMetrics(this.n), this.i.fd);
        final String string = String.valueOf(this.x) + " column";
        final String s = "132 column";
        final int n = 20;
        final int n2 = n + a.a(string) + 20;
        final Color background = this.getBackground();
        final Color color = new Color(255 - background.getRed(), 255 - background.getGreen(), 255 - background.getBlue());
        this.a(graphics, background, 0, 0, rectangle.width, rectangle.height);
        graphics.setColor(color);
        this.b(dimension);
        int dv = this.dv;
        final int dw = this.dw;
        dv += 10;
        final int n3 = dw + (a.g + 10);
        graphics.setFont(this.n);
        graphics.drawString("Move mouse over body to redisplay details", dv, n3);
        final int n4 = n3 + (a.g + 2);
        if (this.i.bq) {
            graphics.drawString("(using font size " + this.ag[this.ab] + " for " + this.x + " column, " + this.ag[this.ac] + " for 132)", dv, n4);
        }
        else {
            graphics.drawString("(using font size " + this.ag[this.ab] + ")", dv, n4);
        }
        if (this.i.bq) {
            this.a(graphics, "Sample 132-column input text", new_font.a(this.ah[this.ac], 0, this.ag[this.ac] - 2), dv, this.a(graphics, "Sample 132-column screen text", new_font.a(this.ah[this.ac], 0, this.ag[this.ac]), dv, this.a(graphics, "Sample " + this.x + "-column input text", new_font.a(this.ah[this.ab], 0, this.ag[this.ab] - 2), dv, this.a(graphics, "Sample " + this.x + "-column screen text", new_font.a(this.ah[this.ab], 0, this.ag[this.ab]), dv, n4, 10), 2), 10), 2);
        }
        else {
            this.a(graphics, "Sample input text", new_font.a(this.ah[this.ab], 0, this.ag[this.ab] - 2), dv, this.a(graphics, "Sample screen text", new_font.a(this.ah[this.ab], 0, this.ag[this.ab]), dv, n4, 10), 2);
        }
        graphics.setFont(this.n);
        this.a(graphics, this.ai[this.ab], this.i.bq ? string : null, this.n, n);
        if (this.i.bq) {
            this.a(graphics, this.aj[this.ac], s, this.n, n2);
        }
    }
    
    private int a(final Graphics graphics, final String s, final Font font, final int n, int n2, final int n3) {
        n2 += font_metrics.a(graphics.getFontMetrics(font), this.i.fd).g + n3;
        graphics.setFont(font);
        graphics.drawString(s, n, n2);
        return n2;
    }
    
    private void a(final Graphics graphics, final Dimension dimension, final String s, final Font font, final int n) {
        if (dimension != null) {
            graphics.drawRect(this.dv, this.dw, dimension.width, dimension.height);
            if (s != null) {
                graphics.setFont(font);
                graphics.drawString(s, this.dv + n, this.dw + dimension.height - 5);
            }
        }
    }
    
    private int a(final Dimension dimension, final Dimension[] array) {
        int i;
        for (i = array.length - 1; i > 0; --i) {
            if (array[i].width <= dimension.width && array[i].height <= dimension.height) {
                return i;
            }
        }
        return i;
    }
    
    public String[] e(final String s) {
        final String[] array = new String[2];
        final StringBuffer sb = new StringBuffer();
        char c = ' ';
        int length;
        int i;
        for (length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (c != ' ') {
                if (char1 == c) {
                    c = ' ';
                }
                else {
                    sb.append(char1);
                }
            }
            else if (this.if.indexOf(char1) >= 0) {
                c = char1;
            }
            else if (char1 != ' ') {
                break;
            }
        }
        array[0] = new String(sb);
        array[1] = ((i >= length) ? "" : s.substring(i));
        return array;
    }
    
    private String[] n(final String s) {
        final String[] array = new String[2];
        final StringBuffer sb = new StringBuffer();
        array[1] = (array[0] = "");
        int length;
        int n;
        for (length = s.length(), n = 0; n < length && s.charAt(n) == ' '; ++n) {}
        int n2;
        if ((n2 = n) < length && this.if.indexOf(s.charAt(n)) < 0) {
            for (n2 = n; n2 < length && s.charAt(n2) != ' '; ++n2) {}
            sb.append(s.substring(n, n2));
            while (n2 < length && s.charAt(n2) == ' ') {
                ++n2;
            }
        }
        if (n2 < length) {
            array[1] = s.substring(n2);
        }
        if ((sb.charAt(0) == 'F' || sb.charAt(0) == 'f') && sb.length() == 2) {
            sb.insert(1, '0');
        }
        array[0] = new String(sb).toUpperCase();
        return array;
    }
    
    public boolean a(final Event event) {
        return this.e0.a(event, this.e1);
    }
    
    private boolean o(final String s) {
        return this.d(new Event(this.e0.e, 401, null), s);
    }
    
    private boolean d(final Event event, final String s) {
        event.arg = new StyleEventArg(s);
        return this.b(event);
    }
    
    public boolean b(final Event event) {
        if (event.arg instanceof abljempi) {
            final String[] e = this.e(((abljempi)event.arg).a.c);
            this.m(e[0]);
            event.arg = new StyleEventArg(e[1]);
        }
        if (event.target == this.i.j.h5 && event.id == 401 && event.key == 27) {
            this.d8 = true;
            this.c4 = false;
            this.a("P", 'W');
            this.requestFocus();
            return true;
        }
        if (!(event.arg instanceof StyleEventArg)) {
            return false;
        }
        final StyleEventArg styleEventArg = (StyleEventArg)event.arg;
        if (styleEventArg.d != null && styleEventArg.d instanceof jemScrollPanel) {
            final jemScrollPanel jemScrollPanel = (jemScrollPanel)styleEventArg.d;
            styleEventArg.d = null;
            if (this.i.e5 < jemScrollPanel.a || this.i.e5 >= jemScrollPanel.a + jemScrollPanel.d) {
                this.i.e5 = jemScrollPanel.a;
                this.i.e6 = 2;
            }
        }
        if (styleEventArg.b > 0) {
            this.i.e5 = styleEventArg.b;
            this.i.e6 = styleEventArg.c;
            final Component o = this.o(0);
            if (o == null || !(o instanceof abljemtf)) {
                return true;
            }
            this.e2 = event;
            ((abljemtf)o).requestFocus();
            return true;
        }
        else {
            if (styleEventArg.a == null || styleEventArg.a.length() <= 0) {
                return false;
            }
            final String a = styleEventArg.a;
            if (a.length() > 7 && a.startsWith("SFTSEL:")) {
                this.cy = a.substring(7);
                this.at();
                return true;
            }
            if (a.length() > 7 && a.startsWith("SFTACT:")) {
                this.c(event, a.substring(7));
                return true;
            }
            if (a.length() > 7 && a.startsWith("MBRACT:")) {
                this.a(event, a.substring(7));
                return true;
            }
            if (a.length() > 7 && a.startsWith("PDNACT:")) {
                this.b(event, a.substring(7));
                return true;
            }
            if (a.startsWith("CMC:")) {
                this.e0.b(a.substring(4));
                this.a(null, 'P');
                return true;
            }
            if (a.startsWith("LNK")) {
                this.i.j.h5.handleEvent(event);
                return true;
            }
            final String[] e2 = this.e(a);
            final String s = e2[0];
            final String[] n = this.n(e2[1]);
            String string = n[0];
            if (string.length() == 0) {
                return true;
            }
            if (string.length() != 3) {
                abljem.d("sbmkeystr not 3:" + string + ":");
                return true;
            }
            if (s.length() > 0) {
                string = String.valueOf(string) + "AFT" + s;
            }
            this.g(string);
            if (n[1].length() > 0) {
                styleEventArg.a = n[1];
                this.i.j.h7 = event;
            }
            return true;
        }
    }
    
    protected void processEvent(final AWTEvent awtEvent) {
        abljemeh.a(awtEvent);
        super.processEvent(awtEvent);
    }
    
    public boolean handleEvent(final Event event) {
        if ((event.id == 401 || event.id == 402) && (event.key == 0 || event.key == 65535)) {
            return true;
        }
        if ((event.id == 401 || event.id == 403) && abljemeh.a(event) == null) {
            return true;
        }
        if (event.target != null) {
            if (event.target == this.f7 && this.f7.isVisible()) {
                return super.handleEvent(event);
            }
            if (event.target == this.ff && this.ff.isVisible()) {
                return super.handleEvent(event);
            }
            if (event.target == abljemgt.br && abljemgt.br.isVisible()) {
                return super.handleEvent(event);
            }
            if (event.target == this.hi && this.hi.isVisible()) {
                return super.handleEvent(event);
            }
            if (event.target == this.fl) {
                return super.handleEvent(event);
            }
            if (event.target == this.i.j.h5) {
                return this.b(event);
            }
        }
        if ((event.id == 205 && event.target == this) || (event.id == 505 && event.target == this)) {
            final Dimension a1 = this.a1();
            if (a1.width > 0 && a1.height > 0 && this.e5 != null && !this.a(this.e5, a1)) {
                this.repaint();
            }
        }
        if (event.id == 501) {
            this.h3 = 0;
            if ((event.modifiers & 0x4) != 0x0) {
                this.h3 = 4;
            }
        }
        if (event.id == 502) {
            event.modifiers |= this.h3;
            this.h3 = 0;
        }
        if (abljema.b(event) && event.id != 506) {
            return super.handleEvent(event);
        }
        if (event.id == 201) {
            if (event.target == this) {
                return this.e(event);
            }
            if (event.target == this.e4) {
                this.e4.hide();
            }
            else if (event.target == this.fw) {
                this.bl();
            }
            else if (event.target == this.fu) {
                this.bk();
            }
            else if (event.target == this.fz) {
                if (this.i.bl) {
                    this.bn();
                }
                return true;
            }
        }
        if (!this.l && this.e0.a(event, this.e1)) {
            return true;
        }
        if (event.target == this) {
            switch (event.id) {
                case 1004: {
                    this.d7 = true;
                    break;
                }
                case 1005: {
                    this.d7 = false;
                    break;
                }
            }
        }
        if (event.target == this.e0.e) {
            event.target = this;
        }
        return super.handleEvent(event);
    }
    
    private boolean e(final Event event) {
        if (this.i.i > 1 && !this.ea) {
            if (this.i.j.dy[this.i.j.ag] != null) {
                this.g("ENT");
            }
            else if (this.cd != null && this.cd.length() > 0) {
                this.g(this.cd);
            }
            return super.handleEvent(event);
        }
        if (this.i.j.d0 || this.i.a1) {
            this.r();
            return super.handleEvent(event);
        }
        final String o = this.i.o();
        final String s = (o == null || !this.i.j.ae) ? null : (String.valueOf((o.length() > 2) ? "Sessions " : "Session ") + o + " active");
        if (this.i.j.a7 == 'N' || (this.i.j.a7 == 'S' && o != null)) {
            String a8 = this.i.j.a8;
            if (this.i.j.ae) {
                a8 = ((a8 == null) ? s : (String.valueOf(a8) + " (" + s + ")"));
            }
            if (a8 != null) {
                this.s(a8);
            }
            return true;
        }
        if (this.i.j.az && o != null) {
            this.j(s);
        }
        else {
            this.r();
        }
        return super.handleEvent(event);
    }
    
    public void r() {
        if (this.i.j.az) {
            if (this.i.j.bm.a(0L)) {
                abljema.a(2000L);
            }
            this.i.j.a2 = null;
        }
        this.i.j.az = false;
        this.dispose();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.hi && this.hi != null) {
            if (this.hi.a(event) == this.hk) {
                this.bf();
            }
            else {
                this.f6.hide();
            }
            return true;
        }
        if (event.target == this.ff && this.ff != null) {
            final MenuItem a = this.ff.a(event);
            if (a == this.fq) {
                this.al();
            }
            else {
                this.a(a);
            }
            return true;
        }
        if (event.target == this.gn && this.gn != null) {
            event.arg = this.gn.a(event);
            this.b(event);
            return true;
        }
        if (event.target == this.cv && this.cv != null) {
            event.arg = this.cv.a(event);
            this.b(event);
            return true;
        }
        if (event.target == this.f7 && this.f7 != null) {
            final MenuItem a2 = this.f7.a(event);
            if (a2 == this.g3) {
                event.target = this.f9;
            }
            else if (a2 == this.g2) {
                event.target = this.f8;
            }
            else if (a2 == this.g4) {
                event.target = this.ga;
            }
            else if (a2 == this.g5) {
                this.i((Event)null);
            }
            else if (a2 == this.g6) {
                event.target = this.gb;
            }
            else {
                if (a2 == this.g7) {
                    this.a((Component)null);
                    return true;
                }
                if (a2 == this.g8) {
                    event.target = this.gc;
                }
                else if (a2 == this.ha) {
                    event.target = this.gd;
                }
                else if (a2 == this.hb) {
                    event.target = this.ge;
                }
                else if (a2 == this.hc) {
                    event.target = this.gf;
                }
                else if (a2 == this.hd) {
                    event.target = this.gg;
                }
                else {
                    if (a2 != this.he) {
                        if (this.h != null) {
                            for (int i = 0; i < this.h.length; ++i) {
                                if (a2 == this.h[i]) {
                                    this.d(event, this.f[i]);
                                    return true;
                                }
                            }
                        }
                        return true;
                    }
                    event.target = this.gh;
                }
            }
        }
        if (event.target == this.d && this.d != null) {
            final MenuItem a3 = this.d.a(event);
            if (a3 == this.gj) {
                this.ak();
                this.g("F01");
            }
            else if (a3 == this.gm) {
                this.ak();
                this.g("UPP");
            }
            else {
                if (a3 != this.gl) {
                    return true;
                }
                this.ak();
                this.g("DWN");
            }
        }
        if (event.target == this.f9) {
            this.h((Event)null);
        }
        else if (event.target == this.f8) {
            this.g((Event)null);
        }
        else if (event.target == this.ga) {
            this.g("ENT");
        }
        else if (event.target == this.gb) {
            this.f((Event)null);
        }
        else if (event.target == this.gc) {
            this.i.s();
        }
        else if (event.target == this.gd) {
            this.g("HLP");
        }
        else if (event.target == this.ge) {
            this.j((Event)null);
        }
        else if (event.target == this.gf) {
            this.k((Event)null);
        }
        else if (event.target == this.gg) {
            this.v();
        }
        else if (event.target == this.gh) {
            this.c((Event)null);
        }
        else if (event.target == this.fv) {
            this.bk();
        }
        else if (event.target == this.fx) {
            this.bm();
        }
        else if (event.target == this.fy) {
            this.bl();
        }
        else if (event.target == this.f0) {
            this.bo();
        }
        else if (event.target == this.f2) {
            this.bo();
        }
        else if (event.target == this.f1) {
            this.bn();
        }
        else if (event.target == this.fm) {
            this.bh();
        }
        else if (event.target == this.fn) {
            this.bj();
        }
        else {
            if (event.target != this.fo) {
                return false;
            }
            this.bi();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, int h6, int h7) {
        h6 -= this.dv;
        h7 -= this.dw;
        final int m = this.m(h7);
        final int n = this.n(h6);
        this.h4 = m;
        this.h5 = n;
        this.h6 = h6;
        this.h7 = h7;
        return super.mouseEnter(event, h6, h7);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.h4 = 0;
        this.h5 = 0;
        this.h6 = 0;
        this.h7 = 0;
        if (this.j(-1) > 0) {
            this.repaint();
        }
        return super.mouseExit(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, int f, int g) {
        if (event.target instanceof abljemtf && this.i.cm && !this.f6.isVisible()) {
            return super.mouseUp(event, f, g);
        }
        f -= this.dv;
        g -= this.dw;
        int h4 = this.m(g);
        int h5 = this.n(f);
        if (event.target == this.i.fx) {
            f = this.i.fx.f;
            g = this.i.fx.g;
            h4 = this.i.fx.c;
            h5 = this.i.fx.d;
        }
        this.h4 = h4;
        this.h5 = h5;
        this.h6 = f;
        this.h7 = g;
        if (this.e7) {
            return true;
        }
        if (event.target == this && this.f7 != null && this.f7.isVisible()) {
            this.f7.hide();
        }
        if (event.target == this.f7 && this.f7 != null) {
            this.f7.hide();
            return true;
        }
        if (event.modifiers == 0 && this.f6.isVisible()) {
            if (!this.hi.isVisible()) {
                this.hj = true;
                this.f6.b();
                if (event.target == this) {
                    this.hi.a(this.dx + event.x, this.dy + event.y);
                }
                else {
                    this.hi.a(event.target, this.dx + event.x, this.dy + event.y);
                }
                this.hi.repaint();
                return true;
            }
            this.f6.b(f, g, h4, h5);
            this.requestFocus();
            return true;
        }
        else {
            this.f6.c();
            if (event.target == this && event.modifiers == 4 && h4 >= this.i.et + 1 && h4 < this.i.et + 3) {
                this.ep = !this.ep;
                for (int countComponents = this.countComponents(), i = 0; i < countComponents; ++i) {
                    Component component;
                    try {
                        component = this.getComponent(i);
                    }
                    catch (Throwable t) {
                        continue;
                    }
                    if (component instanceof abljemfb) {
                        final abljemfb abljemfb = (abljemfb)component;
                        if (abljemfb.b == this.i.et + 1) {
                            if (this.ep) {
                                abljemfb.show();
                            }
                            else {
                                abljemfb.hide();
                            }
                        }
                    }
                }
                return true;
            }
            if (this.a(event, h4, h5)) {
                return true;
            }
            if ((event.target == this || event.target == this.i.fx) && (event.modifiers & 0x4) != 0x0 && h4 >= 1 && this.e4 != null && (event.modifiers & 0x8) != 0x0 && this.i.i == 0) {
                if (this.e3 != null) {
                    this.e3.hide();
                }
                if (this.e3 == this.e4 && this.f7 != null) {
                    this.e3 = this.f7;
                }
                else if (this.e3 == this.f7 && this.e4 != null) {
                    this.e3 = this.e4;
                }
            }
            int n = 0;
            Component component2 = null;
            boolean b = false;
            boolean b2 = false;
            int a = -1;
            if (event.target == this || event.target == this.i.fx) {
                component2 = (((event.modifiers & 0x4) != 0x0 && event.clickCount < this.i.ck) ? this.e3 : null);
                a = this.a(h4, h5);
                int y = 0;
                if (a >= 0) {
                    if (this.gv[a].ac != null && this.cy != null) {
                        int n2 = 0;
                        final EmuPanel emuPanel = this.gv[a];
                        final abljemtf a2 = this.a(a, '\0');
                        switch (event.modifiers) {
                            case 0: {
                                n2 = emuPanel.aa;
                                break;
                            }
                            case 4: {
                                n2 = emuPanel.ab;
                                break;
                            }
                        }
                        if (n2 == 1 && !a2.isVisible()) {
                            n2 = 0;
                        }
                        if (n2 >= 1 && n2 <= 2) {
                            final String[] e = this.e(this.cy);
                            if (e[0].length() > 0 && a2 != null) {
                                a2.b(e[0]);
                                if (n2 == 2) {
                                    this.d(event, e[1]);
                                }
                                return true;
                            }
                        }
                    }
                    if (event.clickCount == this.i.ck) {
                        if (this.e1 != ' ') {
                            return true;
                        }
                        if (event.modifiers == 0) {
                            y = this.gv[a].y;
                            if (y == 1) {
                                if (this.gn != null && this.gn.isVisible()) {
                                    this.gn.hide();
                                }
                                if (!this.h(a)) {
                                    return true;
                                }
                            }
                            if (y == 0 || y == 1) {
                                this.i.e();
                                return true;
                            }
                        }
                    }
                    else if (!this.i.j.ax) {
                        if ((event.modifiers & 0x4) != 0x0) {
                            y = 2;
                        }
                    }
                    else {
                        y = (((event.modifiers & 0x4) == 0x0) ? this.gx[a] : this.gy[a]);
                    }
                    if ((event.modifiers & 0x4) == 0x0) {
                        switch (y) {
                            default: {
                                y = 1;
                                break;
                            }
                            case 1:
                            case 3:
                            case 4: {
                                break;
                            }
                            case 2: {
                                y = 3;
                                break;
                            }
                        }
                    }
                    switch (y) {
                        case 1: {
                            b = true;
                            component2 = null;
                            break;
                        }
                        case 2: {
                            if (this.gw[a] != ' ') {
                                component2 = this.gn;
                                break;
                            }
                            break;
                        }
                        case 3: {
                            b = true;
                            component2 = this.gn;
                            break;
                        }
                        case 4: {
                            b = true;
                            component2 = this.gn;
                            b2 = true;
                            break;
                        }
                    }
                }
                if (this.ea || h4 != this.g0 || h5 != this.g1) {
                    if (component2 != this.e3) {
                        component2 = null;
                    }
                    b = false;
                    b2 = false;
                    a = -1;
                }
                else if (y == 0) {
                    n += this.as();
                }
            }
            if (this.e1 != ' ') {
                b = false;
                if (component2 != this.e3) {
                    component2 = null;
                }
                b2 = false;
            }
            if (b) {
                n += this.b(a, event.modifiers);
            }
            if (n > 0) {
                this.repaint();
            }
            if (b2 && this.i(a)) {
                return true;
            }
            if (component2 != null && h4 >= 1) {
                if (component2 == this.e4) {
                    int n3 = this.bounds().x + event.x;
                    int n4 = this.bounds().y + event.y;
                    if (n3 + this.e4.bounds().width > this.p) {
                        n3 = this.p - this.e4.bounds().width - 2;
                    }
                    if (n4 + this.e4.bounds().height > this.q) {
                        n4 = this.q - this.e4.bounds().height - 2;
                    }
                    this.e4.move(n3, n4);
                    this.e3.show();
                    this.e4.requestFocus();
                }
                else if (component2 instanceof abljempu) {
                    if (this.i.g9 != null && this.i.g9.isVisible()) {
                        this.i.g9.hide();
                    }
                    this.a((abljempu)component2, this.dx + event.x + 2, this.dy + event.y);
                }
                return true;
            }
            return this.i.ci && this.a(event, f, g, h4, h5);
        }
    }
    
    public boolean mouseDown(final Event event, int h6, int h7) {
        this.l(event);
        if (event.target instanceof abljemtf && this.i.cm) {
            return super.mouseDown(event, h6, h7);
        }
        h6 -= this.dv;
        h7 -= this.dw;
        final int m = this.m(h7);
        final int n = this.n(h6);
        this.h4 = m;
        this.h5 = n;
        this.g0 = m;
        this.g1 = n;
        this.h6 = h6;
        this.h7 = h7;
        if (this.e7) {
            if (event.modifiers == 0) {
                this.f(false);
            }
            return true;
        }
        if (event.target == this && this.f7 != null && this.f7.isVisible()) {
            this.f7.hide();
        }
        if ((event.modifiers & 0x4) != 0x0) {
            return true;
        }
        if (!this.i.bu && this.d9 != null) {
            this.d9.a((Graphics)null);
        }
        if (!this.e0.h && this.i.ip != null) {
            if (event.modifiers == 0 && event.target == this) {
                this.f6.a(h6, h7, m, n);
            }
            else {
                this.f6.c();
            }
        }
        if (this.hi.isVisible()) {
            this.hi.hide();
        }
        return this.i.ci || this.a(event, h6, h7, m, n);
    }
    
    private boolean a(final Event event, final int n, final int n2) {
        if (event.target == this && event.clickCount > 0 && this.i.i > 0 && this.ce != null) {
            for (EmuRange emuRange = this.ce.a(); emuRange != null; emuRange = this.ce.b()) {
                if (n == emuRange.a && n2 >= emuRange.b && n2 < emuRange.b + emuRange.d) {
                    if (event.modifiers == 0 && event.clickCount < this.i.ck && !this.ea) {
                        if (emuRange.f == 'L') {
                            this.g(true);
                            final long currentTimeMillis = System.currentTimeMillis();
                            this.i.j.h5.handleEvent(emuRange.e);
                            while (System.currentTimeMillis() - currentTimeMillis < 5000L) {
                                abljema.a(100L);
                            }
                            this.g(false);
                        }
                        else {
                            this.i.j.h5.handleEvent(emuRange.e);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean a(final Event event, final int n, final int n2, final int n3, final int n4) {
        final int et = (n3 - 1) * this.i.es + (n4 - 1);
        if (event.modifiers == 0 && n3 >= this.i.et + 1 && n3 < this.i.et + 3) {
            this.ei = !this.ei;
            this.b(this.getGraphics());
            this.ba();
            return true;
        }
        if (event.target == this && event.modifiers == 0 && event.clickCount <= this.i.ck && !this.ea && this.i.fh != null && n3 > 0 && n3 <= this.i.fg.length) {
            final abljemtf abljemtf = this.i.fg[n3];
            if (abljemtf != null && abljemtf != abljemtf.bz) {
                if (abljemtf.g() && abljemtf.ae >= this.i.fh.length()) {
                    abljemtf.c(this.i.fh);
                    abljemtf.requestFocus();
                    if (event.clickCount == this.i.ck) {
                        this.i.e();
                    }
                    return true;
                }
                if (event.clickCount == this.i.ck && abljemtf.av.equals(this.i.fh)) {
                    this.i.e();
                    return true;
                }
                abljemtf.requestFocus();
                if (event.clickCount < this.i.ck) {
                    return true;
                }
            }
        }
        if (event.clickCount == this.i.ck && !this.ea && event.modifiers == 0 && n3 >= 1 && n3 <= this.i.et && n4 >= 1 && n4 <= this.i.es) {
            this.i.e5 = n3;
            this.i.e6 = n4;
            this.i.e();
            return true;
        }
        if (event.target != this || event.modifiers != 0 || event.clickCount >= this.i.ck || this.ea) {
            return false;
        }
        if (n3 > this.i.et && n3 < this.i.et + 3 && this.i.em != null) {
            this.i.em = null;
            this.i.en = null;
            this.o();
            return true;
        }
        if (this.hq != 0) {
            final Rectangle rectangle = this.ht[this.hq];
            if (n >= rectangle.x && n <= rectangle.x + rectangle.width && n2 >= rectangle.y && n2 <= rectangle.y + rectangle.height) {
                this.i.a(this.hr[this.hq]);
                if (this.i.fx.k) {
                    this.i.fx.b();
                    this.i.fx.paint(this.getGraphics());
                }
                return true;
            }
        }
        if (n3 < 1 || n3 > this.i.et || n4 < 1 || n4 > this.i.es) {
            return true;
        }
        this.i.e5 = n3;
        this.i.e6 = n4;
        if (this.i.bu) {
            this.et = et;
            for (int i = 1; i <= this.i.e2; ++i) {
                final abljemtf abljemtf2 = this.i.e0[i];
                if (et >= abljemtf2.ad && et < abljemtf2.ad + abljemtf2.ae) {
                    this.i.fx.b();
                    if (this.es != null && this.es != abljemtf2) {
                        this.es.lostFocus(null, null);
                    }
                    abljemtf2.requestFocus();
                    return true;
                }
            }
            if (this.es != null) {
                this.es.lostFocus(null, null);
            }
        }
        if (this.i.b(n3, n4)) {
            this.i.fx.b();
        }
        else {
            this.i.fx.a();
            this.requestFocus();
        }
        this.i.fx.paint(this.getGraphics());
        return true;
    }
    
    public boolean mouseDrag(final Event event, int h6, int h7) {
        h6 -= this.dv;
        h7 -= this.dw;
        final int m = this.m(h7);
        final int n = this.n(h6);
        this.h4 = m;
        this.h5 = n;
        this.h6 = h6;
        this.h7 = h7;
        if (event.modifiers == 0) {
            this.f6.b(h6, h7, m, n);
            if (this.f6.a()) {
                this.f6.e = (!this.z && this.ap.w == '1');
                this.f6.show();
                this.hj = false;
                this.i();
            }
            return true;
        }
        this.f6.c();
        return false;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int m = this.m(n2 - this.dw);
        final int n3 = this.n(n - this.dv);
        this.h4 = m;
        this.h5 = n3;
        this.h6 = n - this.dv;
        this.h7 = n2 - this.dw;
        if (this.e7) {
            final Rectangle bounds = this.bounds();
            this.a0();
            final Insets insets = (Insets)this.d5.clone();
            if (this.dv == 0) {
                insets.top = 0;
                insets.left = 0;
                insets.bottom = 0;
                insets.right = 0;
            }
            if (event.modifiers == 0 && n > insets.left && n < bounds.width - insets.right && n2 > insets.top && n2 < bounds.height - insets.bottom) {
                this.f(false);
            }
            return super.mouseMove(event, n, n2);
        }
        if (this.ea) {
            return super.mouseMove(event, n, n2);
        }
        final int n4 = 0;
        int n5;
        if (event.target == this) {
            n5 = n4 + this.b(this.a(m, n3), -1) + this.k(this.c(m, n3));
        }
        else {
            n5 = n4 + this.j(-1);
        }
        if (n5 > 0) {
            this.repaint();
        }
        int i;
        if (event.target != this) {
            i = this.ho + 1;
        }
        else {
            for (i = 1; i <= this.ho; ++i) {
                final Rectangle rectangle = this.ht[i];
                if (n >= rectangle.x && n <= rectangle.x + rectangle.width && n2 >= rectangle.y && n2 <= rectangle.y + rectangle.height) {
                    break;
                }
            }
        }
        if (this.hq != 0 && i != this.hq) {
            this.a(null, this.hq, 'B');
            this.hq = 0;
        }
        if (i <= this.ho && i != this.hq && i != this.hp) {
            this.a(null, this.hq = i, 'O');
        }
        return super.mouseMove(event, n, n2);
    }
    
    public boolean getFocusTraversalKeysEnabled() {
        return false;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (event.target == this.f7 && this.f7 != null && this.f7.isVisible()) {
            return super.keyUp(event, n);
        }
        if (event.target == this.ff && this.ff != null && this.ff.isVisible()) {
            return super.keyUp(event, n);
        }
        if (event.target == abljemgt.br && abljemgt.br != null && abljemgt.br.isVisible()) {
            return super.keyUp(event, n);
        }
        if (event.target == this.hi && this.hi != null && this.hi.isVisible()) {
            return super.keyUp(event, n);
        }
        if (event.target == this.fl && this.fl != null) {
            return super.keyUp(event, n);
        }
        if (this.e7) {
            return super.keyUp(event, n);
        }
        if (this.i.j.b9) {
            String s = null;
            if (n == 1002) {
                s = "DWN";
            }
            else if (n == 1003) {
                s = "UPP";
            }
            if (s != null) {
                if (this.e0.a(s, event, n, this.e1)) {
                    return true;
                }
                if (!this.ea) {
                    this.g(s);
                }
                return true;
            }
        }
        return super.keyUp(event, n);
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.l(event);
        if (event.target == this.f2 && this.f2 != null) {
            return super.keyDown(event, n);
        }
        final int n2 = 0;
        final Vector aq = this.aq();
        if (n2 + this.at() + this.b(this.a(this.h4, this.h5), -1) > 0) {
            this.repaint();
        }
        if (event.target != null && (event.target == this.gn || event.target == this.f7 || event.target == this.ff || event.target == abljemgt.br || event.target == this.hi)) {
            return this.b(event, n);
        }
        if (event.target == this.fl && this.fl != null) {
            if (n == 10 && event.modifiers == 0 && this.fo.isEnabled() && !this.fm.isEnabled()) {
                this.bi();
                return true;
            }
            return super.keyDown(event, n);
        }
        else {
            if (this.e7) {
                return super.keyDown(event, n);
            }
            if (n == 0) {
                return super.keyDown(event, n);
            }
            if (this.i.bu && this.es != null && this.es.keyDown(event, n)) {
                return true;
            }
            if (n == 1017 && this.i.cc) {
                abljema.a(100L);
                this.e6.move(this.location().x, this.location().y);
                this.e6.show();
                this.e6.hide();
            }
            if ((n == 65 || n == 97) && (event.modifiers & 0x8) != 0x0) {
                this.g(event);
                return true;
            }
            if ((n == 67 || n == 99) && event.modifiers == 9) {
                this.af();
                return true;
            }
            if ((n == 68 || n == 100) && event.modifiers == 9) {
                this.u();
                return true;
            }
            if ((n == 71 || n == 103) && event.modifiers == 9) {
                this.v();
                return true;
            }
            if (((n == 73 || n == 105) && event.modifiers == 8) || n == 1025) {
                this.c(event);
                return true;
            }
            if ((n == 74 || n == 106) && (event.modifiers & 0x8) != 0x0) {
                abljem.d("------------- Alt + J " + ++this.i.j.bc);
                return true;
            }
            if ((n == 76 || n == 108) && event.modifiers == 9) {
                this.w();
                return true;
            }
            if ((n == 77 || n == 109) && event.modifiers == 8) {
                this.a((Component)null);
                return true;
            }
            if ((n == 83 || n == 115) && (event.modifiers & 0x8) != 0x0) {
                this.h(event);
                return true;
            }
            if (this.a(event, n)) {
                return true;
            }
            if (this.ea && !this.l) {
                return true;
            }
            if ((n == 82 || n == 114) && event.modifiers == 9) {
                this.a6();
                return true;
            }
            if ((n == 84 || n == 116) && (event.modifiers & 0x8) != 0x0) {
                this.g("TST");
                return true;
            }
            if ((n == 67 || n == 99) && (event.modifiers & 0x8) != 0x0) {
                this.g("CLR");
                return true;
            }
            if (n == 1000 && (event.modifiers == 8 || ((this.i.bu || this.bn != '0') && event.modifiers == 0))) {
                this.j(event);
                return true;
            }
            if (n == 1001 && (event.modifiers == 8 || ((this.i.bu || this.bn != '0') && event.modifiers == 0))) {
                this.k(event);
                return true;
            }
            if (n >= 1004 && n <= 1005 && event.modifiers == 2) {
                if (this.c3 != null) {
                    if (this.c3.handleEvent(event)) {
                        this.c4 = true;
                        this.s();
                        this.d8 = true;
                        this.c3.requestFocus();
                    }
                    else {
                        abljem.d("Ctrl + Up/Down ignored (sidebar menu or highlight not defined)");
                    }
                }
                return true;
            }
            if (n >= 1004 && n <= 1007) {
                abljemtf es = null;
                abljembx abljembx = null;
                abljemrb abljemrb = null;
                if (n == 1004 || n == 1005) {
                    if (event.target instanceof abljemtf) {
                        es = (abljemtf)event.target;
                        this.i.e5 = es.ab;
                        this.i.e6 = es.ac + (es.n() & 0xFFFF);
                        this.requestFocus();
                    }
                    if (event.target instanceof abljembx) {
                        abljembx = (abljembx)event.target;
                        this.i.e5 = abljembx.b;
                        this.i.e6 = abljembx.c;
                        this.requestFocus();
                    }
                    if (event.target instanceof abljemrb) {
                        abljemrb = (abljemrb)event.target;
                        this.i.e5 = abljemrb.a;
                        this.i.e6 = abljemrb.b;
                        this.requestFocus();
                    }
                }
                if (this.i.bu && this.es != null) {
                    es = this.es;
                    this.s();
                    this.es.lostFocus(null, null);
                }
                if (es != null || abljembx != null || abljemrb != null || (event.target == this && this.i.fx.k)) {
                    switch (n) {
                        case 1004: {
                            final abljema i = this.i;
                            --i.e5;
                            if (this.i.e5 < 1) {
                                this.i.e5 = this.i.et;
                                break;
                            }
                            break;
                        }
                        case 1005: {
                            final abljema j = this.i;
                            ++j.e5;
                            if (this.i.e5 > this.i.et) {
                                this.i.e5 = 1;
                                break;
                            }
                            break;
                        }
                        case 1006: {
                            final abljema k = this.i;
                            --k.e6;
                            if (this.i.e6 < 1) {
                                this.i.e6 = this.i.es;
                                break;
                            }
                            break;
                        }
                        case 1007: {
                            final abljema l = this.i;
                            ++l.e6;
                            if (this.i.e6 > this.i.es) {
                                this.i.e6 = 1;
                                break;
                            }
                            break;
                        }
                    }
                    this.o(n);
                    return true;
                }
            }
            else {
                if (n == 9 && event.target == this) {
                    this.e(event.modifiers);
                    return true;
                }
                if (n == 10) {
                    switch (event.modifiers) {
                        case 0: {
                            if (!this.i.j.a4 && this.a(aq)) {
                                this.i.e();
                            }
                            return true;
                        }
                        case 2: {
                            if (this.i.ca) {
                                this.i.cb = !this.i.cb;
                                if (this.i.cb) {
                                    return true;
                                }
                            }
                            if (this.i.j.a4 && this.a(aq)) {
                                this.i.e();
                            }
                            return true;
                        }
                        case 9:
                        case 10: {
                            this.t();
                            return true;
                        }
                    }
                }
                else if (n >= 1008 && n <= 1019) {
                    if (event.modifiers == 0) {
                        switch (n) {
                            case 1008: {
                                this.g("F01");
                                return true;
                            }
                            case 1009: {
                                this.g("F02");
                                return true;
                            }
                            case 1010: {
                                this.g("F03");
                                return true;
                            }
                            case 1011: {
                                this.g("F04");
                                return true;
                            }
                            case 1012: {
                                this.g("F05");
                                return true;
                            }
                            case 1013: {
                                this.g("F06");
                                return true;
                            }
                            case 1014: {
                                this.g("F07");
                                return true;
                            }
                            case 1015: {
                                this.g("F08");
                                return true;
                            }
                            case 1016: {
                                this.g("F09");
                                return true;
                            }
                            case 1017: {
                                this.g("F10");
                                return true;
                            }
                            case 1018: {
                                this.g("F11");
                                return true;
                            }
                            case 1019: {
                                this.g("F12");
                                return true;
                            }
                        }
                    }
                    if (event.modifiers == 1) {
                        switch (n) {
                            case 1008: {
                                this.g("F13");
                                return true;
                            }
                            case 1009: {
                                this.g("F14");
                                return true;
                            }
                            case 1010: {
                                this.g("F15");
                                return true;
                            }
                            case 1011: {
                                this.g("F16");
                                return true;
                            }
                            case 1012: {
                                this.g("F17");
                                return true;
                            }
                            case 1013: {
                                this.g("F18");
                                return true;
                            }
                            case 1014: {
                                this.g("F19");
                                return true;
                            }
                            case 1015: {
                                this.g("F20");
                                return true;
                            }
                            case 1016: {
                                this.g("F21");
                                return true;
                            }
                            case 1017: {
                                this.g("F22");
                                return true;
                            }
                            case 1018: {
                                this.g("F23");
                                return true;
                            }
                            case 1019: {
                                this.g("F24");
                                return true;
                            }
                        }
                    }
                    if (event.modifiers == 8) {
                        switch (n) {
                            case 1008: {
                                this.a('1');
                                return true;
                            }
                            case 1009: {
                                this.a('2');
                                return true;
                            }
                            case 1010: {
                                this.a('3');
                                return true;
                            }
                        }
                    }
                }
                else {
                    if (n == 1002 && (this.e1 == 'P' || !this.i.b9)) {
                        this.g("DWN");
                        return true;
                    }
                    if (n == 1003 && (this.e1 == 'P' || !this.i.b9)) {
                        this.g("UPP");
                        return true;
                    }
                    if ((n == 80 || n == 112) && (event.modifiers & 0x8) != 0x0) {
                        this.f(event);
                        return true;
                    }
                    if ((n == 72 || n == 104) && (event.modifiers & 0x8) != 0x0) {
                        this.g("HLP");
                        return true;
                    }
                    if ((n == 70 || n == 102) && (event.modifiers & 0x8) != 0x0) {
                        if (this.i.j.hs) {
                            abljem.d("Available files list disabled");
                        }
                        else {
                            this.i.s();
                        }
                        return true;
                    }
                    if (event.modifiers > 1) {
                        return false;
                    }
                }
            }
            return false;
        }
    }
    
    private boolean a(final Event event, final int n) {
        boolean b = false;
        int n2 = 0;
        switch (this.i.j.r) {
            case '1': {
                final int index = "\u00e0&\u00e9\"'(-\u00e8_\u00e7".indexOf((char)n);
                if (n == 48 && event.modifiers == 9) {
                    b = true;
                }
                if (index > 0 && event.modifiers == 8) {
                    n2 = 48 + index;
                    break;
                }
                break;
            }
            case '2': {
                if (n == 224 && event.modifiers == 8) {
                    b = true;
                }
                if (n >= 48 && n <= 57 && event.modifiers == 9) {
                    n2 = n;
                    break;
                }
                break;
            }
        }
        if (n == 41 && event.modifiers == 9) {
            b = true;
        }
        if (n >= 48 && n <= 57 && (event.modifiers & 0x8) != 0x0) {
            n2 = n;
        }
        if (b) {
            this.i.j.a();
            return true;
        }
        if (n2 > 0) {
            this.l(n2);
            return true;
        }
        return false;
    }
    
    private boolean b(final Event event, final int n) {
        try {
            final Component component = (Component)event.target;
            if (component.isVisible() && n == 27) {
                if (component == this.hi) {
                    this.f6.hide();
                }
                component.hide();
                return true;
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return super.keyDown(event, n);
    }
    
    public void c(final Event event) {
        if (!this.i.bu) {
            return;
        }
        if (this.e0.h && event == null) {
            this.e0.d("INS");
        }
        this.er = !this.er;
        if (this.es != null) {
            this.es.a((Graphics)null);
        }
    }
    
    public void f(final String s) {
        if (this.bp == null) {
            return;
        }
        if (s == null || s.length() < 3) {
            return;
        }
        final String substring = s.substring(0, 3);
        final Enumeration<String> elements = this.bp.elements();
        while (elements.hasMoreElements()) {
            final String s2 = elements.nextElement();
            final String substring2 = s2.substring(0, 3);
            boolean b = false;
            if (substring.equals(substring2)) {
                b = true;
            }
            else if (substring.charAt(0) != '*' && substring2.equals("*OS")) {
                b = true;
            }
            if (b) {
                this.i.j.h5.handleEvent(new Event(this, 1001, new StyleEventArg(s2.substring(3))));
            }
        }
    }
    
    private boolean f(final Event event) {
        if (this.ey) {
            return false;
        }
        if (this.e0.h && event == null) {
            this.e0.d("PRT");
        }
        this.g("PRT");
        return true;
    }
    
    private boolean g(final Event event) {
        if (this.ew) {
            return false;
        }
        if (this.e0.h && event == null) {
            this.e0.d("ATT");
        }
        this.g("ATT");
        return true;
    }
    
    private boolean h(final Event event) {
        if (this.ex) {
            return false;
        }
        if (this.e0.h && event == null) {
            this.e0.d("SRQ");
        }
        this.g("SRQ");
        return true;
    }
    
    private void l(final int n) {
        switch (this.i.j.s) {
            case 'P': {
                if (n >= 49 && n <= 51) {
                    this.a((char)n);
                    break;
                }
                break;
            }
            case 'S': {
                if (n < 49 || n > 57) {
                    break;
                }
                final int n2 = n - 48;
                if (n2 == this.i.j.ag) {
                    return;
                }
                this.a(n2, true);
                break;
            }
        }
    }
    
    public void a(final int n, final boolean b) {
        if (this.i.bn.c != null) {
            abljem.d("Session switch ignored due one already outstanding");
            return;
        }
        if (this.i.j.d0 && this.i.j.d1 == 1) {
            abljem.d("Session switch ignored - only one playback session defined");
            return;
        }
        if (!this.i.j.u && n != 1 && this.i.j.dy[n] == null) {
            abljem.d("Session switching not supported by current server");
            return;
        }
        this.i.bn.c = new Boolean(this.ea);
        if (b) {
            this.g("INJ");
        }
        final String b2 = this.i.bn.b(n);
        if (b2 != null) {
            this.i.bm.a(b2);
        }
    }
    
    public void a(final char c) {
        this.g("MA" + c);
    }
    
    public void g(final String s) {
        if (s.length() == 3 && this.bo != null) {
            final Enumeration<String> elements = this.bo.elements();
            while (elements.hasMoreElements()) {
                final String s2 = elements.nextElement();
                if (s2.startsWith(s)) {
                    final String substring = s2.substring(3);
                    if (substring.startsWith("SFT") || substring.startsWith("MBR") || substring.startsWith("PDN") || substring.startsWith("CMC") || substring.startsWith("LNK")) {
                        this.o(substring);
                    }
                    else {
                        abljem.d(String.valueOf(s) + " mapping to \"" + substring + "\" ignored - only LNK etc supported");
                    }
                    return;
                }
            }
        }
        if (this.i.bm != null) {
            this.p(s);
        }
    }
    
    private void p(String substring) {
        if (substring.equals("AVF")) {
            this.i.s();
            return;
        }
        if (substring.equals("ENT") && this.i.e1 == 1) {
            final String string = "ENTAFT" + this.i.ez[0].av.trim();
            int i = 0;
            while (i < this.i.ey) {
                final abljemob abljemob = this.i.ex[i];
                if (abljemob.j.equalsIgnoreCase(string)) {
                    if (abljemob.k != null) {
                        this.b(new Event(this, 1001, abljemob.k));
                        return;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        this.i.a(null, 'R');
        if (this.l) {
            return;
        }
        if (this.i.j.aj[this.i.j.ag] == 'E') {
            return;
        }
        if (substring.equals("ENT") && !this.a(this.aq())) {
            return;
        }
        if (substring.charAt(0) != '*') {
            this.b(true);
            this.ab();
        }
        this.f(substring);
        if (substring.length() > 3) {
            if (!substring.substring(3, 6).equals("AFT")) {
                this.i.bm.a(substring);
                return;
            }
            abljemtf abljemtf = this.a;
            if (abljemtf == null) {
                if (this.i.j.e1 <= 1) {
                    this.i.bm.a(substring);
                    return;
                }
                abljemtf = this.i.j.ez[0];
            }
            if (this.i.i > 0) {
                if (!abljemtf.u) {
                    abljem.d("Auto entry field not cloned, using first field");
                    this.i.bm.a(substring);
                    return;
                }
                abljemtf = abljemtf.v;
            }
            abljemtf.b(substring.substring(6));
            if (abljemtf.isVisible()) {
                abljemtf.repaint();
            }
            substring = substring.substring(0, 3);
        }
        final byte[] array = new byte[6000];
        if (this.d9 != null) {
            this.d9.lostFocus(null, null);
            this.d9 = null;
        }
        if (this.i.b5 != null) {
            this.i.b5.lostFocus(null, null);
            this.i.b5 = null;
        }
        if (this.i.b8 != null) {
            this.i.b8.lostFocus(null, null);
            this.i.b8 = null;
        }
        this.s();
        int n = this.i.e5;
        int n2 = this.i.e6;
        if (this.aq != null) {
            final Component c = this.aq.c;
            if (c != null) {
                final abljemtf abljemtf2 = (abljemtf)c;
                final int n3 = (n - 1) * this.i.es + (n2 - 1);
                if (n3 >= abljemtf2.ad && n3 < abljemtf2.ad + abljemtf2.ae) {
                    this.eh = true;
                }
            }
        }
        if (!this.eh) {
            if (n2 == 1 && this.i.fa) {
                n2 = 2;
            }
            if (this.i.i > 0) {
                final Point a = this.i.a(n, n2);
                if (a != null) {
                    n = a.x;
                    n2 = a.y;
                }
                if (n < 1 || n > this.i.j.et) {
                    n = 1;
                }
                if (n2 < 1 || n2 > this.i.j.es) {
                    n2 = 1;
                }
            }
        }
        this.eh = false;
        String s = String.valueOf(n);
        if (n < 10) {
            s = "0".concat(s);
        }
        String s2 = String.valueOf(n2);
        if (n2 < 10) {
            s2 = "00".concat(s2);
        }
        else if (n2 < 100) {
            s2 = "0".concat(s2);
        }
        if (s.length() != 2) {
            s = "01";
        }
        if (s2.length() != 3) {
            s2 = "001";
        }
        final String concat = new String("ARC").concat(s).concat(s2);
        final int n4 = 0;
        final int length = substring.length();
        substring.getBytes(0, length, array, n4);
        final int n5 = n4 + length;
        final int length2 = concat.length();
        concat.getBytes(0, length2, array, n5);
        int n6 = n5 + length2;
        for (int j = 0; j < this.i.e1; ++j) {
            final abljemtf abljemtf3 = this.i.ez[j];
            if (abljemtf3.f() && (abljemtf3.q == null || abljemtf3.r == 0)) {
                final String l = abljemtf3.l();
                final int length3 = l.length();
                l.getBytes(0, length3, array, n6);
                n6 += length3;
                if (abljemtf3.a0) {
                    final String m = abljemtf3.m();
                    final int n7 = (m == null) ? 0 : m.length();
                    if (n7 > 0) {
                        m.getBytes(0, n7, array, n6);
                        n6 += n7;
                    }
                }
            }
        }
        if (n6 > 0) {
            final byte[] array2 = new byte[n6];
            System.arraycopy(array, 0, array2, 0, n6);
            this.i.bm.a(array2);
        }
    }
    
    private void i(final Event event) {
        if (this.e0.h && event == null) {
            this.e0.d("XIT");
        }
        final Event event2 = new Event(this.e0.e, 401, null);
        event2.key = 10;
        event2.modifiers = (this.i.j.a4 ? 0 : 2);
        if (this.i.bu) {
            this.handleEvent(event2);
        }
        else {
            this.d(event2);
        }
    }
    
    private void j(final Event event) {
        this.s();
        if (this.e0.h && event == null) {
            this.e0.d("BCK");
        }
        if (this.i.e5 == this.eu && this.i.e6 == this.ev) {
            this.g("BCK");
            return;
        }
        this.i.e5 = this.eu;
        this.i.e6 = this.ev;
        this.o(0);
    }
    
    private void k(final Event event) {
        if (this.e0.h && event == null) {
            this.e0.d("NWL");
        }
        this.s();
        if (this.i.bu && this.es != null) {
            final int et = (this.i.e5 - 1 + 1) * this.i.es;
            if (et > this.es.ad && et < this.es.ad + this.es.ae) {
                this.et = et;
                this.s();
                this.o(0);
                return;
            }
        }
        this.i.e6 = this.i.es;
        this.e(0);
    }
    
    public void s() {
        if (this.i.bu) {
            this.i.e5 = this.et / this.i.es;
            this.i.e6 = this.et - this.i.e5 * this.i.es;
            final abljema i = this.i;
            ++i.e5;
            final abljema j = this.i;
            ++j.e6;
        }
        else {
            final int n = (this.i.e5 - 1) * this.i.es + (this.i.e6 - 1);
            int k = 0;
            while (k < this.i.e1) {
                final abljemtf abljemtf = this.i.ez[k];
                if (n == abljemtf.ad) {
                    final int n2 = abljemtf.n();
                    int o = abljemtf.o();
                    if (n2 > 65535) {
                        o = n2 / 65536;
                    }
                    int n3 = o;
                    if (n3 >= abljemtf.ae) {
                        n3 = abljemtf.ae - 1;
                    }
                    if (n3 < 1) {
                        return;
                    }
                    final int n4 = n + n3;
                    this.i.e5 = n4 / this.i.es;
                    this.i.e6 = n4 - this.i.e5 * this.i.es;
                    final abljema l = this.i;
                    ++l.e5;
                    final abljema m = this.i;
                    ++m.e6;
                }
                else {
                    ++k;
                }
            }
        }
    }
    
    private int m(final int n) {
        if (this.a6 < 1) {
            return 0;
        }
        return 1 + (n - this.bc - this.a5) / this.a6;
    }
    
    private int n(final int n) {
        if (this.a7 < 1) {
            return 0;
        }
        return 1 + (n - this.bb) / this.a7;
    }
    
    public Color a(int n) {
        n -= 32;
        if (n < 0 || n >= 32) {
            n = 0;
        }
        return this.b1[n];
    }
    
    public Color b(int n) {
        n -= 32;
        if (n < 0 || n >= 32) {
            n = 0;
        }
        return this.b2[n];
    }
    
    public Color c(int n) {
        n -= 32;
        if (n < 0 || n >= 32) {
            n = 0;
        }
        return this.b3[n];
    }
    
    public Color a(int n, final Color color) {
        if (this.ap == null || this.ap.f == null) {
            return color;
        }
        n -= 32;
        if (n < 0 || n >= 32) {
            return color;
        }
        final Color color2 = this.ap.f[n];
        if (color2 == null) {
            return color;
        }
        return color2;
    }
    
    public Color b(int n, final Color color) {
        if (this.ap == null || this.ap.h == null) {
            return color;
        }
        n -= 32;
        if (n < 0 || n >= 32) {
            return color;
        }
        final Color color2 = this.ap.h[n];
        if (color2 == null) {
            return color;
        }
        return color2;
    }
    
    public Font a(int n, final Font font) {
        if (this.ap == null || this.ap.d == null) {
            return font;
        }
        n -= 32;
        if (n < 0 || n >= 32) {
            return font;
        }
        final Font font2 = this.ap.d[n];
        if (font2 == null) {
            return font;
        }
        Font a = font;
        if (this.ap.r == '1' && font2.isBold()) {
            a = new_font.a(a.getName(), 1, a.getSize());
        }
        return a;
    }
    
    public void t() {
        this.i.j.a4 = !this.i.j.a4;
        String s;
        if (this.i.j.a4) {
            s = "Enter is now Field Exit, Ctrl + Enter is AS/400 Enter";
        }
        else {
            s = "Ctrl + Enter is now Field Exit, Enter is AS/400 Enter";
        }
        this.i(s);
        this.a9();
    }
    
    public void u() {
        final String k = this.i.e.k;
        if (k.length() != k.indexOf(47) + 6) {
            return;
        }
        this.i.j.y = !this.i.j.y;
        String s;
        if (this.i.j.y) {
            s = "Dumping enabled";
        }
        else {
            s = "Dumping disabled";
        }
        abljem.d(s);
        this.i(s);
    }
    
    public void v() {
        if (this.i.i > 0) {
            return;
        }
        this.i.bt = !this.i.bt;
        this.a9();
        String s;
        if (this.i.bt) {
            s = "Text 5250 mode";
        }
        else {
            s = "GUI mode";
        }
        this.i(s.concat(" input will be used starting with next screen"));
        this.a7();
    }
    
    private void a6() {
        this.i(this.i.bn.a());
    }
    
    public void w() {
        this.i("Loading Style definitions...");
        this.b(this.getGraphics());
        final String c = this.i.j.c();
        this.i((c == null) ? ("Style reload " + ++this.c1 + " applied") : c);
        this.b(this.getGraphics());
    }
    
    private void a7() {
        final boolean b = this.e3 != null && this.e3.isVisible();
        if (this.i.bt) {
            this.gg.setLabel("GUI Mode");
            if (this.f7 != null) {
                this.hd.setLabel("GUI Mode");
            }
        }
        else {
            this.gg.setLabel("Text Mode");
            if (this.f7 != null) {
                this.hd.setLabel("Text Mode");
            }
        }
        if (this.f7 != null && this.i.bu != this.he.isEnabled()) {
            if (b) {
                this.f7.hide();
            }
            if (this.i.bu && !this.he.isEnabled()) {
                this.he.enable();
            }
            if (!this.i.bu && this.he.isEnabled()) {
                this.he.disable();
            }
        }
        if (this.i.bu != this.gh.isVisible()) {
            if (b) {
                this.e4.hide();
            }
            if (this.i.bu && !this.gh.isVisible()) {
                this.gh.show();
            }
            if (!this.i.bu && this.gh.isVisible()) {
                this.gh.hide();
            }
            if (this.e4 != null) {
                this.e4.layout();
            }
        }
        if (b) {
            this.e3.show();
        }
    }
    
    private Rectangle a8() {
        final Rectangle bounds = this.bounds();
        if (bounds.x > this.p - 20 || bounds.y > this.q - 20 || bounds.width < 100 || bounds.height < 100) {
            return null;
        }
        if (bounds.x < 0 || bounds.y < 0 || bounds.width >= this.p || bounds.height >= this.q) {
            return null;
        }
        return bounds;
    }
    
    private void a9() {
        if (!this.i.di) {
            return;
        }
        if (this.fr == null) {
            this.fr = this.a8();
            if (this.fr == null) {
                return;
            }
            this.fs = this.i.bt;
            this.ft = this.i.j.a4;
        }
        else {
            if (this.i.j.a4 != this.ft) {
                this.ft = this.i.j.a4;
                final StringBuffer sb = new StringBuffer(abljema.b("XAAENTERKEYACTION", 20));
                sb.append(this.i.j.a4 ? "FieldExit" : "Enter");
                this.i.bm.a(sb);
            }
            if (this.i.i > 0) {
                return;
            }
            final Rectangle a8 = this.a8();
            if (a8 != null && !a8.equals(this.fr)) {
                this.fr = a8;
                final StringBuffer sb2 = new StringBuffer(abljema.b("XAADISPLAYWINDOW", 20));
                sb2.append(abljema.e(a8.x, 6));
                sb2.append(abljema.e(a8.y, 6));
                sb2.append(abljema.e(a8.width, 6));
                sb2.append(abljema.e(a8.height, 6));
                this.i.bm.a(sb2);
            }
            if (this.i.bt != this.fs) {
                this.fs = this.i.bt;
                final StringBuffer sb3 = new StringBuffer(abljema.b("XAADISPLAYMODE", 20));
                sb3.append(this.i.bt ? "Text" : "GUI");
                this.i.bm.a(sb3);
            }
        }
    }
    
    public void a(final String s, final String ay) {
        if (s.equalsIgnoreCase("XAADISPLAYMODE      ")) {
            if (ay.equalsIgnoreCase("GUI")) {
                this.i.bt = false;
                this.i.bs = true;
            }
            if (ay.equalsIgnoreCase("Text")) {
                this.i.bt = true;
                this.i.bs = true;
            }
        }
        else if (s.equalsIgnoreCase("XAADISPLAYWINDOW    ")) {
            if (ay.length() >= 24) {
                final int d = abljema.d(ay.substring(0, 6));
                final int d2 = abljema.d(ay.substring(6, 12));
                final int d3 = abljema.d(ay.substring(12, 18));
                final int d4 = abljema.d(ay.substring(18, 24));
                this.bounds();
                this.c(d, d2, d3, d4);
            }
        }
        else if (s.equalsIgnoreCase("XAARETRIEVE         ")) {
            if (ay.length() >= 4) {
                if (!this.i.hk) {
                    if (ay.charAt(0) == '0') {
                        this.i.hg = false;
                    }
                    else {
                        this.i.hg = true;
                    }
                }
                if (!this.i.hj) {
                    if (ay.charAt(1) == '0') {
                        this.i.hf = false;
                    }
                    else {
                        this.i.hf = true;
                    }
                }
                if (!this.i.hl) {
                    this.i.hh = abljema.d(ay.substring(2, 4));
                }
            }
        }
        else if (s.equalsIgnoreCase("XAAENTERKEYACTION   ")) {
            if (ay.equalsIgnoreCase("Enter")) {
                this.i.j.a4 = false;
                this.i.j.a3 = true;
            }
            if (ay.equalsIgnoreCase("FieldExit")) {
                this.i.j.a4 = true;
                this.i.j.a3 = true;
            }
        }
        else if (s.substring(0, 3).equalsIgnoreCase("XAM")) {
            this.ez.b(s.substring(3).trim(), new StringBuffer(ay));
        }
        else if (s.equalsIgnoreCase("*USER.NUMBER        ")) {
            this.i.j.ay = ay;
        }
    }
    
    static int a(final byte[] array, final int n, final int n2, final abljema abljema, final int n3) {
        switch (array[n]) {
            case 73: {
                return abljema.b(array, n, n3);
            }
            case 67: {
                return a(array, n, abljema);
            }
            case 66: {
                return abljemfb.a(array, n, abljema);
            }
            case 79: {
                return abljemob.a(array, n, abljema);
            }
            case 65: {
                return abljema.a(array, n);
            }
            case 68: {
                return abljema.a(array, n, n3);
            }
            case 84: {
                return abljemtf.a(array, n, n2, abljema);
            }
            case 77: {
                return abljema.a(array, n, abljema);
            }
            case 69: {
                return abljema.h1.a(array, n, n3);
            }
            default: {
                abljem.d("jem error 32, " + n + "=" + new String(array, 0, n, 10));
                return -1;
            }
        }
    }
    
    static int a(final byte[] array, final int n, final abljema abljema) {
        int e5 = abljema.c(array, n + 1, 2);
        int e6 = abljema.c(array, n + 3, 3);
        if (e5 < 1) {
            e5 = 1;
        }
        if (e5 > abljema.et) {
            e5 = abljema.et;
        }
        if (e6 < 1) {
            e6 = 1;
        }
        if (e6 > abljema.es) {
            e6 = abljema.es;
        }
        abljema.e5 = e5;
        abljema.e6 = e6;
        if (abljema.bu) {
            abljema.fb.et = (abljema.e5 - 1) * abljema.es + abljema.e6 - 1;
        }
        return 6;
    }
    
    public void hide() {
        if (this.i != null) {
            if (this.i.i > 1) {
                this.a(null, 'R');
            }
            if (this.i.i < 2) {
                this.e0.b();
            }
        }
        super.hide();
    }
    
    public void show() {
        if (this.i.j.g8 != null && this.i.j.g8.isVisible()) {
            this.i.j.g8.s.hide();
            this.i.j.g8.hide();
        }
        super.show();
    }
    
    public void x() {
        if (this.i.av) {
            return;
        }
        synchronized (this.k) {
            this.show();
            this.b(!this.ea);
            this.b(!this.ea);
            this.show();
            this.b(!this.ea);
            this.b(!this.ea);
        }
        // monitorexit(this.k)
    }
    
    public void y() {
        if (this.aq != null) {
            this.aq.c(false);
        }
        this.b(true);
    }
    
    private void g(final boolean b) {
        synchronized (this.k) {
            if (b) {
                this.eb.hide();
                this.setCursor(3);
                this.add(this.eb);
                this.remove(this.eb);
            }
            else {
                this.eb.hide();
                this.setCursor(0);
                this.add(this.eb);
                this.remove(this.eb);
            }
        }
        // monitorexit(this.k)
    }
    
    public void b(final boolean b) {
        this.a(b, true);
    }
    
    public void a(final boolean b, final boolean b2) {
        if (this.l && !b) {
            return;
        }
        synchronized (this.k) {
            this.b(b, b2);
        }
        // monitorexit(this.k)
    }
    
    private void b(final boolean ea, final boolean b) {
        if (ea != this.ea || this.i.j.ap) {
            this.g(this.ea = ea);
            final Component[] components = this.getComponents();
            for (int i = 0; i < components.length; ++i) {
                try {
                    final Component component = components[i];
                    if (!(component instanceof jemTabPanel) || !((jemTabPanel)component).e || !ea || b) {
                        if (ea) {
                            component.disable();
                        }
                        else {
                            component.enable();
                        }
                    }
                }
                catch (Throwable t) {}
            }
        }
        else if (!b) {
            final Component[] components2 = this.getComponents();
            for (int j = 0; j < components2.length; ++j) {
                try {
                    final Component component2 = components2[j];
                    if (component2 instanceof jemTabPanel && ((jemTabPanel)component2).e && !b) {
                        component2.enable();
                    }
                }
                catch (Throwable t2) {}
            }
        }
        if (this.aq != null) {
            this.aq.b(this.ea);
        }
        if (!this.ea && this.aq != null) {
            this.aq.c(true);
        }
    }
    
    public void z() {
        this.i.fh = null;
        this.i.fg = new abljemtf[this.i.et + 1];
        for (int i = 0; i <= this.i.et; ++i) {
            this.i.fg[i] = null;
        }
        this.i.ff = 0;
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final Color color) {
        if (this.i.ff < 1) {
            return;
        }
        for (int n5 = n + 1, i = 1; i < n2; ++i, ++n5) {
            if (this.i.el[n5] == 61 && !this.i.a(this.i.el[n5 - 1]) && this.i.el[n5 - 1] != 61 && (i <= 2 || this.i.a(this.i.el[n5 - 2]) || this.i.a(this.i.el[n5 - 3])) && (i < 2 || this.i.el[n5 - 2] != 70 || this.i.el[n5 - 1] < 48 || this.i.el[n5 - 1] > 57)) {
                int n6;
                if (i < 2) {
                    n6 = n5 - 1;
                }
                else {
                    n6 = (this.i.a(this.i.el[n5 - 2]) ? (n5 - 1) : (n5 - 2));
                }
                int j;
                for (j = 0; j < this.i.e1; ++j) {
                    final abljemtf abljemtf = this.i.ez[j];
                    if (n6 >= abljemtf.ad && n6 <= abljemtf.ad + abljemtf.ae) {
                        break;
                    }
                }
                if (j >= this.i.e1) {
                    while (i < n2 && !this.i.a(this.i.el[n5])) {
                        ++i;
                        ++n5;
                    }
                    int n7 = this.a6 - this.ba - 1;
                    if (this.a4 <= 8 || this.a4 >= 19) {
                        ++n7;
                    }
                    int n8 = n4 + 2;
                    if (this.a4 <= 12) {
                        --n8;
                    }
                    if (this.a4 <= 11) {
                        n8 -= 2;
                    }
                    if (this.a4 <= 8) {
                        --n8;
                    }
                    if (this.a4 <= 7) {
                        --n8;
                    }
                    ++this.ho;
                    if (this.ho >= this.hr.length) {
                        --this.ho;
                    }
                    this.hr[this.ho] = n6;
                    this.hs[this.ho] = color;
                    this.ht[this.ho] = new Rectangle(n3 + (n6 - n) * this.a7 - 4, n8, (n5 - n6) * this.a7 + 8, n7);
                    this.hu[this.ho] = null;
                    this.hv[this.ho] = null;
                    if (n6 == n) {
                        this.hu[this.ho] = new Rectangle(n3 + (n6 - n - 1) * this.a7, n4, this.a7, this.a6);
                    }
                    if (n5 - n >= n2) {
                        this.hv[this.ho] = new Rectangle(n3 + (n5 - n) * this.a7, n4, this.a7, this.a6);
                    }
                }
            }
        }
    }
    
    public void d(final int n) {
        if (this.hp != 0) {
            this.a(null, this.hp, 'B');
            this.hp = 0;
        }
        if (this.hq != 0) {
            this.a(null, this.hq, 'B');
            this.hq = 0;
        }
        int hp;
        for (hp = 1; hp <= this.ho && this.hr[hp] != n; ++hp) {}
        if (hp <= this.ho) {
            this.a(null, this.hp = hp, 'A');
        }
    }
    
    private void a(Graphics graphics, final int n, final char c) {
        if (graphics == null) {
            graphics = this.getGraphics();
        }
        Color bx = this.hs[n];
        Color color = Color.black;
        if (bx.equals(color)) {
            color = Color.gray;
        }
        Color color2 = Color.white;
        if (bx.equals(color2)) {
            color2 = Color.lightGray;
        }
        switch (c) {
            default: {
                color = this.hs[n];
                color2 = this.hs[n];
                bx = this.bx;
            }
            case 'O': {
                final Color color3 = color;
                color = color2;
                color2 = color3;
            }
            case 'A': {
                final Rectangle rectangle;
                if ((rectangle = this.hu[n]) != null) {
                    this.a(graphics, bx, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                }
                final Rectangle rectangle2;
                if ((rectangle2 = this.hv[n]) != null) {
                    this.a(graphics, bx, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                }
                final Rectangle rectangle3;
                if ((rectangle3 = this.ht[n]) == null) {
                    return;
                }
                final int n2 = rectangle3.x + rectangle3.width;
                final int n3 = rectangle3.y + rectangle3.height;
                graphics.setColor(color);
                graphics.drawLine(rectangle3.x, rectangle3.y, n2, rectangle3.y);
                graphics.drawLine(rectangle3.x, rectangle3.y, rectangle3.x, n3);
                graphics.setColor(color2);
                graphics.drawLine(rectangle3.x + 1, n3, n2, n3);
                graphics.drawLine(n2, rectangle3.y + 1, n2, n3);
                graphics.setColor(this.bz);
            }
        }
    }
    
    private void ba() {
        if (this.en != 0) {
            this.repaint(this.el, this.em, this.en, this.eo);
        }
    }
    
    private void l(final Event event) {
        if (this.i.en == null) {
            return;
        }
        if (abljema.a(this.i.en, 0, "Macro")) {
            this.aa();
        }
    }
    
    public void aa() {
        synchronized (this.k) {
            if (this.aq != null) {
                this.aq.a((String)null);
                this.aq.repaint();
            }
            this.i.en = null;
            this.ba();
        }
        // monitorexit(this.k)
    }
    
    public void ab() {
        synchronized (this.k) {
            this.i.em = null;
            this.i.en = null;
            if (this.aq == null) {
                this.ba();
            }
            else {
                this.aq.a((String)null);
                this.aq.repaint();
            }
        }
        // monitorexit(this.k)
    }
    
    private void b(final Graphics graphics) {
        synchronized (this.k) {
            this.c(graphics);
        }
        // monitorexit(this.k)
    }
    
    private void c(final Graphics graphics) {
        if (this.aq != null) {
            return;
        }
        graphics.setFont(this.y);
        if (this.en == 0) {
            this.ej = this.bb;
            this.ek = this.bc + (this.i.et + 1) * this.a6 + this.bf + this.ba / 2;
            this.ej += this.dv;
            this.ek += this.dw;
            this.el = this.ej;
            this.em = this.ek - this.a8 - this.ba / 2 + 2;
            this.en = this.i.es * this.a7;
            this.eo = this.a6 - 2;
        }
        if (this.i.em != null && (!this.ei || this.i.en == null)) {
            final int length = this.i.em.length;
            this.a(graphics, this.b6[1], this.el, this.em, this.en, this.eo);
            graphics.setColor(this.b6[0]);
            a(graphics, this.i.em, 0, length, this.ej, this.ek);
            graphics.setColor(this.bz);
        }
        else if (this.i.en != null) {
            final int length2 = this.i.en.length;
            this.a(graphics, this.b7[1], this.el, this.em, this.en, this.eo);
            graphics.setColor(this.b7[0]);
            a(graphics, this.i.en, 0, length2, this.ej, this.ek);
            graphics.setColor(this.bz);
        }
    }
    
    public void h(final String s) {
        final byte[] bytes = s.getBytes();
        this.a(bytes, 0, bytes.length);
    }
    
    public void a(final byte[] array, final int n, final int n2) {
        synchronized (this.k) {
            this.d(array, n, n2);
        }
        // monitorexit(this.k)
    }
    
    private void d(final byte[] array, final int n, final int n2) {
        boolean b = true;
        int es = n2 - n;
        if (es > this.i.es && this.aq == null) {
            es = this.i.es;
        }
        if (es <= 0) {
            return;
        }
        this.i.em = new byte[es];
        for (int n3 = n, n4 = 0; n3 < n2 && n4 < es; ++n3, ++n4) {
            this.i.em[n4] = array[n3];
            if (array[n3] != 32) {
                b = false;
            }
        }
        if (b) {
            this.i.em = null;
        }
        if (this.aq != null) {
            this.aq.a(new String(this.i.em, 0));
            this.aq.repaint();
        }
        else if (this.en == 0) {
            this.o();
        }
        else {
            this.ba();
        }
    }
    
    public void i(final String s) {
        synchronized (this.k) {
            this.q(s);
        }
        // monitorexit(this.k)
    }
    
    private void q(final String s) {
        int length = s.length();
        byte[] array;
        if (length == 0) {
            array = new byte[] { 32 };
            length = 1;
        }
        else {
            array = new byte[length];
            s.getBytes(0, length, array, 0);
        }
        this.b(array, 0, length);
    }
    
    public void b(final byte[] array, final int n, final int n2) {
        synchronized (this.k) {
            this.c(array, n, n2);
        }
        // monitorexit(this.k)
    }
    
    public void c(final byte[] array, final int n, final int n2) {
        boolean b = true;
        int es = n2 - n;
        if (es > this.i.es && this.aq == null) {
            es = this.i.es;
        }
        if (es <= 0) {
            return;
        }
        this.i.en = new byte[es];
        for (int n3 = n, n4 = 0; n3 < n2 && n4 < es; ++n3, ++n4) {
            this.i.en[n4] = array[n3];
            if (array[n3] != 32) {
                b = false;
            }
        }
        if (b) {
            this.i.en = null;
        }
        if (this.eg) {
            this.ei = true;
        }
        if (this.aq != null) {
            if (this.i.en == null) {
                this.aq.a((String)null);
            }
            else {
                this.aq.a(new String(this.i.en, 0));
            }
            this.aq.repaint();
        }
        else if (this.en == 0) {
            this.o();
        }
        else {
            this.ba();
        }
    }
    
    public void a(final Component fc, final boolean d8) {
        this.fc = fc;
        this.d8 = d8;
        this.requestFocus();
    }
    
    public void c(final Component fd) {
        this.fd = fd;
        this.d8 = false;
        this.requestFocus();
    }
    
    public void requestFocus() {
        super.requestFocus();
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        return super.lostFocus(event, o);
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        if (this.c3 != null) {
            this.c4 = false;
            if (!this.i.ew) {
                this.c3.handleEvent(this.c5);
            }
        }
        if (event.target == this) {
            final boolean b = this.fc != null || this.fd != null;
            try {
                if (this.fc != null) {
                    this.fc.hide();
                }
            }
            catch (Throwable t) {}
            finally {
                this.fc = null;
            }
            try {
                if (this.fd != null) {
                    this.fd.disable();
                }
            }
            catch (Throwable t2) {}
            finally {
                this.fd = null;
            }
            if ((!this.d8 || this.o(27) == this) && !b) {
                if (this.e1 == 'W') {
                    this.a("W", 'P');
                }
                else if (!this.i.bu) {
                    this.a("F", 'P');
                }
            }
            this.d8 = false;
        }
        return super.gotFocus(event, o);
    }
    
    private Component o(final int n) {
        final abljemtf p = this.p(n);
        if (p == null) {
            return this;
        }
        if (!p.a || p.a) {
            p.requestFocus();
        }
        return p;
    }
    
    private abljemtf p(final int n) {
        int n2 = 1;
        final int et = (this.i.e5 - 1) * this.i.es + this.i.e6 - 1;
        if (this.i.bu) {
            this.et = et;
        }
        if (this.bn == '1') {
            n2 = 0;
        }
        for (int i = 1; i <= this.i.e2; ++i) {
            final abljemtf abljemtf = this.i.e0[i];
            if (this.i.bu) {
                if (et >= abljemtf.ad && et < abljemtf.ad + abljemtf.ae) {
                    this.i.fx.b();
                    return abljemtf;
                }
            }
            else if (this.i.e5 == abljemtf.ab && this.i.e6 >= abljemtf.ac - n2 && this.i.e6 <= abljemtf.ac + abljemtf.ae + n2 - 1) {
                if (this.eg || this.i.e6 < abljemtf.ac + abljemtf.ae) {
                    switch (n) {
                        case 27: {
                            abljemtf.h();
                            break;
                        }
                        case 1000: {
                            abljemtf.i();
                            break;
                        }
                        case 1004: {
                            abljemtf.i();
                            break;
                        }
                        case 1005: {
                            abljemtf.i();
                            break;
                        }
                        case 1006: {
                            abljemtf.c(false);
                            break;
                        }
                        case 1007: {
                            abljemtf.c(true);
                            break;
                        }
                        default: {
                            abljemtf.c(true);
                            break;
                        }
                    }
                    if (abljemtf.a) {
                        abljemtf.j();
                    }
                    return abljemtf;
                }
            }
            else if (abljemtf.a) {
                return abljemtf;
            }
        }
        if (this.d9 != null) {
            this.d9.b(false);
            this.d9 = null;
        }
        if (this.i.fx != null) {
            this.i.fx.a();
        }
        if (n != 27 && !this.d7) {
            this.a("P", 'W');
            this.requestFocus();
        }
        return null;
    }
    
    public void e(final int n) {
        int e2 = 0;
        int ab = 0;
        int ac = 0;
        for (int i = 1; i <= this.i.e2; ++i) {
            final abljemtf abljemtf = this.i.e0[i];
            if ((abljemtf.ab > this.i.e5 || (abljemtf.ab == this.i.e5 && abljemtf.ac >= this.i.e6)) && (e2 == 0 || abljemtf.ab < ab || (abljemtf.ab == ab && abljemtf.ac < ac))) {
                e2 = i;
                ab = abljemtf.ab;
                ac = abljemtf.ac;
            }
        }
        if (e2 == 0 && this.i.e2 > 0) {
            e2 = 1;
        }
        if (e2 != 0 && n == 1 && --e2 < 1) {
            e2 = this.i.e2;
        }
        if (e2 != 0) {
            final abljemtf abljemtf2 = this.i.e0[e2];
            abljemtf2.c(true);
            abljemtf2.requestFocus();
        }
    }
    
    public void b(final char bn) {
        this.bn = bn;
        if (this.bn != '0' && !this.i.cm) {
            abljem.d("Text-compatible GUI type not supported");
            this.bn = '0';
        }
    }
    
    public void ac() {
        this.bc();
        this.bb();
    }
    
    public void a(final Color color, final Color color2) {
        if (this.f6 == null) {
            return;
        }
        this.f6.a(color, color2);
    }
    
    private void bb() {
        if (this.i.i > 0) {
            return;
        }
        if (this.bx.equals(Color.white)) {
            this.a(Color.white, Color.black);
        }
        else {
            this.a(Color.black, Color.white);
        }
    }
    
    private void bc() {
        this.i.bv = this.i.bu;
        this.bx = this.j.a("screen_background_color", this.bx);
        this.bz = this.j.a("screen_text_color", this.bz);
        final Color[] a;
        if ((a = this.j.a("screen_colors", (Color[])null)) != null) {
            this.bz = a[0];
            this.bx = a[1];
        }
        if (this.getBackground() != this.bx) {
            this.setBackground(this.bx);
        }
        final Color a2;
        if ((a2 = this.j.a("input_background_color", (Color)null)) != null) {
            this.by = a2;
            this.cb = true;
        }
        final Color a3;
        if ((a3 = this.j.a("input_text_color", (Color)null)) != null) {
            this.b0 = a3;
            this.cb = true;
        }
        final Color[] a4;
        if ((a4 = this.j.a("input_colors", (Color[])null)) != null) {
            this.b0 = a4[0];
            this.by = a4[1];
            this.cb = true;
        }
        for (int i = 0; i < 32; ++i) {
            this.b9[i] = false;
        }
        this.be();
        this.bd();
        if (this.cb && !this.i.bv && this.i.i == 0) {
            return;
        }
        for (int j = 32; j <= 63; ++j) {
            final Color[] a5 = this.j.a("attribute_".concat(abljema.c(j)).concat("_colors"), (Color[])null);
            if (a5 != null) {
                final int n = j - 32;
                this.b3[n] = a5[0];
                this.b2[n] = a5[0];
                this.b1[n] = a5[1];
                this.b9[n] = true;
                if (j == 32) {
                    this.bz = a5[0];
                    this.bx = a5[1];
                    if (this.getBackground() != this.bx) {
                        this.setBackground(this.bx);
                    }
                    this.be();
                }
                if ((j & 0x7) == 0x7) {
                    this.b2[n] = this.b1[n];
                }
            }
        }
        final Color[] a6;
        if ((a6 = this.j.a("cursor_colors", (Color[])null)) != null) {
            this.b4 = a6[0];
            this.b5 = a6[1];
        }
        else {
            this.b4 = Color.white;
            this.b5 = Color.blue;
        }
        this.bd();
    }
    
    private void bd() {
        this.b6[0] = this.bx;
        this.b6[1] = this.bz;
        this.b7[0] = this.by;
        this.b7[1] = this.b0;
        if (!this.cb || !this.i.bv) {
            final Color[] a;
            if ((a = this.j.a("feedback_line_colors", (Color[])null)) != null) {
                this.b7 = a;
            }
            final Color[] a2;
            if ((a2 = this.j.a("error_line_colors", (Color[])null)) != null) {
                this.b6 = a2;
            }
        }
        if (this.i.bv) {
            final Color[] a3;
            if ((a3 = this.j.a("textmode_feedback_line_colors", (Color[])null)) != null) {
                this.b7 = a3;
            }
            final Color[] a4;
            if ((a4 = this.j.a("textmode_error_line_colors", (Color[])null)) != null) {
                this.b6 = a4;
            }
        }
    }
    
    private void be() {
        for (int i = 0; i < 32; ++i) {
            if (!this.b9[i]) {
                if ((i & 0x1) != 0x0 && (i & 0x7) != 0x7) {
                    this.b3[i] = this.bx;
                    this.b2[i] = this.bx;
                    this.b1[i] = this.bz;
                }
                else {
                    this.b3[i] = this.bz;
                    this.b2[i] = this.bz;
                    this.b1[i] = this.bx;
                }
                if ((i & 0x7) == 0x7) {
                    this.b2[i] = this.b1[i];
                }
            }
        }
    }
    
    public void ad() {
        for (int i = 0; i < this.i.eu - 70; ++i) {
            if (this.i.el[i] == 60 && abljema.a(this.i.el, i, this.i.eu, "<IMG SRC")) {
                boolean b = false;
                final int n = i;
                i += 8;
                switch (this.i.el[i]) {
                    case 32: {
                        b = true;
                    }
                    case 61: {
                        int n2;
                        for (n2 = ++i; i < this.i.eu && this.i.el[i] != 32 && this.i.el[i] != 62; ++i) {}
                        if (i >= this.i.eu) {
                            return;
                        }
                        int n3;
                        if (this.i.el[i] == 62 || b) {
                            n3 = i - n2;
                        }
                        else {
                            final int e;
                            if (this.i.el[i + 1] != 32 && (e = abljema.e(this.i.el, i + 1, this.i.eu)) > 0) {
                                this.i.el[i] = 47;
                                n3 = e + (i + 1 - n2);
                            }
                            else {
                                n3 = i - n2;
                            }
                        }
                        if (n3 > 0) {
                            final String lowerCase = new String(this.i.el, 0, n2, n3).toLowerCase();
                            if (this.i.fz[this.i.ga] == null || this.i.fz[this.i.ga].compareTo(lowerCase) != 0) {
                                this.i.f0[this.i.ga] = this.i.f.a(this.i.f.c(), lowerCase);
                                (this.i.f1[this.i.ga] = new abljemtc()).a(this.i.f0[this.i.ga]);
                                this.i.fz[this.i.ga] = lowerCase;
                                if (this.i.fy == null) {
                                    this.i.fy = new MediaTracker(this);
                                }
                                this.i.fy.addImage(this.i.f0[this.i.ga], 0);
                            }
                            this.i.f1[this.i.ga].hide();
                            this.i.f2[this.i.ga] = n / this.i.es + 1;
                            this.i.f3[this.i.ga] = n % this.i.es + 1;
                            int j = n + this.i.es;
                            while (j < this.i.eu) {
                                if (abljema.a(this.i.el, j, this.i.eu, "<IMGEND")) {
                                    int n4;
                                    for (n4 = j; n4 < this.i.eu && this.i.el[n4] != 62; ++n4) {}
                                    if (n4 >= this.i.eu) {
                                        break;
                                    }
                                    this.i.f4[this.i.ga] = j / this.i.es + 1;
                                    this.i.f5[this.i.ga] = n4 % this.i.es + 1;
                                    if (this.i.f4[this.i.ga] != n4 / this.i.es + 1 || this.i.f4[this.i.ga] <= this.i.f2[this.i.ga]) {
                                        break;
                                    }
                                    if (this.i.f5[this.i.ga] <= this.i.f3[this.i.ga]) {
                                        break;
                                    }
                                    this.i.f6[this.i.ga] = 0;
                                    this.i.f7[this.i.ga] = 0;
                                    this.i.f8[this.i.ga] = 0;
                                    this.i.f9[this.i.ga] = 0;
                                    this.add(this.i.f1[this.i.ga]);
                                    final abljema k = this.i;
                                    ++k.ga;
                                    break;
                                }
                                else {
                                    j += this.i.es;
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private Button r(final String s) {
        final Button button = new Button(s);
        button.setFont(this.n);
        return button;
    }
    
    public boolean a(final Dimension dimension, final Dimension dimension2) {
        return (dimension == null && dimension2 == null) || (dimension != null && dimension2 != null && dimension.width == dimension2.width && dimension.height == dimension2.height);
    }
    
    public void ae() {
        String a;
        try {
            a = abljemcb.a(this.i.ip, this);
        }
        catch (Exception ex) {
            return;
        }
        if (this.e1 != ' ') {
            return;
        }
        this.e0.a(a, this.i.bu);
        this.a(null, 'P');
    }
    
    private void bf() {
        final StringBuffer sb = new StringBuffer();
        this.f6.hide();
        for (int i = this.f6.a; i <= this.f6.c; ++i) {
            final int n = (i - 1) * this.i.es + (this.f6.b - 1);
            final int n2 = this.f6.d - this.f6.b + 1;
            if (n >= 0 && n <= this.i.el.length && n2 > 0 && n + n2 <= this.i.el.length) {
                if (i > this.f6.a) {
                    sb.append("\n");
                }
                sb.append(new String(this.i.el, 0, n, n2));
            }
        }
        final String string = sb.toString();
        if (string.length() > 0) {
            abljemcb.a(this.i.ip, string);
        }
    }
    
    public void a(Rectangle rectangle) {
        if (rectangle == null) {
            rectangle = new Rectangle(this.d3 * 2 / 3, this.d4 * 2 / 3, 10, 10);
        }
        if (this.fg == null) {
            (this.fg = new abljemd(this.i, this, "Macros", true)).setLayout(new BorderLayout());
            if (this.o != null) {
                this.fg.setBackground(this.o);
            }
            if (this.n != null) {
                this.fg.setFont(this.n);
            }
            this.fg.c = null;
            this.fi = new List(4, false);
            this.fl = new abljemgt("", 14, this.i);
            this.fm = new Button("Delete");
            this.fn = new Button("Quit");
            this.fo = new Button("Save");
            this.fp = new Label();
            (this.fj = new Panel()).setLayout(new FlowLayout());
            this.fj.add(this.fi);
            final Panel panel = new Panel();
            panel.setLayout(new BorderLayout());
            panel.add("North", this.fp);
            panel.add("South", this.fj);
            final Panel panel2 = new Panel();
            panel2.setLayout(new FlowLayout());
            panel2.add(new Label("Macro name:"));
            panel2.add(this.fl);
            final Panel panel3 = new Panel();
            panel3.setLayout(new GridLayout(1, 5));
            panel3.add(new Label(" "));
            panel3.add(this.fm);
            panel3.add(new Label(" "));
            panel3.add(this.fo);
            panel3.add(new Label(" "));
            panel3.add(this.fn);
            panel3.add(new Label(" "));
            final Panel panel4 = new Panel();
            panel4.setLayout(new BorderLayout());
            panel4.add("North", panel2);
            panel4.add("Center", new Label(" "));
            panel4.add("South", panel3);
            this.fg.add("North", panel);
            this.fg.add("Center", panel4);
            this.fg.add("South", new Label(" "));
        }
        this.h(true);
        this.fl.c("");
        this.fg.pack();
        final Rectangle bounds = this.fg.bounds();
        final Rectangle bounds2 = this.bounds();
        final int n = bounds2.x + rectangle.x + rectangle.width - bounds.width;
        final int n2 = bounds2.y + rectangle.y - bounds.height - 4;
        this.fo.disable();
        this.fm.disable();
        this.fg.move(n, n2);
        this.fk = true;
        this.fg.show();
        this.fl.requestFocus();
    }
    
    private void h(final boolean b) {
        String text;
        if (this.e0.j == null) {
            text = "  NOTHING RECORDED - can delete existing macros, or Quit. ";
        }
        else if (this.e0.j.length() > 128) {
            text = "  MACRO TOO LONG - cannot be saved. ";
        }
        else if (this.ez.b >= 11) {
            text = "  TOO MANY MACROS - select macro to be replaced or deleted. ";
        }
        else {
            text = "  Enter new macro name, select macro to be replaced, or Quit. ";
        }
        this.fp.setText(text);
        this.fh = this.ez.a(this.fh, b, this.fi);
        boolean b2 = false;
        try {
            if (this.fj.getComponent(0) == this.fh) {
                b2 = true;
            }
        }
        catch (Exception ex) {
            abljem.d("mcrListPanel(0) failed");
        }
        if (!b2) {
            this.fj.removeAll();
            this.fj.add(this.fh);
        }
    }
    
    private void bg() {
        if (this.fk && this.fl.isVisible()) {
            this.fk = false;
            this.fl.requestFocus();
        }
        final int selectedIndex;
        if ((selectedIndex = this.fh.getSelectedIndex()) >= 0) {
            this.fh.deselect(selectedIndex);
            this.fl.c(this.fh.getItem(selectedIndex));
        }
        final String av = this.fl.av;
        if (av.length() < 1 || av.charAt(0) <= ' ') {
            if (this.fm.isEnabled()) {
                this.fm.disable();
            }
            if (this.fo.isEnabled()) {
                this.fo.disable();
            }
        }
        else {
            this.ez.a(av, this.e0.c(), this.fm, this.fo);
        }
    }
    
    private void bh() {
        final String trim = this.fl.av.trim();
        if (trim.length() <= 0) {
            return;
        }
        if (!this.ez.a(trim)) {
            return;
        }
        this.fl.c("");
        this.fh.hide();
        this.h(false);
        this.fh.show();
        this.fg.pack();
        this.fl.requestFocus();
        this.i("Macro " + trim + " deleted");
    }
    
    private void bi() {
        final String trim = this.fl.av.trim();
        final String a = this.e0.a(trim);
        this.i((a == null) ? ("Macro " + trim + " saved") : a);
        this.bj();
    }
    
    private void bj() {
        this.fg.hide();
    }
    
    private abljemd a(final String s, final String s2, final Component component, final Component component2) {
        final abljemd abljemd = new abljemd(this.i, this, s, true);
        abljemd.setLayout(new BorderLayout());
        if (this.o != null) {
            abljemd.setBackground(this.o);
        }
        if (this.n != null) {
            abljemd.setFont(this.n);
        }
        abljemd.c = null;
        final Label label = new Label(s2);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 5));
        panel2.add(new Label(" "));
        if (component2 == null) {
            panel2.add(new Label(" "));
            panel2.add(component);
            panel2.add(new Label(" "));
        }
        else {
            panel2.add(component);
            panel2.add(new Label(" "));
            panel2.add(component2);
        }
        panel2.add(new Label(" "));
        final Panel panel3 = new Panel();
        panel3.setLayout(null);
        panel3.resize(40, 4);
        final Panel panel4 = new Panel();
        panel4.setLayout(null);
        panel4.resize(40, 12);
        final Panel panel5 = new Panel();
        panel5.setLayout(new BorderLayout());
        panel5.add("North", panel3);
        panel5.add("Center", panel2);
        panel5.add("South", panel4);
        abljemd.add("North", panel);
        abljemd.add("South", panel5);
        abljemd.move(this.bounds().x + this.bounds().width / 3, this.bounds().y + 100);
        abljemd.pack();
        return abljemd;
    }
    
    private void s(final String s) {
        if (this.fu != null) {
            this.fu.a();
        }
        this.fv = new Button("  OK  ");
        (this.fu = this.a("Info", s, this.fv, null)).show();
    }
    
    private void bk() {
        this.fu.a();
        this.fu = null;
    }
    
    public void j(final String s) {
        if (this.fw != null) {
            this.fw.a();
        }
        String s2 = this.j.a("end_session_prompt", "This will" + ((this.i.j.d1 == 0) ? " abnormally " : " ") + "end your session.");
        if (s != null) {
            s2 = String.valueOf(s2) + " (" + s + ")";
        }
        this.fx = new Button("  OK  ");
        this.fy = new Button("Cancel");
        (this.fw = this.a("End Session", s2, this.fx, this.fy)).show();
    }
    
    private void bl() {
        this.fw.hide();
    }
    
    private void bm() {
        this.fx.disable();
        this.fy.disable();
        this.r();
    }
    
    public boolean a(final String s, final byte[] array) {
        synchronized (this.k) {
            // monitorexit(this.k)
            return this.b(s, array);
        }
    }
    
    private boolean b(final String f4, final byte[] f5) {
        if (f4 == null) {
            this.f5 = null;
            this.f4 = null;
            return true;
        }
        if (this.f4 != null) {
            return false;
        }
        this.f4 = f4;
        this.f5 = f5;
        if (this.fz == null) {
            this.fz = new abljemd(this.i, this, "Binary Print", false);
            this.fz.d = false;
            this.fz.setLayout(new BorderLayout());
            if (this.o != null) {
                this.fz.setBackground(this.o);
            }
            if (this.n != null) {
                this.fz.setFont(this.n);
            }
            this.fz.c = null;
            this.f3 = new Label();
            this.f2 = new abljemgt(this.i, this);
            this.f0 = new Button("  OK  ");
            this.f1 = new Button("Cancel");
            final Panel panel = new Panel();
            panel.setLayout(new GridLayout(0, 1));
            panel.add(this.f3);
            panel.add(this.f2);
            panel.add(new Label("eg  LPT1  or  \\\\WORKGROUP\\SERVER\\LASER"));
            panel.add(new Label("or program, eg  COMMAND /C COPY " + this.i.bk + " LPT1"));
            final Panel panel2 = new Panel();
            panel2.setLayout(new GridLayout(1, 5));
            panel2.add(new Label(" "));
            panel2.add(this.f0);
            panel2.add(new Label(" "));
            if (this.i.bl) {
                panel2.add(this.f1);
                panel2.add(new Label(" "));
            }
            final Panel panel3 = new Panel();
            panel3.setLayout(null);
            panel3.resize(40, 4);
            final Panel panel4 = new Panel();
            panel4.setLayout(null);
            panel4.resize(40, 12);
            final Panel panel5 = new Panel();
            panel5.setLayout(new BorderLayout());
            panel5.add("North", panel3);
            panel5.add("Center", panel2);
            panel5.add("South", panel4);
            this.fz.add("North", panel);
            this.fz.add("South", panel5);
            this.fz.move(this.bounds().x + this.bounds().width / 3, this.bounds().y + 40);
        }
        this.f3.setText("Send " + f4 + " to printer");
        this.f2.c((this.i.j.hb == null) ? "" : this.i.j.hb);
        this.fz.pack();
        abljema.a(this.fz, this.i.j.g9);
        this.fz.show();
        return true;
    }
    
    private void bn() {
        if (!this.i.bl) {
            return;
        }
        this.fz.hide();
        this.a(null, (byte[])null);
    }
    
    private void bo() {
        final String av = this.f2.av;
        av.trim();
        if ((av.length() <= 2 || (!av.substring(0, 2).equals("\\\\") && !av.substring(0, 2).equals("//"))) && (av.length() != 4 || !av.substring(0, 3).equalsIgnoreCase("LPT") || av.charAt(3) < '1' || av.charAt(3) > '9') && av.indexOf(this.i.bk) < 0 && !av.equalsIgnoreCase(this.i.bj)) {
            return;
        }
        try {
            this.fz.hide();
            if (av.length() > 0 && !av.equals(this.i.j.hb)) {
                this.i.h(av);
            }
            if (av.length() > 0 && av.charAt(0) != '*') {
                try {
                    this.i.a(this.f5, this.i.j.hb, this.f4);
                }
                catch (Exception ex) {}
            }
        }
        catch (Exception ex2) {}
        this.a(null, (byte[])null);
    }
    
    static void a(final abljemf abljemf) {
        abljemf.hide();
        abljemf.removeAll();
        abljemf.dispose();
        abljemf.ia = null;
        abljemf.ib = null;
        abljemf.eb = null;
        abljemf.es = null;
        abljemf.e6 = null;
        if (abljemf.fu != null) {
            abljemf.fu.a();
        }
        abljemf.fu = null;
        if (abljemf.fw != null) {
            abljemf.fw.a();
        }
        abljemf.fw = null;
        if (abljemf.fz != null) {
            abljemf.fz.a();
        }
        abljemf.fz = null;
        abljemf.f6 = null;
        if (abljemf.e4 != null) {
            abljemf.e4.removeAll();
            abljemf.e4.dispose();
        }
        abljemf.e4 = null;
        abljemf.f7 = null;
        abljemf.e3 = null;
        abljemgt.br = null;
        abljemf.gn = null;
        abljemf.d = null;
        abljemf.gt = null;
        abljemf.hi = null;
        abljemf.fe = null;
        abljemf.ff = null;
        if (abljemf.fg != null) {
            abljemf.fg.removeAll();
            abljemf.fg.dispose();
        }
        abljemf.fg = null;
        abljemf.ap = null;
        abljemf.df = null;
        abljemf.dg = null;
        abljemf.dh = null;
        abljemf.di = null;
        abljemf.e0 = null;
        abljemf.ez = null;
        abljemf.i = null;
    }
    
    static class abljempi extends MenuItem
    {
        public menu_item a;
        
        abljempi(final menu_item a) {
            super(a.b);
            this.a = a;
        }
    }
}
