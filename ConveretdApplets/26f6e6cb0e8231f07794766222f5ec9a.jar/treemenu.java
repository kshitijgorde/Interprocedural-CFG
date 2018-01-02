import java.awt.Event;
import java.applet.AppletContext;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.image.ImageConsumer;
import java.awt.image.PixelGrabber;
import java.awt.Insets;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Font;
import java.net.URL;
import java.awt.Color;
import java.awt.Image;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class treemenu extends Applet implements Runnable, ImageObserver
{
    float a;
    Frame b;
    boolean c;
    final String d = "\u001ecUw\u0019+3Gb\\\u001e}Cb\\\u000bvDv\\wdRlR>}Cb";
    boolean e;
    Image f;
    Color g;
    int h;
    Color i;
    int j;
    String k;
    int[] l;
    int[] m;
    int[] n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int[] t;
    int u;
    Image v;
    int[] w;
    int x;
    int y;
    int z;
    int A;
    int B;
    int C;
    int D;
    private boolean E;
    int F;
    int G;
    int H;
    int I;
    long J;
    int K;
    boolean L;
    Color M;
    int N;
    int O;
    boolean P;
    boolean Q;
    int R;
    Color S;
    int T;
    Color U;
    int V;
    Frame W;
    final int X = 1;
    final int Y = 2;
    final int Z = 3;
    final int ba = 0;
    int[][] bb;
    int[] bc;
    int[] bd;
    private Image be;
    int[][] bf;
    int[][] bg;
    boolean[][] bh;
    int[] bi;
    private int bj;
    int[] bk;
    boolean[][] bl;
    int[] bm;
    boolean[] bn;
    int[] bo;
    boolean[] bp;
    int bq;
    int br;
    int bs;
    int bt;
    String[] bu;
    String[] bv;
    private int bw;
    boolean bx;
    int by;
    final int bz = 6;
    final int bA = 7;
    final int bB = 11;
    final int bC = 9;
    final int bD = 8;
    final int bE = 12;
    final int bF = 10;
    URL bG;
    private boolean bH;
    final int bI = 5;
    String bJ;
    final int bK = 4;
    int bL;
    Thread bM;
    int bN;
    Font bO;
    int bP;
    boolean bQ;
    int bR;
    int bS;
    int bT;
    int bU;
    boolean bV;
    boolean bW;
    int bX;
    int bY;
    MediaTracker bZ;
    int ca;
    boolean cb;
    boolean cc;
    int[] cd;
    boolean[] ce;
    short[] cf;
    int cg;
    int ch;
    int ci;
    int cj;
    int ck;
    int cl;
    int cm;
    final int cn = 13;
    final int co = 5;
    private Graphics cp;
    private Image cq;
    boolean cr;
    int cs;
    int ct;
    int cu;
    int cv;
    int cw;
    int cx;
    int cy;
    int[] cz;
    boolean[] cA;
    short[] cB;
    int cC;
    boolean cD;
    private Image cE;
    int cF;
    int cG;
    int cH;
    anfy cI;
    MemoryImageSource cJ;
    int cK;
    int cL;
    int cM;
    int cN;
    String cO;
    boolean cP;
    int cQ;
    int cR;
    final int cS = -4;
    int cT;
    final int cU = -3;
    final int cV = -2;
    int cW;
    final int cX = -5;
    int cY;
    int cZ;
    final int da = -8;
    int db;
    final int dc = -9;
    final int dd = -7;
    int de;
    final int df = -6;
    int dg;
    int dh;
    int di;
    int[] dj;
    boolean[] dk;
    int dl;
    short[] dm;
    int[][] dn;
    boolean[][] do;
    Color[] dp;
    int[] dq;
    int dr;
    private Toolkit ds;
    Thread dt;
    int du;
    int dv;
    float[] dw;
    float[] dx;
    int dy;
    int dz;
    int dA;
    int dB;
    int dC;
    int dD;
    int dE;
    int dF;
    boolean dG;
    float[] dH;
    float[] dI;
    int dJ;
    int dK;
    private int dL;
    Lware dM;
    
    final void a(final int n, final int n2) {
        final int[] array = new int[this.bm[n]];
        final int n3 = this.bm[n];
        final int[] array2 = this.bf[n];
        for (int i = 0; i < n3; ++i) {
            array[i] = array2[i];
        }
        final int[] bm = this.bm;
        ++bm[n];
        int n4 = this.bm[n];
        this.bf[n] = new int[n4];
        final int[] array3 = this.bf[n];
        --n4;
        for (int j = 0; j < n4; ++j) {
            array3[j] = array[j];
        }
        array3[this.bm[n] - 1] = n2;
    }
    
    private final void a(final int n) {
        ++this.H;
        final int n2 = this.bm[n];
        if (this.bL <= this.H) {
            this.bL = this.H + 1;
        }
        for (int i = 0; i < n2; ++i) {
            final int n3 = this.bf[n][i];
            if (i == n2 - 1) {
                this.a(n3, this.H, true);
            }
            else {
                this.a(n3, this.H, false);
            }
            if (this.bn[n3]) {
                this.a(n3);
            }
        }
        --this.H;
    }
    
    private final void a(final int n, final int n2, final boolean b) {
        final int[] array = new int[this.cm];
        final short[] array2 = new short[this.cm];
        final boolean[] array3 = new boolean[this.cm];
        for (int i = 0; i < this.cm; ++i) {
            array[i] = this.dj[i];
            array2[i] = this.dm[i];
            array3[i] = this.dk[i];
        }
        ++this.cm;
        this.dj = new int[this.cm];
        this.dm = new short[this.cm];
        this.dk = new boolean[this.cm];
        for (int j = 0; j < this.cm - 1; ++j) {
            this.dj[j] = array[j];
            this.dm[j] = array2[j];
            this.dk[j] = array3[j];
        }
        this.dj[this.cm - 1] = n;
        this.dm[this.cm - 1] = (short)n2;
        this.dk[this.cm - 1] = b;
    }
    
    private final void a() {
        this.ck = this.cm;
        this.cd = new int[this.ck];
        this.cf = new short[this.ck];
        this.ce = new boolean[this.ck];
        for (int i = 0; i < this.ck; ++i) {
            this.cd[i] = this.dj[i];
            this.cf[i] = this.dm[i];
            this.ce[i] = this.dk[i];
        }
    }
    
    private final void b() {
        while (true) {
            this.showStatus(d("\u001b|K<\b\u007fa@v\u0013)v\u0005l\u000b(=Du\u001a&g@z\u0011qpJv\\<a@\u007f\u0015+`\u0005w\u00151v\u0005r\u0012\u007f[qV0~"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean c() {
        this.prepareImage(this.cE, this);
        if (this.bx) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.cr;
        }
        return false;
    }
    
    private final void d() {
        if (this.cl < 1 | this.cl == this.cm) {
            return;
        }
        this.dL = 0;
        this.a();
        if (this.cl > this.cm) {
            int cc = this.cm - 1;
            for (int i = 0; i < this.cm; ++i) {
                if (this.dj[i] != this.cz[i]) {
                    cc = i - 1;
                    i = this.cm;
                }
            }
            int cs = this.cl - 1;
            final short n = this.cB[cc];
            for (int j = cc + 1; j < this.cl; ++j) {
                if (this.cB[j] <= n) {
                    cs = j - 1;
                    j = this.cl;
                }
            }
            this.cg = cc;
            this.ca = cc;
            this.cC = cc;
            this.cs = cs;
            return;
        }
        int cs2 = this.cl - 1;
        for (int k = 0; k < this.cl; ++k) {
            if (this.dj[k] != this.cz[k]) {
                cs2 = k - 1;
                k = this.cl;
            }
        }
        int ca = this.cm - 1;
        final short n2 = this.dm[cs2];
        for (int l = cs2 + 1; l < this.cm; ++l) {
            if (this.dm[l] <= n2) {
                ca = l - 1;
                l = this.cm;
            }
        }
        this.cg = cs2;
        this.ca = ca;
        this.cC = cs2;
        this.cs = cs2;
    }
    
    private void e() {
        for (int i = 0; i < this.cj; ++i) {
            if (this.bm[i] < 1 & this.bp[this.bk[i]]) {
                this.showStatus(d("\u0012vKn\\\u0016g@vF\u007f") + i + d("\u007fpJu\b>zKh\\>3Hz\f\u007fZFt\u0012\u007f;\u0016)\u0004n%\f;\u00151`Q~\u001d;3J}\\>3Kt\u000e2rI;5<|K;Tn%]*Jv"));
                this.stop();
            }
        }
    }
    
    private void f() {
        if (this.e) {
            if (this.bX > 2 & this.bX < this.x - 2 & this.bY > 2 & this.bY < this.u - 2) {
                if (this.bi[this.bX + this.bY * this.x] != -1) {
                    this.G = this.bi[this.bX + this.bY * this.x];
                }
                else {
                    this.G = this.by;
                }
            }
            else {
                this.G = this.by;
            }
            if (this.cD) {
                this.G = this.by;
            }
        }
        if (this.dG) {
            if (this.bR > -1 & this.bR < this.x & this.bT > -1 & this.bT < this.u) {
                this.di = this.bi[this.bR + this.bT * this.x];
                this.K = this.di;
            }
            this.dG = false;
        }
        if ((this.bR != this.bS | this.bT != this.bU) & this.bW & (this.K == -8 | this.K == -4)) {
            if (this.K == -8) {
                this.B = -((this.de + this.bT - this.bU) * this.cL / (this.cQ - this.cY * 2));
                this.bS = this.bR;
                this.bU = this.bT;
            }
            if (this.K == -4) {
                this.y = -((this.cW + this.bR - this.bS) * this.cM / (this.cR - this.cY * 2));
                this.bS = this.bR;
                this.bU = this.bT;
            }
            this.bQ = true;
            return;
        }
        if (this.bQ) {
            return;
        }
        this.bV = false;
        if (this.di > -1) {
            this.bn[this.di] = !this.bn[this.di];
            this.bQ = true;
            this.G = this.di;
            this.by = this.di;
            if (this.bv[this.di] != null) {
                this.b(this.di);
            }
            return;
        }
        this.bQ = true;
        switch (this.di) {
            case -6: {
                this.d(-1);
                this.bQ = false;
            }
            case -7: {
                this.d(0);
                this.bQ = false;
            }
            case -9: {
                this.d(1);
                this.bQ = false;
            }
            case -2: {
                this.c(-1);
                this.bQ = false;
            }
            case -3: {
                this.c(0);
                this.bQ = false;
            }
            case -5: {
                this.c(1);
                this.bQ = false;
            }
            default: {}
        }
    }
    
    public void g() {
        System.arraycopy(this.m, 0, this.t, 0, this.x * this.u);
        System.arraycopy(this.n, 0, this.bi, 0, this.x * this.u);
    }
    
    private final void h() {
        this.dj = new int[0];
        this.dm = new short[0];
        this.cm = 0;
    }
    
    public boolean a(final String s, final boolean b) {
        if (s == null) {
            return b;
        }
        if (s.length() < 1) {
            return b;
        }
        return s.equalsIgnoreCase(d("\u0006Vv"));
    }
    
    public int a(final String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 1) {
            return 0;
        }
        int int1;
        try {
            int1 = Integer.parseInt(s, 16);
        }
        catch (Exception ex) {
            int1 = 0;
        }
        return int1;
    }
    
    public int a(final Color color) {
        return 0xFF000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
    }
    
    public int a(final String s, final int n) {
        if (s == null) {
            return n;
        }
        if (s.length() < 1) {
            return n;
        }
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {
            int1 = n;
        }
        return int1;
    }
    
    private void a(final String[] array, final int n, final int n2, final int n3) {
        final boolean[] array2 = this.do[n];
        final int[] array3 = this.dn[n];
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n2; ++j) {
                final char c = (char)(array[i].charAt(j) - '0');
                final int n4 = i * n2 + j;
                if (c > -1) {
                    array3[n4] = this.dq[c];
                    array2[n4] = true;
                }
                else {
                    array2[n4] = false;
                }
            }
        }
    }
    
    public void a(final int[] array, int n, int n2, final int n3, final int n4, final int[] array2, int n5, int n6, final int n7, final int n8) {
        int n9 = n3;
        int n10 = n4;
        if (n5 < 0) {
            n -= n5;
            n9 += n5;
            n5 = 0;
        }
        if (n6 < 0) {
            n2 -= n6;
            n10 += n6;
            n6 = 0;
        }
        if (n5 + n9 > n7) {
            n9 = n7 - n5;
        }
        if (n6 + n10 > n8) {
            n10 = n8 - n6;
        }
        for (int i = 0; i < n10; ++i) {
            System.arraycopy(array, (n2 + i) * n3 + n, array2, (n6 + i) * n7 + n5, n9);
        }
    }
    
    final void a(final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int n6 = 0;
        int bs = this.bs;
        int br = this.br;
        int n7 = n;
        int n8 = n2;
        if (n7 < 0) {
            n5 = -n7;
            bs += n7;
            n7 = 0;
        }
        if (n8 < 0) {
            n6 = -n8;
            br += n8;
            n8 = 0;
        }
        if (n7 + bs > this.x - 1) {
            bs = this.x - 1 - n7;
        }
        if (n8 + br > this.u - 1) {
            br = this.u - 1 - n8;
        }
        if (bs < 0 | br < 0) {
            return;
        }
        this.b(this.dn[n3], this.do[n3], n5, n6, this.bs, this.t, n7, n8, this.x, bs, br);
        if (n4 != -1) {
            this.a(this.bi, n7, n8, this.x, bs, br, n4);
        }
    }
    
    private final void a(final int[] array, final int n, final int n2, final int n3, final int[] array2, final int n4, final int n5, final int n6, final int n7, final int n8) {
        int n9 = n2 * n3 + n;
        int n10 = n5 * n6 + n4;
        for (int i = 0; i < n8; ++i) {
            System.arraycopy(array, n9, array2, n10, n7);
            n9 += n3;
            n10 += n6;
        }
    }
    
    private final void a(final int[] array, final boolean[] array2, final int n, final int n2, final int n3, final int[] array3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        int n9 = n2 * n3 + n;
        int n10 = n5 * n6 + n4;
        if (n7 < 1 | n8 < 1) {
            return;
        }
        final int n11 = this.j & 0xFF0000;
        final int n12 = this.j & 0xFF00;
        final int n13 = this.j & 0xFF;
        for (int i = 0; i < n8; ++i) {
            for (int j = 0; j < n7; ++j) {
                if (array2[n9 + j]) {
                    array3[n10 + j] = this.V;
                }
                else {
                    array3[n10 + j] = (0xFF000000 | ((array3[n10 + j] & 0xFF0000) + n11 >> 1 & 0xFF0000) | ((array3[n10 + j] & 0xFF00) + n12 >> 1 & 0xFF00) | ((array3[n10 + j] & 0xFF) + n13 >> 1 & 0xFF));
                }
            }
            n9 += n3;
            n10 += n6;
        }
    }
    
    private final void b(final int[] array, final boolean[] array2, final int n, final int n2, final int n3, final int[] array3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        int n9 = n2 * n3 + n;
        int n10 = n5 * n6 + n4;
        for (int i = 0; i < n8; ++i) {
            for (int j = 0; j < n7; ++j) {
                final int n11 = n9 + j;
                if (array2[n11]) {
                    array3[n10 + j] = array[n11];
                }
            }
            n9 += n3;
            n10 += n6;
        }
    }
    
    private final void a(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        int n7 = n2 * n3;
        for (int i = 0; i < n5; ++i) {
            int n8 = n7 + n;
            for (int j = 0; j < n4; ++j) {
                array[n8++] = n6;
            }
            n7 += n3;
        }
    }
    
    final void a(final int n, final int n2, final int n3) {
        int n4 = 0;
        int n5 = 0;
        int n6 = this.bd[n3];
        int n7 = this.bc[n3];
        int n8 = n;
        int n9 = n2;
        if (n8 < 0) {
            n4 = -n8;
            n6 += n8;
            n8 = 0;
        }
        if (n9 < 0) {
            n5 = -n9;
            n7 += n9;
            n9 = 0;
        }
        if (n8 + n6 > this.x - 1) {
            n6 = this.x - 1 - n8;
        }
        if (n9 + n7 > this.u - 1) {
            n7 = this.u - 1 - n9;
        }
        if (n6 < 0 | n7 < 0) {
            return;
        }
        if (this.G != n3) {
            this.b(this.bb[n3], this.bl[n3], n4, n5, this.bd[n3], this.t, n8, n9, this.x, n6, n7);
        }
        else {
            this.a(this.bb[n3], this.bl[n3], n4, n5, this.bd[n3], this.t, n8, n9, this.x, n6, n7);
        }
        this.a(this.bi, n8, n9, this.x, n6, n7, n3);
    }
    
    final void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = 0;
        if (n5 == 1) {
            n6 = 16;
        }
        int n7 = 0;
        int n8 = 0;
        int br = this.br;
        int br2 = this.br;
        int n9 = n;
        int n10 = n2;
        if (n9 < 0) {
            n7 = -n9;
            br += n9;
            n9 = 0;
        }
        if (n10 < 0) {
            n8 = -n10;
            br2 += n10;
            n10 = 0;
        }
        if (n9 + br > this.x - 1) {
            br = this.x - 1 - n9;
        }
        if (n10 + br2 > this.u - 1) {
            br2 = this.u - 1 - n10;
        }
        if (br < 0 | br2 < 0) {
            return;
        }
        this.b(this.bg[n3], this.bh[n3], n7 + n6, n8, 32, this.t, n9, n10, this.x, br, br2);
        if (n4 != -1) {
            this.a(this.bi, n9, n10, this.x, br, br2, n4);
        }
    }
    
    final void b(final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int n6 = 0;
        int br = this.br;
        int br2 = this.br;
        int n7 = n;
        int n8 = n2;
        if (n7 < 0) {
            n5 = -n7;
            br += n7;
            n7 = 0;
        }
        if (n8 < 0) {
            n6 = -n8;
            br2 += n8;
            n8 = 0;
        }
        if (n7 + br > this.x - 1) {
            br = this.x - 1 - n7;
        }
        if (n8 + br2 > this.u - 1) {
            br2 = this.u - 1 - n8;
        }
        if (br < 0 | br2 < 0) {
            return;
        }
        this.b(this.bg[n3], this.bh[n3], n5, n6, this.br, this.t, n7, n8, this.x, br, br2);
        if (n4 != -1) {
            this.a(this.bi, n7, n8, this.x, br, br2, n4);
        }
    }
    
    public void i() {
        if (this.k == null) {
            this.k = "";
        }
        if (this.k.length() > 2) {
            final Image b = this.b(this.k);
            final int width = b.getWidth(this);
            final int height = b.getHeight(this);
            this.a(0, 0, width, height, b);
            final int n = this.x / width + 2;
            final int n2 = this.u / height + 2;
            final int n3 = -(n * width - this.x) / 2;
            final int n4 = -(n2 * height - this.u) / 2;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n2; ++j) {
                    this.a(this.l, 0, 0, width, height, this.m, n3 + i * width, n4 + j * height, this.x, this.u);
                }
            }
            return;
        }
        this.a(this.m, 0, 0, this.x, this.x, this.u, this.h);
    }
    
    public void j() {
        int i = 0;
        int n = 0;
        try {
            for (i = 1; i < this.cj; ++i) {
                n = this.bo[i];
                this.a(n, i);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.showStatus(d("\u001aaWt\u000e\u007fa@z\u00186}B;\u001f0}Cr\u001b*aDo\u00150}\u0005 \\6g@v5;)\u0005") + i + d("\u007fcDi\u00191gl\u007f\\e3") + n);
            this.stop();
        }
    }
    
    private final void k() {
        this.H = 0;
        this.I = 0;
        System.arraycopy(this.t, 0, this.w, 0, this.x * this.u);
        this.D = this.B;
        this.A = this.y;
        this.cl = this.cm;
        this.cz = new int[this.cl];
        this.cB = new short[this.cl];
        this.cA = new boolean[this.cl];
        for (int i = 0; i < this.cl; ++i) {
            this.cz[i] = this.dj[i];
            this.cB[i] = this.dm[i];
            this.cA[i] = this.dk[i];
        }
        this.h();
        final int n = 0;
        this.ct = this.bL;
        this.bL = 1;
        this.a(n, this.H, true);
        if (this.bn[n]) {
            this.a(n);
        }
        this.dl = 0;
    }
    
    private void l() {
        this.dn = new int[13][];
        this.do = new boolean[13][];
        for (int i = 0; i < 13; ++i) {
            this.dn[i] = new int[this.bs * this.br];
            this.do[i] = new boolean[this.bs * this.br];
        }
        final int n = this.cY * this.cY;
        this.dn[0] = new int[n];
        this.dn[1] = new int[n];
        this.dn[2] = new int[n];
        this.dn[3] = new int[n];
        final int n2 = this.cY - 1;
        this.a(this.dn[0], 0, 0, n2, n2);
        this.a(this.dn[1], 0, 0, n2, n2);
        this.a(this.dn[2], 0, 0, n2, n2);
        this.a(this.dn[3], 0, 0, n2, n2);
        final String[] array = new String[this.cY];
        int n3 = 0;
        if (this.cY == 11) {
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Lo=\u000b5Rq");
            array[n3++] = d("q=\u000b+Lo#\u000b5Rq");
            array[n3++] = d("q=\u0015+Lo#\u00155Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq");
        }
        else {
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u00155Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq#\u0015+Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Ro#\u0015+Lq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Lo#\u0015+Lo=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n3++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
        }
        this.a(array, 0, this.cY, this.cY);
        int n4 = 0;
        if (this.cY == 11) {
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u0015+Lo#\u00155Rq");
            array[n4++] = d("q=\u000b+Lo#\u000b5Rq");
            array[n4++] = d("q=\u000b5Lo=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq");
        }
        else {
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Lo#\u0015+Lo=\u000b5Rq");
            array[n4++] = d("q=\u000b5Ro#\u0015+Lq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq#\u0015+Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u00155Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n4++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
        }
        this.a(array, 1, this.cY, this.cY);
        int n5 = 0;
        if (this.cY == 11) {
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Ro=\u000b5Rq");
            array[n5++] = d("q=\u000b5Lo=\u000b5Rq");
            array[n5++] = d("q=\u000b+Lo=\u000b5Rq");
            array[n5++] = d("q=\u000b+Lo=\u000b5Rq");
            array[n5++] = d("q=\u000b5Lo=\u000b5Rq");
            array[n5++] = d("q=\u000b5Ro=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq");
        }
        else {
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b+Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u0015+Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq#\u0015+Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Ro#\u0015+Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq#\u0015+Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u0015+Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b+Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n5++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
        }
        this.a(array, 2, this.cY, this.cY);
        int n6 = 0;
        if (this.cY == 11) {
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Lq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Lo=\u000b5Rq");
            array[n6++] = d("q=\u000b5Lo#\u000b5Rq");
            array[n6++] = d("q=\u000b5Lo#\u000b5Rq");
            array[n6++] = d("q=\u000b5Lo=\u000b5Rq");
            array[n6++] = d("q=\u000b5Lq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq");
        }
        else {
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq#\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq#\u00155Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq#\u0015+Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq#\u0015+Lq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq#\u0015+Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq#\u00155Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq#\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
            array[n6++] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq");
        }
        this.a(array, 3, this.cY, this.cY);
        final int n7 = this.bs * this.br;
        for (int j = 6; j < 13; ++j) {
            for (int k = 0; k < n7; ++k) {
                this.do[j][k] = false;
            }
        }
        final String[] array2 = new String[this.br];
        array2[0] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[1] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[2] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[3] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[4] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[5] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[6] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[7] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[8] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[9] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[10] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[11] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[12] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[13] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[14] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[15] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        this.a(array2, 6, this.bs, this.br);
        array2[0] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[1] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[2] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[3] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[4] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[5] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[6] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[7] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[8] = d("q=\u000b5Rq=\u000b(Rl=\u00165Oq \u000b5");
        array2[9] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[10] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[11] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[12] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[13] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[14] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[15] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        this.a(array2, 7, this.bs, this.br);
        array2[0] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[1] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[2] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[3] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[4] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[5] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[6] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[7] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[8] = d("q=\u000b5Rq=\u000b(Rl=\u00165Oq \u000b5");
        array2[9] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[10] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[11] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[12] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[13] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[14] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[15] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        this.a(array2, 8, this.bs, this.br);
        array2[0] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[1] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[2] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[3] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[4] = d("q=\u000b5Ol \u0016(Ol \u00165Rq=\u000b5");
        array2[5] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[6] = d("q=\u000b5Oq=\u000b(Rq=\u00165Rq=\u000b5");
        array2[7] = d("q=\u000b5Oq=\u000b(Rq=\u00165Rq=\u000b5");
        array2[8] = d("q=\u000b5Oq \u0016(Ol=\u00165Oq \u000b(");
        array2[9] = d("q=\u000b5Oq=\u000b(Rq=\u00165Rq=\u000b5");
        array2[10] = d("q=\u000b5Oq=\u000b(Rq=\u00165Rq=\u000b5");
        array2[11] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[12] = d("q=\u000b5Ol \u0016(Ol \u00165Rq=\u000b5");
        array2[13] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[14] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[15] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        this.a(array2, 9, this.bs, this.br);
        array2[0] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[1] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[2] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[3] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[4] = d("q=\u000b5Ol \u0016(Ol \u00165Rq=\u000b5");
        array2[5] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[6] = d("q=\u000b5Oq=\u000b(Rq=\u00165Rq=\u000b5");
        array2[7] = d("q=\u000b5Oq=\u000b(Rq=\u00165Rq=\u000b5");
        array2[8] = d("q=\u000b5Oq \u0016(Ol=\u00165Oq \u000b(");
        array2[9] = d("q=\u000b5Oq=\u000b(Rq=\u00165Rq=\u000b5");
        array2[10] = d("q=\u000b5Oq=\u000b(Rq=\u00165Rq=\u000b5");
        array2[11] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[12] = d("q=\u000b5Ol \u0016(Ol \u00165Rq=\u000b5");
        array2[13] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[14] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[15] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        this.a(array2, 10, this.bs, this.br);
        array2[0] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[1] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[2] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[3] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[4] = d("q=\u000b5Ol \u0016(Ol \u00165Rq=\u000b5");
        array2[5] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[6] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[7] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[8] = d("q=\u000b5Oq \u0016(Ol=\u00165Oq \u000b(");
        array2[9] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[10] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[11] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[12] = d("q=\u000b5Ol \u0016(Ol \u00165Rq=\u000b5");
        array2[13] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[14] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[15] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        this.a(array2, 11, this.bs, this.br);
        array2[0] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[1] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[2] = d("q=\u000b5Rq=\u000b(Rq=\u000b5Rq=\u000b5");
        array2[3] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[4] = d("q=\u000b5Ol \u0016(Ol \u00165Rq=\u000b5");
        array2[5] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[6] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[7] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[8] = d("q=\u000b5Oq \u0016(Ol=\u00165Oq \u000b(");
        array2[9] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[10] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[11] = d("q=\u000b5Oq=\u000b5Rq=\u00165Rq=\u000b5");
        array2[12] = d("q=\u000b5Ol \u0016(Ol \u00165Rq=\u000b5");
        array2[13] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[14] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        array2[15] = d("q=\u000b5Rq=\u000b5Rq=\u000b5Rq=\u000b5");
        this.a(array2, 12, this.bs, this.br);
    }
    
    final void m() {
        final int n = 20;
        final int br = this.br;
        final Graphics graphics = this.createImage(n, br).getGraphics();
        graphics.setFont(this.bO);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n2 = 0;
        for (int i = 0; i < this.cj; ++i) {
            final int n3 = fontMetrics.stringWidth(this.bu[i]) + 8;
            if (n3 > n2) {
                n2 = n3;
            }
        }
        int n4 = 0;
        if (this.bt < 10) {
            n4 = 0;
        }
        if (this.bt >= 10) {
            n4 = 1;
        }
        if (this.bt > 11) {
            n4 = 2;
        }
        for (int j = 0; j < this.cj; ++j) {
            final int n5 = j;
            final Image image = this.createImage(n2, br);
            final Graphics graphics2 = image.getGraphics();
            graphics2.setFont(this.bO);
            final FontMetrics fontMetrics2 = graphics2.getFontMetrics();
            this.du = fontMetrics2.getAscent() - n4 + (this.br - this.bt) / 2;
            graphics2.drawString(this.bu[n5], 2, this.du);
            this.bd[n5] = fontMetrics2.stringWidth(this.bu[n5]) + 8;
            this.bc[n5] = br;
            if (this.bd[n5] > n2) {
                this.bd[n5] = n2 + 8;
            }
            this.a(0, 0, image, n5);
            this.bl[n5] = new boolean[this.bd[n5] * this.bc[n5]];
            final boolean[] array = this.bl[n5];
            final int[] array2 = this.bb[n5];
            for (int n6 = this.bd[n5] * this.bc[n5], k = 0; k < n6; ++k) {
                array[k] = true;
            }
            final int n7 = array2[0];
            for (int l = 0; l < br; ++l) {
                final int n8 = l * this.bd[n5];
                for (int n9 = 0; n9 < this.bd[n5]; ++n9) {
                    final int n10 = n9 + n8;
                    final int n11 = array2[n10];
                    if (n11 == n7 | (n11 & 0xFF000000) == 0x0) {
                        array[n10] = false;
                    }
                    else {
                        array2[n10] = this.T;
                    }
                }
            }
        }
    }
    
    private void n() {
        final float n = this.s / 100.0f;
        this.a = this.r / 100.0f;
        (this.dx = new float[this.dD])[0] = 1.0f;
        for (int i = 1; i < this.dD; ++i) {
            this.dx[i] = this.dx[i - 1] * n;
        }
        (this.dw = new float[this.dC])[0] = 1.0f;
        for (int j = 1; j < this.dC; ++j) {
            this.dw[j] = this.dw[j - 1] * n;
        }
    }
    
    private void o() {
        this.dE = 1000 / this.dg;
        this.dH = new float[this.dE * 2];
        this.dF = 1000 / this.dh;
        this.dI = new float[this.dF * 2];
        final double n = 1.5707963267948966 / this.dE;
        for (int i = 0; i < this.dE; ++i) {
            this.dH[i] = (float)Math.sin(i * n + 1.5707963267948966);
        }
        final double n2 = 1.5707963267948966 / this.dF;
        for (int j = 0; j < this.dF; ++j) {
            this.dI[j] = (float)Math.sin(j * n2 + 1.5707963267948966);
        }
        for (int k = 0; k < this.dE; ++k) {
            this.dH[this.dE + k] = this.dH[this.dE - k];
        }
        for (int l = 0; l < this.dF; ++l) {
            this.dI[this.dF + l] = this.dI[this.dF - l];
        }
    }
    
    private void p() {
        this.ch = 0;
        while (this.getParameter(d("\u0016pJu") + this.ch) != null) {
            ++this.ch;
        }
        this.bg = new int[this.ch][];
        this.bh = new boolean[this.ch][];
        this.bp = new boolean[this.ch];
        for (int i = 0; i < this.ch; ++i) {
            final Image b = this.b(this.getParameter(d("\u0016pJu") + i));
            final int width = b.getWidth(this);
            final int height = b.getHeight(this);
            if ((width != 32 & width != 16) | height != 16) {
                this.showStatus(d("\fz_~\\0u\u0005R\u001f0}") + i + d("\u007fzV;\u00151pJi\u000e:pQ5") + width + "x" + height);
                this.stop();
            }
            if (width == 32) {
                this.bp[i] = true;
            }
            else {
                this.bp[i] = false;
            }
            final int n = width * height;
            this.bg[i] = new int[n];
            this.bh[i] = new boolean[n];
            this.a(0, 0, width, height, b);
            final boolean[] array = new boolean[n];
            for (int j = 0; j < height; ++j) {
                final int n2 = j * width;
                for (int k = 0; k < width; ++k) {
                    final int n3 = k + n2;
                    final int n4 = this.l[n3];
                    if (n4 == this.N | (n4 & 0xFF000000) == 0x0) {
                        this.l[n3] = 0;
                        array[n3] = false;
                    }
                    else {
                        array[n3] = true;
                    }
                }
            }
            System.arraycopy(this.l, 0, this.bg[i], 0, n);
            System.arraycopy(array, 0, this.bh[i], 0, n);
        }
    }
    
    public void destroy() {
        this.cE = null;
        this.be = null;
        if (this.cp != null) {
            this.cp.dispose();
        }
        this.cp = null;
        System.gc();
    }
    
    private void q() {
        this.bo = new int[this.bq];
        this.bk = new int[this.bq];
        this.bu = new String[this.bq];
        this.bv = new String[this.bq];
        this.bf = new int[this.bq][];
        this.bm = new int[this.bq];
        this.bb = new int[this.bq][];
        this.bl = new boolean[this.bq][];
        this.bd = new int[this.bq];
        this.bc = new int[this.bq];
        this.bn = new boolean[this.bq];
    }
    
    private void a(final int[] array, final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.dq[3];
        this.a(array, n, n2, this.cY, n3 - n, 1, n5);
        this.a(array, n, n2, this.cY, 1, n4 - n2, n5);
        final int n6 = this.dq[4];
        this.a(array, n + 1, n2 + 1, this.cY, n3 - 1 - (n + 1), 1, n6);
        this.a(array, n + 1, n2 + 1, this.cY, 1, n4 - 1 - (n2 + 1), n6);
        this.a(array, n + 2, n2 + 2, this.cY, n3 - 1 - (n + 1), n4 - 1 - (n2 + 2), this.dq[2]);
        final int n7 = this.dq[1];
        this.a(array, n + 1, n4 - 1, this.cY, n3 - (n + 1), 1, n7);
        this.a(array, n3 - 1, n2 + 1, this.cY, 1, n4 - n2 - 1, n7);
        final int n8 = this.dq[0];
        this.a(array, n, n4, this.cY, n3 - n + 1, 1, n8);
        this.a(array, n3, n2, this.cY, 1, n4 - n2, n8);
    }
    
    private final void r() {
        final int[] array = { this.dq[2], this.dq[4] };
        final int n = 0;
        final int cr = this.cR;
        final int cq = this.cQ;
        final int n2 = this.cQ + this.cY;
        for (int i = cq; i < n2; ++i) {
            final int n3 = i * this.x;
            for (int j = n; j < cr; ++j) {
                this.t[n3 + j] = array[j + i & 0x1];
                this.bi[n3 + j] = -3;
            }
        }
        this.a(this.dn[2], 0, 0, this.cY, this.t, n, cq, this.x, this.cY, n2 - cq);
        this.a(this.bi, n, cq, this.x, this.cY, this.cY, -2);
        this.a(this.dn[3], 0, 0, this.cY, this.t, cr - this.cY, cq, this.x, this.cY, this.cY);
        this.a(this.bi, cr - this.cY, cq, this.x, this.cY, this.cY, -5);
        int ct = this.cR * (this.cR - this.cY * 2) / this.cM;
        if (ct < 6) {
            ct = 6;
        }
        this.cT = ct;
        this.cW = -this.y * (this.cR - this.cY * 2) / this.cM;
        final int n4 = this.cY + this.cW;
        final int n5 = n2 - this.cY;
        final int n6 = this.cY + this.cW + ct;
        final int n7 = n2 - 1;
        this.c(n4, n5, n6, n7);
        this.a(this.bi, n4, n5, this.x, n6 - n4, n7 - n5, -4);
    }
    
    private void c(final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.dq[3];
        this.a(this.t, n, n2, this.x, n3 - n, 1, n5);
        this.a(this.t, n, n2, this.x, 1, n4 - n2, n5);
        final int n6 = this.dq[4];
        this.a(this.t, n + 1, n2 + 1, this.x, n3 - 1 - (n + 1), 1, n6);
        this.a(this.t, n + 1, n2 + 1, this.x, 1, n4 - 1 - (n2 + 1), n6);
        this.a(this.t, n + 2, n2 + 2, this.x, n3 - 1 - (n + 1), n4 - 1 - (n2 + 2), this.dq[2]);
        final int n7 = this.dq[1];
        this.a(this.t, n + 1, n4 - 1, this.x, n3 - (n + 1), 1, n7);
        this.a(this.t, n3 - 1, n2 + 1, this.x, 1, n4 - n2 - 1, n7);
        final int n8 = -16777216;
        this.a(this.t, n, n4, this.x, n3 - n + 1, 1, n8);
        this.a(this.t, n3, n2, this.x, 1, n4 - n2, n8);
    }
    
    private final void s() {
        this.a(this.t, this.cR, this.cQ, this.x, this.cY, this.cY, this.dq[2]);
    }
    
    private void t() {
        final int[] array = { this.dq[2], this.dq[4] };
        final int cr = this.cR;
        final int n = this.cR + this.cY;
        final int n2 = 0;
        final int cq = this.cQ;
        for (int i = n2; i < cq; ++i) {
            final int n3 = i * this.x;
            for (int j = cr; j < n; ++j) {
                this.t[n3 + j] = array[j + i & 0x1];
                this.bi[n3 + j] = -7;
            }
        }
        this.a(this.dn[0], 0, 0, this.cY, this.t, cr, n2, this.x, this.cY, this.cY);
        this.a(this.bi, cr, n2, this.x, this.cY, this.cY, -6);
        this.a(this.dn[1], 0, 0, this.cY, this.t, cr, cq - this.cY, this.x, this.cY, this.cY);
        this.a(this.bi, cr, cq - this.cY, this.x, this.cY, this.cY, -9);
        int db = this.cQ * (this.cQ - this.cY * 2) / this.cL;
        if (db < 6) {
            db = 6;
        }
        this.db = db;
        this.de = -this.B * (this.cQ - this.cY * 2) / this.cL;
        final int n4 = cr;
        final int n5 = this.cY + this.de;
        final int n6 = n - 1;
        final int n7 = this.cY + this.de + db;
        this.c(n4, n5, n6, n7);
        this.a(this.bi, n4, n5, this.x, n6 - n4, n7 - n5, -8);
    }
    
    Image b(final String s) {
        try {
            return this.c(s);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.c(s);
        }
    }
    
    synchronized Image c(final String s) {
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
                this.showStatus(d("\u0016~D|\u0019\u007f") + s + d("\u007f}Jo\\9|Pu\u0018~"));
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
    
    void u() {
        final Insets insets = this.W.insets();
        this.W.setResizable(true);
        this.W.resize(this.R + insets.left + insets.right, this.O + insets.bottom + insets.top);
        this.W.repaint();
        this.W.validate();
        this.W.setResizable(false);
        this.W.move(Integer.valueOf(this.getParameter(d("9\u007fJz\b'"))), Integer.valueOf(this.getParameter(d("9\u007fJz\b&"))));
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final Image image) {
        this.l = new int[n3 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, this.l, 0, n3);
        image.getSource().addConsumer(pixelGrabber);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            return;
        }
    }
    
    public void a(final int n, final int n2, final Image image, final int n3) {
        final int n4 = this.bd[n3];
        final int n5 = this.bc[n3];
        this.bb[n3] = new int[n4 * n5];
        this.bl[n3] = new boolean[n4 * n5];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n4, n5, this.bb[n3], 0, n4);
        image.getSource().addConsumer(pixelGrabber);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            return;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.cE) {
            if (n == 16) {
                this.cr = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.ds = this.getToolkit();
        final String parameter = this.getParameter(d("<a@\u007f\u0015+`"));
        if (parameter != null) {
            if (!parameter.startsWith(d("\u001ecUw\u0019+3Gb\\\u001e}Cb\\\u000bvDv\\wdRlR>}Cb"))) {
                this.b();
            }
        }
        else {
            this.b();
        }
        String s;
        try {
            s = this.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s = d("9zI~");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(d("9zI~")) || s2.length() == 0 || s2.equalsIgnoreCase(d("3|Fz\u00107|Vo")) || s2.equals(d("n!\u00125Lq#\u000b*"))) {
            this.cP = true;
        }
        else {
            if (s2.startsWith(d("(dR5"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(d("-vBx\u0013;v"));
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
                            array3[n7] = this.d(b, n5, 48, 57);
                        }
                        else if (b >= 65 && b <= 90) {
                            array3[n7] = this.d(b, n5, 65, 90);
                        }
                        else if (b >= 97 && b <= 122) {
                            array3[n7] = this.d(b, n5, 97, 122);
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
                        if (s5.startsWith(d("(dR5"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.cP = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(d("0e@i\u00152t"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            this.cE = this.b(parameter3);
            if (this.cE != null) {
                String parameter4 = this.getParameter(d("0e@i\u00152t}"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.cF = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(d("0e@i\u00152t|"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cG = Integer.valueOf(parameter5);
            }
        }
        String parameter6 = this.getParameter(d("9\u007fJz\b2|A~"));
        if (parameter6 == null) {
            parameter6 = "NO";
        }
        if (parameter6.equalsIgnoreCase(d("\u0006Vv"))) {
            this.Q = true;
            this.R = Integer.valueOf(this.getParameter(d("9\u007fJz\b(")));
            this.O = Integer.valueOf(this.getParameter(d("9\u007fJz\b7")));
            String parameter7 = this.getParameter(d("9\u007fJz\b6}Ci\u00131g"));
            if (parameter7 == null) {
                parameter7 = "NO";
            }
            if (parameter7.equalsIgnoreCase(d("\u0006Vv"))) {
                this.P = true;
            }
        }
        else {
            this.Q = false;
        }
        if (this.Q) {
            this.cN = this.R;
            this.cK = this.O;
        }
        else {
            this.cN = this.size().width;
            this.cK = this.size().height;
        }
        this.bw = this.cN;
        this.bj = this.cK;
        this.be = this.createImage(this.bw, this.bj);
        this.cp = this.be.getGraphics();
        this.E = true;
        this.dp = new Color[5];
        this.dq = new int[5];
        final Color color = new Color(this.a(this.getParameter(d(",jVo\u00192PJw\u0013-"))));
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        this.dp[0] = new Color(0, 0, 0);
        final double n14 = 0.4980392156862745;
        this.dp[1] = new Color((int)(red * n14), (int)(green * n14), (int)(blue * n14));
        final double n15 = 0.7490196078431373;
        this.dp[2] = new Color((int)(red * n15), (int)(green * n15), (int)(blue * n15));
        final double n16 = 0.4823529411764706;
        this.dp[3] = new Color((int)(red * n16), (int)(green * n16), (int)(blue * n16));
        final double n17 = 1.0;
        this.dp[4] = new Color((int)(red * n17), (int)(green * n17), (int)(blue * n17));
        for (int n18 = 0; n18 < 5; ++n18) {
            this.dq[n18] = this.a(this.dp[n18]);
        }
        this.M = new Color(this.a(this.getParameter(d("9zIo\u0019-PJw\u0013-"))));
        this.g = new Color(this.a(this.getParameter(d("=rFp\u001b-|Pu\u0018\u001c|It\u000e"))));
        this.S = new Color(this.a(this.getParameter(d("9|W~\u001b-|Pu\u0018\u001c|It\u000e"))));
        this.i = new Color(this.a(this.getParameter(d("=rFp\u001b-|Pu\u0018\u001c|It\u000e\fvI~\u001f+zJu"))));
        this.U = new Color(this.a(this.getParameter(d("9|W~\u001b-|Pu\u0018\u001c|It\u000e\fvI~\u001f+zJu"))));
        this.N = this.a(this.M);
        this.h = this.a(this.g);
        this.T = this.a(this.S);
        this.j = this.a(this.i);
        this.V = this.a(this.U);
        this.e = this.a(this.getParameter(d(">fQt46tMW\u00158{Q")), false);
        this.bN = this.a(this.getParameter(d("2vH\u007f\u00193r\\")), 1000);
        this.cH = this.a(this.getParameter(d("/aLt\u000e6g\\")), 3);
        if (this.cH < 1) {
            this.cH = 1;
        }
        this.bP = this.a(this.getParameter(d("\u0012zKH%\u0011P")), 10);
        this.cO = this.getParameter(d("-vB}\u000e>~@u\u001d2v"));
        if (this.cO == null) {
            this.cO = d("\u0000`@w\u001a");
        }
        if (this.getParameter(d("-vBu\u0019(uWz\u0011:")).equalsIgnoreCase(d("\u0006Vv"))) {
            this.cc = true;
        }
        this.dg = this.a(this.getParameter(d(",pWt\u00103@U~\u0019;ZK\\\u00136}B")), 128);
        this.dh = this.a(this.getParameter(d(",pWt\u00103@U~\u0019;\\Po;0zK|")), 128);
        this.dC = this.a(this.getParameter(d("1fHy\u0019-\\CY\u0013*}F~\u000f\u0016}bt\u00151t")), 0) + 1;
        this.dD = this.a(this.getParameter(d("1fHy\u0019-\\CY\u0013*}F~\u000f\u0010fQ\\\u00136}B")), 0) + 1;
        this.s = this.a(this.getParameter(d("=|Pu\u001f:TWz\n6g\\")), 5);
        this.r = this.a(this.getParameter(d("=|Pu\u001f:RHk\u00106gP\u007f\u0019")), 100);
        switch (this.q = this.a(this.getParameter(d("\u001d|W\u007f\u0019-@Qb\u0010:")), 1)) {
            case 0: {
                this.p = 0;
                this.o = 0;
                break;
            }
            case 1: {
                this.p = 1;
                this.o = 1;
                break;
            }
            case 2: {
                this.p = 4;
                this.o = 4;
                break;
            }
        }
        this.cZ = this.a(this.getParameter(d(",pWt\u00103QDi/+jI~")), 1);
        if (this.cZ == 0) {
            this.cY = 11;
        }
        else {
            this.cY = 16;
        }
        this.k = this.getParameter(d("=rFp\u00152rB~"));
        if (this.bN < 0) {
            this.bN = 0;
        }
        if (this.cH > 10) {
            this.cH = 10;
        }
        else if (this.cH < 1) {
            this.cH = 1;
        }
        int n19 = 0;
        if (this.getParameter(d("+a@~>0\u007fA")).equalsIgnoreCase(d("\u0006Vv"))) {
            n19 |= 0x1;
        }
        final String parameter8 = this.getParameter(d("+a@~5+rIr\u001f"));
        if (parameter8 != null && parameter8.equalsIgnoreCase(d("\u0006Vv"))) {
            n19 |= 0x2;
        }
        this.bt = this.a(this.getParameter(d("+a@~:0}QH\u0015%v")), 11);
        this.bO = new Font(this.getParameter(d("+a@~:0}Q")), n19, this.bt);
        this.br = 16;
        this.bs = 19;
        this.l();
        this.p();
        this.y();
        this.j();
        this.m();
        this.e();
        this.o();
        this.n();
        this.x = this.bw - 2 * this.p;
        this.u = this.bj - 2 * this.o;
        this.w = new int[this.x * this.u];
        this.t = new int[this.x * this.u];
        this.bi = new int[this.x * this.u];
        this.m = new int[this.x * this.u];
        this.n = new int[this.x * this.u];
        for (int n20 = 0; n20 < this.x * this.u; ++n20) {
            this.n[n20] = -1;
        }
        this.i();
        try {
            this.v();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.v();
        }
        if (this.Q) {
            (this.W = new Frame(this.getParameter(d("9\u007fJz\b6gI~")))).add(d("\u001cvKo\u0019-"), this);
        }
        if (!this.cP) {
            (this.dM = new Lware(this.getAppletContext(), new Label(d("\u000ba@~\\\u0012vKn\\>cUw\u0019+3Gb\\\u001e}Cb\\\u000bvDv\\n*\u001c#Sf*\u000b")))).setTitle(d("\u000ba@~\\\u0012vKn\\\u001ecUw\u0019+3Gb\\\u001e}Cb\\\u000bvDv"));
            this.dM.hide();
        }
    }
    
    void v() {
        this.cJ = new MemoryImageSource(this.x, this.u, new DirectColorModel(24, 16711680, 65280, 255), this.t, 0, this.x);
        String s;
        try {
            s = System.getProperty(d("5rSzR)vWh\u00150}"));
        }
        catch (SecurityException ex) {
            s = d("*}N");
        }
        if (!s.startsWith(d("n=\u0015"))) {
            try {
                this.cJ.setAnimated(true);
                this.cJ.setFullBufferUpdates(true);
                this.v = this.createImage(this.cJ);
                this.cJ.newPixels();
                this.bx = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.bx = false;
            }
        }
        if (!this.bx) {
            this.cJ = null;
            this.cI = new anfy(this.x, this.u, new DirectColorModel(24, 16711680, 65280, 255), this.t, 0, this.x);
            this.v = this.createImage(this.cI);
        }
    }
    
    private void b(final int n) {
        if (this.bv[n] != null && this.bv[n].length() > 1) {
            try {
                final URL url = new URL(this.getDocumentBase(), this.bv[n]);
                final AppletContext appletContext = this.getAppletContext();
                if (this.cc) {
                    appletContext.showDocument(url, this.cO);
                    return;
                }
                appletContext.showDocument(url);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public final synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.cP) {
            this.dM.show();
            this.dM.toFront();
            this.dM.requestFocus();
        }
        else {
            this.bV = true;
            this.bQ = false;
            this.bR = -1;
            this.bT = -1;
            if (!(n < this.p | n > this.p + this.x | n2 < this.o | n2 > this.o + this.u)) {
                this.bR = n - this.p;
                this.bT = n2 - this.o;
                this.bS = this.bR;
                this.bU = this.bT;
                this.dG = true;
            }
        }
        return true;
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        this.bW = true;
        this.bV = true;
        this.bR = -1;
        this.bT = -1;
        if (!(n < this.p | n > this.p + this.x | n2 < this.o | n2 > this.o + this.u)) {
            this.bR = n - this.p;
            this.bT = n2 - this.o;
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.G = this.by;
        this.cD = false;
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.G = this.by;
        return this.cD = true;
    }
    
    public final synchronized boolean mouseMove(final Event event, final int bx, final int by) {
        this.bX = bx;
        this.bY = by;
        return true;
    }
    
    public final synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.cP) {
            this.bV = false;
            this.bW = false;
            this.di = -1;
            this.K = -1;
            this.bR = -1;
            this.bT = -1;
            if (!(n < this.p | n > this.p + this.x | n2 < this.o | n2 > this.o + this.u)) {
                this.bR = n - this.p;
                this.bT = n2 - this.o;
                this.bS = this.bR;
                this.bU = this.bT;
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.E) {
            if (this.cE != null) {
                this.w();
            }
            graphics.drawImage(this.be, 0, 0, this);
        }
    }
    
    public synchronized void w() {
        if (this.c) {
            this.notifyAll();
            while (!this.cr) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.cr = false;
        }
        this.cp.drawImage(this.cE, this.cF, this.cG, this);
    }
    
    public final void x() {
        try {
            if (this.bx) {
                this.cJ.newPixels();
                return;
            }
            this.cI.startProduction(this.cI.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    private void b(final int dl, final int n, final boolean b) {
        int[] array;
        short[] array2;
        boolean[] array3;
        if (b) {
            array = this.dj;
            array2 = this.dm;
            array3 = this.dk;
        }
        else {
            array = this.cz;
            array2 = this.cB;
            array3 = this.cA;
        }
        int n2 = this.bL;
        if (this.ct > this.bL) {
            n2 = this.ct;
        }
        final boolean[] array4 = new boolean[n2];
        for (int i = 0; i < n2; ++i) {
            array4[i] = false;
        }
        this.dl = dl;
        for (int j = 1; j < dl; ++j) {
            final short n3 = array2[j];
            if (!array3[j]) {
                array4[n3 - 1] = false;
            }
            else {
                array4[n3 - 1] = true;
            }
        }
        while (this.dl < n) {
            final int n4 = array[this.dl];
            final short n5 = array2[this.dl];
            this.H = (short)(n5 * this.bs);
            if (this.dl != 0) {
                if (!array3[this.dl]) {
                    array4[n5 - 1] = false;
                    if (this.bm[n4] < 1) {
                        this.a(this.y + (n5 - 1) * this.bs, this.I, 7, -1);
                    }
                    else if (this.bn[n4]) {
                        this.a(this.y + (n5 - 1) * this.bs, this.I, 11, n4);
                    }
                    else {
                        this.a(this.y + (n5 - 1) * this.bs, this.I, 9, n4);
                    }
                }
                else {
                    array4[n5 - 1] = true;
                    if (this.bm[n4] < 1) {
                        this.a(this.y + (n5 - 1) * this.bs, this.I, 8, -1);
                    }
                    else if (this.bn[n4]) {
                        this.a(this.y + (n5 - 1) * this.bs, this.I, 12, n4);
                    }
                    else {
                        this.a(this.y + (n5 - 1) * this.bs, this.I, 10, n4);
                    }
                }
                for (short n6 = 0; n6 < n5 - 1; ++n6) {
                    if (!array4[n6]) {
                        this.a(this.y + n6 * this.bs, this.I, 6, -1);
                    }
                }
            }
            if (this.bp[this.bk[n4]]) {
                if (this.bn[n4] & this.bm[n4] > 0) {
                    this.a(this.H + this.y, this.I, this.bk[n4], n4, 0);
                }
                else {
                    this.a(this.H + this.y, this.I, this.bk[n4], n4, 1);
                }
            }
            else {
                this.b(this.H + this.y, this.I, this.bk[n4], n4);
            }
            this.a(this.H + this.y + this.bs, this.I, n4);
            this.I += this.br;
            if (this.I > this.u) {
                return;
            }
            ++this.dl;
        }
    }
    
    private void y() {
        this.bq = 0;
        int bq;
        for (bq = 0; this.getParameter(d("<|K}\u00158") + bq) != null; ++bq) {}
        this.bq = bq;
        this.q();
        for (int i = 0; i < this.bq; ++i) {
            int j = 0;
            final String parameter = this.getParameter(d("<|K}\u00158") + i);
            int n = 0;
            int n2 = 0;
            while (j != -1) {
                int n3;
                j = (n3 = parameter.indexOf(44, n));
                if (n3 == -1) {
                    n3 = parameter.length();
                }
                final String substring = parameter.substring(n, n3);
                n = j + 1;
                switch (n2) {
                    case 0: {
                        this.bo[i] = this.a(substring, 0);
                        break;
                    }
                    case 1: {
                        this.bu[i] = substring;
                        break;
                    }
                    case 2: {
                        this.bk[i] = this.a(substring, 0);
                        break;
                    }
                    case 3: {
                        this.bv[i] = substring;
                        break;
                    }
                }
                ++n2;
            }
        }
        this.cj = this.bq;
    }
    
    final void z() {
        int n;
        int n2;
        if (this.cg != this.ca) {
            n = this.cg * this.br;
            n2 = (this.ca + 1) * this.br;
        }
        else {
            n = this.cC * this.br;
            n2 = (this.cs + 1) * this.br;
        }
        if (n2 > this.cQ - this.B) {
            if (n2 - n > this.cQ) {
                this.B = -n;
                return;
            }
            this.B = -(n2 - this.cQ);
        }
    }
    
    public void run() {
        if (this.Q) {
            this.u();
        }
        Thread.currentThread().setPriority(this.cH);
        final Graphics graphics = this.getGraphics();
        if (this.cE != null && !this.c) {
            this.c = this.c();
        }
        System.gc();
        this.J = System.currentTimeMillis();
        this.ds = this.getToolkit();
        int n = 0;
        this.cp.setColor(this.g);
        this.cp.fillRect(0, 0, this.bw, this.bj);
        this.A();
        this.repaint();
        int n2 = 0;
        if (this.cj > 0) {
            this.bn[0] = true;
        }
        while (this.dt != null) {
            if (this.dL == -1) {
                this.f();
                this.k();
            }
            this.g();
            if (this.P && ++n2 == 100) {
                this.W.show();
                n2 = 0;
            }
            this.B();
            if (this.dL != 1) {
                try {
                    this.x();
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            this.cp.drawImage(this.v, this.p, this.o, this);
            if (this.cE != null) {
                this.w();
            }
            this.cp.setColor(this.S);
            if (this.E) {
                graphics.drawImage(this.be, 0, 0, this);
            }
            if (++n == this.bN) {
                System.gc();
                n = 0;
            }
            this.E();
        }
    }
    
    byte d(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    private final void c(final int n) {
        switch (n) {
            case -1: {
                this.y += this.br;
            }
            case 0: {
                if (this.bR < this.cW + this.br) {
                    this.y += this.cR;
                    return;
                }
                this.y -= this.cR;
            }
            case 1: {
                this.y -= this.br;
            }
            default: {}
        }
    }
    
    private final synchronized void d(final int n) {
        switch (n) {
            case -1: {
                this.B += this.br;
            }
            case 0: {
                if (this.bT < this.de + this.br) {
                    this.B += this.cQ;
                }
                else {
                    this.B -= this.cQ;
                }
                this.di = -1;
            }
            case 1: {
                this.B -= this.br;
            }
            default: {}
        }
    }
    
    private void A() {
        if (this.q == 0) {
            return;
        }
        this.cp.setColor(this.dp[4]);
        this.cp.drawLine(0, 0, this.bw, 0);
        if (this.q == 2) {
            this.cp.setColor(this.dp[2]);
            this.cp.drawLine(1, 1, this.bw - 1, 1);
            this.cp.setColor(this.dp[1]);
            this.cp.drawLine(2, 2, this.bw - 2, 2);
            this.cp.setColor(this.dp[0]);
            this.cp.drawLine(3, 3, this.bw - 3, 3);
        }
        this.cp.setColor(this.dp[3]);
        this.cp.drawLine(0, 0, 0, this.bj);
        if (this.q == 2) {
            this.cp.setColor(this.dp[4]);
            this.cp.drawLine(1, 1, 1, this.bj - 1);
            this.cp.setColor(this.dp[1]);
            this.cp.drawLine(2, 2, 2, this.bj - 2);
            this.cp.setColor(this.dp[0]);
            this.cp.drawLine(3, 3, 3, this.bj - 3);
        }
        this.cp.setColor(this.dp[1]);
        this.cp.drawLine(0, this.bj - 1, this.bw, this.bj - 1);
        if (this.q == 2) {
            this.cp.setColor(this.dp[2]);
            this.cp.drawLine(1, this.bj - 2, this.bw - 1, this.bj - 2);
            this.cp.setColor(this.dp[4]);
            this.cp.drawLine(2, this.bj - 3, this.bw - 2, this.bj - 3);
            this.cp.setColor(this.dp[3]);
            this.cp.drawLine(3, this.bj - 4, this.bw - 3, this.bj - 4);
        }
        this.cp.setColor(this.dp[1]);
        this.cp.drawLine(this.bw - 1, 0, this.bw - 1, this.bj - 1);
        if (this.q == 2) {
            this.cp.setColor(this.dp[2]);
            this.cp.drawLine(this.bw - 2, 1, this.bw - 2, this.bj - 2);
            this.cp.setColor(this.dp[4]);
            this.cp.drawLine(this.bw - 3, 2, this.bw - 3, this.bj - 3);
            this.cp.setColor(this.dp[3]);
            this.cp.drawLine(this.bw - 4, 3, this.bw - 4, this.bj - 4);
        }
    }
    
    final synchronized void B() {
        this.cR = this.x;
        this.cQ = this.u;
        this.cu = this.cL;
        if (this.dL < 1) {
            this.cL = this.cm * this.br;
        }
        else {
            this.cL = this.D();
        }
        this.cM = 0;
        for (int i = 0; i < this.cm; ++i) {
            final int cm = this.bd[this.dj[i]] + this.dm[i] * this.bs + this.br;
            if (cm > this.cM) {
                this.cM = cm;
            }
        }
        boolean b = false;
        if (this.cM > this.cR) {
            this.cQ -= this.cY;
            b = true;
        }
        if (this.cL > this.cQ) {
            this.cR -= this.cY;
        }
        if (!b & this.cM > this.cR) {
            this.cQ -= this.cY;
        }
        if (this.dL == -1) {
            this.d();
        }
        else {
            this.z();
        }
        if (this.B > 0) {
            this.B = 0;
        }
        if (this.B + this.cL < this.cQ) {
            this.B = this.cQ - this.cL;
        }
        if (this.cL < this.cQ) {
            this.B = 0;
        }
        if (this.y > 0) {
            this.y = 0;
        }
        if (this.y + this.cM < this.cR) {
            this.y = this.cR - this.cM;
        }
        if (this.cM < this.cR) {
            this.y = 0;
        }
        if (this.dL == -1) {
            this.C = this.B;
            this.z = this.y;
        }
        this.I = this.B;
        if (this.dL == -1) {
            this.b(0, this.cm, true);
        }
        else {
            this.C();
        }
        if (this.cM > this.cR) {
            this.r();
        }
        if (this.cL > this.cQ) {
            this.t();
        }
        if (this.cM > this.cR & this.cL > this.cQ) {
            this.s();
        }
    }
    
    public synchronized void start() {
        if (this.dt == null) {
            (this.dt = new Thread(this)).start();
            if (this.Q) {
                this.u();
                this.W.show();
            }
        }
    }
    
    public synchronized void stop() {
        if (this.dt != null && this.dt.isAlive()) {
            this.dt.stop();
        }
        if (this.Q) {
            this.W.hide();
        }
        this.dt = null;
    }
    
    public treemenu() {
        this.bx = false;
        this.E = false;
        this.bH = false;
        this.bw = 600;
        this.bj = 600;
        this.cc = false;
        this.cY = 11;
        this.p = 4;
        this.o = 4;
        this.e = false;
        this.q = 1;
        this.cZ = 1;
        this.Q = false;
        this.P = false;
        this.c = false;
        this.cP = false;
        this.cb = false;
        this.cr = false;
        this.a = 1.0f;
        this.L = false;
        this.dG = false;
        this.bQ = true;
        this.G = -1;
        this.K = -1;
        this.by = -1;
        this.cD = true;
        this.dL = -1;
        this.bX = -1;
        this.bY = -1;
        this.di = -1;
        this.bV = false;
        this.bW = false;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void C() {
        if (this.dL == 0) {
            this.dy = 1;
            this.dv = 0;
            this.dB = this.cs - this.cC;
            this.dA = this.ca - this.cg;
            if (this.dA != 0) {
                this.dJ = this.dA * this.br;
                this.dK = -this.dJ;
            }
            else {
                this.dJ = this.dB * this.br;
                this.dK = this.dJ;
            }
        }
        ++this.dL;
        int n;
        if (this.dA != 0) {
            n = this.cg;
            final int ca = this.ca;
            this.I = this.B + (n + 1) * this.br + this.dK;
            this.b(n + 1, ca + 1, true);
        }
        else {
            n = this.cC;
            final int cs = this.cs;
            this.I = this.B + (n + 1) * this.br + this.dK - this.dB * this.br;
            this.b(n + 1, cs + 1, false);
        }
        int b = this.B;
        int n2 = this.B + (n + 1) * this.br;
        if (b < 0) {
            b = 0;
        }
        if (n2 >= this.u) {
            n2 = this.u - 1;
        }
        System.arraycopy(this.m, b * this.x, this.t, b * this.x, (n2 - b) * this.x);
        this.I = this.B;
        this.b(0, n + 1, true);
        this.I += this.dA * this.br + this.dK;
        this.b(this.ca + 1, this.cm, true);
        if (this.dA != 0) {
            this.dK = (int)(-this.dI[this.dy] * this.dJ * this.dx[this.dv] * this.a);
            ++this.dy;
            if (this.dy >= this.dF - 1 & this.dz < this.dF - 1) {
                ++this.dv;
            }
            if (this.dy >= this.dF * 2 - 1) {
                this.dy = 0;
            }
            this.dz = this.dy;
            if (this.dv >= this.dD) {
                this.dL = -1;
            }
        }
        else {
            this.dK = (int)(this.dH[this.dy] * this.dJ * this.dw[this.dv] * this.a);
            ++this.dy;
            if (this.dy >= this.dE - 1 & this.dz < this.dE - 1) {
                ++this.dv;
            }
            if (this.dy >= this.dE * 2 - 1) {
                this.dy = 0;
            }
            this.dz = this.dy;
            if (this.dv >= this.dC) {
                this.dL = -1;
            }
        }
        ++this.dy;
        if (this.dL == -1) {
            this.dK = 0;
        }
    }
    
    private int D() {
        int cu;
        if (this.dL > 0) {
            cu = this.cm * this.br + this.dK;
        }
        else {
            cu = this.cu;
        }
        return cu;
    }
    
    public synchronized void E() {
        Thread.yield();
        this.ds.sync();
        final long n = 10L - (System.currentTimeMillis() - this.J);
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
        this.J = System.currentTimeMillis();
        try {
            Thread.sleep(this.bP);
        }
        catch (InterruptedException ex3) {}
    }
    
    private static String d(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = '_';
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
                                c = '\u0013';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '%';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '\u001b';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '|';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
