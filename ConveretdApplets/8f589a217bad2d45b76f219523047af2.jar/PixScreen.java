import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Window;
import java.awt.Frame;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dialog;
import java.util.Stack;
import java.awt.Panel;
import java.util.Hashtable;
import java.awt.MediaTracker;
import java.awt.image.ImageConsumer;
import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PixScreen extends Applet implements Runnable, ImageProducer
{
    public int p;
    public int d;
    public String a;
    public String n;
    public String v;
    public int i;
    public boolean l;
    public int[] b;
    public Image c;
    public ColorModel e;
    public boolean f;
    public Image g;
    public PixelGrabber h;
    public _zy j;
    public double k;
    public boolean m;
    public Thread o;
    public double q;
    public double r;
    public double s;
    public double t;
    public double u;
    public double w;
    public double x;
    public double y;
    private double z;
    private double dp;
    private double dd;
    private double da;
    private double dn;
    private double dv;
    private double di;
    private double dl;
    private double db;
    private double dc;
    private double de;
    private double df;
    private double dg;
    private double dh;
    private double dj;
    private double dk;
    private double dm;
    private double do;
    private boolean dq;
    public int dr;
    public int ds;
    public int dt;
    public double du;
    public double dw;
    private int dx;
    private int dy;
    private int dz;
    private long ap;
    private long ad;
    public int aa;
    public _zc an;
    public int av;
    public int ai;
    public int al;
    public int ab;
    public boolean ac;
    public boolean ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private boolean aj;
    public ImageConsumer ak;
    public MediaTracker am;
    public int ao;
    public int aq;
    public _zh ar;
    public _zh as;
    public Hashtable at;
    public boolean au;
    public _zi[] aw;
    public Panel ax;
    public int ay;
    public int az;
    public int np;
    public int nd;
    public boolean na;
    public boolean nn;
    public boolean nv;
    public boolean ni;
    public double nl;
    public int nb;
    public int nc;
    public int ne;
    public double nf;
    public int ng;
    public int nh;
    public boolean nj;
    public boolean nk;
    public int nm;
    public int no;
    public int nq;
    public int nr;
    public int ns;
    public int nt;
    public int nu;
    public int nw;
    public boolean nx;
    public Image ny;
    public int[] nz;
    public v vp;
    public _zf vd;
    public _zo va;
    public Stack vn;
    public Dialog vv;
    public Image vi;
    public int vl;
    private _zc vb;
    public Stack vc;
    public String ve;
    public String vf;
    public int vg;
    public int vh;
    public int vj;
    public int vk;
    public boolean vm;
    public int vo;
    public int vq;
    
    public final void a(final String s) {
        this.p(s, this.vl, -1, -1, -1);
    }
    
    public final void stop() {
        if (this.o != null) {
            this.m = false;
            try {
                this.o.join(0L);
            }
            catch (InterruptedException ex) {}
            this.o = null;
        }
        this.b = null;
        this.c = null;
        this.ak = null;
        this.vi = null;
        this.v();
    }
    
    public final void f() {
        if (this.j != null) {
            this.p(this.j.d, this.vl, -1, -1, -1);
        }
    }
    
    public final synchronized void p(final String vf) {
        this.vf = vf;
        final Graphics graphics = this.getGraphics();
        if (vf != null) {
            this.vj = graphics.getFontMetrics().stringWidth(this.vf) + 6;
            this.vk = 15;
            this.vm = false;
            return;
        }
        this.c();
    }
    
    public final synchronized void TransitTo(final String n, final int n2) {
        if (n2 == 0) {
            this.ae = false;
        }
        else {
            this.ae = true;
        }
        this.dq = false;
        if (n.compareTo(this.a) != 0) {
            this.vn.push(new _zt(this.a, this.r, this.s, this.t));
        }
        this.dr = 101;
        this.n = n;
    }
    
    private final synchronized void a(final int n) {
        if (n == 501) {
            this.dw = 10.0;
            this.dr = 90;
            this.na = true;
            if (this.np == -1) {
                this.np = this.j.p.m;
                this.nd = this.j.p.o;
            }
            this.nf = this.nl;
            return;
        }
        this.dw = 0.0;
        this.dr = 5;
        this.na = false;
        this.nm = (int)(this.j.p.m * this.nl + 0.5);
        if (this.g.getWidth(null) != this.j.p.j) {
            this.nq = (int)(this.j.d() / 2 * this.nl + 0.5);
        }
        else {
            this.nq = this.nm;
        }
        this.no = (int)(this.j.p.o * this.nl + 0.5);
        if (this.g.getHeight(null) != this.nb) {
            this.nr = (int)(this.j.p.q / 2 * this.nl + 0.5);
        }
        else {
            this.nr = this.no;
        }
        if (this.nl != 1.0) {
            final double nl = this.nl;
            if (this.ns != 0) {
                this.ns = (int)(nl / this.nf * this.ns + 0.5);
            }
            if (this.nt != 0) {
                this.nt = (int)(nl / this.nf * this.nt + 0.5);
            }
        }
    }
    
    private final void e() {
        this.ap = System.currentTimeMillis();
        this.du = this.dx;
        this.dr = 80;
    }
    
    private final void g() {
        if (this.j == null) {
            this.l();
        }
        this.af = true;
        if (this.j.n != null) {
            final Image image = this.getImage(this.getDocumentBase(), this.j.n);
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.am.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, -1, -1, false);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex2) {}
            this.j.p((byte[])pixelGrabber.getPixels(), pixelGrabber.getWidth(), pixelGrabber.getHeight());
        }
        if (this.dr == 0) {
            this.dr = 5;
        }
        if (this.au) {
            this.j.v();
        }
        this.j.n();
        if (this.au) {
            this.j.a();
        }
        this.dn = this.j.d();
        this.p();
        if (this.ni) {
            this.aw[7].a = this.aw[7].n;
            this.aw[7].repaint();
            this.aw[7].v = false;
            this.ni = false;
        }
        this.i();
        this.repaint();
    }
    
    public final synchronized void a() {
        if (this.j == null) {
            return;
        }
        int n;
        if (this.ao == 1) {
            n = 60;
        }
        else {
            n = 20;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < n; ++i) {
            this.i();
            this.repaint();
        }
        this.y = (System.currentTimeMillis() - currentTimeMillis) / n / 1000.0;
        if (this.ao == 1) {
            this.da = 400.0;
        }
        else {
            this.da = 200.0;
        }
        if (this.y < 1.0 / this.q) {
            this.dz = (int)((1.0 / this.q - this.y) * 1000.0);
            this.y = 1.0 / this.q;
        }
        else {
            this.dz = 0;
        }
        if (this.ao == 1) {
            this.dz = 20;
        }
    }
    
    private final void d(final String a) {
        if (this.vd.p == -1) {
            return;
        }
        this.v();
        this.b();
        this.f = false;
        this.af = false;
        this.a = a;
        this.g = this.getImage(this.getDocumentBase(), a);
        (this.am = new MediaTracker(this)).addImage(this.g, 0);
        this.prepareImage(this.g, this);
        if (this.ae) {
            this.e();
        }
        else {
            this.dr = 0;
        }
        this.p();
    }
    
    public final synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public final synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return (n & 0x20) != 0x20;
    }
    
    private final synchronized void v(final int n) {
        if (n == 501) {
            if (this.ni) {
                this.ni = false;
                if (this.aw[7] != null) {
                    this.aw[7].v = false;
                }
            }
            else {
                this.ni = true;
                if (this.aw[7] != null) {
                    this.aw[7].v = true;
                }
            }
            this.dr = 5;
        }
    }
    
    private final synchronized void d() {
        if (this.j == null) {
            return;
        }
        int n = 0;
        if (this.t > this.dn) {
            this.t = this.dn;
        }
        if (this.t < this.dv) {
            this.t = this.dv;
        }
        final double p = this.j.p();
        switch (this.ao) {
            case 2:
            case 4:
            case 5:
            case 6: {
                if (this.t != this.di) {
                    this.db = this.j.p(this.t);
                    this.dl = this.j.d(this.t);
                }
                if (this.j.a()) {
                    this.dc = this.j.p(this.s, this.t);
                    break;
                }
                break;
            }
            case 1: {
                final int height = this.g.getHeight(null);
                final int width = this.g.getWidth(null);
                if (height - this.d > 0) {
                    this.db = height / 2 / 120 * 2.0;
                }
                else {
                    this.db = 0.0;
                }
                if (width < this.p) {
                    this.dc = p / 2.0;
                    break;
                }
                if (this.j.a()) {
                    this.dc = p / width * this.p / 2.0;
                    break;
                }
                this.dc = 0.0;
                break;
            }
            case 3: {
                this.j.p(this.s, this.t);
                if (Math.abs(p - 360.0) > 1.0E-4) {
                    if (this.j.p.d < 0) {
                        this.j.p.d = 0;
                        n = 1;
                    }
                    if (this.j.p.d >= this.j.p.p) {
                        this.j.p.d = this.j.p.p - 1;
                        n = 1;
                    }
                    if (this.dr == 80 && n != 0) {
                        this.du *= -1.0;
                    }
                }
                else {
                    if (this.j.p.d >= this.j.p.p) {
                        this.j.p.d = 0;
                    }
                    if (this.j.p.d < 0) {
                        this.j.p.d = this.j.p.p - 1;
                    }
                }
                return;
            }
        }
        this.di = this.t;
        if (this.s < this.dl) {
            this.s = this.dl;
        }
        if (this.s > this.db) {
            this.s = this.db;
        }
        if (this.j.a()) {
            if (this.r < this.dc) {
                this.r = this.dc;
                this.u = 0.0;
                n = 1;
            }
            if (this.r > p - this.dc) {
                this.r = p - this.dc;
                this.u = 0.0;
                n = 1;
            }
            if (this.dr == 80 && n != 0) {
                this.du *= -1.0;
            }
        }
        else {
            while (this.r < 0.0) {
                this.r += 360.0;
            }
            while (this.r > 360.0) {
                this.r -= 360.0;
            }
        }
    }
    
    public final void start() {
        if (!this.nx) {
            this.vi = this.va.p("psg.gif", -120, 89, 112);
        }
        if (this.nx && this.vi == null) {
            this.vi = this.va.p("psg.gif", -120, 89, 112);
        }
        this.vi.getWidth(null);
        this.vi.getHeight(null);
        this.r = 0.0;
        this.s = 0.0;
        this.t = 0.0;
        this.z = 0.4;
        this.dp = 0.3;
        this.dd = 0.5;
        this.dv = 2.0;
        this.di = 180.0;
        this.k = 360.0;
        this.dr = 80;
        final String parameter = this.getParameter("width");
        if (parameter != null) {
            this.p = Integer.valueOf(parameter, 10);
        }
        final String parameter2 = this.getParameter("height");
        if (parameter2 != null) {
            this.d = Integer.valueOf(parameter2, 10);
        }
        final String parameter3 = this.getParameter("speed");
        if (parameter3 != null) {
            final double doubleValue = Double.valueOf(parameter3);
            this.z *= doubleValue;
            this.dp *= doubleValue;
            this.dd *= doubleValue;
        }
        final String parameter4 = this.getParameter("autoPan");
        if (parameter4 != null && parameter4.compareTo("no") == 0) {
            this.ae = false;
            this.dr = 0;
        }
        final String parameter5 = this.getParameter("autoScale");
        if (parameter5 != null) {
            if (parameter5.compareTo("no") == 0) {
                this.ac = false;
            }
            else if (parameter5.compareTo("yes") == 0) {
                this.ac = true;
            }
        }
        final String parameter6 = this.getParameter("hsTransparency");
        if (parameter6 != null) {
            this.ay = Integer.valueOf(parameter6, 10);
        }
        final String parameter7 = this.getParameter("autoPanSpeed");
        if (parameter7 != null) {
            this.dx = Integer.valueOf(parameter7, 10);
        }
        final String parameter8 = this.getParameter("autoPanDelay");
        if (parameter8 != null) {
            this.dy = Integer.valueOf(parameter8, 10);
        }
        final String parameter9 = this.getParameter("autoPanTimeout");
        if (parameter9 != null) {
            this.ad = Long.valueOf(parameter9, 10) * 1000L * 60L;
        }
        int intValue = -1;
        int intValue2 = -1;
        int intValue3 = -1;
        int intValue4 = -1;
        final String parameter10 = this.getParameter("titlePos");
        if (parameter10 != null) {
            intValue = Integer.valueOf(parameter10, 10);
        }
        final String parameter11 = this.getParameter("titleForegroundColor");
        if (parameter11 != null) {
            intValue2 = Integer.valueOf(parameter11, 16);
        }
        final String parameter12 = this.getParameter("titleBackgroundColor");
        if (parameter12 != null) {
            intValue3 = Integer.valueOf(parameter12, 16);
        }
        final String parameter13 = this.getParameter("titleBorderColor");
        if (parameter13 != null) {
            intValue4 = Integer.valueOf(parameter13, 16);
        }
        final String parameter14 = this.getParameter("hq");
        if (parameter14 != null) {
            this.aa = Integer.valueOf(parameter14, 16);
        }
        final String parameter15 = this.getParameter("maxFps");
        if (parameter15 != null) {
            this.q = Integer.valueOf(parameter15, 16);
        }
        this.add(this.vb = new _zc(this, this.va, this.vd, 2));
        this.vc = new Stack();
        this.SetTitle(this.ve, intValue, intValue2, intValue3, intValue4);
        this.vv = new Dialog(new Frame(), "PixScreen Help", true);
        this.add(this.an = new _zc(this, this.va, this.vd, 1));
        this.an.move(4, 25);
        this.an.resize(500, 208);
        this.vv.add(this.an);
        this.vv.resize(500, 226);
        this.p(this.vv);
        this.vn = new Stack();
        this.b = new int[this.p * this.d];
        this.c = this.createImage(this);
        _zh.p = this;
        _zh.i();
        final String parameter16 = this.getParameter("world");
        if (parameter16 != null) {
            this.p(parameter16);
        }
        if (this.a != null) {
            this.d(this.a);
        }
        if (this.o == null) {
            this.o = new Thread(this);
            this.m = true;
            this.o.start();
        }
        if (this.vd.p != -1) {
            this.p(this.va.p(1003) + this.vd.d, -1, -1, -1, -1);
        }
    }
    
    private final synchronized void v() {
        if (this.j != null) {
            this.j.i();
        }
        this.j = null;
        this.g = null;
        this.h = null;
        this.am = null;
        this.as = null;
        this.ar = null;
        System.gc();
    }
    
    protected final _zh p(final int n, final int n2) {
        final int n3 = this.b[n2 * this.p + n] >> 24;
        if (this.j != null && n3 != 0) {
            return this.j.p(n3);
        }
        return null;
    }
    
    private final Image p(final Image image, final int n, final int n2, final int n3) {
        final Image image2 = this.createImage(20, 20);
        final Graphics graphics = image2.getGraphics();
        graphics.setColor(new Color(-4342339));
        graphics.fillRect(0, 0, 20, 20);
        int n4 = 2;
        int n5 = 2;
        Color color;
        Color color2;
        if (n2 == 0) {
            n4 = 3;
            n5 = 3;
            color = new Color(-10526881);
            color2 = new Color(-1);
        }
        else {
            color = new Color(-1);
            color2 = new Color(-10526881);
        }
        if (this.nx) {
            if (n == 0) {
                if (this.ny == null) {
                    graphics.drawImage(image, n4, -16 * n + n5, this);
                }
                else {
                    graphics.drawImage(this.ny, n4, -16 * n + n5, this);
                }
            }
            else {
                graphics.drawImage(image, n4, -16 * n + n5, this);
            }
        }
        else {
            graphics.drawImage(image, n4, -16 * n + n5, this);
        }
        graphics.setColor(new Color(-4342339));
        graphics.setColor(color);
        graphics.drawRect(0, 0, 20, 20);
        graphics.setColor(color2);
        graphics.drawLine(19, 0, 19, 20);
        graphics.drawLine(1, 19, 20, 19);
        if (n3 == 1) {
            graphics.setColor(new Color(-1179648));
            graphics.drawLine(4, 4, 15, 15);
            graphics.drawLine(4, 15, 15, 4);
        }
        return image2;
    }
    
    public final void addConsumer(final ImageConsumer ak) {
        (this.ak = ak).setColorModel(this.e);
        this.ak.setDimensions(this.p, this.d);
        this.ak.setHints(1);
    }
    
    private final synchronized void p(final int n) {
        if (n == 502) {
            final _zt zt = this.vn.pop();
            this.dq = true;
            this.dk = zt.p;
            this.dm = zt.d;
            this.do = zt.a;
            this.d(zt.p);
        }
    }
    
    private final void p() {
        this.ax.remove(this.aw[5]);
        this.ax.remove(this.aw[6]);
        this.ax.remove(this.aw[7]);
        this.ax.remove(this.aw[1]);
        this.ax.remove(this.aw[2]);
        int n = 0;
        if (this.aj) {
            ++n;
        }
        if (this.ao != 3) {
            if (this.nx) {
                n = 0;
                this.ax.add(this.aw[0], n++);
                this.aw[0].repaint();
            }
            this.ax.add(this.aw[1], n++);
            this.ax.add(this.aw[2], n++);
            this.aw[1].repaint();
            this.aw[2].repaint();
            this.ax.validate();
            ++n;
        }
        else {
            if (this.nx) {
                n = 0;
                this.ax.add(this.aw[0], n++);
                this.aw[0].repaint();
            }
            this.ax.add(this.aw[1], n++);
            this.ax.add(this.aw[2], n++);
            this.ax.add(this.aw[7], n++);
            this.aw[1].repaint();
            this.aw[2].repaint();
            this.aw[7].repaint();
            this.ax.validate();
            ++n;
        }
        if (this.j != null) {
            if (this.j.p.size() > 0) {
                this.ax.add(this.aw[5], n++);
                this.aw[5].repaint();
            }
            this.ax.validate();
        }
        if (this.vn.size() > 0) {
            this.ax.add(this.aw[6], n++);
            this.aw[6].repaint();
            this.ax.validate();
        }
        this.ax.resize(20 * this.ax.countComponents(), 20);
        this.ax.validate();
    }
    
    private final synchronized void d(final int n) {
        if (n == 501) {
            this.dw = -10.0;
            this.dr = 90;
            this.nn = true;
            if (this.np == -1) {
                this.np = this.j.p.m;
                this.nd = this.j.p.o;
            }
            this.nc = this.np;
            this.ne = this.nd;
            this.ng = this.np;
            this.nh = this.nd;
            return;
        }
        this.dw = 0.0;
        this.dr = 5;
        this.nn = false;
        this.nm = (int)(this.j.p.m * this.nl + 0.5);
        if (this.g.getWidth(null) != this.j.p.j) {
            this.nq = (int)(this.j.d() / 2 * this.nl + 0.5);
        }
        else {
            this.nq = this.nm;
        }
        this.no = (int)(this.j.p.o * this.nl + 0.5);
        if (this.g.getHeight(null) != this.nb) {
            this.nr = (int)(this.j.p.q / 2 * this.nl + 0.5);
        }
        else {
            this.nr = this.no;
        }
        if (this.nl != 1.0) {
            if (this.np == this.ng && this.nd == this.nh) {
                this.ns = 0;
                this.nt = 0;
            }
            else {
                this.ns = (int)(this.nl * (this.np - this.j.p.m) + 0.5);
                this.nt = (int)(this.nl * (this.nd - this.j.p.o) + 0.5);
            }
        }
        if (this.nl == 1.0) {
            this.ns = 0;
            this.nt = 0;
        }
    }
    
    public final void n() {
        final Image p = this.va.p("dat.gif", -111, -59, -114);
        this.setLayout(null);
        (this.ax = new Panel()).setLayout(new FlowLayout(0, 0, 0));
        this.add(this.ax);
        final int n = 8;
        this.aw = new _zi[n];
        for (int i = 0; i < n; ++i) {
            final Image p2 = this.p(p, i, 1, 0);
            final Image p3 = this.p(p, i, 0, 0);
            if (this.nx) {
                if (i == 0) {
                    this.aw[i] = new _zi(p3, p2, this, i, this.vp.p);
                }
                else {
                    this.aw[i] = new _zi(p3, p2, this, i, this.va.p(100 + i));
                }
            }
            else {
                this.aw[i] = new _zi(p3, p2, this, i, this.va.p(100 + i));
            }
            if (!this.aj && i == 0) {
                if (this.ny != null && this.nx) {
                    this.ax.add(this.aw[i]);
                }
            }
            else {
                this.ax.add(this.aw[i]);
            }
        }
        this.aw[5].n = this.p(p, 5, 1, 1);
        final Image p4 = this.p(p, 7, 1, 1);
        this.aw[7].n = p4;
        this.aw[7].a = p4;
        this.av = 0;
        this.ai = this.size().height - 20;
        this.al = 20 * this.ax.countComponents();
        this.ab = this.size().height;
        this.ax.move(this.av, this.ai);
        this.ax.resize(this.al - this.av, this.ab - this.ai);
        if (this.ag) {
            this.ax.setVisible(false);
            this.ah = true;
        }
    }
    
    public final void SetTitle(final String ve, final int n, final int n2, final int n3, final int n4) {
        this.p(this.ve = ve, n, n2, n3, n4);
    }
    
    public final synchronized boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 104: {
                if (this.ao != 2 && this.ao != 4 && this.ao != 6 && this.ao != 3) {
                    this.p(this.va.p(300), 1000);
                    this.repaint();
                    break;
                }
                this.aa = ((this.aa == 0) ? 1 : 0);
                if (this.j != null) {
                    if (this.aa == 0) {
                        this.j.p(false);
                        this.p(this.va.p(301), 1000);
                    }
                    else {
                        this.j.p(true);
                        this.p(this.va.p(302), 1000);
                    }
                    this.l = false;
                }
                this.dr = 5;
                break;
            }
            case 101: {
                this.a();
                break;
            }
            case 97: {
                if (this.j != null) {
                    if (this.ao == 1) {
                        this.ao = this.aq;
                        this.p(this.va.p(303), 1000);
                    }
                    else {
                        this.aq = this.ao;
                        this.ao = 1;
                        this.p(this.va.p(304), 1000);
                    }
                    this.t = this.j.d();
                    this.dc = this.j.p(this.s, this.t);
                    this.r = this.dc;
                    this.p();
                    this.a();
                    this.repaint();
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    public final boolean p(final String s) {
        String a = null;
        final StreamTokenizer streamTokenizer = new StreamTokenizer(new StringReader(s));
        streamTokenizer.whitespaceChars(123, 125);
        try {
            while (streamTokenizer.nextToken() != -1) {
                try {
                    switch (streamTokenizer.ttype) {
                        case -3: {
                            if (streamTokenizer.sval.equalsIgnoreCase("CYL") || streamTokenizer.sval.equalsIgnoreCase("FLT")) {
                                boolean b = false;
                                if (streamTokenizer.sval.equalsIgnoreCase("FLT")) {
                                    b = true;
                                }
                                streamTokenizer.nextToken();
                                final String sval = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final String sval2 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final String sval3 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final double p = this.p(streamTokenizer, sval, "dTotalYawAngle");
                                streamTokenizer.nextToken();
                                final double p2 = this.p(streamTokenizer, sval, "dDefaultYaw");
                                streamTokenizer.nextToken();
                                final double p3 = this.p(streamTokenizer, sval, "dDefaultPitch");
                                streamTokenizer.nextToken();
                                final double p4 = this.p(streamTokenizer, sval, "dDefaultFov");
                                streamTokenizer.nextToken();
                                this.j = this.p(b ? 6 : 2, sval3, sval2, p, -90.0, 90.0, p2, p3, p4, streamTokenizer.sval);
                                if (a == null) {
                                    a = sval3;
                                    continue;
                                }
                                continue;
                            }
                            else if (streamTokenizer.sval.equalsIgnoreCase("SPH") || streamTokenizer.sval.equalsIgnoreCase("CUB")) {
                                int n = 4;
                                if (streamTokenizer.sval.equalsIgnoreCase("CUB")) {
                                    n = 5;
                                }
                                streamTokenizer.nextToken();
                                final String sval4 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final String sval5 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final String sval6 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final double p5 = this.p(streamTokenizer, sval4, "dTotalYawAngle");
                                streamTokenizer.nextToken();
                                final double p6 = this.p(streamTokenizer, sval4, "dTotalYawAngle");
                                streamTokenizer.nextToken();
                                final double p7 = this.p(streamTokenizer, sval4, "dTotalYawAngle");
                                streamTokenizer.nextToken();
                                final double p8 = this.p(streamTokenizer, sval4, "dDefaultYaw");
                                streamTokenizer.nextToken();
                                final double p9 = this.p(streamTokenizer, sval4, "dDefaultPitch");
                                streamTokenizer.nextToken();
                                final double p10 = this.p(streamTokenizer, sval4, "dDefaultFov");
                                streamTokenizer.nextToken();
                                this.j = this.p(n, sval6, sval5, p5, p6, p7, p8, p9, p10, streamTokenizer.sval);
                                if (a == null) {
                                    a = sval6;
                                    continue;
                                }
                                continue;
                            }
                            else if (streamTokenizer.sval.equalsIgnoreCase("OBV")) {
                                streamTokenizer.nextToken();
                                final String sval7 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final String sval8 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final String sval9 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final double p11 = this.p(streamTokenizer, sval7, "dTotalYawAngle");
                                streamTokenizer.nextToken();
                                final int p12 = (int)this.p(streamTokenizer, sval7, "numFrames");
                                streamTokenizer.nextToken();
                                final int a2 = (int)this.p(streamTokenizer, sval7, "defaultFrame");
                                streamTokenizer.nextToken();
                                final double p13 = this.p(streamTokenizer, sval7, "AutoPanFps");
                                streamTokenizer.nextToken();
                                this.j = this.p(3, sval9, sval8, p11, -90.0, 90.0, 0.0, 0.0, 0.0, streamTokenizer.sval);
                                if (this.j != null) {
                                    this.j.p.p = p12;
                                    this.j.p.a = a2;
                                    this.j.p.p = p13;
                                }
                                if (a == null) {
                                    a = sval9;
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (streamTokenizer.sval.equalsIgnoreCase("SCE")) {
                                    streamTokenizer.nextToken();
                                    final String sval10 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final String sval11 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final String sval12 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final double p14 = this.p(streamTokenizer, sval10, "dYawPos");
                                    streamTokenizer.nextToken();
                                    final double p15 = this.p(streamTokenizer, sval10, "dPitchPos");
                                    streamTokenizer.nextToken();
                                    final int n2 = (int)this.p(streamTokenizer, sval10, "AutoPan");
                                    streamTokenizer.nextToken();
                                    final double p16 = this.p(streamTokenizer, sval10, "srcYaw");
                                    streamTokenizer.nextToken();
                                    final double p17 = this.p(streamTokenizer, sval10, "srcPitch");
                                    streamTokenizer.nextToken();
                                    final double p18 = this.p(streamTokenizer, sval10, "srcFov");
                                    streamTokenizer.nextToken();
                                    final double p19 = this.p(streamTokenizer, sval10, "tgtYaw");
                                    streamTokenizer.nextToken();
                                    final double p20 = this.p(streamTokenizer, sval10, "tgtPitch");
                                    streamTokenizer.nextToken();
                                    this.j.p(sval10, sval11, p14, p15, this, sval12, n2, p16, p17, p18, p19, p20, this.p(streamTokenizer, sval10, "tgtFov"));
                                    continue;
                                }
                                if (streamTokenizer.sval.equalsIgnoreCase("url") || streamTokenizer.sval.equalsIgnoreCase("aud") || streamTokenizer.sval.equalsIgnoreCase("vid") || streamTokenizer.sval.equalsIgnoreCase("eml") || streamTokenizer.sval.equalsIgnoreCase("lbl")) {
                                    int n3 = 0;
                                    if (streamTokenizer.sval.equalsIgnoreCase("url")) {
                                        n3 = 0;
                                    }
                                    else if (streamTokenizer.sval.equalsIgnoreCase("aud")) {
                                        n3 = 1;
                                    }
                                    else if (streamTokenizer.sval.equalsIgnoreCase("vid")) {
                                        n3 = 2;
                                    }
                                    else if (streamTokenizer.sval.equalsIgnoreCase("eml")) {
                                        n3 = 3;
                                    }
                                    else if (streamTokenizer.sval.equalsIgnoreCase("lbl")) {
                                        n3 = 4;
                                    }
                                    streamTokenizer.nextToken();
                                    final String sval13 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final String sval14 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final String sval15 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final double p21 = this.p(streamTokenizer, sval13, "dYawPos");
                                    streamTokenizer.nextToken();
                                    final double p22 = this.p(streamTokenizer, sval13, "dPitchPos");
                                    streamTokenizer.nextToken();
                                    this.j.p(sval13, sval14, p21, p22, this, sval15, (int)this.p(streamTokenizer, sval13, "ActivationType"), n3, 16);
                                    continue;
                                }
                                if (streamTokenizer.sval.equalsIgnoreCase("HSM")) {
                                    streamTokenizer.nextToken();
                                    this.j.n = streamTokenizer.sval;
                                    continue;
                                }
                                continue;
                            }
                            break;
                        }
                        default: {
                            continue;
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
        catch (IOException ex2) {
            return false;
        }
        if (a != null) {
            this.a = a;
            this.j = null;
        }
        return true;
    }
    
    public final synchronized void i() {
        switch (this.ao) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: {
                if (this.f && this.j != null && this.ak != null) {
                    if (this.ao == 3) {
                        this.j.p.d = this.j.p.d;
                        this.j.p.p(this.b, this.nl, this.np, this.nd);
                    }
                    else {
                        this.j.p(this.b, this.r, this.s, this.t, this);
                    }
                    this.ak.setPixels(0, 0, this.p, this.d, this.e, this.b, 0, this.p);
                    this.ak.imageComplete(2);
                    return;
                }
                break;
            }
        }
    }
    
    public final double p(final StreamTokenizer streamTokenizer, final String s, final String s2) throws Exception {
        if (streamTokenizer.ttype != -2) {
            throw new Exception("Error while parsing " + s + ". Double expected for " + s2 + " but get \"" + streamTokenizer.sval + "\" instead");
        }
        return streamTokenizer.nval;
    }
    
    public final void p(final int n, final int n2) {
        switch (n2) {
            case 1: {
                this.a(n);
            }
            case 2: {
                this.d(n);
            }
            case 3: {
                this.n(n);
            }
            case 4: {
                if (this.vv == null || n != 502) {
                    break;
                }
                if (this.vv.isVisible()) {
                    this.vv.setVisible(false);
                    this.vv.dispose();
                    return;
                }
                this.vv.setVisible(true);
            }
            case 5: {
                this.i(n);
            }
            case 6: {
                this.p(n);
            }
            case 7: {
                this.v(n);
            }
            case 0: {
                if (n == 502) {
                    final AppletContext appletContext = this.getAppletContext();
                    try {
                        URL url;
                        if (this.nx) {
                            url = new URL(this.vp.p);
                        }
                        else {
                            url = new URL(this.getDocumentBase(), this.va.p(200));
                        }
                        appletContext.showDocument(url, "_blank");
                        return;
                    }
                    catch (MalformedURLException ex) {}
                    break;
                }
                break;
            }
        }
    }
    
    private final void p(final Window window) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        window.move((screenSize.width - window.size().width) / 2, (screenSize.height - window.size().height) / 2);
        if (screenSize.width < window.size().width) {
            window.resize(screenSize.width, window.size().height);
        }
    }
    
    public final synchronized void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.v != null && this.v.length() > 0) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int height = fontMetrics.getHeight();
            final int stringWidth = fontMetrics.stringWidth(this.v);
            int n = (this.p - stringWidth) / 2;
            final int n2 = this.d / 2;
            if (n < 0) {
                n = 2;
            }
            graphics.setColor(new Color(68));
            graphics.fillRect(n, n2, stringWidth + 8, height + 6);
            graphics.setColor(new Color(136));
            graphics.drawRect(n, n2, stringWidth + 7, height + 5);
            graphics.setColor(new Color(16777215));
            graphics.drawString(this.v, n + 4, n2 + height);
            this.v = null;
            try {
                Thread.sleep(this.i);
            }
            catch (InterruptedException ex) {}
        }
        if (this.j == null) {
            this.p(graphics);
            return;
        }
        this.g.getWidth(null);
        this.g.getHeight(null);
        switch (this.ao) {
            case 1: {
                if (this.j == null) {
                    break;
                }
                final int width = this.g.getWidth(null);
                final int height2 = this.g.getHeight(null);
                if (width == -1) {
                    break;
                }
                final int n3 = -((int)(this.r / this.j.p() * width) - this.p / 2);
                final int n4 = (height2 - this.d) / 2;
                final int n5 = -n4 + (int)((this.s - this.dl) / (this.db - this.dl) * n4);
                graphics.drawImage(this.g, n3, n5, null);
                if (n3 > 0) {
                    graphics.drawImage(this.g, -width + n3, n5, null);
                }
                if (n3 < -(width - this.p)) {
                    graphics.drawImage(this.g, n3 + width, n5, null);
                    return;
                }
                break;
            }
            default: {
                if (this.c != null) {
                    graphics.drawImage(this.c, 0, 0, null);
                    this.c();
                    return;
                }
                break;
            }
        }
    }
    
    public final void p(final int n, final int n2, final int n3, final boolean b) {
        Label_0538: {
            switch (this.dr) {
                case 0:
                case 7: {
                    switch (n) {
                        case 123: {
                            if (this.ar != null) {
                                this.dr = 112;
                                this.ar.p(20);
                                return;
                            }
                            break Label_0538;
                        }
                        case 120: {
                            if (this.o != null) {
                                switch (this.ao) {
                                    case 1:
                                    case 2:
                                    case 4:
                                    case 5:
                                    case 6: {
                                        if (b) {
                                            this.dr = 20;
                                            break;
                                        }
                                        this.dr = 10;
                                        break;
                                    }
                                    case 3: {
                                        this.dr = 105;
                                        break;
                                    }
                                }
                                this.ds = n2;
                                this.dt = n3;
                                this.du = 0.0;
                                this.dw = 0.0;
                                return;
                            }
                            break Label_0538;
                        }
                    }
                    break;
                }
                case 110:
                case 111:
                case 112: {
                    switch (n) {
                        case 120: {
                            this.dr = 112;
                            if (this.ar != null) {
                                this.ar.p(0);
                                return;
                            }
                            break Label_0538;
                        }
                        case 121: {
                            this.dr = 112;
                            if (this.ar != null) {
                                this.ar.p(10);
                                return;
                            }
                            break Label_0538;
                        }
                    }
                    break;
                }
                case 80:
                case 100: {
                    switch (n) {
                        case 120: {
                            switch (this.ao) {
                                case 1:
                                case 2:
                                case 4:
                                case 5:
                                case 6: {
                                    if (b) {
                                        this.dr = 20;
                                        break;
                                    }
                                    this.dr = 10;
                                    break;
                                }
                                case 3: {
                                    this.dr = 105;
                                    break;
                                }
                            }
                            this.ds = n2;
                            this.dt = n3;
                            this.du = 0.0;
                            this.dw = 0.0;
                            if (this.au && this.j != null) {
                                this.j.a();
                                return;
                            }
                            break Label_0538;
                        }
                    }
                    break;
                }
                case 10:
                case 20: {
                    switch (n) {
                        case 121: {
                            if (this.o != null) {
                                this.du = 0.0;
                                this.dw = 0.0;
                                this.dr = 7;
                                return;
                            }
                            break Label_0538;
                        }
                    }
                    break;
                }
                case 105: {
                    switch (n) {
                        case 121: {
                            this.dr = 5;
                            return;
                        }
                        default: {
                            break Label_0538;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private final synchronized void i(final int n) {
        if (n == 501) {
            if (this.au) {
                this.au = false;
                this.j.v();
                if (this.aw[5] != null) {
                    this.aw[5].v = false;
                }
            }
            else {
                this.au = true;
                this.j.a();
                if (this.aw[5] != null) {
                    this.aw[5].v = true;
                }
            }
            this.dr = 5;
            this.i();
            this.repaint();
        }
    }
    
    public final synchronized void TransitTo6(final String s, final double n, final double n2, final double n3, final int n4, final double n5) {
        this.TransitTo9(s, this.r, this.s, this.t, n, n2, n3, n4, n5);
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        this.p(121, n, n2, event.shiftDown());
        return true;
    }
    
    public PixScreen() {
        this.l = false;
        this.q = 60.0;
        this.y = 0.05;
        this.da = 200.0;
        this.dx = 3;
        this.dy = 50;
        this.ad = 180000L;
        this.ac = true;
        this.ae = true;
        this.ag = false;
        this.aj = true;
        this.ao = 2;
        this.au = true;
        this.az = 10;
        this.np = -1;
        this.nd = -1;
        this.na = false;
        this.nn = false;
        this.nv = true;
        this.ni = false;
        this.nl = 1.0;
        this.nf = 0.0;
        this.ng = 0;
        this.nh = 0;
        this.nj = false;
        this.nk = false;
        this.nm = 0;
        this.no = 0;
        this.nq = 0;
        this.nr = 0;
        this.ns = 0;
        this.nt = 0;
        this.nu = 0;
        this.nw = 0;
        this.nx = false;
        this.ve = "";
        this.vm = false;
    }
    
    private final _zy p(final String s) {
        return this.at.get(s);
    }
    
    private final _zy p(final int n, final String s, final String s2, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final String s3) {
        final _zy zy = this.at.get(s);
        if (zy != null) {
            return zy;
        }
        final _zy zy2 = new _zy(n, s, s2, n2, n3, n4, n5, n6, n7, s3);
        this.at.put(s, zy2);
        return zy2;
    }
    
    public final synchronized boolean p() {
        if (!this.f && this.g != null) {
            final int width = this.g.getWidth(null);
            final int height = this.g.getHeight(null);
            if (width != -1 && height != -1 && this.h == null) {
                this.p(this.g, width, height);
                this.h.startGrabbing();
            }
            if (this.h != null && this.h.getWidth() != -1 && this.h.getHeight() != -1 && this.h.getPixels() != null) {
                this.l();
            }
        }
        if (this.j == null) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            return false;
        }
        if (!this.af && this.h != null && (this.h.getStatus() & 0x20) == 0x20) {
            this.g();
        }
        this.j.p(this.aa != 0);
        Label_0863: {
            switch (this.dr) {
                case 0:
                case 110: {
                    try {
                        Thread.sleep(50L);
                    }
                    catch (InterruptedException ex2) {}
                    return !this.af && this.h != null;
                }
                case 5: {
                    this.dr = 0;
                    if (this.aa == 0) {
                        this.j.p(true);
                        break;
                    }
                    return false;
                }
                case 7: {
                    this.r += this.u * this.z * this.y * this.t / this.dn;
                    this.s += this.w * this.dp * this.y * this.t / this.dn;
                    this.t += this.x * this.dd * this.y;
                    if (this.u == 0.0 && this.w == 0.0 && this.x == 0.0) {
                        this.dr = 5;
                        break;
                    }
                    final double n = this.da * this.y;
                    if (this.u > 0.0) {
                        this.u -= n;
                        if (this.u < 0.0) {
                            this.u = 0.0;
                        }
                    }
                    if (this.u < 0.0) {
                        this.u += n;
                        if (this.u > 0.0) {
                            this.u = 0.0;
                        }
                    }
                    if (this.w > 0.0) {
                        this.w -= n;
                        if (this.w < 0.0) {
                            this.w = 0.0;
                        }
                    }
                    if (this.w < 0.0) {
                        this.w += n;
                        if (this.w > 0.0) {
                            this.w = 0.0;
                        }
                    }
                    if (this.x > 0.0) {
                        this.x -= n;
                        if (this.x < 0.0) {
                            this.x = 0.0;
                        }
                    }
                    if (this.x >= 0.0) {
                        break;
                    }
                    this.x += n;
                    if (this.x > 0.0) {
                        this.x = 0.0;
                        break;
                    }
                    break;
                }
                case 111: {
                    this.dr = 110;
                    if (this.aa == 0) {
                        this.j.p(true);
                        break;
                    }
                    return false;
                }
                case 112: {
                    this.dr = 111;
                    this.j.p(true);
                    this.dr = 110;
                    break;
                }
                case 80: {
                    int dy = this.dy;
                    if (System.currentTimeMillis() - this.ap > this.ad) {
                        this.dr = 5;
                    }
                    if (this.ao == 3) {
                        if (this.du > 0.0) {
                            final _zj p = this.j.p;
                            ++p.d;
                        }
                        else {
                            final _zj p2 = this.j.p;
                            --p2.d;
                        }
                        dy = (int)(this.j.p() / this.j.p.p * 16.666666666666668);
                    }
                    if (dy > 0) {
                        try {
                            Thread.sleep(dy);
                        }
                        catch (InterruptedException ex3) {}
                    }
                    if (this.ao != 3) {
                        break Label_0863;
                    }
                    break;
                }
                case 10: {
                    this.u = this.du;
                    this.w = this.dw;
                    this.r += this.du * this.z * this.y * this.t / this.dn;
                    this.s += this.dw * this.dp * this.y * this.t / this.dn;
                    if (this.j.d() && this.dw != 0.0) {
                        this.dn = this.j.p(this.p, this.d);
                    }
                    if (this.dr == 80 && !this.af && this.h != null && (this.h.getStatus() & 0x20) == 0x20) {
                        this.g();
                        break;
                    }
                    break;
                }
                case 20:
                case 90: {
                    if (this.ao == 2 || this.ao == 6 || this.ao == 4 || this.ao == 5) {
                        this.x = -this.dw;
                        this.t += -this.dw * this.dd * this.y;
                        break;
                    }
                    if (this.ao != 3) {
                        break;
                    }
                    if (this.na) {
                        if (this.nl <= 5.0) {
                            this.nl += 0.01;
                            break;
                        }
                        break;
                    }
                    else {
                        if (!this.nn) {
                            break;
                        }
                        if (this.ne < this.j.p.o) {
                            if (this.nd < this.j.p.o) {
                                this.nd += (int)(this.nl + 0.5);
                            }
                        }
                        else if (this.ne > this.j.p.o && this.nd > this.j.p.o) {
                            this.nd -= (int)(this.nl + 0.5);
                        }
                        if (this.nc < this.j.p.m) {
                            if (this.np < this.j.p.m) {
                                this.np += (int)(this.nl + 0.5);
                            }
                        }
                        else if (this.nc > this.j.p.m && this.np > this.j.p.m) {
                            this.np -= (int)(this.nl + 0.5);
                        }
                        this.nl -= 0.01;
                        if (this.nl < 1.0) {
                            this.nl = 1.0;
                        }
                        if (this.nl == 1.0 && this.ni) {
                            this.aw[7].a = this.aw[7].n;
                            this.aw[7].repaint();
                            this.aw[7].v = false;
                            this.ni = false;
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 100: {
                    final double y = this.y;
                    this.dj += y;
                    if (this.dj > this.dh) {
                        final double n2 = this.dh - (this.dj - y);
                        this.dr = 101;
                    }
                    else {
                        this.r += this.de * y;
                        this.s += this.df * y;
                        this.t += this.dg * y;
                        this.d();
                    }
                    return true;
                }
                case 101: {
                    this.dr = 0;
                    if (this.n.compareTo(this.a) != 0) {
                        this.r = this.dk;
                        this.s = this.dm;
                        this.t = this.do;
                    }
                    if (!this.n.equals(this.a)) {
                        this.d(this.n);
                    }
                    else {
                        this.j.n();
                        if (this.au) {
                            this.j.a();
                        }
                        this.dr = 5;
                    }
                    return false;
                }
            }
        }
        this.d();
        return true;
    }
    
    private final synchronized void n(final int n) {
        if (n == 502) {
            this.e();
        }
    }
    
    public final synchronized void p(final String v, final int i) {
        this.v = v;
        this.i = i;
    }
    
    public final synchronized void TransitTo9(final String n, final double n2, final double n3, final double n4, final double dk, final double dm, final double do1, final int n5, final double n6) {
        this.n = n;
        this.dk = dk;
        this.dm = dm;
        this.do = do1;
        this.dq = true;
        if (!n.equals(this.a)) {
            this.p(n2, n3, n4, n6);
        }
        else {
            this.p(dk, dm, do1, n6);
        }
        if (n5 == 1) {
            this.ae = true;
        }
        if (n5 == 0) {
            this.ae = false;
        }
        this.vn.push(new _zt(this.a, this.r, this.s, this.t));
    }
    
    private final void p(final Image image, final int n, final int n2) {
        if (!this.ac) {
            this.h = new PixelGrabber(this.g, 0, 0, -1, -1, false);
            return;
        }
        if (n * n2 > 1000000) {
            final double sqrt = Math.sqrt(1000000.0 / (n * n2));
            final int n3 = (int)(n * sqrt);
            final int n4 = (int)(n2 * sqrt);
            this.h = new PixelGrabber(image.getScaledInstance(n3, n4, 1), 0, 0, n3, n4, false);
            return;
        }
        this.h = new PixelGrabber(this.g, 0, 0, -1, -1, false);
    }
    
    public final boolean isConsumer(final ImageConsumer imageConsumer) {
        return true;
    }
    
    public final void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public final synchronized void l() {
        if (this.h == null) {
            return;
        }
        if (this.k == 0.0) {
            this.k = 1.0 / (this.h.getHeight() / 2.0 / Math.tan(0.46227777777777784)) * this.h.getWidth() * 180.0 / 3.14;
            if (this.k > 360.0) {
                this.k = 360.0;
            }
        }
        if (this.j == null) {
            this.j = this.p(this.a);
            if (this.j == null) {
                this.p("Scene Not Found: " + this.a, 10000);
                this.paint(this.getGraphics());
                return;
            }
            if (this.j.n == 0.0) {
                this.j.n = this.k;
            }
            this.ao = this.j.p;
            this.j.p((int[])this.h.getPixels(), this.h.getWidth(), this.h.getHeight(), this.p, this.d, this.j.n, this.j.v, this.j.i);
            if (this.ao == 3) {
                this.j.p.p = this.j.p.p;
                this.j.p.d = this.j.p.a;
            }
            this.SetTitle(this.j.d, -1, -1, -1, -1);
            final Applet applet = this.getAppletContext().getApplet("myBard");
            if (applet != null && applet instanceof CBard) {
                ((CBard)applet).SetStory(this.j.a);
            }
            this.f = true;
            this.e = this.h.getColorModel();
        }
        if (this.dq) {
            this.r = this.dk;
            this.s = this.dm;
            this.t = this.do;
            this.dq = false;
        }
        else {
            this.r = this.j.p;
            this.s = this.j.d;
            this.t = this.j.a;
        }
        this.dn = this.j.p(this.p, this.d);
        if (this.t > this.dn) {
            this.t = this.dn;
        }
        if (this.t == 0.0) {
            if (this.dn >= 15.0) {
                this.t = this.dn - 10.0;
            }
            else {
                this.t = this.dn;
            }
        }
        this.d();
        if (this.ao == 3 || this.ao == 1) {
            for (int n = this.c.getWidth(null) * this.c.getHeight(null), i = 0; i < n; ++i) {
                this.b[i] = 0;
            }
        }
    }
    
    public final void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.requestFocus();
        this.p(120, n, n2, event.shiftDown());
        return true;
    }
    
    private final void p(final Graphics graphics) {
        if (graphics == null || this.vi == null) {
            return;
        }
        if (this.vi != null) {
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(0, 0, this.p, this.d);
            final int width = this.vi.getWidth(null);
            final int height = this.vi.getHeight(null);
            final int n = (this.p - width) / 2;
            final int n2 = (this.d - height) / 2;
            graphics.drawImage(this.vi, n, n2 - 2, null);
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.getFont());
            graphics.setColor(Color.black);
            graphics.drawString("Powered by PixMaker", (int)((this.p - fontMetrics.stringWidth("Powered by PixMaker")) / 2.0 + 0.5), n2 + height + 10);
        }
    }
    
    public final synchronized void c() {
        final Graphics graphics = this.getGraphics();
        if (this.vf != null) {
            this.vm = true;
            this.vo = this.vg - 3;
            this.vq = this.vh - 12 + 30;
            graphics.setColor(Color.yellow);
            graphics.fillRect(this.vo, this.vq, this.vj, this.vk);
            graphics.setColor(Color.black);
            graphics.drawRect(this.vo, this.vq, this.vj, this.vk);
            graphics.drawString(this.vf, this.vg, this.vh + 30);
        }
    }
    
    public final void p(final double n, final double n2, double n3, final double n4) {
        if (this.j == null) {
            return;
        }
        double n5 = 0.0;
        double n6 = 0.0;
        double n7 = 0.0;
        if (n3 > this.dn) {
            n3 = this.dn;
        }
        if (n3 < this.dv) {
            n3 = this.dv;
        }
        this.dr = 100;
        this.j.v();
        if (n != -1.0) {
            n5 = n - this.r;
            if (n5 > 180.0) {
                n5 -= 360.0;
            }
            if (n5 < -180.0) {
                n5 += 360.0;
            }
        }
        if (n2 != -999.0) {
            n6 = n2 - this.s;
        }
        if (n3 != -1.0) {
            n7 = n3 - this.t;
        }
        double n8 = 0.0;
        if (Math.abs(n5) > Math.abs(n6) && Math.abs(n5) > Math.abs(n7)) {
            n8 = Math.abs(n5);
        }
        if (Math.abs(n6) > Math.abs(n5) && Math.abs(n6) > Math.abs(n7)) {
            n8 = Math.abs(n6);
        }
        if (Math.abs(n7) > Math.abs(n5) && Math.abs(n7) > Math.abs(n6)) {
            n8 = Math.abs(n7);
        }
        final double dh = n8 / n4;
        this.de = n5 / dh;
        this.df = n6 / dh;
        this.dg = n7 / dh;
        this.dh = dh;
        if (this.ao == 3) {
            this.dh = 0.0;
        }
        this.dj = 0.0;
        if (this.aa == 0) {
            this.j.p(false);
        }
    }
    
    private final synchronized void p(final String v, final int vl, final int n, final int n2, final int n3) {
        this.vb.v = v;
        if (v.length() == 1 && v.equalsIgnoreCase(" ")) {
            this.vb.setVisible(false);
        }
        else {
            this.vb.setVisible(true);
        }
        if (vl != -1) {
            this.vl = vl;
        }
        if (n != -1) {
            this.vb.l = new Color(n);
        }
        if (n2 != -1) {
            this.vb.b = new Color(n2);
        }
        if (n3 != -1) {
            this.vb.i = new Color(n3);
        }
        final int n4 = 100;
        final int n5 = 20;
        final int d = this.vb.d();
        final int p5 = this.vb.p();
        Point point = null;
        switch (this.vl) {
            case 0: {
                point = new Point(0, this.d - n5 - d);
                break;
            }
            case 1: {
                point = new Point(0, 0);
                break;
            }
            case 2: {
                point = new Point(this.p - p5, 0);
                break;
            }
            case 3: {
                point = new Point(this.p - p5, this.d - d);
                break;
            }
            case 4: {
                point = new Point(n4, this.d - d);
                break;
            }
        }
        if (point != null) {
            this.vb.move(point.x, point.y);
            this.vb.resize(p5, d);
        }
        this.vb.repaint();
    }
    
    public final void run() {
        Thread.currentThread().setPriority(1);
        while (this.m) {
            if (!this.l && this.j != null) {
                this.l = true;
                this.a();
            }
            if (this.p()) {
                this.i();
                this.repaint();
                if (this.dz <= 0) {
                    continue;
                }
                try {
                    Thread.sleep(this.dz);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public final void init() {
        this.va = new _zo(this);
        this.vd = new _zf(this.va, this);
        this.va.p("ss.gif");
        final String parameter = this.getParameter("licenseFile");
        if (parameter != null) {
            this.vd.p = true;
            this.vd.p = parameter;
        }
        this.vd.p(this.getCodeBase());
        final String parameter2 = this.getParameter("customizationFile");
        if (parameter2 != null) {
            this.nx = true;
            (this.vp = new v(this.va)).p(this.getCodeBase() + parameter2, this);
            this.ny = this.vp.p;
            this.nz = this.vp.d;
            this.vi = this.vp.d;
        }
        if ((this.vd.p & 0xFFF) == 0x7B5 || (this.vd.p & 0xFFF) == 0x7B6 || (this.vd.p & 0xFFF) == 0x3F2 || (this.vd.p & 0xFFF) == 0x3F3) {
            if (this.nx) {
                this.aj = true;
            }
            else {
                this.aj = false;
            }
            final String parameter3 = this.getParameter("autoHide");
            if (parameter3 != null && parameter3.compareTo("yes") == 0) {
                this.ag = true;
            }
        }
        this.n();
        this.at = new Hashtable();
    }
    
    private final void b() {
        final int n = -1;
        this.nd = n;
        this.np = n;
        this.nl = 1.0;
        final boolean b = false;
        this.nt = (b ? 1 : 0);
        this.ns = (b ? 1 : 0);
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.dr == 90) {
            return true;
        }
        this.requestFocus();
        this.mouseMove(event, n, n2);
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        this.vg = n;
        this.vh = n2;
        if (this.o != null && this.j != null) {
            switch (this.dr) {
                case 10:
                case 20:
                case 105: {
                    this.du = n - this.ds;
                    this.dw = this.dt - n2;
                    if (this.dr != 105 || this.j == null) {
                        break;
                    }
                    if (this.ni) {
                        if (this.nl == 1.0) {
                            return false;
                        }
                        this.j.p.p = true;
                        this.requestFocus();
                        if (n < this.j.p.e || n > this.j.p.f) {
                            return false;
                        }
                        if (n2 < this.j.p.g || n2 > this.j.p.h) {
                            return false;
                        }
                        if (this.nj) {
                            if (this.ns < 0) {
                                if (n > this.ds) {
                                    if (this.nk) {
                                        return false;
                                    }
                                }
                                else {
                                    this.nj = !this.nj;
                                }
                            }
                            else if (n < this.ds) {
                                if (this.nk) {
                                    return false;
                                }
                            }
                            else {
                                this.nj = !this.nj;
                            }
                        }
                        if (this.nk) {
                            if (this.nt < 0) {
                                if (n2 > this.dt) {
                                    if (this.nj) {
                                        return false;
                                    }
                                }
                                else {
                                    this.nk = !this.nk;
                                }
                            }
                            else if (n2 < this.dt) {
                                if (this.nj) {
                                    return false;
                                }
                            }
                            else {
                                this.nk = !this.nk;
                            }
                        }
                        if (n > this.ds && !this.nj) {
                            this.ns -= n - this.ds;
                            this.nu += n - this.ds;
                        }
                        if (n < this.ds && !this.nj) {
                            this.ns += this.ds - n;
                            this.nu -= this.ds - n;
                        }
                        if (n2 > this.dt && !this.nk) {
                            this.nt -= n2 - this.dt;
                            this.nw += n2 - this.dt;
                        }
                        if (n2 < this.dt && !this.nk) {
                            this.nt += this.dt - n2;
                            this.nw -= this.dt - n2;
                        }
                        final int n3 = (int)(this.ns / this.nl + 0.5);
                        final int n4 = (this.ns > 0) ? this.ns : (-this.ns);
                        final int n5 = (int)(this.nt / this.nl + 0.5);
                        final int n6 = (this.nt > 0) ? this.nt : (-this.nt);
                        int n7;
                        if (this.nm != this.nq && this.ns > 0) {
                            n7 = this.nq;
                        }
                        else {
                            n7 = this.nm;
                        }
                        int n8;
                        if (this.no != this.nr && this.nt > 0) {
                            n8 = this.nr;
                        }
                        else {
                            n8 = this.no;
                        }
                        if (this.j.p.m + n4 >= n7 && !this.nj) {
                            this.nj = !this.nj;
                        }
                        if (this.j.p.m + n4 < n7 && !this.nj) {
                            this.np = this.j.p.m + n3;
                        }
                        if (this.j.p.o + n6 >= n8 && !this.nk) {
                            this.nk = !this.nk;
                        }
                        if (this.j.p.o + n6 < n8 && !this.nk) {
                            this.nd = this.j.p.o + n5;
                        }
                        this.ds = n;
                        this.dt = n2;
                        break;
                    }
                    else {
                        if (this.dr == 90) {
                            return true;
                        }
                        final int n9 = n - this.ds;
                        if (n9 > this.az) {
                            this.j.p.p(1);
                            this.ds = n;
                        }
                        if (n9 < -this.az) {
                            this.j.p.p(-1);
                            this.ds = n;
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 0:
                case 110: {
                    if (this.j != null) {
                        this.ar = this.p(n, n2);
                        if (this.ar != this.as) {
                            if (this.as != null) {
                                this.as.p(30);
                                this.dr = 0;
                            }
                            if (this.ar != null) {
                                this.p(123, n, n2, false);
                            }
                            if (this.as != null && this.ar == null) {
                                this.dr = 5;
                            }
                            this.as = this.ar;
                        }
                    }
                    this.paint(this.getGraphics());
                    break;
                }
            }
            if (this.ag) {
                if (n > this.av && n < this.al && n2 > this.ai && n2 < this.ab) {
                    if (this.ah) {
                        this.ax.setVisible(true);
                        this.ah = false;
                    }
                }
                else if (!this.ah) {
                    this.ax.setVisible(false);
                    this.ah = true;
                }
            }
        }
        return true;
    }
}
