import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.applet.AudioClip;
import java.awt.Font;
import java.net.URL;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import a3dAPI.b;
import a3dAPI.a;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fps extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
    a a;
    Thread b;
    Thread c;
    int d;
    float[] e;
    float[][] f;
    int[] g;
    int[] h;
    float[] i;
    int[] j;
    float[] k;
    float[] l;
    int[] m;
    int[] n;
    int[] o;
    int[] p;
    int[][] q;
    float[] r;
    float[] s;
    float[] t;
    float[] u;
    float[] v;
    float[] w;
    static int x;
    int y;
    static int z;
    static int A;
    static int B;
    static int C;
    static int D;
    static int E;
    static int F;
    static boolean G;
    static boolean H;
    static float I;
    static int J;
    static int K;
    int L;
    static boolean M;
    static int N;
    static int O;
    static boolean P;
    static boolean Q;
    static int R;
    static boolean S;
    static int T;
    static int U;
    static int V;
    static int W;
    static int X;
    static int Y;
    static boolean Z;
    static int aa;
    static int ab;
    static int ac;
    String ad;
    j ae;
    j af;
    j[] ag;
    j[] ah;
    int ai;
    int aj;
    int ak;
    int al;
    int am;
    int an;
    int ao;
    int ap;
    int aq;
    int ar;
    int as;
    int at;
    int au;
    int av;
    int aw;
    int ax;
    int ay;
    int[] az;
    int[] aA;
    e aB;
    d[] aC;
    j[] aD;
    j aE;
    b aF;
    public Image aG;
    public Graphics aH;
    Component aI;
    MediaTracker aJ;
    Image aK;
    Image aL;
    Image aM;
    Image aN;
    Image aO;
    Image aP;
    Image aQ;
    Image aR;
    Image aS;
    Image aT;
    Image aU;
    boolean[] aV;
    int aW;
    boolean aX;
    boolean aY;
    int aZ;
    int ba;
    String[] bb;
    int bc;
    int bd;
    Runtime be;
    String bf;
    boolean bg;
    boolean bh;
    boolean bi;
    static boolean bj;
    static boolean bk;
    boolean bl;
    boolean bm;
    static boolean bn;
    boolean bo;
    boolean bp;
    static int bq;
    int br;
    int[] bs;
    int bt;
    int bu;
    Color bv;
    Color bw;
    Color bx;
    int by;
    int bz;
    int bA;
    int bB;
    int bC;
    int bD;
    int[] bE;
    int[] bF;
    int[] bG;
    int[] bH;
    int bI;
    int bJ;
    int bK;
    Color bL;
    Color bM;
    Color bN;
    boolean bO;
    int bP;
    int bQ;
    static float bR;
    static int bS;
    int bT;
    int bU;
    Color[] bV;
    Color[] bW;
    Color[] bX;
    Color bY;
    URL bZ;
    boolean ca;
    int cb;
    boolean cc;
    int cd;
    static int ce;
    m cf;
    m cg;
    m[] ch;
    m[] ci;
    static int cj;
    static int ck;
    Color cl;
    boolean cm;
    int cn;
    int co;
    l cp;
    boolean[] cq;
    boolean[] cr;
    int cs;
    int ct;
    int cu;
    float cv;
    int cw;
    int cx;
    int cy;
    boolean cz;
    int cA;
    int cB;
    int[] cC;
    a[] cD;
    a cE;
    a cF;
    a[] cG;
    a[] cH;
    a[] cI;
    a[] cJ;
    a cK;
    a[][] cL;
    a cM;
    b[] cN;
    k cO;
    Font cP;
    Font cQ;
    Font cR;
    Font cS;
    Font cT;
    Font cU;
    Font cV;
    Font cW;
    Font cX;
    Font cY;
    Font cZ;
    Font da;
    Font db;
    Font dc;
    Font dd;
    Font de;
    int[] df;
    long dg;
    boolean dh;
    long di;
    long dj;
    long dk;
    long dl;
    int dm;
    long dn;
    long do;
    long dp;
    long dq;
    n[] dr;
    int ds;
    int dt;
    h[] du;
    int dv;
    int dw;
    int dx;
    int dy;
    j[] dz;
    j[][] dA;
    j[][] dB;
    j[][][] dC;
    j[] dD;
    j[][] dE;
    int[] dF;
    int[] dG;
    int[] dH;
    int[] dI;
    j[][] dJ;
    j[] dK;
    c[] dL;
    int dM;
    int dN;
    Image[] dO;
    Image dP;
    Image dQ;
    Image dR;
    Image dS;
    Image dT;
    Image dU;
    AudioClip dV;
    AudioClip[] dW;
    AudioClip[] dX;
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void a() {
        if (!fps.G) {
            (this.aF = (b)this.a.a()).a(this);
            this.aF.addMouseListener(this);
            this.aF.addMouseMotionListener(this);
        }
        this.add(this.aI = this.a.a());
        this.aI.resize(this.size().width, this.size().height);
        this.addKeyListener(this);
        fps.B = this.size().width;
        fps.C = fps.B / 2;
        fps.D = this.size().height;
        fps.R = (int)(fps.D * 0.16f);
        fps.E = fps.B / 5 / 2;
        fps.J = fps.B / 3;
        fps.K = fps.B - fps.J;
        if (fps.B > 800 || fps.D > 600) {
            this.bc = 3;
        }
        this.a.a(0.0f, 0.0f, 0.0f);
        this.aF.a(fps.X);
    }
    
    public final void b() {
        this.be.gc();
    }
    
    public final void init() {
        this.be = Runtime.getRuntime();
        this.b();
        this.be.gc();
        this.b();
        this.bZ = this.getCodeBase();
        this.cp = new l();
        for (int i = 0; i < 3; ++i) {
            this.aV[i] = false;
        }
        fps.G = false;
        if (this.getParameter("advert").startsWith("true")) {
            this.bi = true;
        }
        this.bf = this.getParameter("adverttarget");
        this.setLayout(null);
        fps fps;
        a a;
        if (fps.G) {
            fps = this;
            a = new a("hardware-gl4j");
        }
        else {
            fps = this;
            a = new a("software");
        }
        fps.a = a;
        this.a.b("BQQb1qUpBGNkKoYEO2rwpAR5AtEABHXxacE=");
        n();
        this.a();
        this.cb = fps.B / 40;
        final Dimension size = this.size();
        this.aG = this.createImage(size.width, size.height);
        this.aH = this.aG.getGraphics();
        fps.B = this.size().width;
        fps.C = fps.B / 2;
        fps.D = this.size().height;
        this.cp.a = -1;
        this.f();
        this.g();
        this.e();
        this.d();
        System.out.println(".");
        this.aJ();
        System.out.println(".");
        System.out.println();
        this.al();
        this.c();
    }
    
    public final void c() {
        if (this.ca) {
            this.cp.u = true;
            this.ac();
            this.H(this.cp.m = 15);
            this.cp.d = fps.B;
            return;
        }
        this.cp.a = 5;
    }
    
    public final void a(final int n) {
        this.cp.a = 0;
    }
    
    public final void a(final String s) {
        if (!this.aX) {
            this.aE();
            System.out.println(" .");
            this.I(6);
            this.aH();
            this.I(7);
            this.aD();
            System.out.println(" .");
            this.I(8);
            this.p();
            System.out.println(" .");
            this.I(9);
            final URL d = this.d(s);
            InputStream openStream;
            try {
                openStream = d.openStream();
            }
            catch (IOException ex) {
                System.out.println("  " + d);
                return;
            }
            try {
                final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openStream));
                System.out.println(" .");
                this.i(dataInputStream);
                System.out.println(" .");
                this.I(10);
                this.d(dataInputStream);
                System.out.println(" .");
                this.I(11);
                this.e(dataInputStream);
                System.out.println(" .");
                this.I(12);
                this.b(dataInputStream);
                this.I(13);
                this.c(dataInputStream);
                this.I(15);
                System.out.println(" .");
                this.a(dataInputStream);
                System.out.println(" .");
                this.I(16);
                openStream.close();
                dataInputStream.close();
            }
            catch (IOException ex2) {
                System.out.println("");
                return;
            }
            this.aX = true;
            this.cp.u = true;
            this.cI[0].b = this.a(c("nr/c/s/n/u/f/2/003"), this.cC);
            this.I(17);
            for (int i = 0; i < 5; ++i) {
                this.ae(i);
            }
            this.aI();
            this.I(18);
            return;
        }
        this.I(18);
    }
    
    public final void b(final String s) {
        if (!this.aY) {
            System.out.println();
            System.out.println();
            System.out.println();
            final URL d = this.d(s);
            InputStream openStream;
            try {
                openStream = d.openStream();
            }
            catch (IOException ex) {
                System.out.println("  " + d);
                return;
            }
            try {
                final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openStream));
                this.c(dataInputStream, true);
                this.I(19);
                final int h = this.h(dataInputStream);
                System.out.println(" .");
                this.I(20);
                this.a(dataInputStream, h);
                System.out.println(" .");
                this.I(21);
                this.m();
                this.I(22);
                openStream.close();
                dataInputStream.close();
            }
            catch (IOException ex2) {
                System.out.println("!");
                return;
            }
            System.out.println(" .");
            System.out.println(" .");
            System.out.println();
            System.out.println();
            this.aY = true;
            this.c.stop();
            this.c = null;
        }
    }
    
    public final void d() {
        this.aK = this.getImage(this.bZ, "injar/sprites/logo/used/logo.gif");
        this.aJ.addImage(this.aK, 0);
        final double random;
        fps fps;
        fps fps2;
        URL url;
        String s;
        if ((random = Math.random()) < 0.2) {
            fps = this;
            fps2 = this;
            url = this.bZ;
            s = "nr-s-n-u+t1";
        }
        else if (random < 0.4) {
            fps = this;
            fps2 = this;
            url = this.bZ;
            s = "nr*s*n+u=t2";
        }
        else if (random < 0.6) {
            fps = this;
            fps2 = this;
            url = this.bZ;
            s = "nr-s+n+u=t3";
        }
        else if (random < 0.8) {
            fps = this;
            fps2 = this;
            url = this.bZ;
            s = "nr-s-n+u=t4";
        }
        else {
            fps = this;
            fps2 = this;
            url = this.bZ;
            s = "nr+s+n+u=t5";
        }
        fps.aR = fps2.getImage(url, c(s));
        System.out.println(".");
        this.aJ.addImage(this.aR, 0);
        final double random2;
        fps fps3;
        fps fps4;
        URL url2;
        String s2;
        if ((random2 = Math.random()) < 0.2) {
            fps3 = this;
            fps4 = this;
            url2 = this.bZ;
            s2 = "nr-s-n+u=g1";
        }
        else if (random2 < 0.4) {
            fps3 = this;
            fps4 = this;
            url2 = this.bZ;
            s2 = "nr+s+n=u=g2";
        }
        else if (random2 < 0.6) {
            fps3 = this;
            fps4 = this;
            url2 = this.bZ;
            s2 = "nr-s-n-u=g3";
        }
        else if (random2 < 0.8) {
            fps3 = this;
            fps4 = this;
            url2 = this.bZ;
            s2 = "nr-s-n+u=g4";
        }
        else {
            fps3 = this;
            fps4 = this;
            url2 = this.bZ;
            s2 = "nr+s+n+u=g5";
        }
        fps3.aT = fps4.getImage(url2, c(s2));
        this.aJ.addImage(this.aT, 0);
        this.aU = this.getImage(this.bZ, "injar/sprites/loadscreen/loadscreen.jpg");
        this.aJ.addImage(this.aU, 0);
        System.out.println(".");
        this.aL = this.getImage(this.bZ, "injar/sprites/levelscreens/used/level-screen1.jpg");
        this.aJ.addImage(this.aL, 0);
        System.out.println(".");
        this.aM = this.getImage(this.bZ, "injar/sprites/levelscreens/used/level-screen2.jpg");
        this.aJ.addImage(this.aM, 0);
        System.out.println(".");
        this.aN = this.getImage(this.bZ, "injar/sprites/levelscreens/used/level-screen3a.jpg");
        this.aJ.addImage(this.aN, 0);
        System.out.println(".");
        this.aO = this.getImage(this.bZ, "injar/sprites/levelscreens/used/level-screen4.jpg");
        this.aJ.addImage(this.aO, 0);
        System.out.println(".");
        this.aP = this.getImage(this.bZ, "injar/sprites/levelscreens/used/level-screen5.jpg");
        this.aJ.addImage(this.aP, 0);
        System.out.println(".");
        this.aQ = this.getImage(this.bZ, "injar/sprites/levelscreens/used/level-screen6o.jpg");
        this.aJ.addImage(this.aQ, 0);
        System.out.println(".");
        if (this.bi) {
            this.aS = this.getImage(this.bZ, "nr/advert/1.jpg");
            this.aJ.addImage(this.aS, 0);
            System.out.println(".");
        }
    }
    
    public final void a(final DataInputStream dataInputStream) {
        this.a(dataInputStream, true);
        final float[] array = { 0.0f, 0.0f, 0.0f, 0.7813f, 0.1953f, 0.7813f, 0.1953f, 0.0f };
        this.dK[0] = new j();
        this.dK[0].v = this.a.e();
        final Object a = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[0].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a), a3dAPI.c.a(a), this.o, this.q[10]);
        this.a.d(this.dK[0].w, this.az[0]);
        this.dK[10] = new j();
        this.dK[10].v = this.a.e();
        final Object a2 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[10].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a2), a3dAPI.c.a(a2), this.o, this.q[10]);
        this.a.d(this.dK[10].w, this.az[0]);
        this.a(dataInputStream, true);
        final float[] array2 = { 0.1953f, 0.0f, 0.1953f, 0.7813f, 0.3906f, 0.7813f, 0.3906f, 0.0f };
        this.dK[1] = new j();
        this.dK[1].v = this.a.e();
        final Object a3 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[1].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array2, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a3), a3dAPI.c.a(a3), this.o, this.q[10]);
        this.a.d(this.dK[1].w, this.az[0]);
        this.a(dataInputStream, true);
        final float[] array3 = { 0.3906f, 0.0f, 0.3906f, 0.7813f, 0.5859f, 0.7813f, 0.5859f, 0.0f };
        this.dK[2] = new j();
        this.dK[2].v = this.a.e();
        final Object a4 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[2].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array3, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a4), a3dAPI.c.a(a4), this.o, this.q[10]);
        this.a.d(this.dK[2].w, this.az[0]);
        this.a(dataInputStream, true);
        final float[] array4 = { 0.5859f, 0.0f, 0.5859f, 0.7813f, 0.7812f, 0.7813f, 0.7812f, 0.0f };
        this.dK[3] = new j();
        this.dK[3].v = this.a.e();
        final Object a5 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[3].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array4, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a5), a3dAPI.c.a(a5), this.o, this.q[10]);
        this.a.d(this.dK[3].w, this.az[0]);
        this.a(dataInputStream, true);
        final float[] array5 = { 0.7812f, 0.0f, 0.7812f, 0.7813f, 0.9765f, 0.7813f, 0.9765f, 0.0f };
        this.dK[4] = new j();
        this.dK[4].v = this.a.e();
        final Object a6 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[4].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array5, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a6), a3dAPI.c.a(a6), this.o, this.q[10]);
        this.a.d(this.dK[4].w, this.az[0]);
        this.a(dataInputStream, true);
        final float[] array6 = { 0.0f, 0.0f, 0.0f, 0.7813f, 0.1953f, 0.7813f, 0.1953f, 0.0f };
        this.dK[5] = new j();
        this.dK[5].v = this.a.e();
        final Object a7 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[5].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array6, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a7), a3dAPI.c.a(a7), this.o, this.q[11]);
        this.a.d(this.dK[5].w, this.az[0]);
        final float[] array7 = { 0.1953f, 0.0f, 0.1953f, 0.7813f, 0.3906f, 0.7813f, 0.3906f, 0.0f };
        this.a(dataInputStream, true);
        this.dK[6] = new j();
        this.dK[6].v = this.a.e();
        final Object a8 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[6].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array7, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a8), a3dAPI.c.a(a8), this.o, this.q[11]);
        this.a.d(this.dK[6].w, this.az[0]);
        final float[] array8 = { 0.3906f, 0.0f, 0.3906f, 0.7813f, 0.5859f, 0.7813f, 0.5859f, 0.0f };
        this.a(dataInputStream, true);
        this.dK[7] = new j();
        this.dK[7].v = this.a.e();
        final Object a9 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[7].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array8, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a9), a3dAPI.c.a(a9), this.o, this.q[11]);
        this.a.d(this.dK[7].w, this.az[0]);
        final float[] array9 = { 0.5859f, 0.0f, 0.5859f, 0.7813f, 0.7812f, 0.7813f, 0.7812f, 0.0f };
        this.a(dataInputStream, true);
        this.dK[8] = new j();
        this.dK[8].v = this.a.e();
        final Object a10 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[8].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array9, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a10), a3dAPI.c.a(a10), this.o, this.q[11]);
        this.a.d(this.dK[8].w, this.az[0]);
        final float[] array10 = { 0.7812f, 0.0f, 0.7812f, 0.7813f, 0.9765f, 0.7813f, 0.9765f, 0.0f };
        this.a(dataInputStream, true);
        this.dK[9] = new j();
        this.dK[9].v = this.a.e();
        final Object a11 = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
        this.dK[9].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array10, a3dAPI.c.a(this.m, true), a3dAPI.c.b(a11), a3dAPI.c.a(a11), this.o, this.q[11]);
        this.a.d(this.dK[9].w, this.az[0]);
    }
    
    public final void b(final DataInputStream dataInputStream) {
        this.a(dataInputStream, true);
        this.dI[0] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.5f, 0.0f, 0.5f, 1.0f, 0.625f, 1.0f, 0.625f, 0.0f }, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dI[0], this.cK.a);
        this.a(dataInputStream, true);
        this.dI[1] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.635f, 0.0f, 0.635f, 1.0f, 0.75f, 1.0f, 0.75f, 0.0f }, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dI[1], this.cK.a);
        this.a(dataInputStream, true);
        this.dI[2] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.6133f, 0.75f, 0.668f, 0.75f, 0.668f, 0.0469f, 0.6133f, 0.0469f, 0.3996f, 0.8125f, 0.4727f, 0.8125f, 0.4727f, 0.9297f, 0.3996f, 0.9297f, 0.4414f, 0.6016f, 0.539f, 0.6016f, 0.539f, 0.7656f, 0.4414f, 0.7656f }, a3dAPI.c.a(new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 8, 9, 10, 11, -1, 3, 2, 1, 0, -1, 0, 3, 2, 1, -1, 4, 5, 6, 7, -1, 7, 6, 5, 4, -1 }, true), null, null, null, null);
        this.a.b(this.dI[2], this.cE.a);
        this.a(dataInputStream, true);
        this.dI[3] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.19531f, 0.7813f, 0.1953f, 0.9766f, 0.39f, 0.9766f, 0.39f, 0.7813f }, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dI[3], this.cE.a);
        this.a(dataInputStream, true);
        this.dI[4] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.6133f, 0.75f, 0.668f, 0.75f, 0.668f, 0.0469f, 0.6133f, 0.0469f, 0.6133f, 0.75f, 0.7461f, 0.75f, 0.7461f, 0.0469f, 0.6133f, 0.0469f, 0.6445f, 0.8672f, 0.6953f, 0.8672f, 0.6953f, 0.9297f, 0.6445f, 0.9297f, 0.4414f, 0.4297f, 0.539f, 0.4297f, 0.539f, 0.7656f, 0.4414f, 0.7656f }, a3dAPI.c.a(new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 13, 14, 15, 12, -1, 3, 2, 1, 0, -1, 5, 6, 7, 4, -1, 8, 9, 10, 11, -1, 11, 10, 9, 8, -1 }, true), null, null, null, null);
        this.a.b(this.dI[4], this.cE.a);
        this.a(dataInputStream, true);
        this.dI[5] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.8281f, 0.6875f, 0.9375f, 0.6875f, 0.9375f, 0.07813f, 0.8281f, 0.07813f, 0.7227f, 0.5547f, 0.7227f, 0.7186f, 0.6289f, 0.7186f, 0.6289f, 0.5547f, 0.7891f, 0.8125f, 0.8945f, 0.8125f, 0.8945f, 0.9531f, 0.7891f, 0.9531f, 0.4414f, 0.4297f, 0.539f, 0.4297f, 0.539f, 0.7656f, 0.4414f, 0.7656f }, a3dAPI.c.a(new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 13, 14, 15, 12, -1, 3, 2, 1, 0, -1, 1, 2, 3, 0, -1, 8, 9, 10, 11, -1, 11, 10, 9, 8, -1 }, true), null, null, null, null);
        this.a.b(this.dI[5], this.cE.a);
        this.a(dataInputStream, true);
        this.dI[6] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.0117f, 0.8359f, 0.0117f, 0.9141f, 0.1836f, 0.9141f, 0.1836f, 0.8359f, 0.7227f, 0.5547f, 0.7227f, 0.7186f, 0.6289f, 0.7186f, 0.6289f, 0.5547f, 0.0781f, 0.8359f, 0.0781f, 0.9141f, 0.1171f, 0.9141f, 0.1171f, 0.8359f, 0.0117f, 0.8984f, 0.0117f, 0.9531f, 0.1836f, 0.9531f, 0.1836f, 0.8984f }, a3dAPI.c.a(new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 4, 5, 6, 7, -1, 8, 9, 10, 11, -1, 1, 2, 3, 0, -1, 12, 13, 14, 15, -1, 15, 14, 13, 12, -1, 13, 12, 15, 14, -1, 14, 15, 12, 13, -1, 0, 1, 2, 3, -1, 0, 1, 2, 3, -1, 15, 12, 13, 14, -1, 14, 15, 12, 13, -1 }, true), null, null, null, null);
        this.a.b(this.dI[6], this.cF.a);
        this.a(dataInputStream, true);
        this.dI[7] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.2617f, 0.6953f, 0.3516f, 0.6953f, 0.3398f, 0.125f, 0.2695f, 0.125f, 0.7227f, 0.5547f, 0.7227f, 0.7186f, 0.6289f, 0.7186f, 0.6289f, 0.5547f, 0.2695f, 0.7422f, 0.2695f, 0.1484f, 0.2227f, 0.1406f, 0.2305f, 0.7422f }, a3dAPI.c.a(new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 4, 5, 6, 7, -1, 4, 5, 6, 7, -1, 1, 2, 3, 0, -1, 0, 1, 2, 3, -1, 8, 9, 10, 11, -1, 11, 10, 9, 8, -1, 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 4, 5, 6, 7, -1, 4, 5, 6, 7, -1, 1, 2, 3, 0, -1 }, true), null, null, null, null);
        this.a.b(this.dI[7], this.cF.a);
        this.a(dataInputStream, true);
        this.dI[8] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.4023f, 0.8593f, 0.4023f, 0.9297f, 0.5547f, 0.9297f, 0.5547f, 0.8593f, 0.7227f, 0.5547f, 0.7227f, 0.7186f, 0.6289f, 0.7186f, 0.6289f, 0.5547f }, a3dAPI.c.a(new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 4, 5, 6, 7, -1, 4, 5, 6, 7, -1, 1, 2, 3, 0, -1, 0, 1, 2, 3, -1, 0, 1, 2, 3, -1 }, true), null, null, null, null);
        this.a.b(this.dI[8], this.cF.a);
        this.a(dataInputStream, true);
        final int[] array = new int[20];
        for (int i = 0; i < 20; ++i) {
            array[i] = this.cF.a;
        }
        this.dI[9] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.5859f, 0.825f, 0.5859f, 0.8672f, 0.7813f, 0.8672f, 0.7813f, 0.825f, 0.6641f, 0.8672f, 0.7148f, 0.8672f, 0.7148f, 0.9141f, 0.6641f, 0.9141f, 0.7227f, 0.5547f, 0.7227f, 0.7186f, 0.6289f, 0.7186f, 0.6289f, 0.5547f }, a3dAPI.c.a(new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 0, 1, 2, 3, -1, 8, 9, 10, 11, -1, 8, 9, 10, 11, -1, 0, 1, 2, 3, -1, 4, 5, 6, 7, -1, 7, 6, 5, 4, -1 }, true), null, null, null, array);
        this.a(dataInputStream, true);
        this.dI[10] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.9609f, 0.8203f, 0.9609f, 0.9297f, 0.7813f, 0.9297f, 0.7813f, 0.8203f, 0.9648f, 0.8516f, 0.9648f, 0.9297f, 0.8712f, 0.9297f, 0.8712f, 0.8516f, 0.7227f, 0.5547f, 0.7227f, 0.7186f, 0.6289f, 0.7186f, 0.6289f, 0.5547f, 0.9686f, 0.785f, 0.9686f, 0.8438f, 0.7813f, 0.8438f, 0.7813f, 0.785f }, a3dAPI.c.a(new int[] { 0, 1, 2, 3, -1, 2, 3, 0, 1, -1, 2, 3, 0, 1, -1, 0, 1, 2, 3, -1, 5, 6, 7, 4, -1, 9, 10, 11, 8, -1, 15, 14, 13, 12, -1, 12, 13, 14, 15, -1 }, true), null, null, null, array);
        this.a(dataInputStream, true);
        this.dI[11] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.0f, 0.7813f, 0.0f, 0.9766f, 0.19531f, 0.9766f, 0.19531f, 0.7813f }, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dI[11], this.cE.a);
    }
    
    public final void c(final DataInputStream dataInputStream) {
        final float[] array = { 0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f, 0.125f, 0.0f };
        this.a(dataInputStream, true);
        this.dI[30] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dI[30], this.cK.a);
        this.dI[31] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.125f, 0.0f, 0.125f, 1.0f, 0.25f, 1.0f, 0.25f, 0.0f }, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dI[31], this.cK.a);
        this.dI[32] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.25f, 0.0f, 0.25f, 1.0f, 0.375f, 1.0f, 0.375f, 0.0f }, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dI[32], this.cK.a);
        this.dI[33] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, new float[] { 0.375f, 0.0f, 0.375f, 1.0f, 0.5f, 1.0f, 0.5f, 0.0f }, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dI[33], this.cK.a);
        this.a(dataInputStream, true);
        System.out.println(" .");
        for (int i = 0; i < 40; ++i) {
            for (int j = 0; j < 25; ++j) {
                this.dJ[i][j].v = this.a.e();
                this.dJ[i][j].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.n, true), null, null, null, null);
                this.a.b(this.dJ[i][j].w, this.cG[0].a);
            }
        }
        System.out.println(" .");
    }
    
    public final void a(final int n, final int n2) {
        for (int i = 0; i < 10; ++i) {
            a a;
            int n3;
            a cm;
            if (n2 == 0) {
                a = this.a;
                n3 = this.aC[n].e[i];
                cm = this.cH[0];
            }
            else {
                a = this.a;
                n3 = this.aC[n].e[i];
                cm = this.cM;
            }
            a.b(n3, cm.a);
        }
    }
    
    public final void d(final DataInputStream dataInputStream) {
        this.a(dataInputStream, true);
        final float[] array = { 0.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.25f, 0.5f, 0.25f, 0.0f };
        final int[] array2 = { 0, 1, 2, 3, -1, 7, 6, 5, 4, -1, 0, 1, 2, 3, -1, 7, 6, 5, 4, -1, 4, 7, 6, 5, -1, 7, 6, 5, 4, -1 };
        for (int i = 0; i < 5; ++i) {
            this.aC[i].e[0] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array, a3dAPI.c.a(array2, true), null, null, null, null);
            this.a.b(this.aC[i].e[0], this.cH[0].a);
            this.aC[i].f[0].v = this.a.e();
        }
        this.a(dataInputStream, true);
        final float[] array3 = { 0.5f, 0.0f, 0.5f, 0.5f, 1.0f, 0.5f, 1.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 1.0f, 0.5f, 1.0f, 0.0f };
        final int[] array4 = { 0, 1, 2, 3, -1, 7, 6, 5, 4, -1, 0, 1, 2, 3, -1, 7, 6, 5, 4, -1, 4, 7, 6, 5, -1, 7, 6, 5, 4, -1 };
        for (int j = 0; j < 5; ++j) {
            this.aC[j].e[1] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array3, a3dAPI.c.a(array4, true), null, null, null, null);
            this.a.b(this.aC[j].e[1], this.cH[0].a);
            this.aC[j].f[1].v = this.a.e();
            this.aC[j].e[2] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array3, a3dAPI.c.a(array4, true), null, null, null, null);
            this.a.b(this.aC[j].e[2], this.cH[0].a);
            this.aC[j].f[2].v = this.a.e();
        }
        for (int k = 0; k < 5; ++k) {
            this.aC[k].e[3] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array3, a3dAPI.c.a(array4, true), null, null, null, null);
            this.a.b(this.aC[k].e[3], this.cH[0].a);
            this.aC[k].f[3].v = this.a.e();
            this.aC[k].e[4] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array3, a3dAPI.c.a(array4, true), null, null, null, null);
            this.a.b(this.aC[k].e[4], this.cH[0].a);
            this.aC[k].f[4].v = this.a.e();
        }
        final float[] array5 = { 0.0f, 0.5f, 0.0f, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f, 0.0f, 0.5f, 0.0f, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f };
        final int[] array6 = { 0, 1, 2, 3, -1, 7, 6, 5, 4, -1, 0, 1, 2, 3, -1, 7, 6, 5, 4, -1, 4, 7, 6, 5, -1, 7, 6, 5, 4, -1 };
        this.a(dataInputStream, true);
        for (int l = 0; l < 5; ++l) {
            this.aC[l].e[5] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array5, a3dAPI.c.a(array6, true), null, null, null, null);
            this.a.b(this.aC[l].e[5], this.cH[0].a);
            this.aC[l].f[5].v = this.a.e();
            this.aC[l].e[6] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array5, a3dAPI.c.a(array6, true), null, null, null, null);
            this.a.b(this.aC[l].e[6], this.cH[0].a);
            this.aC[l].f[6].v = this.a.e();
            this.aC[l].e[7] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array5, a3dAPI.c.a(array6, true), null, null, null, null);
            this.a.b(this.aC[l].e[7], this.cH[0].a);
            this.aC[l].f[7].v = this.a.e();
            this.aC[l].e[8] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array5, a3dAPI.c.a(array6, true), null, null, null, null);
            this.a.b(this.aC[l].e[8], this.cH[0].a);
            this.aC[l].f[8].v = this.a.e();
        }
        this.a(dataInputStream, true);
        final float[] array7 = { 0.0f, 0.5f, 0.0f, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f, 0.8f, 0.5f, 0.8f, 0.65f, 1.0f, 0.65f, 1.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.75f, 0.75f, 0.75f, 0.75f, 0.5f, 0.75f, 0.5f, 0.75f, 0.75f, 1.0f, 0.75f, 1.0f, 0.5f };
        final int[] array8 = { 4, 5, 6, 7, -1, 15, 14, 13, 12, -1, 8, 9, 10, 11, -1, 12, 13, 14, 15, -1, 4, 7, 6, 5, -1, 7, 6, 5, 4, -1 };
        for (int n = 0; n < 5; ++n) {
            this.aC[n].e[9] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array7, a3dAPI.c.a(array8, true), null, null, null, null);
            this.a.b(this.aC[n].e[9], this.cH[0].a);
            this.aC[n].f[9].v = this.a.e();
        }
    }
    
    public final void e(final DataInputStream dataInputStream) {
        this.a(dataInputStream, true);
        this.dH[0] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dH[0], this.cG[11].a);
        this.a(dataInputStream, true);
        this.dH[10] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.l, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dH[10], this.cG[9].a);
        this.a(dataInputStream, true);
        this.dH[1] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[1], this.cG[0].a);
        this.a(dataInputStream, true);
        this.dH[2] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[2], this.cG[1].a);
        this.a(dataInputStream, true);
        this.dH[3] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[3], this.cG[2].a);
        this.dH[11] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[11], this.cG[4].a);
        this.a(dataInputStream, true);
        this.dH[4] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[4], this.cG[1].a);
        for (int i = 0; i < 10; ++i) {
            this.dB[i][0].p = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
            this.a.b(this.dB[i][0].p, this.cL[0][0].a);
        }
        this.a(dataInputStream, true);
        this.dH[5] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[5], this.cG[1].a);
        for (int j = 0; j < 10; ++j) {
            this.dB[j][0].q = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
            this.a.b(this.dB[j][0].q, this.cL[0][0].a);
            for (int k = 0; k < 5; ++k) {
                this.dC[k][j][0].q = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
                this.a.b(this.dC[k][j][0].q, this.cL[0][0].a);
            }
        }
        this.dH[9] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[9], this.cG[3].a);
        this.a(dataInputStream, true);
        for (int l = 0; l < 10; ++l) {
            this.dB[l][0].s = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
            this.a.b(this.dB[l][0].q, this.cL[0][0].a);
            for (int n = 0; n < 5; ++n) {
                this.dC[n][l][0].s = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
                this.a.b(this.dC[n][l][0].q, this.cL[0][0].a);
            }
        }
        this.a(dataInputStream, true);
        this.dH[6] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.d(this.dH[6], this.ar);
        this.a(dataInputStream, true);
        this.dH[7] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dH[7], this.cG[11].a);
        this.a(dataInputStream, true);
        this.dH[8] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[8], this.cG[7].a);
        for (int n2 = 0; n2 < 10; ++n2) {
            this.dB[n2][0].r = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
            this.a.b(this.dB[n2][0].r, this.cG[7].a);
            for (int n3 = 0; n3 < 5; ++n3) {
                this.dC[n3][n2][0].r = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
                this.a.b(this.dC[n3][n2][0].r, this.cG[7].a);
            }
        }
        this.a(dataInputStream, true);
        this.dH[13] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.n, true), null, null, null, null);
        this.a.b(this.dH[13], this.cG[11].a);
        this.a(dataInputStream, true);
        this.dH[12] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[12], this.cG[6].a);
        this.a(dataInputStream, true);
        this.dH[14] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[14], this.cG[5].a);
        this.dH[15] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[15], this.cG[0].a);
        this.dH[16] = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        this.a.b(this.dH[16], this.cG[3].a);
    }
    
    public final void e() {
        this.dL = new c[10];
        for (int i = 0; i < 10; ++i) {
            this.dL[i] = new c();
        }
        this.dL[0].a = "Mac-10";
        this.dL[0].b = 6.0f;
        this.dL[0].c = 20;
        this.dL[0].d = 1;
        this.dL[0].e = 10;
        this.dL[0].f = 2.0f;
        this.dL[0].g = 2;
        this.dL[0].i = 7;
        this.dL[0].j = 7;
        this.dL[0].k = 2;
        this.dL[0].l = 5;
        this.dL[0].m = 20;
        this.dL[0].n = 3;
        this.dL[0].p = 8;
        this.dL[0].q = 0;
        this.dL[0].r = 0.05f;
        this.dL[0].s = 0.07f;
        this.dL[0].t = 0.002f;
        this.dL[0].u = 2.0f;
        this.dL[0].J = true;
        this.dL[0].o = 0;
        this.dL[0].L = -0.7f;
        this.dL[0].M = 1.1f;
        this.dL[0].N = false;
        this.dL[0].O = 1.0f;
        this.dL[0].P = 0.0f;
        this.dL[0].Q = 0.0f;
        this.dL[0].S = 20.0f;
        this.dL[0].R = 1.0f;
        this.dL[0].h = 20;
        this.dL[0].K = false;
        this.dL[0].T = 3;
        this.dL[0].U = 9;
        this.dL[0].V = true;
        this.dL[0].W = 1.0f;
        this.dL[0].X = 1.0f;
        this.dL[0].Y = 1.0f;
        this.dL[0].Z = 1.0f;
        this.dL[0].aa = 50.0f;
        this.dL[0].ab = -1;
        this.dL[0].ac = -1;
        this.dL[0].ad = 1;
        this.dL[0].ae = 0;
        this.dL[0].af = 0.2f;
        this.dL[0].ah = 10.0f;
        this.dL[0].ai = 20;
        this.dL[0].v = 2;
        this.dL[0].w = 5;
        this.dL[0].x = 6;
        this.dL[0].I = false;
        this.dL[0].aj = 3;
        this.dL[0].ak = 2.1f;
        this.dL[0].al = 1;
        this.dL[1].a = "Assault Rifle";
        this.dL[1].b = 5.0f;
        this.dL[1].c = 5;
        this.dL[1].d = 1;
        this.dL[1].e = 10;
        this.dL[1].f = 2.0f;
        this.dL[1].g = 5;
        this.dL[1].i = 0;
        this.dL[1].j = 13;
        this.dL[1].k = 2;
        this.dL[1].l = 10;
        this.dL[1].m = 20;
        this.dL[1].n = 3;
        this.dL[1].p = 8;
        this.dL[1].q = 1;
        this.dL[1].r = 0.05f;
        this.dL[1].s = 0.07f;
        this.dL[1].t = 0.002f;
        this.dL[1].u = 2.0f;
        this.dL[1].J = true;
        this.dL[1].o = 0;
        this.dL[1].h = 35;
        this.dL[1].K = false;
        this.dL[1].L = -1.0f;
        this.dL[1].M = 0.7f;
        this.dL[1].N = false;
        this.dL[1].T = 0;
        this.dL[1].U = 9;
        this.dL[1].V = true;
        this.dL[1].W = 1.0f;
        this.dL[1].X = 1.0f;
        this.dL[1].Y = 1.0f;
        this.dL[1].Z = 1.0f;
        this.dL[1].aa = 50.0f;
        this.dL[1].ab = -1;
        this.dL[1].ac = -1;
        this.dL[1].ad = 1;
        this.dL[1].ae = 0;
        this.dL[1].af = 0.3f;
        this.dL[1].ah = 10.0f;
        this.dL[1].ai = 20;
        this.dL[1].I = false;
        this.dL[1].aj = 3;
        this.dL[1].ak = 4.5f;
        this.dL[1].al = 3;
        this.dL[2].a = "Shotgun";
        this.dL[2].b = 6.0f;
        this.dL[2].c = 30;
        this.dL[2].d = 5;
        this.dL[2].e = 6;
        this.dL[2].f = 0.0f;
        this.dL[2].g = 5;
        this.dL[2].i = -1;
        this.dL[2].j = 7;
        this.dL[2].k = 2;
        this.dL[2].l = 5;
        this.dL[2].m = 25;
        this.dL[2].n = 3;
        this.dL[2].p = 8;
        this.dL[2].q = 1;
        this.dL[2].r = 0.0f;
        this.dL[2].s = 0.1f;
        this.dL[2].t = 0.002f;
        this.dL[2].u = 0.5f;
        this.dL[2].J = true;
        this.dL[2].o = 0;
        this.dL[2].h = 30;
        this.dL[2].K = false;
        this.dL[2].L = -1.0f;
        this.dL[2].M = 0.2f;
        this.dL[2].y = 0.25f;
        this.dL[2].N = false;
        this.dL[2].T = 1;
        this.dL[2].W = 1.0f;
        this.dL[2].X = 1.0f;
        this.dL[2].Y = 1.0f;
        this.dL[2].Z = 1.0f;
        this.dL[2].aa = 50.0f;
        this.dL[2].ab = -1;
        this.dL[2].ac = -1;
        this.dL[2].ad = 1;
        this.dL[2].ae = 0;
        this.dL[2].af = 0.5f;
        this.dL[2].ah = 10.0f;
        this.dL[2].ai = 20;
        this.dL[2].I = false;
        this.dL[3].a = "Double Shotgun";
        this.dL[3].b = 6.0f;
        this.dL[3].c = 60;
        this.dL[3].d = 10;
        this.dL[3].e = 10;
        this.dL[3].f = 0.0f;
        this.dL[3].g = 5;
        this.dL[3].i = -1;
        this.dL[3].j = 7;
        this.dL[3].k = 6;
        this.dL[3].l = 5;
        this.dL[3].m = 25;
        this.dL[3].n = 1;
        this.dL[3].p = 5;
        this.dL[3].q = 1;
        this.dL[3].r = 0.0f;
        this.dL[3].s = 0.1f;
        this.dL[3].t = 0.002f;
        this.dL[3].u = 0.5f;
        this.dL[3].J = true;
        this.dL[3].o = 0;
        this.dL[3].h = 30;
        this.dL[3].K = false;
        this.dL[3].L = -1.1f;
        this.dL[3].M = 0.55f;
        this.dL[3].y = 0.75f;
        this.dL[3].N = false;
        this.dL[3].T = 1;
        this.dL[3].W = 1.0f;
        this.dL[3].X = 1.0f;
        this.dL[3].Y = 1.0f;
        this.dL[3].Z = 1.0f;
        this.dL[3].aa = 50.0f;
        this.dL[3].ab = -1;
        this.dL[3].ac = -1;
        this.dL[3].ad = 1;
        this.dL[3].ae = 0;
        this.dL[3].af = 0.5f;
        this.dL[3].ah = 10.0f;
        this.dL[3].ai = 20;
        this.dL[3].I = false;
        this.dL[3].D = true;
        this.dL[4].a = "Trident";
        this.dL[4].b = 6.0f;
        this.dL[4].c = 25;
        this.dL[4].d = 3;
        this.dL[4].e = 5;
        this.dL[4].f = 0.0f;
        this.dL[4].g = 5;
        this.dL[4].i = 3;
        this.dL[4].j = 3;
        this.dL[4].k = 3;
        this.dL[4].l = 6;
        this.dL[4].m = 25;
        this.dL[4].n = 0;
        this.dL[4].p = 5;
        this.dL[4].q = 1;
        this.dL[4].r = 0.0f;
        this.dL[4].s = 0.2f;
        this.dL[4].t = 0.01f;
        this.dL[4].u = 1.0f;
        this.dL[4].J = false;
        this.dL[4].o = 2;
        this.dL[4].h = 40;
        this.dL[4].K = true;
        this.dL[4].L = -1.0f;
        this.dL[4].M = 1.5f;
        this.dL[4].N = false;
        this.dL[4].T = 5;
        this.dL[4].U = -1;
        this.dL[4].W = 1.0f;
        this.dL[4].X = 0.0f;
        this.dL[4].Y = 75.0f;
        this.dL[4].Z = 100.0f;
        this.dL[4].aa = 50.0f;
        this.dL[4].ab = -1;
        this.dL[4].ac = -1;
        this.dL[4].ad = 50;
        this.dL[4].ae = 2;
        this.dL[4].af = 0.5f;
        this.dL[4].ah = 10.0f;
        this.dL[4].ai = 20;
        this.dL[5].a = "Plasma Gun";
        this.dL[5].b = 5.0f;
        this.dL[5].c = 15;
        this.dL[5].d = 1;
        this.dL[5].e = 3;
        this.dL[5].f = 5.0f;
        this.dL[5].g = 20;
        this.dL[5].i = 14;
        this.dL[5].j = 14;
        this.dL[5].k = 14;
        this.dL[5].l = 10;
        this.dL[5].m = 40;
        this.dL[5].n = 0;
        this.dL[5].p = 6;
        this.dL[5].q = 2;
        this.dL[5].r = 0.02f;
        this.dL[5].s = 0.1f;
        this.dL[5].t = 0.02f;
        this.dL[5].u = 1.5f;
        this.dL[5].J = false;
        this.dL[5].o = 5;
        this.dL[5].h = 100;
        this.dL[5].K = false;
        this.dL[5].v = 2;
        this.dL[5].w = 10;
        this.dL[5].x = 0;
        this.dL[5].L = -0.7f;
        this.dL[5].M = 0.75f;
        this.dL[5].N = true;
        this.dL[5].O = 0.0f;
        this.dL[5].P = 0.3f;
        this.dL[5].Q = 1.0f;
        this.dL[5].S = 20.0f;
        this.dL[5].R = 1.0f;
        this.dL[5].T = 8;
        this.dL[5].U = -1;
        this.dL[5].W = 1.0f;
        this.dL[5].X = 0.0f;
        this.dL[5].Y = 0.0f;
        this.dL[5].Z = 1.0f;
        this.dL[5].aa = 50.0f;
        this.dL[5].ab = -1;
        this.dL[5].ac = -1;
        this.dL[5].ad = 25;
        this.dL[5].ae = 5;
        this.dL[5].af = 6.0f;
        this.dL[5].ah = 10.0f;
        this.dL[5].ai = 5;
        this.dL[5].z = 0.0f;
        this.dL[5].A = 0.0f;
        this.dL[5].B = 5.0f;
        this.dL[5].C = 0.5f;
        this.dL[6].a = "Prometheus Cannon";
        this.dL[6].b = 5.0f;
        this.dL[6].c = 15;
        this.dL[6].d = 1;
        this.dL[6].e = 3;
        this.dL[6].f = 5.0f;
        this.dL[6].g = 20;
        this.dL[6].i = 15;
        this.dL[6].j = 15;
        this.dL[6].k = 15;
        this.dL[6].l = 10;
        this.dL[6].m = 40;
        this.dL[6].n = 0;
        this.dL[6].p = 6;
        this.dL[6].q = 2;
        this.dL[6].r = 0.04f;
        this.dL[6].s = 0.15f;
        this.dL[6].t = 0.02f;
        this.dL[6].u = 1.5f;
        this.dL[6].J = true;
        this.dL[6].o = 4;
        this.dL[6].h = 100;
        this.dL[6].K = false;
        this.dL[6].L = -1.0f;
        this.dL[6].M = 1.3f;
        this.dL[6].N = true;
        this.dL[6].O = 1.0f;
        this.dL[6].P = 0.3f;
        this.dL[6].Q = 0.05f;
        this.dL[6].S = 20.0f;
        this.dL[6].R = 1.0f;
        this.dL[6].T = 5;
        this.dL[6].U = -1;
        this.dL[6].W = 1.0f;
        this.dL[6].X = 0.0f;
        this.dL[6].Y = 0.0f;
        this.dL[6].Z = 1.0f;
        this.dL[6].aa = 50.0f;
        this.dL[6].ab = 10;
        this.dL[6].ac = 10;
        this.dL[6].ad = 50;
        this.dL[6].ae = 4;
        this.dL[6].af = 4.0f;
        this.dL[6].ah = 10.0f;
        this.dL[6].ai = 30;
        this.dL[6].z = 1.0f;
        this.dL[6].A = 1.0f;
        this.dL[6].B = 5.0f;
        this.dL[6].C = 0.5f;
        this.dL[7].a = "Zeus Cannon";
        this.dL[7].b = 8.0f;
        this.dL[7].c = 50;
        this.dL[7].d = 1;
        this.dL[7].e = 6;
        this.dL[7].f = 0.0f;
        this.dL[7].g = 0;
        this.dL[7].E = true;
        this.dL[7].i = 10;
        this.dL[7].j = 10;
        this.dL[7].k = 11;
        this.dL[7].l = 1;
        this.dL[7].m = 25;
        this.dL[7].n = 3;
        this.dL[7].p = 6;
        this.dL[7].q = 2;
        this.dL[7].r = 0.05f;
        this.dL[7].s = 0.1f;
        this.dL[7].t = 0.02f;
        this.dL[7].u = 1.0f;
        this.dL[7].J = false;
        this.dL[7].o = 0;
        this.dL[7].h = 500;
        this.dL[7].K = true;
        this.dL[7].L = -1.0f;
        this.dL[7].M = 1.6f;
        this.dL[7].N = true;
        this.dL[7].O = 0.0f;
        this.dL[7].P = 0.0f;
        this.dL[7].Q = 1.0f;
        this.dL[7].S = 40.0f;
        this.dL[7].R = 10.0f;
        this.dL[7].T = 7;
        this.dL[7].U = -1;
        this.dL[7].W = 0.0f;
        this.dL[7].X = 1.0f;
        this.dL[7].Y = 0.0f;
        this.dL[7].Z = 0.0f;
        this.dL[7].aa = 50.0f;
        this.dL[7].ab = -1;
        this.dL[7].ac = -1;
        this.dL[7].ad = 0;
        this.dL[7].ae = 5;
        this.dL[7].af = 0.5f;
        this.dL[7].ah = 10.0f;
        this.dL[7].ai = 25;
        this.dL[7].ag = 0.2f;
        this.dL[7].v = 3;
        this.dL[7].w = 5;
        this.dL[7].x = 2;
        this.dL[7].I = false;
        this.dL[8].a = "Stinger";
        this.dL[8].b = 2.5f;
        this.dL[8].c = 25;
        this.dL[8].d = 1;
        this.dL[8].e = 3;
        this.dL[8].f = 0.0f;
        this.dL[8].g = 3;
        this.dL[8].z = 1.0f;
        this.dL[8].A = 1.0f;
        this.dL[8].B = 1.0f;
        this.dL[8].C = 0.5f;
        this.dL[8].i = 9;
        this.dL[8].j = 9;
        this.dL[8].k = 5;
        this.dL[8].l = 25;
        this.dL[8].m = 35;
        this.dL[8].n = 0;
        this.dL[8].p = 6;
        this.dL[8].q = 2;
        this.dL[8].r = 0.05f;
        this.dL[8].s = 0.12f;
        this.dL[8].t = 0.02f;
        this.dL[8].u = 2.0f;
        this.dL[8].J = false;
        this.dL[8].o = 1;
        this.dL[8].h = 100;
        this.dL[8].K = true;
        this.dL[8].L = 0.0f;
        this.dL[8].M = -0.3f;
        this.dL[8].N = true;
        this.dL[8].O = 1.0f;
        this.dL[8].P = 0.6f;
        this.dL[8].Q = 0.1f;
        this.dL[8].S = 20.0f;
        this.dL[8].R = 500.0f;
        this.dL[8].T = 2;
        this.dL[8].U = 6;
        this.dL[8].W = 0.0f;
        this.dL[8].X = 1.0f;
        this.dL[8].Y = 0.6f;
        this.dL[8].Z = 0.1f;
        this.dL[8].aa = 50.0f;
        this.dL[8].ab = 10;
        this.dL[8].ac = 30;
        this.dL[8].F = -25.0f;
        this.dL[8].G = 1.5f;
        this.dL[8].H = 1.0f;
        this.dL[8].ad = 50;
        this.dL[8].ae = 0;
        this.dL[8].af = 1.0f;
        this.dL[8].ah = 1.0f;
        this.dL[8].ai = 40;
        this.dL[9].a = "Rocket Launcher";
        this.dL[9].b = 2.5f;
        this.dL[9].c = 25;
        this.dL[9].d = 1;
        this.dL[9].e = 3;
        this.dL[9].f = 0.0f;
        this.dL[9].g = 3;
        this.dL[9].z = 0.0f;
        this.dL[9].A = 0.0f;
        this.dL[9].B = 1.0f;
        this.dL[9].C = 0.5f;
        this.dL[9].i = 9;
        this.dL[9].j = 9;
        this.dL[9].k = 5;
        this.dL[9].l = 25;
        this.dL[9].m = 35;
        this.dL[9].n = 0;
        this.dL[9].p = 6;
        this.dL[9].q = 2;
        this.dL[9].r = 0.05f;
        this.dL[9].s = 0.12f;
        this.dL[9].t = 0.02f;
        this.dL[9].u = 2.0f;
        this.dL[9].J = false;
        this.dL[9].o = 1;
        this.dL[9].h = 100;
        this.dL[9].K = true;
        this.dL[9].L = -1.0f;
        this.dL[9].M = 1.5f;
        this.dL[9].N = true;
        this.dL[9].O = 1.0f;
        this.dL[9].P = 0.6f;
        this.dL[9].Q = 0.1f;
        this.dL[9].S = 20.0f;
        this.dL[9].R = 500.0f;
        this.dL[9].T = 2;
        this.dL[9].U = 6;
        this.dL[9].W = 0.0f;
        this.dL[9].X = 1.0f;
        this.dL[9].Y = 0.6f;
        this.dL[9].Z = 0.1f;
        this.dL[9].aa = 50.0f;
        this.dL[9].ab = 10;
        this.dL[9].ac = 30;
        this.dL[9].ad = 50;
        this.dL[9].ae = 0;
        this.dL[9].af = 1.0f;
        this.dL[9].ah = 1.0f;
        this.dL[9].ai = 40;
    }
    
    public final void f() {
        this.bV = new Color[25];
        for (int i = 0; i < 25; ++i) {
            this.bV[i] = new Color(0, 255 - 10 * i, 0);
            this.bW[i] = new Color(255 - 10 * i, 255 - 10 * i, 255 - 10 * i);
            this.bX[i] = new Color(255 - 10 * i, 0, 0);
        }
        this.bT = 0;
        this.bU = 1;
    }
    
    public final void g() {
        fps fps;
        Font de;
        if (fps.B > 500) {
            this.bO = true;
            this.y = 5;
            this.cR = new Font("Dialog", 1, 17);
            this.cQ = new Font("Dialog", 1, 17);
            this.cX = new Font("Dialog", 1, 21);
            this.cY = new Font("Dialog", 1, 23);
            this.dd = new Font("Helvetica", 1, 14);
            fps = this;
            de = new Font("Dialog", 1, 20);
        }
        else {
            this.bO = false;
            this.y = 3;
            this.cR = new Font("Dialog", 1, 12);
            this.cQ = new Font("Dialog", 1, 15);
            this.cX = new Font("Dialog", 1, 15);
            this.cY = new Font("Dialog", 1, 17);
            this.dd = new Font("Helvetica", 1, 10);
            fps = this;
            de = new Font("Dialog", 1, 15);
        }
        fps.de = de;
        for (int i = 0; i < this.y; ++i) {
            this.ch[i] = new m();
        }
        fps.cj = fps.z + this.y * fps.A + 5;
        fps.ck = fps.cj + 10;
        this.aB = new e(true);
        this.aC = new d[5];
        for (int j = 0; j < 5; ++j) {
            this.aC[j] = new d();
        }
        this.aD = new j[4];
        for (int k = 0; k < 4; ++k) {
            this.aD[k] = new j();
        }
        this.aE = new j();
        this.dH = new int[30];
        this.dI = new int[40];
        this.dJ = new j[40][25];
        for (int l = 0; l < 40; ++l) {
            for (int n = 0; n < 25; ++n) {
                this.dJ[l][n] = new j();
            }
        }
        this.dK = new j[11];
        for (int n2 = 0; n2 < 11; ++n2) {
            this.dK[n2] = new j();
        }
        this.cq = new boolean[17];
        for (int n3 = 0; n3 < 17; ++n3) {
            this.cq[n3] = false;
        }
        this.cr = new boolean[8];
        for (int n4 = 0; n4 < 8; ++n4) {
            this.cr[n4] = false;
        }
        this.dF = new int[10];
        this.dG = new int[10];
        this.dB = new j[10][30];
        this.dC = new j[5][10][30];
        this.dD = new j[50];
        this.dE = new j[5][50];
        for (int n5 = 0; n5 < 50; ++n5) {
            this.dD[n5] = new j();
            for (int n6 = 0; n6 < 5; ++n6) {
                this.dE[n6][n5] = new j();
            }
        }
        this.dz = new j[10];
        this.dA = new j[5][10];
        for (int n7 = 0; n7 < 10; ++n7) {
            this.dF[n7] = this.a.c();
            this.dG[n7] = this.a.c();
            this.dz[n7] = new j();
            this.dz[n7].u = false;
            for (int n8 = 0; n8 < 5; ++n8) {
                this.dA[n8][n7] = new j();
                this.dA[n8][n7].u = false;
            }
            for (int n9 = 0; n9 < 30; ++n9) {
                this.dB[n7][n9] = new j();
            }
            for (int n10 = 0; n10 < 30; ++n10) {
                for (int n11 = 0; n11 < 5; ++n11) {
                    this.dC[n11][n7][n10] = new j();
                }
            }
        }
        this.du = new h[8];
        for (int n12 = 0; n12 < 8; ++n12) {
            this.du[n12] = new h();
        }
        this.k();
        this.cD = new a[20];
        for (int n13 = 0; n13 < 20; ++n13) {
            this.cD[n13] = new a();
        }
        this.cE = new a();
        this.cF = new a();
        this.cK = new a();
        this.cG = new a[20];
        for (int n14 = 0; n14 < 20; ++n14) {
            this.cG[n14] = new a();
        }
        this.cM = new a();
        this.cH = new a[20];
        for (int n15 = 0; n15 < 20; ++n15) {
            this.cH[n15] = new a();
        }
        this.cJ = new a[10];
        for (int n16 = 0; n16 < 10; ++n16) {
            this.cJ[n16] = new a();
        }
        this.cI = new a[1];
        for (int n17 = 0; n17 < 1; ++n17) {
            this.cI[n17] = new a();
        }
        for (int n18 = 0; n18 < 5; ++n18) {
            this.aC[n18].b.g = new int[22];
        }
        this.ah = new j[5];
        for (int n19 = 0; n19 < 5; ++n19) {
            this.ah[n19] = new j();
        }
        for (int n20 = 0; n20 < 6; ++n20) {
            this.ci[n20] = new m();
        }
        this.df = new int[10];
        for (int n21 = 0; n21 < 10; ++n21) {
            this.df[n21] = n21 + 9;
        }
        this.j();
        this.h();
    }
    
    public final void h() {
        this.ba = 6;
        (this.bb = new String[this.ba])[0] = "TIP : Press 'Escape' During Gameplay To Pause Game And Open The Options Menu.";
        this.bb[1] = "TIP : If The Game Runs Too Slowly, Turn Down 'Graphics Detail' In The Options Menu.";
        this.bb[2] = "TIP : Playing On Harder Difficulty Settings Allows Faster Tournament Progression.";
        this.bb[3] = "TIP : Adjust Your 'Turn Speed' In The Options Menu. Lower Turn Speed Allows More Accurate Aiming.";
        this.bb[4] = "TIP : You Can View Scores And Level Progress By Selecting 'Display Scores' In The Options Menu.";
        this.bb[5] = "TIP : You Can Turn Off (Or Adjust) 'Frag Camera' Cut Scenes In The Options Menu.";
        this.aZ = (int)Math.round(Math.random() * this.ba);
        if (this.aZ >= this.ba) {
            this.aZ = 0;
        }
    }
    
    public final void i() {
        ++this.aZ;
        if (this.aZ >= this.ba) {
            this.aZ = 0;
        }
    }
    
    public final void j() {
        this.dO = new Image[10];
        this.bL = Color.lightGray;
        this.bM = Color.white;
        fps fps;
        int bk;
        if (fps.B < 500) {
            this.bI = 25;
            fps = this;
            bk = 12;
        }
        else {
            this.bI = 50;
            fps = this;
            bk = 25;
        }
        fps.bK = bk;
        this.bJ = this.bI / 2;
        if (this.bO) {
            fps.V = 0;
            return;
        }
        fps.V = 1;
    }
    
    public final void k() {
        this.du[0].a = 1.0f;
        this.du[0].b = -0.03f;
        this.du[0].c = 1.0f;
        this.du[0].d = 0.2f;
        this.du[1].a = 0.0f;
        this.du[1].b = 0.03f;
        this.du[1].c = 1.0f;
        this.du[1].d = 0.2f;
        this.du[2].a = 1.0f;
        this.du[2].b = -0.05f;
        this.du[2].c = 1.0f;
        this.du[2].d = 0.2f;
        this.du[3].a = 0.0f;
        this.du[3].b = 0.05f;
        this.du[3].c = 1.0f;
        this.du[3].d = 0.2f;
        this.du[4].a = 1.0f;
        this.du[4].b = -0.08f;
        this.du[4].c = 1.0f;
        this.du[4].d = 0.2f;
        this.du[5].a = 0.0f;
        this.du[5].b = 0.04f;
        this.du[5].c = 1.0f;
        this.du[5].d = 0.5f;
        this.du[6].a = 1.0f;
        this.du[6].b = -0.09f;
        this.du[6].c = 1.0f;
        this.du[6].d = 0.8f;
        this.du[7].a = 0.0f;
        this.du[7].b = 0.09f;
        this.du[7].c = 1.0f;
        this.du[7].d = 0.8f;
    }
    
    public final void l() {
        for (int i = 0; i < 8; ++i) {
            if (this.dr[i].j && this.dr[i].k < 10) {
                final h h = this.du[i];
                h.a += this.du[i].b;
                if (this.du[i].a > this.du[i].c || this.du[i].a >= 1.0f || this.du[i].a < this.du[i].d || this.du[i].a <= 0.2f) {
                    final h h2 = this.du[i];
                    h2.b *= -1.0f;
                    Label_0193: {
                        h h3;
                        float a;
                        if (this.du[i].a < 0.0f) {
                            h3 = this.du[i];
                            a = 0.0f;
                        }
                        else {
                            if (this.du[i].a <= 1.0f) {
                                break Label_0193;
                            }
                            h3 = this.du[i];
                            a = 1.0f;
                        }
                        h3.a = a;
                    }
                    if (this.du[i].a < this.du[i].d) {
                        this.du[i].a = this.du[i].d;
                    }
                }
            }
        }
        for (int j = 0; j < 12; ++j) {
            if (this.dr[j].j && this.dr[j].k > 10) {
                this.a.b(this.dr[j].a, this.dr[j].e + (float)(Math.random() * 10.0));
            }
            else if (this.dr[j].j && this.dr[j].k > -1) {
                this.a.c(this.dr[j].a, this.du[this.dr[j].k].a);
            }
        }
        final l cp = this.cp;
        cp.x += 0.2;
        if (this.cp.x > 360.0f) {
            this.cp.x = 360.0f - this.cp.x;
        }
    }
    
    public final void f(final DataInputStream dataInputStream) {
        try {
            dataInputStream.readLine();
            final StringTokenizer stringTokenizer;
            this.cO.a = Integer.parseInt((stringTokenizer = new StringTokenizer(dataInputStream.readLine())).nextToken());
            for (int i = 0; i < this.cO.a; ++i) {
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                final String nextToken3 = stringTokenizer.nextToken();
                this.cO.c[i].a = Integer.parseInt(nextToken);
                this.cO.c[i].c = Integer.parseInt(nextToken2);
                this.cO.c[i].f = Integer.parseInt(nextToken3);
            }
            new StringTokenizer(dataInputStream.readLine()).nextToken();
            dataInputStream.readLine();
            final StringTokenizer stringTokenizer2;
            final String nextToken4 = (stringTokenizer2 = new StringTokenizer(dataInputStream.readLine())).nextToken();
            stringTokenizer2.nextToken();
            dataInputStream.readLine();
            final int int1 = Integer.parseInt(nextToken4);
            this.cO.d = int1;
            for (int j = 0; j < int1; ++j) {
                dataInputStream.readLine();
                dataInputStream.readLine();
                this.a(j, dataInputStream);
            }
            dataInputStream.readLine();
            dataInputStream.readLine();
            dataInputStream.readLine();
            for (int k = 0; k < int1; ++k) {
                final StringTokenizer stringTokenizer3;
                final String nextToken5 = (stringTokenizer3 = new StringTokenizer(dataInputStream.readLine())).nextToken();
                final String nextToken6 = stringTokenizer3.nextToken();
                final int int2 = Integer.parseInt(nextToken5);
                final int int3 = Integer.parseInt(nextToken6);
                this.cN[int2].l = int3;
                for (int l = 0; l < int3; ++l) {
                    this.cN[int2].k[l] = Integer.parseInt(stringTokenizer3.nextToken());
                }
                for (int n = 0; n < int3; ++n) {
                    final String nextToken7 = stringTokenizer3.nextToken();
                    final String nextToken8 = stringTokenizer3.nextToken();
                    this.cN[int2].m[n].a = Integer.parseInt(nextToken7);
                    this.cN[int2].m[n].c = Integer.parseInt(nextToken8);
                }
            }
            dataInputStream.readLine();
            final int int4 = Integer.parseInt(new StringTokenizer(dataInputStream.readLine()).nextToken());
            dataInputStream.readLine();
            dataInputStream.readLine();
            if (int4 > 0) {
                for (int n2 = 0; n2 < int4; ++n2) {
                    final StringTokenizer stringTokenizer4;
                    final int int5 = Integer.parseInt((stringTokenizer4 = new StringTokenizer(dataInputStream.readLine())).nextToken());
                    final int int6 = Integer.parseInt(stringTokenizer4.nextToken());
                    this.cN[int5].n = int6;
                    for (int n3 = 0; n3 < int6; ++n3) {
                        this.cN[int5].o[n3].a = Integer.parseInt(stringTokenizer4.nextToken());
                        this.cN[int5].o[n3].c = Integer.parseInt(stringTokenizer4.nextToken());
                    }
                }
            }
            dataInputStream.readLine();
            this.cO.e = Integer.parseInt(new StringTokenizer(dataInputStream.readLine()).nextToken());
            dataInputStream.readLine();
            dataInputStream.readLine();
            if (this.cO.e > 0) {
                for (int n4 = 0; n4 < this.cO.e; ++n4) {
                    final StringTokenizer stringTokenizer5;
                    this.cO.f[n4].g = Integer.parseInt((stringTokenizer5 = new StringTokenizer(dataInputStream.readLine())).nextToken());
                    this.cO.f[n4].a = Integer.parseInt(stringTokenizer5.nextToken());
                    this.cO.f[n4].c = Integer.parseInt(stringTokenizer5.nextToken());
                    this.cO.f[n4].i = Integer.parseInt(stringTokenizer5.nextToken());
                    this.cO.f[n4].j = Integer.parseInt(stringTokenizer5.nextToken());
                    this.cO.f[n4].k = Integer.parseInt(stringTokenizer5.nextToken());
                    this.cO.f[n4].h = this.a(this.cO.f[n4].g, this.cO.f[n4].a, this.cO.f[n4].c, this.cO.f[n4].i, this.cO.f[n4].j, this.cO.f[n4].k, n4);
                }
            }
        }
        catch (IOException ex) {
            System.out.println("!");
        }
    }
    
    public final e a(final float n, final float n2, final int n3, e e, final int n4) {
        if (n3 != -1 && this.cN[n3].t > 0) {
            for (int i = 0; i < this.cN[n3].t; ++i) {
                if (this.cN[n3].u[i].E > -1 && a(n, n2, this.cN[n3].u[i].a - 4.5f, this.cN[n3].u[i].c - 4.5f, this.cN[n3].u[i].a + 4.5f, this.cN[n3].u[i].c + 4.5f)) {
                    if (n4 == -1) {
                        this.ag(5);
                        this.by = 10;
                    }
                    this.a.a(this.cN[n3].u[i].w, false);
                    this.cN[n3].u[i].E = this.cN[n3].u[i].J;
                    this.a(this.dJ[this.cN[n3].u[i].H], 25);
                    if (this.cN[n3].u[i].I == 0) {
                        final e e2 = e;
                        e2.z += this.cN[n3].u[i].G;
                        Label_0310: {
                            e e3;
                            int z;
                            if (e.M > -1) {
                                if (e.z <= 200) {
                                    break Label_0310;
                                }
                                e3 = e;
                                z = 200;
                            }
                            else {
                                if (e.z <= 100) {
                                    break Label_0310;
                                }
                                e3 = e;
                                z = 100;
                            }
                            e3.z = z;
                        }
                        if (n4 == -1) {
                            this.cf.a("Got Health Pack 100", fps.cj, Color.white, 200, fps.B);
                            this.cg.a("", fps.ck, Color.white, 10, fps.B);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 1) {
                        final e e4 = e;
                        e4.A += this.cN[n3].u[i].G;
                        if (e.A > 100) {
                            e.A = 100;
                        }
                        if (n4 == -1) {
                            this.cf.a("Got Armour Pack 100", fps.cj, Color.white, 200, fps.B);
                            this.cg.a("", fps.ck, Color.white, 10, fps.B);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 2) {
                        e.D[2] = true;
                        final int[] c = e.C;
                        final int n5 = 2;
                        c[n5] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(2);
                        }
                        if (n4 == -1) {
                            this.cf.a("You got the " + this.dL[2].a + " with " + this.cN[n3].u[i].G + " rounds", fps.cj, Color.white, 200, fps.B);
                            this.cg.a("", fps.ck, Color.white, 10, fps.B);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 3) {
                        e.D[1] = true;
                        final int[] c2 = e.C;
                        final int n6 = 1;
                        c2[n6] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(1);
                        }
                        if (n4 == -1) {
                            this.cf.a("You got the " + this.dL[1].a + " with " + this.cN[n3].u[i].G + " rounds", fps.cj, Color.white, 200, fps.B);
                            this.cg.a("", fps.ck, Color.white, 10, fps.B);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 4) {
                        e.D[3] = true;
                        final int[] c3 = e.C;
                        final int n7 = 3;
                        c3[n7] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(3);
                        }
                        if (n4 == -1) {
                            this.cf.a("You got the " + this.dL[3].a + " with " + this.cN[n3].u[i].G + " rounds", fps.cj, Color.white, 200, fps.B);
                            this.cg.a("", fps.ck, Color.white, 10, fps.B);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 5) {
                        e.D[4] = true;
                        final int[] c4 = e.C;
                        final int n8 = 4;
                        c4[n8] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(4);
                        }
                        if (n4 == -1) {
                            this.cf.a("You got the " + this.dL[4].a + " with " + this.cN[n3].u[i].G + " rounds", fps.cj, Color.white, 200, fps.B);
                            this.cg.a("", fps.ck, Color.white, 10, fps.B);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 6) {
                        e.D[5] = true;
                        final int[] c5 = e.C;
                        final int n9 = 5;
                        c5[n9] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(5);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 7) {
                        e.D[6] = true;
                        final int[] c6 = e.C;
                        final int n10 = 6;
                        c6[n10] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(6);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 8) {
                        e.D[7] = true;
                        final int[] c7 = e.C;
                        final int n11 = 7;
                        c7[n11] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(7);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 9) {
                        e.D[8] = true;
                        final int[] c8 = e.C;
                        final int n12 = 8;
                        c8[n12] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(8);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 10) {
                        e.D[9] = true;
                        final int[] c9 = e.C;
                        final int n13 = 9;
                        c9[n13] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(9);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 11) {
                        e.c = true;
                        final int[] c10 = e.C;
                        final int n14 = 0;
                        c10[n14] += this.cN[n3].u[i].G;
                        if (n4 != -1) {
                            e = this.a(e, n4);
                        }
                        else {
                            this.j(0);
                        }
                    }
                    else if (this.cN[n3].u[i].I == 30) {
                        this.ab(n4);
                    }
                    else if (this.cN[n3].u[i].I == 31) {
                        this.V(n4);
                    }
                    else if (this.cN[n3].u[i].I == 32) {
                        this.Z(n4);
                    }
                    else if (this.cN[n3].u[i].I == 33) {
                        this.aa(n4);
                    }
                }
            }
        }
        return e;
    }
    
    public final int a(final int n, final int n2, final int n3, final int i, final int g, final int e, final int h) {
        final b b = this.cN[n];
        ++b.t;
        this.cN[n].u[this.cN[n].t - 1].a = n2;
        this.cN[n].u[this.cN[n].t - 1].c = n3;
        this.cN[n].u[this.cN[n].t - 1].I = i;
        this.cN[n].u[this.cN[n].t - 1].G = g;
        this.cN[n].u[this.cN[n].t - 1].E = e;
        this.cN[n].u[this.cN[n].t - 1].J = e * -1;
        this.cN[n].u[this.cN[n].t - 1].v = this.a.e();
        this.cN[n].u[this.cN[n].t - 1].u = true;
        this.cN[n].u[this.cN[n].t - 1].w = this.dI[i];
        this.cN[n].u[this.cN[n].t - 1].H = h;
        return this.cN[n].t - 1;
    }
    
    public final void a(final DataInputStream dataInputStream, final int b) {
        System.out.println("");
        this.cO = new k();
        System.out.println(" .");
        this.cN = new b[22];
        for (int i = 0; i < 22; ++i) {
            this.cN[i] = new b(i);
        }
        this.cO.b = b;
        this.f(dataInputStream);
    }
    
    public static e a(final e e) {
        for (int i = 0; i < 10; ++i) {
            e.D[i] = false;
            e.C[i] = 0;
        }
        e.b = 0;
        e.c = false;
        e.C[0] = 99;
        e.z = 100;
        e.A = 0;
        e.B = -1;
        e.K = -1;
        e.L = -1;
        return e;
    }
    
    public final j a(final j j) {
        j.a = this.cO.c[this.cB].a;
        j.c = this.cO.c[this.cB].c;
        j.e = this.cO.c[this.cB].f;
        j.d = 0.0f;
        j.f = 0.0f;
        ++this.cB;
        if (this.cB > this.cO.a - 1) {
            this.cB = 0;
        }
        return j;
    }
    
    public final j a(final j j, final int n) {
        j.a = this.cO.c[n].a;
        j.c = this.cO.c[n].c;
        j.e = this.cO.c[n].f;
        j.d = 0.0f;
        j.f = 0.0f;
        return j;
    }
    
    public final void a(final int n, final boolean b) {
        this.ae.b = 4.0f;
        fps fps;
        j ae;
        if (n > -1) {
            fps = this;
            ae = this.a(this.ae, n);
        }
        else {
            fps = this;
            ae = this.a(this.ae);
        }
        fps.ae = ae;
        this.ae.l = this.a(this.ae.a, this.ae.c);
        this.aB = a(this.aB);
        this.y();
        this.a(this.cN[this.ae.l].i, this.cN[this.ae.l].j);
        for (int i = 0; i < fps.F; ++i) {
            this.g(i);
        }
        this.w();
        this.s();
        this.r();
        this.d(this.cN[this.ae.l].i, this.cN[this.ae.l].j);
        this.aB = this.b(this.aB);
        for (int j = 0; j < fps.F; ++j) {
            this.aC[j].c = this.b(this.aC[j].c);
        }
        this.v();
        this.aB.D[0] = true;
        this.aB.c = false;
        this.aB.x = -1;
        this.aB.F = 0.75f;
        this.aB.G = 0.4f;
        this.ac(-1);
        if (b) {
            this.ag(11);
        }
        this.cf.a("B  E  G  I  N  !", fps.cj, Color.white, 200, fps.B);
    }
    
    public final void m() {
        this.ae = new j(0.0f, 4.0f, 0.0f, 0.0f);
        this.ae.v = this.a.e();
        this.af = new j(0.0f, 4.0f, 0.0f, 0.0f);
        this.af.v = this.a.e();
    }
    
    public static void n() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("FPS Engine - by Ben Librojo. Copyright (c) 2003-2006. All Rights Reserved.");
        System.out.println("");
        System.out.println("Ancient Arenas : Copyright (c) 2006.");
        System.out.println("by");
        System.out.println("Ben Librojo");
        System.out.println("version 1.02 : March 12 2006.");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
    public final void o() {
        this.au = this.a.b();
        this.a.a(this.au, 0.25f);
        this.a.c(this.au, 0.0f, 0.0f, 1.0f);
        this.av = this.a.b();
        this.a.a(this.av, 0.95f);
        this.a.c(this.av, 1.0f, 1.0f, 1.0f);
        this.ax = this.a.b();
        this.a.a(this.ax, 0.25f);
        this.a.c(this.ax, 1.0f, 0.0f, 1.0f);
        this.aw = this.a.b();
        this.a.a(this.aw, 0.25f);
        this.a.c(this.aw, 1.0f, 1.0f, 0.0f);
        this.ay = this.a.b();
        this.a.a(this.ay, 0.0f);
        this.a.c(this.ay, 0.75f, 0.0f, 0.0f);
    }
    
    public final void p() {
        final int b = this.a.b();
        this.a.c(b, 1.0f, 1.0f, 1.0f);
        this.o[0] = b;
        this.o[1] = b;
        this.o[2] = b;
        this.o[3] = b;
        this.al = this.a.b();
        this.a.a(this.al, 0.25f);
        this.a.c(this.al, 1.0f, 0.0f, 0.0f);
        this.as = this.a.b();
        this.a.a(this.as, 0.0f);
        this.a.c(this.as, 1.0f, 1.0f, 1.0f);
        this.at = this.a.b();
        this.a.a(this.at, 0.5f);
        this.a.c(this.at, 1.0f, 1.0f, 1.0f);
        this.am = this.a.b();
        this.a.a(this.am, 0.75f);
        this.a.c(this.am, 1.0f, 1.0f, 0.1f);
        this.ar = this.a.b();
        this.a.c(this.ar, 1.0f, 0.95f, 0.6f);
        this.ap = this.a.b();
        this.a.a(this.ap, 0.5f);
        this.a.c(this.ap, 0.0f, 0.0f, 1.0f);
        this.an = this.a.b();
        this.a.a(this.an, 0.25f);
        this.a.c(this.an, 0.5f, 0.5f, 0.5f);
        this.ao = this.a.b();
        this.a.a(this.ao, 0.75f);
        this.a.c(this.ao, 1.0f, 1.0f, 1.0f);
        this.az = new int[20];
        for (int i = 0; i < 20; ++i) {
            this.az[i] = this.a.b();
            this.a.a(this.az[i], 5.0f * i / 100.0f);
            this.a.c(this.az[i], 1.0f, 1.0f, 1.0f);
        }
        this.aA = new int[20];
        for (int j = 0; j < 20; ++j) {
            this.aA[j] = this.a.b();
            this.a.a(this.aA[j], 4.0f * j / 100.0f);
            this.a.c(this.aA[j], 1.0f, 0.0f, 0.0f);
        }
        this.aq = this.a.b();
        this.a.a(this.aq, 0.0f);
        this.a.c(this.aq, 1.0f, 1.0f, 1.0f);
        this.o();
    }
    
    public final void q() {
        for (int i = 0; i < 12; ++i) {
            this.dr[i].j = false;
            this.a.b(this.dr[i].a, false);
            this.a.c(this.aj, this.dr[i].a);
        }
    }
    
    public final void r() {
        this.dv = this.a.c();
        this.a.b(this.dv, 80.0f);
        this.a.b(this.dv, 0.0f, 0.0f, 1.0f);
        this.a.a(this.dv, 0.0f, 0.0f, 0.0f);
        this.a.c(this.dv, 1.0f);
        this.a.a(this.aj, this.dv);
        this.a.b(this.dv, false);
        this.dw = this.a.c();
        this.a.b(this.dw, 80.0f);
        this.a.b(this.dw, 1.0f, 0.0f, 1.0f);
        this.a.a(this.dw, 0.0f, 0.0f, 0.0f);
        this.a.c(this.dw, 1.0f);
        this.a.a(this.aj, this.dw);
        this.a.b(this.dw, false);
        this.dx = this.a.c();
        this.a.b(this.dx, 80.0f);
        this.a.b(this.dx, 1.0f, 1.0f, 0.0f);
        this.a.a(this.dx, 0.0f, 0.0f, 0.0f);
        this.a.c(this.dx, 1.0f);
        this.a.a(this.aj, this.dx);
        this.a.b(this.dx, false);
    }
    
    public final void s() {
        this.dr = new n[12];
        for (int i = 0; i < 12; ++i) {
            this.dr[i] = new n();
        }
    }
    
    public final int t() {
        int n;
        for (n = 0; this.dr[n].j; ++n) {}
        if (n <= 12 && !this.dr[n].j) {
            return n;
        }
        return -1;
    }
    
    public final void b(final int n) {
        this.ds = 0;
        for (int i = 0; i < this.cN[n].A; ++i) {
            final int t;
            if ((t = this.t()) > -1) {
                this.dr[t].a = this.cN[n].B[i].a;
                this.dr[t].b = this.cN[n].B[i].b;
                this.dr[t].c = this.cN[n].B[i].c;
                this.dr[t].d = this.cN[n].B[i].d;
                this.dr[t].g = this.cN[n].B[i].g / 100.0f;
                this.dr[t].h = this.cN[n].B[i].h / 100.0f;
                this.dr[t].i = this.cN[n].B[i].i / 100.0f;
                this.dr[t].e = this.cN[n].B[i].e;
                this.dr[t].f = this.cN[n].B[i].f / 100.0f;
                this.dr[t].j = true;
                this.dr[t].k = this.cN[n].B[i].k;
                this.dr[t].a = this.a.c();
                this.a.b(this.dr[t].a, (float)this.dr[t].e);
                this.a.b(this.dr[t].a, this.dr[t].g, this.dr[t].h, this.dr[t].i);
                this.a.a(this.dr[t].a, this.dr[t].b, this.dr[t].c, this.dr[t].d);
                this.a.c(this.dr[t].a, this.dr[t].f);
                this.a.b(this.dr[t].a, true);
                this.a.a(this.aj, this.dr[t].a);
                ++this.ds;
            }
        }
    }
    
    public final e b(final e e) {
        e.i = this.a.c();
        this.a.a(this.aj, e.i);
        this.a.b(e.i, false);
        e.k = false;
        return e;
    }
    
    public final e a(final e e, final float n, final float n2, final int n3) {
        this.a.b(e.i, this.dL[n3].aa);
        this.a.b(e.i, this.dL[n3].X, this.dL[n3].Y, this.dL[n3].Z);
        this.a.a(e.i, n, 10.0f, n2);
        this.a.c(e.i, this.dL[n3].W);
        this.a.b(e.i, true);
        e.k = true;
        e.j = this.dL[n3].W;
        this.a.a(this.aj, e.i);
        return e;
    }
    
    public final e c(final e e) {
        e.j -= 0.05;
        if (e.j > 0.05) {
            this.a.c(e.i, e.j);
        }
        else {
            e.k = false;
            this.a.b(e.i, false);
        }
        return e;
    }
    
    public final int g(final DataInputStream dataInputStream) {
        int int1 = 0;
        try {
            final StringTokenizer stringTokenizer;
            final String nextToken = (stringTokenizer = new StringTokenizer(dataInputStream.readLine())).nextToken();
            stringTokenizer.nextToken();
            dataInputStream.readLine();
            dataInputStream.readLine();
            for (int int2 = Integer.parseInt(nextToken), i = 0; i < int2; ++i) {
                boolean b = false;
                final StringTokenizer stringTokenizer2;
                new StringBuffer("loadresources/models/").append((stringTokenizer2 = new StringTokenizer(dataInputStream.readLine())).nextToken()).toString();
                final String nextToken2 = stringTokenizer2.nextToken();
                final String nextToken3 = stringTokenizer2.nextToken();
                final String nextToken4 = stringTokenizer2.nextToken();
                final String nextToken5 = stringTokenizer2.nextToken();
                final String nextToken6 = stringTokenizer2.nextToken();
                final String nextToken7 = stringTokenizer2.nextToken();
                final String nextToken8 = stringTokenizer2.nextToken();
                final String nextToken9 = stringTokenizer2.nextToken();
                final String nextToken10 = stringTokenizer2.nextToken();
                final String nextToken11 = stringTokenizer2.nextToken();
                Integer.parseInt(nextToken2);
                fps fps;
                DataInputStream dataInputStream2;
                boolean b2;
                if (Integer.parseInt(nextToken3) > 0) {
                    fps = this;
                    dataInputStream2 = dataInputStream;
                    b2 = true;
                }
                else {
                    fps = this;
                    dataInputStream2 = dataInputStream;
                    b2 = false;
                }
                fps.a(dataInputStream2, b2);
                this.ag[i] = new j(0.0f, 0.0f, 0.0f, 0.0f);
                this.ag[i].v = this.a.e();
                final Object a = a3dAPI.c.a(this.e, a3dAPI.c.a(this.j, true), 0.0f);
                this.p = new int[8];
                final float[] array = { Integer.parseInt(nextToken4) / 100.0f, Integer.parseInt(nextToken5) / 100.0f, Integer.parseInt(nextToken6) / 100.0f, Integer.parseInt(nextToken7) / 100.0f, Integer.parseInt(nextToken8) / 100.0f, Integer.parseInt(nextToken9) / 100.0f, Integer.parseInt(nextToken10) / 100.0f, Integer.parseInt(nextToken11) / 100.0f };
                final StringTokenizer stringTokenizer3;
                final int int3 = Integer.parseInt((stringTokenizer3 = new StringTokenizer(dataInputStream.readLine())).nextToken());
                final int int4 = Integer.parseInt(stringTokenizer3.nextToken());
                final int[] array2 = new int[12];
                if (int3 > 1) {
                    System.out.println(" .");
                    b = true;
                    Integer.parseInt(stringTokenizer3.nextToken());
                }
                else {
                    for (int j = 0; j < int4; ++j) {
                        array2[j] = this.cD[Integer.parseInt(stringTokenizer3.nextToken())].a;
                    }
                }
                j k;
                a a2;
                float[] array3;
                int[] array4;
                float[] array5;
                int[] array6;
                float[] array7;
                int[] array8;
                float[] b3;
                int[] a3;
                int[] p;
                if (int3 == 1) {
                    k = this.ag[i];
                    a2 = this.a;
                    array3 = this.e;
                    array4 = a3dAPI.c.a(this.j, true);
                    array5 = null;
                    array6 = null;
                    array7 = array;
                    array8 = a3dAPI.c.a(this.n, true);
                    b3 = a3dAPI.c.b(a);
                    a3 = a3dAPI.c.a(a);
                    p = this.p;
                }
                else {
                    if (b) {
                        this.ag[i].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, array, a3dAPI.c.a(this.n, true), null, null, null, null);
                        this.a.b(this.ag[i].w, this.cL[0][3].a);
                        continue;
                    }
                    k = this.ag[i];
                    a2 = this.a;
                    array3 = this.e;
                    array4 = a3dAPI.c.a(this.j, true);
                    array5 = null;
                    array6 = null;
                    array7 = array;
                    array8 = a3dAPI.c.a(this.n, true);
                    b3 = null;
                    a3 = null;
                    p = null;
                }
                k.w = a2.a(array3, array4, array5, array6, array7, array8, b3, a3, p, array2);
            }
            dataInputStream.readLine();
            int1 = Integer.parseInt(new StringTokenizer(dataInputStream.readLine()).nextToken());
            dataInputStream.readLine();
            dataInputStream.readLine();
            for (int l = 0; l < int1; ++l) {
                final StringTokenizer stringTokenizer4;
                this.ah[l].z = Integer.parseInt((stringTokenizer4 = new StringTokenizer(dataInputStream.readLine())).nextToken());
                this.ah[l].B = Integer.parseInt(stringTokenizer4.nextToken());
                this.ah[l].C = Integer.parseInt(stringTokenizer4.nextToken());
                this.ah[l].D = this.ah[l].C;
                this.ah[l].E = Integer.parseInt(stringTokenizer4.nextToken());
                this.ah[l].n = Integer.parseInt(stringTokenizer4.nextToken());
            }
            System.out.println(" .");
        }
        catch (IOException ex) {
            System.out.println("!");
            return int1;
        }
        return int1;
    }
    
    public final int h(final DataInputStream dataInputStream) {
        this.ag = new j[25];
        System.out.println(" .");
        return this.g(dataInputStream);
    }
    
    public final void i(final DataInputStream dataInputStream) {
        System.out.println(" .");
        this.a(dataInputStream, true);
        for (int i = 0; i < 10; ++i) {
            this.dz[i].v = this.a.e();
            this.dz[i].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
            this.a.d(this.dz[i].w, this.am);
            for (int j = 0; j < 5; ++j) {
                this.dA[j][i].v = this.a.e();
                this.dA[j][i].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
                this.a.d(this.dA[j][i].w, this.am);
            }
        }
        System.out.println(" .");
        if (this.bp) {
            this.b();
        }
        System.out.println(" .");
        this.a(dataInputStream, true);
        for (int k = 0; k < 10; ++k) {
            for (int l = 0; l < 30; ++l) {
                this.dB[k][l].v = this.a.e();
                this.dB[k][l].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
                this.a.b(this.dB[k][l].w, this.cD[6].a);
                for (int n = 0; n < 5; ++n) {
                    this.dC[n][k][l].v = this.a.e();
                    this.dC[n][k][l].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
                    this.a.b(this.dC[n][k][l].w, this.cD[6].a);
                }
            }
        }
        System.out.println(" .");
        if (this.bp) {
            this.b();
        }
        System.out.println(" .");
        this.a(dataInputStream, true);
        for (int n2 = 0; n2 < 50; ++n2) {
            this.dD[n2].v = this.a.e();
            this.dD[n2].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
            for (int n3 = 0; n3 < 5; ++n3) {
                this.dE[n3][n2].v = this.a.e();
                this.dE[n3][n2].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
            }
        }
        System.out.println(" .");
        if (this.bp) {
            this.b();
        }
        System.out.println(" .");
        for (int n4 = 0; n4 < 20; ++n4) {
            for (int n5 = 0; n5 < 5; ++n5) {
                this.aC[n5].o[n4].v = this.a.e();
                this.aC[n5].o[n4].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
                this.a.b(this.aC[n5].o[n4].w, this.cG[6].a);
            }
        }
        System.out.println(" .");
        if (this.bp) {
            this.b();
        }
        System.out.println(" .");
        for (int n6 = 0; n6 < 100; ++n6) {
            for (int n7 = 0; n7 < 5; ++n7) {
                this.aC[n7].k[n6].v = this.a.e();
                this.aC[n7].k[n6].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
                this.a.b(this.aC[n7].k[n6].w, this.cG[6].a);
            }
        }
        System.out.println(" .");
        if (this.bp) {
            this.b();
        }
        System.out.println(" .");
        for (int n8 = 0; n8 < 50; ++n8) {
            this.aB.l[n8].v = this.a.e();
            this.aB.l[n8].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
        }
        for (int n9 = 0; n9 < 20; ++n9) {
            for (int n10 = 0; n10 < 5; ++n10) {
                this.aC[n10].c.l[n9].v = this.a.e();
                this.aC[n10].c.l[n9].w = this.a.a(this.e, a3dAPI.c.a(this.j, true), null, null, this.k, a3dAPI.c.a(this.m, true), null, null, null, null);
            }
        }
        System.out.println(" .");
        if (this.bp) {
            this.b();
        }
    }
    
    public final void u() {
        if (this.aB.c && this.aB.b == 0) {
            this.a(this.ae.e, this.dK[this.aB.b].v, this.ae.a + (float)(Math.sin((-this.ae.e + 10.0f) * 0.0174444) * this.dL[this.aB.b].M), this.ae.b + this.dL[this.aB.b].L + this.aB.H * 0.85f, this.ae.c - (float)(Math.cos((-this.ae.e + 10.0f) * 0.0174444) * this.dL[this.aB.b].M));
            this.a(this.ae.e, this.dK[10].v, this.ae.a + (float)(Math.sin((-this.ae.e - 10.0f) * 0.0174444) * this.dL[this.aB.b].M), this.ae.b + this.dL[this.aB.b].L + this.aB.H * 0.85f, this.ae.c - (float)(Math.cos((-this.ae.e - 10.0f) * 0.0174444) * this.dL[this.aB.b].M));
            return;
        }
        this.a(this.ae.e, this.dK[this.aB.b].v, this.ae.a + (float)(Math.sin(-this.ae.e * 0.0174444) * this.dL[this.aB.b].M), this.ae.b + this.dL[this.aB.b].L + this.aB.H * 0.85f, this.ae.c - (float)(Math.cos(-this.ae.e * 0.0174444) * this.dL[this.aB.b].M));
    }
    
    public final void c(final int n) {
        if (this.aC[n].c.B < 0) {
            this.a(this.aC[n].a.e, this.aC[n].a.v, this.aC[n].a.a, this.aC[n].a.b, this.aC[n].a.c, 1.3f);
        }
        else {
            this.a(this.aC[n].a.d, this.aC[n].a.e, 0.0f, this.aC[n].a.v, this.aC[n].a.a, this.aC[n].a.b + 1.0f, this.aC[n].a.c, 1.3f);
        }
        if (this.aC[n].h > -1) {
            for (int i = 0; i < 10; ++i) {
                if (this.aC[n].i || i == 0 || i == 9 || i == 10) {
                    this.a(this.aC[n].f[i].e, this.aC[n].f[i].f, this.aC[n].f[i].v, this.aC[n].f[i].a, this.aC[n].f[i].b, this.aC[n].f[i].c, 1.0f);
                }
                else {
                    this.a(this.aC[n].f[i].e, this.aC[n].f[i].f, this.aC[n].f[i].v, this.aC[n].f[i].a, this.aC[n].f[i].b, this.aC[n].f[i].c, 0.3f, 1.0f, 0.3f);
                }
            }
        }
    }
    
    public final void v() {
        for (int i = 0; i < this.cO.d; ++i) {
            if (this.cN[i].t > 0) {
                for (int j = 0; j < this.cN[i].t; ++j) {
                    this.cN[i].u[j].i = 0.05f;
                    this.cN[i].u[j].p = 0;
                    this.cN[i].u[j].b = 0.0f;
                    this.a.a(this.aj, this.cN[i].u[j].v);
                    this.a.a(this.cN[i].u[j].v, this.cN[i].u[j].w);
                    if (this.cN[i].u[j].E > -1) {
                        this.a.a(this.cN[i].u[j].w, true);
                        this.a(this.dJ[this.cN[i].u[j].H], 25, 1000, 0);
                    }
                }
            }
        }
    }
    
    public final void w() {
        for (int i = 0; i < 11; ++i) {
            this.a.a(this.aj, this.dK[i].v);
            this.a.a(this.dK[i].v, this.dK[i].w);
            a a;
            int n;
            boolean b;
            if (i != this.aB.b) {
                a = this.a;
                n = this.dK[i].w;
                b = false;
            }
            else {
                if (this.aB.B >= 0) {
                    continue;
                }
                a = this.a;
                n = this.dK[i].w;
                b = true;
            }
            a.a(n, b);
        }
        if (this.aB.c && this.aB.b == 0) {
            this.a.a(this.dK[10].w, true);
        }
    }
    
    public final void x() {
        for (int i = 0; i < 10; ++i) {
            if (i == this.aB.b) {
                this.a.a(this.dK[i].w, false);
            }
        }
        if (this.aB.c && this.aB.b == 0) {
            this.a.a(this.dK[10].w, false);
        }
    }
    
    public final void b(final int n, final int n2) {
        this.a.a(this.aC[n].a.w, false);
        this.a.a(this.aC[n].a.v, this.aD[n2].w);
        this.a.a(this.aD[n2].w, true);
    }
    
    public final void c(final int n, final int n2) {
        this.a.c(this.aC[n].a.v, this.aD[n2].w);
        this.a.a(this.aD[n2].w, false);
        this.a.a(this.aC[n].a.w, true);
    }
    
    public final void d(final int n) {
        this.a.a(this.aj, this.aC[n].a.v);
        this.a.a(this.aC[n].a.v, this.aC[n].a.w);
        this.a.a(this.aC[n].a.w, true);
        if (this.aC[n].c.B > -1 && this.aC[n].j) {
            this.a.a(this.aC[n].a.w, false);
        }
        if (this.aC[n].c.K > -1) {
            this.b(n, 0);
        }
        if (this.aC[n].c.L > -1) {
            this.b(n, 1);
        }
        if (this.aC[n].c.M > -1) {
            this.b(n, 2);
        }
        if (this.aC[n].c.O > -1) {
            this.b(n, 3);
        }
        for (int i = 0; i < 10; ++i) {
            this.a.a(this.aj, this.aC[n].f[i].v);
            this.a.a(this.aC[n].f[i].v, this.aC[n].e[i]);
            this.a.a(this.aC[n].e[i], false);
        }
    }
    
    public final void e(final int n) {
        this.a.a(this.aC[n].a.w, false);
    }
    
    public final void f(final int n) {
        this.a.c(this.aj, this.aC[n].a.v);
        this.a.c(this.aC[n].a.v, this.aC[n].a.w);
        this.d(n);
    }
    
    public final void g(final int n) {
        if (this.aC[n].c.s < 0) {
            this.a.c(this.aj, this.aC[n].a.v);
            this.a.c(this.aC[n].a.v, this.aC[n].a.w);
            this.d(n);
        }
    }
    
    public final void d(final int n, final int n2) {
        this.a.a(this.dK[n].w, false);
        if (n == 0 && n2 != 0 && this.aB.c) {
            this.a.a(this.dK[10].w, false);
        }
        this.a.a(this.dK[n2].w, true);
        if (n2 == 0 && this.aB.c) {
            this.a.a(this.dK[10].w, true);
        }
    }
    
    public final void a(final int n, final int[] array) {
        this.aj = this.a.e();
        this.ak = this.a.h();
        this.af.w = this.a.h();
        this.ai = this.a.e();
        this.a.a(this.aj, this.ai);
        this.a.a(this.ai, this.ak);
        this.af.v = this.a.e();
        this.a.a(this.aj, this.af.v);
        this.a.a(this.af.v, this.af.w);
        for (int i = 0; i < n; ++i) {
            this.cN[array[i]].f = true;
            this.cN[array[i]].g = true;
            ++this.cd;
            for (int j = 0; j < 76; ++j) {
                if (this.cN[array[i]].h[j].u) {
                    this.a.a(this.aj, this.cN[array[i]].h[j].v);
                    this.a.a(this.cN[array[i]].h[j].v, this.ag[this.cN[array[i]].h[j].n].w);
                    a a;
                    int n2;
                    boolean b;
                    if (this.cN[array[i]].h[j].o <= this.cs) {
                        a = this.a;
                        n2 = this.cN[array[i]].h[j].w;
                        b = true;
                    }
                    else {
                        a = this.a;
                        n2 = this.cN[array[i]].h[j].w;
                        b = false;
                    }
                    a.a(n2, b);
                }
            }
        }
        this.a.b(this.aj);
        this.a.a(this.ak);
    }
    
    public final void h(final int n) {
        if (!this.cN[n].g) {
            this.cN[n].g = true;
            ++this.cd;
            for (int i = 0; i < 76; ++i) {
                if (this.cN[n].h[i].u) {
                    this.a.a(this.aj, this.cN[n].h[i].v);
                    this.a.a(this.cN[n].h[i].v, this.ag[this.cN[n].h[i].n].w);
                    a a;
                    int n2;
                    boolean b;
                    if (this.cN[n].h[i].o <= this.cs) {
                        a = this.a;
                        n2 = this.ag[this.cN[n].h[i].n].w;
                        b = true;
                    }
                    else {
                        a = this.a;
                        n2 = this.ag[this.cN[n].h[i].n].w;
                        b = false;
                    }
                    a.a(n2, b);
                }
            }
        }
    }
    
    public final void i(final int n) {
        if (this.cN[n].g) {
            this.cN[n].g = false;
            --this.cd;
            for (int i = 0; i < 76; ++i) {
                if (this.cN[n].h[i].u) {
                    this.a.c(this.cN[n].h[i].v, this.ag[this.cN[n].h[i].n].w);
                    this.a.c(this.aj, this.cN[n].h[i].v);
                }
            }
        }
    }
    
    public final void y() {
        for (int i = 0; i < 22; ++i) {
            this.i(i);
        }
        this.cd = 0;
    }
    
    public final void a(final float n, final float n2, final int n3, final float n4, final float n5, final float n6) {
        a3dAPI.c.c(a(n), this.s);
        a3dAPI.c.b(a(n2), this.t);
        a3dAPI.c.b(n4, n5, n6, this.u);
        a3dAPI.c.a(this.s, this.t, this.w);
        a3dAPI.c.a(this.t, this.u, this.w);
        this.a.a(n3, this.w);
    }
    
    public final void a(final float n, final int n2, final float n3, final float n4, final float n5) {
        a3dAPI.c.c(a(n), this.s);
        a3dAPI.c.b(n3, n4, n5, this.u);
        a3dAPI.c.a(this.s, this.u, this.w);
        this.a.a(n2, this.w);
    }
    
    public final void a(final float n, final int n2, final float n3, final float n4, final float n5, final float n6) {
        a3dAPI.c.a(0.0f, this.r);
        a3dAPI.c.c(a(n), this.s);
        a3dAPI.c.b(0.0f, this.t);
        a3dAPI.c.b(n3, n4, n5, this.u);
        a3dAPI.c.a(n6, n6, n6, this.v);
        a3dAPI.c.a(this.v, this.s, this.w);
        a3dAPI.c.a(this.w, this.u, this.w);
        this.a.a(n2, this.w);
    }
    
    public final void a(final float n, final float n2, final float n3, final int n4, final float n5, final float n6, final float n7) {
        a3dAPI.c.a(-n, this.r);
        a3dAPI.c.c(-n2, this.s);
        a3dAPI.c.b(-n3, this.t);
        a3dAPI.c.b(-n5, -n6, -n7, this.u);
        a3dAPI.c.a(this.u, this.s, this.w);
        a3dAPI.c.a(this.w, this.t, this.w);
        a3dAPI.c.a(this.w, this.r, this.w);
        this.a.b(n4, this.w);
    }
    
    public final void a(final float n, final float n2, final float n3, final int n4, final float n5, final float n6, final float n7, final float n8) {
        a3dAPI.c.a(a(n), this.r);
        a3dAPI.c.c(a(n2), this.s);
        a3dAPI.c.b(a(n3), this.t);
        a3dAPI.c.b(n5, n6, n7, this.u);
        a3dAPI.c.a(n8, n8, n8, this.v);
        a3dAPI.c.a(this.r, this.s, this.w);
        a3dAPI.c.a(this.w, this.t, this.w);
        a3dAPI.c.a(this.w, this.v, this.w);
        a3dAPI.c.a(this.w, this.u, this.w);
        this.a.a(n4, this.w);
    }
    
    public final void a(final float n, final float n2, final int n3, final float n4, final float n5, final float n6, final float n7) {
        a3dAPI.c.a(0.0f, this.r);
        a3dAPI.c.c(a(n), this.s);
        a3dAPI.c.b(a(n2), this.t);
        a3dAPI.c.b(n4, n5, n6, this.u);
        a3dAPI.c.a(n7, n7, n7, this.v);
        a3dAPI.c.a(this.t, this.s, this.w);
        a3dAPI.c.a(this.w, this.v, this.w);
        a3dAPI.c.a(this.w, this.u, this.w);
        this.a.a(n3, this.w);
    }
    
    public final void a(final float n, final float n2, final int n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        a3dAPI.c.a(0.0f, this.r);
        a3dAPI.c.c(a(n), this.s);
        a3dAPI.c.b(a(n2), this.t);
        a3dAPI.c.b(n4, n5, n6, this.u);
        a3dAPI.c.a(n7, n8, n9, this.v);
        a3dAPI.c.a(this.v, this.s, this.w);
        a3dAPI.c.a(this.w, this.t, this.w);
        a3dAPI.c.a(this.w, this.u, this.w);
        this.a.a(n3, this.w);
    }
    
    public final void z() {
        for (int i = 0; i < this.cO.e; ++i) {
            this.a(this.cN[this.cO.f[i].g].u[this.cO.f[i].h].e, this.cN[this.cO.f[i].g].u[this.cO.f[i].h].v, this.cN[this.cO.f[i].g].u[this.cO.f[i].h].a, this.cN[this.cO.f[i].g].u[this.cO.f[i].h].b, this.cN[this.cO.f[i].g].u[this.cO.f[i].h].c);
            final j j = this.cN[this.cO.f[i].g].u[this.cO.f[i].h];
            j.e += 6.0f;
            if (this.cN[this.cO.f[i].g].u[this.cO.f[i].h].e > 359.0f) {
                this.cN[this.cO.f[i].g].u[this.cO.f[i].h].e = 360.0f - this.cN[this.cO.f[i].g].u[this.cO.f[i].h].e;
            }
            final j k = this.cN[this.cO.f[i].g].u[this.cO.f[i].h];
            k.b += this.cN[this.cO.f[i].g].u[this.cO.f[i].h].i;
            final j l = this.cN[this.cO.f[i].g].u[this.cO.f[i].h];
            ++l.p;
            if (this.cN[this.cO.f[i].g].u[this.cO.f[i].h].p > 20) {
                final j m = this.cN[this.cO.f[i].g].u[this.cO.f[i].h];
                m.i *= -1.0f;
                this.cN[this.cO.f[i].g].u[this.cO.f[i].h].p = 0;
            }
            if (this.cN[this.cO.f[i].g].u[this.cO.f[i].h].E < 0) {
                final j j2 = this.cN[this.cO.f[i].g].u[this.cO.f[i].h];
                ++j2.E;
                if (this.cN[this.cO.f[i].g].u[this.cO.f[i].h].E > -1) {
                    this.a.a(this.cN[this.cO.f[i].g].u[this.cO.f[i].h].w, true);
                }
                else if (this.cN[this.cO.f[i].g].u[this.cO.f[i].h].E == -35) {
                    this.a(this.dJ[i], 25, 1000, 0);
                }
            }
            fps fps;
            j[] array;
            int n;
            float n2;
            float n3;
            int n4;
            int n5;
            int n6;
            if (this.cN[this.cO.f[i].g].u[this.cO.f[i].h].E > -36 && this.cN[this.cO.f[i].g].u[this.cO.f[i].h].E < -2) {
                fps = this;
                array = this.dJ[this.cN[this.cO.f[i].g].u[this.cO.f[i].h].H];
                n = 25;
                n2 = this.cN[this.cO.f[i].g].u[this.cO.f[i].h].a;
                n3 = this.cN[this.cO.f[i].g].u[this.cO.f[i].h].c;
                n4 = 1000;
                n5 = 0;
                n6 = 3;
            }
            else {
                if (this.cN[this.cO.f[i].g].u[this.cO.f[i].h].E <= -1) {
                    continue;
                }
                fps = this;
                array = this.dJ[this.cN[this.cO.f[i].g].u[this.cO.f[i].h].H];
                n = 25;
                n2 = this.cN[this.cO.f[i].g].u[this.cO.f[i].h].a;
                n3 = this.cN[this.cO.f[i].g].u[this.cO.f[i].h].c;
                n4 = 1001;
                n5 = 1;
                n6 = 10;
            }
            fps.a(array, n, n2, n3, n4, n5, n6);
        }
    }
    
    public final void A() {
        for (int i = 0; i < this.cN[this.ae.l].i; ++i) {
            if (this.cN[this.cN[this.ae.l].j[i]].f) {
                for (int j = 0; j < 76; ++j) {
                    if (this.cN[this.cN[this.ae.l].j[i]].h[j].u) {
                        fps fps;
                        j ae;
                        if (this.cN[this.cN[this.ae.l].j[i]].h[j].e == -99.0f) {
                            fps = this;
                            ae = this.ae;
                        }
                        else {
                            if (this.cN[this.cN[this.ae.l].j[i]].h[j].e == -999.0f) {
                                this.a(this.ae.e, this.cp.x, this.cN[this.cN[this.ae.l].j[i]].h[j].v, this.cN[this.cN[this.ae.l].j[i]].h[j].a, this.cN[this.cN[this.ae.l].j[i]].h[j].b, this.cN[this.cN[this.ae.l].j[i]].h[j].c);
                                continue;
                            }
                            fps = this;
                            ae = this.cN[this.cN[this.ae.l].j[i]].h[j];
                        }
                        fps.a(ae.e, this.cN[this.cN[this.ae.l].j[i]].h[j].v, this.cN[this.cN[this.ae.l].j[i]].h[j].a, this.cN[this.cN[this.ae.l].j[i]].h[j].b, this.cN[this.cN[this.ae.l].j[i]].h[j].c);
                    }
                }
            }
        }
    }
    
    public final void a(final j j, final int n, final j[][] array, final int n2) {
        for (int i = 0; i < array[n][0].t; ++i) {
            Label_0145: {
                a a = null;
                int n3 = 0;
                int[] array2 = null;
                int n4 = 0;
                Label_0141: {
                    if (array[n][i].G == 0 && i == 0) {
                        if (j.x <= 19) {
                            a = this.a;
                            n3 = array[n][i].w;
                            array2 = this.az;
                            n4 = j.x;
                            break Label_0141;
                        }
                        a = this.a;
                        n3 = array[n][i].w;
                        array2 = this.az;
                    }
                    else {
                        if (array[n][i].G != 1) {
                            break Label_0145;
                        }
                        if (j.x <= 19) {
                            a = this.a;
                            n3 = array[n][i].w;
                            array2 = this.aA;
                            n4 = j.x;
                            break Label_0141;
                        }
                        a = this.a;
                        n3 = array[n][i].w;
                        array2 = this.aA;
                    }
                    n4 = 19;
                }
                a.d(n3, array2[n4]);
            }
            if (array[n][i].G == 1) {
                final j k = array[n][i];
                k.y += 0.1;
            }
            Label_0687: {
                if (i == 0 && this.dL[j.z].o >= 0) {
                    if (this.dL[j.z].r != 0.0) {
                        final j l = array[n][i];
                        l.b += this.dL[j.z].r;
                    }
                    if (array[n][i].G == 0 && this.dL[j.z].o >= 0) {
                        if (i == 0) {
                            this.a.b(array[n][0].w, this.cG[this.dL[j.z].o].a);
                            final j m = array[n][i];
                            m.y += this.dL[j.z].s;
                            final j j2 = array[n][i];
                            j2.b += this.dL[j.z].r;
                            final j j3 = array[n][i];
                            j3.f += this.dL[j.z].u;
                        }
                    }
                    else {
                        this.a.b(array[n][0].w, this.cG[6].a);
                    }
                }
                else {
                    final j j4 = array[n][i];
                    j4.a += array[n][i].h;
                    final j j5 = array[n][i];
                    j5.b += array[n][i].i;
                    final j j6 = array[n][i];
                    j6.c += array[n][i].j;
                    Label_0600: {
                        j j8;
                        float i2;
                        if (array[n][i].i > 0.0f) {
                            final j j7 = array[n][i];
                            j7.i *= 0.8;
                            if (array[n][i].i >= 0.05) {
                                break Label_0600;
                            }
                            j8 = array[n][i];
                            i2 = -0.05f;
                        }
                        else {
                            final j j9 = array[n][i];
                            j9.i *= 1.2;
                            if (array[n][i].b > 0.0f) {
                                break Label_0600;
                            }
                            i2 = (float)((j8 = array[n][i]).i * -0.4);
                        }
                        j8.i = i2;
                    }
                    j j11;
                    float y;
                    if (array[n][i].G == 0) {
                        final j j10 = array[n][i];
                        j10.y -= this.dL[j.z].t;
                        if (array[n][i].y >= 0.0f) {
                            break Label_0687;
                        }
                        j11 = array[n][i];
                        y = 0.0f;
                    }
                    else {
                        if (n2 != -1) {
                            break Label_0687;
                        }
                        y = (float)((j11 = array[n][i]).y + 0.2);
                    }
                    j11.y = y;
                }
            }
            if (array[n][i].G == 1) {
                final j j12 = array[n][i];
                ++j12.f;
                if (array[n][i].f > 360.0f) {
                    array[n][i].f -= 360.0f;
                }
            }
            this.a(this.ae.e, array[n][i].f, array[n][i].v, array[n][i].a, array[n][i].b, array[n][i].c, array[n][i].y);
        }
        ++j.x;
        if (n2 == -1) {
            this.a.c(this.dF[n], (float)(Math.random() + 0.25));
            this.a.c(this.dG[n], (float)(Math.random() + 0.25));
        }
        if (j.x > this.dL[j.z].m) {
            this.c(j, n, array, n2);
        }
        if (j.H > -1 && j.x == j.H) {
            this.b(j, n2);
        }
    }
    
    public final void e(final int n, final int n2) {
        if (this.aB.O <= -1) {
            if (fps.T >= 3) {
                this.r(n, n2);
                return;
            }
            if (fps.T == 2) {
                --fps.U;
                if (fps.U <= 0 || this.aB.y >= this.cp.g) {
                    fps.U = 5;
                    this.r(n, n2);
                }
                return;
            }
            if (fps.T == 1 && this.aB.y >= this.cp.g) {
                this.r(n, n2);
            }
        }
    }
    
    public final void b(final j j, final int n) {
        if (n != -1) {
            for (int i = 0; i < fps.F; ++i) {
                final float n2;
                if (i != n && i != j.L && (n2 = (float)b(this.aC[i].a.a, this.aC[i].a.c, j.a, j.c)) <= this.dL[j.I].ac) {
                    this.aC[i].c = this.a((int)((this.dL[j.I].ac - n2) * (this.dL[j.I].h / this.dL[j.I].ac)), this.aC[i].c, i, n);
                    if (this.aC[i].c.z < 1 && this.aC[i].c.B == -1) {
                        final e c = this.aC[n].c;
                        ++c.y;
                        final int[] bg = this.bG;
                        ++bg[n];
                        this.P(n);
                        final float n3 = a(j.a, j.c, this.aC[i].a.a, this.aC[i].a.c);
                        this.a(i, 3, (float)(0.0 + Math.sin(-n3 * 0.0174444) * 0.5), (float)(0.0 - Math.cos(-n3 * 0.0174444) * 0.5), 0.9f, 3, -10, false, j.I, true);
                        this.a(n, i, j.I, this.aC[i].c.K > -1);
                    }
                    else if (this.aC[i].c.z > 0 && this.aC[i].c.B == -1 && (n == -1 || this.x(i))) {
                        this.ag(10);
                    }
                }
            }
            final float n4;
            if (j.L != -1 && this.aB.s < 0 && (n4 = (float)b(this.ae.a, this.ae.c, j.a, j.c)) <= this.dL[j.I].ac) {
                this.aB = this.a((int)((this.dL[j.I].ac - n4) * (this.dL[j.I].h / this.dL[j.I].ac)), this.aB, -1, n);
                if (this.aB.z < 1 && this.aB.B < 0) {
                    this.n(n, j.I);
                }
                this.aB.v = 20;
            }
        }
        if (n == -1) {
            for (int k = 0; k < fps.F; ++k) {
                final float n5;
                if (k != j.L && (n5 = (float)b(this.aC[k].a.a, this.aC[k].a.c, j.a, j.c)) <= this.dL[j.I].ac) {
                    final float n6 = (this.dL[j.I].ac - n5) * (this.dL[j.I].h / this.dL[j.I].ac);
                    this.aC[k].c = this.a((int)n6, this.aC[k].c, k, n);
                    this.b(this.aC[k].o, 20, k, Math.max(1, (int)(n6 / 20.0f)));
                    if (this.aC[k].c.z < 1 && this.aC[k].c.B == -1) {
                        this.bz = 10;
                        final e ab = this.aB;
                        ++ab.y;
                        final int[] bg2 = this.bG;
                        final int n7 = 5;
                        ++bg2[n7];
                        this.ag(12);
                        this.e(k, j.I);
                        final float e = a(j.a, j.c, this.aC[k].a.a, this.aC[k].a.c);
                        if (!fps.P) {
                            this.aC[k].a.e = e;
                        }
                        this.a(k, 3, (float)(0.0 + Math.sin(-e * 0.0174444) * 0.5), (float)(0.0 - Math.cos(-e * 0.0174444) * 0.5), 1.0f, 3, -10, false, j.I, true);
                        this.o(j.I, k);
                    }
                    else if (this.aC[k].c.z > 0 && this.aC[k].c.B == -1) {
                        this.ag(this.bz = 10);
                    }
                    else if (this.aC[k].c.B > -1 && !this.aC[k].j && this.aC[k].h <= 0) {
                        if (n == -1 || this.x(k)) {
                            this.ag(7);
                        }
                        this.q(k, 1);
                        this.a(k, 0);
                        this.b(this.aC[k].k, 100, k);
                        this.f(k, 10);
                        this.aC[k].c.B = 0;
                    }
                }
            }
        }
    }
    
    public final void a(final j[] array, final int n, final int n2, final int n3) {
        int n4 = 7;
        Label_0024: {
            int n5;
            if (n3 == 0) {
                n5 = 7;
            }
            else {
                if (n3 != 1) {
                    break Label_0024;
                }
                n5 = 8;
            }
            n4 = n5;
        }
        for (int i = 0; i < n; ++i) {
            j j;
            float y;
            if (n3 == 0) {
                j = array[i];
                y = 30.0f;
            }
            else {
                j = array[i];
                y = 3.0f;
            }
            j.y = y;
            array[i].a = 0.0f;
            j k;
            float n6;
            double n7;
            if (n2 == 1000) {
                k = array[i];
                n6 = 1.0f;
                n7 = Math.random() * 2.0 - 1.0;
            }
            else {
                k = array[i];
                n6 = 1.0f;
                n7 = Math.random() * 5.0;
            }
            k.b = n6 + (float)n7;
            array[i].c = 0.0f;
            array[i].e = 360 / n * i;
            array[i].f = (float)(Math.random() * 360.0);
            j l;
            float f;
            if (n2 == -1) {
                l = array[i];
                f = 4.0f;
            }
            else {
                l = array[i];
                f = 2.0f;
            }
            l.F = f;
            Label_0222: {
                a a;
                e e;
                if (n2 == -1) {
                    a = this.a;
                    e = this.aB;
                }
                else {
                    if (n2 >= 5) {
                        break Label_0222;
                    }
                    a = this.a;
                    e = this.aC[n2].c;
                }
                a.b(e.l[i].w, this.cG[n4].a);
            }
            array[i].u = true;
            this.a.a(this.aj, array[i].v);
            this.a.a(array[i].v, array[i].w);
        }
    }
    
    public final void a(final j[] array, final int n, final float a, final float c, final int n2, final int n3, final int n4) {
        if (!fps.M || fps.N <= 0) {
            for (int i = 0; i < n; ++i) {
                fps fps;
                float n5;
                float n6;
                int n7;
                float n8;
                float n9;
                float n10;
                float y;
                if (n3 == 0) {
                    Label_0132: {
                        if (n2 == -1) {
                            if (this.aB.s % 2 != 0 || array[i].y <= 3.0f) {
                                break Label_0132;
                            }
                        }
                        else if (n2 < 5) {
                            if (this.aC[n2].c.s % 2 != 0 || array[i].y <= 3.0f) {
                                break Label_0132;
                            }
                        }
                        else if (n2 != 1000 || array[i].y <= 1.0f) {
                            break Label_0132;
                        }
                        final j j = array[i];
                        --j.y;
                    }
                    final j k = array[i];
                    ++k.f;
                    final j l = array[i];
                    l.e += array[i].F;
                    if (array[i].e > 359.0f) {
                        array[i].e -= 360.0f;
                    }
                    if (array[i].e < 0.0f) {
                        final j m = array[i];
                        m.e += 360.0f;
                    }
                    array[i].a = (float)(a - Math.sin(array[i].e * 0.0174444) * array[i].y);
                    array[i].c = (float)(c + Math.cos(array[i].e * 0.0174444) * array[i].y);
                    fps = this;
                    n5 = this.ae.e;
                    n6 = array[i].f;
                    n7 = array[i].v;
                    n8 = array[i].a;
                    n9 = array[i].b;
                    n10 = array[i].c;
                    y = n4;
                }
                else {
                    if (n3 != 1) {
                        continue;
                    }
                    if (n2 == 1001) {
                        if (array[i].i == 0.0f) {
                            array[i].i = (float)(Math.random() * 0.10000000149011612) + 0.1f;
                        }
                        if (array[i].h == 0.0f) {
                            array[i].h = (float)(Math.random() * 0.05000000074505806) - 0.025f;
                        }
                        if (array[i].j == 0.0f) {
                            array[i].j = (float)(Math.random() * 0.05000000074505806) - 0.025f;
                        }
                        final j j2 = array[i];
                        j2.b += array[i].i;
                        if (array[i].b > 10.0f) {
                            array[i].b = 0.0f;
                            array[i].a = a;
                            array[i].c = c;
                            array[i].i = (float)(Math.random() * 0.10000000149011612) + 0.1f;
                            array[i].h = (float)(Math.random() * 0.05000000074505806) - 0.025f;
                            array[i].j = (float)(Math.random() * 0.05000000074505806) - 0.025f;
                        }
                        array[i].y = (10 - (int)array[i].b) / 3;
                        final j j3 = array[i];
                        j3.a += array[i].h;
                        final j j4 = array[i];
                        j4.c += array[i].j;
                    }
                    fps = this;
                    n5 = this.ae.e;
                    n6 = array[i].f;
                    n7 = array[i].v;
                    n8 = array[i].a;
                    n9 = array[i].b;
                    n10 = array[i].c;
                    y = array[i].y;
                }
                fps.a(n5, n6, n7, n8, n9, n10, y);
            }
        }
    }
    
    public static j a(final j j, final float n, final float n2, final float n3, final float n4) {
        j.a = (float)(n + Math.sin(-n3 * 0.0174444) * n4);
        j.c = (float)(n2 - Math.cos(-n3 * 0.0174444) * n4);
        return j;
    }
    
    public final void a(final j[] array, final int n, final int z, final float a, final float b, final float c) {
        final d d = this.aC[n];
        ++d.l;
        final int l = this.aC[n].l;
        array[l].u = true;
        array[l].a = a;
        array[l].b = b;
        array[l].c = c;
        array[l].z = z;
        array[l].f = (float)(Math.random() * 360.0);
        array[l].E = 0;
        Label_0689: {
            j i = null;
            float y = 0.0f;
            Label_0686: {
                j j;
                double n2;
                double n3;
                if (z == 0) {
                    array[l].h = (float)(Math.random() * 0.04 - 0.02);
                    array[l].i = 0.6f;
                    j = array[l];
                    n2 = Math.random() * 0.04;
                    n3 = 0.02;
                }
                else {
                    if (z == 1) {
                        array[l].h = (float)(Math.random() * 0.08 - 0.04);
                        array[l].i = 0.2f;
                        array[l].j = (float)(Math.random() * 0.08 - 0.04);
                        i = array[l];
                        y = 2.5f;
                        break Label_0686;
                    }
                    if (z == 2) {
                        array[l].h = (float)(Math.random() * 0.08 - 0.04);
                        array[l].i = 0.15f;
                        array[l].j = (float)(Math.random() * 0.08 - 0.04);
                        array[l].y = 1.0f;
                        a(array[l], a, c, this.aC[n].a.e + 180.0f, -2.0f);
                        break Label_0689;
                    }
                    if (z == 3) {
                        array[l].h = 0.0f;
                        array[l].i = 0.05f;
                        array[l].j = 0.0f;
                        i = array[l];
                        y = 4.0f;
                        break Label_0686;
                    }
                    if (z == 4) {
                        array[l].h = (float)(Math.random() * 0.08 - 0.04);
                        array[l].i = 0.15f;
                        array[l].j = (float)(Math.random() * 0.08 - 0.04);
                        array[l].y = 5.0f;
                        a(array[l], a, c, this.aC[n].a.e + 180.0f, -1.5f);
                        break Label_0689;
                    }
                    if (z == 5) {
                        array[l].h = (float)(Math.random() * 0.04 - 0.02);
                        array[l].i = 0.05f;
                        array[l].j = (float)(Math.random() * 0.04 - 0.02);
                        array[l].y = 2.0f;
                        a(array[l], a, c, this.aC[n].a.e + 180.0f, -1.5f);
                        break Label_0689;
                    }
                    if (z == 6) {
                        array[l].h = (float)(Math.random() * 0.04 - 0.02);
                        array[l].i = 0.1f;
                        array[l].j = (float)(Math.random() * 0.04 - 0.02);
                        array[l].y = 4.0f;
                        a(array[l], a, c, this.aC[n].a.e + 180.0f, -1.5f);
                        break Label_0689;
                    }
                    array[l].h = (float)(Math.random() * 0.02 - 0.01);
                    array[l].i = -5.0E-5f;
                    j = array[l];
                    n2 = Math.random() * 0.02;
                    n3 = 0.01;
                }
                j.j = (float)(n2 - n3);
                i = array[l];
                y = 3.0f;
            }
            i.y = y;
        }
        this.a(this.ae.e, array[l].f, array[l].v, array[l].a, array[l].b, array[l].c, array[l].y);
        a a2;
        int n4;
        a[] array2;
        int n5;
        if (z == 3 || z == 6) {
            a2 = this.a;
            n4 = array[l].w;
            array2 = this.cG;
            n5 = 0;
        }
        else if (z == 4) {
            a2 = this.a;
            n4 = array[l].w;
            array2 = this.cG;
            n5 = 4;
        }
        else if (z == 5) {
            a2 = this.a;
            n4 = array[l].w;
            array2 = this.cG;
            n5 = 5;
        }
        else {
            a2 = this.a;
            n4 = array[l].w;
            array2 = this.cG;
            n5 = 6;
        }
        a2.b(n4, array2[n5].a);
        this.a.d(array[l].w, this.az[0]);
        array[l].K = 0.0f;
        this.a.a(this.aj, array[l].v);
        this.a.a(array[l].v, array[l].w);
        this.a.a(array[l].w, true);
    }
    
    public final void a(final j[] array, final int n, final int n2) {
        final d d = this.aC[n2];
        ++d.m;
        if (this.aC[n2].m >= 200) {
            this.b(array, n, n2);
            return;
        }
        for (int i = 0; i < this.aC[n2].l + 1; ++i) {
            if (this.aC[n2].m < 100 || array[i].z == 3 || array[i].z == 4 || array[i].z == 5 || array[i].z == 6) {
                array[i].a();
            }
            if (array[i].z == 3) {
                final j j = array[i];
                j.y += 0.25;
                final j k = array[i];
                ++k.f;
                final j l = array[i];
                l.K += 0.25;
                if (array[i].K > 18.0f) {
                    array[i].K = 18.0f;
                }
                this.a.d(array[i].w, this.az[(int)array[i].K]);
            }
            else if (array[i].z == 4) {
                final j m = array[i];
                m.y += 0.2;
                final j j2 = array[i];
                ++j2.f;
                final j j3 = array[i];
                j3.K += 0.5;
                if (array[i].K > 18.0f) {
                    array[i].K = 18.0f;
                }
                this.a.d(array[i].w, this.az[(int)array[i].K]);
                if (array[i].E > 50) {
                    this.a.b(array[i].w, this.cG[0].a);
                }
            }
            else if (array[i].z == 5) {
                final j j4 = array[i];
                j4.y += 0.1;
                final j j5 = array[i];
                ++j5.f;
                final j j6 = array[i];
                j6.K += 5.25;
                if (array[i].K > 18.0f) {
                    array[i].K = 18.0f;
                }
                this.a.d(array[i].w, this.az[(int)array[i].K]);
            }
            else if (array[i].z == 6) {
                final j j7 = array[i];
                j7.y += 0.05;
                final j j8 = array[i];
                ++j8.f;
                final j j9 = array[i];
                j9.K += 0.5;
                if (array[i].K > 18.0f) {
                    array[i].K = 18.0f;
                }
                this.a.d(array[i].w, this.az[(int)array[i].K]);
            }
            else if (array[i].z == 10) {
                final j j10 = array[i];
                j10.K += 0.35;
                if (array[i].K > 18.0f) {
                    array[i].K = 18.0f;
                }
                if (array[i].y <= 15.0f) {
                    final j j11 = array[i];
                    j11.y += 0.5;
                }
                final j j12 = array[i];
                ++j12.f;
                if (array[i].f >= 360.0f) {
                    array[i].f = 0.0f;
                }
                this.a.d(array[i].w, this.az[(int)array[i].K]);
                if (array[i].K >= 18.0f) {
                    array[i].u = false;
                    this.a.c(this.aj, array[i].v);
                    this.a.c(array[i].v, array[i].w);
                }
            }
            else if (this.aC[n2].m >= 100) {
                array[i].b -= 0.05f;
            }
            else {
                array[i].a(false);
            }
            this.a(this.ae.e, array[i].f, array[i].v, array[i].a, array[i].b, array[i].c, (array[i].b <= 0.0f && array[i].i == 0.0f) ? 6.0f : array[i].y);
            final j j13 = array[i];
            ++j13.E;
            if (array[i].E > 150) {
                this.a(i, array);
            }
        }
        if (this.aC[n2].m < 100) {
            final e c = this.aC[n2].c;
            --c.d;
            if (this.aC[n2].c.d <= 0) {
                if (this.aC[n2].h > -1) {
                    if (this.aC[n2].i) {
                        this.aC[n2].c.d = 10;
                        for (int n3 = 0; n3 < 4; ++n3) {
                            if (this.aC[n2].l < 99) {
                                this.a(array, n2, 10, this.aC[n2].f[n3].a, this.aC[n2].f[n3].b, this.aC[n2].f[n3].c);
                            }
                        }
                    }
                }
                else if (this.aC[n2].n == 0) {
                    this.aC[n2].c.d = 1;
                    if (this.aC[n2].l < 99) {
                        this.a(array, n2, 0, this.aC[n2].a.a, this.aC[n2].a.b + 2.0f, this.aC[n2].a.c);
                    }
                }
                else if (this.aC[n2].n == 2) {
                    this.aC[n2].c.d = 0;
                    if (this.aC[n2].l < 99 && this.aC[n2].a.B >= fps.ac) {
                        this.a(array, n2, 2, this.aC[n2].a.a, this.aC[n2].a.b + 2.0f, this.aC[n2].a.c);
                    }
                }
                else if (this.aC[n2].n == 3) {
                    this.aC[n2].c.d = 15;
                    if (this.aC[n2].l < 99) {
                        this.a(array, n2, 3, this.aC[n2].a.a, this.aC[n2].a.b + 2.0f, this.aC[n2].a.c);
                    }
                }
                else if (this.aC[n2].n == 4) {
                    this.aC[n2].c.d = 5;
                    if (this.aC[n2].l < 99) {
                        if (!fps.P || this.aC[n2].a.B >= fps.ac || this.aC[n2].a.k > 0.0f) {
                            this.a(array, n2, 4, this.aC[n2].a.a, this.aC[n2].a.b + 2.25f, this.aC[n2].a.c);
                        }
                    }
                }
                else if (this.aC[n2].n == 6) {
                    this.aC[n2].c.d = 5;
                    if (!fps.P) {
                        this.a(array, n2, 6, this.aC[n2].a.a, this.aC[n2].a.b + 2.0f, this.aC[n2].a.c);
                        return;
                    }
                    if (this.aC[n2].a.B >= fps.ac - 1) {
                        this.a(array, n2, 6, this.aC[n2].a.a, this.aC[n2].a.b + 2.5f, this.aC[n2].a.c);
                        return;
                    }
                    this.a(array, n2, 6, this.aC[n2].a.a, this.aC[n2].a.b + 4.0f, this.aC[n2].a.c);
                }
                else if (this.aC[n2].n == 5) {
                    this.aC[n2].c.d = 5;
                    if (!fps.P || this.aC[n2].a.B >= 12) {
                        this.a(array, n2, 5, this.aC[n2].a.a, this.aC[n2].a.b + 2.0f, this.aC[n2].a.c);
                        return;
                    }
                    this.a(array, n2, 5, this.aC[n2].a.a, this.aC[n2].a.b + 4.0f, this.aC[n2].a.c);
                }
                else if (this.aC[n2].n == 1) {
                    final float n4 = (fps.P && this.aC[n2].a.B < fps.ac - 1) ? 3.5f : 2.5f;
                    this.aC[n2].c.d = 0;
                    for (int n5 = 0; n5 < 3; ++n5) {
                        if (this.aC[n2].l + 1 + n5 < 100) {
                            fps fps;
                            j[] array2;
                            int n6;
                            int n7;
                            float n8;
                            float n9;
                            float n10;
                            if (n5 == 0) {
                                fps = this;
                                array2 = array;
                                n6 = n2;
                                n7 = 1;
                                n8 = this.aC[n2].a.a - 1.0f;
                                n9 = this.aC[n2].a.b + n4;
                                n10 = this.aC[n2].a.c;
                            }
                            else if (n5 == 1) {
                                fps = this;
                                array2 = array;
                                n6 = n2;
                                n7 = 1;
                                n8 = this.aC[n2].a.a;
                                n9 = this.aC[n2].a.b + n4;
                                n10 = this.aC[n2].a.c;
                            }
                            else if (n5 == 2) {
                                fps = this;
                                array2 = array;
                                n6 = n2;
                                n7 = 1;
                                n8 = this.aC[n2].a.a + 1.0f;
                                n9 = this.aC[n2].a.b + n4;
                                n10 = this.aC[n2].a.c;
                            }
                            else {
                                if (n5 != 3) {
                                    continue;
                                }
                                fps = this;
                                array2 = array;
                                n6 = n2;
                                n7 = 1;
                                n8 = this.aC[n2].a.a;
                                n9 = this.aC[n2].a.b + n4;
                                n10 = this.aC[n2].a.c + 1.0f;
                            }
                            fps.a(array2, n6, n7, n8, n9, n10);
                        }
                    }
                }
            }
        }
    }
    
    public final void f(final int n, final int n2) {
        this.b(this.aC[n].k, 100, n);
        this.aC[n].l = 0;
        this.aC[n].m = 0;
        this.aC[n].n = n2;
        this.aC[n].c.d = 1;
    }
    
    public final void b(final j[] array, final int n, final int n2) {
        this.aC[n2].l = -1;
        for (int i = 0; i < n; ++i) {
            array[i].u = false;
            this.a.c(this.aj, array[i].v);
            this.a.c(array[i].v, array[i].w);
            this.a.a(array[i].w, false);
        }
    }
    
    public final void a(final int n, final j[] array) {
        array[n].u = false;
        this.a.c(this.aj, array[n].v);
        this.a.c(array[n].v, array[n].w);
        this.a.a(array[n].w, false);
    }
    
    public final void b(final j[] array, final int n, final int n2, final int q) {
        this.aC[n2].p = 0;
        this.aC[n2].q = q;
        for (int i = 0; i < n; ++i) {
            array[i].u = true;
            array[i].a = 0.0f;
            array[i].b = 0.0f;
            array[i].c = 0.0f;
            array[i].h = (float)(Math.random() * 0.4 - 0.2);
            array[i].i = (float)(Math.random() * 0.8 + 0.2);
            array[i].j = (float)(Math.random() * 0.4 - 0.2);
            this.a.a(this.aj, array[i].v);
            this.a.a(array[i].v, array[i].w);
        }
    }
    
    public final void c(final j[] array, final int n, final int n2) {
        this.aC[n2].p = -1;
        for (int i = 0; i < n; ++i) {
            array[i].u = false;
            this.a.c(this.aj, array[i].v);
            this.a.c(array[i].v, array[i].w);
        }
    }
    
    public final void d(final j[] array, final int n, final int n2) {
        final d d = this.aC[n2];
        ++d.p;
        if (this.aC[n2].p >= 50) {
            this.c(array, n, n2);
            return;
        }
        for (int i = 0; i < n; ++i) {
            array[i].a();
            array[i].a(false);
            this.a(this.ae.e, 0.0f, array[i].v, array[i].a + this.aC[n2].a.a, array[i].b + this.aC[n2].a.b, array[i].c + this.aC[n2].a.c, this.aC[n2].q);
        }
    }
    
    public final void a(final j[] array, final int n, final int n2, final int n3, final float n4, final float n5, final float n6, final int n7, final int n8) {
        final int f;
        if ((f = f(array, n)) > -1) {
            this.a(array, f, n3, n4, n5, n6, n2, n7, n8);
        }
    }
    
    public final void a(final j[] array, final int n, final int i, final float a, final float b, final float c, final int a2, final int n2, final int n3) {
        array[n].a = a;
        array[n].b = b;
        array[n].c = c;
        array[n].A = a2;
        j j;
        int g;
        if (n2 > -1) {
            this.a.b(array[n].w, this.cG[n2].a);
            j = array[n];
            g = n3;
        }
        else {
            this.a.b(array[n].w, this.cG[this.dL[i].ae].a);
            j = array[n];
            g = -1;
        }
        j.G = g;
        this.a.a(this.aj, array[n].v);
        this.a.a(array[n].v, array[n].w);
        array[n].u = true;
        array[n].I = i;
        array[n].y = this.dL[i].af;
        array[n].f = (float)(Math.random() * 360.0);
        array[n].K = this.dL[i].ah;
        array[n].D = this.dL[i].ai;
        array[n].E = 1;
    }
    
    public final void e(final j[] array, final int n, final int n2) {
        for (int i = 0; i < n; ++i) {
            if (array[i].A == n2) {
                this.b(array, i);
            }
        }
    }
    
    public final void a(final j[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            this.a.c(array[i].v, array[i].w);
            this.a.c(this.aj, array[i].v);
            array[i].u = false;
        }
    }
    
    public final void b(final j[] array, final int n) {
        this.a.c(array[n].v, array[n].w);
        this.a.c(this.aj, array[n].v);
        array[n].u = false;
    }
    
    public final void c(final j[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            if (array[i].u) {
                final j j = array[i];
                ++j.E;
                if (array[i].E > array[i].D) {
                    this.b(array, i);
                }
                else {
                    fps fps;
                    if (array[i].G > 0) {
                        if (!fps.P) {
                            Label_0528: {
                                j k;
                                double n2;
                                float e;
                                if (array[i].G == 1) {
                                    array[i].a = (float)(this.ae.a + Math.sin(-(this.ae.e + this.dL[array[i].I].F) * 0.0174444) * this.dL[array[i].I].ak);
                                    array[i].b = this.ae.b + this.dL[this.aB.b].L + this.aB.H * 0.85f;
                                    k = array[i];
                                    n2 = this.ae.c;
                                    e = this.ae.e;
                                }
                                else if (array[i].G == 2) {
                                    array[i].a = (float)(this.ae.a + Math.sin(-(this.ae.e + 6.0f + this.dL[array[i].I].F) * 0.0174444) * this.dL[array[i].I].ak);
                                    array[i].b = this.ae.b + this.dL[this.aB.b].L + this.aB.H * 0.85f;
                                    k = array[i];
                                    n2 = this.ae.c;
                                    e = this.ae.e + 6.0f;
                                }
                                else {
                                    if (array[i].G != 3) {
                                        break Label_0528;
                                    }
                                    array[i].a = (float)(this.ae.a + Math.sin(-(this.ae.e - 5.0f + this.dL[array[i].I].F) * 0.0174444) * this.dL[array[i].I].ak);
                                    array[i].b = this.ae.b + this.dL[this.aB.b].L + this.aB.H * 0.85f;
                                    k = array[i];
                                    n2 = this.ae.c;
                                    e = this.ae.e - 5.0f;
                                }
                                k.c = (float)(n2 - Math.cos(-(e + this.dL[array[i].I].F) * 0.0174444) * this.dL[array[i].I].ak);
                            }
                            if (this.aB.B > -1) {
                                final j l = array[i];
                                l.b -= 1000.0f;
                            }
                        }
                        array[i].y = this.dL[array[i].I].al;
                        final j m = array[i];
                        ++m.K;
                        if (array[i].K > 15.0f) {
                            array[i].K = 19.0f;
                        }
                        fps = this;
                    }
                    else {
                        final j j2 = array[i];
                        j2.y += this.dL[array[i].I].ag;
                        final j j3 = array[i];
                        j3.K += 0.5;
                        if (array[i].K > 19.0f) {
                            array[i].K = 19.0f;
                        }
                        final j j4 = array[i];
                        j4.b += this.dL[array[i].I].am;
                        fps = this;
                    }
                    fps.a.d(array[i].w, this.az[(int)array[i].K]);
                    this.a(this.ae.e, array[i].f, array[i].v, array[i].a, array[i].b, array[i].c, array[i].y);
                }
            }
        }
    }
    
    public final void d(final j[] array, final int n) {
        for (int i = 0; i < 10; ++i) {
            if (array[i].u || array[i].x > -1) {
                if (array[i].x > -1) {
                    if (n == -1) {
                        this.a(array[i], i, this.dB, n);
                    }
                    else {
                        this.a(array[i], i, this.dC[n], n);
                    }
                }
                else {
                    array[i] = this.a(array[i], i, array[i].F, n);
                    if (array[i].x < 0 && array[i].A == -1) {
                        this.a.a(this.dF[i], array[i].a, 10.0f, array[i].c);
                        this.a.a(this.dG[i], array[i].a, -6.0f, array[i].c);
                    }
                    final j j = array[i];
                    ++j.D;
                    if (array[i].x > -1) {
                        this.g(array, i);
                    }
                    fps fps;
                    j ae;
                    if (this.dL[array[i].z].I) {
                        fps = this;
                        ae = this.ae;
                    }
                    else {
                        fps = this;
                        ae = array[i];
                    }
                    fps.a(ae.e, array[i].v, array[i].a, array[i].b, array[i].c);
                    if (array[i].J < this.dL[array[i].I].ad) {
                        final j k = array[i];
                        ++k.J;
                        fps fps2;
                        j[] dd;
                        if (n == -1) {
                            fps2 = this;
                            dd = this.dD;
                        }
                        else {
                            final j l = array[i];
                            --l.M;
                            if (array[i].M > 0) {
                                continue;
                            }
                            array[i].M = this.L;
                            fps2 = this;
                            dd = this.dE[n];
                        }
                        fps2.a(dd, 50, i, array[i].I, array[i].a, array[i].b, array[i].c, -1, 1);
                    }
                }
            }
        }
    }
    
    public final void e(final j[] array, final int n) {
        for (int i = 0; i < 10; ++i) {
            if (array[i].u || array[i].x > -1) {
                if (array[i].x > -1) {
                    if (n == -1) {
                        this.a(array[i], i, this.dB, n);
                    }
                    else {
                        this.a(array[i], i, this.dC[n], n);
                    }
                }
                else {
                    array[i] = this.a(array[i], i, array[i].F, n);
                    if (array[i].x < 0 && array[i].A == -1) {
                        this.a.a(this.dF[i], array[i].a, 10.0f, array[i].c);
                        this.a.a(this.dG[i], array[i].a, -6.0f, array[i].c);
                    }
                    final j j = array[i];
                    ++j.D;
                    if (array[i].x > -1) {
                        this.g(array, i);
                    }
                    this.a(array[i].e, array[i].v, array[i].a, array[i].b, array[i].c);
                    if (array[i].J < this.dL[array[i].I].ad) {
                        final j k = array[i];
                        ++k.J;
                        fps fps;
                        j[] dd;
                        if (n == -1) {
                            fps = this;
                            dd = this.dD;
                        }
                        else {
                            fps = this;
                            dd = this.dE[n];
                        }
                        fps.a(dd, 50, i, array[i].I, array[i].a, array[i].b, array[i].c, -1, 1);
                    }
                }
            }
        }
    }
    
    public final void j(final int n) {
        if ((this.cz && n > this.aB.b) || n == 0) {
            this.U(n);
        }
    }
    
    public final e a(final e e, final int n) {
        int b = 0;
        for (int i = 0; i < 10; ++i) {
            int n2;
            if (i == 0) {
                n2 = 0;
            }
            else {
                if (!e.D[i] || e.C[i] <= 0) {
                    continue;
                }
                n2 = i;
            }
            b = n2;
        }
        if (n == -1) {
            this.U(b);
        }
        else {
            e.b = b;
        }
        e.e = 0;
        return e;
    }
    
    public final int g(final int n, final int n2) {
        int n3 = -2;
        int n4 = 1000;
        for (int i = 0; i < fps.F; ++i) {
            if (i != n2 && this.aC[i].c.L <= -1 && this.aC[n2].c.x != this.aC[i].c.x && this.aC[i].c.B < 0 && this.aC[i].a.l == n && this.aC[i].c.z + this.aC[i].c.A < n4) {
                n3 = i;
                n4 = this.aC[i].c.z + this.aC[i].c.A;
            }
        }
        if (this.aB.B < 0 && this.ae.l == n && this.aB.L <= -1 && (n3 == -2 || this.aB.z + this.aB.A < n4)) {
            n3 = -1;
        }
        return n3;
    }
    
    public final void B() {
        for (int i = 0; i < fps.F; ++i) {
            if (!this.cp.n) {
                if (!fps.S && (!fps.M || fps.N <= 0) && this.aC[i].c.B < 0) {
                    if (this.aC[i].c.s < 0) {
                        this.aC[i].a.l = this.a(this.aC[i].a.a, this.aC[i].a.c);
                        this.aC[i].c.w = this.g(this.aC[i].a.l, i);
                        Label_0294: {
                            f f;
                            int a;
                            if (this.aC[i].c.w != -2 && this.aC[i].b.a == 0) {
                                f = this.aC[i].b;
                                a = 1;
                            }
                            else {
                                if (this.aC[i].c.w == -2 && this.aC[i].b.a == 1) {
                                    this.aC[i].b.a = 0;
                                    this.q(i);
                                    break Label_0294;
                                }
                                if (this.aC[i].b.a != 1 || this.aC[i].a.l != this.ae.l || this.aB.B <= -1) {
                                    break Label_0294;
                                }
                                f = this.aC[i].b;
                                a = 0;
                            }
                            f.a = a;
                        }
                        if (this.aC[i].b.a == 1 && this.aC[i].a.l > -1) {
                            this.p(i);
                        }
                        else if (this.aC[i].a.l > -1) {
                            this.s(i);
                            if (this.aC[i].c.B < 0) {
                                this.F(i);
                            }
                        }
                        else if (this.aC[i].a.l <= -1 && !fps.S) {
                            this.a(i, 0, 0.0f, 0.0f, 0.5f, 3, -1, false, 0, false);
                        }
                        if (this.aC[i].c.B < 0) {
                            this.E(i);
                            if (this.aC[i].c.w >= -1) {
                                this.v(i);
                            }
                            this.aC[i].c = this.a(this.aC[i].a.a, this.aC[i].a.c, this.aC[i].a.l, this.aC[i].c, i);
                        }
                    }
                }
                else if (!fps.S && (!fps.M || fps.N <= 0)) {
                    this.C(i);
                }
                this.c(i);
                this.k(i);
                if (this.aC[i].p > -1) {
                    this.d(this.aC[i].o, 20, i);
                }
                if (this.aC[i].h > -1 && !fps.S && (!fps.M || fps.N <= 0)) {
                    this.N(i);
                }
            }
        }
    }
    
    public final void k(final int n) {
        if (this.aC[n].c.e > 0) {
            final e c = this.aC[n].c;
            --c.f;
            if (this.aC[n].c.f <= 0) {
                if (this.aC[n].c.C[this.aC[n].c.b] >= 1) {
                    this.aC[n].c.f = this.aC[n].c.g;
                    this.a(this.dA[n], this.aC[n].c.b, n, true, this.aC[n].c.c);
                    final e c2 = this.aC[n].c;
                    --c2.e;
                    final int[] c3 = this.aC[n].c.C;
                    final int b = this.aC[n].c.b;
                    --c3[b];
                }
                else {
                    this.aC[n].c.e = 0;
                }
            }
        }
        if (this.aC[n].c.s > -1) {
            this.a(this.aC[n].c.l, 20, this.aC[n].a.a, this.aC[n].a.c, n, 0, 3);
            final e c4 = this.aC[n].c;
            c4.s -= 2;
            if (this.aC[n].c.s < 2) {
                this.a(this.aC[n].c.l, 20);
                this.aC[n].c.s = -1;
                this.f(n);
            }
        }
        if (this.aC[n].c.K > -1) {
            this.a.a(this.dv, this.aC[n].a.a, 5.0f, this.aC[n].a.c);
            final e c5 = this.aC[n].c;
            --c5.K;
            if (this.aC[n].c.K < 1) {
                this.X(n);
            }
        }
        if (this.aC[n].c.M > -1) {
            final e c6 = this.aC[n].c;
            --c6.N;
            if (this.aC[n].c.N < 1) {
                this.aC[n].c.N = 20;
                if (this.aC[n].c.z < 200) {
                    final e c7 = this.aC[n].c;
                    c7.z += 20;
                }
                if (this.aC[n].c.z > 200) {
                    this.aC[n].c.z = 200;
                }
            }
            this.a.a(this.dw, this.aC[n].a.a, 5.0f, this.aC[n].a.c);
            final e c8 = this.aC[n].c;
            --c8.M;
            if (this.aC[n].c.M < 1) {
                this.Y(n);
            }
        }
        if (this.aC[n].c.L > -1) {
            final e c9 = this.aC[n].c;
            --c9.L;
            if (this.aC[n].c.L < 1) {
                this.m(n);
            }
        }
        if (this.aC[n].c.O > -1) {
            this.a.a(this.dx, this.aC[n].a.a, 5.0f, this.aC[n].a.c);
            final e c10 = this.aC[n].c;
            --c10.O;
            if (this.aC[n].c.O < 1) {
                this.W(n);
            }
        }
    }
    
    public final void C() {
        this.D();
        if (this.aB.M > -1) {
            this.E();
        }
        if (this.aB.O > -1) {
            this.F();
        }
        if (this.aB.K > -1) {
            this.G();
        }
        if (this.aB.L > -1) {
            this.H();
        }
    }
    
    public final void D() {
        for (int i = 0; i < 5; ++i) {
            this.l(i);
        }
    }
    
    public final void l(final int n) {
        if (this.aC[n].c.K > -1) {
            this.X(n);
        }
        if (this.aC[n].c.L > -1) {
            this.m(n);
        }
        if (this.aC[n].c.M > -1) {
            this.Y(n);
        }
        if (this.aC[n].c.O > -1) {
            this.W(n);
        }
    }
    
    public final void m(final int n) {
        this.aC[n].c.L = -1;
        this.c(n, 1);
    }
    
    public final void E() {
        this.aB.M = -1;
        this.a.b(this.dw, false);
        if (this.aB.z > 100) {
            this.aB.z = 100;
        }
    }
    
    public final void F() {
        this.aB.F = 0.75f;
        this.aB.G = 0.4f;
        this.aB.O = -1;
        this.a.b(this.dx, false);
        fps.M = false;
    }
    
    public final void G() {
        this.aB.K = -1;
        this.a.b(this.dv, false);
    }
    
    public final void H() {
        if (this.aB.B < 0) {
            this.w();
        }
        this.aB.L = -1;
    }
    
    public final void I() {
        if (!fps.P) {
            this.bu = this.a(this.ae.a, this.ae.c);
            if (this.cN[this.ae.l].j[0] != this.bu) {
                this.p(this.ae.l = this.a(this.ae.a, this.ae.c), this.ae.l);
            }
            if (this.bt > 0 && (this.bs[0] != this.cN[this.ae.l].j[0] || this.bs[this.bt - 1] != this.cN[this.ae.l].j[this.cN[this.ae.l].i - 1])) {
                this.p(this.ae.l, this.ae.l);
            }
        }
        if (this.aB.d > 0) {
            final e ab = this.aB;
            --ab.d;
        }
        Label_0275: {
            j j;
            float e;
            if (this.ae.e < 0.0f) {
                e = (j = this.ae).e + 360.0f;
            }
            else {
                if (this.ae.e <= 359.0f) {
                    break Label_0275;
                }
                j = this.ae;
                e = 360.0f - this.ae.e;
            }
            j.e = e;
        }
        if (this.aB.e > 0) {
            final e ab2 = this.aB;
            --ab2.f;
            if (this.aB.f <= 0) {
                if (this.aB.C[this.aB.b] >= 1) {
                    this.aB.f = this.aB.g;
                    this.a(this.dz, this.aB.b, -1, true, this.aB.c);
                    final e ab3 = this.aB;
                    --ab3.e;
                    final int[] c = this.aB.C;
                    final int b = this.aB.b;
                    --c[b];
                    if (this.aB.C[this.aB.b] <= 0) {
                        this.aB = this.a(this.aB, -1);
                    }
                }
                else {
                    this.aB.e = 0;
                }
            }
        }
        if (this.aB.s > -1) {
            this.a(this.aB.l, 50, this.ae.a, this.ae.c, -1, 0, 3);
            final e ab4 = this.aB;
            --ab4.s;
            if (this.aB.s < 1) {
                this.a(this.aB.l, 50);
                this.aB.s = -1;
            }
        }
        if (this.aB.K > -1) {
            if (!fps.P) {
                final e ab5 = this.aB;
                --ab5.K;
                if (this.aB.K < 1) {
                    this.G();
                }
            }
            this.a.a(this.dv, this.ae.a, 5.0f, this.ae.c);
        }
        if (this.aB.M > -1) {
            final e ab6 = this.aB;
            --ab6.N;
            if (this.aB.N < 1) {
                this.aB.N = 20;
                if (this.aB.z < 200) {
                    final e ab7 = this.aB;
                    ab7.z += 20;
                }
                if (this.aB.z > 200) {
                    this.aB.z = 200;
                }
            }
            if (!fps.P) {
                final e ab8 = this.aB;
                --ab8.M;
                if (this.aB.M < 1) {
                    this.E();
                }
            }
            this.a.a(this.dw, this.ae.a, 5.0f, this.ae.c);
        }
        if (this.aB.L > -1 && !fps.P) {
            final e ab9 = this.aB;
            --ab9.L;
            if (this.aB.L < 1) {
                this.H();
            }
        }
        if (this.aB.O > -1) {
            if (!fps.P) {
                final e ab10 = this.aB;
                --ab10.O;
                if (this.aB.O < 1) {
                    this.F();
                }
            }
            this.a.a(this.dx, this.ae.a, 5.0f, this.ae.c);
        }
    }
    
    public final void J() {
        if (this.aB.L > -1) {
            this.H();
        }
        if (this.aB.K > -1) {
            this.G();
        }
        if (this.aB.M > -1) {
            this.E();
        }
        if (this.aB.O > -1) {
            this.F();
        }
    }
    
    public static void K() {
        --fps.N;
        if (fps.N <= -1) {
            fps.N = fps.O;
        }
    }
    
    public final void L() {
        if (this.bA == -1) {
            this.bA = 100;
        }
    }
    
    public final void n(final int ba) {
        if (this.bA == -1) {
            this.bA = ba;
        }
    }
    
    public final void M() {
        if (this.bA > -1) {
            --this.bA;
            if (this.bA <= 0) {
                this.bA = -1;
                this.as();
            }
        }
    }
    
    public final void N() {
        if (this.cc) {
            this.requestFocus();
        }
        this.az();
        this.A();
        if (!fps.M || fps.N <= 0) {
            this.z();
            this.I();
            this.e(this.dz, -1);
            this.c(this.dD, 50);
            if (!fps.S && !this.cp.n) {
                for (int i = 0; i < fps.F; ++i) {
                    this.d(this.dA[i], i);
                    this.c(this.dE[i], 50);
                    if (this.aC[i].c.d > 0) {
                        final e c = this.aC[i].c;
                        --c.d;
                    }
                    if (this.aC[i].c.O > -1 && this.aC[i].c.d > 0) {
                        final e c2 = this.aC[i].c;
                        --c2.d;
                    }
                }
            }
            this.l();
            if (this.aB.k) {
                this.aB = this.c(this.aB);
            }
            for (int j = 0; j < fps.F; ++j) {
                if (this.aC[j].c.k) {
                    this.aC[j].c = this.c(this.aC[j].c);
                }
            }
            this.O();
            this.P();
        }
        else {
            this.I();
            this.e(this.dz, -1);
            this.c(this.dD, 50);
        }
        this.u();
        this.ax();
        this.B();
        this.M();
        if (fps.M) {
            K();
        }
        if (fps.P && !this.cp.n) {
            this.au();
        }
    }
    
    public final void O() {
        for (int i = 0; i < this.cO.b; ++i) {
            final j j = this.ah[i];
            --j.D;
            if (this.ah[i].D < 1) {
                this.ah[i].D = this.ah[i].C;
                final j k = this.ah[i];
                ++k.B;
                if (this.ah[i].B >= this.ah[i].E) {
                    this.ah[i].B = 0;
                }
                this.a.b(this.ag[this.ah[i].n].w, this.cL[this.ah[i].z][this.ah[i].B].a);
            }
        }
    }
    
    public final void P() {
        if (this.aB.z > 0 && !this.cp.n && !fps.P) {
            --this.dy;
            if (this.dy <= 0) {
                this.ag(4);
                this.dy = (int)(100.0f * (this.aB.z / 100.0f)) + 20;
                if (this.aB.z <= 30 && this.cp.l <= -10) {
                    this.cp.l = 10;
                }
            }
        }
    }
    
    public final void o(final int n) {
        this.aC[n].b.h = 0;
        for (int i = 0; i < 22; ++i) {
            this.aC[n].b.g[i] = -1;
        }
    }
    
    public final boolean h(final int n, final int n2) {
        boolean b = false;
        for (int i = 0; i < this.aC[n2].b.h; ++i) {
            if (this.aC[n2].b.g[i] == n) {
                b = true;
            }
        }
        return b;
    }
    
    public final void i(final int n, final int n2) {
        this.aC[n2].b.g[this.aC[n2].b.h] = n;
        final f b = this.aC[n2].b;
        ++b.h;
    }
    
    public final int j(final int n, final int n2) {
        final int l = this.ae.l;
        this.o(n2);
        this.i(n, n2);
        return l;
    }
    
    public final int k(final int n, final int n2) {
        int n3 = n;
        while (n3 == n && this.cN[n3].l <= 0) {
            n3 = (int)(Math.random() * this.cO.d);
            if (this.cN[n3].l <= 0) {
                System.out.println("  " + n3);
            }
        }
        this.o(n2);
        this.i(n, n2);
        return n3;
    }
    
    public final void l(final int n, final int n2) {
        int n3 = 999999;
        this.aC[n2].b.d = -1;
        for (int i = 0; i < this.cN[this.aC[n2].a.l].l; ++i) {
            final int n4;
            if (((n4 = (int)b(this.cN[this.aC[n2].b.c].a, this.cN[this.aC[n2].b.c].b, this.cN[this.cN[this.aC[n2].a.l].k[i]].a, this.cN[this.cN[this.aC[n2].a.l].k[i]].b)) < n3 && this.cN[this.cN[this.aC[n2].a.l].k[i]].l > 1 && !this.h(this.cN[this.aC[n2].a.l].k[i], n2)) || this.cN[this.aC[n2].a.l].k[i] == n) {
                n3 = n4;
                this.aC[n2].b.d = this.cN[this.aC[n2].a.l].k[i];
                this.aC[n2].b.e = this.cN[this.aC[n2].a.l].m[i].a;
                this.aC[n2].b.f = this.cN[this.aC[n2].a.l].m[i].c;
            }
        }
        this.aC[n2].b.b = 0;
        if (this.cN[this.aC[n2].a.l].n > 0) {
            this.a(this.aC[n2].a.l, this.aC[n2].b.d, n2);
            this.aC[n2].b.b = 3;
        }
    }
    
    public final void p(final int n) {
        this.cv = this.cu * 0.06f;
        this.A(n);
        float a;
        float c;
        if (this.cN[this.aC[n].a.l].t > 0 && this.aC[n].g <= 0 && this.cN[this.aC[n].a.l].u[0].E > -1) {
            final float n2 = a(this.aC[n].a.a, this.aC[n].a.c, this.cN[this.aC[n].a.l].u[0].a, this.cN[this.aC[n].a.l].u[0].c);
            a = this.aC[n].a.a + (float)Math.sin(-n2 * 0.0174444) * (this.aC[n].c.G + 0.1f - this.cv);
            c = this.aC[n].a.c - (float)Math.cos(-n2 * 0.0174444) * (this.aC[n].c.G + 0.1f - this.cv);
            if (this.a(this.aC[n].a.l, a, c) > -1) {
                this.aC[n].g = 50;
            }
        }
        else {
            if (this.aC[n].g > 0) {
                final d d = this.aC[n];
                --d.g;
            }
            final int n3 = (this.aC[n].d == 1) ? 90 : -90;
            a = this.aC[n].a.a + (float)Math.sin(-(this.aC[n].a.e + n3) * 0.0174444) * (this.aC[n].c.G + 0.1f - this.cv);
            c = this.aC[n].a.c - (float)Math.cos(-(this.aC[n].a.e + n3) * 0.0174444) * (this.aC[n].c.G + 0.1f - this.cv);
        }
        if (!a(a, c, this.cN[this.aC[n].a.l].v.a, this.cN[this.aC[n].a.l].v.b, this.cN[this.aC[n].a.l].v.c, this.cN[this.aC[n].a.l].v.d)) {
            this.aC[n].d = 1 - this.aC[n].d;
            return;
        }
        if (this.a(this.aC[n].a.l, a, c) < 0) {
            this.aC[n].a.a = a;
            this.aC[n].a.c = c;
            return;
        }
        this.aC[n].d = 1 - this.aC[n].d;
    }
    
    public final void q(final int n) {
        this.o(n);
        Label_0568: {
            if (this.aC[n].a.l == this.ae.l && this.aB.B < 0 && this.aB.L <= -1) {
                this.aC[n].b.a = 1;
                this.aC[n].c.E = true;
            }
            else if (this.aC[n].c.E) {
                this.l(this.aC[n].b.c = this.j(this.aC[n].a.l, n), n);
                this.aC[n].b.a = 0;
                this.o(n);
                this.i(this.aC[n].a.l, n);
            }
            else {
                if (this.cO.e > 0) {
                    this.aC[n].b.c = this.r(n);
                    if (this.aC[n].b.c != -1) {
                        if (this.aC[n].a.l != this.aC[n].b.c) {
                            this.o(n);
                            this.l(this.aC[n].b.c, n);
                            this.aC[n].b.a = 0;
                            this.i(this.aC[n].a.l, n);
                            break Label_0568;
                        }
                        if (this.cN[this.aC[n].a.l].n == 0) {
                            this.aC[n].b.b = 4;
                            this.aC[n].b.e = (int)this.cN[this.aC[n].a.l].u[0].a;
                            this.aC[n].b.f = (int)this.cN[this.aC[n].a.l].u[0].c;
                            break Label_0568;
                        }
                        this.a(this.aC[n].a.l, (int)this.cN[this.aC[n].a.l].u[0].a, (int)this.cN[this.aC[n].a.l].u[0].c, n);
                        this.aC[n].b.b = 5;
                        break Label_0568;
                    }
                }
                this.aC[n].b.c = this.k(this.aC[n].a.l, n);
                this.o(n);
                this.l(this.aC[n].b.c, n);
                this.aC[n].b.a = 0;
            }
        }
        if (this.cN[this.aC[n].a.l].n > 0 && this.aC[n].b.b != 5) {
            this.a(this.aC[n].a.l, this.aC[n].b.d, n);
            this.aC[n].b.b = 3;
        }
    }
    
    public final int r(final int n) {
        int n2 = -1;
        int n3 = -1;
        int n4 = 0;
        for (int i = 0; i < this.cO.e; ++i) {
            if (this.cN[this.cO.f[i].g].u[this.cO.f[i].h].E > -1) {
                Label_0334: {
                    int n5 = 0;
                    Label_0332: {
                        if (this.cO.f[i].i == 0) {
                            n5 = 100 - this.aC[n].c.z;
                        }
                        else if (this.cO.f[i].i > 1 && this.cO.f[i].i < 30) {
                            n4 = (this.cO.f[i].i - 1) * 10;
                            if (this.aC[n].c.b == 0) {
                                n4 *= 3;
                            }
                            if (!this.aC[n].c.D[this.cO.f[i].i + 2]) {
                                break Label_0334;
                            }
                            n5 = 0;
                        }
                        else if (this.cO.f[i].i == 1) {
                            n5 = (100 - this.aC[n].c.A) / 2;
                        }
                        else {
                            if (this.cO.f[i].i < 30) {
                                break Label_0334;
                            }
                            if (this.cO.f[i].i == 30) {
                                n5 = 60;
                            }
                            else {
                                if (this.cO.f[i].i != 31) {
                                    if (this.cO.f[i].i == 32) {
                                        n5 = 60;
                                        break Label_0332;
                                    }
                                    if (this.cO.f[i].i != 33) {
                                        break Label_0334;
                                    }
                                }
                                n5 = 55;
                            }
                        }
                    }
                    n4 = n5;
                }
                Label_0434: {
                    final double b;
                    int n6;
                    int n7;
                    if ((b = b(this.aC[n].a.a, this.aC[n].a.c, this.cO.f[i].a, this.cO.f[i].c)) < 50.0) {
                        n6 = n4;
                        n7 = 10;
                    }
                    else if (b < 100.0) {
                        n6 = n4;
                        n7 = 5;
                    }
                    else {
                        if (b >= 150.0) {
                            break Label_0434;
                        }
                        n6 = n4;
                        n7 = 3;
                    }
                    n4 = n6 * n7;
                }
                if (n4 > n3) {
                    n3 = n4;
                    n2 = i;
                }
            }
        }
        if (n2 == -1) {
            return n2;
        }
        return this.cO.f[n2].g;
    }
    
    public final void s(final int n) {
        if (this.aC[n].b.b == -1) {
            if (this.aC[n].b.c == -1) {
                this.q(n);
            }
            this.aC[n].b.a = 0;
            this.l(this.aC[n].b.c, n);
            return;
        }
        if (this.aC[n].b.b == 0) {
            if (b(this.aC[n].a.a, this.aC[n].a.c, this.aC[n].b.e, this.aC[n].b.f) < 2.0) {
                this.aC[n].b.b = 1;
                if (this.aC[n].b.c <= -1) {
                    this.q(n);
                }
                if (this.aC[n].b.d <= -1) {
                    this.l(this.aC[n].b.c, n);
                    if (this.aC[n].b.d <= -1) {
                        this.a(n, 0, 0.0f, 0.0f, 0.5f, 3, -1, false, 0, false);
                    }
                }
                if (this.aC[n].c.B < 0 && this.aC[n].b.d > -1) {
                    final int m = this.m(this.aC[n].b.d, this.a(this.aC[n].a.a, this.aC[n].a.c));
                    this.aC[n].b.e = this.cN[this.aC[n].b.d].m[m].a;
                    this.aC[n].b.f = this.cN[this.aC[n].b.d].m[m].c;
                }
            }
        }
        else if (this.aC[n].b.b == 1) {
            if (b(this.aC[n].a.a, this.aC[n].a.c, this.aC[n].b.e, this.aC[n].b.f) < 2.0) {
                final boolean t;
                if (!(t = this.t(n))) {
                    if (this.cN[this.aC[n].a.l].n == 0) {
                        this.aC[n].b.b = 2;
                        this.aC[n].b.e = this.cN[this.aC[n].a.l].a;
                        this.aC[n].b.f = this.cN[this.aC[n].a.l].b;
                    }
                    else {
                        this.l(this.aC[n].b.c, n);
                        this.a(this.aC[n].a.l, this.aC[n].b.d, n);
                        this.aC[n].b.b = 3;
                    }
                    this.i(this.aC[n].a.l, n);
                    return;
                }
                if (t) {
                    if (this.cN[this.aC[n].a.l].t <= 0) {
                        this.aC[n].b.b = 2;
                        this.aC[n].b.e = this.cN[this.aC[n].a.l].a;
                        this.aC[n].b.f = this.cN[this.aC[n].a.l].b;
                        return;
                    }
                    if (this.cN[this.aC[n].a.l].n == 0) {
                        this.aC[n].b.b = 4;
                        this.aC[n].b.e = (int)this.cN[this.aC[n].a.l].u[0].a;
                        this.aC[n].b.f = (int)this.cN[this.aC[n].a.l].u[0].c;
                        return;
                    }
                    this.a(this.aC[n].a.l, (int)this.cN[this.aC[n].a.l].u[0].a, (int)this.cN[this.aC[n].a.l].u[0].c, n);
                    this.aC[n].b.b = 5;
                }
            }
        }
        else if (this.aC[n].b.b == 2 || this.aC[n].b.b == 4) {
            if (b(this.aC[n].a.a, this.aC[n].a.c, this.aC[n].b.e, this.aC[n].b.f) < 2.0) {
                this.aC[n].b.b = -1;
                if (this.t(n)) {
                    this.q(n);
                }
            }
        }
        else if (this.aC[n].b.b == 3) {
            if (b(this.aC[n].a.a, this.aC[n].a.c, this.aC[n].b.e, this.aC[n].b.f) < 2.0) {
                this.a(this.aC[n].a.l, this.aC[n].b.d, n);
            }
        }
        else if (this.aC[n].b.b == 5 && b(this.aC[n].a.a, this.aC[n].a.c, this.aC[n].b.e, this.aC[n].b.f) < 2.0) {
            this.a(this.aC[n].a.l, (int)this.cN[this.aC[n].a.l].u[0].a, (int)this.cN[this.aC[n].a.l].u[0].c, n);
        }
    }
    
    public final void a(final int n, final int e, final int f, final int n2) {
        boolean b = false;
        final float a = this.aC[n2].a.a;
        final float c = this.aC[n2].a.c;
        final double b2 = b(this.aC[n2].a.a, this.aC[n2].a.c, e, f);
        double n3 = 999999.0;
        for (int i = 0; i < this.cN[n].n; ++i) {
            final double b3 = b(this.cN[n].o[i].a, this.cN[n].o[i].c, e, f);
            final double b4 = b(this.cN[n].o[i].a, this.cN[n].o[i].c, a, c);
            if (b3 < b2 && b4 < n3 && b4 >= 2.0) {
                b = true;
                n3 = b4;
                this.aC[n2].b.e = this.cN[n].o[i].a;
                this.aC[n2].b.f = this.cN[n].o[i].c;
            }
        }
        if (!b) {
            this.aC[n2].b.b = 4;
            this.aC[n2].b.e = e;
            this.aC[n2].b.f = f;
        }
    }
    
    public final void a(final int n, final int n2, final int n3) {
        boolean b = false;
        final int m = this.m(n, n2);
        final int a = this.cN[this.aC[n3].a.l].m[m].a;
        final int c = this.cN[this.aC[n3].a.l].m[m].c;
        final float a2 = this.aC[n3].a.a;
        final float c2 = this.aC[n3].a.c;
        final double b2 = b(this.aC[n3].a.a, this.aC[n3].a.c, a, c);
        double n4 = 999999.0;
        for (int i = 0; i < this.cN[n].n; ++i) {
            final double b3 = b(this.cN[n].o[i].a, this.cN[n].o[i].c, a, c);
            final double b4 = b(this.cN[n].o[i].a, this.cN[n].o[i].c, a2, c2);
            if (b3 < b2 && b4 < n4 && b4 >= 2.0) {
                b = true;
                n4 = b4;
                this.aC[n3].b.e = this.cN[n].o[i].a;
                this.aC[n3].b.f = this.cN[n].o[i].c;
            }
        }
        if (!b) {
            this.aC[n3].b.b = 0;
            this.aC[n3].b.e = a;
            this.aC[n3].b.f = c;
        }
    }
    
    public final int m(final int n, final int n2) {
        int n3 = -1;
        if (n < 0 || n2 < 0) {
            n3 = 0;
        }
        else {
            for (int i = 0; i < this.cN[n].l; ++i) {
                if (this.cN[n].k[i] == n2) {
                    n3 = i;
                }
            }
        }
        if (n3 < 0) {
            n3 = 0;
        }
        return n3;
    }
    
    public final boolean t(final int n) {
        boolean b = false;
        if (this.aC[n].a.l == this.aC[n].b.c) {
            b = true;
            this.aC[n].b.c = -1;
            this.aC[n].b.b = -1;
        }
        return b;
    }
    
    public final boolean u(final int n) {
        return (n == -1 && this.aB.B >= 0) || (n >= 0 && this.aC[n].c.B >= 0);
    }
    
    public final void v(final int n) {
        e e;
        float n2;
        float n3;
        float n4;
        j j;
        if (this.aC[n].c.w == -1) {
            e = this.aC[n].c;
            n2 = this.aC[n].a.a;
            n3 = this.aC[n].a.c;
            n4 = this.ae.a;
            j = this.ae;
        }
        else {
            e = this.aC[n].c;
            n2 = this.aC[n].a.a;
            n3 = this.aC[n].a.c;
            n4 = this.aC[this.aC[n].c.w].a.a;
            j = this.aC[this.aC[n].c.w].a;
        }
        e.o = a(n2, n3, n4, j.c);
        this.aC[n].c.n = this.aC[n].c.o - this.aC[n].a.e;
        Label_0324: {
            e e2;
            float n5;
            if (this.aC[n].c.n > 359.0f) {
                e2 = this.aC[n].c;
                n5 = this.aC[n].c.n - 360.0f;
            }
            else {
                float n6;
                float n7;
                if (this.aC[n].c.n < -360.0f) {
                    e2 = this.aC[n].c;
                    n6 = this.aC[n].c.n;
                    n7 = 720.0f;
                }
                else {
                    if (this.aC[n].c.n >= 0.0f) {
                        break Label_0324;
                    }
                    e2 = this.aC[n].c;
                    n6 = this.aC[n].c.n;
                    n7 = 360.0f;
                }
                n5 = n6 + n7;
            }
            e2.n = n5;
        }
        if (this.aC[n].c.n > 165.0f && this.aC[n].c.n < 195.0f && this.aC[n].c.d <= 0 && !this.u(this.aC[n].c.w) && this.aC[n].a.l != -1) {
            if (this.aC[n].c.C[this.aC[n].c.b] > 0) {
                fps fps;
                j[] array;
                int n8;
                int n9;
                boolean b;
                if (this.x(n)) {
                    fps = this;
                    array = this.dA[n];
                    n8 = this.aC[n].c.b;
                    n9 = n;
                    b = false;
                }
                else {
                    fps = this;
                    array = this.dA[n];
                    n8 = this.aC[n].c.b;
                    n9 = n;
                    b = true;
                }
                fps.a(array, n8, n9, b, this.aC[n].c.c);
                this.aC[n].c.e = this.dL[this.aC[n].c.b].v;
                this.aC[n].c.f = this.dL[this.aC[n].c.b].w;
                this.aC[n].c.g = this.dL[this.aC[n].c.b].w;
                this.aC[n].c = this.a(this.aC[n].c, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].c.b);
                final int[] c = this.aC[n].c.C;
                final int b2 = this.aC[n].c.b;
                --c[b2];
                return;
            }
            this.aC[n].c = this.a(this.aC[n].c, 0);
        }
    }
    
    public final boolean w(final int n) {
        return fps.Y == 2 || n == this.ae.l || (fps.Y == 1 && this.y(n)) || (fps.Y == 0 && this.z(n));
    }
    
    public final boolean x(final int n) {
        return fps.Y == 2 || this.aC[n].a.l == this.ae.l || (fps.Y == 1 && this.y(this.aC[n].a.l)) || (fps.Y == 0 && this.z(this.aC[n].a.l));
    }
    
    public final boolean y(final int n) {
        for (int i = 0; i < this.cN[this.ae.l].i; ++i) {
            if (this.cN[this.ae.l].j[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public final boolean z(final int n) {
        for (int i = 0; i < this.cN[this.ae.l].l; ++i) {
            if (this.cN[this.ae.l].k[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public final void A(final int n) {
        if (this.aC[n].c.w > -2) {
            j j;
            float n2;
            float n3;
            float n4;
            j i;
            if (this.aC[n].c.w == -1) {
                j = this.aC[n].a;
                n2 = this.aC[n].a.a;
                n3 = this.aC[n].a.c;
                n4 = this.ae.a;
                i = this.ae;
            }
            else {
                j = this.aC[n].a;
                n2 = this.aC[n].a.a;
                n3 = this.aC[n].a.c;
                n4 = this.aC[this.aC[n].c.w].a.a;
                i = this.aC[this.aC[n].c.w].a;
            }
            j.g = a(n2, n3, n4, i.c);
            for (int k = 0; k < 3; ++k) {
                this.B(n);
            }
        }
    }
    
    public final boolean B(final int n) {
        boolean b = false;
        this.aC[n].c.m = this.aC[n].a.g - this.aC[n].a.e;
        Label_0177: {
            e e;
            float m;
            if (this.aC[n].c.m > 359.0f) {
                e = this.aC[n].c;
                m = 360.0f - this.aC[n].c.m;
            }
            else {
                float n2;
                float n3;
                if (this.aC[n].c.m < -360.0f) {
                    e = this.aC[n].c;
                    n2 = this.aC[n].c.m;
                    n3 = 720.0f;
                }
                else {
                    if (this.aC[n].c.m >= 0.0f) {
                        break Label_0177;
                    }
                    e = this.aC[n].c;
                    n2 = this.aC[n].c.m;
                    n3 = 360.0f;
                }
                m = n2 + n3;
            }
            e.m = m;
        }
        boolean b2;
        if (this.aC[n].c.m >= 0.0f && this.aC[n].c.m < 179.0f) {
            final j a = this.aC[n].a;
            --a.e;
            if (this.aC[n].a.e < 0.0f) {
                final j a2 = this.aC[n].a;
                a2.e += 360.0f;
            }
            b2 = true;
        }
        else {
            if (this.aC[n].c.m < 180.0f || this.aC[n].c.m > 360.0f) {
                return b;
            }
            final j a3 = this.aC[n].a;
            ++a3.e;
            if (this.aC[n].a.e > 359.0f) {
                this.aC[n].a.e = 360.0f - this.aC[n].a.e;
            }
            b2 = true;
        }
        b = b2;
        return b;
    }
    
    public final void a(final int n, final int t, final float h, final float j, final float i, final int u, final int n2, final boolean b, final int n3, final boolean b2) {
        this.l(n);
        this.aC[n].j = false;
        this.aC[n].a.G = 0;
        this.aC[n].c.B = 0;
        this.aC[n].c.t = t;
        this.aC[n].c.u = u;
        if (n2 < -199 || b) {
            if (this.x(n)) {
                this.ag(7);
            }
            if (this.dL[n3].E) {
                this.q(n, 2);
                this.a(n, 1);
                this.aC[n].j = true;
            }
            else {
                this.q(n, 0);
                this.a(n, 0);
            }
            this.f(n, 10);
            return;
        }
        Label_0254: {
            fps fps;
            int n4;
            int n5;
            if (n3 == 2 || n3 == 3) {
                fps = this;
                n4 = n;
                n5 = 1;
            }
            else if (n3 == 8 || n3 == 9) {
                fps = this;
                n4 = n;
                n5 = 3;
            }
            else if (n3 == 7) {
                fps = this;
                n4 = n;
                n5 = 0;
            }
            else if (n3 == 6) {
                fps = this;
                n4 = n;
                n5 = 4;
            }
            else if (n3 == 4) {
                fps = this;
                n4 = n;
                n5 = 6;
            }
            else if (n3 == 5) {
                fps = this;
                n4 = n;
                n5 = 5;
            }
            else {
                if (n3 != 1) {
                    break Label_0254;
                }
                fps = this;
                n4 = n;
                n5 = 2;
            }
            fps.f(n4, n5);
        }
        if (this.x(n)) {
            this.ag(1);
        }
        this.aC[n].a.h = h;
        this.aC[n].a.i = i;
        this.aC[n].a.j = j;
        this.aC[n].a.d = 0.0f;
        j k;
        float l;
        if (b2) {
            k = this.aC[n].a;
            l = 5.0f;
        }
        else {
            k = this.aC[n].a;
            l = 0.0f;
        }
        k.k = l;
        this.aC[n].c.t = 1;
        this.aC[n].a.B = 8;
        this.aC[n].a.C = 5;
        final j a = this.aC[n].a;
        --a.b;
        this.a.c(this.aC[n].a.w, this.f[this.aC[n].a.B]);
    }
    
    public final void C(final int n) {
        final e c = this.aC[n].c;
        ++c.B;
        final j a = this.aC[n].a;
        --a.C;
        if (this.aC[n].a.C <= 0) {
            this.aC[n].a.C = this.aC[n].c.u;
            final j a2 = this.aC[n].a;
            ++a2.B;
            if (this.aC[n].a.B > fps.ac) {
                this.aC[n].a.B = fps.ac;
            }
            this.a.c(this.aC[n].a.w, this.f[this.aC[n].a.B]);
        }
        this.D(n);
        if (this.aC[n].l > -1) {
            this.a(this.aC[n].k, 100, n);
        }
        if (this.aC[n].c.B > 100 && this.aB.y >= this.cp.g) {
            this.L();
            return;
        }
        if (this.aC[n].c.B > 200 && ((this.aC[n].a.B == fps.ac && this.aC[n].c.t == 0) || (this.aC[n].a.B == fps.ac && this.aC[n].c.t > 0))) {
            final j a3 = this.aC[n].a;
            a3.b -= 0.05;
        }
        if (this.aC[n].c.B > 250) {
            this.a(false, n);
        }
    }
    
    public final void D(final int n) {
        final float n2 = (this.aC[n].a.k == 0.0f) ? -1.0f : 0.0f;
        final j a = this.aC[n].a;
        a.b += this.aC[n].a.i;
        this.aC[n].a = this.a(this.aC[n].a, this.a(this.aC[n].a.a, this.aC[n].a.c), this.aC[n].a.a + this.aC[n].a.h, this.aC[n].a.c + this.aC[n].a.j, false);
        Label_0232: {
            j j;
            float d;
            if (this.aC[n].a.G < 1) {
                d = (j = this.aC[n].a).d - this.aC[n].a.k;
            }
            else {
                if (this.aC[n].a.k == 0.0f) {
                    break Label_0232;
                }
                j = this.aC[n].a;
                d = 180.0f;
            }
            j.d = d;
        }
        final j a2 = this.aC[n].a;
        a2.h *= 0.98;
        final j a3 = this.aC[n].a;
        a3.j *= 0.98;
        if (this.aC[n].a.i > 0.0f) {
            final j a4 = this.aC[n].a;
            a4.i *= 0.8;
            if (this.aC[n].a.i < 0.01) {
                this.aC[n].a.i = -0.05f;
            }
        }
        else {
            final j a5 = this.aC[n].a;
            a5.i *= 1.2;
            if (this.aC[n].a.i < -0.95) {
                this.aC[n].a.i = -0.95f;
            }
            if (this.aC[n].a.b <= n2) {
                if (this.aC[n].a.G < 3) {
                    final j a6 = this.aC[n].a;
                    a6.i *= (float)(-0.2);
                    final j a7 = this.aC[n].a;
                    ++a7.G;
                    return;
                }
                this.aC[n].a.i = 0.0f;
                final j a8 = this.aC[n].a;
                a8.h *= 0.8;
                final j a9 = this.aC[n].a;
                a9.j *= 0.8;
            }
        }
    }
    
    public final void E(final int n) {
        final j a = this.aC[n].a;
        --a.C;
        if (this.aC[n].a.C <= 0) {
            this.aC[n].a.C = 3;
            final j a2 = this.aC[n].a;
            ++a2.B;
            if (this.aC[n].a.B > fps.ab - 1) {
                this.aC[n].a.B = 0;
            }
            if (this.aC[n].c.K > -1) {
                this.a.c(this.aD[0].w, this.f[this.aC[n].a.B]);
                return;
            }
            if (this.aC[n].c.L > -1) {
                this.a.c(this.aD[1].w, this.f[this.aC[n].a.B]);
                return;
            }
            if (this.aC[n].c.M > -1) {
                this.a.c(this.aD[2].w, this.f[this.aC[n].a.B]);
                return;
            }
            if (this.aC[n].c.O > -1) {
                this.a.c(this.aD[3].w, this.f[this.aC[n].a.B]);
                return;
            }
            this.a.c(this.aC[n].a.w, this.f[this.aC[n].a.B]);
        }
    }
    
    public final int a(final int n, final int n2, final int[] array) {
        int n3 = -2;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < fps.F; ++j) {
                if (this.aC[j].a.l == array[i] && this.aC[j].c.x != this.aC[n].c.x && j != n && this.aC[j].c.B < 0 && this.aC[j].c.L <= -1) {
                    this.aC[n].c.w = j;
                    n3 = j;
                }
            }
            if (n3 == -2 && this.aB.B < 0 && this.aB.x != this.aC[n].c.x && this.ae.l == array[i] && this.aB.L <= -1) {
                this.aC[n].c.w = -1;
                n3 = -1;
            }
        }
        return n3;
    }
    
    public final void F(final int n) {
        if (this.aC[n].b.a == 0 && this.aC[n].b.b > -1) {
            if (this.aC[n].a.l == -1) {
                this.aC[n].a.l = this.a(this.aC[n].a.a, this.aC[n].a.c);
            }
            final int a;
            if ((a = this.a(n, this.cN[this.aC[n].a.l].l, this.cN[this.aC[n].a.l].k)) >= 0) {
                this.aC[n].a.g = a(this.aC[n].a.a, this.aC[n].a.c, this.aC[a].a.a, this.aC[a].a.c);
                for (int i = 0; i < 3; ++i) {
                    this.B(n);
                }
            }
            else if (a == -1) {
                this.aC[n].a.g = a(this.aC[n].a.a, this.aC[n].a.c, this.ae.a, this.ae.c);
                for (int j = 0; j < 3; ++j) {
                    this.B(n);
                }
            }
            else {
                this.aC[n].a.g = a(this.aC[n].a.a, this.aC[n].a.c, this.aC[n].b.e, this.aC[n].b.f);
                for (int k = 0; k < 3; ++k) {
                    this.B(n);
                }
            }
            this.aC[n].a.g = a(this.aC[n].a.a, this.aC[n].a.c, this.aC[n].b.e, this.aC[n].b.f);
            if (this.aC[n].c.m > 170.0f && this.aC[n].c.m < 190.0f) {
                final j a2 = this.aC[n].a;
                a2.a += (float)(Math.sin(-this.aC[n].a.g * 0.0174444) * this.aC[n].c.F);
                final j a3 = this.aC[n].a;
                a3.c -= (float)(Math.cos(-this.aC[n].a.g * 0.0174444) * this.aC[n].c.F);
                return;
            }
            j l;
            double n2;
            double n3;
            float g;
            if (a != -2) {
                final j a4 = this.aC[n].a;
                a4.a += (float)(Math.sin(-this.aC[n].a.g * 0.0174444) * (this.aC[n].c.G - this.cv));
                n2 = (l = this.aC[n].a).c;
                n3 = Math.cos(-this.aC[n].a.g * 0.0174444);
                g = this.aC[n].c.G - this.cv;
            }
            else {
                final j a5 = this.aC[n].a;
                a5.a += (float)(Math.sin(-this.aC[n].a.g * 0.0174444) * this.aC[n].c.G);
                n2 = (l = this.aC[n].a).c;
                n3 = Math.cos(-this.aC[n].a.g * 0.0174444);
                g = this.aC[n].c.G;
            }
            l.c = (float)(n2 - n3 * g);
        }
    }
    
    public static void a(final Graphics graphics, final String s, int n, final int n2) {
        n -= graphics.getFontMetrics().stringWidth(s);
        graphics.drawString(s, n, n2);
    }
    
    public static void a(final Graphics graphics, final String s, int n, final int n2, final Color color) {
        n -= graphics.getFontMetrics().stringWidth(s);
        graphics.setColor(Color.black);
        graphics.drawString(s, n - 1, n2 - 1);
        graphics.setColor(color);
        graphics.drawString(s, n, n2);
    }
    
    public final void Q() {
        for (int i = 0; i < this.y; ++i) {
            final m m;
            (m = this.ch[i]).h = false;
            m.g = -1;
        }
    }
    
    public final void a(final Graphics graphics) {
        for (int i = 0; i < this.y; ++i) {
            if (this.ch[i].h) {
                this.ch[i].a(graphics, this.da);
            }
        }
    }
    
    public final void a(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(Color.white);
        graphics.setFont(this.dd);
        int n4 = 0;
        int n5 = 10;
        if (this.bO) {
            n5 = 15;
        }
        for (int i = 0; i < 6; ++i) {
            Graphics graphics2;
            String s;
            int n6;
            int n7;
            Color bn;
            if (this.bF[i] < 5) {
                if (this.bF[i] >= fps.F) {
                    continue;
                }
                graphics2 = graphics;
                s = String.valueOf(this.aC[this.bF[i]].c.h) + " : " + this.bH[this.bF[i]] + " round(s)";
                n6 = n + n3;
                n7 = n2 + n4 * n5;
                bn = this.bW[3];
            }
            else {
                graphics2 = graphics;
                s = "Your score : " + this.bH[this.bF[i]] + " round(s)";
                n6 = n + n3;
                n7 = n2 + n4 * n5;
                bn = this.bN;
            }
            a(graphics2, s, n6, n7, bn);
            ++n4;
        }
    }
    
    public final void b(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(Color.white);
        graphics.setFont(this.dd);
        int n4 = 0;
        int n5 = 10;
        if (this.bO) {
            n5 = 15;
        }
        for (int i = 0; i < 6; ++i) {
            Graphics graphics2;
            String s;
            int n6;
            int n7;
            Color bn;
            if (this.bE[i] < 5) {
                if (this.bE[i] >= fps.F) {
                    continue;
                }
                graphics2 = graphics;
                s = String.valueOf(this.aC[this.bE[i]].c.h) + " : " + this.aC[this.bE[i]].c.y + " frag(s)";
                n6 = n + n3;
                n7 = n2 + n4 * n5;
                bn = this.bW[3];
            }
            else {
                graphics2 = graphics;
                s = "Your score : " + this.bG[this.bE[i]] + " frag(s)";
                n6 = n + n3;
                n7 = n2 + n4 * n5;
                bn = this.bN;
            }
            a(graphics2, s, n6, n7, bn);
            ++n4;
        }
    }
    
    public final void c(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(Color.white);
        graphics.setFont(this.da);
        if (fps.W == 0) {
            int n4 = 0;
            for (int i = 0; i < 6; ++i) {
                Graphics graphics2;
                StringBuffer append;
                int y;
                if (this.bE[i] < 5) {
                    graphics.setColor(Color.white);
                    if (this.bE[i] >= fps.F) {
                        continue;
                    }
                    graphics2 = graphics;
                    append = new StringBuffer(String.valueOf(this.aC[this.bE[i]].c.h)).append(" : ");
                    y = this.aC[this.bE[i]].c.y;
                }
                else {
                    graphics.setColor(this.bN);
                    graphics2 = graphics;
                    append = new StringBuffer("Your score : ");
                    y = this.bG[this.bE[i]];
                }
                a(graphics2, append.append(y).toString(), n + n3, n2 + n4 * 10);
                ++n4;
            }
            return;
        }
        if (fps.W == 1) {
            a(graphics, "Your score : " + this.aB.y, n + n3, n2);
        }
    }
    
    public final void b(final Graphics graphics) {
        if (this.aB.b == 0 && this.aB.C[0] < 1) {
            graphics.setColor(Color.darkGray);
            a(graphics, this.cV, "Out Of", (int)(fps.D * 0.49f), -1, 1);
            a(graphics, this.cV, "Ammunition", (int)(fps.D * 0.52f), -1, 1);
            return;
        }
        if (this.dL[this.aB.b].D && this.aB.d > 0) {
            graphics.setColor(Color.darkGray);
            a(graphics, this.cV, "Weapon", (int)(fps.D * 0.49f), -1, 1);
            a(graphics, this.cV, "Reloading", (int)(fps.D * 0.52f), -1, 1);
            if (this.bz > -1) {
                --this.bz;
            }
            if (this.by > -1) {
                --this.by;
            }
        }
        else {
            if (this.bz > -1) {
                graphics.setColor(Color.red);
                --this.bz;
            }
            else {
                graphics.setColor(Color.lightGray);
            }
            if (this.by > -1) {
                graphics.setColor(Color.green);
                graphics.drawOval(fps.B / 2 - 12, fps.D / 2 - 12, 24, 24);
                --this.by;
                return;
            }
            graphics.drawOval(fps.B / 2 - 8, fps.D / 2 - 8, 16, 16);
        }
    }
    
    public final void a(final Graphics graphics, final int n) {
        graphics.setColor(Color.white);
        graphics.setFont(this.cW);
        Graphics graphics3 = null;
        Image image = null;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        Label_0562: {
            if (this.aB.A > 0) {
                if (this.bO) {
                    Label_0095: {
                        Graphics graphics2;
                        Color color;
                        if (this.cp.l > -1) {
                            final l cp = this.cp;
                            --cp.l;
                            graphics2 = graphics;
                            color = Color.red;
                        }
                        else {
                            if (this.cp.l <= -10) {
                                break Label_0095;
                            }
                            final l cp2 = this.cp;
                            --cp2.l;
                            graphics2 = graphics;
                            color = Color.white;
                        }
                        graphics2.setColor(color);
                    }
                    graphics.drawString(String.valueOf(this.aB.z), 45, fps.D - 20 - n);
                    graphics.drawImage(this.dP, 10, (int)(fps.D - 20 - n - 30.0f), 30, 30, this);
                    graphics.setColor(Color.white);
                    graphics.drawString(String.valueOf(this.aB.A), 125, fps.D - 20 - n);
                    graphics3 = graphics;
                    image = this.dQ;
                    n2 = 90;
                    n3 = (int)(fps.D - 20 - n - 30.0f);
                    n4 = 30;
                    n5 = 30;
                    break Label_0562;
                }
                Label_0271: {
                    Graphics graphics4;
                    Color color2;
                    if (this.cp.l > -1) {
                        final l cp3 = this.cp;
                        --cp3.l;
                        graphics4 = graphics;
                        color2 = Color.red;
                    }
                    else {
                        if (this.cp.l <= -10) {
                            break Label_0271;
                        }
                        final l cp4 = this.cp;
                        --cp4.l;
                        graphics4 = graphics;
                        color2 = Color.white;
                    }
                    graphics4.setColor(color2);
                }
                graphics.drawString(String.valueOf(this.aB.z), 35, fps.D - 20 - n);
                graphics.drawImage(this.dP, 10, fps.D - 20 - n - 20, 20, 20, this);
                graphics.setColor(Color.white);
                graphics.drawString(String.valueOf(this.aB.A), 85, fps.D - 20 - n);
                graphics3 = graphics;
                image = this.dQ;
                n2 = 60;
            }
            else {
                if (this.bO) {
                    Label_0433: {
                        Graphics graphics5;
                        Color color3;
                        if (this.cp.l > -1) {
                            final l cp5 = this.cp;
                            --cp5.l;
                            graphics5 = graphics;
                            color3 = Color.red;
                        }
                        else {
                            if (this.cp.l <= -10) {
                                break Label_0433;
                            }
                            final l cp6 = this.cp;
                            --cp6.l;
                            graphics5 = graphics;
                            color3 = Color.white;
                        }
                        graphics5.setColor(color3);
                    }
                    graphics.drawString(String.valueOf(this.aB.z), 45, fps.D - 20 - n);
                    graphics3 = graphics;
                    image = this.dP;
                    n2 = 10;
                    n3 = (int)(fps.D - 20 - n - 30.0f);
                    n4 = 30;
                    n5 = 30;
                    break Label_0562;
                }
                if (this.cp.l > -1) {
                    final l cp7 = this.cp;
                    --cp7.l;
                    graphics.setColor(Color.red);
                }
                graphics.drawString(String.valueOf(this.aB.z), 35, fps.D - 20 - n);
                graphics3 = graphics;
                image = this.dP;
                n2 = 10;
            }
            n3 = fps.D - 20 - n - 20;
            n4 = 20;
            n5 = 20;
        }
        graphics3.drawImage(image, n2, n3, n4, n5, this);
        graphics.setColor(Color.white);
        if (this.aB.c && this.aB.b == 0) {
            graphics.setColor(this.bM);
            graphics.fillRect(fps.B - 50 - 10 - 5, fps.D - 20 - 25 - n - 5, 55, 30);
            graphics.drawImage(this.dO[this.aB.b], fps.B - 50 - 10 - 5, fps.D - 20 - 25 - n - 5, 50, 25, null);
            graphics.drawImage(this.dO[this.aB.b], fps.B - 50 - 10, fps.D - 20 - 25 - n + 5, 50, 25, null);
        }
        else {
            graphics.drawImage(this.dO[this.aB.b], fps.B - 50 - 10, fps.D - 20 - 25 - n, 50, 25, this.bM, null);
        }
        a(graphics, String.valueOf(this.aB.C[this.aB.b]), fps.B - 50 - 10 - 5, fps.D - 20 - 25 - n + 25);
        if (this.aB.O > 0) {
            this.a(graphics, Color.yellow, this.dS, n, "Time Shift: " + this.aB.O / 40);
            return;
        }
        if (this.aB.K > -1) {
            this.a(graphics, Color.cyan, this.dR, n, "Quad Damage: " + this.aB.K / 40);
            return;
        }
        if (this.aB.L > -1) {
            this.a(graphics, Color.green, this.dT, n, "Invisibility: " + this.aB.L / 40);
            return;
        }
        if (this.aB.M > -1) {
            this.a(graphics, this.bY, this.dU, n, "Protection: " + this.aB.M / 40);
        }
    }
    
    public final void a(final Graphics graphics, final Color color, final Image image, final int n, final String s) {
        graphics.setFont(this.cV);
        graphics.setColor(color);
        graphics.drawImage(image, (int)(fps.B * 0.35f) - 20, fps.D - 20 - n - 20 + 5, 20, 20, this);
        graphics.drawImage(image, (int)(fps.B * 0.65f), fps.D - 20 - n - 20 + 5, 20, 20, this);
        a(graphics, this.cY, s, fps.D - 20 - n, -1, 1);
    }
    
    public final void c(final Graphics graphics) {
        if (this.aB.B < 0 && !fps.P) {
            this.b(graphics);
        }
        graphics.setColor(Color.white);
        graphics.setFont(this.cU);
        if (this.cf.h) {
            this.cf.a(graphics, this.dc);
            if (this.cg.h && this.cf.j) {
                this.cg.a(graphics, this.dc);
            }
        }
        this.a(graphics);
        graphics.setFont(this.da);
        this.X();
        this.c(graphics, fps.B - 10, 10, 0);
    }
    
    public final void d(final Graphics graphics, final int n, final int n2, final int n3) {
        final int n4 = fps.B / 5;
        graphics.setColor(Color.white);
        Graphics graphics2;
        Font font;
        if (this.bO) {
            graphics2 = graphics;
            font = this.dc;
        }
        else {
            graphics2 = graphics;
            font = this.da;
        }
        graphics2.setFont(font);
        for (int i = 0; i < 5; ++i) {
            final int ak = this.ak(i);
            final int n5 = n + i * n4;
            int n6;
            if (this.aB.b == ak) {
                graphics.setColor(Color.white);
                n6 = 10;
            }
            else {
                graphics.setColor(Color.white.darker());
                n6 = 0;
            }
            final int n7 = n6;
            if (this.aB.C[ak] > 0) {
                Graphics graphics3;
                Image image;
                int n8;
                int n9;
                int n10;
                int n11;
                Color color;
                if (ak == this.aB.b) {
                    graphics3 = graphics;
                    image = this.dO[ak];
                    n8 = n5 - this.bJ;
                    n9 = fps.D - this.bK - 1 - n7 + n3;
                    n10 = this.bI;
                    n11 = this.bK;
                    color = this.bM;
                }
                else {
                    graphics3 = graphics;
                    image = this.dO[ak];
                    n8 = n5 - this.bJ;
                    n9 = fps.D - this.bK - 1 + n3;
                    n10 = this.bI;
                    n11 = this.bK;
                    color = this.bL;
                }
                graphics3.drawImage(image, n8, n9, n10, n11, color, null);
            }
            graphics.drawRect(n5 - n4 / 2, fps.D - this.bK - 2 - n7 + n3, n4 - 1, this.bK);
            graphics.setColor(Color.white);
            graphics.drawString(String.valueOf(i + 1) + ".", n5 - n4 / 2 + 1, n2 - n7 + n3);
        }
    }
    
    public final void R() {
        int n = -1;
        int bb = 0;
        for (int i = 0; i < 6; ++i) {
            if (this.bG[i] >= n) {
                bb = i;
                n = this.bG[i];
            }
        }
        this.bB = bb;
        final int[] bh = this.bH;
        final int bb2 = this.bB;
        ++bh[bb2];
    }
    
    public final void d(final Graphics graphics) {
        if (this.cp.c > 0) {
            graphics.drawImage(this.aR, this.cp.c, 0, fps.B, fps.D, this);
            final l cp = this.cp;
            cp.c -= 10;
            if (this.cp.c < 0) {
                this.cp.c = 0;
            }
        }
        else if (this.cp.c < 0) {
            graphics.drawImage(this.aR, this.cp.c, 0, fps.B, fps.D, this);
            final l cp2 = this.cp;
            cp2.c -= 10;
            if (this.cp.c < -fps.B) {
                this.ag();
            }
        }
        else {
            graphics.drawImage(this.aR, 0, 0, fps.B, fps.D, this);
        }
        graphics.setFont(this.cZ);
        a(graphics, this.cS, "End of Round " + this.cp.e, 25, -this.cp.c, Color.white);
        graphics.setFont(this.cW);
        graphics.setColor(Color.lightGray);
        Graphics graphics2;
        Font font;
        String string;
        int n;
        int n2;
        Color color;
        if (this.bB >= 5) {
            graphics2 = graphics;
            font = this.de;
            string = "You Win This Round!";
            n = (int)(fps.D * 0.15);
            n2 = -this.cp.c;
            color = Color.green;
        }
        else {
            a(graphics, this.de, "You Lost!", (int)(fps.D * 0.15), -this.cp.c, Color.red);
            graphics2 = graphics;
            font = this.de;
            string = String.valueOf(this.aC[this.bB].c.h) + " wins this round ";
            n = (int)(fps.D * 0.2);
            n2 = -this.cp.c;
            color = Color.red;
        }
        a(graphics2, font, string, n, n2, color);
        this.d(graphics, -this.cp.c);
        graphics.setColor(Color.white);
        if (this.ad()) {
            graphics.setColor(Color.red);
            a(graphics, this.cX, "You have been beaten and knocked out of the tournament.", (int)(fps.D * 0.9), -this.cp.c, Color.red);
            a(graphics, this.cX, "> > > >  G A M E   O V E R  < < < <", (int)(fps.D * 0.95), -this.cp.c, Color.red);
        }
        else if (this.cp.m == 4) {
            b(graphics, this.cT, "Prepare for the next round!", (int)(fps.D * 0.9), -this.cp.c);
        }
        else if (this.cp.f - this.bH[5] > 0) {
            b(graphics, this.cT, "You must win " + (this.cp.f - this.bH[5]) + " more round(s) to proceed to the next level.", (int)(fps.D * 0.9), -this.cp.c);
            b(graphics, this.cT, "Prepare for the next round!", (int)(fps.D * 0.95), -this.cp.c);
        }
        else if (this.cp.f - this.bH[5] <= 0) {
            graphics.setColor(Color.green);
            Graphics graphics3;
            Font font2;
            String s;
            if (this.bO) {
                graphics3 = graphics;
                font2 = this.cT;
                s = "Congratulations, you qualify for the next level of the tournament!";
            }
            else {
                graphics3 = graphics;
                font2 = this.cT;
                s = "You qualify for the next level of the tournament!";
            }
            b(graphics3, font2, s, (int)(fps.D * 0.9), -this.cp.c);
            if (this.cp.h >= 2) {
                b(graphics, this.cT, "The Level Password is : " + this.S(), (int)(fps.D * 0.95), -this.cp.c);
                this.T();
            }
        }
        if (this.cp.c == 0) {
            this.k(graphics);
        }
    }
    
    public final String S() {
        String s;
        return (this.cp.h == 2) ? (s = "comforts") : ((this.cp.h == 3) ? (s = "madness") : (s = "spoonfed"));
    }
    
    public final void T() {
        if (this.cp.h < 5 && this.cp.v[this.cp.h + 1]) {
            this.cp.v[this.cp.h + 1] = false;
        }
    }
    
    public final void e(final Graphics graphics) {
        if (this.cp.u) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, fps.B, fps.D);
            this.cp.u = false;
        }
    }
    
    public final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n == 16) {
            graphics.drawImage(this.aL, n2, n3, n4, n5, this);
            return;
        }
        if (n == 15) {
            graphics.drawImage(this.aM, n2, n3, n4, n5, this);
            return;
        }
        if (n == 18) {
            graphics.drawImage(this.aN, n2, n3, n4, n5, this);
            return;
        }
        if (n == 19) {
            graphics.drawImage(this.aO, n2, n3, n4, n5, this);
            return;
        }
        if (n == 5) {
            graphics.drawImage(this.aP, n2, n3, n4, n5, this);
            return;
        }
        if (n == 4) {
            graphics.drawImage(this.aQ, n2, n3, n4, n5, this);
        }
    }
    
    public final void e(final Graphics graphics, final int n, final int n2, final int n3) {
        final float n4 = this.bO ? 0.35f : 0.225f;
        final int n5 = (int)(this.aL.getWidth(this) * n4);
        final int n6 = (int)(this.aL.getHeight(this) * n4);
        final int n7 = fps.B / 2 - (n5 * 6 + n2 * 5) / 2;
        for (int i = 0; i < 6; ++i) {
            this.a(graphics, this.cp.i[i], n7 + n5 * i + n2 * i, n, n5, n6);
            Graphics graphics2;
            String s;
            if (n3 == 0) {
                if (i != this.cp.h) {
                    continue;
                }
                graphics.setColor(Color.white);
                graphics.setFont(this.cV);
                graphics2 = graphics;
                s = "You Are Here";
            }
            else {
                if (n3 != 1) {
                    continue;
                }
                if (this.cp.v[i]) {
                    graphics.setColor(Color.red);
                    graphics.setFont(this.cV);
                    graphics2 = graphics;
                    s = "Locked";
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.setFont(this.cV);
                    graphics2 = graphics;
                    s = "Unlocked";
                }
            }
            b(graphics2, s, n7 + n5 * i + n2 * i + n5 / 2, n + n6 + 15);
        }
    }
    
    public final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawImage(this.aS, n, n2, n3, n4, this);
        if (this.cn > n && this.cn < n + n3 && this.co > n2 && this.co < n2 + n3) {
            graphics.setColor(Color.white);
            graphics.drawRect(n - 1, n2 - 1, n3 + 2, n4 + 2);
            graphics.setColor(Color.white);
            graphics.setFont(this.cP);
            a(graphics, "Visit Our Sponsor (this will NOT interrupt your game)", n + n3, n2 - 2);
            return;
        }
        graphics.setColor(Color.white);
        graphics.setFont(this.cP);
        a(graphics, "Advertisement", n + n3, n2 - 2);
    }
    
    public final void f(final Graphics graphics) {
        float n = 1.5f;
        if (!this.bO) {
            n = 0.75f;
        }
        this.e(graphics);
        graphics.drawImage(this.aU, 0, 0, fps.B, fps.D, this);
        final int n2 = (int)(this.aL.getWidth(this) * n);
        final int n3 = (int)(this.aL.getHeight(this) * n);
        final int n4 = fps.B / 2 - n2 / 2;
        Label_0690: {
            Graphics graphics2;
            Font font;
            String s;
            int n5;
            if (this.bi) {
                if (this.bO) {
                    this.a(graphics, fps.B / 2 - this.aS.getWidth(this) / 2, fps.D / 2 - this.aS.getHeight(this) / 2, this.aS.getWidth(this), this.aS.getHeight(this));
                    graphics.setColor(Color.lightGray);
                    a(graphics, this.cW, "You must score " + this.cp.g + " frags to win a round.", (int)(fps.D * 0.85f), 0, Color.lightGray);
                    graphics2 = graphics;
                    font = this.cW;
                    s = "You must win " + this.cp.f + " rounds to advance to the next level.";
                    n5 = (int)(fps.D * 0.87);
                }
                else {
                    this.a(graphics, fps.B / 2 - this.aS.getWidth(this) / 2, 10, this.aS.getWidth(this), this.aS.getHeight(this));
                    graphics.setColor(Color.lightGray);
                    a(graphics, this.cV, "You must score " + this.cp.g + " frags to win a round.", (int)(fps.D * 0.89f), 0, Color.lightGray);
                    graphics2 = graphics;
                    font = this.cV;
                    s = "You must win " + this.cp.f + " rounds to advance to the next level.";
                    n5 = (int)(fps.D * 0.92);
                }
            }
            else {
                fps fps;
                Graphics graphics3;
                int n6;
                int n7;
                double n8;
                double n9;
                if (this.bO) {
                    fps = this;
                    graphics3 = graphics;
                    n6 = this.cp.m;
                    n7 = n4;
                    n8 = fps.D;
                    n9 = 0.29;
                }
                else {
                    fps = this;
                    graphics3 = graphics;
                    n6 = this.cp.m;
                    n7 = n4;
                    n8 = fps.D;
                    n9 = 0.33;
                }
                fps.a(graphics3, n6, n7, (int)(n8 * n9), n2, n3);
                graphics.setColor(Color.lightGray);
                Graphics graphics4;
                Font font2;
                String s2;
                float n10;
                float n11;
                if (this.bO) {
                    a(graphics, this.cW, "You must score " + this.cp.g + " frags to win a round.", (int)(fps.D * 0.32f), 0, Color.lightGray);
                    graphics4 = graphics;
                    font2 = this.cW;
                    s2 = "You must win " + this.cp.f + " rounds to advance to the next level.";
                    n10 = fps.D;
                    n11 = 0.32f;
                }
                else {
                    a(graphics, this.cW, "You must score " + this.cp.g + " frags to win a round.", (int)(fps.D * 0.85f), 0, Color.lightGray);
                    graphics4 = graphics;
                    font2 = this.cW;
                    s2 = "You must win " + this.cp.f + " rounds to advance to the next level.";
                    n10 = fps.D;
                    n11 = 0.85f;
                }
                a(graphics4, font2, s2, (int)(n10 * n11) + 20, 0, Color.lightGray);
                if (!this.bO) {
                    break Label_0690;
                }
                graphics2 = graphics;
                font = this.cV;
                s = this.bb[this.aZ];
                n5 = (int)(fps.D * 0.9f);
            }
            a(graphics2, font, s, n5, 0, Color.lightGray);
        }
        if (!this.bi || this.bO) {
            a(graphics, this.cV, "Tournament Progress", 25, 0, Color.lightGray);
            this.e(graphics, 40, 10, 0);
        }
        graphics.setColor(this.cl);
        graphics.setFont(this.cU);
        graphics.setColor(Color.red);
        if (this.aX && this.aY) {
            Graphics graphics5;
            Font font3;
            String s3;
            int n12;
            if (this.bO) {
                this.a(graphics, (int)(fps.B / 2 - fps.B * 0.5f / 2.0f), fps.D - 25, (int)(fps.B * 0.5f), 20, Color.green, Color.lightGray);
                graphics.setColor(Color.green.darker());
                graphics5 = graphics;
                font3 = this.cW;
                s3 = "Finished Loading. Ready To Play!";
                n12 = fps.D - 10;
            }
            else {
                this.a(graphics, (int)(fps.B / 2 - fps.B * 0.5f / 2.0f), fps.D - 20, (int)(fps.B * 0.5f), 20, Color.green, Color.lightGray);
                graphics.setColor(Color.green.darker());
                graphics5 = graphics;
                font3 = this.cW;
                s3 = "Done Loading. Ready To Play!";
                n12 = fps.D - 10 + 5;
            }
            a(graphics5, font3, s3, n12, 0, Color.green.darker());
            if (this.cm) {
                this.k(graphics);
            }
            if (this.ca) {
                this.cp.a = 2;
                this.cm = false;
            }
            return;
        }
        if (this.bO) {
            this.a(graphics, (int)(fps.B / 2 - fps.B * 0.5f / 2.0f), fps.D - 25, (int)(fps.B * 0.5f), 20, Color.red, Color.lightGray);
            graphics.setColor(Color.red);
            a(graphics, this.cW, "Loading level, please wait ...", (int)(fps.D - 10 + Math.random() * 2.0), 0, Color.red);
            return;
        }
        this.a(graphics, (int)(fps.B / 2 - fps.B * 0.5f / 2.0f), fps.D - 20, (int)(fps.B * 0.5f), 20, Color.red, Color.lightGray);
        graphics.setColor(Color.red);
        a(graphics, this.cW, "Loading level, please wait ...", (int)(fps.D - 10 + Math.random() * 2.0) + 5, 0, Color.red);
    }
    
    public final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final Color color2) {
        final int n5 = this.d * n3 / 22;
        graphics.setColor(color);
        graphics.drawRect(n, n2, n3, n4);
        graphics.setColor(color2);
        graphics.fillRect(n + 1, n2 + 1, n5 - 1, n4 - 1);
    }
    
    public final void g(final Graphics graphics) {
        this.f(graphics);
        if (this.cp.d > 0) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.cp.d, fps.D);
            final l cp = this.cp;
            cp.d -= this.cb;
            if (this.cp.d <= 0) {
                this.cp.d = 0;
                final l cp2 = this.cp;
                ++cp2.h;
            }
            if (this.cp.d == 0) {
                this.dN = 1;
            }
        }
        else if (this.cp.d < 0) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.cp.d * -1, fps.D);
            graphics.setColor(Color.red);
            graphics.drawLine(this.cp.d * -1, 0, this.cp.d * -1, fps.D);
            final l cp3 = this.cp;
            cp3.d -= this.cb;
            if (this.cp.d < -fps.B || this.ca) {
                this.i();
                this.cp.d = 0;
                this.cm = false;
                this.cp.a = 2;
                this.ag(11);
                this.ag(4);
            }
        }
    }
    
    public final void G(final int n) {
        int x = 0;
        for (int i = 0; i < fps.F; ++i) {
            this.aC[i].c.x = x;
            if (n == 0) {
                ++x;
            }
        }
    }
    
    public final void H(final int aw) throws OutOfMemoryError {
        this.cp.e = 1;
        this.bA = -1;
        this.U();
        String s = " ";
        if (aw == 1) {
            s = "1";
        }
        if (aw == 2) {
            s = "2";
        }
        if (aw == 3) {
            s = "3";
        }
        if (aw == 4) {
            s = "e";
        }
        if (aw == 5) {
            s = "m";
        }
        if (aw == 6) {
            s = "6";
        }
        if (aw == 7) {
            s = "7";
        }
        if (aw == 8) {
            s = "8";
        }
        if (aw == 9) {
            s = "9";
        }
        if (aw == 0) {
            s = "10";
        }
        if (aw == 11) {
            s = "space1";
        }
        if (aw == 12) {
            s = "spacewalk";
        }
        if (aw == 13) {
            s = "space3";
        }
        if (aw == 14) {
            s = "brand_new";
        }
        if (aw == 15) {
            s = "p";
        }
        if (aw == 16) {
            s = "g";
        }
        if (aw == 17) {
            s = "quad";
        }
        if (aw == 18) {
            s = "t";
        }
        if (aw == 19) {
            s = "c";
        }
        new StringBuffer("loadresources-optimised/l/").append(s).append("/level-desc.txt").toString();
        new StringBuffer(String.valueOf(this.ad)).append("/level-textures.txt").toString();
        new StringBuffer("loadresources-optimised/l/").append(s).append("/level-objects.txt").toString();
        this.cp.u = true;
        this.cp.a = 1;
        this.Z();
        fps fps;
        int n;
        if (this.aX) {
            fps = this;
            n = 18;
        }
        else {
            fps = this;
            n = 0;
        }
        fps.I(n);
        Label_0452: {
            if (this.aW != aw) {
                (this.c = new Thread(this)).start();
                try {
                    this.a(c(String.valueOf(this.bZ) + "nr-m+" + "n"));
                    this.b(c(String.valueOf(this.bZ) + "nr+l=u*" + s + "+001"));
                    break Label_0452;
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    System.out.println("  ");
                    this.bc = 1;
                    return;
                }
            }
            this.I(22);
            this.a(false);
            this.ah();
        }
        this.aW = aw;
        this.a(this.dD, 50);
        this.b();
        this.cm = true;
    }
    
    public static String c(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            StringBuffer sb2;
            char char1;
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '=' || s.charAt(i) == '&') {
                sb2 = sb;
                char1 = '/';
            }
            else {
                sb2 = sb;
                char1 = s.charAt(i);
            }
            sb2.append(char1);
        }
        return sb.toString();
    }
    
    public final void I(final int d) {
        this.d = d;
    }
    
    public final void U() {
        for (int i = 0; i < 6; ++i) {
            this.bE[i] = i;
            this.bF[i] = i;
        }
    }
    
    public final boolean V() {
        boolean b = false;
        for (int i = 0; i < 5; ++i) {
            if (this.bG[this.bE[i]] < this.bG[this.bE[i + 1]]) {
                final int n = this.bE[i];
                this.bE[i] = this.bE[i + 1];
                this.bE[i + 1] = n;
                b = true;
            }
        }
        return b;
    }
    
    public final boolean W() {
        boolean b = false;
        for (int i = 0; i < 5; ++i) {
            if (this.bH[this.bF[i]] < this.bH[this.bF[i + 1]]) {
                final int n = this.bF[i];
                this.bF[i] = this.bF[i + 1];
                this.bF[i + 1] = n;
                b = true;
            }
        }
        return b;
    }
    
    public final void X() {
        while (this.V()) {}
    }
    
    public final void Y() {
        while (this.W()) {}
    }
    
    public final void Z() {
        this.aa();
        this.ab();
        this.ac();
    }
    
    public final void aa() {
        for (int i = 0; i < 5; ++i) {
            this.aC[i].c.y = 0;
        }
        this.aB.y = 0;
        for (int j = 0; j < 6; ++j) {
            this.bG[j] = 0;
        }
        fps.U = 5;
    }
    
    public final void ab() {
        for (int i = 0; i < 6; ++i) {
            this.bH[i] = 0;
        }
    }
    
    public final void ac() {
        for (int i = 0; i < 5; ++i) {
            this.aC[i].c.y = 0;
        }
        this.aB.y = 0;
    }
    
    public final boolean ad() {
        boolean b = false;
        for (int i = 0; i < fps.F; ++i) {
            if (this.bH[this.bF[i]] >= this.cp.f && this.bF[i] < 5) {
                b = true;
            }
        }
        return b;
    }
    
    public final void ae() {
        this.cp.d = fps.B;
        this.cp.a = 10;
        fps.bq = 500;
    }
    
    public final void af() {
        this.D();
        this.c();
    }
    
    public final void ag() {
        this.bA = -1;
        if (this.ad()) {
            this.aA();
        }
        else if (this.bH[5] >= this.cp.f && this.aW != 4) {
            this.aa();
            this.ab();
            this.C();
            this.cp.m = this.cp.i[this.cp.h + 1];
            if (this.aX) {
                this.I(18);
            }
            this.cp.e = 1;
            this.aY = false;
            this.cp.a = 1;
            this.cp.d = fps.B;
        }
        else {
            final l cp = this.cp;
            ++cp.e;
            this.aa();
            this.cp.a = 2;
        }
        this.Q();
        this.ah();
    }
    
    public final void ah() {
        Label_0298: {
            fps fps = null;
            int cu = 0;
            Label_0295: {
                if (this.cp.h == 1 && this.bH[5] >= this.cp.f - 1) {
                    if (this.cu <= 4) {
                        break Label_0298;
                    }
                    fps = this;
                    cu = 4;
                }
                else if (this.cp.h == 2 && this.bH[5] >= this.cp.f - 1) {
                    if (this.cu <= 3) {
                        break Label_0298;
                    }
                    fps = this;
                    cu = 3;
                }
                else {
                    if (this.cp.h == 3 && this.bH[5] >= this.cp.f - 1) {
                        if (this.cu <= 2) {
                            break Label_0298;
                        }
                    }
                    else if (this.cp.h == 4 && this.bH[5] >= this.cp.f - 1) {
                        if (this.cu <= 2) {
                            break Label_0298;
                        }
                    }
                    else if (this.cp.h == 5 && this.bH[5] >= this.cp.f) {
                        if (this.cu > 1) {
                            fps = this;
                            cu = 1;
                            break Label_0295;
                        }
                        break Label_0298;
                    }
                    else {
                        if (this.cp.h == 2 && this.cu > 4) {
                            fps = this;
                            cu = 4;
                            break Label_0295;
                        }
                        if (this.cp.h == 3 && this.cu > 3) {
                            fps = this;
                            cu = 3;
                            break Label_0295;
                        }
                        if (this.cp.h != 4 || this.cu <= 2) {
                            if (this.cp.h != 5 || this.cu <= 2) {
                                break Label_0298;
                            }
                        }
                    }
                    fps = this;
                    cu = 2;
                }
            }
            fps.cu = cu;
        }
        this.cv = this.cu * 0.06f;
    }
    
    public final void ai() {
        this.ac();
        this.H(this.cp.m);
    }
    
    public final void a(final Graphics graphics, final float n, final int n2, final String s, final int n3) {
        if (this.co > fps.D * n - n2 - 7.0f && this.co < fps.D * n + n2 - 7.0f && this.cn >= fps.J && this.cn <= fps.K) {
            graphics.setColor(Color.black);
            a(graphics, this.cY, s, (int)(fps.D * n), -1, 1);
            graphics.setColor(this.bw);
            a(graphics, this.cY, s, (int)(fps.D * n), n3, 0);
            return;
        }
        graphics.setColor(Color.black);
        a(graphics, this.cX, s, (int)(fps.D * n), -1, 1);
        graphics.setColor(this.bv);
        a(graphics, this.cX, s, (int)(fps.D * n), n3, 0);
    }
    
    public final void aj() {
        this.bT += this.bU;
        if (this.bT > 24) {
            this.bT = 24;
            this.bU = -1;
            return;
        }
        if (this.bT < 0) {
            this.bT = 0;
            this.bU = 1;
        }
    }
    
    public final void h(final Graphics graphics) {
        this.aj();
        graphics.setColor(this.bW[this.bT]);
        if (this.co > fps.D - 20 && this.cn < fps.B / 5) {
            graphics.setColor(this.bw);
        }
        Graphics graphics2;
        String s;
        int n;
        int n2;
        if (this.bO) {
            graphics.setFont(this.cX);
            if (this.cp.a == 2) {
                graphics2 = graphics;
                s = "Back To Game";
                n = 30;
                n2 = fps.D - 10 + 3;
            }
            else {
                graphics2 = graphics;
                s = "Go Back";
                n = 30;
                n2 = fps.D - 10 + 3;
            }
        }
        else {
            graphics.setFont(this.cV);
            if (this.cp.a == 2) {
                graphics2 = graphics;
                s = "Back To Game";
            }
            else {
                graphics2 = graphics;
                s = "Go Back";
            }
            n = 30;
            n2 = fps.D - 10;
        }
        graphics2.drawString(s, n, n2);
        a(graphics, Color.green, this.bV[this.bT], 25, fps.D - 25, 5, fps.D - 5, 0, 0);
    }
    
    public final void b(final Graphics graphics, final int n) {
        graphics.setColor(this.bx);
        a(graphics, this.cX, "Please Select Difficulty", (int)(this.bQ * 0.75f), n, this.bx);
        this.a(graphics, 0.4f, 10, "Easy", n);
        this.a(graphics, 0.5f, 10, "Medium", n);
        this.a(graphics, 0.6f, 10, "Hard", n);
        this.a(graphics, 0.7f, 10, "Nightmare", n);
        this.h(graphics);
    }
    
    public final void i(final Graphics graphics) {
        this.e(graphics);
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, fps.B, fps.D);
        this.l(graphics);
        this.b(graphics, 0);
        if (this.cp.d > 0) {
            final l cp = this.cp;
            cp.d += this.cb * 3;
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.cp.d, fps.D);
            graphics.setColor(Color.red);
            graphics.drawLine(this.cp.d, 0, this.cp.d, fps.D);
            if (this.cp.d > fps.B || this.ca) {
                this.cp.u = true;
                this.cp.a = 1;
                this.cp.d = fps.B;
            }
        }
    }
    
    public final void ak() {
        this.cp.a = 4;
    }
    
    public final void j(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, fps.B, fps.D);
        if (this.cp.d > 0) {
            final l cp = this.cp;
            cp.d += this.cb * 3;
            this.l(graphics);
            this.c(graphics, this.cp.d);
            if (this.cp.d > fps.B || this.ca) {
                this.ak();
            }
        }
        else {
            this.l(graphics);
            this.c(graphics, 0);
        }
        if (this.ca) {
            this.aB();
        }
    }
    
    public final void al() {
        final int n = this.bO ? 2 : 1;
        this.bP = fps.B / 2 - 62 * n;
        this.bQ = fps.D / 2 - 56 * n;
    }
    
    public static void a(final Graphics graphics, final Color color, final Color color2, int n, int n2, int n3, int n4, final int n5, final int n6) {
        graphics.getColor();
        int n7;
        if (n < n3) {
            n += n5;
            n7 = n3 - n5;
        }
        else {
            n -= n5;
            n7 = n3 + n5;
        }
        n3 = n7;
        n2 += n6;
        n4 -= n6;
        final int n8 = (n3 - n) / 2 + n;
        final int n9 = n3;
        final int n10 = n2;
        final int n11 = n4;
        final int n12 = (n4 - n2) / 2 + n2;
        final int[] array = { n8, n8, n9 };
        final int[] array2 = { n10, n11, n12 };
        graphics.setColor(color2);
        graphics.fillPolygon(array, array2, 3);
        graphics.setColor(color);
        graphics.drawPolygon(array, array2, 3);
        if (n < n3) {
            graphics.setColor(color2);
            graphics.fillRect(n, n2 + (n4 - n2) / 4, (n3 - n) / 2, (n4 - n2) / 2);
            graphics.setColor(color);
            graphics.drawRect(n, n2 + (n4 - n2) / 4, (n3 - n) / 2, (n4 - n2) / 2);
            return;
        }
        graphics.setColor(color2);
        graphics.fillRect(n + (n3 - n) / 2, n2 + (n4 - n2) / 4, Math.abs((n3 - n) / 2), Math.abs((n4 - n2) / 2));
        graphics.setColor(color);
        graphics.drawRect(n + (n3 - n) / 2, n2 + (n4 - n2) / 4, Math.abs((n3 - n) / 2), Math.abs((n4 - n2) / 2));
    }
    
    public final void a(final Graphics graphics, int n, int n2, int n3, int n4, final int n5, final int n6) {
        final Color color = graphics.getColor();
        int n7;
        if (n < n3) {
            n += n5;
            n7 = n3 - n5;
        }
        else {
            n -= n5;
            n7 = n3 + n5;
        }
        n3 = n7;
        n2 += n6;
        n4 -= n6;
        final int n8 = (n3 - n) / 2 + n;
        final int n9 = n3;
        final int n10 = n2;
        final int n11 = n4;
        final int n12 = (n4 - n2) / 2 + n2;
        final int[] array = { n8, n8, n9 };
        final int[] array2 = { n10, n11, n12 };
        graphics.fillPolygon(array, array2, 3);
        graphics.setColor(this.bw.darker());
        graphics.drawPolygon(array, array2, 3);
        graphics.setColor(color);
        if (n < n3) {
            graphics.fillRect(n, n2 + (n4 - n2) / 4, (n3 - n) / 2, (n4 - n2) / 2);
            graphics.setColor(this.bw.darker());
            graphics.drawRect(n, n2 + (n4 - n2) / 4, (n3 - n) / 2, (n4 - n2) / 2);
            graphics.setColor(color);
            return;
        }
        graphics.fillRect(n + (n3 - n) / 2, n2 + (n4 - n2) / 4, Math.abs((n3 - n) / 2), Math.abs((n4 - n2) / 2));
        graphics.setColor(this.bw.darker());
        graphics.drawRect(n + (n3 - n) / 2, n2 + (n4 - n2) / 4, Math.abs((n3 - n) / 2), Math.abs((n4 - n2) / 2));
        graphics.setColor(color);
    }
    
    public final void c(final Graphics graphics, final int n) {
        graphics.setColor(this.bx);
        a(graphics, this.cX, "Please Select A Starting Level", (int)(this.bQ * 0.75f), n, this.bx);
        int bs;
        if (this.bO) {
            fps.bR = 1.0f;
            bs = 2;
        }
        else {
            fps.bR = 0.5f;
            bs = 1;
        }
        fps.bS = bs;
        Label_0212: {
            Graphics graphics2;
            Image image;
            if (this.cp.b == 1) {
                graphics2 = graphics;
                image = this.aL;
            }
            else if (this.cp.b == 0) {
                graphics2 = graphics;
                image = this.aM;
            }
            else if (this.cp.b == 2) {
                graphics2 = graphics;
                image = this.aN;
            }
            else if (this.cp.b == 3) {
                graphics2 = graphics;
                image = this.aO;
            }
            else if (this.cp.b == 4) {
                graphics2 = graphics;
                image = this.aP;
            }
            else {
                if (this.cp.b != 5) {
                    break Label_0212;
                }
                graphics2 = graphics;
                image = this.aQ;
            }
            graphics2.drawImage(image, this.bP + n, this.bQ, (int)(this.aL.getWidth(this) * fps.bR), (int)(this.aL.getHeight(this) * fps.bR), null);
        }
        if (this.cn >= this.bP && this.cn <= this.bP + this.aL.getWidth(this) * fps.bR && this.co >= this.bQ && this.co <= this.bQ + this.aL.getHeight(this) * fps.bR) {
            Graphics graphics3;
            Color[] array;
            if (this.cp.v[this.cp.b]) {
                graphics3 = graphics;
                array = this.bX;
            }
            else {
                a(graphics, this.cX, "Click To Play On This Level", this.bQ + 112 * fps.bS + 10 + 30, n, this.bw);
                graphics3 = graphics;
                array = this.bV;
            }
            graphics3.setColor(array[this.bT]);
            graphics.drawRect(this.bP + n, this.bQ, (int)(this.aL.getWidth(this) * fps.bR), (int)(this.aL.getHeight(this) * fps.bR));
        }
        Label_0554: {
            fps fps2 = null;
            Graphics graphics5 = null;
            Color bw2 = null;
            int n3 = 0;
            boolean b2 = false;
            Label_0551: {
                fps fps = null;
                Graphics graphics4 = null;
                Color bw = null;
                int n2 = 0;
                boolean b = false;
                Label_0533: {
                    if (this.co > this.bQ && this.co < this.bQ + 112 * fps.bS && n == 0) {
                        if (this.cn > this.bP + 125 * fps.bS) {
                            fps = this;
                            graphics4 = graphics;
                            bw = this.bw;
                            n2 = fps.bS;
                            b = true;
                            break Label_0533;
                        }
                        if (this.cn < this.bP) {
                            this.a(graphics, this.bV[this.bT], fps.bS, false);
                            fps2 = this;
                            graphics5 = graphics;
                            bw2 = this.bw;
                            n3 = fps.bS;
                            b2 = true;
                            break Label_0551;
                        }
                        fps = this;
                    }
                    else {
                        if (n != 0) {
                            break Label_0554;
                        }
                        fps = this;
                    }
                    graphics4 = graphics;
                    bw = this.bV[this.bT];
                    n2 = fps.bS;
                    b = false;
                }
                fps.a(graphics4, bw, n2, b);
                fps2 = this;
                graphics5 = graphics;
                bw2 = this.bV[this.bT];
                n3 = fps.bS;
                b2 = false;
            }
            fps2.b(graphics5, bw2, n3, b2);
        }
        graphics.setColor(Color.white);
        Label_0711: {
            Graphics graphics6;
            Font font;
            String s;
            if (this.cp.b == 1) {
                graphics6 = graphics;
                font = this.cX;
                s = "Greek Sanctuary";
            }
            else if (this.cp.b == 0) {
                graphics6 = graphics;
                font = this.cX;
                s = "Palace Entrance";
            }
            else if (this.cp.b == 2) {
                graphics6 = graphics;
                font = this.cX;
                s = "Victorian Town";
            }
            else if (this.cp.b == 3) {
                graphics6 = graphics;
                font = this.cX;
                s = "Highland Castle";
            }
            else if (this.cp.b == 4) {
                graphics6 = graphics;
                font = this.cX;
                s = "Medieval Monastery";
            }
            else {
                if (this.cp.b != 5) {
                    break Label_0711;
                }
                graphics6 = graphics;
                font = this.cX;
                s = "Egyptian Tomb";
            }
            a(graphics6, font, s, this.bQ + 112 * fps.bS + 10, n, this.bx);
        }
        if (this.cp.v[this.cp.b]) {
            a(graphics, this.cX, "Level Locked. Please Select Another", this.bQ + 112 * fps.bS + 10 + 30, n, Color.red.brighter());
        }
        else {
            this.k(graphics);
        }
        this.h(graphics);
    }
    
    public final void k(final Graphics graphics) {
        if (this.cm || this.cp.a == 3 || (this.cp.a == 10 && !this.bi && this.bl)) {
            this.aj();
        }
        graphics.setColor(this.bV[this.bT]);
        final Color color = (this.co > fps.D - 20 && this.cn > fps.B - fps.B / 5) ? this.bw : this.bW[this.bT];
        Graphics graphics2;
        Font font;
        String s;
        int n;
        int n2;
        if (this.bO) {
            graphics.setColor(color);
            graphics2 = graphics;
            font = this.cX;
            s = "Continue";
            n = fps.B - 30;
            n2 = fps.D - 10 + 2;
        }
        else {
            graphics.setColor(color);
            graphics2 = graphics;
            font = this.cV;
            s = "Continue";
            n = fps.B - 30;
            n2 = fps.D - 10;
        }
        a(graphics2, font, s, n, n2);
        a(graphics, Color.green, this.bV[this.bT], fps.B - 30 + 5, fps.D - 25, fps.B - 5, fps.D - 5, 0, 0);
    }
    
    public final void a(final Graphics graphics, final Color color, final int n, final boolean b) {
        graphics.setColor(color);
        this.a(graphics, this.bP + 125 * n, this.bQ, this.bP + 125 * n + (fps.B - (this.bP + 125 * n)), this.bQ + 112 * n, (int)(fps.B * 0.1f), (int)(fps.B * 0.1f));
        if (b) {
            graphics.setFont(this.cY);
            b(graphics, "Next Level", (this.bP + 125 * n + (this.bP + 125 * n) + (fps.B - (this.bP + 125 * n))) / 2, this.bQ + 112 * n - (int)(fps.B * 0.05f), this.bw);
            return;
        }
        graphics.setFont(this.cX);
        b(graphics, "Next Level", (this.bP + 125 * n + (this.bP + 125 * n) + (fps.B - (this.bP + 125 * n))) / 2, this.bQ + 112 * n - (int)(fps.B * 0.05f), this.bv);
    }
    
    public final void b(final Graphics graphics, final Color color, final int n, final boolean b) {
        graphics.setColor(color);
        this.a(graphics, this.bP, this.bQ, this.bP - (fps.B - (this.bP + 125 * n)), this.bQ + 112 * n, (int)(fps.B * 0.1f), (int)(fps.B * 0.1f));
        if (b) {
            graphics.setFont(this.cY);
            b(graphics, "Previous Level", (this.bP + this.bP - (fps.B - (this.bP + 125 * n))) / 2, this.bQ + 112 * n - (int)(fps.B * 0.05f), this.bw);
            return;
        }
        graphics.setFont(this.cX);
        b(graphics, "Previous Level", (this.bP + this.bP - (fps.B - (this.bP + 125 * n))) / 2, this.bQ + 112 * n - (int)(fps.B * 0.05f), this.bv);
    }
    
    public final void l(final Graphics graphics) {
        graphics.drawImage(this.aR, 0, 0, fps.B, fps.D, this);
        this.s(graphics);
    }
    
    public final void am() {
        if (this.bD > 0) {
            this.bD = 80;
            return;
        }
        if (this.bC < 50) {
            this.bD = 80;
            return;
        }
        if (this.bC <= 50) {
            this.bD = -1;
            this.bC = 50 + this.bK + 10;
        }
    }
    
    public final void d(final Graphics graphics, final int n) {
        graphics.setColor(Color.white);
        a(graphics, this.de, "Scores for this Round", (int)(fps.D * 0.3f), n, Color.white);
        this.X();
        graphics.setFont(this.dd);
        final int n2 = fps.B / 2 + graphics.getFontMetrics().stringWidth(String.valueOf(this.aC[this.bd].c.h) + " : 0 round(s)") / 2;
        this.b(graphics, n2, (int)(fps.D * 0.35), n);
        graphics.setColor(Color.white);
        a(graphics, this.de, "Overall Scores for this Level", (int)(fps.D * 0.6f), n, Color.white);
        this.Y();
        this.a(graphics, n2, (int)(fps.D * 0.65), n);
    }
    
    public final String an() {
        String s = "unknown";
        String s2;
        if (this.cw == 5) {
            s2 = "Easy";
        }
        else if (this.cw == 4) {
            s2 = "Easy";
        }
        else if (this.cw == 3) {
            s2 = "Medium";
        }
        else if (this.cw == 2) {
            s2 = "Hard";
        }
        else {
            if (this.cw != 1) {
                return s;
            }
            s2 = "Nightmare";
        }
        s = s2;
        return s;
    }
    
    public final void m(final Graphics graphics) {
        this.a(graphics, (int)(fps.D * 0.14), (int)(fps.D * 0.19));
        this.h(graphics);
    }
    
    public final void a(final Graphics graphics, final int n, final int n2) {
        a(graphics, this.cY, "Current Scores", n, 0, Color.white);
        a(graphics, this.cT, "( Difficulty : " + this.an() + " )", n2, -this.cp.c, Color.lightGray);
        this.d(graphics, 0);
        a(graphics, this.cT, "You must score " + this.cp.g + " frags to win a round.", (int)(fps.D * 0.89f), 0, Color.lightGray);
        a(graphics, this.cT, "You must win " + (this.cp.f - this.bH[5]) + " more round(s) to proceed to the next level.", (int)(fps.D * 0.93), -this.cp.c, Color.lightGray);
    }
    
    public final void n(final Graphics graphics) {
        this.aj();
        if (!this.bg) {
            graphics.setColor(this.bX[this.bT]);
            graphics.fillRect(0, 0, 5, fps.D);
            graphics.fillRect(fps.B - 5, 0, 5, fps.D);
            graphics.fillRect(0, 0, fps.B, 5);
            graphics.fillRect(0, fps.D - 5, fps.B, 5);
            graphics.setFont(this.cZ);
            Graphics graphics2;
            Font font;
            String s;
            if (this.bO) {
                graphics2 = graphics;
                font = this.cZ;
                s = "GAME PAUSED";
            }
            else {
                graphics2 = graphics;
                font = this.cW;
                s = "G A M E  P A U S E D";
            }
            a(graphics2, font, s, (int)(fps.D * 0.06f), 0, this.bX[this.bT]);
            a(graphics, this.cW, "Place mouse pointer on game window to continue playing", (int)(fps.D * 0.09f), 0, this.bX[this.bT]);
            this.a(graphics, (int)(fps.D * 0.16f), (int)(fps.D * 0.21));
        }
    }
    
    public final void o(final Graphics graphics) {
        graphics.setColor(Color.red.darker());
        graphics.fillRect(0, 0, fps.B, fps.D);
        Graphics graphics2;
        Font font;
        String s;
        if (this.bc == 2) {
            graphics2 = graphics;
            font = this.cX;
            s = "Null Pointer Error";
        }
        else if (this.bc == 3) {
            graphics2 = graphics;
            font = this.cX;
            s = "Invalid Screen Size Error";
        }
        else {
            graphics2 = graphics;
            font = this.cX;
            s = "Out Of Memory Error";
        }
        a(graphics2, font, s, (int)(fps.D * 0.1), 0, Color.white);
        a(graphics, this.cX, "Hold down [Control] and click the refresh button", (int)(fps.D * 0.25), 0, Color.white);
        a(graphics, this.cX, "on your browser. This will clear your cache and", (int)(fps.D * 0.3), 0, Color.white);
        a(graphics, this.cX, "re-load the game.", (int)(fps.D * 0.35), 0, Color.white);
        a(graphics, this.cX, "Apologies for the inconvenience.", (int)(fps.D * 0.5), 0, Color.white);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.bc > -1) {
            this.o(graphics);
            return;
        }
        if (this.cp.a == -1 || this.cp.a < 0 || this.cp.a == 1) {
            this.g(this.aH);
            graphics.drawImage(this.aG, 0, 0, this);
            return;
        }
        if (this.cp.a == 0) {
            this.j(this.aH);
            graphics.drawImage(this.aG, 0, 0, this);
            return;
        }
        if (this.cp.a != 5) {
            if (this.cp.a == 2) {
                if (!this.cc && !this.cp.n) {
                    this.n(graphics);
                    return;
                }
                if (this.cp.n) {
                    if (this.cp.q) {
                        this.x(graphics);
                        return;
                    }
                    if (this.cp.r) {
                        this.m(graphics);
                        return;
                    }
                    this.y(graphics);
                }
                else {
                    this.c(graphics);
                    if (this.bD > 0) {
                        this.d(graphics, fps.E, fps.D - 5, 0);
                        this.a(graphics, this.bK);
                        --this.bD;
                        if (this.bD <= 0) {
                            this.bC = 0;
                        }
                    }
                    else if (this.bC < 50) {
                        this.d(graphics, fps.E, fps.D - 5, this.bC);
                        fps fps;
                        Graphics graphics2;
                        int n;
                        if (this.bC < this.bK + 10) {
                            fps = this;
                            graphics2 = graphics;
                            n = this.bK - this.bC;
                        }
                        else {
                            fps = this;
                            graphics2 = graphics;
                            n = -10;
                        }
                        fps.a(graphics2, n);
                        ++this.bC;
                    }
                    else if (this.bC > 50) {
                        this.d(graphics, fps.E, fps.D - 5, this.bC - 50);
                        this.a(graphics, this.bK - (this.bC - 50));
                        --this.bC;
                        if (this.bC == 50) {
                            this.bD = 80;
                        }
                    }
                    else {
                        this.a(graphics, -10);
                    }
                    if (!fps.P) {
                        this.q(graphics);
                    }
                    if (this.bg || this.bh) {
                        this.r(graphics);
                    }
                    if (this.cA > 0) {
                        graphics.setColor(Color.black);
                        graphics.fillRect(0, 0, fps.B, this.cA);
                        graphics.fillRect(0, fps.D - this.cA, fps.B, fps.D);
                        graphics.setColor(Color.darkGray);
                        graphics.drawLine(0, this.cA, fps.B, this.cA);
                        graphics.drawLine(0, fps.D - this.cA, fps.B, fps.D - this.cA);
                        a(graphics, this.cW, "Frag Camera", this.cA - fps.R / 2, 0, Color.darkGray);
                        a(graphics, this.cW, "You Fragged " + this.aC[this.af.n].c.h + " with the " + this.dL[this.af.H].a, fps.D - this.cA + fps.R / 2, 0, Color.darkGray);
                        return;
                    }
                    if (this.cA < 0) {
                        graphics.setColor(Color.black);
                        graphics.fillRect(0, 0, fps.B, -this.cA);
                        graphics.fillRect(0, fps.D + this.cA, fps.B, fps.D);
                        graphics.setColor(Color.darkGray);
                        graphics.drawLine(0, -this.cA, fps.B, -this.cA);
                        graphics.drawLine(0, fps.D + this.cA, fps.B, fps.D + this.cA);
                        a(graphics, this.cW, "Frag Camera", -this.cA - fps.R / 2, 0, Color.darkGray);
                        a(graphics, this.cW, "You Fragged " + this.aC[this.af.n].c.h + " with the " + this.dL[this.af.H].a, fps.D - this.cA + fps.R / 2, 0, Color.darkGray);
                    }
                }
            }
            else {
                if (this.cp.a == 3) {
                    this.d(graphics);
                    return;
                }
                if (this.cp.a == 4) {
                    this.i(graphics);
                    return;
                }
                if (this.cp.a == 10) {
                    this.e(graphics);
                    graphics.setColor(Color.black);
                    graphics.fillRect(0, 0, fps.B, fps.D);
                    this.p(graphics);
                }
            }
            return;
        }
        if (!this.cp.n) {
            this.t(this.aH);
            graphics.drawImage(this.aG, 0, 0, this);
            return;
        }
        if (this.cp.o) {
            this.w(graphics);
            return;
        }
        if (this.cp.p) {
            this.u(graphics);
            return;
        }
        if (this.cp.t) {
            this.v(graphics);
            return;
        }
        this.y(graphics);
    }
    
    public final void e(final Graphics graphics, final int n) {
        if (this.bO) {
            a(graphics, this.cZ, "G A M E   O V E R", (int)(fps.D * 0.1), n, Color.red);
        }
        graphics.setColor(Color.white);
        a(graphics, this.cP, "Game Will Continue In : " + fps.bq / 50, fps.B - 15 + n, 15);
        this.a(graphics, fps.B / 2 - this.aS.getWidth(this) / 2 + n, fps.D / 2 - this.aS.getHeight(this) / 2, this.aS.getWidth(this), this.aS.getHeight(this));
    }
    
    public final void f(final Graphics graphics, final int n) {
        this.aT.getWidth(this);
        this.aT.getHeight(this);
        graphics.drawImage(this.aT, n, 0, fps.B, fps.D, this);
        a(graphics, this.cZ, "G A M E   O V E R", (int)(fps.D * 0.2), n, Color.red);
        if (n == 0) {
            this.k(graphics);
        }
    }
    
    public final void p(final Graphics graphics) {
        if (this.cp.d < 0) {
            graphics.drawImage(this.aR, 0, 0, fps.B, fps.D, this);
        }
        else if (this.bi) {
            this.e(graphics, 0);
        }
        else {
            this.f(graphics, 0);
        }
        if (this.cp.d > 0) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.cp.d, fps.D);
            graphics.setColor(Color.red);
            graphics.drawLine(this.cp.d + 1, 0, this.cp.d + 1, fps.D);
            final l cp = this.cp;
            cp.d -= this.cb;
            if (this.cp.d <= 0) {
                this.cp.d = 0;
            }
        }
        else if (this.cp.d < 0) {
            graphics.setColor(Color.black);
            graphics.fillRect(-this.cp.d, 0, fps.B + this.cp.d, fps.D);
            if (this.bi) {
                this.e(graphics, -this.cp.d);
            }
            else {
                this.f(graphics, -this.cp.d);
            }
            final l cp2 = this.cp;
            cp2.d -= this.cb;
            if (this.cp.d <= -fps.B) {
                this.cp.d = 0;
                this.af();
            }
        }
        else {
            --fps.bq;
            if (fps.bq <= 0) {
                this.cp.d = -1;
            }
        }
    }
    
    public final void run() throws NullPointerException {
        while (this.c != null) {
            this.a.g();
            this.a.f();
            this.a.j();
            this.repaint();
            try {
                if (this.c == null) {
                    continue;
                }
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        while (this.b != null) {
            this.dp = 0L;
            this.dn = System.currentTimeMillis();
            if (!this.dh) {
                this.dg = System.currentTimeMillis();
                this.dh = true;
            }
            if (this.cp.a < 2 || this.cp.a == 4 || this.cp.a == 5 || this.cp.a == 10) {
                if (this.cc) {
                    this.requestFocus();
                }
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex2) {}
                this.repaint();
            }
            Label_0533: {
                if (this.cp.a != -1 && this.cp.a != 0 && this.cp.a != 1 && this.cp.a != 2 && this.cp.a != 3 && this.cp.a != 4 && this.cp.a != 5) {
                    if (this.cp.a != 10) {
                        break Label_0533;
                    }
                }
                try {
                    this.a.g();
                    if (this.cp.a == 2 && this.cc) {
                        this.N();
                        this.N();
                    }
                    this.a.f();
                    this.a.j();
                }
                catch (NullPointerException ex3) {
                    this.bc = 2;
                    return;
                }
                if (fps.Z) {
                    this.aF.a(fps.X);
                    fps.Z = false;
                }
                if (this.dN > 0) {
                    this.ai();
                    this.dN = -1;
                }
                if (this.cx > 0) {
                    try {
                        Thread.sleep(Math.max(this.dm, 10));
                    }
                    catch (Exception ex4) {}
                }
                else {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception ex5) {}
                }
                this.di = (System.currentTimeMillis() - this.dg) / 1000L;
                if (this.dj != this.di) {
                    this.dl = this.dk;
                    this.dk = 0L;
                    this.dj = this.di;
                    if (this.cx > 0) {
                        if (this.dl < this.cy) {
                            this.dm -= this.cx;
                            if (this.dm < 0) {
                                this.dm = 0;
                            }
                            if (this.cx < 0) {
                                this.cx = 1;
                            }
                        }
                        else if (this.dl > this.cy) {
                            this.dm += (int)((this.dl - this.cy) / 3L);
                        }
                    }
                }
                else {
                    ++this.dk;
                }
            }
            this.do = System.currentTimeMillis();
            this.dq = this.do - this.dn;
            if (this.dq < 40L) {
                do {
                    this.do = System.currentTimeMillis();
                    this.dq = this.do - this.dn;
                    ++this.dp;
                } while (this.dq < 40L);
            }
        }
    }
    
    public static float a(final float n) {
        return (float)(n / 57.3248);
    }
    
    public final void start() {
        this.requestFocus();
        (this.b = new Thread(this)).start();
    }
    
    public final void stop() {
        this.b();
        this.a.d();
        this.aF.a();
        this.b();
        this.b.stop();
        this.dV.stop();
    }
    
    private int[] a(final String s, final int[] array) {
        try {
            return a3dAPI.c.a(new URL(this.getDocumentBase(), s), array, this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static boolean a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return n >= Math.min(n3, n5) && n <= Math.max(n5, n3) && n2 >= Math.min(n4, n6) && n2 <= Math.max(n6, n4);
    }
    
    public final int a(final float n, final float n2, final int n3) {
        int e = -1;
        for (int i = 0; i < this.cN[n3].d; ++i) {
            if (a(n, n2, this.cN[n3].x[i].a, this.cN[n3].x[i].b, this.cN[n3].x[i].c, this.cN[n3].x[i].d)) {
                e = this.cN[n3].x[i].e;
            }
        }
        return e;
    }
    
    public final int b(final float n, final float n2, final int n3) {
        int e = -1;
        for (int i = 0; i < this.cN[n3].d; ++i) {
            if (a(n, n2, this.cN[n3].y[i].a, this.cN[n3].y[i].b, this.cN[n3].y[i].c, this.cN[n3].y[i].d)) {
                e = this.cN[n3].x[i].e;
            }
        }
        return e;
    }
    
    public final int a(final float n, final float n2) {
        for (int i = 0; i < 22; ++i) {
            if (this.cN[i].f && a(n, n2, this.cN[i].v.a, this.cN[i].v.b, this.cN[i].v.c, this.cN[i].v.d)) {
                return i;
            }
        }
        return -1;
    }
    
    public final j a(j a, final int n, final float n2, final float c, final boolean b) {
        if (n > -1) {
            j j;
            float c2;
            if (a(n2, c, this.cN[n].v.a + 2.0f, this.cN[n].v.b + 2.0f, this.cN[n].v.c - 2.0f, this.cN[n].v.d - 2.0f)) {
                final int b2;
                if ((b2 = this.b(n, n2, c)) >= 0) {
                    a = this.a(a, n, b2, n2, c);
                    return a;
                }
                a.a = n2;
                j = a;
                c2 = c;
            }
            else {
                if (this.a(n2, c, n) > -1) {
                    a.a = n2;
                    a.c = c;
                    a.l = this.a(a.a, a.c);
                    return a;
                }
                if (!b) {
                    return a;
                }
                final float n3 = n2 - a.a;
                final float n4 = c - a.c;
                if (a(a.a + n3, a.c, this.cN[n].v.a, this.cN[n].v.b, this.cN[n].v.c, this.cN[n].v.d)) {
                    final j i = a;
                    i.a += n3;
                    return a;
                }
                if (!a(a.a, a.c + n4, this.cN[n].v.a, this.cN[n].v.b, this.cN[n].v.c, this.cN[n].v.d)) {
                    return a;
                }
                c2 = (j = a).c + n4;
            }
            j.c = c2;
        }
        return a;
    }
    
    public final j a(final j j, final float a, final float c) {
        j.a = a;
        j.c = c;
        j.m = j.l;
        j.l = this.a(j.a, j.c);
        if (j.l == -1) {
            j.l = j.m;
        }
        return j;
    }
    
    public static boolean a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        boolean b = false;
        boolean b2;
        if (Math.abs(n5 - n7) < Math.abs(n6 - n8)) {
            if (((n < n5 || n3 > n5) && (n > n5 || n3 < n5)) || ((n2 < n6 || n2 > n8) && (n4 < n6 || n4 > n8))) {
                return b;
            }
            b2 = true;
        }
        else {
            if (((n2 < n6 || n4 > n6) && (n2 > n6 || n4 < n6)) || ((n < n5 || n > n7) && (n3 < n5 || n3 > n7))) {
                return b;
            }
            b2 = true;
        }
        b = b2;
        return b;
    }
    
    public final boolean a(final j j, final int n, final float n2, final float n3) {
        boolean b = false;
        for (int i = 0; i < this.cN[n].p; ++i) {
            if (a(j.a, j.c, n2, n3, this.cN[n].r[i].a, this.cN[n].r[i].b, this.cN[n].r[i].d, this.cN[n].r[i].e)) {
                b = true;
            }
        }
        return b;
    }
    
    public final boolean a(final int n, final float n2, final float n3, final float n4, final float n5) {
        boolean b = false;
        for (int i = 0; i < this.cN[n].q; ++i) {
            if (a(n2, n3, n4, n5, this.cN[n].s[i].a, this.cN[n].s[i].b, this.cN[n].s[i].d, this.cN[n].s[i].e)) {
                b = true;
            }
        }
        return b;
    }
    
    public final j b(j a, final int n, final float n2, final float n3) {
        boolean b = false;
        final float a2 = a.a;
        final float c = a.c;
        Label_0124: {
            if (this.a(a, n, n2, n3)) {
                if (!this.a(a, n, n2, a.c)) {
                    a.a = n2;
                }
                else {
                    if (!this.a(a, n, a.a, n3)) {
                        b = true;
                        a.c = n3;
                    }
                    break Label_0124;
                }
            }
            else {
                final int a3;
                if ((a3 = this.a(n, n2, n3)) < 0) {
                    a.a = n2;
                    a.c = n3;
                }
                else {
                    a = this.a(a, n, a3, n2, n3);
                }
            }
            b = true;
        }
        if (b && this.a(n, a2, c, a.a, a.c)) {
            a.l = this.a(a.a, a.c);
        }
        return a;
    }
    
    public final j b(j j, final int n, final float n2, final float n3, final boolean b) {
        return j = ((this.cN[n].p == 0) ? this.c(j, n, n2, n3, b) : this.b(j, n, n2, n3));
    }
    
    public final j c(j a, final int n, final float n2, final float n3, final boolean b) {
        if (a(n2, n3, this.cN[n].w.a, this.cN[n].w.b, this.cN[n].w.c, this.cN[n].w.d)) {
            final int a2;
            if ((a2 = this.a(n, n2, n3)) < 0) {
                a.a = n2;
                a.c = n3;
            }
            else {
                a = this.a(a, n, a2, n2, n3);
            }
        }
        else {
            int n4;
            if ((n4 = this.b(n2, n3, n)) > -1) {
                a.a = n2;
                a.c = n3;
            }
            else {
                if (!b) {
                    return a;
                }
                final float n5 = n2 - a.a;
                final float n6 = n3 - a.c;
                if (a(a.a + n5, a.c, this.cN[n].w.a, this.cN[n].w.b, this.cN[n].w.c, this.cN[n].w.d) || (n4 = this.b((float)(int)(a.a + n5), (int)a.c, n)) != -1) {
                    final j j = a;
                    j.a += n5;
                    if (n4 == -1) {
                        return a;
                    }
                }
                else {
                    if (!a(a.a, a.c + n6, this.cN[n].w.a, this.cN[n].w.b, this.cN[n].w.c, this.cN[n].w.d) && (n4 = this.b((float)(int)a.a, (int)(a.c + n6), n)) == -1) {
                        return a;
                    }
                    final j i = a;
                    i.c += n6;
                    if (n4 == -1) {
                        return a;
                    }
                }
            }
            a.l = this.a(a.a, a.c);
        }
        return a;
    }
    
    public final j a(final j j, final int n, final int n2, final float n3, final float n4) {
        final float n5 = n3 - j.a;
        final float n6 = n4 - j.c;
        if (!a(j.a + n5, j.c, this.cN[n].z[n2].a, this.cN[n].z[n2].b, this.cN[n].z[n2].c, this.cN[n].z[n2].d)) {
            j.a += n5;
        }
        else if (!a(j.a, j.c + n6, this.cN[n].z[n2].a, this.cN[n].z[n2].b, this.cN[n].z[n2].c, this.cN[n].z[n2].d)) {
            j.c += n6;
        }
        return j;
    }
    
    public final int b(final int n, final float n2, final float n3, final float n4, final float n5) {
        int n6 = -1;
        for (int i = 0; i < this.cN[n].e; ++i) {
            if (a(n2, n3, this.cN[n].z[i].a, this.cN[n].z[i].b, this.cN[n].z[i].c, this.cN[n].z[i].d) || a(n4, n5, this.cN[n].z[i].a, this.cN[n].z[i].b, this.cN[n].z[i].c, this.cN[n].z[i].d)) {
                n6 = i;
            }
        }
        return n6;
    }
    
    public final int a(final int n, final float n2, final float n3) {
        int n4 = -1;
        for (int i = 0; i < this.cN[n].e; ++i) {
            if (a(n2, n3, this.cN[n].z[i].a, this.cN[n].z[i].b, this.cN[n].z[i].c, this.cN[n].z[i].d)) {
                n4 = i;
            }
        }
        return n4;
    }
    
    public final int b(final int n, final float n2, final float n3) {
        int n4 = -1;
        for (int i = 0; i < this.cN[n].e; ++i) {
            if (a(n2, n3, this.cN[n].z[i].a - 2.0f, this.cN[n].z[i].b - 2.0f, this.cN[n].z[i].c + 2.0f, this.cN[n].z[i].d + 2.0f)) {
                n4 = i;
            }
        }
        return n4;
    }
    
    public final boolean a(final float n, final float n2, final float n3, final float n4, final float n5) {
        boolean b = false;
        if (this.aB.s < 0 && (a(n, n3, this.ae.a - 1.5f, this.ae.c - 1.5f, this.ae.a + 1.5f, this.ae.c + 1.5f) || a(n4, n5, this.ae.a - 1.5f, this.ae.c - 1.5f, this.ae.a + 1.5f, this.ae.c + 1.5f)) && n2 <= 6.0f && n2 >= 0.0f) {
            b = true;
        }
        return b;
    }
    
    public final int b(final float n, final float n2, final float n3, final float n4, final float n5) {
        int n6 = -1;
        for (int i = 0; i < fps.F; ++i) {
            if (this.aC[i].c.B < 1 && this.aC[i].c.s < 0 && (a(n, n3, this.aC[i].a.a - 1.5f, this.aC[i].a.c - 1.5f, this.aC[i].a.a + 1.5f, this.aC[i].a.c + 1.5f) || a(n4, n5, this.aC[i].a.a - 1.5f, this.aC[i].a.c - 1.5f, this.aC[i].a.a + 1.5f, this.aC[i].a.c + 1.5f)) && n2 <= 6.0f && n2 >= 0.0f) {
                n6 = i;
            }
        }
        return n6;
    }
    
    public final int a(final int n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        int n7 = -1;
        for (int i = 0; i < fps.F; ++i) {
            if (this.aC[i].c.s < 0 && i != n && this.aC[i].c.x != this.aC[n].c.x && this.aC[i].c.B < 1 && (a(n2, n4, this.aC[i].a.a - 1.5f, this.aC[i].a.c - 1.5f, this.aC[i].a.a + 1.5f, this.aC[i].a.c + 1.5f) || a(n5, n6, this.aC[i].a.a - 1.5f, this.aC[i].a.c - 1.5f, this.aC[i].a.a + 1.5f, this.aC[i].a.c + 1.5f)) && n3 <= 6.0f && n3 >= 0.0f) {
                n7 = i;
            }
        }
        return n7;
    }
    
    public final void J(final int v) {
        this.aB.v = v;
    }
    
    public final void q(final Graphics graphics) {
        int n = 2;
        if (this.bO) {
            n = 4;
        }
        if (this.aB.v > 0) {
            final e ab = this.aB;
            --ab.v;
            graphics.setColor(Color.red.darker().darker().darker());
            graphics.fillOval(0, 0, fps.B, this.aB.v * n);
            graphics.fillOval(0, 0, this.aB.v * n, fps.D);
            graphics.fillOval(fps.B - this.aB.v * n, 0, this.aB.v * n, fps.D);
        }
    }
    
    public final e a(int n, final e e, final int n2, final int n3) {
        if (!fps.P) {
            if ((n3 == -1 && this.aB.K > -1) || (n3 > -1 && this.aC[n3].c.K > -1)) {
                n *= 4;
            }
            if (n2 == -1 && this.cu > 1) {
                n -= (int)(n * (this.cu / 10.0f));
            }
            if (e.A > n) {
                e.A -= n;
            }
            else {
                e e2;
                int n5;
                int n6;
                if (e.A > 0) {
                    final int n4 = n - e.A;
                    e.A = 0;
                    e2 = e;
                    n5 = e.z;
                    n6 = n4;
                }
                else {
                    e2 = e;
                    n5 = e.z;
                    n6 = n;
                }
                e2.z = n5 - n6;
            }
        }
        return e;
    }
    
    public final void n(final int n, final int n2) {
        this.J();
        final e c = this.aC[n].c;
        ++c.y;
        final int[] bg = this.bG;
        ++bg[n];
        this.P(n);
        this.ag(this.aB.B = 0);
        this.x();
        Label_0152: {
            fps fps;
            StringBuffer append;
            String h;
            if (fps.V == 0) {
                fps = this;
                append = new StringBuffer("You got wasted by ").append(this.aC[n].c.h).append(" with the ").append(this.dL[n2].a);
                h = "!";
            }
            else {
                if (fps.V != 1) {
                    break Label_0152;
                }
                fps = this;
                append = new StringBuffer("You got wasted by ");
                h = this.aC[n].c.h;
            }
            fps.a(append.append(h).toString(), Color.red);
        }
        this.q(n);
        final double random;
        if ((random = Math.random()) <= 0.3) {
            this.aB.t = 0;
            return;
        }
        if (random <= 0.6) {
            this.aB.t = 1;
            return;
        }
        this.aB.t = 2;
    }
    
    public final void o(final int n, final int n2) {
        if (fps.V == 0) {
            this.a("You Fragged " + this.aC[n2].c.h + " with the " + this.dL[n].a, this.bN);
            return;
        }
        if (fps.V == 1) {
            this.a("You Fragged " + this.aC[n2].c.h, this.bN);
        }
    }
    
    public final void K(final int n) {
        for (int i = 0; i < this.y; ++i) {
            if (i != n) {
                this.ch[i].c -= fps.A;
            }
        }
    }
    
    public final void a(final String s, final Color color) {
        int n = 0;
        int n2 = -1;
        int n3 = fps.D;
        int n4 = 0;
        while (n < this.y && this.ch[n].h) {
            ++n4;
            if (this.ch[n].c < n3) {
                n2 = n;
                n3 = this.ch[n].c;
            }
            ++n;
        }
        if (n < this.y && !this.ch[n].h) {
            this.ch[n].a(s, 10, fps.z + n4 * fps.A, color, 12345, fps.B);
            return;
        }
        this.K(n2);
        this.ch[n2].a(s, 10, fps.z + (n4 - 1) * fps.A, color, 12345, fps.B);
    }
    
    public final void a(final int n, final int n2, final int n3, final boolean b) {
        if (fps.V != 0) {
            if (fps.V == 1) {
                this.a(this.aC[n].c.h + " fragged " + this.aC[n2].c.h, Color.lightGray);
            }
            return;
        }
        if (b) {
            this.a(this.aC[n].c.h + " fragged " + this.aC[n2].c.h + " with the quad " + this.dL[n3].a, Color.lightGray);
            return;
        }
        this.a(this.aC[n].c.h + " fragged " + this.aC[n2].c.h + " with the " + this.dL[n3].a, Color.lightGray);
    }
    
    public final j a(j j, final int n, final float n2, final int n3) {
        final int l;
        if ((l = j.l) == -1) {
            j.l = this.a(j.a, j.c);
            if (j.l == -1) {
                fps fps;
                j i;
                int n4;
                j[][] db;
                if (n3 == -1) {
                    this.a(j, n, this.dB);
                    fps = this;
                    i = j;
                    n4 = n;
                    db = this.dB;
                }
                else {
                    this.a(j, n, this.dC[n3]);
                    fps = this;
                    i = j;
                    n4 = n;
                    db = this.dC[n3];
                }
                fps.a(i, n4, db, n3);
            }
        }
        else {
            if (this.dL[j.I].z > 0.0f) {
                final j k = j;
                k.e += (float)(Math.random() * this.dL[j.I].B - this.dL[j.I].B / 2.0f);
            }
            if (this.dL[j.I].A > 0.0f) {
                final j m = j;
                m.b += (float)(Math.random() * this.dL[j.I].C - this.dL[j.I].C / 2.0f);
            }
            final j j2 = j;
            j2.b += j.i;
            final float n5 = (float)(j.a + Math.sin(-j.e * 0.0174444) * n2);
            final float n6 = (float)(j.c - Math.cos(-j.e * 0.0174444) * n2);
            final float n7 = (float)(j.a + Math.sin(-j.e * 0.0174444) * (n2 / 2.0f));
            final float n8 = (float)(j.c - Math.cos(-j.e * 0.0174444) * (n2 / 2.0f));
            if (n3 > -1 && this.aB.B < 0 && this.a(n5, j.b, n6, n7, n8)) {
                j = this.b(j, n, this.dC[n3], -1);
                this.a(j, n, this.dC[n3], n3);
                this.J(20);
                this.aB = this.a(this.dL[j.I].h, this.aB, -1, n3);
                if (this.aB.z < 1 && this.aB.B < 0) {
                    this.n(n3, j.I);
                }
                else {
                    this.ag(2);
                }
            }
            else {
                final int a;
                if (n3 > -1 && (a = this.a(n3, n5, j.b, n6, n7, n8)) != -1 && a != n3) {
                    j.a = n7;
                    j.c = n8;
                    j = this.b(j, n, this.dC[n3], a);
                    this.a(j, n, this.dC[n3], n3);
                    this.aC[a].c = this.a(this.dL[j.I].h, this.aC[a].c, a, n3);
                    if (this.aC[a].c.z < 1 && this.aC[a].c.B == -1) {
                        if (n3 == -1) {
                            final e ab = this.aB;
                            ++ab.y;
                            final int[] bg = this.bG;
                            final int n9 = 5;
                            ++bg[n9];
                            this.ag(12);
                        }
                        else {
                            final e c = this.aC[n3].c;
                            ++c.y;
                            final int[] bg2 = this.bG;
                            ++bg2[n3];
                            this.P(n3);
                            this.a(n3, a, j.I, this.aC[n3].c.K > -1);
                        }
                        if (j.I == 8 || j.I == 9 || j.I == 7) {
                            this.a(a, 2, (n5 - j.a) / 2.0f, (n6 - j.c) / 2.0f, 0.8f, 4, this.aC[a].c.z, true, j.I, false);
                        }
                        else if (j.I == 2 || j.I == 3) {
                            this.a(a, 1, (n5 - j.a) / 5.0f, (n6 - j.c) / 5.0f, 0.4f, 3, this.aC[a].c.z, false, j.I, false);
                        }
                        else if (j.I == 5) {
                            this.a(a, 3, (n5 - j.a) / 3.0f, (n6 - j.c) / 3.0f, 0.2f, 3, this.aC[a].c.z, false, j.I, false);
                        }
                        else if (j.I == 3) {
                            this.a(a, 3, (n5 - j.a) / 4.0f, (n6 - j.c) / 4.0f, 0.7f, 4, this.aC[a].c.z, false, j.I, false);
                        }
                        else {
                            this.a(a, 0, 0.0f, 0.0f, 0.0f, 4, this.aC[a].c.z, false, j.I, false);
                        }
                    }
                    else if (this.aC[a].c.z > 0 && this.aC[a].c.B == -1) {
                        if (n3 == -1 || this.x(a)) {
                            this.ag(10);
                        }
                    }
                    else if (this.aC[a].c.z < -199 && this.aC[a].c.B < 1) {
                        if (n3 == -1 || this.x(a)) {
                            this.ag(7);
                        }
                        this.q(a, 0);
                    }
                }
                else {
                    final int b;
                    if (n3 == -1 && (b = this.b(n5, j.b, n6, n7, n8)) != -1) {
                        j.a = n7;
                        j.c = n8;
                        j = this.b(j, n, this.dB, b);
                        this.a(j, n, this.dB, n3);
                        this.aC[b].c = this.a(this.dL[j.I].h, this.aC[b].c, b, n3);
                        this.bz = 10;
                        if (this.aC[b].c.z < 1 && this.aC[b].c.B == -1) {
                            final e ab2 = this.aB;
                            ++ab2.y;
                            final int[] bg3 = this.bG;
                            final int n10 = 5;
                            ++bg3[n10];
                            this.ag(12);
                            this.e(b, j.I);
                            fps fps2;
                            int n11;
                            int n12;
                            float n13;
                            float n14;
                            float n15;
                            int n16;
                            int n17;
                            boolean b2;
                            if (j.I == 8 || j.I == 9 || j.I == 7) {
                                fps2 = this;
                                n11 = b;
                                n12 = 2;
                                n13 = (n5 - j.a) / 2.0f;
                                n14 = (n6 - j.c) / 2.0f;
                                n15 = 0.8f;
                                n16 = 4;
                                n17 = this.aC[b].c.z;
                                b2 = true;
                            }
                            else {
                                if (j.I == 2 || j.I == 3) {
                                    fps2 = this;
                                    n11 = b;
                                    n12 = 1;
                                    n13 = (n5 - j.a) / 5.0f;
                                    n14 = (n6 - j.c) / 5.0f;
                                    n15 = 0.4f;
                                    n16 = 3;
                                }
                                else if (j.I == 4) {
                                    fps2 = this;
                                    n11 = b;
                                    n12 = 3;
                                    n13 = (n5 - j.a) / 3.0f;
                                    n14 = (n6 - j.c) / 3.0f;
                                    n15 = 0.2f;
                                    n16 = 3;
                                }
                                else {
                                    if (j.I == 5) {
                                        fps2 = this;
                                        n11 = b;
                                        n12 = 3;
                                        n13 = (n5 - j.a) / 4.0f;
                                        n14 = (n6 - j.c) / 4.0f;
                                        n15 = 0.7f;
                                    }
                                    else {
                                        fps2 = this;
                                        n11 = b;
                                        n12 = 0;
                                        n13 = 0.0f;
                                        n14 = 0.0f;
                                        n15 = 0.0f;
                                    }
                                    n16 = 4;
                                }
                                n17 = this.aC[b].c.z;
                                b2 = false;
                            }
                            fps2.a(n11, n12, n13, n14, n15, n16, n17, b2, j.I, false);
                            this.o(j.I, b);
                        }
                        else if (this.aC[b].c.z > 0 && this.aC[b].c.B == -1) {
                            this.ag(10);
                        }
                        else if (this.aC[b].c.z < -199 && this.aC[b].c.B < 1) {
                            if (n3 == -1 || this.x(b)) {
                                this.ag(7);
                            }
                            if (this.dL[j.I].E) {
                                this.q(b, 2);
                            }
                            else {
                                this.q(b, 0);
                            }
                        }
                    }
                    else {
                        fps fps4 = null;
                        j j4 = null;
                        int n19 = 0;
                        j[][] db3 = null;
                        Label_2039: {
                            if (a(n5, n6, this.cN[l].v.a, this.cN[l].v.b, this.cN[l].v.c, this.cN[l].v.d)) {
                                if (this.b(l, n5, n6, n7, n8) > -1 || j.b <= 0.5f) {
                                    fps fps3;
                                    j j3;
                                    int n18;
                                    j[][] db2;
                                    if (n3 == -1) {
                                        this.a(j, n, this.dB);
                                        fps3 = this;
                                        j3 = j;
                                        n18 = n;
                                        db2 = this.dB;
                                    }
                                    else {
                                        this.a(j, n, this.dC[n3]);
                                        fps3 = this;
                                        j3 = j;
                                        n18 = n;
                                        db2 = this.dC[n3];
                                    }
                                    fps3.a(j3, n18, db2, n3);
                                    return j;
                                }
                                if (n3 <= -1 || !this.a(n5, j.b, n6, n7, n8)) {
                                    j.a = n5;
                                    j.c = n6;
                                    return j;
                                }
                                j = this.b(j, n, this.dC[n3], -1);
                            }
                            else {
                                if (this.a(l, j.a, j.c, n5, n6)) {
                                    j.a = n5;
                                    j.c = n6;
                                    j.l = this.a(j.a, j.c);
                                    return j;
                                }
                                if (n3 == -1) {
                                    this.a(j, n, this.dB);
                                    fps4 = this;
                                    j4 = j;
                                    n19 = n;
                                    db3 = this.dB;
                                    break Label_2039;
                                }
                                this.a(j, n, this.dC[n3]);
                            }
                            fps4 = this;
                            j4 = j;
                            n19 = n;
                            db3 = this.dC[n3];
                        }
                        fps4.a(j4, n19, db3, n3);
                    }
                }
            }
        }
        return j;
    }
    
    public final void ao() {
        System.out.println();
    }
    
    public final void ap() {
        for (int i = 0; i < this.bt; ++i) {
            this.bs[i] = -1;
        }
        this.bt = 0;
    }
    
    public final void L(final int n) {
        this.bs[this.bt] = n;
        ++this.bt;
    }
    
    public final void b(final int n, final int[] array) {
        this.y();
        this.ap();
        for (int i = 0; i < n; ++i) {
            this.h(array[i]);
            this.L(array[i]);
        }
    }
    
    public final void c(final int n, final int[] array) {
        for (int i = 0; i < n; ++i) {
            this.b(array[i]);
        }
    }
    
    public final void d(final int n, final int[] array) {
        this.q();
        this.c(n, array);
    }
    
    public final void p(final int n, final int n2) {
        this.b(this.cN[n2].i, this.cN[n2].j);
        this.d(this.cN[n2].i, this.cN[n2].j);
    }
    
    public static int f(final j[] array, final int n) {
        int n2;
        for (n2 = 0; (array[n2].u || array[n2].x > -1) && n2 < n - 1; ++n2) {}
        if (!array[n2].u && array[n2].x < 0 && n2 < n - 1) {
            return n2;
        }
        return -1;
    }
    
    public static int a(final j[] array) {
        int n;
        for (n = 0; (array[n].u || array[n].x > -1) && n < 9; ++n) {}
        if (!array[n].u && array[n].x < 0 && n < 9) {
            return n;
        }
        return -1;
    }
    
    public final void a(final j[] array, final int n, final int n2, final boolean b, final boolean b2) {
        final float n3 = this.dL[n].e;
        if (this.dL[n].d >= 2) {
            for (int i = 0; i < this.dL[n].d; ++i) {
                this.a(array, a(array), n, -n3 / 2.0f + n3 / this.dL[n].d / 2.0f + i * n3 / this.dL[n].d, 0.0f, n2, i != 0, 1);
            }
            return;
        }
        if (n == 0 && b2) {
            this.a(array, a(array), n, 0.0f, (float)(Math.random() * 2.0 - 1.0), n2, b, 2);
            this.a(array, a(array), n, 0.0f, (float)(Math.random() * 2.0 - 1.0), n2, true, 3);
            return;
        }
        this.a(array, a(array), n, 0.0f, 0.0f, n2, b, 1);
    }
    
    public final void b(final int n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.a.b(this.dF[n], n2, n3, n4);
        this.a.b(this.dF[n], n5);
        this.a.c(this.dF[n], n6);
        this.a.a(this.dF[n], this.dz[n].a, 10.0f, this.dz[n].c);
        this.a.b(this.dF[n], true);
        this.a.a(this.aj, this.dF[n]);
        this.a.b(this.dG[n], n2, n3, n4);
        this.a.b(this.dG[n], n5);
        this.a.c(this.dG[n], n6 * 2.0f);
        this.a.a(this.dG[n], this.dz[n].a, -6.0f, this.dz[n].c);
        this.a.b(this.dG[n], true);
        this.a.a(this.aj, this.dG[n]);
    }
    
    public final void M(final int n) {
        this.aB.a = this.dL[this.aB.b].g;
        final j ae = this.ae;
        ae.e += (float)(Math.random() * this.dL[this.aB.b].f - this.dL[this.aB.b].f / 2.0f);
        if (this.dL[this.aB.b].N) {
            this.b(n, this.dL[this.aB.b].O, this.dL[this.aB.b].P, this.dL[this.aB.b].Q, this.dL[this.aB.b].S, this.dL[this.aB.b].R);
        }
    }
    
    public final void a(final j[] array, final int n, final int z, final float n2, final float n3, final int a, final boolean b, final int n4) {
        if (n > -1) {
            array[n].J = 0;
            array[n].f = 45.0f;
            array[n].D = 0;
            array[n].z = z;
            array[n].A = a;
            array[n].F = this.dL[z].b;
            if (!b) {
                fps fps;
                int t;
                if (z == 0 && a == -1) {
                    fps = this;
                    t = 4;
                }
                else {
                    fps = this;
                    t = this.dL[z].T;
                }
                fps.af(t);
            }
            if (a == -1) {
                j j;
                j i;
                if (fps.P) {
                    array[n].a = this.af.a;
                    array[n].b = this.ae.b - 1.0f + n3;
                    array[n].c = this.af.c;
                    j = array[n];
                    i = this.af;
                }
                else {
                    array[n].a = this.ae.a;
                    array[n].b = this.ae.b - 1.0f + n3;
                    array[n].c = this.ae.c;
                    j = array[n];
                    i = this.ae;
                }
                j.e = i.e + n2;
                if (this.dL[z].G != 0.0f) {
                    final j k = array[n];
                    k.a += (float)(Math.sin(-(this.ae.e + this.dL[z].F) * 0.0174444) * this.dL[z].G);
                    final j l = array[n];
                    l.c -= (float)(Math.cos(-(this.ae.e + this.dL[z].F) * 0.0174444) * this.dL[z].G);
                }
                if (this.dL[z].H > 0.0f) {
                    final j m = array[n];
                    m.b += this.dL[z].H;
                }
                if (this.dL[this.aB.b].x != 0) {
                    final j j2 = array[n];
                    j2.e += (float)(Math.random() * this.dL[this.aB.b].x - this.dL[this.aB.b].x / 2);
                }
                this.aB.d = this.dL[z].c;
                this.M(n);
                if (this.dL[z].aj > -1) {
                    this.a(this.dD, 50, n, z, (float)(this.ae.a + Math.sin(-(this.ae.e + this.dL[z].F) * 0.0174444) * 5.0), array[n].b, (float)(this.ae.c - Math.cos(-(this.ae.e + this.dL[z].F) * 0.0174444) * 5.0), this.dL[z].aj, n4);
                }
            }
            else {
                array[n].a = this.aC[a].a.a;
                array[n].b = 2.5f + n3;
                array[n].c = this.aC[a].a.c;
                array[n].e = this.aC[a].a.e + n2 - 180.0f;
                this.aC[a].c.d = this.dL[z].c * this.cu;
                final j j3 = array[n];
                j3.e += (float)(Math.random() * this.dL[z].x - this.dL[z].x / 2);
            }
            j j4;
            float i2;
            if (this.dL[z].y != 0.0f) {
                j4 = array[n];
                i2 = (float)(Math.random() * this.dL[z].y) - this.dL[z].y / 2.0f;
            }
            else {
                j4 = array[n];
                i2 = 0.0f;
            }
            j4.i = i2;
            array[n].l = this.a(array[n].a, array[n].c);
            this.c(array, n, z, a);
            if (this.dL[array[n].z].I) {
                this.a(this.ae.e, array[n].v, array[n].a, array[n].b, array[n].c);
                return;
            }
            this.a(array[n].e, array[n].v, array[n].a, array[n].b, array[n].c);
        }
    }
    
    public final void c(final j[] array, final int n, final int i, final int n2) {
        if (this.dL[i].i != -1) {
            j j;
            int[] array2;
            int n3;
            if (n2 == -1) {
                j = array[n];
                array2 = this.dH;
                n3 = this.dL[i].i;
            }
            else {
                j = array[n];
                array2 = this.dH;
                n3 = this.dL[i].j;
            }
            j.w = array2[n3];
            this.a.a(this.aj, array[n].v);
            this.a.a(array[n].v, array[n].w);
        }
        array[n].u = true;
        array[n].I = i;
    }
    
    public final void g(final j[] array, final int n) {
        this.a.c(array[n].v, array[n].w);
        this.a.c(this.aj, array[n].v);
        array[n].u = false;
    }
    
    public final void q(final int n, final int n2) {
        this.l(n);
        this.a.a(this.aC[n].a.w, false);
        this.aC[n].h = 0;
        this.aC[n].j = true;
        this.aC[n].c.B = 0;
        d d;
        boolean i;
        if (n2 == 2) {
            d = this.aC[n];
            i = false;
        }
        else {
            d = this.aC[n];
            i = true;
        }
        d.i = i;
        for (int j = 0; j < 10; ++j) {
            this.aC[n].f[j].a = this.aC[n].a.a;
            this.aC[n].f[j].b = this.aC[n].a.b;
            this.aC[n].f[j].c = this.aC[n].a.c;
            j k;
            double n3;
            double n4;
            if (n2 == 2) {
                this.aC[n].f[j].h = (float)(Math.random() * 0.2 - 0.001);
                this.aC[n].f[j].i = 0.1f;
                k = this.aC[n].f[j];
                n3 = Math.random() * 0.2;
                n4 = 0.001;
            }
            else {
                this.aC[n].f[j].h = (float)(Math.random() * 0.4 - 0.2);
                this.aC[n].f[j].i = (float)(Math.random() * 0.8 + 0.2);
                k = this.aC[n].f[j];
                n3 = Math.random() * 0.4;
                n4 = 0.2;
            }
            k.j = (float)(n3 - n4);
            this.aC[n].f[j].e = 0.0f;
            this.aC[n].f[j].f = 0.0f;
            this.a.a(this.aC[n].e[j], true);
        }
        if (n2 == 1) {
            for (int l = 0; l < 10; ++l) {
                final j m = this.aC[n].f[l];
                m.b += 1.5;
                final j j2 = this.aC[n].f[l];
                j2.a += (float)(Math.random() * 3.0);
                final j j3 = this.aC[n].f[l];
                j3.c += (float)(Math.random() * 3.0);
                final j j4 = this.aC[n].f[l];
                j4.i += 0.2f;
            }
            return;
        }
        final j j5 = this.aC[n].f[0];
        j5.b += 4.5;
        this.aC[n].f[0].e = this.aC[n].a.e - 180.0f;
        this.a(n, 1, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].a.e - 180.0f - 90.0f, 0.5f, 1.5f);
        this.a(n, 2, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].a.e - 180.0f + 90.0f, 0.5f, 1.5f);
        fps fps;
        int n5;
        int n6;
        float n7;
        float n8;
        float n9;
        float n10;
        if (n2 == 2) {
            this.a(n, 3, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].a.e - 180.0f - 90.0f, 0.75f, 4.25f);
            fps = this;
            n5 = n;
            n6 = 4;
            n7 = this.aC[n].a.a;
            n8 = this.aC[n].a.c;
            n9 = this.aC[n].a.e - 180.0f + 90.0f;
            n10 = 0.75f;
        }
        else {
            this.a(n, 3, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].a.e - 180.0f - 90.0f, 0.85f, 4.25f);
            fps = this;
            n5 = n;
            n6 = 4;
            n7 = this.aC[n].a.a;
            n8 = this.aC[n].a.c;
            n9 = this.aC[n].a.e - 180.0f + 90.0f;
            n10 = 0.85f;
        }
        fps.a(n5, n6, n7, n8, n9, n10, 4.25f);
        this.aC[n].f[3].d = 30.0f;
        this.aC[n].f[4].d = 30.0f;
        this.a(n, 5, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].a.e - 180.0f - 90.0f, 0.25f, 3.5f);
        this.a(n, 6, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].a.e - 180.0f + 90.0f, 0.25f, 3.5f);
        this.a(n, 7, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].a.e - 180.0f - 90.0f, 0.5f, 2.5f);
        this.a(n, 8, this.aC[n].a.a, this.aC[n].a.c, this.aC[n].a.e - 180.0f + 90.0f, 0.5f, 2.5f);
        final j j6 = this.aC[n].f[9];
        j6.a += (float)0.0;
        final j j7 = this.aC[n].f[9];
        j7.b += 5.5;
        this.aC[n].f[9].e = this.aC[n].a.e - 180.0f;
    }
    
    public final void a(final int n, final int n2, final float n3, final float n4, final float n5, final float n6, final float b) {
        this.aC[n].f[n2].a = (float)(n3 + Math.sin(-n5 * 0.0174444) * n6);
        this.aC[n].f[n2].c = (float)(n4 - Math.cos(-n5 * 0.0174444) * n6);
        this.aC[n].f[n2].b = b;
    }
    
    public final void N(final int n) {
        for (int i = 0; i < 10; ++i) {
            final j j = this.aC[n].f[i];
            j.a += this.aC[n].f[i].h;
            final j k = this.aC[n].f[i];
            k.b += this.aC[n].f[i].i;
            final j l = this.aC[n].f[i];
            l.c += this.aC[n].f[i].j;
            final j m = this.aC[n].f[i];
            m.e += this.aC[n].f[i].h * 50.0f;
            final j j2 = this.aC[n].f[i];
            j2.f += this.aC[n].f[i].j * 50.0f;
            j j4;
            float i2;
            if (this.aC[n].f[i].i > 0.0f) {
                final j j3 = this.aC[n].f[i];
                j3.i *= 0.9;
                if (this.aC[n].f[i].i >= 0.05) {
                    continue;
                }
                j4 = this.aC[n].f[i];
                i2 = -0.05f;
            }
            else {
                final j j5 = this.aC[n].f[i];
                j5.i *= 1.1;
                if (this.aC[n].f[i].b > 1.0f) {
                    continue;
                }
                i2 = (float)((j4 = this.aC[n].f[i]).i * -0.15);
            }
            j4.i = i2;
        }
        final d d = this.aC[n];
        ++d.h;
        if (this.aC[n].h > 100) {
            this.O(n);
        }
    }
    
    public final void O(final int n) {
        this.aC[n].h = -1;
        for (int i = 0; i < 10; ++i) {
            this.a.a(this.aC[n].e[i], false);
        }
    }
    
    public final j b(final j j, final int n, final j[][] array, final int l) {
        j.L = l;
        if (this.dL[j.I].K && (!this.dL[j.z].V || j.A == -1) && j.l >= 0 && this.w(j.l)) {
            this.af(this.dL[j.I].U);
        }
        j.H = this.dL[j.I].ab;
        array[n][0].t = 10;
        for (int i = 0; i < array[n][0].t; ++i) {
            array[n][i].u = true;
            array[n][i].a = j.a;
            j k;
            float b;
            if (l == -1) {
                k = array[n][i];
                b = 0.75f;
            }
            else {
                k = array[n][i];
                b = j.b;
            }
            k.b = b;
            array[n][i].c = j.c;
            array[n][i].f = 0.0f;
            array[n][i].h = (float)(Math.random() * 0.4 - 0.2);
            array[n][i].i = (float)(Math.random() * 0.6 - 0.1);
            array[n][i].j = (float)(Math.random() * 0.4 - 0.2);
            array[n][i].D = 0;
            array[n][i].G = 1;
            array[n][i].y = 1.0f;
            this.a.a(this.aj, array[n][i].v);
            if (i == 0 && this.dL[j.z].n > -1) {
                j m;
                int w;
                if (this.dL[j.z].p == 5 || l == -1) {
                    m = array[n][i];
                    w = array[n][0].q;
                }
                else if (this.dL[j.z].p == 8) {
                    m = array[n][i];
                    w = array[n][0].r;
                }
                else if (this.dL[j.z].p == 6) {
                    m = array[n][i];
                    w = array[n][0].s;
                }
                else {
                    m = array[n][i];
                    w = array[n][0].p;
                }
                m.w = w;
                j.B = 0;
                j.C = this.dL[j.z].q;
            }
            else {
                array[n][i].w = this.dH[12];
            }
            this.a.a(array[n][i].v, array[n][i].w);
            ++this.dM;
        }
        j.x = 0;
        return j;
    }
    
    public final void a(final j j, final int n, final j[][] array) {
        j.L = -2;
        array[n][0].t = this.dL[j.z].l;
        Label_0074: {
            j i;
            int t;
            if (this.ct > 2) {
                t = (i = array[n][0]).t * 2;
            }
            else {
                if (this.ct >= 2) {
                    break Label_0074;
                }
                t = (int)((i = array[n][0]).t * 0.5);
            }
            i.t = t;
        }
        j.H = this.dL[j.z].ab;
        for (int k = 0; k < array[n][0].t; ++k) {
            array[n][k].u = true;
            array[n][k].a = j.a;
            array[n][k].b = j.b;
            array[n][k].c = j.c;
            array[n][k].f = 0.0f;
            array[n][k].h = (float)(Math.random() * 0.5 - 0.25);
            array[n][k].i = (float)(Math.random() - 0.25);
            array[n][k].j = (float)(Math.random() * 0.5 - 0.25);
            if (j.z > 2) {
                array[n][k].i = (float)(Math.random() + 0.25);
            }
            array[n][k].D = 0;
            array[n][k].G = 0;
            array[n][k].y = 1.0f;
            this.a.a(this.aj, array[n][k].v);
            if (k == 0 && this.dL[j.z].n >= 0) {
                j l;
                int w;
                if (this.dL[j.z].p == 5) {
                    l = array[n][k];
                    w = array[n][0].q;
                }
                else if (this.dL[j.z].p == 8) {
                    l = array[n][k];
                    w = array[n][0].r;
                }
                else if (this.dL[j.z].p == 6) {
                    l = array[n][k];
                    w = array[n][0].s;
                }
                else {
                    l = array[n][k];
                    w = array[n][0].p;
                }
                l.w = w;
                j.B = 0;
                j.C = this.dL[j.z].q;
            }
            else {
                array[n][k].w = this.dH[this.dL[j.z].k];
            }
            this.a.a(array[n][k].v, array[n][k].w);
            ++this.dM;
        }
        j.x = 0;
        final float n2 = (float)(j.a + Math.sin(-(j.e + 180.0f) * 0.0174444) * 3.0);
        final float n3 = (float)(j.c - Math.cos(-(j.e + 180.0f) * 0.0174444) * 3.0);
        this.a.a(this.dF[n], n2, 10.0f, n3);
        this.a.a(this.dG[n], n2, -6.0f, n3);
        if ((!this.dL[j.z].V || j.A == -1) && j.l >= 0 && this.w(j.l)) {
            this.af(this.dL[j.z].U);
        }
    }
    
    public final void c(final j j, final int n, final j[][] array, final int n2) {
        j.x = -1;
        for (int i = 0; i < array[n][0].t; ++i) {
            if (array[n][i].u) {
                array[n][i].u = false;
                this.a.c(array[n][i].v, array[n][i].w);
                this.a.c(this.aj, array[n][i].v);
                --this.dM;
            }
        }
        if (n2 == -1) {
            this.a.b(this.dF[n], false);
            this.a.c(this.aj, this.dF[n]);
            this.a.b(this.dG[n], false);
            this.a.c(this.aj, this.dG[n]);
        }
        if (n2 == -1) {
            this.e(this.dD, 50, n);
        }
    }
    
    public final void aq() {
        final e ab = this.aB;
        ++ab.B;
        if (this.ae.b > 0.2) {
            final j ae = this.ae;
            ae.b -= 0.1;
        }
        Label_0206: {
            if (this.aB.t == 0 && this.ae.b < 1.5 && this.ae.d < 90.0f) {
                final j ae2 = this.ae;
                ae2.d += 1.5;
            }
            else {
                if (this.aB.t != 1 || this.ae.f >= 90.0f) {
                    if (this.aB.t != 2 || this.ae.b >= 1.5 || this.ae.d >= 90.0f) {
                        break Label_0206;
                    }
                    final j ae3 = this.ae;
                    ae3.d += 1.5;
                }
                final j ae4 = this.ae;
                ae4.f += 1.5;
            }
        }
        this.a(a(this.ae.d), a(this.ae.e), a(this.ae.f), this.ak, this.ae.a, this.ae.b, this.ae.c);
        if (this.aB.B > 150) {
            if (this.at()) {
                this.as();
                return;
            }
            this.a(-1, true);
        }
    }
    
    public final void P(final int n) {
        if (this.bG[n] >= this.cp.g) {
            this.as();
        }
    }
    
    public final int ar() {
        int n = 0;
        for (int i = 1; i < fps.F; ++i) {
            if (this.aC[i].c.h.length() > this.aC[n].c.h.length()) {
                n = i;
            }
        }
        return n;
    }
    
    public final void as() {
        if (fps.P) {
            fps.Q = true;
            return;
        }
        if (this.cp.a != 3 && this.bA == -1) {
            this.R();
            this.cp.a = 3;
            this.cp.c = fps.B;
            this.bd = this.ar();
        }
    }
    
    public final boolean at() {
        boolean b = false;
        for (int i = 0; i < fps.F; ++i) {
            if (this.aC[i].c.y >= this.cp.g) {
                b = true;
                this.as();
            }
        }
        return b;
    }
    
    public final void r(final int n, final int h) {
        if (this.aB.B <= -1) {
            fps.Q = (this.aB.y >= this.cp.g);
            fps.P = true;
            fps.M = true;
            fps.O = 2;
            this.af.a(this.ae);
            this.af.G = 0;
            this.af.n = n;
            this.af.H = h;
            this.a.a(this.aj, this.aE.v);
            a a;
            j ae;
            if (this.aB.K > -1) {
                this.a.a(this.aE.v, this.aD[0].w);
                a = this.a;
                ae = this.aD[0];
            }
            else if (this.aB.L > -1) {
                this.a.a(this.aE.v, this.aD[1].w);
                a = this.a;
                ae = this.aD[1];
            }
            else if (this.aB.M > -1) {
                this.a.a(this.aE.v, this.aD[2].w);
                a = this.a;
                ae = this.aD[2];
            }
            else if (this.aB.O > -1) {
                this.a.a(this.aE.v, this.aD[3].w);
                a = this.a;
                ae = this.aD[3];
            }
            else {
                this.a.a(this.aE.v, this.aE.w);
                a = this.a;
                ae = this.aE;
            }
            a.a(ae.w, true);
            this.x();
            this.cA = 1;
            fps.S = true;
        }
    }
    
    public final void au() {
        int n = 0;
        final int l = this.ae.l;
        float a = this.ae.a;
        float c = this.ae.c;
        Label_0083: {
            fps fps;
            int ca;
            if (this.cA > 0) {
                if (this.cA >= fps.R) {
                    break Label_0083;
                }
                fps = this;
                ca = this.cA + 2;
            }
            else {
                if (this.cA >= 0) {
                    break Label_0083;
                }
                this.cA += 2;
                if (this.cA < 0) {
                    break Label_0083;
                }
                fps = this;
                ca = 0;
            }
            fps.cA = ca;
        }
        this.a(this.af.e - 180.0f, this.aE.v, this.af.a, 0.0f, this.af.c, 1.3f);
        if (this.af.G == 0) {
            Label_0217: {
                float n2;
                if (this.ae.a < this.aC[this.af.n].a.a) {
                    n2 = this.ae.a + 0.90000004f;
                }
                else {
                    if (this.ae.a <= this.aC[this.af.n].a.a) {
                        break Label_0217;
                    }
                    n2 = this.ae.a - 0.90000004f;
                }
                a = n2;
            }
            Label_0302: {
                float n3;
                if (this.ae.c < this.aC[this.af.n].a.c) {
                    n3 = this.ae.c + 0.90000004f;
                }
                else {
                    if (this.ae.c <= this.aC[this.af.n].a.c) {
                        break Label_0302;
                    }
                    n3 = this.ae.c - 0.90000004f;
                }
                c = n3;
            }
            this.ae = this.a(this.ae, a, c);
            if (this.ae.l != l && this.ae.l != -1 && l != -1) {
                this.p(l, this.ae.l);
                n = 1;
            }
            this.ae.e = a(this.ae.a, this.ae.c, this.aC[this.af.n].a.a, this.aC[this.af.n].a.c);
            if (b(this.ae.a, this.ae.c, this.aC[this.af.n].a.a, this.aC[this.af.n].a.c) < 15.0) {
                fps.S = false;
                this.af.G = 1;
                this.af.E = 0;
                this.af.y = (float)b(this.ae.a, this.ae.c, this.aC[this.af.n].a.a, this.aC[this.af.n].a.c);
                this.af.g = -this.ae.e;
                if (this.af.g < 0.0f) {
                    final j af = this.af;
                    af.g += 360.0f;
                }
            }
        }
        if (this.af.G == 1) {
            this.ae = this.a(this.ae, (float)(this.aC[this.af.n].a.a - Math.sin(this.af.g * 0.0174444) * this.af.y), (float)(this.aC[this.af.n].a.c + Math.cos(this.af.g * 0.0174444) * this.af.y));
            if (n == 0 && this.ae.l != l && this.ae.l != -1 && l != -1) {
                this.p(l, this.ae.l);
                n = 1;
            }
            final j af2 = this.af;
            af2.g += 2.0f;
            if (this.af.g > 359.0f) {
                this.af.g -= 360.0f;
            }
            if (this.af.y < 15.0f) {
                final j af3 = this.af;
                ++af3.y;
            }
            this.ae.e = -this.af.g;
            if (this.ae.e < 0.0f) {
                final j ae = this.ae;
                ae.e += 360.0f;
            }
            final j af4 = this.af;
            ++af4.E;
            if (this.af.E > 100) {
                float n4;
                if ((n4 = a(this.aC[this.af.n].a.a, this.aC[this.af.n].a.c, this.af.a, this.af.c)) < 0.0f) {
                    n4 += 360.0f;
                }
                if (this.ae.e > n4 - 3.0f && this.ae.e < n4 + 3.0f) {
                    this.af.G = 2;
                    this.cA = -fps.R;
                    fps.S = true;
                }
            }
        }
        Label_1457: {
            if (this.af.G == 2) {
                this.ae.e = a(this.ae.a, this.ae.c, this.af.a, this.af.c);
                Label_1123: {
                    float n5;
                    if (this.ae.a < this.af.a) {
                        n5 = a + 0.90000004f;
                    }
                    else {
                        if (this.ae.a <= this.af.a) {
                            break Label_1123;
                        }
                        n5 = a - 0.90000004f;
                    }
                    a = n5;
                }
                Label_1176: {
                    float n6;
                    if (this.ae.c < this.af.c) {
                        n6 = c + 0.90000004f;
                    }
                    else {
                        if (this.ae.c <= this.af.c) {
                            break Label_1176;
                        }
                        n6 = c - 0.90000004f;
                    }
                    c = n6;
                }
                this.ae = this.a(this.ae, a, c);
                if (n == 0 && this.ae.l != l && this.ae.l != -1 && l != -1) {
                    this.p(l, this.ae.l);
                }
                Label_1293: {
                    j j;
                    float e;
                    if (this.ae.e < 0.0f) {
                        e = (j = this.ae).e + 360.0f;
                    }
                    else {
                        if (this.ae.e <= 359.0f) {
                            break Label_1293;
                        }
                        j = this.ae;
                        e = 360.0f - this.ae.e;
                    }
                    j.e = e;
                }
                if (b(this.ae.a, this.ae.c, this.af.a, this.af.c) < 4.5) {
                    this.af.G = 3;
                    Label_1398: {
                        j i;
                        float e2;
                        if (this.ae.e < 0.0f) {
                            e2 = (i = this.ae).e + 360.0f;
                        }
                        else {
                            if (this.ae.e <= 359.0f) {
                                break Label_1398;
                            }
                            i = this.ae;
                            e2 = 360.0f - this.ae.e;
                        }
                        i.e = e2;
                    }
                    j k;
                    float e3;
                    if (this.af.e < 0.0f) {
                        e3 = (k = this.af).e + 360.0f;
                    }
                    else {
                        if (this.af.e <= 359.0f) {
                            break Label_1457;
                        }
                        k = this.af;
                        e3 = 360.0f - this.af.e;
                    }
                    k.e = e3;
                }
            }
        }
        if (this.af.G == 3) {
            Label_1534: {
                j m;
                float e4;
                if (this.ae.e < this.af.e) {
                    e4 = (m = this.ae).e + 8.0f;
                }
                else {
                    if (this.ae.e <= this.af.e) {
                        break Label_1534;
                    }
                    e4 = (m = this.ae).e - 8.0f;
                }
                m.e = e4;
            }
            if (this.ae.e > this.af.e - 10.0f && this.ae.e < this.af.e + 10.0f) {
                fps.S = false;
                this.av();
                this.w();
            }
        }
    }
    
    public final void av() {
        fps.P = false;
        fps.M = false;
        if (this.cA != 0) {
            this.cA = 0;
        }
        this.ae.a(this.af);
        a a;
        j ae;
        if (this.aB.K > -1) {
            this.a.c(this.aE.v, this.aD[0].w);
            a = this.a;
            ae = this.aD[0];
        }
        else if (this.aB.L > -1) {
            this.a.c(this.aE.v, this.aD[1].w);
            a = this.a;
            ae = this.aD[1];
        }
        else if (this.aB.M > -1) {
            this.a.c(this.aE.v, this.aD[2].w);
            a = this.a;
            ae = this.aD[2];
        }
        else if (this.aB.O > -1) {
            this.a.c(this.aE.v, this.aD[3].w);
            a = this.a;
            ae = this.aD[3];
        }
        else {
            this.a.c(this.aE.v, this.aE.w);
            a = this.a;
            ae = this.aE;
        }
        a.a(ae.w, false);
        this.a.c(this.aj, this.aE.v);
        this.ae.l = this.a(this.ae.a, this.ae.c);
        this.y();
        this.a(this.cN[this.ae.l].i, this.cN[this.ae.l].j);
        this.d(this.cN[this.ae.l].i, this.cN[this.ae.l].j);
        for (int i = 0; i < fps.F; ++i) {
            this.g(i);
        }
        this.aw();
        this.v();
        if (fps.Q) {
            fps.Q = false;
            this.n(10);
        }
        else {
            fps.Q = false;
        }
        this.a.a(this.aj, this.dv);
        this.a.a(this.aj, this.dw);
        this.a.a(this.aj, this.dx);
        this.aB.a = 0;
    }
    
    public final void aw() {
        for (int i = 0; i < fps.F; ++i) {
            if (this.aC[i].l > -1) {
                for (int j = 0; j < this.aC[i].l + 1; ++j) {
                    this.a.a(this.aj, this.aC[i].k[j].v);
                    this.a.a(this.aC[i].k[j].v, this.aC[i].k[j].w);
                    this.a.a(this.aC[i].k[j].w, true);
                }
            }
        }
    }
    
    public final void ax() {
        Label_0102: {
            if (this.aB.B < 0) {
                j j;
                float f;
                if (this.ae.f > 0.0f && (!this.cq[0] || !this.cq[8])) {
                    f = (j = this.ae).f - 0.25f;
                }
                else {
                    if (this.ae.f >= 0.0f || (this.cq[1] && this.cq[8])) {
                        break Label_0102;
                    }
                    f = (j = this.ae).f + 0.25f;
                }
                j.f = f;
            }
        }
        if (fps.P) {
            this.a(a(this.ae.d), a(this.ae.e), a(this.ae.f), this.ak, this.ae.a, this.ae.b, this.ae.c);
            return;
        }
        if (this.aB.B > -1) {
            this.aq();
            return;
        }
        if (this.aB.a < 1) {
            this.a(a(this.ae.d), a(this.ae.e), a(this.ae.f + this.aB.H * 2.0f), this.ak, this.ae.a, this.ae.b + this.aB.H, this.ae.c);
            return;
        }
        this.aB.p = (float)(Math.random() * 0.10000000149011612);
        this.aB.q = (float)(Math.random() * 0.10000000149011612);
        this.aB.r = (float)(Math.random() * 0.10000000149011612);
        this.a(a(this.ae.d), a(this.ae.e), a(this.ae.f + this.aB.H * 2.0f), this.ak, this.ae.a + this.aB.p, this.ae.b + this.aB.q + this.aB.H, this.ae.c + this.aB.r);
        final e ab = this.aB;
        --ab.a;
    }
    
    public final void ay() {
        if (!this.aB.J) {
            this.aB.J = true;
            final e ab = this.aB;
            ab.H += 0.05f * this.aB.I;
            if (this.aB.H > 0.3f) {
                final e ab2 = this.aB;
                ab2.I *= -1.0f;
                this.ag(8);
                return;
            }
            if (this.aB.H < -0.3f) {
                final e ab3 = this.aB;
                ab3.I *= -1.0f;
                this.ag(9);
            }
        }
    }
    
    public final void az() {
        final int l = this.ae.l;
        this.aB.J = false;
        boolean b = false;
        if (this.aB.B < 0 && !fps.P) {
            Label_0614: {
                j j = null;
                float e;
                if (this.cq[0]) {
                    if (this.cq[8]) {
                        this.ae = this.b(this.ae, this.ae.l, (float)(this.ae.a - Math.sin((-this.ae.e + 90.0f) * 0.0174444) * this.aB.G), (float)(this.ae.c + Math.cos((-this.ae.e + 90.0f) * 0.0174444) * this.aB.G), true);
                        if (this.ae.l != l) {
                            this.p(l, this.ae.l);
                            b = true;
                        }
                        this.aB = this.a(this.ae.a, this.ae.c, this.ae.l, this.aB, -1);
                        if (this.ae.f < 3.0f) {
                            final j ae = this.ae;
                            ae.f += 0.25f;
                        }
                        this.ay();
                        break Label_0614;
                    }
                    if (fps.bn) {
                        final l cp = this.cp;
                        cp.j += 0.37f;
                        e = ((this.cp.j < fps.I) ? ((j = this.ae).e + this.cp.j) : ((j = this.ae).e + fps.I));
                    }
                    else {
                        e = (j = this.ae).e + fps.I;
                    }
                }
                else {
                    if (!this.cq[1]) {
                        break Label_0614;
                    }
                    if (this.cq[8]) {
                        this.ae = this.b(this.ae, this.ae.l, (float)(this.ae.a - Math.sin((-this.ae.e - 90.0f) * 0.0174444) * this.aB.G), (float)(this.ae.c + Math.cos((-this.ae.e - 90.0f) * 0.0174444) * this.aB.G), true);
                        if (this.ae.l != l) {
                            this.p(l, this.ae.l);
                            b = true;
                        }
                        this.aB = this.a(this.ae.a, this.ae.c, this.ae.l, this.aB, -1);
                        if (this.ae.f > -3.0f) {
                            final j ae2 = this.ae;
                            ae2.f -= 0.25f;
                        }
                        this.ay();
                        break Label_0614;
                    }
                    float n = 0.0f;
                    float n2 = 0.0f;
                    Label_0610: {
                        fps fps;
                        if (fps.bn) {
                            final l cp2 = this.cp;
                            cp2.k += 0.37f;
                            if (this.cp.k < fps.I) {
                                n = (j = this.ae).e;
                                n2 = this.cp.k;
                                break Label_0610;
                            }
                            fps = this;
                        }
                        else {
                            fps = this;
                        }
                        n = (j = fps.ae).e;
                        n2 = fps.I;
                    }
                    e = n - n2;
                }
                j.e = e;
            }
            if (fps.bn) {
                if (!this.cq[0]) {
                    this.cp.j = 0.0f;
                }
                if (!this.cq[1]) {
                    this.cp.k = 0.0f;
                }
            }
            Label_0950: {
                fps fps2;
                if (this.cq[2]) {
                    this.ae = this.b(this.ae, this.ae.l, (float)(this.ae.a - Math.sin(-this.ae.e * 0.0174444) * this.aB.F), (float)(this.ae.c + Math.cos(-this.ae.e * 0.0174444) * this.aB.F), true);
                    if (!b && this.ae.l != l) {
                        this.p(l, this.ae.l);
                    }
                    fps2 = this;
                }
                else {
                    if (!this.cq[3]) {
                        break Label_0950;
                    }
                    this.ae = this.b(this.ae, this.ae.l, (float)(this.ae.a + Math.sin(-this.ae.e * 0.0174444) * this.aB.F), (float)(this.ae.c - Math.cos(-this.ae.e * 0.0174444) * this.aB.F), true);
                    if (!b && this.ae.l != l) {
                        this.p(l, this.ae.l);
                    }
                    fps2 = this;
                }
                fps2.aB = this.a(this.ae.a, this.ae.c, this.ae.l, this.aB, -1);
                this.ay();
            }
            if (this.cq[4] && this.aB.d <= 0) {
                if (this.aB.C[this.aB.b] > 0) {
                    this.a(this.dz, this.aB.b, -1, false, this.aB.c);
                    if (this.dL[this.aB.b].W > 0.0f && !this.dL[this.aB.b].N) {
                        this.aB = this.a(this.aB, this.ae.a, this.ae.c, this.aB.b);
                    }
                    final int[] c = this.aB.C;
                    final int b2 = this.aB.b;
                    --c[b2];
                    if (this.aB.C[this.aB.b] <= 0) {
                        this.aB = this.a(this.aB, -1);
                    }
                    this.aB.e = this.dL[this.aB.b].v;
                    this.aB.f = this.dL[this.aB.b].w;
                    this.aB.g = this.dL[this.aB.b].w;
                }
                else {
                    this.aB = this.a(this.aB, -1);
                }
            }
            Label_1267: {
                if (this.bg) {
                    j i;
                    float d;
                    if (this.cq[5]) {
                        d = (i = this.ae).d + 1.0f;
                    }
                    else {
                        if (!this.cq[6]) {
                            break Label_1267;
                        }
                        d = (i = this.ae).d - 1.0f;
                    }
                    i.d = d;
                }
            }
            if (this.cN[this.ae.l].j[0] != this.ae.l) {
                this.ae.l = this.a(this.ae.a, this.ae.c);
            }
            if (this.cN[this.ae.l].i != this.cd) {
                this.p(l, this.ae.l);
            }
        }
    }
    
    public final void Q(final int n) {
        if (n == 27) {
            this.cp.n = false;
            if (fps.V == 2) {
                this.Q();
            }
        }
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.cn = mouseEvent.getX();
        this.co = mouseEvent.getY();
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.cc = true;
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.cc = false;
    }
    
    public final void R(final int n) {
        if (n == 0) {
            this.cp.i[0] = 15;
            this.cp.i[1] = 16;
            this.cp.i[2] = 18;
            return;
        }
        if (n == 1) {
            this.cp.i[0] = 16;
            this.cp.i[1] = 18;
            this.cp.i[2] = 15;
            return;
        }
        if (n == 2) {
            this.cp.i[0] = 18;
            this.cp.i[1] = 15;
            this.cp.i[2] = 16;
        }
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.cc = true;
        this.requestFocus();
        if (this.cp.a == 0 && this.cp.d < 9 && this.cp.b != -1) {
            if (((this.co > fps.D - 40 && this.cn > fps.B - fps.B / 5) || (this.cn >= this.bP && this.cn <= this.bP + this.aL.getWidth(this) * fps.bR && this.co >= this.bQ && this.co <= this.bQ + this.aL.getHeight(this) * fps.bR)) && !this.cp.v[this.cp.b]) {
                this.ag(this.br);
                Label_0449: {
                    if (this.cp.b == 1) {
                        this.cp.m = 16;
                        this.cp.h = -1;
                        this.cp.i[0] = 16;
                        this.cp.i[1] = 18;
                        this.cp.i[2] = 15;
                    }
                    else if (this.cp.b == 0) {
                        this.cp.m = 15;
                        this.cp.h = -1;
                        this.cp.i[0] = 15;
                        this.cp.i[1] = 16;
                        this.cp.i[2] = 18;
                    }
                    else if (this.cp.b == 2) {
                        this.cp.m = 18;
                        this.cp.h = -1;
                        this.cp.i[0] = 18;
                        this.cp.i[1] = 15;
                        this.cp.i[2] = 16;
                    }
                    else {
                        l l;
                        int h;
                        if (this.cp.b == 3) {
                            this.cp.m = 19;
                            l = this.cp;
                            h = 2;
                        }
                        else if (this.cp.b == 4) {
                            this.cp.m = 5;
                            l = this.cp;
                            h = 3;
                        }
                        else {
                            if (this.cp.b != 5) {
                                break Label_0449;
                            }
                            this.cp.m = 4;
                            l = this.cp;
                            h = 4;
                        }
                        l.h = h;
                    }
                }
                if (this.aW != this.cp.m) {
                    this.aY = false;
                }
                this.ak();
                this.cp.u = true;
                return;
            }
            if (this.co > fps.D - 40 && this.cn < fps.B / 5) {
                this.ag(this.br);
                this.c();
                return;
            }
            if (this.co > this.bQ && this.co < this.bQ + 112 * fps.bS) {
                Label_0603: {
                    l i;
                    int b;
                    if (this.cn > this.bP + 125 * fps.bS) {
                        b = (i = this.cp).b + 1;
                    }
                    else {
                        if (this.cn >= this.bP) {
                            break Label_0603;
                        }
                        b = (i = this.cp).b - 1;
                    }
                    i.b = b;
                }
                Label_0640: {
                    l j;
                    int b2;
                    if (this.cp.b < 0) {
                        j = this.cp;
                        b2 = 5;
                    }
                    else {
                        if (this.cp.b <= 5) {
                            break Label_0640;
                        }
                        j = this.cp;
                        b2 = 0;
                    }
                    j.b = b2;
                }
                this.ag(this.br);
            }
        }
        else {
            if (this.cp.a == 1) {
                if (this.bi) {
                    if (this.cn > fps.B / 2 - this.aS.getWidth(this) / 2 && this.cn < fps.B / 2 - this.aS.getWidth(this) / 2 + this.aS.getWidth(this) && this.co > fps.D / 2 - this.aS.getHeight(this) / 2 && this.co < fps.D / 2 - this.aS.getHeight(this) / 2 + this.aS.getHeight(this)) {
                        try {
                            this.getAppletContext().showDocument(new URL(this.bf), "_blank");
                        }
                        catch (Exception ex) {
                            System.out.println(String.valueOf(ex));
                        }
                    }
                    if (this.cm && this.co > fps.D / 2 - this.aS.getHeight(this) / 2 + this.aS.getHeight(this)) {
                        this.ah();
                        this.a(false);
                        this.cp.u = true;
                        this.cm = false;
                        this.cp.d = -1;
                    }
                }
                else {
                    if (!this.cm) {
                        return;
                    }
                    this.ah();
                    this.a(false);
                    this.cp.u = true;
                    this.cm = false;
                    this.cp.d = -1;
                }
                return;
            }
            if (this.cp.a == 3) {
                if (this.cp.c == 0) {
                    this.cp.c = -1;
                }
            }
            else if (this.cp.a == 4) {
                this.cu = 1;
                this.cw = 1;
                if (this.co > (int)(fps.D * 0.4f) - 10 - 7 && this.co < (int)(fps.D * 0.4f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                    this.cp.d = 1;
                    this.cp.u = true;
                    this.cu = 5;
                    this.cw = 5;
                    this.cp.f = 5;
                    this.cp.g = 20;
                    this.bo = true;
                    this.ag(this.br);
                    return;
                }
                if (this.co > (int)(fps.D * 0.5f) - 10 - 7 && this.co < (int)(fps.D * 0.5f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                    this.cp.d = 1;
                    this.cp.u = true;
                    this.cu = 3;
                    this.cw = 3;
                    this.cp.f = 4;
                    this.cp.g = 15;
                    this.bo = true;
                    this.ag(this.br);
                    return;
                }
                if (this.co > (int)(fps.D * 0.6f) - 10 - 7 && this.co < (int)(fps.D * 0.6f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                    this.cp.d = 1;
                    this.cp.u = true;
                    this.cp.f = 3;
                    this.cp.g = 10;
                    this.cu = 2;
                    this.cw = 2;
                    this.bo = true;
                    this.ag(this.br);
                    return;
                }
                if (this.co > (int)(fps.D * 0.7f) - 10 - 7 && this.co < (int)(fps.D * 0.7f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                    this.cp.d = 1;
                    this.cp.u = true;
                    this.cp.f = 3;
                    this.cp.g = 10;
                    this.cu = 1;
                    this.cw = 1;
                    this.bo = true;
                    this.ag(this.br);
                    return;
                }
                if (this.co > fps.D - 40 && this.cn < fps.B / 5) {
                    this.ag(this.br);
                    if (!this.bo || this.bm || this.bg) {
                        this.a(0);
                        return;
                    }
                    this.c();
                }
            }
            else if (this.cp.a == 2 && this.cp.n && this.cp.q) {
                if (this.co > fps.D - 40 && this.cn < fps.B / 5) {
                    this.ag(this.br);
                    this.cp.q = false;
                    this.cp.n = false;
                    return;
                }
                if (this.co > (int)(fps.D * 0.45f) - 10 - 7 && this.co < (int)(fps.D * 0.45f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                    this.ag(this.br);
                    this.aA();
                    return;
                }
                if (this.co > (int)(fps.D * 0.55f) - 10 - 7 && this.co < (int)(fps.D * 0.55f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                    this.cp.q = false;
                    this.ag(this.br);
                }
            }
            else if ((this.cp.a == 2 && this.cp.n) || (this.cp.a == 5 && this.cp.n)) {
                if (this.co > (int)(fps.D * 0.2f) - 10 - 7 && this.co < (int)(fps.D * 0.2f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K && !this.cp.o && !this.cp.p && !this.cp.t) {
                    fps.H = !fps.H;
                    this.ag(this.br);
                    return;
                }
                if (this.co > (int)(fps.D * 0.4f) - 10 - 7 && this.co < (int)(fps.D * 0.4f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K && !this.cp.o && !this.cp.p && !this.cp.t) {
                    this.ag(this.br);
                    fps.I += 0.5f;
                    if (fps.I > 3.5f) {
                        fps.I = 1.5f;
                    }
                }
                else if (this.co > (int)(fps.D * 0.7f) - 10 - 7 && this.co < (int)(fps.D * 0.7f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K && !this.cp.o && !this.cp.p && !this.cp.t) {
                    this.ag(this.br);
                    ++fps.T;
                    if (fps.T >= 4) {
                        fps.T = 0;
                    }
                }
                else if (this.co > (int)(fps.D * 0.6f) - 10 - 7 && this.co < (int)(fps.D * 0.6f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K && !this.cp.o && !this.cp.p && !this.cp.t) {
                    this.ag(this.br);
                    ++fps.V;
                    if (fps.V > 2) {
                        if (this.bO) {
                            fps.V = 0;
                            return;
                        }
                        fps.V = 1;
                    }
                }
                else if (this.co > (int)(fps.D * 0.5f) - 10 - 7 && this.co < (int)(fps.D * 0.5f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K && !this.cp.o && !this.cp.p && !this.cp.t) {
                    this.ag(this.br);
                    ++fps.W;
                    if (fps.W > 2) {
                        fps.W = 0;
                    }
                }
                else {
                    if (this.co > (int)(fps.D * 0.3f) - 10 - 7 && this.co < (int)(fps.D * 0.3f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K && !this.cp.o && !this.cp.p && !this.cp.t) {
                        this.ag(this.br);
                        Label_2582: {
                            int x;
                            if (fps.X == 0) {
                                x = 3;
                            }
                            else if (fps.X == 3) {
                                x = 2;
                            }
                            else {
                                if (fps.X != 2) {
                                    break Label_2582;
                                }
                                x = 0;
                            }
                            fps.X = x;
                        }
                        fps.Z = true;
                        return;
                    }
                    if (this.co > (int)(fps.D * 0.8f) - 10 && this.co < (int)(fps.D * 0.8f) + 10 && this.cp.a == 2 && this.cn >= fps.J && this.cn <= fps.K) {
                        this.ag(this.br);
                        this.cp.r = true;
                        return;
                    }
                    if (this.co > (int)(fps.D * 0.9f) - 10 && this.co < (int)(fps.D * 0.9f) + 10 && this.cp.a == 2 && this.cn >= fps.J && this.cn <= fps.K) {
                        this.ag(this.br);
                        this.cp.q = true;
                        return;
                    }
                    if (this.co <= fps.D - 40 || this.cn >= fps.B / 5) {
                        return;
                    }
                    this.ag(this.br);
                    this.cp.n = false;
                    if (fps.V == 2) {
                        this.Q();
                    }
                    if (this.cp.a != 5) {
                        return;
                    }
                    if (this.cp.o) {
                        this.cp.o = false;
                    }
                    if (this.cp.p) {
                        this.cp.p = false;
                    }
                    if (!this.cp.t) {
                        return;
                    }
                    this.cp.t = false;
                }
            }
            else if (this.cp.a == 5) {
                if (this.co > (int)(fps.D * 0.3f) - 10 - 7 && this.co < (int)(fps.D * 0.3f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K && !this.cp.o && !this.cp.p && !this.cp.t) {
                    this.ag(this.br);
                    if (!this.bo || this.bm || this.bg) {
                        this.a(0);
                        return;
                    }
                    final l cp = this.cp;
                    --cp.h;
                    this.ak();
                }
                else {
                    if (this.co > (int)(fps.D * 0.4f) - 10 - 7 && this.co < (int)(fps.D * 0.4f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                        this.ag(this.br);
                        this.cp.n = true;
                        this.cp.o = true;
                        this.cp.p = false;
                        this.cp.t = false;
                        return;
                    }
                    if (this.co > (int)(fps.D * 0.5f) - 10 - 7 && this.co < (int)(fps.D * 0.5f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K && !this.cp.o && !this.cp.p && !this.cp.t) {
                        this.ag(this.br);
                        this.cp.n = true;
                        this.cp.o = false;
                        this.cp.p = false;
                        this.cp.t = false;
                        return;
                    }
                    if (this.co > (int)(fps.D * 0.6f) - 10 - 7 && this.co < (int)(fps.D * 0.6f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                        this.ag(this.br);
                        this.cp.n = true;
                        this.cp.o = false;
                        this.cp.p = true;
                        this.cp.t = false;
                        this.cp.s = "";
                        return;
                    }
                    if (this.co > (int)(fps.D * 0.7f) - 10 - 7 && this.co < (int)(fps.D * 0.7f) + 10 - 7 && this.cn >= fps.J && this.cn <= fps.K) {
                        this.ag(this.br);
                        this.cp.n = true;
                        this.cp.o = false;
                        this.cp.p = false;
                        this.cp.t = true;
                    }
                }
            }
            else {
                if (this.bi && this.cp.a == 10) {
                    try {
                        this.getAppletContext().showDocument(new URL(this.bf), "_blank");
                        return;
                    }
                    catch (Exception ex2) {
                        System.out.println(String.valueOf(ex2));
                        return;
                    }
                }
                if (this.cp.a == 10 && !this.bi && this.bl) {
                    this.ag(this.br);
                    this.cp.d = -1;
                }
            }
        }
    }
    
    public final void aA() {
        this.cp.n = false;
        if (this.bi || this.bl) {
            this.ae();
            return;
        }
        this.D();
        this.c();
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.cc = true;
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void aB() {
        fps.ce = 0;
        this.cp.d = 1;
        this.cp.m = 16;
    }
    
    public final void aC() {
        System.out.println();
    }
    
    public final void S(final int n) {
        if (n == 112) {
            System.out.println();
            this.aC();
            this.ao();
        }
        if (n == 113) {
            System.out.println();
            this.d(this.cN[this.ae.l].i, this.cN[this.ae.l].j);
        }
        if (n == 114) {
            this.q();
        }
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        final int keyCode;
        if ((keyCode = keyEvent.getKeyCode()) >= 112 && keyCode <= 115) {
            this.S(keyCode);
        }
        if (this.cp.n && !this.cp.p) {
            this.Q(keyCode);
            this.ah(keyCode);
            return;
        }
        if (this.cp.a == 5 && this.cp.p) {
            this.ai(keyCode);
            return;
        }
        if (this.cp.a != 0) {
            if (keyCode == 10) {
                if (this.bg) {
                    if (fps.S) {
                        fps.S = false;
                        return;
                    }
                    if (!fps.S) {
                        fps.S = true;
                    }
                }
            }
            else {
                if (keyCode == 37) {
                    this.cq[0] = true;
                    return;
                }
                if (keyCode == 39) {
                    this.cq[1] = true;
                    return;
                }
                if (keyCode == 40) {
                    this.cq[2] = true;
                    return;
                }
                if (keyCode == 38) {
                    this.cq[3] = true;
                    return;
                }
                if (keyCode == 32) {
                    this.cq[4] = true;
                    return;
                }
                if (keyCode == 90) {
                    this.cq[8] = true;
                    return;
                }
                if (keyCode == 33) {
                    this.cq[5] = true;
                    return;
                }
                if (keyCode == 34) {
                    this.cq[6] = true;
                    return;
                }
                if (keyCode > 48 && keyCode < 54) {
                    if (!fps.P) {
                        this.T(keyCode);
                    }
                }
                else if (keyCode == 27) {
                    this.cp.n = true;
                    this.cp.q = false;
                    this.cp.r = false;
                }
            }
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        final int keyCode;
        if ((keyCode = keyEvent.getKeyCode()) == 37) {
            this.cp.j = 0.0f;
            this.cq[0] = false;
        }
        if (keyCode == 39) {
            this.cp.k = 0.0f;
            this.cq[1] = false;
        }
        if (keyCode == 40) {
            this.cq[2] = false;
        }
        if (keyCode == 38) {
            this.cq[3] = false;
        }
        if (keyCode == 32) {
            this.cq[4] = false;
        }
        if (keyCode == 33) {
            this.cq[5] = false;
        }
        if (keyCode == 34) {
            this.cq[6] = false;
        }
        if (keyCode == 90) {
            this.cq[8] = false;
            return;
        }
        if (keyCode == 67) {
            this.cr[0] = false;
            return;
        }
        if (keyCode == 65) {
            this.cr[1] = false;
            return;
        }
        if (keyCode == 84) {
            this.cr[2] = false;
            return;
        }
        if (keyCode == 79) {
            this.cr[3] = true;
            return;
        }
        if (keyCode == 80) {
            this.cr[4] = true;
        }
    }
    
    public final void T(final int n) {
        final int ak = this.ak(n - 49);
        if (this.aB.D[ak] && this.aB.C[ak] > 0) {
            this.U(ak);
        }
    }
    
    public final void U(final int b) {
        if (b < 10 && (b != this.aB.b || (b == 0 && this.aB.b == 0))) {
            if (this.aB.L <= -1) {
                this.d(this.aB.b, b);
            }
            this.aB.b = b;
            this.am();
        }
    }
    
    public final void aD() {
        this.cL = new a[10][20];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 20; ++j) {
                this.cL[i][j] = new a();
            }
        }
        if (fps.ce == 0) {
            this.cL[0][0].a = this.a.i();
            this.cL[0][0].b = this.a(c(String.valueOf(this.bZ) + "nr-t-a-f+u+001"), this.cC);
            this.a.a(this.cL[0][0].a, this.cL[0][0].b, this.cC[0], this.cC[1]);
            this.cL[0][1].a = this.a.i();
            this.cL[0][1].b = this.a(c(String.valueOf(this.bZ) + "nr-t*a+f-u+002"), this.cC);
            this.a.a(this.cL[0][1].a, this.cL[0][1].b, this.cC[0], this.cC[1]);
            this.cL[0][2].a = this.a.i();
            this.cL[0][2].b = this.a(c(String.valueOf(this.bZ) + "nr-t+a-f+u-003"), this.cC);
            this.a.a(this.cL[0][2].a, this.cL[0][2].b, this.cC[0], this.cC[1]);
            this.cL[0][3].a = this.a.i();
            this.cL[0][3].b = this.a(c(String.valueOf(this.bZ) + "nr-t*a-f+u-004"), this.cC);
            this.a.a(this.cL[0][3].a, this.cL[0][3].b, this.cC[0], this.cC[1]);
            this.cL[0][4].a = this.a.i();
            this.cL[0][4].b = this.a(c(String.valueOf(this.bZ) + "nr-t*a*f*u*005"), this.cC);
            this.a.a(this.cL[0][4].a, this.cL[0][4].b, this.cC[0], this.cC[1]);
        }
    }
    
    public final void aE() {
        this.cH[0].a = this.a.i();
        this.cH[0].b = this.a(c(String.valueOf(this.bZ) + "nr+g*u-g"), this.cC);
        this.a.a(this.cH[0].a, this.cH[0].b, this.cC[0], this.cC[1]);
        this.cM.a = this.a.i();
        this.cM.b = this.a(c(String.valueOf(this.bZ) + "nr-g-u+s"), this.cC);
        this.a.a(this.cM.a, this.cM.b, this.cC[0], this.cC[1]);
        this.I(1);
        this.cG[0].a = this.a.i();
        this.cG[0].b = this.a(c(String.valueOf(this.bZ) + "nr+p-u*s"), this.cC);
        this.a.a(this.cG[0].a, this.cG[0].b, this.cC[0], this.cC[1]);
        this.cG[1].a = this.a.i();
        this.cG[1].b = this.a(c(String.valueOf(this.bZ) + "nr-p+u+e"), this.cC);
        this.a.a(this.cG[1].a, this.cG[1].b, this.cC[0], this.cC[1]);
        this.I(2);
        this.cG[2].a = this.a.i();
        this.cG[2].b = this.a(c(String.valueOf(this.bZ) + "nr+p-u*b"), this.cC);
        this.a.a(this.cG[2].a, this.cG[2].b, this.cC[0], this.cC[1]);
        this.cG[3].a = this.a.i();
        this.cG[3].b = this.a(c(String.valueOf(this.bZ) + "nr*p+u-st"), this.cC);
        this.a.a(this.cG[3].a, this.cG[3].b, this.cC[0], this.cC[1]);
        this.I(3);
        this.cG[4].a = this.a.i();
        this.cG[4].b = this.a(c(String.valueOf(this.bZ) + "nr-p*u=f"), this.cC);
        this.a.a(this.cG[4].a, this.cG[4].b, this.cC[0], this.cC[1]);
        this.cG[5].a = this.a.i();
        this.cG[5].b = this.a(c(String.valueOf(this.bZ) + "nr+p-u=p"), this.cC);
        this.a.a(this.cG[5].a, this.cG[5].b, this.cC[0], this.cC[1]);
        this.I(4);
        this.cG[9].a = this.a.i();
        this.cG[9].b = this.a(c(String.valueOf(this.bZ) + "nr-p+u=w"), this.cC);
        this.a.a(this.cG[9].a, this.cG[9].b, this.cC[0], this.cC[1]);
        this.cG[11].a = this.a.i();
        this.cG[11].b = this.a(c(String.valueOf(this.bZ) + "nr*p*u=t"), this.cC);
        this.a.a(this.cG[11].a, this.cG[11].b, this.cC[0], this.cC[1]);
        this.cG[6].a = this.a.i();
        this.cG[6].b = this.a(c(String.valueOf(this.bZ) + "nr+p+u*sp"), this.cC);
        this.a.a(this.cG[6].a, this.cG[6].b, this.cC[0], this.cC[1]);
        this.cG[7].a = this.a.i();
        this.cG[7].b = this.a(c(String.valueOf(this.bZ) + "nr+p-u+te"), this.cC);
        this.a.a(this.cG[7].a, this.cG[7].b, this.cC[0], this.cC[1]);
        this.I(5);
        this.cE.a = this.a.i();
        this.cE.b = this.a(c(String.valueOf(this.bZ) + "nr-w2*u=1"), this.cC);
        this.cF.a = this.a.i();
        this.cF.b = this.a(c(String.valueOf(this.bZ) + "nr+w2*u=2"), this.cC);
        this.a.a(this.cF.a, this.cF.b, this.cC[0], this.cC[1]);
        this.q[11][0] = this.cF.a;
        this.q[11][1] = this.cF.a;
        this.a.a(this.cE.a, this.cE.b, this.cC[0], this.cC[1]);
        this.q[10][0] = this.cE.a;
        this.q[10][1] = this.cE.a;
        this.cK.a = this.a.i();
        this.cK.b = this.a(c(String.valueOf(this.bZ) + "nr-t*p=u+i"), this.cC);
        this.a.a(this.cK.a, this.cK.b, this.cC[0], this.cC[1]);
        this.aF();
    }
    
    public final void aF() {
        this.dO[0] = this.a(this.cE.b, 256, 0, 50, 100, 28);
        this.dO[1] = this.a(this.cE.b, 256, 50, 100, 100, 28);
        this.dO[2] = this.a(this.cE.b, 256, 100, 150, 100, 28);
        this.dO[3] = this.a(this.cE.b, 256, 150, 200, 100, 28);
        this.dO[4] = this.a(this.cE.b, 256, 200, 250, 100, 28);
        this.dO[5] = this.a(this.cF.b, 256, 0, 50, 100, 28);
        this.dO[6] = this.a(this.cF.b, 256, 50, 100, 100, 28);
        this.dO[7] = this.a(this.cF.b, 256, 100, 150, 100, 28);
        this.dO[8] = this.a(this.cF.b, 256, 150, 200, 100, 28);
        this.dO[9] = this.a(this.cF.b, 256, 200, 250, 100, 28);
        this.dP = this.a(this.cK.b, 256, 128, 160, 1, 32);
        this.dQ = this.a(this.cK.b, 256, 160, 190, 1, 32);
        this.dR = this.a(this.cK.b, 256, 0, 32, 1, 32);
        this.dS = this.a(this.cK.b, 256, 96, 127, 1, 32);
        this.dT = this.a(this.cK.b, 256, 33, 64, 1, 32);
        this.dU = this.a(this.cK.b, 256, 65, 94, 1, 32);
    }
    
    public final Image a(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6;
        final int[] array2 = new int[(n6 = n3 - n2) * n5];
        int n7 = 0;
        int n8 = n2 + (n4 - 1) * n;
        for (int i = n4; i < n4 + n5 - 1; ++i) {
            for (int j = 0; j < n6; ++j) {
                array2[n7] = array[n8];
                ++n7;
                ++n8;
            }
            n8 += n - n3 + n2;
        }
        return this.createImage(new MemoryImageSource(n6, n5, array2, 0, n6));
    }
    
    public final void V(final int n) {
        if (n == -1) {
            this.J();
            this.aB.L = 1000;
            this.x();
            return;
        }
        this.l(n);
        this.b(n, 1);
        this.aC[n].c.L = 1000;
    }
    
    public final void W(final int n) {
        this.aC[n].c.F = 0.75f;
        this.aC[n].c.G = 0.4f;
        this.aC[n].c.O = -1;
        this.a.b(this.dx, false);
        this.c(n, 3);
    }
    
    public final void X(final int n) {
        this.aC[n].c.K = -1;
        this.a.b(this.dv, false);
        this.c(n, 0);
    }
    
    public final void Y(final int n) {
        this.aC[n].c.M = -1;
        if (this.aC[n].c.z > 100) {
            this.aC[n].c.z = 100;
        }
        this.a.b(this.dw, false);
        this.c(n, 2);
    }
    
    public final void Z(final int n) {
        a a;
        int n2;
        float n3;
        float n4;
        j j;
        if (n == -1) {
            this.J();
            this.aB.M = 1000;
            this.aB.z = 200;
            a = this.a;
            n2 = this.dw;
            n3 = this.ae.a;
            n4 = this.ae.b;
            j = this.ae;
        }
        else {
            this.l(n);
            this.b(n, 2);
            this.aC[n].c.M = 1000;
            this.aC[n].c.z = 200;
            a = this.a;
            n2 = this.dw;
            n3 = this.aC[n].a.a;
            n4 = this.aC[n].a.b;
            j = this.aC[n].a;
        }
        a.a(n2, n3, n4, j.c);
        this.a.b(this.dw, true);
    }
    
    public final void aa(final int n) {
        if (n == -1) {
            this.J();
            this.aB.O = 1000;
            this.a.a(this.dx, this.ae.a, this.ae.b, this.ae.c);
            this.aB.F = 1.125f;
            this.aB.G = 0.6f;
            fps.M = true;
            fps.O = 4;
        }
        else {
            this.l(n);
            this.b(n, 3);
            this.aC[n].c.O = 1000;
            this.a.a(this.dx, this.aC[n].a.a, this.aC[n].a.b, this.aC[n].a.c);
            this.aC[n].c.F = 1.5f;
            this.aC[n].c.G = 0.8f;
        }
        this.a.b(this.dx, true);
    }
    
    public final void ab(final int n) {
        a a;
        int n2;
        float n3;
        float n4;
        j j;
        if (n == -1) {
            this.J();
            this.aB.K = 1000;
            a = this.a;
            n2 = this.dv;
            n3 = this.ae.a;
            n4 = this.ae.b;
            j = this.ae;
        }
        else {
            this.l(n);
            this.b(n, 0);
            this.aC[n].c.K = 1000;
            a = this.a;
            n2 = this.dv;
            n3 = this.aC[n].a.a;
            n4 = this.aC[n].a.b;
            j = this.aC[n].a;
        }
        a.a(n2, n3, n4, j.c);
        this.a.b(this.dv, true);
    }
    
    public final void ac(final int n) {
        if (n == -1) {
            this.aB.s = 70;
            this.a(this.aB.l, 50, -1, 0);
            return;
        }
        this.aC[n].c.s = 70;
        this.a(this.aC[n].c.l, 20, n, 0);
    }
    
    public final void ad(final int n) {
        if (this.cp.h == 0) {
            if (n == 0) {
                this.aC[n].c.h = "Major Havoc";
                return;
            }
            if (n == 1) {
                this.aC[n].c.h = "Gloom Marine";
                return;
            }
            if (n == 2) {
                this.aC[n].c.h = "Turbo Headcase";
                return;
            }
            if (n == 3) {
                this.aC[n].c.h = "Nuke Dukem";
                return;
            }
            if (n == 4) {
                this.aC[n].c.h = "Brutal Deluxe";
            }
        }
        else if (this.cp.h == 1) {
            if (n == 0) {
                this.aC[n].c.h = "Corporal Punishment";
                return;
            }
            if (n == 1) {
                this.aC[n].c.h = "Jack Hacket";
                return;
            }
            if (n == 2) {
                this.aC[n].c.h = "Violent Desire";
                return;
            }
            if (n == 3) {
                this.aC[n].c.h = "Lunatic Calm";
                return;
            }
            if (n == 4) {
                this.aC[n].c.h = "Lo Wang";
            }
        }
        else if (this.cp.h == 3) {
            if (n == 0) {
                this.aC[n].c.h = "Guybrush";
                return;
            }
            if (n == 1) {
                this.aC[n].c.h = "Renegade Nutjob";
                return;
            }
            if (n == 2) {
                this.aC[n].c.h = "Yuck Foo";
                return;
            }
            if (n == 3) {
                this.aC[n].c.h = "Shotgun Negociator";
                return;
            }
            if (n == 4) {
                this.aC[n].c.h = "Captain Captured";
            }
        }
        else if (this.cp.h == 4) {
            if (n == 0) {
                this.aC[n].c.h = "Enraged Pacifist";
                return;
            }
            if (n == 1) {
                this.aC[n].c.h = "Strife";
                return;
            }
            if (n == 2) {
                this.aC[n].c.h = "Drain Bamage";
                return;
            }
            if (n == 3) {
                this.aC[n].c.h = "Shodan";
                return;
            }
            if (n == 4) {
                this.aC[n].c.h = "Raw Fury";
            }
        }
        else {
            if (n == 0) {
                this.aC[n].c.h = "Damocles";
                return;
            }
            if (n == 1) {
                this.aC[n].c.h = "Blanka";
                return;
            }
            if (n == 2) {
                this.aC[n].c.h = "Scorpian Zero";
                return;
            }
            if (n == 3) {
                this.aC[n].c.h = "Xerxes";
                return;
            }
            if (n == 4) {
                this.aC[n].c.h = "Steel Justice";
            }
        }
    }
    
    public final void a(final boolean b) {
        final int n = (int)(Math.random() * this.cO.a);
        this.a(n, b);
        this.t((int)(Math.random() * this.cO.a), n);
    }
    
    public final int s(final int n, final int n2) {
        int n3 = n;
        boolean b = false;
        Label_0044: {
            break Label_0044;
            boolean b2;
            do {
                b2 = (++n3 != n2);
                if (n3 >= this.cO.a) {
                    n3 = -1;
                    b = false;
                }
                else {
                    if (n3 == n2) {
                        continue;
                    }
                    b = true;
                }
                b2 = b;
            } while (!b2);
        }
        return n3;
    }
    
    public final void t(final int n, final int n2) {
        int s = n;
        for (int i = 0; i < fps.F; ++i) {
            s = this.s(s, n2);
            this.ad(i);
            this.aC[i].a.b = 0.0f;
            this.aC[i].a.a = this.cO.c[s].a;
            this.aC[i].a.c = this.cO.c[s].c;
            this.aC[i].a.e = this.cO.c[s].f - 180;
            if (this.aC[i].a.e < 0.0f) {
                final j a = this.aC[i].a;
                a.e += 360.0f;
            }
            this.aC[i].a.l = this.a(this.aC[i].a.a, this.aC[i].a.c);
            this.a.a(this.aC[i].a.w, true);
            this.aC[i].c = a(this.aC[i].c);
            this.aC[i].h = -1;
            this.aC[i].a.B = 0;
            this.a.c(this.aC[i].a.w, this.f[this.aC[i].a.B]);
            this.aC[i].c.E = false;
            this.o(i);
            this.q(i);
            this.ac(i);
            this.aC[i].g = 0;
        }
        this.G(0);
    }
    
    public final void a(final boolean b, final int n) {
        this.aC[n].a.b = 0.0f;
        if (b) {
            this.aC[n].a.a = this.cO.c[0].a + n * 2;
            this.aC[n].a.c = this.cO.c[0].c;
            this.aC[n].a.e = this.cO.c[0].f;
        }
        else {
            this.aC[n].a = this.a(this.aC[n].a);
        }
        this.aC[n].a.l = this.a(this.aC[n].a.a, this.aC[n].a.c);
        this.a.a(this.aC[n].a.w, true);
        this.aC[n].c = a(this.aC[n].c);
        this.aC[n].a.B = 0;
        this.a.c(this.aC[n].a.w, this.f[this.aC[n].a.B]);
        this.aC[n].c.E = false;
        this.o(n);
        this.q(n);
        this.e(n);
        this.ac(n);
        this.aC[n].c.F = 0.75f;
        this.aC[n].c.G = 0.4f;
    }
    
    public final void ae(final int n) {
        this.aC[n].a = new j();
        this.aC[n].a.v = this.a.e();
    }
    
    public final void a(final int n, final boolean b, final DataInputStream dataInputStream, final int n2) {
        try {
            dataInputStream.readLine();
            this.f[n] = new float[n2 * 3];
            int i = 0;
            while (i < n2 * 3) {
                final StringTokenizer stringTokenizer = new StringTokenizer(dataInputStream.readLine());
                if (b) {
                    stringTokenizer.nextToken();
                }
                for (int j = 0; j < 3; ++j) {
                    this.f[n][i] = Integer.parseInt(stringTokenizer.nextToken()) / 12.0f;
                    if (j == 1) {
                        final float[] array = this.f[n];
                        final int n3 = i;
                        array[n3] += 2.0f;
                    }
                    ++i;
                }
            }
        }
        catch (IOException ex) {
            System.out.println("!");
        }
    }
    
    public final void b(final DataInputStream dataInputStream, final int n) {
        try {
            this.g = new int[n * 4];
            dataInputStream.readLine();
            int i = 0;
            while (i < n * 4) {
                final StringTokenizer stringTokenizer = new StringTokenizer(dataInputStream.readLine());
                for (int j = 0; j < 4; ++j) {
                    int[] array;
                    int n2;
                    int int1;
                    if (j == 3) {
                        array = this.g;
                        n2 = i;
                        int1 = -1;
                    }
                    else {
                        array = this.g;
                        n2 = i;
                        int1 = Integer.parseInt(stringTokenizer.nextToken());
                    }
                    array[n2] = int1;
                    ++i;
                }
            }
        }
        catch (IOException ex) {
            System.out.println("!");
        }
    }
    
    public final void a(final boolean b, final DataInputStream dataInputStream) {
        int n = 0;
        try {
            dataInputStream.readLine();
            final int int1 = Integer.parseInt(new StringTokenizer(dataInputStream.readLine()).nextToken());
            this.i = new float[int1 * 2];
            for (int i = 0; i < int1; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(dataInputStream.readLine());
                if (b) {
                    Integer.parseInt(stringTokenizer.nextToken());
                }
                this.i[n] = Float.valueOf(stringTokenizer.nextToken());
                this.i[n + 1] = Float.valueOf(stringTokenizer.nextToken());
                n += 2;
            }
        }
        catch (IOException ex) {
            System.out.println("!");
        }
    }
    
    public final void aG() {
        int n = 0;
        for (int i = 0; i < this.i.length / 2 - 1; ++i) {
            final float[] j = this.i;
            final int n2 = n;
            j[n2] *= 0.33333334f;
            final float[] k = this.i;
            final int n3 = n + 1;
            k[n3] *= 0.5f;
            n += 2;
        }
    }
    
    public final void b(final float n, final float n2) {
        int n3 = 0;
        for (int i = 0; i < this.i.length / 2 - 1; ++i) {
            final float[] j = this.i;
            final int n4 = n3;
            j[n4] += n;
            final float[] k = this.i;
            final int n5 = n3 + 1;
            k[n5] += n2;
            n3 += 2;
        }
    }
    
    public final void b(final boolean b, final DataInputStream dataInputStream) {
        int n = 0;
        try {
            dataInputStream.readLine();
            final int int1 = Integer.parseInt(new StringTokenizer(dataInputStream.readLine()).nextToken());
            this.h = new int[int1 * 4];
            for (int i = 0; i < int1; ++i) {
                final StringTokenizer stringTokenizer = new StringTokenizer(dataInputStream.readLine());
                if (b) {
                    Integer.parseInt(stringTokenizer.nextToken());
                }
                this.h[n] = Integer.parseInt(stringTokenizer.nextToken());
                this.h[n + 1] = Integer.parseInt(stringTokenizer.nextToken());
                this.h[n + 2] = Integer.parseInt(stringTokenizer.nextToken());
                this.h[n + 3] = -1;
                n += 4;
            }
        }
        catch (IOException ex) {
            System.out.println("!");
        }
    }
    
    public final void aH() {
        new String();
        fps.aa = 19;
        fps.ab = 8;
        fps.ac = 17;
        final String s = "nr/c/u/q";
        this.f = new float[39][10];
        URL url;
        try {
            url = new URL(this.bZ, s);
        }
        catch (MalformedURLException ex) {
            System.out.println("!");
            return;
        }
        InputStream openStream;
        try {
            openStream = url.openStream();
        }
        catch (IOException ex2) {
            System.out.println(" " + url);
            return;
        }
        final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openStream));
        try {
            final StringTokenizer stringTokenizer;
            final String nextToken = (stringTokenizer = new StringTokenizer(dataInputStream.readLine())).nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(nextToken);
            this.b(dataInputStream, Integer.parseInt(nextToken2));
            this.a(false, dataInputStream);
            this.aG();
            this.b(true, dataInputStream);
            for (int i = 0; i < fps.aa; ++i) {
                this.a(i, false, dataInputStream, int1);
            }
            for (int j = 0; j < 1; ++j) {
                this.cI[j].a = this.a.i();
            }
            openStream.close();
            dataInputStream.close();
        }
        catch (IOException ex3) {
            System.out.println("!");
        }
    }
    
    public final void aI() {
        for (int i = 0; i < 1; ++i) {
            this.a.a(this.cI[i].a, this.cI[i].b, this.cC[0], this.cC[1]);
        }
        a3dAPI.c.a(this.f[0], a3dAPI.c.a(this.g, true), 0.0f);
        this.aE = new j();
        this.aE.v = this.a.e();
        this.aE.w = this.a.a(this.f[18], a3dAPI.c.a(this.g, false), null, null, this.i, a3dAPI.c.a(this.h, false), null, null, null, null);
        this.a.b(this.aE.w, this.cI[0].a);
        for (int j = 0; j < 4; ++j) {
            this.aD[j] = new j();
            this.aD[j].v = this.a.e();
            this.aD[j].w = this.a.a(this.f[0], a3dAPI.c.a(this.g, false), null, null, null, null, null, null, null, null);
            a a;
            int n;
            int n2;
            if (j == 0) {
                a = this.a;
                n = this.aD[j].w;
                n2 = this.au;
            }
            else if (j == 1) {
                a = this.a;
                n = this.aD[j].w;
                n2 = this.av;
            }
            else if (j == 2) {
                a = this.a;
                n = this.aD[j].w;
                n2 = this.ax;
            }
            else {
                if (j != 3) {
                    continue;
                }
                a = this.a;
                n = this.aD[j].w;
                n2 = this.aw;
            }
            a.d(n, n2);
        }
        for (int k = 0; k < 5; ++k) {
            this.aC[k].a = new j();
            this.aC[k].a.v = this.a.e();
            Label_0425: {
                fps fps;
                float n3;
                float n4;
                if (k == 0 || k == 1 || k == 3 || k == 4) {
                    fps = this;
                    n3 = 0.33333334f;
                    n4 = 0.0f;
                }
                else {
                    if (k != 2) {
                        break Label_0425;
                    }
                    fps = this;
                    n3 = -0.6666667f;
                    n4 = 0.5f;
                }
                fps.b(n3, n4);
            }
            this.aC[k].a.w = this.a.a(this.f[0], a3dAPI.c.a(this.g, false), null, null, this.i, a3dAPI.c.a(this.h, false), null, null, null, null);
            this.a.b(this.aC[k].a.w, this.cI[0].a);
            this.aC[k].a.B = 0;
            this.aC[k].a.C = 3;
        }
    }
    
    public final void a(final DataInputStream dataInputStream, final boolean b) {
        if (!b) {
            this.b(dataInputStream, false);
            return;
        }
        this.d(dataInputStream, false);
    }
    
    public final void b(final DataInputStream dataInputStream, final boolean b) {
        try {
            final StringTokenizer stringTokenizer;
            final String nextToken = (stringTokenizer = new StringTokenizer(dataInputStream.readLine())).nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(nextToken);
            final int int2 = Integer.parseInt(nextToken2);
            dataInputStream.readLine();
            this.e = new float[int1 * 3];
            int i = 0;
            while (i < int1 * 3) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(dataInputStream.readLine());
                for (int j = 0; j < 3; ++j) {
                    this.e[i] = Integer.parseInt(stringTokenizer2.nextToken());
                    ++i;
                }
                if (b) {
                    System.out.println();
                }
            }
            if (b) {
                System.out.println();
            }
            this.j = new int[int2 * 5];
            dataInputStream.readLine();
            int k = 0;
            while (k < int2 * 5) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(dataInputStream.readLine());
                for (int l = 0; l < 5; ++l) {
                    this.j[k] = Integer.parseInt(stringTokenizer3.nextToken());
                    ++k;
                }
                if (b) {
                    System.out.println();
                }
            }
            if (b) {
                System.out.println();
            }
        }
        catch (IOException ex) {
            System.out.println("!");
        }
    }
    
    public final int c(final DataInputStream dataInputStream, final boolean b) {
        int int1;
        try {
            final StringTokenizer stringTokenizer;
            final String nextToken = (stringTokenizer = new StringTokenizer(dataInputStream.readLine())).nextToken();
            stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final String nextToken3 = stringTokenizer.nextToken();
            dataInputStream.readLine();
            int1 = Integer.parseInt(nextToken);
            if (nextToken2.equals("v2")) {
                fps.x = 2;
                fps.F = Integer.parseInt(nextToken3);
                if (fps.F > 5) {
                    fps.F = 5;
                }
            }
            else {
                fps.x = 1;
            }
            for (int i = 0; i < int1; ++i) {
                String s = dataInputStream.readLine();
                if (fps.bj) {
                    s = "c/" + s;
                }
                if (b) {
                    System.out.println(" .");
                }
                this.cD[i].a = this.a.i();
                this.cD[i].b = this.a(c(String.valueOf(this.bZ) + s), this.cC);
                this.a.a(this.cD[i].a, this.cD[i].b, this.cC[0], this.cC[1]);
            }
        }
        catch (IOException ex) {
            System.out.println("!");
            return -1;
        }
        return int1;
    }
    
    public final void a(final int n, final DataInputStream dataInputStream) {
        try {
            final StringTokenizer stringTokenizer;
            final String nextToken = (stringTokenizer = new StringTokenizer(dataInputStream.readLine())).nextToken();
            stringTokenizer.nextToken();
            Integer.parseInt(nextToken);
            final StringTokenizer stringTokenizer2;
            final String nextToken2 = (stringTokenizer2 = new StringTokenizer(dataInputStream.readLine())).nextToken();
            stringTokenizer2.nextToken();
            this.cN[n].c = Integer.parseInt(nextToken2);
            final StringTokenizer stringTokenizer3;
            final String nextToken3 = (stringTokenizer3 = new StringTokenizer(dataInputStream.readLine())).nextToken();
            stringTokenizer3.nextToken();
            this.cN[n].d = Integer.parseInt(nextToken3);
            final StringTokenizer stringTokenizer4;
            final String nextToken4 = (stringTokenizer4 = new StringTokenizer(dataInputStream.readLine())).nextToken();
            stringTokenizer4.nextToken();
            this.cN[n].A = Integer.parseInt(nextToken4);
            final StringTokenizer stringTokenizer5;
            final String nextToken5 = (stringTokenizer5 = new StringTokenizer(dataInputStream.readLine())).nextToken();
            stringTokenizer5.nextToken();
            this.cN[n].e = Integer.parseInt(nextToken5);
            dataInputStream.readLine();
            final StringTokenizer stringTokenizer6;
            final String nextToken6 = (stringTokenizer6 = new StringTokenizer(dataInputStream.readLine())).nextToken();
            final String nextToken7 = stringTokenizer6.nextToken();
            final String nextToken8 = stringTokenizer6.nextToken();
            final String nextToken9 = stringTokenizer6.nextToken();
            this.cN[n].v.a = Integer.parseInt(nextToken6);
            this.cN[n].v.b = Integer.parseInt(nextToken7);
            this.cN[n].v.c = Integer.parseInt(nextToken8);
            this.cN[n].v.d = Integer.parseInt(nextToken9);
            this.cN[n].w.a = this.cN[n].v.a;
            this.cN[n].w.b = this.cN[n].v.b;
            this.cN[n].w.c = this.cN[n].v.c;
            this.cN[n].w.d = this.cN[n].v.d;
            this.cN[n].a = (int)((this.cN[n].v.a + this.cN[n].v.c) / 2.0f);
            this.cN[n].b = (int)((this.cN[n].v.b + this.cN[n].v.d) / 2.0f);
            for (int i = 0; i < this.cN[n].d; ++i) {
                final StringTokenizer stringTokenizer7;
                final String nextToken10 = (stringTokenizer7 = new StringTokenizer(dataInputStream.readLine())).nextToken();
                final String nextToken11 = stringTokenizer7.nextToken();
                final String nextToken12 = stringTokenizer7.nextToken();
                final String nextToken13 = stringTokenizer7.nextToken();
                final String nextToken14 = stringTokenizer7.nextToken();
                this.cN[n].x[i].a = Integer.parseInt(nextToken10);
                this.cN[n].x[i].b = Integer.parseInt(nextToken11);
                this.cN[n].x[i].c = Integer.parseInt(nextToken12);
                this.cN[n].x[i].d = Integer.parseInt(nextToken13);
                this.cN[n].x[i].e = Integer.parseInt(nextToken14);
                this.cN[n].y[i].a = this.cN[n].x[i].a;
                this.cN[n].y[i].b = this.cN[n].x[i].b;
                this.cN[n].y[i].c = this.cN[n].x[i].c;
                this.cN[n].y[i].d = this.cN[n].x[i].d;
            }
            dataInputStream.readLine();
            final StringTokenizer stringTokenizer8;
            this.cN[n].i = Integer.parseInt((stringTokenizer8 = new StringTokenizer(dataInputStream.readLine())).nextToken());
            for (int j = 0; j < this.cN[n].i; ++j) {
                new String();
                this.cN[n].j[j] = Integer.parseInt(stringTokenizer8.nextToken());
            }
            if (fps.x == 2) {
                final StringTokenizer stringTokenizer9;
                this.cN[n].p = Integer.parseInt((stringTokenizer9 = new StringTokenizer(dataInputStream.readLine())).nextToken());
                if (this.cN[n].p > 0) {
                    for (int k = 0; k < this.cN[n].p; ++k) {
                        new String();
                        this.cN[n].r[k].a = Integer.parseInt(stringTokenizer9.nextToken());
                        this.cN[n].r[k].b = Integer.parseInt(stringTokenizer9.nextToken());
                        this.cN[n].r[k].d = Integer.parseInt(stringTokenizer9.nextToken());
                        this.cN[n].r[k].e = Integer.parseInt(stringTokenizer9.nextToken());
                    }
                }
                final StringTokenizer stringTokenizer10;
                this.cN[n].q = Integer.parseInt((stringTokenizer10 = new StringTokenizer(dataInputStream.readLine())).nextToken());
                if (this.cN[n].q > 0) {
                    for (int l = 0; l < this.cN[n].q; ++l) {
                        new String();
                        this.cN[n].s[l].a = Integer.parseInt(stringTokenizer10.nextToken());
                        this.cN[n].s[l].b = Integer.parseInt(stringTokenizer10.nextToken());
                        this.cN[n].s[l].d = Integer.parseInt(stringTokenizer10.nextToken());
                        this.cN[n].s[l].e = Integer.parseInt(stringTokenizer10.nextToken());
                    }
                }
            }
            else {
                this.cN[n].p = 0;
                this.cN[n].q = 0;
            }
            dataInputStream.readLine();
            dataInputStream.readLine();
            for (int n2 = 0; n2 < this.cN[n].c; ++n2) {
                final StringTokenizer stringTokenizer11;
                final String nextToken15 = (stringTokenizer11 = new StringTokenizer(dataInputStream.readLine())).nextToken();
                final String nextToken16 = stringTokenizer11.nextToken();
                final String nextToken17 = stringTokenizer11.nextToken();
                final String nextToken18 = stringTokenizer11.nextToken();
                final String nextToken19 = stringTokenizer11.nextToken();
                final String nextToken20 = stringTokenizer11.nextToken();
                this.cN[n].h[n2].a = Integer.parseInt(nextToken15);
                this.cN[n].h[n2].b = Integer.parseInt(nextToken16);
                this.cN[n].h[n2].c = Integer.parseInt(nextToken17);
                this.cN[n].h[n2].e = Integer.parseInt(nextToken18);
                this.cN[n].h[n2].n = Integer.parseInt(nextToken19);
                this.cN[n].h[n2].o = Integer.parseInt(nextToken20);
                this.cN[n].h[n2].v = this.a.e();
                this.cN[n].h[n2].u = true;
            }
            if (this.cN[n].A > 0) {
                dataInputStream.readLine();
                dataInputStream.readLine();
                for (int n3 = 0; n3 < this.cN[n].A; ++n3) {
                    final StringTokenizer stringTokenizer12;
                    final String nextToken21 = (stringTokenizer12 = new StringTokenizer(dataInputStream.readLine())).nextToken();
                    final String nextToken22 = stringTokenizer12.nextToken();
                    final String nextToken23 = stringTokenizer12.nextToken();
                    final String nextToken24 = stringTokenizer12.nextToken();
                    final String nextToken25 = stringTokenizer12.nextToken();
                    final String nextToken26 = stringTokenizer12.nextToken();
                    final String nextToken27 = stringTokenizer12.nextToken();
                    final String nextToken28 = stringTokenizer12.nextToken();
                    final String nextToken29 = stringTokenizer12.nextToken();
                    this.cN[n].B[n3].b = Integer.parseInt(nextToken21);
                    this.cN[n].B[n3].c = Integer.parseInt(nextToken22);
                    this.cN[n].B[n3].d = Integer.parseInt(nextToken23);
                    this.cN[n].B[n3].g = Integer.parseInt(nextToken24);
                    this.cN[n].B[n3].h = Integer.parseInt(nextToken25);
                    this.cN[n].B[n3].i = Integer.parseInt(nextToken26);
                    this.cN[n].B[n3].e = Integer.parseInt(nextToken27);
                    this.cN[n].B[n3].f = Integer.parseInt(nextToken28);
                    this.cN[n].B[n3].k = Integer.parseInt(nextToken29);
                    this.cN[n].B[n3].a = this.dt;
                    ++this.dt;
                }
            }
            if (this.cN[n].e > 0) {
                dataInputStream.readLine();
                dataInputStream.readLine();
                for (int n4 = 0; n4 < this.cN[n].e; ++n4) {
                    final StringTokenizer stringTokenizer13;
                    final String nextToken30 = (stringTokenizer13 = new StringTokenizer(dataInputStream.readLine())).nextToken();
                    final String nextToken31 = stringTokenizer13.nextToken();
                    final String nextToken32 = stringTokenizer13.nextToken();
                    final String nextToken33 = stringTokenizer13.nextToken();
                    this.cN[n].z[n4].a = Integer.parseInt(nextToken30) - 0.5f;
                    this.cN[n].z[n4].b = Integer.parseInt(nextToken31) - 0.5f;
                    this.cN[n].z[n4].c = Integer.parseInt(nextToken32) + 0.5f;
                    this.cN[n].z[n4].d = Integer.parseInt(nextToken33) + 0.5f;
                }
            }
            this.cN[n].n = 0;
            this.cN[n].f = true;
        }
        catch (IOException ex) {
            System.out.println(" !");
        }
    }
    
    public final void d(final DataInputStream dataInputStream, final boolean b) {
        try {
            final StringTokenizer stringTokenizer;
            final String nextToken = (stringTokenizer = new StringTokenizer(dataInputStream.readLine())).nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(nextToken);
            final int int2 = Integer.parseInt(nextToken2);
            dataInputStream.readLine();
            this.e = new float[int1 * 3];
            int i = 0;
            while (i < int1 * 3) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(dataInputStream.readLine());
                for (int j = 0; j < 3; ++j) {
                    this.e[i] = Integer.parseInt(stringTokenizer2.nextToken()) / 100.0f;
                    ++i;
                }
                if (b) {
                    System.out.println();
                }
            }
            if (b) {
                System.out.println();
            }
            this.j = new int[int2 * 5];
            dataInputStream.readLine();
            int k = 0;
            while (k < int2 * 5) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(dataInputStream.readLine());
                for (int l = 0; l < 5; ++l) {
                    this.j[k] = Integer.parseInt(stringTokenizer3.nextToken());
                    ++k;
                }
                if (b) {
                    System.out.println();
                }
            }
        }
        catch (IOException ex) {
            System.out.println("!");
        }
    }
    
    public final void aJ() {
        this.dW = new AudioClip[20];
        this.dX = new AudioClip[15];
        this.dW[0] = this.getAudioClip(this.bZ, "injar/sounds/used/rifle.au");
        this.dW[1] = this.getAudioClip(this.bZ, "injar/sounds/used/shotgun.au");
        this.dW[2] = this.getAudioClip(this.bZ, "injar/sounds/used/rocket.au");
        this.dW[3] = this.getAudioClip(this.bZ, "injar/sounds/used/smg2.au");
        this.dW[4] = this.getAudioClip(this.bZ, "injar/sounds/used/playerpistol.au");
        this.dW[5] = this.getAudioClip(this.bZ, "injar/sounds/used/zeus.au");
        this.dW[7] = this.getAudioClip(this.bZ, "injar/sounds/used/bfg.au");
        this.dW[8] = this.getAudioClip(this.bZ, "injar/sounds/used/plasmax3.au");
        this.dW[9] = this.getAudioClip(this.bZ, "injar/sounds/used/bullet-impact.au");
        (this.dW[6] = this.getAudioClip(this.bZ, "injar/sounds/used/explosion.au")).play();
        this.dW[6].stop();
        this.dV = this.getAudioClip(this.bZ, "injar/sounds/used/bl.au");
        this.dX[0] = this.getAudioClip(this.bZ, "injar/sounds/used/player_scream5.au");
        this.dX[1] = this.getAudioClip(this.bZ, "injar/sounds/used/bot_scream3.au");
        this.dX[2] = this.getAudioClip(this.bZ, "injar/sounds/used/breath.au");
        this.dX[4] = this.getAudioClip(this.bZ, "injar/sounds/used/ambient.au");
        this.dX[5] = this.getAudioClip(this.bZ, "injar/sounds/used/collect.au");
        this.dX[7] = this.getAudioClip(this.bZ, "injar/sounds/used/gib3.au");
        this.dX[8] = this.getAudioClip(this.bZ, "injar/sounds/used/step1.au");
        this.dX[9] = this.getAudioClip(this.bZ, "injar/sounds/used/step2.au");
        this.dX[10] = this.getAudioClip(this.bZ, "injar/sounds/used/bot_injured2.au");
        this.dX[11] = this.getAudioClip(this.bZ, "injar/sounds/used/spawn.au");
        this.dX[12] = this.getAudioClip(this.bZ, "injar/sounds/used/player_frag.au");
        this.dX[4].play();
        this.dX[4].stop();
        this.dX[5].play();
        this.dX[5].stop();
        this.dX[8].play();
        this.dX[8].stop();
        this.dX[9].play();
        this.dX[9].stop();
        this.dX[10].play();
        this.dX[10].stop();
        this.dX[11].play();
        this.dX[11].stop();
        this.dV.loop();
    }
    
    public final void af(final int n) {
        if (n >= 0 && fps.H) {
            this.dW[n].play();
        }
    }
    
    public final void ag(final int n) {
        if (n >= 0 && fps.H) {
            this.dX[n].play();
        }
    }
    
    public static int a(final float n, final float n2, final float n3, final float n4) {
        return (int)(Math.atan2(n - n3, n2 - n4) * 57.3248);
    }
    
    public static double b(final float n, final float n2, final float n3, final float n4) {
        final float n5 = n4 - n2;
        final float n6 = n3 - n;
        return Math.sqrt(n5 * n5 + n6 * n6);
    }
    
    public final URL d(final String s) {
        URL url;
        try {
            url = new URL(this.getCodeBase(), s);
        }
        catch (MalformedURLException ex) {
            System.out.println("!");
            return null;
        }
        return url;
    }
    
    public final void r(final Graphics graphics) {
        graphics.setFont(this.cU);
        graphics.setColor(Color.white);
        if (this.bg) {
            graphics.drawString("FPS : " + this.dl + "  f-time=" + this.dq, 10, 90);
            graphics.drawString("p camera : " + (int)this.ae.a + ", " + this.ae.b + ", " + (int)this.ae.c + " : " + (int)this.ae.e + " deg", 10, 100);
            graphics.drawString("p room : " + this.ae.l, 10, 110);
            graphics.drawString("diff : " + this.cu, 10, 120);
            if (fps.S) {
                graphics.drawString("game paused", 10, 130);
            }
        }
        else if (this.bh) {
            graphics.drawString("FPS : " + this.dl * 2L, 10, 90);
        }
    }
    
    public static void a(final Graphics graphics, final Font font, final String s, final int n, final int n2) {
        graphics.setFont(font);
        graphics.drawString(s, n - graphics.getFontMetrics().stringWidth(s), n2);
    }
    
    public static void a(final Graphics graphics, final String s, final int n) {
        graphics.drawString(s, fps.B / 2 - graphics.getFontMetrics().stringWidth(s) / 2, n);
    }
    
    public static void b(final Graphics graphics, final Font font, final String s, final int n, final int n2) {
        graphics.setFont(font);
        graphics.drawString(s, fps.B / 2 - graphics.getFontMetrics().stringWidth(s) / 2 + n2, n);
    }
    
    public static void a(final Graphics graphics, final Font font, final String s, final int n, final int n2, final Color color) {
        graphics.setFont(font);
        final int n3 = fps.B / 2 - graphics.getFontMetrics().stringWidth(s) / 2;
        graphics.setColor(Color.black);
        graphics.drawString(s, n3 + n2 - 1, n - 1);
        graphics.setColor(color);
        graphics.drawString(s, n3 + n2, n);
    }
    
    public static void a(final Graphics graphics, final Font font, final String s, final int n, final int n2, final int n3) {
        graphics.setFont(font);
        graphics.drawString(s, fps.B / 2 - graphics.getFontMetrics().stringWidth(s) / 2 + n2, n + n3);
    }
    
    public static void b(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.drawString(s, n - graphics.getFontMetrics().stringWidth(s) / 2, n2);
    }
    
    public static void b(final Graphics graphics, final String s, final int n, final int n2, final Color color) {
        final int n3 = n - graphics.getFontMetrics().stringWidth(s) / 2;
        graphics.setColor(Color.black);
        graphics.drawString(s, n3 - 1, n2 - 1);
        graphics.setColor(color);
        graphics.drawString(s, n3, n2);
    }
    
    public final void s(final Graphics graphics) {
        float n = 1.0f;
        int n2 = 10;
        if (this.bO) {
            n = 1.5f;
            n2 = 20;
            graphics.drawImage(this.aK, (int)(fps.B / 2 - this.aK.getWidth(this) * n / 2.0f), 20, (int)(this.aK.getWidth(this) * n), (int)(this.aK.getHeight(this) * n), this);
        }
        else {
            graphics.drawImage(this.aK, fps.B / 2 - this.aK.getWidth(this) / 2, 10, this);
        }
        if (this.cp.a == 5 || this.bO) {
            this.a(graphics, n, n2);
        }
    }
    
    public final void a(final Graphics graphics, final float n, final int n2) {
        graphics.setFont(this.db);
        graphics.setColor(Color.lightGray);
        a(graphics, "by Ben Librojo", (int)(fps.B / 2 + this.aK.getWidth(this) * n / 2.0f), (int)(n2 + this.aK.getHeight(this) * n + 15.0f));
        a(graphics, this.db, "Copyright © 2006 Ben Librojo. All Rights Reserved.", fps.D - 10, 0, Color.lightGray);
    }
    
    public final void t(final Graphics graphics) {
        graphics.drawImage(this.aR, 0, 0, fps.B, fps.D, this);
        this.s(graphics);
        graphics.setColor(Color.green);
        graphics.setFont(this.cS);
        fps fps;
        Graphics graphics2;
        float n;
        int n2;
        String s;
        if (this.bo) {
            fps = this;
            graphics2 = graphics;
            n = 0.3f;
            n2 = 10;
            s = "Start Game";
        }
        else {
            fps = this;
            graphics2 = graphics;
            n = 0.3f;
            n2 = 10;
            s = "Start New Game";
        }
        fps.a(graphics2, n, n2, s, 0);
        this.a(graphics, 0.4f, 10, "Instructions", 0);
        this.a(graphics, 0.5f, 10, "Options", 0);
        this.a(graphics, 0.6f, 10, "Enter Level Password", 0);
        this.a(graphics, 0.7f, 10, "Advertise Here", 0);
    }
    
    public final void u(final Graphics graphics) {
        this.a(graphics, "Enter Level Password");
        fps fps;
        Graphics graphics2;
        int n;
        if (this.bO) {
            fps = this;
            graphics2 = graphics;
            n = 70;
        }
        else {
            fps = this;
            graphics2 = graphics;
            n = 40;
        }
        fps.e(graphics2, n, 10, 1);
        graphics.setFont(this.cQ);
        graphics.setColor(Color.black);
        a(graphics, "Please enter your level-unlock password now", (int)(0.45 * fps.D) - 1);
        a(graphics, "(nothing will appear on screen as you type)", (int)(0.5 * fps.D) - 1);
        graphics.setColor(Color.white);
        a(graphics, "Please enter your level-unlock password now", (int)(0.45 * fps.D));
        a(graphics, "(nothing will appear on screen as you type)", (int)(0.5 * fps.D));
        if (this.cp.s != "") {
            graphics.setFont(this.cQ);
            graphics.setColor(Color.black);
            a(graphics, this.cp.s, (int)(0.6 * fps.D) - 1);
            graphics.setColor(Color.blue);
            a(graphics, this.cp.s, (int)(0.6 * fps.D));
        }
    }
    
    public final void v(final Graphics graphics) {
        this.a(graphics, "Advertise Here");
        final Color white = Color.white;
        a(graphics, this.cR, "We offer in-game advertising opportunites for companies and websites", (int)(fps.D * 0.2f), 0, white);
        a(graphics, this.cR, "wishing to raise their brand awareness while re-enforcing", (int)(fps.D * 0.25f), 0, white);
        a(graphics, this.cR, "it with a positive user experience.", (int)(fps.D * 0.3f), 0, white);
        a(graphics, this.cR, "For a low-cost monthly fee your advertisement will be displayed in :", (int)(fps.D * 0.4f), 0, white);
        a(graphics, this.cR, "1) the game level loading screen", (int)(fps.D * 0.45f), 0, white);
        a(graphics, this.cR, "2) a game-over splash-screen at the conclusion of each game", (int)(fps.D * 0.5f), 0, white);
        a(graphics, this.cR, "3) as a clickable text link directly below the game window.", (int)(fps.D * 0.55f), 0, white);
        a(graphics, this.cR, "Contact us now at", (int)(fps.D * 0.65f), 0, white);
        a(graphics, this.cR, "advertise@JavaGamePlay.com", (int)(fps.D * 0.7f), 0, white);
        a(graphics, this.cR, "to discuss rates, ad-creative styles and sizes, and/or to see a demo.", (int)(fps.D * 0.75f), 0, white);
    }
    
    public final void w(final Graphics graphics) {
        this.a(graphics, "Game Instructions");
        final Color white = Color.white;
        a(graphics, this.cR, "Game Controls", (int)(fps.D * 0.2f), 0, white);
        a(graphics, this.cR, "Use the 'left' and 'right' arrow keys to rotate left and right.", (int)(fps.D * 0.25f), 0, white);
        a(graphics, this.cR, "Use the 'up' and 'down' arrow keys to move foward and back.", (int)(fps.D * 0.3f), 0, white);
        a(graphics, this.cR, "Hold down 'z' (strafe key) while pressing left or right", (int)(fps.D * 0.35f), 0, white);
        a(graphics, this.cR, "arrow keys to sidestep.", (int)(fps.D * 0.4f), 0, white);
        a(graphics, this.cR, "Press 'space bar' to fire your current weapon.", (int)(fps.D * 0.45f), 0, white);
        a(graphics, this.cR, "Press number keys '1'-'5' to change your current weapon.", (int)(fps.D * 0.5f), 0, white);
        a(graphics, this.cR, "Press 'Escape' during gameplay to access the options menu.", (int)(fps.D * 0.7f), 0, white);
        a(graphics, this.cR, "Use this menu to toggle sounds effects, change graphics quality,", (int)(fps.D * 0.75f), 0, white);
        a(graphics, this.cR, "adjust control options, enable/disable cut scenes etc..", (int)(fps.D * 0.8f), 0, white);
    }
    
    public final void a(final Graphics graphics, final String s) {
        graphics.drawImage(this.aR, 0, 0, fps.B, fps.D, this);
        graphics.setColor(this.bx);
        graphics.setFont(this.cS);
        Graphics graphics2;
        String s2;
        int n;
        if (this.bO) {
            graphics.setColor(Color.black);
            a(graphics, s, 29);
            graphics.setColor(this.bx);
            graphics2 = graphics;
            s2 = s;
            n = 30;
        }
        else {
            graphics.setColor(Color.black);
            a(graphics, s, 19);
            graphics.setColor(this.bx);
            graphics2 = graphics;
            s2 = s;
            n = 20;
        }
        a(graphics2, s2, n);
        this.h(graphics);
    }
    
    public final void x(final Graphics graphics) {
        graphics.setColor(this.bx);
        graphics.setFont(this.cS);
        Graphics graphics2;
        String s;
        int n;
        if (this.bO) {
            graphics.setColor(Color.black);
            a(graphics, "Quit Game?", 29);
            graphics.setColor(this.bx);
            graphics2 = graphics;
            s = "Quit Game?";
            n = 30;
        }
        else {
            graphics.setColor(Color.black);
            a(graphics, "Quit Game?", 19);
            graphics.setColor(this.bx);
            graphics2 = graphics;
            s = "Quit Game?";
            n = 20;
        }
        a(graphics2, s, n);
        a(graphics, this.cW, "All progress will be lost.", (int)(fps.D * 0.2f), 0, this.bx);
        a(graphics, this.cW, "Are you sure you want to quite your game?", (int)(fps.D * 0.25f), 0, this.bx);
        this.a(graphics, 0.45f, 10, "Yes", 0);
        this.a(graphics, 0.55f, 10, "No", 0);
        this.h(graphics);
    }
    
    public final void y(final Graphics graphics) {
        graphics.getFont();
        if (this.cp.a == 5) {
            graphics.drawImage(this.aR, 0, 0, fps.B, fps.D, this);
        }
        graphics.setColor(this.bx);
        graphics.setFont(this.cS);
        Graphics graphics2;
        String s;
        int n;
        if (this.bO) {
            graphics.setColor(Color.black);
            a(graphics, "Options Menu", 29);
            graphics.setColor(this.bx);
            graphics2 = graphics;
            s = "Options Menu";
            n = 30;
        }
        else {
            graphics.setColor(Color.black);
            a(graphics, "Options Menu", 19);
            graphics.setColor(this.bx);
            graphics2 = graphics;
            s = "Options Menu";
            n = 20;
        }
        a(graphics2, s, n);
        graphics.setFont(this.cV);
        a(graphics, "Version 1.02", fps.B - 10, 10);
        if (this.bg) {
            a(graphics, "Admin Mode", fps.B - 10, 25);
        }
        if (this.bh) {
            a(graphics, "Display FPS", fps.B - 10, 35);
        }
        final String s2 = "Sound Effects : ";
        StringBuffer sb;
        String s3;
        if (fps.H) {
            sb = new StringBuffer(String.valueOf(s2));
            s3 = " On";
        }
        else {
            sb = new StringBuffer(String.valueOf(s2));
            s3 = " Off";
        }
        this.a(graphics, 0.2f, 10, sb.append(s3).toString(), 0);
        String string = "Player Turn Speed : ";
        Label_0398: {
            StringBuffer sb2;
            String s4;
            if (fps.I == 1.5) {
                sb2 = new StringBuffer(String.valueOf(string));
                s4 = " Slowest";
            }
            else if (fps.I == 2.0) {
                sb2 = new StringBuffer(String.valueOf(string));
                s4 = " Slow";
            }
            else if (fps.I == 2.5) {
                sb2 = new StringBuffer(String.valueOf(string));
                s4 = " Medium";
            }
            else if (fps.I == 3.0) {
                sb2 = new StringBuffer(String.valueOf(string));
                s4 = " Fast";
            }
            else {
                if (fps.I != 3.5) {
                    break Label_0398;
                }
                sb2 = new StringBuffer(String.valueOf(string));
                s4 = " Fastest";
            }
            string = sb2.append(s4).toString();
        }
        this.a(graphics, 0.4f, 10, string, 0);
        String string2 = "'Frag Camera' Cut Scenes : ";
        Label_0513: {
            StringBuffer sb3;
            String s5;
            if (fps.T == 0) {
                sb3 = new StringBuffer(String.valueOf(string2));
                s5 = " Never";
            }
            else if (fps.T == 1) {
                sb3 = new StringBuffer(String.valueOf(string2));
                s5 = " Seldom";
            }
            else if (fps.T == 2) {
                sb3 = new StringBuffer(String.valueOf(string2));
                s5 = " Often";
            }
            else {
                if (fps.T != 3) {
                    break Label_0513;
                }
                sb3 = new StringBuffer(String.valueOf(string2));
                s5 = " Always";
            }
            string2 = sb3.append(s5).toString();
        }
        this.a(graphics, 0.7f, 10, string2, 0);
        final String s6 = "Frag Messages : ";
        StringBuffer sb4;
        String s7;
        if (fps.V == 0) {
            sb4 = new StringBuffer(String.valueOf(s6));
            s7 = " Full";
        }
        else if (fps.V == 1) {
            sb4 = new StringBuffer(String.valueOf(s6));
            s7 = " Simple";
        }
        else {
            sb4 = new StringBuffer(String.valueOf(s6));
            s7 = " Off";
        }
        this.a(graphics, 0.6f, 10, sb4.append(s7).toString(), 0);
        String string3 = "Display Scores : ";
        Label_0688: {
            StringBuffer sb5;
            String s8;
            if (fps.W == 0) {
                sb5 = new StringBuffer(String.valueOf(string3));
                s8 = " All Players";
            }
            else if (fps.W == 1) {
                sb5 = new StringBuffer(String.valueOf(string3));
                s8 = " Only Mine";
            }
            else {
                if (fps.W != 2) {
                    break Label_0688;
                }
                sb5 = new StringBuffer(String.valueOf(string3));
                s8 = " Off";
            }
            string3 = sb5.append(s8).toString();
        }
        this.a(graphics, 0.5f, 10, string3, 0);
        String string4 = "Graphics Detail : ";
        Label_0803: {
            StringBuffer sb6;
            String s9;
            if (fps.X == 0) {
                sb6 = new StringBuffer(String.valueOf(string4));
                s9 = " Normal";
            }
            else if (fps.X == 1) {
                sb6 = new StringBuffer(String.valueOf(string4));
                s9 = " Low";
            }
            else if (fps.X == 2) {
                sb6 = new StringBuffer(String.valueOf(string4));
                s9 = " Low";
            }
            else {
                if (fps.X != 3) {
                    break Label_0803;
                }
                sb6 = new StringBuffer(String.valueOf(string4));
                s9 = " Ultra High";
            }
            string4 = sb6.append(s9).toString();
        }
        this.a(graphics, 0.3f, 10, string4, 0);
        if (this.cp.a == 2) {
            this.a(graphics, 0.8f, 10, "Display Current Scores", 0);
            this.a(graphics, 0.9f, 10, "Exit Game", 0);
        }
        this.h(graphics);
    }
    
    public final void ah(final int n) {
        this.aj(n);
        this.aL();
    }
    
    public final void ai(final int n) {
        if (fps.H) {
            this.ag(12);
        }
        this.aj(n);
        this.aK();
    }
    
    public final void aj(final int n) {
        for (int i = 0; i < 9; ++i) {
            this.df[i] = this.df[i + 1];
        }
        this.df[9] = n;
    }
    
    public final void aK() {
        Label_0977: {
            if (this.df[2] == 67 && this.df[3] == 79 && this.df[4] == 77 && this.df[5] == 70 && this.df[6] == 79 && this.df[7] == 82 && this.df[8] == 84 && this.df[9] == 83) {
                if (this.cp.v[3]) {
                    if (fps.H) {
                        this.ag(5);
                    }
                    this.cp.s = "Password accepted : Level 4 Unlocked!";
                    this.R(0);
                    this.cp.v[3] = false;
                    this.cp.h = 3;
                    this.cp.m = this.cp.i[this.cp.h];
                    if (this.aW != this.cp.m) {
                        this.aY = false;
                    }
                    this.bo = true;
                }
                else {
                    this.cp.s = "Password rejected, level 4 already unlocked!";
                }
            }
            else if (this.df[3] == 77 && this.df[4] == 65 && this.df[5] == 68 && this.df[6] == 78 && this.df[7] == 69 && this.df[8] == 83 && this.df[9] == 83) {
                if (this.cp.v[4]) {
                    if (fps.H) {
                        this.ag(5);
                    }
                    this.cp.s = "Password accepted : Level 5 Unlocked!";
                    this.R(0);
                    this.cp.v[3] = false;
                    this.cp.v[4] = false;
                    this.cp.h = 4;
                    this.cp.m = this.cp.i[this.cp.h];
                    if (this.aW != this.cp.m) {
                        this.aY = false;
                    }
                    this.bo = true;
                }
                else {
                    this.cp.s = "Password rejected, level 5 already unlocked!";
                }
            }
            else if (this.df[2] == 83 && this.df[3] == 80 && this.df[4] == 79 && this.df[5] == 79 && this.df[6] == 78 && this.df[7] == 70 && this.df[8] == 69 && this.df[9] == 68) {
                if (this.cp.v[5]) {
                    if (fps.H) {
                        this.ag(5);
                    }
                    this.cp.s = "Password accepted : Level 6 Unlocked!";
                    this.R(0);
                    this.cp.v[3] = false;
                    this.cp.v[4] = false;
                    this.cp.v[5] = false;
                    this.cp.h = 5;
                    this.cp.m = this.cp.i[this.cp.h];
                    if (this.aW != this.cp.m) {
                        this.aY = false;
                    }
                    this.bo = true;
                }
                else {
                    this.cp.s = "Password rejected, level 6 already unlocked!";
                }
            }
            else {
                boolean[] array;
                int n;
                if (this.df[3] == 69 && this.df[4] == 78 && this.df[5] == 82 && this.df[6] == 65 && this.df[7] == 71 && this.df[8] == 69 && this.df[9] == 68) {
                    this.cp.s = "Password accepted : Level 4 Unlocked! TO=1";
                    this.R(1);
                    array = this.cp.v;
                    n = 3;
                }
                else if (this.df[3] == 73 && this.df[4] == 78 && this.df[5] == 72 && this.df[6] == 69 && this.df[7] == 82 && this.df[8] == 73 && this.df[9] == 84) {
                    this.cp.s = "Password accepted : Level 5 Unlocked! TO=1";
                    this.R(1);
                    this.cp.v[3] = false;
                    array = this.cp.v;
                    n = 4;
                }
                else {
                    if (this.df[5] != 69 || this.df[6] != 65 || this.df[7] != 82 || this.df[8] != 84 || this.df[9] != 72) {
                        break Label_0977;
                    }
                    this.cp.s = "Password accepted : Level 6 Unlocked! TO=1";
                    this.R(1);
                    this.cp.v[3] = false;
                    this.cp.v[4] = false;
                    array = this.cp.v;
                    n = 5;
                }
                array[n] = false;
            }
        }
        if (this.df[0] == 65 && this.df[1] == 68 && this.df[2] == 77 && this.df[3] == 73 && this.df[4] == 78 && this.df[5] == 73 && this.df[6] == 83 && this.df[7] == 84 && this.df[8] == 82 && this.df[9] == 65) {
            this.cp.v[3] = false;
            this.cp.v[4] = false;
            this.cp.v[5] = false;
            this.cp.s = "All Levels Now Unlocked!";
        }
    }
    
    public final void aL() {
        if (this.df[4] != 82 || this.df[5] != 65 || this.df[6] != 84 || this.df[7] != 65 || this.df[8] != 75 || this.df[9] != 73) {
            if (this.df[3] == 83 && this.df[4] == 72 && this.df[5] == 79 && this.df[6] == 87 && this.df[7] == 70 && this.df[8] == 80 && this.df[9] == 83) {
                if (fps.H) {
                    this.ag(5);
                }
                if (this.bh) {
                    this.bh = false;
                    return;
                }
                this.bh = true;
            }
            return;
        }
        if (fps.H) {
            this.ag(5);
        }
        this.cp.v[3] = false;
        this.cp.v[4] = false;
        this.cp.v[5] = false;
        if (this.bg) {
            this.bg = false;
            return;
        }
        this.bg = true;
    }
    
    public final int ak(final int n) {
        int n2 = n;
        if (this.cp.m == 16) {
            if (n == 1) {
                return 4;
            }
            if (n == 2) {
                return 6;
            }
            if (n == 3) {
                return 7;
            }
            if (n != 4) {
                return n2;
            }
        }
        else if (this.cp.m == 15) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 3;
            }
            if (n == 3) {
                return 5;
            }
            if (n == 4) {
                return 9;
            }
            return n2;
        }
        else if (this.cp.m == 19) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            if (n == 3) {
                return 6;
            }
            if (n != 4) {
                return n2;
            }
        }
        else if (this.cp.m == 18) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 3;
            }
            if (n == 3) {
                return 6;
            }
            if (n != 4) {
                return n2;
            }
        }
        else if (this.cp.m == 5) {
            if (n == 1) {
                return 2;
            }
            if (n == 2) {
                return 4;
            }
            if (n == 3) {
                return 7;
            }
            if (n == 4) {
                return 9;
            }
            return n2;
        }
        else {
            if (this.cp.m != 4) {
                return n2;
            }
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 3;
            }
            if (n == 3) {
                return 4;
            }
            if (n != 4) {
                return n2;
            }
        }
        final int n3 = 8;
        n2 = n3;
        return n2;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public fps() {
        final float[] array = { 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        final int[] array2 = { 0, 1, 2, 3, -1, 4, 5, 1, 0, -1, 3, 2, 6, 7, -1, 4, 0, 3, 7, -1, 7, 6, 5, 4, -1, 1, 5, 6, 2, -1 };
        this.k = new float[] { 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f };
        this.l = new float[] { 0.0f, 0.0f, 0.0f, 2.0f, 1.0f, 2.0f, 1.0f, 0.0f };
        final float[] array3 = { 0.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f };
        final float[] array4 = { 0.0f, 0.0f, 0.0f, 2.0f, 2.0f, 2.0f, 2.0f, 0.0f };
        final float[] array5 = { 0.0f, 0.0f, 0.0f, 4.0f, 4.0f, 4.0f, 4.0f, 0.0f };
        final float[] array6 = { 0.0f, 0.0f, 0.0f, 2.0f, 1.0f, 2.0f, 1.0f, 0.0f };
        final float[] array7 = { 0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f, 2.0f, 0.0f };
        final float[] array8 = { 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f };
        final float[] array9 = { 0.0f, 0.0f, 0.0f, 2.0f, 6.0f, 2.0f, 6.0f, 0.0f };
        this.m = new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1 };
        this.n = new int[] { 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 0, 1, 2, 3, -1, 3, 2, 1, 0, -1, 0, 1, 2, 3, -1 };
        this.o = new int[4];
        this.q = new int[20][12];
        this.r = new float[12];
        this.s = new float[12];
        this.t = new float[12];
        this.u = new float[12];
        this.v = new float[12];
        this.w = new float[12];
        this.y = 5;
        this.L = 1;
        this.aJ = new MediaTracker(this);
        this.aV = new boolean[3];
        this.aW = -1;
        this.aX = false;
        this.aY = false;
        this.aZ = -1;
        this.ba = 3;
        this.bc = -1;
        this.bf = "http://www.JavaGamePlay.com";
        this.bg = false;
        this.bh = false;
        this.bi = false;
        this.bl = true;
        this.bm = false;
        this.bo = false;
        this.bp = true;
        this.br = 12;
        this.bs = new int[20];
        this.bv = Color.lightGray;
        this.bw = Color.green;
        this.bx = Color.white;
        this.by = -1;
        this.bz = -1;
        this.bA = -1;
        this.bB = -1;
        this.bC = 50;
        this.bE = new int[6];
        this.bF = new int[6];
        this.bG = new int[6];
        this.bH = new int[6];
        this.bN = Color.green;
        this.bO = true;
        this.bU = 1;
        this.bV = new Color[25];
        this.bW = new Color[25];
        this.bX = new Color[25];
        this.bY = new Color(255, 30, 255);
        final String[] array10 = { "JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM", "DAVID", "RICHARD", "CHARLES", "JOSEPH", "THOMAS", "CHRISTOPHER", "DANIEL", "PAUL", "MARK", "DONALD", "GEORGE", "KENNETH", "STEVEN", "EDWARD", "BRIAN", "RONALD", "ANTHONY", "KEVIN", "JASON", "MATTHEW", "GARY", "TIMOTHY", "JOSE", "LARRY", "JEFFREY", "FRANK", "SCOTT", "ERIC", "STEPHEN", "ANDREW", "RAYMOND", "GREGORY", "JOSHUA", "JERRY", "DENNIS", "WALTER", "PATRICK", "PETER", "HAROLD", "DOUGLAS", "HENRY", "CARL", "ARTHUR", "RYAN", "ROGER" };
        final String[] array11 = { "SMITH", "JOHNSON", "WILLIAMS", "JONES", "BROWN", "DAVIS", "MILLER", "WILSON", "MOORE", "TAYLOR", "ANDERSON", "THOMAS", "JACKSON", "WHITE", "HARRIS", "MARTIN", "THOMPSON", "GARCIA", "MARTINEZ", "ROBINSON", "CLARK", "RODRIGUEZ", "LEWIS", "LEE", "WALKER", "HALL", "ALLEN", "YOUNG", "HERNANDEZ", "KING", "WRIGHT", "LOPEZ", "HILL", "SCOTT", "GREEN", "ADAMS", "BAKER", "GONZALEZ", "NELSON", "CARTER", "MITCHELL", "PEREZ", "ROBERTS", "TURNER", "PHILLIPS", "CAMPBELL", "PARKER", "EVANS", "EDWARDS", "COLLINS" };
        final String[] array12 = { " ", "_", "-", ".", "  " };
        final String[] array13 = { "dangerous", "bad", "scary", "strange", "horrible", "grumpy", "abusive", "careful", "clever", "cruel", "foolish", "greedy", "brave", "careless", "ambitious", "cheerful", "conceited", "faithful", "irritable", "irritating", "naughty", "spiteful", "troublesome", "rude", "stubborn", "vain", "wicked", "mischievous", "noisy", "playful", "slow", "stupid", "timid", "vicious", "violent", "immoral", "quick", "antagonistic", "cynical", "competitive", "criminal", "cowardly", "hostile", "militant", "bold", "aggressive", "strong", "gloomy", "non-violent" };
        this.ca = false;
        this.cc = false;
        this.cf = new m();
        this.cg = new m();
        this.ch = new m[this.y];
        this.ci = new m[6];
        new Color(0, 0, 130);
        new Color(0, 0, 180);
        this.cl = new Color(200, 200, 200);
        new Color(250, 0, 0);
        new Color(220, 0, 0);
        new Color(190, 0, 0);
        new Color(160, 0, 0);
        new Color(130, 0, 0);
        this.cm = false;
        this.cs = 4;
        this.ct = 2;
        this.cu = 5;
        this.cv = this.cu * 0.06f;
        this.cw = this.cu;
        this.cx = 1;
        this.cy = 20;
        this.cz = true;
        this.cC = new int[2];
        this.cP = new Font("Dialog", 2, 11);
        this.cQ = new Font("Dialog", 1, 15);
        this.cR = new Font("Dialog", 1, 12);
        this.cS = new Font("TimesRoman", 1, 28);
        this.cT = new Font("TimesRoman", 1, 16);
        this.cU = new Font("Dialog", 1, 10);
        this.cV = new Font("Dialog", 1, 12);
        this.cW = new Font("Dialog", 1, 15);
        this.cX = new Font("Dialog", 1, 15);
        this.cY = new Font("Dialog", 1, 17);
        new Font("Dialog", 1, 16);
        new Font("Dialog", 1, 25);
        this.cZ = new Font("Dialog", 1, 38);
        this.da = new Font("Helvetica", 1, 10);
        this.db = new Font("Helvetica", 1, 10);
        this.dc = new Font("Helvetica", 1, 12);
        this.dd = new Font("Helvetica", 1, 10);
        this.de = new Font("Dialog", 1, 15);
        this.dg = -1L;
        this.dh = false;
        this.dj = -100L;
        this.dl = -777L;
        this.dm = 15;
        this.dy = 100;
        this.dN = -1;
    }
    
    static {
        fps.x = 1;
        fps.z = 10;
        fps.A = 10;
        fps.F = 5;
        fps.H = true;
        fps.I = 2.5f;
        fps.T = 2;
        fps.U = 5;
        fps.Y = 1;
        fps.bj = true;
        fps.bk = true;
        fps.bn = true;
        fps.bR = 1.0f;
        fps.bS = 1;
    }
}
