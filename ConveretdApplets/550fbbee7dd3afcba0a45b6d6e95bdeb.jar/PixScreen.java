import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
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
import java.util.Date;
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
    public _zs j;
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
    private boolean do;
    public int dq;
    public int dr;
    public int ds;
    public double dt;
    public double du;
    private int dw;
    private int dx;
    private int dy;
    private long dz;
    private long ap;
    public int ad;
    public _zc aa;
    public int an;
    public int av;
    public int ai;
    public int al;
    public boolean ab;
    public boolean ac;
    private boolean ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    public ImageConsumer aj;
    public MediaTracker ak;
    public int am;
    public _zh ao;
    public _zh aq;
    public Hashtable ar;
    public boolean as;
    public _zi[] at;
    public Panel au;
    public int aw;
    public int ax;
    public Stack ay;
    public long az;
    public Dialog np;
    public int nd;
    private _zc na;
    public Stack nn;
    public String nv;
    public String ni;
    public int nl;
    public int nb;
    public String nc;
    public String ne;
    public int nf;
    public long ng;
    public Date nh;
    private Hashtable nj;
    
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
        this.aj = null;
        this.i();
    }
    
    public final void n(final String s) {
        this.p(s, this.nd, -1, -1, -1);
    }
    
    public final void l() {
        if (this.j != null) {
            this.p(this.j.d, this.nd, -1, -1, -1);
        }
    }
    
    public final synchronized void a(final String ni) {
        this.ni = ni;
    }
    
    public final void d() {
        final URL codeBase = this.getCodeBase();
        if (this.p(codeBase.toString() + this.p(1005)) == 0) {
            this.p(this.p(1004), 1000);
            return;
        }
        if ((this.nf & 0xFFF) == 0x3EC || (this.nf & 0xFFF) == 0x3EF || (this.nf & 0xFFF) == 0x3F0) {
            if (this.nh == null) {
                this.p(this.p(1001), 1000);
                this.nf = 0;
            }
            switch (this.nf & 0xFFF) {
                case 1004: {
                    this.ng = 30L;
                    break;
                }
                case 1007: {
                    this.ng = 60L;
                    break;
                }
                case 1008: {
                    this.ng = 90L;
                    break;
                }
            }
            this.nf = 1004;
            this.az = new Date().getTime() - this.nh.getTime();
            if (this.az < 0L || this.az > this.ng * 1000L * 60L * 60L * 24L) {
                this.p(this.p(1000), 1000);
            }
        }
        else if ((this.nf & 0x1000) != 0x0 && !codeBase.getHost().equalsIgnoreCase(this.nc) && !codeBase.getHost().equalsIgnoreCase(this.nc.substring(4, this.nc.length())) && !codeBase.toString().startsWith(this.p(1006))) {
            this.p(this.p(1002) + this.nc, 1000);
            this.nf = 0;
        }
    }
    
    public final synchronized void TransitTo(final String n, final double n2, final double n3, final double n4, final double dj, final double dk, final double dm, final int n5, final double n6) {
        this.n = n;
        this.dj = dj;
        this.dk = dk;
        this.dm = dm;
        this.do = true;
        if (!n.equals(this.a)) {
            this.p(n2, n3, n4, n6);
        }
        else {
            this.p(dj, dk, dm, n6);
        }
        if (n5 == 1) {
            this.ac = true;
        }
        if (n5 == 0) {
            this.ac = false;
        }
        this.ay.push(new _zt(this.a, this.r, this.s, this.t));
    }
    
    public final synchronized void TransitTo(final String s, final double n, final double n2, final double n3, final int n4, final double n5) {
        this.TransitTo(s, this.r, this.s, this.t, n, n2, n3, n4, n5);
    }
    
    private final void c() {
        this.dz = System.currentTimeMillis();
        this.dt = this.dw;
        this.dq = 80;
    }
    
    public final synchronized void TransitTo(final String s, final int n) {
        if (n == 0) {
            this.ac = false;
        }
        else {
            this.ac = true;
        }
        this.do = false;
        if (s.compareTo(this.a) != 0) {
            this.ay.push(new _zt(this.a, this.r, this.s, this.t));
        }
        this.d(s);
    }
    
    private final void b() {
        if (this.j == null) {
            this.e();
        }
        this.ae = true;
        if (this.dq == 0) {
            this.dq = 5;
        }
        if (this.as) {
            this.j.p();
        }
        this.f();
        this.j.a();
        if (this.as) {
            this.j.n();
        }
        this.dn = this.j.p(this.p, this.d);
        this.v();
        this.n();
        this.repaint();
    }
    
    private final synchronized void d(final int n) {
        if (n == 501) {
            this.du = 10.0;
            this.dq = 90;
            return;
        }
        this.du = 0.0;
        this.dq = 5;
    }
    
    public final synchronized void a() {
        if (this.j == null) {
            return;
        }
        int n;
        if (this.am == 2) {
            n = 60;
        }
        else {
            n = 20;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < n; ++i) {
            this.n();
            this.repaint();
        }
        this.y = (System.currentTimeMillis() - currentTimeMillis) / n / 1000.0;
        if (this.am == 2) {
            this.da = 400.0;
        }
        else {
            this.da = 200.0;
        }
        if (this.y < 1.0 / this.q) {
            this.dy = (int)((1.0 / this.q - this.y) * 1000.0);
            this.y = 1.0 / this.q;
        }
        else {
            this.dy = 0;
        }
        if (this.am == 2) {
            this.dy = 20;
        }
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
        this.i();
        this.f = false;
        this.ae = false;
        this.a = a;
        this.g = this.getImage(this.getDocumentBase(), a);
        (this.ak = new MediaTracker(this)).addImage(this.g, 0);
        this.prepareImage(this.g, this);
        if (this.ac) {
            this.c();
            return;
        }
        this.dq = 0;
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
        this.nj = new Hashtable();
        int j = 0;
        while (j < p.length) {
            final int p2 = this.p(p, j);
            j += 4;
            final int p3 = this.p(p, j);
            j += 4;
            final String s2 = new String(p, j, p3);
            j += p3;
            this.nj.put(new Integer(p2), s2);
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
    
    private final void f() {
        final int[] array = (int[])this.h.getPixels();
        final int height = this.h.getHeight();
        final int width = this.h.getWidth();
        final int n = array[width * (height - 1 - 8)];
        final int n2 = n & 0xFF;
        final int n3 = (n & 0xFF00) >> 8;
        final int n4 = (n & 0xFF0000) >> 16;
        if (n2 != n3 || n3 != n4 || n2 < 170 || n2 > 190 || n3 < 170 || n3 > 190 || n4 < 170 || n4 > 190) {
            return;
        }
        boolean b = true;
        int n5 = 0;
    Label_0186:
        do {
            int n6 = width * (height - 1 - 8 + n5);
            int n7 = 0;
            while (array[n6] == n) {
                ++n7;
                ++n6;
                if (n7 >= 8) {
                    continue Label_0186;
                }
            }
            b = false;
        } while (++n5 < 8);
        if (b) {
            this.j.p((int[])this.h.getPixels(), this.h.getWidth(), this.h.getHeight() - 24, this.h.getWidth(), this.p, this.d, 32, this.j.p);
            this.dn = this.j.p(this.p, this.d);
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
    
    public final void start() {
        this.r = 0.0;
        this.s = 0.0;
        this.t = 0.0;
        this.z = 0.4;
        this.dp = 0.3;
        this.dd = 0.5;
        this.dv = 2.0;
        this.di = 180.0;
        this.k = 360.0;
        this.dq = 80;
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
            this.ac = false;
            this.dq = 0;
        }
        final String parameter5 = this.getParameter("autoScale");
        if (parameter5 != null) {
            if (parameter5.compareTo("no") == 0) {
                this.ab = false;
            }
            else if (parameter5.compareTo("yes") == 0) {
                this.ab = true;
            }
            else {
                System.out.println("undefined autoscale option:" + parameter5);
            }
        }
        final String parameter6 = this.getParameter("hsTransparency");
        if (parameter6 != null) {
            this.aw = Integer.valueOf(parameter6, 10);
        }
        final String parameter7 = this.getParameter("autoPanSpeed");
        if (parameter7 != null) {
            this.dw = Integer.valueOf(parameter7, 10);
        }
        final String parameter8 = this.getParameter("autoPanDelay");
        if (parameter8 != null) {
            this.dx = Integer.valueOf(parameter8, 10);
        }
        final String parameter9 = this.getParameter("autoPanTimeout");
        if (parameter9 != null) {
            this.ap = Long.valueOf(parameter9, 10) * 1000L * 60L;
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
            this.ad = Integer.valueOf(parameter14, 16);
        }
        final String parameter15 = this.getParameter("maxFps");
        if (parameter15 != null) {
            this.q = Integer.valueOf(parameter15, 16);
        }
        this.add(this.na = new _zc(this, 2));
        this.nn = new Stack();
        this.SetTitle(this.nv, intValue, intValue2, intValue3, intValue4);
        this.np = new Dialog(new Frame(), "PixAround PixScreen  Help", true);
        this.add(this.aa = new _zc(this, 1));
        this.aa.move(4, 25);
        this.aa.resize(500, 208);
        this.np.add(this.aa);
        this.np.resize(500, 226);
        this.p(this.np);
        this.ay = new Stack();
        this.b = new int[this.p * this.d];
        this.c = this.createImage(this);
        _zh.p = this;
        _zh.p();
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
        this.p(this.p(1003) + this.nc, -1, -1, -1, -1);
    }
    
    private final void i() {
        if (this.j != null) {
            this.j.d();
        }
        this.j = null;
        this.g = null;
        this.h = null;
        this.ak = null;
        this.aq = null;
        this.ao = null;
        System.gc();
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
        graphics.drawImage(image, n4, -16 * n + n5, this);
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
    
    public final void addConsumer(final ImageConsumer aj) {
        (this.aj = aj).setColorModel(this.e);
        this.aj.setDimensions(this.p, this.d);
        this.aj.setHints(1);
    }
    
    private final synchronized void p(final int n) {
        if (n == 502) {
            final _zt zt = this.ay.pop();
            this.do = true;
            this.dj = zt.p;
            this.dk = zt.d;
            this.dm = zt.a;
            this.d(zt.p);
        }
    }
    
    private final void v() {
        this.au.remove(this.at[5]);
        this.au.remove(this.at[6]);
        this.au.remove(this.at[1]);
        this.au.remove(this.at[2]);
        int n = 0;
        if (this.ah) {
            ++n;
        }
        if (this.am == 1) {
            this.au.add(this.at[1], n++);
            this.au.add(this.at[2], n++);
            this.at[1].repaint();
            this.at[2].repaint();
            this.au.validate();
            ++n;
        }
        if (this.j != null) {
            if (this.j.p.size() > 0) {
                this.au.add(this.at[5], n++);
                this.at[5].repaint();
            }
            this.au.validate();
        }
        if (this.ay.size() > 0) {
            this.au.add(this.at[6], n++);
            this.at[6].repaint();
            this.au.validate();
        }
        this.au.resize(20 * this.au.countComponents(), 20);
        this.au.validate();
    }
    
    private final synchronized void a(final int n) {
        if (n == 501) {
            this.du = -10.0;
            this.dq = 90;
            return;
        }
        this.du = 0.0;
        this.dq = 5;
    }
    
    public final void p() {
        new MediaTracker(this);
        final Image p = this.p("dat.gif", -91, -25, 107);
        this.setLayout(null);
        (this.au = new Panel()).setLayout(new FlowLayout(0, 0, 0));
        this.add(this.au);
        this.at = new _zi[7];
        int n = 0;
        do {
            this.at[n] = new _zi(this.p(p, n, 0, 0), this.p(p, n, 1, 0), this, n, this.p(100 + n));
            if (this.ah || n != 0) {
                this.au.add(this.at[n]);
            }
        } while (++n < 7);
        this.at[5].n = this.p(p, 5, 1, 1);
        this.an = 0;
        this.av = this.size().height - 20;
        this.ai = 20 * this.au.countComponents();
        this.al = this.size().height;
        this.au.move(this.an, this.av);
        this.au.resize(this.ai - this.an, this.al - this.av);
        if (this.af) {
            this.au.setVisible(false);
            this.ag = true;
        }
    }
    
    public final void SetTitle(final String nv, final int n, final int n2, final int n3, final int n4) {
        this.p(this.nv = nv, n, n2, n3, n4);
    }
    
    public final synchronized boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 104: {
                if (this.am != 1) {
                    this.p("No Effect in 2D View", 1000);
                    this.repaint();
                    break;
                }
                this.ad = ((this.ad == 0) ? 1 : 0);
                if (this.j != null) {
                    if (this.ad == 0) {
                        this.j.p(false);
                        this.p("Fast View", 1000);
                    }
                    else {
                        this.j.p(true);
                        this.p("High Quality View", 1000);
                    }
                    this.l = false;
                }
                this.dq = 5;
                break;
            }
            case 101: {
                this.a();
                break;
            }
            case 97: {
                switch (this.am) {
                    case 2: {
                        if (this.j != null) {
                            this.j.a = 1;
                        }
                        this.am = 1;
                        this.p("3D View", 1000);
                        break;
                    }
                    case 1: {
                        if (this.j != null) {
                            this.j.a = 2;
                        }
                        this.am = 2;
                        this.p("2D View", 1000);
                        break;
                    }
                }
                this.t = this.j.p(this.p, this.d);
                this.db = this.j.p();
                this.r = this.db;
                this.v();
                this.a();
                this.repaint();
                break;
            }
        }
        return true;
    }
    
    public final Image p(final String s, final int n, final int n2, final int n3) {
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
        Image image;
        try {
            final int available = resourceAsStream.available();
            final byte[] array = new byte[available + 3];
            for (int i = 0; i < available; i += resourceAsStream.read(array, i, available - i)) {}
            array[available] = (byte)n;
            array[available + 1] = (byte)n2;
            array[available + 2] = (byte)n3;
            image = defaultToolkit.createImage(p(array, 1278932113));
        }
        catch (Exception ex) {
            System.out.println(ex + " getting resource " + s);
            return null;
        }
        this.prepareImage(image, this);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {}
        return image;
    }
    
    public final synchronized void n() {
        switch (this.am) {
            case 1:
            case 3: {
                if (this.f && this.j != null && this.aj != null) {
                    this.j.d = this.j.d;
                    this.j.p(this.b, this.p * 4, this.r, this.s, this.t);
                    this.aj.setPixels(0, 0, this.p, this.d, this.e, this.b, 0, this.p);
                    this.aj.imageComplete(2);
                    return;
                }
                break;
            }
        }
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
                                int n = 0;
                                if (streamTokenizer.sval.equalsIgnoreCase("FLT")) {
                                    n = 1;
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
                                final String sval4 = streamTokenizer.sval;
                                this.j = this.p(sval3, sval2, p);
                                if (this.j != null) {
                                    this.j.a = p2;
                                    this.j.n = p3;
                                    this.j.v = p4;
                                    this.j.a = sval4;
                                }
                                if (n == 1) {
                                    this.j.a = 2;
                                }
                                if (a == null) {
                                    a = sval3;
                                    continue;
                                }
                                continue;
                            }
                            else if (streamTokenizer.sval.equalsIgnoreCase("OBV")) {
                                streamTokenizer.nextToken();
                                final String sval5 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final String sval6 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final String sval7 = streamTokenizer.sval;
                                streamTokenizer.nextToken();
                                final double p5 = this.p(streamTokenizer, sval5, "dTotalYawAngle");
                                streamTokenizer.nextToken();
                                final int p6 = (int)this.p(streamTokenizer, sval5, "numFrames");
                                streamTokenizer.nextToken();
                                final int n2 = (int)this.p(streamTokenizer, sval5, "defaultFrame");
                                streamTokenizer.nextToken();
                                final double p7 = this.p(streamTokenizer, sval5, "AutoPanFps");
                                streamTokenizer.nextToken();
                                final String sval8 = streamTokenizer.sval;
                                this.j = this.p(sval7, sval6, p5);
                                if (this.j != null) {
                                    this.j.p = p6;
                                    this.j.n = n2;
                                    this.j.d = p7;
                                    this.j.a = 3;
                                    this.j.a = sval8;
                                }
                                if (a == null) {
                                    a = sval7;
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (streamTokenizer.sval.equalsIgnoreCase("SCE")) {
                                    streamTokenizer.nextToken();
                                    final String sval9 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final String sval10 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final String sval11 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final double p8 = this.p(streamTokenizer, sval9, "dYawPos");
                                    streamTokenizer.nextToken();
                                    final double p9 = this.p(streamTokenizer, sval9, "dPitchPos");
                                    streamTokenizer.nextToken();
                                    final int n3 = (int)this.p(streamTokenizer, sval9, "AutoPan");
                                    streamTokenizer.nextToken();
                                    final double p10 = this.p(streamTokenizer, sval9, "srcYaw");
                                    streamTokenizer.nextToken();
                                    final double p11 = this.p(streamTokenizer, sval9, "srcPitch");
                                    streamTokenizer.nextToken();
                                    final double p12 = this.p(streamTokenizer, sval9, "srcFov");
                                    streamTokenizer.nextToken();
                                    final double p13 = this.p(streamTokenizer, sval9, "tgtYaw");
                                    streamTokenizer.nextToken();
                                    final double p14 = this.p(streamTokenizer, sval9, "tgtPitch");
                                    streamTokenizer.nextToken();
                                    this.j.p(sval9, sval10, p8, p9, this, sval11, n3, p10, p11, p12, p13, p14, this.p(streamTokenizer, sval9, "tgtFov"));
                                    continue;
                                }
                                if (streamTokenizer.sval.equalsIgnoreCase("url") || streamTokenizer.sval.equalsIgnoreCase("aud") || streamTokenizer.sval.equalsIgnoreCase("vid") || streamTokenizer.sval.equalsIgnoreCase("eml") || streamTokenizer.sval.equalsIgnoreCase("lbl")) {
                                    int n4 = 0;
                                    if (streamTokenizer.sval.equalsIgnoreCase("url")) {
                                        n4 = 0;
                                    }
                                    else if (streamTokenizer.sval.equalsIgnoreCase("aud")) {
                                        n4 = 1;
                                    }
                                    else if (streamTokenizer.sval.equalsIgnoreCase("vid")) {
                                        n4 = 2;
                                    }
                                    else if (streamTokenizer.sval.equalsIgnoreCase("eml")) {
                                        n4 = 3;
                                    }
                                    else if (streamTokenizer.sval.equalsIgnoreCase("lbl")) {
                                        n4 = 4;
                                    }
                                    streamTokenizer.nextToken();
                                    final String sval12 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final String sval13 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final String sval14 = streamTokenizer.sval;
                                    streamTokenizer.nextToken();
                                    final double p15 = this.p(streamTokenizer, sval12, "dYawPos");
                                    streamTokenizer.nextToken();
                                    final double p16 = this.p(streamTokenizer, sval12, "dPitchPos");
                                    streamTokenizer.nextToken();
                                    this.j.p(sval12, sval13, p15, p16, this, sval14, (int)this.p(streamTokenizer, sval12, "ActivationType"), n4);
                                    continue;
                                }
                                System.out.println("Unknown Token:" + streamTokenizer.sval);
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
        }
        return true;
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
                this.d(n);
            }
            case 2: {
                this.a(n);
            }
            case 3: {
                this.v(n);
            }
            case 4: {
                if (this.np == null || n != 502) {
                    break;
                }
                if (this.np.isVisible()) {
                    this.np.setVisible(false);
                    this.np.dispose();
                    return;
                }
                this.np.setVisible(true);
            }
            case 5: {
                this.n(n);
            }
            case 6: {
                this.p(n);
            }
            case 0: {
                if (n == 502) {
                    final AppletContext appletContext = this.getAppletContext();
                    try {
                        appletContext.showDocument(new URL(this.getDocumentBase(), this.p(200)), "_blank");
                        return;
                    }
                    catch (MalformedURLException ex) {}
                    break;
                }
                break;
            }
        }
    }
    
    public final String p(final int n) {
        return this.nj.get(new Integer(n));
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
        final int width = this.g.getWidth(null);
        final int height2 = this.g.getHeight(null);
        switch (this.am) {
            case 2: {
                if (width == -1) {
                    break;
                }
                final int n3 = -((int)(this.r / this.j.d() * width) - this.p / 2);
                final int n4 = (height2 - this.d) / 2;
                final int n5 = -n4 + (int)(this.s / this.dl * n4);
                graphics.drawImage(this.g, n3, n5, null);
                if (this.j.p()) {
                    break;
                }
                if (n3 > 0) {
                    graphics.drawImage(this.g, -width + n3, n5, null);
                }
                if (n3 < -(width - this.p)) {
                    graphics.drawImage(this.g, n3 + width, n5, null);
                    return;
                }
                break;
            }
            case 1:
            case 3: {
                if (this.c == null) {
                    break;
                }
                graphics.drawImage(this.c, 0, 0, null);
                if (this.ni != null) {
                    final int stringWidth2 = graphics.getFontMetrics().stringWidth(this.ni);
                    graphics.setColor(Color.yellow);
                    graphics.fillRect(this.nl - 3, this.nb - 12 + 30, stringWidth2 + 6, 15);
                    graphics.setColor(Color.black);
                    graphics.drawRect(this.nl - 3, this.nb - 12 + 30, stringWidth2 + 6, 15);
                    graphics.drawString(this.ni, this.nl, this.nb + 30);
                    return;
                }
                break;
            }
        }
    }
    
    public final void p(final int n, final int n2, final int n3, final boolean b) {
        Label_0514: {
            switch (this.dq) {
                case 0:
                case 7: {
                    switch (n) {
                        case 123: {
                            if (this.ao != null) {
                                this.dq = 112;
                                this.ao.p(20);
                                return;
                            }
                            break Label_0514;
                        }
                        case 120: {
                            if (this.o != null) {
                                switch (this.am) {
                                    case 1:
                                    case 2: {
                                        if (b) {
                                            this.dq = 20;
                                            break;
                                        }
                                        this.dq = 10;
                                        break;
                                    }
                                    case 3: {
                                        this.dq = 105;
                                        break;
                                    }
                                }
                                this.dr = n2;
                                this.ds = n3;
                                this.dt = 0.0;
                                this.du = 0.0;
                                return;
                            }
                            break Label_0514;
                        }
                    }
                    break;
                }
                case 110:
                case 111:
                case 112: {
                    switch (n) {
                        case 120: {
                            this.dq = 112;
                            if (this.ao != null) {
                                this.ao.p(0);
                                return;
                            }
                            break Label_0514;
                        }
                        case 121: {
                            this.dq = 112;
                            if (this.ao != null) {
                                this.ao.p(10);
                                return;
                            }
                            break Label_0514;
                        }
                    }
                    break;
                }
                case 80:
                case 100: {
                    switch (n) {
                        case 120: {
                            switch (this.am) {
                                case 1:
                                case 2: {
                                    if (b) {
                                        this.dq = 20;
                                        break;
                                    }
                                    this.dq = 10;
                                    break;
                                }
                                case 3: {
                                    this.dq = 105;
                                    break;
                                }
                            }
                            this.dr = n2;
                            this.ds = n3;
                            this.dt = 0.0;
                            this.du = 0.0;
                            if (this.as && this.j != null) {
                                this.j.n();
                                return;
                            }
                            break Label_0514;
                        }
                    }
                    break;
                }
                case 10:
                case 20: {
                    switch (n) {
                        case 121: {
                            if (this.o != null) {
                                this.dt = 0.0;
                                this.du = 0.0;
                                this.dq = 7;
                                return;
                            }
                            break Label_0514;
                        }
                    }
                    break;
                }
                case 105: {
                    switch (n) {
                        case 121: {
                            this.dq = 0;
                            return;
                        }
                        default: {
                            break Label_0514;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private final synchronized void n(final int n) {
        if (n == 501) {
            if (this.as) {
                this.as = false;
                this.j.p();
                if (this.at[5] != null) {
                    this.at[5].v = false;
                }
            }
            else {
                this.as = true;
                this.j.n();
                if (this.at[5] != null) {
                    this.at[5].v = true;
                }
            }
            this.dq = 5;
        }
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
        this.dw = 3;
        this.dx = 50;
        this.ap = 180000L;
        this.ab = true;
        this.ac = true;
        this.af = false;
        this.ah = true;
        this.am = 1;
        this.as = true;
        this.ax = 10;
        this.nv = "";
    }
    
    private final _zs p(final String s, final String s2, final double n) {
        final _zs zs = this.ar.get(s);
        if (zs != null) {
            return zs;
        }
        final _zs zs2 = new _zs(s, s2, n);
        this.ar.put(s, zs2);
        return zs2;
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
        if (!this.ae && this.h != null && (this.h.getStatus() & 0x20) == 0x20) {
            this.b();
        }
        this.j.p(this.ad != 0);
        Label_0857: {
            switch (this.dq) {
                case 0:
                case 110: {
                    try {
                        Thread.sleep(50L);
                    }
                    catch (InterruptedException ex2) {}
                    return !this.ae && this.h != null;
                }
                case 5: {
                    this.dq = 0;
                    if (this.ad == 0) {
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
                        this.dq = 5;
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
                    this.dq = 110;
                    if (this.ad == 0) {
                        this.j.p(true);
                        break;
                    }
                    return false;
                }
                case 112: {
                    this.dq = 111;
                    this.j.p(true);
                    this.dq = 110;
                    break;
                }
                case 80: {
                    int dx = this.dx;
                    if (System.currentTimeMillis() - this.dz > this.ap) {
                        this.dq = 5;
                    }
                    if (this.am == 3) {
                        if (this.dt > 0.0) {
                            final _zs j = this.j;
                            ++j.d;
                        }
                        else {
                            final _zs i = this.j;
                            --i.d;
                        }
                        dx = (int)(this.j.d() / this.j.p * 16.666666666666668);
                    }
                    if (dx > 0) {
                        try {
                            Thread.sleep(dx);
                        }
                        catch (InterruptedException ex3) {}
                    }
                    if (this.am != 3) {
                        break Label_0857;
                    }
                    break;
                }
                case 10: {
                    this.u = this.dt;
                    this.w = this.du;
                    this.r += this.dt * this.z * this.y * this.t / this.dn;
                    this.s += this.du * this.dp * this.y * this.t / this.dn;
                    if (this.dq == 80 && !this.ae && this.h != null && (this.h.getStatus() & 0x20) == 0x20) {
                        this.b();
                        break;
                    }
                    break;
                }
                case 20:
                case 90: {
                    if (this.am == 1) {
                        this.x = -this.du;
                        this.t += -this.du * this.dd * this.y;
                        break;
                    }
                    break;
                }
                case 100: {
                    final double y = this.y;
                    this.dh += y;
                    if (this.dh > this.dg) {
                        final double n2 = this.dg - (this.dh - y);
                        this.dq = 101;
                    }
                    else {
                        this.r += this.dc * y;
                        this.s += this.de * y;
                        this.t += this.df * y;
                    }
                    return true;
                }
                case 101: {
                    this.dq = 0;
                    this.r = this.dj;
                    this.s = this.dk;
                    this.t = this.dm;
                    if (!this.n.equals(this.a)) {
                        this.d(this.n);
                    }
                    else {
                        this.j.a();
                        if (this.as) {
                            this.j.n();
                        }
                        this.dq = 5;
                    }
                    return false;
                }
            }
        }
        if (this.t > this.dn) {
            this.t = this.dn;
        }
        if (this.t < this.dv) {
            this.t = this.dv;
        }
        int n3 = 0;
        switch (this.am) {
            case 1: {
                if (this.t == this.di) {
                    break;
                }
                this.dl = this.j.p(this.t);
                if (this.j != null) {
                    this.db = this.j.p();
                    break;
                }
                break;
            }
            case 2: {
                final int height2 = this.g.getHeight(null);
                if (height2 - this.d > 0) {
                    this.dl = height2 / 2 / 120 * 2.0;
                }
                else {
                    this.dl = 0.0;
                }
                if (this.j != null) {
                    this.db = this.j.p();
                    break;
                }
                break;
            }
            case 3: {
                if (Math.abs(this.j.d() - 360.0) > 1.0E-4) {
                    if (this.j.d < 0) {
                        this.j.d = 0;
                        n3 = 1;
                    }
                    if (this.j.d >= this.j.p) {
                        this.j.d = this.j.p - 1;
                        n3 = 1;
                    }
                    if (this.dq == 80 && n3 != 0) {
                        this.dt *= -1.0;
                    }
                }
                else {
                    if (this.j.d >= this.j.p) {
                        this.j.d = 0;
                    }
                    if (this.j.d < 0) {
                        this.j.d = this.j.p - 1;
                    }
                }
                return true;
            }
        }
        this.di = this.t;
        if (this.s < -this.dl) {
            this.s = -this.dl;
        }
        if (this.s > this.dl) {
            this.s = this.dl;
        }
        final double d = this.j.d();
        if (Math.abs(d - 360.0) > 1.0E-4) {
            if (this.r < this.db) {
                this.r = this.db;
                this.u = 0.0;
                n3 = 1;
            }
            if (this.r > d - this.db) {
                this.r = d - this.db;
                this.u = 0.0;
                n3 = 1;
            }
            if (this.dq == 80 && n3 != 0) {
                this.dt *= -1.0;
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
        return true;
    }
    
    private final synchronized void v(final int n) {
        if (n == 502) {
            this.c();
        }
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
            this.nf = this.p(p, n3);
            n3 += 4;
            final int p2 = this.p(p, n3);
            n3 += 4;
            final int p3 = this.p(p, n3);
            n3 += 4;
            final int p4 = this.p(p, n3);
            n3 += 4;
            this.nh = new Date(p4 - 1900, p3 - 1, p2);
            this.nc = this.p(p, n3);
            final int n4 = n3 + (4 + this.nc.length());
            final int n5 = n4 + (4 + this.p(p, n4).length());
            final String p5 = this.p(p, n5);
            final int n6 = n5 + (4 + p5.length());
            if ((this.nf & 0xFFF) == 0x3EB) {
                String nc = p5.trim();
                try {
                    nc = new URL(nc).getHost();
                }
                catch (MalformedURLException ex3) {
                    final String string = "http://" + nc;
                    try {
                        nc = new URL(string).getHost();
                    }
                    catch (MalformedURLException ex4) {}
                }
                this.nc = nc;
            }
            this.ne = this.p(p, n6);
            final int n7 = n6 + (4 + this.ne.length());
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
        if (!this.ab) {
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
            this.j = this.p(this.a, "", 0.0);
            if (this.j.p == 0.0) {
                this.j.p = this.k;
            }
            this.am = this.j.a;
            this.j.p((int[])this.h.getPixels(), this.h.getWidth(), this.h.getHeight(), this.h.getWidth(), this.p, this.d, 32, this.j.p);
            if (this.am == 3) {
                this.j.p = this.j.p;
                this.j.d = this.j.n;
            }
            this.SetTitle(this.j.d, -1, -1, -1, -1);
            final Applet applet = this.getAppletContext().getApplet("myBard");
            if (applet != null && applet instanceof CBard) {
                ((CBard)applet).SetStory(this.j.a);
            }
            this.f = true;
            this.e = this.h.getColorModel();
        }
        if (this.do) {
            this.r = this.dj;
            this.s = this.dk;
            this.t = this.dm;
            this.do = false;
        }
        else {
            this.r = this.j.a;
            this.s = this.j.n;
            this.t = this.j.v;
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
        this.j.p(this.t);
        this.db = this.j.p();
        final double d = this.j.d();
        if (this.j.p()) {
            if (this.r < this.db) {
                this.r = this.db;
            }
            if (this.r > d - this.db) {
                this.r = d - this.db;
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
        this.dl = this.j.p(this.t);
        if (this.s < -this.dl) {
            this.s = -this.dl;
        }
        if (this.s > this.dl) {
            this.s = this.dl;
        }
        if (this.am != 1) {
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
        if (n3 > this.dn) {
            n3 = this.dn;
        }
        if (n3 < this.dv) {
            n3 = this.dv;
        }
        this.dq = 100;
        this.j.p();
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
        final double dg = n8 / n4;
        this.dc = n5 / dg;
        this.de = n6 / dg;
        this.df = n7 / dg;
        this.dg = dg;
        this.dh = 0.0;
        if (this.ad == 0) {
            this.j.p(false);
        }
    }
    
    private final synchronized void p(final String a, final int nd, final int n, final int n2, final int n3) {
        this.na.a = a;
        if (a.length() == 0) {
            this.na.setVisible(false);
        }
        else {
            this.na.setVisible(true);
        }
        if (nd != -1) {
            this.nd = nd;
        }
        if (n != -1) {
            this.na.v = new Color(n);
        }
        if (n2 != -1) {
            this.na.i = new Color(n2);
        }
        if (n3 != -1) {
            this.na.n = new Color(n3);
        }
        final int n4 = 100;
        final int n5 = 20;
        final int d = this.na.d();
        final int p5 = this.na.p();
        Point point = null;
        switch (this.nd) {
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
            this.na.move(point.x, point.y);
            this.na.resize(p5, d);
        }
        this.na.repaint();
    }
    
    public final void run() {
        Thread.currentThread().setPriority(1);
        while (this.m) {
            if (!this.l && this.j != null) {
                this.l = true;
                this.a();
            }
            if (this.p()) {
                this.n();
                this.repaint();
                if (this.dy <= 0) {
                    continue;
                }
                try {
                    Thread.sleep(this.dy);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public final void init() {
        this.p("ss2.gif");
        this.d();
        if ((this.nf & 0xFFF) == 0x3EB) {
            this.ah = false;
            final String parameter = this.getParameter("autoHide");
            if (parameter != null && parameter.compareTo("yes") == 0) {
                this.af = true;
            }
        }
        this.p();
        this.ar = new Hashtable();
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.dq == 90) {
            return true;
        }
        this.requestFocus();
        this.mouseMove(event, n, n2);
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int dr, final int nb) {
        this.nl = dr;
        this.nb = nb;
        if (this.o != null && this.j != null) {
            switch (this.dq) {
                case 10:
                case 20:
                case 105: {
                    this.dt = dr - this.dr;
                    this.du = this.ds - nb;
                    if (this.dq != 105 || this.j == null) {
                        break;
                    }
                    if (this.dt > this.ax) {
                        final _zs j = this.j;
                        ++j.d;
                        this.dr = dr;
                        break;
                    }
                    if (this.dt < -this.ax) {
                        final _zs i = this.j;
                        --i.d;
                        this.dr = dr;
                        break;
                    }
                    break;
                }
                case 0:
                case 110: {
                    if (this.j == null) {
                        break;
                    }
                    this.ao = this.j.p(dr, nb);
                    if (this.ao != this.aq) {
                        if (this.aq != null) {
                            this.aq.p(30);
                            this.dq = 0;
                        }
                        if (this.ao != null) {
                            this.p(123, dr, nb, false);
                        }
                        if (this.aq != null && this.ao == null) {
                            this.dq = 5;
                        }
                        this.aq = this.ao;
                        break;
                    }
                    break;
                }
            }
            if (this.af) {
                if (dr > this.an && dr < this.ai && nb > this.av && nb < this.al) {
                    if (this.ag) {
                        this.au.setVisible(true);
                        this.ag = false;
                    }
                }
                else if (!this.ag) {
                    this.au.setVisible(false);
                    this.ag = true;
                }
            }
        }
        return true;
    }
}
