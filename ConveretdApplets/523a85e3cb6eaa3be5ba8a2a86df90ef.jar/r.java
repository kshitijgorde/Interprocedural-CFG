import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    private int p;
    private int d;
    private int a;
    private int n;
    private int[] p;
    private int v;
    private int i;
    private int l;
    private int b;
    private int c;
    private int e;
    private int f;
    private int[] d;
    private int[] a;
    private static int[] n;
    private static int[] v;
    private static int[] i;
    private static int[] l;
    
    public r(final Image image, final int a, final int n, final int v, final int i) {
        this.v = 26;
        this.i = -64;
        this.l = Integer.MIN_VALUE;
        this.b = Integer.MIN_VALUE;
        this.c = Integer.MIN_VALUE;
        this.d = image.getHeight(null);
        this.p = image.getWidth(null);
        this.a = a;
        this.n = n;
        this.p = new int[this.p * this.d];
        this.v = v;
        this.i = i;
        this.p();
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.p, this.d, this.p, 0, this.p);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void p() {
        this.e = (this.a >> 3) + 1;
        this.f = (this.n >> 3) + 1;
        this.d = new int[this.f * this.e];
        this.a = new int[this.f * this.e];
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                r.n[i << 4 | j] = (16 - j) * (16 - i);
                r.v[i << 4 | j] = j * (16 - i);
                r.i[i << 4 | j] = (16 - j) * i;
                r.l[i << 4 | j] = j * i;
            }
        }
    }
    
    private final int p(final int n, final int n2, final int n3) {
        final int n6;
        final int n5;
        final int n8;
        final int n7;
        final int n9;
        final int n10;
        final int n11;
        final int n12;
        final int n4 = ((n5 = this.p[(n6 = (n3 >> 18) * this.p) + (n >> 18)]) & 0xFF00FF) * (n7 = r.n[n8 = ((n3 & 0x3C000) >> 10 | (n & 0x3C000) >> 14)]) + ((n9 = this.p[n6 + (n2 >> 18)]) & 0xFF00FF) * (n10 = r.v[n8]) + ((n11 = this.p[n6 + this.p + (n >> 18)]) & 0xFF00FF) * (n12 = r.i[n8]);
        final int n14;
        final int n13 = (n14 = this.p[n6 + this.p + (n2 >> 18)]) & 0xFF00FF;
        final int n15 = r.l[n8];
        return ((n4 + n13 * n15 & 0xFF00FF00) | ((n5 & 0xFF00) * n7 + (n9 & 0xFF00) * n10 + (n11 & 0xFF00) * n12 + (n14 & 0xFF00) * n15 & 0xFF0000)) >>> 8 | 0xFF000000;
    }
    
    private final void d(final int[] array) {
        final int p = this.p;
        final int n = p << 12;
        final int[] p2 = this.p;
        final int[] d = this.d;
        final int[] a = this.a;
        final int l = this.l;
        int n2 = 0;
        int e = this.e;
        final int n3 = this.e - 1;
        final int a2 = this.a;
        int n4 = 0;
        while (++n4 < this.f) {
            int i = 0;
            while (i < n3) {
                int n5 = d[n2 + i] + l;
                int n6 = d[e + i] + l;
                final int n7 = a[n2 + i];
                final int n8 = a[e + i++];
                int n9 = d[n2 + i] + l;
                int n10 = d[e + i] + l;
                final int n11 = a[n2 + i];
                final int n12 = a[e + i];
                int n13 = 0;
                if ((n5 | n6 | n9 | n10) < 0) {
                    n13 = (((n5 & n6 & n9 & n10) >= 0) ? 1 : 0);
                    n5 += n;
                    n6 += n;
                    n9 += n;
                    n10 += n;
                }
                int n14 = n10 << 6;
                int n15 = n12 << 6;
                final int n16 = n9 - n10 << 3;
                final int n17 = n11 - n12 << 3;
                int n18 = n6 - n10 << 3;
                int n19 = n8 - n12 << 3;
                final int n20 = n5 - n9 - n6 + n10;
                final int n21 = n7 - n11 - n8 + n12;
                int n22 = n4 * a2 + i << 3;
                if (n13 != 0) {
                    int n23 = 8;
                    while (--n23 >= 0) {
                        int n24;
                        n22 = (n24 = n22 - a2);
                        final int n25;
                        n14 = (n25 = n14 + n16);
                        final int n26;
                        n15 = (n26 = n15 + n17);
                        n18 += n20;
                        n19 += n21;
                        final int n27;
                        final int n28;
                        array[--n24] = p2[((n27 = n25 + n18) >> 18) % p + ((n28 = n26 + n19) >> 18) * p];
                        final int n29;
                        final int n30;
                        array[--n24] = p2[((n29 = n27 + n18) >> 18) % p + ((n30 = n28 + n19) >> 18) * p];
                        final int n31;
                        final int n32;
                        array[--n24] = p2[((n31 = n29 + n18) >> 18) % p + ((n32 = n30 + n19) >> 18) * p];
                        final int n33;
                        final int n34;
                        array[--n24] = p2[((n33 = n31 + n18) >> 18) % p + ((n34 = n32 + n19) >> 18) * p];
                        final int n35;
                        final int n36;
                        array[--n24] = p2[((n35 = n33 + n18) >> 18) % p + ((n36 = n34 + n19) >> 18) * p];
                        final int n37;
                        final int n38;
                        array[--n24] = p2[((n37 = n35 + n18) >> 18) % p + ((n38 = n36 + n19) >> 18) * p];
                        final int n39;
                        final int n40;
                        array[--n24] = p2[((n39 = n37 + n18) >> 18) % p + ((n40 = n38 + n19) >> 18) * p];
                        array[--n24] = p2[(n39 + n18 >> 18) % p + (n40 + n19 >> 18) * p];
                    }
                }
                else {
                    int n41 = 8;
                    while (--n41 >= 0) {
                        int n42;
                        n22 = (n42 = n22 - a2);
                        final int n43;
                        n14 = (n43 = n14 + n16);
                        final int n44;
                        n15 = (n44 = n15 + n17);
                        n18 += n20;
                        n19 += n21;
                        final int n45;
                        final int n46;
                        array[--n42] = p2[((n45 = n43 + n18) >> 18) + ((n46 = n44 + n19) >> 18) * p];
                        final int n47;
                        final int n48;
                        array[--n42] = p2[((n47 = n45 + n18) >> 18) + ((n48 = n46 + n19) >> 18) * p];
                        final int n49;
                        final int n50;
                        array[--n42] = p2[((n49 = n47 + n18) >> 18) + ((n50 = n48 + n19) >> 18) * p];
                        final int n51;
                        final int n52;
                        array[--n42] = p2[((n51 = n49 + n18) >> 18) + ((n52 = n50 + n19) >> 18) * p];
                        final int n53;
                        final int n54;
                        array[--n42] = p2[((n53 = n51 + n18) >> 18) + ((n54 = n52 + n19) >> 18) * p];
                        final int n55;
                        final int n56;
                        array[--n42] = p2[((n55 = n53 + n18) >> 18) + ((n56 = n54 + n19) >> 18) * p];
                        final int n57;
                        final int n58;
                        array[--n42] = p2[((n57 = n55 + n18) >> 18) + ((n58 = n56 + n19) >> 18) * p];
                        array[--n42] = p2[(n57 + n18 >> 18) + (n58 + n19 >> 18) * p];
                    }
                }
            }
            e = (n2 = e) + this.e;
        }
    }
    
    private final void p(final int[] array) {
        final int n = this.p << 12;
        final int n2 = this.p << 18;
        final int n3 = this.p - 1 << 12;
        final int[] d = this.d;
        final int[] a = this.a;
        final int l = this.l;
        int n4 = 0;
        int e = this.e;
        final int n5 = this.e - 1;
        final int a2 = this.a;
        int n6 = 0;
        while (++n6 < this.f) {
            int i = 0;
            while (i < n5) {
                int n7 = d[n4 + i] + l;
                int n8 = d[e + i] + l;
                final int n9 = a[n4 + i];
                final int n10 = a[e + i++];
                int n11 = d[n4 + i] + l;
                int n12 = d[e + i] + l;
                final int n13 = a[n4 + i];
                final int n14 = a[e + i];
                boolean b = false;
                if ((n7 | n8 | n11 | n12) < 0) {
                    b = ((n7 & n8 & n11 & n12) >= 0);
                    n7 += n;
                    n8 += n;
                    n11 += n;
                    n12 += n;
                }
                if (!b) {
                    b = ((n7 - n3 & n8 - n3 & n11 - n3 & n12 - n3) >= 0);
                }
                int n15 = n12 << 6;
                int n16 = n14 << 6;
                final int n17 = n11 - n12 << 3;
                final int n18 = n13 - n14 << 3;
                int n19 = n8 - n12 << 3;
                int n20 = n10 - n14 << 3;
                final int n21 = n7 - n11 - n8 + n12;
                final int n22 = n9 - n13 - n10 + n14;
                int n23 = n6 * a2 + i << 3;
                if (b) {
                    int n24 = 8;
                    while (--n24 >= 0) {
                        int n25;
                        n23 = (n25 = n23 - a2);
                        final int n26;
                        n15 = (n26 = n15 + n17);
                        final int n27;
                        n16 = (n27 = n16 + n18);
                        n19 += n21;
                        n20 += n22;
                        final int n28;
                        final int n29;
                        array[--n25] = this.p((n28 = n26 + n19) % n2, (n28 + 262144) % n2, n29 = n27 + n20);
                        final int n30;
                        final int n31;
                        array[--n25] = this.p((n30 = n28 + n19) % n2, (n30 + 262144) % n2, n31 = n29 + n20);
                        final int n32;
                        final int n33;
                        array[--n25] = this.p((n32 = n30 + n19) % n2, (n32 + 262144) % n2, n33 = n31 + n20);
                        final int n34;
                        final int n35;
                        array[--n25] = this.p((n34 = n32 + n19) % n2, (n34 + 262144) % n2, n35 = n33 + n20);
                        final int n36;
                        final int n37;
                        array[--n25] = this.p((n36 = n34 + n19) % n2, (n36 + 262144) % n2, n37 = n35 + n20);
                        final int n38;
                        final int n39;
                        array[--n25] = this.p((n38 = n36 + n19) % n2, (n38 + 262144) % n2, n39 = n37 + n20);
                        final int n40;
                        final int n41;
                        array[--n25] = this.p((n40 = n38 + n19) % n2, (n40 + 262144) % n2, n41 = n39 + n20);
                        final int n42;
                        array[--n25] = this.p((n42 = n40 + n19) % n2, (n42 + 262144) % n2, n41 + n20);
                    }
                }
                else {
                    int n43 = 8;
                    while (--n43 >= 0) {
                        int n44;
                        n23 = (n44 = n23 - a2);
                        final int n45;
                        n15 = (n45 = n15 + n17);
                        final int n46;
                        n16 = (n46 = n16 + n18);
                        n19 += n21;
                        n20 += n22;
                        final int n47;
                        final int n48;
                        array[--n44] = this.p(n47 = n45 + n19, n47 + 262144, n48 = n46 + n20);
                        final int n49;
                        final int n50;
                        array[--n44] = this.p(n49 = n47 + n19, n49 + 262144, n50 = n48 + n20);
                        final int n51;
                        final int n52;
                        array[--n44] = this.p(n51 = n49 + n19, n51 + 262144, n52 = n50 + n20);
                        final int n53;
                        final int n54;
                        array[--n44] = this.p(n53 = n51 + n19, n53 + 262144, n54 = n52 + n20);
                        final int n55;
                        final int n56;
                        array[--n44] = this.p(n55 = n53 + n19, n55 + 262144, n56 = n54 + n20);
                        final int n57;
                        final int n58;
                        array[--n44] = this.p(n57 = n55 + n19, n57 + 262144, n58 = n56 + n20);
                        final int n59;
                        final int n60;
                        array[--n44] = this.p(n59 = n57 + n19, n59 + 262144, n60 = n58 + n20);
                        final int n61 = --n44;
                        final int n62 = n59 + n19;
                        array[n61] = this.p(n62, n62 + 262144, n60 + n20);
                    }
                }
            }
            e = (n4 = e) + this.e;
        }
    }
    
    public final double p(final int[] array, final double n, double n2, int c, final boolean b) {
        final int n3 = -(this.a >> 1);
        int i = -(this.n >> 1);
        final int n4 = i + this.n;
        final int n5 = n3 + this.a >> 3;
        final int p5 = this.p;
        final int d = this.d;
        final int n6 = d - 1 << 12;
        if (c < -i) {
            c = -i;
        }
        final double n7 = Math.atan2(-i, c) * 180 / 3.141592653589793;
        if (n2 - n7 <= -this.v) {
            n2 = n7 - this.v;
        }
        else if (n2 + n7 >= -this.i) {
            n2 = -this.i - n7;
        }
        final int n8 = (int)(Math.cos(n2 * 3.141592653589793 / 180) * 4096);
        final int b2 = (int)(Math.sin(n2 * 3.141592653589793 / 180) * 4096);
        this.l = (int)(n * 1024.0 / 90.0 * p5);
        if (b2 != this.b || c != this.c) {
            this.b = b2;
            this.c = c;
            int n9 = n5;
            while (i <= n4) {
                final int n10 = c * n8 - i * b2 >> 12;
                final int n11 = c * b2 + i * n8 >> 7;
                final int n12 = i * i + c * c;
                final boolean b3 = n11 > 0;
                final int n13 = n10 * n10;
                final int n14 = n11 * n11;
                for (int j = 0; j <= n5; ++j) {
                    final int n15 = j * j << 6;
                    final int n16 = n13 + n15 >> 2;
                    final int n17 = n12 + n15 >> 2;
                    final int n18 = (n15 << 6 < n16) ? v.d[(n15 << 18) / n16] : v.p[(n15 << 10) / n16];
                    final int n19 = (n14 < n17 << 4) ? v.d[(n14 << 8) / n17] : v.p[n14 / n17];
                    this.d[n9 + j] = n18 * p5 >> 2;
                    this.d[n9 - j] = -(n18 * p5 >> 2);
                    int n20 = ((b3 ? n19 : (-n19 - 1)) + this.v * 4096 / 90) * d;
                    if (n20 < 0) {
                        n20 = 0;
                    }
                    if (n20 >= n6) {
                        n20 = n6 - 4096;
                    }
                    this.a[n9 - j] = (this.a[n9 + j] = n20);
                }
                n9 += this.e;
                i += 8;
            }
        }
        if (b) {
            this.p(array);
        }
        else {
            this.d(array);
        }
        return n2;
    }
    
    static {
        r.n = new int[256];
        r.v = new int[256];
        r.i = new int[256];
        r.l = new int[256];
    }
}
