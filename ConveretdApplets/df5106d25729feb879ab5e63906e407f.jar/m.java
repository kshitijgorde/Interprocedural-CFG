import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.IndexColorModel;
import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class m
{
    private static int o;
    private static final int j = 3;
    private static final int fb = 40;
    private static final float[] hb;
    private static final float[] db;
    private static final Color[] gb;
    private static Color[] eb;
    private Applet v;
    private Image[] bb;
    private boolean[] t;
    private int n;
    private int m;
    private int q;
    private boolean[] k;
    private boolean[] u;
    private float[] w;
    private byte[] s;
    private int[] ab;
    private int[] b;
    private int[] y;
    private int[] z;
    private int[] x;
    private int[] cb;
    protected f p;
    protected int[] f;
    protected int[] g;
    protected int[] h;
    protected float d;
    protected float a;
    protected float c;
    protected float e;
    protected boolean r;
    protected boolean i;
    protected e l;
    
    static {
        m.eb = new Color[225];
        m.gb = new Color[] { new Color(239, 239, 239), new Color(159, 159, 159), new Color(255, 0, 0), new Color(0, 0, 255), new Color(255, 255, 0), new Color(255, 127, 0), new Color(0, 255, 255), new Color(0, 255, 0), new Color(127, 0, 0), new Color(191, 0, 191), new Color(0, 127, 0), new Color(127, 127, 255), new Color(0, 127, 255), new Color(255, 127, 127), new Color(255, 191, 191), new Color(191, 127, 127), new Color(255, 0, 255), new Color(127, 0, 255), new Color(223, 223, 0), new Color(255, 159, 0), new Color(127, 191, 127), new Color(0, 0, 191), new Color(159, 255, 159), new Color(255, 0, 63), new Color(255, 127, 255), new Color(0, 95, 255), new Color(127, 255, 255), new Color(0, 191, 191) };
        m.db = new float[] { 1.2f, 1.7f, 1.5f, 1.55f, 1.85f, 1.88f, 1.47f, 1.81f, 1.95f, 2.15f, 2.31f, 1.7f, 1.25f, 1.28f, 2.1f, 2.08f, 1.71f, 2.31f, 2.0f, 1.43f, 1.44f, 1.25f, 1.48f, 1.71f, 1.35f, 1.41f, 1.98f, 1.75f };
        m.hb = new float[] { 0.37f, 0.77f, 0.66f, 0.75f, 1.04f, 1.1f, 0.64f, 0.99f, 1.14f, 1.33f, 2.03f, 1.17f, 1.16f, 1.17f, 1.17f, 0.88f, 1.36f, 1.57f, 1.17f, 1.25f, 1.28f, 1.15f, 1.22f, 1.32f, 1.25f, 1.4f, 1.0f, 1.0f };
    }
    
    protected String a() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n   Name                         ");
        sb.append(this.l.h.substring(0, this.l.h.length() - 4));
        sb.append("\n   Number of Atoms       ");
        sb.append(this.l.e);
        sb.append("\n   Number of Bonds       ");
        sb.append(this.l.d);
        if (this.l.g) {
            sb.append("\n   Number of Groups     ");
            sb.append(this.m - 1);
            sb.append("\n   Number of Chains      ");
            sb.append(this.n - 1);
        }
        return sb.toString();
    }
    
    protected int a(final int n) {
        return this.y[n];
    }
    
    protected Color b(final int n) {
        return m.gb[n];
    }
    
    protected void b() {
        System.arraycopy(this.cb, 0, this.y, 0, this.l.e);
        System.arraycopy(m.db, 0, this.w, 0, 27);
    }
    
    protected void c() {
        if (this.l.g) {
            System.arraycopy(this.z, 0, this.y, 0, this.l.e);
        }
        else {
            int e = this.l.e;
            while (--e >= 0) {
                this.y[e] = 1;
            }
        }
        int n = 27;
        while (--n >= 0) {
            this.w[n] = 1.7f;
        }
    }
    
    protected void d() {
        if (this.l.g) {
            System.arraycopy(this.x, 0, this.y, 0, this.l.e);
        }
        else {
            int e = this.l.e;
            while (--e >= 0) {
                this.y[e] = 0;
            }
        }
        int n = 27;
        while (--n >= 0) {
            this.w[n] = 1.7f;
        }
    }
    
    protected float a(final int n, final int n2, final int n3, final int n4) {
        final float n5 = this.l.c[n] - this.l.c[n2];
        final float n6 = this.l.b[n] - this.l.b[n2];
        final float n7 = this.l.a[n] - this.l.a[n2];
        final float n8 = this.l.c[n3] - this.l.c[n2];
        final float n9 = this.l.b[n3] - this.l.b[n2];
        final float n10 = this.l.a[n3] - this.l.a[n2];
        final float n11 = this.l.c[n3] - this.l.c[n4];
        final float n12 = this.l.b[n3] - this.l.b[n4];
        final float n13 = this.l.a[n3] - this.l.a[n4];
        final float n14 = n6 * n10 - n7 * n9;
        final float n15 = n7 * n8 - n5 * n10;
        final float n16 = n5 * n9 - n6 * n8;
        final float n17 = n9 * n13 - n10 * n12;
        final float n18 = n10 * n11 - n8 * n13;
        final float n19 = n8 * n12 - n9 * n11;
        final double n20 = (n14 * n17 + n15 * n18 + n16 * n19) * Math.sqrt(1.0 / (n14 * n14 + n15 * n15 + n16 * n16)) * Math.sqrt(1.0 / (n17 * n17 + n18 * n18 + n19 * n19));
        return (int)(Math.acos((n20 > 1.0) ? 1.0 : ((n20 < -1.0) ? -1.0 : n20)) * 180000.0 / 3.1415926535) / 1000.0f;
    }
    
    protected float a(final int n, final int n2, final int n3) {
        final float a = this.a(n2, n3);
        final float a2 = this.a(n, n2);
        final float a3 = this.a(n, n3);
        return (int)(Math.acos((a * a + a2 * a2 - a3 * a3) / (2.0f * a * a2)) * 180000.0 / 3.1415926535) / 1000.0f;
    }
    
    protected float a(final int n, final int n2) {
        final float n3 = this.l.c[n] - this.l.c[n2];
        final float n4 = this.l.b[n] - this.l.b[n2];
        final float n5 = this.l.a[n] - this.l.a[n2];
        return (int)(Math.sqrt(n3 * n3 + n4 * n4 + n5 * n5) * 100.0) / 100.0f;
    }
    
    protected int b(final int n, final int n2) {
        double n3 = 10000.0;
        int n4 = 0;
        int e = this.l.e;
        while (--e >= 0) {
            final double n5 = Math.pow(n - this.h[e], 2.0) + Math.pow(n2 - this.g[e], 2.0);
            if (n5 < n3) {
                n3 = n5;
                n4 = e;
            }
        }
        return n4;
    }
    
    void a(final Graphics graphics, final int n, final int n2, final float n3) {
        this.e();
        this.f();
        if (n != 1) {
            final int n4 = (n3 / 8.0f > 1.0f) ? ((int)(n3 / 8.0f)) : 1;
            final int n5 = (n4 > 5) ? ((n4 < 11) ? (n4 / 2) : 5) : 2;
            final int n6 = n4 << 1;
            System.arraycopy(this.k, 0, this.u, 0, this.l.d);
            int e = this.l.e;
            while (--e >= 0) {
                final int n7 = this.b[e];
                if (this.cb[n7] != 0 || n2 == 0) {
                    final int n8 = this.y[n7];
                    final int n9 = this.h[n7];
                    final int n10 = this.g[n7];
                    int n11 = this.ab[n7 << 3];
                    while (--n11 >= 0) {
                        final int n12 = this.ab[(n7 << 3) + n11 + 1];
                        if (!this.u[n12]) {
                            this.u[n12] = true;
                            int n13;
                            if (this.l.i[n12] == n7) {
                                n13 = this.l.j[n12];
                            }
                            else {
                                n13 = this.l.i[n12];
                            }
                            final int n14 = this.cb[n13];
                            final int n15 = this.h[n13];
                            final int n16 = this.g[n13];
                            final int n17 = this.y[n13];
                            final int n18 = this.l.k[n12];
                            final int n19 = n15 + n9 >> 1;
                            final int n20 = n16 + n10 >> 1;
                            final int n21 = n15 - n9;
                            final int n22 = n16 - n10;
                            final double sqrt = Math.sqrt(n21 * n21 + n22 * n22);
                            final double n23 = -n22 / sqrt;
                            final double n24 = n21 / sqrt;
                            if (n == 0 || n4 < 3) {
                                if (n18 != 2) {
                                    graphics.setColor(m.gb[n8]);
                                    graphics.drawLine(n9, n10, n19, n20);
                                    if (n14 != 0 || n2 == 0) {
                                        graphics.setColor(m.gb[n17]);
                                        graphics.drawLine(n19, n20, n15, n16);
                                    }
                                }
                                if (n18 != 2 && n18 != 3) {
                                    continue;
                                }
                                final int n25 = (int)(n23 * n4);
                                final int n26 = (int)(n24 * n4);
                                graphics.setColor(m.gb[n8]);
                                graphics.drawLine(n9 + n25, n10 + n26, n19 + n25, n20 + n26);
                                graphics.drawLine(n9 - n25, n10 - n26, n19 - n25, n20 - n26);
                                if (n14 == 0 && n2 != 0) {
                                    continue;
                                }
                                graphics.setColor(m.gb[n17]);
                                graphics.drawLine(n19 + n25, n20 + n26, n15 + n25, n16 + n26);
                                graphics.drawLine(n19 - n25, n20 - n26, n15 - n25, n16 - n26);
                            }
                            else {
                                if (n == 2) {
                                    graphics.setColor(m.eb[(n8 << 3) + n5]);
                                    graphics.fillOval(n9 - n4, n10 - n4, n6, n6);
                                    if (n14 != 0 || n2 == 0) {
                                        graphics.setColor(m.eb[(n17 << 3) + n5]);
                                        graphics.fillOval(n15 - n4, n16 - n4, n6, n6);
                                    }
                                }
                                final int[] array = new int[4];
                                final int[] array2 = new int[4];
                                if (n14 != 0 || n2 == 0) {
                                    int n27 = n4 + 2;
                                    while (--n27 > 1) {
                                        final int n28 = (int)(n23 * n27);
                                        final int n29 = (int)(n24 * n27);
                                        array[0] = n9 + n28;
                                        array2[0] = n10 + n29;
                                        array[1] = n9 - n28;
                                        array2[1] = n10 - n29;
                                        array[2] = n19 - n28;
                                        array2[2] = n20 - n29;
                                        array[3] = n19 + n28;
                                        array2[3] = n20 + n29;
                                        graphics.setColor(m.eb[(n8 << 3) + ((n27 - 1 < 8) ? (n27 - 1) : 7)]);
                                        graphics.fillPolygon(new Polygon(array, array2, 4));
                                        array[0] = n15 + n28;
                                        array2[0] = n16 + n29;
                                        array[1] = n15 - n28;
                                        array2[1] = n16 - n29;
                                        graphics.setColor(m.eb[(n17 << 3) + ((n27 - 1 < 8) ? (n27 - 1) : 7)]);
                                        graphics.fillPolygon(new Polygon(array, array2, 4));
                                    }
                                }
                                else {
                                    int n30 = n4 + 2;
                                    while (--n30 > 1) {
                                        final int n31 = (int)(n23 * n30);
                                        final int n32 = (int)(n24 * n30);
                                        array[0] = n9 + n31;
                                        array2[0] = n10 + n32;
                                        array[1] = n9 - n31;
                                        array2[1] = n10 - n32;
                                        array[2] = n19 - n31;
                                        array2[2] = n20 - n32;
                                        array[3] = n19 + n31;
                                        array2[3] = n20 + n32;
                                        graphics.setColor(m.eb[(n8 << 3) + ((n30 - 1 < 8) ? (n30 - 1) : 7)]);
                                        graphics.fillPolygon(new Polygon(array, array2, 4));
                                    }
                                }
                            }
                        }
                    }
                    if (n != 3) {
                        continue;
                    }
                    final int n33 = (int)(this.w[this.cb[n7]] * n3) >> 1;
                    graphics.drawImage(this.bb[n8], n9 - (n33 >> 1), n10 - (n33 >> 1), n33, n33, this.v);
                }
            }
            return;
        }
        int e2 = this.l.e;
        while (--e2 >= 0) {
            final int n34 = this.b[e2];
            final int n35 = this.cb[n34];
            final int n36 = (int)(n3 * this.w[n35]);
            if (n35 != 0 || n2 == 0) {
                graphics.drawImage(this.bb[this.y[n34]], this.h[n34] - (n36 >> 1), this.g[n34] - (n36 >> 1), n36, n36, this.v);
            }
        }
    }
    
    protected void e() {
        if (this.i) {
            return;
        }
        this.p.a(this.l.c, this.l.b, this.l.a, this.h, this.g, this.f);
        this.i = true;
    }
    
    protected void c(final int n, final int n2) {
        float n4;
        float n3 = n4 = this.l.c[0];
        float n6;
        float n5 = n6 = this.l.b[0];
        float n8;
        float n7 = n8 = this.l.a[0];
        int e = this.l.e;
        while (--e >= 0) {
            if (this.l.c[e] < n3) {
                n3 = this.l.c[e];
            }
            if (this.l.c[e] > n4) {
                n4 = this.l.c[e];
            }
            if (this.l.b[e] < n5) {
                n5 = this.l.b[e];
            }
            if (this.l.b[e] > n6) {
                n6 = this.l.b[e];
            }
            if (this.l.a[e] < n7) {
                n7 = this.l.a[e];
            }
            if (this.l.a[e] > n8) {
                n8 = this.l.a[e];
            }
        }
        float n9 = n4 - n3;
        final float n10 = n6 - n5;
        final float n11 = n8 - n7;
        if (n10 > n9) {
            n9 = n10;
        }
        if (n11 > n9) {
            n9 = n11;
        }
        final float n12 = n / n9;
        final float n13 = n2 / n9;
        this.d = 0.7f * ((n12 < n13) ? n12 : n13);
        this.e = (n4 + n3) / 2.0f;
        this.c = (n6 + n5) / 2.0f;
        this.a = (n8 + n7) / 2.0f;
    }
    
    private void f() {
        if (this.l.e < 250) {
            int e = this.l.e;
            int i = -1;
            while (i < e) {
                ++i;
                --e;
                this.r = false;
                for (int j = i; j < e; ++j) {
                    final int n = this.b[j + 1];
                    if (this.f[this.b[j]] < this.f[n]) {
                        this.b[j + 1] = this.b[j];
                        this.b[j] = n;
                        this.r = true;
                    }
                }
                if (!this.r) {
                    return;
                }
                this.r = false;
                int n2 = e;
                while (--n2 >= i) {
                    final int n3 = this.b[n2 + 1];
                    if (this.f[this.b[n2]] < this.f[n3]) {
                        this.b[n2 + 1] = this.b[n2];
                        this.b[n2] = n3;
                        this.r = true;
                    }
                }
                if (!this.r) {
                    return;
                }
            }
            return;
        }
        this.g();
    }
    
    private void g() {
        int n;
        int i;
        for (n = this.l.e - 1, i = 1; i <= n / 9; i = 3 * i + 1) {}
        while (i > 0) {
            for (int j = i; j <= n; ++j) {
                int n2;
                int n3;
                for (n2 = j, n3 = this.b[j]; n2 >= i && this.f[n3] > this.f[this.b[n2 - i]]; n2 -= i) {
                    this.b[n2] = this.b[n2 - i];
                }
                this.b[n2] = n3;
            }
            i /= 3;
        }
    }
    
    private void h() {
        int n = 27;
        while (--n >= 0) {
            if (this.t[n]) {
                final int red = m.gb[n].getRed();
                final int green = m.gb[n].getGreen();
                final int blue = m.gb[n].getBlue();
                final byte[] array = new byte[m.o + 1];
                final byte[] array2 = new byte[m.o + 1];
                final byte[] array3 = new byte[m.o + 1];
                array[0] = 0;
                array3[0] = (array2[0] = 0);
                if (this.q < 191) {
                    for (int i = 1; i <= 3; ++i) {
                        array[i] = (byte)red;
                        array2[i] = (byte)green;
                        array3[i] = (byte)blue;
                    }
                    for (int j = m.o; j > 3; --j) {
                        final float n2 = j / m.o;
                        array[j] = (byte)(red + ((this.q >> 1) - red) * n2);
                        array2[j] = (byte)(green + ((this.q >> 1) - green) * n2);
                        array3[j] = (byte)(blue + ((this.q >> 1) - blue) * n2);
                    }
                }
                else {
                    for (int k = 1; k <= 3; ++k) {
                        final byte[] array4 = array2;
                        final int n3 = k;
                        final byte[] array5 = array3;
                        final int n4 = k;
                        final byte[] array6 = array;
                        final int n5 = k;
                        final byte b = -1;
                        array6[n5] = b;
                        array4[n3] = (array5[n4] = b);
                    }
                    for (int l = m.o; l > 3; --l) {
                        final float n6 = l / m.o;
                        final byte[] array7 = array;
                        final int n7 = l;
                        final byte b2 = (byte)(255.0f + (red - 255) * n6);
                        final int q = this.q;
                        array7[n7] = (byte)(q + (b2 - q));
                        final byte[] array8 = array2;
                        final int n8 = l;
                        final byte b3 = (byte)(255.0f + (green - 255) * n6);
                        final int q2 = this.q;
                        array8[n8] = (byte)(q2 + (b3 - q2));
                        final byte[] array9 = array3;
                        final int n9 = l;
                        final byte b4 = (byte)(255.0f + (blue - 255) * n6);
                        final int q3 = this.q;
                        array9[n9] = (byte)(q3 + (b4 - q3));
                    }
                }
                this.bb[n] = this.v.createImage(new MemoryImageSource(80, 80, new IndexColorModel(6, m.o + 1, array, array2, array3, 0), this.s, 0, 80));
                int n10 = 8;
                while (--n10 >= 0) {
                    final int n11 = m.o / 10 * (n10 + 2);
                    m.eb[(n << 3) + n10] = new Color((array[n11] < 0) ? (array[n11] + 256) : array[n11], (array2[n11] < 0) ? (array2[n11] + 256) : array2[n11], (array3[n11] < 0) ? (array3[n11] + 256) : array3[n11]);
                }
            }
        }
    }
    
    private static final byte a(final int n, final int n2, final float n3) {
        return (byte)(n2 + (n - n2) * n3);
    }
    
    private void d(final int n, final int n2) {
        int o = 0;
        int n3 = 80;
        while (--n3 >= 0) {
            final int n4 = (int)(Math.sqrt(1600 - (n3 - 40) * (n3 - 40)) + 0.5);
            int n5 = n3 * 80 + 40 - n4;
            for (int i = -n4; i < n4; ++i) {
                final int n6 = i + n;
                final int n7 = n3 - 40 + n2;
                final int n8 = (int)(Math.sqrt(n6 * n6 + n7 * n7) + 0.5);
                if (n8 > o) {
                    o = n8;
                }
                this.s[n5++] = (byte)((n8 <= 0) ? 1 : ((byte)n8));
            }
        }
        m.o = o;
    }
    
    private static final int a(final char c, final char c2, final char c3) {
        if (c == ' ') {
            if (c2 == 'H') {
                return 0;
            }
            if (c2 == 'C') {
                return 1;
            }
            if (c2 == 'O') {
                return 2;
            }
            if (c2 == 'N') {
                return 3;
            }
            if (c2 == 'P') {
                return 5;
            }
            if (c2 == 'S') {
                return 4;
            }
            if (c2 == 'F') {
                return 6;
            }
            if (c2 == 'B') {
                return 15;
            }
            if (c2 == 'I') {
                return 9;
            }
            if (c2 == 'K') {
                return 16;
            }
            if (c2 != ' ' && c2 != '+') {
                return 14;
            }
            if (c3 == 'A') {
                return 2;
            }
            if (c3 == 'C') {
                return 16;
            }
            if (c3 == 'G') {
                return 7;
            }
            if (c3 == 'T') {
                return 6;
            }
            if (c3 == 'U') {
                return 17;
            }
            if (c3 == 'I') {
                return 14;
            }
            return 14;
        }
        else if (c == 'A') {
            if (c2 == 'L') {
                if (c3 == 'A') {
                    return 1;
                }
                return 19;
            }
            else if (c2 == 'R') {
                if (c3 == 'G') {
                    return 12;
                }
                return 14;
            }
            else {
                if (c2 != 'S') {
                    return 2;
                }
                if (c3 == 'N') {
                    return 26;
                }
                if (c3 == 'P') {
                    return 23;
                }
                return 14;
            }
        }
        else if (c == 'B') {
            if (c2 == 'R') {
                return 8;
            }
            return 14;
        }
        else if (c == 'C') {
            if (c2 == 'L') {
                return 7;
            }
            if (c2 == 'Y') {
                return 4;
            }
            if (c2 == '3') {
                if (c3 == ' ') {
                    return 1;
                }
                return 16;
            }
            else {
                if (c2 == 'U') {
                    return 13;
                }
                if (c2 == 'O') {
                    return 12;
                }
                return 1;
            }
        }
        else {
            if (c == 'F') {
                return 11;
            }
            if (c == 'G') {
                if (c2 != 'L') {
                    return 7;
                }
                if (c3 == 'N') {
                    return 27;
                }
                if (c3 == 'Y') {
                    return 0;
                }
                return 13;
            }
            else if (c == 'H') {
                if (c2 == 'I') {
                    return 11;
                }
                if (c2 == 'O') {
                    return 14;
                }
                if (c2 == 'Y') {
                    return 15;
                }
                return 0;
            }
            else {
                if (c == 'I') {
                    return 22;
                }
                if (c == 'L') {
                    if (c2 == 'Y') {
                        return 25;
                    }
                    if (c2 == 'E') {
                        return 10;
                    }
                    return 14;
                }
                else if (c == 'M') {
                    if (c2 == 'G') {
                        return 10;
                    }
                    if (c2 == 'E') {
                        return 18;
                    }
                    return 14;
                }
                else if (c == 'N') {
                    if (c2 == 'A') {
                        return 17;
                    }
                    if (c2 == 'I') {
                        return 21;
                    }
                    return 3;
                }
                else {
                    if (c == 'O') {
                        return 2;
                    }
                    if (c == 'P') {
                        if (c2 == 'R') {
                            return 15;
                        }
                        if (c2 == 'H') {
                            return 3;
                        }
                        if (c2 == 'C') {
                            return 8;
                        }
                        return 20;
                    }
                    else if (c == 'R') {
                        if (c2 == 'H') {
                            return 24;
                        }
                        return 14;
                    }
                    else if (c == 'S') {
                        if (c2 == 'E') {
                            if (c3 == 'R') {
                                return 5;
                            }
                            return 18;
                        }
                        else {
                            if (c2 == 'I') {
                                return 14;
                            }
                            if (c2 == 'G' || c2 == 'D') {
                                return 4;
                            }
                            if (c2 == 'N') {
                                return 25;
                            }
                            return 14;
                        }
                    }
                    else if (c == 'T') {
                        if (c2 == 'H') {
                            return 19;
                        }
                        if (c2 == 'R') {
                            return 9;
                        }
                        if (c2 == 'Y') {
                            return 21;
                        }
                        if (c2 == 'I') {
                            return 23;
                        }
                        return 6;
                    }
                    else {
                        if (c == 'U') {
                            return 17;
                        }
                        if (c == 'V') {
                            return 20;
                        }
                        return 14;
                    }
                }
            }
        }
    }
    
    m(final Applet v, e e, final int n, final int q, final int n2, final int n3) {
        this.v = v;
        this.s = new byte[6400];
        this.q = q;
        this.l = e;
        this.p = new f();
        this.cb = new int[e.e];
        this.h = new int[e.e];
        this.g = new int[e.e];
        this.f = new int[e.e];
        this.b = new int[e.e];
        this.y = new int[e.e];
        this.ab = new int[e.e * 8];
        this.w = new float[28];
        (this.t = new boolean[28])[0] = true;
        this.t[1] = true;
        int n4 = 1;
        int e2 = e.e;
        while (--e2 >= 0) {
            this.b[e2] = e2;
            char char1;
            char c;
            if (e.l[e2].length() == 1) {
                char1 = ' ';
                c = e.l[e2].charAt(0);
            }
            else {
                char1 = e.l[e2].charAt(0);
                c = Character.toUpperCase(e.l[e2].charAt(1));
            }
            this.cb[e2] = a(char1, c, ' ');
            if (!this.t[this.cb[e2]]) {
                this.t[this.cb[e2]] = true;
                if (this.cb[e2] <= n4) {
                    continue;
                }
                n4 = this.cb[e2];
            }
        }
        if (e.g) {
            this.x = new int[e.e];
            this.z = new int[e.e];
            this.m = 1;
            this.n = 2;
            this.z[0] = 2;
            this.x[0] = a(e.n[0].charAt(0), e.n[0].charAt(1), e.n[0].charAt(2));
            int e3 = e.e;
            while (--e3 > 0) {
                this.x[e3] = a(e.n[e3].charAt(0), e.n[e3].charAt(1), e.n[e3].charAt(2));
                if (!this.t[this.x[e3]]) {
                    this.t[this.x[e3]] = true;
                    if (this.x[e3] > n4) {
                        n4 = this.x[e3];
                    }
                }
                if (e.m[e3] != e.m[e3 - 1]) {
                    ++this.m;
                }
                if (e.o[e3] != ' ') {
                    this.z[e3] = this.n;
                    if (e.o[e3] == e.o[e3 - 1]) {
                        continue;
                    }
                    ++this.n;
                    if (this.t[this.n]) {
                        continue;
                    }
                    this.t[this.n] = true;
                    if (this.n <= n4) {
                        continue;
                    }
                    n4 = this.n;
                }
                else {
                    this.z[e3] = 1;
                }
            }
        }
        if (e.g || e.f) {
            int n5 = 0;
            int d = e.d;
            for (int i = 0; i < e.e; ++i) {
                for (int j = i + 1; j < e.e; ++j) {
                    if (this.a(i, j) < m.hb[this.cb[i]] + m.hb[this.cb[j]] + 0.2f) {
                        e.j[n5] = i;
                        e.i[n5] = j;
                        if (++n5 >= d) {
                            d += 100;
                            e = e.a(e.e, d);
                        }
                    }
                }
            }
            e = e.a(e.e, n5);
            this.l = e;
        }
        this.u = new boolean[e.d];
        this.k = new boolean[e.d];
        for (int k = 0; k < e.d; ++k) {
            final int n6 = e.j[k] << 3;
            final int n7 = e.i[k] << 3;
            final int[] ab = this.ab;
            final int n8 = n6;
            ++ab[n8];
            this.ab[n6 + this.ab[n6]] = k;
            final int[] ab2 = this.ab;
            final int n9 = n7;
            ++ab2[n9];
            this.ab[n7 + this.ab[n7]] = k;
        }
        this.bb = new Image[n4 + 1];
        this.d(n2, n3);
        this.h();
        this.s = null;
        if (n == 0) {
            this.b();
            return;
        }
        if (n == 1) {
            this.d();
            return;
        }
        if (n == 2) {
            this.c();
        }
    }
}
