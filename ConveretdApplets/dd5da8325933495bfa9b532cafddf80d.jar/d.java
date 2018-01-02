import java.applet.Applet;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.util.Enumeration;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Date;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Point;
import java.awt.Container;
import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.util.Vector;
import java.awt.Color;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    private int a;
    private int b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    public boolean A;
    public boolean B;
    private final int C;
    private long[] D;
    private int E;
    private int F;
    private int G;
    private int H;
    private float I;
    public a J;
    public String K;
    private String L;
    private String M;
    private String N;
    private String O;
    private String P;
    public int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private long X;
    private long Y;
    private long Z;
    private long ba;
    private long bb;
    private long bc;
    private long bd;
    public long be;
    public long bf;
    private boolean bg;
    private boolean bh;
    private boolean bi;
    private boolean bj;
    public boolean bk;
    public boolean bl;
    public boolean bm;
    public boolean bn;
    public int bo;
    public int bp;
    public int bq;
    public int br;
    public int bs;
    private Thread bt;
    private final String bu;
    public Frame bv;
    private String bw;
    private String bx;
    private String by;
    private String bz;
    private Color bA;
    private boolean bB;
    private boolean bC;
    private String bD;
    private String bE;
    private int bF;
    private int bG;
    private int bH;
    private int bI;
    private int bJ;
    public String bK;
    private String bL;
    private int bM;
    private int bN;
    private int bO;
    private int bP;
    private int bQ;
    private int bR;
    public String bS;
    private boolean[] bT;
    private boolean[] bU;
    private Vector bV;
    public Image[] bW;
    private Image bX;
    public String[] bY;
    public int[] bZ;
    public int[] ca;
    public int[] cb;
    public int[] cc;
    public int cd;
    public int[] ce;
    private int cf;
    private int cg;
    private int ch;
    private int ci;
    private int cj;
    private int ck;
    private int cl;
    private int cm;
    private int cn;
    private int co;
    private int cp;
    private int cq;
    public boolean[] cr;
    public boolean cs;
    private boolean ct;
    private boolean cu;
    private boolean cv;
    private boolean cw;
    private MediaTracker cx;
    public Graphics cy;
    public Graphics cz;
    private Color cA;
    private Graphics[] cB;
    private final String cC;
    private int cD;
    private String[] cE;
    private String[] cF;
    private String[] cG;
    private String[] cH;
    private String[] cI;
    private String[] cJ;
    private String[] cK;
    private int[] cL;
    private int[] cM;
    private int[] cN;
    private int[] cO;
    private int[] cP;
    private int cQ;
    private int cR;
    private int[] cS;
    private int[] cT;
    private int[] cU;
    private int[] cV;
    private int[] cW;
    private int[] cX;
    private int cY;
    private int cZ;
    private int[] da;
    private int[] db;
    private int[] dc;
    private int dd;
    private int[] de;
    private int[] df;
    private int[] dg;
    private int[] dh;
    private j[] di;
    private Rectangle[] dj;
    private Color[] dk;
    public int dl;
    public int dm;
    public boolean dn;
    private float[] dp;
    private float[] dq;
    private float[] dr;
    private float[] ds;
    private e[] dt;
    private Vector du;
    private Vector dv;
    public String dw;
    private String dx;
    private String dy;
    private String dz;
    private String dA;
    private String dB;
    private String dC;
    private String dD;
    private String dE;
    private String dF;
    private String dG;
    private boolean dH;
    private boolean dI;
    private boolean dJ;
    private boolean dK;
    private boolean dL;
    private boolean dM;
    private boolean dN;
    private boolean dO;
    private boolean dP;
    private boolean dQ;
    private boolean dR;
    private int dS;
    private int dT;
    private int dU;
    private int dV;
    private int dW;
    private long dX;
    public AudioClip[] dY;
    private String[] dZ;
    private boolean[] ea;
    private boolean[] eb;
    private Vector ec;
    private Color ed;
    private Color ee;
    private Color ef;
    private int eg;
    private int eh;
    private AudioClip ei;
    private int ej;
    private int ek;
    private int el;
    private int em;
    private int en;
    private String[] eo;
    private String ep;
    private Color eq;
    private Font er;
    private FontMetrics es;
    private boolean et;
    private boolean eu;
    private boolean ev;
    private boolean ew;
    private long[] ex;
    private long ey;
    private String ez;
    private String[] eA;
    private int eB;
    private int eC;
    private Color eD;
    private Font eE;
    private FontMetrics eF;
    private String eG;
    private int eH;
    private int eI;
    private Color eJ;
    private Hashtable eK;
    private boolean fc;
    private boolean fd;
    private boolean fe;
    private String ff;
    private String fg;
    private String fh;
    private String fi;
    private int fj;
    private int fk;
    private int fl;
    private int fm;
    private String fn;
    private int fo;
    private int fp;
    private int fq;
    private int fr;
    private int fs;
    private int ft;
    private int fu;
    private Color fv;
    private Color fw;
    private Color fx;
    private Font fy;
    private long fz;
    private long fA;
    private long fB;
    private long fC;
    public static boolean fD;
    
    public d(final a j, final int n, final int cd, final int ds, final String k, final long n2, final Thread bt, final Rectangle rectangle) {
        final boolean dj = p.dJ;
        this.a = 0;
        this.b = 0;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = true;
        this.g = true;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = true;
        this.o = true;
        this.q = 0;
        this.r = 0;
        this.s = 3;
        this.t = 12;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 255;
        this.y = 255;
        this.z = 0;
        this.A = false;
        this.B = false;
        this.C = 10;
        this.D = new long[10];
        this.E = 0;
        this.I = 1.0f;
        this.L = null;
        this.M = null;
        this.O = bu("M\u0005@");
        this.P = bu("Y\u0014[$WF\u0014V&yF\u0010Q-PPS[.T");
        this.W = 0;
        this.ba = -1L;
        this.bg = false;
        this.bi = true;
        this.bj = false;
        this.bm = false;
        this.bn = false;
        this.bt = null;
        this.bu = bu("\u0005L\nr\r\u0000K\u000fy\u0000t?{\u0005|s");
        this.bv = null;
        this.bA = Color.black;
        this.bB = false;
        this.bC = true;
        this.bJ = -1;
        this.bK = bu("j\"_'Aj\u001fg\u001e");
        this.bL = bu("j\"_'Aj\u0011Z\u001ef");
        this.bP = 200;
        this.bQ = 16;
        this.bR = 12;
        this.bS = bu("Y\u0012Y%P[\u001a\u0016&PS");
        this.cs = false;
        this.ct = true;
        this.cx = null;
        this.cC = bu("\u0005M\b");
        this.dn = true;
        this.du = null;
        this.dv = null;
        this.dw = bu("Y\u0012Y%P[\u001a\u0016 L");
        this.dD = null;
        this.dE = null;
        this.dF = null;
        this.dG = null;
        this.dL = true;
        this.dM = false;
        this.dN = false;
        this.dO = false;
        this.dP = false;
        this.dQ = false;
        this.dR = true;
        this.dT = 83;
        this.dU = 115;
        this.dV = 77;
        this.dW = 109;
        this.ed = Color.blue;
        this.ee = Color.white;
        this.ef = Color.white;
        this.ei = null;
        this.ek = 12;
        this.el = 1;
        this.ep = bu("}\u0018T7\\A\u0014[ ");
        this.eq = Color.white;
        this.er = null;
        this.es = null;
        this.et = false;
        this.eu = false;
        this.ez = bu("}\u0018T7\\A\u0014[ ");
        this.eB = 25;
        this.eC = 1;
        this.eD = Color.white;
        this.eE = null;
        this.eF = null;
        this.eG = bu("}\u0018T7\\A\u0014[ ");
        this.eH = 25;
        this.eI = 1;
        this.eJ = Color.white;
        this.fl = 1;
        this.fm = 1;
        this.ft = -1;
        this.fu = -1;
        this.bg = false;
        this.e = false;
        this.bM = i.a(n, 1, Integer.MAX_VALUE);
        this.cD = cd;
        this.dS = ds;
        this.J = j;
        this.K = k;
        this.bt = bt;
        this.bk = false;
        this.bl = false;
        this.bo = 0;
        this.bp = 0;
        this.p = 0;
        this.bf = i.a(n2, 1L, 200L);
        this.Q = -1;
        this.bN = this.T;
        this.bO = this.U;
        this.cu = false;
        this.cv = false;
        this.j = (f.h != null);
        Label_0826: {
            if (rectangle == null) {
                final Rectangle bounds = this.J.bounds();
                this.R = 0;
                this.S = 0;
                this.T = bounds.width;
                this.U = bounds.height;
                if (!dj) {
                    break Label_0826;
                }
            }
            this.R = rectangle.x;
            this.S = rectangle.y;
            this.T = rectangle.width;
            this.U = rectangle.height;
        }
        Container container = this.J.getParent();
        while (true) {
            Label_0847: {
                if (!dj) {
                    break Label_0847;
                }
                container = container.getParent();
            }
            if (container == null || container instanceof Frame) {
                this.bv = (Frame)container;
                this.bP = i.a(this.bP, 1, this.T - 8);
                this.J.requestFocus();
                this.A();
                this.br();
                this.b();
                this.i();
                this.r();
                this.K();
                this.bp();
                this.bt();
                this.bz();
                this.bG();
                this.U();
                this.Z(this.r);
                this.m = (f.a(bu("_\u001cN \u0017C\u0018V%VGSM3U")).indexOf(bu("\u001b\u0010Q\"KZ\u000eW'M\u001b")) > 0);
                this.c(bu("}\u0018T7\\A\u0014[ "), 25, 1, Color.white);
                try {
                    final String lowerCase;
                    if ((lowerCase = System.getProperty(bu("Z\u000e\u0016/XX\u0018")).toLowerCase()).startsWith(bu("B\u0014V")) && !lowerCase.startsWith(bu("B\u0014V%VB\u000e\u0018/M")) && !lowerCase.startsWith(bu("B\u0014V%VB\u000e\u0018s\t\u0005M")) && !lowerCase.startsWith(bu("B\u0014V%VB\u000e\u00189I"))) {
                        (this.ei = this.J.getAudioClip(this.S(), bu("F\u0012M/]FRZ-X[\u0016\u0016 L"))).loop();
                    }
                }
                catch (Exception ex) {}
                this.e = true;
                return;
            }
            continue;
        }
    }
    
    public void a() {
        this.e = false;
        this.bH();
        this.bu();
        this.bq();
        this.s();
        this.c();
        this.j();
        this.L();
        this.bs();
        this.C();
        this.J(64);
        if (this.ei != null) {
            this.ei.stop();
        }
        this.H(this.K + bu("\u0015\u0015Y2\u0019S\u0014V(J]\u0018\\aN\\\tPasp:\u0018\u0017") + this.bd());
    }
    
    private void b() {
        final boolean dj = p.dJ;
        this.bT = new boolean[3];
        this.bW = new Image[this.bM];
        this.bY = new String[this.bM];
        this.bZ = new int[this.bM];
        this.ca = new int[this.bM];
        this.cr = new boolean[this.bM];
        this.cb = new int[this.bM];
        this.cc = new int[this.bM];
        this.cf = -1;
        this.cj = 0;
        this.cy = this.J.getGraphics();
        this.ck = 0;
        this.ce = new int[this.bM];
        this.ci = 0;
        this.cA = Color.black;
        this.cm = -1;
        this.cl = 0;
        this.cB = new Graphics[this.bM];
        this.cw = false;
        this.bU = new boolean[this.bM];
        int n = 0;
        while (true) {
            while (true) {
                Label_0213: {
                    if (!dj) {
                        break Label_0213;
                    }
                    this.bW[n] = null;
                    this.bY[n] = null;
                    this.cr[n] = false;
                    final int[] ce = this.ce;
                    final int n2 = n;
                    ce[n2] = n2 + 1;
                    final d d = this;
                    d.cB[n] = null;
                    ++n;
                }
                if (n < this.bM) {
                    continue;
                }
                break;
            }
            final d d = this;
            if (!dj) {
                if (this.bM > 0) {
                    this.ce[this.bM - 1] = -1;
                }
                (this.bV = new Vector(this.bM)).insertElementAt("#", 0);
                this.cq = this.a(this.bK, this.T, this.U);
                this.bX = this.bW[this.cq];
                this.cz = this.cB[this.cq];
                return;
            }
            continue;
        }
    }
    
    private void c() {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0019: {
                    if (!dj) {
                        break Label_0019;
                    }
                    this.a(n, false);
                    ++n;
                }
                if (n < this.bM) {
                    continue;
                }
                break;
            }
            this.bT = null;
            this.bW = null;
            this.bY = null;
            this.bZ = null;
            this.ca = null;
            this.cr = null;
            this.cb = null;
            this.cc = null;
            this.ce = null;
            this.cB = null;
            this.bU = null;
            this.cy.dispose();
            this.cy = null;
            this.bV.removeAllElements();
            this.bV = null;
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    public void a(final boolean cw) {
        this.cw = cw;
    }
    
    public void a(final int ck, final boolean b) {
        if (this.bW[ck] != null) {
            if (this.cB[ck] != null) {
                this.cB[ck].dispose();
                this.cB[ck] = null;
            }
            this.bW[ck].flush();
            this.bW[ck] = null;
            this.cr[ck] = false;
            if (!b) {
                if (this.fc && this.bY[ck].equals(this.fh)) {
                    this.fc = false;
                    this.bA();
                }
                this.bY[ck] = null;
                this.ce[ck] = ((this.ck == -1) ? -1 : this.ck);
                this.ck = ck;
            }
        }
    }
    
    public int d() {
        if (this.ck == -1) {
            return -1;
        }
        final int ck = this.ck;
        this.ck = this.ce[this.ck];
        return ck;
    }
    
    private int a(final String s, final int n, final int n2) {
        final int d;
        if ((d = this.d()) >= 0) {
            this.bW[d] = this.J.createImage(n, n2);
            this.bY[d] = s;
            this.bZ[d] = n;
            this.ca[d] = n2;
            this.cr[d] = false;
            this.cB[d] = this.bW[d].getGraphics();
        }
        return d;
    }
    
    public Point a(final String s) {
        final int d;
        if ((d = this.d(s)) != -1) {
            return new Point(this.bZ[d], this.ca[d]);
        }
        if (this.c) {
            this.J(bu("`\u0013S/VB\u0013\u0018\u0006\u007fm]\u0002a") + s);
        }
        return null;
    }
    
    public void b(final String s) {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0042: {
                    if (!dj) {
                        break Label_0042;
                    }
                    Label_0039: {
                        if (this.bW[n] == null) {
                            break Label_0039;
                        }
                        this.bY[n].startsWith(s);
                        final boolean c;
                        if (c) {
                            this.a(n, false);
                            return;
                        }
                    }
                    ++n;
                }
                if (n < this.bM) {
                    continue;
                }
                break;
            }
            final boolean c = this.c;
            if (!dj) {
                if (c) {
                    this.J(bu("v\u001cVfM\u0015\u0019]-\\A\u0018\u0018\u0006\u007fm]\u0002a") + s);
                }
                return;
            }
            continue;
        }
    }
    
    public void a(final String s, final int n, final int n2, final String s2, final int n3, final int n4, final int n5, final int n6) {
        this.a(this.cB[this.d(s)], this.a(this.bW[this.d(s2)], n3, n4, n5, n6), n, n2);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.a(this.cB[n], this.bW[n2], n3, n4);
        if (n == this.cf) {
            this.n = true;
        }
    }
    
    public void a(final String s, final String s2, final int n, final int n2) {
        this.a(this.d(s), s2, n, n2);
    }
    
    public void a(final int n, final String s, final int n2, final int n3) {
        final Graphics create = this.cB[n].create();
        Label_0082: {
            if (s.charAt(0) == '@') {
                create.setColor(this.eD);
                create.setFont(this.eE);
                create.drawString(s.substring(1), n2, n3 + this.Q());
                if (!p.dJ) {
                    break Label_0082;
                }
            }
            this.a(create, this.bW[this.d(s)], n2, n3);
        }
        create.dispose();
    }
    
    public void b(final String s, final int n, final int n2) {
        this.a(this.cq, s, n, n2);
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.a(this.cq, n, n2, n3);
    }
    
    public void a(final String s, final String s2, final int n) {
        this.a(this.d(s), s2, n);
    }
    
    public void a(final int n, final String s, final int n2) {
        final Graphics create = this.cB[n].create();
        Label_0115: {
            if (s.charAt(0) == '@') {
                create.setColor(this.eD);
                create.setFont(this.eE);
                create.drawString(s.substring(1), this.bZ[n] / 2 - this.eF.stringWidth(s.substring(1)) / 2, n2);
                if (!p.dJ) {
                    break Label_0115;
                }
            }
            this.a(create, this.bW[this.d(s)], (this.bZ[n] - this.bZ[this.d(s)]) / 2, n2);
        }
        create.dispose();
    }
    
    public int a(final String s, final int n, final int n2, final Color color) {
        final int a;
        if ((a = this.a(s, n, n2)) >= 0) {
            this.a(this.bW[a], color);
            return a;
        }
        if (this.c) {
            this.J(bu("v\u001cVfM\u0015\u001eJ$XA\u0018\u0018\u0006\u007fm]\u0002a") + s + "(" + n + "," + n2 + ")");
        }
        return -1;
    }
    
    public int c(final String s, final int n, final int n2) {
        return this.a(s, n, n2, Color.black);
    }
    
    private void a(final String s, final String s2, final boolean b) {
        final boolean dj = p.dJ;
        final int d = this.d(s2);
        final int n = this.bZ[d];
        final int n2 = this.ca[d];
        int n3 = 0;
        int n4 = b ? (n * (n2 - 1)) : (n - 1);
        if (this.a(s, n, n2) >= 0) {
            final int[] b2 = this.b(this.bW[d], 0, 0, n, n2);
            final int[] array = new int[n * n2];
            if (b) {
                int n5 = 0;
            Label_0129_Outer:
                while (true) {
                    Label_0156: {
                        if (!dj) {
                            break Label_0156;
                        }
                        int n6 = 0;
                        while (true) {
                            while (true) {
                                Label_0132: {
                                    if (!dj) {
                                        break Label_0132;
                                    }
                                    array[n3++] = b2[n4++];
                                    ++n6;
                                }
                                if (n6 < n) {
                                    continue Label_0129_Outer;
                                }
                                break;
                            }
                            ++n5;
                            if (dj) {
                                continue;
                            }
                            break;
                        }
                        n4 -= n << 1;
                    }
                    if (n5 < n2) {
                        continue;
                    }
                    break;
                }
            }
            else {
                int n7 = 0;
            Label_0200_Outer:
                while (true) {
                    Label_0227: {
                        if (!dj) {
                            break Label_0227;
                        }
                        int n8 = 0;
                        while (true) {
                            while (true) {
                                Label_0203: {
                                    if (!dj) {
                                        break Label_0203;
                                    }
                                    array[n3++] = b2[n4--];
                                    ++n8;
                                }
                                if (n8 < n) {
                                    continue Label_0200_Outer;
                                }
                                break;
                            }
                            ++n7;
                            if (dj) {
                                continue;
                            }
                            break;
                        }
                        n4 += n << 1;
                    }
                    if (n7 < n2) {
                        continue;
                    }
                    break;
                }
            }
            this.a(s, array, n, n2);
        }
    }
    
    public void a(final String s, final String s2) {
        this.a(s, s2, false);
    }
    
    public void b(final String s, final String s2) {
        this.a(s, s2, true);
    }
    
    public void a(final String s, final int n, final boolean b) {
        final boolean dj = p.dJ;
        final int d = this.d(s);
        final int n2 = this.bZ[d];
        final int n3 = this.ca[d];
        final int n4 = n2 * n3;
        final int n5 = i.a(n, 0, 255) << 24;
        final int[] b2 = this.b(this.bW[d], 0, 0, n2, n3);
        while (true) {
            Label_0158: {
                while (true) {
                    int n6 = 0;
                    Label_0151: {
                        if (b) {
                            n6 = 0;
                            while (true) {
                                Label_0113: {
                                    if (!dj) {
                                        break Label_0113;
                                    }
                                    final int n7;
                                    if (((n7 = b2[n6]) & 0xFF000000) != 0x0) {
                                        b2[n6] = ((n7 & 0xFFFFFF) | n5);
                                    }
                                    ++n6;
                                }
                                if (n6 >= n4) {
                                    break Label_0158;
                                }
                                continue;
                            }
                        }
                        else {
                            n6 = 0;
                            if (!dj) {
                                break Label_0151;
                            }
                        }
                        b2[n6] = ((b2[n6] & 0xFFFFFF) | n5);
                        ++n6;
                    }
                    if (n6 < n4) {
                        continue;
                    }
                    break;
                }
            }
            this.a(s, b2, n2, n3);
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    public void c(final String s, final String s2) {
        final int d = this.d(s2);
        final int a;
        if ((a = this.a(s, this.bZ[d], this.ca[d])) >= 0) {
            this.a(a, d, 0, 0);
        }
    }
    
    public void a(final int n, final int[] array, final int n2, final int n3) {
        this.a(n, true);
        this.bW[n] = this.J.createImage(new MemoryImageSource(n2, n3, array, 0, n2));
        this.bZ[n] = n2;
        this.ca[n] = n3;
    }
    
    public void a(final String s, final int[] array, final int n, final int n2) {
        this.a(this.d(s), array, n, n2);
    }
    
    public Image a(final Image image, final int n, final int n2, final int n3, final int n4) {
        Image image2 = null;
        final int[] array = new int[n3 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n3);
        try {
            if (pixelGrabber.grabPixels()) {
                image2 = this.J.createImage(new MemoryImageSource(n3, n4, array, 0, n3));
            }
        }
        catch (InterruptedException ex) {
            if (this.c) {
                this.J(bu("r;`\u001eZG\u0018Y5\\|\u0010Y&\\s\u000fW,\u0019\u000f]") + ex.toString());
            }
        }
        return image2;
    }
    
    public Image a(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n2 * n5 + n;
        Image image = null;
        try {
            image = this.J.createImage(new MemoryImageSource(n3, n4, array, n6, n5));
        }
        catch (Exception ex) {
            if (this.c) {
                this.G(bu("r;`\u001eZG\u0018Y5\\|\u0010Y&\\s\u000fW,\u0019\u000f]") + ex.toString());
            }
        }
        return image;
    }
    
    public int a(final String s, final String s2, final int n, final int n2, final int n3, final int n4) {
        final int d;
        if ((d = this.d()) >= 0) {
            this.bW[d] = this.a(this.bW[this.d(s2)], n, n2, n3, n4);
            this.bY[d] = s;
            this.bZ[d] = n3;
            this.ca[d] = n4;
            this.cr[d] = false;
            if (!p.dJ) {
                return d;
            }
        }
        if (this.c) {
            this.J(bu("v\u001cVfM\u0015\u001eJ$XA\u0018\u0018{\u0019") + s);
        }
        return d;
    }
    
    private int a(final String s, final int[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int d;
        if ((d = this.d()) >= 0) {
            this.bW[d] = this.a(array, n, n2, n3, n4, n5);
            this.bY[d] = s;
            this.bZ[d] = n3;
            this.ca[d] = n4;
            this.cr[d] = false;
            if (!p.dJ) {
                return d;
            }
        }
        if (this.c) {
            this.J(bu("v\u001cVfM\u0015\u001eJ$XA\u0018\u0018{\u0019") + s);
        }
        return d;
    }
    
    public int a(final String s, final Image image, final int n, final int n2, final int n3, final int n4) {
        final int[] array = new int[n3 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n3);
        int a = -1;
        try {
            if (pixelGrabber.grabPixels()) {
                a = this.a(s, array, n, n2, n3, n4, n3);
            }
        }
        catch (InterruptedException ex) {
            if (this.c) {
                this.J(bu("r;`\u001eZG\u0018Y5\\{\u0018O\bTT\u001a]\u0007KZ\u0010\u0018{\u0019") + ex.toString());
            }
        }
        return a;
    }
    
    public Image a(final String s, final int n, final int n2, final int n3, final int n4) {
        return this.a(this.bW[this.d(s)], n, n2, n3, n4);
    }
    
    public int[] b(final Image image, final int n, final int n2, final int n3, final int n4) {
        final int[] array = new int[n3 * n4];
        final PixelGrabber pixelGrabber;
        (pixelGrabber = new PixelGrabber(image.getSource(), n, n2, n3, n4, array, 0, n3)).setColorModel(ColorModel.getRGBdefault());
        try {
            if (pixelGrabber.grabPixels()) {
                return array;
            }
        }
        catch (InterruptedException ex) {
            if (this.c) {
                this.J(ex.toString());
            }
        }
        return null;
    }
    
    public int[] b(final String s, final int n, final int n2, final int n3, final int n4) {
        return this.b(this.bW[this.d(s)], n, n2, n3, n4);
    }
    
    public int[] a(final int n) {
        return this.b(this.bW[n], 0, 0, this.bZ[n], this.ca[n]);
    }
    
    public int[] c(final String s) {
        return this.a(this.d(s));
    }
    
    public int d(final String s, final int n, final int n2) {
        final int[] array = { 0 };
        final int d = this.d(s);
        int n3 = 0;
        if (n < 0 || n2 < 0 || n >= this.bZ[d] || n2 >= this.ca[d]) {
            return 0;
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(this.bW[d], n, n2, 1, 1, array, 0, 1);
        try {
            if (pixelGrabber.grabPixels()) {
                n3 = array[0];
            }
        }
        catch (Exception ex) {}
        return n3;
    }
    
    public int a(final String s, final boolean b) {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0039: {
                    if (!dj) {
                        break Label_0039;
                    }
                    Label_0036: {
                        if (this.bW[n] == null) {
                            break Label_0036;
                        }
                        this.bY[n].equals(s);
                        final boolean c;
                        if (c) {
                            return n;
                        }
                    }
                    ++n;
                }
                if (n < this.bM) {
                    continue;
                }
                break;
            }
            try {
                final boolean c = this.c;
                if (dj) {
                    continue;
                }
                if (c) {
                    throw new Exception(bu("v\u001cVfM\u0015\u001bQ/]\u0015:~\u0019\u0019\u000f]") + s);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            break;
        }
        if (b) {
            return 0;
        }
        return -1;
    }
    
    public int d(final String s) {
        return this.a(s, true);
    }
    
    public void a(final Color ca) {
        this.cf = -3;
        this.cA = ca;
    }
    
    public void e(final String s) {
        final boolean dj = p.dJ;
        if (s.equalsIgnoreCase(bu("w1y\u0002r"))) {
            this.cf = -1;
            if (!dj) {
                return;
            }
        }
        if (s.equalsIgnoreCase(bu("{2v\u0004"))) {
            this.cf = -2;
            if (!dj) {
                return;
            }
        }
        this.cf = this.d(s);
        this.cg = this.bZ[this.cf];
        this.ch = this.ca[this.cf];
    }
    
    public void b(final int cf) {
        this.cf = cf;
        this.cg = this.bZ[cf];
        this.ch = this.ca[cf];
        this.n = true;
    }
    
    public void c(final int ci) {
        this.ci = ci;
    }
    
    public void d(int n) {
        while (true) {
            Label_0013: {
                if (!p.dJ) {
                    break Label_0013;
                }
                this.f();
            }
            if (--n < 0) {
                return;
            }
            continue;
        }
    }
    
    public void e() {
        this.n = true;
    }
    
    public boolean f() {
        final boolean dj = p.dJ;
        if (!this.e) {
            return false;
        }
        long y = 0L;
        Label_0176: {
            if (!this.A) {
                if (this.d) {
                    Label_0121: {
                        switch (this.E) {
                            case 0: {
                                final long bb = this.bb;
                                y = this.Y();
                                if (this.be <= y) {
                                    this.W();
                                }
                                if (this.be < y && this.p < this.bf / 2L) {
                                    return false;
                                }
                                this.p = 0;
                                if (dj) {
                                    break Label_0121;
                                }
                                break Label_0176;
                            }
                            case 1: {
                                if (this.H < 256) {
                                    this.W();
                                    this.H += this.F;
                                    return false;
                                }
                                this.H &= 0xFF;
                                if (dj) {
                                    break;
                                }
                                break Label_0176;
                            }
                        }
                    }
                    if (!dj) {
                        break Label_0176;
                    }
                }
                this.W();
            }
        }
        this.J.c();
        boolean n = this.n;
        this.n = false;
        if (!this.f && this.g) {
            n = true;
        }
        Label_0264: {
            if (i.b(this.V(), this.fz, this.fA)) {
                if (this.ft == this.fu) {
                    break Label_0264;
                }
                n = true;
                if (!dj) {
                    break Label_0264;
                }
            }
            if (this.fu != -1) {
                n = true;
                this.fu = -1;
            }
        }
        if (this.V() < this.dX && this.dR) {
            n = true;
        }
        Label_0316: {
            if (n) {
                ++this.a;
                if (!dj) {
                    break Label_0316;
                }
            }
            ++this.b;
        }
        if (n) {
            this.bg = true;
            Label_1226: {
                if (!this.f && this.g) {
                    final int n2 = (this.U - this.eF.getHeight()) / 2 - 50;
                    this.a(this.bX, Color.black);
                    this.E(25);
                    this.cz.setColor(this.eD);
                    this.cz.setFont(this.eE);
                    this.g(bu("a\u0015Q2\u0019\\\u000e\u0018") + this.O, n2);
                    this.g(bu("\\\u0013K5XY\u0011Y5PZ\u0013\u0018._"), n2 + 25);
                    this.g("'" + this.K + "'", n2 + 50);
                    this.E(15);
                    this.cz.setFont(this.eE);
                    this.g(bu("e\u0011] JP][.WA\u001c[5\u0019\u000f]"), n2 + 80);
                    this.g(this.P, n2 + 100);
                    if (!dj) {
                        break Label_1226;
                    }
                }
                Label_0651: {
                    if (this.ct) {
                        Label_0633: {
                            Label_0586: {
                                switch (this.cf) {
                                    case 1: {
                                        if (dj) {
                                            break Label_0586;
                                        }
                                        break Label_0633;
                                    }
                                    case 0: {
                                        this.a(this.bX, this.cA);
                                        if (dj) {
                                            break Label_0586;
                                        }
                                        break Label_0633;
                                    }
                                    case 2: {
                                        this.a(this.bX, Color.black);
                                        if (dj) {
                                            break;
                                        }
                                        break Label_0633;
                                    }
                                }
                            }
                            this.a(this.bW[this.cf], this.bZ[this.cf], this.ca[this.cf]);
                        }
                        this.g();
                        if (!dj) {
                            break Label_0651;
                        }
                    }
                    this.e(-1);
                    this.g();
                }
                if (this.V() < this.dX && this.dR) {
                    final Font font = this.cz.getFont();
                    final Color color = this.cz.getColor();
                    try {
                        this.E(this.bR);
                        this.D(1);
                        this.cz.setColor(this.ef);
                        final int n3 = this.K(this.dy) + 4;
                        final int n4 = this.P() + 4;
                        final int n5 = this.dN ? (this.eg - n3 / 2) : ((this.T - n3) / 2);
                        final int n6 = this.dN ? this.eh : (this.U - n4 - 8);
                        this.a(this.bX, this.ed, n5, n6, n3, n4);
                        final int n7 = this.ee.getRGB() | 0xFF000000;
                        this.a(this.cz, n5 - 1, n6 - 1, n3 + 1, n4 + 1, n7, false);
                        this.a(this.cz, n5 - 2, n6 - 2, n3 + 3, n4 + 3, n7, false);
                        this.j(this.dy, n5 + n3 / 2, n6 + this.eF.getHeight());
                    }
                    catch (Throwable t) {
                        this.H(bu("r;`\u001eKP\u0013\\$K\u0015G\u00185QG\u0018Oa\u0003\u0015") + t.toString());
                    }
                    finally {
                        this.a(font, color);
                    }
                }
                this.b(this.cz);
                this.a(this.cz);
                if (this.c && this.ev) {
                    final String string = "" + this.H() + bu("\u0015R\u0018") + this.en + bu("\u0015&") + 1 + "." + 0 + "." + 0 + "]";
                    final int stringWidth = this.es.stringWidth(string);
                    final int height = this.es.getHeight();
                    final int n8 = this.T - stringWidth - 8;
                    final int n9 = this.U - height - 8;
                    this.a(this.bX, Color.green, n8, n9, stringWidth + 8, height + 8);
                    this.a(this.bX, Color.black, n8 + 2, n9 + 2, stringWidth + 4, height + 4);
                    this.cz.setColor(Color.green);
                    this.cz.drawString(string, n8 + 4, n9 + 4 + this.es.getMaxAscent());
                }
            }
            this.bg = false;
        }
        long n10 = 0L;
        if (this.E == 1) {
            ++n10;
        }
        if (this.E == 0 && this.d) {
            n10 += i.a(this.X + (y + 1L) * this.Y - this.bb, 0L, Long.MAX_VALUE);
        }
        if (n) {
            this.b(true);
        }
        if (this.c && this.eu) {
            final long v = this.V();
            this.ex[this.em] = v - this.ey;
            this.ey = v;
            this.em = (this.em + 1) % 50;
        }
        if (!this.J.c) {
            Thread.yield();
        }
        if (this.bn && !this.d) {
            n10 += 5L;
        }
        if (this.E == 1 && this.d) {
            this.bh();
        }
        if (this.m && this.q++ % 512 == 0) {
            this.bn();
        }
        this.a(n10);
        return true;
    }
    
    private void g() {
        final boolean dj = p.dJ;
        int cq = this.cQ;
        while (true) {
            Label_1138: {
                if (!this.fc) {
                    break Label_1138;
                }
                this.p(this.fh);
                if (!dj) {
                    break Label_1138;
                }
                Label_1131: {
                    if (this.d(cq, 256) && !this.d(cq, 1024)) {
                        Label_0389: {
                            switch (this.cU[cq]) {
                                case 0: {
                                    final int n = this.cP[cq];
                                    this.a(this.cz, this.bW[n], this.cL[cq] - this.bZ[n] / 2, this.cM[cq] - this.ca[n] / 2);
                                    if (dj) {
                                        break Label_0389;
                                    }
                                    break Label_1131;
                                }
                                case 5: {
                                    final int n2 = this.dc[cq];
                                    final int n3 = this.cP[cq];
                                    int n4 = this.bZ[n3];
                                    int n5 = this.ca[n3];
                                    int n6 = 0;
                                    int n7 = 0;
                                    int n8 = this.bZ[n3];
                                    int n9 = this.ca[n3];
                                    if (i.c(n2, 1)) {
                                        final float n10 = this.ds[cq];
                                        n4 *= (int)n10;
                                        n5 *= (int)n10;
                                    }
                                    if (i.c(n2, 2)) {
                                        final int n11 = n6 ^ n8;
                                        n8 ^= n11;
                                        n6 = (n11 ^ n8);
                                    }
                                    if (i.c(n2, 4)) {
                                        final int n12 = n7 ^ n9;
                                        n9 ^= n12;
                                        n7 = (n12 ^ n9);
                                    }
                                    this.a(this.cz, this.bW[n3], this.cL[cq] - n4 / 2, this.cM[cq] - n5 / 2, this.cL[cq] + n4 / 2, this.cM[cq] + n5 / 2, n6, n7, n8, n9);
                                    if (dj) {
                                        break Label_0389;
                                    }
                                    break Label_1131;
                                }
                                case 1: {
                                    this.cz.setColor(this.eD);
                                    try {
                                        this.cz.setFont(this.eE);
                                        this.cz.drawString(this.cF[cq], this.cL[cq] - this.K(this.cF[cq]) / 2, this.cM[cq] + (this.bj ? 0 : (this.Q() - this.eF.getHeight() / 2)));
                                        break Label_1131;
                                    }
                                    catch (Throwable t) {
                                        if (!dj) {
                                            break Label_1131;
                                        }
                                    }
                                }
                                case 6: {
                                    final e e;
                                    final int a = (e = this.dt[cq]).a;
                                    int n13 = 0;
                                    while (true) {
                                        Label_0557: {
                                            if (!dj) {
                                                break Label_0557;
                                            }
                                            if (!e.h[n13]) {
                                                this.a(this.cz, this.bW[e.e[n13]], e.f[n13], e.g[n13]);
                                            }
                                            ++n13;
                                        }
                                        if (n13 >= a) {
                                            break Label_1131;
                                        }
                                        continue;
                                    }
                                }
                                case 2: {
                                    this.cz.setColor(this.dk[cq]);
                                    int n14 = this.db[cq];
                                    final double n15;
                                    final double sin = Math.sin(n15 = this.dr[cq] * 3.141592653589793 / 180.0);
                                    final double cos = Math.cos(n15);
                                    final double n16 = this.ds[cq];
                                    int n17 = 0;
                                    while (true) {
                                        Label_0839: {
                                            if (!dj) {
                                                break Label_0839;
                                            }
                                            final int n18 = this.cX[n14++];
                                            final int n19 = this.cX[n14++];
                                            final double n20 = this.cV[n18 << 1];
                                            final double n21 = this.cV[(n18 << 1) + 1];
                                            final double n22 = this.cV[n19 << 1];
                                            final double n23 = this.cV[(n19 << 1) + 1];
                                            this.cz.drawLine((int)(n20 * cos * n16 - n21 * sin * n16 + this.cL[cq]), (int)(n21 * cos * n16 + n20 * sin * n16 + this.cM[cq]), (int)(n22 * cos * n16 - n23 * sin * n16 + this.cL[cq]), (int)(n23 * cos * n16 + n22 * sin * n16 + this.cM[cq]));
                                            ++n17;
                                        }
                                        if (n17 >= this.da[cq]) {
                                            break Label_1131;
                                        }
                                        continue;
                                    }
                                }
                                case 4: {
                                    this.cz.setColor(this.dk[cq]);
                                    int n24 = this.db[cq];
                                    final double n25;
                                    final double sin2 = Math.sin(n25 = this.dr[cq] * 3.141592653589793 / 180.0);
                                    final double cos2 = Math.cos(n25);
                                    final double n26 = this.ds[cq];
                                    int n27 = 0;
                                    while (true) {
                                        Label_1021: {
                                            if (!dj) {
                                                break Label_1021;
                                            }
                                            final double n28 = this.cV[n24++];
                                            final double n29 = this.cV[n24++];
                                            final double n30 = n28 * cos2 * n26 - n29 * sin2 * n26 + this.cL[cq];
                                            final double n31 = n29 * cos2 * n26 + n28 * sin2 * n26 + this.cM[cq];
                                            this.cz.drawLine((int)n30, (int)n31, (int)n30, (int)n31);
                                            ++n27;
                                        }
                                        if (n27 >= this.da[cq]) {
                                            break Label_1131;
                                        }
                                        continue;
                                    }
                                }
                                case 7: {
                                    final j j = this.di[cq];
                                    this.a(this.cz, j.g, this.cL[cq] - j.b / 2, this.cM[cq] - j.c / 2, this.J);
                                    if (dj) {
                                        break;
                                    }
                                    break Label_1131;
                                }
                            }
                        }
                        if (this.c) {
                            this.J(bu("r;`\u001eKP\u0013\\$K\u001dT\u0018{\u0019`\u0013K4IE\u0012J5\\Q]K1K\\\t]aML\r]a\u0003\u0015") + this.cU[cq]);
                        }
                    }
                }
                cq = this.cS[cq];
            }
            if (cq == -1) {
                return;
            }
            continue;
        }
    }
    
    private void a(final Image image, final int n, final int n2) {
        final boolean dj = p.dJ;
        Label_0258: {
            switch (this.ci) {
                case 2: {
                    this.a(this.cz, image, 0, 0, this.T, this.U);
                    if (dj) {
                        break Label_0258;
                    }
                    break;
                }
                case 3: {
                    int n3 = 0;
                Label_0097_Outer:
                    while (true) {
                        Label_0119: {
                            if (!dj) {
                                break Label_0119;
                            }
                            int n4 = 0;
                            int n5 = 0;
                            while (true) {
                                while (true) {
                                    Label_0099: {
                                        if (!dj) {
                                            break Label_0099;
                                        }
                                        this.a(this.cz, image, n4, n3);
                                        n4 = n5;
                                    }
                                    if (n4 < this.T) {
                                        continue Label_0097_Outer;
                                    }
                                    break;
                                }
                                n5 = n3 + n2;
                                if (dj) {
                                    continue;
                                }
                                break;
                            }
                            n3 = n5;
                        }
                        if (n3 >= this.U) {
                            return;
                        }
                        continue;
                    }
                }
                case 1: {
                    final int n6 = (this.T - n) / 2;
                    final int n7;
                    if ((n7 = (this.U - n2) / 2) > 0) {
                        this.a(this.bX, this.cA, 0, 0, this.T, n7);
                        this.a(this.bX, this.cA, 0, n7 + n2, this.T, n7);
                    }
                    if (n6 > 0) {
                        this.a(this.bX, this.cA, 0, n7, n6, n2);
                        this.a(this.bX, this.cA, n6 + n, n7, n6, n2);
                    }
                    this.a(this.cz, image, n6, n7);
                    if (dj) {
                        break Label_0258;
                    }
                    break;
                }
                case 0: {
                    final int n8 = this.T - n;
                    final int n9 = this.U - n2;
                    if (n8 > 0) {
                        this.a(this.bX, this.cA, n, 0, n8, n2);
                    }
                    if (n9 > 0) {
                        this.a(this.bX, this.cA, 0, n2, this.T, n9);
                    }
                    this.a(this.cz, image, 0, 0);
                    break;
                }
            }
        }
    }
    
    public synchronized void e(final int n) {
        final boolean dj = p.dJ;
        if (this.bi) {
            try {
                final int n2 = this.cu ? this.co : ((this.T - this.bN) / 2);
                final int n3;
                int cn = ((n3 = (this.cu ? this.cp : ((this.U - this.bO) / 2))) == 0) ? (this.U - (this.eF.getHeight() << 1)) : (this.U - n3 + this.eF.getHeight() + 8);
                this.E(this.bR);
                this.cz.setFont(this.eE);
                this.cz.setColor(this.eD);
                if (this.cu) {
                    cn = this.cp + this.bO + (this.eF.getHeight() << 1);
                }
                if (this.cv) {
                    cn = this.cn;
                }
                final boolean c = i.c(n, 1);
                if (this.cs) {
                    int n4 = 0;
                    while (true) {
                        Label_0287: {
                            if (!dj) {
                                break Label_0287;
                            }
                            if (this.bW[n4] != null && this.bY[n4] != null && this.bY[n4].equalsIgnoreCase(this.bS)) {
                                if ((n2 > 0 || n3 > 0) && c) {
                                    this.a(this.bX, this.bA);
                                }
                                if (i.c(n, 2)) {
                                    this.a(this.cz, this.bW[n4], n2, n3);
                                }
                            }
                            ++n4;
                        }
                        if (n4 < this.bM) {
                            continue;
                        }
                        break;
                    }
                }
                else if (c) {
                    this.a(this.bX, this.bA);
                }
                Label_0710: {
                    if (i.c(n, 4)) {
                        if (this.bB) {
                            if (this.Q <= 0) {
                                break Label_0710;
                            }
                            final float a = i.a(this.Q, 0.0f, 100.0f, -this.bH, 0.0f);
                            this.a(this.bJ, this.bD, 0, 0);
                            this.a(this.bJ, this.bE, (int)a, 0);
                            this.a(this.cz, this.bW[this.bJ], this.bF, this.bG);
                            if (!dj) {
                                break Label_0710;
                            }
                        }
                        if (this.cm != -1 && this.Q >= 0) {
                            final int n5 = (this.T - this.bP) / 2;
                            final int n6 = cn - 12;
                            final int n7 = this.Q * this.bP / 100;
                            final int n8 = this.bP - n7;
                            this.a(this.cz, this.bW[this.cm], n5, n6);
                            if (n8 > 0) {
                                this.a(this.bX, Color.black, n5 + n7, n6, n8, this.bQ);
                            }
                            this.a(this.cz, n5 - 1, n6 - 1, this.bP + 1, this.bQ + 1, -1, false);
                            this.a(this.cz, n5 - 2, n6 - 2, this.bP + 3, this.bQ + 3, -1, false);
                        }
                        if (this.Q >= 0) {
                            final String substring = this.bw.substring(0, this.bq + 1);
                            this.bq = (this.bq + 1) % this.bw.length();
                            this.g((this.o ? substring : "") + " " + this.bx + " " + (this.o ? substring : ""), cn);
                            if (!dj) {
                                break Label_0710;
                            }
                        }
                        this.g(this.L, cn);
                    }
                }
                if (this.M != null && i.c(n, 8)) {
                    final StringTokenizer stringTokenizer;
                    final int countTokens = (stringTokenizer = new StringTokenizer(this.M, "|")).countTokens();
                    this.E(10);
                    this.cz.setFont(this.eE);
                    int n9 = 0;
                    while (true) {
                        Label_0800: {
                            if (!dj) {
                                break Label_0800;
                            }
                            this.g(stringTokenizer.nextToken(), cn + this.eF.getHeight() + n9 * 11);
                            ++n9;
                        }
                        if (n9 < countTokens) {
                            continue;
                        }
                        break;
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public synchronized void b(final boolean b) {
        final boolean dj = p.dJ;
        if (this.e && !this.bg) {
            Label_0393: {
                if (this.cl != 0 && b && !this.A) {
                    final int n;
                    int a = i.a(-(n = this.cl / 2), n);
                    int a2 = i.a(-n, n);
                    if (a == 0) {
                        ++a;
                    }
                    if (a2 == 0) {
                        ++a2;
                    }
                    this.cz.setColor(Color.black);
                    this.cz.setPaintMode();
                    if (a < 0) {
                        if (a2 < 0) {
                            this.cz.copyArea(-a, -a2, this.T + a, this.U + a2, a, a2);
                            this.cz.fillRect(this.T + a, 0, -a, this.U + a2);
                            this.cz.fillRect(0, this.U + a2, this.T, -a2);
                            if (!dj) {
                                break Label_0393;
                            }
                        }
                        if (a2 <= 0) {
                            break Label_0393;
                        }
                        this.cz.copyArea(-a, 0, this.T + a, this.U + a2, a, a2);
                        this.cz.fillRect(0, 0, this.T, a2);
                        this.cz.fillRect(this.T + a, a2, -a, this.U - a2);
                        if (!dj) {
                            break Label_0393;
                        }
                    }
                    if (a > 0) {
                        if (a2 < 0) {
                            this.cz.copyArea(0, -a2, this.T - a, this.U + a2, a, a2);
                            this.cz.fillRect(0, 0, a, this.U);
                            this.cz.fillRect(a, this.U + a2, this.T, -a2);
                            if (!dj) {
                                break Label_0393;
                            }
                        }
                        if (a2 > 0) {
                            this.cz.copyArea(0, 0, this.T - a, this.U - a2, a, a2);
                            this.cz.fillRect(0, 0, this.T, a2);
                            this.cz.fillRect(0, a2, a, this.U - a2);
                        }
                    }
                }
            }
            this.a(this.cy, this.bX, this.R, this.S);
        }
    }
    
    public synchronized void h() {
        this.b(false);
    }
    
    private void a(final Image image, final Color color, final int n, final int n2, final int n3, final int n4) {
        try {
            final Graphics graphics;
            (graphics = image.getGraphics()).setColor(color);
            graphics.setPaintMode();
            graphics.fillRect(n, n2, n3, n4);
            graphics.dispose();
        }
        catch (Exception ex) {
            this.a(this, bu("r;`\u001e_\\\u0011T\u0000KP\u001c"), ex);
        }
    }
    
    public void a(final int n, final Color color, final int n2, final int n3, final int n4, final int n5) {
        this.a(this.bW[n], color, n2, n3, n4, n5);
    }
    
    public void a(final String s, final Color color, final int n, final int n2, final int n3, final int n4) {
        this.a(this.bW[this.d(s)], color, n, n2, n3, n4);
    }
    
    public void a(final Color color, final int n, final int n2, final int n3, final int n4) {
        this.a(this.bX, color, n, n2, n3, n4);
    }
    
    private void a(final Image image, final Color color) {
        if (image != null) {
            this.a(image, color, 0, 0, image.getWidth(null), image.getHeight(null));
        }
    }
    
    public void a(final String s, final Color color) {
        this.a(this.bW[this.d(s)], color);
    }
    
    public void a(final int n, final Color color) {
        this.a(this.bW[n], color);
    }
    
    public void b(final Color color) {
        this.a(this.bX, color);
    }
    
    public void a(final int n, final int n2, final float n3) {
        final boolean dj = p.dJ;
        final int[] a;
        if ((a = this.a(n)) != null) {
            final int n4 = this.bZ[n];
            final int n5 = this.ca[n];
            final int n6 = n4 * n5;
            final float n7 = (n2 & 0xFF0000) >> 16;
            final float n8 = (n2 & 0xFF00) >> 8;
            final float n9 = n2 & 0xFF;
            int n10 = 0;
            while (true) {
                Label_0228: {
                    if (!dj) {
                        break Label_0228;
                    }
                    final int n12;
                    int n13 = 0;
                    int n14 = 0;
                    int n15 = 0;
                    Label_0199: {
                        final float n11;
                        if ((n11 = ((n12 = a[n10]) & 0xFF)) > n3) {
                            n13 = (int)i.a(n11, n3 + 1.0f, 255.0f, n7, 255.0f);
                            n14 = (int)i.a(n11, n3 + 1.0f, 255.0f, n8, 255.0f);
                            n15 = (int)i.a(n11, n3 + 1.0f, 255.0f, n9, 255.0f);
                            if (!dj) {
                                break Label_0199;
                            }
                        }
                        n13 = (int)i.a(n11, 0.0f, n3, 0.0f, n7);
                        n14 = (int)i.a(n11, 0.0f, n3, 0.0f, n8);
                        n15 = (int)i.a(n11, 0.0f, n3, 0.0f, n9);
                    }
                    a[n10] = ((n12 & 0xFF000000) | n13 << 16 | n14 << 8 | n15);
                    ++n10;
                }
                if (n10 < n6) {
                    continue;
                }
                break;
            }
            this.a(n, a, n4, n5);
        }
    }
    
    public void a(final String s, final int n, final float n2) {
        this.a(this.d(s), n, n2);
    }
    
    public void a(final int n, final int n2) {
        final int[] a;
        if ((a = this.a(n)) != null) {
            final int n3 = this.bZ[n];
            final int n4 = this.ca[n];
            final int n5 = n3 * n4;
            int n6 = 0;
            while (true) {
                Label_0053: {
                    if (!p.dJ) {
                        break Label_0053;
                    }
                    final int[] array = a;
                    final int n7 = n6;
                    array[n7] &= n2;
                    ++n6;
                }
                if (n6 < n5) {
                    continue;
                }
                break;
            }
            this.a(n, a, n3, n4);
        }
    }
    
    public void a(final String s, final int n) {
        this.a(this.d(s), n);
    }
    
    public void a(final String s, final int n, final int n2, final int n3) {
        final int[] c = this.c(s);
        final int d = this.d(s);
        if (c != null) {
            final int n4 = this.bZ[d];
            final int n5 = this.ca[d];
            final int n6 = n4 * n5;
            final int n7 = n << 16;
            final int n8 = n2 << 8;
            int n9 = 0;
            while (true) {
                Label_0213: {
                    if (!p.dJ) {
                        break Label_0213;
                    }
                    final int n11;
                    final int n10 = (n11 = c[n9]) & 0xFF0000;
                    final int n12 = n11 & 0xFF00;
                    final int n13 = n11 & 0xFF;
                    int n14 = n10 + n7;
                    int n15 = n12 + n8;
                    int n16 = n13 + n3;
                    if (n14 > 16711680) {
                        n14 = 16711680;
                    }
                    if (n15 > 65280) {
                        n15 = 65280;
                    }
                    if (n16 > 255) {
                        n16 = 255;
                    }
                    if (n14 < 65536) {
                        n14 = 0;
                    }
                    if (n15 < 256) {
                        n15 = 0;
                    }
                    if (n16 < 1) {
                        n16 = 0;
                    }
                    c[n9] = (n11 & 0xFF000000) + n14 + n15 + n16;
                    ++n9;
                }
                if (n9 < n6) {
                    continue;
                }
                break;
            }
            this.a(d, c, n4, n5);
        }
    }
    
    public void f(final String s) {
        int n = 0;
        while (true) {
            Label_0040: {
                if (!p.dJ) {
                    break Label_0040;
                }
                if (this.bW[n] != null && this.bY[n].equals(s)) {
                    this.a(n, false);
                    return;
                }
                ++n;
            }
            if (n >= this.bM) {
                return;
            }
            continue;
        }
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        graphics.setColor(new Color(n5));
        if (b) {
            graphics.fillRect(n, n2, n3, n4);
            if (!p.dJ) {
                return;
            }
        }
        graphics.drawRect(n, n2, n3, n4);
    }
    
    public void f(final int cl) {
        this.cl = cl;
    }
    
    public void g(final int n) {
        if (n >= 0 && n < this.bM && this.bW[n] != null) {
            this.a(n, 0, 0, this.bZ[n], this.ca[n]);
        }
    }
    
    public void g(final String s) {
        this.g(this.d(s));
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.cB[n].setClip(n2, n3, n4, n5);
        }
        catch (Exception ex) {
            if (this.c) {
                this.J(bu("r;`\u001eJP\t{-PE/]\"M\u001dT\u0018'X\\\u0011]%\u0019\u000f]") + ex.toString());
            }
        }
    }
    
    public void c(final String s, final int n, final int n2, final int n3, final int n4) {
        this.a(this.d(s), n, n2, n3, n4);
    }
    
    public void a(final Graphics graphics, final Image image, final int n, final int n2, final a a) {
        graphics.drawImage(image, n, n2, a);
    }
    
    public void a(final Graphics graphics, final Image image, final int n, final int n2) {
        graphics.drawImage(image, n, n2, null);
    }
    
    private void a(final Graphics graphics, final Image image, final int n, final int n2, final int n3, final int n4) {
        graphics.drawImage(image, n, n2, n3, n4, null);
    }
    
    private void a(final Graphics graphics, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        graphics.drawImage(image, n, n2, n3, n4, n5, n6, n7, n8, null);
    }
    
    public void h(final int n) {
        final int n2 = this.bZ[n];
        final int n3 = this.ca[n];
        this.a(n, this.b(this.bW[n], 0, 0, n2, n3), n2, n3);
    }
    
    public void h(final String s) {
        final int a;
        if ((a = this.a(s, false)) > -1) {
            this.h(a);
        }
    }
    
    private void i() {
        final boolean dj = p.dJ;
        this.cE = new String[this.cD];
        this.cF = new String[this.cD];
        this.cL = new int[this.cD];
        this.cM = new int[this.cD];
        this.cU = new int[this.cD];
        this.cP = new int[this.cD];
        this.dr = new float[this.cD];
        this.cG = new String[this.cD];
        this.dp = new float[this.cD];
        this.dq = new float[this.cD];
        this.cO = new int[this.cD];
        this.cN = new int[this.cD];
        this.dl = -1;
        this.dm = -1;
        this.cQ = -1;
        this.cR = -1;
        this.cS = new int[this.cD];
        this.cT = new int[this.cD];
        this.cV = new int[256];
        this.cW = new int[256];
        this.cX = new int[128];
        this.cY = 0;
        this.cZ = 0;
        this.da = new int[this.cD];
        this.db = new int[this.cD];
        this.dk = new Color[this.cD];
        this.ds = new float[this.cD];
        this.dc = new int[this.cD];
        this.dd = 0;
        this.de = new int[this.cD];
        this.dt = new e[this.cD];
        this.dj = new Rectangle[this.cD];
        this.di = new j[this.cD];
        this.cH = new String[this.cD];
        this.cI = new String[this.cD];
        this.cJ = new String[this.cD];
        this.df = new int[this.cD];
        this.dg = new int[this.cD];
        this.dh = new int[this.cD];
        this.cK = new String[this.cD];
        int n = 0;
        while (true) {
            while (true) {
                Label_0444: {
                    if (!dj) {
                        break Label_0444;
                    }
                    this.cE[n] = null;
                    this.cF[n] = null;
                    this.dt[n] = null;
                    this.dj[n] = null;
                    this.cH[n] = null;
                    this.cK[n] = null;
                    final int[] de = this.de;
                    final int n2 = n;
                    de[n2] = n2 + 1;
                    final d d = this;
                    d.df[n] = 0;
                    ++n;
                }
                if (n < this.cD) {
                    continue;
                }
                break;
            }
            final d d = this;
            if (!dj) {
                if (this.cD > 0) {
                    this.de[this.cD - 1] = -1;
                }
                return;
            }
            continue;
        }
    }
    
    private void j() {
        this.cE = null;
        this.cF = null;
        this.cL = null;
        this.cM = null;
        this.cU = null;
        this.cP = null;
        this.dr = null;
        this.cG = null;
        this.dp = null;
        this.dq = null;
        this.cO = null;
        this.cS = null;
        this.cT = null;
        this.cV = null;
        this.cW = null;
        this.cX = null;
        this.da = null;
        this.db = null;
        this.dk = null;
        this.ds = null;
        this.cN = null;
        this.dc = null;
        this.de = null;
        this.dt = null;
        this.dj = null;
        this.di = null;
        this.cH = null;
        this.cI = null;
        this.cJ = null;
        this.df = null;
        this.dg = null;
        this.dh = null;
        this.cK = null;
    }
    
    public void b(final int n, final int n2) {
        if (n >= 0 && n < this.cD) {
            final int[] df = this.df;
            df[n] |= n2;
        }
    }
    
    public void a(final int n, final int n2, final boolean b) {
        if (b) {
            this.b(n, n2);
            if (!p.dJ) {
                return;
            }
        }
        this.c(n, n2);
    }
    
    public void c(final int n, final int n2) {
        if (n >= 0 && n < this.cD) {
            final int[] df = this.df;
            df[n] &= ~n2;
        }
    }
    
    public boolean d(final int n, final int n2) {
        return n >= 0 && n < this.cD && (this.df[n] & n2) != 0x0;
    }
    
    private int a(final String s, final int n, final int n2, final boolean b, final int n3) {
        final int dd;
        Label_0135: {
            if ((dd = this.dd) >= 0) {
                this.dd = this.de[this.dd];
                this.cE[dd] = s;
                this.cL[dd] = n;
                this.cM[dd] = n2;
                this.df[dd] = 0;
                this.a(dd, 1, b);
                this.b(dd, 256);
                this.cU[dd] = n3;
                this.cJ[dd] = null;
                this.n(dd);
                if (!p.dJ) {
                    break Label_0135;
                }
            }
            if (this.c) {
                this.J(bu("`\u0013Y#UP]L.\u0019V\u000f] MP]k\u0011k|)}a\u0003\u0015") + s);
            }
        }
        this.n = true;
        return dd;
    }
    
    public int a(final String s, final int n, final int n2, final String s2, final boolean b, final int n3) {
        final boolean dj = p.dJ;
        final int a;
        if ((a = this.a(s, n, n2, b, n3)) >= 0) {
            Label_0191: {
                switch (n3) {
                    case 1: {
                        this.cF[a] = s2.substring(1);
                        if (dj) {
                            break Label_0191;
                        }
                        return a;
                    }
                    case 6: {
                        if (dj) {
                            break Label_0191;
                        }
                        return a;
                    }
                    case 2: {
                        this.dr[a] = 0.0f;
                        this.dk[a] = Color.white;
                        this.ds[a] = 1.0f;
                        if (dj) {
                            break Label_0191;
                        }
                        return a;
                    }
                    case 4: {
                        this.dr[a] = 0.0f;
                        this.dk[a] = Color.white;
                        this.ds[a] = 1.0f;
                        if (dj) {
                            break Label_0191;
                        }
                        return a;
                    }
                    case 5: {
                        this.cP[a] = this.d(s2.substring(1));
                        this.ds[a] = 1.0f;
                        this.dc[a] = 0;
                        if (dj) {
                            break Label_0191;
                        }
                        return a;
                    }
                    case 7: {
                        if (dj) {
                            break;
                        }
                        return a;
                    }
                }
            }
            this.cP[a] = this.d(s2);
            return a;
        }
        return -1;
    }
    
    public int a(final String s, final int n, final int n2, final boolean b, final j j) {
        final int a;
        if ((a = this.a(s, n, n2, b, 7)) >= 0) {
            this.di[a] = j;
        }
        return a;
    }
    
    public int a(final String s, final int n, final int n2, final int n3, final boolean b, final int n4) {
        final int a;
        if ((a = this.a(s, n, n2, b, n4)) >= 0) {
            this.cP[a] = n3;
        }
        return a;
    }
    
    public int a(final String s, final int n, final int n2, final String s2, final boolean b) {
        this.I(bu("f-j\bmp\"[3\\T\t]i\u0010\u0015\u0014Ka]P\rJ$ZT\t]%\u0019\\\u0013\u00185Q\\\u000e\u0018'VG\u0010\u0018`"));
        switch (s2.charAt(0)) {
            case '@': {
                return this.a(s, n, n2, s2, b, 1);
            }
            case '~': {
                return this.a(s, n, n2, s2, b, 6);
            }
            case '#': {
                return this.a(s, n, n2, s2, b, 4);
            }
            case '$': {
                return this.a(s, n, n2, s2, b, 2);
            }
            case '*': {
                return this.a(s, n, n2, s2, b, 5);
            }
            default: {
                return this.a(s, n, n2, s2, b, 0);
            }
        }
    }
    
    public int a(final String s, final e e, final int n) {
        final int a;
        if ((a = this.a(s, 0, 0, false, 6)) >= 0) {
            this.dt[a] = e;
            this.i(a, n);
        }
        return a;
    }
    
    public int a(final String s, final e e, final int n, final int n2, final int n3) {
        final int a;
        if ((a = this.a(s, n, n2, false, 0)) >= 0) {
            this.a(a, e, true);
            this.i(a, n3);
        }
        return a;
    }
    
    public int e(final int n, final int n2) {
        final boolean dj = p.dJ;
        int n3 = 0;
        if (n >= 0 && n2 >= n && n2 < this.cD) {
            int dd = n;
            int n4 = 0;
            while (true) {
                while (true) {
                    Label_0159: {
                        if (!dj) {
                            break Label_0159;
                        }
                        if (this.ft == dd) {
                            this.bI();
                        }
                        Label_0093: {
                            if (this.cE[dd] == null) {
                                break Label_0093;
                            }
                            this.de[dd] = ((this.dd == -1) ? -1 : this.dd);
                            this.dd = dd;
                            final d d = this;
                            d.o(n4);
                            ++n3;
                        }
                        this.cE[dd] = null;
                        this.cF[dd] = null;
                        this.dt[dd] = null;
                        this.cH[dd] = null;
                        this.cJ[dd] = null;
                        this.df[dd] = 0;
                        if (this.dl == dd) {
                            this.bf("");
                        }
                        ++dd;
                    }
                    if (dd <= n2) {
                        continue;
                    }
                    break;
                }
                final d d = this;
                n4 = 1;
                if (dj) {
                    continue;
                }
                break;
            }
            this.n = (n4 != 0);
        }
        return n3;
    }
    
    public int i(final int n) {
        return this.e(n, n);
    }
    
    public int i(final String s) {
        return this.i(this.y(s));
    }
    
    public int j(final int n) {
        return this.e(n, this.cD - 1);
    }
    
    public int k() {
        return this.e(0, this.cD - 1);
    }
    
    public void f(final int n, final int n2) {
        this.dc[n] = n2;
    }
    
    public void b(final String s, final int n) {
        this.f(this.y(s), n);
    }
    
    public boolean g(final int n, final int n2) {
        final Rectangle rectangle = this.dj[n];
        final Rectangle rectangle2 = this.dj[n2];
        final int n3 = this.cL[n] + rectangle.x;
        final int n4 = this.cL[n2] + rectangle2.x;
        if (n3 + rectangle.width < n4) {
            return false;
        }
        if (n3 > n4 + rectangle2.width) {
            return false;
        }
        final int n5 = this.cM[n] + rectangle.y;
        final int n6 = this.cM[n2] + rectangle2.y;
        return n5 + rectangle.height >= n6 && n5 <= n6 + rectangle2.height;
    }
    
    public boolean h(final int n, final int n2) {
        final int n3 = this.cP[n];
        final int n4 = this.cP[n2];
        final int n5 = this.cL[n];
        final int n6 = this.cL[n2];
        final int n7 = this.bZ[n3] / 2;
        final int n8 = this.bZ[n4] / 2;
        if (n5 + n7 < n6 - n8) {
            return false;
        }
        if (n5 - n7 > n6 + n8) {
            return false;
        }
        final int n9 = this.cM[n];
        final int n10 = this.cM[n2];
        final int n11 = this.ca[n3] / 2;
        final int n12 = this.ca[n4] / 2;
        return n9 + n11 >= n10 - n12 && n9 - n11 <= n10 + n12;
    }
    
    public boolean d(final String s, final String s2) {
        final int y;
        final int y2;
        return (y = this.y(s)) >= 0 && (y2 = this.y(s2)) >= 0 && this.h(y, y2);
    }
    
    public void a(final int n, final Rectangle rectangle) {
        if (n >= 0 && n < this.cD) {
            this.dj[n] = rectangle;
        }
    }
    
    public void a(final String s, final Rectangle rectangle) {
        this.a(this.y(s), rectangle);
    }
    
    public void k(final int n) {
        if (n >= 0 && n < this.cD) {
            this.dj[n] = null;
        }
    }
    
    public void j(final String s) {
        this.k(this.y(s));
    }
    
    public void e(final String s, final String s2) {
        final int y;
        if ((y = this.y(s)) != -1) {
            this.cE[y] = s2;
        }
    }
    
    public void a(final int n, final float n2) {
        if (n >= 0 && n < this.cD && this.ds[n] != n2) {
            this.ds[n] = n2;
            this.n = true;
        }
    }
    
    public void a(final String s, final float n) {
        this.a(this.y(s), n);
    }
    
    public int k(final String s) {
        final boolean dj = p.dJ;
        final StringTokenizer stringTokenizer;
        final int countTokens = (stringTokenizer = new StringTokenizer(s, ",")).countTokens();
        int n = -1;
        if (countTokens + this.cY <= 256) {
            int n2 = 0;
            while (true) {
                while (true) {
                    Label_0070: {
                        if (!dj) {
                            break Label_0070;
                        }
                        final d d = this;
                        d.cV[this.cY++] = Integer.parseInt(stringTokenizer.nextToken());
                        ++n2;
                    }
                    if (n2 < countTokens) {
                        continue;
                    }
                    break;
                }
                final d d = this;
                if (dj) {
                    continue;
                }
                break;
            }
            n = this.cY - countTokens;
        }
        return n;
    }
    
    public int c(final String s, final int n) {
        final boolean dj = p.dJ;
        final StringTokenizer stringTokenizer;
        final int countTokens = (stringTokenizer = new StringTokenizer(s, ",")).countTokens();
        int n2 = -1;
        if (countTokens + this.cZ <= 128) {
            int n3 = 0;
            while (true) {
                while (true) {
                    Label_0076: {
                        if (!dj) {
                            break Label_0076;
                        }
                        final d d = this;
                        d.cX[this.cZ++] = Integer.parseInt(stringTokenizer.nextToken()) + n / 2;
                        ++n3;
                    }
                    if (n3 < countTokens) {
                        continue;
                    }
                    break;
                }
                final d d = this;
                if (dj) {
                    continue;
                }
                break;
            }
            n2 = this.cZ - countTokens;
        }
        return n2;
    }
    
    public void b(final String s, final Color color) {
        this.dk[this.y(s)] = color;
    }
    
    public void e(final String s, final int n, final int n2) {
        final int y = this.y(s);
        this.da[y] = n;
        this.db[y] = n2;
    }
    
    public void f(final String s, final int n, final int n2) {
        final int y = this.y(s);
        this.da[y] = n;
        this.db[y] = n2;
    }
    
    public void b(final int n, final float n2) {
        this.dr[n] = (this.dr[n] + n2 + 360.0f) % 360.0f;
    }
    
    public void b(final String s, final float n) {
        this.b(this.y(s), n);
    }
    
    public void c(final String s, final float n) {
        this.dr[this.y(s)] = n;
    }
    
    public void l() {
        this.dn = true;
    }
    
    public void m() {
        this.dn = false;
    }
    
    public boolean l(final String s) {
        final int y;
        return (y = this.y(s)) != -1 && this.cL[y] >= 0 && this.cL[y] < this.T && this.cM[y] >= 0 && this.cM[y] < this.U;
    }
    
    public boolean l(final int n) {
        final int n2 = this.cP[n];
        final int n3 = this.cL[n];
        final int n4 = this.bZ[n2] / 2;
        if (n3 + n4 <= 0) {
            return false;
        }
        if (n3 - n4 >= this.T) {
            return false;
        }
        final int n5 = this.cM[n];
        final int n6 = this.ca[n2] / 2;
        return n5 + n6 > 0 && n5 - n6 < this.U;
    }
    
    public boolean m(final String s) {
        return this.l(this.y(s));
    }
    
    public int m(final int n) {
        if (n == -1) {
            return -1;
        }
        return (int)Math.floor(this.dp[n]);
    }
    
    public int n(final String s) {
        return this.m(this.y(s));
    }
    
    private void n(final int n) {
        final boolean dj = p.dJ;
        if ((n < 0 || n < this.cD) && this.cE[n] != null && !this.d(n, 32)) {
            this.cN[n] = 50000;
            this.b(n, 32);
            if (this.cQ == -1) {
                this.cT[n] = -1;
                this.cS[n] = -1;
                this.cQ = n;
                this.cR = n;
                if (!dj) {
                    return;
                }
            }
            int cr = this.cR;
            int n2 = -1;
            int n3 = 0;
            int n4 = 0;
            Label_0137: {
                while (true) {
                    Label_0110: {
                        if (!dj) {
                            break Label_0110;
                        }
                        n2 = cr;
                        cr = this.cT[n2];
                    }
                    if (cr != -1) {
                        n3 = this.cN[cr];
                        n4 = 50000;
                        if (dj) {
                            break Label_0137;
                        }
                        if (n3 > n4) {
                            continue;
                        }
                    }
                    break;
                }
                final int cq = this.cQ;
            }
            if (n3 == n4) {
                this.cS[n] = this.cQ;
                this.cT[n] = -1;
                this.cT[this.cQ] = n;
                this.cQ = n;
                if (!dj) {
                    return;
                }
            }
            if (n2 == -1) {
                this.cS[n] = -1;
                this.cT[n] = this.cR;
                this.cS[this.cR] = n;
                this.cR = n;
                if (!dj) {
                    return;
                }
            }
            this.cS[n] = n2;
            this.cT[n] = this.cT[n2];
            this.cS[this.cT[n2]] = n;
            this.cT[n2] = n;
        }
    }
    
    private void o(final int n) {
        final boolean dj = p.dJ;
        if (this.cE[n] != null && this.d(n, 32)) {
            Label_0146: {
                if (this.cS[n] == -1) {
                    if (this.cT[n] == -1) {
                        this.cQ = -1;
                        if (!dj) {
                            break Label_0146;
                        }
                    }
                    this.cS[this.cT[n]] = -1;
                    this.cR = this.cT[n];
                    if (!dj) {
                        break Label_0146;
                    }
                }
                if (this.cT[n] == -1) {
                    this.cQ = this.cS[n];
                    this.cT[this.cQ] = -1;
                    if (!dj) {
                        break Label_0146;
                    }
                }
                this.cT[this.cS[n]] = this.cT[n];
                this.cS[this.cT[n]] = this.cS[n];
            }
            this.c(n, 32);
        }
    }
    
    public void p(final int cq) {
        if (cq >= 0 && cq < this.cD) {
            if (this.cQ == -1) {
                this.n(cq);
                if (!p.dJ) {
                    return;
                }
            }
            if (this.cQ != cq) {
                this.o(cq);
                this.cT[this.cQ] = cq;
                this.cS[cq] = this.cQ;
                this.cT[cq] = -1;
                this.cN[cq] = this.cN[this.cQ];
                this.b(cq, 32);
                this.cQ = cq;
            }
        }
    }
    
    public void o(final String s) {
        this.p(this.y(s));
    }
    
    public void q(final int cr) {
        if (cr >= 0 && cr < this.cD) {
            if (this.cQ == -1) {
                this.n(cr);
                if (!p.dJ) {
                    return;
                }
            }
            if (this.cR != cr) {
                this.o(cr);
                this.cS[this.cR] = cr;
                this.cT[cr] = this.cR;
                this.cS[cr] = -1;
                this.cN[cr] = this.cN[this.cR];
                this.b(cr, 32);
                this.cR = cr;
            }
        }
    }
    
    public void p(final String s) {
        this.q(this.y(s));
    }
    
    public void i(final int n, final int n2) {
        final boolean dj = p.dJ;
        final int a;
        if (this.d(n, 32) && (a = i.a(n2, 0, 100000)) != this.cN[n]) {
            this.cN[n] = a;
            if (this.cQ != n || this.cR != n) {
                int n3 = this.cT[n];
                int i = this.cS[n];
                int n4 = n;
                int n5;
                int n6;
                while (true) {
                    Label_0094: {
                        if (!dj) {
                            break Label_0094;
                        }
                        n4 = n3;
                        n3 = this.cT[n4];
                    }
                    if (n3 == -1) {
                        goto Label_0116;
                    }
                    n5 = this.cN[n3];
                    n6 = a;
                    if (!dj && n5 > n6) {
                        continue;
                    }
                    break;
                }
                if (n5 != n6) {
                    this.o(n);
                    Label_0215: {
                        if (this.cQ == n4) {
                            this.cS[n] = this.cQ;
                            this.cT[n] = -1;
                            this.cT[this.cQ] = n;
                            this.cQ = n;
                            if (!dj) {
                                break Label_0215;
                            }
                        }
                        this.cS[n] = n4;
                        this.cT[n] = this.cT[n4];
                        this.cS[this.cT[n4]] = n;
                        this.cT[n4] = n;
                    }
                    this.b(n, 32);
                    return;
                }
                int n7 = 0;
                int n8 = 0;
                Label_0261: {
                    while (i != -1) {
                        n7 = this.cN[i];
                        n8 = a;
                        if (dj || n7 >= n8) {
                            break Label_0261;
                        }
                        n4 = i;
                        i = this.cS[n4];
                    }
                    goto Label_0258;
                }
                if (n7 != n8) {
                    this.o(n);
                    Label_0357: {
                        if (this.cR == n4) {
                            this.cS[n] = -1;
                            this.cT[n] = this.cR;
                            this.cS[this.cR] = n;
                            this.cR = n;
                            if (!dj) {
                                break Label_0357;
                            }
                        }
                        this.cS[n] = this.cS[n4];
                        this.cT[n] = n4;
                        this.cT[this.cS[n4]] = n;
                        this.cS[n4] = n;
                    }
                    this.b(n, 32);
                }
            }
        }
    }
    
    public void d(final String s, final int n) {
        this.i(this.y(s), n);
    }
    
    public Point r(final int n) {
        if (n >= 0 && n < this.cD) {
            final int n2 = this.cP[n];
            return new Point(this.bZ[n2], this.ca[n2]);
        }
        return null;
    }
    
    public Point q(final String s) {
        return this.r(this.y(s));
    }
    
    public void s(final int dm) {
        if (dm >= 0 && dm < this.cD) {
            this.dm = dm;
            this.bk = true;
        }
    }
    
    public void r(final String s) {
        this.s(this.y(s));
    }
    
    public void n() {
        if (this.e) {
            this.dl = -1;
            Label_0041: {
                if (this.bn) {
                    this.Z(this.s);
                    if (!p.dJ) {
                        break Label_0041;
                    }
                }
                this.Z(this.r);
            }
            this.bf("");
            this.bI();
        }
    }
    
    public void o() {
        this.dm = -1;
        this.bk = false;
        this.bl = false;
        this.B = false;
    }
    
    public int j(final int bo, final int bp) {
        if (!this.e) {
            return -1;
        }
        this.bo = bo;
        this.bp = bp;
        this.dm = this.l(bo, bp);
        this.bk = true;
        final long v;
        if ((v = this.V()) - this.bd < 250L) {
            this.bl = true;
        }
        this.bd = v;
        if (this.dm != -1 && this.cH[this.dm] != null) {
            this.o(this.cH[this.dm], this.cI[this.dm]);
        }
        return this.dm;
    }
    
    public void k(final int n, final int n2) {
        if (n >= 0 && n < this.cD) {
            this.dg[n] = this.cP[n];
            this.dh[n] = n2;
            this.b(n, 128);
        }
    }
    
    public void a(final int n, final String s) {
        this.k(n, this.d(s));
    }
    
    public void f(final String s, final String s2) {
        this.a(this.y(s), s2);
    }
    
    public int p() {
        return this.l(this.bo, this.bp);
    }
    
    public int l(final int bo, final int bp) {
        final boolean dj = p.dJ;
        if (!this.e) {
            return -1;
        }
        final int dl = this.dl;
        this.bo = bo;
        this.bp = bp;
        this.dl = -1;
        if (this.dn) {
            int cr = this.cR;
            while (true) {
                Label_0490: {
                    if (!dj) {
                        break Label_0490;
                    }
                    Label_0481: {
                        if (this.d(cr, 1) && this.d(cr, 256)) {
                            int n = 0;
                            int n2 = 0;
                            Label_0190: {
                                switch (this.cU[cr]) {
                                    case 0: {
                                        final int n3 = this.cP[cr];
                                        n = this.bZ[n3] / 2;
                                        n2 = this.ca[n3] / 2;
                                        if (dj) {
                                            break Label_0190;
                                        }
                                        break;
                                    }
                                    case 1: {
                                        n = this.eF.stringWidth(this.cF[cr]) / 2;
                                        n2 = this.eF.getHeight() / 2;
                                        if (dj) {
                                            break Label_0190;
                                        }
                                        break;
                                    }
                                    case 5: {
                                        final int n4 = this.cP[cr];
                                        n = this.bZ[n4] / 2;
                                        n2 = this.ca[n4] / 2;
                                        if (i.c(this.dc[cr], 1)) {
                                            final float n5 = this.ds[cr];
                                            n *= (int)n5;
                                            n2 *= (int)n5;
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                            if (bo >= this.cL[cr] - n && bo <= this.cL[cr] + n && bp >= this.cM[cr] - n2 && bp <= this.cM[cr] + n2) {
                                if (this.dl != -1) {
                                    break Label_0481;
                                }
                                if (this.d(cr, 128)) {
                                    this.m(cr, this.dh[cr]);
                                }
                                this.bf(this.cJ[cr]);
                                if (!this.d(cr, 1024)) {
                                    this.Z(this.t);
                                }
                                this.dl = cr;
                                if (!this.d(cr, 512)) {
                                    break Label_0481;
                                }
                                this.fn = this.cK[cr];
                                this.ft = cr;
                                if (this.dl == dl) {
                                    break Label_0481;
                                }
                                this.fz = this.V() + this.fB;
                                this.fA = this.fz + this.fC;
                                if (!dj) {
                                    break Label_0481;
                                }
                            }
                            if (this.d(cr, 128)) {
                                this.m(cr, this.dg[cr]);
                            }
                        }
                    }
                    cr = this.cT[cr];
                }
                if (cr != -1) {
                    continue;
                }
                break;
            }
        }
        if (this.dl == -1) {
            this.n();
        }
        return this.dl;
    }
    
    public boolean t(final int n) {
        return this.d(n, 4);
    }
    
    public boolean s(final String s) {
        return this.t(this.y(s));
    }
    
    public void b(final int n, final boolean b) {
        if (this.d(n, 256) != b) {
            this.a(n, 256, b);
            this.n = true;
        }
    }
    
    public void b(final String s, final boolean b) {
        this.b(this.y(s), b);
    }
    
    public void c(final int n, final boolean b) {
        this.a(n, 1, b);
    }
    
    public void c(final String s, final boolean b) {
        this.c(this.y(s), b);
    }
    
    public void u(final int n) {
        this.b(n, 64);
    }
    
    public void t(final String s) {
        this.u(this.y(s));
    }
    
    public void c(final int n, final float n2) {
        if (n >= 0 && n < this.cD) {
            this.dq[n] = i.a(n2, 0.0f, this.cO[n]) * this.I;
        }
    }
    
    public void d(final String s, final float n) {
        this.c(this.y(s), n);
    }
    
    public void d(final int n, final float n2) {
        if (n >= 0 && n < this.cD) {
            this.dq[n] = n2 / this.bf;
        }
    }
    
    public void e(final String s, final float n) {
        this.d(this.y(s), n);
    }
    
    public void v(final int n) {
        if (n >= 0 && n < this.cD) {
            this.c(n, 2);
            this.dt[n] = null;
        }
    }
    
    public void u(final String s) {
        this.v(this.y(s));
    }
    
    public boolean a(final int n, final e e) {
        return n >= 0 && n < this.cD && this.dt[n] != null && this.dt[n] == e;
    }
    
    public void a(final int n, final e e, final boolean b) {
        if (n >= 0 && n < this.cD && e != this.dt[n]) {
            this.dt[n] = e;
            this.a(n, 16, b);
            this.b(n, 2);
            this.dp[n] = 0.0f;
            this.dq[n] = 1.0f;
            this.cO[n] = e.a;
            this.a(n, 8, e.i);
            this.c(n, 68);
            this.cP[n] = e.e[0];
        }
    }
    
    public void a(final String s, final e e, final boolean b) {
        this.a(this.y(s), e, b);
    }
    
    public void a(final String s, final e e) {
        final int y;
        if ((y = this.y(s)) >= 0 && y < this.cD) {
            this.a(y, e, false);
            this.c(y, 2);
        }
    }
    
    public void w(final int n) {
        this.b(n, 2);
    }
    
    public void v(final String s) {
        this.w(this.y(s));
    }
    
    public void x(final int n) {
        this.c(n, 2);
    }
    
    public void w(final String s) {
        this.x(this.y(s));
    }
    
    public void a(final int n, final String s, final String s2, final boolean b, final boolean b2) {
        this.I(bu("f-j\bmp\"K$Mt\u0013Q,XA\u0014W/\u0011\u001c]Q2\u0019Q\u0018H3\\V\u001cL$]\u0015\u0014VaM]\u0014Ka_Z\u000fUa\u0018"));
        if (n >= 0 && n < this.cD) {
            final StringTokenizer stringTokenizer;
            int countTokens = (stringTokenizer = new StringTokenizer(s2, ",")).countTokens();
            String s3 = "";
            if (b2) {
                countTokens /= 3;
            }
            this.a(n, 8, b2);
            this.a(n, 16, b);
            this.b(n, 2);
            this.dp[n] = 0.0f;
            this.dq[n] = 1.0f;
            this.cO[n] = countTokens;
            this.c(n, 68);
            int n2 = 0;
            while (true) {
                Label_0348: {
                    if (!p.dJ) {
                        break Label_0348;
                    }
                    final String nextToken = stringTokenizer.nextToken();
                    final String string = bu("\u0005M\b") + this.d(s + (nextToken.equals(bu("[\bT-")) ? "" : nextToken));
                    final StringBuffer append = new StringBuffer().append(s3);
                    final String s4 = string;
                    s3 = append.append(s4.substring(s4.length() - 3)).toString();
                    if (b2) {
                        final String string2 = bu("\u0005M\b") + stringTokenizer.nextToken();
                        final StringBuffer append2 = new StringBuffer().append(s3);
                        final String s5 = string2;
                        final String string3 = append2.append(s5.substring(s5.length() - 3)).toString();
                        final String string4 = bu("\u0005M\b") + stringTokenizer.nextToken();
                        final StringBuffer append3 = new StringBuffer().append(string3);
                        final String s6 = string4;
                        s3 = append3.append(s6.substring(s6.length() - 3)).toString();
                    }
                    ++n2;
                }
                if (n2 < countTokens) {
                    continue;
                }
                break;
            }
            this.cG[n] = s3;
        }
    }
    
    public void a(final String s, final String s2, final String s3, final boolean b) {
        this.a(this.y(s), s2, s3, b, false);
    }
    
    public void a(final int n, final String s, final String s2, final boolean b) {
        this.a(n, s, s2, b, true);
    }
    
    public void b(final String s, final String s2, final String s3, final boolean b) {
        this.a(this.y(s), s2, s3, b, true);
    }
    
    public void a(final int n, final e e, final int n2) {
        if (n >= 0 && n < this.cD && n2 >= 0 && n2 < this.cO[n]) {
            this.cP[n] = this.dt[n].e[n2];
        }
    }
    
    public void b(final String s, final e e, final int n) {
        this.a(this.y(s), e, n);
    }
    
    public Point y(final int n) {
        if (n >= 0 && n < this.cD) {
            return new Point(this.cL[n], this.cM[n]);
        }
        return null;
    }
    
    public Point x(final String s) {
        return this.y(this.y(s));
    }
    
    public void b(final int n, final String s) {
        if (n >= 0 && n < this.cD) {
            this.cF[n] = s;
        }
    }
    
    public void g(final String s, final String s2) {
        this.b(this.y(s), s2);
    }
    
    public void b(final int n, final int n2, final boolean b) {
        if (n >= 0 && n < this.cD) {
            if (this.cP[n] != n2) {
                this.n = true;
                this.cP[n] = n2;
            }
            if (b) {
                this.c(n, 2);
            }
        }
    }
    
    public void m(final int n, final int n2) {
        this.b(n, n2, true);
    }
    
    public void c(final int n, final String s) {
        this.m(n, this.d(s));
    }
    
    public void h(final String s, final String s2) {
        this.m(this.y(s), this.d(s2));
    }
    
    public int y(final String s) {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0037: {
                    if (!dj) {
                        break Label_0037;
                    }
                    Label_0034: {
                        if (this.cE[n] == null) {
                            break Label_0034;
                        }
                        this.cE[n].equals(s);
                        final int n2;
                        if (n2 != 0) {
                            return n;
                        }
                    }
                    ++n;
                }
                if (n < this.cD) {
                    continue;
                }
                break;
            }
            final int n2 = -1;
            if (!dj) {
                return n2;
            }
            continue;
        }
    }
    
    public int e(final String s, final int n) {
        final boolean dj = p.dJ;
        int n2 = n;
        while (true) {
            while (true) {
                Label_0039: {
                    if (!dj) {
                        break Label_0039;
                    }
                    Label_0036: {
                        if (this.cE[n2] == null) {
                            break Label_0036;
                        }
                        this.cE[n2].startsWith(s);
                        final int n3;
                        if (n3 != 0) {
                            return n2;
                        }
                    }
                    ++n2;
                }
                if (n2 < this.cD) {
                    continue;
                }
                break;
            }
            final int n3 = -1;
            if (!dj) {
                return n3;
            }
            continue;
        }
    }
    
    public int z(final String s) {
        return this.e(s, 0);
    }
    
    public void b(final int n, final int n2, final int n3) {
        if (n >= 0 && n < this.cD && (this.cL[n] != n2 || this.cM[n] != n3)) {
            this.cL[n] = n2;
            this.cM[n] = n3;
            this.n = true;
        }
    }
    
    public void g(final String s, final int n, final int n2) {
        this.b(this.y(s), n, n2);
    }
    
    public void c(final int n, final int n2, final int n3) {
        if (n >= 0 && n < this.cD) {
            final int[] cl = this.cL;
            cl[n] += n2;
            final int[] cm = this.cM;
            cm[n] += n3;
        }
    }
    
    public void h(final String s, final int n, final int n2) {
        this.c(this.y(s), n, n2);
    }
    
    private void q() {
        final boolean dj = p.dJ;
        int cq = this.cQ;
        while (true) {
            Label_0371: {
                if (!dj) {
                    break Label_0371;
                }
                if (this.d(cq, 2)) {
                    final float n2;
                    final int n = (int)Math.floor(n2 = this.dp[cq]);
                    Label_0260: {
                        if (this.d(cq, 8)) {
                            if (this.dt[cq] != null) {
                                this.cP[cq] = this.dt[cq].e[n];
                                this.cL[cq] = this.dt[cq].f[n];
                                this.cM[cq] = this.dt[cq].g[n];
                                if (!dj) {
                                    break Label_0260;
                                }
                            }
                            final int n3 = n * 3 * 3;
                            final int[] cp = this.cP;
                            final int n4 = cq;
                            final String s = this.cG[cq];
                            final int n5 = n3;
                            cp[n4] = Integer.parseInt(s.substring(n5, n5 + 3));
                            this.cL[cq] = Integer.parseInt(this.cG[cq].substring(n3 + 3, n3 + 6));
                            this.cM[cq] = Integer.parseInt(this.cG[cq].substring(n3 + 6, n3 + 9));
                            if (!dj) {
                                break Label_0260;
                            }
                        }
                        if (this.dt[cq] != null) {
                            this.b(cq, this.dt[cq].e[n], false);
                            if (!dj) {
                                break Label_0260;
                            }
                        }
                        this.b(cq, Integer.parseInt(this.cG[cq].substring(n * 3, (n + 1) * 3)), false);
                    }
                    float n6;
                    if ((n6 = n2 + this.dq[cq]) >= this.cO[cq]) {
                        Label_0351: {
                            if (this.d(cq, 64)) {
                                final int n7 = cq;
                                this.e(n7, n7);
                                if (!dj) {
                                    break Label_0351;
                                }
                            }
                            if (this.d(cq, 16)) {
                                while (n6 >= this.cO[cq]) {
                                    n6 -= this.cO[cq];
                                }
                            }
                            else {
                                n6 = this.cO[cq] - 1;
                            }
                        }
                        this.b(cq, 4);
                    }
                    this.dp[cq] = n6;
                }
                cq = this.cS[cq];
            }
            if (cq == -1) {
                return;
            }
            continue;
        }
    }
    
    public void a(final int n, final String s, final String s2) {
        if (n >= 0 && n < this.cD) {
            this.cH[n] = s;
            this.cI[n] = s2;
        }
    }
    
    public void a(final String s, final String s2, final String s3) {
        this.a(this.y(s), s2, s3);
    }
    
    public void d(final int n, final String s) {
        if (n >= 0 && n < this.cD) {
            this.cJ[n] = s;
        }
    }
    
    public void i(final String s, final String s2) {
        this.d(this.y(s), s2);
    }
    
    private void r() {
        this.dY = new AudioClip[this.dS];
        this.dZ = new String[this.dS];
        this.ea = new boolean[this.dS];
        this.eb = new boolean[this.dS];
        this.ec = new Vector(this.dS);
        this.dH = true;
        this.dI = true;
        this.dJ = true;
        this.dK = false;
        this.dx = "";
        this.dX = 0L;
        this.ec.insertElementAt("#", 0);
        int n = 0;
        while (true) {
            Label_0130: {
                if (!p.dJ) {
                    break Label_0130;
                }
                this.dY[n] = null;
                this.dZ[n] = null;
                this.ea[n] = false;
                ++n;
            }
            if (n >= this.dS) {
                return;
            }
            continue;
        }
    }
    
    private void s() {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0032: {
                    if (!dj) {
                        break Label_0032;
                    }
                    this.A(n);
                    this.dY[n] = null;
                    this.dZ[n] = null;
                    ++n;
                }
                if (n < this.dS) {
                    continue;
                }
                break;
            }
            this.dY = null;
            this.dZ = null;
            this.ea = null;
            this.eb = null;
            this.ec.removeAllElements();
            this.ec = null;
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    public boolean t() {
        return this.dI;
    }
    
    public boolean u() {
        return this.dJ;
    }
    
    public void a(final Color ef, final Color ed, final Color ee) {
        this.ef = ef;
        this.ed = ed;
        this.ee = ee;
    }
    
    public void n(final int eg, final int eh) {
        this.eg = eg;
        this.eh = eh;
        this.dN = true;
    }
    
    public int A(final String s) {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0046: {
                    if (!dj) {
                        break Label_0046;
                    }
                    Label_0043: {
                        if (this.dY[n] == null || this.dZ[n] == null) {
                            break Label_0043;
                        }
                        this.dZ[n].equals(s);
                        final int n2;
                        if (n2 != 0) {
                            return n;
                        }
                    }
                    ++n;
                }
                if (n < this.dS) {
                    continue;
                }
                break;
            }
            final int n2 = -1;
            if (!dj) {
                return n2;
            }
            continue;
        }
    }
    
    public void d(final int n, final boolean b) {
        final boolean dj = p.dJ;
        if ((!this.dH || n < 0 || n < this.dS) && this.dY[n] != null) {
            Label_0143: {
                if (b) {
                    if (this.ea[n]) {
                        break Label_0143;
                    }
                    if (this.dZ[n].equals(this.dx)) {
                        this.dY[n].loop();
                        if (!dj) {
                            break Label_0143;
                        }
                    }
                    if (this.dI) {
                        this.dY[n].loop();
                        if (!dj) {
                            break Label_0143;
                        }
                    }
                    this.eb[n] = true;
                    if (!dj) {
                        break Label_0143;
                    }
                }
                if (!this.dZ[n].equals(this.dx) && this.dI) {
                    this.dY[n].play();
                }
            }
            this.ea[n] = b;
        }
    }
    
    public void z(final int n) {
        this.d(n, false);
    }
    
    public void B(final String s) {
        this.d(s, false);
    }
    
    public void d(final String s, final boolean b) {
        this.d(this.A(s), b);
    }
    
    public void A(final int n) {
        if (n >= 0 && n < this.dS && this.dY[n] != null) {
            this.dY[n].stop();
            this.ea[n] = false;
            this.eb[n] = false;
        }
    }
    
    public void v() {
        int n = 0;
        while (true) {
            Label_0016: {
                if (!p.dJ) {
                    break Label_0016;
                }
                this.A(n);
                ++n;
            }
            if (n >= this.dS) {
                return;
            }
            continue;
        }
    }
    
    public void C(final String s) {
        this.A(this.A(s));
    }
    
    public void w() {
        int n = 0;
        while (true) {
            Label_0034: {
                if (!p.dJ) {
                    break Label_0034;
                }
                if (this.dY[n] != null && this.ea[n]) {
                    this.A(n);
                }
                ++n;
            }
            if (n >= this.dS) {
                return;
            }
            continue;
        }
    }
    
    public boolean D(final String s) {
        final int a;
        return (a = this.A(s)) >= 0 && this.ea[a];
    }
    
    public void x() {
        this.v();
        this.dH = false;
    }
    
    private void y() {
        if (!this.dx.equals("")) {
            this.d(this.dx, true);
        }
    }
    
    public void E(final String dx) {
        Label_0065: {
            if (this.O(dx)) {
                this.C(this.dx);
                if (!p.dJ) {
                    break Label_0065;
                }
            }
            if (!this.O(this.dx) && !dx.equals(this.dx)) {
                this.C(this.dx);
            }
            if (this.dJ) {
                this.d(dx, true);
            }
        }
        this.dx = dx;
    }
    
    public void c(final boolean dk) {
        this.dK = dk;
    }
    
    public void a(final boolean b, final boolean b2, final boolean b3) {
        Label_0048: {
            if (b) {
                this.dy = (b2 ? this.dF : this.dG);
                if (!p.dJ) {
                    break Label_0048;
                }
            }
            this.dy = (b2 ? this.dD : this.dE);
        }
        if (!this.k) {
            this.dX = this.V() + 2000L;
        }
    }
    
    public void a(final boolean b, final boolean b2) {
        this.a(b, b2, true);
    }
    
    public void a(final String s, final boolean b, final boolean b2) {
        this.dy = s + bu("\u0015G\u0018") + (b ? this.dB : this.dC);
        if (!this.k) {
            this.dX = this.V() + 2000L;
        }
    }
    
    public void e(final String s, final boolean b) {
        this.a(s, b, true);
    }
    
    public void z() {
        this.E("");
        this.w();
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final boolean dl) {
        this.dL = dl;
        if (dl) {
            this.dz = s;
            this.dA = s2;
            this.dB = s3;
            this.dC = s4;
            if (!p.dJ) {
                return;
            }
        }
        this.dD = s;
        this.dE = s2;
        this.dF = s3;
        this.dG = s4;
    }
    
    public void a(final String s, final String s2, final String s3, final String s4) {
        this.a(s, s2, s3, s4, false);
    }
    
    public void b(final int dv, final int dw, final int dt, final int du) {
        this.dV = dv;
        this.dW = dw;
        this.dT = dt;
        this.dU = du;
    }
    
    public void o(final int n, final int n2) {
        this.b(n, n2, n, n2);
        this.dM = true;
    }
    
    public void d(final boolean do1) {
        this.dO = do1;
    }
    
    public void e(final boolean b) {
        final boolean dj = p.dJ;
        while (true) {
            Label_0165: {
                while (true) {
                    int n = 0;
                    Label_0157: {
                        if (!(this.dI = !this.dI)) {
                            n = 0;
                            while (true) {
                                Label_0082: {
                                    if (!dj) {
                                        break Label_0082;
                                    }
                                    if (this.dY[n] != null && !this.dZ[n].equals(this.dx)) {
                                        final boolean b2 = this.ea[n];
                                        this.A(n);
                                        this.eb[n] = b2;
                                    }
                                    ++n;
                                }
                                if (n >= this.dS) {
                                    break Label_0165;
                                }
                                continue;
                            }
                        }
                        else {
                            n = 0;
                            if (!dj) {
                                break Label_0157;
                            }
                        }
                        Label_0154: {
                            if (this.dY[n] == null) {
                                break Label_0154;
                            }
                            this.dZ[n].equals(this.dx);
                            final boolean dl;
                            if (!dl && this.eb[n]) {
                                this.ea[n] = false;
                                this.d(n, true);
                                this.eb[n] = false;
                            }
                        }
                        ++n;
                    }
                    if (n < this.dS) {
                        continue;
                    }
                    break;
                }
            }
            final boolean dl = this.dL;
            if (!dj) {
                if (dl) {
                    this.a(this.dz, this.dI, b);
                    if (!dj) {
                        return;
                    }
                }
                this.a(false, this.dI, b);
                return;
            }
            continue;
        }
    }
    
    public void f(final boolean b) {
        final boolean dj = p.dJ;
        Label_0043: {
            if (!(this.dJ = !this.dJ)) {
                this.C(this.dx);
                if (!dj) {
                    break Label_0043;
                }
            }
            this.y();
        }
        if (this.dL) {
            this.a(this.dA, this.dJ, b);
            if (!dj) {
                return;
            }
        }
        this.a(true, this.dJ, b);
    }
    
    public void g(final boolean dr) {
        this.dR = dr;
    }
    
    private void A() {
        this.eo = new String[96];
        this.eu = false;
        this.ex = null;
        this.en = 0;
        this.ew = true;
        this.ev = true;
        this.ev = true;
        this.B();
        this.D();
        this.J();
    }
    
    public void B() {
        this.ej = 0;
        int n = 0;
        while (true) {
            Label_0024: {
                if (!p.dJ) {
                    break Label_0024;
                }
                this.eo[n] = "";
                ++n;
            }
            if (n >= 96) {
                return;
            }
            continue;
        }
    }
    
    private void C() {
        this.eo = null;
        this.er = null;
    }
    
    public void h(final boolean ev) {
        this.ev = ev;
    }
    
    private void D() {
        this.er = null;
        this.er = new Font(this.ep, this.el, this.ek);
        this.es = this.J.getFontMetrics(this.er);
    }
    
    public void F(final String ep) {
        this.ep = ep;
        this.D();
    }
    
    public void B(final int ek) {
        this.ek = ek;
        this.D();
    }
    
    public void C(final int el) {
        this.el = el;
        this.D();
    }
    
    public void c(final Color eq) {
        this.eq = eq;
        this.D();
    }
    
    public void G(final String s) {
        if (this.c) {
            this.J(s);
            this.f();
        }
    }
    
    public void H(final String s) {
        System.out.println(s);
    }
    
    public void I(final String s) {
        if (this.c && this.ew) {
            this.J(s);
        }
    }
    
    public void i(final boolean ew) {
        this.ew = ew;
    }
    
    public void J(final String s) {
        final boolean dj = p.dJ;
        if (this.c) {
            int n = 0;
            int length = s.length();
            int et = 0;
            while (true) {
                Label_0109: {
                    if (!dj) {
                        break Label_0109;
                    }
                    final int n2 = (et > 64) ? 64 : length;
                    final String[] eo = this.eo;
                    final int ej = this.ej;
                    final StringBuffer sb = new StringBuffer();
                    final int n3 = n;
                    eo[ej] = sb.append(s.substring(n3, n3 + n2)).append((length > 64) ? bu("\u0015\"") : "").toString();
                    this.ej = (this.ej + 1) % 96;
                    n += 64;
                    length -= n2;
                }
                if (length > 0) {
                    continue;
                }
                et = (this.et ? 1 : 0);
                if (dj) {
                    continue;
                }
                break;
            }
            if (et != 0) {
                this.H(s);
            }
        }
    }
    
    private void a(final Graphics graphics) {
        if (this.c) {
            final int height = this.es.getHeight();
            final int a = i.a(this.U / height, 1, 96);
            int n = (this.ej - a + 96) % 96;
            final int n2 = -this.es.getMaxDescent();
            graphics.setColor(this.eq);
            graphics.setFont(this.er);
            int n3 = 0;
            while (true) {
                Label_0111: {
                    if (!p.dJ) {
                        break Label_0111;
                    }
                    graphics.drawString(this.eo[n], 16, n2 + (n3 + 1) * height);
                    n = (n + 1) % 96;
                    ++n3;
                }
                if (n3 < a) {
                    continue;
                }
                break;
            }
        }
    }
    
    public boolean j(final boolean et) {
        final boolean et2 = this.et;
        this.et = et;
        return et2;
    }
    
    public void k(final boolean c) {
        this.c = c;
    }
    
    public boolean E() {
        return this.c;
    }
    
    public void F() {
        this.l(false);
    }
    
    public void l(final boolean b) {
        final boolean dj = p.dJ;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        final boolean j = this.j(true);
        int n5 = 0;
    Label_0112_Outer:
        while (true) {
            while (true) {
                Label_0115: {
                    if (!dj) {
                        break Label_0115;
                    }
                    if (this.bW[n5] != null) {
                        ++n2;
                        n4 += this.bZ[n5] * this.ca[n5];
                        if (b) {
                            this.J(bu("|0\u007f\u001a") + n5 + bu("h]\u0002a") + this.bY[n5]);
                        }
                    }
                    ++n5;
                }
                if (n5 >= this.bM) {
                    int n6 = 0;
                    if (dj) {
                        if (dj) {
                            continue;
                        }
                    }
                    int n7;
                    while (true) {
                        if (n6 >= this.cD) {
                            n7 = 0;
                            if (!dj) {
                                break;
                            }
                            if (!dj) {
                                break;
                            }
                        }
                        else if (this.cE[n6] != null) {
                            ++n;
                            if (b) {
                                this.J(bu("f-j\u001a") + n6 + bu("h]\u0002a") + this.cE[n6]);
                            }
                        }
                        ++n6;
                    }
                    while (true) {
                        if (n7 >= this.dS) {
                            this.J(bu("|0y\u0006|f]t\u000exq8|a\u0003\u0015") + n2 + "/" + this.bM + bu("\u0015U") + i.b(n2, this.bM) + bu("\u0010T"));
                            this.J(bu("e4`\u0004u\u0015>w\u0014wa]\u0002a") + n4 + bu("\u0015U") + (n4 << 2) / 1024 + bu("^]^.K\u0015N\na[\\\t\u0018\"VY\u0012M3") + ")");
                            this.J(bu("f-j\bmp.\u0018\rvt9}\u0005\u0019\u000f]") + n + "/" + this.cD + bu("\u0015U") + i.b(n, this.cD) + bu("\u0010T"));
                            this.J(bu("f2m\u000f}f]t\u000exq8|a\u0003\u0015") + n3 + "/" + this.dS + bu("\u0015U") + i.b(n3, this.dS) + bu("\u0010T"));
                            this.J(bu("\u007f;tfJ\u0015>j\u0004xa8|a\u0003\u0015") + this.du.size());
                            this.J(bu("\u007f-zfJ\u0015>j\u0004xa8|a\u0003\u0015") + this.dv.size());
                            this.j(j);
                            if (!dj) {
                                break;
                            }
                        }
                        else if (this.dY[n7] != null) {
                            ++n3;
                            if (b) {
                                this.J(bu("f3|\u001a") + n7 + bu("h]\u0002a") + this.dZ[n7]);
                            }
                        }
                        ++n7;
                    }
                    return;
                }
                break;
            }
            continue Label_0112_Outer;
        }
    }
    
    public void G() {
        this.en = 0;
    }
    
    public long H() {
        final boolean dj = p.dJ;
        long n = 0L;
        if (this.be > 0L) {
            float n4 = 0.0f;
            Label_0098: {
                if (this.eu) {
                    long n2 = 0L;
                    int n3 = 0;
                    while (true) {
                        while (true) {
                            Label_0049: {
                                if (!dj) {
                                    break Label_0049;
                                }
                                n2 += this.ex[n3];
                                ++n3;
                            }
                            if (n3 < 50) {
                                continue;
                            }
                            break;
                        }
                        n4 = n2 / 50L;
                        if (dj) {
                            continue;
                        }
                        break;
                    }
                    if (!dj) {
                        break Label_0098;
                    }
                }
                n4 = i.a(this.V() - this.ey, 0L, Long.MAX_VALUE) / this.be;
            }
            n = ((n4 == 0.0f) ? 0 : ((int)(1000.0f / n4)));
        }
        if (n > this.en) {
            this.en = (int)n;
        }
        return n;
    }
    
    public void I() {
        if (this.eu) {
            this.eu = false;
            this.ex = null;
            this.ey = this.V();
        }
    }
    
    public void J() {
        if (!this.eu) {
            this.eu = true;
            this.ex = null;
            this.ex = new long[50];
            this.ey = this.V();
            this.em = 0;
            int n = 0;
            while (true) {
                Label_0058: {
                    if (!p.dJ) {
                        break Label_0058;
                    }
                    this.ex[n] = 10000L;
                    ++n;
                }
                if (n < 50) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void a(final Object o, final String s, final Exception ex) {
        final String string = o.getClass().getName() + ((s != null) ? ("." + s + bu("\u001dT")) : "") + bu("\u0015G\u0018") + ex.toString();
        this.J(string);
        this.H(string);
    }
    
    private void K() {
        final boolean dj = p.dJ;
        try {
            this.eA = Toolkit.getDefaultToolkit().getFontList();
            int n = 0;
            while (true) {
            Label_0092:
                while (true) {
                    Label_0083: {
                        if (!dj) {
                            break Label_0083;
                        }
                        if (this.eA[n].equalsIgnoreCase(bu("}\u0018T7\\A\u0014[ "))) {
                            this.ez = this.eA[n];
                            if (!dj) {
                                break Label_0092;
                            }
                        }
                        if (this.eA[n].equalsIgnoreCase(bu("f\u001cV2jP\u000fQ'"))) {
                            this.ez = this.eA[n];
                        }
                        ++n;
                    }
                    if (n < this.eA.length) {
                        continue;
                    }
                    break;
                }
                if (dj) {
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {
            if (this.c) {
                this.J(bu("s2v\u0015fF\u0004K5\\X2H$W\u001dT\u0018{\u0019`\u0013Y#UP]L.\u0019R\u0018L\u0007V[\tt(JAU\u0011"));
            }
        }
        this.O();
    }
    
    private void L() {
        this.eE = null;
        this.eF = null;
    }
    
    public Font M() {
        return this.eE;
    }
    
    public FontMetrics N() {
        return this.eF;
    }
    
    private void O() {
        this.L();
        this.eE = null;
        this.eE = new Font(this.ez, this.eC, this.eB);
        this.eF = this.J.getFontMetrics(this.eE);
    }
    
    public void b(final String ez, final int eb, final int ec, final Color ed) {
        this.ez = ez;
        this.eB = eb;
        this.eC = ec;
        this.eD = ed;
        this.O();
        this.cz.setFont(this.eE);
        this.cz.setColor(this.eD);
    }
    
    public void a(final Font font, final Color color) {
        this.b(font.getName(), font.getSize(), font.getStyle(), color);
    }
    
    public void D(final int n) {
        this.b(this.ez, this.eB, n, this.eD);
    }
    
    public void E(final int n) {
        this.b(this.ez, n, this.eC, this.eD);
    }
    
    public void d(final Color color) {
        this.b(this.ez, this.eB, this.eC, color);
    }
    
    public int K(final String s) {
        return this.eF.stringWidth(s);
    }
    
    public int P() {
        return this.eF.getHeight();
    }
    
    public int Q() {
        return this.eF.getMaxAscent();
    }
    
    public int R() {
        return this.eF.getMaxDescent();
    }
    
    public void b(final String s, final String s2, final String s3) {
        final Point a = this.a(s);
        this.h(s);
        final int[] b = this.b(s, 0, 0, a.x, a.y);
        if (a != null) {
            int n = 0;
            int n2 = 0;
            int n3 = 0;
            final int n4 = a.x * (a.y - 1);
            final int length = s3.length();
            int n5 = b[n4];
            while (true) {
                Label_0178: {
                    if (!p.dJ) {
                        break Label_0178;
                    }
                    final int n6;
                    if ((n6 = ((n == a.x) ? (-n5) : b[n + n4])) != n5) {
                        final StringBuffer append = new StringBuffer().append(s2);
                        final int n7 = n3;
                        this.a(append.append(s3.substring(n7, n7 + 1)).toString(), b, n2, 0, n - n2, a.y - 1, a.x);
                        ++n3;
                        n2 = n;
                        n5 = n6;
                    }
                    ++n;
                }
                if (n <= a.x && n3 < length) {
                    continue;
                }
                break;
            }
        }
        this.f(s);
    }
    
    public void L(final String s) {
        this.I(bu("s2v\u0015fE\bK)jE\u000fQ5\\y\u0018L5\\G\u000el.mZ\r\u0010h\u0019\\\u000e\u0018%\\E\u000f]\"XA\u0018\\a\u0018"));
        int i = -1;
        do {
            if ((i = this.e(bu("F\u0011g") + s, i + 1)) >= 0) {
                this.q(i);
            }
        } while (i != -1);
    }
    
    public void M(final String s) {
        this.I(bu("s2v\u0015fV\u0011] Kf\rJ(MP1]5MP\u000fKi\u0010\u0015\u0014Ka]P\rJ$ZT\t]%\u0019\u0014"));
        int i = -1;
        do {
            if ((i = this.e(bu("F\u0011g") + s, i + 1)) >= 0) {
                this.i(bu("F\u0011g") + s);
            }
        } while (i != -1);
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final int n, final int n2, final boolean b, final int n3) {
        final boolean dj = p.dJ;
        this.I(bu("s2v\u0015fV\u000f] MP.H3PA\u0018t$MA\u0018J2\u0011\u001c]Q2\u0019Q\u0018H3\\V\u001cL$]\u0015\\"));
        int n4 = 0;
        final int length = s4.length();
        int n5 = n;
        int n6 = 0;
        while (true) {
            Label_0107: {
                if (!dj) {
                    break Label_0107;
                }
                final int n7 = n6;
                final String substring = s4.substring(n7, n7 + 1);
                Label_0104: {
                    if (s3.indexOf(substring) < 0) {
                        n4 += n3;
                        if (!dj) {
                            break Label_0104;
                        }
                    }
                    n4 += this.a(s2 + substring).x;
                }
                ++n6;
            }
            if (n6 < length) {
                continue;
            }
            break;
        }
        if (!b) {
            n5 -= n4 / 2;
        }
        int n8 = 0;
        while (true) {
            Label_0266: {
                if (!dj) {
                    break Label_0266;
                }
                final int n9 = n8;
                final String substring2 = s4.substring(n9, n9 + 1);
                Label_0263: {
                    if (s3.indexOf(substring2) < 0) {
                        n5 += n3;
                        if (!dj) {
                            break Label_0263;
                        }
                    }
                    final Point a = this.a(s2 + substring2);
                    this.a(bu("F\u0011g") + s, n5 + a.x / 2, n2, s2 + substring2, false);
                    n5 += a.x;
                }
                ++n8;
            }
            if (n8 >= length) {
                return;
            }
            continue;
        }
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final boolean b, final int n) {
        final boolean dj = p.dJ;
        this.I(bu("s2v\u0015fV\u000f] MP)]9M|\u0010Y&\\\u001dT\u0018(J\u0015\bV2MT\u001fT$\u0019\u0014"));
        int n2 = 0;
        int y = 0;
        int n3 = 0;
        final int length = s4.length();
        int n4 = 0;
        while (true) {
            Label_0112: {
                if (!dj) {
                    break Label_0112;
                }
                final int n5 = n4;
                final String substring = s4.substring(n5, n5 + 1);
                Label_0109: {
                    if (s3.indexOf(substring) < 0) {
                        n2 += 8;
                        if (!dj) {
                            break Label_0109;
                        }
                    }
                    final Point a = this.a(s2 + substring);
                    n2 += a.x;
                    y = a.y;
                }
                ++n4;
            }
            if (n4 < length) {
                continue;
            }
            break;
        }
        this.a(s, n2, y, new Color(n));
        int n6 = 0;
    Label_0299_Outer:
        while (true) {
            Label_0240: {
                if (!dj) {
                    break Label_0240;
                }
                final int n7 = n6;
                final String substring2 = s4.substring(n7, n7 + 1);
                Label_0237: {
                    if (s3.indexOf(substring2) < 0) {
                        n3 += 8;
                        if (!dj) {
                            break Label_0237;
                        }
                    }
                    final Point a2 = this.a(s2 + substring2);
                    this.a(s, s2 + substring2, n3, 0);
                    n3 += a2.x;
                }
                ++n6;
            }
            if (n6 >= length) {
                if (b) {
                    final int[] c = this.c(s);
                    final int n8 = n2 * y;
                    int n9 = 0;
                    while (true) {
                        while (true) {
                            Label_0302: {
                                if (!dj) {
                                    break Label_0302;
                                }
                                if ((c[n9] & 0xFFFFFF) == n) {
                                    final int[] array = c;
                                    final int n10 = n9;
                                    array[n10] &= 0xFFFFFF;
                                }
                                ++n9;
                            }
                            if (n9 < n8) {
                                continue Label_0299_Outer;
                            }
                            break;
                        }
                        this.a(s, c, n2, y);
                        if (dj) {
                            continue;
                        }
                        break;
                    }
                }
                return;
            }
            continue;
        }
    }
    
    public void F(final int fl) {
        this.fl = fl;
    }
    
    public boolean j(final String s, String string) {
        final boolean dj = p.dJ;
        final String bu = bu("{8l\u001eZT\u0011T\u0011\\G\u0011k\"K\\\rLa\u0003\u0015");
        final String bu2 = bu("]\tL1");
        final int n = 80;
        final int n2 = 0;
        boolean b = true;
        if (this.c) {
            this.H(bu + bu("P\u0013L$K"));
        }
        try {
            Label_0335: {
                switch (this.fl) {
                    case 0: {
                        if (this.c) {
                            this.H(bu + bu("@\u000eQ/^\u0015.W\"RP\tK"));
                        }
                        final Socket socket;
                        final OutputStream outputStream = (socket = new Socket(s, 80)).getOutputStream();
                        final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                        final InputStream inputStream = socket.getInputStream();
                        final DataInputStream dataInputStream = new DataInputStream(inputStream);
                        socket.setSoTimeout(20000);
                        string = bu("r8la") + string + "\n";
                        if (this.c) {
                            this.H(bu + bu("P\u0013L$K\u000f]P.JA.]3OP\u000f\u0005") + s + bu("\u0015]\u0018aZZ\u0010U WQ1Q/\\\b") + string);
                            this.H(bu + bu("F\u0012[*\\A]\u0002a") + socket);
                        }
                        dataOutputStream.writeBytes(string);
                        dataOutputStream.flush();
                        dataInputStream.readLine();
                        dataOutputStream.close();
                        dataInputStream.close();
                        outputStream.close();
                        inputStream.close();
                        socket.close();
                        if (dj) {
                            break Label_0335;
                        }
                        break;
                    }
                    case 1: {
                        final String string2 = bu2 + bu("\u000fR\u0017") + s + ":" + n + string;
                        if (this.c) {
                            this.H(bu + bu("@\u000eQ/^\u0015(j\rzZ\u0013V$ZA\u0014W/"));
                            this.H(bu + bu("Z\r]/P[\u001a\u0018\u0014ky>W/WP\u001eL(V[]L.\u0019\u000f]") + string2);
                        }
                        final URLConnection openConnection = new URL(string2).openConnection();
                        InputStreamReader inputStreamReader;
                        try {
                            inputStreamReader = new InputStreamReader(openConnection.getInputStream());
                            if (this.c) {
                                this.H(bu + bu("`/t\u0002V[\u0013]\"M\\\u0012Va\\F\tY#U\\\u000eP$]\u0015\tWa") + openConnection.toString());
                            }
                        }
                        catch (NullPointerException ex) {
                            if (this.c) {
                                this.H(bu + bu("V\u0012M-]\u0015\u0013W5\u0019P\u000eL [Y\u0014K)\u0019`/t\u0002V[\u0013]\"M\\\u0012VaMZ]") + openConnection.toString());
                            }
                            throw ex;
                        }
                        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        while (true) {
                            final String line;
                            if ((line = bufferedReader.readLine()) == null) {
                                bufferedReader.close();
                                if (!dj) {
                                    break;
                                }
                                continue;
                            }
                            else {
                                d d = this;
                                while (d.c) {
                                    d = this;
                                    if (!dj) {
                                        this.H(bu + bu("G\u0018[$PC\u0018\\a\u0003\u0015") + line);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    default: {
                        this.H(bu + bu("e8j\rfx8l\tvq\"l\u0004ja]Q2\u0019[\u0012LaZ@\u000fJ$WA\u0011AaPX\rT$TP\u0013L$]\u0014"));
                        break;
                    }
                }
            }
        }
        catch (Exception ex2) {
            if (this.c) {
                this.H(bu + bu("A\u0015J$N\u0015") + ex2.toString() + bu("\u0015\u0012H|") + n2);
                b = false;
            }
        }
        if (this.c) {
            this.H(bu + bu("P\u0005Q5\u0019\u000f]") + b);
        }
        return b;
    }
    
    public String N(final String s) {
        String s2 = s;
        if (this.O(s2) && !this.j) {
            s2 = this.X(bu("}4g\tvf)"));
            if (this.O(s2)) {
                String s3 = null;
                switch (this.fm) {
                    case 1: {
                        s3 = this.S().toString();
                        break;
                    }
                    default: {
                        s3 = this.T().toString();
                        break;
                    }
                }
                if (s3 != null) {
                    s2 = s3.substring(7, s3.indexOf("/", 7));
                }
            }
        }
        return s2;
    }
    
    public void G(final int fm) {
        this.fm = fm;
    }
    
    public URL S() {
        if (this.j) {
            return null;
        }
        return this.J.getCodeBase();
    }
    
    public URL T() {
        if (this.j) {
            return null;
        }
        return this.J.getDocumentBase();
    }
    
    public String[] k(final String s, final String s2) {
        final boolean dj = p.dJ;
        final String bu = bu("{8l\u001eZT\u0011T\u0012ZG\u0014H5x[\u0019\u007f$Mg\u0018H.WF\u0018\u0018{\u0019");
        final Vector vector = new Vector<String>();
        if (this.c) {
            this.H(bu + bu("F\u001eJ(IA3Y,\\\b") + s);
            this.H(bu + bu("P\u0012^\fXG\u0016]3\u0004") + s2);
        }
        try {
            final URL url = new URL(s);
            if (this.c) {
                this.H(bu + bu("Z\r]/P[\u001a\u0018\u0014ky>W/WP\u001eL(V[]L.\u0019\u000f]") + url.toString());
            }
            final DataInputStream dataInputStream = new DataInputStream(url.openConnection().getInputStream());
            String line = "";
            while (true) {
                Label_0220: {
                    if (!dj) {
                        break Label_0220;
                    }
                    line = dataInputStream.readLine();
                    vector.addElement(line);
                    if (this.c) {
                        this.H(bu + bu("G\u0005\u0018{\u0019") + line);
                    }
                }
                if (line != null && !line.equalsIgnoreCase(s2)) {
                    continue;
                }
                break;
            }
            dataInputStream.close();
        }
        catch (Exception ex) {
            this.H(bu + bu("A\u0015J$N\u0015G\u0018") + ex.toString());
        }
        final String[] array = new String[vector.size()];
        int n = 0;
        while (true) {
            while (true) {
                Label_0314: {
                    if (!dj) {
                        break Label_0314;
                    }
                    array[n] = vector.elementAt(n);
                    ++n;
                }
                if (n < vector.size()) {
                    continue;
                }
                break;
            }
            vector.removeAllElements();
            if (!dj) {
                return array;
            }
            continue;
        }
    }
    
    public String e(final int n, final String s) {
        final boolean dj = p.dJ;
        if (n == 0) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(n * s.length());
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0043: {
                    if (!dj) {
                        break Label_0043;
                    }
                    sb.append(s);
                    ++n2;
                }
                if (n2 < n) {
                    continue;
                }
                break;
            }
            final String string = sb.toString();
            if (!dj) {
                return string;
            }
            continue;
        }
    }
    
    public String a(final long n, final int n2, final String s) {
        final String string = this.e(n2, s) + n;
        return string.substring(string.length() - n2);
    }
    
    public boolean O(final String s) {
        return s == null || s.equals("");
    }
    
    public String l(final String s, final String s2) {
        if (this.O(s)) {
            return s2;
        }
        return s;
    }
    
    public String P(final String s) {
        final boolean dj = p.dJ;
        String s2 = "";
        if (!this.O(s)) {
            int n = 0;
            while (true) {
                Label_0105: {
                    if (!dj) {
                        break Label_0105;
                    }
                    Label_0102: {
                        final char char1;
                        if ((char1 = s.charAt(n)) == 'c' || !Character.isLetterOrDigit(char1)) {
                            s2 = s2 + "c" + this.a(char1, 4, "0");
                            if (!dj) {
                                break Label_0102;
                            }
                        }
                        s2 += char1;
                    }
                    ++n;
                }
                if (n < s.length()) {
                    continue;
                }
                break;
            }
        }
        return s2;
    }
    
    public String Q(final String s) {
        final boolean dj = p.dJ;
        String s2 = "";
        if (!this.O(s)) {
            int n = 0;
            while (true) {
                Label_0099: {
                    if (!dj) {
                        break Label_0099;
                    }
                    Label_0096: {
                        final char char1;
                        if ((char1 = s.charAt(n)) == 'c') {
                            s2 += (char)Integer.parseInt(s.substring(n + 1, n + 5));
                            n += 4;
                            if (!dj) {
                                break Label_0096;
                            }
                        }
                        s2 += char1;
                    }
                    ++n;
                }
                if (n < s.length()) {
                    continue;
                }
                break;
            }
        }
        return s2;
    }
    
    public String R(final String s) {
        String string = "";
        if (s != null) {
            int n = s.length() - 1;
            while (true) {
                Label_0049: {
                    if (!p.dJ) {
                        break Label_0049;
                    }
                    final StringBuffer append = new StringBuffer().append(string);
                    final int n2 = n;
                    string = append.append(s.substring(n2, n2 + 1)).toString();
                    --n;
                }
                if (n >= 0) {
                    continue;
                }
                break;
            }
        }
        return string;
    }
    
    public boolean S(final String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public boolean T(final String s) {
        try {
            Float.valueOf(s);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public String f(final String s, final int n) {
        final boolean dj = p.dJ;
        String string = null;
        if (!this.O(s)) {
            final StringBuffer sb = new StringBuffer(s.length());
            final char[] array = { '\0' };
            int n2 = 0;
            StringBuffer sb2;
            while (true) {
                while (true) {
                    Label_0069: {
                        if (!dj) {
                            break Label_0069;
                        }
                        array[0] = (char)(s.charAt(n2) + n);
                        sb.append(array);
                        ++n2;
                    }
                    if (n2 < s.length()) {
                        continue;
                    }
                    break;
                }
                sb2 = sb;
                if (dj) {
                    continue;
                }
                break;
            }
            string = sb2.toString();
        }
        return string;
    }
    
    public void U(final String s) {
        this.L = s;
        this.N = s;
        this.b(this.eG, this.eH, this.eI, this.eJ);
    }
    
    public void c(final String eg, final int eh, final int ei, final Color ej) {
        this.eG = eg;
        this.eH = eh;
        this.eI = ei;
        this.eJ = ej;
    }
    
    public void V(final String m) {
        this.M = m;
    }
    
    public void U() {
        this.U(bu("y\u0012Y%P[\u001a\u0018") + this.K);
    }
    
    public void m(final boolean bi) {
        this.bi = bi;
    }
    
    public void W(final String dw) {
        this.dw = dw;
    }
    
    public void a(final int u, final int v, final int w, final int x, final int y, final int z) {
        this.u = u;
        this.v = v;
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void n(final boolean o) {
        this.o = o;
    }
    
    public void e(final Color ba) {
        this.bA = ba;
    }
    
    public void i(final String bs, final int bn, final int bo) {
        this.bS = bs;
        this.bN = bn;
        this.bO = bo;
    }
    
    public void a(final boolean cu, final int co, final int cp) {
        this.cu = cu;
        this.co = co;
        this.cp = cp;
    }
    
    public void a(final boolean cv, final int cn) {
        this.cv = cv;
        this.cn = cn;
    }
    
    public void b(final String bd, final String be, final int bf, final int bg, final int bh, final int bi) {
        this.bB = true;
        this.bD = bd;
        this.bE = be;
        this.bF = bf;
        this.bG = bg;
        this.bH = bh;
        this.bI = bi;
    }
    
    public long V() {
        return System.currentTimeMillis();
    }
    
    public void a(final long n) {
        if (!this.i && n > 0L && this.bt != null) {
            if (this.J.c) {
                return;
            }
        }
        try {
            Thread.yield();
            Thread.sleep(n);
        }
        catch (Exception ex) {
            if (this.c) {
                this.J(ex.toString());
            }
        }
    }
    
    public void b(final long n) {
        final long v = this.V();
        while (true) {
            Label_0016: {
                if (!p.dJ) {
                    break Label_0016;
                }
                this.f();
            }
            if (this.V() >= v + n) {
                return;
            }
            continue;
        }
    }
    
    public String X(final String s) {
        if (this.j) {
            return null;
        }
        return this.J.getParameter(s);
    }
    
    public boolean m(final String s, final String s2) {
        final String parameter;
        return !this.j && (parameter = this.J.getParameter(s)) != null && parameter.equals(s2);
    }
    
    private void W() {
        if (this.e) {
            this.q();
            ++this.be;
            ++this.p;
        }
    }
    
    public boolean X() {
        if (this.d) {
            switch (this.E) {
                case 0: {
                    return this.be <= this.Y();
                }
                case 1: {
                    return this.H < 256;
                }
            }
        }
        return true;
    }
    
    private long Y() {
        if (this.d && this.E == 0) {
            this.bb = this.V();
            return i.a((this.bb - this.X) / this.Y, 0L, Long.MAX_VALUE);
        }
        return 0L;
    }
    
    public String b(final String s, final String s2, final int n) {
        String nextToken = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (n < stringTokenizer.countTokens()) {
            for (int i = 0; i <= n; ++i) {
                nextToken = stringTokenizer.nextToken();
            }
        }
        return nextToken;
    }
    
    public int n(final String s, final String s2) {
        return new StringTokenizer(s, s2).countTokens();
    }
    
    public int c(final String s, final String s2, final int n) {
        final String b;
        if ((b = this.b(s, s2, n)) != null) {
            return Integer.parseInt(b);
        }
        return 0;
    }
    
    public long d(final String s, final String s2, final int n) {
        final String b;
        if ((b = this.b(s, s2, n)) != null) {
            return Long.parseLong(b);
        }
        return 0L;
    }
    
    public String a(final int[] array, final int n, final int n2, final boolean b) {
        final boolean dj = p.dJ;
        final int[] array2 = new int[n2 + 256];
        int n3 = 0;
        int i = 0;
        Label_0289: {
            if (b) {
            Label_0245:
                while (true) {
                    break Label_0245;
                Block_8:
                    do {
                        int n6 = 0;
                        int n9 = 0;
                        int n10 = 0;
                    Label_0206:
                        while (true) {
                            Label_0028: {
                                break Label_0028;
                                int n4 = 0;
                                int n5 = 0;
                                int n7;
                                int n8;
                                Block_4_Outer:Block_7_Outer:Label_0155_Outer:
                                while (true) {
                                    if (n4 < n5) {
                                        continue Block_8;
                                    }
                                    break Label_0289;
                                    // iftrue(Label_0206:, n6 >= 128)
                                    // iftrue(Label_0155:, n4 < n)
                                    // iftrue(Label_0206:, array[n3] == array2[i - 1])
                                    // iftrue(Label_0096:, n7 = n3 - n8 <= 128)
                                    // iftrue(Label_0224:, n3 >= n)
                                    // iftrue(Label_0130:, array[n3] != n9)
                                    // iftrue(Label_0248:, dj)
                                    while (true) {
                                    Label_0068_Outer:
                                        while (true) {
                                            Block_6: {
                                                while (true) {
                                                    while (true) {
                                                        while (true) {
                                                            while (true) {
                                                                break Block_6;
                                                                n7 = 128;
                                                                break Label_0068_Outer;
                                                                break Label_0206;
                                                                array2[i++] = array[n3++];
                                                                ++n6;
                                                                continue Block_4_Outer;
                                                            }
                                                            continue Block_7_Outer;
                                                        }
                                                        while (array[n3] == n9 && ++n3 != n) {}
                                                        continue Label_0068_Outer;
                                                    }
                                                    n8 = n3;
                                                    n9 = array[n3++];
                                                    continue;
                                                }
                                            }
                                            n4 = n3;
                                            n5 = n;
                                            continue Label_0155_Outer;
                                        }
                                        n3 = n8 + n7;
                                        array2[i++] = n7 - 1;
                                        array2[i++] = n9;
                                        Label_0130: {
                                            n10 = i++;
                                        }
                                        n6 = 1;
                                        array2[i++] = n9;
                                        continue;
                                    }
                                }
                                // iftrue(Label_0245:, !dj)
                                // iftrue(Label_0187:, !dj)
                            }
                            int n5 = n;
                            continue;
                        }
                        array2[n10] = n6 - 1 + 128;
                        if (!dj) {
                            continue Label_0245;
                        }
                        Label_0224: {
                            array2[i++] = 128;
                        }
                        array2[i++] = n9;
                        continue Label_0245;
                    } while (i < n2);
                    break;
                }
            }
            else {
                i = n;
                int n11 = 0;
                while (true) {
                    Label_0283: {
                        if (!dj) {
                            break Label_0283;
                        }
                        array2[n11] = array[n11];
                        ++n11;
                    }
                    if (n11 < n) {
                        continue;
                    }
                    break;
                }
            }
        }
        String string = "";
        if (i < n2) {
            int n12 = 0;
            while (true) {
                Label_0396: {
                    if (!dj) {
                        break Label_0396;
                    }
                    final int n14;
                    final int n13 = (n14 = array2[n12]) / 16;
                    final int n15 = n14 % 16;
                    final StringBuffer append = new StringBuffer().append(string);
                    final String bu = bu("\u0005L\nr\r\u0000K\u000fy\u0000t?{\u0005|s");
                    final int n16 = n13;
                    final StringBuffer append2 = new StringBuffer().append(append.append(bu.substring(n16, n16 + 1)).toString());
                    final String bu2 = bu("\u0005L\nr\r\u0000K\u000fy\u0000t?{\u0005|s");
                    final int n17 = n15;
                    string = append2.append(bu2.substring(n17, n17 + 1)).toString();
                    ++n12;
                }
                if (n12 < i) {
                    continue;
                }
                break;
            }
        }
        return string;
    }
    
    public String Y(final String s) {
        final boolean dj = p.dJ;
        int n = 0;
        String s2 = "";
        while (true) {
            while (true) {
                Label_0143: {
                    if (!dj) {
                        break Label_0143;
                    }
                    final int n2 = n;
                    s.substring(n2, n2 + 2);
                    final String s3;
                    final int int1;
                    if ((int1 = Integer.parseInt(s3, 16)) > 128) {
                        final int n3 = int1 - 128 + 1;
                        s2 += s.substring(n + 2, n + 2 + (n3 << 1));
                        n += (n3 << 1) + 2;
                        if (!dj) {
                            break Label_0143;
                        }
                    }
                    int n4 = 0;
                    while (true) {
                        Label_0133: {
                            if (!dj) {
                                break Label_0133;
                            }
                            s2 += s.substring(n + 2, n + 4);
                            ++n4;
                        }
                        if (n4 <= int1) {
                            continue;
                        }
                        break;
                    }
                    n += 4;
                }
                if (n < s.length()) {
                    continue;
                }
                break;
            }
            final String s3 = s2;
            if (!dj && !dj) {
                return s3;
            }
            continue;
        }
    }
    
    public void Z(final String s) {
        this.J(s);
        this.f();
    }
    
    public void ba(final String s) {
        this.U(s);
        this.Z();
        this.f();
    }
    
    public void Z() {
        this.ct = false;
        this.Q = -1;
        this.M = null;
    }
    
    public void ba() {
        this.ct = true;
        this.M = null;
    }
    
    public int bb() {
        return this.T;
    }
    
    public int bc() {
        return this.U;
    }
    
    public String bd() {
        return bu("\u0004S\bo\t");
    }
    
    public String be() {
        String s = bu("\u0015U") + bu("z\rL(T\\\u000e]%\u0019g\u0018V%\\GQ\u0018");
        if (this.ei != null) {
            s += bu("t\b\\(V\u0015;Q9\u0015\u0015");
        }
        return this.K + bu("\u0015\u0014KaIZ\n]3\\Q]Z8\u0019\u007f8\u007fs\u0019c") + this.bd() + (s + bu("y\u0014L$\u0010")) + bu("8wr\u0004~\u0007]Q2\u0019\u001d\u001e\u0011a\u000b\u0005M\u000ba}T\u0010Q W\u0015.T$\\\u0015[\u0018{\u0010\u0015.U(U\\\u0018\u0018\rMQ]5K]T\u0010Q Wu\u000eU(U\\\u0018\u0016-MQSM*\u0019\u001a]O6N\u001b\u000eU(U\\\u0018\u0016\"VXp2") + bu("z\r]3XA\u0014V&\u0019f\u0004K5\\X]\u0002a") + System.getProperty(bu("Z\u000e\u0016/XX\u0018")).toLowerCase() + ((this.J.d != null) ? this.J.d : "");
    }
    
    public boolean bf() {
        return this.d;
    }
    
    public void o(final boolean d) {
        this.Y = 1000L / this.bf;
        this.X = this.V();
        this.be = 0L;
        this.d = d;
        this.ey = this.X;
        this.p = 0;
        if (this.E == 1) {
            this.bg();
        }
    }
    
    private void bg() {
        final boolean dj = p.dJ;
        final long v = this.V();
        int n = 0;
        while (true) {
            while (true) {
                Label_0027: {
                    if (!dj) {
                        break Label_0027;
                    }
                    this.D[n] = v;
                    ++n;
                }
                if (n < 10) {
                    continue;
                }
                break;
            }
            this.G = 0;
            this.H = 0;
            this.bh();
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    private void bh() {
        long n = 1L;
        this.F = 300;
        final long v;
        if ((v = this.V()) > this.D[this.G]) {
            this.F = (int)(2560L * this.Y / (v - this.D[this.G]));
        }
        if (this.F < 25) {
            this.F = 25;
        }
        if (this.F > 256) {
            this.F = 256;
            if ((n = (int)(this.Y - (v - this.D[this.G]) / 10L)) < 1L) {
                n = 1L;
            }
        }
        this.a(n);
        this.D[this.G] = v;
        this.G = (this.G + 1) % 10;
        if (n > 1L) {
            int n2 = 0;
            while (true) {
                Label_0173: {
                    if (!p.dJ) {
                        break Label_0173;
                    }
                    final long[] d = this.D;
                    final int n3 = n2;
                    d[n3] += n;
                    ++n2;
                }
                if (n2 < 10) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void H(final int e) {
        this.E = e;
        this.I = 1.0f;
    }
    
    public void c(final long ba) {
        this.ba = ba;
    }
    
    public long bi() {
        return i.a(this.ba - (this.V() - this.Z), 0L, Long.MAX_VALUE);
    }
    
    public boolean bj() {
        if (this.A && this.ba > 0L && this.V() - this.Z > this.ba) {
            this.bk();
            this.ba = 0L;
            return true;
        }
        return false;
    }
    
    public boolean bk() {
        final boolean dj = p.dJ;
        if (this.A) {
            final long a = i.a(this.V() - this.Z, 0L, Long.MAX_VALUE);
            if (this.bh) {
                this.X += a;
                if (this.E == 1) {
                    this.bg();
                }
            }
            this.d = this.bh;
            this.ey = this.X;
            this.p = 0;
            this.A = false;
            if (this.ba != -1L) {
                this.ba = i.a(this.ba - a, 0L, Long.MAX_VALUE);
            }
            if (!this.dO) {
                return this.A;
            }
            this.k = true;
            if (this.dP) {
                this.e(false);
            }
            if (this.dQ) {
                this.f(false);
            }
            this.k = false;
            if (!dj) {
                return this.A;
            }
        }
        if (this.ba != 0L) {
            Label_0344: {
                if (this.d) {
                    switch (this.E) {
                        case 0: {
                            while (true) {
                                long n = 0L;
                                Label_0236: {
                                    if ((n = (this.V() - this.X) / this.Y) >= 0L) {
                                        break Label_0236;
                                    }
                                    n = 0L;
                                    if (!dj) {
                                        break Label_0236;
                                    }
                                    this.W();
                                }
                                if (this.be > n) {
                                    break;
                                }
                                continue;
                            }
                        }
                        case 1: {
                            while (true) {
                                while (true) {
                                    Label_0270: {
                                        if (!dj) {
                                            break Label_0270;
                                        }
                                        this.W();
                                        final d d = this;
                                        final int n2 = this.H + this.F;
                                        final int n3;
                                        d.H = n3;
                                    }
                                    if (this.H < 256) {
                                        continue;
                                    }
                                    break;
                                }
                                final d d = this;
                                final int n3 = this.H & 0xFF;
                                if (!dj) {
                                    this.H = n3;
                                    break;
                                }
                                continue;
                            }
                        }
                    }
                    this.Z = this.V();
                    this.bh = this.d;
                    this.d = false;
                    if (!dj) {
                        break Label_0344;
                    }
                }
                this.Z = this.V();
                this.bh = this.d;
                this.d = false;
            }
            this.A = true;
            this.dP = false;
            this.dQ = false;
            if (this.dO) {
                this.k = true;
                if (this.dI) {
                    this.e(false);
                    this.dP = true;
                }
                if (this.dJ) {
                    this.f(false);
                    this.dQ = true;
                }
                this.k = false;
            }
        }
        return this.A;
    }
    
    public void bb(final String o) {
        this.O = o;
        this.f = false;
    }
    
    public boolean bl() {
        return this.f;
    }
    
    public void p(final boolean g) {
        this.g = g;
    }
    
    public void q(final boolean h) {
        this.h = h;
    }
    
    public void bc(final String s) {
        if (s.length() == 6) {
            final String r = this.R(s);
            final int n = 40;
            final int int1 = Integer.parseInt(r.substring(0, 2));
            final int int2 = Integer.parseInt(r.substring(2, 4));
            final int int3 = Integer.parseInt(r.substring(4, 6));
            final int n2 = n * 50;
            if (new Date().after(new Date(int3 + n2 - n2 + 100, int2 - 1, int1))) {
                this.bb(bu("T]L(TPP]-XE\u000e]%"));
            }
        }
    }
    
    private void a(final URL url, final String s, final boolean b) {
        final boolean dj = p.dJ;
        if (url != null) {
            String s2 = url.toString().toLowerCase();
            if (b) {
                try {
                    s2 = InetAddress.getByName(url.getHost()).toString();
                }
                catch (Exception ex) {
                    this.a(this, bu("x4k\u0002fV\u0015]\"Ry\u0018_ U\\\tA"), ex);
                }
            }
            if (this.h) {
                final int index;
                if ((index = s2.indexOf(bu("\u001aR"))) >= 0) {
                    s2 = s2.substring(index + 2);
                }
                final int index2;
                if ((index2 = s2.indexOf("/")) > 0) {
                    s2 = s2.substring(0, index2);
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            int n = false ? 1 : 0;
            while (true) {
                Label_0186: {
                    if (!dj) {
                        break Label_0186;
                    }
                    final String lowerCase = stringTokenizer.nextToken().toLowerCase();
                Label_0138:
                    while (true) {
                        final boolean h = this.h;
                        boolean b2 = false;
                        do {
                            if (b2) {
                                if (!s2.endsWith(lowerCase)) {
                                    break Label_0186;
                                }
                                n = (true ? 1 : 0);
                                if (dj) {
                                    continue Label_0138;
                                }
                                if (!dj) {
                                    break Label_0186;
                                }
                            }
                            if (s2.indexOf(lowerCase) < 0) {
                                break Label_0186;
                            }
                            b2 = true;
                        } while (dj);
                        n = (b2 ? 1 : 0);
                        break;
                    }
                }
                stringTokenizer.hasMoreTokens();
                int n2 = 0;
                while (n2 == 0) {
                    n2 = n;
                    if (!dj && !dj) {
                        if (n2 == 0) {
                            this.bb(bu("T\u0013\u00184WY\u0014[$WF\u0018\\"));
                        }
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    public void f(final String s, final boolean b) {
        this.a(this.S(), s, b);
    }
    
    public void bd(final String s) {
        this.f(s, false);
    }
    
    public void g(final String s, final boolean b) {
        this.a(this.T(), s, b);
    }
    
    public void be(final String s) {
        this.g(s, false);
    }
    
    public void j(final String s, final int n, final int n2) {
        this.cz.drawString(s, n - this.eF.stringWidth(s) / 2, n2);
    }
    
    public void g(final String s, final int n) {
        this.j(s, this.T / 2, n);
    }
    
    public void bm() {
        this.i = true;
    }
    
    public void I(final int priority) {
        if (this.bt != null) {
            this.bt.setPriority(priority);
        }
    }
    
    public boolean o(final String s, final String s2) {
        boolean b = false;
        try {
            this.J.getAppletContext().showDocument(new URL(s), s2);
            b = true;
        }
        catch (Exception ex) {}
        return b;
    }
    
    public void J(final int n) {
        int n2 = 0;
        while (true) {
            Label_0017: {
                if (!p.dJ) {
                    break Label_0017;
                }
                System.runFinalization();
                System.gc();
                ++n2;
            }
            if (n2 >= n) {
                return;
            }
            continue;
        }
    }
    
    public void bn() {
        this.J(1);
    }
    
    public void bf(final String s) {
        if (!this.j) {
            this.J.showStatus(s);
        }
    }
    
    public String h(final String s, final boolean b) {
        final String bu = bu("x4k\u0002fR\u0018L\u0011XG\u0018V5}\\\u000f\u0018{\u0019");
        final int lastIndex = s.lastIndexOf("/");
        final String substring = s.substring(0, lastIndex + (b ? 1 : 0));
        if (this.c) {
            this.H(bu + bu("Y\u001cK5jY\u001cK)\u0019\u000f]") + lastIndex);
            this.H(bu + bu("E\u001cJ$WA]\\(KP\u001eL.KL]\u0002a") + substring);
        }
        return substring;
    }
    
    public boolean bo() {
        return this.e;
    }
    
    public void bg(final String p) {
        this.P = p;
    }
    
    public boolean r(final boolean bj) {
        final boolean bj2 = this.bj;
        this.bj = bj;
        return bj2;
    }
    
    public boolean bh(final String s) {
        final int index;
        final int index2;
        return !this.O(s) && (index = s.indexOf("@")) >= 1 && (index2 = s.indexOf(".", index + 2)) >= 0 && index2 <= s.length() - 3;
    }
    
    public void s(final boolean b) {
        this.B = b;
    }
    
    public boolean bi(final String s) {
        final boolean dj = p.dJ;
        try {
            Method method = null;
            Method method2 = null;
            final Class<?> forName;
            final Method[] methods = (forName = Class.forName(bu("[\u0018L2ZT\r]oST\u000bY2ZG\u0014H5\u0017\u007f.w#SP\u001eL"))).getMethods();
            int n = 0;
            Object[] array2;
            Method method3 = null;
            while (true) {
                if (n >= methods.length) {
                    final Object[] array = array2 = new Object[] { null };
                    array[0] = this.J;
                    method3 = method;
                    if (!dj) {
                        break;
                    }
                }
                else {
                    final Method method4 = methods[n];
                }
                Label_0086: {
                    if (method3.getName().compareTo(bu("R\u0018L\u0016P[\u0019W6")) == 0) {
                        method = methods[n];
                        if (!dj) {
                            break Label_0086;
                        }
                    }
                    if (methods[n].getName().compareTo(bu("P\u000bY-")) == 0) {
                        method2 = methods[n];
                    }
                }
                ++n;
            }
            final Object invoke = method3.invoke(forName, array2);
            array2[0] = s;
            method2.invoke(invoke, array2);
            return true;
        }
        catch (Exception ex) {
            System.out.println(bu("\u007f.\u0018'X\\\u0011\u0018{\u0019") + ex.toString());
            return false;
        }
    }
    
    private void bp() {
        this.du = new Vector(0, 1);
    }
    
    private void bq() {
        final boolean dj = p.dJ;
        final Enumeration<e> elements = (Enumeration<e>)this.du.elements();
        while (true) {
            while (true) {
                Label_0028: {
                    if (!dj) {
                        break Label_0028;
                    }
                    final Object nextElement = elements.nextElement();
                    ((e)nextElement).a();
                }
                if (elements.hasMoreElements()) {
                    continue;
                }
                break;
            }
            this.du.removeAllElements();
            final Object nextElement = this;
            if (!dj) {
                this.du = null;
                return;
            }
            continue;
        }
    }
    
    public e b(final String s, final String s2, final boolean b) {
        final e e = new e(this, s, s2, b);
        this.du.addElement(e);
        return e;
    }
    
    public e p(final String s, final String s2) {
        return this.b(s, s2, false);
    }
    
    public e a(final String s, final String s2, final int n, final int n2, final boolean b) {
        final e b2;
        (b2 = this.b(s, s2.substring(0, 1), false)).a(s2);
        if (b) {
            b2.a(n, n2);
        }
        return b2;
    }
    
    public boolean a(final e e) {
        if (e != null) {
            final boolean removeElement = this.du.removeElement(e);
            e.a();
            return removeElement;
        }
        return false;
    }
    
    private void br() {
        (this.eK = new Hashtable(32, 0.5f)).clear();
    }
    
    private void bs() {
        if (this.eK != null) {
            this.eK.clear();
            this.eK = null;
        }
    }
    
    private void e(final int n, final boolean b) {
        final boolean dj = p.dJ;
        if (this.eK != null) {
            synchronized (this.eK) {
                final Integer o = this.O(n);
                if (this.eK.containsKey(o)) {
                    final h h = this.eK.get(o);
                    Label_0087: {
                        if (b) {
                            if (h.b) {
                                break Label_0087;
                            }
                            h.c = true;
                            if (!dj) {
                                break Label_0087;
                            }
                        }
                        h.c = false;
                        h.b = false;
                    }
                    if (!dj) {
                        return;
                    }
                }
                this.eK.put(o, new h(b));
            }
        }
    }
    
    public void K(final int n) {
        if (this.e) {
            this.e(n, true);
        }
    }
    
    public void L(final int n) {
        if (this.e) {
            this.e(n, false);
            if (this.dK) {
                if ((n == this.dU || n == this.dT) && (!this.dO || !this.A)) {
                    this.e(true);
                }
                if ((n == this.dW || n == this.dV) && (!this.dO || !this.A)) {
                    this.f(!this.dM);
                }
            }
        }
    }
    
    private boolean f(final int n, final boolean b) {
        if (this.eK != null) {
            synchronized (this.eK) {
                final Integer o = this.O(n);
                if (this.eK.containsKey(o)) {
                    final h h = this.eK.get(o);
                    if (!b) {
                        return !h.c;
                    }
                    if (h.c) {
                        if (h.a) {
                            h.c = false;
                            h.b = true;
                        }
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean M(final int n) {
        return this.f(n, true);
    }
    
    public boolean bj(final String s) {
        final boolean dj = p.dJ;
        final int length = s.length();
        int n = 0;
        while (true) {
            while (true) {
                Label_0035: {
                    if (!dj) {
                        break Label_0035;
                    }
                    this.f(s.charAt(n), true);
                    final boolean b;
                    if (b) {
                        return true;
                    }
                    ++n;
                }
                if (n < length) {
                    continue;
                }
                break;
            }
            final boolean b = false;
            if (!dj) {
                return b;
            }
            continue;
        }
    }
    
    public boolean N(final int n) {
        return this.f(n, false);
    }
    
    public boolean bk(final String s) {
        final boolean dj = p.dJ;
        final int length = s.length();
        int n = 0;
        while (true) {
            while (true) {
                Label_0035: {
                    if (!dj) {
                        break Label_0035;
                    }
                    this.f(s.charAt(n), false);
                    final boolean b;
                    if (b) {
                        return true;
                    }
                    ++n;
                }
                if (n < length) {
                    continue;
                }
                break;
            }
            final boolean b = false;
            if (!dj) {
                return b;
            }
            continue;
        }
    }
    
    private Integer O(final int n) {
        return new Integer((n >= 97 && n <= 122) ? (n - 32) : n);
    }
    
    public void g(final int n, final boolean b) {
        if (this.eK != null) {
            synchronized (this.eK) {
                final Integer o = this.O(n);
                if (this.eK.containsKey(o)) {
                    final h h;
                    if (!((h = this.eK.get(o)).a = b)) {
                        h.b = false;
                    }
                    if (!p.dJ) {
                        return;
                    }
                }
                final h h2;
                (h2 = new h(false)).a = b;
                this.eK.put(o, h2);
            }
        }
    }
    
    public void i(final String s, final boolean b) {
        final int length = s.length();
        int n = 0;
        while (true) {
            Label_0028: {
                if (!p.dJ) {
                    break Label_0028;
                }
                this.g(s.charAt(n), b);
                ++n;
            }
            if (n >= length) {
                return;
            }
            continue;
        }
    }
    
    private void bt() {
        this.dv = new Vector(0, 1);
    }
    
    private void bu() {
        final boolean dj = p.dJ;
        final Enumeration<j> elements = (Enumeration<j>)this.dv.elements();
        while (true) {
            while (true) {
                Label_0028: {
                    if (!dj) {
                        break Label_0028;
                    }
                    final Object nextElement = elements.nextElement();
                    ((j)nextElement).a();
                }
                if (elements.hasMoreElements()) {
                    continue;
                }
                break;
            }
            this.dv.removeAllElements();
            final Object nextElement = this;
            if (!dj) {
                this.dv = null;
                return;
            }
            continue;
        }
    }
    
    public j c(final int n, final int n2, final boolean b) {
        final j j = new j(this, n, n2, b);
        this.dv.addElement(j);
        return j;
    }
    
    public boolean a(final j j) {
        j.a();
        return this.dv.removeElement(j);
    }
    
    public synchronized void bv() {
        this.J.a(this, this.cz, this.Q);
        this.b(true);
    }
    
    public void bw() {
        this.t(false);
    }
    
    private boolean bl(final String s) {
        if (this.a(s, false) >= 0) {
            return true;
        }
        boolean b = false;
        if (this.bV.contains(s)) {
            this.cd = 0;
            this.j(s, false);
            b = (this.R(1) == 1);
            this.bV.removeElement(s);
        }
        return b;
    }
    
    public void t(final boolean b) {
        final boolean dj = p.dJ;
        final boolean d = this.d;
        this.bq = 0;
        this.cs = false;
        this.br = this.bV.indexOf("#") + this.ec.indexOf("#");
        this.bs = 0;
        this.ct = false;
        this.Q = 0;
        this.cm = this.c(this.bL, this.bP, this.bQ);
        this.bn = true;
        this.cx = null;
        this.bc = this.V();
        this.Z(this.s);
        this.o(false);
        int n = 0;
        int n2;
        while (true) {
            while (true) {
                Label_0238: {
                    if (!dj) {
                        break Label_0238;
                    }
                    n2 = i.b(n, this.bP);
                    this.a(this.bW[this.cm], new Color((int)i.a(n2, 0.0f, 100.0f, this.u, this.v), (int)i.a(n2, 0.0f, 100.0f, this.w, this.x), (int)i.a(n2, 0.0f, 100.0f, this.y, this.z)), n, 0, 2, this.bQ);
                    n += 2;
                }
                if (n < this.bP) {
                    continue;
                }
                break;
            }
            this.bm(this.by);
            this.cx = new MediaTracker(this.J);
            n2 = this.d(this.bS);
            if (dj) {
                continue;
            }
            break;
        }
        if (this.bW[n2] != null && !this.O(this.bY[n2]) && this.bY[n2].equalsIgnoreCase(this.bS)) {
            this.cs = true;
            if (this.bV.contains(this.bS)) {
                this.S(1);
                this.bV.removeElement(this.bS);
            }
        }
        if (this.bV.contains(this.bS)) {
            this.cd = 0;
            this.j(this.bS, false);
            if (this.R(1) == 1) {
                this.cs = true;
            }
            this.bV.removeElement(this.bS);
        }
        if (this.bB) {
            this.bC = false;
            Label_0499: {
                if (!this.bl(this.bD) || !this.bl(this.bE)) {
                    this.bB = false;
                    if (!dj) {
                        break Label_0499;
                    }
                }
                if (this.bJ == -1) {
                    this.a(this.bD);
                    this.bJ = this.c(bu("Y\u0012Y%\\G"), this.bH, this.bI);
                }
            }
            this.bC = true;
        }
        if (this.fh != null && this.bV.contains(this.fh)) {
            this.cd = 0;
            this.j(this.fh, false);
            this.R(1);
            this.bV.removeElement(this.fh);
            final int a;
            if ((a = this.a(this.fh, false)) != -1) {
                this.P(a);
                this.bB();
                this.bs(this.fi);
            }
        }
        if (this.A(this.dw) >= 0) {
            this.E(this.dw);
            if (this.ec.contains(this.dw)) {
                this.S(1);
                this.ec.removeElement(this.dw);
            }
        }
        while (true) {
            while (true) {
                Label_0730: {
                    if (!this.ec.contains(this.dw)) {
                        break Label_0730;
                    }
                    if (this.bn(this.dw) != -1) {
                        this.E(this.dw);
                    }
                    this.S(1);
                    this.ec.removeElement(this.dw);
                    if (!dj) {
                        break Label_0730;
                    }
                    this.bn(this.ec.elementAt(0).toString());
                    this.S(1);
                    this.ec.removeElementAt(0);
                }
                if (this.ec.indexOf("#") > 0) {
                    continue;
                }
                break;
            }
            this.ct = true;
            if (dj) {
                continue;
            }
            break;
        }
        int n3;
        while (true) {
        Label_1226_Outer:
            while (true) {
                Label_1311: {
                    if (!dj) {
                        break Label_1311;
                    }
                    n3 = 3;
                    int n4 = 0;
                    final String[] array = new String[3];
                    final String[] array2 = new String[3];
                    final int[] array3 = new int[3];
                    this.ct = false;
                    int n9 = 0;
                    Label_1193: {
                        int n5;
                        Object element;
                        String string;
                        StringTokenizer stringTokenizer;
                        int n6;
                        int n7;
                        int n8;
                        Label_0840_Outer:Label_0988_Outer:Label_1011_Outer:Label_1019_Outer:Label_1052_Outer:
                        while (true) {
                            Label_1167: {
                                if (!dj) {
                                    break Label_1167;
                                }
                                this.cd = 0;
                                n4 = ((this.bV.indexOf("#") > 3) ? 3 : this.bV.indexOf("#"));
                                n5 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0946: {
                                            if (!dj) {
                                                break Label_0946;
                                            }
                                            element = this.bV.elementAt(n5);
                                            string = element.toString();
                                            Label_0943: {
                                                if ((stringTokenizer = new StringTokenizer(string, "|")).countTokens() == 1) {
                                                    array2[n5] = (array[n5] = string);
                                                    array3[n5] = this.j(string, false);
                                                    if (!dj) {
                                                        break Label_0943;
                                                    }
                                                }
                                                array[n5] = null;
                                                array2[n5] = stringTokenizer.nextToken();
                                                array3[n5] = this.k(array2[n5], Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
                                            }
                                            ++n5;
                                        }
                                        if (n5 < n4) {
                                            continue Label_0840_Outer;
                                        }
                                        break;
                                    }
                                    element = this;
                                    if (dj) {
                                        continue Label_1052_Outer;
                                    }
                                    break;
                                }
                                if (this.R(n4) == n4) {
                                    n6 = 0;
                                    while (true) {
                                        while (true) {
                                            Label_0991: {
                                                if (!dj) {
                                                    break Label_0991;
                                                }
                                                this.bU[array3[n6]] = false;
                                                ++n6;
                                            }
                                            if (n6 < n4) {
                                                continue Label_0988_Outer;
                                            }
                                            break;
                                        }
                                        n7 = 0;
                                        if (dj) {
                                            continue Label_1011_Outer;
                                        }
                                        break;
                                    }
                                    while (true) {
                                        while (true) {
                                            Label_1022: {
                                                if (!dj) {
                                                    break Label_1022;
                                                }
                                                this.bV.removeElementAt(0);
                                                ++n7;
                                            }
                                            if (n7 < n4) {
                                                continue Label_1019_Outer;
                                            }
                                            break;
                                        }
                                        this.ct = true;
                                        if (dj) {
                                            continue Label_1052_Outer;
                                        }
                                        break;
                                    }
                                    if (!dj) {
                                        break Label_1167;
                                    }
                                }
                                n8 = 0;
                                while (true) {
                                    Label_1157: {
                                        if (!dj) {
                                            break Label_1157;
                                        }
                                        System.out.println(bu("|\u0010Y&\\\u0015\u0011W ]\u0015\u001bY(U\u0015G\u0018") + array[n8]);
                                        this.bU[array3[n8]] = false;
                                        if (this.bW[array3[n8]] == null) {
                                            this.bW[array3[n8]] = this.J.createImage(8, 8);
                                        }
                                        this.a(array3[n8], false);
                                        if (this.bT[n8]) {
                                            this.S(-1);
                                        }
                                        ++n8;
                                    }
                                    if (n8 < n4) {
                                        continue;
                                    }
                                    break;
                                }
                                --n3;
                            }
                            if (!this.ct) {
                                n9 = n3;
                                if (dj || dj) {
                                    break Label_1193;
                                }
                                if (n9 > 0) {
                                    continue;
                                }
                            }
                            break;
                        }
                        final boolean ct = this.ct;
                    }
                    if (n9 == 0) {
                        String s = bu("s\u0014T$J\u0015G");
                        this.Q = -1;
                        this.U(bu("r;`aUZ\u001c\\(WR]]3KZ\u000f\u0018`"));
                        int n10 = 0;
                        while (true) {
                            while (true) {
                                Label_1268: {
                                    if (!dj) {
                                        break Label_1268;
                                    }
                                    final d d2 = this;
                                    if (!d2.bT[n10]) {
                                        s = s + " " + array2[n10];
                                    }
                                    ++n10;
                                }
                                if (n10 < n4) {
                                    continue Label_1226_Outer;
                                }
                                break;
                            }
                            this.V(s);
                            this.bx();
                            this.f();
                            final d d2 = this;
                            if (dj) {
                                continue;
                            }
                            break;
                        }
                        this.J.stop();
                    }
                }
                if (this.bV.indexOf("#") > 0) {
                    continue;
                }
                break;
            }
            this.bm(this.bz);
            n3 = 0;
            if (dj) {
                if (dj) {
                    continue;
                }
            }
            break;
        }
        while (true) {
            if (n3 >= this.bM) {
                this.br = 0;
                n3 = 0;
                if (!dj) {
                    break;
                }
                if (!dj) {
                    break;
                }
            }
            else {
                this.P(n3);
            }
            ++n3;
        }
        while (true) {
            if (n3 >= this.bM) {
                this.bs = 0;
                this.cj = 0;
                this.ct = false;
                n3 = 0;
                if (!dj) {
                    break;
                }
            }
            else if (this.bW[n3] != null && this.cr[n3]) {
                this.br += this.bZ[n3] / this.cb[n3] * (this.ca[n3] / this.cc[n3]);
            }
            ++n3;
        }
        while (true) {
            while (true) {
                Label_2070: {
                    if (!dj) {
                        break Label_2070;
                    }
                    Label_2067: {
                        if (this.bW[n3] == null) {
                            break Label_2067;
                        }
                        final boolean b2 = this.cr[n3];
                        final boolean b3;
                        if (b3) {
                            Label_1623: {
                                if (this.cb[n3] > this.bZ[n3]) {
                                    if (!this.E()) {
                                        break Label_1623;
                                    }
                                    this.J(bu("p/j\u000ek\u0015\\\u0018\u0002QZ\r\u00186PQ\tPaMZ\u0012\u0018&KP\u001cLa_Z\u000f\u0018{\u0019") + this.bY[n3]);
                                    if (!dj) {
                                        break Label_1623;
                                    }
                                }
                                if (this.cc[n3] > this.ca[n3] && this.E()) {
                                    this.J(bu("p/j\u000ek\u0015\\\u0018\u0002QZ\r\u0018)\\\\\u001aP5\u0019A\u0012Wa^G\u0018Y5\u0019S\u0012Ja\u0003\u0015") + this.bY[n3]);
                                }
                            }
                            this.h(n3);
                            boolean b4 = true;
                            int n11 = 0;
                            int n12 = 0;
                            int n13 = 0;
                        Label_2039:
                            while (true) {
                                if (!dj) {
                                    break Label_2039;
                                }
                            Label_2044:
                                while (true) {
                                    final int d3 = this.d();
                                    boolean b6;
                                    do {
                                        int n14 = 0;
                                        int n15 = 0;
                                        do {
                                            if (n14 == n15) {
                                                if (!this.c) {
                                                    break Label_2044;
                                                }
                                                this.J(bu("`\u0013Y#UP]L.\u0019V\u000f] MP]\u007f\u0007a\u0015\u000eP$\\A]\u0002a") + this.bY[n3]);
                                                if (!dj) {
                                                    break Label_2044;
                                                }
                                            }
                                            boolean b5 = false;
                                            if (this.cw) {
                                                this.bW[d3] = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(this.bW[n3].getSource(), new CropImageFilter(n11, n12, this.cb[n3], this.cc[n3])));
                                                (this.cx = new MediaTracker(new Frame())).addImage(this.bW[d3], 0);
                                                try {
                                                    this.cx.waitForAll(0L);
                                                }
                                                catch (Exception ex) {
                                                    b5 = true;
                                                }
                                                this.cx = null;
                                            }
                                            if (!this.cw || b5) {
                                                this.bW[d3] = this.a(this.bW[n3], n11, n12, this.cb[n3], this.cc[n3]);
                                            }
                                            this.bY[d3] = this.bY[n3] + Integer.toString(n13++);
                                            this.bZ[d3] = this.cb[n3];
                                            this.ca[d3] = this.cc[n3];
                                            this.cr[d3] = false;
                                            this.Q(d3);
                                            this.S(1);
                                            if ((n11 += this.cb[n3]) + this.cb[n3] <= this.bZ[n3]) {
                                                break Label_2039;
                                            }
                                            n11 = 0;
                                            n14 = (n12 += this.cc[n3]) + this.cc[n3];
                                            n15 = this.ca[n3];
                                        } while (dj);
                                        if (n14 <= n15) {
                                            break Label_2039;
                                        }
                                        final boolean b7;
                                        b6 = (b7 = false);
                                    } while (dj);
                                    b4 = b6;
                                    if (b4) {
                                        continue;
                                    }
                                    break;
                                }
                                this.a(n3, false);
                                if (dj) {
                                    continue Label_2039;
                                }
                                break;
                            }
                            if (!dj) {
                                break Label_2067;
                            }
                        }
                        this.Q(n3);
                    }
                    ++n3;
                }
                if (n3 < this.bM) {
                    continue;
                }
                break;
            }
            this.ct = true;
            this.cx = null;
            this.bn = false;
            this.Z(this.r);
            this.a(this.cm, false);
            this.bn();
            final boolean b3 = d;
            if (!dj) {
                if (b3) {
                    this.o(true);
                }
                if (d.fD) {
                    p.dJ = !dj;
                }
                return;
            }
            continue;
        }
    }
    
    private void bm(final String bx) {
        this.bw = "";
        this.bx = bx;
        while (true) {
            Label_0043: {
                if (!p.dJ) {
                    break Label_0043;
                }
                this.bw += ":";
            }
            if (this.K(this.bw + " " + bx + " " + this.bw) >= this.bP || this.bw.length() >= 10) {
                return;
            }
            continue;
        }
    }
    
    public void bx() {
    }
    
    public void P(final int n) {
        if (this.bW[n] != null) {
            this.bZ[n] = this.bW[n].getWidth(null);
            this.ca[n] = this.bW[n].getHeight(null);
        }
    }
    
    public void Q(final int n) {
        if (!this.bU[n]) {
            Label_0188: {
                final String lowerCase;
                if ((lowerCase = this.bY[n].toLowerCase()).indexOf(bu("\u001b\u0017H&")) >= 0) {
                    final Image image = this.J.createImage(this.bZ[n], this.ca[n]);
                    if (this.cB[n] != null) {
                        this.cB[n].dispose();
                        this.cB[n] = null;
                    }
                    this.a(this.cB[n] = image.getGraphics(), this.bW[n], 0, 0);
                    this.bW[n].flush();
                    this.bW[n] = null;
                    this.bW[n] = image;
                    if (!p.dJ) {
                        break Label_0188;
                    }
                }
                if (lowerCase.indexOf(bu("\u001b\u001aQ'")) >= 0) {
                    this.a(n, this.b(this.bW[n], 0, 0, this.bZ[n], this.ca[n]), this.bZ[n], this.ca[n]);
                }
            }
            this.bU[n] = true;
        }
    }
    
    private int R(final int n) {
        final boolean dj = p.dJ;
        boolean b = false;
        int n2 = 0;
        int n3 = 0;
        while (true) {
            Label_0028: {
                if (!dj) {
                    break Label_0028;
                }
                this.bT[n3] = false;
                ++n3;
            }
            if (n3 >= n) {
                int n5 = 0;
            Label_0038_Outer:
                while (true) {
                    while (true) {
                        Label_0174: {
                            if (n2 == n) {
                                break Label_0174;
                            }
                            int n4 = n5;
                            boolean errorAny = false;
                            while (true) {
                                Label_0146: {
                                    Label_0140: {
                                        if (!dj) {
                                            break Label_0140;
                                        }
                                        int n6 = errorAny ? 1 : 0;
                                        final int statusID;
                                        if (i.c(statusID = this.cx.statusID(n4, true), 4) || i.c(statusID, 2)) {
                                            b = true;
                                            if (!dj) {
                                                break Label_0146;
                                            }
                                        }
                                        if (i.c(statusID, 8) && !i.c(statusID, 1) && !this.bT[n4]) {
                                            n6 = 1;
                                        }
                                        if (n6 != 0) {
                                            this.bT[n4] = true;
                                            ++n2;
                                            this.S(1);
                                        }
                                        ++n4;
                                    }
                                    if (n4 < n) {
                                        continue;
                                    }
                                }
                                errorAny = this.cx.isErrorAny();
                                if (dj) {
                                    continue;
                                }
                                break;
                            }
                            if (!errorAny && !b) {
                                this.by();
                                continue Label_0038_Outer;
                            }
                        }
                        n5 = n2;
                        if (!dj) {
                            break;
                        }
                        continue;
                    }
                }
                return n5;
            }
            continue;
        }
    }
    
    private void by() {
        if (this.V() >= this.bc && this.bC) {
            this.n = true;
            this.f();
            this.bc += 20L;
        }
    }
    
    private void S(final int n) {
        this.bs += n;
        this.Q = i.a(i.b(this.bs, this.br), 0, 100);
        this.by();
    }
    
    private int j(final String s, final boolean b) {
        try {
            final int d;
            if ((d = this.d()) >= 0) {
                final String string = bu("\\\u0010Y&\\FR") + s;
                this.bW[d] = (this.j ? Toolkit.getDefaultToolkit().getImage(string) : this.J.getImage(this.S(), string));
                this.cr[d] = false;
                this.bY[d] = s;
                this.ct = false;
                this.cx.addImage(this.bW[d], this.cd++);
                return d;
            }
        }
        catch (Exception ex) {
            if (this.c) {
                this.J(ex.toString());
            }
            return -1;
        }
        if (this.c) {
            this.J(bu("`\u0013Y#UP]L.\u0019Y\u0012Y%\u0019r;`a\u0003\u0015") + s);
        }
        return -1;
    }
    
    private int k(final String s, final int n, final int n2) {
        final int j;
        if ((j = this.j(s, true)) != -1) {
            this.cr[j] = true;
            this.cb[j] = n;
            this.cc[j] = n2;
        }
        return j;
    }
    
    private int bn(final String s) {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            while (true) {
                Label_0152: {
                    if (!dj) {
                        break Label_0152;
                    }
                    final d d = this;
                    if (d.dY[n] == null) {
                        final String string = bu("F\u0012M/]FR") + s;
                        Label_0095: {
                            if (this.j) {
                                this.dY[n] = Applet.newAudioClip(this.getClass().getResource(string));
                                if (!dj) {
                                    break Label_0095;
                                }
                            }
                            this.dY[n] = this.J.getAudioClip(this.S(), string);
                            try {
                                this.dY[n].play();
                                this.dY[n].stop();
                            }
                            catch (Exception ex) {
                                System.out.println(ex.toString());
                            }
                        }
                        this.dZ[n] = s;
                        this.ea[n] = false;
                        return n;
                    }
                    ++n;
                }
                if (n < this.dS) {
                    continue;
                }
                break;
            }
            final d d = this;
            if (!dj) {
                if (this.c) {
                    this.J(bu("`\u0013Y#UP]L.\u0019Y\u0012Y%\u0019f2m\u000f}\u0015G\u0018") + s);
                }
                return -1;
            }
            continue;
        }
    }
    
    public void bo(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (true) {
            Label_0029: {
                if (!p.dJ) {
                    break Label_0029;
                }
                this.bV.insertElementAt(stringTokenizer.nextToken(), 0);
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            continue;
        }
    }
    
    public void l(final String s, final int n, final int n2) {
        ++this.cj;
        this.bV.insertElementAt(s + "|" + n + "|" + n2, 0);
    }
    
    public void bp(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (true) {
            Label_0039: {
                if (!p.dJ) {
                    break Label_0039;
                }
                this.l(stringTokenizer.nextToken(), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            continue;
        }
    }
    
    public void bq(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (true) {
            Label_0029: {
                if (!p.dJ) {
                    break Label_0029;
                }
                this.ec.insertElementAt(stringTokenizer.nextToken(), 0);
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            continue;
        }
    }
    
    public void q(final String by, final String bz) {
        this.by = by;
        this.bz = bz;
    }
    
    private void bz() {
        this.fc = false;
        this.fd = false;
        this.fi = "";
        this.fe = false;
    }
    
    public void r(final String fh, final String s) {
        if (this.a(fh, false) == -1) {
            this.bo(fh);
        }
        this.fh = fh;
        this.fc = true;
        this.s(bu("]\tL1\u0003\u001aRO6N\u001b\u000eU(U\\\u0018\u0016\"VX"), bu("j?t\u0000w~"));
        this.bs(s);
    }
    
    public void br(final String s) {
    }
    
    public void p(final int fj, final int fk) {
        this.fd = true;
        this.fj = fj;
        this.fk = fk;
        if (this.fc) {
            this.bB();
        }
    }
    
    public void u(final boolean fe) {
        this.fe = fe;
    }
    
    public void v(final boolean fc) {
        this.fc = fc;
        if (this.fc) {
            this.bB();
            if (!p.dJ) {
                return;
            }
        }
        this.bA();
    }
    
    public void s(final String ff, final String fg) {
        this.ff = ff;
        this.fg = fg;
        if (this.fc) {
            this.bB();
        }
    }
    
    public void bs(final String fi) {
        this.fi = fi;
        if (this.fc) {
            this.d(this.y(this.fh), fi);
        }
    }
    
    private void bA() {
        if (this.fh != null) {
            this.i(this.fh);
        }
    }
    
    private void bB() {
        final boolean dj = p.dJ;
        this.bA();
        final int a;
        if ((a = this.a(this.fh, false)) != -1) {
            final int n = this.bZ[a];
            final int n2 = this.ca[a];
            int fj = 0;
            int fk = 0;
            Label_0114: {
                if (this.fd) {
                    fj = this.fj;
                    fk = this.fk;
                    if (!dj) {
                        break Label_0114;
                    }
                }
                fk = this.U - n2 / 2 - 8;
                if (this.fe) {
                    fj = (this.T - n) / 2 + n / 2;
                    if (!dj) {
                        break Label_0114;
                    }
                }
                fj = this.T - n / 2 - 8;
            }
            this.a(this.a(this.fh, fj, fk, this.fh, this.ff != null, 0), this.ff, this.fg);
            this.bs(this.fi);
        }
    }
    
    public void T(final int n) {
        this.W |= n;
    }
    
    public void U(final int n) {
        this.W &= ~n;
    }
    
    public boolean V(final int n) {
        return (this.W & n) != 0x0;
    }
    
    public int bC() {
        return this.W;
    }
    
    public void W(final int r) {
        this.r = r;
    }
    
    public void X(final int s) {
        this.s = s;
    }
    
    public void Y(final int t) {
        this.t = t;
    }
    
    public void bD() {
        this.Z(this.r);
    }
    
    public void bE() {
        this.Z(this.s);
    }
    
    public void bF() {
        this.Z(this.t);
    }
    
    public void Z(final int n) {
        if (this.V != n && this.bv != null) {
            this.bv.setCursor(n);
            this.V = n;
        }
    }
    
    private void bG() {
        this.fn = bu("q(u\f`");
        this.ba(2);
        this.b(Color.black, Color.yellow, Color.black);
        this.q(4, 4);
        this.a(1000L, 4000L);
        this.m(bu("}\u0018T7\\A\u0014[ "), 0, 12);
        this.bI();
    }
    
    private void bH() {
        this.fy = null;
    }
    
    private void bI() {
        this.fz = 0L;
        this.fA = 0L;
        if (this.fu != -1) {
            this.n = true;
        }
        this.fu = -1;
    }
    
    public void m(final String s, final int n, final int n2) {
        this.fy = null;
        this.fy = new Font(s, n, n2);
    }
    
    public void ba(final int fo) {
        this.fo = fo;
    }
    
    public void b(final Color fv, final Color fw, final Color fx) {
        this.fv = fv;
        this.fw = fw;
        this.fx = fx;
    }
    
    public void q(final int fp, final int fq) {
        this.fp = fp;
        this.fq = fq;
    }
    
    public void a(final long fb, final long fc) {
        this.fB = fb;
        this.fC = fc;
    }
    
    private void b(final Graphics graphics) {
        final boolean dj = p.dJ;
        if (i.b(this.V(), this.fz, this.fA)) {
            this.fu = this.ft;
            int n = 0;
            int max = 0;
            int countTokens = 0;
            this.a(this.fy, this.fv);
            int n2 = 0;
            while (true) {
                Label_0531: {
                    if (!dj) {
                        break Label_0531;
                    }
                    if (n2 == 1 && n == 0) {
                        final int n3 = (this.fp + this.fq << 1) + max;
                        final int n4 = (this.fp + this.fq << 1) + countTokens * this.P();
                        final Point r = this.r(this.ft);
                        this.fs = i.a(this.cM[this.ft], 8, this.U - 8 - n4);
                        this.fr = this.cL[this.ft] + r.x / 2 + 8;
                        if (this.fr > this.T - 8 - n3) {
                            this.fr = this.cL[this.ft] - r.x / 2 - 8 - n3;
                            if (this.fr < 0) {
                                this.fr = this.cL[this.ft] - n3 / 2;
                                this.fs = this.cM[this.ft] + r.y / 2 + 8;
                            }
                        }
                        graphics.setColor(this.fx);
                        graphics.fillRoundRect(this.fr, this.fs, n3, n4, this.fp / 2, this.fp / 2);
                        graphics.setColor(this.fw);
                        graphics.fillRoundRect(this.fr + this.fp, this.fs + this.fp, (this.fq << 1) + max, (this.fq << 1) + countTokens * this.P(), this.fp / 2, this.fp / 2);
                        graphics.setColor(this.fv);
                        n = 1;
                    }
                    final StringTokenizer stringTokenizer;
                    countTokens = (stringTokenizer = new StringTokenizer(this.fn, "|")).countTokens();
                    int n5 = 0;
                    while (true) {
                        Label_0521: {
                            if (!dj) {
                                break Label_0521;
                            }
                            final String nextToken = stringTokenizer.nextToken();
                            final int k = this.K(nextToken);
                            max = Math.max(max, k);
                            if (n2 == 1) {
                                int n6 = this.fr + this.fp + this.fq;
                                Label_0483: {
                                    if (this.fo == 1) {
                                        n6 += max - k;
                                        if (!dj) {
                                            break Label_0483;
                                        }
                                    }
                                    if (this.fo == 2) {
                                        n6 += (max - k) / 2;
                                    }
                                }
                                graphics.drawString(nextToken, n6, this.fs + this.fp + this.fq + n5 * this.P() + this.Q());
                            }
                            ++n5;
                        }
                        if (n5 < countTokens) {
                            continue;
                        }
                        break;
                    }
                    ++n2;
                }
                if (n2 < 2) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void f(final int n, final String s) {
        if (n >= 0 && n < this.cD) {
            this.cK[n] = s;
            this.b(n, 512);
        }
    }
    
    public void t(final String s, final String s2) {
        this.f(this.y(s), s2);
    }
    
    private static String bu(final String s) {
        char[] charArray;
        for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '5';
                    break;
                }
                case 1: {
                    c2 = '}';
                    break;
                }
                case 2: {
                    c2 = '8';
                    break;
                }
                case 3: {
                    c2 = 'A';
                    break;
                }
                default: {
                    c2 = '9';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
