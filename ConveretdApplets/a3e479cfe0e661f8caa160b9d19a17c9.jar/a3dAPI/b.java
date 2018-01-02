// 
// Decompiled by Procyon v0.5.30
// 

package a3dAPI;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.image.DirectColorModel;
import java.awt.Component;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.Canvas;

public final class b extends Canvas implements ImageProducer
{
    private float k;
    private float l;
    private float m;
    private boolean n;
    private int o;
    private float p;
    private Image q;
    private int r;
    protected int[][][] a;
    private int[] s;
    private int[] t;
    private int[] u;
    private int v;
    private int w;
    private int x;
    float b;
    float c;
    private int y;
    private int z;
    private Image A;
    private Image B;
    private Graphics C;
    private ImageConsumer D;
    private ColorModel E;
    private int[] F;
    private boolean G;
    private float H;
    private float I;
    private float J;
    private float K;
    private float L;
    private float M;
    private float N;
    private float O;
    private float P;
    private float Q;
    private float R;
    private float S;
    private float T;
    private float U;
    private float V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int aa;
    private int ab;
    private int ac;
    private float ad;
    private float ae;
    private float af;
    private float ag;
    private float ah;
    private float ai;
    private float aj;
    private float ak;
    private float al;
    private float am;
    private float an;
    private float ao;
    private int ap;
    private int aq;
    private int ar;
    private int as;
    private int at;
    private int au;
    private float av;
    private float aw;
    private float ax;
    private float ay;
    private float az;
    private float aA;
    private static float[] aB;
    private int aC;
    private float[][] aD;
    private float[][] aE;
    private float[][] aF;
    private boolean aG;
    private boolean aH;
    private int aI;
    private int aJ;
    private int[] aK;
    private float aL;
    private int aM;
    private int aN;
    private boolean aO;
    private boolean aP;
    private int aQ;
    private int[] aR;
    private int aS;
    private int aT;
    private int[] aU;
    private boolean aV;
    private boolean aW;
    private int aX;
    protected boolean d;
    protected int e;
    protected float f;
    protected float g;
    protected float h;
    protected float i;
    protected float j;
    private Component aY;
    
    public final void a(final float n, final float m, final float n2) {
        this.l = n * this.p;
        this.m = m;
    }
    
    public final void a() {
        this.r = 0;
        this.a = new int[0][][];
        System.gc();
    }
    
