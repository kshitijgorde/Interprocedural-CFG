import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Label;
import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Font;
import java.awt.image.IndexColorModel;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnFlag extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    static final byte[] d;
    static final byte[] e;
    static final byte[] f;
    final String g = "z,mQaO|\u007fD$}=\u007fTk\u001b\u001ftHgX5=\u0015sL+3\\j]";
    int h;
    int i;
    int j;
    byte[][] k;
    byte[][] l;
    byte[] m;
    static final byte[] n;
    byte[] o;
    int p;
    static final byte[] q;
    byte[] r;
    IndexColorModel s;
    int t;
    static final byte[] u;
    long v;
    int w;
    static final byte[] x;
    static final byte[] y;
    Font z;
    int A;
    byte[] B;
    int C;
    float D;
    float E;
    private Image F;
    int G;
    boolean H;
    String[] I;
    URL J;
    int K;
    int L;
    String M;
    String N;
    String O;
    String P;
    String Q;
    String R;
    String S;
    String T;
    String U;
    String V;
    String W;
    String X;
    String Y;
    int Z;
    int ba;
    boolean bb;
    static final byte[] bc;
    int bd;
    int be;
    private Graphics bf;
    private Image bg;
    boolean bh;
    private Image bi;
    int bj;
    int bk;
    int bl;
    int bm;
    anfy bn;
    MemoryImageSource bo;
    int bp;
    int bq;
    byte[] br;
    boolean bs;
    int bt;
    int bu;
    String bv;
    int bw;
    int bx;
    int by;
    Color bz;
    int bA;
    int bB;
    int bC;
    int bD;
    int bE;
    int bF;
    int bG;
    int[] bH;
    int[] bI;
    int bJ;
    int bK;
    int[] bL;
    int bM;
    int bN;
    String bO;
    Color bP;
    int bQ;
    int bR;
    boolean bS;
    boolean bT;
    Toolkit bU;
    Thread bV;
    int bW;
    int bX;
    int bY;
    int bZ;
    int ca;
    static final byte[] cb;
    int[] cc;
    int cd;
    Lware ce;
    int cf;
    int cg;
    int ch;
    int ci;
    int cj;
    int ck;
    int cl;
    int cm;
    int cn;
    int co;
    int cp;
    int cq;
    int cr;
    int cs;
    Font[] ct;
    
    static {
        d = new byte[] { 1, 2, 1, 2, 2, 2, 1, 2, 1 };
        e = new byte[] { 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1 };
        f = new byte[] { 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1 };
        bc = new byte[] { 1, 2, 2, 1, 2, 3, 3, 2, 2, 3, 3, 2, 1, 2, 2, 1 };
        cb = new byte[] { 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
        y = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 };
        q = new byte[] { 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
        u = new byte[] { 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1 };
        x = new byte[] { 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2 };
        n = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
    }
    
    public AnFlag() {
        this.H = false;
        this.bt = 1;
        this.Z = 1;
        this.bh = false;
        this.c = false;
        this.bs = false;
        this.bb = false;
        this.ch = 50;
        this.co = 33;
    }
    
    public final void a() {
        final byte[][] array = new byte[this.ch / 2 + 1][this.co / 2 + 1];
        this.E();
        for (int i = 0; i < this.co; ++i) {
            for (int j = 0; j < this.ch - 1; ++j) {
                array[j / 2][i / 2] = this.k[j + 1][i];
            }
        }
        for (int k = 0; k < this.co; ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k] = 1;
            }
        }
        for (int n = 0; n < this.co / 2; ++n) {
            for (int n2 = 0; n2 < this.ch / 2; ++n2) {
                this.k[n2][n] = array[n2][n];
            }
        }
        for (int n3 = 18; n3 < 21; ++n3) {
            for (int n4 = 40; n4 < 43; ++n4) {
                this.k[n4][n3] = AnFlag.d[n4 - 40 + (n3 - 18) * 3];
            }
        }
        for (int n5 = 12; n5 < 16; ++n5) {
            for (int n6 = 42; n6 < 46; ++n6) {
                this.k[n6][n5] = AnFlag.e[n6 - 42 + (n5 - 12) * 4];
            }
        }
        for (int n7 = 5; n7 < 9; ++n7) {
            for (int n8 = 35; n8 < 39; ++n8) {
                this.k[n8][n7] = AnFlag.e[n8 - 35 + (n7 - 5) * 4];
            }
        }
        for (int n9 = 14; n9 < 18; ++n9) {
            for (int n10 = 28; n10 < 32; ++n10) {
                this.k[n10][n9] = AnFlag.e[n10 - 28 + (n9 - 14) * 4];
            }
        }
        for (int n11 = 25; n11 < 29; ++n11) {
            for (int n12 = 35; n12 < 39; ++n12) {
                this.k[n12][n11] = AnFlag.e[n12 - 35 + (n11 - 25) * 4];
            }
        }
        for (int n13 = 22; n13 < 28; ++n13) {
            for (int n14 = 9; n14 < 15; ++n14) {
                this.k[n14][n13] = AnFlag.f[n14 - 9 + (n13 - 22) * 6];
            }
        }
    }
    
    public final void b() {
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 25; ++j) {
                this.k[j][i + 1] = AnFlag.n[j + i * 25];
            }
        }
        for (int k = 16; k < 32; ++k) {
            for (int l = 0; l < 25; ++l) {
                this.k[l][k + 1] = AnFlag.n[l + (400 - (k - 15) * 25)];
            }
        }
        for (int n = 0; n < 16; ++n) {
            for (int n2 = 25; n2 < 50; ++n2) {
                this.k[n2][n + 1] = AnFlag.n[24 - (n2 - 25) + n * 25];
            }
        }
        for (int n3 = 16; n3 < 32; ++n3) {
            for (int n4 = 25; n4 < 50; ++n4) {
                this.k[n4][n3 + 1] = AnFlag.n[24 - (n4 - 25) + (375 - (n3 - 16) * 25)];
            }
        }
        this.k[15][16] = 2;
        this.k[16][16] = 2;
        this.k[17][16] = 2;
        this.k[18][16] = 2;
        this.k[19][16] = 2;
        this.k[20][16] = 2;
        this.k[21][16] = 2;
        this.k[22][16] = 2;
        this.k[23][17] = 2;
        this.k[24][17] = 2;
        this.k[25][17] = 2;
        this.k[26][17] = 2;
        this.k[27][17] = 2;
        this.k[28][18] = 2;
        this.k[29][18] = 2;
        this.k[30][18] = 2;
        this.k[31][19] = 2;
        this.k[32][19] = 2;
        this.k[33][20] = 2;
        this.k[26][14] = 2;
        this.k[16][18] = 2;
        this.k[17][20] = 2;
        this.k[19][22] = 2;
        this.k[22][24] = 2;
        this.k[25][25] = 2;
        this.k[28][24] = 2;
        this.k[30][22] = 2;
        this.k[32][21] = 2;
        this.k[22][20] = 2;
        this.k[25][21] = 2;
    }
    
    private final void c() {
        while (true) {
            this.showStatus(c("\u007f3s\u001ap\u001b.xPkM9=JsLr|SbB6|Ke\u0015?rP$X.xYmO/=QmU9=Tj\u001b\u0014IpH\u001a"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void d() {
        for (int i = 0; i < this.ch / 4 + 2; ++i) {
            for (int j = 0; j < this.co; ++j) {
                this.k[i][j] = 2;
            }
        }
        for (int k = this.ch / 4 + 2; k < this.ch - this.ch / 4 - 2; ++k) {
            for (int l = 0; l < this.co; ++l) {
                this.k[k][l] = 1;
            }
        }
        for (int n = this.ch - this.ch / 4 - 2; n < this.ch; ++n) {
            for (int n2 = 0; n2 < this.co; ++n2) {
                this.k[n][n2] = 2;
            }
        }
        for (int n3 = 6; n3 < 28; ++n3) {
            for (int n4 = 14; n4 < 25; ++n4) {
                this.k[n4][n3] = AnFlag.y[n4 - 14 + (n3 - 6) * 11];
            }
        }
        for (int n5 = 6; n5 < 28; ++n5) {
            for (int n6 = 25; n6 < 36; ++n6) {
                this.k[n6][n5] = AnFlag.y[10 - (n6 - 14 - 11) + (n5 - 6) * 11];
            }
        }
    }
    
    public synchronized boolean e() {
        this.prepareImage(this.bi, this);
        if (this.H) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bh;
        }
        return false;
    }
    
    public final void f() {
        for (int i = 0; i < this.co / 2; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = this.co / 2; k < 3 * (this.co / 4); ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k] = 2;
            }
        }
        for (int n = 3 * (this.co / 4); n < this.co; ++n) {
            for (int n2 = 0; n2 < this.ch; ++n2) {
                this.k[n2][n] = 3;
            }
        }
    }
    
    public final void g() {
        for (int i = 0; i < this.co; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = this.co / 5 * 2; k < this.co / 5 * 3; ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k] = 2;
            }
        }
        for (int n = this.ch / 8 * 2; n < this.ch / 8 * 3; ++n) {
            for (int n2 = 0; n2 < this.co; ++n2) {
                this.k[n][n2] = 2;
            }
        }
    }
    
    public final void h() {
        this.g();
        for (int i = this.co / 20 * 13; i < this.co / 20 * 17; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 3;
            }
        }
        for (int k = this.ch / 32 * 13; k < this.ch / 32 * 17; ++k) {
            for (int l = 0; l < this.co; ++l) {
                this.k[k][l] = 3;
            }
        }
    }
    
    public void destroy() {
        if (this.bi != null) {
            this.bi.flush();
        }
        this.bi = null;
        if (this.bg != null) {
            this.bg.flush();
        }
        this.bg = null;
        if (this.bf != null) {
            this.bf.dispose();
        }
        this.bf = null;
        System.gc();
    }
    
    public final void i() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        for (int i = 0; i < this.cg; ++i) {
            final int n = this.bA + this.t * i;
            final int n2 = this.bJ * i;
            final int n3 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n4 = this.t * j;
                this.cj = n2 + bl[n + n4 & 0xFF];
                this.cq = this.bJ * j + bl[n3 + n4 + j & 0xFF];
                o[this.cq * cd + this.cj] = array[j];
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    public final void j() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        for (int i = 0; i < this.cg; ++i) {
            final int n = this.bA + this.t * i;
            final int n2 = this.bJ * i;
            final int n3 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n4 = this.t * j;
                this.cj = n2 + bl[n + n4 & 0xFF];
                this.cq = this.bJ * j + bl[n3 + n4 + j & 0xFF];
                this.be = this.cq * cd + this.cj;
                final byte[] array2 = o;
                final int be = this.be;
                final byte[] array3 = o;
                final int n5 = this.be - cd;
                final byte[] array4 = o;
                final int n6 = this.be - 1;
                final byte[] array5 = o;
                final int n7 = this.be - cd - 1;
                final byte b = array[j];
                array4[n6] = (array5[n7] = b);
                array2[be] = (array3[n5] = b);
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    public final void k() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        final int n = cd - 1;
        final int n2 = cd + 1;
        for (int i = 0; i < this.cg; ++i) {
            final int n3 = this.bA + this.t * i;
            final int n4 = this.bJ * i;
            final int n5 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n6 = this.t * j;
                this.cj = n4 + bl[n3 + n6 & 0xFF];
                this.cq = this.bJ * j + bl[n5 + n6 + j & 0xFF];
                this.be = this.cq * cd + this.cj;
                final byte[] array2 = o;
                final int be = this.be;
                final byte[] array3 = o;
                final int n7 = this.be - n;
                final byte[] array4 = o;
                final int n8 = this.be - cd;
                final byte[] array5 = o;
                final int n9 = this.be - n2;
                final byte[] array6 = o;
                final int n10 = this.be - 1;
                final byte[] array7 = o;
                final int n11 = this.be + 1;
                final byte[] array8 = o;
                final int n12 = this.be + n;
                final byte[] array9 = o;
                final int n13 = this.be + cd;
                final byte[] array10 = o;
                final int n14 = this.be + n2;
                final byte b = array[j];
                array10[n14] = b;
                array8[n12] = (array9[n13] = b);
                array6[n10] = (array7[n11] = b);
                array4[n8] = (array5[n9] = b);
                array2[be] = (array3[n7] = b);
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    public final void l() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        final int n = cd - 1;
        final int n2 = cd + 1;
        for (int i = 0; i < this.cg; ++i) {
            final int n3 = this.bA + this.t * i;
            final int n4 = this.bJ * i;
            final int n5 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n6 = this.t * j;
                this.cj = n4 + bl[n3 + n6 & 0xFF];
                this.cq = this.bJ * j + bl[n5 + n6 + j & 0xFF];
                this.be = this.cq * cd + this.cj;
                final byte[] array2 = o;
                final int n7 = this.be - n;
                final byte[] array3 = o;
                final int n8 = this.be - cd;
                final byte[] array4 = o;
                final int n9 = this.be - n2;
                final byte[] array5 = o;
                final int n10 = this.be - 1;
                final byte[] array6 = o;
                final int n11 = this.be + 1;
                final byte[] array7 = o;
                final int n12 = this.be + n;
                final byte[] array8 = o;
                final int n13 = this.be + this.cd;
                final byte[] array9 = o;
                final int n14 = this.be + n2;
                final byte b = array[j];
                array8[n13] = (array9[n14] = b);
                array6[n11] = (array7[n12] = b);
                array4[n9] = (array5[n10] = b);
                array2[n7] = (array3[n8] = b);
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    public final void m() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        final int n = cd - 1;
        final int n2 = cd + 1;
        for (int i = 0; i < this.cg; ++i) {
            final int n3 = this.bA + this.t * i;
            final int n4 = this.bJ * i;
            final int n5 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n6 = this.t * j;
                this.cj = n4 + bl[n3 + n6 & 0xFF];
                this.cq = this.bJ * j + bl[n5 + n6 + j & 0xFF];
                this.be = this.cq * cd + this.cj;
                final byte[] array2 = o;
                final int be = this.be;
                final byte[] array3 = o;
                final int n7 = this.be - n;
                final byte[] array4 = o;
                final int n8 = this.be - this.cd + 1;
                final byte[] array5 = o;
                final int n9 = this.be + n;
                final byte[] array6 = o;
                final int n10 = this.be + n2;
                final byte b = array[j];
                array6[n10] = b;
                array4[n8] = (array5[n9] = b);
                array2[be] = (array3[n7] = b);
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    public final void n() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        final int n = cd - 1;
        final int n2 = cd + 1;
        for (int i = 0; i < this.cg; ++i) {
            final int n3 = this.bA + this.t * i;
            final int n4 = this.bJ * i;
            final int n5 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n6 = this.t * j;
                this.cj = n4 + bl[n3 + n6 & 0xFF];
                this.cq = this.bJ * j + bl[n5 + n6 + j & 0xFF];
                this.be = this.cq * cd + this.cj;
                final byte[] array2 = o;
                final int n7 = this.be - n;
                final byte[] array3 = o;
                final int n8 = this.be - n2;
                final byte[] array4 = o;
                final int n9 = this.be + n;
                final byte[] array5 = o;
                final int n10 = this.be + n2;
                final byte b = array[j];
                array4[n9] = (array5[n10] = b);
                array2[n7] = (array3[n8] = b);
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    public final void o() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        for (int i = 0; i < this.cg; ++i) {
            final int n = this.bA + this.t * i;
            final int n2 = this.bJ * i;
            final int n3 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n4 = this.t * j;
                this.cj = n2 + bl[n + n4 & 0xFF];
                this.cq = this.bJ * j + bl[n3 + n4 + j & 0xFF];
                this.be = this.cq * cd + this.cj;
                final byte[] array2 = o;
                final int be = this.be;
                final byte[] array3 = o;
                final int n5 = this.be - cd;
                final byte[] array4 = o;
                final int n6 = this.be - 1;
                final byte[] array5 = o;
                final int n7 = this.be + 1;
                final byte[] array6 = o;
                final int n8 = this.be + cd;
                final byte b = array[j];
                array6[n8] = b;
                array4[n6] = (array5[n7] = b);
                array2[be] = (array3[n5] = b);
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    public final void p() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        for (int i = 0; i < this.cg; ++i) {
            final int n = this.bA + this.t * i;
            final int n2 = this.bJ * i;
            final int n3 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n4 = this.t * j;
                this.cj = n2 + bl[n + n4 & 0xFF];
                this.cq = this.bJ * j + bl[n3 + n4 + j & 0xFF];
                this.be = this.cq * cd + this.cj;
                final byte[] array2 = o;
                final int n5 = this.be - cd;
                final byte[] array3 = o;
                final int n6 = this.be - 1;
                final byte[] array4 = o;
                final int n7 = this.be + 1;
                final byte[] array5 = o;
                final int n8 = this.be + cd;
                final byte b = array[j];
                array4[n7] = (array5[n8] = b);
                array2[n5] = (array3[n6] = b);
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    public final void q() {
        final int[] bl = this.bL;
        final byte[] o = this.o;
        final int cd = this.cd;
        for (int i = 0; i < this.cg; ++i) {
            final int n = this.bA + this.t * i;
            final int n2 = this.bJ * i;
            final int n3 = this.bA + (i << 2);
            final byte[] array = this.l[i];
            for (int j = this.cn; j > 0; --j) {
                final int n4 = this.t * j;
                this.cj = n2 + bl[n + n4 & 0xFF];
                this.cq = this.bJ * j + bl[n3 + n4 + j & 0xFF];
                this.be = this.cq * cd + this.cj;
                final byte[] array2 = o;
                final int be = this.be;
                final byte[] array3 = o;
                final int n5 = this.be - 1;
                final byte[] array4 = o;
                final int n6 = this.be + 1;
                final byte[] array5 = o;
                final int n7 = this.be + cd;
                final byte b = array[j];
                array4[n6] = (array5[n7] = b);
                array2[be] = (array3[n5] = b);
            }
        }
        this.bA += this.bK;
        this.bA &= 0xFF;
    }
    
    Image a(final String s) {
        try {
            return this.b(s);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.b(s);
        }
    }
    
    synchronized Image b(final String s) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Label_0172: {
            try {
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(url.toString());
                    if (resourceAsStream == null) {
                        break Label_0172;
                    }
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                    final byte[] array = new byte[512];
                    boolean b = false;
                    byte[] byteArray;
                    try {
                        while (!b) {
                            final int read = resourceAsStream.read(array, 0, 512);
                            if (read != -1) {
                                byteArrayOutputStream.write(array, 0, read);
                                byteArrayOutputStream.flush();
                            }
                            else {
                                b = true;
                            }
                        }
                        byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        resourceAsStream.close();
                    }
                    catch (IOException ex2) {
                        byteArray = null;
                    }
                    System.gc();
                    if (byteArray != null) {
                        image = this.getToolkit().createImage(byteArray);
                        this.prepareImage(image, this);
                    }
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            catch (SecurityException ex3) {}
        }
        if (image == null) {
            int i = 0;
            while (i < 5) {
                try {
                    if (i % 2 == 0) {
                        image = Toolkit.getDefaultToolkit().getImage(url);
                    }
                    else {
                        image = this.getImage(url);
                    }
                    ++i;
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    this.notifyAll();
                    Thread.currentThread();
                    Thread.yield();
                    try {
                        mediaTracker.addImage(image, 0);
                        mediaTracker.waitForID(0);
                    }
                    catch (InterruptedException ex4) {
                        image = null;
                    }
                    if (mediaTracker.isErrorID(0)) {
                        image = null;
                    }
                    else {
                        i = 6;
                    }
                }
                catch (NullPointerException ex5) {
                    System.gc();
                }
            }
        }
        if (image == null) {
            for (int j = 0; j < 25; ++j) {
                this.showStatus(c("r1|Za\u001b") + s + c("\u001b2rI$]3hS`\u001a"));
                try {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex6) {}
            }
        }
        else {
            while (image.getWidth(this) < 0) {
                this.notifyAll();
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex7) {}
            }
        }
        return image;
    }
    
    public void a(final String s, final int n) {
        try {
            this.b(s, n);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.b(s, n);
        }
    }
    
    public void b(final String s, final int n) {
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            try {
                final DataInputStream dataInputStream = new DataInputStream(url.openStream());
                if (dataInputStream == null) {
                    return;
                }
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                final byte[] array = new byte[512];
                int n2 = 0;
                boolean b = false;
                try {
                    while (!b) {
                        final int read = dataInputStream.read(array, 0, 512);
                        if (read == -1) {
                            b = true;
                        }
                        else {
                            byteArrayOutputStream.write(array, 0, read);
                            byteArrayOutputStream.flush();
                            n2 += read;
                        }
                    }
                    final byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    dataInputStream.close();
                    System.gc();
                    if (n == 0) {
                        for (int i = 0; i < n2; ++i) {
                            final byte b2 = byteArray[i];
                            if (b2 == 13 || b2 == 10) {
                                byteArray[i] = 32;
                            }
                        }
                        try {
                            this.bv = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bv = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.I = new String[n3 - 1];
                    final int[] array2 = new int[n3 + 1];
                    final int[] array3 = new int[n3 + 1];
                    array2[0] = 0;
                    int n4 = 0;
                    int n5 = 0;
                    for (int k = 0; k < n2; ++k) {
                        final byte b3 = byteArray[k];
                        if (b3 == 10) {
                            array2[n4 + 1] = k + 1;
                            if (n5 == 13) {
                                array3[n4] = k - array2[n4] - 1;
                            }
                            else {
                                array3[n4] = k - array2[n4];
                            }
                            ++n4;
                        }
                        n5 = b3;
                    }
                    array3[n4] = n2 - array2[n4 + 1] - 1;
                    try {
                        for (int l = 0; l < n3 - 1; ++l) {
                            try {
                                this.I[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.I[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.I = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final void r() {
        this.C();
        for (int i = this.co / 9 * 2; i < this.co / 9 * 3; ++i) {
            for (int j = 0; j < this.ch / 32 * 22; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = this.ch / 16 * 3; k < this.ch / 16 * 4; ++k) {
            for (int l = 0; l < this.co / 10 * 5; ++l) {
                this.k[k][l] = 1;
            }
        }
    }
    
    public final void s() {
        for (int i = 0; i < this.ch / 3; ++i) {
            for (int j = 0; j < this.co; ++j) {
                this.k[i][j] = 1;
            }
        }
        for (int k = this.ch / 3; k < 2 * (this.ch / 3); ++k) {
            for (int l = 0; l < this.co; ++l) {
                this.k[k][l] = 2;
            }
        }
        for (int n = 2 * (this.ch / 3); n < this.ch; ++n) {
            for (int n2 = 0; n2 < this.co; ++n2) {
                this.k[n][n2] = 3;
            }
        }
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.z);
        if (this.a == 0) {
            this.cl = this.cp;
        }
        else {
            this.G += this.bX;
            this.cl = this.cp - (int)Math.abs(this.a * Math.sin(this.G / 90.0 * 3.141592653589793));
        }
        if (this.bC != 0) {
            for (int i = 0; i < this.bZ; ++i) {
                final int n = this.bH[this.bB + i];
                graphics.copyArea(i, n, 1, this.bE, 0, this.bp - n);
            }
            if (this.bT) {
                graphics.setColor(this.bz);
                graphics.drawString(this.bv, this.ci + 1, this.bp + this.bw + 1);
            }
            graphics.setColor(this.bP);
            graphics.drawString(this.bv, this.ci, this.bp + this.bw);
            for (int j = 0; j < this.bZ; ++j) {
                graphics.copyArea(j, this.bp, 1, this.bF, 0, this.bI[this.bB + j]);
            }
            this.bB -= this.bG;
            if (this.bB < 0) {
                this.bB += 360;
            }
        }
        else {
            if (this.bT) {
                graphics.setColor(this.bz);
                graphics.drawString(this.bv, this.ci + 1, this.cl + 1);
            }
            graphics.setColor(this.bP);
            graphics.drawString(this.bv, this.ci, this.cl);
        }
        this.ci -= this.bY;
        if (this.ci < -this.bx) {
            this.ci = this.bZ;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bi) {
            if (n == 16) {
                this.bh = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bU = this.getToolkit();
        this.bO = this.getParameter(c("H(|IqH1nZ"));
        final String parameter = this.getParameter(c("X.xYmO/"));
        if (parameter != null) {
            if (!parameter.startsWith(c("z,mQaO|\u007fD$}=\u007fTk\u001b\u001ftHgX5=\u0015sL+3\\j]"))) {
                this.c();
            }
        }
        else {
            this.c();
        }
        String s;
        try {
            s = this.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s = c("]5qX");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("]5qX")) || s2.length() == 0 || s2.equalsIgnoreCase(c("W3~\\hS3nI")) || s2.equals(c("\nn*\u00134\u0015l3\f"))) {
            this.bs = true;
        }
        else {
            if (s2.startsWith(c("L+j\u0013"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("I9z^k_9"));
            if (parameter2 != null && !parameter2.equals("NO") && parameter2.length() > 10) {
                int n = 1;
                try {
                    for (int i = 0; i < parameter2.length(); ++i) {
                        if (parameter2.charAt(i) == '+') {
                            ++n;
                        }
                    }
                }
                catch (StringIndexOutOfBoundsException ex3) {}
                final int[] array = new int[n];
                if (n == 1) {
                    array[0] = parameter2.length();
                }
                else {
                    int n2 = 0;
                    try {
                        for (int j = 0; j < parameter2.length(); ++j) {
                            if (parameter2.charAt(j) == '+') {
                                array[n2] = j;
                                ++n2;
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex4) {}
                    array[n2] = parameter2.length();
                }
                final String[] array2 = new String[n];
                int n3 = 0;
                for (int k = 0; k < n; ++k) {
                    try {
                        array2[k] = parameter2.substring(n3, array[k]);
                    }
                    catch (StringIndexOutOfBoundsException ex5) {}
                    n3 = array[k] + 1;
                }
                for (int l = 0; l < n; ++l) {
                    final int n4 = array2[l].length() - 8;
                    final byte[] array3 = new byte[n4];
                    final byte[] array4 = new byte[8];
                    array2[l].getBytes(0, n4, array3, 0);
                    array2[l].getBytes(n4, n4 + 8, array4, 0);
                    int n5 = n4 % 7;
                    final int n6 = n4 % 3;
                    for (int n7 = 0; n7 < n4; ++n7) {
                        final byte b = array3[n7];
                        if (b >= 48 && b <= 57) {
                            array3[n7] = this.a(b, n5, 48, 57);
                        }
                        else if (b >= 65 && b <= 90) {
                            array3[n7] = this.a(b, n5, 65, 90);
                        }
                        else if (b >= 97 && b <= 122) {
                            array3[n7] = this.a(b, n5, 97, 122);
                        }
                        else if (b == 45) {
                            array3[n7] = 46;
                        }
                        else if (b == 46) {
                            array3[n7] = 45;
                        }
                        if ((n5 += n6) > 7) {
                            n5 = 1;
                        }
                    }
                    byte b2 = 0;
                    byte b3 = 0;
                    for (int n8 = 0; n8 < 4; ++n8) {
                        final byte[] array5 = array4;
                        final int n9 = n8;
                        array5[n9] -= 52;
                    }
                    for (int n10 = 4; n10 < 8; ++n10) {
                        final byte[] array6 = array4;
                        final int n11 = n10;
                        array6[n11] -= 55;
                    }
                    for (int n12 = 0; n12 < n4; n12 += 2) {
                        b2 += array3[n12];
                    }
                    for (int n13 = 1; n13 < n4; n13 += 2) {
                        b3 += array3[n13];
                    }
                    String s3 = String.valueOf(b2);
                    String s4 = String.valueOf(b3);
                    while (s3.length() < 4) {
                        s3 = "0" + s3;
                    }
                    while (s4.length() < 4) {
                        s4 = "0" + s4;
                    }
                    final byte[] array7 = new byte[8];
                    s3.getBytes(0, 4, array7, 0);
                    s4.getBytes(0, 4, array7, 4);
                    if (new String(array7, 0).equals(new String(array4, 0))) {
                        final String s5 = new String(array3, 0);
                        String substring;
                        if (s5.startsWith(c("L+j\u0013"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bs = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("I9zQmU7"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.J = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.J = null;
            }
        }
        if (this.getParameter(c("I9zSaL:o\\i^")).equalsIgnoreCase(c("b\u0019N"))) {
            this.bb = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("T*xOmV;"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bi = this.a(parameter4);
            if (this.bi != null) {
                String parameter5 = this.getParameter(c("T*xOmV;E"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bj = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("T*xOmV;D"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bk = Integer.valueOf(parameter6);
            }
        }
        this.V = this.getParameter(c("v5sn]u\u001f"));
        if (this.V == null) {
            this.V = "10";
        }
        this.L = Integer.valueOf(this.V);
        this.M = this.getParameter(c("I9n"));
        if (this.M == null) {
            this.M = "1";
        }
        this.Q = this.getParameter(c("Y=~VV"));
        if (this.Q == null) {
            this.Q = "0";
        }
        this.R = this.getParameter(c("Y=~VC"));
        if (this.R == null) {
            this.R = "0";
        }
        this.S = this.getParameter(c("Y=~VF"));
        if (this.S == null) {
            this.S = "60";
        }
        this.T = this.getParameter(c("K=iIaI2"));
        if (this.T == null) {
            this.T = "8";
        }
        this.U = this.getParameter(c("H,xX`"));
        if (this.U == null) {
            this.U = "3";
        }
        this.X = this.getParameter(c("X)oKa"));
        if (this.X == null) {
            this.X = "3";
        }
        this.Y = this.getParameter(c("L5sY"));
        if (this.Y == null) {
            this.Y = "8";
        }
        this.N = this.getParameter(c("U=iTkU"));
        if (this.N == null) {
            this.N = "1";
        }
        this.O = this.getParameter(c("V9pYaW=d"));
        this.P = this.getParameter(c("K.tRvR(d"));
        this.K = Integer.valueOf(this.O);
        this.bm = Integer.valueOf(this.P);
        if (this.K < 0) {
            this.K = 0;
        }
        if (this.bm > 10) {
            this.bm = 10;
        }
        else if (this.bm < 1) {
            this.bm = 1;
        }
        this.bt = Integer.valueOf(this.M);
        this.j = Integer.valueOf(this.Q);
        this.i = Integer.valueOf(this.R);
        this.h = Integer.valueOf(this.S);
        this.bl = Integer.valueOf(this.T);
        this.bK = Integer.valueOf(this.U);
        this.t = Integer.valueOf(this.X) + 122;
        this.bu = Integer.valueOf(this.Y);
        this.Z = Integer.valueOf(this.N);
        this.N = this.getParameter(c("K5eYaU/tI}"));
        if (this.N == null) {
            this.N = "3";
        }
        this.bJ = Integer.valueOf(this.N);
        if (this.bt > 8) {
            this.bt = 8;
        }
        else if (this.bt < 1) {
            this.bt = 1;
        }
        final Dimension size = this.size();
        this.cd = size.width / this.bt;
        this.C = size.height / this.bt;
        final int n14 = this.cd - this.bu * 2 - 3;
        final int n15 = this.C - this.bu * 2 - 2;
        this.cg = n14 / this.bJ;
        this.cm = n15 / this.bJ;
        this.cn = n15 / this.bJ - 2;
        this.bq = this.cd * this.bt;
        this.bp = this.C * this.bt;
        this.br = new byte[4];
        this.B = new byte[4];
        this.m = new byte[4];
        this.br[0] = (byte)this.j;
        this.B[0] = (byte)this.i;
        this.m[0] = (byte)this.h;
        this.l = new byte[this.cg + 1][this.cm + 1];
        this.k = new byte[51][34];
        this.w();
        float n16 = 0.0f;
        float n17 = 0.0f;
        final float n18 = 49.0f / this.cg;
        final float n19 = 32.0f / this.cm;
        for (int n20 = 0; n20 < this.cm; ++n20) {
            final int n21 = (int)n17;
            for (int n22 = 0; n22 < this.cg; ++n22) {
                n16 += n18;
                this.l[n22][n20] = this.k[(int)n16][n21];
            }
            n17 += n19;
            n16 = 0.0f;
        }
        this.s = new IndexColorModel(2, 4, this.br, this.B, this.m);
        this.bW = this.cd * this.C;
        this.o = new byte[this.bW];
        this.r = new byte[this.bW];
        this.cf = 0;
        while (this.cf < this.bW) {
            this.r[this.cf] = 0;
            ++this.cf;
        }
        this.bM = 256;
        this.bN = this.bM - 1;
        this.bL = new int[this.bM];
        final double n23 = 3.141592653589793 / this.bN;
        final int n24 = this.bu + 2;
        for (int n25 = 0; n25 < this.bM; ++n25) {
            this.bL[n25] = (int)(Math.sin(n25 * 4 * n23) * this.bu + n24);
        }
        try {
            this.t();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.t();
        }
        this.B();
        this.bg = this.createImage(this.bq, this.bp + this.bE);
        this.bf = this.bg.getGraphics();
        if (!this.bs) {
            (this.ce = new Lware(this.getAppletContext(), new Label(c("z2{DBW=z\u001deK,qXp\u001b>d\u001dBZ>tR$x5h^gR|,\u0004=\rs$\u0005*")))).setTitle(c("z2{DBW=z\u001dEK,qXp\u001b>d\u001dBZ>tR$x5h^gR"));
            this.ce.hide();
        }
    }
    
    void t() {
        this.bo = new MemoryImageSource(this.cd, this.C, this.s, this.o, 0, this.cd);
        String s;
        try {
            s = System.getProperty(c("Q=k\\*M9oNmT2"));
        }
        catch (SecurityException ex) {
            s = c("N2v");
        }
        if (!s.startsWith(c("\nr-"))) {
            try {
                this.bo.setAnimated(true);
                this.bo.setFullBufferUpdates(true);
                this.F = this.createImage(this.bo);
                this.bo.newPixels();
                this.H = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.H = false;
            }
        }
        if (!this.H) {
            this.bo = null;
            this.bn = new anfy(this.cd, this.C, this.s, this.o, 0, this.cd);
            this.F = this.createImage(this.bn);
        }
    }
    
    public final void u() {
        for (int i = 0; i < this.co; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = this.co / 7; k < this.co / 5; ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k] = 2;
            }
        }
        for (int n = this.co - this.co / 5; n < this.co - this.co / 7; ++n) {
            for (int n2 = 0; n2 < this.ch; ++n2) {
                this.k[n2][n] = 2;
            }
        }
        for (int n3 = 8; n3 < 16; ++n3) {
            for (int n4 = 18; n4 < 26; ++n4) {
                this.k[n4][n3] = AnFlag.u[n4 - 18 + (n3 - 8) * 8];
            }
        }
        for (int n5 = 16; n5 < 24; ++n5) {
            for (int n6 = 18; n6 < 26; ++n6) {
                this.k[n6][n5] = AnFlag.u[n6 - 18 + (56 - (n5 - 16) * 8)];
            }
        }
        for (int n7 = 8; n7 < 16; ++n7) {
            for (int n8 = 26; n8 < 34; ++n8) {
                this.k[n8][n7] = AnFlag.u[7 - (n8 - 26) + (n7 - 8) * 8];
            }
        }
        for (int n9 = 16; n9 < 24; ++n9) {
            for (int n10 = 26; n10 < 34; ++n10) {
                this.k[n10][n9] = AnFlag.u[7 - (n10 - 26) + (56 - (n9 - 16) * 8)];
            }
        }
    }
    
    public final void v() {
        for (int i = 0; i < this.co; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = 8; k < 16; ++k) {
            for (int l = 18; l < 26; ++l) {
                this.k[l][k] = AnFlag.q[l - 18 + (k - 8) * 8];
            }
        }
        for (int n = 16; n < 24; ++n) {
            for (int n2 = 18; n2 < 26; ++n2) {
                this.k[n2][n] = AnFlag.q[n2 - 18 + (56 - (n - 16) * 8)];
            }
        }
        for (int n3 = 8; n3 < 16; ++n3) {
            for (int n4 = 26; n4 < 34; ++n4) {
                this.k[n4][n3] = AnFlag.q[7 - (n4 - 26) + (n3 - 8) * 8];
            }
        }
        for (int n5 = 16; n5 < 24; ++n5) {
            for (int n6 = 26; n6 < 34; ++n6) {
                this.k[n6][n5] = AnFlag.q[7 - (n6 - 26) + (56 - (n5 - 16) * 8)];
            }
        }
    }
    
    public final void w() {
        switch (this.Z) {
            case 1: {
                this.s();
                this.a((byte)0, (byte)(-1), (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0);
            }
            case 2: {
                this.H();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)0, (byte)(-1), (byte)0, (byte)0);
            }
            case 3: {
                this.s();
                this.a((byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)20, (byte)20);
            }
            case 4: {
                this.s();
                this.a((byte)0, (byte)(-31), (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-56), (byte)0);
            }
            case 5: {
                this.H();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0);
            }
            case 6: {
                this.H();
                this.a((byte)0, (byte)0, (byte)0, (byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)0);
            }
            case 7: {
                this.H();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)(-1));
            }
            case 8: {
                this.s();
                this.a((byte)0, (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)0, (byte)(-1), (byte)0, (byte)0);
            }
            case 9: {
                this.H();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)127, (byte)(-96), (byte)(-1));
            }
            case 10: {
                this.g();
                this.a((byte)100, (byte)(-78), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)0, (byte)0);
            }
            case 11: {
                this.h();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)100, (byte)(-126), (byte)(-1));
            }
            case 12: {
                this.h();
                this.a((byte)50, (byte)(-126), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0);
            }
            case 13: {
                this.g();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-86), (byte)0, (byte)0, (byte)0);
            }
            case 14: {
                this.g();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)0);
            }
            case 15: {
                this.g();
                this.a((byte)(-1), (byte)(-1), (byte)(-1), (byte)100, (byte)(-81), (byte)(-1), (byte)0, (byte)0, (byte)0);
            }
            case 16: {
                this.G();
                this.a((byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)0, (byte)0, (byte)0);
            }
            case 17: {
                this.H();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)(-1), (byte)0);
            }
            case 18: {
                this.D();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)0);
            }
            case 19: {
                this.J();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)0, (byte)(-1), (byte)0, (byte)0, (byte)0, (byte)(-1));
            }
            case 20: {
                this.H();
                this.a((byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)30, (byte)30);
            }
            case 21: {
                this.v();
                this.a((byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)0, (byte)0, (byte)0);
            }
            case 22: {
                this.u();
                this.a((byte)(-1), (byte)(-1), (byte)(-1), (byte)100, (byte)(-101), (byte)(-1), (byte)0, (byte)0, (byte)0);
            }
            case 23: {
                this.r();
                this.a((byte)(-1), (byte)(-1), (byte)(-1), (byte)100, (byte)(-101), (byte)(-1), (byte)0, (byte)0, (byte)0);
            }
            case 24: {
                this.F();
                this.a((byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)30, (byte)100, (byte)(-1));
            }
            case 25: {
                this.d();
                this.a((byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0, (byte)0, (byte)0, (byte)0);
            }
            case 26: {
                this.a();
                this.a((byte)10, (byte)50, (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0);
            }
            case 27: {
                this.x();
                this.a((byte)100, (byte)(-106), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0);
            }
            case 28: {
                this.E();
                this.a((byte)100, (byte)(-106), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0);
            }
            case 29: {
                this.H();
                this.a((byte)100, (byte)(-56), (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)100, (byte)(-56), (byte)(-1));
            }
            case 30: {
                this.s();
                this.a((byte)(-1), (byte)0, (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)0, (byte)0);
            }
            case 31: {
                this.H();
                this.a((byte)(-1), (byte)(-1), (byte)0, (byte)(-106), (byte)(-56), (byte)(-1), (byte)(-1), (byte)0, (byte)0);
            }
            case 32: {
                this.s();
                this.a((byte)0, (byte)(-1), (byte)0, (byte)(-1), (byte)(-1), (byte)(-1), (byte)(-1), (byte)88, (byte)0);
            }
            case 33: {
                this.b();
                this.a((byte)0, (byte)(-56), (byte)0, (byte)(-1), (byte)(-1), (byte)(-106), (byte)100, (byte)(-126), (byte)(-1));
            }
            case 34: {
                this.f();
                this.a((byte)(-1), (byte)(-1), (byte)100, (byte)100, (byte)(-56), (byte)(-1), (byte)(-1), (byte)30, (byte)0);
            }
            default: {}
        }
    }
    
    public final void a(final byte b, final byte b2, final byte b3, final byte b4, final byte b5, final byte b6, final byte b7, final byte b8, final byte b9) {
        this.br[1] = b;
        this.B[1] = b2;
        this.m[1] = b3;
        this.br[2] = b4;
        this.B[2] = b5;
        this.m[2] = b6;
        this.br[3] = b7;
        this.B[3] = b8;
        this.m[3] = b9;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bs) {
            this.ce.show();
            this.ce.toFront();
            this.ce.requestFocus();
        }
        else if (this.J != null) {
            if (this.bb) {
                this.getAppletContext().showDocument(this.J, this.getParameter(c("I9z[vZ1xSeV9")));
            }
            else {
                this.getAppletContext().showDocument(this.J);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bO);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final void x() {
        final byte[][] array = new byte[this.ch / 2 + 1][this.co / 2 + 1];
        this.E();
        for (int i = 0; i < this.co; ++i) {
            for (int j = 0; j < this.ch - 1; ++j) {
                array[j / 2][i / 2] = this.k[j + 1][i];
            }
        }
        for (int k = 0; k < this.co; ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k] = 1;
            }
        }
        for (int n = 0; n < this.co / 2; ++n) {
            for (int n2 = 0; n2 < this.ch / 2; ++n2) {
                this.k[n2][n] = array[n2][n];
            }
        }
        for (int n3 = 14; n3 < 18; ++n3) {
            for (int n4 = 42; n4 < 46; ++n4) {
                this.k[n4][n3] = AnFlag.bc[n4 - 42 + (n3 - 14) * 4];
            }
        }
        for (int n5 = 14; n5 < 18; ++n5) {
            for (int n6 = 30; n6 < 34; ++n6) {
                this.k[n6][n5] = AnFlag.bc[n6 - 30 + (n5 - 14) * 4];
            }
        }
        for (int n7 = 6; n7 < 10; ++n7) {
            for (int n8 = 36; n8 < 40; ++n8) {
                this.k[n8][n7] = AnFlag.bc[n8 - 36 + (n7 - 6) * 4];
            }
        }
        for (int n9 = 26; n9 < 30; ++n9) {
            for (int n10 = 36; n10 < 40; ++n10) {
                this.k[n10][n9] = AnFlag.bc[n10 - 36 + (n9 - 26) * 4];
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.F != null) {
            if (this.bt == 1) {
                this.bf.drawImage(this.F, 0, 0, this);
            }
            else {
                this.z();
                this.bf.drawImage(this.F, 0, 0, this.bq, this.bp, this);
            }
            if (this.bi != null) {
                this.y();
            }
            if (this.bS) {
                this.b(this.bf);
            }
            graphics.drawImage(this.bg, 0, 0, this);
        }
    }
    
    public synchronized void y() {
        if (this.c) {
            this.notifyAll();
            while (!this.bh) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bh = false;
        }
        this.bf.drawImage(this.bi, this.bj, this.bk, this);
    }
    
    public synchronized void z() {
        int checkImage = 0;
        this.prepareImage(this.F, this.bq, this.bp, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.F, this.bq, this.bp, this);
        }
    }
    
    public final void A() {
        try {
            if (this.H) {
                this.bo.newPixels();
                return;
            }
            this.bn.startProduction(this.bn.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.bV.setPriority(this.bm);
        this.showStatus("");
        System.gc();
        this.v = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.bi != null && !this.c) {
            this.c = this.e();
        }
        if (this.J != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bV != null) {
            try {
                System.arraycopy(this.r, 0, this.o, 0, this.bW);
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                this.stop();
            }
            catch (ArrayStoreException ex2) {
                this.stop();
            }
            switch (this.bl) {
                case 1: {
                    this.i();
                    break;
                }
                case 2: {
                    this.j();
                    break;
                }
                case 3: {
                    this.o();
                    break;
                }
                case 4: {
                    this.p();
                    break;
                }
                case 5: {
                    this.q();
                    break;
                }
                case 6: {
                    this.k();
                    break;
                }
                case 7: {
                    this.m();
                    break;
                }
                case 8: {
                    this.n();
                    break;
                }
            }
            if (++this.p == this.K) {
                System.gc();
                this.p = 0;
            }
            try {
                this.A();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bt == 1) {
                this.bf.drawImage(this.F, 0, 0, this);
            }
            else {
                this.z();
                this.bf.drawImage(this.F, 0, 0, this.bq, this.bp, this);
            }
            if (this.bi != null) {
                this.y();
            }
            if (this.bS) {
                this.b(this.bf);
            }
            graphics.drawImage(this.bg, 0, 0, this);
            this.I();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void B() {
        this.bS = false;
        final String parameter = this.getParameter(c("O9eIwX.rQh"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("O9eIpB,x"));
            if (s == null) {
                s = c("S3oT~T2i\\h");
            }
            if (s.equals(c("S3oT~T2i\\h"))) {
                this.by = 0;
            }
            else if (s.equals(c("M9oImX=q"))) {
                this.by = 1;
            }
            else if (s.equals(c("A3rPmU;"))) {
                this.by = 2;
            }
            else if (s.equals(c("R2kGkT1tSc"))) {
                this.by = 3;
            }
            if (this.by == 0) {
                this.a(parameter, 0);
                if (this.bv != null) {
                    this.bS = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.I != null) {
                    this.bS = true;
                }
            }
        }
        if (this.bS) {
            String parameter2 = this.getParameter(c("O9eIwK9xY"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bY = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("O9eIbT2i"));
            if (s2 == null) {
                s2 = c("z.t\\h");
            }
            int n = 0;
            if (this.getParameter(c("O9eIfT0y")).equalsIgnoreCase(c("b\u0019N"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("O9eImO=qTg"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("b\u0019N"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("O9eIwR&x"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.z = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("O9eIwS=yRs")).equalsIgnoreCase(c("b\u0019N"))) {
                this.bT = true;
            }
            else {
                this.bT = false;
            }
            this.bP = new Color(Integer.valueOf(this.getParameter(c("o9eIGT0O"))), Integer.valueOf(this.getParameter(c("o9eIGT0Z"))), Integer.valueOf(this.getParameter(c("o9eIGT0_"))));
            this.bz = new Color(Integer.valueOf(this.getParameter(c("o9eIWx3qo"))), Integer.valueOf(this.getParameter(c("o9eIWx3qz"))), Integer.valueOf(this.getParameter(c("o9eIWx3q\u007f"))));
            this.bZ = this.size().width;
            this.ca = this.size().height;
            if (this.by == 0) {
                String parameter5 = this.getParameter(c("O9eIk]:nXp"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cp = Integer.valueOf(parameter5);
                if (this.cp < 0) {
                    this.cp = 0;
                }
                String parameter6 = this.getParameter(c("o9eINN1m|iK"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("o9eINN1mnt_"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bX = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("o9eIWR2x|iK"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bC = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("o9eIWR2xnt_"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bG = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("o9eIWR2x|j\\0x"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.bD = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.z);
                this.bx = fontMetrics.stringWidth(this.bv);
                this.bw = fontMetrics.getHeight();
                this.w = fontMetrics.getMaxDescent();
                this.ci = this.bZ;
                if (this.cp < this.bw - this.w) {
                    this.cp = this.bw - this.w;
                }
                else if (this.cp > this.ca - this.w) {
                    this.cp = this.ca - this.w;
                }
                if (this.bC != 0) {
                    this.bH = new int[this.bZ + 360];
                    this.bI = new int[this.bZ + 360];
                    for (int i = 0; i < this.bZ + 360; ++i) {
                        this.bH[i] = (int)(this.bC * Math.sin(this.bD * i * 3.141592653589793 / 180.0)) - this.bw - this.w + this.cp;
                        this.bI[i] = this.bH[i] - this.bp;
                    }
                    this.bB = 360;
                    this.bE = this.bw + this.w + 1;
                    this.bF = this.bE - 1;
                }
            }
            else {
                if (this.by == 1) {
                    String parameter11 = this.getParameter(c("O9eIrH,|^a"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.z);
                    this.A = fontMetrics2.getHeight() + intValue;
                    this.cc = new int[this.I.length];
                    for (int j = 0; j < this.I.length; ++j) {
                        this.cc[j] = (this.bZ - fontMetrics2.stringWidth(this.I[j])) / 2;
                    }
                    this.ba = -this.A;
                    return;
                }
                if (this.by >= 2) {
                    String parameter12 = this.getParameter(c("O9eIiR2{RjO"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bR = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("O9eIiZ${RjO"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bQ = Integer.valueOf(parameter13);
                    this.bd = this.bQ - this.bR;
                    this.z = null;
                    this.ct = new Font[this.bd];
                    int br = this.bR;
                    for (int k = 0; k < this.bd; ++k) {
                        this.ct[k] = new Font(s2, n, br++);
                    }
                    this.E = this.bZ / 2.0f;
                    this.D = this.ca / 2.0f;
                    if (this.by == 3) {
                        this.cs = this.bd - 1;
                        return;
                    }
                    this.cs = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.by) {
            case 0: {
                this.a(graphics);
            }
            case 1: {
                this.c(graphics);
            }
            default: {
                this.d(graphics);
            }
        }
    }
    
    public void start() {
        if (this.bV == null) {
            (this.bV = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bV != null && this.bV.isAlive()) {
            this.bV.stop();
        }
        this.bV = null;
    }
    
    public final void C() {
        for (int i = 0; i < this.co; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = 0; k < this.co / 9; ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k] = 2;
            }
        }
        for (int n = this.co / 9 * 2; n < this.co / 9 * 3; ++n) {
            for (int n2 = 0; n2 < this.ch; ++n2) {
                this.k[n2][n] = 2;
            }
        }
        for (int n3 = this.co / 9 * 4; n3 < this.co / 9 * 5; ++n3) {
            for (int n4 = 0; n4 < this.ch; ++n4) {
                this.k[n4][n3] = 2;
            }
        }
        for (int n5 = this.co / 9 * 6; n5 < this.co / 9 * 7; ++n5) {
            for (int n6 = 0; n6 < this.ch; ++n6) {
                this.k[n6][n5] = 2;
            }
        }
        for (int n7 = this.co / 9 * 8; n7 < this.co / 9 * 9; ++n7) {
            for (int n8 = 0; n8 < this.ch; ++n8) {
                this.k[n8][n7] = 2;
            }
        }
        for (int n9 = this.co / 9 * 10; n9 < this.co / 9 * 11; ++n9) {
            for (int n10 = 0; n10 < this.ch; ++n10) {
                this.k[n10][n9] = 2;
            }
        }
        for (int n11 = 0; n11 < this.co / 9 * 5; ++n11) {
            for (int n12 = 0; n12 < this.ch / 32 * 22; ++n12) {
                this.k[n12][n11] = 2;
            }
        }
    }
    
    public final void D() {
        for (int i = 0; i < this.co; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = this.co / 9 * 4; k < this.co / 9 * 7; ++k) {
            for (int l = this.ch / 32 * 9; l < this.ch / 32 * 37; ++l) {
                this.k[l][k] = 2;
            }
        }
        for (int n = this.ch / 32 * 19; n < this.ch / 32 * 28; ++n) {
            for (int n2 = this.co / 10; n2 < this.co / 10 * 10; ++n2) {
                this.k[n][n2] = 2;
            }
        }
    }
    
    public final void E() {
        for (int i = 0; i < this.co; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = this.co / 20 * 12; k < this.co / 20 * 19; ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k + 1] = 2;
            }
        }
        for (int n = this.ch / 32 * 22; n < this.ch / 32 * 29; ++n) {
            for (int n2 = 0; n2 < this.co; ++n2) {
                this.k[n][n2] = 2;
            }
        }
        for (int n3 = this.co / 20 * 13; n3 < this.co / 20 * 18; ++n3) {
            for (int n4 = 0; n4 < this.ch; ++n4) {
                this.k[n4][n3 + 1] = 3;
            }
        }
        for (int n5 = this.ch / 32 * 23; n5 < this.ch / 32 * 28; ++n5) {
            for (int n6 = 0; n6 < this.co; ++n6) {
                this.k[n5][n6] = 3;
            }
        }
        for (int n7 = 0; n7 < 12; ++n7) {
            for (int n8 = 0; n8 < 22; ++n8) {
                this.k[n8][n7 + 1] = AnFlag.x[n8 + n7 * 22];
            }
        }
        for (int n9 = 19; n9 < 31; ++n9) {
            for (int n10 = 0; n10 < 22; ++n10) {
                this.k[n10][n9 + 1] = AnFlag.x[n10 + (242 - (n9 - 19) * 22)];
            }
        }
        for (int n11 = 0; n11 < 12; ++n11) {
            for (int n12 = 29; n12 < 51; ++n12) {
                this.k[n12][n11 + 1] = AnFlag.x[21 - (n12 - 29) + n11 * 22];
            }
        }
        for (int n13 = 19; n13 < 31; ++n13) {
            for (int n14 = 29; n14 < 51; ++n14) {
                this.k[n14][n13 + 1] = AnFlag.x[21 - (n14 - 29) + (242 - (n13 - 19) * 22)];
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void F() {
        this.C();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < this.ch / 32 * 22; ++j) {
                this.k[j][i] = AnFlag.cb[j + i * 22];
            }
        }
        for (int k = 4; k < 8; ++k) {
            for (int l = 0; l < this.ch / 32 * 22; ++l) {
                this.k[l][k] = AnFlag.cb[l + (k - 4) * 22];
            }
        }
        for (int n = 8; n < 12; ++n) {
            for (int n2 = 0; n2 < this.ch / 32 * 22; ++n2) {
                this.k[n2][n] = AnFlag.cb[n2 + (n - 8) * 22];
            }
        }
        for (int n3 = 12; n3 < 16; ++n3) {
            for (int n4 = 0; n4 < this.ch / 32 * 22; ++n4) {
                this.k[n4][n3] = AnFlag.cb[n4 + (n3 - 12) * 22];
            }
        }
    }
    
    public final void G() {
        for (int i = 0; i < this.co / 2; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = this.co / 2; k < this.co; ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k] = 2;
            }
        }
    }
    
    public final void H() {
        for (int i = 0; i < this.co / 3; ++i) {
            for (int j = 0; j < this.ch; ++j) {
                this.k[j][i] = 1;
            }
        }
        for (int k = this.co / 3; k < 2 * (this.co / 3); ++k) {
            for (int l = 0; l < this.ch; ++l) {
                this.k[l][k] = 2;
            }
        }
        for (int n = 2 * (this.co / 3); n < this.co; ++n) {
            for (int n2 = 0; n2 < this.ch; ++n2) {
                this.k[n2][n] = 3;
            }
        }
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.z);
        this.ba += this.bY;
        if (this.ba > this.ca + this.I.length * this.A) {
            this.ba = -this.A;
        }
        if (this.bT) {
            for (int i = 0; i < this.I.length; ++i) {
                final String s = this.I[i];
                final int n = this.cc[i];
                final int n2 = this.ca - this.ba + i * this.A;
                graphics.setColor(this.bz);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bP);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bP);
        for (int j = 0; j < this.I.length; ++j) {
            graphics.drawString(this.I[j], this.cc[j], this.ca - this.ba + j * this.A);
        }
    }
    
    public synchronized void I() {
        Thread.yield();
        this.bU.sync();
        final long n = 10L - (System.currentTimeMillis() - this.v);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.v = System.currentTimeMillis();
        try {
            Thread.sleep(this.L);
        }
        catch (InterruptedException ex3) {}
    }
    
    public final void J() {
        for (int n = 0; n < this.co / 2.5f; ++n) {
            for (int i = 0; i < this.ch; ++i) {
                this.k[i][n] = 1;
            }
        }
        for (int j = (int)(this.co / 2.5f); j < 2 * (this.co / 3); ++j) {
            for (int k = 0; k < this.ch; ++k) {
                this.k[k][j] = 2;
            }
        }
        for (int l = 2 * (int)(this.co / 3.3); l < this.co; ++l) {
            for (int n2 = 0; n2 < this.ch; ++n2) {
                this.k[n2][l] = 3;
            }
        }
        float n3 = 3.0f;
        for (int n4 = 0; n4 < 16; ++n4) {
            for (int n5 = 0; n5 < (int)n3; ++n5) {
                this.k[n5][n4] = 2;
            }
            n3 += 1.7;
        }
        for (int n6 = 16; n6 < 32; ++n6) {
            for (int n7 = 0; n7 < (int)n3; ++n7) {
                this.k[n7][n6] = 2;
            }
            n3 -= 1.7;
        }
        float n8 = 3.0f;
        for (int n9 = 8; n9 < 16; ++n9) {
            for (int n10 = 1; n10 < (int)n8; ++n10) {
                this.k[n10][n9] = 0;
            }
            n8 += 1.7;
        }
        for (int n11 = 16; n11 < 24; ++n11) {
            for (int n12 = 1; n12 < (int)n8; ++n12) {
                this.k[n12][n11] = 0;
            }
            n8 -= 1.7;
        }
        for (int n13 = 8; n13 < 24; ++n13) {
            this.k[0][n13] = 3;
        }
    }
    
    public void d(final Graphics graphics) {
        final String s = this.I[this.cr];
        graphics.setFont(this.ct[this.cs]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.ct[this.cs]);
        final int n = (int)(this.E - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.D + fontMetrics.getHeight() / 4.0f);
        if (this.bT) {
            graphics.setColor(this.bz);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bP);
        graphics.drawString(s, n, n2);
        if (this.by == 3) {
            this.cs -= this.bY;
            if (this.cs <= 1) {
                this.cs = this.bd - 1;
                ++this.cr;
                if (this.cr >= this.I.length) {
                    this.cr = 0;
                }
            }
        }
        else {
            this.cs += this.bY;
            if (this.cs >= this.bd) {
                this.cs = 0;
                ++this.cr;
                if (this.cr >= this.I.length) {
                    this.cr = 0;
                }
            }
        }
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = ';';
                char[] array2;
                char[] array = array2 = charArray;
                int n4;
                int n3 = n4 = n;
                while (true) {
                    array[n3] = (char)(c ^ array2[n4]);
                    Label_0047: {
                        Label_0039: {
                            Label_0029: {
                            Label_0021:
                                while (true) {
                                    ++n;
                                    ++n2;
                                    if (length == n) {
                                        break Label_0010;
                                    }
                                    switch (n2) {
                                        case 5: {
                                            continue Label_0010;
                                        }
                                        case 1: {
                                            break Label_0021;
                                        }
                                        case 2: {
                                            break Label_0029;
                                        }
                                        case 3: {
                                            break Label_0039;
                                        }
                                        case 4: {
                                            break Label_0047;
                                        }
                                        default: {
                                            continue;
                                        }
                                    }
                                }
                                c = '\\';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '\u001d';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '=';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '\u0004';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
