import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ColorModel;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class planet extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
    int a;
    int b;
    int[] c;
    float d;
    float e;
    float f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int[] m;
    float[] n;
    float[] o;
    int[] p;
    int q;
    boolean r;
    float s;
    int t;
    float[] u;
    String[] v;
    int[] w;
    d x;
    Thread y;
    int[] z;
    int[] A;
    int[] B;
    DirectColorModel C;
    MemoryImageSource D;
    Image E;
    int F;
    int[] G;
    String H;
    int I;
    int J;
    int K;
    int L;
    int M;
    int N;
    long O;
    long P;
    long Q;
    b R;
    boolean S;
    boolean T;
    boolean U;
    boolean V;
    boolean W;
    boolean X;
    boolean Y;
    boolean Z;
    int aa;
    int ab;
    int ac;
    int ad;
    int ae;
    float af;
    float ag;
    boolean[] ah;
    boolean[] ai;
    float[] aj;
    int ak;
    int al;
    float[][] am;
    
    final void a() {
        this.e();
        this.g();
        this.i();
        this.b(50);
        this.R.a("font.gif", 8, 16);
        this.x.a();
        this.c();
    }
    
    final void b() {
        this.a(-16777216);
        this.f();
        this.h();
        this.j();
        Label_0064: {
            planet planet;
            boolean r;
            if (this.ac < this.q && this.Z) {
                planet = this;
                r = false;
            }
            else {
                if (this.ac < this.q || !this.Z) {
                    break Label_0064;
                }
                planet = this;
                r = true;
            }
            planet.r = r;
        }
        this.x.b();
        this.x.c();
        final int ab = this.ab;
        final int ac = this.ac;
        this.b(0.0f, this.q, 512.0f, this.q, this.r ? -10485760 : -10461088);
        this.R.a(8, 452, "Color", 255, 255, 255, 0, 255, 255, 255, 255);
        int i;
        for (i = 0; i < 10; ++i) {
            this.c(58 + i * 10, 452, 8.0f, 16.0f, this.m[i]);
        }
        if (this.r && 57 < ab && ab < 167 && 452 <= ac && ac <= 468) {
            final int h = (ab - 57) / 10;
            this.c(57 + h * 10, 451, 10.0f, 18.0f, this.m[h]);
            if (this.U) {
                this.h = h;
            }
        }
        this.R.a(58 + i * 10, 452, "?", 255, 255, 255, 255, 64, 64, 64, 255);
        this.a(57 + this.h * 10, 451, 9, 17, -65536);
        this.R.a(8, 472, "Track", 255, 255, 255, 0, 255, 255, 255, 255);
        this.R.a(58, 472, (new String[] { "non", "line", "long line", "point" })[this.j], 255, 255, 255, 255, 255, 255, 255, 0);
        if (this.r && 57 < ab && ab < 167 && 472 <= ac && ac <= 488) {
            this.a(57, 471, 110, 17, -65536);
            if (this.aa != 0) {
                this.j = b(this.j + this.aa, 0, 3);
            }
        }
        this.R.a(255, 255, 255, 255);
        this.R.b(0, 0, 0, 255);
        this.R.a(210, 452, " Target");
        this.R.a(210, 472, " Random" + this.k);
        this.R.a(210, 492, " Circle");
        this.R.a(282, 452, " Size " + (new String[] { "S", "M", "L" })[this.i]);
        this.R.a(282, 472, " Star");
        this.R.a(354, 452, " Scale " + this.p[this.l]);
        this.R.a(354, 472, " Speed" + this.v[this.t]);
        this.R.a(354, 492, " Reset");
        if (this.r && 210 <= ab && ab < 498 && 452 <= ac && ac < 512) {
            final int n = (ab - 210) / 72;
            final int n2 = (ac - 452) / 20;
            this.a(210 + n * 72, 451 + n2 * 20, 72, 18, -65536);
            if (this.aa != 0) {
                final int a;
                if ((a = a(n * 3 + n2, 0, 11)) == 1 && this.g == 1) {
                    this.k = b(this.k + this.aa, 1, 5);
                }
                else if (a == 3) {
                    this.i = b(this.i + this.aa, 0, 2);
                }
                else if (a == 6) {
                    final int[] array = { 20, 10, 5 };
                    this.l = b(this.l + this.aa, 0, 2);
                    this.f = array[this.l];
                }
                else if (a == 7) {
                    this.t = b(this.t + this.aa, 0, 6);
                    this.s = this.u[this.t];
                }
                else if (a == 8) {
                    this.x.a();
                }
                else {
                    this.g = a;
                }
            }
        }
        this.R.a(210 + this.g / 3 * 72, 452 + this.g % 3 * 20, "*", 255, 0, 0, 255, 0, 0, 0, 255);
        this.R.a(8, 492, "Copyright(C) 2006 ha55ii", 0, 0, 0, 0, 255, 255, 0, 80);
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        for (int j = 0; j < this.x.b; ++j) {
            if (this.x.a[j].f == 0) {
                ++n3;
            }
            if (this.x.a[j].f == 1) {
                ++n4;
            }
            if (this.x.a[j].f == 2) {
                ++n5;
            }
        }
        this.R.a(310, 2, "S=" + n3, 255, 255, 255, 255, 0, 0, 0, 0);
        this.R.a(358, 2, "M=" + n4, 255, 255, 255, 255, 0, 0, 0, 0);
        this.R.a(406, 2, "L=" + n5, 255, 255, 255, 255, 0, 0, 0, 0);
        this.R.a(454, 2, "SUM=" + this.x.b, 255, 255, 255, 255, 0, 0, 0, 0);
        this.a(2, 2);
    }
    
    public void init() {
        this.z = new int[this.a * this.b];
        this.A = new int[this.b];
        this.B = new int[this.b];
        this.C = new DirectColorModel(32, 16711680, 65280, 255);
        (this.D = new MemoryImageSource(this.a, this.b, this.z, 0, this.a)).setFullBufferUpdates(true);
        this.D.setAnimated(true);
        this.E = this.createImage(this.D);
        this.k();
        this.a();
        System.out.println("Copyright(C) 2006 ha55ii, http://dan-ball.jp/");
    }
    
    public void start() {
        if (this.y == null) {
            (this.y = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.y != null) {
            this.b();
            this.paint(this.getGraphics());
            try {
                this.F = this.d();
                Thread.sleep(this.F);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.D.newPixels(this.z, this.C, 0, this.a);
        graphics.drawImage(this.E, 0, 0, null);
    }
    
    public void stop() {
        this.y = null;
    }
    
    final void c() {
        this.J = this.c.length;
        planet planet = this;
        int i = 0;
        Label_0047: {
            break Label_0047;
            do {
                planet = this;
                i = this.I + 1;
                planet.I = i;
            } while (this.I < this.J - 1 && this.H.charAt(this.I) == this.c[this.I]);
        }
        if (this.I == this.J - 1 && this.H.charAt(this.I) == this.c[this.I]) {
            this.G[0] = 1;
            ++this.I;
        }
        if (this.I != this.J) {
            this.z = null;
        }
    }
    
    final void a(final float n, final float n2, final int n3) {
        final int n4 = (int)(n * this.f + this.d + 0.5f);
        final int n5 = (int)(n2 * this.f + this.e + 0.5f);
        if (n4 < 0 || this.a <= n4) {
            return;
        }
        if (n5 < 0 || this.b <= n5) {
            return;
        }
        this.z[n5 * this.a + n4] = n3;
    }
    
    final void a(final float n, final float n2, final float n3, final float n4, final int n5) {
        this.b(n * this.f + this.d, n2 * this.f + this.e, n3 * this.f + this.d, n4 * this.f + this.e, n5);
    }
    
    final void a(final float n, final float n2, final float n3, final int n4) {
        this.b(n * this.f + this.d, n2 * this.f + this.e, n3 * this.f, n4);
    }
    
    final void a(final int n) {
        for (int i = this.a * this.b - 1; i >= 0; --i) {
            this.z[i] = n;
        }
    }
    
    final void b(float n, float n2, final float n3, final float n4, final int n5) {
        final float n6 = n3 - n;
        final float n7 = n4 - n2;
        final int n8 = (int)(a(b(n6), b(n7)) + 1.0f);
        final float n9 = n6 / n8;
        final float n10 = n7 / n8;
        n += 0.5f;
        n2 += 0.5f;
        for (int i = 0; i < n8; ++i, n += n9, n2 += n10) {
            final int n11 = (int)n;
            final int n12 = (int)n2;
            if (n11 >= 0) {
                if (this.a > n11) {
                    if (n12 >= 0) {
                        if (this.b > n12) {
                            this.z[n12 * this.a + n11] = n5;
                        }
                    }
                }
            }
        }
    }
    
    final void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.b(n, n2, n + n3, n2, n5);
        this.b(n, n2 + n4, n + n3, n2 + n4, n5);
        this.b(n, n2, n, n2 + n4, n5);
        this.b(n + n3, n2, n + n3, n2 + n4, n5);
    }
    
    final void c(final float n, final float n2, final float n3, final float n4, final int n5) {
        final int n6 = (n < 0.0f) ? 0 : ((int)n);
        int i = (n2 < 0.0f) ? 0 : ((int)n2);
        final int n7 = (n + n3 > this.a) ? this.a : ((int)(n + n3));
        final int n8 = (n2 + n4 > this.b) ? this.b : ((int)(n2 + n4));
        int n9 = i * this.a + n6;
        final int n10 = this.a - (n7 - n6);
        while (i < n8) {
            for (int j = n6; j < n7; ++j) {
                this.z[n9++] = n5;
            }
            n9 += n10;
            ++i;
        }
    }
    
    final void b(float n, float n2, final float n3, final int n4) {
        n += 0.5f;
        int n5 = (int)((n2 += 0.5f) - n3);
        int b = (int)(n2 + n3) + 1;
        if (n5 < 0) {
            n5 = 0;
        }
        if (b > this.b) {
            b = this.b;
        }
        for (int i = n5; i < b; ++i) {
            this.A[i] = this.a;
            this.B[i] = -1;
        }
        int n6;
        int n7;
        for (n6 = (int)(n3 * 2.0f * 3.141593f), n7 = 3; n7 < 9 && 1 << n7 <= n6; ++n7) {}
        for (int n8 = 1 << n7, n9 = 512 / n8, n10 = 0, j = 0; j < n8; ++j, n10 += n9) {
            final float n11 = n + this.am[n10][0] * n3;
            final float n12 = n2 + this.am[n10][1] * n3;
            final int n13 = (int)n11;
            final int n14;
            if ((n14 = (int)n12) >= 1) {
                if (this.b - 1 > n14) {
                    Label_0968: {
                        if (n13 > 0 && n13 < this.a - 1) {
                            final int n15 = n14 * this.a + n13;
                            int[] array = null;
                            int n16 = 0;
                            int n17 = 0;
                            int n18 = 0;
                            float n19 = 0.0f;
                            Label_0964: {
                                float n20 = 0.0f;
                                float n22 = 0.0f;
                                Label_0963: {
                                    int n21 = 0;
                                    Label_0962: {
                                        if (n10 < 64) {
                                            if (this.B[n14] >= n13) {
                                                break Label_0968;
                                            }
                                        }
                                        else {
                                            if (n10 == 64) {
                                                this.z[n15 + 1] = a(this.z[n15 + 1], n4, n11 - n13);
                                                this.z[n15 + this.a] = a(this.z[n15 + this.a], n4, n12 - n14);
                                                array = this.z;
                                                n16 = n15 + 1 + this.a;
                                                n17 = this.z[n15 + 1 + this.a];
                                                n18 = n4;
                                                n19 = (n11 - n13 + (n12 - n14)) / 4.0f;
                                                break Label_0964;
                                            }
                                            if (n10 < 192) {
                                                if (n13 < this.A[n14] || this.B[n14] < n13) {
                                                    array = this.z;
                                                    n16 = n15 + this.a;
                                                    n17 = this.z[n15 + this.a];
                                                    n18 = n4;
                                                    n20 = n12;
                                                    n21 = n14;
                                                    break Label_0962;
                                                }
                                                break Label_0968;
                                            }
                                            else {
                                                if (n10 == 192) {
                                                    this.z[n15 + this.a] = a(this.z[n15 + this.a], n4, n12 - n14);
                                                    this.z[n15 - 1] = a(this.z[n15 - 1], n4, 1.0f - (n11 - n13));
                                                    array = this.z;
                                                    n16 = n15 - 1 + this.a;
                                                    n17 = this.z[n15 - 1 + this.a];
                                                    n18 = n4;
                                                    n19 = (n12 - n14 + 1.0f - (n11 - n13)) / 4.0f;
                                                    break Label_0964;
                                                }
                                                if (n10 < 320) {
                                                    if (this.A[n14] > n13) {
                                                        array = this.z;
                                                        n16 = n15 - 1;
                                                        n17 = this.z[n15 - 1];
                                                        n18 = n4;
                                                        n20 = 1.0f;
                                                        n22 = n11 - n13;
                                                        break Label_0963;
                                                    }
                                                    break Label_0968;
                                                }
                                                else {
                                                    if (n10 == 320) {
                                                        this.z[n15 - 1] = a(this.z[n15 - 1], n4, 1.0f - (n11 - n13));
                                                        this.z[n15 - this.a] = a(this.z[n15 - this.a], n4, 1.0f - (n12 - n14));
                                                        array = this.z;
                                                        n16 = n15 - 1 - this.a;
                                                        n17 = this.z[n15 - 1 - this.a];
                                                        n18 = n4;
                                                        n19 = (1.0f - (n11 - n13) + 1.0f - (n12 - n14)) / 4.0f;
                                                        break Label_0964;
                                                    }
                                                    if (n10 < 448) {
                                                        if (n13 < this.A[n14] || this.B[n14] < n13) {
                                                            array = this.z;
                                                            n16 = n15 - this.a;
                                                            n17 = this.z[n15 - this.a];
                                                            n18 = n4;
                                                            n20 = 1.0f;
                                                            n22 = n12 - n14;
                                                            break Label_0963;
                                                        }
                                                        break Label_0968;
                                                    }
                                                    else {
                                                        if (n10 == 448) {
                                                            this.z[n15 - this.a] = a(this.z[n15 - this.a], n4, 1.0f - (n12 - n14));
                                                            this.z[n15 + 1] = a(this.z[n15 + 1], n4, n11 - n13);
                                                            array = this.z;
                                                            n16 = n15 + 1 - this.a;
                                                            n17 = this.z[n15 + 1 - this.a];
                                                            n18 = n4;
                                                            n19 = (1.0f - (n12 - n14) + (n11 - n13)) / 4.0f;
                                                            break Label_0964;
                                                        }
                                                        if (this.B[n14] >= n13) {
                                                            break Label_0968;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        array = this.z;
                                        n16 = n15 + 1;
                                        n17 = this.z[n15 + 1];
                                        n18 = n4;
                                        n20 = n11;
                                        n21 = n13;
                                    }
                                    n22 = n21;
                                }
                                n19 = n20 - n22;
                            }
                            array[n16] = a(n17, n18, n19);
                        }
                    }
                    if (this.A[n14] > n13) {
                        this.A[n14] = n13;
                    }
                    if (this.B[n14] < n13) {
                        this.B[n14] = n13;
                    }
                }
            }
        }
        for (int k = n5; k < b; ++k) {
            for (int n23 = k * this.a + this.A[k], l = this.A[k]; l <= this.B[k]; ++l, ++n23) {
                if (l >= 0) {
                    if (this.a > l) {
                        this.z[n23] = n4;
                    }
                }
            }
        }
    }
    
    static int a(final int n, final int n2, final float n3) {
        final int n4 = (n & 0xFF0000) >> 16;
        final int n5 = (int)((((n2 & 0xFF0000) >> 16) - n4) * n3) + n4;
        final int n6 = (n & 0xFF00) >> 8;
        final int n7 = (int)((((n2 & 0xFF00) >> 8) - n6) * n3) + n6;
        final int n8 = n & 0xFF;
        return 0xFF000000 | n5 << 16 | n7 << 8 | (int)(((n2 & 0xFF) - n8) * n3) + n8;
    }
    
    final void b(final int n) {
        this.K = 0;
        this.L = 0;
        this.N = 1000 / n;
        this.O = System.currentTimeMillis();
        this.P = this.O + this.N;
        this.Q = this.O;
        this.M = 0;
    }
    
    final int d() {
        ++this.K;
        this.O = System.currentTimeMillis();
        int n;
        if ((n = (int)(this.P - this.O)) < 1) {
            n = 1;
        }
        this.M += n;
        this.P += this.N;
        if (this.O + n >= this.Q) {
            this.M = 0;
            this.L = this.K;
            this.K = 0;
            this.P = this.O + this.N;
            this.Q = this.O + 1000L;
        }
        return n;
    }
    
    final void a(final int n, final int n2) {
        this.R.a(8, 16);
        this.R.a(255, 255, 255, 0);
        this.R.b(64, 64, 64, 255);
        this.R.a(n, n2, String.valueOf(this.L) + "fps");
    }
    
    final void e() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    final void f() {
        this.S = (!this.U && this.V);
        this.T = (this.U && !this.V);
        this.W = (this.X && !this.Y);
        this.U = this.V;
        this.X = this.Y;
        this.Z = !(this.T | this.U | this.W | this.X);
        this.aa = (this.T ? 1 : (this.W ? -1 : 0));
        this.ab = this.ad;
        this.ac = this.ae;
        this.af = (this.ab - this.d) / this.f;
        this.ag = (this.ac - this.e) / this.f;
        if (this.I != this.J) {
            this.z = null;
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.V = true;
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            this.Y = true;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.V = false;
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            this.Y = false;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.ad = point.x;
        this.ae = point.y;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.ad = point.x;
        this.ae = point.y;
    }
    
    final void g() {
        this.addKeyListener(this);
        for (int i = 0; i < 256; ++i) {
            this.ah[i] = false;
            this.ai[i] = false;
        }
    }
    
    final void h() {
        for (int i = 0; i < 256; ++i) {
            this.ah[i] = this.ai[i];
            this.ai[i] = false;
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar;
        if ((keyChar = keyEvent.getKeyChar()) < '\u0100') {
            this.ai[keyChar] = true;
            if (keyChar >= 'A' && keyChar <= 'Z') {
                this.ai[keyChar + ' '] = true;
            }
            if (keyChar >= 'a' && keyChar <= 'z') {
                this.ai[keyChar - ' '] = true;
            }
        }
    }
    
    final void i() {
        for (int i = 0; i < 1024; ++i) {
            this.aj[i] = i / 1024.0f;
        }
        for (int j = 0; j < 1024; ++j) {
            final int n = (int)(Math.random() * 1024.0);
            final float n2 = this.aj[j];
            this.aj[j] = this.aj[n];
            this.aj[n] = n2;
        }
    }
    
    final void j() {
        this.ak = (this.ak + (int)(Math.random() * 1024.0) & 0x3FF);
        this.al = ((int)(Math.random() * 512.0) | 0x1);
        if (this.G[0] == 0) {
            for (int i = 0; i < 1024; ++i) {
                this.aj[i] = 0.0f;
            }
        }
    }
    
    final float a(final float n) {
        this.ak += this.al;
        this.ak &= 0x3FF;
        return this.aj[this.ak] * n;
    }
    
    final void k() {
        int i;
        for (i = 0; i < 512; ++i) {
            final float n = 360.0f * i / 512 * 3.141593f / 180.0f;
            this.am[i][0] = (float)Math.cos(n);
            this.am[i][1] = (float)Math.sin(n);
        }
        this.am[i][0] = this.am[0][0];
        this.am[i][1] = this.am[0][1];
    }
    
    static float b(final float n) {
        if (n < 0.0f) {
            return -n;
        }
        return n;
    }
    
    static float a(final float n, final float n2) {
        if (n > n2) {
            return n;
        }
        return n2;
    }
    
    static int a(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    static int b(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n3;
        }
        if (n > n3) {
            return n2;
        }
        return n;
    }
    
    public planet() {
        this.a = 512;
        this.b = 512;
        this.c = new int[] { 104, 116, 116, 112, 58, 47, 47, 100, 97, 110, 45, 98, 97, 108, 108, 46, 106, 112, 47 };
        this.d = 256.0f;
        this.e = 236.0f;
        this.f = 20.0f;
        this.h = 10;
        this.i = 1;
        this.j = 1;
        this.k = 3;
        this.m = new int[] { -65536, -32768, -256, -16711936, -16711681, -16776961, -8388353, -65408, -8355712, -1, -1 };
        this.n = new float[] { 0.0f, 0.032f, 0.096f, 0.3f };
        this.o = new float[] { 0.1f, 1.0f, 100.0f, 1000.0f };
        this.p = new int[] { 1, 2, 4 };
        this.q = 444;
        this.r = false;
        this.s = 0.1f;
        this.t = 3;
        this.u = new float[] { 0.0f, 0.025f, 0.05f, 0.1f, 0.1f, 0.1f, 0.1f };
        this.v = new String[] { " 0", "1/4", "1/2", " 1", " 2", " 4", " 8" };
        this.w = new int[] { 1, 1, 1, 1, 2, 4, 8 };
        this.x = new Object() {
            private final planet g = planet;
            e[] a;
            int b;
            int c;
            int d;
            int e;
            f f = new Object() {
                float a = 0.0f;
                float b = 0.0f;
                
                final f a(final float a, final float b) {
                    this.a = a;
                    this.b = b;
                    return this;
                }
                
                final f a(final f object) {
                    this.a = object.a;
                    this.b = object.b;
                    return this;
                }
                
                final f b(final f object) {
                    this.a += object.a;
                    this.b += object.b;
                    return this;
                }
                
                final f a(final f object, final f object2) {
                    this.a = object.a + object2.a;
                    this.b = object.b + object2.b;
                    return this;
                }
                
                final f b(final f object, final f object2) {
                    this.a = object.a - object2.a;
                    this.b = object.b - object2.b;
                    return this;
                }
                
                final f a(final float n) {
                    this.a *= n;
                    this.b *= n;
                    return this;
                }
                
                final f a(final f object, final float n) {
                    this.a = object.a * n;
                    this.b = object.b * n;
                    return this;
                }
                
                final f a() {
                    final float a = this.a;
                    this.a = this.b;
                    this.b = -a;
                    return this;
                }
                
                final float b() {
                    return (float)Math.sqrt(this.a * this.a + this.b * this.b);
                }
                
                final float c() {
                    final float b;
                    if ((b = this.b()) == 0.0f) {
                        return 0.0f;
                    }
                    this.a /= b;
                    this.b /= b;
                    return b;
                }
            };
            
            final void a() {
                this.e = 0;
                this.b = 2;
                this.c = 999;
                this.d = 64;
                this.a = new e[this.c];
                for (int i = 0; i < this.c; ++i) {
                    this.a[i] = new Object() {
                        private final d j = object;
                        f a = new Object() {
                            float a;
                            float b;
                            
                            {
                                this.a = 0.0f;
                                this.b = 0.0f;
                            }
                            
                            final f a(final float a, final float b) {
                                this.a = a;
                                this.b = b;
                                return this;
                            }
                            
                            final f a(final f object) {
                                this.a = object.a;
                                this.b = object.b;
                                return this;
                            }
                            
                            final f b(final f object) {
                                this.a += object.a;
                                this.b += object.b;
                                return this;
                            }
                            
                            final f a(final f object, final f object2) {
                                this.a = object.a + object2.a;
                                this.b = object.b + object2.b;
                                return this;
                            }
                            
                            final f b(final f object, final f object2) {
                                this.a = object.a - object2.a;
                                this.b = object.b - object2.b;
                                return this;
                            }
                            
                            final f a(final float n) {
                                this.a *= n;
                                this.b *= n;
                                return this;
                            }
                            
                            final f a(final f object, final float n) {
                                this.a = object.a * n;
                                this.b = object.b * n;
                                return this;
                            }
                            
                            final f a() {
                                final float a = this.a;
                                this.a = this.b;
                                this.b = -a;
                                return this;
                            }
                            
                            final float b() {
                                return (float)Math.sqrt(this.a * this.a + this.b * this.b);
                            }
                            
                            final float c() {
                                final float b;
                                if ((b = this.b()) == 0.0f) {
                                    return 0.0f;
                                }
                                this.a /= b;
                                this.b /= b;
                                return b;
                            }
                        };
                        f b = new Object() {
                            float a;
                            float b;
                            
                            {
                                this.a = 0.0f;
                                this.b = 0.0f;
                            }
                            
                            final f a(final float a, final float b) {
                                this.a = a;
                                this.b = b;
                                return this;
                            }
                            
                            final f a(final f object) {
                                this.a = object.a;
                                this.b = object.b;
                                return this;
                            }
                            
                            final f b(final f object) {
                                this.a += object.a;
                                this.b += object.b;
                                return this;
                            }
                            
                            final f a(final f object, final f object2) {
                                this.a = object.a + object2.a;
                                this.b = object.b + object2.b;
                                return this;
                            }
                            
                            final f b(final f object, final f object2) {
                                this.a = object.a - object2.a;
                                this.b = object.b - object2.b;
                                return this;
                            }
                            
                            final f a(final float n) {
                                this.a *= n;
                                this.b *= n;
                                return this;
                            }
                            
                            final f a(final f object, final float n) {
                                this.a = object.a * n;
                                this.b = object.b * n;
                                return this;
                            }
                            
                            final f a() {
                                final float a = this.a;
                                this.a = this.b;
                                this.b = -a;
                                return this;
                            }
                            
                            final float b() {
                                return (float)Math.sqrt(this.a * this.a + this.b * this.b);
                            }
                            
                            final float c() {
                                final float b;
                                if ((b = this.b()) == 0.0f) {
                                    return 0.0f;
                                }
                                this.a /= b;
                                this.b /= b;
                                return b;
                            }
                        };
                        float c;
                        float d;
                        int e;
                        int f;
                        f[] g = new f[object.d];
                        int h;
                        int i;
                        
                        {
                            this.j = object;
                            for (int i = 0; i < object.d; ++i) {
                                this.g[i] = new Object() {
                                    float a;
                                    float b;
                                    
                                    {
                                        this.a = 0.0f;
                                        this.b = 0.0f;
                                    }
                                    
                                    final f a(final float a, final float b) {
                                        this.a = a;
                                        this.b = b;
                                        return this;
                                    }
                                    
                                    final f a(final f object) {
                                        this.a = object.a;
                                        this.b = object.b;
                                        return this;
                                    }
                                    
                                    final f b(final f object) {
                                        this.a += object.a;
                                        this.b += object.b;
                                        return this;
                                    }
                                    
                                    final f a(final f object, final f object2) {
                                        this.a = object.a + object2.a;
                                        this.b = object.b + object2.b;
                                        return this;
                                    }
                                    
                                    final f b(final f object, final f object2) {
                                        this.a = object.a - object2.a;
                                        this.b = object.b - object2.b;
                                        return this;
                                    }
                                    
                                    final f a(final float n) {
                                        this.a *= n;
                                        this.b *= n;
                                        return this;
                                    }
                                    
                                    final f a(final f object, final float n) {
                                        this.a = object.a * n;
                                        this.b = object.b * n;
                                        return this;
                                    }
                                    
                                    final f a() {
                                        final float a = this.a;
                                        this.a = this.b;
                                        this.b = -a;
                                        return this;
                                    }
                                    
                                    final float b() {
                                        return (float)Math.sqrt(this.a * this.a + this.b * this.b);
                                    }
                                    
                                    final float c() {
                                        final float b;
                                        if ((b = this.b()) == 0.0f) {
                                            return 0.0f;
                                        }
                                        this.a /= b;
                                        this.b /= b;
                                        return b;
                                    }
                                };
                            }
                        }
                        
                        final void a(final float n, final float n2, final float n3, final float n4, final float c, final float d, final int e, final int f) {
                            this.a.a(n, n2);
                            this.b.a(n3, n4);
                            this.c = c;
                            this.d = d;
                            this.e = e;
                            this.f = f;
                            for (int i = 0; i < this.j.d; ++i) {
                                this.g[i].a(this.a);
                            }
                            this.h = 0;
                            this.i = 0;
                        }
                        
                        final void a(final f object, final f object2, final float c, final float d, final int e, final int f) {
                            this.a.a(object);
                            this.b.a(object2);
                            this.c = c;
                            this.d = d;
                            this.e = e;
                            this.f = f;
                            for (int i = 0; i < this.j.d; ++i) {
                                this.g[i].a(this.a);
                            }
                            this.h = 0;
                            this.i = 0;
                        }
                        
                        final void a(final e object) {
                            this.a.a(object.a);
                            this.b.a(object.b);
                            this.c = object.c;
                            this.d = object.d;
                            this.e = object.e;
                            this.f = object.f;
                            for (int i = 0; i < this.j.d; ++i) {
                                this.g[i].a(object.g[i]);
                            }
                            this.h = object.h;
                            this.i = object.i;
                        }
                    };
                }
                this.a[0].a(0.0f, 0.0f, 0.0f, 0.0f, this.g.o[3], this.g.n[3], 16777088, 3);
                this.a[1].a(5.0f, 0.0f, 0.0f, -0.45f, this.g.o[2], this.g.n[2], 255, 2);
            }
            
            final void b() {
                final Object object = new Object() {
                    float a;
                    float b;
                    
                    {
                        this.a = 0.0f;
                        this.b = 0.0f;
                    }
                    
                    final f a(final float a, final float b) {
                        this.a = a;
                        this.b = b;
                        return this;
                    }
                    
                    final f a(final f object) {
                        this.a = object.a;
                        this.b = object.b;
                        return this;
                    }
                    
                    final f b(final f object) {
                        this.a += object.a;
                        this.b += object.b;
                        return this;
                    }
                    
                    final f a(final f object, final f object2) {
                        this.a = object.a + object2.a;
                        this.b = object.b + object2.b;
                        return this;
                    }
                    
                    final f b(final f object, final f object2) {
                        this.a = object.a - object2.a;
                        this.b = object.b - object2.b;
                        return this;
                    }
                    
                    final f a(final float n) {
                        this.a *= n;
                        this.b *= n;
                        return this;
                    }
                    
                    final f a(final f object, final float n) {
                        this.a = object.a * n;
                        this.b = object.b * n;
                        return this;
                    }
                    
                    final f a() {
                        final float a = this.a;
                        this.a = this.b;
                        this.b = -a;
                        return this;
                    }
                    
                    final float b() {
                        return (float)Math.sqrt(this.a * this.a + this.b * this.b);
                    }
                    
                    final float c() {
                        final float b;
                        if ((b = this.b()) == 0.0f) {
                            return 0.0f;
                        }
                        this.a /= b;
                        this.b /= b;
                        return b;
                    }
                };
                final Object object2;
                (object2 = new Object() {
                    float a;
                    float b;
                    
                    {
                        this.a = 0.0f;
                        this.b = 0.0f;
                    }
                    
                    final f a(final float a, final float b) {
                        this.a = a;
                        this.b = b;
                        return this;
                    }
                    
                    final f a(final f object) {
                        this.a = object.a;
                        this.b = object.b;
                        return this;
                    }
                    
                    final f b(final f object) {
                        this.a += object.a;
                        this.b += object.b;
                        return this;
                    }
                    
                    final f a(final f object, final f object2) {
                        this.a = object.a + object2.a;
                        this.b = object.b + object2.b;
                        return this;
                    }
                    
                    final f b(final f object, final f object2) {
                        this.a = object.a - object2.a;
                        this.b = object.b - object2.b;
                        return this;
                    }
                    
                    final f a(final float n) {
                        this.a *= n;
                        this.b *= n;
                        return this;
                    }
                    
                    final f a(final f object, final float n) {
                        this.a = object.a * n;
                        this.b = object.b * n;
                        return this;
                    }
                    
                    final f a() {
                        final float a = this.a;
                        this.a = this.b;
                        this.b = -a;
                        return this;
                    }
                    
                    final float b() {
                        return (float)Math.sqrt(this.a * this.a + this.b * this.b);
                    }
                    
                    final float c() {
                        final float b;
                        if ((b = this.b()) == 0.0f) {
                            return 0.0f;
                        }
                        this.a /= b;
                        this.b /= b;
                        return b;
                    }
                }).a(this.g.af, this.g.ag);
                Label_0834: {
                    if (!this.g.r) {
                        Object object3 = null;
                        Object object4 = null;
                        Object object5 = null;
                        float n2 = 0.0f;
                        float n3 = 0.0f;
                        int n4 = 0;
                        int n5 = 0;
                        switch (this.g.g) {
                            case 0: {
                                if (this.g.S) {
                                    this.f.a(this.g.af, this.g.ag);
                                }
                                if (this.g.T && this.b < this.c) {
                                    object.b(this.f, object2);
                                    object.a(0.1f);
                                    final int n = (this.g.h == 10) ? ((int)this.g.a(9.9f)) : this.g.h;
                                    object3 = this.a[this.b];
                                    object4 = this.f;
                                    object5 = object;
                                    n2 = this.g.o[this.g.i];
                                    n3 = this.g.n[this.g.i];
                                    n4 = this.g.m[n];
                                    n5 = this.g.i;
                                    break;
                                }
                                break Label_0834;
                            }
                            case 1: {
                                if (this.g.U && this.b < this.c && this.e % ((this.g.i == 0) ? 2 : 10) == 0) {
                                    final float a = this.g.a(6.283185f);
                                    object.a((float)Math.cos(a), (float)Math.sin(a));
                                    object.a(this.g.k * 0.1f + 0.1f);
                                    final int n6 = (this.g.h == 10) ? ((int)this.g.a(9.9f)) : this.g.h;
                                    object3 = this.a[this.b];
                                    object4 = object2;
                                    object5 = object;
                                    n2 = this.g.o[this.g.i];
                                    n3 = this.g.n[this.g.i];
                                    n4 = this.g.m[n6];
                                    n5 = this.g.i;
                                    break;
                                }
                                break Label_0834;
                            }
                            case 2: {
                                if (this.g.S && this.b < this.c) {
                                    object.a(object2);
                                    object.a();
                                    object.c();
                                    object.a((float)Math.sqrt(1.0f / (object2.b() + 0.01f) * this.g.o[3] * 0.001f));
                                    final int n7 = (this.g.h == 10) ? ((int)this.g.a(9.9f)) : this.g.h;
                                    object3 = this.a[this.b];
                                    object4 = object2;
                                    object5 = object;
                                    n2 = this.g.o[this.g.i];
                                    n3 = this.g.n[this.g.i];
                                    n4 = this.g.m[n7];
                                    n5 = this.g.i;
                                    break;
                                }
                                break Label_0834;
                            }
                            case 4: {
                                if (!this.g.S) {
                                    break Label_0834;
                                }
                                int i;
                                for (i = 0; i < this.b; ++i) {
                                    object.b(object2, this.a[i].a);
                                    if (object.b() < this.g.n[3] * 2.0f) {
                                        this.a[i].i = 1;
                                        break;
                                    }
                                }
                                if (i == this.b && this.b < this.c) {
                                    this.f.a(this.g.af, this.g.ag);
                                    object.a(0.0f, 0.0f);
                                    final int n8 = (this.g.h == 10) ? ((int)this.g.a(9.9f)) : this.g.h;
                                    object3 = this.a[this.b];
                                    object4 = this.f;
                                    object5 = object;
                                    n2 = this.g.o[3];
                                    n3 = this.g.n[3];
                                    n4 = this.g.m[n8];
                                    n5 = 3;
                                    break;
                                }
                                break Label_0834;
                            }
                        }
                        object3.a(object4, object5, n2, n3, n4, n5);
                        ++this.b;
                    }
                }
                for (int j = 0; j < this.g.w[this.g.t]; ++j) {
                    ++this.e;
                    for (int k = 0; k < this.b; ++k) {
                        switch (this.a[k].f) {
                            case 0:
                            case 1:
                            case 2: {
                                final Object a2 = this.a[k].a;
                                a2.a += this.a[k].b.a * this.g.s;
                                final Object a3 = this.a[k].a;
                                a3.b += this.a[k].b.b * this.g.s;
                                if ((this.e & 0x1) == 0x0) {
                                    object.a(this.a[k].b);
                                    object.c();
                                    object.a(-this.a[k].d);
                                    object.b(this.a[k].a);
                                    this.a[k].g[this.a[k].h].a(object);
                                    this.a[k].h = (this.a[k].h + 1 & this.d - 1);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    for (int l = 0; l < this.b; ++l) {
                        for (int n9 = l + 1; n9 < this.b; ++n9) {
                            object.b(this.a[l].a, this.a[n9].a);
                            final float c = object.c();
                            object.a(1.0f / ((c + 0.01f) * (c + 0.01f)) * this.a[l].c * this.a[n9].c * 0.001f);
                            final Object b = this.a[l].b;
                            b.a -= object.a / this.a[l].c * this.g.s;
                            final Object b2 = this.a[l].b;
                            b2.b -= object.b / this.a[l].c * this.g.s;
                            final Object b3 = this.a[n9].b;
                            b3.a += object.a / this.a[n9].c * this.g.s;
                            final Object b4 = this.a[n9].b;
                            b4.b += object.b / this.a[n9].c * this.g.s;
                            if (c < this.a[l].d + this.a[n9].d) {
                                Object object6;
                                int m;
                                if (this.a[l].d == this.a[n9].d) {
                                    this.a[l].i = 2;
                                    object6 = this.a[n9];
                                    m = 2;
                                }
                                else {
                                    Object array;
                                    int n10;
                                    if (this.a[l].d < this.a[n9].d) {
                                        array = this.a;
                                        n10 = l;
                                    }
                                    else {
                                        array = this.a;
                                        n10 = n9;
                                    }
                                    object6 = array[n10];
                                    m = 1;
                                }
                                object6.i = m;
                            }
                        }
                    }
                }
                this.e = this.e - this.g.w[this.g.t] + 1;
                for (int n11 = 0; n11 < this.b; ++n11) {
                    if (this.a[n11].i >= 2) {
                        if (this.a[n11].f != 0) {
                            this.a[n11].b.a(0.2f);
                            for (int n12 = (this.a[n11].f == 1) ? 3 : 6, n13 = 0; n13 < n12 && this.b != this.c; ++n13) {
                                final float a4 = this.g.a(6.283185f);
                                object.a((float)Math.cos(a4), (float)Math.sin(a4));
                                object.a(object, 0.1f);
                                object.a(object, this.a[n11].b);
                                this.a[this.b].a(this.a[n11].a, object, this.g.o[0], this.g.n[0], this.a[n11].e, 0);
                                ++this.b;
                            }
                        }
                    }
                }
                for (int n14 = 0; n14 < this.b; ++n14) {
                    if (this.a[n14].a.b() > 40.0f) {
                        this.a[n14].i = 1;
                    }
                }
                for (int n15 = 0; n15 < this.b; ++n15) {
                    if (this.a[n15].i != 0) {
                        final Object object7 = this.a[n15--];
                        final Object a5 = this.a;
                        final int b5 = this.b - 1;
                        this.b = b5;
                        object7.a(a5[b5]);
                    }
                }
            }
            
            final void c() {
                final Object object = new Object() {
                    float a;
                    float b;
                    
                    {
                        this.a = 0.0f;
                        this.b = 0.0f;
                    }
                    
                    final f a(final float a, final float b) {
                        this.a = a;
                        this.b = b;
                        return this;
                    }
                    
                    final f a(final f object) {
                        this.a = object.a;
                        this.b = object.b;
                        return this;
                    }
                    
                    final f b(final f object) {
                        this.a += object.a;
                        this.b += object.b;
                        return this;
                    }
                    
                    final f a(final f object, final f object2) {
                        this.a = object.a + object2.a;
                        this.b = object.b + object2.b;
                        return this;
                    }
                    
                    final f b(final f object, final f object2) {
                        this.a = object.a - object2.a;
                        this.b = object.b - object2.b;
                        return this;
                    }
                    
                    final f a(final float n) {
                        this.a *= n;
                        this.b *= n;
                        return this;
                    }
                    
                    final f a(final f object, final float n) {
                        this.a = object.a * n;
                        this.b = object.b * n;
                        return this;
                    }
                    
                    final f a() {
                        final float a = this.a;
                        this.a = this.b;
                        this.b = -a;
                        return this;
                    }
                    
                    final float b() {
                        return (float)Math.sqrt(this.a * this.a + this.b * this.b);
                    }
                    
                    final float c() {
                        final float b;
                        if ((b = this.b()) == 0.0f) {
                            return 0.0f;
                        }
                        this.a /= b;
                        this.b /= b;
                        return b;
                    }
                };
                final Object object2;
                (object2 = new Object() {
                    float a;
                    float b;
                    
                    {
                        this.a = 0.0f;
                        this.b = 0.0f;
                    }
                    
                    final f a(final float a, final float b) {
                        this.a = a;
                        this.b = b;
                        return this;
                    }
                    
                    final f a(final f object) {
                        this.a = object.a;
                        this.b = object.b;
                        return this;
                    }
                    
                    final f b(final f object) {
                        this.a += object.a;
                        this.b += object.b;
                        return this;
                    }
                    
                    final f a(final f object, final f object2) {
                        this.a = object.a + object2.a;
                        this.b = object.b + object2.b;
                        return this;
                    }
                    
                    final f b(final f object, final f object2) {
                        this.a = object.a - object2.a;
                        this.b = object.b - object2.b;
                        return this;
                    }
                    
                    final f a(final float n) {
                        this.a *= n;
                        this.b *= n;
                        return this;
                    }
                    
                    final f a(final f object, final float n) {
                        this.a = object.a * n;
                        this.b = object.b * n;
                        return this;
                    }
                    
                    final f a() {
                        final float a = this.a;
                        this.a = this.b;
                        this.b = -a;
                        return this;
                    }
                    
                    final float b() {
                        return (float)Math.sqrt(this.a * this.a + this.b * this.b);
                    }
                    
                    final float c() {
                        final float b;
                        if ((b = this.b()) == 0.0f) {
                            return 0.0f;
                        }
                        this.a /= b;
                        this.b /= b;
                        return b;
                    }
                }).a(this.g.af, this.g.ag);
                if (!this.g.r && this.g.U && this.g.g == 0) {
                    object.b(this.f, object2);
                    object.a(0.2f);
                    this.g.a(this.f.a + object.a, this.f.b + object.b, this.f.a, this.f.b, -65536);
                    if (this.g.i == 0) {
                        this.g.a(this.f.a, this.f.b, this.g.m[this.g.h]);
                    }
                    else {
                        this.g.a(this.f.a, this.f.b, this.g.n[this.g.i], this.g.m[this.g.h]);
                    }
                }
                if (this.g.j != 0) {
                    for (int i = 0; i < this.b; ++i) {
                        final int n = 0xFF000000 | (this.a[i].e & 0xFF0000) >> 18 << 16 | (this.a[i].e & 0xFF00) >> 10 << 8 | (this.a[i].e & 0xFF) >> 2;
                        int n2 = this.a[i].h - 1 & this.d - 1;
                        int n3 = this.a[i].h - 2 & this.d - 1;
                        for (int n4 = ((this.g.j == 2) ? this.d : (this.d / 2)) - 1, j = 0; j < n4; ++j) {
                            if (this.g.j == 1 || this.g.j == 2) {
                                this.g.a(this.a[i].g[n2].a, this.a[i].g[n2].b, this.a[i].g[n3].a, this.a[i].g[n3].b, n);
                            }
                            else if (this.g.j == 3) {
                                this.g.a(this.a[i].g[n2].a, this.a[i].g[n2].b, n);
                            }
                            n2 = (n2 - 1 & this.d - 1);
                            n3 = (n3 - 1 & this.d - 1);
                        }
                    }
                }
                for (int k = 0; k < this.b; ++k) {
                    if (this.a[k].d != 0.0f) {
                        this.g.a(this.a[k].a.a, this.a[k].a.b, this.a[k].d, this.a[k].e);
                    }
                }
                for (int l = 0; l < this.b; ++l) {
                    if (this.a[l].d == 0.0f) {
                        this.g.a(this.a[l].a.a, this.a[l].a.b, this.a[l].e);
                    }
                }
            }
            
            {
                this.g = planet;
            }
            
            static planet a(final d object) {
                return object.g;
            }
        };
        this.F = 1;
        this.G = new int[3];
        this.J = 1;
        final float[] array = { -0.16666667f, 0.5f, -0.5f, 0.16666667f, 0.5f, -1.0f, 0.5f, 0.0f, -0.5f, 0.0f, 0.5f, 0.0f, 0.16666667f, 0.6666667f, 0.16666667f, 0.0f };
        this.R = new Object() {
            private final planet o = planet;
            c a = new Object() {
                private final planet d = planet;
                int a;
                int b;
                int[] c;
                
                final void a(final String s) {
                    final Image image = this.d.getImage(this.d.getCodeBase(), s);
                    final MediaTracker mediaTracker;
                    (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                    try {
                        mediaTracker.waitForAll();
                    }
                    catch (Exception ex) {}
                    this.a = image.getWidth(null);
                    this.b = image.getHeight(null);
                    this.c = new int[this.a * this.b];
                    final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.a, this.b, this.c, 0, this.a);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (Exception ex2) {}
                    this.d.H = this.d.getDocumentBase().toString();
                }
                
                {
                    this.d = planet;
                }
            };
            int b;
            int c;
            int d;
            int e;
            int f;
            int g;
            int h;
            float i;
            int j;
            int k;
            int l;
            float m;
            int n;
            
            final void a(final String s, final int n, final int n2) {
                this.a.a(s);
                this.d = n;
                this.b = n;
                this.e = n2;
                this.c = n2;
                final int f = 255;
                this.h = f;
                this.g = f;
                this.f = f;
                this.i = 0.0f;
                final boolean j = false;
                this.l = (j ? 1 : 0);
                this.k = (j ? 1 : 0);
                this.j = (j ? 1 : 0);
                this.m = 0.0f;
                this.n = 0;
            }
            
            final void a(final int b, final int c) {
                this.b = b;
                this.c = c;
            }
            
            final void a(final int n, final int n2, final int n3, final int n4) {
                this.i = n4 / 255.0f;
                this.f = (int)(n * this.i);
                this.g = (int)(n2 * this.i);
                this.h = (int)(n3 * this.i);
                this.i = 1.0f - this.i;
            }
            
            final void b(final int n, final int n2, final int n3, final int n4) {
                this.m = n4 / 255.0f;
                this.j = (int)(n * this.m);
                this.k = (int)(n2 * this.m);
                this.l = (int)(n3 * this.m);
                this.m = 1.0f - this.m;
            }
            
            final void a(int n, final int n2, final String s) {
                for (int length = s.length(), i = 0; i < length; ++i) {
                    final int n3 = (s.charAt(i) - ' ') * this.d;
                    for (int n4 = n2 * this.o.a + n, j = 0; j < this.c; ++j, n4 += this.o.a - this.b) {
                        final int n5 = j * this.e / this.c * this.a.a + n3;
                        for (int k = 0; k < this.b; ++k, ++n4) {
                            final int n6;
                            if ((n6 = this.a.c[n5 + k * this.d / this.b]) != -8355712) {
                                int[] array;
                                int n8;
                                int n9;
                                int n10;
                                float n11;
                                float n12;
                                if (n6 == -1) {
                                    final int n7 = this.o.z[n4];
                                    array = this.o.z;
                                    n8 = n4;
                                    n9 = (0xFF000000 | this.f + (int)((n7 >> 16 & 0xFF) * this.i) << 16 | this.g + (int)((n7 >> 8 & 0xFF) * this.i) << 8);
                                    n10 = this.h;
                                    n11 = (n7 & 0xFF);
                                    n12 = this.i;
                                }
                                else {
                                    if (n6 != -16777216) {
                                        continue;
                                    }
                                    final int n13 = this.o.z[n4];
                                    array = this.o.z;
                                    n8 = n4;
                                    n9 = (0xFF000000 | this.j + (int)((n13 >> 16 & 0xFF) * this.m) << 16 | this.k + (int)((n13 >> 8 & 0xFF) * this.m) << 8);
                                    n10 = this.l;
                                    n11 = (n13 & 0xFF);
                                    n12 = this.m;
                                }
                                array[n8] = (n9 | n10 + (int)(n11 * n12));
                            }
                        }
                    }
                    n += this.b + this.n;
                }
            }
            
            final void a(final int n, final int n2, final String s, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
                this.a(n3, n4, n5, n6);
                this.b(n7, n8, n9, n10);
                this.a(n, n2, s);
            }
            
            {
                this.o = planet;
            }
        };
        this.ah = new boolean[256];
        this.ai = new boolean[256];
        final byte[] array2 = { 71, 69, 84, 32 };
        final byte[] array3 = { 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10, 72, 111, 115, 116, 58, 32 };
        final byte[] array4 = { 13, 10, 67, 111, 110, 110, 101, 99, 116, 105, 111, 110, 58, 32, 99, 108, 111, 115, 101, 13, 10, 13, 10 };
        final byte[] array5 = { 100, 97, 110, 45, 98, 97, 108, 108, 46, 106, 112 };
        this.aj = new float[1024];
        this.am = new float[513][2];
    }
}