    private final int a(final float[][] array, final float[][] array2, final int n) {
        float[] array3;
        float n2;
        boolean b = (n2 = (array3 = array[n - 1])[3]) >= 0.0f;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            final float[] array4;
            final float n4;
            final boolean b2 = (n4 = (array4 = array[i])[3]) >= 0.0f;
            if (b ^ b2) {
                final float n5 = n2 / (n2 - n4);
                final float[] array5;
                (array5 = this.aD[this.aC++])[3] = 0.0f;
                array5[4] = array3[4] + (array4[4] - array3[4]) * n5;
                array5[5] = array3[5] + (array4[5] - array3[5]) * n5;
                if (this.aG) {
                    array5[11] = array3[11] + (array4[11] - array3[11]) * n5;
                    array5[12] = array3[12] + (array4[12] - array3[12]) * n5;
                }
                if (this.aH) {
                    array5[6] = array3[6] + (array4[6] - array3[6]) * n5;
                    array5[7] = array3[7] + (array4[7] - array3[7]) * n5;
                    array5[8] = array3[8] + (array4[8] - array3[8]) * n5;
                }
                array2[n3++] = array5;
            }
            if (b2) {
                array2[n3++] = array4;
            }
            b = b2;
            array3 = array4;
            n2 = n4;
        }
        return n3;
    }
    
    public final void a(final int n, final int[] array, final int n2, final int n3) {
        final int max;
        int n6;
        int n5;
        final int n4 = ((max = Math.max(n2, n3)) > 256) ? (n5 = (n6 = 512)) : ((max > 128) ? (n5 = (n6 = 256)) : ((max > 64) ? (n5 = (n6 = 128)) : ((max > 32) ? (n5 = (n6 = 64)) : (n5 = (n6 = 32)))));
        final int n7 = n5;
        final int n8 = n4;
        final int[] array2 = new int[n6 * n7];
        a(array, n2, n3, array2, n8, n7);
        int n9 = -1;
        int n10 = n8 * n7;
        while (--n10 != 0) {
            n9 &= array2[n10];
        }
        int[] array3;
        int n11;
        boolean b;
        if ((n9 & 0xFF000000) != 0xFF000000) {
            array3 = this.a[n][0];
            n11 = 0;
            b = true;
        }
        else {
            array3 = this.a[n][0];
            n11 = 0;
            b = false;
        }
        array3[n11] = (b ? 1 : 0);
        this.a[n][1] = array2;
        int n12 = 0;
        int n13 = 0;
        final int n14 = n8 - 1;
        while (true) {
            int n15 = 0;
            switch (n8) {
                case 512: {
                    n12 = 33488896;
                    n15 = 7;
                    break;
                }
                case 256: {
                    n12 = 16711680;
                    n15 = 8;
                    break;
                }
                case 128: {
                    n12 = 8323072;
                    n15 = 9;
                    break;
                }
                case 64: {
                    n12 = 4128768;
                    n15 = 10;
                    break;
                }
                case 32: {
                    n12 = 2031616;
                    n15 = 11;
                    break;
                }
                default: {
                    this.a[n][0][1] = n14;
                    this.a[n][0][2] = n12;
                    this.a[n][0][3] = n13;
                    return;
                }
            }
            n13 = n15;
            continue;
        }
    }
    
    private static void a(final int[] array, final int n, final int n2, final int[] array2, int n3, int n4) {
        if (n == n3 && n2 == n4) {
            System.arraycopy(array, 0, array2, 0, n * n2);
            return;
        }
        final int n5 = (n << 16) / n3;
        final int n6 = (n2 << 16) / n4;
        int n7 = 0;
        int n8 = 0;
        while (--n4 >= 0) {
            int n9 = 0;
            final int n10 = (n7 >> 16) * n;
            n7 += n6;
            while (--n3 >= 0) {
                array2[n8++] = array[(n9 >> 16) + n10];
                n9 += n5;
            }
        }
    }
    
    private final void a(final float[] array, final float[] array2) {
        final int n = (int)(array[3] * 16.0f);
        final int n2 = (int)(array[4] * 16.0f);
        final int n3 = (int)(array2[3] * 16.0f);
        final int n4 = (int)(array2[4] * 16.0f);
        final int n5 = n2 + 15 >> 4;
        this.ab = n5 * this.v;
        this.ac = (n4 + 15 >> 4) - n5;
        if (this.ac != 0) {
            final int n6;
            final int z = (n6 = n4 - n2) << 4;
            final int n9;
            final int n8;
            final int n7 = (n8 = (n9 = n3 - n) << 4) * n5 - n9 * n2 + n6 * n - 1 + z;
            this.W = n7 / z;
            this.aa = n7 - this.W * z;
            if (n7 < 0 && this.aa != 0) {
                --this.W;
                this.aa += z;
            }
            this.X = n8 / z;
            this.Y = n8 - this.X * z;
            if (n8 < 0 && this.Y != 0) {
                --this.X;
                this.Y += z;
            }
            this.Z = z;
            final float n10 = ((n5 << 4) - n2) * 0.0625f;
            final float n11 = ((this.W << 4) - n) * 0.0625f;
            this.ad = array[5] + n10 * this.I + n11 * this.H;
            this.ae = this.X * this.H + this.I;
            if (this.aG) {
                this.af = array[11] + n10 * this.K + n11 * this.J;
                this.ag = this.X * this.J + this.K;
                this.ah = array[12] + n10 * this.M + n11 * this.L;
                this.ai = this.X * this.L + this.M;
            }
            if (this.aH) {
                this.aj = array[6] + n10 * this.O + n11 * this.N;
                this.ak = this.X * this.N + this.O;
                this.al = array[7] + n10 * this.Q + n11 * this.P;
                this.am = this.X * this.P + this.Q;
                this.an = array[8] + n10 * this.S + n11 * this.R;
                this.ao = this.X * this.R + this.S;
            }
        }
    }
    
    private final void a(final int[] array, final int[] array2, final int[] array3) {
        final int n;
        if ((n = this.ap - this.W) <= 0) {
            return;
        }
        int i = this.ab + this.W;
        float av = this.ad + this.T;
        float n2 = this.af + this.U;
        float n3 = this.ah + this.V;
        float n4 = 65536.0f / this.ad;
        float n5 = this.af * n4;
        float n6 = this.ah * n4;
        int n7 = n >> 4;
        final int n8 = n & 0xF;
        final int an = this.aN;
        final int am = this.aM;
        final boolean ao = this.aO;
        boolean b = true;
        do {
            int n9 = (int)n4;
            int n10 = (int)n5 & 0x1FFFFFF;
            int n11 = (int)n6 & 0x1FFFFFF;
            int n15;
            int n16;
            int n17;
            int n18;
            if (n7-- > 0) {
                final float n12 = 65536.0f / av;
                final float n13 = n2 * n12;
                final float n14 = n3 * n12;
                n15 = (int)(n12 - n4) >> 4;
                n16 = (int)(n13 - n5) >> 4;
                n17 = (int)(n14 - n6) >> 4;
                n4 = n12;
                n5 = n13;
                n6 = n14;
                av += this.T;
                n2 += this.U;
                n3 += this.V;
                n18 = i + 16;
            }
            else {
                if (n8 == 0) {
                    return;
                }
                av = this.av;
                final float n20;
                final float n19 = (n20 = 65536.0f / av) * this.ax;
                final float n21 = n20 * this.az;
                final float n22 = a3dAPI.b.aB[n8];
                n15 = (int)((n20 - n4) * n22);
                n16 = (int)((n19 - n5) * n22);
                n17 = (int)((n21 - n6) * n22);
                n18 = i + n8;
                b = false;
            }
            if (ao) {
                while (i < n18) {
                    if (n9 < array2[i]) {
                        array[i] = array3[(n11 & an) >> am | (n10 & an) >> 16];
                        array2[i] = n9;
                    }
                    ++i;
                    n9 += n15;
                    n10 += n16;
                    n11 += n17;
                }
            }
            else {
                while (i < n18) {
                    final int n23;
                    if (n9 < array2[i] && ((n23 = array3[(n11 & an) >> am | (n10 & an) >> 16]) & 0xFF000000) != 0x0) {
                        array[i] = n23;
                        array2[i] = n9;
                    }
                    ++i;
                    n9 += n15;
                    n10 += n16;
                    n11 += n17;
                }
            }
        } while (b);
    }
    
    public final void a(final int o) {
        this.o = o;
        b b;
        float p;
        if (this.o == 1 || this.o == 2) {
            b = this;
            p = 0.5f;
        }
        else if (this.o == 3) {
            b = this;
            p = 2.0f;
        }
        else {
            b = this;
            p = 1.0f;
        }
        b.p = p;
        this.y = -1;
        this.z = -1;
        this.aU = null;
    }
    
    private void e() {
        switch (this.aX) {
            case 1: {
                System.arraycopy(this.s, 0, this.t, 0, this.v);
                System.arraycopy(this.s, this.x - this.v, this.t, this.x - this.v, this.v);
                int v = this.v;
                final int[] t = this.t;
                final int[] s = this.s;
                for (int i = 1; i < this.w - 1; ++i) {
                    for (int j = v; j < v + this.v; ++j) {
                        t[j] = ((s[j + 1] & 0xF8F8F8) >>> 3) + ((s[j - 1] & 0xF8F8F8) >>> 3) + ((s[j + this.v] & 0xF8F8F8) >>> 3) + ((s[j - this.v] & 0xF8F8F8) >>> 3) + ((s[j] & 0xFEFEFE) >>> 1);
                    }
                    v += this.v;
                }
            }
            case 2: {
                final int[] t2 = this.t;
                final int[] s2 = this.s;
                for (int k = 0; k < this.x; ++k) {
                    t2[k] = ((s2[k] & 0xFEFEFE) >> 1) + ((t2[k] & 0xFEFEFE) >> 1);
                }
                break;
            }
        }
    }
    
    private final void b(final int[] array, final int[] array2, final int[] array3) {
        final int n;
        if ((n = this.ap - this.W) <= 0) {
            return;
        }
        int i = this.ab + this.W;
        float av = this.ad + this.T;
        float n2 = this.af + this.U;
        float n3 = this.ah + this.V;
        float n4 = 65536.0f / this.ad;
        float n5 = this.af * n4;
        float n6 = this.ah * n4;
        int n7 = (int)(this.aj * 1.671168E7f);
        final int n8 = (int)(this.N * 1.671168E7f);
        int n9 = (int)(this.al * 1.671168E7f);
        final int n10 = (int)(this.P * 1.671168E7f);
        int n11 = (int)(this.an * 1.671168E7f);
        final int n12 = (int)(this.R * 1.671168E7f);
        if (n7 < 0) {
            n7 = 0;
        }
        if (n9 < 0) {
            n9 = 0;
        }
        if (n11 < 0) {
            n11 = 0;
        }
        int n13 = n >> 4;
        final int n14 = n & 0xF;
        final int an = this.aN;
        final int am = this.aM;
        final boolean ao = this.aO;
        final int n16;
        final int n15 = (n16 = (an >> 16) + 1) + 1;
        final int n17 = (1 << (16 - am << 1)) - 1;
        boolean b = true;
        do {
            int n18 = (int)n4;
            int n19 = (int)n5 & 0x1FFFFFF;
            int n20 = (int)n6 & 0x1FFFFFF;
            int n24;
            int n25;
            int n26;
            int n27;
            if (n13-- > 0) {
                final float n21 = 65536.0f / av;
                final float n22 = n2 * n21;
                final float n23 = n3 * n21;
                n24 = (int)(n21 - n4) >> 4;
                n25 = (int)(n22 - n5) >> 4;
                n26 = (int)(n23 - n6) >> 4;
                n4 = n21;
                n5 = n22;
                n6 = n23;
                av += this.T;
                n2 += this.U;
                n3 += this.V;
                n27 = i + 16;
            }
            else {
                if (n14 == 0) {
                    return;
                }
                av = this.av;
                final float n29;
                final float n28 = (n29 = 65536.0f / av) * this.ax;
                final float n30 = n29 * this.az;
                final float n31 = a3dAPI.b.aB[n14];
                n24 = (int)((n29 - n4) * n31);
                n25 = (int)((n28 - n5) * n31);
                n26 = (int)((n30 - n6) * n31);
                n27 = i + n14;
                b = false;
            }
            if (ao) {
                while (i < n27) {
                    if (n18 < array2[i]) {
                        final int n32 = (n20 & an) >> am | (n19 & an) >> 16;
                        final int n33 = n19 >> 8 & 0xFF;
                        final int n34 = n20 >> 8 & 0xFF;
                        final int n36;
                        final int n35 = (n36 = array3[n32]) & 0xFF00;
                        final int n37 = n36 & 0xFF00FF;
                        final int n38 = array3[n32 + n16 & n17];
                        final int n39 = n37 + (n34 * ((n38 & 0xFF00FF) - n37) >>> 8) & 0xFF00FF;
                        final int n40 = n35 + (n34 * ((n38 & 0xFF00) - n35) >>> 8) & 0xFF00;
                        final int n42;
                        final int n41 = (n42 = array3[n32 + 1 & n17]) & 0xFF00;
                        final int n43 = n42 & 0xFF00FF;
                        final int n44 = array3[n32 + n15 & n17];
                        final int n46;
                        int n45 = ((n46 = ((n39 + (n33 * ((n43 + (n34 * ((n44 & 0xFF00FF) - n43) >> 8) & 0xFF00FF) - n39) >> 8) & 0xFF00FF) | (n40 + (n33 * ((n41 + (n34 * ((n44 & 0xFF00) - n41) >> 8) & 0xFF00) - n40) >> 8) & 0xFF00))) & 0xFF0000) * (n7 >>> 16) >>> 8 & 0xFF0000;
                        final int n47 = (n46 & 0xFF00) * (n9 >>> 16) >>> 8 & 0xFF00;
                        final int n48 = (n46 & 0xFF) * n11 >>> 24;
                        if (n45 > 16711680) {
                            n45 = 16711680;
                        }
                        if (n47 > 65280) {
                            n45 = 65280;
                        }
                        if (n48 > 255) {
                            n45 = 255;
                        }
                        array[i] = (n45 | n47 | n48);
                        array2[i] = n18;
                    }
                    ++i;
                    n18 += n24;
                    n19 += n25;
                    n20 += n26;
                    n7 += n8;
                    n9 += n10;
                    n11 += n12;
                }
            }
            else {
                while (i < n27) {
                    final int n49;
                    if (n18 < array2[i] && ((n49 = array3[(n20 & an) >> am | (n19 & an) >> 16]) & 0xFF000000) != 0x0) {
                        final int n50 = (n20 & an) >> am | (n19 & an) >> 16;
                        final int n51 = n19 >> 8 & 0xFF;
                        final int n52 = n20 >> 8 & 0xFF;
                        final int n54;
                        final int n53 = (n54 = n49) & 0xFF00;
                        final int n55 = n54 & 0xFF00FF;
                        final int n56 = array3[n50 + n16 & n17];
                        final int n57 = n55 + (n52 * ((n56 & 0xFF00FF) - n55) >>> 8) & 0xFF00FF;
                        final int n58 = n53 + (n52 * ((n56 & 0xFF00) - n53) >>> 8) & 0xFF00;
                        final int n60;
                        final int n59 = (n60 = array3[n50 + 1 & n17]) & 0xFF00;
                        final int n61 = n60 & 0xFF00FF;
                        final int n62 = array3[n50 + n15 & n17];
                        final int n64;
                        int n63 = ((n64 = ((n57 + (n51 * ((n61 + (n52 * ((n62 & 0xFF00FF) - n61) >> 8) & 0xFF00FF) - n57) >> 8) & 0xFF00FF) | (n58 + (n51 * ((n59 + (n52 * ((n62 & 0xFF00) - n59) >> 8) & 0xFF00) - n58) >> 8) & 0xFF00))) & 0xFF0000) * (n7 >>> 16) >>> 8 & 0xFF0000;
                        final int n65 = (n64 & 0xFF00) * (n9 >>> 16) >>> 8 & 0xFF00;
                        final int n66 = (n64 & 0xFF) * n11 >>> 24;
                        if (n63 > 16711680) {
                            n63 = 16711680;
                        }
                        if (n65 > 65280) {
                            n63 = 65280;
                        }
                        if (n66 > 255) {
                            n63 = 255;
                        }
                        array[i] = (n63 | n65 | n66);
                        array2[i] = n18;
                    }
                    ++i;
                    n18 += n24;
                    n19 += n25;
                    n20 += n26;
                    n7 += n8;
                    n9 += n10;
                    n11 += n12;
                }
            }
        } while (b);
    }
    
    public final void b() {
        final Dimension size;
        final int width = (size = this.getSize()).width;
        final int height = size.height;
        if (width > 0 && height > 0 && (width != this.y || height != this.z)) {
            this.v = size.width;
            this.w = size.height;
            if (this.o != 0) {
                this.v *= (int)this.p;
                this.w *= (int)this.p;
            }
            this.y = size.width;
            this.z = size.height;
            this.b = this.v >> 1;
            this.c = this.w >> 1;
            this.x = this.v * this.w;
            final int[] array = new int[this.x];
            this.s = array;
            this.t = array;
            this.u = new int[this.x];
            if (this.o == 1 || this.o == 3) {
                this.F = new int[this.y * this.z];
            }
            this.b(this.aX);
            this.E = new DirectColorModel(24, 16711680, 65280, 255);
            this.A = this.createImage(this);
            this.B = this.createImage(width, height);
            if (this.B != null) {
                this.C = this.B.getGraphics();
                this.prepareImage(this.A, null);
            }
            System.gc();
        }
        if (this.aR != null && this.aU == null) {
            final int[] au = new int[this.v * this.w];
            a(this.aR, this.aS, this.aT, au, this.v, this.w);
            this.aU = au;
        }
        final int[] s = this.s;
        if (this.aU == null) {
            final int aq = this.aQ;
            int x = this.x;
            while (--x >= 0) {
                s[x] = aq;
            }
        }
        else {
            System.arraycopy(this.aU, 0, s, 0, s.length);
        }
        final int[] u = this.u;
        int x2 = this.x;
        while (--x2 >= 0) {
            u[x2] = Integer.MAX_VALUE;
        }
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public final void b(final float n, final float n2, final float n3) {
        this.aQ = ((int)(n * 255.0f) << 16) + ((int)(n2 * 255.0f) << 8) + (int)(n3 * 255.0f);
        this.aR = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private final void c(final int[] array, final int[] array2, final int[] array3) {
        final int n;
        if ((n = this.ap - this.W) <= 0) {
            return;
        }
        int i = this.ab + this.W;
        float av = this.ad + this.T;
        float n2 = this.af + this.U;
        float n3 = this.ah + this.V;
        float n4 = 65536.0f / this.ad;
        float n5 = this.af * n4;
        float n6 = this.ah * n4;
        int n7 = n >> 4;
        final int n8 = n & 0xF;
        final int an = this.aN;
        final int am = this.aM;
        final boolean ao = this.aO;
        final int n10;
        final int n9 = (n10 = (an >> 16) + 1) + 1;
        final int n11 = (1 << (16 - am << 1)) - 1;
        boolean b = true;
        do {
            int n12 = (int)n4;
            int n13 = (int)n5 & 0x1FFFFFF;
            int n14 = (int)n6 & 0x1FFFFFF;
            int n18;
            int n19;
            int n20;
            int n21;
            if (n7-- > 0) {
                final float n15 = 65536.0f / av;
                final float n16 = n2 * n15;
                final float n17 = n3 * n15;
                n18 = (int)(n15 - n4) >> 4;
                n19 = (int)(n16 - n5) >> 4;
                n20 = (int)(n17 - n6) >> 4;
                n4 = n15;
                n5 = n16;
                n6 = n17;
                av += this.T;
                n2 += this.U;
                n3 += this.V;
                n21 = i + 16;
            }
            else {
                if (n8 == 0) {
                    return;
                }
                av = this.av;
                final float n23;
                final float n22 = (n23 = 65536.0f / av) * this.ax;
                final float n24 = n23 * this.az;
                final float n25 = a3dAPI.b.aB[n8];
                n18 = (int)((n23 - n4) * n25);
                n19 = (int)((n22 - n5) * n25);
                n20 = (int)((n24 - n6) * n25);
                n21 = i + n8;
                b = false;
            }
            if (ao) {
                while (i < n21) {
                    if (n12 < array2[i]) {
                        final int n26 = (n14 & an) >> am | (n13 & an) >> 16;
                        final int n27 = n13 >> 8 & 0xFF;
                        final int n28 = n14 >> 8 & 0xFF;
                        final int n30;
                        final int n29 = (n30 = array3[n26]) & 0xFF00;
                        final int n31 = n30 & 0xFF00FF;
                        final int n32 = array3[n26 + n10 & n11];
                        final int n33 = n31 + (n28 * ((n32 & 0xFF00FF) - n31) >>> 8) & 0xFF00FF;
                        final int n34 = n29 + (n28 * ((n32 & 0xFF00) - n29) >>> 8) & 0xFF00;
                        final int n36;
                        final int n35 = (n36 = array3[n26 + 1 & n11]) & 0xFF00;
                        final int n37 = n36 & 0xFF00FF;
                        final int n38 = array3[n26 + n9 & n11];
                        array[i] = ((n33 + (n27 * ((n37 + (n28 * ((n38 & 0xFF00FF) - n37) >> 8) & 0xFF00FF) - n33) >> 8) & 0xFF00FF) | (n34 + (n27 * ((n35 + (n28 * ((n38 & 0xFF00) - n35) >> 8) & 0xFF00) - n34) >> 8) & 0xFF00));
                        array2[i] = n12;
                    }
                    ++i;
                    n12 += n18;
                    n13 += n19;
                    n14 += n20;
                }
            }
            else {
                while (i < n21) {
                    if (n12 < array2[i] && (array3[(n14 & an) >> am | (n13 & an) >> 16] & 0xFF000000) != 0x0) {
                        final int n39 = (n14 & an) >> am | (n13 & an) >> 16;
                        final int n40 = n13 >> 8 & 0xFF;
                        final int n41 = n14 >> 8 & 0xFF;
                        final int n43;
                        final int n42 = (n43 = array3[n39]) & 0xFF00;
                        final int n44 = n43 & 0xFF00FF;
                        final int n45 = array3[n39 + n10 & n11];
                        final int n46 = n44 + (n41 * ((n45 & 0xFF00FF) - n44) >>> 8) & 0xFF00FF;
                        final int n47 = n42 + (n41 * ((n45 & 0xFF00) - n42) >>> 8) & 0xFF00;
                        final int n49;
                        final int n48 = (n49 = array3[n39 + 1 & n11]) & 0xFF00;
                        final int n50 = n49 & 0xFF00FF;
                        final int n51 = array3[n39 + n9 & n11];
                        array[i] = ((n46 + (n40 * ((n50 + (n41 * ((n51 & 0xFF00FF) - n50) >> 8) & 0xFF00FF) - n46) >> 8) & 0xFF00FF) | (n47 + (n40 * ((n48 + (n41 * ((n51 & 0xFF00) - n48) >> 8) & 0xFF00) - n47) >> 8) & 0xFF00));
                        array2[i] = n12;
                    }
                    ++i;
                    n12 += n18;
                    n13 += n19;
                    n14 += n20;
                }
            }
        } while (b);
    }
    
    private final int b(final float[][] array, final float[][] array2, final int n) {
        float[] array3;
        float n2;
        boolean b = (n2 = (array3 = array[n - 1])[4]) >= 0.0f;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            final float[] array4;
            final float n4;
            final boolean b2 = (n4 = (array4 = array[i])[4]) >= 0.0f;
            if (b ^ b2) {
                final float n5 = n2 / (n2 - n4);
                final float[] array5;
                (array5 = this.aD[this.aC++])[4] = 0.0f;
                array5[3] = array3[3] + (array4[3] - array3[3]) * n5;
                array5[5] = array3[5] + (array4[5] - array3[5]) * n5;
                if (this.aG) {
                    array5[11] = array3[11] + (array4[11] - array3[11]) * n5;
                    array5[12] = array3[12] + (array4[12] - array3[12]) * n5;
                }
                if (this.aH) {
                    array5[6] = array3[6] + (array4[6] - array3[6]) * n5;
                    array5[7] = array3[7] + (array4[7] - array3[7]) * n5;
                    array5[8] = array3[8] + (array4[8] - array3[8]) * n5;
                }
                array2[n3++] = array5;
            }
            if (b2) {
                array2[n3++] = array4;
            }
            b = b2;
            array3 = array4;
            n2 = n4;
        }
        return n3;
    }
    
    private final void d(final int[] array, final int[] array2, final int[] array3) {
        final int n;
        if ((n = this.ap - this.W) <= 0) {
            return;
        }
        int i = this.ab + this.W;
        float av = this.ad + this.T;
        float n2 = this.af + this.U;
        float n3 = this.ah + this.V;
        float n4 = 65536.0f / this.ad;
        float n5 = this.af * n4;
        float n6 = this.ah * n4;
        int n7 = (int)(this.aj * 1.671168E7f);
        final int n8 = (int)(this.N * 1.671168E7f);
        int n9 = (int)(this.al * 1.671168E7f);
        final int n10 = (int)(this.P * 1.671168E7f);
        int n11 = (int)(this.an * 1.671168E7f);
        final int n12 = (int)(this.R * 1.671168E7f);
        if (n7 < 0) {
            n7 = 0;
        }
        if (n9 < 0) {
            n9 = 0;
        }
        if (n11 < 0) {
            n11 = 0;
        }
        int n13 = n >> 4;
        final int n14 = n & 0xF;
        final int an = this.aN;
        final int am = this.aM;
        final boolean ao = this.aO;
        final int n16;
        final int n15 = (n16 = (an >> 16) + 1) + 1;
        final int n17 = (1 << (16 - am << 1)) - 1;
        boolean b = true;
        do {
            int n18 = (int)n4;
            int n19 = (int)n5 & 0x1FFFFFF;
            int n20 = (int)n6 & 0x1FFFFFF;
            int n24;
            int n25;
            int n26;
            int n27;
            if (n13-- > 0) {
                final float n21 = 65536.0f / av;
                final float n22 = n2 * n21;
                final float n23 = n3 * n21;
                n24 = (int)(n21 - n4) >> 4;
                n25 = (int)(n22 - n5) >> 4;
                n26 = (int)(n23 - n6) >> 4;
                n4 = n21;
                n5 = n22;
                n6 = n23;
                av += this.T;
                n2 += this.U;
                n3 += this.V;
                n27 = i + 16;
            }
            else {
                if (n14 == 0) {
                    return;
                }
                av = this.av;
                final float n29;
                final float n28 = (n29 = 65536.0f / av) * this.ax;
                final float n30 = n29 * this.az;
                final float n31 = a3dAPI.b.aB[n14];
                n24 = (int)((n29 - n4) * n31);
                n25 = (int)((n28 - n5) * n31);
                n26 = (int)((n30 - n6) * n31);
                n27 = i + n14;
                b = false;
            }
            if (ao) {
                while (i < n27) {
                    if (n18 < array2[i]) {
                        final int n32 = (n20 & an) >> am | (n19 & an) >> 16;
                        final int n33 = n19 >> 8 & 0xFF;
                        final int n34 = n20 >> 8 & 0xFF;
                        final int n36;
                        final int n35 = (n36 = array3[n32]) & 0xFF00;
                        final int n37 = n36 & 0xFF00FF;
                        final int n38 = array3[n32 + n16 & n17];
                        final int n39 = n37 + (n34 * ((n38 & 0xFF00FF) - n37) >>> 8) & 0xFF00FF;
                        final int n40 = n35 + (n34 * ((n38 & 0xFF00) - n35) >>> 8) & 0xFF00;
                        final int n42;
                        final int n41 = (n42 = array3[n32 + 1 & n17]) & 0xFF00;
                        final int n43 = n42 & 0xFF00FF;
                        final int n44 = array3[n32 + n15 & n17];
                        final int n46;
                        final int n45 = ((n46 = ((n39 + (n33 * ((n43 + (n34 * ((n44 & 0xFF00FF) - n43) >> 8) & 0xFF00FF) - n39) >> 8) & 0xFF00FF) | (n40 + (n33 * ((n41 + (n34 * ((n44 & 0xFF00) - n41) >> 8) & 0xFF00) - n40) >> 8) & 0xFF00))) & 0xFF0000) * (n7 >>> 16) >>> 8 & 0xFF0000;
                        final int n47 = (n46 & 0xFF00) * (n9 >>> 16) >>> 8 & 0xFF00;
                        final int n48 = (n46 & 0xFF) * n11 >>> 24;
                        final int n49 = array[i];
                        int n50 = (n45 >> 8) * this.aJ + ((n49 & 0xFF0000) >> 8) * this.aI & 0xFFFF0000;
                        final int n51 = n47 * this.aJ + (n49 & 0xFF00) * this.aI >> 8 & 0xFFFF00;
                        final int n52 = n48 * this.aJ + (n49 & 0xFF) * this.aI >> 8;
                        if (n50 > 16711680) {
                            n50 = 16711680;
                        }
                        if (n51 > 65280) {
                            n50 = 65280;
                        }
                        if (n52 > 255) {
                            n50 = 255;
                        }
                        array[i] = (n50 | n51 | n52);
                    }
                    ++i;
                    n18 += n24;
                    n19 += n25;
                    n20 += n26;
                    n7 += n8;
                    n9 += n10;
                    n11 += n12;
                }
            }
            else {
                while (i < n27) {
                    final int n53;
                    if (n18 < array2[i] && ((n53 = array3[(n20 & an) >> am | (n19 & an) >> 16]) & 0xFF000000) != 0x0) {
                        final int n54 = (n20 & an) >> am | (n19 & an) >> 16;
                        final int n55 = n19 >> 8 & 0xFF;
                        final int n56 = n20 >> 8 & 0xFF;
                        final int n58;
                        final int n57 = (n58 = n53) & 0xFF00;
                        final int n59 = n58 & 0xFF00FF;
                        final int n60 = array3[n54 + n16 & n17];
                        final int n61 = n59 + (n56 * ((n60 & 0xFF00FF) - n59) >>> 8) & 0xFF00FF;
                        final int n62 = n57 + (n56 * ((n60 & 0xFF00) - n57) >>> 8) & 0xFF00;
                        final int n64;
                        final int n63 = (n64 = array3[n54 + 1 & n17]) & 0xFF00;
                        final int n65 = n64 & 0xFF00FF;
                        final int n66 = array3[n54 + n15 & n17];
                        final int n68;
                        final int n67 = ((n68 = ((n61 + (n55 * ((n65 + (n56 * ((n66 & 0xFF00FF) - n65) >> 8) & 0xFF00FF) - n61) >> 8) & 0xFF00FF) | (n62 + (n55 * ((n63 + (n56 * ((n66 & 0xFF00) - n63) >> 8) & 0xFF00) - n62) >> 8) & 0xFF00))) & 0xFF0000) * (n7 >>> 16) >>> 8 & 0xFF0000;
                        final int n69 = (n68 & 0xFF00) * (n9 >>> 16) >>> 8 & 0xFF00;
                        final int n70 = (n68 & 0xFF) * n11 >>> 24;
                        final int n71 = array[i];
                        int n72 = (n67 >> 8) * this.aJ + ((n71 & 0xFF0000) >> 8) * this.aI & 0xFFFF0000;
                        final int n73 = n69 * this.aJ + (n71 & 0xFF00) * this.aI >> 8 & 0xFFFF00;
                        final int n74 = n70 * this.aJ + (n71 & 0xFF) * this.aI >> 8;
                        if (n72 > 16711680) {
                            n72 = 16711680;
                        }
                        if (n73 > 65280) {
                            n72 = 65280;
                        }
                        if (n74 > 255) {
                            n72 = 255;
                        }
                        array[i] = (n72 | n73 | n74);
                    }
                    ++i;
                    n18 += n24;
                    n19 += n25;
                    n20 += n26;
                    n7 += n8;
                    n9 += n10;
                    n11 += n12;
                }
            }
        } while (b);
    }
    
    public final void addConsumer(final ImageConsumer imageConsumer) {
    }
    
    private int c(final float[][] array, final float[][] array2, final int n) {
        float[] array3 = array[n - 1];
        float n2;
        boolean b = (n2 = this.w - array3[4]) >= 0.0f;
        int n3 = 0;
        for (final float[] array4 : array) {
            final float n4;
            final boolean b2 = (n4 = this.w - array4[4]) >= 0.0f;
            if (b ^ b2) {
                final float n5 = n2 / (n2 - n4);
                final float[] array5;
                (array5 = this.aD[this.aC++])[4] = this.w;
                array5[3] = array3[3] + (array4[3] - array3[3]) * n5;
                array5[5] = array3[5] + (array4[5] - array3[5]) * n5;
                if (this.aG) {
                    array5[11] = array3[11] + (array4[11] - array3[11]) * n5;
                    array5[12] = array3[12] + (array4[12] - array3[12]) * n5;
                }
                if (this.aH) {
                    array5[6] = array3[6] + (array4[6] - array3[6]) * n5;
                    array5[7] = array3[7] + (array4[7] - array3[7]) * n5;
                    array5[8] = array3[8] + (array4[8] - array3[8]) * n5;
                }
                array2[n3++] = array5;
            }
            if (b2) {
                array2[n3++] = array4;
            }
            b = b2;
            array3 = array4;
            n2 = n4;
        }
        return n3;
    }
    
    private final void f() {
        this.W += this.X;
        this.ab += this.v;
        --this.ac;
        this.ad += this.ae;
        this.ap += this.aq;
        --this.au;
        this.av += this.aw;
        if (this.aG) {
            this.af += this.ag;
            this.ah += this.ai;
            this.ax += this.ay;
            this.az += this.aA;
        }
        if (this.aH) {
            this.aj += this.ak;
            this.al += this.am;
            this.an += this.ao;
        }
        this.aa += this.Y;
        if (this.aa >= this.Z) {
            ++this.W;
            this.aa -= this.Z;
            this.ad += this.H;
            if (this.aG) {
                this.af += this.J;
                this.ah += this.L;
            }
            if (this.aH) {
                this.aj += this.N;
                this.al += this.P;
                this.an += this.R;
            }
        }
        this.at += this.ar;
        if (this.at >= this.as) {
            ++this.ap;
            this.at -= this.as;
            this.av += this.H;
            if (this.aG) {
                this.ax += this.J;
                this.az += this.L;
            }
        }
    }
    
    private final void e(final int[] array, final int[] array2, final int[] array3) {
        final int n;
        if ((n = this.ap - this.W) <= 0) {
            return;
        }
        int i = this.ab + this.W;
        float av = this.ad + this.T;
        float n2 = this.af + this.U;
        float n3 = this.ah + this.V;
        float n4 = 65536.0f / this.ad;
        float n5 = this.af * n4;
        float n6 = this.ah * n4;
        int n7 = (int)(this.aj * 1.671168E7f);
        final int n8 = (int)(this.N * 1.671168E7f);
        int n9 = (int)(this.al * 1.671168E7f);
        final int n10 = (int)(this.P * 1.671168E7f);
        int n11 = (int)(this.an * 1.671168E7f);
        final int n12 = (int)(this.R * 1.671168E7f);
        if (n7 < 0) {
            n7 = 0;
        }
        if (n9 < 0) {
            n9 = 0;
        }
        if (n11 < 0) {
            n11 = 0;
        }
        int n13 = n >> 4;
        final int n14 = n & 0xF;
        final int an = this.aN;
        final int am = this.aM;
        final boolean ao = this.aO;
        boolean b = true;
        do {
            int n15 = (int)n4;
            int n16 = (int)n5 & 0x1FFFFFF;
            int n17 = (int)n6 & 0x1FFFFFF;
            int n21;
            int n22;
            int n23;
            int n24;
            if (n13-- > 0) {
                final float n18 = 65536.0f / av;
                final float n19 = n2 * n18;
                final float n20 = n3 * n18;
                n21 = (int)(n18 - n4) >> 4;
                n22 = (int)(n19 - n5) >> 4;
                n23 = (int)(n20 - n6) >> 4;
                n4 = n18;
                n5 = n19;
                n6 = n20;
                av += this.T;
                n2 += this.U;
                n3 += this.V;
                n24 = i + 16;
            }
            else {
                if (n14 == 0) {
                    return;
                }
                av = this.av;
                final float n26;
                final float n25 = (n26 = 65536.0f / av) * this.ax;
                final float n27 = n26 * this.az;
                final float n28 = a3dAPI.b.aB[n14];
                n21 = (int)((n26 - n4) * n28);
                n22 = (int)((n25 - n5) * n28);
                n23 = (int)((n27 - n6) * n28);
                n24 = i + n14;
                b = false;
            }
            if (ao) {
                while (i < n24) {
                    if (n15 < array2[i]) {
                        final int n30;
                        final int n29 = ((n30 = array3[(n17 & an) >> am | (n16 & an) >> 16]) & 0xFF0000) * (n7 >>> 16) >>> 8 & 0xFF0000;
                        final int n31 = (n30 & 0xFF00) * (n9 >>> 16) >>> 8 & 0xFF00;
                        final int n32 = (n30 & 0xFF) * n11 >>> 24;
                        final int n33 = array[i];
                        int n34 = (n29 >> 8) * this.aJ + ((n33 & 0xFF0000) >> 8) * this.aI & 0xFFFF0000;
                        final int n35 = n31 * this.aJ + (n33 & 0xFF00) * this.aI >> 8 & 0xFFFF00;
                        final int n36 = n32 * this.aJ + (n33 & 0xFF) * this.aI >> 8;
                        if (n34 > 16711680) {
                            n34 = 16711680;
                        }
                        if (n35 > 65280) {
                            n34 = 65280;
                        }
                        if (n36 > 255) {
                            n34 = 255;
                        }
                        array[i] = (n34 | n35 | n36);
                    }
                    ++i;
                    n15 += n21;
                    n16 += n22;
                    n17 += n23;
                    n7 += n8;
                    n9 += n10;
                    n11 += n12;
                }
            }
            else {
                while (i < n24) {
                    final int n37;
                    if (n15 < array2[i] && ((n37 = array3[(n17 & an) >> am | (n16 & an) >> 16]) & 0xFF000000) != 0x0) {
                        final int n38 = (n37 & 0xFF0000) * (n7 >>> 16) >>> 8 & 0xFF0000;
                        final int n39 = (n37 & 0xFF00) * (n9 >>> 16) >>> 8 & 0xFF00;
                        final int n40 = (n37 & 0xFF) * n11 >>> 24;
                        final int n41 = array[i];
                        int n42 = (n38 >> 8) * this.aJ + ((n41 & 0xFF0000) >> 8) * this.aI & 0xFFFF0000;
                        final int n43 = n39 * this.aJ + (n41 & 0xFF00) * this.aI >> 8 & 0xFFFF00;
                        final int n44 = n40 * this.aJ + (n41 & 0xFF) * this.aI >> 8;
                        if (n42 > 16711680) {
                            n42 = 16711680;
                        }
                        if (n43 > 65280) {
                            n42 = 65280;
                        }
                        if (n44 > 255) {
                            n42 = 255;
                        }
                        array[i] = (n42 | n43 | n44);
                    }
                    ++i;
                    n15 += n21;
                    n16 += n22;
                    n17 += n23;
                    n7 += n8;
                    n9 += n10;
                    n11 += n12;
                }
            }
        } while (b);
    }
    
    private final void a(final float[][] array) {
        final float[] array2 = array[0];
        final float[] array3 = array[1];
        final float[] array4 = array[2];
        final float n = array3[3] - array4[3];
        final float n2 = array2[4] - array4[4];
        final float n3 = array2[3] - array4[3];
        final float n4 = array3[4] - array4[4];
        final float n6;
        final float n5 = -(n6 = 1.0f / (n * n2 - n3 * n4));
        final float n7 = array3[5] - array4[5];
        final float n8 = array2[5] - array4[5];
        this.H = n6 * (n7 * n2 - n8 * n4);
        this.I = n5 * (n7 * n3 - n8 * n);
        this.T = this.H * 16.0f;
        if (this.aG) {
            final float n9 = array3[11] - array4[11];
            final float n10 = array2[11] - array4[11];
            this.J = n6 * (n9 * n2 - n10 * n4);
            this.K = n5 * (n9 * n3 - n10 * n);
            final float n11 = array3[12] - array4[12];
            final float n12 = array2[12] - array4[12];
            this.L = n6 * (n11 * n2 - n12 * n4);
            this.M = n5 * (n11 * n3 - n12 * n);
            this.U = this.J * 16.0f;
            this.V = this.L * 16.0f;
        }
        if (this.aH) {
            final float n13 = array3[6] - array4[6];
            final float n14 = array2[6] - array4[6];
            this.N = n6 * (n13 * n2 - n14 * n4);
            this.O = n5 * (n13 * n3 - n14 * n);
            final float n15 = array3[7] - array4[7];
            final float n16 = array2[7] - array4[7];
            this.P = n6 * (n15 * n2 - n16 * n4);
            this.Q = n5 * (n15 * n3 - n16 * n);
            final float n17 = array3[8] - array4[8];
            final float n18 = array2[8] - array4[8];
            this.R = n6 * (n17 * n2 - n18 * n4);
            this.S = n5 * (n17 * n3 - n18 * n);
        }
    }
    
    public final int getWidth() {
        return this.getSize().width;
    }
    
    private final int d(final float[][] array, final float[][] array2, final int n) {
        float[] array3 = array[n - 1];
        float n2;
        boolean b = (n2 = this.v - array3[3]) >= 0.0f;
        int n3 = 0;
        for (final float[] array4 : array) {
            final float n4;
            final boolean b2 = (n4 = this.v - array4[3]) >= 0.0f;
            if (b ^ b2) {
                final float n5 = n2 / (n2 - n4);
                final float[] array5;
                (array5 = this.aD[this.aC++])[3] = this.v;
                array5[4] = array3[4] + (array4[4] - array3[4]) * n5;
                array5[5] = array3[5] + (array4[5] - array3[5]) * n5;
                if (this.aG) {
                    array5[11] = array3[11] + (array4[11] - array3[11]) * n5;
                    array5[12] = array3[12] + (array4[12] - array3[12]) * n5;
                }
                if (this.aH) {
                    array5[6] = array3[6] + (array4[6] - array3[6]) * n5;
                    array5[7] = array3[7] + (array4[7] - array3[7]) * n5;
                    array5[8] = array3[8] + (array4[8] - array3[8]) * n5;
                }
                array2[n3++] = array5;
            }
            if (b2) {
                array2[n3++] = array4;
            }
            b = b2;
            array3 = array4;
            n2 = n4;
        }
        return n3;
    }
    
    private final void a(final int[] array, final int[] array2) {
        final int n;
        if ((n = this.ap - this.W) <= 0) {
            return;
        }
        int i = this.ab + this.W;
        float av = this.ad + this.T;
        float n2 = 65536.0f / this.ad;
        final int n3 = 16777215;
        int n4 = n >> 4;
        final int n5 = n & 0xF;
        boolean b = true;
        do {
            int n6 = (int)n2;
            int n7;
            int n9;
            if (n4-- > 0) {
                final float n8;
                n7 = (int)((n8 = 65536.0f / av) - n2) >> 4;
                n2 = n8;
                av += this.T;
                n9 = i + 16;
            }
            else {
                if (n5 == 0) {
                    return;
                }
                av = this.av;
                n7 = (int)((65536.0f / av - n2) * a3dAPI.b.aB[n5]);
                n9 = i + n5;
                b = false;
            }
            while (i < n9) {
                if (n6 < array2[i]) {
                    array[i] = n3;
                    array2[i] = n6;
                }
                ++i;
                n6 += n7;
            }
        } while (b);
    }
    
    public final void a(final int n, final boolean ah, final float n2) {
        if (n == -1) {
            this.aG = false;
        }
        else {
            final int[][] array = this.a[n];
            this.aO = (array[0][0] == 0);
            this.aL = array[0][1];
            this.aN = array[0][2];
            this.aM = array[0][3];
            this.aK = array[1];
            this.aG = (this.aK != null);
            this.aV = (array[0][4] == 1 | this.aW);
        }
        this.aH = ah;
        this.aI = (int)(n2 * 256.0f);
        this.aJ = 256 - this.aI;
        this.aP = (this.aI == 0);
    }
    
    public final int c() {
        int n = -1;
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] == null) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            n = this.r++;
            this.a = a(this.a);
        }
        (this.a[n] = new int[2][])[0] = new int[5];
        this.a[n][1] = null;
        this.a[n][0][3] = -1;
        this.a[n][0][4] = 0;
        return n;
    }
    
    public final void a(final String s) {
        this.n = a3dAPI.a.a(s);
    }
    
    protected b(final int o) {
        b("<=\u001fgl%3\u000fr;7.\f&>32\rc>?2\u000e");
        this.k = 0.1f;
        this.n = false;
        this.o = 0;
        this.p = 1.0f;
        this.r = 0;
        this.v = -1;
        this.w = -1;
        this.aD = new float[20][17];
        this.aE = new float[20][];
        this.aF = new float[20][];
        this.aG = false;
        this.aH = false;
        this.aQ = 0;
        this.aR = null;
        this.aU = null;
        this.aV = false;
        this.aW = false;
        this.aX = 0;
        this.d = false;
        this.e = 0;
        this.f = 0.0f;
        this.g = 1000.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.aY = null;
        this.o = o;
        b b;
        float p;
        if (this.o == 1 || this.o == 2) {
            b = this;
            p = 0.5f;
        }
        else if (this.o == 3) {
            b = this;
            p = 2.0f;
        }
        else {
            b = this;
            p = 1.0f;
        }
        b.p = p;
        this.h();
        this.a();
    }
    
    public final void paint(final Graphics graphics) {
        if (this.B != null) {
            if (this.A != null) {
                if (this.o == 2) {
                    this.C.drawImage(this.A, 0, 0, this.y, this.z, null);
                }
                else {
                    this.C.drawImage(this.A, 0, 0, null);
                }
            }
            if (this.aY != null) {
                this.aY.paint(this.C);
            }
            if (!this.n) {
                this.C.drawImage(this.q, 4, this.z * 3 / 4, null);
            }
            graphics.drawImage(this.B, 0, 0, null);
        }
    }
    
    private void b(final int ax) {
        this.aX = ax;
        if (this.s != null) {
            switch (ax) {
                case 0: {
                    this.t = this.s;
                }
                case 1: {
                    this.t = new int[this.s.length];
                }
                case 2: {
                    this.t = new int[this.s.length];
                }
            }
        }
    }
    
    private final void b(final float[] array, final float[] array2) {
        final int n = (int)(array[3] * 16.0f);
        final int n2 = (int)(array[4] * 16.0f);
        final int n3 = (int)(array2[3] * 16.0f);
        final int n4 = (int)(array2[4] * 16.0f);
        final int n5 = n2 + 15 >> 4;
        this.au = (n4 + 15 >> 4) - n5;
        if (this.au != 0) {
            final int n6;
            final int as = (n6 = n4 - n2) << 4;
            final int n9;
            final int n8;
            final int n7 = (n8 = (n9 = n3 - n) << 4) * n5 - n9 * n2 + n6 * n - 1 + as;
            this.ap = n7 / as;
            this.at = n7 - this.ap * as;
            if (n7 < 0 && this.at != 0) {
                --this.ap;
                this.at += as;
            }
            this.aq = n8 / as;
            this.ar = n8 - this.aq * as;
            if (n8 < 0 && this.ar != 0) {
                --this.aq;
                this.ar += as;
            }
            this.as = as;
            final float n10 = ((n5 << 4) - n2) * 0.0625f;
            final float n11 = ((this.ap << 4) - n) * 0.0625f;
            this.av = array[5] + n10 * this.I + n11 * this.H;
            this.aw = this.aq * this.H + this.I;
            if (this.aG) {
                this.ax = array[11] + n10 * this.K + n11 * this.J;
                this.ay = this.aq * this.J + this.K;
                this.az = array[12] + n10 * this.M + n11 * this.L;
                this.aA = this.aq * this.L + this.M;
            }
        }
    }
    
    private void g() {
        final int n = (int)(-this.f * 65536.0f / (this.l * this.k));
        final int n2 = (int)(-this.g * 65536.0f / (this.l * this.k));
        final int n3 = (int)(this.h * 255.0f);
        final int n4 = (int)(this.i * 255.0f);
        final int n5 = (int)(this.j * 255.0f);
        final int n6 = n2 - n;
        final int n7 = n3 << 16 | n4 << 8 | n5;
        if (this.e == 0) {
            int length = this.s.length;
            while (--length >= 0) {
                final int n8;
                if ((n8 = this.u[length]) >= n) {
                    int[] array;
                    int n9;
                    int n10;
                    if (n8 >= n2) {
                        array = this.s;
                        n9 = length;
                        n10 = n7;
                    }
                    else {
                        final int n11 = 256 * (n2 - n8) / n6;
                        final int n12 = this.s[length];
                        final int n13 = 256 - n11;
                        final int n14 = n3 * n13 + n11 * (n12 >> 16 & 0xFF) >> 8;
                        final int n15 = n4 * n13 + n11 * (n12 >> 8 & 0xFF) >> 8;
                        final int n16 = n5 * n13 + n11 * (n12 & 0xFF) >> 8;
                        array = this.s;
                        n9 = length;
                        n10 = (n14 << 16 | n15 << 8 | n16);
                    }
                    array[n9] = n10;
                }
            }
            return;
        }
        int length2 = this.s.length;
        while (--length2 >= 0) {
            final int n17;
            if ((n17 = this.u[length2]) >= n) {
                int[] array2;
                int n18;
                int n19;
                if (n17 >= n2) {
                    array2 = this.s;
                    n18 = length2;
                    n19 = n7;
                }
                else {
                    final int n20 = (int)(256.0 * Math.exp((n - n17) / (n2 - n17)));
                    final int n21 = this.s[length2];
                    final int n22 = 256 - n20;
                    final int n23 = n3 * n22 + n20 * (n21 >> 16 & 0xFF) >> 8;
                    final int n24 = n4 * n22 + n20 * (n21 >> 8 & 0xFF) >> 8;
                    final int n25 = n5 * n22 + n20 * (n21 & 0xFF) >> 8;
                    array2 = this.s;
                    n18 = length2;
                    n19 = (n23 << 16 | n24 << 8 | n25);
                }
                array2[n18] = n19;
            }
        }
    }
    
    static {
        b.aB = new float[17];
        int n = 0;
        do {
            b.aB[n] = 1.0f / n;
        } while (++n < 17);
    }
    
    public final boolean isConsumer(final ImageConsumer imageConsumer) {
        return false;
    }
    
    public final void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    private final void a(final float[][] array, final int n, final int n2) {
        this.a(array);
        System.arraycopy(array, 0, array, n, n);
        int n3 = 0;
        for (int i = 1; i < n; ++i) {
            if (array[i][4] < array[n3][4]) {
                n3 = i;
            }
        }
        int n4 = n3 + n;
        this.a(array[n3++], array[n3]);
        this.b(array[n4--], array[n4]);
        int n5 = n - 1;
        while (true) {
            if (this.ac <= 0 || this.au <= 0) {
                if (this.ac == 0) {
                    if (--n5 == 0) {
                        return;
                    }
                    this.a(array[n3++], array[n3]);
                }
                if (this.au != 0) {
                    continue;
                }
                if (--n5 == 0) {
                    return;
                }
                this.b(array[n4--], array[n4]);
            }
            else {
                if (this.ab >= this.x) {
                    return;
                }
                switch (n2) {
                    case 0:
                    case 4:
                    case 8:
                    case 12: {
                        this.a(this.s, this.u);
                        break;
                    }
                    case 1:
                    case 5:
                    case 9:
                    case 13: {
                        this.b(this.s, this.u);
                        break;
                    }
                    case 2:
                    case 6: {
                        this.a(this.s, this.u, this.aK);
                        break;
                    }
                    case 10:
                    case 14: {
                        this.c(this.s, this.u, this.aK);
                        break;
                    }
                    case 3: {
                        this.f(this.s, this.u, this.aK);
                        break;
                    }
                    case 7: {
                        this.e(this.s, this.u, this.aK);
                        break;
                    }
                    case 11: {
                        this.b(this.s, this.u, this.aK);
                        break;
                    }
                    case 15: {
                        this.d(this.s, this.u, this.aK);
                        break;
                    }
                }
                this.f();
            }
        }
    }
    
    public final int getHeight() {
        return this.getSize().height;
    }
    
    public final void startProduction(final ImageConsumer d) {
        this.D = d;
        ImageConsumer imageConsumer;
        int n;
        int n2;
        if (this.o == 1 || this.o == 3) {
            imageConsumer = d;
            n = this.y;
            n2 = this.z;
        }
        else {
            imageConsumer = d;
            n = this.v;
            n2 = this.w;
        }
        imageConsumer.setDimensions(n, n2);
        d.setColorModel(this.E);
    }
    
    private static int[][][] a(final int[][][] array) {
        final int[][][] array2 = new int[array.length + 1][][];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public final void d() {
        if (this.B != null) {
            if (this.d) {
                this.g();
            }
            this.e();
            if (this.D != null) {
                this.D.setHints(14);
                ImageConsumer imageConsumer;
                int n5;
                int n6;
                int n7;
                int n8;
                ColorModel colorModel;
                int[] array;
                int n9;
                int n10;
                if (this.o == 1) {
                    int n = 0;
                    final int[] t = this.t;
                    final int[] f = this.F;
                    int n2;
                    int n3;
                    if (this.G) {
                        n2 = 0;
                        n3 = this.y + 1;
                    }
                    else {
                        n2 = 1;
                        n3 = this.y - 1;
                    }
                    final int n4 = n3;
                    this.G = !this.G;
                    for (int i = 0; i < this.w; ++i) {
                        for (int j = 0; j < this.v; ++j) {
                            f[n2] = (f[n2 + n4] = t[n++]);
                            n2 += 2;
                        }
                        n2 += this.y;
                    }
                    imageConsumer = this.D;
                    n5 = 0;
                    n6 = 0;
                    n7 = this.y;
                    n8 = this.z;
                    colorModel = this.E;
                    array = this.F;
                    n9 = 0;
                    n10 = this.y;
                }
                else if (this.o == 3) {
                    final int[] t2 = this.t;
                    final int[] f2 = this.F;
                    int n11 = 0;
                    int n12 = 0;
                    final int v = this.v;
                    for (int k = 0; k < this.z; ++k) {
                        for (int l = 0; l < this.y; ++l) {
                            final int n13 = t2[n11];
                            final int n14 = t2[n11 + 1];
                            final int n15 = t2[n11 + v];
                            final int n16 = t2[n11 + v + 1];
                            f2[n12++] = ((n13 & 0xFF0000) + (n14 & 0xFF0000) + (n15 & 0xFF0000) + (n16 & 0xFF0000) >> 2 & 0xFF0000) + ((n13 & 0xFF00) + (n14 & 0xFF00) + (n15 & 0xFF00) + (n16 & 0xFF00) >> 2 & 0xFF00) + ((n13 & 0xFF) + (n14 & 0xFF) + (n15 & 0xFF) + (n16 & 0xFF) >> 2 & 0xFF);
                            n11 += 2;
                        }
                        n11 += v;
                    }
                    imageConsumer = this.D;
                    n5 = 0;
                    n6 = 0;
                    n7 = this.y;
                    n8 = this.z;
                    colorModel = this.E;
                    array = this.F;
                    n9 = 0;
                    n10 = this.y;
                }
                else {
                    imageConsumer = this.D;
                    n5 = 0;
                    n6 = 0;
                    n7 = this.v;
                    n8 = this.w;
                    colorModel = this.E;
                    array = this.t;
                    n9 = 0;
                    n10 = this.v;
                }
                imageConsumer.setPixels(n5, n6, n7, n8, colorModel, array, n9, n10);
                this.D.imageComplete(2);
                this.paint(this.getGraphics());
            }
        }
    }
    
    private void h() {
        try {
            final byte[] array = new byte[332];
            int n = 0;
            int n2 = 0;
            do {
                final int n3 = a3dAPI.a.F[n2];
                array[n++] = (byte)(n3 >>> 24 & 0xFF);
                array[n++] = (byte)(n3 >>> 16 & 0xFF);
                array[n++] = (byte)(n3 >>> 8 & 0xFF);
                array[n++] = (byte)(n3 & 0xFF);
            } while (++n2 < 83);
            a3dAPI.c.a(this.q = this.getToolkit().createImage(array), this);
        }
        catch (Exception ex) {}
    }
    
    public final Dimension getMinimumSize() {
        return new Dimension(32, 32);
    }
    
    private final int e(final float[][] array, final float[][] array2, final int n) {
        float[] array3;
        float n2;
        boolean b = (n2 = (array3 = array[n - 1])[2] + this.m) <= 0.0f;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            final float[] array4;
            final float n4;
            final boolean b2 = (n4 = (array4 = array[i])[2] + this.m) <= 0.0f;
            if (b ^ b2) {
                final float n5 = n2 / (n2 - n4);
                final float[] array5;
                (array5 = this.aD[this.aC++])[2] = -this.m;
                final float[] array6 = array5;
                final int n6 = 0;
                final float n7 = array3[0] + (array4[0] - array3[0]) * n5;
                array6[n6] = n7;
                final float n8 = n7;
                final float[] array7 = array5;
                final int n9 = 1;
                final float n10 = array3[1] + (array4[1] - array3[1]) * n5;
                array7[n9] = n10;
                final float n11 = n10;
                final float n12 = this.l / -this.m;
                array5[3] = n8 * n12 + this.b;
                array5[4] = -n11 * n12 + this.c;
                final float n13 = n12 * this.k;
                array5[5] = n13;
                if (this.aG) {
                    final float[] array8 = array5;
                    final int n14 = 11;
                    final float[] array9 = array5;
                    final int n15 = 9;
                    final float n16 = array3[9] + (array4[9] - array3[9]) * n5;
                    array9[n15] = n16;
                    array8[n14] = n16 * n13 * this.aL;
                    final float[] array10 = array5;
                    final int n17 = 12;
                    final float[] array11 = array5;
                    final int n18 = 10;
                    final float n19 = array3[10] + (array4[10] - array3[10]) * n5;
                    array11[n18] = n19;
                    array10[n17] = n19 * n13 * this.aL;
                }
                if (this.aH) {
                    array5[6] = array3[6] + (array4[6] - array3[6]) * n5;
                    array5[7] = array3[7] + (array4[7] - array3[7]) * n5;
                    array5[8] = array3[8] + (array4[8] - array3[8]) * n5;
                }
                array2[n3++] = array5;
            }
            if (b2) {
                array2[n3++] = array4;
            }
            b = b2;
            array3 = array4;
            n2 = n4;
        }
        return n3;
    }
    
    private final void b(final int[] array, final int[] array2) {
        final int n;
        if ((n = this.ap - this.W) <= 0) {
            return;
        }
        int i = this.ab + this.W;
        float av = this.ad + this.T;
        float n2 = 65536.0f / this.ad;
        int n3 = (int)(this.aj * 1.671168E7f);
        final int n4 = (int)(this.N * 1.671168E7f);
        int n5 = (int)(this.al * 1.671168E7f);
        final int n6 = (int)(this.P * 1.671168E7f);
        int n7 = (int)(this.an * 1.671168E7f);
        final int n8 = (int)(this.R * 1.671168E7f);
        if (n3 < 0) {
            n3 = 0;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n7 < 0) {
            n7 = 0;
        }
        int n9 = n >> 4;
        final int n10 = n & 0xF;
        boolean b = true;
        do {
            int n11 = (int)n2;
            int n12;
            int n14;
            if (n9-- > 0) {
                final float n13;
                n12 = (int)((n13 = 65536.0f / av) - n2) >> 4;
                n2 = n13;
                av += this.T;
                n14 = i + 16;
            }
            else {
                if (n10 == 0) {
                    return;
                }
                av = this.av;
                n12 = (int)((65536.0f / av - n2) * a3dAPI.b.aB[n10]);
                n14 = i + n10;
                b = false;
            }
            if (this.aP) {
                while (i < n14) {
                    if (n11 < array2[i]) {
                        int n15;
                        if ((n15 = (n3 & 0xFFFF0000)) > 16711680) {
                            n15 = 16711680;
                        }
                        int n16;
                        if ((n16 = (n5 & 0xFFFF0000) >> 8) > 65280) {
                            n16 = 65280;
                        }
                        final int n17 = n15 | n16;
                        int n18;
                        if ((n18 = (n7 & 0xFFFF0000) >> 16) > 255) {
                            n18 = 255;
                        }
                        array[i] = (n17 | n18);
                        array2[i] = n11;
                    }
                    ++i;
                    n11 += n12;
                    n3 += n4;
                    n5 += n6;
                    n7 += n8;
                }
            }
            else {
                while (i < n14) {
                    if (n11 < array2[i]) {
                        final int n19 = array[i];
                        int n20;
                        if ((n20 = (((n3 & 0xFFFF0000) >> 8) * this.aJ + ((n19 & 0xFF0000) >> 8) * this.aI & 0xFFFF0000)) > 16711680) {
                            n20 = 16711680;
                        }
                        int n21;
                        if ((n21 = (((n5 & 0xFFFF0000) >> 8) * this.aJ + (n19 & 0xFF00) * this.aI >> 8 & 0xFFFF00)) > 65280) {
                            n21 = 65280;
                        }
                        final int n22 = n20 + n21;
                        int n23;
                        if ((n23 = ((n7 & 0xFFFF0000) >> 16) * this.aJ + (n19 & 0xFF) * this.aI >> 8) > 255) {
                            n23 = 255;
                        }
                        array[i] = n22 + n23;
                        array2[i] = n11;
                    }
                    ++i;
                    n11 += n12;
                    n3 += n4;
                    n5 += n6;
                    n7 += n8;
                }
            }
        } while (b);
    }
    
    private final void f(final int[] array, final int[] array2, final int[] array3) {
        final int n;
        if ((n = this.ap - this.W) <= 0) {
            return;
        }
        int i = this.ab + this.W;
        float av = this.ad + this.T;
        float n2 = this.af + this.U;
        float n3 = this.ah + this.V;
        float n4 = 65536.0f / this.ad;
        float n5 = this.af * n4;
        float n6 = this.ah * n4;
        int n7 = (int)(this.aj * 1.671168E7f);
        final int n8 = (int)(this.N * 1.671168E7f);
        int n9 = (int)(this.al * 1.671168E7f);
        final int n10 = (int)(this.P * 1.671168E7f);
        int n11 = (int)(this.an * 1.671168E7f);
        final int n12 = (int)(this.R * 1.671168E7f);
        if (n7 < 0) {
            n7 = 0;
        }
        if (n9 < 0) {
            n9 = 0;
        }
        if (n11 < 0) {
            n11 = 0;
        }
        int n13 = n >> 4;
        final int n14 = n & 0xF;
        final int an = this.aN;
        final int am = this.aM;
        final boolean ao = this.aO;
        boolean b = true;
        do {
            int n15 = (int)n4;
            int n16 = (int)n5 & 0x1FFFFFF;
            int n17 = (int)n6 & 0x1FFFFFF;
            int n21;
            int n22;
            int n23;
            int n24;
            if (n13-- > 0) {
                final float n18 = 65536.0f / av;
                final float n19 = n2 * n18;
                final float n20 = n3 * n18;
                n21 = (int)(n18 - n4) >> 4;
                n22 = (int)(n19 - n5) >> 4;
                n23 = (int)(n20 - n6) >> 4;
                n4 = n18;
                n5 = n19;
                n6 = n20;
                av += this.T;
                n2 += this.U;
                n3 += this.V;
                n24 = i + 16;
            }
            else {
                if (n14 == 0) {
                    return;
                }
                av = this.av;
                final float n26;
                final float n25 = (n26 = 65536.0f / av) * this.ax;
                final float n27 = n26 * this.az;
                final float n28 = a3dAPI.b.aB[n14];
                n21 = (int)((n26 - n4) * n28);
                n22 = (int)((n25 - n5) * n28);
                n23 = (int)((n27 - n6) * n28);
                n24 = i + n14;
                b = false;
            }
            if (ao) {
                while (i < n24) {
                    if (n15 < array2[i]) {
                        final int n30;
                        int n29 = ((n30 = array3[(n17 & an) >> am | (n16 & an) >> 16]) & 0xFF0000) * (n7 >>> 16) >>> 8 & 0xFF0000;
                        final int n31 = (n30 & 0xFF00) * (n9 >>> 16) >>> 8 & 0xFF00;
                        final int n32 = (n30 & 0xFF) * n11 >>> 24;
                        if (n29 > 16711680) {
                            n29 = 16711680;
                        }
                        if (n31 > 65280) {
                            n29 = 65280;
                        }
                        if (n32 > 255) {
                            n29 = 255;
                        }
                        array[i] = (n29 | n31 | n32);
                        array2[i] = n15;
                    }
                    ++i;
                    n15 += n21;
                    n16 += n22;
                    n17 += n23;
                    n7 += n8;
                    n9 += n10;
                    n11 += n12;
                }
            }
            else {
                while (i < n24) {
                    final int n33;
                    if (n15 < array2[i] && ((n33 = array3[(n17 & an) >> am | (n16 & an) >> 16]) & 0xFF000000) != 0x0) {
                        int n34 = (n33 & 0xFF0000) * (n7 >>> 16) >>> 8 & 0xFF0000;
                        final int n35 = (n33 & 0xFF00) * (n9 >>> 16) >>> 8 & 0xFF00;
                        final int n36 = (n33 & 0xFF) * n11 >>> 24;
                        if (n34 > 16711680) {
                            n34 = 16711680;
                        }
                        if (n35 > 65280) {
                            n34 = 65280;
                        }
                        if (n36 > 255) {
                            n34 = 255;
                        }
                        array[i] = (n34 | n35 | n36);
                        array2[i] = n15;
                    }
                    ++i;
                    n15 += n21;
                    n16 += n22;
                    n17 += n23;
                    n7 += n8;
                    n9 += n10;
                    n11 += n12;
                }
            }
        } while (b);
    }
    
    public final void a(final Component ay) {
        this.aY = ay;
    }
    
    public final void a(final float[] array, final float[] array2, final float[] array3, int n) {
        if (this.o != 0) {
            final int n2 = 3;
            array[n2] *= this.p;
            final int n3 = 4;
            array[n3] *= this.p;
            final int n4 = 5;
            array[n4] *= this.p;
            final int n5 = 3;
            array2[n5] *= this.p;
            final int n6 = 4;
            array2[n6] *= this.p;
            final int n7 = 5;
            array2[n7] *= this.p;
            final int n8 = 3;
            array3[n8] *= this.p;
            final int n9 = 4;
            array3[n9] *= this.p;
            final int n10 = 5;
            array3[n10] *= this.p;
        }
        this.aC = 0;
        final int n11 = (this.aH ? 1 : 0) | (this.aG ? 2 : 0) | (this.aP ? 0 : 4) | (this.aV ? 8 : 0);
        final int n12 = 5;
        array[n12] *= this.k;
        final int n13 = 5;
        array2[n13] *= this.k;
        final int n14 = 5;
        array3[n14] *= this.k;
        final float n15 = array[5] * this.aL;
        final float n16 = array2[5] * this.aL;
        final float n17 = array3[5] * this.aL;
        array[11] = array[9] * n15;
        array[12] = array[10] * n15;
        array2[11] = array2[9] * n16;
        array2[12] = array2[10] * n16;
        array3[11] = array3[9] * n17;
        array3[12] = array3[10] * n17;
        int n18 = 3;
        float[][] ae = this.aE;
        float[][] af;
        (af = this.aF)[0] = array;
        af[1] = array2;
        af[2] = array3;
        if ((n & 0x20) != 0x0) {
            final float[][] array4 = af;
            af = ae;
            ae = array4;
            if ((n18 = this.e(ae, af, 3)) < 3) {
                return;
            }
            n |= 0x7E;
        }
        if ((n & 0x2) != 0x0) {
            final float[][] array5 = af;
            af = ae;
            ae = array5;
            if ((n18 = this.a(ae, af, n18)) < 3) {
                return;
            }
        }
        if ((n & 0x4) != 0x0) {
            final float[][] array6 = af;
            af = ae;
            ae = array6;
            if ((n18 = this.d(ae, af, n18)) < 3) {
                return;
            }
        }
        if ((n & 0x8) != 0x0) {
            final float[][] array7 = af;
            af = ae;
            ae = array7;
            if ((n18 = this.b(ae, af, n18)) < 3) {
                return;
            }
        }
        if ((n & 0x10) != 0x0) {
            final float[][] array8 = af;
            af = ae;
            if ((n18 = this.c(array8, af, n18)) < 3) {
                return;
            }
        }
        this.a(af, n18, n11);
    }
    
    private static String b(final String s) {
        char[] charArray;
        for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'V';
                    break;
                }
                case 1: {
                    c2 = '\\';
                    break;
                }
                case 2: {
                    c2 = 'i';
                    break;
                }
                case 3: {
                    c2 = '\u0006';
                    break;
                }
                default: {
                    c2 = 'L';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
