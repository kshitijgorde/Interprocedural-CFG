// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.FontMetrics;

public final class fl extends u
{
    private static int a;
    private int b;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private fd a;
    private en[] a;
    private String[] a;
    private String[] b;
    private int[] a;
    private int y;
    private int z;
    private int A;
    private FontMetrics a;
    private String a;
    private Polygon[] a;
    private int B;
    private aa[] a;
    private int[] b;
    private int C;
    private en[] b;
    private ak a;
    
    fl(final fd a, final boolean b, final int b2, final ak a2) {
        this.a = new String[] { "", "", "", "", "" };
        this.b = new String[] { "", "", "", "", "" };
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = b2;
        this.a = a;
        this.a = a2;
        this.a = new int[b2];
        this.a = new Polygon[b2];
        this.a = new aa[b2];
        this.a = new en[b2];
        this.b = new int[b2];
        this.b = new en[b2];
        this.b = (b ? 124 : 160) * ((b2 == 5) ? 3 : 2) / 2;
        this.t = (fl.a - 54) / 2;
        this.u = ((b2 != 5) ? ((this.b - 54) / 2) : ((this.b << 1) / 3 - 27));
        this.v = this.b / 4 - 27;
        this.w = this.b - 72 - 5;
        this.x = fl.a - 72 - 5;
        this.a[0] = al.a(this.t + 4, this.b - 5 - 36 - 1, 46, 36, 2);
        this.a[1] = al.a(6, this.u + 4, 46, 36, 3);
        if (b2 != 5) {
            this.a[2] = al.a(this.t + 4, 6, 46, 36, 0);
        }
        else {
            this.a[2] = al.a(6, this.v + 4, 46, 36, 3);
            if (b2 > 3) {
                this.a[3] = al.a(fl.a - 5 - 36 - 1, this.v + 4, 46, 36, 1);
            }
        }
        this.a[b2 - 1] = al.a(fl.a - 5 - 36 - 1, this.u + 4, 46, 36, 1);
        this.a[0] = new aa(this.t, this.w);
        this.a[1] = new aa(5, this.u);
        if (b2 != 5) {
            this.a[2] = new aa(this.t, 5);
        }
        else {
            this.a[2] = new aa(5, this.v);
            if (b2 > 3) {
                this.a[3] = new aa(this.x, this.v);
            }
        }
        this.a[b2 - 1] = new aa(this.x, this.u);
    }
    
    final void a() {
        this.C = 0;
        for (int i = 0; i < this.B; ++i) {
            if (this.b[i] > 0) {
                final int[] b = this.b;
                final int n = i;
                --b[n];
                if (this.b[i] == 0) {
                    this.h();
                }
                if (this.b[i] > this.C) {
                    this.C = this.b[i];
                }
            }
        }
    }
    
    public final int g() {
        return this.C;
    }
    
    public final void b(final int n, final int c) {
        if (c > this.C) {
            this.C = c;
        }
        this.b[n] = c;
        this.b[n] = this.a[n];
    }
    
    public final void a(final int n, final String s) {
        this.a[n] = s;
        this.h();
    }
    
    private void d(final int n, final int n2) {
        if (n2 == 0) {
            this.b[n] = "";
            return;
        }
        this.b[n] = this.a.a(1716519102) + n2;
    }
    
    public final void a(final int y) {
        this.y = y;
        this.h();
    }
    
    public final void a(final String a) {
        this.a = a;
        this.h();
    }
    
    public final void c(final int n, int n2) {
        this.a[n] = n2;
        if (this.A != -1) {
            n2 = this.a[n] + this.a[(n + 2) % 4];
            if (n % 2 == this.A % 2) {
                this.d(this.A, n2);
            }
            else {
                this.d((this.A + 3) % 4, n2);
            }
        }
        else {
            this.d(n, this.a[n]);
        }
        this.h();
    }
    
    public final void a(final int n, final en en) {
        this.a[n] = en;
        this.h();
    }
    
    public final void b(final int z) {
        this.z = z;
        this.h();
    }
    
    public final void b() {
        super.b();
        this.a = this.a(y.u.a);
    }
    
