import java.awt.Event;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.image.MemoryImageSource;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EggApplet extends Applet implements Runnable
{
    private static final String p = "v1.3";
    private Applet d;
    private Thread a;
    private b n;
    private b v;
    private r i;
    private int[] l;
    private MemoryImageSource b;
    public MediaTracker c;
    private int e;
    private int f;
    private int g;
    private int h;
    private Dimension j;
    private Dimension k;
    private Dimension m;
    private Dimension o;
    private Dimension q;
    private Dimension r;
    private Dimension s;
    private Dimension t;
    private Dimension u;
    private Dimension w;
    private int x;
    private boolean y;
    private boolean z;
    private boolean dp;
    private boolean dd;
    private double da;
    private double dn;
    private double dv;
    private double di;
    private int dl;
    private int db;
    private int dc;
    private int de;
    private int df;
    private Vector dg;
    private int dh;
    private int dj;
    private Thread dk;
    private boolean dm;
    private int do;
    private boolean dq;
    private boolean dr;
    private long ds;
    private long dt;
    private Cursor du;
    public byte[] dw;
    public byte[] dx;
    public byte[] dy;
    public byte[] dz;
    public byte[] ap;
    public byte[] ad;
    public byte[] aa;
    public byte[] an;
    public byte[] av;
    public byte[] ai;
    public byte[] al;
    public byte[] ab;
    public byte[] ac;
    public byte[] ae;
    public byte[] af;
    private Color ag;
    private Image ah;
    private Image aj;
    private Image ak;
    private Image am;
    private Image ao;
    private Image aq;
    private Image ar;
    private Image as;
    private Image at;
    private Image au;
    private Image aw;
    private Image ax;
    private Image ay;
    private Image az;
    private Image np;
    private int[] nd;
    private int na;
    private int nn;
    private int nv;
    private int ni;
    private int nl;
    private Image nb;
    private Graphics nc;
    private Image ne;
    private Graphics nf;
    private boolean ng;
    private Panel nh;
    private Panel nj;
    private Panel nk;
    private Panel nm;
    private Panel no;
    private Panel nq;
    private Panel nr;
    private g ns;
    private q nt;
    private Panel nu;
    private Panel nw;
    private boolean nx;
    private boolean ny;
    private boolean nz;
    private long[] vp;
    private int vd;
    
    public EggApplet() {
        this.y = false;
        this.z = false;
        this.dp = false;
        this.dd = true;
        this.da = 0.0;
        this.dn = -30.0;
        this.dv = 0.0;
        this.di = 0.0;
        this.dl = 180;
        this.db = 0;
        this.dc = 0;
        this.de = 0;
        this.df = 150;
        this.dh = 3223088;
        this.dm = false;
        this.do = 10000;
        this.dq = true;
        this.dr = true;
        this.ds = 0L;
        this.dt = 0L;
        this.ag = Color.white;
        this.nd = new int[7];
        this.ng = false;
        this.ny = true;
        this.nz = false;
        this.vp = new long[16];
        this.vd = 15;
    }
    
    public final void p(final o o) {
        this.dg.addElement(o);
        this.ns.p(o.p());
        if (this.dd) {
            this.nt.addItem(o.p());
        }
    }
    
    public final void p() {
        this.n = null;
        this.ns.p();
        if (this.dd) {
            this.nt.p();
        }
        System.gc();
        this.nh.repaint();
    }
    
    public final void d() {
        this.v = null;
        this.n();
        this.dp = true;
        System.gc();
    }
    
    public final boolean p(final int n) {
        return n == this.dh;
    }
    
    public final void p(final boolean y) {
        this.y = y;
    }
    
    public final int p() {
        return (this.n == null) ? 0 : this.n.p();
    }
    
    public final void p(final int n) {
        this.ag = new Color(n);
    }
    
    public final void a() {
        while (this.dj < 0) {
            this.dj += this.dg.size();
        }
        if (this.dj >= this.dg.size()) {
            this.dj %= this.dg.size();
        }
        while (this.dg.size() < this.dj + 1) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                System.out.println("Caught InterruptedException: ".concat(String.valueOf(String.valueOf(ex))));
            }
        }
        this.du = this.getCursor();
        this.setCursor(Cursor.getPredefinedCursor(3));
        this.dm = true;
        this.ns.p(this.dj);
        if (this.dd) {
            this.nt.select(this.dj);
        }
        final o o = this.dg.elementAt(this.dj);
        final byte[] p = o.p();
        final Image image = Toolkit.getDefaultToolkit().createImage(p, 0, p.length);
        this.c.addImage(image, 2);
        try {
            this.c.waitForID(2);
        }
        catch (InterruptedException ex2) {
            System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex2))));
        }
        this.c.removeImage(image, 2);
        this.i = new r(image, this.g, this.h, o.d(), o.p());
        this.da = 0.0;
        this.dn = -30.0;
        this.dl = 180;
        this.dv = 360.0;
        this.de = 0;
        this.ny = true;
        this.z = true;
        this.dk = null;
        this.nh.repaint();
        this.dm = false;
        this.setCursor(this.du);
        System.gc();
    }
    
    public final void init() {
        this.d = this;
        v.p();
        this.c = new MediaTracker(this);
        this.v = new b(this, this.getParameter("Icons"));
        if (this.getParameter("DisplayList") == null) {
            this.dd = true;
        }
        else {
            this.dd = new Boolean(this.getParameter("DisplayList"));
        }
        if (!this.dd) {
            this.df = 0;
        }
        final String parameter = this.getParameter("StillTime");
        if (parameter != null) {
            final int do1 = new Integer(parameter) * 1000;
            if (do1 != 0) {
                this.do = do1;
            }
            else {
                this.dq = false;
            }
        }
        while (!this.dp) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                System.out.println("InterruptedException caught while awaiting for icons: ".concat(String.valueOf(String.valueOf(ex))));
            }
        }
        this.setBackground(this.ag);
        this.e = this.size().width - this.df;
        this.nd[0] = this.ak.getWidth(null);
        this.nd[1] = this.nd[0] + this.ao.getWidth(null);
        this.nd[2] = this.nd[1] + this.ar.getWidth(null);
        this.nd[3] = this.nd[2] + this.at.getWidth(null);
        this.nd[4] = this.nd[3] + this.ax.getWidth(null);
        this.nd[6] = this.e;
        int width = this.az.getWidth(null);
        if (this.aw != null) {
            width += this.aw.getWidth(null);
            final int[] nd = this.nd;
            final int n = 6;
            nd[n] -= this.aw.getWidth(null);
        }
        this.nd[5] = this.e - width;
        this.na = this.ak.getHeight(null);
        this.f = this.size().height - this.na;
        this.j = new Dimension(this.e, this.f);
        this.k = new Dimension(this.e, this.na);
        this.m = new Dimension(this.ak.getWidth(null), this.ak.getHeight(null));
        this.o = new Dimension(this.ao.getWidth(null), this.ao.getHeight(null));
        this.q = new Dimension(this.ar.getWidth(null), this.ar.getHeight(null));
        this.r = new Dimension(this.at.getWidth(null), this.at.getHeight(null));
        this.s = new Dimension(this.ax.getWidth(null), this.ax.getHeight(null));
        this.u = new Dimension(this.az.getWidth(null), this.az.getHeight(null));
        this.t = new Dimension(this.nd[5] - this.nd[4], this.na);
        if (this.aw != null) {
            this.w = new Dimension(this.aw.getWidth(null), this.aw.getHeight(null));
        }
        this.nb = this.createImage(this.e, this.f);
        this.nc = this.nb.getGraphics();
        this.nn = this.e - this.aj.getWidth(this) >> 1;
        this.nv = this.f - this.aj.getHeight(this) >> 1;
        this.ni = (this.e + this.aj.getWidth(this) >> 1) - 5 - this.nc.getFontMetrics().stringWidth("v1.3");
        this.nl = (this.f + this.aj.getHeight(this) >> 1) - 5;
        this.x = this.size().height;
        this.ne = this.createImage(this.e, this.x);
        (this.nf = this.ne.getGraphics()).setFont(new Font("ARIAL", 0, 12));
        this.requestFocus();
        this.nh = new k(this);
        this.nk = new m(this);
        this.nm = new p(this);
        this.no = new l(this);
        this.nq = new i(this);
        this.nr = new h(this);
        this.ns = new g(this);
        if (this.dd) {
            this.nt = new q(this);
        }
        this.nu = new j(this);
        if (this.aw != null) {
            this.nw = new c(this);
        }
        this.nj = new f(this);
        this.nh.setSize(this.e, this.f);
        if (this.a == null) {
            (this.a = new Thread(this)).start();
        }
        this.g = (this.e + 15 & 0xFFFFFFF0);
        this.h = (this.f + 7 & 0xFFFFFFF8);
        final String property = System.getProperty("os.name");
        this.nx = (property != null && property.startsWith("Windows"));
        this.e += this.df;
    }
    
    public final void start() {
        this.l = new int[this.g * this.h];
        this.b = new MemoryImageSource(this.g, this.h, this.l, 0, this.g);
        System.gc();
    }
    
    public final void run() {
        this.dg = new Vector();
        this.dj = 0;
        this.n = new b(this, this.getParameter("Album"));
        while (!this.y) {
            try {
                this.repaint();
                Thread.sleep(30L);
            }
            catch (InterruptedException ex) {
                System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex))));
            }
        }
        this.a();
        this.add(this.nh, "Center");
        this.add(this.nj, "South");
        if (this.dd) {
            this.add(this.nt, "East");
        }
        this.ne.flush();
        System.gc();
        this.dt = System.currentTimeMillis();
        this.nj.repaint();
        this.nk.repaint();
        this.nm.repaint();
        this.no.repaint();
        this.nq.repaint();
        this.nr.repaint();
        this.ns.repaint();
        this.nu.repaint();
        if (this.aw != null) {
            this.nw.repaint();
        }
        for (int i = 0; i < 16; ++i) {
            this.vp[i] = System.currentTimeMillis() - 1;
        }
        this.validate();
        while (true) {
            long n = this.nx ? 30 : 15;
            this.db += this.dc;
            if (this.dv != 0 || this.di != 0 || this.db != 0 || this.nz) {
                if (this.nz) {
                    this.ny = !this.ny;
                }
                if (!this.dr) {
                    this.dr = true;
                }
                this.da -= ((this.dv >= 180) ? -0.25 : this.dv);
                this.dn += this.di;
                this.dl += this.db;
                if (this.da >= 180) {
                    this.da -= 360;
                }
                if (this.da <= -180) {
                    this.da += 360;
                }
                if (this.dn >= 45) {
                    this.dn = 45.0;
                }
                if (this.dn <= -45) {
                    this.dn = -45.0;
                }
                if (this.dl > 1024) {
                    this.dl = 1024;
                }
                if (this.dl << 1 < this.h) {
                    this.dl = this.h >> 1;
                }
                if (this.dv < 180) {
                    n = (this.nx ? 0 : 20);
                    if (this.de != 0) {
                        this.di *= 0.8;
                        this.dv *= 0.8;
                        if (--this.de == 0) {
                            final double n2 = 0.0;
                            this.dv = n2;
                            this.di = n2;
                        }
                    }
                }
                this.nb.flush();
                if (System.currentTimeMillis() - this.dt > 3000) {
                    System.gc();
                    this.dt = System.currentTimeMillis();
                }
                this.nh.repaint();
            }
            else if (this.dr) {
                this.dr = false;
                this.ds = System.currentTimeMillis();
            }
            if (this.dq && System.currentTimeMillis() - this.ds > this.do && !this.dr) {
                this.dv = -0.25;
                System.gc();
            }
            this.vd = (this.vd + 1 & 0xF);
            final long n3 = -this.vp[this.vd];
            final long[] vp = this.vp;
            final int vd = this.vd;
            final long currentTimeMillis = System.currentTimeMillis();
            vp[vd] = currentTimeMillis;
            final long n4 = n3 + currentTimeMillis;
            this.nz = (this.ny ? (n4 > 1280) : (n4 < 640));
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex2) {
                System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex2))));
            }
        }
    }
    
    public final void stop() {
        this.a = null;
        System.gc();
    }
    
    public final void v() {
        this.requestFocus();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.z) {
            this.nf.setColor(Color.white);
            this.nf.fillRect(0, 0, this.e, this.x);
            this.nf.drawImage(this.ah, this.e - this.ah.getWidth(this) >> 1, this.x - this.ah.getHeight(this) >> 1, this);
            this.nf.setColor(Color.black);
            this.nf.fillRect(this.e - 150 >> 1, (this.x << 1) / 3, 150, 15);
            this.nf.setColor(Color.blue);
            this.nf.fillRect(this.e - 150 >> 1, (this.x << 1) / 3, 150 * this.p() / 100, 15);
            this.nf.setColor(Color.white);
            this.nf.drawString(String.valueOf(String.valueOf(new StringBuffer("").append(this.p()).append("%"))), (this.e >> 1) - 10, (this.x << 1) / 3 + 12);
            graphics.drawImage(this.ne, 0, 0, this);
        }
        else {
            super.paint(graphics);
        }
    }
    
    public final void n() {
        this.ah = Toolkit.getDefaultToolkit().createImage(this.dw);
        this.c.addImage(this.ah, 1);
        this.aj = Toolkit.getDefaultToolkit().createImage(this.dx);
        this.c.addImage(this.aj, 1);
        this.ak = Toolkit.getDefaultToolkit().createImage(this.dy);
        this.c.addImage(this.ak, 1);
        this.am = Toolkit.getDefaultToolkit().createImage(this.dz);
        this.c.addImage(this.am, 1);
        this.at = Toolkit.getDefaultToolkit().createImage(this.ap);
        this.c.addImage(this.at, 1);
        this.au = Toolkit.getDefaultToolkit().createImage(this.ad);
        this.c.addImage(this.au, 1);
        this.ao = Toolkit.getDefaultToolkit().createImage(this.aa);
        this.c.addImage(this.ao, 1);
        this.aq = Toolkit.getDefaultToolkit().createImage(this.an);
        this.c.addImage(this.aq, 1);
        this.ar = Toolkit.getDefaultToolkit().createImage(this.av);
        this.c.addImage(this.ar, 1);
        this.as = Toolkit.getDefaultToolkit().createImage(this.ai);
        this.c.addImage(this.as, 1);
        if (this.al != null) {
            this.aw = Toolkit.getDefaultToolkit().createImage(this.al);
            this.c.addImage(this.aw, 1);
        }
        this.ax = Toolkit.getDefaultToolkit().createImage(this.ab);
        this.c.addImage(this.ax, 1);
        this.ay = Toolkit.getDefaultToolkit().createImage(this.ac);
        this.c.addImage(this.ay, 1);
        this.az = Toolkit.getDefaultToolkit().createImage(this.ae);
        this.c.addImage(this.az, 1);
        this.np = Toolkit.getDefaultToolkit().createImage(this.af);
        this.c.addImage(this.np, 1);
        try {
            this.c.waitForID(1);
        }
        catch (InterruptedException ex) {
            System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex))));
        }
        this.dw = null;
        this.dx = null;
        this.dy = null;
        this.dz = null;
        this.ap = null;
        this.ad = null;
        this.aa = null;
        this.an = null;
        this.av = null;
        this.ai = null;
        this.al = null;
        this.ab = null;
        this.ac = null;
        this.ae = null;
        this.af = null;
        System.gc();
    }
    
    public final boolean keyDown(final Event event, final int n) {
        if (n == 97 || n == 1002) {
            ++this.db;
        }
        if (n == 122 || n == 1003) {
            --this.db;
        }
        if (n == 1007) {
            this.dv = -1.0;
        }
        if (n == 1006) {
            this.dv = 1.0;
        }
        if (n == 1004) {
            this.di = -1.0;
        }
        if (n == 1005) {
            this.di = 1.0;
        }
        this.de = 0;
        return true;
    }
    
    public final boolean keyUp(final Event event, final int n) {
        if (n == 97 || n == 122 || n == 1002 || n == 1003) {
            this.db = 0;
        }
        if (n == 1006 || n == 1007 || n == 1004 || n == 1005) {
            this.de = 12;
        }
        if (n == 10 || n == 32) {
            ++this.dj;
            this.a();
        }
        return true;
    }
    
    public static final boolean d(final EggApplet eggApplet) {
        return eggApplet.ny;
    }
    
    public static final Panel v(final EggApplet eggApplet) {
        return eggApplet.nw;
    }
    
    public static final Panel l(final EggApplet eggApplet) {
        return eggApplet.nu;
    }
    
    public static final g p(final EggApplet eggApplet) {
        return eggApplet.ns;
    }
    
    public static final Panel i(final EggApplet eggApplet) {
        return eggApplet.nr;
    }
    
    public static final Panel n(final EggApplet eggApplet) {
        return eggApplet.nq;
    }
    
    public static final Panel p(final EggApplet eggApplet) {
        return eggApplet.no;
    }
    
    public static final Panel d(final EggApplet eggApplet) {
        return eggApplet.nm;
    }
    
    public static final Panel b(final EggApplet eggApplet) {
        return eggApplet.nk;
    }
    
    public static final Panel a(final EggApplet eggApplet) {
        return eggApplet.nh;
    }
    
    public static final boolean p(final EggApplet eggApplet, final boolean ng) {
        return eggApplet.ng = ng;
    }
    
    public static final boolean p(final EggApplet eggApplet) {
        return eggApplet.ng;
    }
    
    public static final Graphics p(final EggApplet eggApplet) {
        return eggApplet.nc;
    }
    
    public static final Image n(final EggApplet eggApplet) {
        return eggApplet.nb;
    }
    
    public static final int i(final EggApplet eggApplet) {
        return eggApplet.nl;
    }
    
    public static final int e(final EggApplet eggApplet) {
        return eggApplet.ni;
    }
    
    public static final int d(final EggApplet eggApplet) {
        return eggApplet.nv;
    }
    
    public static final int c(final EggApplet eggApplet) {
        return eggApplet.nn;
    }
    
    public static final int n(final EggApplet eggApplet) {
        return eggApplet.na;
    }
    
    public static final int[] p(final EggApplet eggApplet) {
        return eggApplet.nd;
    }
    
    public static final Image f(final EggApplet eggApplet) {
        return eggApplet.np;
    }
    
    public static final Image a(final EggApplet eggApplet) {
        return eggApplet.az;
    }
    
    public static final Image j(final EggApplet eggApplet) {
        return eggApplet.ay;
    }
    
    public static final Image h(final EggApplet eggApplet) {
        return eggApplet.ax;
    }
    
    public static final Image b(final EggApplet eggApplet) {
        return eggApplet.aw;
    }
    
    public static final Image v(final EggApplet eggApplet) {
        return eggApplet.au;
    }
    
    public static final Image k(final EggApplet eggApplet) {
        return eggApplet.at;
    }
    
    public static final Image g(final EggApplet eggApplet) {
        return eggApplet.as;
    }
    
    public static final Image l(final EggApplet eggApplet) {
        return eggApplet.ar;
    }
    
    public static final Image i(final EggApplet eggApplet) {
        return eggApplet.aq;
    }
    
    public static final Image e(final EggApplet eggApplet) {
        return eggApplet.ao;
    }
    
    public static final Image c(final EggApplet eggApplet) {
        return eggApplet.am;
    }
    
    public static final Image p(final EggApplet eggApplet) {
        return eggApplet.ak;
    }
    
    public static final Image d(final EggApplet eggApplet) {
        return eggApplet.aj;
    }
    
    public static final Color p(final EggApplet eggApplet) {
        return eggApplet.ag;
    }
    
    public static final Cursor p(final EggApplet eggApplet, final Cursor du) {
        return eggApplet.du = du;
    }
    
    public static final boolean a(final EggApplet eggApplet) {
        return eggApplet.dm;
    }
    
    public static final Thread p(final EggApplet eggApplet, final Thread dk) {
        return eggApplet.dk = dk;
    }
    
    public static final int n(final EggApplet eggApplet, final int dj) {
        return eggApplet.dj = dj;
    }
    
    public static final int v(final EggApplet eggApplet) {
        return eggApplet.dj;
    }
    
    public static final int h(final EggApplet eggApplet) {
        return ++eggApplet.dj;
    }
    
    public static final int g(final EggApplet eggApplet) {
        return --eggApplet.dj;
    }
    
    public static final int p(final EggApplet eggApplet, final int de) {
        return eggApplet.de = de;
    }
    
    public static final int a(final EggApplet eggApplet, final int dc) {
        return eggApplet.dc = dc;
    }
    
    public static final int f(final EggApplet eggApplet) {
        return ++eggApplet.dc;
    }
    
    public static final int p(final EggApplet eggApplet) {
        return --eggApplet.dc;
    }
    
    public static final int d(final EggApplet eggApplet, final int db) {
        return eggApplet.db = db;
    }
    
    public static final int b(final EggApplet eggApplet) {
        return eggApplet.dl;
    }
    
    public static final double a(final EggApplet eggApplet, final double di) {
        return eggApplet.di = di;
    }
    
    public static final double d(final EggApplet eggApplet, final double dv) {
        return eggApplet.dv = dv;
    }
    
    public static final double p(final EggApplet eggApplet, final double dn) {
        return eggApplet.dn = dn;
    }
    
    public static final double d(final EggApplet eggApplet) {
        return eggApplet.dn;
    }
    
    public static final double p(final EggApplet eggApplet) {
        return eggApplet.da;
    }
    
    public static final Dimension a(final EggApplet eggApplet) {
        return eggApplet.w;
    }
    
    public static final Dimension l(final EggApplet eggApplet) {
        return eggApplet.u;
    }
    
    public static final Dimension b(final EggApplet eggApplet) {
        return eggApplet.t;
    }
    
    public static final Dimension d(final EggApplet eggApplet) {
        return eggApplet.s;
    }
    
    public static final Dimension c(final EggApplet eggApplet) {
        return eggApplet.r;
    }
    
    public static final Dimension p(final EggApplet eggApplet) {
        return eggApplet.q;
    }
    
    public static final Dimension i(final EggApplet eggApplet) {
        return eggApplet.o;
    }
    
    public static final Dimension n(final EggApplet eggApplet) {
        return eggApplet.m;
    }
    
    public static final Dimension e(final EggApplet eggApplet) {
        return eggApplet.k;
    }
    
    public static final Dimension v(final EggApplet eggApplet) {
        return eggApplet.j;
    }
    
    public static final int l(final EggApplet eggApplet) {
        return eggApplet.f;
    }
    
    public static final int a(final EggApplet eggApplet) {
        return eggApplet.e;
    }
    
    public static final MemoryImageSource p(final EggApplet eggApplet) {
        return eggApplet.b;
    }
    
    public static final int[] d(final EggApplet eggApplet) {
        return eggApplet.l;
    }
    
    public static final r p(final EggApplet eggApplet) {
        return eggApplet.i;
    }
    
    public static final b p(final EggApplet eggApplet) {
        return eggApplet.n;
    }
    
    public static final Applet p(final EggApplet eggApplet) {
        return eggApplet.d;
    }
}
