import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.5.30
// 

class firewk
{
    public boolean sleep;
    AudioClip a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int[] j;
    private int[] k;
    private int[] l;
    private int[] m;
    private int[] n;
    private int[] o;
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
    float A;
    float B;
    int C;
    float D;
    float E;
    int F;
    private int G;
    int H;
    int I;
    int J;
    int K;
    private Random L;
    public static boolean M;
    
    public firewk(final int e, final int f, final int g, final int n) {
        this.sleep = true;
        this.e = e;
        this.f = f;
        this.g = g;
        this.j = new int[n];
        this.k = new int[n];
        this.l = new int[n + 1];
        this.m = new int[n + 1];
        this.n = new int[n + 1];
        this.o = new int[n + 1];
    }
    
    public void init(final int b, final int c, final int d, final long n, final AudioClip a) {
        final boolean m = firewk.M;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.L = null;
        System.gc();
        this.L = new Random(n);
        this.r = (int)(this.L.nextFloat() * 128.0f) + 128;
        this.s = (int)(this.L.nextFloat() * 128.0f) + 128;
        this.t = (int)(this.L.nextFloat() * 128.0f) + 128;
        this.H = (int)(this.L.nextFloat() * 2.99) + 1;
        this.A = this.L.nextFloat() * 5.0f + 1.2f;
        this.B = this.g / this.A;
        this.C = (int)(this.d / this.A);
        this.D = this.L.nextFloat() * 5.0f + 1.2f;
        this.E = this.g / this.D;
        this.F = (int)(this.d / this.D);
        this.I = (int)(this.L.nextFloat() * 3.99) + 1;
        this.J = (int)(this.L.nextFloat() * 3.99) + 1;
        this.K = (int)(this.L.nextFloat() * 3.99) + 1;
        this.h = (int)(Math.random() * this.e / 2.0) + this.e / 4;
        this.i = (int)(Math.random() * this.f / 2.0) + this.f / 4;
        final int n2 = this.b / 2;
        final int n3 = this.b / 2;
        int i = 0;
        while (true) {
            Label_0346: {
                if (m) {
                    break Label_0346;
                }
                while (i >= this.c / 2) {
                    if (!m) {
                        while (true) {
                            Label_0483: {
                                if (m) {
                                    break Label_0483;
                                }
                                while (i >= this.c) {
                                    if (!m) {
                                        if (firewks.bN != 0) {
                                            firewk.M = !m;
                                        }
                                        return;
                                    }
                                }
                            }
                            final int n4 = (int)(Math.random() * this.b / 2.0);
                            final int n5 = (int)(Math.random() * this.b / 2.0);
                            final int n6 = n4 - n2 / 2;
                            final int n7 = n5 - n2 / 2;
                            while ((float)Math.sqrt(n6 * n6 + n7 * n7) < n2 / 2 - (int)(Math.random() * n3 / 2.0)) {
                                this.j[i] = n4 - this.b / 4;
                                this.k[i] = n5 - this.b / 32;
                                if (!m) {
                                    ++i;
                                    break;
                                }
                            }
                            continue;
                        }
                    }
                }
            }
            final int n8 = (int)(Math.random() * this.b);
            final int n9 = (int)(Math.random() * this.b);
            final int n10 = n8 - n2;
            final int n11 = n9 - n2;
            while ((float)Math.sqrt(n10 * n10 + n11 * n11) < n2 - (int)(Math.random() * n3)) {
                this.j[i] = n8 - this.b / 2;
                this.k[i] = n9 - this.b / 8;
                if (!m) {
                    ++i;
                    break;
                }
            }
            continue;
        }
    }
    
    public void start() {
        this.G = 0;
        this.sleep = false;
        if (this.a != null) {
            this.a.play();
        }
    }
    