    public final void a(final ei ei) {
        ei.a(y.u.a);
        aq.a(ei, this);
        ei.a(aq.a);
        ei.b(5, 5, fl.a - 10, this.b - 10);
        for (int i = 0; i < this.B; ++i) {
            final int n = i;
            int n2;
            int n3;
            if ((n % 2 == 0 && this.B % 2 != 1) || n == 0) {
                n2 = 54;
                n3 = 72;
            }
            else {
                n3 = 54;
                n2 = 72;
            }
            final aa aa = this.a[n];
            ei.a(aq.a);
            ei.b(aa.a, aa.b, n2, n3);
            en en2;
            final en en = (this.b[n] > 0) ? (en2 = this.b[n]) : ((this.C > 0) ? (en2 = null) : (en2 = this.a[n]));
            final en en3 = en2;
            if (en != null) {
                this.a.a(en3, ei, aa.a, aa.b, false, (this.B % 2 == 1) ? (n != 0) : (n % 2 == 1));
            }
            else if (this.C == 0) {
                if (this.z == n) {
                    ei.a(Color.yellow);
                    ei.c(aa.a, aa.b, n2 - 1, n3 - 1);
                }
                if (n == this.y || this.z == n) {
                    ei.a(Color.cyan.darker());
                    final Polygon polygon = this.a[n];
                    final int a = ei.a;
                    final int b = ei.b;
                    final int n4 = a;
                    final Polygon polygon2 = polygon;
                    for (int j = 0; j < polygon2.npoints; ++j) {
                        final int[] xpoints = polygon2.xpoints;
                        final int n5 = j;
                        xpoints[n5] += n4;
                        final int[] ypoints = polygon2.ypoints;
                        final int n6 = j;
                        ypoints[n6] += b;
                    }
                    ei.a.fillPolygon(polygon2);
                    for (int k = 0; k < polygon2.npoints; ++k) {
                        final int[] xpoints2 = polygon2.xpoints;
                        final int n7 = k;
                        xpoints2[n7] -= n4;
                        final int[] ypoints2 = polygon2.ypoints;
                        final int n8 = k;
                        ypoints2[n8] -= b;
                    }
                    ei.a(Color.black);
                    final Polygon polygon3 = this.a[n];
                    for (int l = 0; l < polygon3.npoints; ++l) {
                        final int[] xpoints3 = polygon3.xpoints;
                        final int n9 = l;
                        xpoints3[n9] += ei.a;
                        final int[] ypoints3 = polygon3.ypoints;
                        final int n10 = l;
                        ypoints3[n10] += ei.b;
                    }
                    ei.a.drawPolygon(polygon3);
                    for (int n11 = 0; n11 < polygon3.npoints; ++n11) {
                        final int[] xpoints4 = polygon3.xpoints;
                        final int n12 = n11;
                        xpoints4[n12] -= ei.a;
                        final int[] ypoints4 = polygon3.ypoints;
                        final int n13 = n11;
                        ypoints4[n13] -= ei.b;
                    }
                    final int n14 = (this.a[n].xpoints[2] + this.a[n].xpoints[4]) / 2;
                    final int n15 = (this.a[n].ypoints[2] + this.a[n].ypoints[5]) / 2;
                    if (this.a != null && n == this.y) {
                        ei.a(this.a, n14 - this.a.stringWidth(this.a) / 2, n15 + this.a.getHeight() / 2);
                    }
                }
            }
        }
        ei.a(Color.black);
        for (int n16 = 0; n16 < this.B; ++n16) {
            int n17 = 0;
            int n18 = 0;
            if (n16 == 0) {
                n17 = this.t + 54 + 1;
                n18 = this.b - 2 - 5;
            }
            else if (n16 == 1) {
                n17 = 6;
                n18 = this.u + 54 + this.a.getHeight();
            }
            if (n16 == 2 && this.B != 5) {
                n17 = this.t - this.a.stringWidth(this.a[2]) - 1;
                n18 = this.a.getHeight() + 5;
            }
            if (this.B == 5) {
                if (n16 == 2) {
                    n17 = 6;
                    n18 = this.v + 54 + this.a.getHeight();
                }
                else if (n16 == 3) {
                    n17 = fl.a - this.a.stringWidth(this.a[3]) - 1 - 5;
                    n18 = this.v - 1;
                }
            }
            if (n16 == this.B - 1) {
                n17 = fl.a - this.a.stringWidth(this.a[this.B - 1]) - 1 - 5;
                n18 = this.u - 1;
            }
            ei.a(this.a[n16], n17, n18);
            if (n16 == 0) {
                n17 = this.t - this.a.stringWidth(this.b[0]) - 1;
                n18 = this.b - 2 - 5;
            }
            else if (n16 == 1) {
                n17 = 6;
                n18 = this.u - 1;
            }
            if (n16 == 2 && this.B != 5) {
                n17 = this.t + 54 + 1;
                n18 = this.a.getHeight() + 5;
            }
            if (this.B == 5) {
                if (n16 == 2) {
                    n17 = 6;
                    n18 = this.v - 1;
                }
                else if (n16 == 3) {
                    n17 = fl.a - this.a.stringWidth(this.b[3]) - 1 - 5;
                    n18 = this.v + 54 + this.a.getHeight();
                }
            }
            if (n16 == this.B - 1) {
                n17 = fl.a - this.a.stringWidth(this.b[this.B - 1]) - 1 - 5;
                n18 = this.u + 54 + this.a.getHeight();
            }
            ei.a(this.b[n16], n17, n18);
        }
    }
    
    public final int a() {
        return fl.a;
    }
    
    public final int b() {
        return this.b;
    }
    
    static {
        fl.a = s.a(13);
    }
}
