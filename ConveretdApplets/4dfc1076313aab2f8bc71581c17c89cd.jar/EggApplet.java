import java.awt.Event;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Image;
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
    private Thread p;
    private b d;
    private b a;
    private q n;
    private int[] v;
    private MemoryImageSource i;
    public MediaTracker l;
    private int b;
    private int c;
    private Dimension e;
    private Dimension f;
    private Dimension g;
    private Dimension h;
    private Dimension j;
    private Dimension k;
    private Dimension m;
    private Dimension o;
    private Dimension q;
    private Dimension r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean w;
    private double x;
    private double y;
    private double z;
    private double dp;
    private int dd;
    private int da;
    private int dn;
    private double dv;
    private double di;
    private Vector dl;
    private int db;
    private int dc;
    private Thread de;
    public byte[] df;
    public byte[] dg;
    public byte[] dh;
    public byte[] dj;
    public byte[] dk;
    public byte[] dm;
    public byte[] do;
    public byte[] dq;
    public byte[] dr;
    public byte[] ds;
    public byte[] dt;
    public byte[] du;
    public byte[] dw;
    public byte[] dx;
    public byte[] dy;
    private Image dz;
    private Image ap;
    private Image ad;
    private Image aa;
    private Image an;
    private Image av;
    private Image ai;
    private Image al;
    private Image ab;
    private Image ac;
    private Image ae;
    private Image af;
    private Image ag;
    private Image ah;
    private Image aj;
    private int[] ak;
    private int am;
    private Image ao;
    private Graphics aq;
    private Image ar;
    private Graphics as;
    private boolean at;
    private Panel au;
    private Panel aw;
    private Panel ax;
    private Panel ay;
    private Panel az;
    private Panel np;
    private Panel nd;
    private g na;
    private Panel nn;
    private Panel nv;
    
    public EggApplet() {
        this.t = false;
        this.u = false;
        this.w = false;
        this.x = 0.0;
        this.y = -30.0;
        this.z = -0.25;
        this.dp = 0.0;
        this.dd = 180;
        this.da = 0;
        this.dn = 0;
        this.dv = 45.0;
        this.di = -45.0;
        this.db = 3223088;
        this.ak = new int[7];
        this.at = false;
    }
    
    public final void p(final o o) {
        this.dl.addElement(o);
        this.na.p(o.p());
    }
    
    public final void n() {
        this.d = null;
        System.gc();
    }
    
    public final void d() {
        this.a = null;
        this.a();
        this.w = true;
        System.gc();
    }
    
    public final boolean p(final int n) {
        return n == this.db;
    }
    
    public final void p(final boolean t) {
        this.t = t;
    }
    
    public final int p() {
        if (this.d == null) {
            return 0;
        }
        return this.d.p();
    }
    
    public final void v() {
        while (this.dc < 0) {
            this.dc += this.dl.size();
        }
        if (this.dc >= this.dl.size()) {
            this.dc %= this.dl.size();
        }
        while (this.dl.size() < this.dc + 1) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                System.out.println("Caught InterruptedException: ".concat(String.valueOf(String.valueOf(ex))));
            }
        }
        this.na.p(this.dc);
        final o o = this.dl.elementAt(this.dc);
        final byte[] p = o.p();
        final Image image = Toolkit.getDefaultToolkit().createImage(p, 0, p.length);
        this.l.addImage(image, 2);
        try {
            this.l.waitForID(2);
        }
        catch (InterruptedException ex2) {
            System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex2))));
        }
        this.n = new q(image, this.b, this.c, o.d(), o.p());
        this.x = 0.0;
        this.y = -30.0;
        this.dd = 180;
        this.z = -0.25;
        this.u = true;
        this.de = null;
        System.gc();
        this.au.repaint();
    }
    
    public final void init() {
        this.setBackground(Color.white);
        v.p();
        this.l = new MediaTracker(this);
        this.a = new b(this, this.getParameter("Icons"));
        while (!this.w) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                System.out.println("InterruptedException caught while awaiting for images: ".concat(String.valueOf(String.valueOf(ex))));
            }
        }
        this.b = this.size().width;
        this.ak[0] = this.ad.getWidth(null);
        this.ak[1] = this.ak[0] + this.an.getWidth(null);
        this.ak[2] = this.ak[1] + this.ai.getWidth(null);
        this.ak[3] = this.ak[2] + this.ab.getWidth(null);
        this.ak[4] = this.ak[3] + this.af.getWidth(null);
        this.ak[6] = this.b;
        int width = this.ah.getWidth(null);
        if (this.ae != null) {
            width += this.ae.getWidth(null);
            final int[] ak = this.ak;
            final int n = 6;
            ak[n] -= this.ae.getWidth(null);
        }
        this.ak[5] = this.b - width;
        this.am = this.ad.getHeight(null);
        this.c = this.size().height - this.am;
        this.e = new Dimension(this.b, this.c);
        this.f = new Dimension(this.b, this.am);
        this.g = new Dimension(this.ad.getWidth(null), this.ad.getHeight(null));
        this.h = new Dimension(this.an.getWidth(null), this.an.getHeight(null));
        this.j = new Dimension(this.ai.getWidth(null), this.ai.getHeight(null));
        this.k = new Dimension(this.ab.getWidth(null), this.ab.getHeight(null));
        this.m = new Dimension(this.af.getWidth(null), this.af.getHeight(null));
        this.q = new Dimension(this.ah.getWidth(null), this.ah.getHeight(null));
        this.o = new Dimension(this.ak[5] - this.ak[4], this.am);
        if (this.ae != null) {
            this.r = new Dimension(this.ae.getWidth(null), this.ae.getHeight(null));
        }
        this.ao = this.createImage(this.b, this.c);
        this.aq = this.ao.getGraphics();
        this.s = this.size().height;
        this.ar = this.createImage(this.b, this.s);
        (this.as = this.ar.getGraphics()).setFont(new Font("ARIAL", 0, 12));
        this.requestFocus();
        this.au = new k(this);
        this.ax = new m(this);
        this.ay = new p(this);
        this.az = new l(this);
        this.np = new i(this);
        this.nd = new h(this);
        this.na = new g(this);
        this.nn = new j(this);
        if (this.ae != null) {
            this.nv = new c(this);
        }
        this.aw = new f(this);
        this.au.setSize(this.b, this.c);
        if (this.p == null) {
            (this.p = new Thread(this)).start();
        }
    }
    
    public final void start() {
        this.v = new int[this.b * this.c];
        this.i = new MemoryImageSource(this.b, this.c, this.v, 0, this.b);
        System.gc();
    }
    
    public final void run() {
        this.dl = new Vector();
        this.dc = 0;
        this.d = new b(this, this.getParameter("Album"));
        while (!this.t) {
            try {
                this.repaint();
                Thread.sleep(30L);
            }
            catch (InterruptedException ex) {
                System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex))));
            }
        }
        this.v();
        this.add(this.au, "Center");
        this.add(this.aw, "South");
        this.ar.flush();
        System.gc();
        this.aw.repaint();
        this.ax.repaint();
        this.ay.repaint();
        this.az.repaint();
        this.np.repaint();
        this.nd.repaint();
        this.na.repaint();
        this.nn.repaint();
        if (this.ae != null) {
            this.nv.repaint();
        }
        while (true) {
            if (this.dn != 0) {
                this.da += this.dn;
            }
            if (this.z != 0 || this.dp != 0 || this.da != 0) {
                this.x -= this.z;
                this.y += this.dp;
                this.dd += this.da;
                if (this.x >= 360) {
                    this.x -= 360;
                }
                if (this.x <= 0) {
                    this.x += 360;
                }
                if (this.y >= 45) {
                    this.y = 45.0;
                }
                if (this.y <= -45) {
                    this.y = -45.0;
                }
                if (this.dd > 512) {
                    this.dd = 512;
                }
                if (this.dd << 1 < this.c) {
                    this.dd = this.c >> 1;
                }
                this.ao.flush();
                this.au.repaint();
            }
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex2) {
                System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex2))));
            }
        }
    }
    
    public final void stop() {
        this.p = null;
        System.gc();
    }
    
    public final void p() {
        this.requestFocus();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.u) {
            this.as.setColor(Color.white);
            this.as.fillRect(0, 0, this.b, this.s);
            this.as.drawImage(this.dz, this.b - this.dz.getWidth(this) >> 1, this.s - this.dz.getHeight(this) >> 1, this);
            this.as.setColor(Color.black);
            this.as.fillRect(this.b - 150 >> 1, (this.s << 1) / 3, 150, 15);
            this.as.setColor(Color.blue);
            this.as.fillRect(this.b - 150 >> 1, (this.s << 1) / 3, 150 * this.p() / 100, 15);
            this.as.setColor(Color.white);
            this.as.drawString(String.valueOf(String.valueOf(new StringBuffer("").append(this.p()).append("%"))), (this.b >> 1) - 10, (this.s << 1) / 3 + 12);
            graphics.drawImage(this.ar, 0, 0, this);
        }
        else {
            super.paint(graphics);
        }
    }
    
    public final void a() {
        this.dz = Toolkit.getDefaultToolkit().createImage(this.df);
        this.l.addImage(this.dz, 1);
        this.ap = Toolkit.getDefaultToolkit().createImage(this.dg);
        this.l.addImage(this.ap, 1);
        this.ad = Toolkit.getDefaultToolkit().createImage(this.dh);
        this.l.addImage(this.ad, 1);
        this.aa = Toolkit.getDefaultToolkit().createImage(this.dj);
        this.l.addImage(this.aa, 1);
        this.ab = Toolkit.getDefaultToolkit().createImage(this.dk);
        this.l.addImage(this.ab, 1);
        this.ac = Toolkit.getDefaultToolkit().createImage(this.dm);
        this.l.addImage(this.ac, 1);
        this.an = Toolkit.getDefaultToolkit().createImage(this.do);
        this.l.addImage(this.an, 1);
        this.av = Toolkit.getDefaultToolkit().createImage(this.dq);
        this.l.addImage(this.av, 1);
        this.ai = Toolkit.getDefaultToolkit().createImage(this.dr);
        this.l.addImage(this.ai, 1);
        this.al = Toolkit.getDefaultToolkit().createImage(this.ds);
        this.l.addImage(this.al, 1);
        if (this.dt != null) {
            this.ae = Toolkit.getDefaultToolkit().createImage(this.dt);
            this.l.addImage(this.ae, 1);
        }
        this.af = Toolkit.getDefaultToolkit().createImage(this.du);
        this.l.addImage(this.af, 1);
        this.ag = Toolkit.getDefaultToolkit().createImage(this.dw);
        this.l.addImage(this.ag, 1);
        this.ah = Toolkit.getDefaultToolkit().createImage(this.dx);
        this.l.addImage(this.ah, 1);
        this.aj = Toolkit.getDefaultToolkit().createImage(this.dy);
        this.l.addImage(this.aj, 1);
        try {
            this.l.waitForID(1);
        }
        catch (InterruptedException ex) {
            System.out.println("Caught Exception: ".concat(String.valueOf(String.valueOf(ex))));
        }
        this.df = null;
        this.dg = null;
        this.dh = null;
        this.dj = null;
        this.dk = null;
        this.dm = null;
        this.do = null;
        this.dq = null;
        this.dr = null;
        this.ds = null;
        this.dt = null;
        this.du = null;
        this.dw = null;
        this.dx = null;
        this.dy = null;
        System.gc();
    }
    
    public final boolean keyDown(final Event event, final int n) {
        if (n == 97 || n == 1002) {
            ++this.da;
        }
        if (n == 122 || n == 1003) {
            --this.da;
        }
        if (n == 1007) {
            this.z = -1.0;
        }
        if (n == 1006) {
            this.z = 1.0;
        }
        if (n == 1004) {
            this.dp = -1.0;
        }
        if (n == 1005) {
            this.dp = 1.0;
        }
        return true;
    }
    
    public final boolean keyUp(final Event event, final int n) {
        if (n == 97 || n == 122 || n == 1002 || n == 1003) {
            this.da = 0;
        }
        if (n == 1006 || n == 1007) {
            this.z = 0.0;
        }
        if (n == 1004 || n == 1005) {
            this.dp = 0.0;
        }
        if (n == 10 || n == 32) {
            ++this.dc;
            this.v();
        }
        return true;
    }
    
    public static final Panel i(final EggApplet eggApplet) {
        return eggApplet.nv;
    }
    
    public static final Panel a(final EggApplet eggApplet) {
        return eggApplet.nn;
    }
    
    public static final g p(final EggApplet eggApplet) {
        return eggApplet.na;
    }
    
    public static final Panel l(final EggApplet eggApplet) {
        return eggApplet.nd;
    }
    
    public static final Panel b(final EggApplet eggApplet) {
        return eggApplet.np;
    }
    
    public static final Panel p(final EggApplet eggApplet) {
        return eggApplet.az;
    }
    
    public static final Panel d(final EggApplet eggApplet) {
        return eggApplet.ay;
    }
    
    public static final Panel n(final EggApplet eggApplet) {
        return eggApplet.ax;
    }
    
    public static final Panel v(final EggApplet eggApplet) {
        return eggApplet.au;
    }
    
    public static final boolean p(final EggApplet eggApplet, final boolean at) {
        return eggApplet.at = at;
    }
    
    public static final boolean p(final EggApplet eggApplet) {
        return eggApplet.at;
    }
    
    public static final Graphics p(final EggApplet eggApplet) {
        return eggApplet.aq;
    }
    
    public static final Image g(final EggApplet eggApplet) {
        return eggApplet.ao;
    }
    
    public static final int d(final EggApplet eggApplet) {
        return eggApplet.am;
    }
    
    public static final int[] p(final EggApplet eggApplet) {
        return eggApplet.ak;
    }
    
    public static final Image a(final EggApplet eggApplet) {
        return eggApplet.aj;
    }
    
    public static final Image n(final EggApplet eggApplet) {
        return eggApplet.ah;
    }
    
    public static final Image i(final EggApplet eggApplet) {
        return eggApplet.ag;
    }
    
    public static final Image c(final EggApplet eggApplet) {
        return eggApplet.af;
    }
    
    public static final Image k(final EggApplet eggApplet) {
        return eggApplet.ae;
    }
    
    public static final Image l(final EggApplet eggApplet) {
        return eggApplet.ac;
    }
    
    public static final Image e(final EggApplet eggApplet) {
        return eggApplet.ab;
    }
    
    public static final Image v(final EggApplet eggApplet) {
        return eggApplet.al;
    }
    
    public static final Image j(final EggApplet eggApplet) {
        return eggApplet.ai;
    }
    
    public static final Image p(final EggApplet eggApplet) {
        return eggApplet.av;
    }
    
    public static final Image b(final EggApplet eggApplet) {
        return eggApplet.an;
    }
    
    public static final Image d(final EggApplet eggApplet) {
        return eggApplet.aa;
    }
    
    public static final Image h(final EggApplet eggApplet) {
        return eggApplet.ad;
    }
    
    public static final Image f(final EggApplet eggApplet) {
        return eggApplet.ap;
    }
    
    public static final Thread p(final EggApplet eggApplet, final Thread de) {
        return eggApplet.de = de;
    }
    
    public static final int p(final EggApplet eggApplet, final int dc) {
        return eggApplet.dc = dc;
    }
    
    public static final int i(final EggApplet eggApplet) {
        return ++eggApplet.dc;
    }
    
    public static final int b(final EggApplet eggApplet) {
        return --eggApplet.dc;
    }
    
    public static final int a(final EggApplet eggApplet, final int dn) {
        return eggApplet.dn = dn;
    }
    
    public static final int n(final EggApplet eggApplet) {
        return ++eggApplet.dn;
    }
    
    public static final int v(final EggApplet eggApplet) {
        return --eggApplet.dn;
    }
    
    public static final int d(final EggApplet eggApplet, final int da) {
        return eggApplet.da = da;
    }
    
    public static final int l(final EggApplet eggApplet) {
        return eggApplet.dd;
    }
    
    public static final double a(final EggApplet eggApplet, final double dp) {
        return eggApplet.dp = dp;
    }
    
    public static final double d(final EggApplet eggApplet, final double z) {
        return eggApplet.z = z;
    }
    
    public static final double p(final EggApplet eggApplet, final double y) {
        return eggApplet.y = y;
    }
    
    public static final double p(final EggApplet eggApplet) {
        return eggApplet.y;
    }
    
    public static final double d(final EggApplet eggApplet) {
        return eggApplet.x;
    }
    
    public static final Dimension p(final EggApplet eggApplet) {
        return eggApplet.r;
    }
    
    public static final Dimension v(final EggApplet eggApplet) {
        return eggApplet.q;
    }
    
    public static final Dimension d(final EggApplet eggApplet) {
        return eggApplet.o;
    }
    
    public static final Dimension e(final EggApplet eggApplet) {
        return eggApplet.m;
    }
    
    public static final Dimension b(final EggApplet eggApplet) {
        return eggApplet.k;
    }
    
    public static final Dimension l(final EggApplet eggApplet) {
        return eggApplet.j;
    }
    
    public static final Dimension i(final EggApplet eggApplet) {
        return eggApplet.h;
    }
    
    public static final Dimension n(final EggApplet eggApplet) {
        return eggApplet.g;
    }
    
    public static final Dimension a(final EggApplet eggApplet) {
        return eggApplet.f;
    }
    
    public static final Dimension c(final EggApplet eggApplet) {
        return eggApplet.e;
    }
    
    public static final int a(final EggApplet eggApplet) {
        return eggApplet.c;
    }
    
    public static final int p(final EggApplet eggApplet) {
        return eggApplet.b;
    }
    
    public static final MemoryImageSource p(final EggApplet eggApplet) {
        return eggApplet.i;
    }
    
    public static final int[] d(final EggApplet eggApplet) {
        return eggApplet.v;
    }
    
    public static final q p(final EggApplet eggApplet) {
        return eggApplet.n;
    }
}
