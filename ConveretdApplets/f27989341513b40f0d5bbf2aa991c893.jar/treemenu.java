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
    final String d = "ds$SjQ#6F/dm2F/qf5R/\rt#H!Dm2F";
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
    final int by = 6;
    final int bz = 7;
    final int bA = 11;
    final int bB = 9;
    final int bC = 8;
    final int bD = 12;
    final int bE = 10;
    URL bF;
    private boolean bG;
    final int bH = 5;
    String bI;
    final int bJ = 4;
    int bK;
    Thread bL;
    int bM;
    Font bN;
    int bO;
    boolean bP;
    int bQ;
    int bR;
    int bS;
    int bT;
    boolean bU;
    boolean bV;
    int bW;
    int bX;
    MediaTracker bY;
    int bZ;
    boolean ca;
    boolean cb;
    int[] cc;
    boolean[] cd;
    short[] ce;
    int cf;
    int cg;
    int ch;
    int ci;
    int cj;
    int ck;
    int cl;
    final int cm = 13;
    final int cn = 5;
    private Graphics co;
    private Image cp;
    boolean cq;
    int cr;
    int cs;
    int ct;
    int cu;
    int cv;
    int cw;
    int cx;
    int[] cy;
    boolean[] cz;
    short[] cA;
    int cB;
    private Image cC;
    int cD;
    int cE;
    int cF;
    anfy cG;
    MemoryImageSource cH;
    int cI;
    int cJ;
    int cK;
    int cL;
    String cM;
    boolean cN;
    int cO;
    int cP;
    final int cQ = -4;
    int cR;
    final int cS = -3;
    final int cT = -2;
    int cU;
    final int cV = -5;
    int cW;
    int cX;
    final int cY = -8;
    int cZ;
    final int da = -9;
    final int db = -7;
    int dc;
    final int dd = -6;
    int de;
    int df;
    int dg;
    int[] dh;
    boolean[] di;
    int dj;
    short[] dk;
    int[][] dl;
    boolean[][] dm;
    Color[] dn;
    int[] do;
    int dp;
    private Toolkit dq;
    Thread dr;
    int ds;
    int dt;
    float[] du;
    float[] dv;
    int dw;
    int dx;
    int dy;
    int dz;
    int dA;
    int dB;
    int dC;
    int dD;
    boolean dE;
    float[] dF;
    float[] dG;
    int dH;
    int dI;
    private int dJ;
    Lware dK;
    
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
        if (this.bK <= this.H) {
            this.bK = this.H + 1;
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
        final int[] array = new int[this.cl];
        final short[] array2 = new short[this.cl];
        final boolean[] array3 = new boolean[this.cl];
        for (int i = 0; i < this.cl; ++i) {
            array[i] = this.dh[i];
            array2[i] = this.dk[i];
            array3[i] = this.di[i];
        }
        ++this.cl;
        this.dh = new int[this.cl];
        this.dk = new short[this.cl];
        this.di = new boolean[this.cl];
        for (int j = 0; j < this.cl - 1; ++j) {
            this.dh[j] = array[j];
            this.dk[j] = array2[j];
            this.di[j] = array3[j];
        }
        this.dh[this.cl - 1] = n;
        this.dk[this.cl - 1] = (short)n2;
        this.di[this.cl - 1] = b;
    }
    
    private final void a() {
        this.cj = this.cl;
        this.cc = new int[this.cj];
        this.ce = new short[this.cj];
        this.cd = new boolean[this.cj];
        for (int i = 0; i < this.cj; ++i) {
            this.cc[i] = this.dh[i];
            this.ce[i] = this.dk[i];
            this.cd[i] = this.di[i];
        }
    }
    
    private final void b() {
        while (true) {
            this.showStatus(d("al:\u0018{\u0005q1R`SftHxR-5Qi\\w1^b\u000b`;R/Fq1[fQptSfKftVa\u0005K\u0000rC\u0004"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean c() {
        this.prepareImage(this.cC, this);
        if (this.bx) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.cq;
        }
        return false;
    }
    
    private final void d() {
        if (this.ck < 1 | this.ck == this.cl) {
            return;
        }
        this.dJ = 0;
        this.a();
        if (this.ck > this.cl) {
            int cb = this.cl - 1;
            for (int i = 0; i < this.cl; ++i) {
                if (this.dh[i] != this.cy[i]) {
                    cb = i - 1;
                    i = this.cl;
                }
            }
            int cr = this.ck - 1;
            final short n = this.cA[cb];
            for (int j = cb + 1; j < this.ck; ++j) {
                if (this.cA[j] <= n) {
                    cr = j - 1;
                    j = this.ck;
                }
            }
            this.cf = cb;
            this.bZ = cb;
            this.cB = cb;
            this.cr = cr;
            return;
        }
        int cr2 = this.ck - 1;
        for (int k = 0; k < this.ck; ++k) {
            if (this.dh[k] != this.cy[k]) {
                cr2 = k - 1;
                k = this.ck;
            }
        }
        int bz = this.cl - 1;
        final short n2 = this.dk[cr2];
        for (int l = cr2 + 1; l < this.cl; ++l) {
            if (this.dk[l] <= n2) {
                bz = l - 1;
                l = this.cl;
            }
        }
        this.cf = cr2;
        this.bZ = bz;
        this.cB = cr2;
        this.cr = cr2;
    }
    
    private void e() {
        for (int i = 0; i < this.ci; ++i) {
            if (this.bm[i] < 1 & this.bp[this.bk[i]]) {
                this.showStatus(d("hf:J/lw1R5\u0005") + i + d("\u0005`;Q{Dj:L/D#9^\u007f\u0005J7Pa\u0005+g\rw\u00145}\u001ffKp ZnA#;Y/D#:P}Hb8\u001fFFl:\u001f'\u00145,\u000e9\f"));
                this.stop();
            }
        }
    }
    
    private void f() {
        if (this.e && (this.bW > -1 & this.bW < this.x & this.bX > -1 & this.bX < this.u)) {
            this.G = this.bi[this.bW + this.bX * this.x];
        }
        if (this.dE) {
            if (this.bQ > -1 & this.bQ < this.x & this.bS > -1 & this.bS < this.u) {
                this.dg = this.bi[this.bQ + this.bS * this.x];
                this.K = this.dg;
            }
            this.dE = false;
        }
        if ((this.bQ != this.bR | this.bS != this.bT) & this.bV & (this.K == -8 | this.K == -4)) {
            if (this.K == -8) {
                this.B = -((this.dc + this.bS - this.bT) * this.cJ / (this.cO - this.cW * 2));
                this.bR = this.bQ;
                this.bT = this.bS;
            }
            if (this.K == -4) {
                this.y = -((this.cU + this.bQ - this.bR) * this.cK / (this.cP - this.cW * 2));
                this.bR = this.bQ;
                this.bT = this.bS;
            }
            this.bP = true;
            return;
        }
        if (this.bP) {
            return;
        }
        this.bU = false;
        if (this.dg > -1) {
            this.bn[this.dg] = !this.bn[this.dg];
            this.bP = true;
            this.G = this.dg;
            if (this.bv[this.dg] != null) {
                this.b(this.dg);
            }
            return;
        }
        this.bP = true;
        switch (this.dg) {
            case -6: {
                this.d(-1);
                this.bP = false;
            }
            case -7: {
                this.d(0);
                this.bP = false;
            }
            case -9: {
                this.d(1);
                this.bP = false;
            }
            case -2: {
                this.c(-1);
                this.bP = false;
            }
            case -3: {
                this.c(0);
                this.bP = false;
            }
            case -5: {
                this.c(1);
                this.bP = false;
            }
            default: {}
        }
    }
    
    public void g() {
        System.arraycopy(this.m, 0, this.t, 0, this.x * this.u);
        System.arraycopy(this.n, 0, this.bi, 0, this.x * this.u);
    }
    
    private final void h() {
        this.dh = new int[0];
        this.dk = new short[0];
        this.cl = 0;
    }
    
    public boolean a(final String s, final boolean b) {
        if (s == null) {
            return b;
        }
        if (s.length() < 1) {
            return b;
        }
        return s.equalsIgnoreCase(d("|F\u0007"));
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
        final boolean[] array2 = this.dm[n];
        final int[] array3 = this.dl[n];
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n2; ++j) {
                final char c = (char)(array[i].charAt(j) - '0');
                final int n4 = i * n2 + j;
                if (c > -1) {
                    array3[n4] = this.do[c];
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
        this.b(this.dl[n3], this.dm[n3], n5, n6, this.bs, this.t, n7, n8, this.x, bs, br);
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
            for (i = 1; i < this.ci; ++i) {
                n = this.bo[i];
                this.a(n, i);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.showStatus(d("`q&P}\u0005q1^kLm3\u001flJm2VhPq5KfJmt\u0004/Lw1RFA9t") + i + d("\u0005s5MjKw\u001d[/\u001f#") + n);
            this.stop();
        }
    }
    
    private final void k() {
        this.H = 0;
        this.I = 0;
        System.arraycopy(this.t, 0, this.w, 0, this.x * this.u);
        this.D = this.B;
        this.A = this.y;
        this.ck = this.cl;
        this.cy = new int[this.ck];
        this.cA = new short[this.ck];
        this.cz = new boolean[this.ck];
        for (int i = 0; i < this.ck; ++i) {
            this.cy[i] = this.dh[i];
            this.cA[i] = this.dk[i];
            this.cz[i] = this.di[i];
        }
        this.h();
        final int n = 0;
        this.cs = this.bK;
        this.bK = 1;
        this.a(n, this.H, true);
        if (this.bn[n]) {
            this.a(n);
        }
        this.dj = 0;
    }
    
    private void l() {
        this.dl = new int[13][];
        this.dm = new boolean[13][];
        for (int i = 0; i < 13; ++i) {
            this.dl[i] = new int[this.bs * this.br];
            this.dm[i] = new boolean[this.bs * this.br];
        }
        final int n = this.cW * this.cW;
        this.dl[0] = new int[n];
        this.dl[1] = new int[n];
        this.dl[2] = new int[n];
        this.dl[3] = new int[n];
        final int n2 = this.cW - 1;
        this.a(this.dl[0], 0, 0, n2, n2);
        this.a(this.dl[1], 0, 0, n2, n2);
        this.a(this.dl[2], 0, 0, n2, n2);
        this.a(this.dl[3], 0, 0, n2, n2);
        final String[] array = new String[this.cW];
        int n3 = 0;
        if (this.cW == 11) {
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011?\u0015-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u000f?\u00153z\u0011!\u000b");
            array[n3++] = d("\u000b-d\u000f?\u00153d\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
        }
        else {
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-d\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b3d\u000f!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u00153d\u000f?\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011?\u00153d\u000f?\u0015-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n3++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
        }
        this.a(array, 0, this.cW, this.cW);
        int n4 = 0;
        if (this.cW == 11) {
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-d\u000f?\u00153d\u0011!\u000b");
            array[n4++] = d("\u000b-z\u000f?\u00153z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011?\u0015-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
        }
        else {
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011?\u00153d\u000f?\u0015-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u00153d\u000f?\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b3d\u000f!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-d\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n4++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
        }
        this.a(array, 1, this.cW, this.cW);
        int n5 = 0;
        if (this.cW == 11) {
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u0015-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011?\u0015-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u000f?\u0015-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u000f?\u0015-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011?\u0015-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u0015-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
        }
        else {
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u000f!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-d\u000f!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b3d\u000f!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u00153d\u000f!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b3d\u000f!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-d\u000f!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u000f!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n5++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
        }
        this.a(array, 2, this.cW, this.cW);
        int n6 = 0;
        if (this.cW == 11) {
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011?\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011?\u0015-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011?\u00153z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011?\u00153z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011?\u0015-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011?\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b");
        }
        else {
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b3z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b3d\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b3d\u000f!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b3d\u000f?\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b3d\u000f!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b3d\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b3z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
            array[n6++] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b");
        }
        this.a(array, 3, this.cW, this.cW);
        final int n7 = this.bs * this.br;
        for (int j = 6; j < 13; ++j) {
            for (int k = 0; k < n7; ++k) {
                this.dm[j][k] = false;
            }
        }
        final String[] array2 = new String[this.br];
        array2[0] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[1] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[2] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[3] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[4] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[5] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[6] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[7] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[8] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[9] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[10] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[11] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[12] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[13] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[14] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[15] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        this.a(array2, 6, this.bs, this.br);
        array2[0] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[1] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[2] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[3] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[4] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[5] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[6] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[7] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[8] = d("\u000b-z\u0011!\u000b-z\f!\u0016-g\u0011<\u000b0z\u0011");
        array2[9] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[10] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[11] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[12] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[13] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[14] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[15] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        this.a(array2, 7, this.bs, this.br);
        array2[0] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[1] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[2] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[3] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[4] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[5] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[6] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[7] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[8] = d("\u000b-z\u0011!\u000b-z\f!\u0016-g\u0011<\u000b0z\u0011");
        array2[9] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[10] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[11] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[12] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[13] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[14] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[15] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        this.a(array2, 8, this.bs, this.br);
        array2[0] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[1] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[2] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[3] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[4] = d("\u000b-z\u0011<\u00160g\f<\u00160g\u0011!\u000b-z\u0011");
        array2[5] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[6] = d("\u000b-z\u0011<\u000b-z\u000f!\u000b-g\u0011!\u000b-z\u0011");
        array2[7] = d("\u000b-z\u0011<\u000b-z\u000f!\u000b-g\u0011!\u000b-z\u0011");
        array2[8] = d("\u000b-z\u0011<\u000b3d\u000f?\u0015-g\u0011<\u000b0z\f");
        array2[9] = d("\u000b-z\u0011<\u000b-z\u000f!\u000b-g\u0011!\u000b-z\u0011");
        array2[10] = d("\u000b-z\u0011<\u000b-z\u000f!\u000b-g\u0011!\u000b-z\u0011");
        array2[11] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[12] = d("\u000b-z\u0011<\u00160g\f<\u00160g\u0011!\u000b-z\u0011");
        array2[13] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[14] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[15] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        this.a(array2, 9, this.bs, this.br);
        array2[0] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[1] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[2] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[3] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[4] = d("\u000b-z\u0011<\u00160g\f<\u00160g\u0011!\u000b-z\u0011");
        array2[5] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[6] = d("\u000b-z\u0011<\u000b-z\u000f!\u000b-g\u0011!\u000b-z\u0011");
        array2[7] = d("\u000b-z\u0011<\u000b-z\u000f!\u000b-g\u0011!\u000b-z\u0011");
        array2[8] = d("\u000b-z\u0011<\u000b3d\u000f?\u0015-g\u0011<\u000b0z\f");
        array2[9] = d("\u000b-z\u0011<\u000b-z\u000f!\u000b-g\u0011!\u000b-z\u0011");
        array2[10] = d("\u000b-z\u0011<\u000b-z\u000f!\u000b-g\u0011!\u000b-z\u0011");
        array2[11] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[12] = d("\u000b-z\u0011<\u00160g\f<\u00160g\u0011!\u000b-z\u0011");
        array2[13] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[14] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[15] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        this.a(array2, 10, this.bs, this.br);
        array2[0] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[1] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[2] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[3] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[4] = d("\u000b-z\u0011<\u00160g\f<\u00160g\u0011!\u000b-z\u0011");
        array2[5] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[6] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[7] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[8] = d("\u000b-z\u0011<\u000b3d\u000f?\u0015-g\u0011<\u000b0z\f");
        array2[9] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[10] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[11] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[12] = d("\u000b-z\u0011<\u00160g\f<\u00160g\u0011!\u000b-z\u0011");
        array2[13] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[14] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[15] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        this.a(array2, 11, this.bs, this.br);
        array2[0] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[1] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[2] = d("\u000b-z\u0011!\u000b-z\f!\u000b-z\u0011!\u000b-z\u0011");
        array2[3] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[4] = d("\u000b-z\u0011<\u00160g\f<\u00160g\u0011!\u000b-z\u0011");
        array2[5] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[6] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[7] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[8] = d("\u000b-z\u0011<\u000b3d\u000f?\u0015-g\u0011<\u000b0z\f");
        array2[9] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[10] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[11] = d("\u000b-z\u0011<\u000b-z\u0011!\u000b-g\u0011!\u000b-z\u0011");
        array2[12] = d("\u000b-z\u0011<\u00160g\f<\u00160g\u0011!\u000b-z\u0011");
        array2[13] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[14] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        array2[15] = d("\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011!\u000b-z\u0011");
        this.a(array2, 12, this.bs, this.br);
    }
    
    final void m() {
        final int n = 20;
        final int br = this.br;
        final Graphics graphics = this.createImage(n, br).getGraphics();
        graphics.setFont(this.bN);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n2 = 0;
        for (int i = 0; i < this.ci; ++i) {
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
        for (int j = 0; j < this.ci; ++j) {
            final int n5 = j;
            final Image image = this.createImage(n2, br);
            final Graphics graphics2 = image.getGraphics();
            graphics2.setFont(this.bN);
            final FontMetrics fontMetrics2 = graphics2.getFontMetrics();
            this.ds = fontMetrics2.getAscent() - n4 + (this.br - this.bt) / 2;
            graphics2.drawString(this.bu[n5], 2, this.ds);
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
        (this.dv = new float[this.dB])[0] = 1.0f;
        for (int i = 1; i < this.dB; ++i) {
            this.dv[i] = this.dv[i - 1] * n;
        }
        (this.du = new float[this.dA])[0] = 1.0f;
        for (int j = 1; j < this.dA; ++j) {
            this.du[j] = this.du[j - 1] * n;
        }
    }
    
    private void o() {
        this.dC = 1000 / this.de;
        this.dF = new float[this.dC * 2];
        this.dD = 1000 / this.df;
        this.dG = new float[this.dD * 2];
        final double n = 1.5707963267948966 / this.dC;
        for (int i = 0; i < this.dC; ++i) {
            this.dF[i] = (float)Math.sin(i * n + 1.5707963267948966);
        }
        final double n2 = 1.5707963267948966 / this.dD;
        for (int j = 0; j < this.dD; ++j) {
            this.dG[j] = (float)Math.sin(j * n2 + 1.5707963267948966);
        }
        for (int k = 0; k < this.dC; ++k) {
            this.dF[this.dC + k] = this.dF[this.dC - k];
        }
        for (int l = 0; l < this.dD; ++l) {
            this.dG[this.dD + l] = this.dG[this.dD - l];
        }
    }
    
    private void p() {
        this.cg = 0;
        while (this.getParameter(d("l`;Q") + this.cg) != null) {
            ++this.cg;
        }
        this.bg = new int[this.cg][];
        this.bh = new boolean[this.cg][];
        this.bp = new boolean[this.cg];
        for (int i = 0; i < this.cg; ++i) {
            final Image b = this.b(this.getParameter(d("l`;Q") + i));
            final int width = b.getWidth(this);
            final int height = b.getHeight(this);
            if ((width != 32 & width != 16) | height != 16) {
                this.showStatus(d("vj.Z/JetvlJm") + i + d("\u0005j'\u001ffK`;M}@` \u0011") + width + "x" + height);
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
        this.cC = null;
        this.be = null;
        if (this.co != null) {
            this.co.dispose();
        }
        this.co = null;
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
        final int n5 = this.do[3];
        this.a(array, n, n2, this.cW, n3 - n, 1, n5);
        this.a(array, n, n2, this.cW, 1, n4 - n2, n5);
        final int n6 = this.do[4];
        this.a(array, n + 1, n2 + 1, this.cW, n3 - 1 - (n + 1), 1, n6);
        this.a(array, n + 1, n2 + 1, this.cW, 1, n4 - 1 - (n2 + 1), n6);
        this.a(array, n + 2, n2 + 2, this.cW, n3 - 1 - (n + 1), n4 - 1 - (n2 + 2), this.do[2]);
        final int n7 = this.do[1];
        this.a(array, n + 1, n4 - 1, this.cW, n3 - (n + 1), 1, n7);
        this.a(array, n3 - 1, n2 + 1, this.cW, 1, n4 - n2 - 1, n7);
        final int n8 = this.do[0];
        this.a(array, n, n4, this.cW, n3 - n + 1, 1, n8);
        this.a(array, n3, n2, this.cW, 1, n4 - n2, n8);
    }
    
    private final void r() {
        final int[] array = { this.do[2], this.do[4] };
        final int n = 0;
        final int cp = this.cP;
        final int co = this.cO;
        final int n2 = this.cO + this.cW;
        for (int i = co; i < n2; ++i) {
            final int n3 = i * this.x;
            for (int j = n; j < cp; ++j) {
                this.t[n3 + j] = array[j + i & 0x1];
                this.bi[n3 + j] = -3;
            }
        }
        this.a(this.dl[2], 0, 0, this.cW, this.t, n, co, this.x, this.cW, n2 - co);
        this.a(this.bi, n, co, this.x, this.cW, this.cW, -2);
        this.a(this.dl[3], 0, 0, this.cW, this.t, cp - this.cW, co, this.x, this.cW, this.cW);
        this.a(this.bi, cp - this.cW, co, this.x, this.cW, this.cW, -5);
        int cr = this.cP * (this.cP - this.cW * 2) / this.cK;
        if (cr < 6) {
            cr = 6;
        }
        this.cR = cr;
        this.cU = -this.y * (this.cP - this.cW * 2) / this.cK;
        final int n4 = this.cW + this.cU;
        final int n5 = n2 - this.cW;
        final int n6 = this.cW + this.cU + cr;
        final int n7 = n2 - 1;
        this.c(n4, n5, n6, n7);
        this.a(this.bi, n4, n5, this.x, n6 - n4, n7 - n5, -4);
    }
    
    private void c(final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.do[3];
        this.a(this.t, n, n2, this.x, n3 - n, 1, n5);
        this.a(this.t, n, n2, this.x, 1, n4 - n2, n5);
        final int n6 = this.do[4];
        this.a(this.t, n + 1, n2 + 1, this.x, n3 - 1 - (n + 1), 1, n6);
        this.a(this.t, n + 1, n2 + 1, this.x, 1, n4 - 1 - (n2 + 1), n6);
        this.a(this.t, n + 2, n2 + 2, this.x, n3 - 1 - (n + 1), n4 - 1 - (n2 + 2), this.do[2]);
        final int n7 = this.do[1];
        this.a(this.t, n + 1, n4 - 1, this.x, n3 - (n + 1), 1, n7);
        this.a(this.t, n3 - 1, n2 + 1, this.x, 1, n4 - n2 - 1, n7);
        final int n8 = -16777216;
        this.a(this.t, n, n4, this.x, n3 - n + 1, 1, n8);
        this.a(this.t, n3, n2, this.x, 1, n4 - n2, n8);
    }
    
    private final void s() {
        this.a(this.t, this.cP, this.cO, this.x, this.cW, this.cW, this.do[2]);
    }
    
    private void t() {
        final int[] array = { this.do[2], this.do[4] };
        final int cp = this.cP;
        final int n = this.cP + this.cW;
        final int n2 = 0;
        final int co = this.cO;
        for (int i = n2; i < co; ++i) {
            final int n3 = i * this.x;
            for (int j = cp; j < n; ++j) {
                this.t[n3 + j] = array[j + i & 0x1];
                this.bi[n3 + j] = -7;
            }
        }
        this.a(this.dl[0], 0, 0, this.cW, this.t, cp, n2, this.x, this.cW, this.cW);
        this.a(this.bi, cp, n2, this.x, this.cW, this.cW, -6);
        this.a(this.dl[1], 0, 0, this.cW, this.t, cp, co - this.cW, this.x, this.cW, this.cW);
        this.a(this.bi, cp, co - this.cW, this.x, this.cW, this.cW, -9);
        int cz = this.cO * (this.cO - this.cW * 2) / this.cJ;
        if (cz < 6) {
            cz = 6;
        }
        this.cZ = cz;
        this.dc = -this.B * (this.cO - this.cW * 2) / this.cJ;
        final int n4 = cp;
        final int n5 = this.cW + this.dc;
        final int n6 = n - 1;
        final int n7 = this.cW + this.dc + cz;
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
                this.showStatus(d("ln5Xj\u0005") + s + d("\u0005m;K/Cl!Qk\u0004"));
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
        this.W.move(Integer.valueOf(this.getParameter(d("Co;^{]"))), Integer.valueOf(this.getParameter(d("Co;^{\\"))));
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
        if (image == this.cC) {
            if (n == 16) {
                this.cq = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.dq = this.getToolkit();
        final String parameter = this.getParameter(d("Fq1[fQp"));
        if (parameter != null) {
            if (!parameter.startsWith(d("ds$SjQ#6F/dm2F/qf5R/\rt#H!Dm2F"))) {
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
            s = d("Cj8Z");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(d("Cj8Z")) || s2.length() == 0 || s2.equalsIgnoreCase(d("Il7^cMl'K")) || s2.equals(d("\u00141c\u0011?\u000b3z\u000e"))) {
            this.cN = true;
        }
        else {
            if (s2.startsWith(d("Rt#\u0011"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(d("Wf3\\`Af"));
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
                        if (s5.startsWith(d("Rt#\u0011"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.cN = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(d("Ju1MfHd"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            this.cC = this.b(parameter3);
            if (this.cC != null) {
                String parameter4 = this.getParameter(d("Ju1MfHd\f"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.cD = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(d("Ju1MfHd\r"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cE = Integer.valueOf(parameter5);
            }
        }
        String parameter6 = this.getParameter(d("Co;^{Hl0Z"));
        if (parameter6 == null) {
            parameter6 = "NO";
        }
        if (parameter6.equalsIgnoreCase(d("|F\u0007"))) {
            this.Q = true;
            this.R = Integer.valueOf(this.getParameter(d("Co;^{R")));
            this.O = Integer.valueOf(this.getParameter(d("Co;^{M")));
            String parameter7 = this.getParameter(d("Co;^{Lm2M`Kw"));
            if (parameter7 == null) {
                parameter7 = "NO";
            }
            if (parameter7.equalsIgnoreCase(d("|F\u0007"))) {
                this.P = true;
            }
        }
        else {
            this.Q = false;
        }
        if (this.Q) {
            this.cL = this.R;
            this.cI = this.O;
        }
        else {
            this.cL = this.size().width;
            this.cI = this.size().height;
        }
        this.bw = this.cL;
        this.bj = this.cI;
        this.be = this.createImage(this.bw, this.bj);
        this.co = this.be.getGraphics();
        this.E = true;
        this.dn = new Color[5];
        this.do = new int[5];
        final Color color = new Color(this.a(this.getParameter(d("Vz'KjH@;S`W"))));
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        this.dn[0] = new Color(0, 0, 0);
        final double n14 = 0.4980392156862745;
        this.dn[1] = new Color((int)(red * n14), (int)(green * n14), (int)(blue * n14));
        final double n15 = 0.7490196078431373;
        this.dn[2] = new Color((int)(red * n15), (int)(green * n15), (int)(blue * n15));
        final double n16 = 0.4823529411764706;
        this.dn[3] = new Color((int)(red * n16), (int)(green * n16), (int)(blue * n16));
        final double n17 = 1.0;
        this.dn[4] = new Color((int)(red * n17), (int)(green * n17), (int)(blue * n17));
        for (int n18 = 0; n18 < 5; ++n18) {
            this.do[n18] = this.a(this.dn[n18]);
        }
        this.M = new Color(this.a(this.getParameter(d("Cj8KjW@;S`W"))));
        this.g = new Color(this.a(this.getParameter(d("Gb7ThWl!Qkfl8P}"))));
        this.S = new Color(this.a(this.getParameter(d("Cl&ZhWl!Qkfl8P}"))));
        this.i = new Color(this.a(this.getParameter(d("Gb7ThWl!Qkfl8P}vf8ZlQj;Q"))));
        this.U = new Color(this.a(this.getParameter(d("Cl&ZhWl!Qkfl8P}vf8ZlQj;Q"))));
        this.N = this.a(this.M);
        this.h = this.a(this.g);
        this.T = this.a(this.S);
        this.j = this.a(this.i);
        this.V = this.a(this.U);
        this.e = this.a(this.getParameter(d("Dv PGLd<sfBk ")), false);
        this.bM = this.a(this.getParameter(d("Hf9[jIb-")), 1000);
        this.cF = this.a(this.getParameter(d("Uq=P}Lw-")), 3);
        if (this.cF < 1) {
            this.cF = 1;
        }
        this.bO = this.a(this.getParameter(d("hj:lVk@")), 10);
        this.cM = this.getParameter(d("Wf3Y}Dn1QnHf"));
        if (this.cM == null) {
            this.cM = d("zp1Si");
        }
        if (this.getParameter(d("Wf3QjRe&^b@")).equalsIgnoreCase(d("|F\u0007"))) {
            this.ca = true;
        }
        this.de = this.a(this.getParameter(d("V`&PcIP$ZjAJ:x`Lm3")), 128);
        this.df = this.a(this.getParameter(d("V`&PcIP$ZjAL!KHJj:X")), 128);
        this.dA = this.a(this.getParameter(d("Kv9]jWL2}`Pm7Z|lm\u0013PfKd")), 0) + 1;
        this.dB = this.a(this.getParameter(d("Kv9]jWL2}`Pm7Z|jv x`Lm3")), 0) + 1;
        this.s = this.a(this.getParameter(d("Gl!Ql@D&^yLw-")), 5);
        this.r = this.a(this.getParameter(d("Gl!Ql@B9OcLw![j")), 100);
        switch (this.q = this.a(this.getParameter(d("gl&[jWP Fc@")), 1)) {
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
        this.cX = this.a(this.getParameter(d("V`&PcIA5M\\Qz8Z")), 1);
        if (this.cX == 0) {
            this.cW = 11;
        }
        else {
            this.cW = 16;
        }
        this.k = this.getParameter(d("Gb7TfHb3Z"));
        if (this.bM < 0) {
            this.bM = 0;
        }
        if (this.cF > 10) {
            this.cF = 10;
        }
        else if (this.cF < 1) {
            this.cF = 1;
        }
        int n19 = 0;
        if (this.getParameter(d("Qq1ZMJo0")).equalsIgnoreCase(d("|F\u0007"))) {
            n19 |= 0x1;
        }
        final String parameter8 = this.getParameter(d("Qq1ZFQb8Vl"));
        if (parameter8 != null && parameter8.equalsIgnoreCase(d("|F\u0007"))) {
            n19 |= 0x2;
        }
        this.bt = this.a(this.getParameter(d("Qq1ZIJm lf_f")), 11);
        this.bN = new Font(this.getParameter(d("Qq1ZIJm ")), n19, this.bt);
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
            (this.W = new Frame(this.getParameter(d("Co;^{Lw8Z")))).add(d("ff:KjW"), this);
        }
        if (!this.cN) {
            (this.dK = new Lware(this.getAppletContext(), new Label(d("qq1Z/hf:J/Ds$SjQ#6F/dm2F/qf5R/\u0014:m\u0007 \u001c:z")))).setTitle(d("qq1Z/hf:J/ds$SjQ#6F/dm2F/qf5R"));
            this.dK.hide();
        }
    }
    
    void v() {
        this.cH = new MemoryImageSource(this.x, this.u, new DirectColorModel(24, 16711680, 65280, 255), this.t, 0, this.x);
        String s;
        try {
            s = System.getProperty(d("Ob\"^!Sf&LfJm"));
        }
        catch (SecurityException ex) {
            s = d("Pm?");
        }
        if (!s.startsWith(d("\u0014-d"))) {
            try {
                this.cH.setAnimated(true);
                this.cH.setFullBufferUpdates(true);
                this.v = this.createImage(this.cH);
                this.cH.newPixels();
                this.bx = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.bx = false;
            }
        }
        if (!this.bx) {
            this.cH = null;
            this.cG = new anfy(this.x, this.u, new DirectColorModel(24, 16711680, 65280, 255), this.t, 0, this.x);
            this.v = this.createImage(this.cG);
        }
    }
    
    private void b(final int n) {
        if (this.bv[n] != null && this.bv[n].length() > 1) {
            try {
                final URL url = new URL(this.getDocumentBase(), this.bv[n]);
                final AppletContext appletContext = this.getAppletContext();
                if (this.ca) {
                    appletContext.showDocument(url, this.cM);
                    return;
                }
                appletContext.showDocument(url);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public final synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.cN) {
            this.dK.show();
            this.dK.toFront();
            this.dK.requestFocus();
        }
        else {
            this.bU = true;
            this.bP = false;
            this.bQ = -1;
            this.bS = -1;
            if (!(n < this.p | n > this.p + this.x | n2 < this.o | n2 > this.o + this.u)) {
                this.bQ = n - this.p;
                this.bS = n2 - this.o;
                this.bR = this.bQ;
                this.bT = this.bS;
                this.dE = true;
            }
        }
        return true;
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        this.bV = true;
        this.bU = true;
        this.bQ = -1;
        this.bS = -1;
        if (!(n < this.p | n > this.p + this.x | n2 < this.o | n2 > this.o + this.u)) {
            this.bQ = n - this.p;
            this.bS = n2 - this.o;
        }
        return true;
    }
    
    public final synchronized boolean mouseMove(final Event event, final int bw, final int bx) {
        this.bW = bw;
        this.bX = bx;
        return true;
    }
    
    public final synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.cN) {
            this.bU = false;
            this.bV = false;
            this.dg = -1;
            this.K = -1;
            this.bQ = -1;
            this.bS = -1;
            if (!(n < this.p | n > this.p + this.x | n2 < this.o | n2 > this.o + this.u)) {
                this.bQ = n - this.p;
                this.bS = n2 - this.o;
                this.bR = this.bQ;
                this.bT = this.bS;
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.E) {
            if (this.cC != null) {
                this.w();
            }
            graphics.drawImage(this.be, 0, 0, this);
        }
    }
    
    public synchronized void w() {
        if (this.c) {
            this.notifyAll();
            while (!this.cq) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.cq = false;
        }
        this.co.drawImage(this.cC, this.cD, this.cE, this);
    }
    
    public final void x() {
        try {
            if (this.bx) {
                this.cH.newPixels();
                return;
            }
            this.cG.startProduction(this.cG.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    private void b(final int dj, final int n, final boolean b) {
        int[] array;
        short[] array2;
        boolean[] array3;
        if (b) {
            array = this.dh;
            array2 = this.dk;
            array3 = this.di;
        }
        else {
            array = this.cy;
            array2 = this.cA;
            array3 = this.cz;
        }
        int n2 = this.bK;
        if (this.cs > this.bK) {
            n2 = this.cs;
        }
        final boolean[] array4 = new boolean[n2];
        for (int i = 0; i < n2; ++i) {
            array4[i] = false;
        }
        this.dj = dj;
        for (int j = 1; j < dj; ++j) {
            final short n3 = array2[j];
            if (!array3[j]) {
                array4[n3 - 1] = false;
            }
            else {
                array4[n3 - 1] = true;
            }
        }
        while (this.dj < n) {
            final int n4 = array[this.dj];
            final short n5 = array2[this.dj];
            this.H = (short)(n5 * this.bs);
            if (this.dj != 0) {
                if (!array3[this.dj]) {
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
            ++this.dj;
        }
    }
    
    private void y() {
        this.bq = 0;
        int bq;
        for (bq = 0; this.getParameter(d("Fl:YfB") + bq) != null; ++bq) {}
        this.bq = bq;
        this.q();
        for (int i = 0; i < this.bq; ++i) {
            int j = 0;
            final String parameter = this.getParameter(d("Fl:YfB") + i);
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
        this.ci = this.bq;
    }
    
    final void z() {
        int n;
        int n2;
        if (this.cf != this.bZ) {
            n = this.cf * this.br;
            n2 = (this.bZ + 1) * this.br;
        }
        else {
            n = this.cB * this.br;
            n2 = (this.cr + 1) * this.br;
        }
        if (n2 > this.cO - this.B) {
            if (n2 - n > this.cO) {
                this.B = -n;
                return;
            }
            this.B = -(n2 - this.cO);
        }
    }
    
    public void run() {
        if (this.Q) {
            this.u();
        }
        Thread.currentThread().setPriority(this.cF);
        final Graphics graphics = this.getGraphics();
        if (this.cC != null && !this.c) {
            this.c = this.c();
        }
        System.gc();
        this.J = System.currentTimeMillis();
        this.dq = this.getToolkit();
        int n = 0;
        this.co.setColor(this.g);
        this.co.fillRect(0, 0, this.bw, this.bj);
        this.A();
        this.repaint();
        int n2 = 0;
        if (this.ci > 0) {
            this.bn[0] = true;
        }
        while (this.dr != null) {
            if (this.dJ == -1) {
                this.f();
                this.k();
            }
            this.g();
            if (this.P && ++n2 == 100) {
                this.W.show();
                n2 = 0;
            }
            this.B();
            if (this.dJ != 1) {
                try {
                    this.x();
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            this.co.drawImage(this.v, this.p, this.o, this);
            if (this.cC != null) {
                this.w();
            }
            this.co.setColor(this.S);
            if (this.E) {
                graphics.drawImage(this.be, 0, 0, this);
            }
            if (++n == this.bM) {
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
                if (this.bQ < this.cU + this.br) {
                    this.y += this.cP;
                    return;
                }
                this.y -= this.cP;
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
                if (this.bS < this.dc + this.br) {
                    this.B += this.cO;
                }
                else {
                    this.B -= this.cO;
                }
                this.dg = -1;
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
        this.co.setColor(this.dn[4]);
        this.co.drawLine(0, 0, this.bw, 0);
        if (this.q == 2) {
            this.co.setColor(this.dn[2]);
            this.co.drawLine(1, 1, this.bw - 1, 1);
            this.co.setColor(this.dn[1]);
            this.co.drawLine(2, 2, this.bw - 2, 2);
            this.co.setColor(this.dn[0]);
            this.co.drawLine(3, 3, this.bw - 3, 3);
        }
        this.co.setColor(this.dn[3]);
        this.co.drawLine(0, 0, 0, this.bj);
        if (this.q == 2) {
            this.co.setColor(this.dn[4]);
            this.co.drawLine(1, 1, 1, this.bj - 1);
            this.co.setColor(this.dn[1]);
            this.co.drawLine(2, 2, 2, this.bj - 2);
            this.co.setColor(this.dn[0]);
            this.co.drawLine(3, 3, 3, this.bj - 3);
        }
        this.co.setColor(this.dn[1]);
        this.co.drawLine(0, this.bj - 1, this.bw, this.bj - 1);
        if (this.q == 2) {
            this.co.setColor(this.dn[2]);
            this.co.drawLine(1, this.bj - 2, this.bw - 1, this.bj - 2);
            this.co.setColor(this.dn[4]);
            this.co.drawLine(2, this.bj - 3, this.bw - 2, this.bj - 3);
            this.co.setColor(this.dn[3]);
            this.co.drawLine(3, this.bj - 4, this.bw - 3, this.bj - 4);
        }
        this.co.setColor(this.dn[1]);
        this.co.drawLine(this.bw - 1, 0, this.bw - 1, this.bj - 1);
        if (this.q == 2) {
            this.co.setColor(this.dn[2]);
            this.co.drawLine(this.bw - 2, 1, this.bw - 2, this.bj - 2);
            this.co.setColor(this.dn[4]);
            this.co.drawLine(this.bw - 3, 2, this.bw - 3, this.bj - 3);
            this.co.setColor(this.dn[3]);
            this.co.drawLine(this.bw - 4, 3, this.bw - 4, this.bj - 4);
        }
    }
    
    final synchronized void B() {
        this.cP = this.x;
        this.cO = this.u;
        this.ct = this.cJ;
        if (this.dJ < 1) {
            this.cJ = this.cl * this.br;
        }
        else {
            this.cJ = this.D();
        }
        this.cK = 0;
        for (int i = 0; i < this.cl; ++i) {
            final int ck = this.bd[this.dh[i]] + this.dk[i] * this.bs + this.br;
            if (ck > this.cK) {
                this.cK = ck;
            }
        }
        boolean b = false;
        if (this.cK > this.cP) {
            this.cO -= this.cW;
            b = true;
        }
        if (this.cJ > this.cO) {
            this.cP -= this.cW;
        }
        if (!b & this.cK > this.cP) {
            this.cO -= this.cW;
        }
        if (this.dJ == -1) {
            this.d();
        }
        else {
            this.z();
        }
        if (this.B > 0) {
            this.B = 0;
        }
        if (this.B + this.cJ < this.cO) {
            this.B = this.cO - this.cJ;
        }
        if (this.cJ < this.cO) {
            this.B = 0;
        }
        if (this.y > 0) {
            this.y = 0;
        }
        if (this.y + this.cK < this.cP) {
            this.y = this.cP - this.cK;
        }
        if (this.cK < this.cP) {
            this.y = 0;
        }
        if (this.dJ == -1) {
            this.C = this.B;
            this.z = this.y;
        }
        this.I = this.B;
        if (this.dJ == -1) {
            this.b(0, this.cl, true);
        }
        else {
            this.C();
        }
        if (this.cK > this.cP) {
            this.r();
        }
        if (this.cJ > this.cO) {
            this.t();
        }
        if (this.cK > this.cP & this.cJ > this.cO) {
            this.s();
        }
    }
    
    public synchronized void start() {
        if (this.dr == null) {
            (this.dr = new Thread(this)).start();
            if (this.Q) {
                this.u();
                this.W.show();
            }
        }
    }
    
    public synchronized void stop() {
        if (this.dr != null && this.dr.isAlive()) {
            this.dr.stop();
        }
        if (this.Q) {
            this.W.hide();
        }
        this.dr = null;
    }
    
    public treemenu() {
        this.bx = false;
        this.E = false;
        this.bG = false;
        this.bw = 600;
        this.bj = 600;
        this.ca = false;
        this.cW = 11;
        this.p = 4;
        this.o = 4;
        this.e = false;
        this.q = 1;
        this.cX = 1;
        this.Q = false;
        this.P = false;
        this.c = false;
        this.cN = false;
        this.cb = false;
        this.cq = false;
        this.a = 1.0f;
        this.L = false;
        this.dE = false;
        this.bP = true;
        this.G = -1;
        this.K = -1;
        this.dJ = -1;
        this.bW = -1;
        this.bX = -1;
        this.dg = -1;
        this.bU = false;
        this.bV = false;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void C() {
        if (this.dJ == 0) {
            this.dw = 1;
            this.dt = 0;
            this.dz = this.cr - this.cB;
            this.dy = this.bZ - this.cf;
            if (this.dy != 0) {
                this.dH = this.dy * this.br;
                this.dI = -this.dH;
            }
            else {
                this.dH = this.dz * this.br;
                this.dI = this.dH;
            }
        }
        ++this.dJ;
        int n;
        if (this.dy != 0) {
            n = this.cf;
            final int bz = this.bZ;
            this.I = this.B + (n + 1) * this.br + this.dI;
            this.b(n + 1, bz + 1, true);
        }
        else {
            n = this.cB;
            final int cr = this.cr;
            this.I = this.B + (n + 1) * this.br + this.dI - this.dz * this.br;
            this.b(n + 1, cr + 1, false);
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
        this.I += this.dy * this.br + this.dI;
        this.b(this.bZ + 1, this.cl, true);
        if (this.dy != 0) {
            this.dI = (int)(-this.dG[this.dw] * this.dH * this.dv[this.dt] * this.a);
            ++this.dw;
            if (this.dw >= this.dD - 1 & this.dx < this.dD - 1) {
                ++this.dt;
            }
            if (this.dw >= this.dD * 2 - 1) {
                this.dw = 0;
            }
            this.dx = this.dw;
            if (this.dt >= this.dB) {
                this.dJ = -1;
            }
        }
        else {
            this.dI = (int)(this.dF[this.dw] * this.dH * this.du[this.dt] * this.a);
            ++this.dw;
            if (this.dw >= this.dC - 1 & this.dx < this.dC - 1) {
                ++this.dt;
            }
            if (this.dw >= this.dC * 2 - 1) {
                this.dw = 0;
            }
            this.dx = this.dw;
            if (this.dt >= this.dA) {
                this.dJ = -1;
            }
        }
        ++this.dw;
        if (this.dJ == -1) {
            this.dI = 0;
        }
    }
    
    private int D() {
        int ct;
        if (this.dJ > 0) {
            ct = this.cl * this.br + this.dI;
        }
        else {
            ct = this.ct;
        }
        return ct;
    }
    
    public synchronized void E() {
        Thread.yield();
        this.dq.sync();
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
            Thread.sleep(this.bO);
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
                char c = '%';
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
                                c = '\u0003';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = 'T';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '?';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '\u000f';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