    public final void showf(final Graphics graphics) {
        if (!this.sleep) {
            if (this.G < this.d) {
                Color color = null;
                Color color2 = null;
                if (this.I == 1) {
                    final int r = (int)(this.L.nextFloat() * 64.0f) - 32 + this.r;
                    if (r >= 0 && r < 256) {
                        this.r = r;
                    }
                    final int t = (int)(this.L.nextFloat() * 64.0f) - 32 + this.t;
                    if (t >= 0 && t < 256) {
                        this.t = t;
                    }
                    final int s = (int)(this.L.nextFloat() * 64.0f) - 32 + this.s;
                    if (s >= 0 && s < 256) {
                        this.s = s;
                    }
                }
                else if (this.I == 2) {
                    final int r2 = (int)(this.L.nextFloat() * 64.0f) - 24 + this.r;
                    if (r2 >= 0 && r2 < 256) {
                        this.r = r2;
                    }
                    final int t2 = (int)(this.L.nextFloat() * 64.0f) - 24 + this.t;
                    if (t2 >= 0 && t2 < 256) {
                        this.t = t2;
                    }
                    final int s2 = (int)(this.L.nextFloat() * 64.0f) - 24 + this.s;
                    if (s2 >= 0 && s2 < 256) {
                        this.s = s2;
                    }
                }
                else if (this.I == 3) {
                    final int r3 = (int)(this.L.nextFloat() * 96.0f) - 48 + this.r;
                    if (r3 >= 0 && r3 < 256) {
                        this.r = r3;
                    }
                    final int t3 = (int)(this.L.nextFloat() * 96.0f) - 48 + this.t;
                    if (t3 >= 0 && t3 < 256) {
                        this.t = t3;
                    }
                    final int s3 = (int)(this.L.nextFloat() * 96.0f) - 48 + this.s;
                    if (s3 >= 0 && s3 < 256) {
                        this.s = s3;
                    }
                }
                int r4;
                int t4;
                int s4;
                int n5;
                int n6;
                int n7;
                int n8;
                int n9;
                int n10;
                int n11;
                int n12;
                int n13;
                int n14;
                int n15;
                int n16;
                if (this.G > this.d / 2) {
                    final float n = this.d;
                    float n2;
                    float n3;
                    float n4;
                    if (this.G < this.d - 20) {
                        n2 = this.r / n * this.G / 3.0f;
                        n3 = this.t / n * this.G / 3.0f;
                        n4 = this.s / n * this.G / 3.0f;
                    }
                    else {
                        n2 = this.r / n * this.G;
                        n3 = this.t / n * this.G;
                        n4 = this.s / n * this.G;
                    }
                    r4 = this.r - (int)n2;
                    t4 = this.t - (int)n3;
                    s4 = this.s - (int)n4;
                    n5 = (int)(r4 * 0.9f) - 10;
                    n6 = (int)(t4 * 0.9f) - 10;
                    n7 = (int)(s4 * 0.9f) - 10;
                    n8 = (int)(r4 * 1.1f) + 5;
                    n9 = (int)(t4 * 1.1f) + 5;
                    n10 = (int)(s4 * 1.1f) + 5;
                    n11 = (int)(r4 * 1.3f) + 15;
                    n12 = (int)(t4 * 1.3f) + 15;
                    n13 = (int)(s4 * 1.3f) + 15;
                    n14 = (int)(r4 * 1.5f) + 25;
                    n15 = (int)(t4 * 1.5f) + 25;
                    n16 = (int)(s4 * 1.5f) + 25;
                }
                else {
                    r4 = this.r;
                    t4 = this.t;
                    s4 = this.s;
                    n8 = (n5 = (n11 = (n14 = r4)));
                    n9 = (n6 = (n12 = (n15 = t4)));
                    n10 = (n7 = (n13 = (n16 = s4)));
                }
                int n17 = (int)(this.L.nextFloat() * 4.0f);
                if (n17 < 0) {
                    n17 = -n17;
                }
                int n18 = n17 * 15;
                int n19 = (int)(this.L.nextFloat() * 4.0f);
                if (n19 < 0) {
                    n19 = -n19;
                }
                if (n19 < 10) {
                    n19 = 0;
                }
                final int n20 = n19 * 20;
                int n21 = (int)(this.L.nextFloat() * 4.0f);
                if (n21 < 0) {
                    n21 = -n21;
                }
                if (n21 < 10) {
                    n21 = 0;
                }
                final int n22 = n21 * 30;
                int n23 = (int)(this.L.nextFloat() * 4.0f);
                if (n23 < 0) {
                    n23 = -n23;
                }
                if (n18 < 10) {
                    n18 = 0;
                }
                final int n24 = n23 * 40;
                int n25 = (int)(this.L.nextFloat() * 4.0f);
                if (n25 < 0) {
                    n25 = -n25;
                }
                if (n18 < 10) {
                    n18 = 0;
                }
                final int n26 = n25 * 50;
                int n27 = r4 + n18;
                int n28 = t4 + n18;
                int n29 = s4 + n18;
                int n30 = n5 + n20;
                int n31 = n6 + n20;
                int n32 = n7 + n20;
                int n33 = n8 + n22;
                int n34 = n9 + n22;
                int n35 = n10 + n22;
                int n36 = n11 + n24;
                int n37 = n12 + n24;
                int n38 = n13 + n24;
                int n39 = n14 + n26;
                int n40 = n15 + n26;
                int n41 = n16 + n26;
                if (n27 > 255) {
                    n27 = 255;
                }
                if (n28 > 255) {
                    n28 = 255;
                }
                if (n29 > 255) {
                    n29 = 255;
                }
                if (n30 < 0) {
                    n30 = 0;
                }
                if (n31 < 0) {
                    n31 = 0;
                }
                if (n32 < 0) {
                    n32 = 0;
                }
                if (n30 > 255) {
                    n30 = 255;
                }
                if (n31 > 255) {
                    n31 = 255;
                }
                if (n32 > 255) {
                    n32 = 255;
                }
                if (n33 > 255) {
                    n33 = 255;
                }
                if (n34 > 255) {
                    n34 = 255;
                }
                if (n35 > 255) {
                    n35 = 255;
                }
                if (n36 > 255) {
                    n36 = 255;
                }
                if (n37 > 255) {
                    n37 = 255;
                }
                if (n38 > 255) {
                    n38 = 255;
                }
                if (n39 > 255) {
                    n39 = 255;
                }
                if (n40 > 255) {
                    n40 = 255;
                }
                if (n41 > 255) {
                    n41 = 255;
                }
                final Color color3 = new Color(n27, n28, n29);
                final Color color4 = new Color(n30, n31, n32);
                final Color color5 = new Color(n33, n34, n35);
                final Color color6 = new Color(n36, n37, n38);
                final Color color7 = new Color(n39, n40, n41);
                if (this.J == 1) {
                    color = new Color(n31, n32, n30);
                }
                else if (this.J == 2) {
                    color = new Color(n32, n30, n31);
                }
                else if (this.J == 3) {
                    color = new Color(n31, n30, n32);
                }
                else if (this.J == 4) {
                    color = color4;
                }
                if (this.K == 1) {
                    color2 = new Color(n34, n35, n33);
                }
                else if (this.K == 2) {
                    color2 = new Color(n35, n33, n34);
                }
                else if (this.K == 3) {
                    color2 = new Color(n34, n33, n35);
                }
                else if (this.K == 4) {
                    color2 = color5;
                }
                final int[] j = this.j;
                final int[] k = this.k;
                final int[] l = this.l;
                final int[] m = this.m;
                final int[] n42 = this.n;
                final int[] o = this.o;
                int n43 = 0;
                int n44 = 0;
                int n45 = 0;
                int n46 = 0;
                for (int i = 0; i < this.c; ++i) {
                    final float n47 = this.G / 100.0f;
                    this.p = (int)(j[i] * n47);
                    this.q = (int)(k[i] * n47 - this.g * n47 * n47);
                    final int n48 = this.h + this.p;
                    final int n49 = this.i - this.q;
                    if (this.G > 1) {
                        n43 = l[i];
                        n44 = m[i];
                        l[i] = n48;
                        m[i] = n49;
                        n45 = n42[i];
                        n46 = o[i];
                        n42[i] = n43;
                        o[i] = n44;
                    }
                    else if (this.G == 0) {
                        n43 = n48;
                        n44 = n49;
                        n45 = n48;
                        n46 = n49;
                        l[i] = (n42[i] = n48);
                        m[i] = (o[i] = n49);
                    }
                    else if (this.G == 1) {
                        n43 = l[i];
                        n44 = m[i];
                        final int[] array = n42;
                        final int n50 = i;
                        final int n51 = n43;
                        array[n50] = n51;
                        n45 = n51;
                        final int[] array2 = o;
                        final int n52 = i;
                        final int n53 = n44;
                        array2[n52] = n53;
                        n46 = n53;
                        l[i] = n48;
                        m[i] = n49;
                    }
                    if (i % 2 == 0) {
                        graphics.setColor(color3);
                    }
                    else {
                        graphics.setColor(color4);
                    }
                    if (i % 5 == 0) {
                        graphics.setColor(color5);
                    }
                    if (i % 8 == 0) {
                        graphics.setColor(color6);
                    }
                    if (i % 14 == 0) {
                        graphics.setColor(color7);
                    }
                    graphics.fillRect(n48, n49, 2, 2);
                    graphics.fillRect(n43, n44, 1, 1);
                    graphics.fillRect(n45, n46, 0, 0);
                    if (this.H > 1) {
                        if (this.G >= this.C) {
                            if (this.G == this.C && this.a != null) {
                                this.a.play();
                            }
                            for (int n54 = 0; n54 < 2; ++n54) {
                                final float n55 = ((this.G - this.C) * this.A + n54) / 100.0f;
                                this.p = (int)(j[i] * n55);
                                this.q = (int)(k[i] * n55 - this.B * n55 * n55);
                                final int n56 = this.h + this.p;
                                final int n57 = this.i - this.q;
                                graphics.setColor(color);
                                graphics.fillRect(n56, n57, 1, 1);
                            }
                        }
                        if (this.H > 2 && this.G >= this.F) {
                            if (this.G == this.F && this.a != null) {
                                this.a.play();
                            }
                            for (int n58 = 0; n58 < 2; ++n58) {
                                final float n59 = ((this.G - this.F) * this.D + n58) / 100.0f;
                                this.p = (int)(j[i] * n59);
                                this.q = (int)(k[i] * n59 - this.E * n59 * n59);
                                final int n60 = this.h + this.p;
                                final int n61 = this.i - this.q;
                                graphics.setColor(color2);
                                graphics.fillRect(n60, n61, 1, 1);
                            }
                        }
                    }
                }
                ++this.G;
                return;
            }
            this.sleep = true;
        }
    }
}
