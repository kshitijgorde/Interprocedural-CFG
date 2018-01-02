import java.awt.Point;
import java.io.IOException;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Window;
import java.awt.Frame;
import java.awt.Graphics;
import java.io.InputStream;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.net.URL;
import java.util.Hashtable;
import java.util.Date;
import java.util.Stack;
import java.awt.Dialog;
import java.awt.Panel;
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

public class uPixScreen extends Applet implements Runnable, ImageProducer
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
    public dzd j;
    public double k;
    public boolean m;
    public boolean o;
    public Thread q;
    public double r;
    public double s;
    public double t;
    public double u;
    public double w;
    public double x;
    public double y;
    public double z;
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
    private double dq;
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
    public dzh an;
    public int av;
    public int ai;
    public int al;
    public int ab;
    public boolean ac;
    public boolean ae;
    private boolean af;
    public ImageConsumer ag;
    public MediaTracker ah;
    public int aj;
    public Panel ak;
    private boolean am;
    private boolean ao;
    private boolean aq;
    public Dialog ar;
    public long as;
    public dzi at;
    public dzi au;
    public int aw;
    private dzh ax;
    public Stack ay;
    public String az;
    public String np;
    public String nd;
    public int na;
    public long nn;
    public Date nv;
    private Hashtable ni;
    
    public static final byte[] p(final byte[] array, final int n) {
        final int n2 = array.length - 3;
        final byte[] array2 = new byte[n2];
        int n3 = ((array[n2] & 0xFF) << 8 | (array[n2 + 1] & 0xFF)) << 8 | (array[n2 + 2] & 0xFF);
        for (int i = n2 - 1; i >= 0; --i) {
            final int n4 = (n3 | array[i] << 24) * n;
            n3 = (n4 & 0xFFFFFF);
            array2[i] = (byte)(n4 >>> 24);
        }
        return array2;
    }
    
    public final void stop() {
        if (this.q != null) {
            this.m = false;
            try {
                this.q.join(1000L);
            }
            catch (InterruptedException ex) {
                System.out.println("No answer from renderer: interrupted");
            }
            this.q = null;
        }
        this.b = null;
        this.c = null;
        this.ag = null;
        this.n();
    }
    
    public final void a(final String s) {
        if (this.ax == null) {
            return;
        }
        this.ay.push(this.ax.a);
        this.SetTitleCanvas(s, this.aw, -1, -1, -1);
    }
    
    public final void f() {
        if (this.ax == null) {
            return;
        }
        this.SetTitleCanvas(this.ay.pop(), this.aw, -1, -1, -1);
    }
    
    public final void d() {
        final URL codeBase = this.getCodeBase();
        if (this.p(codeBase.toString() + this.p(1005)) == 0) {
            this.p(this.p(1004), 1000);
            return;
        }
        if ((this.na & 0xFFF) == 0x3EC || (this.na & 0xFFF) == 0x3EF || (this.na & 0xFFF) == 0x3F0) {
            if (this.nv == null) {
                this.p(this.p(1001), 1000);
                this.na = 0;
            }
            switch (this.na & 0xFFF) {
                case 1004: {
                    this.nn = 30L;
                    break;
                }
                case 1007: {
                    this.nn = 60L;
                    break;
                }
                case 1008: {
                    this.nn = 90L;
                    break;
                }
            }
            this.na = 1004;
            this.as = new Date().getTime() - this.nv.getTime();
            if (this.as < 0L || this.as > this.nn * 1000L * 60L * 60L * 24L) {
                this.p(this.p(1000), 1000);
            }
        }
        else if ((this.na & 0x1000) != 0x0 && !codeBase.getHost().equalsIgnoreCase(this.np) && !codeBase.getHost().equalsIgnoreCase(this.np.substring(4, this.np.length())) && !codeBase.toString().startsWith(this.p(1006))) {
            this.p(this.p(1002) + this.np, 1000);
            this.na = 0;
        }
    }
    
    public final void TransitTo(final String n, final double n2, final double n3, final double n4, final double dk, final double dm, final double do1, final double dq, final int n5, final double n6) {
        this.n = n;
        this.dk = dk;
        this.dm = dm;
        this.do = do1;
        this.dq = dq;
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
    }
    
    public final void TransitTo(final String s, final double n, final double n2, final double n3, final double n4, final int n5, final double n6) {
        this.TransitTo(s, this.s, this.t, this.u, n, n2, n3, n4, n5, n6);
    }
    
    private final void a() {
        this.ap = System.currentTimeMillis();
        this.du = this.dx;
        this.dr = 80;
    }
    
    private final void c() {
        if (this.j == null) {
            this.e();
        }
        this.af = true;
        if (this.dr == 0) {
            this.dr = 5;
        }
        this.p();
        this.i();
        this.repaint();
        this.SetTitleCanvas(this.az, -1, -1, -1, -1);
    }
    
    public final synchronized void b() {
        if (this.j == null) {
            return;
        }
        int n;
        if (this.aj == 2) {
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
        this.z = (System.currentTimeMillis() - currentTimeMillis) / n / 1000.0;
        if (this.aj == 2) {
            this.dn = 400.0;
        }
        else {
            this.dn = 200.0;
        }
        if (this.z < 1.0 / this.r) {
            this.dz = (int)((1.0 / this.r - this.z) * 1000.0);
            this.z = 1.0 / this.r;
        }
        else {
            this.dz = 0;
        }
        if (this.aj == 2) {
            this.dz = 20;
        }
        this.SetTitleCanvas(this.az, -1, -1, -1, -1);
    }
    
    public final String p(final byte[] array, final int n) {
        final int p2 = this.p(array, n);
        final StringBuffer sb = new StringBuffer(p2);
        for (int i = 0; i < p2; ++i) {
            sb.append((char)array[i + 4 + n]);
        }
        return new String(sb.toString());
    }
    
    private final void d(final String a) {
        this.n();
        this.f = false;
        this.af = false;
        this.a = a;
        this.g = this.getImage(this.getDocumentBase(), a);
        (this.ah = new MediaTracker(this)).addImage(this.g, 0);
        this.prepareImage(this.g, this);
        if (this.ae) {
            this.a();
            return;
        }
        this.dr = 0;
    }
    
    private final void p(final String s) {
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
        byte[] p;
        try {
            final int available = resourceAsStream.available();
            final byte[] array = new byte[available];
            for (int i = 0; i < available; i += resourceAsStream.read(array, i, available - i)) {}
            p = p(array, 1357549999);
        }
        catch (Exception ex) {
            return;
        }
        this.ni = new Hashtable();
        int j = 0;
        while (j < p.length) {
            final int p2 = this.p(p, j);
            j += 4;
            final int p3 = this.p(p, j);
            j += 4;
            final String s2 = new String(p, j, p3);
            j += p3;
            this.ni.put(new Integer(p2), s2);
        }
    }
    
    public final synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public final synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return (n & 0x20) != 0x20;
    }
    
    public final synchronized void g() {
        try {
            while (!this.o) {
                this.wait(500L, 0);
                this.o = true;
            }
        }
        catch (InterruptedException ex) {}
    }
    
    private final void p() {
        final int[] array = (int[])this.h.getPixels();
        final int height = this.h.getHeight();
        final int width = this.h.getWidth();
        final int n = array[width * (height - 1 - 8)];
        boolean b = true;
        int n2 = 0;
    Label_0093:
        do {
            int n3 = width * (height - 1 - 8 + n2);
            int n4 = 0;
            while (array[n3] == n) {
                ++n4;
                ++n3;
                if (n4 >= 8) {
                    continue Label_0093;
                }
            }
            b = false;
        } while (++n2 < 8);
        if (b) {
            this.j.p((int[])this.h.getPixels(), this.h.getWidth(), this.h.getHeight() - 24, this.h.getWidth(), this.p, this.d, 32, this.k);
            this.dv = this.j.p(this.p, this.d);
        }
    }
    
    private final int p(final byte[] array, final int n) {
        final int[] array2 = new int[4];
        int n2 = 0;
        do {
            array2[n2] = array[n + n2];
            if (array2[n2] < 0) {
                final int[] array3 = array2;
                final int n3 = n2;
                array3[n3] += 256;
            }
        } while (++n2 < 4);
        return array2[0] | array2[1] << 8 | array2[2] << 16 | array2[3] << 24;
    }
    
    private final synchronized void h() {
        if (this.u > this.dv) {
            this.u = this.dv;
        }
        if (this.u < this.di) {
            this.u = this.di;
        }
        final double d = this.j.d();
        switch (this.aj) {
            case 1: {
                if (this.u == this.dl) {
                    break;
                }
                this.db = this.j.p(this.u);
                if (this.j != null) {
                    this.dc = this.j.p();
                    break;
                }
                break;
            }
            case 2: {
                final int height = this.g.getHeight(null);
                final int width = this.g.getWidth(null);
                if (height - this.d > 0) {
                    this.db = height / 2 / 120 * 2.0;
                }
                else {
                    this.db = 0.0;
                }
                if (width < this.p) {
                    this.dc = d / 2.0;
                    break;
                }
                if (Math.abs(d - 360.0) > 1.0E-4) {
                    this.dc = d / width * this.p / 2.0;
                    break;
                }
                this.dc = 0.0;
                break;
            }
        }
        this.dl = this.u;
        if (this.t < -this.db) {
            this.t = -this.db;
        }
        if (this.t > this.db) {
            this.t = this.db;
        }
        if (Math.abs(d - 360.0) > 1.0E-4) {
            boolean b = false;
            if (this.s < this.dc) {
                this.s = this.dc;
                this.w = 0.0;
                b = true;
            }
            if (this.s > d - this.dc) {
                this.s = d - this.dc;
                this.w = 0.0;
                b = true;
            }
            if (this.dr == 80 && b) {
                this.du *= -1.0;
            }
        }
        else {
            while (this.s < 0.0) {
                this.s += 360.0;
            }
            while (this.s > 360.0) {
                this.s -= 360.0;
            }
        }
    }
    
    public final void start() {
        this.s = 0.0;
        this.t = 0.0;
        this.u = 0.0;
        this.dp = 0.4;
        this.dd = 0.3;
        this.da = 0.5;
        this.di = 2.0;
        this.dl = 180.0;
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
            this.dp *= doubleValue;
            this.dd *= doubleValue;
            this.da *= doubleValue;
        }
        this.a = this.getParameter("url");
        final String parameter4 = this.getParameter("partialStitch");
        if (parameter4 != null && parameter4.compareTo("yes") == 0) {
            this.k = 0.0;
        }
        final String parameter5 = this.getParameter("horFov");
        if (parameter5 != null) {
            this.k = Double.valueOf(parameter5);
        }
        final String parameter6 = this.getParameter("startYaw");
        if (parameter6 != null) {
            this.s = Double.valueOf(parameter6);
        }
        final String parameter7 = this.getParameter("startPitch");
        if (parameter7 != null) {
            this.t = Double.valueOf(parameter7);
        }
        final String parameter8 = this.getParameter("startFov");
        if (parameter8 != null) {
            this.u = Double.valueOf(parameter8);
        }
        final String parameter9 = this.getParameter("autoPan");
        if (parameter9 != null && parameter9.compareTo("no") == 0) {
            this.ae = false;
            this.dr = 0;
        }
        final String parameter10 = this.getParameter("autoPanSpeed");
        if (parameter10 != null) {
            this.dx = Integer.valueOf(parameter10, 10);
        }
        final String parameter11 = this.getParameter("autoPanDelay");
        if (parameter11 != null) {
            this.dy = Integer.valueOf(parameter11, 10);
        }
        final String parameter12 = this.getParameter("autoPanTimeout");
        if (parameter12 != null) {
            this.ad = Long.valueOf(parameter12, 10) * 1000L * 60L;
        }
        int intValue = -1;
        int intValue2 = -1;
        int intValue3 = -1;
        int intValue4 = -1;
        final String parameter13 = this.getParameter("titlePos");
        if (parameter13 != null) {
            intValue = Integer.valueOf(parameter13, 10);
        }
        final String parameter14 = this.getParameter("titleForegroundColor");
        if (parameter14 != null) {
            intValue2 = Integer.valueOf(parameter14, 16);
        }
        final String parameter15 = this.getParameter("titleBackgroundColor");
        if (parameter15 != null) {
            intValue3 = Integer.valueOf(parameter15, 16);
        }
        final String parameter16 = this.getParameter("titleBorderColor");
        if (parameter16 != null) {
            intValue4 = Integer.valueOf(parameter16, 16);
        }
        final String parameter17 = this.getParameter("hq");
        if (parameter17 != null) {
            this.aa = Integer.valueOf(parameter17, 16);
        }
        final String parameter18 = this.getParameter("viewType");
        if (parameter18 != null && (this.aj == 1 || this.aj == 2)) {
            this.aj = Integer.valueOf(parameter18, 16);
        }
        final String parameter19 = this.getParameter("maxFps");
        if (parameter19 != null) {
            this.r = Integer.valueOf(parameter19, 16);
        }
        final String parameter20 = this.getParameter("title");
        if (parameter20 != null) {
            this.az = parameter20;
        }
        this.add(this.ax = new dzh(this, 2));
        this.ay = new Stack();
        this.SetTitle(this.az, intValue, intValue2, intValue3, intValue4);
        this.ar = new Dialog(new Frame(), "PixAround PixScreen  Help", true);
        this.add(this.an = new dzh(this, 1));
        this.an.move(4, 25);
        this.an.resize(500, 208);
        this.ar.add(this.an);
        this.ar.resize(500, 226);
        this.p(this.ar);
        this.b = new int[this.p * this.d];
        this.c = this.createImage(this);
        if (this.a != null) {
            this.d(this.a);
        }
        if (this.q == null) {
            this.q = new Thread(this);
            this.m = true;
            this.q.start();
        }
        this.v();
        this.SetTitleCanvas(this.p(1003) + this.np, -1, -1, -1, -1);
    }
    
    private final void n() {
        this.j = null;
        this.g = null;
        this.h = null;
        this.ah = null;
        System.gc();
    }
    
    public final void addConsumer(final ImageConsumer ag) {
        (this.ag = ag).setColorModel(this.e);
        this.ag.setDimensions(this.p, this.d);
        this.ag.setHints(1);
    }
    
    public final Image p(final Image image, final int n, final int n2) {
        final Image image2 = this.createImage(20, 20);
        final Graphics graphics = image2.getGraphics();
        graphics.setColor(new Color(-4342339));
        graphics.fillRect(0, 0, 20, 20);
        int n3 = 2;
        int n4 = 2;
        Color color;
        Color color2;
        if (n2 == 0) {
            n3 = 3;
            n4 = 3;
            color = new Color(-10526881);
            color2 = new Color(-1);
        }
        else {
            color = new Color(-1);
            color2 = new Color(-10526881);
        }
        graphics.drawImage(image, n3, -16 * n + n4, null);
        graphics.setColor(new Color(-4342339));
        graphics.drawRect(1, 1, 20, 2);
        graphics.setColor(color);
        graphics.drawRect(0, 0, 20, 20);
        graphics.setColor(color2);
        graphics.drawLine(19, 0, 19, 20);
        graphics.drawLine(1, 19, 20, 19);
        return image2;
    }
    
    private final synchronized void v() {
        int n = 0;
        if (this.aq) {
            n = 1;
        }
        switch (this.aj) {
            case 1: {
                this.ak.add(this.at, n);
                this.ak.add(this.au, n + 1);
                this.at.repaint();
                this.au.repaint();
                this.ak.resize(20 * this.ak.countComponents(), 20);
                break;
            }
            case 2: {
                this.ak.remove(this.at);
                this.ak.remove(this.au);
                this.ak.resize(20 * this.ak.countComponents(), 20);
                break;
            }
        }
        this.ak.validate();
    }
    
    public final void l() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image p = this.p("dat.gif");
        mediaTracker.addImage(p, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.setLayout(null);
        (this.ak = new Panel()).setLayout(new FlowLayout(0, 0, 0));
        this.add(this.ak);
        int n = 0;
        do {
            final dzi dzi = new dzi(this.p(p, n, 0), this.p(p, n, 1), this, n, this.p(100 + n));
            if (this.aq || n != 0) {
                this.ak.add(dzi);
                switch (n) {
                    default: {
                        continue;
                    }
                    case 1: {
                        this.at = dzi;
                        continue;
                    }
                    case 2: {
                        this.au = dzi;
                        continue;
                    }
                }
            }
        } while (++n < 5);
        this.av = 0;
        this.ai = this.size().height - 20;
        this.al = 20 * this.ak.countComponents();
        this.ab = this.size().height;
        this.ak.move(this.av, this.ai);
        this.ak.resize(this.al - this.av, this.ab - this.ai);
        if (this.am) {
            this.ak.setVisible(false);
            this.ao = true;
        }
    }
    
    public final void SetTitle(final String az, final int n, final int n2, final int n3, final int n4) {
        this.SetTitleCanvas(this.az = az, n, n2, n3, n4);
    }
    
    public final boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 104: {
                if (this.aj != 1) {
                    this.p("No Effect in 2D View", 1000);
                    this.repaint();
                    break;
                }
                this.aa = ((this.aa == 0) ? 1 : 0);
                if (this.j != null) {
                    if (this.aa == 0) {
                        this.j.p(false);
                        this.p("Fast View", 1000);
                    }
                    else {
                        this.j.p(true);
                        this.p("High Quality View", 1000);
                    }
                    this.l = false;
                }
                this.dr = 5;
                break;
            }
            case 101: {
                this.b();
                break;
            }
            case 97: {
                this.dr = 0;
                switch (this.aj) {
                    case 2: {
                        this.aj = 1;
                        this.u = this.dv;
                        this.p("3D View", 1000);
                        break;
                    }
                    case 1: {
                        this.aj = 2;
                        this.p("2D View", 1000);
                        break;
                    }
                }
                this.dl = -1.0;
                this.v();
                this.h();
                this.b();
                break;
            }
        }
        return true;
    }
    
    public final Image p(final String s) {
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
        Image image;
        try {
            final int available = resourceAsStream.available();
            final byte[] array = new byte[available + 3];
            for (int i = 0; i < available; i += resourceAsStream.read(array, i, available - i)) {}
            array[available] = -75;
            array[available + 1] = -74;
            array[available + 2] = -46;
            image = defaultToolkit.createImage(p(array, 1278932113));
        }
        catch (Exception ex) {
            return null;
        }
        this.prepareImage(image, this);
        return image;
    }
    
    public final synchronized void i() {
        switch (this.aj) {
            case 1: {
                if (this.f && this.j != null && this.ag != null) {
                    this.j.p(this.b, this.p * 4, this.s, this.t, this.u);
                    this.ag.setPixels(0, 0, this.p, this.d, this.e, this.b, 0, this.p);
                    this.ag.imageComplete(2);
                    return;
                }
                break;
            }
        }
    }
    
    public final String p(final int n) {
        return this.ni.get(new Integer(n));
    }
    
    public final void p(final int n, final int n2) {
        switch (n2) {
            case 1: {
                if (n == 501) {
                    this.dw = 10.0;
                    this.dr = 90;
                    return;
                }
                this.dw = 0.0;
                this.dr = 5;
            }
            case 2: {
                if (n == 501) {
                    this.dw = -10.0;
                    this.dr = 90;
                    return;
                }
                this.dw = 0.0;
                this.dr = 5;
            }
            case 3: {
                if (n == 502) {
                    this.a();
                    return;
                }
                break;
            }
            case 4: {
                if (this.ar == null || n != 502) {
                    break;
                }
                if (this.ar.isVisible()) {
                    this.ar.setVisible(false);
                    this.ar.dispose();
                    return;
                }
                this.ar.setVisible(true);
            }
            case 0: {
                if (n == 502) {
                    final AppletContext appletContext = this.getAppletContext();
                    try {
                        appletContext.showDocument(new URL(this.getDocumentBase(), this.p(200)), "_blank");
                        return;
                    }
                    catch (MalformedURLException ex) {
                        System.out.println(ex);
                    }
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
            this.o = true;
            this.notifyAll();
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
            this.o = true;
            this.notifyAll();
            return;
        }
        switch (this.aj) {
            case 2: {
                if (this.j == null) {
                    this.o = true;
                    this.notifyAll();
                    return;
                }
                final int width = this.g.getWidth(null);
                final int height2 = this.g.getHeight(null);
                if (width == -1) {
                    break;
                }
                final int n3 = -((int)(this.s / this.j.d() * width) - this.p / 2);
                final int n4 = (height2 - this.d) / 2;
                final int n5 = -n4 + (int)(this.t / this.db * n4);
                graphics.drawImage(this.g, n3, n5, null);
                if (n3 > 0) {
                    graphics.drawImage(this.g, -width + n3, n5, null);
                }
                if (n3 < -(width - this.p)) {
                    graphics.drawImage(this.g, n3 + width, n5, null);
                    break;
                }
                break;
            }
            case 1: {
                if (this.c != null) {
                    graphics.drawImage(this.c, 0, 0, null);
                    break;
                }
                break;
            }
        }
        this.o = true;
        this.notifyAll();
    }
    
    public uPixScreen() {
        this.l = false;
        this.r = 60.0;
        this.z = 0.05;
        this.dn = 200.0;
        this.dx = 3;
        this.dy = 50;
        this.ad = 180000L;
        this.ac = true;
        this.ae = true;
        this.aj = 1;
        this.am = false;
        this.aq = true;
        this.az = "";
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.q != null) {
            this.du = 0.0;
            this.dw = 0.0;
            this.dr = 7;
        }
        return true;
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
                this.e();
            }
        }
        if (this.j == null) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            return false;
        }
        this.j.p(this.aa != 0);
        switch (this.dr) {
            case 0: {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex2) {}
                if (!this.af && this.h != null) {
                    if ((this.h.getStatus() & 0x20) == 0x20) {
                        this.c();
                    }
                    return true;
                }
                return false;
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
                this.s += this.w * this.dp * this.z * this.u / this.dv;
                this.t += this.x * this.dd * this.z * this.u / this.dv;
                this.u += this.y * this.da * this.z;
                if (this.w == 0.0 && this.x == 0.0 && this.y == 0.0) {
                    this.dr = 5;
                    break;
                }
                final double n = this.dn * this.z;
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
                if (this.x < 0.0) {
                    this.x += n;
                    if (this.x > 0.0) {
                        this.x = 0.0;
                    }
                }
                if (this.y > 0.0) {
                    this.y -= n;
                    if (this.y < 0.0) {
                        this.y = 0.0;
                    }
                }
                if (this.y >= 0.0) {
                    break;
                }
                this.y += n;
                if (this.y > 0.0) {
                    this.y = 0.0;
                    break;
                }
                break;
            }
            case 80: {
                if (System.currentTimeMillis() - this.ap > this.ad) {
                    this.dr = 5;
                }
                if (this.dy > 0) {
                    try {
                        Thread.sleep(this.dy);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
            case 10: {
                this.w = this.du;
                this.x = this.dw;
                this.s += this.du * this.dp * this.z * this.u / this.dv;
                this.t += this.dw * this.dd * this.z * this.u / this.dv;
                if (this.dr == 80 && !this.af && this.h != null && (this.h.getStatus() & 0x20) == 0x20) {
                    this.c();
                    break;
                }
                break;
            }
            case 20:
            case 90: {
                if (this.aj == 1) {
                    this.y = -this.dw;
                    this.u += -this.dw * this.da * this.z;
                    break;
                }
                break;
            }
            case 100: {
                final double z = this.z;
                this.dj += z;
                if (this.dj > this.dh) {
                    final double n2 = this.dh - (this.dj - z);
                    this.dr = 101;
                }
                else {
                    this.s += this.de * z;
                    this.t += this.df * z;
                    this.u += this.dg * z;
                }
                return true;
            }
            case 101: {
                this.dr = 0;
                this.s = this.dk;
                this.t = this.dm;
                this.u = this.do;
                if (!this.n.equals(this.a)) {
                    this.k = this.dq;
                    this.d(this.n);
                }
                else {
                    this.dr = 5;
                }
                return false;
            }
        }
        this.h();
        return true;
    }
    
    private final int p(final String s) {
        InputStream openStream = null;
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            return 0;
        }
        try {
            openStream = url.openStream();
        }
        catch (IOException ex2) {}
        if (openStream == null) {
            return 0;
        }
        try {
            final int n = 1027;
            final byte[] array = new byte[n];
            for (int i = 0; i < n; i += openStream.read(array, i, n - i)) {}
            final byte[] p = p(array, 1357549999);
            int n2 = 0;
            if (this.p(p, n2) != 856100) {
                return 0;
            }
            n2 += 4;
            int n3 = n2 + (4 + this.p(p, n2).length());
            this.p(p, n3);
            n3 += 4;
            if (this.p(p, n3) > 1) {
                return 0;
            }
            n3 += 4;
            this.p(p, n3);
            n3 += 4;
            this.na = this.p(p, n3);
            n3 += 4;
            final int p2 = this.p(p, n3);
            n3 += 4;
            final int p3 = this.p(p, n3);
            n3 += 4;
            final int p4 = this.p(p, n3);
            n3 += 4;
            this.nv = new Date(p4 - 1900, p3 - 1, p2);
            this.np = this.p(p, n3);
            final int n4 = n3 + (4 + this.np.length());
            final int n5 = n4 + (4 + this.p(p, n4).length());
            final String p5 = this.p(p, n5);
            final int n6 = n5 + (4 + p5.length());
            if ((this.na & 0xFFF) == 0x3EB) {
                String np = p5.trim();
                try {
                    np = new URL(np).getHost();
                }
                catch (MalformedURLException ex3) {
                    final String string = "http://" + np;
                    try {
                        np = new URL(string).getHost();
                    }
                    catch (MalformedURLException ex4) {}
                }
                this.np = np;
            }
            this.nd = this.p(p, n6);
            final int n7 = n6 + (4 + this.nd.length());
        }
        catch (Exception ex5) {
            return 0;
        }
        return 1;
    }
    
    public final synchronized void p(final String v, final int i) {
        this.v = v;
        this.i = i;
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
    
    public final synchronized void e() {
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
            (this.j = new dzd()).p((int[])this.h.getPixels(), this.h.getWidth(), this.h.getHeight(), this.h.getWidth(), this.p, this.d, 32, this.k);
            this.f = true;
            this.dv = this.j.p(this.p, this.d);
            this.e = this.h.getColorModel();
        }
        if (this.u > this.dv) {
            this.u = this.dv;
        }
        if (this.u == 0.0) {
            if (this.dv >= 15.0) {
                this.u = this.dv - 10.0;
                return;
            }
            this.u = this.dv;
        }
    }
    
    public final void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public final boolean mouseDown(final Event event, final int ds, final int dt) {
        this.requestFocus();
        if (this.q != null) {
            if (event.shiftDown()) {
                this.dr = 20;
            }
            else {
                this.dr = 10;
            }
            this.ds = ds;
            this.dt = dt;
            this.du = 0.0;
            this.dw = 0.0;
        }
        return true;
    }
    
    private final void p(final Graphics graphics) {
        final int n = this.p / 3;
        final int n2 = this.d / 2;
        int n3;
        if (n < n2) {
            n3 = n;
        }
        else {
            n3 = n2;
        }
        final double n4 = 0.06 * n3;
        final int n5 = (this.d - n3) / 2;
        final int n6 = (this.p - n3) / 2;
        final Color color = new Color(230, 230, 230);
        final Color color2 = new Color(255, 255, 255);
        graphics.setColor(color2);
        graphics.fillRect(0, 0, this.p, this.d);
        final int n7 = (int)(n6 - n3 * 0.125);
        final int n8 = (int)(n5 + n3 * 0.275 + n4 / 2.0);
        final int n9 = (int)(n3 * 1.25);
        final int n10 = (int)(n3 * 0.45);
        graphics.setColor(color);
        graphics.fillOval(n7, n8, n9, n10);
        graphics.setColor(color2);
        graphics.fillOval(n7, (int)(n8 - n4), n9, n10);
        final int n11 = (int)(n7 + n9 * 0.1 / 2.0);
        final int n12 = (int)(n8 - n4 / 2.0);
        final int n13 = (int)(n9 * 0.9);
        final int n14 = (int)(n10 * 0.9);
        graphics.setColor(color);
        graphics.fillArc(n11, n12, n13, n14, -15, 210);
        graphics.setColor(color2);
        graphics.fillArc(n11, (int)(n12 + n4), n13, n14, -15, 210);
        graphics.setColor(color);
        graphics.fillOval((int)(n6 + n3 / 2 - n3 * 0.3 / 2.0), (int)(n5 + n3 / 2 - n3 * 0.3 / 2.0), (int)(n3 * 0.3), (int)(n3 * 0.3));
        graphics.setColor(color);
        graphics.fillRect(n6, n5, (int)(n3 - n4), (int)n4);
        graphics.fillRect(n6, (int)(n5 + n3 - n4 + 0.5), (int)(n3 - n4 + 0.5), (int)n4);
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[0] = n6;
        array2[0] = n5;
        array[1] = n6;
        array2[1] = (int)(n5 + n3 * 0.58 - n4 / 2.0);
        array[2] = (int)(n6 + n4);
        array2[2] = (int)(n5 + n3 * 0.58);
        array[3] = (int)(n6 + n4);
        array2[3] = n5;
        graphics.fillPolygon(array, array2, 4);
        graphics.fillRect(n6, (int)(n5 + n3 * 0.65 + 0.5), (int)n4, (int)(n3 * 0.35 - n4 + 0.5));
        array[0] = (int)(n6 + n3 - n4 + 0.5);
        array2[0] = n5;
        array[1] = array[0];
        array2[1] = (int)(n5 + n3 * 0.58 + 0.5);
        array[2] = n6 + n3;
        array2[2] = (int)(n5 + n3 * 0.58 - n4 / 2.0 + 0.5);
        array[3] = array[2];
        array2[3] = n5;
        graphics.fillPolygon(array, array2, 4);
        graphics.fillRect((int)(n6 + n3 - n4 + 0.5), (int)(n5 + n3 * 0.65 + 0.5), (int)(n4 + 0.5), (int)(n3 * 0.35 + 0.5));
    }
    
    public final void p(final double n, final double n2, double n3, final double n4) {
        if (this.j == null) {
            return;
        }
        double n5 = 0.0;
        double n6 = 0.0;
        double n7 = 0.0;
        if (n3 > this.dv) {
            n3 = this.dv;
        }
        if (n3 < this.di) {
            n3 = this.di;
        }
        this.dr = 100;
        if (n != -1.0) {
            n5 = n - this.s;
            if (n5 > 180.0) {
                n5 -= 360.0;
            }
            if (n5 < -180.0) {
                n5 += 360.0;
            }
        }
        if (n2 != -999.0) {
            n6 = n2 - this.t;
        }
        if (n3 != -1.0) {
            n7 = n3 - this.u;
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
        this.dj = 0.0;
        if (this.aa == 0) {
            this.j.p(false);
        }
    }
    
    private final synchronized void SetTitleCanvas(final String a, final int aw, final int n, final int n2, final int n3) {
        this.ax.a = a;
        if (a.length() == 0) {
            this.ax.setVisible(false);
        }
        else {
            this.ax.setVisible(true);
        }
        if (aw != -1) {
            this.aw = aw;
        }
        if (n != -1) {
            this.ax.v = new Color(n);
        }
        if (n2 != -1) {
            this.ax.i = new Color(n2);
        }
        if (n3 != -1) {
            this.ax.n = new Color(n3);
        }
        final int n4 = 100;
        final int n5 = 20;
        final int p5 = this.ax.p();
        final int d = this.ax.d();
        Point point = null;
        switch (this.aw) {
            case 0: {
                point = new Point(0, this.d - n5 - p5);
                break;
            }
            case 1: {
                point = new Point(0, 0);
                break;
            }
            case 2: {
                point = new Point(this.p - d, 0);
                break;
            }
            case 3: {
                point = new Point(this.p - d, this.d - p5);
                break;
            }
            case 4: {
                point = new Point(n4, this.d - p5);
                break;
            }
        }
        if (point != null) {
            this.ax.move(point.x, point.y);
            this.ax.resize(d, p5);
        }
        this.ax.repaint();
    }
    
    public final void run() {
        Thread.currentThread().setPriority(1);
        while (this.m) {
            if (!this.l && this.j != null) {
                this.l = true;
                this.b();
            }
            if (this.p()) {
                this.i();
                this.o = false;
                this.repaint();
                this.g();
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
        this.p("ss.gif");
        this.d();
        if ((this.na & 0xFFF) == 0x3EB) {
            this.aq = false;
            final String parameter = this.getParameter("autoHide");
            if (parameter != null && parameter.compareTo("yes") == 0) {
                this.am = true;
            }
        }
        this.l();
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.dr == 90) {
            return true;
        }
        this.requestFocus();
        if (this.q != null) {
            this.du = n - this.ds;
            this.dw = this.dt - n2;
        }
        return true;
    }
    
    public final synchronized boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.q != null && this.am) {
            if (n > this.av && n < this.al && n2 > this.ai && n2 < this.ab) {
                if (this.ao) {
                    this.ak.setVisible(true);
                    this.ao = false;
                }
            }
            else if (!this.ao) {
                this.ak.setVisible(false);
                this.ao = true;
            }
        }
        return true;
    }
}
