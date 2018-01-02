import java.applet.AppletStub;
import java.io.InputStream;
import java.net.URLConnection;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Event;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ptviewer extends Applet implements Runnable
{
    AudioClip a;
    static final boolean b = false;
    static final double c = 10.5;
    static final double d = 165.0;
    static final long e = 10L;
    static final long f = 100000000L;
    int g;
    boolean h;
    Color i;
    long j;
    long k;
    boolean l;
    Image m;
    Image n;
    Image o;
    Image p;
    Graphics q;
    int r;
    int s;
    MemoryImageSource t;
    int u;
    int v;
    public int w;
    public int x;
    boolean y;
    int z;
    int A;
    int B;
    int C;
    int[] D;
    byte[] E;
    int[][] F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    boolean L;
    boolean M;
    boolean N;
    public boolean O;
    boolean P;
    boolean Q;
    int R;
    int S;
    int T;
    int U;
    int V;
    public double W;
    public double X;
    public double Y;
    public double Z;
    public double ba;
    public double bb;
    public double bc;
    public double bd;
    public double be;
    public double bf;
    double bg;
    double bh;
    double bi;
    double bj;
    double bk;
    double bl;
    public double bm;
    String bn;
    String bo;
    String bp;
    String bq;
    int br;
    int bs;
    long bt;
    long bu;
    long bv;
    Thread bw;
    Thread bx;
    String by;
    String bz;
    boolean bA;
    int bB;
    Hashtable bC;
    Thread bD;
    String bE;
    String bF;
    int bG;
    int bH;
    int bI;
    Hashtable bJ;
    boolean bK;
    Color bL;
    int bM;
    int bN;
    int bO;
    int bP;
    int[] bQ;
    int bR;
    int bS;
    int[] bT;
    int[] bU;
    int[] bV;
    int[] bW;
    String[] bX;
    String[] bY;
    Object[] bZ;
    boolean[] ca;
    int[] cb;
    Vector cc;
    static int[] cd;
    int[] ce;
    int[] cf;
    int[] cg;
    int[] ch;
    int[] ci;
    int[] cj;
    static int[] ck;
    static int cl;
    static int cm;
    static final int cn = 4096;
    static final int co = 4096;
    private double[][] cp;
    private int[][] cq;
    int cr;
    String[] cs;
    int[] ct;
    int[] cu;
    boolean[] cv;
    Vector cw;
    Hashtable cx;
    Vector cy;
    Vector cz;
    int cA;
    int cB;
    Object cC;
    double[] cD;
    double[] cE;
    double[] cF;
    double[] cG;
    int[] cH;
    int[] cI;
    Color[] cJ;
    String[] cK;
    String[] cL;
    String[] cM;
    Object[] cN;
    String[] cO;
    boolean[] cP;
    int[] cQ;
    int[] cR;
    static final double cS = -200.0;
    static final int cT = 12;
    static final int cU = 0;
    static final int cV = 1;
    static final int cW = 2;
    static final int cX = 4;
    static final int cY = 8;
    static final int cZ = 16;
    static Class da;
    static Class db;
    
    public ptviewer() {
        this.g = 3;
        this.h = false;
        this.l = false;
        this.u = 320;
        this.v = 200;
        this.y = false;
        this.G = true;
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = true;
        this.M = true;
        this.N = false;
        this.O = true;
        this.P = false;
        this.Q = false;
        this.V = 0;
        this.W = 15.0;
        this.Y = 70.0;
        this.Z = 10.5;
        this.ba = 165.0;
        this.bc = 90.0;
        this.bd = -90.0;
        this.be = 180.0;
        this.bf = -180.0;
        this.bl = 1.0;
        this.bm = 20.0;
        this.br = -1;
        this.bs = -1;
        this.bA = true;
        this.bB = -1;
        this.bG = 524288;
        this.bH = 16777215;
        this.bK = true;
        this.bL = Color.gray;
        this.bM = -1;
        this.bN = -1;
        this.bO = -1;
        this.bP = 10;
        this.bS = -1;
        this.cB = -1;
    }
    
    public ptviewer(final int[][] f) {
        this.g = 3;
        this.h = false;
        this.l = false;
        this.u = 320;
        this.v = 200;
        this.y = false;
        this.G = true;
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = true;
        this.M = true;
        this.N = false;
        this.O = true;
        this.P = false;
        this.Q = false;
        this.V = 0;
        this.Y = 70.0;
        this.Z = 10.5;
        this.ba = 165.0;
        this.bc = 90.0;
        this.bd = -90.0;
        this.be = 180.0;
        this.bf = -180.0;
        this.bl = 1.0;
        this.bm = 20.0;
        this.br = -1;
        this.bs = -1;
        this.bA = true;
        this.bB = -1;
        this.bG = 524288;
        this.bH = 16777215;
        this.bK = true;
        this.bL = Color.gray;
        this.bM = -1;
        this.bN = -1;
        this.bO = -1;
        this.bP = 10;
        this.bS = -1;
        this.cB = -1;
        this.F = f;
        this.J = true;
        this.a(this.F);
        this.bn = n("^B\u001c+");
    }
    
    void a() {
        this.cA = 0;
        this.cB = -1;
        this.bS = -1;
        this.cr = 0;
        this.bA = true;
        this.X = 0.0;
        this.Y = 70.0;
        this.Z = 10.5;
        this.ba = 165.0;
        this.bb = 0.0;
        this.bc = 90.0;
        this.bd = -90.0;
        this.be = 180.0;
        this.bf = -180.0;
        this.bj = 0.0;
        this.bk = 0.0;
        this.bl = 1.0;
        this.B = 0;
        this.C = 0;
        this.u();
        this.bu = 0L;
        this.O = true;
        this.P = false;
        this.Q = false;
        this.bp = null;
        this.bq = null;
        this.l = false;
        this.bm = 20.0;
        this.bF = null;
    }
    
    public void init() {
        this.K = false;
        this.bD = null;
        this.bE = null;
        this.V = 0;
        this.A();
        this.H();
        this.D();
        this.N();
        this.K();
        this.E();
        this.O();
        this.bC = new Hashtable();
        this.h = true;
        this.repaint();
        this.a();
        this.c(null);
        if (this.bn != null && this.bn.startsWith(n("~W\u0004--yF\u0000~"))) {
            final int int1 = Integer.parseInt(this.bn.substring(this.bn.indexOf(58) + 1));
            if (this.b(null, n("~B\u001c+") + int1) != null) {
                this.bn = null;
                this.c(this.b(null, n("~B\u001c+") + int1));
            }
        }
    }
    
    public String getAppletInfo() {
        return n("^w$--yF\u0000d> \u0003@jz.\u0003\u00dbd\u0000 \u00036!:}@\u001ahhjF\u0000\u0004.f\u000e\u00141:zT\u0013*/kM\\ -");
    }
    
    public void start() {
        if (this.bw == null) {
            (this.bw = new Thread(this)).start();
        }
    }
    
    public synchronized void stop() {
        this.a(this.bD);
        this.bD = null;
        this.a(this.bw);
        this.bw = null;
        this.t();
        this.u();
        this.q(0);
        this.H = false;
        this.I = false;
        this.D = null;
        this.E = null;
        this.m = null;
        if (!this.y) {
            this.w = 0;
            this.x = 0;
        }
        this.p = null;
    }
    
    synchronized void b() {
        this.H = false;
        this.I = false;
        this.P();
        this.J();
        this.J = false;
        this.bn = null;
        this.bp = null;
        this.bq = null;
        this.C();
        this.bo = null;
        this.bF = null;
        System.gc();
    }
    
    public synchronized void destroy() {
        if (this.a != null) {
            this.a.stop();
        }
        this.a(this.bx);
        this.bx = null;
        this.b();
        if (this.bC != null) {
            this.bC.clear();
            this.bC = null;
        }
        this.D = null;
        this.E = null;
        this.t = null;
        this.o = null;
        this.m = null;
        this.n = null;
        this.F = null;
        this.I();
        this.G();
        this.L();
        System.gc();
    }
    
    public void run() {
        if (Thread.currentThread() == this.bD && this.bE != null) {
            final int a = this.a(this.bE, ',');
            if (a > 0) {
                for (int i = 0; i < a; ++i) {
                    final String a2 = this.a(i, this.bE, ',');
                    if (a2 != null && this.bK && this.bJ != null && this.bJ.get(a2) == null && a2 != this.bn) {
                        this.a(a2, (int[])null);
                    }
                }
            }
            return;
        }
        if (Thread.currentThread() == this.bx) {
            if (this.by != null) {
                this.f(this.by);
            }
            return;
        }
        this.c();
        if (!this.J) {
            this.G = true;
            if (this.bn == null) {
                if (this.B != 0) {
                    this.bn = n("Qs&\u001b\u000f|J\u0016");
                }
                else {
                    this.G = false;
                }
            }
            if (this.bn != null && this.bn.toLowerCase().endsWith(n(" N\u001d2"))) {
                this.F = this.a(null, this.F, this.B, this.C);
            }
            else {
                this.F = this.a(this.bn, this.F, this.B, this.C);
            }
            System.gc();
        }
        if (this.F == null) {
            this.K = true;
            this.repaint();
            return;
        }
        if (this.bn != null && this.bn.toLowerCase().endsWith(n(" N\u001d2"))) {
            try {
                String s = n(".X\u0014-$k\u001e") + this.bn + n("s\u0003");
                if (this.bF != null) {
                    s = String.valueOf(s) + n("uL\u0000 -|\u001e") + this.bF + n("s\u0003");
                }
                final Applet applet = (Applet)Class.forName(n("~W\u001f2!kT\u00176")).getConstructor(Class.forName(n("~W\u0004--yF\u0000")), (ptviewer.da == null) ? (ptviewer.da = m(n("dB\u0004%fbB\u001c#f]W\u0000-&i"))) : ptviewer.da).newInstance(this, s);
                applet.init();
                applet.start();
                System.gc();
            }
            catch (Exception ex) {}
        }
        this.C = this.F.length;
        this.B = this.F[0].length;
        if (this.C != this.B / 2) {
            final double bc = this.C / this.B * 180.0;
            if (this.bc > bc) {
                this.bc = bc;
            }
            if (this.bd < -bc) {
                this.bd = -bc;
            }
        }
        if (this.Y > this.be - this.bf) {
            this.Y = this.be - this.bf;
        }
        if (!this.J) {
            this.a(this.F);
        }
        this.a(this.J);
    }
    
    void a(final boolean b) {
        if (!b) {
            this.F();
        }
        this.H = true;
        this.requestFocus();
        this.c();
        this.repaint();
        this.paint(this.getGraphics());
        if (this.bA && !this.J) {
            this.b(0, this.cr - 1);
        }
        if (!this.J) {
            this.b(this.F);
        }
        this.I = true;
        this.J = true;
        if (this.bj != 0.0) {
            this.bu = this.bt + 100000000L;
        }
        if (this.bo != null) {
            this.a(this.bo, (String)null);
        }
        this.repaint();
        this.M();
        if (this.bE != null && this.bD == null) {
            this.bD = new Thread(this);
            try {
                this.bD.setPriority(1);
            }
            catch (SecurityException ex) {}
            this.bD.start();
        }
    }
    
    public boolean mouseDown(final Event event, final int t, final int u) {
        if (t >= this.z && t < this.z + this.w && u >= this.A && u < this.A + this.x) {
            if (this.bu > this.bt) {
                this.a(this.bx);
                this.bx = null;
                this.t();
                this.R = t;
                this.S = u;
                return true;
            }
            if (this.Q) {
                this.showStatus(this.c(t - this.z, u - this.A));
                this.Q = false;
                return true;
            }
        }
        if (!this.N && this.M) {
            this.R = t;
            this.S = u;
            if (this.cB < 0) {
                this.N = true;
                if (event.shiftDown()) {
                    this.bl = 0.970873786407767;
                }
                else if (event.controlDown()) {
                    this.bl = 1.03;
                }
                else {
                    this.bl = 1.0;
                }
                this.repaint();
                this.a(t, u);
            }
        }
        this.T = t;
        this.U = u;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int t, final int u) {
        this.T = t;
        this.U = u;
        if (this.M) {
            this.N = true;
            if (event.shiftDown()) {
                this.bl = 0.970873786407767;
            }
            else if (event.controlDown()) {
                this.bl = 1.03;
            }
            else {
                this.bl = 1.0;
            }
            this.c();
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.T = n;
        this.U = n2;
        this.u();
        this.bl = 1.0;
        if (this.I) {
            if (this.bS >= 0) {
                for (int i = 0; i < this.bR; ++i) {
                    if (this.ca[i]) {
                        this.c(i);
                    }
                }
            }
            else if (this.cB >= 0) {
                this.b(this.cB);
                for (int n3 = this.cB + 1; n3 < this.cA && this.cB != -1; ++n3) {
                    if (this.cR[n3] == this.cB) {
                        this.b(n3);
                    }
                }
                if (this.cB < 0) {
                    return true;
                }
            }
            this.a(n, n2);
            this.br = n;
            this.bs = n2;
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.L = true;
        this.M = this.e(n, n2);
        this.a(n, n2);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        final boolean b = false;
        this.M = b;
        this.L = b;
        this.u();
        this.bl = 1.0;
        this.c();
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!this.H) {
            return true;
        }
        switch (n) {
            case 1004: {
                this.i();
                break;
            }
            case 1005: {
                this.j();
                break;
            }
            case 1006: {
                this.k();
                break;
            }
            case 1007: {
                this.l();
                break;
            }
            case 43:
            case 46:
            case 61:
            case 62:
            case 65:
            case 97: {
                this.g();
                break;
            }
            case 44:
            case 45:
            case 60:
            case 90:
            case 95:
            case 122: {
                this.h();
                break;
            }
            case 32: {
                this.o();
                break;
            }
            case 73:
            case 105: {
                this.showStatus(this.getAppletInfo());
                break;
            }
            case 118: {
                this.showStatus(n("~B\u001cdu.") + (int)(this.X * 100.0) / 100.0 + n("¾\u0018R0!bWRyh") + (int)(this.bb * 100.0) / 100.0 + n("¾\u0018R\"'x\u0003Od") + (int)(this.Y * 100.0) / 100.0 + "°");
                break;
            }
            case 80:
            case 112: {
                this.showStatus(this.z());
                break;
            }
            case 85:
            case 117: {
                this.showStatus(this.getDocumentBase().toString());
                break;
            }
            case 104: {
                this.Q = true;
                this.showStatus(n("MO\u001b'#.n\u001d1;k\u0003\u0006+hjJ\u00014$oZR\u001cgW\u00031+'|G\u001b*)zF\u0001"));
                break;
            }
            case 10: {
                if (!this.I) {
                    break;
                }
                if (this.bS >= 0) {
                    for (int i = 0; i < this.bR; ++i) {
                        if (this.ca[i]) {
                            this.c(i);
                        }
                    }
                    break;
                }
                if (this.N) {
                    break;
                }
                if (this.cB < 0) {
                    break;
                }
                this.b(this.cB);
                for (int n2 = this.cB + 1; n2 < this.cA && this.cB != -1; ++n2) {
                    if (this.cR[n2] == this.cB) {
                        this.b(n2);
                    }
                }
                if (this.cB < 0) {
                    return true;
                }
                break;
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int t, final int u) {
        this.M = this.e(t, u);
        if (this.L) {
            this.T = t;
            this.U = u;
        }
        this.a(t, u);
        return true;
    }
    
    void a(final int n, final int n2) {
        if (!this.L) {
            this.c();
            return;
        }
        int f;
        if (!this.H) {
            f = -1;
        }
        else {
            f = this.f(n, n2);
        }
        if (f != this.bS) {
            this.bS = f;
            if (this.bS >= 0) {
                try {
                    ((Frame)this.getParent()).setCursor(12);
                }
                catch (Exception ex) {}
                this.cB = -1;
                this.repaint();
                return;
            }
            this.c();
            this.repaint();
        }
        if (this.bS >= 0) {
            return;
        }
        if (this.N || this.bu > this.bt || !this.M) {
            this.cB = -1;
            this.c();
            return;
        }
        int d;
        if (!this.I) {
            d = -1;
        }
        else {
            d = this.d(n - this.z, n2 - this.A);
        }
        Label_0250: {
            if (d != this.cB) {
                this.cB = d;
                if (this.cB >= 0) {
                    try {
                        ((Frame)this.getParent()).setCursor(12);
                        if (this.I) {
                            this.showStatus(this.cK[this.cB]);
                            this.s(this.cB);
                            this.repaint();
                            this.e();
                        }
                        return;
                    }
                    catch (Exception ex2) {
                        break Label_0250;
                    }
                }
                this.c();
                this.repaint();
                this.showStatus("");
                this.e();
                return;
            }
        }
        if (this.cB >= 0) {
            return;
        }
        this.c();
    }
    
    void c() {
        try {
            if (this.M) {
                if (!this.H) {
                    ((Frame)this.getParent()).setCursor(3);
                    return;
                }
                if (((Frame)this.getParent()).getCursorType() != this.V) {
                    ((Frame)this.getParent()).setCursor(this.V);
                }
            }
            else if (((Frame)this.getParent()).getCursorType() != 0) {
                ((Frame)this.getParent()).setCursor(0);
            }
        }
        catch (Exception ex) {}
    }
    
    void d() {
        if (this.bq != null && this.H && this.bw != null) {
            this.d(String.valueOf(this.bq) + "(" + this.X + "," + this.bb + "," + this.Y + ")");
        }
    }
    
    void e() {
        if (this.bp != null && this.H && this.bw != null) {
            this.d(String.valueOf(this.bp) + "(" + this.cB + ")");
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (!this.h) {
            return;
        }
        if (this.K) {
            this.setBackground(Color.red);
            graphics.clearRect(0, 0, this.size().width, this.size().height);
            return;
        }
        if (this.p == null) {
            this.u = this.size().width;
            this.v = this.size().height;
            if (!this.y || this.r == 0) {
                this.r = this.size().width;
                this.s = this.size().height;
            }
            this.p = this.createImage(this.r, this.s);
            this.q = this.p.getGraphics();
        }
        if (!this.H || System.currentTimeMillis() < this.bv) {
            if (this.n != null) {
                if (this.i != null && !this.l) {
                    this.setBackground(this.i);
                    this.q.clearRect(0, 0, this.r, this.s);
                }
                if (!this.l) {
                    if (this.j != 0L) {
                        this.bv = System.currentTimeMillis() + this.j;
                    }
                    this.l = true;
                }
                this.q.drawImage(this.n, (this.r - this.n.getWidth(null)) / 2, (this.s - this.n.getHeight(null)) / 2, this);
                this.a(this.q, this.r, this.s);
                graphics.drawImage(this.p, 0, 0, this);
                if (this.H) {
                    try {
                        Thread.sleep(20L);
                    }
                    catch (InterruptedException ex) {
                        return;
                    }
                    this.repaint();
                }
            }
            else {
                if (this.i != null) {
                    this.setBackground(this.i);
                }
                graphics.clearRect(0, 0, this.size().width, this.size().height);
                if (this.bQ != null && this.bQ[0] > 0) {
                    graphics.drawString(n("BL\u0013 !`DR\r%oD\u0017jf ") + this.bQ[0] + n("+\u0003\u0011+%~O\u00170-"), 30, this.size().height / 2);
                    return;
                }
                graphics.drawString(n("BL\u0013 !`DR\r%oD\u0017jf "), 30, this.size().height / 2);
            }
            return;
        }
        if (this.D == null) {
            if (this.w == 0) {
                this.w = this.size().width;
            }
            if (this.x == 0) {
                this.x = this.size().height;
            }
            while (114.59155902616465 * Math.atan(this.x / this.w * Math.tan(this.Y / 2.0 * 3.141592653589793 / 180.0)) > this.bc - this.bd) {
                this.Y /= 1.03;
            }
            final double n = 114.59155902616465 * Math.atan(this.x / this.w * Math.tan(this.Y / 2.0 * 3.141592653589793 / 180.0)) / 2.0;
            if (this.bb > this.bc - n && this.bc != 90.0) {
                this.bb = 0.0;
            }
            if (this.bb < this.bd + n && this.bd != -90.0) {
                this.bb = 0.0;
            }
            this.D = new int[this.w * this.x];
            this.E = new byte[this.w * this.x];
            if (this.bn != null && this.bn.toLowerCase().endsWith(n(" N\u001d2"))) {
                for (int i = 0; i < this.E.length; ++i) {
                    this.E[i] = 0;
                }
            }
            else {
                for (int j = 0; j < this.E.length; ++j) {
                    this.E[j] = -1;
                }
            }
            this.O = true;
            (this.t = new MemoryImageSource(this.w, this.x, this.D, 0, this.w)).setAnimated(true);
            if (this.m == null) {
                this.m = this.createImage(this.t);
            }
        }
        if (this.N) {
            final double n2 = 5.0E-4 * this.Y / 70.0 * 320.0 / this.w;
            final double bh = ((this.T - this.R) * (this.T - this.R) * ((this.T <= this.R) ? -1.0 : 1.0) + this.bg * this.bh) / (1.0 + this.bg);
            this.bh = bh;
            final double bi = ((this.S - this.U) * (this.S - this.U) * ((this.S <= this.U) ? -1.0 : 1.0) + this.bg * this.bi) / (1.0 + this.bg);
            this.bi = bi;
            double w = n2 * bh;
            if (w > this.W) {
                w = this.W;
            }
            else if (w < -this.W) {
                w = -this.W;
            }
            double w2 = n2 * bi;
            if (w2 > this.W) {
                w2 = this.W;
            }
            else if (w2 < -this.W) {
                w2 = -this.W;
            }
            this.b(this.X + w, this.bb + w2, this.Y * this.bl);
        }
        if (this.bu > this.bt) {
            this.b(this.X + this.bj, this.bb + this.bk, this.Y * this.bl);
        }
        if (this.I && this.a(this.F, this.cB, this.P)) {
            this.O = true;
        }
        if (this.O) {
            for (int k = 0; k < this.D.length; ++k) {
                this.D[k] = 0;
            }
            for (int l = 0; l < this.cy.size(); ++l) {
                final Applet applet = this.cx.get(this.cy.elementAt(l));
                if (applet != null && this.bC != null && this.bC.get(applet) != null) {
                    final String appletInfo = applet.getAppletInfo();
                    if (this.O && appletInfo != null && appletInfo.equals(n("zL\u0002\u0002:oN\u0017"))) {
                        applet.paint(null);
                    }
                }
            }
            if (this.O && this.G) {
                switch (this.g) {
                    case 0: {
                        final int[][] f = this.F;
                        final int[] d = this.D;
                        final byte[] e = this.E;
                        final int w3 = this.w;
                        this.b(this.Y, this.X, this.bb, w3);
                        this.a(f, f[0].length, f.length, d, e, w3, d.length / w3, false);
                        this.O = false;
                        break;
                    }
                    case 1: {
                        if (this.N || this.bu > this.bt) {
                            final int[][] f2 = this.F;
                            final int[] d2 = this.D;
                            final byte[] e2 = this.E;
                            final int w4 = this.w;
                            this.b(this.Y, this.X, this.bb, w4);
                            this.a(f2, f2[0].length, f2.length, d2, e2, w4, d2.length / w4, false);
                            break;
                        }
                        final int[][] f3 = this.F;
                        final int[] d3 = this.D;
                        final byte[] e3 = this.E;
                        final int w5 = this.w;
                        this.b(this.Y, this.X, this.bb, w5);
                        this.a(f3, f3[0].length, f3.length, d3, e3, w5, d3.length / w5, true);
                        System.gc();
                        this.O = false;
                        break;
                    }
                    case 2: {
                        if (this.N) {
                            final int[][] f4 = this.F;
                            final int[] d4 = this.D;
                            final byte[] e4 = this.E;
                            final int w6 = this.w;
                            this.b(this.Y, this.X, this.bb, w6);
                            this.a(f4, f4[0].length, f4.length, d4, e4, w6, d4.length / w6, false);
                            break;
                        }
                        final int[][] f5 = this.F;
                        final int[] d5 = this.D;
                        final byte[] e5 = this.E;
                        final int w7 = this.w;
                        this.b(this.Y, this.X, this.bb, w7);
                        this.a(f5, f5[0].length, f5.length, d5, e5, w7, d5.length / w7, true);
                        System.gc();
                        this.O = false;
                        break;
                    }
                    case 3: {
                        final int[][] f6 = this.F;
                        final int[] d6 = this.D;
                        final byte[] e6 = this.E;
                        final int w8 = this.w;
                        this.b(this.Y, this.X, this.bb, w8);
                        this.a(f6, f6[0].length, f6.length, d6, e6, w8, d6.length / w8, true);
                        this.O = false;
                        break;
                    }
                }
            }
            this.a(this.w, this.x, this.B, this.C, this.X, this.bb, this.Y);
            this.d();
            ++this.bt;
            this.t.newPixels();
        }
        if (this.N || this.bu > this.bt) {
            this.a(this.T, this.U);
        }
        this.q.drawImage(this.m, this.z, this.A, this);
        if (this.I) {
            this.a(this.q, this.z, this.A, this.w, this.x, this.cB, this.P);
        }
        if (this.o != null) {
            this.q.drawImage(this.o, this.r - this.o.getWidth(null), this.s - this.o.getHeight(null), this);
        }
        if (this.H) {
            this.a(this.q);
        }
        final Enumeration<Applet> elements = (Enumeration<Applet>)this.bC.elements();
        while (elements.hasMoreElements()) {
            try {
                final Applet applet2 = elements.nextElement();
                if (applet2.getAppletInfo() == n("zL\u0002\u0002:oN\u0017")) {
                    continue;
                }
                applet2.paint(this.q);
            }
            catch (Exception ex2) {}
        }
        graphics.drawImage(this.p, 0, 0, this);
    }
    
    public void b(final int n, final int n2) {
        for (int i = n; i <= n2; ++i) {
            this.a(i);
        }
    }
    
    public void a(final int n) {
        if (n < this.cr && !this.cv[n]) {
            final Image h = this.h(this.cs[n]);
            if (h != null) {
                this.a(this.F, this.ct[n], this.cu[n], h, (this.C + 99) / 100);
                if (this.I) {
                    for (int i = 0; i < this.cA; ++i) {
                        if ((this.cQ[i] & 0x4) > 0) {
                            final int n2 = (int)this.cF[i];
                            final int n3 = (int)this.cG[i];
                            this.b(this.F, (int)this.cD[i] - n2 / 2, (int)this.cE[i] - n3 / 2, (int[])this.cN[i], n2, 0, n3, n2, n3);
                        }
                    }
                }
                this.cv[n] = true;
            }
        }
    }
    
    String c(final int n, final int n2) {
        final double[] a = this.a(n, n2, this.w, this.x, this.B, this.C, this.X, this.bb, this.Y);
        a[0] = Math.rint(a[0] * 100000.0 / this.B) / 1000.0;
        a[1] = Math.rint(a[1] * 100000.0 / this.C) / 1000.0;
        return n("V\u0003Od") + a[0] + n("5\u0003+du.") + a[1];
    }
    
    int d(final int n, final int n2) {
        if (!this.I || n < 0 || n >= this.w || n2 < 0 || n2 >= this.x) {
            return -1;
        }
        final int n3 = this.E[n2 * this.w + n] & 0xFF;
        if (this.bn != null && this.bn.toLowerCase().endsWith(n(" N\u001d2"))) {
            if (n3 == 0) {
                return -1;
            }
            return n3 - 1;
        }
        else {
            if (n3 != 255 && n3 < this.cA) {
                return n3;
            }
            if (this.cC != null) {
                return -1;
            }
            for (int i = 0; i < this.cA; ++i) {
                if (this.cP[i] && this.cO[i] == null && this.cR[i] == -1 && this.cF[i] == -200.0 && this.cG[i] == -200.0 && n < this.cH[i] + 12 && n > this.cH[i] - 12 && n2 < this.cI[i] + 12 && n2 > this.cI[i] - 12) {
                    return i;
                }
            }
            return -1;
        }
    }
    
    public void f() {
        while (this.bu > this.bt) {
            try {
                Thread.sleep(200L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void g() {
        this.b(this.X, this.bb, this.Y / 1.03);
    }
    
    public void h() {
        this.b(this.X, this.bb, this.Y * 1.03);
    }
    
    public void i() {
        this.b(this.X, this.bb + this.Y / this.bm, this.Y);
    }
    
    public void j() {
        this.b(this.X, this.bb - this.Y / this.bm, this.Y);
    }
    
    public void k() {
        this.b(this.X - this.Y / this.bm, this.bb, this.Y);
    }
    
    public void l() {
        this.b(this.X + this.Y / this.bm, this.bb, this.Y);
    }
    
    public void m() {
        this.P = true;
        this.repaint();
    }
    
    public void n() {
        this.P = false;
        this.repaint();
    }
    
    public void o() {
        this.P = !this.P;
        this.repaint();
    }
    
    public boolean p() {
        return this.P;
    }
    
    public double q() {
        return this.X;
    }
    
    public double r() {
        return this.bb;
    }
    
    public double s() {
        return this.Y;
    }
    
    public void a(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final int n7) {
        double n8 = 0.0;
        final double n9 = (n4 - n3) / n7;
        final double pow = Math.pow(n6 / n5, 1.0 / n7);
        if (Math.abs(n2 - n) < 180.0 || this.be != 180.0 || this.bf != -180.0) {
            n8 = (n2 - n) / n7;
        }
        else if (n2 > n) {
            n8 = (n2 - n - 360.0) / n7;
        }
        else if (n2 < n) {
            n8 = (n2 - n + 360.0) / n7;
        }
        this.b(n, n3, n5);
        this.bu = this.bt + n7;
        this.a(n8, n9, pow);
    }
    
    public void a(final double n, final double n2, final double n3, final int n4) {
        this.a(this.X, n, this.bb, n2, this.Y, n3, n4);
    }
    
    public void a(final double bj, final double bk, final double bl) {
        this.bj = bj;
        this.bk = bk;
        this.bl = bl;
        if (this.bu <= this.bt) {
            this.bu = this.bt + 100000000L;
        }
        this.repaint();
    }
    
    public void t() {
        this.bu = 0L;
        this.bj = 0.0;
        this.bk = 0.0;
        this.bl = 1.0;
    }
    
    void u() {
        this.N = false;
        this.bh = 0.0;
        this.bi = 0.0;
    }
    
    public boolean v() {
        return this.bu > this.bt;
    }
    
    public void b(double x, double bb, final double y) {
        if (x == this.X && bb == this.bb && y == this.Y) {
            return;
        }
        while (x > 180.0) {
            x -= 360.0;
        }
        while (x < -180.0) {
            x += 360.0;
        }
        final double n = 114.59155902616465 * Math.atan(this.x / this.w * Math.tan(y / 2.0 * 3.141592653589793 / 180.0)) / 2.0;
        if (bb > this.bc - n && this.bc != 90.0) {
            bb = this.bc - n;
        }
        else if (bb > this.bc) {
            bb = this.bc;
        }
        else if (bb < this.bd + n && this.bd != -90.0) {
            bb = this.bd + n;
        }
        else if (bb < this.bd) {
            bb = this.bd;
        }
        if (this.be != 180.0 || this.bf != -180.0) {
            final double n2 = this.a(0, (this.bb <= 0.0) ? (this.x - 1) : 0, this.w, this.x, this.B, this.C, x, bb, y)[0];
            final double n3 = this.a(this.w - 1, (this.bb <= 0.0) ? (this.x - 1) : 0, this.w, this.x, this.B, this.C, x, bb, y)[0];
            if (n3 - n2 > (this.be - this.bf) / 360.0 * this.B) {
                return;
            }
            if (n2 < (this.bf + 180.0) / 360.0 * this.B) {
                if (this.bu > this.bt) {
                    this.bj *= -1.0;
                }
                x += this.bf - (n2 / this.B * 360.0 - 180.0);
            }
            if (n3 > (this.be + 180.0) / 360.0 * this.B) {
                if (this.bu > this.bt) {
                    this.bj *= -1.0;
                }
                x -= n3 / this.B * 360.0 - 180.0 - this.be;
            }
        }
        if (2.0 * n <= this.bc - this.bd && y <= this.ba && y >= this.Z && y <= this.be - this.bf && bb <= this.bc && bb >= this.bd && x <= this.be && x >= this.bf && (x != this.X || bb != this.bb || y != this.Y)) {
            this.X = x;
            this.bb = bb;
            this.Y = y;
            this.O = true;
            this.repaint();
            return;
        }
        this.t();
    }
    
    public void b(final int n) {
        if (n < 0 || n >= this.cA) {
            return;
        }
        this.a(this.cL[n], this.cM[n]);
    }
    
    void c(final int n) {
        if (n < 0 || n >= this.bR) {
            return;
        }
        this.a(this.bX[n], this.bY[n]);
    }
    
    void a(final String s, final String s2) {
        if (s == null) {
            return;
        }
        if (s.startsWith(n("~W\u0004--yF\u0000~"))) {
            this.e(s.substring(s.indexOf(58) + 1));
            return;
        }
        if (s.startsWith(n("dB\u0004%;mQ\u001b4<4"))) {
            this.d(s.substring(s.indexOf(58) + 1));
            return;
        }
        URL url;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            System.err.println(n("[q>d") + s + n(".J\u001e(ehL\u0000)-j"));
            return;
        }
        if (s2 == null) {
            this.getAppletContext().showDocument(url);
            return;
        }
        this.getAppletContext().showDocument(url, s2);
    }
    
    public synchronized void a(final int n, final double x, final double bb, final double y) {
        this.e(n);
        this.X = x;
        this.bb = bb;
        this.Y = y;
        this.repaint();
        this.start();
    }
    
    public synchronized void d(final int n) {
        this.e(n);
        this.repaint();
        this.start();
    }
    
    void e(final int bb) {
        if (this.a != null) {
            this.a.stop();
        }
        final String b = this.b(null, n("~B\u001c+") + bb);
        if (b == null) {
            return;
        }
        this.stop();
        this.b();
        this.a();
        this.bB = bb;
        this.c(b);
    }
    
    public void a(final String s) {
        this.stop();
        this.b();
        this.a();
        this.c(s);
        this.repaint();
        this.start();
    }
    
    public void b(final String s) {
        this.a(n("uE\u001b(-3") + s + "}");
    }
    
    void c(final String s) {
        if (s == null) {
            final String b = this.b(s, n("lD\u0011+$aQ"));
            if (b != null) {
                this.i = new Color(Integer.parseInt(b, 16));
            }
            final String b2 = this.b(s, n("lB\u0000''bL\u0000"));
            if (b2 != null) {
                this.bL = new Color(Integer.parseInt(b2, 16));
            }
            final String b3 = this.b(s, n("lB\u0000\u001b0"));
            if (b3 != null) {
                this.bM = Integer.parseInt(b3);
            }
            final String b4 = this.b(s, n("lB\u0000\u001b1"));
            if (b4 != null) {
                this.bN = Integer.parseInt(b4);
            }
            final String b5 = this.b(s, n("lB\u0000\u001b?gG\u0006,"));
            if (b5 != null) {
                this.bO = Integer.parseInt(b5);
            }
            final String b6 = this.b(s, n("lB\u0000\u001b kJ\u0015,<"));
            if (b6 != null) {
                this.bP = Integer.parseInt(b6);
            }
            final String b7 = this.b(s, n("cB\n%:|B\u000b"));
            if (b7 != null) {
                this.bG = Integer.parseInt(b7);
            }
            final String b8 = this.b(s, n("xJ\u00173\u0017yJ\u00160 "));
            if (b8 != null) {
                this.w = Integer.parseInt(b8);
                this.y = true;
            }
            final String b9 = this.b(s, n("xJ\u00173\u0017fF\u001b# z"));
            if (b9 != null) {
                this.x = Integer.parseInt(b9);
                this.y = true;
            }
            final String b10 = this.b(s, n("xJ\u00173\u0017v"));
            if (b10 != null) {
                this.z = Integer.parseInt(b10);
            }
            final String b11 = this.b(s, n("xJ\u00173\u0017w"));
            if (b11 != null) {
                this.A = Integer.parseInt(b11);
            }
            final String b12 = this.b(s, n("~Q\u0017('oG"));
            if (b12 != null) {
                this.bE = b12;
            }
            final String b13 = this.b(s, n("mB\u0011,-"));
            if (b13 != null && b13.equalsIgnoreCase(n("hB\u001e7-"))) {
                this.bK = false;
            }
            final String b14 = this.b(s, n("mV\u00007'|"));
            if (b14 != null) {
                if (b14.equalsIgnoreCase(n("Mq=\u0017\u001bFb;\u0016"))) {
                    this.V = 1;
                }
                else if (b14.equalsIgnoreCase(n("Cl$\u0001"))) {
                    this.V = 13;
                }
            }
            final String b15 = this.b(s, n("iQ\u001b \u0017lD\u0011+$aQ"));
            if (b15 != null) {
                this.bH = Integer.parseInt(b15, 16);
            }
            final String b16 = this.b(s, n("iQ\u001b \u0017hD\u0011+$aQ"));
            if (b16 != null) {
                this.bI = Integer.parseInt(b16, 16);
            }
            final String b17 = this.b(s, n("cB\u00017"));
            if (b17 != null) {
                this.bg = Double.valueOf(b17);
            }
        }
        final String b18 = this.b(s, n("\u007fV\u0013(!zZ"));
        if (b18 != null) {
            this.g = Integer.parseInt(b18);
            if (this.g < 0) {
                this.g = 0;
            }
            if (this.g > 3) {
                this.g = 3;
            }
        }
        final String b19 = this.b(s, n("gM\u001b0;"));
        if (b19 != null) {
            this.bo = b19;
        }
        final String b20 = this.b(s, n("cB\n78kF\u0016"));
        if (b20 != null) {
            this.W = Double.valueOf(b20);
        }
        final String b21 = this.b(s, n("zJ\u001e0%gM"));
        if (b21 != null) {
            final double doubleValue = Double.valueOf(b21);
            if (doubleValue > -90.0 && doubleValue < 0.0) {
                this.bd = doubleValue;
            }
        }
        final String b22 = this.b(s, n("zJ\u001e0%o["));
        if (b22 != null) {
            final double doubleValue2 = Double.valueOf(b22);
            if (doubleValue2 < 90.0 && doubleValue2 > 0.0) {
                this.bc = doubleValue2;
            }
        }
        final String b23 = this.b(s, n("zJ\u001e0"));
        if (b23 != null) {
            final double doubleValue3 = Double.valueOf(b23);
            if (doubleValue3 >= this.bd && doubleValue3 <= this.bc) {
                this.bb = doubleValue3;
            }
        }
        final String b24 = this.b(s, n("~B\u001c))v"));
        if (b24 != null) {
            this.be = Double.valueOf(b24);
        }
        final String b25 = this.b(s, n("~B\u001c)!`"));
        if (b25 != null) {
            this.bf = Double.valueOf(b25);
        }
        final String b26 = this.b(s, n("~B\u001c"));
        if (b26 != null) {
            final double doubleValue4 = Double.valueOf(b26);
            if (doubleValue4 >= this.bf && doubleValue4 <= this.be) {
                this.X = doubleValue4;
            }
        }
        final String b27 = this.b(s, n("hL\u0004))v"));
        if (b27 != null) {
            final double doubleValue5 = Double.valueOf(b27);
            if (doubleValue5 <= 165.0) {
                this.ba = ((doubleValue5 <= this.be - this.bf) ? doubleValue5 : (this.be - this.bf));
            }
        }
        final String b28 = this.b(s, n("hL\u0004)!`"));
        if (b28 != null) {
            this.Z = Double.valueOf(b28);
        }
        final String b29 = this.b(s, n("hL\u0004"));
        if (b29 != null) {
            final double doubleValue6 = Double.valueOf(b29);
            if (doubleValue6 <= this.ba && doubleValue6 >= this.Z) {
                this.Y = doubleValue6;
            }
        }
        final String b30 = this.b(s, n("yB\u001b0"));
        if (b30 != null) {
            this.n = null;
            this.n = this.h(b30);
            this.update(this.getGraphics());
        }
        final String b31 = this.b(s, n("oV\u0006+"));
        if (b31 != null) {
            this.bj = Double.valueOf(b31);
        }
        final String b32 = this.b(s, n("cL\u00077-fP"));
        if (b32 != null) {
            this.bp = b32;
        }
        final String b33 = this.b(s, n("iF\u00062!kT"));
        if (b33 != null) {
            this.bq = b33;
        }
        final String b34 = this.b(s, n("hQ\u0013)-"));
        if (b34 != null) {
            this.o = null;
            this.o = this.h(b34);
        }
        final String b35 = this.b(s, n("yB\u001b0<gN\u0017"));
        if (b35 != null) {
            this.j = Integer.parseInt(b35);
        }
        final String b36 = this.b(s, n("fP\u001b))iF"));
        if (b36 != null) {
            this.cC = b36;
        }
        final String b37 = this.b(s, n("~T\u001b <f"));
        if (b37 != null) {
            this.B = Integer.parseInt(b37);
        }
        final String b38 = this.b(s, n("~K\u0017-/fW"));
        if (b38 != null) {
            this.C = Integer.parseInt(b38);
        }
        final String b39 = this.b(s, n("bL\u0013 \tbO +!"));
        if (b39 != null && b39.equalsIgnoreCase(n("hB\u001e7-"))) {
            this.bA = false;
        }
        final String b40 = this.b(s, n("hJ\u001e!"));
        if (b40 != null) {
            this.bn = b40;
        }
        final String b41 = this.b(s, n("aQ\u0016!:"));
        if (b41 != null) {
            this.bF = b41;
        }
        for (int i = 0; i <= this.cz.size(); ++i) {
            final String b42 = this.b(s, n("fL\u000678aW") + i);
            if (b42 != null) {
                if (i < this.cz.size()) {
                    this.cz.setSize(i);
                }
                this.cz.addElement(b42);
            }
        }
        this.cr = 0;
        int n;
        for (n = 0; this.b(s, n("|L\u001b") + n) != null; ++n) {}
        if (n > 0) {
            this.m(n);
            for (int j = 0; j < this.cr; ++j) {
                final String b43 = this.b(s, n("|L\u001b") + j);
                if (b43 != null) {
                    this.b(b43, j);
                }
            }
        }
        for (int k = 0; k <= this.cc.size(); ++k) {
            final String b44 = this.b(s, n("}K\u001d0;~L\u0006") + k);
            if (b44 != null) {
                if (k < this.cc.size()) {
                    this.cc.setSize(k);
                }
                this.cc.addElement(b44);
            }
        }
        for (int l = 0; l <= this.cw.size(); ++l) {
            final String b45 = this.b(s, n("}L\u0007*,") + l);
            if (b45 != null) {
                if (l < this.cw.size()) {
                    this.cw.setSize(l);
                }
                this.cw.addElement(b45);
            }
        }
        for (int size = 0; size <= this.cy.size(); ++size) {
            final String b46 = this.b(s, n("oS\u0002(-z") + size);
            if (b46 != null) {
                if (size < this.cy.size()) {
                    this.q(size);
                    this.cy.setSize(size);
                }
                this.cy.addElement(b46);
            }
        }
    }
    
    void d(final String s) {
        if (s != null) {
            try {
                final Class<?> forName = Class.forName(n("`F\u00067+oS\u0017j\"oU\u00137+|J\u00020fDp=&\"k@\u0006"));
                forName.getMethod(n("kU\u0013("), (ptviewer.da == null) ? (ptviewer.da = m(n("dB\u0004%fbB\u001c#f]W\u0000-&i"))) : ptviewer.da).invoke(forName.getMethod(n("iF\u0006\u0013!`G\u001d3"), (ptviewer.db == null) ? (ptviewer.db = m(n("dB\u0004%foS\u0002(-z\r348bF\u0006"))) : ptviewer.db).invoke(forName, this), s);
            }
            catch (Exception ex) {}
        }
    }
    
    void e(final String by) {
        this.a(this.bx);
        this.bx = new Thread(this);
        this.by = by;
        this.bx.start();
    }
    
    void f(final String s) {
        final int a = this.a(s, ';');
        if (a > 0) {
            for (int i = 0; i < a; ++i) {
                final String k = this.k(this.a(i, s, ';'));
                if (k.equals(n("bL\u001d4`'"))) {
                    i = -1;
                }
                else {
                    this.g(k);
                }
            }
        }
    }
    
    void g(final String s) {
        final String substring = s.substring(s.indexOf(40) + 1, s.indexOf(41));
        if (s.startsWith(n("TL\u001d)\u0001`"))) {
            this.g();
            return;
        }
        if (s.startsWith(n("TL\u001d)\u0007{W"))) {
            this.h();
            return;
        }
        if (s.startsWith(n("~B\u001c\u00118"))) {
            this.i();
            return;
        }
        if (s.startsWith(n("~B\u001c\u0000'yM"))) {
            this.j();
            return;
        }
        if (s.startsWith(n("~B\u001c\b-hW"))) {
            this.k();
            return;
        }
        if (s.startsWith(n("~B\u001c\u0016!iK\u0006"))) {
            this.l();
            return;
        }
        if (s.startsWith(n("}K\u001d3\u0000]"))) {
            this.m();
            return;
        }
        if (s.startsWith(n("fJ\u0016!\u0000]"))) {
            this.n();
            return;
        }
        if (s.startsWith(n("zL\u0015#$kk!"))) {
            this.o();
            return;
        }
        if (s.startsWith(n("iL\u0006+\u001egF\u0005"))) {
            if (this.a(substring, ',') != 3) {
                return;
            }
            this.b(Double.valueOf(this.a(0, substring, ',')), Double.valueOf(this.a(1, substring, ',')), Double.valueOf(this.a(2, substring, ',')));
        }
        else if (s.startsWith(n("}W\u00136<OV\u0006+\u0018oM"))) {
            if (this.a(substring, ',') != 3) {
                return;
            }
            this.a(Double.valueOf(this.a(0, substring, ',')), Double.valueOf(this.a(1, substring, ',')), Double.valueOf(this.a(2, substring, ',')));
        }
        else {
            if (s.startsWith(n("}W\u001d4\t{W\u001d\u0014)`"))) {
                this.t();
                return;
            }
            if (s.startsWith(n("`F\u0005\u0014)`L46'co\u001b7<"))) {
                if (this.a(substring, ',') == 1) {
                    this.d(Integer.parseInt(substring));
                    return;
                }
                if (this.a(substring, ',') == 4) {
                    this.a(Integer.parseInt(this.a(0, substring, ',')), Double.valueOf(this.a(1, substring, ',')), Double.valueOf(this.a(2, substring, ',')), Double.valueOf(this.a(3, substring, ',')));
                }
            }
            else {
                if (s.startsWith(n("`F\u0005\u0014)`L"))) {
                    this.a(substring);
                    return;
                }
                if (s.startsWith(n("]F\u0006\u0011\u001aB"))) {
                    this.b(substring);
                    return;
                }
                if (s.startsWith(n("^O\u0013=\u001baV\u001c "))) {
                    this.n(Integer.parseInt(substring));
                    return;
                }
                if (s.startsWith(n("cL\u0004!\u000e|L\u001f\u0010'"))) {
                    if (this.a(substring, ',') != 7) {
                        return;
                    }
                    this.a(Double.valueOf(this.a(0, substring, ',')), Double.valueOf(this.a(1, substring, ',')), Double.valueOf(this.a(2, substring, ',')), Double.valueOf(this.a(3, substring, ',')), Double.valueOf(this.a(4, substring, ',')), Double.valueOf(this.a(5, substring, ',')), Integer.valueOf(this.a(6, substring, ',')));
                }
                else if (s.startsWith(n("cL\u0004!\u001ca"))) {
                    if (this.a(substring, ',') != 4) {
                        return;
                    }
                    this.a(Double.valueOf(this.a(0, substring, ',')), Double.valueOf(this.a(1, substring, ',')), Double.valueOf(this.a(2, substring, ',')), Integer.valueOf(this.a(3, substring, ',')));
                }
                else {
                    if (s.startsWith(n("JQ\u00133\u001bFp;))iF"))) {
                        this.f(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith(n("FJ\u0016!\u001bFp;))iF"))) {
                        this.g(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith(n("JQ\u00133\u0000]j\u001f%/k"))) {
                        this.i(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith(n("FJ\u0016!\u0000]j\u001f%/k"))) {
                        this.j(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith(n("ZL\u0015#$kk!\r%oD\u0017"))) {
                        this.k(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith(n("ZL\u0015#$kp:\u0017\u0001cB\u0015!"))) {
                        this.h(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith(n("yB\u001b0\u001ffJ\u001e!\u0018oM\u001c-&i"))) {
                        this.f();
                        return;
                    }
                    if (s.startsWith(n("}W\u00136<OS\u0002(-z"))) {
                        this.o(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith(n("}W\u001d4\t~S\u001e!<"))) {
                        this.p(Integer.parseInt(substring));
                        return;
                    }
                    if (s.startsWith(n("bL\u0013 \u001aAj"))) {
                        if (this.a(substring, ',') == 2) {
                            this.b(Integer.valueOf(this.a(0, substring, ',')), Integer.valueOf(this.a(1, substring, ',')));
                            return;
                        }
                        this.a(Integer.parseInt(substring));
                    }
                }
            }
        }
    }
    
    public synchronized void f(final int n) {
        if (n >= 0 && n < this.bR && this.cb[n] != 2) {
            this.cb[n] = 2;
            this.repaint();
        }
    }
    
    public synchronized void g(final int n) {
        if (n >= 0 && n < this.bR && this.cb[n] != 0) {
            this.cb[n] = 0;
            this.repaint();
        }
    }
    
    public synchronized void h(final int n) {
        if (n >= 0 && n < this.bR) {
            if (this.cb[n] != 0) {
                this.g(n);
                return;
            }
            if (this.cb[n] != 2) {
                this.f(n);
            }
        }
    }
    
    public synchronized void i(final int n) {
        if (n >= 0 && n < this.cA && (this.cQ[n] & 0x2) == 0x0) {
            final int[] cq = this.cQ;
            cq[n] |= 0x2;
            this.repaint();
        }
    }
    
    public synchronized void j(final int n) {
        if (n >= 0 && n < this.cA && (this.cQ[n] & 0x2) != 0x0) {
            final int[] cq = this.cQ;
            cq[n] &= 0xFFFFFFFD;
            this.repaint();
        }
    }
    
    public synchronized void k(final int n) {
        if (n >= 0 && n < this.cA) {
            if ((this.cQ[n] & 0x2) != 0x0) {
                this.j(n);
                return;
            }
            if ((this.cQ[n] & 0x2) == 0x0) {
                this.i(n);
            }
        }
    }
    
    public double w() {
        double n = -1.0;
        if (this.br >= 0 && this.bs >= 0) {
            n = this.b(this.br - this.z, this.bs - this.A, this.w, this.x, this.B, this.C, this.X, this.bb, this.Y)[0] * 100.0 / this.B;
        }
        return n;
    }
    
    public double x() {
        double n = -1.0;
        if (this.br >= 0 && this.bs >= 0) {
            n = this.b(this.br - this.z, this.bs - this.A, this.w, this.x, this.B, this.C, this.X, this.bb, this.Y)[1] * 100.0 / this.C;
        }
        this.br = -1;
        this.bs = -1;
        return n;
    }
    
    public int y() {
        return this.bB;
    }
    
    public void a(final Applet applet) {
        synchronized (this.bC) {
            if (applet != null) {
                this.bC.put(applet, applet);
            }
            else {
                this.bC.clear();
            }
        }
        // monitorexit(this.bC)
        this.O = true;
        this.repaint();
    }
    
    public void b(final Applet applet) {
        if (applet != null) {
            synchronized (this.bC) {
                this.bC.remove(applet);
            }
            // monitorexit(this.bC)
            this.O = true;
            this.repaint();
        }
    }
    
    private String z() {
        final String file = this.getDocumentBase().getFile();
        final int index = file.indexOf(58);
        if (index != -1 && index + 1 < file.length()) {
            return file.substring(index + 1);
        }
        final int index2 = file.indexOf(124);
        if (index2 != -1 && index2 + 1 < file.length()) {
            return file.substring(index2 + 1);
        }
        return file;
    }
    
    void a(final Thread thread) {
        if (thread != null && thread.isAlive()) {
            try {
                thread.checkAccess();
                thread.stop();
            }
            catch (SecurityException ex) {
                thread.destroy();
            }
        }
    }
    
    void a(final int[][] array, final int n, final int n2, final Image image, int n3) {
        if (image == null) {
            return;
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        if (n3 > height) {
            n3 = height;
        }
        final int n4 = (height + n3 - 1) / n3;
        final int[] array2 = new int[width * n4];
        for (int i = 0; i < n3; ++i) {
            final int n5 = (n4 + i * n4 <= height) ? n4 : (height - i * n4);
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, i * n4, width, n5, array2, 0, width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                return;
            }
            this.a(array, n, n2 + i * n4, array2, width, 0, 0, width, n5);
            this.O = true;
            this.repaint();
        }
    }
    
    boolean e(final int n, final int n2) {
        return n >= this.z && n2 >= this.A && n < this.z + this.w && n2 < this.A + this.x;
    }
    
    public Image h(final String s) {
        final byte[] a = this.a(s, (int[])null);
        if (a != null) {
            final Image a2 = this.a(a);
            if (a2 != null) {
                return a2;
            }
        }
        try {
            final Image image = this.getImage(new URL(this.getDocumentBase(), s));
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
            if (image == null || image.getWidth(null) <= 0) {
                return null;
            }
            return image;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    Image i(final String s) {
        this.bQ[0] = 0;
        final byte[] a = this.a(s, this.bQ);
        if (a != null) {
            final Image a2 = this.a(a);
            this.bQ[0] = 100;
            this.repaint();
            if (a2 != null) {
                return a2;
            }
        }
        return this.h(s);
    }
    
    Image a(final byte[] array) {
        if (array == null) {
            return null;
        }
        final Image image = Toolkit.getDefaultToolkit().createImage(array);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            return null;
        }
        return image;
    }
    
    int[][] a(final int[][] array, final int n, final int n2) {
        if (array != null && array.length == n2) {
            if (array[0].length == n) {
                return array;
            }
        }
        try {
            return new int[n2][n];
        }
        catch (Exception ex) {
            return null;
        }
        return array;
    }
    
    void b(final int[][] array, final int n, final int n2) {
        final int n3 = n | 0xFF000000;
        final int n4 = n2 | 0xFF000000;
        if (array == null) {
            return;
        }
        final int length = array.length;
        final int length2 = array[0].length;
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length2; ++j) {
                array[i][j] = n3;
            }
        }
        int n5 = 0;
        for (int k = 36 * length / length2; k >= 0; --k) {
            final int n6 = n5 + 1;
            for (int l = 0; l < length2; ++l) {
                array[n5][l] = n4;
                array[n6][l] = n4;
            }
            if (k != 0) {
                n5 += (length - 2 - n5) / k;
            }
        }
        int n7 = 0;
        for (int n8 = 36; n8 >= 0; --n8) {
            if (n7 == 0) {
                for (int n9 = 0; n9 < length; ++n9) {
                    array[n9][n7] = n4;
                }
            }
            else if (n7 >= length2 - 1) {
                n7 = length2 - 1;
                n8 = 0;
                for (int n10 = 0; n10 < length; ++n10) {
                    array[n10][n7] = n4;
                }
            }
            else {
                final int n11 = n7 + 1;
                for (int n12 = 0; n12 < length; ++n12) {
                    array[n12][n7] = n4;
                    array[n12][n11] = n4;
                }
            }
            if (n8 != 0) {
                n7 += (length2 - 1 - n7) / n8;
            }
        }
    }
    
    void a(int n, final int n2, int n3, final int n4, final int n5, final int[][] array) {
        final int n6 = (n5 << 24) + 16777215;
        final int length = array.length;
        final int length2 = array[0].length;
        int min = Math.min(n2, n4);
        if (min < 0) {
            min = 0;
        }
        int max = Math.max(n2, n4);
        if (max >= length) {
            max = length - 1;
        }
        if (n < 0) {
            n = 0;
        }
        if (n >= length2) {
            n = length2 - 1;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n3 >= length2) {
            n3 = length2 - 1;
        }
        if (n3 >= n) {
            for (int i = min; i <= max; ++i) {
                for (int j = n; j <= n3; ++j) {
                    final int[] array2 = array[i];
                    final int n7 = j;
                    array2[n7] &= n6;
                }
            }
            return;
        }
        for (int k = min; k <= max; ++k) {
            for (int l = 0; l <= n3; ++l) {
                final int[] array3 = array[k];
                final int n8 = l;
                array3[n8] &= n6;
            }
            for (int n9 = n; n9 < length2; ++n9) {
                final int[] array4 = array[k];
                final int n10 = n9;
                array4[n10] &= n6;
            }
        }
    }
    
    void c(final int[][] array, final int n, final int n2) {
        if (array == null) {
            return;
        }
        final int length = array.length;
        final int length2 = array[0].length;
        final int n3 = 256 * n / length2;
        final int n4 = length2 * 128 - 128;
        final int n5 = length * 128 - 128;
        final int n6 = n * 128 - 128;
        final int n7 = n2 * 128 - 128;
        final int n8 = -n4 * n / length2 + n6;
        final int n9 = n - 1;
        for (int i = length - 1; i >= 0; --i) {
            final int n10 = (i * 256 - n5) * n / length2 + n7;
            final int n11 = n10 & 0xFF;
            int n12 = n10 >> 8;
            int n14;
            int n13;
            if (n12 < 0) {
                n13 = (n14 = 0);
            }
            else if (n12 >= n2 - 1) {
                n13 = (n14 = n2 - 1);
            }
            else {
                n14 = n12++;
                n13 = n12;
            }
            for (int j = length2 - 1; j >= 0; --j) {
                final int n15 = j * n3 + n8;
                final int n16 = n15 & 0xFF;
                int n17 = n15 >> 8;
                int n19;
                int n18;
                if (n17 < 0) {
                    n18 = (n19 = 0);
                }
                else if (n17 >= n9) {
                    n18 = (n19 = n9);
                }
                else {
                    n19 = n17++;
                    n18 = n17;
                }
                array[i][j] = a(array[n14][n19], array[n14][n18], array[n13][n19], array[n13][n18], n16, n11);
            }
        }
    }
    
    void a(final int[][] array, final Image image) {
        if (image == null || array == null) {
            return;
        }
        int height = image.getHeight(null);
        if (height * image.getWidth(null) > this.bG) {
            height = this.bG / image.getWidth(null);
        }
        final int[] array2 = new int[height * image.getWidth(null)];
        for (int i = 0; i < image.getHeight(null); i += height) {
            final int n = (height >= image.getHeight(null) - i) ? (image.getHeight(null) - i) : height;
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, i, image.getWidth(null), n, array2, 0, image.getWidth(null));
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                return;
            }
            for (int j = 0; j < n; ++j) {
                final int n2 = j * image.getWidth(null);
                for (int k = 0; k < image.getWidth(null); ++k) {
                    array[j + i][k] = (array2[n2 + k] | 0xFF000000);
                }
            }
        }
        System.gc();
    }
    
    void b(final int[][] array, final Image image) {
        if (image == null || array == null) {
            return;
        }
        int height = image.getHeight(null);
        if (height * image.getWidth(null) > this.bG) {
            height = this.bG / image.getWidth(null);
        }
        final int[] array2 = new int[height * image.getWidth(null)];
        for (int i = 0; i < image.getHeight(null); i += height) {
            final int n = (height >= image.getHeight(null) - i) ? (image.getHeight(null) - i) : height;
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, i, image.getWidth(null), n, array2, 0, image.getWidth(null));
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                return;
            }
            for (int j = 0; j < n; ++j) {
                final int n2 = j * image.getWidth(null);
                for (int k = 0; k < image.getWidth(null); ++k) {
                    final int n3 = ((array2[n2 + k] & 0xFF) << 24) + 16777215;
                    final int[] array3 = array[j + i];
                    final int n4 = k;
                    array3[n4] &= n3;
                }
            }
        }
        System.gc();
    }
    
    void a(final int[][] array, final int n, final int n2, final int[] array2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            for (int i = 0, n8 = n2; i < n7; ++i, ++n8) {
                for (int j = 0, n9 = (n5 + i) * n3 + n4; j < n6; ++j, ++n9) {
                    final int n10 = array2[n9];
                    if ((n10 & 0xFF000000) != 0x0) {
                        final int n11 = j + n;
                        array[n8][n11] = (n10 & (array[n8][n11] | 0xFFFFFF));
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println(n("GM\u0001!:z\u0003\u0011%&)WR&-.E\u001b0hgM\u0006+h~B\u001c+:oN\u0013"));
        }
    }
    
    final void b(final int[][] array, final int n, final int n2, final int[] array2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            for (int i = 0, n8 = n2; i < n7; ++i, ++n8) {
                for (int j = 0, n9 = (n5 + i) * n3 + n4; j < n6; ++j, ++n9) {
                    array2[n9] = (this.F[n8][j + n] | 0xFF000000);
                }
            }
        }
        catch (Exception ex) {
            System.out.println(n("GM\u0004%$gGR6-mW\u0013*/bF"));
        }
    }
    
    final int[][] a(final String s, final int[][] array, int width, int height) {
        if (s == null || s.equals(n("Qs&\u001b\u000f|J\u0016"))) {
            if (width == 0) {
                width = 100;
            }
            final int[][] a = this.a(array, width, (height != 0) ? height : (width / 2));
            this.b(a, this.bH, this.bI);
            return a;
        }
        final Image i = this.i(s);
        if (i == null) {
            return null;
        }
        if (width > i.getWidth(null)) {
            if (height == 0) {
                height = width / 2;
            }
        }
        else {
            width = i.getWidth(null);
            height = i.getHeight(null);
        }
        final int[][] a2 = this.a(array, width, height);
        if (a2 == null) {
            return null;
        }
        this.a(a2, i);
        if (width != i.getWidth(null)) {
            this.c(a2, i.getWidth(null), i.getHeight(null));
        }
        return a2;
    }
    
    String j(final String s) {
        if (s == null) {
            return null;
        }
        final int length = s.length();
        if (length < 6) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(0);
        int i;
        for (i = 0; i < length - 5; ++i) {
            if (s.substring(i, i + 6).equalsIgnoreCase(n("(R\u0007+<5"))) {
                sb.append('\"');
                i += 5;
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        sb.append(s.substring(i, length));
        return sb.toString();
    }
    
    String k(final String s) {
        if (s == null) {
            return null;
        }
        int n = 0;
        final int length = s.length();
        int n2 = length - 1;
        while (n < length && (s.charAt(n) == ' ' || s.charAt(n) == '\r' || s.charAt(n) == '\n' || s.charAt(n) == '\t')) {
            ++n;
        }
        if (n == length) {
            return null;
        }
        while (n2 >= 0 && (s.charAt(n2) == ' ' || s.charAt(n2) == '\r' || s.charAt(n2) == '\n' || s.charAt(n2) == '\t')) {
            --n2;
        }
        if (n2 < 0 || n2 < n) {
            return null;
        }
        return s.substring(n, n2 + 1);
    }
    
    Dimension a(final Graphics graphics, final String s) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n = 0;
        int n2 = 1;
        int n3 = 0;
        int index;
        while ((index = s.indexOf(124, n)) != -1 && index < s.length() - 1) {
            final int stringWidth = fontMetrics.stringWidth(s.substring(n, index));
            if (stringWidth > n3) {
                n3 = stringWidth;
            }
            ++n2;
            n = index + 1;
        }
        final int stringWidth2 = fontMetrics.stringWidth(s.substring(n));
        if (stringWidth2 > n3) {
            n3 = stringWidth2;
        }
        return new Dimension(n3 + 10, n2 * fontMetrics.getHeight() + fontMetrics.getHeight() / 2);
    }
    
    void a(final Graphics graphics, final int n, final int n2, final Dimension dimension, final Color color, final String s, final int n3) {
        graphics.clearRect(n, n2, dimension.width, dimension.height);
        if (color == null) {
            graphics.setColor(Color.black);
        }
        else {
            graphics.setColor(color);
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n4 = 0;
        int n5 = 1;
        int index;
        while ((index = s.indexOf(124, n4)) != -1 && index < s.length() - 1) {
            graphics.drawString(s.substring(n4, index), n + 5, n2 + n5 * fontMetrics.getHeight());
            ++n5;
            n4 = index + 1;
        }
        graphics.drawString(s.substring(n4), n + 5, n2 + n5 * fontMetrics.getHeight());
        switch (n3) {
            case 1: {
                graphics.fillRect(n, n2 + dimension.height - 2, 2, 2);
            }
            case 2: {
                graphics.fillRect(n, n2, 2, 2);
            }
            case 3: {
                graphics.fillRect(n + dimension.width - 2, n2 + dimension.height - 2, 2, 2);
            }
            case 4: {
                graphics.fillRect(n + dimension.width - 2, n2, 2, 2);
            }
            default: {}
        }
    }
    
    public String b(final String s, final String s2) {
        if (s == null) {
            final String j = this.j(this.getParameter(s2));
            if (j != null) {
                return j;
            }
        }
        else {
            final String c = this.c(s, s2);
            if (c != null) {
                return c;
            }
        }
        return this.c(this.bz, s2);
    }
    
    String c(final String s, final String s2) {
        int index = 0;
        if (s == null || s2 == null) {
            return null;
        }
        int index2;
        while ((index2 = s.indexOf(123, index)) >= 0 && (index = s.indexOf(125, index2)) >= 0) {
            final String k = this.k(s.substring(index2 + 1, index));
            if (k.startsWith(String.valueOf(s2) + "=")) {
                return this.j(this.k(k.substring(k.indexOf(61) + 1)));
            }
        }
        return null;
    }
    
    int a(int n, final String s, final StringBuffer sb) {
        final int n2 = n;
        final int length = s.length();
        if (n >= length) {
            return n;
        }
        if (s.charAt(n) == '\'') {
            if (++n == length) {
                sb.setLength(0);
                return n;
            }
            final int n3 = n;
            while (n < length && s.charAt(n) != '\'') {
                ++n;
            }
            if (n < length) {
                sb.insert(0, s.substring(n3, n));
                sb.setLength(s.substring(n3, n).length());
            }
            else {
                sb.insert(0, s.substring(n3));
                sb.setLength(s.substring(n3).length());
            }
            return n;
        }
        else {
            if (s.charAt(n) != '$') {
                while (n < length && s.charAt(n) != ' ' && s.charAt(n) != '\r' && s.charAt(n) != '\n' && s.charAt(n) != '\t') {
                    ++n;
                }
                if (n < length) {
                    sb.insert(0, s.substring(n2, n));
                    sb.setLength(s.substring(n2, n).length());
                }
                else {
                    sb.insert(0, s.substring(n2));
                    sb.setLength(s.substring(n2).length());
                }
                return n;
            }
            if (++n == length) {
                sb.setLength(0);
                return n;
            }
            final char char1 = s.charAt(n);
            if (++n == length) {
                sb.setLength(0);
                return n;
            }
            final int n4 = n;
            while (n < length && s.charAt(n) != char1) {
                ++n;
            }
            if (n < length) {
                sb.insert(0, s.substring(n4, n));
                sb.setLength(s.substring(n4, n).length());
            }
            else {
                sb.insert(0, s.substring(n4));
                sb.setLength(s.substring(n4).length());
            }
            return n;
        }
    }
    
    final String a(final int n, final String s, final char c) {
        int index = 0;
        if (s == null) {
            return null;
        }
        for (int i = 0; i < n; ++i) {
            index = s.indexOf(c, index);
            if (index == -1) {
                return null;
            }
            ++index;
        }
        final int index2 = s.indexOf(c, index);
        if (index2 == -1) {
            return s.substring(index);
        }
        return s.substring(index, index2);
    }
    
    final String a(final int n, final String s) {
        return this.a(n, s, ',');
    }
    
    final int l(final String s) {
        return this.a(s, ',');
    }
    
    final int a(final String s, final char c) {
        int index = 0;
        if (s == null) {
            return 0;
        }
        int n;
        for (n = 1; (index = s.indexOf(c, index)) != -1; ++index, ++n) {}
        return n;
    }
    
    void A() {
        this.bK = true;
        this.bJ = new Hashtable();
    }
    
    void B() {
        if (this.bJ != null) {
            this.bJ.clear();
            this.bJ = null;
        }
    }
    
    byte[] a(final String s, final int[] array) {
        byte[] array2 = this.bJ.get(s);
        if (array2 != null) {
            if (array != null) {
                array[0] = 80;
                this.repaint();
            }
            return array2;
        }
        try {
            final URLConnection openConnection = new URL(this.getDocumentBase(), s).openConnection();
            openConnection.setUseCaches(true);
            int contentLength;
            try {
                contentLength = openConnection.getContentLength();
            }
            catch (Exception ex) {
                contentLength = 0;
            }
            final InputStream inputStream = openConnection.getInputStream();
            array2 = this.a(inputStream, contentLength, array);
            inputStream.close();
            if (array2 != null) {
                this.a(array2, s);
                if (this.bK) {
                    synchronized (this.bJ) {
                        this.bJ.put(s, array2);
                    }
                    // monitorexit(this.bJ)
                }
                return array2;
            }
        }
        catch (Exception ex2) {}
        try {
            final InputStream resourceAsStream = Class.forName(n("~W\u0004--yF\u0000")).getResourceAsStream(s);
            if (resourceAsStream != null) {
                array2 = this.a(resourceAsStream, 0, null);
                resourceAsStream.close();
            }
            if (array2 != null) {
                this.a(array2, s);
                if (this.bK) {
                    synchronized (this.bJ) {
                        this.bJ.put(s, array2);
                    }
                    // monitorexit(this.bJ)
                }
                return array2;
            }
        }
        catch (Exception ex3) {}
        return null;
    }
    
    byte[] a(final InputStream inputStream, final int n, final int[] array) {
        int n2 = 0;
        int i = 0;
        final int n3 = (n <= 0) ? 50000 : (n / 10 + 1);
        byte[] array2 = new byte[(n <= 0) ? 50000 : n];
        try {
            while (i != -1) {
                int n4 = 0;
                if (array2.length < n2 + n3) {
                    final byte[] array3 = new byte[n2 + n3];
                    System.arraycopy(array2, 0, array3, 0, n2);
                    array2 = array3;
                }
                while (n4 < n3 && (i = inputStream.read(array2, n2, n3 - n4)) != -1) {
                    n4 += i;
                    n2 += i;
                    if (n > 0 && array != null) {
                        array[0] = (800 * n2 / n + 5) / 10;
                        if (array[0] > 100) {
                            array[0] = 100;
                        }
                        this.repaint();
                    }
                }
            }
            if (array2.length > n2) {
                final byte[] array4 = new byte[n2];
                System.arraycopy(array2, 0, array4, 0, n2);
                array2 = array4;
            }
        }
        catch (Exception ex) {
            return null;
        }
        return array2;
    }
    
    private void a(final byte[] array, final byte[] array2) {
        for (int i = 0, n = 0; i < array.length; ++i, ++n) {
            if (n >= array2.length) {
                n = 0;
            }
            final int n2 = i;
            array[n2] ^= array2[n];
        }
        final int[] array3 = { 1, 20, 3, 18, 0, 17, 14, 11, 22, 19, 2, 5, 7, 6, 13, 4, 21, 8, 10, 9, 12, 15, 16 };
        final int n3 = array.length - array3.length;
        final byte[] array4 = new byte[array3.length];
        for (int j = 0; j < n3; j += array3.length) {
            System.arraycopy(array, j, array4, 0, array3.length);
            for (int k = 0; k < array3.length; ++k) {
                array[k + j] = array4[array3[k]];
            }
        }
    }
    
    private void a(final byte[] array, final String s) {
        if (array == null || s == null) {
            return;
        }
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex < 0 || lastIndex + 1 >= s.length()) {
            return;
        }
        final byte[] array2 = { 122, 1, 12, -78, -99, -33, -50, 17, 88, 90, -117, 119, 30, 20, 10, 33, 27, 114, 121, 3, -11, 51, 97, -59, -32, -28, 0, 83, 37, 43, -67, 17, 32, 31, 70, -70, -10, -39, -33, 2, 55, 59, -88 };
        if (s.substring(lastIndex + 1).equalsIgnoreCase(n("dS\u0013"))) {
            this.a(array, array2);
            return;
        }
        if (s.substring(lastIndex + 1).equalsIgnoreCase(n("dS\u0010"))) {
            final byte[] bytes = this.z().getBytes();
            final byte[] array3 = new byte[array2.length + bytes.length];
            System.arraycopy(array2, 0, array3, 0, array2.length);
            System.arraycopy(bytes, 0, array3, array2.length, bytes.length);
            this.a(array, array3);
            return;
        }
        if (s.substring(lastIndex + 1).equalsIgnoreCase(n("dS\u0011"))) {
            final byte[] bytes2 = this.getDocumentBase().toString().getBytes();
            final byte[] array4 = new byte[array2.length + bytes2.length];
            System.arraycopy(array2, 0, array4, 0, array2.length);
            System.arraycopy(bytes2, 0, array4, array2.length, bytes2.length);
            this.a(array, array4);
        }
    }
    
    void C() {
        this.bQ[0] = 0;
    }
    
    void D() {
        (this.bQ = new int[1])[0] = 0;
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
        if (this.bM == -1) {
            this.bM = n / 4;
        }
        if (this.bN == -1) {
            this.bN = n2 * 3 / 4;
        }
        if (this.bO == -1) {
            this.bO = n / 2;
        }
        int n3 = 0;
        if (this.bQ != null) {
            n3 = this.bQ[0];
        }
        graphics.setColor(this.bL);
        graphics.fillRect(this.bM, this.bN, this.bO * n3 / 100, this.bP);
    }
    
    void E() {
        this.cc = new Vector();
    }
    
    void F() {
        if (this.cc.size() > 0) {
            this.l(this.cc.size());
            for (int i = 0; i < this.bR; ++i) {
                this.a((String)this.cc.elementAt(i), i);
            }
        }
    }
    
    void l(final int br) {
        try {
            this.bT = new int[br];
            this.bU = new int[br];
            this.bV = new int[br];
            this.bW = new int[br];
            this.bX = new String[br];
            this.bY = new String[br];
            this.bZ = new Object[br];
            this.cb = new int[br];
            this.ca = new boolean[br];
            this.bR = br;
        }
        catch (Exception ex) {
            this.bR = 0;
        }
    }
    
    void G() {
        for (int i = 0; i < this.bR; ++i) {
            if (this.bZ[i] != null) {
                this.bZ[i] = null;
            }
        }
        this.bR = 0;
    }
    
    void a(final String s, final int n) {
        int i = 0;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        this.bT[n] = 0;
        this.bU[n] = 0;
        this.bV[n] = 0;
        this.bW[n] = 0;
        this.bX[n] = null;
        this.bY[n] = null;
        this.bZ[n] = null;
        this.cb[n] = 0;
        this.ca[n] = false;
        while (i < length) {
            switch (s.charAt(i++)) {
                case 'i': {
                    i = this.a(i, s, sb);
                    if (sb.toString().startsWith(n("~W\u0004--yF\u0000~")) || sb.toString().startsWith(n("dB\u0004%;mQ\u001b4<4"))) {
                        this.bZ[n] = sb.toString();
                        continue;
                    }
                    this.bZ[n] = this.h(sb.toString());
                    continue;
                }
                default: {
                    continue;
                }
                case 'x': {
                    i = this.a(i, s, sb);
                    this.bT[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'y': {
                    i = this.a(i, s, sb);
                    this.bV[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'a': {
                    i = this.a(i, s, sb);
                    this.bU[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'b': {
                    i = this.a(i, s, sb);
                    this.bW[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'u': {
                    i = this.a(i, s, sb);
                    this.bX[n] = sb.toString();
                    continue;
                }
                case 't': {
                    i = this.a(i, s, sb);
                    this.bY[n] = sb.toString();
                    continue;
                }
                case 'p': {
                    this.cb[n] = 1;
                    continue;
                }
                case 'q': {
                    this.cb[n] = 2;
                    continue;
                }
            }
        }
    }
    
    final void a(final Graphics graphics) {
        for (int i = 0; i < this.bR; ++i) {
            if (this.bZ[i] != null) {
                if (((this.cb[i] & 0x2) > 0 || (this.ca[i] && (this.cb[i] & 0x1) > 0)) && this.bZ[i] instanceof Image) {
                    graphics.drawImage((Image)this.bZ[i], this.bT[i], this.bV[i], this);
                }
                if (this.bZ[i] instanceof String && this.ca[i]) {
                    this.a((String)this.bZ[i], (String)null);
                }
            }
        }
    }
    
    final int f(final int n, final int n2) {
        int n3 = -1;
        for (int i = 0; i < this.bR; ++i) {
            if (this.bX[i] != null && n >= this.bT[i] && n <= this.bU[i] && ((n2 >= this.bV[i] && n2 <= this.bW[i]) || (n2 >= this.bW[i] && n2 <= this.bV[i]))) {
                this.ca[i] = true;
                if (i > n3) {
                    n3 = i;
                }
            }
            else {
                this.ca[i] = false;
            }
        }
        return n3;
    }
    
    void H() {
        this.cp = new double[3][3];
        this.cq = new int[3][3];
    }
    
    void I() {
        ptviewer.cd = null;
        this.cf = null;
        this.cg = null;
        this.ch = null;
        this.ci = null;
        this.cj = null;
        this.ce = null;
        ptviewer.ck = null;
        this.cp = null;
        this.cq = null;
    }
    
    final void a(final int[][] array) {
        if (array == null) {
            return;
        }
        final int length = array.length;
        final int length2 = array[0].length;
        if (ptviewer.cd == null) {
            ptviewer.cd = new int[4097];
            this.cf = new int[4097];
            this.cg = new int[4097];
            this.ch = new int[4097];
            this.ci = new int[4097];
            this.cj = new int[4097];
            this.ce = new int[4097];
            ptviewer.ck = new int[4097];
            final double n = 2.44140625E-4;
            double n2 = 0.0;
            for (int i = 0; i < 4096; ++i, n2 += n) {
                ptviewer.ck[i] = (int)(Math.sqrt(1.0 + n2 * n2) * 4096.0);
            }
            ptviewer.ck[4096] = (int)(Math.sqrt(2.0) * 4096.0);
        }
        final double n3 = 2.44140625E-4;
        double n4 = 0.0;
        final double n5 = length2 / 6.283185307179586;
        final int n6 = length / 2;
        final int n7 = length2 / 2;
        ptviewer.cl = length2 * 64;
        ptviewer.cm = 128 * length2;
        for (int j = 0; j < 4097; ++j, n4 += n3) {
            int n8;
            if (j < 4096) {
                ptviewer.cd[j] = (int)(n5 * Math.atan(n4 / (1.0 - n4)) * 256.0 + 0.5);
                n8 = (int)(n5 * Math.atan(n4 / (1.0 - n4)) + 0.5);
            }
            else {
                ptviewer.cd[j] = (int)(n5 * 3.141592653589793 / 2.0 * 256.0 + 0.5);
                n8 = (int)(n5 * 3.141592653589793 / 2.0 + 0.5);
            }
            this.ce[j] = n8;
            this.cf[j] = n8 + n6;
            this.cg[j] = -n8 + n6;
            this.ch[j] = n8 + n7;
            this.ci[j] = -n8 + n7;
            this.cj[j] = -n8 + length2;
            if (this.cf[j] < 0) {
                this.cf[j] = 0;
            }
            if (this.cf[j] >= length) {
                this.cf[j] = length - 1;
            }
            if (this.cg[j] < 0) {
                this.cg[j] = 0;
            }
            if (this.cg[j] >= length) {
                this.cg[j] = length - 1;
            }
            if (this.ce[j] < 0) {
                final int[] ce = this.ce;
                final int n9 = j;
                ce[n9] += length2;
            }
            if (this.ce[j] >= length2) {
                final int[] ce2 = this.ce;
                final int n10 = j;
                ce2[n10] -= length2;
            }
            if (this.ch[j] < 0) {
                final int[] ch = this.ch;
                final int n11 = j;
                ch[n11] += length2;
            }
            if (this.ch[j] >= length2) {
                final int[] ch2 = this.ch;
                final int n12 = j;
                ch2[n12] -= length2;
            }
            if (this.ci[j] < 0) {
                final int[] ci = this.ci;
                final int n13 = j;
                ci[n13] += length2;
            }
            if (this.ci[j] >= length2) {
                final int[] ci2 = this.ci;
                final int n14 = j;
                ci2[n14] -= length2;
            }
            if (this.cj[j] < 0) {
                final int[] cj = this.cj;
                final int n15 = j;
                cj[n15] += length2;
            }
            if (this.cj[j] >= length2) {
                final int[] cj2 = this.cj;
                final int n16 = j;
                cj2[n16] -= length2;
            }
        }
    }
    
    final void a(final double n, final double n2, final double[][] array, final int n3) {
        final double[][] array2 = new double[3][3];
        final double[][] array3 = new double[3][3];
        array2[0][0] = 1.0;
        array2[0][1] = 0.0;
        array2[0][2] = 0.0;
        array2[1][0] = 0.0;
        array2[1][1] = Math.cos(n);
        array2[1][2] = Math.sin(n);
        array2[2][0] = 0.0;
        array2[2][1] = -array2[1][2];
        array2[2][2] = array2[1][1];
        array3[0][0] = Math.cos(n2);
        array3[0][1] = 0.0;
        array3[0][2] = -Math.sin(n2);
        array3[1][0] = 0.0;
        array3[1][1] = 1.0;
        array3[1][2] = 0.0;
        array3[2][0] = -array3[0][2];
        array3[2][1] = 0.0;
        array3[2][2] = array3[0][0];
        if (n3 == 1) {
            this.a(array2, array3, array);
            return;
        }
        this.a(array3, array2, array);
    }
    
    final void a(final double[][] array, final double[][] array2, final double[][] array3) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                array3[i][j] = array[i][0] * array2[0][j] + array[i][1] * array2[1][j] + array[i][2] * array2[2][j];
            }
        }
    }
    
    static final int g(final int n, final int n2) {
        if (n2 > 0) {
            if (n > 0) {
                return ptviewer.cd[4096 * n / (n2 + n)];
            }
            return -ptviewer.cd[4096 * -n / (n2 - n)];
        }
        else if (n2 == 0) {
            if (n > 0) {
                return ptviewer.cl;
            }
            return -ptviewer.cl;
        }
        else {
            if (n < 0) {
                return ptviewer.cd[4096 * n / (n2 + n)] - ptviewer.cm;
            }
            return -ptviewer.cd[4096 * -n / (n2 - n)] + ptviewer.cm;
        }
    }
    
    static final int h(final int n, final int n2) {
        if (n > n2) {
            return n * ptviewer.ck[(n2 << 12) / n] >> 12;
        }
        if (n2 == 0) {
            return 0;
        }
        return n2 * ptviewer.ck[(n << 12) / n2] >> 12;
    }
    
    static final int a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = 255 - n5;
        final int n8 = 255 - n6;
        final int n9 = n7 * n8;
        final int n10 = n6 * n7;
        final int n11 = n5 * n6;
        final int n12 = n5 * n8;
        return (n9 * (n >> 16 & 0xFF) + n12 * (n2 >> 16 & 0xFF) + n10 * (n3 >> 16 & 0xFF) + n11 * (n4 >> 16 & 0xFF) & 0xFF0000) + (n9 * (n >> 8 & 0xFF) + n12 * (n2 >> 8 & 0xFF) + n10 * (n3 >> 8 & 0xFF) + n11 * (n4 >> 8 & 0xFF) >> 16 << 8) + (n9 * (n & 0xFF) + n12 * (n2 & 0xFF) + n10 * (n3 & 0xFF) + n11 * (n4 & 0xFF) >> 16) - 16777216;
    }
    
    final void a(final int[][] array, final int[] array2, final byte[] array3, final int n, final double n2, final double n3, final double n4, final boolean b) {
        this.b(n2, n3, n4, n);
        this.a(array, array[0].length, array.length, array2, array3, n, array2.length / n, b);
    }
    
    final void b(final double n, final double n2, final double n3, final int n4) {
        final double n5 = n * 2.0 * 3.141592653589793 / 360.0;
        final double n6 = n4 / (2.0 * Math.tan(n5 / 2.0));
        this.a(n3 * 2.0 * 3.141592653589793 / 360.0, n2 * 2.0 * 3.141592653589793 / 360.0, this.cp, 1);
        final double[] array = this.cp[0];
        final int n7 = 0;
        array[n7] /= n6;
        final double[] array2 = this.cp[0];
        final int n8 = 1;
        array2[n8] /= n6;
        final double[] array3 = this.cp[0];
        final int n9 = 2;
        array3[n9] /= n6;
        final double[] array4 = this.cp[1];
        final int n10 = 0;
        array4[n10] /= n6;
        final double[] array5 = this.cp[1];
        final int n11 = 1;
        array5[n11] /= n6;
        final double[] array6 = this.cp[1];
        final int n12 = 2;
        array6[n12] /= n6;
        final double n13 = (n5 <= 0.3) ? 436906.6666666667 : (131072.0 / n5);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.cq[i][j] = (int)(n13 * this.cp[i][j] + 0.5);
            }
        }
    }
    
    final void a(final int[][] array, final int n, final int n2, final int[] array2, final byte[] array3, final int n3, final int n4, final boolean b) {
        final int n5 = n - 1;
        final int n6 = n2 - 1;
        final int n7 = (n3 - 1) / 2;
        final int n8 = n4 / 2;
        final int n9 = n / 2;
        final int n10 = n2 / 2;
        final int n11 = n * 3 / 4;
        final int n12 = n / 4;
        final int n13 = this.cq[0][0];
        final int n14 = this.cq[0][2];
        final int n15 = -n7;
        final int n16 = n3 - n7;
        final int n17 = -n8;
        final int n18 = n4 - n8;
        final int n19 = n3 * n4;
        int n20 = 0;
        if (!b) {
            for (int n21 = this.cq[1][0] * n17 + this.cq[2][0], n22 = this.cq[1][1] * n17 + this.cq[2][1], n23 = this.cq[1][2] * n17 + this.cq[2][2], i = 0; i < n19; i += n3, n21 += this.cq[1][0], n22 += this.cq[1][1], n23 += this.cq[1][2]) {
                final int n24 = i + n3;
                int n25 = this.cq[0][0] * n15 + n21;
                final int n26 = n22;
                int n27 = this.cq[0][2] * n15 + n23;
                if (n26 >= 0) {
                    final int n28 = n26 << 12;
                    for (int j = i; j < n24; ++j, n25 += n13, n27 += n14) {
                        if (array2[j] == 0) {
                            if (n27 > 0) {
                                if (n25 > 0) {
                                    if (n25 > n27) {
                                        final int n29 = j;
                                        final int[] cf = this.cf;
                                        final int n30 = n28;
                                        final int n31 = n26;
                                        final int n32 = n25;
                                        final int[] ck = ptviewer.ck;
                                        final int n33 = n27 << 12;
                                        array2[n29] = (array[cf[n30 / (n31 + (n32 * ck[n33 / n25] >> 12))]][this.ch[4096 - n33 / (n25 + n27)]] | 0xFF000000);
                                    }
                                    else {
                                        final int n34 = j;
                                        final int[] cf2 = this.cf;
                                        final int n35 = n28;
                                        final int n36 = n26;
                                        final int n37 = n27;
                                        final int[] ck2 = ptviewer.ck;
                                        final int n38 = n25 << 12;
                                        array2[n34] = (array[cf2[n35 / (n36 + (n37 * ck2[n38 / n27] >> 12))]][this.ch[n38 / (n25 + n27)]] | 0xFF000000);
                                    }
                                }
                                else {
                                    final int n39 = -n25;
                                    if (n39 > n27) {
                                        final int n40 = j;
                                        final int[] cf3 = this.cf;
                                        final int n41 = n28;
                                        final int n42 = n26;
                                        final int n43 = n39;
                                        final int[] ck3 = ptviewer.ck;
                                        final int n44 = n27 << 12;
                                        array2[n40] = (array[cf3[n41 / (n42 + (n43 * ck3[n44 / n39] >> 12))]][this.ci[4096 - n44 / (n39 + n27)]] | 0xFF000000);
                                    }
                                    else {
                                        final int n45 = j;
                                        final int[] cf4 = this.cf;
                                        final int n46 = n28;
                                        final int n47 = n26;
                                        final int n48 = n27;
                                        final int[] ck4 = ptviewer.ck;
                                        final int n49 = n39 << 12;
                                        array2[n45] = (array[cf4[n46 / (n47 + (n48 * ck4[n49 / n27] >> 12))]][this.ci[n49 / (n39 + n27)]] | 0xFF000000);
                                    }
                                }
                            }
                            else if (n27 == 0) {
                                if (n25 > 0) {
                                    array2[j] = (array[this.cf[n28 / (n26 + n25)]][n11] | 0xFF000000);
                                }
                                else {
                                    array2[j] = (array[this.cf[n28 / (n26 - n25)]][n12] | 0xFF000000);
                                }
                            }
                            else {
                                final int n50 = -n27;
                                if (n25 < 0) {
                                    final int n51 = -n25;
                                    if (n51 > n50) {
                                        final int n52 = j;
                                        final int[] cf5 = this.cf;
                                        final int n53 = n28;
                                        final int n54 = n26;
                                        final int n55 = n51;
                                        final int[] ck5 = ptviewer.ck;
                                        final int n56 = n50 << 12;
                                        array2[n52] = (array[cf5[n53 / (n54 + (n55 * ck5[n56 / n51] >> 12))]][this.ce[4096 - n56 / (n51 + n50)]] | 0xFF000000);
                                    }
                                    else {
                                        final int n57 = j;
                                        final int[] cf6 = this.cf;
                                        final int n58 = n28;
                                        final int n59 = n26;
                                        final int n60 = n50;
                                        final int[] ck6 = ptviewer.ck;
                                        final int n61 = n51 << 12;
                                        array2[n57] = (array[cf6[n58 / (n59 + (n60 * ck6[n61 / n50] >> 12))]][this.ce[n61 / (n51 + n50)]] | 0xFF000000);
                                    }
                                }
                                else if (n25 > n50) {
                                    final int n62 = j;
                                    final int[] cf7 = this.cf;
                                    final int n63 = n28;
                                    final int n64 = n26;
                                    final int n65 = n25;
                                    final int[] ck7 = ptviewer.ck;
                                    final int n66 = n50 << 12;
                                    array2[n62] = (array[cf7[n63 / (n64 + (n65 * ck7[n66 / n25] >> 12))]][this.cj[4096 - n66 / (n25 + n50)]] | 0xFF000000);
                                }
                                else {
                                    final int n67 = j;
                                    final int[] cf8 = this.cf;
                                    final int n68 = n28;
                                    final int n69 = n26;
                                    final int n70 = n50;
                                    final int[] ck8 = ptviewer.ck;
                                    final int n71 = n25 << 12;
                                    array2[n67] = (array[cf8[n68 / (n69 + (n70 * ck8[n71 / n50] >> 12))]][this.cj[n71 / (n25 + n50)]] | 0xFF000000);
                                }
                            }
                        }
                    }
                }
                else if (n26 < 0) {
                    final int n72 = -n26;
                    final int n73 = n72 << 12;
                    for (int k = i; k < n24; ++k, n25 += n13, n27 += n14) {
                        if (array2[k] == 0) {
                            if (n27 > 0) {
                                if (n25 > 0) {
                                    if (n25 > n27) {
                                        final int n74 = k;
                                        final int[] cg = this.cg;
                                        final int n75 = n73;
                                        final int n76 = n72;
                                        final int n77 = n25;
                                        final int[] ck9 = ptviewer.ck;
                                        final int n78 = n27 << 12;
                                        array2[n74] = (array[cg[n75 / (n76 + (n77 * ck9[n78 / n25] >> 12))]][this.ch[4096 - n78 / (n25 + n27)]] | 0xFF000000);
                                    }
                                    else {
                                        final int n79 = k;
                                        final int[] cg2 = this.cg;
                                        final int n80 = n73;
                                        final int n81 = n72;
                                        final int n82 = n27;
                                        final int[] ck10 = ptviewer.ck;
                                        final int n83 = n25 << 12;
                                        array2[n79] = (array[cg2[n80 / (n81 + (n82 * ck10[n83 / n27] >> 12))]][this.ch[n83 / (n25 + n27)]] | 0xFF000000);
                                    }
                                }
                                else {
                                    final int n84 = -n25;
                                    if (n84 > n27) {
                                        final int n85 = k;
                                        final int[] cg3 = this.cg;
                                        final int n86 = n73;
                                        final int n87 = n72;
                                        final int n88 = n84;
                                        final int[] ck11 = ptviewer.ck;
                                        final int n89 = n27 << 12;
                                        array2[n85] = (array[cg3[n86 / (n87 + (n88 * ck11[n89 / n84] >> 12))]][this.ci[4096 - n89 / (n84 + n27)]] | 0xFF000000);
                                    }
                                    else {
                                        final int n90 = k;
                                        final int[] cg4 = this.cg;
                                        final int n91 = n73;
                                        final int n92 = n72;
                                        final int n93 = n27;
                                        final int[] ck12 = ptviewer.ck;
                                        final int n94 = n84 << 12;
                                        array2[n90] = (array[cg4[n91 / (n92 + (n93 * ck12[n94 / n27] >> 12))]][this.ci[n94 / (n84 + n27)]] | 0xFF000000);
                                    }
                                }
                            }
                            else if (n27 == 0) {
                                if (n25 > 0) {
                                    array2[k] = array[this.cg[n73 / (n72 + n25)]][n11];
                                }
                                else {
                                    array2[k] = array[this.cg[n73 / (n72 - n25)]][n12];
                                }
                            }
                            else {
                                final int n95 = -n27;
                                if (n25 < 0) {
                                    final int n96 = -n25;
                                    if (n96 > n95) {
                                        final int n97 = k;
                                        final int[] cg5 = this.cg;
                                        final int n98 = n73;
                                        final int n99 = n72;
                                        final int n100 = n96;
                                        final int[] ck13 = ptviewer.ck;
                                        final int n101 = n95 << 12;
                                        array2[n97] = (array[cg5[n98 / (n99 + (n100 * ck13[n101 / n96] >> 12))]][this.ce[4096 - n101 / (n96 + n95)]] | 0xFF000000);
                                    }
                                    else {
                                        final int n102 = k;
                                        final int[] cg6 = this.cg;
                                        final int n103 = n73;
                                        final int n104 = n72;
                                        final int n105 = n95;
                                        final int[] ck14 = ptviewer.ck;
                                        final int n106 = n96 << 12;
                                        array2[n102] = (array[cg6[n103 / (n104 + (n105 * ck14[n106 / n95] >> 12))]][this.ce[n106 / (n96 + n95)]] | 0xFF000000);
                                    }
                                }
                                else if (n25 > n95) {
                                    final int n107 = k;
                                    final int[] cg7 = this.cg;
                                    final int n108 = n73;
                                    final int n109 = n72;
                                    final int n110 = n25;
                                    final int[] ck15 = ptviewer.ck;
                                    final int n111 = n95 << 12;
                                    array2[n107] = (array[cg7[n108 / (n109 + (n110 * ck15[n111 / n25] >> 12))]][this.cj[4096 - n111 / (n25 + n95)]] | 0xFF000000);
                                }
                                else {
                                    final int n112 = k;
                                    final int[] cg8 = this.cg;
                                    final int n113 = n73;
                                    final int n114 = n72;
                                    final int n115 = n95;
                                    final int[] ck16 = ptviewer.ck;
                                    final int n116 = n25 << 12;
                                    array2[n112] = (array[cg8[n113 / (n114 + (n115 * ck16[n116 / n95] >> 12))]][this.cj[n116 / (n25 + n95)]] | 0xFF000000);
                                }
                            }
                        }
                    }
                }
                else {
                    for (int l = i; l < n24; ++l, n25 += n13, n27 += n14) {
                        if (array2[l] == 0) {
                            int n117;
                            if (n27 > 0) {
                                if (n25 > 0) {
                                    n117 = this.ch[(n25 << 12) / (n25 + n27)];
                                }
                                else {
                                    n117 = this.ci[(-n25 << 12) / (n27 - n25)];
                                }
                            }
                            else if (n27 == 0) {
                                if (n25 > 0) {
                                    n117 = n11;
                                }
                                else {
                                    n117 = n12;
                                }
                            }
                            else if (n25 < 0) {
                                n117 = this.ce[(-n25 << 12) / (-n25 - n27)];
                            }
                            else {
                                n117 = this.cj[(n25 << 12) / (n27 - n25)];
                            }
                            array2[l] = (array[0][n117] | 0xFF000000);
                        }
                    }
                }
            }
            return;
        }
        int n118 = 0;
        int[] array4 = array[0];
        int[] array5 = array[1];
        int n119 = this.cq[1][0] * n17 + this.cq[2][0];
        int n120 = this.cq[1][1] * n17 + this.cq[2][1];
        int n121 = this.cq[1][2] * n17 + this.cq[2][2];
        final int n122 = this.cq[0][0];
        final int n123 = this.cq[0][2];
        for (int n124 = n17; n124 < n18; ++n124, n20 += n3, n119 += this.cq[1][0], n120 += this.cq[1][1], n121 += this.cq[1][2]) {
            int n125 = n20;
            int n126 = this.cq[0][0] * n15 + n119;
            final int n127 = n120;
            for (int n128 = this.cq[0][2] * n15 + n121, n129 = n15; n129 < n16; ++n129, ++n125, n126 += n122, n128 += n123) {
                if (array2[n125] == 0) {
                    final int g = g(n126, n128);
                    final int g2 = g(n127, h(Math.abs(n128), Math.abs(n126)));
                    final int n130 = g & 0xFF;
                    final int n131 = g2 & 0xFF;
                    int n132 = (g >> 8) + n9;
                    int n133 = (g2 >> 8) + n10;
                    int n134;
                    int n135;
                    int n136;
                    int n137;
                    if (n133 == n118 && n132 >= 0 && n132 < n5) {
                        n134 = array4[n132];
                        n135 = array5[n132++];
                        n136 = array4[n132];
                        n137 = array5[n132];
                    }
                    else if (n133 >= 0 && n133 < n6 && n132 >= 0 && n132 < n5) {
                        n118 = n133;
                        array4 = array[n133];
                        array5 = array[n133 + 1];
                        n134 = array4[n132];
                        n135 = array5[n132++];
                        n136 = array4[n132];
                        n137 = array5[n132];
                    }
                    else {
                        int n138;
                        if (n133 < 0) {
                            n138 = 0;
                        }
                        else if (n133 > n6) {
                            n138 = n6;
                        }
                        else {
                            n138 = n133;
                        }
                        if (n132 < 0) {
                            n134 = array[n138][n5];
                        }
                        else if (n132 > n5) {
                            n134 = array[n138][0];
                        }
                        else {
                            n134 = array[n138][n132];
                        }
                        if (++n132 < 0) {
                            n136 = array[n138][n5];
                        }
                        else if (n132 > n5) {
                            n136 = array[n138][0];
                        }
                        else {
                            n136 = array[n138][n132];
                        }
                        --n132;
                        int n139;
                        if (++n133 < 0) {
                            n139 = 0;
                        }
                        else if (n133 > n6) {
                            n139 = n6;
                        }
                        else {
                            n139 = n133;
                        }
                        if (n132 < 0) {
                            n135 = array[n139][n5];
                        }
                        else if (n132 > n5) {
                            n135 = array[n139][0];
                        }
                        else {
                            n135 = array[n139][n132];
                        }
                        if (++n132 < 0) {
                            n137 = array[n139][n5];
                        }
                        else if (n132 > n5) {
                            n137 = array[n139][0];
                        }
                        else {
                            n137 = array[n139][n132];
                        }
                    }
                    array2[n125] = a(n134, n136, n135, n137, n130, n131);
                    array3[n125] = (byte)(n134 >> 24);
                }
            }
        }
    }
    
    final double[] a(int n, int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8, final double n9) {
        final double n10 = n5 / 6.283185307179586;
        final double n11 = (int)(n3 / (2.0 * Math.tan(n9 * 2.0 * 3.141592653589793 / 360.0 / 2.0)) + 0.5);
        this.a(n8 * 2.0 * 3.141592653589793 / 360.0, n7 * 2.0 * 3.141592653589793 / 360.0, this.cp, 1);
        n -= n3 / 2;
        n2 -= n4 / 2;
        final double n12 = this.cp[0][0] * n + this.cp[1][0] * n2 + this.cp[2][0] * n11;
        final double n13 = this.cp[0][1] * n + this.cp[1][1] * n2 + this.cp[2][1] * n11;
        final double n14 = this.cp[0][2] * n + this.cp[1][2] * n2 + this.cp[2][2] * n11;
        return new double[] { n10 * Math.atan2(n12, n14) + n5 / 2.0, n10 * Math.atan2(n13, Math.sqrt(n14 * n14 + n12 * n12)) + n6 / 2.0 };
    }
    
    final int[] b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final double n8, final double n9) {
        final double[] a = this.a(n, n2, n3, n4, n5, n6, n7, n8, n9);
        if (a[0] < 0.0) {
            a[0] = 0.0;
        }
        if (a[0] >= n5) {
            a[0] = n5 - 1;
        }
        if (a[1] < 0.0) {
            a[1] = 0.0;
        }
        if (a[1] >= n6) {
            a[1] = n6 - 1;
        }
        return new int[] { (int)a[0], (int)a[1] };
    }
    
    final double a(final double n, final int n2, final int n3) {
        return 114.59155902616465 * Math.atan(n3 / n2 * Math.tan(n / 2.0 * 3.141592653589793 / 180.0));
    }
    
    void m(final int cr) {
        try {
            this.cs = new String[cr];
            this.ct = new int[cr];
            this.cu = new int[cr];
            this.cv = new boolean[cr];
            this.cr = cr;
        }
        catch (Exception ex) {
            this.cr = 0;
        }
    }
    
    void J() {
        for (int i = 0; i < this.cr; ++i) {
            this.cs[i] = null;
        }
        this.cs = null;
        this.ct = null;
        this.cu = null;
        this.cv = null;
        this.cr = 0;
    }
    
    void b(final String s, final int n) {
        int i = 0;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        this.cs[n] = null;
        this.ct[n] = 0;
        this.cu[n] = 0;
        this.cv[n] = false;
        while (i < length) {
            switch (s.charAt(i++)) {
                default: {
                    continue;
                }
                case 'x': {
                    i = this.a(i, s, sb);
                    this.ct[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'y': {
                    i = this.a(i, s, sb);
                    this.cu[n] = Integer.parseInt(sb.toString());
                    continue;
                }
                case 'i': {
                    i = this.a(i, s, sb);
                    this.cs[n] = sb.toString();
                    continue;
                }
            }
        }
    }
    
    void K() {
        this.cw = new Vector();
    }
    
    void L() {
        this.cw.removeAllElements();
    }
    
    public synchronized void n(final int n) {
        if (this.a != null) {
            this.a.stop();
        }
        if (n < this.cw.size() && this.cw.elementAt(n) != null && this.cw.elementAt(n) instanceof AudioClip) {
            (this.a = this.cw.elementAt(n)).play();
        }
    }
    
    void M() {
        for (int i = 0; i < this.cw.size(); ++i) {
            if (this.cw.elementAt(i) != null && this.cw.elementAt(i) instanceof String) {
                final String s = this.cw.elementAt(i);
                try {
                    this.cw.setElementAt(this.getAudioClip(new URL(this.getDocumentBase(), s)), i);
                }
                catch (Exception ex) {
                    try {
                        this.cw.setElementAt(this.getAudioClip(Class.forName(n("~W\u0004--yF\u0000")).getResource(s)), i);
                    }
                    catch (Exception ex2) {
                        this.cw.setElementAt(null, i);
                    }
                }
            }
        }
    }
    
    void N() {
        this.cx = new Hashtable();
        this.cy = new Vector();
    }
    
    public void o(final int n) {
        if (n < 0 || this.cy == null || n >= this.cy.size() || this.cy.elementAt(n) == null) {
            return;
        }
        if (this.cx.get(this.cy.elementAt(n)) != null) {
            this.p(n);
        }
        try {
            final String b = this.b(this.cy.elementAt(n), n("mL\u0016!"));
            final Applet applet = (Applet)Class.forName(b.substring(0, b.lastIndexOf(n(" @\u001e%;}")))).getConstructor(Class.forName(n("~W\u0004--yF\u0000")), (ptviewer.da == null) ? (ptviewer.da = m(n("dB\u0004%fbB\u001c#f]W\u0000-&i"))) : ptviewer.da).newInstance(this, this.cy.elementAt(n));
            this.cx.put(this.cy.elementAt(n), applet);
            applet.init();
            applet.start();
        }
        catch (Exception ex) {
            try {
                final String b2 = this.b(this.cy.elementAt(n), n("mL\u0016!"));
                final Applet applet2 = (Applet)Class.forName(b2.substring(0, b2.lastIndexOf(n(" @\u001e%;}")))).getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
                this.cx.put(this.cy.elementAt(n), applet2);
                applet2.setStub((AppletStub)Class.forName(n("~W\u00010=l")).getConstructor(Class.forName(n("~W\u0004--yF\u0000")), (ptviewer.da == null) ? (ptviewer.da = m(n("dB\u0004%fbB\u001c#f]W\u0000-&i"))) : ptviewer.da).newInstance(this, this.cy.elementAt(n)));
                applet2.init();
                applet2.start();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void p(final int n) {
        if (n < 0 || this.cy == null || n >= this.cy.size() || this.cy.elementAt(n) == null) {
            return;
        }
        final Applet applet = this.cx.get(this.cy.elementAt(n));
        if (applet != null) {
            applet.stop();
            this.cx.remove(this.cy.elementAt(n));
        }
    }
    
    void q(final int n) {
        for (int i = n; i < this.cy.size(); ++i) {
            this.p(i);
        }
    }
    
    void O() {
        this.cz = new Vector();
    }
    
    void r(final int ca) {
        try {
            this.cD = new double[ca];
            this.cE = new double[ca];
            this.cF = new double[ca];
            this.cG = new double[ca];
            this.cH = new int[ca];
            this.cI = new int[ca];
            this.cJ = new Color[ca];
            this.cK = new String[ca];
            this.cL = new String[ca];
            this.cM = new String[ca];
            this.cN = new Object[ca];
            this.cP = new boolean[ca];
            this.cQ = new int[ca];
            this.cO = new String[ca];
            this.cR = new int[ca];
            this.cA = ca;
        }
        catch (Exception ex) {
            this.cA = 0;
        }
    }
    
    void P() {
        for (int i = 0; i < this.cA; ++i) {
            if (this.cN[i] != null) {
                this.cN[i] = null;
            }
            this.cJ[i] = null;
            this.cK[i] = null;
            this.cL[i] = null;
            this.cM[i] = null;
            this.cO[i] = null;
        }
        this.cA = 0;
        this.cz.removeAllElements();
        this.cD = null;
        this.cE = null;
        this.cF = null;
        this.cG = null;
        this.cH = null;
        this.cI = null;
        this.cJ = null;
        this.cK = null;
        this.cL = null;
        this.cN = null;
        this.cP = null;
        this.cM = null;
        this.cO = null;
        this.cQ = null;
        this.cR = null;
        this.cC = null;
    }
    
    void c(final String s, final int n) {
        int i = 0;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        this.cD[n] = 0.0;
        this.cE[n] = 0.0;
        this.cF[n] = -200.0;
        this.cG[n] = -200.0;
        this.cH[n] = 0;
        this.cI[n] = 0;
        this.cJ[n] = null;
        this.cK[n] = null;
        this.cL[n] = null;
        this.cM[n] = null;
        this.cN[n] = null;
        this.cP[n] = false;
        this.cQ[n] = 0;
        this.cO[n] = null;
        this.cR[n] = -1;
        while (i < length) {
            switch (s.charAt(i++)) {
                default: {
                    continue;
                }
                case 'x': {
                    i = this.a(i, s, sb);
                    this.cD[n] = Double.valueOf(sb.toString());
                    continue;
                }
                case 'X': {
                    i = this.a(i, s, sb);
                    this.cD[n] = -Double.valueOf(sb.toString());
                    continue;
                }
                case 'y': {
                    i = this.a(i, s, sb);
                    this.cE[n] = Double.valueOf(sb.toString());
                    continue;
                }
                case 'Y': {
                    i = this.a(i, s, sb);
                    this.cE[n] = -Double.valueOf(sb.toString());
                    continue;
                }
                case 'a': {
                    i = this.a(i, s, sb);
                    this.cF[n] = Double.valueOf(sb.toString());
                    continue;
                }
                case 'A': {
                    i = this.a(i, s, sb);
                    this.cF[n] = -Double.valueOf(sb.toString());
                    continue;
                }
                case 'b': {
                    i = this.a(i, s, sb);
                    this.cG[n] = Double.valueOf(sb.toString());
                    continue;
                }
                case 'B': {
                    i = this.a(i, s, sb);
                    this.cG[n] = -Double.valueOf(sb.toString());
                    continue;
                }
                case 'c': {
                    i = this.a(i, s, sb);
                    this.cJ[n] = new Color(Integer.parseInt(sb.toString(), 16));
                    continue;
                }
                case 'n': {
                    i = this.a(i, s, sb);
                    this.cK[n] = sb.toString();
                    continue;
                }
                case 'm': {
                    i = this.a(i, s, sb);
                    this.cO[n] = sb.toString();
                    continue;
                }
                case 'p': {
                    final int[] cq = this.cQ;
                    cq[n] |= 0x1;
                    continue;
                }
                case 'q': {
                    final int[] cq2 = this.cQ;
                    cq2[n] |= 0x2;
                    continue;
                }
                case 'w': {
                    final int[] cq3 = this.cQ;
                    cq3[n] |= 0x4;
                    continue;
                }
                case 'e': {
                    final int[] cq4 = this.cQ;
                    cq4[n] |= 0x10;
                    continue;
                }
                case 'u': {
                    i = this.a(i, s, sb);
                    this.cL[n] = sb.toString();
                    continue;
                }
                case 'i': {
                    i = this.a(i, s, sb);
                    this.cN[n] = sb.toString();
                    continue;
                }
                case 't': {
                    i = this.a(i, s, sb);
                    this.cM[n] = sb.toString();
                    continue;
                }
            }
        }
    }
    
    void Q() {
        if (this.cz.size() == 0) {
            return;
        }
        this.r(this.cz.size());
        for (int i = 0; i < this.cA; ++i) {
            this.c((String)this.cz.elementAt(i), i);
        }
        this.R();
    }
    
    void b(final int[][] array) {
        if (array == null) {
            return;
        }
        final int length = array.length;
        final int length2 = array[0].length;
        this.Q();
        for (int i = 0; i < this.cA; ++i) {
            if (this.cN[i] != null && (this.cQ[i] & 0x10) == 0x0) {
                final String s = (String)this.cN[i];
                if (!s.startsWith(n("~W\u0004--yF\u0000~")) && !s.startsWith(n("dB\u0004%;mQ\u001b4<4"))) {
                    this.cN[i] = this.h(s);
                }
            }
        }
        this.i(length2, length);
        if (this.cC != null) {
            this.cC = this.h((String)this.cC);
        }
        if (this.cC != null && this.cC instanceof Image && length2 == ((Image)this.cC).getWidth(null) && length == ((Image)this.cC).getHeight(null)) {
            this.b(array, (Image)this.cC);
        }
        else {
            for (int n = 0; n < this.cA && n < 255; ++n) {
                if (this.cR[n] == -1) {
                    if (this.cF[n] != -200.0 && this.cG[n] != -200.0) {
                        this.a((int)this.cD[n], (int)this.cE[n], (int)this.cF[n], (int)this.cG[n], n, array);
                        if (this.cF[n] >= this.cD[n]) {
                            final double[] cd = this.cD;
                            final int n2 = n;
                            cd[n2] += (this.cF[n] - this.cD[n]) / 2.0;
                            this.cF[n] -= this.cD[n];
                        }
                        else {
                            final double[] cd2 = this.cD;
                            final int n3 = n;
                            cd2[n3] += (this.cF[n] + length2 - this.cD[n]) / 2.0;
                            this.cF[n] = this.cF[n] + length2 - this.cD[n];
                        }
                        this.cE[n] = (this.cE[n] + this.cG[n]) / 2.0;
                        this.cG[n] = Math.abs(this.cE[n] - this.cG[n]);
                    }
                    else if ((this.cQ[n] & 0x4) > 0 && this.cN[n] != null && this.cN[n] instanceof Image && this.cO[n] == null) {
                        this.cF[n] = ((Image)this.cN[n]).getWidth(null);
                        this.cG[n] = ((Image)this.cN[n]).getHeight(null);
                        this.a((int)(this.cD[n] - this.cF[n] / 2.0), (int)(this.cE[n] - this.cG[n] / 2.0), (int)(this.cD[n] + this.cF[n] / 2.0), (int)(this.cE[n] + this.cG[n] / 2.0), n, array);
                    }
                    else if (this.cO[n] != null) {
                        final Image h = this.h(this.cO[n]);
                        if (h != null) {
                            final int[] array2 = new int[h.getWidth(null) * h.getHeight(null)];
                            final PixelGrabber pixelGrabber = new PixelGrabber(h, 0, 0, h.getWidth(null), h.getHeight(null), array2, 0, h.getWidth(null));
                            try {
                                pixelGrabber.grabPixels();
                            }
                            catch (InterruptedException ex) {
                                continue;
                            }
                            int n4 = (int)this.cE[n];
                            final int n5 = (n << 24) + 16777215;
                            int n6 = 0;
                            for (int n7 = 0; n7 < h.getHeight(null) && n4 < length; ++n7, ++n4) {
                                final int n8 = n7 * h.getWidth(null);
                                for (int n9 = 0, n10 = (int)this.cD[n]; n9 < h.getWidth(null) && n10 < length2; ++n9, ++n10) {
                                    if ((array2[n8 + n9] & 0xFFFFFF) == 0xFFFFFF) {
                                        final int[] array3 = array[n4];
                                        final int n11 = n10;
                                        array3[n11] &= n5;
                                        ++n6;
                                    }
                                }
                            }
                            final double[] ce = this.cE;
                            final int n12 = n;
                            ce[n12] += h.getHeight(null) / 2;
                            final double[] cd3 = this.cD;
                            final int n13 = n;
                            cd3[n13] += h.getWidth(null) / 2;
                            this.cF[n] = h.getWidth(null);
                            this.cG[n] = h.getHeight(null);
                        }
                    }
                }
            }
        }
        for (int j = 0; j < this.cA; ++j) {
            if (this.cR[j] != -1) {
                this.cD[j] = this.cD[this.cR[j]];
                this.cE[j] = this.cE[this.cR[j]];
                this.cF[j] = this.cF[this.cR[j]];
                this.cG[j] = this.cG[this.cR[j]];
            }
        }
        for (int k = 0; k < this.cA; ++k) {
            if ((this.cQ[k] & 0x4) > 0 && this.cN[k] != null) {
                if (this.cN[k] instanceof Image) {
                    final Image image = (Image)this.cN[k];
                    final int width = image.getWidth(null);
                    final int height = image.getHeight(null);
                    final int n14 = (int)this.cD[k] - width / 2;
                    final int n15 = (int)this.cE[k] - height / 2;
                    if (n14 >= 0 && n15 >= 0 && width + n14 <= length2 && height + n15 <= length) {
                        final int[] array4 = new int[width * height * 2];
                        final PixelGrabber pixelGrabber2 = new PixelGrabber(image, 0, 0, width, height, array4, 0, width);
                        try {
                            pixelGrabber2.grabPixels();
                        }
                        catch (InterruptedException ex2) {
                            continue;
                        }
                        this.b(array, n14, n15, array4, width, 0, height, width, height);
                        this.cN[k] = array4;
                        this.cF[k] = width;
                        this.cG[k] = height;
                    }
                    else {
                        System.out.println(n("GN\u0013#-.E\u001d6hFL\u000678aWR\n'.") + k + n(".L\u00070;gG\u0017d%oJ\u001cd8oM\u001d6)cB"));
                    }
                }
            }
        }
    }
    
    boolean a(final int[][] array, final int n, final boolean b) {
        boolean b2 = false;
        if (array == null) {
            return false;
        }
        for (int i = 0; i < this.cA; ++i) {
            if ((this.cQ[i] & 0x4) > 0 && this.cN[i] != null && this.cN[i] instanceof int[]) {
                final int n2 = (int)this.cF[i];
                final int n3 = (int)this.cG[i];
                final int n4 = (int)this.cD[i] - n2 / 2;
                final int n5 = (int)this.cE[i] - n3 / 2;
                if (b || (this.cQ[i] & 0x2) > 0 || (i == n && (this.cQ[i] & 0x1) > 0) || (n >= 0 && this.cR[i] == n && (this.cQ[i] & 0x1) > 0)) {
                    if ((this.cQ[i] & 0x8) == 0x0) {
                        this.a(array, n4, n5, (int[])this.cN[i], n2, 0, 0, n2, n3);
                        final int[] cq = this.cQ;
                        final int n6 = i;
                        cq[n6] |= 0x8;
                        b2 = true;
                    }
                }
                else if ((this.cQ[i] & 0x8) > 0) {
                    this.a(array, n4, n5, (int[])this.cN[i], n2, 0, n3, n2, n3);
                    final int[] cq2 = this.cQ;
                    final int n7 = i;
                    cq2[n7] &= 0xFFFFFFF7;
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    void i(final int n, final int n2) {
        for (int i = 0; i < this.cA; ++i) {
            if (this.cD[i] < 0.0) {
                this.cD[i] = -this.cD[i] * n / 100.0;
                if (this.cD[i] >= n) {
                    this.cD[i] = n - 1;
                }
            }
            if (this.cE[i] < 0.0) {
                this.cE[i] = -this.cE[i] * n2 / 100.0;
                if (this.cE[i] >= n2) {
                    this.cE[i] = n2 - 1;
                }
            }
            if (this.cF[i] < 0.0 && this.cF[i] != -200.0) {
                this.cF[i] = -this.cF[i] * n / 100.0;
                if (this.cF[i] >= n) {
                    this.cF[i] = n - 1;
                }
            }
            if (this.cG[i] < 0.0 && this.cG[i] != -200.0) {
                this.cG[i] = -this.cG[i] * n2 / 100.0;
                if (this.cG[i] >= n2) {
                    this.cG[i] = n2 - 1;
                }
            }
        }
    }
    
    void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        for (int i = 0; i < this.cA; ++i) {
            if (this.cP[i] && (b || (this.cQ[i] & 0x2) > 0 || (i == n5 && (this.cQ[i] & 0x1) > 0) || (n5 >= 0 && this.cR[i] == n5 && (this.cQ[i] & 0x1) > 0))) {
                if (this.cN[i] == null) {
                    if (this.cJ[i] == null) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(this.cJ[i]);
                    }
                    graphics.drawOval(this.cH[i] - 10 + n, this.cI[i] - 10 + n2, 20, 20);
                    graphics.fillOval(this.cH[i] - 5 + n, this.cI[i] - 5 + n2, 10, 10);
                }
                else if (this.cN[i] instanceof Image) {
                    final Image image = (Image)this.cN[i];
                    graphics.drawImage(image, this.cH[i] - image.getWidth(null) / 2 + n, this.cI[i] - image.getHeight(null) / 2 + n2, this);
                }
                else if ((this.cQ[i] & 0x10) > 0 && this.cN[i] instanceof String) {
                    final String s = (String)this.cN[i];
                    final Dimension a = this.a(graphics, s);
                    if (this.cH[i] >= 0 && this.cH[i] < n3 && this.cI[i] >= 0 && this.cI[i] < n4) {
                        int n6 = 0;
                        int n7 = 0;
                        int n8 = 0;
                        if (this.cH[i] + a.width < n3) {
                            if (this.cI[i] - a.height > 0) {
                                n6 = this.cH[i];
                                n7 = this.cI[i] - a.height;
                                n8 = 1;
                            }
                            else if (this.cI[i] + a.height < n3) {
                                n6 = this.cH[i];
                                n7 = this.cI[i];
                                n8 = 2;
                            }
                        }
                        else if (this.cH[i] - a.width >= 0) {
                            if (this.cI[i] - a.height > 0) {
                                n6 = this.cH[i] - a.width;
                                n7 = this.cI[i] - a.height;
                                n8 = 3;
                            }
                            else if (this.cI[i] + a.height < n3) {
                                n6 = this.cH[i] - a.width;
                                n7 = this.cI[i];
                                n8 = 4;
                            }
                        }
                        if (n8 != 0) {
                            this.a(graphics, n6 + n, n7 + n2, a, this.cJ[i], s, n8);
                        }
                    }
                }
            }
        }
    }
    
    final void s(final int n) {
        for (int i = 0; i < this.cA; ++i) {
            if (this.cP[i] && this.cN[i] != null && (i == n || (n >= 0 && this.cR[i] == n)) && this.cN[i] instanceof String && (this.cQ[i] & 0x10) == 0x0) {
                this.a((String)this.cN[i], (String)null);
            }
        }
    }
    
    final void R() {
        for (int i = 0; i < this.cA; ++i) {
            for (int j = i + 1; j < this.cA; ++j) {
                if (this.cD[i] == this.cD[j] && this.cE[i] == this.cE[j] && this.cR[i] == -1) {
                    this.cR[j] = i;
                }
            }
        }
    }
    
    final void a(final int n, final int n2, final int n3, final int n4, final double n5, final double n6, final double n7) {
        final int n8 = n3 / 2;
        final int n9 = n4 / 2;
        final double[][] array = new double[3][3];
        final double n10 = n / (2.0 * Math.tan(n7 * 2.0 * 3.141592653589793 / 360.0 / 2.0));
        this.a(-n6 * 2.0 * 3.141592653589793 / 360.0, -n5 * 2.0 * 3.141592653589793 / 360.0, array, 0);
        for (int i = 0; i < this.cA; ++i) {
            final double n11 = this.cD[i] - n8;
            final double n12 = this.C - (this.cE[i] - n9);
            final double n13 = n11 / n8 * 3.141592653589793;
            final double n14 = n12 / n9 * 3.141592653589793 / 2.0;
            double n15;
            if (Math.abs(n13) > 1.5707963267948966) {
                n15 = 1.0;
            }
            else {
                n15 = -1.0;
            }
            final double n16 = n15 * Math.tan(n13);
            final double n17 = Math.sqrt(n16 * n16 + n15 * n15) * Math.tan(n14);
            final double n18 = array[0][0] * n16 + array[1][0] * n17 + array[2][0] * n15;
            final double n19 = array[0][1] * n16 + array[1][1] * n17 + array[2][1] * n15;
            final double n20 = array[0][2] * n16 + array[1][2] * n17 + array[2][2] * n15;
            this.cH[i] = (int)(n18 * n10 / n20 + n / 2.0);
            this.cI[i] = (int)(n19 * n10 / n20 + n2 / 2.0);
            int n21 = 12;
            int n22 = 12;
            if (this.cN[i] != null && this.cN[i] instanceof Image) {
                n21 = ((Image)this.cN[i]).getWidth(null) / 2;
                n22 = ((Image)this.cN[i]).getHeight(null) / 2;
            }
            else if (this.cN[i] != null && this.cN[i] instanceof String && (this.cQ[i] & 0x10) > 0) {
                n21 = 100;
                n22 = 100;
            }
            else if (this.cF[i] != -200.0 && this.cG[i] != -200.0) {
                n21 = 100;
                n22 = 100;
            }
            if (this.cH[i] >= -n21 && this.cH[i] < this.w + n21 && this.cI[i] >= -n22 && this.cI[i] < this.x + n22 && n20 < 0.0) {
                this.cP[i] = true;
            }
            else {
                this.cP[i] = false;
            }
        }
    }
    
    static Class m(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ptviewer.cd = null;
    }
    
    private static String n(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u000e';
                    break;
                }
                case 1: {
                    c2 = '#';
                    break;
                }
                case 2: {
                    c2 = 'r';
                    break;
                }
                case 3: {
                    c2 = 'D';
                    break;
                }
                default: {
                    c2 = 'H';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
