import java.awt.image.PixelGrabber;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class j
{
    public int a;
    public int b;
    public int[] c;
    public int[] d;
    public int[] e;
    public byte[] f;
    public int[] g;
    public byte[] h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public e m;
    public Image n;
    public PixelGrabber o;
    public Image p;
    public PixelGrabber q;
    public boolean r;
    
    public void a() {
        if (this.i) {
            if (!this.j) {
                this.k = false;
                this.l = false;
                if (this.d != null) {
                    int n = 0;
                    do {
                        final int n2 = this.d[n] >> 24 & 0xFF;
                        if (n2 == 0) {
                            this.k = true;
                        }
                        else {
                            if (n2 == 255) {
                                continue;
                            }
                            this.l = true;
                        }
                    } while (++n < 256);
                }
                else {
                    for (int i = 0; i < this.a * this.b; ++i) {
                        final int n3 = this.c[i] >> 24 & 0xFF;
                        if (n3 == 0) {
                            this.k = true;
                        }
                        else if (n3 != 255) {
                            this.l = true;
                        }
                    }
                }
            }
            this.i = false;
        }
    }
    
    public int[] b() {
        if (this.c == null && this.d != null) {
            this.c = new int[this.f.length];
            for (int i = 0; i < this.f.length; ++i) {
                this.c[i] = this.d[this.f[i] & 0xFF];
            }
            this.f = null;
            this.d = null;
            this.e = null;
        }
        return this.c;
    }
    
    public void a(final e m, final Image n, final Image p3) {
        this.m = m;
        this.n = n;
        this.p = p3;
        this.a = 0;
        this.b = 0;
        this.c = null;
    }
    
    public void a(final int a, final int b) {
        this.a = a;
        this.b = b;
        this.c = new int[this.a * this.b];
        this.k = true;
        this.l = false;
        this.i = false;
    }
    
    public j(final int[] array, final int a, final int b) {
        this.c = array.clone();
        this.a = a;
        this.b = b;
        this.i = true;
        this.a();
    }
    
    public j() {
    }
    
    public void a(final boolean k, final boolean l) {
        this.j = true;
        this.k = k;
        this.l = l;
    }
    
    public int c() {
        this.e();
        return this.b;
    }
    
    public int[] d() {
        if (this.g == null) {
            final int[] b = this.b();
            this.g = new int[this.a * this.b];
            for (int i = 0; i < this.a * this.b; ++i) {
                final int n = b[i];
                final int n2 = n >> 24 & 0xFF;
                this.g[i] = ((n & 0xFF000000) | ((n & 0xFF) * n2 >> 8 & 0xFF) | ((n & 0xFF00) * n2 >> 8 & 0xFF00) | ((n & 0xFF0000) * n2 >> 8 & 0xFF0000));
            }
        }
        return this.g;
    }
    
    public void e() {
        while (!this.j() && !this.r && !this.m.n) {
            try {
                this.m.wait();
            }
            catch (Exception ex) {}
        }
    }
    
    public byte[] f() {
        if (this.h == null) {
            this.h = new byte[this.a * this.b];
            if (this.d != null) {
                final byte[] h = this.h;
                final byte[] f = this.f;
                final int[] d = this.d;
                int n = 0;
                int n2 = 0;
                for (int i = 0; i < this.b; ++i) {
                    int j = 1;
                    int n3 = 1;
                    final int n4 = f[n2++] >> 24 & 0xFF;
                    int n5 = (n4 == 0) ? 0 : ((n4 == 255) ? 1 : 2);
                    while (j < this.a) {
                        ++j;
                        final int n6 = d[f[n2++] & 0xFF] >> 24 & 0xFF;
                        final int n7 = (n6 == 0) ? 0 : ((n6 == 255) ? 1 : 2);
                        if (n7 != n5 || j == this.a) {
                            if (n7 == n5) {
                                ++n3;
                            }
                            for (int k = n3; k > 0; --k) {
                                if (k >= 255) {
                                    h[n++] = -1;
                                }
                                else {
                                    h[n++] = (byte)k;
                                }
                            }
                            if (j == this.a && n7 != n5) {
                                h[n++] = 1;
                            }
                            n5 = n7;
                            n3 = 1;
                        }
                        else {
                            ++n3;
                        }
                    }
                }
            }
            else {
                final byte[] h2 = this.h;
                final int[] b = this.b();
                int n8 = 0;
                int n9 = 0;
                for (int l = 0; l < this.b; ++l) {
                    int n10 = 1;
                    int n11 = 1;
                    final int n12 = b[n9++] >> 24 & 0xFF;
                    int n13 = (n12 == 0) ? 0 : ((n12 == 255) ? 1 : 2);
                    while (n10 < this.a) {
                        ++n10;
                        final int n14 = b[n9++] >> 24 & 0xFF;
                        final int n15 = (n14 == 0) ? 0 : ((n14 == 255) ? 1 : 2);
                        if (n15 != n13 || n10 == this.a) {
                            if (n15 == n13) {
                                ++n11;
                            }
                            for (int n16 = n11; n16 > 0; --n16) {
                                if (n16 >= 255) {
                                    h2[n8++] = -1;
                                }
                                else {
                                    h2[n8++] = (byte)n16;
                                }
                            }
                            if (n10 == this.a && n15 != n13) {
                                h2[n8++] = 1;
                            }
                            n13 = n15;
                            n11 = 1;
                        }
                        else {
                            ++n11;
                        }
                    }
                }
            }
        }
        return this.h;
    }
    
    public void g() {
        this.i = true;
        this.h = null;
        this.g = null;
        this.f = null;
        this.d = null;
        this.e = null;
    }
    
    public int h() {
        this.e();
        return this.a;
    }
    
    public int[] i() {
        if (this.e == null) {
            this.e = new int[256];
            int n = 0;
            do {
                final int n2 = this.d[n];
                final int n3 = n2 >> 24 & 0xFF;
                this.e[n] = ((n2 & 0xFF000000) | ((n2 & 0xFF) * n3 >> 8 & 0xFF) | ((n2 & 0xFF00) * n3 >> 8 & 0xFF00) | ((n2 & 0xFF0000) * n3 >> 8 & 0xFF0000));
            } while (++n < 256);
        }
        return this.e;
    }
    
    public boolean j() {
        return this.c != null || this.d != null;
    }
}
