import java.awt.Color;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class n
{
    public static int[] a;
    public j b;
    public Rectangle c;
    public boolean d;
    public f e;
    public u f;
    public int g;
    public int h;
    public int i;
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.c = new Rectangle(this.g + n, this.h + n2, n3, n4).intersection(this.c);
    }
    
    public void a(final j j, int n, int n2, final Rectangle rectangle) {
        n += this.g;
        n2 += this.h;
        final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.c);
        final int n3 = rectangle.x + intersection.x - n;
        final int n4 = rectangle.y + intersection.y - n2;
        j.a();
        final int[] b = this.b.b();
        final int[] d = j.d;
        final byte[] f = j.f;
        final int[] i = j.i();
        if (!this.d || (this.e.a == 255 && this.e.b == 255 && this.e.c == 255 && this.e.d == 255)) {
            if (!j.l && !j.k) {
                for (int k = 0; k < intersection.height; ++k) {
                    int n5 = n3 + (n4 + k) * j.a;
                    int n6 = intersection.x + (intersection.y + k) * this.b.a;
                    for (int l = 0; l < intersection.width; ++l) {
                        b[n6++] = d[f[n5++] & 0xFF];
                    }
                }
            }
            else if (this.b.l || this.b.k) {
                for (int n7 = 0; n7 < intersection.height; ++n7) {
                    int n8 = n3 + (n4 + n7) * j.a;
                    int n9 = intersection.x + (intersection.y + n7) * this.b.a;
                    for (int n10 = 0; n10 < intersection.width; ++n10) {
                        final int n11 = b[n9];
                        final int n12 = d[f[n8++] & 0xFF];
                        final int n13 = n12 >> 24 & 0xFF;
                        if (n13 != 0) {
                            final int n14 = n11 >> 24 & 0xFF;
                            final int n15 = n14 + (255 - n14) * n13 / 255;
                            final int n16 = 255 * n13 / n15;
                            final int n17 = 256 - n16;
                            b[n9++] = (n15 << 24 | (((n11 & 0xFF) * n17 >> 8) + ((n12 & 0xFF) * n16 >> 8) & 0xFF) | (((n11 & 0xFF00) * n17 >> 8) + ((n12 & 0xFF00) * n16 >> 8) & 0xFF00) | (((n11 & 0xFF0000) * n17 >> 8) + ((n12 & 0xFF0000) * n16 >> 8) & 0xFF0000));
                        }
                        else {
                            ++n9;
                        }
                    }
                }
            }
            else {
                final byte[] f2 = j.f();
                for (int n18 = 0; n18 < intersection.height; ++n18) {
                    int n19 = n3 + (n4 + n18) * j.a;
                    int n20 = intersection.x + (intersection.y + n18) * this.b.a;
                    int n22;
                    for (int width = intersection.width; width > 0; width -= n22) {
                        final int n21 = i[f[n19] & 0xFF];
                        n22 = (f2[n19] & 0xFF);
                        if (n22 > width) {
                            n22 = width;
                        }
                        final int n23 = 256 - (n21 >> 24 & 0xFF);
                        if (n23 == 1) {
                            for (int n24 = 0; n24 < n22; ++n24) {
                                b[n20++] = d[f[n19++] & 0xFF];
                            }
                        }
                        else if (n23 == 256) {
                            n20 += n22;
                            n19 += n22;
                        }
                        else {
                            for (int n25 = 0; n25 < n22; ++n25) {
                                final int n26 = i[f[n19++] & 0xFF];
                                final int n27 = 256 - (n26 >> 24 & 0xFF);
                                final int n28 = b[n20];
                                b[n20++] = (0xFF000000 | n26 + (((n28 & 0xFF) * n27 >> 8 & 0xFF) | ((n28 & 0xFF00) * n27 >> 8 & 0xFF00) | ((n28 & 0xFF0000) * n27 >> 8 & 0xFF0000)));
                            }
                        }
                    }
                }
            }
        }
        else {
            final int d2 = this.e.d;
            final int a = this.e.a;
            final int b2 = this.e.b;
            final int c = this.e.c;
            if (this.b.l || this.b.k) {
                for (int n29 = 0; n29 < intersection.height; ++n29) {
                    int n30 = n3 + (n4 + n29) * j.a;
                    int n31 = intersection.x + (intersection.y + n29) * this.b.a;
                    for (int n32 = 0; n32 < intersection.width; ++n32) {
                        final int n33 = b[n31];
                        final int n34 = d[f[n30++] & 0xFF];
                        final int n35 = (n34 >> 24 & 0xFF) * d2 / 255 & 0xFF;
                        if (n35 != 0) {
                            final int n36 = n33 >> 24 & 0xFF;
                            final int n37 = n36 + (255 - n36) * n35 / 255;
                            final int n38 = 255 * n35 / n37;
                            final int n39 = 256 - n38;
                            b[n31++] = (n37 << 24 | (((n33 & 0xFF) * n39 >> 8) + ((n34 & 0xFF) * n38 * c >> 16) & 0xFF) | (((n33 & 0xFF00) * n39 >> 8) + ((n34 & 0xFF00) * n38 * b2 >> 16) & 0xFF00) | (((n33 & 0xFF0000) * n39 >> 8) + (((n34 & 0xFF0000) * n38 >> 8 & 0xFF0000) * a >> 8) & 0xFF0000));
                        }
                        else {
                            ++n31;
                        }
                    }
                }
            }
            else {
                for (int n40 = 0; n40 < intersection.height; ++n40) {
                    int n41 = n3 + (n4 + n40) * j.a;
                    int n42 = intersection.x + (intersection.y + n40) * this.b.a;
                    for (int n43 = 0; n43 < intersection.width; ++n43) {
                        final int n44 = b[n42];
                        final int n45 = d[f[n41++] & 0xFF];
                        final int n46 = (n45 >> 24 & 0xFF) * d2 / 255 & 0xFF;
                        final int n47 = 256 - n46;
                        b[n42++] = (0xFF000000 | (((n44 & 0xFF) * n47 >> 8) + ((n45 & 0xFF) * n46 * c >> 16) & 0xFF) | (((n44 & 0xFF00) * n47 >> 8) + ((n45 & 0xFF00) * n46 * b2 >> 16) & 0xFF00) | (((n44 & 0xFF0000) * n47 >> 8) + (((n45 & 0xFF0000) * n46 >> 8 & 0xFF0000) * a >> 8) & 0xFF0000));
                    }
                }
            }
        }
        this.b.g();
    }
    
    public void a(final String s, final int n, final int n2) {
        final boolean d = this.d;
        this.d = true;
        int n3 = n;
        final int n4 = n2 - this.f.e;
        for (int i = 0; i < s.length(); ++i) {
            final char c = (char)(s.charAt(i) & '\u00ff');
            final int n5 = this.f.b[c];
            final int n6 = this.f.c[c];
            if (n6 > 0 && n5 >= 0) {
                this.b(this.f.a, n3, n4, new Rectangle(n5, 0, n6, this.f.d));
            }
            if (n6 > 0) {
                n3 += n6;
            }
        }
        this.d = d;
    }
    
    public void a(final Color color) {
        this.e = new f(color.getRed(), color.getGreen(), color.getBlue(), 255);
    }
    
    public void b(final j j, int n, int n2, final Rectangle rectangle) {
        if (this.i == 1) {
            if (j.d != null) {
                this.c(j, n, n2, rectangle);
                return;
            }
            this.d(j, n, n2, rectangle);
        }
        else {
            if (j.d != null) {
                this.a(j, n, n2, rectangle);
                return;
            }
            n += this.g;
            n2 += this.h;
            final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.c);
            final int n3 = rectangle.x + intersection.x - n;
            final int n4 = rectangle.y + intersection.y - n2;
            j.a();
            final int[] b = this.b.b();
            final int[] b2 = j.b();
            final int[] d = j.d();
            if (!this.d || (this.e.a == 255 && this.e.b == 255 && this.e.c == 255 && this.e.d == 255)) {
                if (!j.l && !j.k) {
                    for (int i = 0; i < intersection.height; ++i) {
                        int n5 = n3 + (n4 + i) * j.a;
                        int n6 = intersection.x + (intersection.y + i) * this.b.a;
                        for (int k = 0; k < intersection.width; ++k) {
                            b[n6++] = b2[n5++];
                        }
                    }
                }
                else if (this.b.l || this.b.k) {
                    for (int l = 0; l < intersection.height; ++l) {
                        int n7 = n3 + (n4 + l) * j.a;
                        int n8 = intersection.x + (intersection.y + l) * this.b.a;
                        for (int n9 = 0; n9 < intersection.width; ++n9) {
                            final int n10 = b[n8];
                            final int n11 = b2[n7++];
                            final int n12 = n11 >> 24 & 0xFF;
                            if (n12 != 0) {
                                final int n13 = n10 >> 24 & 0xFF;
                                final int n14 = n13 + (255 - n13) * n12 / 255;
                                final int n15 = 255 * n12 / n14;
                                final int n16 = 256 - n15;
                                b[n8++] = (n14 << 24 | (((n10 & 0xFF) * n16 >> 8) + ((n11 & 0xFF) * n15 >> 8) & 0xFF) | (((n10 & 0xFF00) * n16 >> 8) + ((n11 & 0xFF00) * n15 >> 8) & 0xFF00) | (((n10 & 0xFF0000) * n16 >> 8) + ((n11 & 0xFF0000) * n15 >> 8) & 0xFF0000));
                            }
                            else {
                                ++n8;
                            }
                        }
                    }
                }
                else {
                    final byte[] f = j.f();
                    for (int n17 = 0; n17 < intersection.height; ++n17) {
                        int n18 = n3 + (n4 + n17) * j.a;
                        int n19 = intersection.x + (intersection.y + n17) * this.b.a;
                        int n21;
                        for (int width = intersection.width; width > 0; width -= n21) {
                            final int n20 = d[n18];
                            n21 = (f[n18] & 0xFF);
                            if (n21 > width) {
                                n21 = width;
                            }
                            final int n22 = 256 - (n20 >> 24 & 0xFF);
                            if (n22 == 1) {
                                for (int n23 = 0; n23 < n21; ++n23) {
                                    b[n19++] = b2[n18++];
                                }
                            }
                            else if (n22 == 256) {
                                n19 += n21;
                                n18 += n21;
                            }
                            else {
                                for (int n24 = 0; n24 < n21; ++n24) {
                                    final int n25 = d[n18++];
                                    final int n26 = 256 - (n25 >> 24 & 0xFF);
                                    final int n27 = b[n19];
                                    b[n19++] = (0xFF000000 | n25 + (((n27 & 0xFF) * n26 >> 8 & 0xFF) | ((n27 & 0xFF00) * n26 >> 8 & 0xFF00) | ((n27 & 0xFF0000) * n26 >> 8 & 0xFF0000)));
                                }
                            }
                        }
                    }
                }
            }
            else {
                final int d2 = this.e.d;
                final int a = this.e.a;
                final int b3 = this.e.b;
                final int c = this.e.c;
                if (this.b.l || this.b.k) {
                    for (int n28 = 0; n28 < intersection.height; ++n28) {
                        int n29 = n3 + (n4 + n28) * j.a;
                        int n30 = intersection.x + (intersection.y + n28) * this.b.a;
                        for (int n31 = 0; n31 < intersection.width; ++n31) {
                            final int n32 = b[n30];
                            final int n33 = b2[n29++];
                            final int n34 = (n33 >> 24 & 0xFF) * d2 / 255 & 0xFF;
                            if (n34 != 0) {
                                final int n35 = n32 >> 24 & 0xFF;
                                final int n36 = n35 + (255 - n35) * n34 / 255;
                                final int n37 = 255 * n34 / n36;
                                final int n38 = 256 - n37;
                                b[n30++] = (n36 << 24 | (((n32 & 0xFF) * n38 >> 8) + ((n33 & 0xFF) * n37 * c >> 16) & 0xFF) | (((n32 & 0xFF00) * n38 >> 8) + ((n33 & 0xFF00) * n37 * b3 >> 16) & 0xFF00) | (((n32 & 0xFF0000) * n38 >> 8) + (((n33 & 0xFF0000) * n37 >> 8 & 0xFF0000) * a >> 8) & 0xFF0000));
                            }
                            else {
                                ++n30;
                            }
                        }
                    }
                }
                else {
                    for (int n39 = 0; n39 < intersection.height; ++n39) {
                        int n40 = n3 + (n4 + n39) * j.a;
                        int n41 = intersection.x + (intersection.y + n39) * this.b.a;
                        for (int n42 = 0; n42 < intersection.width; ++n42) {
                            final int n43 = b[n41];
                            final int n44 = b2[n40++];
                            final int n45 = (n44 >> 24 & 0xFF) * d2 / 255 & 0xFF;
                            final int n46 = 256 - n45;
                            b[n41++] = (0xFF000000 | (((n43 & 0xFF) * n46 >> 8) + ((n44 & 0xFF) * n45 * c >> 16) & 0xFF) | (((n43 & 0xFF00) * n46 >> 8) + ((n44 & 0xFF00) * n45 * b3 >> 16) & 0xFF00) | (((n43 & 0xFF0000) * n46 >> 8) + (((n44 & 0xFF0000) * n45 >> 8 & 0xFF0000) * a >> 8) & 0xFF0000));
                        }
                    }
                }
            }
            this.b.g();
        }
    }
    
    public void a(final j j, final int n, final int n2) {
        this.b(j, n, n2, new Rectangle(0, 0, j.a, j.b));
    }
    
    public void a(final j j, final Rectangle rectangle, final Rectangle rectangle2) {
        final int n = rectangle.x + this.g;
        final int n2 = rectangle.y + this.h;
        final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.c);
        if (rectangle2.width == rectangle.width) {
            final int n3 = rectangle2.x + intersection.x - n;
            final int[] d = j.d();
            final int[] b = this.b.b();
            final byte[] f = j.f();
            final double n4 = rectangle2.height / rectangle.height;
            double n5 = rectangle2.y + (intersection.y - n2) * n4;
            for (int i = 0; i < intersection.height; ++i) {
                int n6 = n3 + (int)n5 * j.a;
                int n7 = intersection.x + (intersection.y + i) * this.b.a;
                int n9;
                for (int k = intersection.width; k > 0; k -= n9) {
                    final int n8 = d[n6];
                    n9 = (f[n6] & 0xFF);
                    if (n9 > k) {
                        n9 = k;
                    }
                    final int n10 = 256 - (n8 >> 24 & 0xFF);
                    if (n10 == 1) {
                        for (int l = 0; l < n9; ++l) {
                            b[n7++] = d[n6++];
                        }
                    }
                    else if (n10 == 256) {
                        n7 += n9;
                        n6 += n9;
                    }
                    else {
                        for (int n11 = 0; n11 < n9; ++n11) {
                            final int n12 = d[n6++];
                            final int n13 = 256 - (n12 >> 24 & 0xFF);
                            final int n14 = b[n7];
                            b[n7++] = (0xFF000000 | n12 + (((n14 & 0xFF) * n13 >> 8 & 0xFF) | ((n14 & 0xFF00) * n13 >> 8 & 0xFF00) | ((n14 & 0xFF0000) * n13 >> 8 & 0xFF0000)));
                        }
                    }
                }
                n5 += n4;
            }
        }
        else {
            final double n15 = rectangle2.width / rectangle.width;
            final double n16 = rectangle2.height / rectangle.height;
            final double n17 = rectangle2.x + (intersection.x - n) * n15;
            double n18 = rectangle2.y + (intersection.y - n2) * n16;
            final int[] b2 = this.b.b();
            final int[] b3 = j.b();
            j.d();
            for (int n19 = 0; n19 < intersection.height; ++n19) {
                double n20 = n17 + (int)n18 * j.a;
                int n21 = intersection.x + (intersection.y + n19) * this.b.a;
                for (int n22 = 0; n22 < intersection.width; ++n22) {
                    final int n23 = b2[n21];
                    final int n24 = b3[(int)n20];
                    final int n25 = n24 >> 24 & 0xFF;
                    if (n25 != 0) {
                        final int n26 = n23 >> 24 & 0xFF;
                        final int n27 = n26 + (255 - n26) * n25 / 255;
                        final int n28 = 255 * n25 / n27;
                        final int n29 = 256 - n28;
                        b2[n21++] = (n27 << 24 | (((n23 & 0xFF) * n29 >> 8) + ((n24 & 0xFF) * n28 >> 8) & 0xFF) | (((n23 & 0xFF00) * n29 >> 8) + ((n24 & 0xFF00) * n28 >> 8) & 0xFF00) | (((n23 & 0xFF0000) * n29 >> 8) + ((n24 & 0xFF0000) * n28 >> 8) & 0xFF0000));
                    }
                    else {
                        ++n21;
                    }
                    n20 += n15;
                }
                n18 += n16;
            }
        }
        this.b.g();
    }
    
    public void c(final j j, int n, int n2, final Rectangle rectangle) {
        n += this.g;
        n2 += this.h;
        final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.c);
        final int n3 = rectangle.x + intersection.x - n;
        final int n4 = rectangle.y + intersection.y - n2;
        j.a();
        final int[] a = this.a();
        final int[] b = this.b.b();
        final int[] d = j.d;
        final byte[] f = j.f;
        if (!this.d || (this.e.a == 255 && this.e.b == 255 && this.e.c == 255 && this.e.d == 255)) {
            for (int i = 0; i < intersection.height; ++i) {
                int n5 = n3 + (n4 + i) * j.a;
                int n6 = intersection.x + (intersection.y + i) * this.b.a;
                for (int k = 0; k < intersection.width; ++k) {
                    final int n7 = b[n6];
                    final int n8 = d[f[n5++] & 0xFF];
                    this.b.c[n6++] = ((n7 & 0xFF000000) | a[(n7 & 0xFF) + (n8 & 0xFF)] | a[(n7 & 0xFF00) + (n8 & 0xFF00) >> 8] << 8 | a[(n7 & 0xFF0000) + (n8 & 0xFF0000) >> 16] << 16);
                }
            }
        }
        else {
            final int a2 = this.e.a;
            final int b2 = this.e.b;
            final int c = this.e.c;
            for (int l = 0; l < intersection.height; ++l) {
                int n9 = n3 + (n4 + l) * j.a;
                int n10 = intersection.x + (intersection.y + l) * this.b.a;
                for (int n11 = 0; n11 < intersection.width; ++n11) {
                    final int n12 = b[n10];
                    final int n13 = d[f[n9++] & 0xFF];
                    this.b.c[n10++] = ((n12 & 0xFF000000) | a[(n12 & 0xFF) + ((n13 & 0xFF) * a2 >> 8)] | a[((n12 & 0xFF00) >> 8) + ((n13 & 0xFF00) * b2 >> 16)] << 8 | a[((n12 & 0xFF0000) >> 16) + ((n13 & 0xFF0000) * c >> 24 & 0xFF)] << 16);
                }
            }
        }
        this.b.g();
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        final Rectangle intersection = new Rectangle(this.g + n, this.h + n2, n3, n4).intersection(this.c);
        final int a = this.e.a();
        if (this.i == 0) {
            int n5 = a >> 24 & 0xFF;
            if (n5 == 255) {
                for (int i = 0; i < intersection.height; ++i) {
                    int n6 = intersection.x + (intersection.y + i) * this.b.a;
                    for (int j = 0; j < intersection.width; ++j) {
                        this.b.c[n6++] = a;
                    }
                }
            }
            else if (n5 != 0) {
                for (int k = 0; k < intersection.height; ++k) {
                    int n7 = intersection.x + (intersection.y + k) * this.b.a;
                    for (int l = 0; l < intersection.width; ++l) {
                        final int n8 = this.b.c[n7];
                        final int n9 = n8 >> 24 & 0xFF;
                        final int n10 = n9 + (255 - n9) * n5 / 255;
                        n5 = 255 * n5 / n10;
                        final int n11 = 256 - n5;
                        this.b.c[n7++] = (n10 << 24 | (((n8 & 0xFF) * n11 >> 8) + ((a & 0xFF) * n5 >> 8) & 0xFF) | (((n8 & 0xFF00) * n11 >> 8) + ((a & 0xFF00) * n5 >> 8) & 0xFF00) | (((n8 & 0xFF0000) * n11 >> 8) + ((a & 0xFF0000) * n5 >> 8) & 0xFF0000));
                    }
                }
            }
        }
        else {
            final int[] a2 = this.a();
            for (int n12 = 0; n12 < intersection.height; ++n12) {
                int n13 = intersection.x + (intersection.y + n12) * this.b.a;
                for (int n14 = 0; n14 < intersection.width; ++n14) {
                    final int n15 = this.b.c[n13];
                    this.b.c[n13++] = ((n15 & 0xFF000000) | a2[(n15 & 0xFF) + (a & 0xFF)] | a2[(n15 & 0xFF00) + (a & 0xFF00) >> 8] << 8 | a2[(n15 & 0xFF0000) + (a & 0xFF0000) >> 16] << 16);
                }
            }
        }
        this.b.g();
    }
    
    public n(final j b) {
        this.e = new f(0, 0, 0);
        this.b = b;
        this.c = new Rectangle(0, 0, this.b.a, this.b.b);
    }
    
    public n(final n n) {
        this.e = new f(0, 0, 0);
        this.b = n.b;
        this.c = n.c;
        this.d = n.d;
        this.e = n.e;
        this.f = n.f;
        this.g = n.g;
        this.h = n.h;
        this.i = n.i;
    }
    
    public void a(final int n, final int n2) {
        this.g += n;
        this.h += n2;
    }
    
    public void d(final j j, int n, int n2, final Rectangle rectangle) {
        n += this.g;
        n2 += this.h;
        final Rectangle intersection = new Rectangle(n, n2, rectangle.width, rectangle.height).intersection(this.c);
        final int n3 = rectangle.x + intersection.x - n;
        final int n4 = rectangle.y + intersection.y - n2;
        j.a();
        final int[] a = this.a();
        final int[] b = this.b.b();
        final int[] b2 = j.b();
        if (!this.d || (this.e.a == 255 && this.e.b == 255 && this.e.c == 255 && this.e.d == 255)) {
            for (int i = 0; i < intersection.height; ++i) {
                int n5 = n3 + (n4 + i) * j.a;
                int n6 = intersection.x + (intersection.y + i) * this.b.a;
                for (int k = 0; k < intersection.width; ++k) {
                    final int n7 = b[n6];
                    final int n8 = b2[n5++];
                    this.b.c[n6++] = ((n7 & 0xFF000000) | a[(n7 & 0xFF) + (n8 & 0xFF)] | a[(n7 & 0xFF00) + (n8 & 0xFF00) >> 8] << 8 | a[(n7 & 0xFF0000) + (n8 & 0xFF0000) >> 16] << 16);
                }
            }
        }
        else {
            final int a2 = this.e.a;
            final int b3 = this.e.b;
            final int c = this.e.c;
            for (int l = 0; l < intersection.height; ++l) {
                int n9 = n3 + (n4 + l) * j.a;
                int n10 = intersection.x + (intersection.y + l) * this.b.a;
                for (int n11 = 0; n11 < intersection.width; ++n11) {
                    final int n12 = b[n10];
                    final int n13 = b2[n9++];
                    this.b.c[n10++] = ((n12 & 0xFF000000) | a[(n12 & 0xFF) + ((n13 & 0xFF) * a2 >> 8)] | a[((n12 & 0xFF00) >> 8) + ((n13 & 0xFF00) * b3 >> 16)] << 8 | a[((n12 & 0xFF0000) >> 16) + ((n13 & 0xFF0000) * c >> 24 & 0xFF)] << 16);
                }
            }
        }
        this.b.g();
    }
    
    public int[] a() {
        if (n.a == null) {
            n.a = new int[512];
            int n = 0;
            do {
                n.a[n] = n;
            } while (++n < 256);
            int n2 = 256;
            do {
                n.a[n2] = 255;
            } while (++n2 < 512);
        }
        return n.a;
    }
    
    public void c(final int n, final int n2, final int n3, final int n4) {
        this.b(n, n2, n3 + 1, 1);
        this.b(n, n2 + n4, n3 + 1, 1);
        this.b(n, n2 + 1, 1, n4 - 1);
        this.b(n + n3, n2 + 1, 1, n4 - 1);
    }
}
