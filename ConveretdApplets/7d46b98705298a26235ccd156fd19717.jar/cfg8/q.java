// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Rectangle;
import java.awt.Font;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;

class q
{
    final g a;
    private String b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    private Color p;
    private String q;
    private Image r;
    private Image s;
    private boolean t;
    private String u;
    private int v;
    private int w;
    private int x;
    private int y;
    private String z;
    private String A;
    private Vector B;
    private x C;
    private Font D;
    private String E;
    private double F;
    private double G;
    private double H;
    private q I;
    private int J;
    private static String[] K;
    
    q(final g a, final x c) {
        final int a2 = RotationImageFilter.a;
        this.a = a;
        this.b = "";
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 10;
        this.g = 10;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = true;
        this.o = true;
        this.p = null;
        this.q = "";
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = "";
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = "";
        this.A = "";
        this.B = new Vector();
        this.C = null;
        this.D = null;
        this.E = "";
        this.F = 0.0;
        this.G = 0.0;
        this.H = 0.0;
        this.I = null;
        this.J = 0;
        this.C = c;
        this.b = c.d(cfg8.q.K[23]);
        this.c = c.e(cfg8.q.K[25]);
        this.z = c.d(cfg8.q.K[24]);
        this.A = c.d(cfg8.q.K[20]);
        this.n = true;
        this.o = true;
        int n2;
        final int n = n2 = (c.c(cfg8.q.K[21]) ? 1 : 0);
        if (a2 == 0) {
            if (n == 1) {
                this.I = a.f(c.d(cfg8.q.K[21]));
                this.J = c.e(cfg8.q.K[22]);
            }
            n2 = c.j();
        }
        final int n3 = n2;
        int i = 0;
        while (i < n3) {
            this.B.addElement(new r(a, c.c(i)));
            ++i;
            if (a2 != 0) {
                break;
            }
        }
    }
    
    protected int a(final double n) {
        return this.d + this.c(n);
    }
    
    protected int b(final double n) {
        return this.e + this.d(n);
    }
    
    protected int c(final double n) {
        final double n2 = dcmpl(this.G, 0.0);
        if (RotationImageFilter.a == 0) {
            if (n2 == 0) {
                return (int)n;
            }
            final int n3 = (int)(n * this.G);
        }
        return (int)n2;
    }
    
    protected int d(final double n) {
        final double n2 = dcmpl(this.H, 0.0);
        if (RotationImageFilter.a == 0) {
            if (n2 == 0) {
                return (int)n;
            }
            final int n3 = (int)(n * this.H);
        }
        return (int)n2;
    }
    
    protected int e(final double n) {
        final int a = RotationImageFilter.a;
        final q i = this.I;
        if (a == 0 && i == null) {
            return (int)n;
        }
        final int n2 = i.J & 0x1;
        if (a == 0) {
            if (n2 == 0) {
                return this.I.d + (int)n;
            }
            this.I.a(n);
        }
        return n2;
    }
    
    protected int f(final double n) {
        final int a = RotationImageFilter.a;
        final q i = this.I;
        if (a == 0 && i == null) {
            return (int)n;
        }
        final int n2 = i.J & 0x2;
        if (a == 0) {
            if (n2 == 0) {
                return this.I.e + (int)n;
            }
            this.I.b(n);
        }
        return n2;
    }
    
    protected int g(final double n) {
        final int a = RotationImageFilter.a;
        final q i = this.I;
        if (a != 0 || i != null) {
            final int n2 = i.J & 0x4;
            if (a == 0) {
                if (n2 == 0) {
                    return (int)n;
                }
                this.I.c(n);
            }
            return n2;
        }
        return (int)n;
    }
    
    protected int h(final double n) {
        final int a = RotationImageFilter.a;
        final q i = this.I;
        if (a != 0 || i != null) {
            final int n2 = i.J & 0x8;
            if (a == 0) {
                if (n2 == 0) {
                    return (int)n;
                }
                this.I.d(n);
            }
            return n2;
        }
        return (int)n;
    }
    
    protected void a() {
        final int a = RotationImageFilter.a;
        q q = this;
        if (a == 0) {
            if (this.C == null) {
                return;
            }
            this.d = this.e(this.a.r(this.C.d("x")));
            this.e = this.f(this.a.r(this.C.d("y")));
            this.f = this.g(this.a.r(this.C.d("w")));
            this.g = this.h(this.a.r(this.C.d("h")));
            this.h = this.C.e(cfg8.q.K[2]);
            this.i = this.C.e(cfg8.q.K[3]);
            this.j = (int)this.a.r(this.C.d(cfg8.q.K[13]));
            this.k = (int)this.a.r(this.C.d(cfg8.q.K[6]));
            this.l = this.g(this.a.r(this.C.d(cfg8.q.K[4])));
            this.m = this.h(this.a.r(this.C.d(cfg8.q.K[1])));
            q = this;
        }
        final int f = q.f;
        final int n = 0;
        final x c;
        final String s;
        Label_0937: {
            q q3 = null;
            Label_0928: {
                Label_0857: {
                    q q2 = null;
                    Label_0330: {
                        Label_0319: {
                            if (a == 0) {
                                if (f > n) {
                                    q2 = this;
                                    if (a != 0) {
                                        break Label_0330;
                                    }
                                    if (this.C.c(cfg8.q.K[7])) {
                                        break Label_0319;
                                    }
                                }
                                q3 = this;
                                if (a != 0) {
                                    break Label_0928;
                                }
                                final int g = this.g;
                            }
                            if (f <= n) {
                                break Label_0857;
                            }
                            c = this.C;
                            s = cfg8.q.K[12];
                            if (a != 0) {
                                break Label_0937;
                            }
                            if (!c.c(s)) {
                                break Label_0857;
                            }
                        }
                        this.G = 0.0;
                        this.H = 0.0;
                        q2 = this;
                    }
                    final double r = q2.a.r(this.C.d(cfg8.q.K[7]));
                    final double r2 = this.a.r(this.C.d(cfg8.q.K[12]));
                    int n3;
                    final int n2 = n3 = this.f;
                    final int n5;
                    final int n4 = n5 = 0;
                    final int g2;
                    final int n6;
                    Label_0767: {
                        if (a == 0) {
                            Label_0766: {
                                if (n2 > n4) {
                                    g2 = this.g;
                                    n6 = 0;
                                    if (a != 0) {
                                        break Label_0767;
                                    }
                                    if (g2 > n6) {
                                        final boolean c2 = this.C.c(cfg8.q.K[7]);
                                        if (a != 0) {
                                            break Label_0766;
                                        }
                                        if (c2) {
                                            final boolean c3 = this.C.c(cfg8.q.K[12]);
                                            if (a != 0) {
                                                break Label_0766;
                                            }
                                            if (c3) {
                                                this.G = this.f / r;
                                                final int e = this.C.e(cfg8.q.K[5]);
                                                double n9;
                                                double n8;
                                                final int n7 = (int)(n8 = (n9 = e));
                                                Label_0757: {
                                                    if (a == 0) {
                                                        if (n7 == 9) {
                                                            this.H = this.g / r2;
                                                            if (a == 0) {
                                                                break Label_0757;
                                                            }
                                                        }
                                                        n9 = (n8 = dcmpl(this.G * r2, (double)this.g));
                                                    }
                                                    while (true) {
                                                        Label_0731: {
                                                            if (a == 0) {
                                                                if (n8 > 0) {
                                                                    this.G = this.g / r2;
                                                                    this.H = this.G;
                                                                    Label_0633: {
                                                                        Label_0612: {
                                                                            if (a == 0) {
                                                                                switch (e) {
                                                                                    case 0:
                                                                                    case 2:
                                                                                    case 6: {
                                                                                        this.d = this.d + this.f / 2 - (int)(this.G * (r / 2.0));
                                                                                        break;
                                                                                    }
                                                                                    case 3:
                                                                                    case 4:
                                                                                    case 5: {
                                                                                        break Label_0612;
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (a == 0) {
                                                                                break Label_0633;
                                                                            }
                                                                        }
                                                                        this.d = this.d + this.f - (int)(this.G * r);
                                                                    }
                                                                    if (a == 0) {
                                                                        break Label_0757;
                                                                    }
                                                                }
                                                                this.H = this.G;
                                                                if (a != 0) {
                                                                    break Label_0731;
                                                                }
                                                                n9 = e;
                                                            }
                                                            switch (n9) {
                                                                case 0:
                                                                case 4:
                                                                case 8: {
                                                                    this.e = this.e + this.g / 2 - (int)(this.G * (r2 / 2.0));
                                                                    break;
                                                                }
                                                                case 5:
                                                                case 6:
                                                                case 7: {
                                                                    this.e = this.e + this.g - (int)(this.G * r2);
                                                                    break Label_0757;
                                                                }
                                                            }
                                                        }
                                                        if (a != 0) {
                                                            continue;
                                                        }
                                                        break;
                                                    }
                                                }
                                                if (a == 0) {
                                                    break Label_0857;
                                                }
                                            }
                                        }
                                    }
                                }
                                final int f2 = this.f;
                            }
                        }
                    }
                    if (a == 0) {
                        Label_0821: {
                            if (n2 > n4) {
                                final boolean c4 = this.C.c(cfg8.q.K[7]);
                                if (a != 0) {
                                    break Label_0821;
                                }
                                if (c4) {
                                    this.G = this.f / r;
                                    if (a == 0) {
                                        break Label_0857;
                                    }
                                }
                            }
                            q3 = this;
                            if (a != 0) {
                                break Label_0928;
                            }
                            n3 = this.g;
                        }
                    }
                    if (g2 > n6) {
                        final x c5 = this.C;
                        final String s2 = cfg8.q.K[12];
                        if (a != 0) {
                            break Label_0937;
                        }
                        if (c5.c(s2)) {
                            this.H = this.g / r2;
                        }
                    }
                }
                this.o = this.C.a(cfg8.q.K[15], true);
                this.n = this.C.a(cfg8.q.K[14], true);
                this.u = this.a.s(this.C.d(cfg8.q.K[9]));
                this.v = 0;
                this.w = 0;
                q3 = this;
            }
            final x c6 = q3.C;
            final String s3 = cfg8.q.K[16];
        }
        String s4 = c.d(s);
        final int length = s4.length();
        final int n10 = 1;
        Label_1207: {
            q q4 = null;
            Label_1203: {
                if (a == 0) {
                    Label_1133: {
                        if (length > n10) {
                            final char char1;
                            final char c7 = char1 = s4.charAt(0);
                            if (a == 0) {
                                Label_1002: {
                                    switch (c7) {
                                        case 84: {
                                            this.w = 1;
                                            if (a != 0) {
                                                break Label_1002;
                                            }
                                            break;
                                        }
                                        case 67: {
                                            this.w = 2;
                                            break;
                                        }
                                    }
                                }
                                s4.charAt(1);
                            }
                            if (a != 0) {
                                break Label_1133;
                            }
                            Label_1054: {
                                switch (c7) {
                                    case 82: {
                                        this.v = 1;
                                        if (a != 0) {
                                            break Label_1054;
                                        }
                                        break;
                                    }
                                    case 67: {
                                        this.v = 2;
                                        break;
                                    }
                                }
                            }
                        }
                        this.x = this.e(this.a.r(this.C.d(cfg8.q.K[11])));
                        this.y = this.f(this.a.r(this.C.d(cfg8.q.K[17])));
                        q4 = this;
                        if (a != 0) {
                            break Label_1203;
                        }
                        this.C.c(cfg8.q.K[10]);
                    }
                }
                if (length == n10) {
                    q q5 = this;
                    Label_1197: {
                        if (a == 0) {
                            s4 = this.a.s(this.C.d(cfg8.q.K[10]));
                            if (s4.startsWith("0")) {
                                this.p = cfg8.t.b(s4);
                                if (a == 0) {
                                    break Label_1197;
                                }
                            }
                            q5 = this;
                        }
                        q5.p = this.a.j(s4);
                    }
                    if (a == 0) {
                        break Label_1207;
                    }
                }
                q4 = this;
            }
            q4.p = null;
        }
        String q6 = "";
        int n12;
        final int n11 = n12 = (this.t ? 1 : 0);
        if (a == 0) {
            if (n11 == 0) {
                q6 = this.a.s(this.C.d(cfg8.q.K[8]));
            }
            n12 = this.B.size();
        }
        final int n13 = n12;
        int i = 0;
        while (true) {
            while (i < n13) {
                final r r3 = this.B.elementAt(i);
                Label_1793: {
                    if (a == 0) {
                        boolean b2;
                        final boolean b = b2 = r3.c();
                        boolean b4;
                        final boolean b3 = b4 = true;
                        if (a != 0) {
                            q q7 = null;
                            Label_1842: {
                                if (a == 0) {
                                    if (b != b3) {
                                        return;
                                    }
                                    q7 = this;
                                    if (a != 0) {
                                        break Label_1842;
                                    }
                                    b2 = this.q.equals(q6);
                                    b4 = false;
                                }
                                if (b2 != b4) {
                                    return;
                                }
                                this.q = q6;
                                this.r = null;
                                q7 = this;
                            }
                            q7.s = null;
                            return;
                        }
                        if (b == b3 && a == 0) {
                            break Label_1793;
                        }
                    }
                    final w a2 = r3.a();
                    this.d = this.a.a(a2.d("x"), this.d, true);
                    this.e = this.a.a(a2.d("y"), this.e, true);
                    this.f = this.a.a(a2.d("w"), this.f, true);
                    this.g = this.a.a(a2.d("h"), this.g, true);
                    this.h = a2.e(cfg8.q.K[2]);
                    this.i = a2.a(cfg8.q.K[3], this.i);
                    this.j = this.a.a(a2.d(cfg8.q.K[13]), this.j, true);
                    this.k = this.a.a(a2.d(cfg8.q.K[6]), this.k, true);
                    this.l = this.a.a(a2.d(cfg8.q.K[4]), this.l, true);
                    this.m = this.a.a(a2.d(cfg8.q.K[1]), this.m, true);
                    this.o = a2.a(cfg8.q.K[15], this.o);
                    this.n = a2.a(cfg8.q.K[14], this.n);
                    int n16;
                    int length2;
                    int n15;
                    int n14;
                    final boolean b5 = (n14 = (n15 = (length2 = (n16 = (a2.c(cfg8.q.K[9]) ? 1 : 0))))) != 0;
                    int n20;
                    int n19;
                    int n18;
                    int n17;
                    final boolean b6 = (n17 = (n18 = (n19 = (n20 = 1)))) != 0;
                    if (a == 0) {
                        if (b5 == b6) {
                            this.u = this.a.s(a2.d(cfg8.q.K[9]));
                        }
                        final int n21;
                        n14 = (n21 = (n15 = (length2 = (n16 = (a2.c(cfg8.q.K[11]) ? 1 : 0)))));
                        final int n22;
                        n17 = (n22 = (n18 = (n19 = (n20 = 1))));
                    }
                    if (a == 0) {
                        if (b5 == b6) {
                            this.x = this.a.a(a2.d(cfg8.q.K[11]), this.x, true);
                        }
                        n15 = (n14 = (length2 = (n16 = (a2.c(cfg8.q.K[17]) ? 1 : 0))));
                        n18 = (n17 = (n19 = (n20 = 1)));
                    }
                    if (a == 0) {
                        if (n14 == n17) {
                            this.y = this.a.a(a2.d(cfg8.q.K[17]), this.y, true);
                        }
                        length2 = (n15 = (n16 = (this.t ? 1 : 0)));
                        n19 = (n18 = (n20 = 0));
                    }
                    if (a == 0) {
                        if (n15 != n18) {
                            break Label_1793;
                        }
                        n16 = (length2 = a2.b().length());
                        n20 = (n19 = 0);
                    }
                    final String s5;
                    Label_1792: {
                        if (a == 0) {
                            if (length2 <= n19) {
                                break Label_1793;
                            }
                            s5 = q6;
                            if (a != 0) {
                                break Label_1792;
                            }
                            n16 = s5.indexOf(46);
                            n20 = 0;
                        }
                        if (n16 >= n20) {
                            q6 = this.a.s(a2.b());
                            if (a == 0) {
                                break Label_1793;
                            }
                        }
                        String.valueOf(q6).concat(String.valueOf(this.a.s(a2.b())));
                    }
                    q6 = s5;
                }
                ++i;
                if (a != 0) {
                    break;
                }
            }
            final boolean t;
            boolean b2 = t = this.t;
            final Object o;
            boolean b4 = (boolean)(o = 0);
            continue;
        }
    }
    
    protected String b() {
        return this.b;
    }
    
    protected String c() {
        return this.z;
    }
    
    protected int d() {
        return this.c;
    }
    
    protected int e() {
        return this.d;
    }
    
    protected int f() {
        return this.e;
    }
    
    protected int g() {
        return this.f;
    }
    
    protected int h() {
        return this.g;
    }
    
    protected Rectangle i() {
        return this.a.a(this.d, this.e, this.f, this.g);
    }
    
    protected boolean j() {
        return this.o;
    }
    
    protected boolean k() {
        return this.n;
    }
    
    protected String l() {
        return this.q;
    }
    
    protected boolean m() {
        int length;
        final int n = length = this.q.length();
        if (RotationImageFilter.a == 0) {
            if (n < 1) {
                length = (false ? 1 : 0);
            }
            else {
                length = (true ? 1 : 0);
            }
        }
        return length != 0;
    }
    
    protected Image n() {
        final int a = RotationImageFilter.a;
        q q = this;
        if (a == 0) {
            if (this.q.length() < 1) {
                return null;
            }
            q = this;
        }
        final Image r = q.r;
        if (a == 0) {
            if (r == null) {
                final ItemApplet i = cfg8.g.i(this.a);
                final String q2 = this.q;
                Label_0108: {
                    if (a == 0) {
                        Label_0081: {
                            if (!q2.startsWith(cfg8.q.K[18])) {
                                final String q3 = this.q;
                                final String s = cfg8.q.K[19];
                                if (a == 0) {
                                    if (q3.startsWith(s)) {
                                        break Label_0081;
                                    }
                                    String.valueOf(cfg8.g.h(this.a));
                                    String.valueOf(this.q);
                                }
                                q3.concat(s);
                                break Label_0108;
                            }
                        }
                        final String q4 = this.q;
                    }
                }
                this.r = i.a(q2);
                this.s = null;
            }
            final Image r2 = this.r;
        }
        return r;
    }
    
    protected Image o() {
        final int a = RotationImageFilter.a;
        q q = this;
        if (a == 0) {
            if (!this.t()) {
                return this.n();
            }
            q = this;
        }
        final Image s = q.s;
        if (a == 0) {
            if (s == null) {
                this.s = this.a.createImage(new FilteredImageSource(this.n().getSource(), new RotationImageFilter()));
            }
            final Image s2 = this.s;
        }
        return s;
    }
    
    protected Color p() {
        return this.p;
    }
    
    protected int q() {
        return this.h;
    }
    
    protected boolean r() {
        final int a = RotationImageFilter.a;
        final int h = this.h;
        if (a == 0) {
            if (h == 0) {
                final int h2 = this.h;
                if (a == 0) {
                    if (h2 != 3) {
                        final int h3 = this.h;
                        if (a == 0) {
                            if (h3 != 5) {
                                final int h4 = this.h;
                                if (a == 0) {
                                    if (h4 == 6) {}
                                }
                            }
                        }
                    }
                }
            }
        }
        return h != 0;
    }
    
    protected boolean s() {
        final int a = RotationImageFilter.a;
        final int h = this.h;
        if (a == 0) {
            if (h != 2) {
                final int h2 = this.h;
                if (a == 0) {
                    if (h2 != 3) {
                        final int h3 = this.h;
                        if (a == 0) {
                            if (h3 != 5) {
                                final int h4 = this.h;
                                if (a == 0) {
                                    if (h4 == 6) {}
                                }
                            }
                        }
                    }
                }
            }
        }
        return h != 0;
    }
    
    protected boolean t() {
        final int a = RotationImageFilter.a;
        final int h = this.h;
        if (a == 0) {
            if (h != 4) {
                final int h2 = this.h;
                if (a == 0) {
                    if (h2 == 6) {}
                }
            }
        }
        return h != 0;
    }
    
    protected int u() {
        return this.i;
    }
    
    protected int v() {
        return this.j;
    }
    
    protected int w() {
        return this.k;
    }
    
    protected int x() {
        return this.l;
    }
    
    protected int y() {
        return this.m;
    }
    
    protected boolean z() {
        int length;
        final int n = length = this.u.length();
        if (RotationImageFilter.a == 0) {
            if (n > 0) {
                length = (true ? 1 : 0);
            }
            else {
                length = (false ? 1 : 0);
            }
        }
        return length != 0;
    }
    
    protected String A() {
        return this.u;
    }
    
    protected int B() {
        return this.x;
    }
    
    protected int C() {
        return this.y;
    }
    
    protected String D() {
        return this.E;
    }
    
    protected String E() {
        return this.C.d(cfg8.q.K[16]);
    }
    
    protected boolean F() {
        final int a = RotationImageFilter.a;
        boolean length;
        final int n = (length = (this.A.length() != 0)) ? 1 : 0;
        if (a == 0) {
            if (n < 1) {
                return false;
            }
            final boolean o;
            length = (o = this.a.o(this.A));
        }
        if (a == 0) {
            if (n == 0) {
                length = true;
            }
            else {
                length = false;
            }
        }
        return length;
    }
    
    protected void a(final String q) {
        this.q = q;
        this.r = null;
        this.t = true;
        this.s = null;
    }
    
    void a(final Graphics graphics, final Panel panel) {
        final int a = RotationImageFilter.a;
        q q = this;
        if (a == 0) {
            if (!this.j()) {
                return;
            }
            q = this;
        }
        final Rectangle i = q.i();
        final int width = i.width;
        final boolean b = false;
        Label_0046: {
            if (a == 0) {
                if (width != (b ? 1 : 0)) {
                    break Label_0046;
                }
                final int height = i.height;
            }
            if (width == (b ? 1 : 0)) {
                return;
            }
        }
        q q2 = this;
        if (a == 0) {
            if (this.p != null) {
                graphics.setColor(this.p);
                graphics.fillRect(i.x, i.y, i.width, i.height);
            }
            q2 = this;
        }
        final Image o = q2.o();
        final String u;
        Label_1042: {
            int n41 = 0;
            Label_1019: {
                Label_1007: {
                    if (o != null) {
                        final int h = this.h;
                        if (a == 0) {
                            if (h == 0) {
                                final int j = this.i;
                                if (a == 0) {
                                    if (j == 0) {
                                        final int l = this.l;
                                        if (a == 0) {
                                            if (l == 0) {
                                                final int m = this.m;
                                                if (a == 0) {
                                                    if (m == 0) {
                                                        graphics.drawImage(o, i.x, i.y, i.width, i.height, panel);
                                                        if (a == 0) {
                                                            break Label_1007;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        int n = h;
                        q q3 = this;
                        int n3 = 0;
                        int n2 = 0;
                        Label_0215: {
                            if (a == 0) {
                                if (this.t()) {
                                    n2 = (n3 = this.r.getHeight(panel));
                                    break Label_0215;
                                }
                                q3 = this;
                            }
                            n2 = (n3 = q3.r.getWidth(panel));
                        }
                        int k = n3;
                        int n4 = n2;
                        final int j2 = this.j;
                        final boolean b2 = false;
                        int n7 = 0;
                        int n6 = 0;
                        Label_0402: {
                            q q4 = null;
                            Label_0395: {
                                Label_0380: {
                                    if (a == 0) {
                                        Label_0379: {
                                            if (j2 != (b2 ? 1 : 0)) {
                                                final int i2 = this.i;
                                                final boolean b3 = false;
                                                if (a != 0) {
                                                    break Label_0380;
                                                }
                                                if (i2 != (b3 ? 1 : 0)) {
                                                    final int i3 = this.i;
                                                    if (a != 0) {
                                                        break Label_0379;
                                                    }
                                                    Label_0336: {
                                                        switch (i3) {
                                                            case 1:
                                                            case 4:
                                                            case 8: {
                                                                n = 0;
                                                                k = this.j;
                                                                if (a != 0) {
                                                                    break Label_0336;
                                                                }
                                                                break;
                                                            }
                                                            case 2:
                                                            case 3:
                                                            case 6: {
                                                                n = n4 - this.j;
                                                                k = n4;
                                                                if (a != 0) {
                                                                    break Label_0336;
                                                                }
                                                                break;
                                                            }
                                                            case 5:
                                                            case 7:
                                                            case 9: {
                                                                final int n5;
                                                                n = (n5 = n4 / 2) - this.j / 2;
                                                                k = n5 + this.j / 2;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            n4 = 0;
                                            q4 = this;
                                            if (a != 0) {
                                                break Label_0395;
                                            }
                                            this.t();
                                        }
                                    }
                                }
                                if (j2 == (b2 ? 1 : 0)) {
                                    n6 = (n7 = this.r.getWidth(panel));
                                    break Label_0402;
                                }
                                q4 = this;
                            }
                            n6 = (n7 = q4.r.getHeight(panel));
                        }
                        int k2 = n7;
                        final int n8 = n6;
                        int n10;
                        final int n9 = n10 = this.k;
                        int width2;
                        final int n11 = width2 = 0;
                        final int i4;
                        final int n12;
                        Label_0560: {
                            if (a == 0) {
                                Label_0559: {
                                    if (n9 != n11) {
                                        i4 = this.i;
                                        n12 = 0;
                                        if (a != 0) {
                                            break Label_0560;
                                        }
                                        if (i4 != n12) {
                                            final int i5 = this.i;
                                            if (a != 0) {
                                                break Label_0559;
                                            }
                                            Label_0524: {
                                                switch (i5) {
                                                    case 1:
                                                    case 2:
                                                    case 5: {
                                                        n4 = 0;
                                                        k2 = this.k;
                                                        if (a != 0) {
                                                            break Label_0524;
                                                        }
                                                        break;
                                                    }
                                                    case 3:
                                                    case 4:
                                                    case 7: {
                                                        n4 = n8 - this.k;
                                                        k2 = n8;
                                                        if (a != 0) {
                                                            break Label_0524;
                                                        }
                                                        break;
                                                    }
                                                    case 6:
                                                    case 8:
                                                    case 9: {
                                                        final int n13;
                                                        n4 = (n13 = n8 / 2) - this.k / 2;
                                                        k2 = n13 + this.k / 2;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    this.r();
                                }
                            }
                        }
                        int x = 0;
                        Label_0584: {
                            if (a == 0) {
                                if (n9 == n11) {
                                    x = i.x;
                                    break Label_0584;
                                }
                                n10 = i.x;
                                width2 = i.width;
                            }
                            x = i4 + n12;
                        }
                        final int n14 = x;
                        final int s = this.s() ? 1 : 0;
                        final int n15 = 0;
                        int y = 0;
                        Label_0615: {
                            if (a == 0) {
                                if (s == n15) {
                                    y = i.y;
                                    break Label_0615;
                                }
                                final int y2 = i.y;
                                final int height2 = i.height;
                            }
                            y = s + n15;
                        }
                        final int n16 = y;
                        final int r = this.r() ? 1 : 0;
                        final int n17 = 0;
                        int x2 = 0;
                        Label_0646: {
                            if (a == 0) {
                                if (r != n17) {
                                    x2 = i.x;
                                    break Label_0646;
                                }
                                final int x3 = i.x;
                                final int width3 = i.width;
                            }
                            x2 = r + n17;
                        }
                        final int n18 = x2;
                        final int s2 = this.s() ? 1 : 0;
                        final int n19 = 0;
                        int y3 = 0;
                        Label_0677: {
                            if (a == 0) {
                                if (s2 != n19) {
                                    y3 = i.y;
                                    break Label_0677;
                                }
                                final int y4 = i.y;
                                final int height3 = i.height;
                            }
                            y3 = s2 + n19;
                        }
                        final int n20 = y3;
                        q q5 = this;
                        Label_0735: {
                            if (a == 0) {
                                if (this.l == 0) {
                                    q5 = this;
                                    if (a != 0) {
                                        break Label_0735;
                                    }
                                    if (this.m == 0) {
                                        graphics.drawImage(o, n14, n16, n18, n20, n, n4, k, k2, panel);
                                        if (a == 0) {
                                            break Label_1007;
                                        }
                                    }
                                }
                                q5 = this;
                            }
                        }
                        final Rectangle a2 = q5.a.a(this.d, this.e, this.l, this.m);
                        final int l2 = this.l;
                        final int n21 = 0;
                        int n22 = 0;
                        Label_0786: {
                            if (a == 0) {
                                if (l2 == n21) {
                                    n22 = 1;
                                    break Label_0786;
                                }
                                final int width4 = i.width;
                                final int width5 = a2.width;
                            }
                            n22 = l2 / n21;
                        }
                        int n23 = n22;
                        final int m2 = this.m;
                        final int n24 = 0;
                        int n25 = 0;
                        Label_0815: {
                            if (a == 0) {
                                if (m2 == n24) {
                                    n25 = 1;
                                    break Label_0815;
                                }
                                final int height4 = i.height;
                                final int height5 = a2.height;
                            }
                            n25 = m2 / n24;
                        }
                        int n26 = n25;
                        int width6;
                        int n28;
                        final int n27 = n28 = (width6 = n23);
                        int n31;
                        int n30;
                        final int n29 = n30 = (n31 = 0);
                        if (a == 0) {
                            if (n27 < n29) {
                                n23 = 1;
                            }
                            final int n32;
                            n28 = (n32 = (width6 = n26));
                            final int n33;
                            n30 = (n33 = (n31 = 0));
                        }
                        if (a == 0) {
                            if (n27 < n29) {
                                n26 = 1;
                            }
                            width6 = (n28 = n23);
                            n31 = (n30 = 1);
                        }
                        int width7 = 0;
                        Label_0870: {
                            if (a == 0) {
                                if (n28 <= n30) {
                                    width7 = i.width;
                                    break Label_0870;
                                }
                                width6 = i.width;
                                n31 = n23;
                            }
                            width7 = width6 / n31;
                        }
                        final int n34 = width7;
                        final int n35 = n26;
                        final int n36 = 1;
                        int height6 = 0;
                        Label_0897: {
                            if (a == 0) {
                                if (n35 <= n36) {
                                    height6 = i.height;
                                    break Label_0897;
                                }
                                final int height7 = i.height;
                            }
                            height6 = n35 / n36;
                        }
                        final int n37 = height6;
                        int n38 = 0;
                        int n39 = 0;
                    Label_1002_Outer:
                        while (n39 < n26) {
                            int n40 = 0;
                            n41 = 0;
                            if (a == 0) {
                                int n42 = n41;
                                while (true) {
                                    while (n42 < n23) {
                                        graphics.drawImage(o, n14 + n40, n16 + n38, n14 + n40 + n34, n16 + n38 + n37, n, n4, k, k2, panel);
                                        n40 += n34;
                                        ++n42;
                                        if (a == 0) {
                                            if (a != 0) {
                                                break;
                                            }
                                            continue Label_1002_Outer;
                                        }
                                        else {
                                            if (a != 0) {
                                                break Label_1007;
                                            }
                                            continue Label_1002_Outer;
                                        }
                                    }
                                    n38 += n37;
                                    ++n39;
                                    continue;
                                }
                            }
                            break Label_1019;
                        }
                    }
                }
                u = this.u;
                if (a != 0) {
                    break Label_1042;
                }
                u.length();
            }
            if (n41 <= 0) {
                return;
            }
            this.a.s(this.C.d(cfg8.q.K[0]));
        }
        final String e = u;
        q q6 = null;
        Label_1116: {
            Label_1115: {
                if (a == 0) {
                    if (e.equals(this.E)) {
                        q6 = this;
                        if (a != 0) {
                            break Label_1116;
                        }
                        if (this.F == this.a.i()) {
                            break Label_1115;
                        }
                    }
                    this.D = cfg8.g.a(e, this.a.i());
                    this.E = e;
                }
                this.F = this.a.i();
            }
            q6 = this;
        }
        if (q6.D != null) {
            graphics.setFont(this.D);
        }
        final int x4 = i.x;
        final int n43 = i.y + i.height;
        int n44 = x4 + (int)(this.x * cfg8.g.e(this.a));
        int n45 = n43 - (int)(this.y * cfg8.g.d(this.a));
        final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
        int w;
        final int n46 = w = this.w;
        if (a == 0) {
            Label_1247: {
                switch (n46) {
                    case 1: {
                        n45 += fontMetrics.getAscent();
                        if (a != 0) {
                            break Label_1247;
                        }
                        break;
                    }
                    case 2: {
                        n45 += fontMetrics.getDescent() + fontMetrics.getDescent() / 2;
                        break;
                    }
                }
            }
            final int v;
            w = (v = this.v);
        }
        Label_1335: {
            Label_1319: {
                if (a == 0) {
                    switch (n46) {
                        case 1: {
                            w = n44 - fontMetrics.stringWidth(this.u);
                            break;
                        }
                        case 2: {
                            break Label_1319;
                        }
                    }
                }
                n44 = w;
                if (a == 0) {
                    break Label_1335;
                }
            }
            n44 -= fontMetrics.stringWidth(this.u) / 2;
        }
        graphics.drawString(this.u, n44, n45);
    }
    
    static {
        final String[] k = new String[26];
        final int n = 0;
        final char[] charArray = "\rdXL".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'k';
                            break;
                        }
                        case 1: {
                            c2 = '\u000b';
                            break;
                        }
                        case 2: {
                            c2 = '6';
                            break;
                        }
                        case 3: {
                            c2 = '8';
                            break;
                        }
                        default: {
                            c2 = 'z';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        k[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u001fc".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'k';
                            break;
                        }
                        case 1: {
                            c4 = '\u000b';
                            break;
                        }
                        case 2: {
                            c4 = '6';
                            break;
                        }
                        case 3: {
                            c4 = '8';
                            break;
                        }
                        default: {
                            c4 = 'z';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        k[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0002m".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'k';
                            break;
                        }
                        case 1: {
                            c6 = '\u000b';
                            break;
                        }
                        case 2: {
                            c6 = '6';
                            break;
                        }
                        case 3: {
                            c6 = '8';
                            break;
                        }
                        default: {
                            c6 = 'z';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        k[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0002h".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'k';
                            break;
                        }
                        case 1: {
                            c8 = '\u000b';
                            break;
                        }
                        case 2: {
                            c8 = '6';
                            break;
                        }
                        case 3: {
                            c8 = '8';
                            break;
                        }
                        default: {
                            c8 = 'z';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        k[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u001f|".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'k';
                            break;
                        }
                        case 1: {
                            c10 = '\u000b';
                            break;
                        }
                        case 2: {
                            c10 = '6';
                            break;
                        }
                        case 3: {
                            c10 = '8';
                            break;
                        }
                        default: {
                            c10 = 'z';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        k[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\u0018{".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'k';
                            break;
                        }
                        case 1: {
                            c12 = '\u000b';
                            break;
                        }
                        case 2: {
                            c12 = '6';
                            break;
                        }
                        case 3: {
                            c12 = '8';
                            break;
                        }
                        default: {
                            c12 = 'z';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        k[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u0002c".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'k';
                            break;
                        }
                        case 1: {
                            c14 = '\u000b';
                            break;
                        }
                        case 2: {
                            c14 = '6';
                            break;
                        }
                        case 3: {
                            c14 = '8';
                            break;
                        }
                        default: {
                            c14 = 'z';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        k[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u0018s".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'k';
                            break;
                        }
                        case 1: {
                            c16 = '\u000b';
                            break;
                        }
                        case 2: {
                            c16 = '6';
                            break;
                        }
                        case 3: {
                            c16 = '8';
                            break;
                        }
                        default: {
                            c16 = 'z';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        k[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u0002fQ".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'k';
                            break;
                        }
                        case 1: {
                            c18 = '\u000b';
                            break;
                        }
                        case 2: {
                            c18 = '6';
                            break;
                        }
                        case 3: {
                            c18 = '8';
                            break;
                        }
                        default: {
                            c18 = 'z';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        k[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u001fnNL".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'k';
                            break;
                        }
                        case 1: {
                            c20 = '\u000b';
                            break;
                        }
                        case 2: {
                            c20 = '6';
                            break;
                        }
                        case 3: {
                            c20 = '8';
                            break;
                        }
                        default: {
                            c20 = 'z';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        k[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "\u0019lT".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = 'k';
                            break;
                        }
                        case 1: {
                            c22 = '\u000b';
                            break;
                        }
                        case 2: {
                            c22 = '6';
                            break;
                        }
                        case 3: {
                            c22 = '8';
                            break;
                        }
                        default: {
                            c22 = 'z';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        k[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "\u001fs".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = 'k';
                            break;
                        }
                        case 1: {
                            c24 = '\u000b';
                            break;
                        }
                        case 2: {
                            c24 = '6';
                            break;
                        }
                        case 3: {
                            c24 = '8';
                            break;
                        }
                        default: {
                            c24 = 'z';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        k[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "\u0018r".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1518: {
                if (n50 > 1) {
                    break Label_1518;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = 'k';
                            break;
                        }
                        case 1: {
                            c26 = '\u000b';
                            break;
                        }
                        case 2: {
                            c26 = '6';
                            break;
                        }
                        case 3: {
                            c26 = '8';
                            break;
                        }
                        default: {
                            c26 = 'z';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        k[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "\u0002|".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1638: {
                if (n54 > 1) {
                    break Label_1638;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = 'k';
                            break;
                        }
                        case 1: {
                            c28 = '\u000b';
                            break;
                        }
                        case 2: {
                            c28 = '6';
                            break;
                        }
                        case 3: {
                            c28 = '8';
                            break;
                        }
                        default: {
                            c28 = 'z';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        k[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "\u000eeWZ\u0016\u000e".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1758: {
                if (n58 > 1) {
                    break Label_1758;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = 'k';
                            break;
                        }
                        case 1: {
                            c30 = '\u000b';
                            break;
                        }
                        case 2: {
                            c30 = '6';
                            break;
                        }
                        case 3: {
                            c30 = '8';
                            break;
                        }
                        default: {
                            c30 = 'z';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        k[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "\u001dbEQ\u0018\u0007n".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1878: {
                if (n62 > 1) {
                    break Label_1878;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = 'k';
                            break;
                        }
                        case 1: {
                            c32 = '\u000b';
                            break;
                        }
                        case 2: {
                            c32 = '6';
                            break;
                        }
                        case 3: {
                            c32 = '8';
                            break;
                        }
                        default: {
                            c32 = 'z';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        k[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "\u001fjZQ\u001d\u0005".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1998: {
                if (n66 > 1) {
                    break Label_1998;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = 'k';
                            break;
                        }
                        case 1: {
                            c34 = '\u000b';
                            break;
                        }
                        case 2: {
                            c34 = '6';
                            break;
                        }
                        case 3: {
                            c34 = '8';
                            break;
                        }
                        default: {
                            c34 = 'z';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        k[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "\u001fr".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2118: {
                if (n70 > 1) {
                    break Label_2118;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = 'k';
                            break;
                        }
                        case 1: {
                            c36 = '\u000b';
                            break;
                        }
                        case 2: {
                            c36 = '6';
                            break;
                        }
                        case 3: {
                            c36 = '8';
                            break;
                        }
                        default: {
                            c36 = 'z';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        k[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = "\u0003\u007fBH@D$".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2238: {
                if (n74 > 1) {
                    break Label_2238;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = 'k';
                            break;
                        }
                        case 1: {
                            c38 = '\u000b';
                            break;
                        }
                        case 2: {
                            c38 = '6';
                            break;
                        }
                        case 3: {
                            c38 = '8';
                            break;
                        }
                        default: {
                            c38 = 'z';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        k[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = "\u0003\u007fBH\tQ$\u0019".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2358: {
                if (n78 > 1) {
                    break Label_2358;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = 'k';
                            break;
                        }
                        case 1: {
                            c40 = '\u000b';
                            break;
                        }
                        case 2: {
                            c40 = '6';
                            break;
                        }
                        case 3: {
                            c40 = '8';
                            break;
                        }
                        default: {
                            c40 = 'z';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 > n80) {
                continue;
            }
            break;
        }
        k[n77] = new String(charArray20).intern();
        final int n81 = 20;
        final char[] charArray21 = "\rbZL\u001f\u0019".toCharArray();
        int length21;
        int n83;
        final int n82 = n83 = (length21 = charArray21.length);
        int n84 = 0;
        while (true) {
            Label_2478: {
                if (n82 > 1) {
                    break Label_2478;
                }
                length21 = (n83 = n84);
                do {
                    final char c41 = charArray21[n83];
                    char c42 = '\0';
                    switch (n84 % 5) {
                        case 0: {
                            c42 = 'k';
                            break;
                        }
                        case 1: {
                            c42 = '\u000b';
                            break;
                        }
                        case 2: {
                            c42 = '6';
                            break;
                        }
                        case 3: {
                            c42 = '8';
                            break;
                        }
                        default: {
                            c42 = 'z';
                            break;
                        }
                    }
                    charArray21[length21] = (char)(c41 ^ c42);
                    ++n84;
                } while (n82 == 0);
            }
            if (n82 > n84) {
                continue;
            }
            break;
        }
        k[n81] = new String(charArray21).intern();
        final int n85 = 21;
        final char[] charArray22 = "\u0006jFJ\u001d\u0005".toCharArray();
        int length22;
        int n87;
        final int n86 = n87 = (length22 = charArray22.length);
        int n88 = 0;
        while (true) {
            Label_2598: {
                if (n86 > 1) {
                    break Label_2598;
                }
                length22 = (n87 = n88);
                do {
                    final char c43 = charArray22[n87];
                    char c44 = '\0';
                    switch (n88 % 5) {
                        case 0: {
                            c44 = 'k';
                            break;
                        }
                        case 1: {
                            c44 = '\u000b';
                            break;
                        }
                        case 2: {
                            c44 = '6';
                            break;
                        }
                        case 3: {
                            c44 = '8';
                            break;
                        }
                        default: {
                            c44 = 'z';
                            break;
                        }
                    }
                    charArray22[length22] = (char)(c43 ^ c44);
                    ++n88;
                } while (n86 == 0);
            }
            if (n86 > n88) {
                continue;
            }
            break;
        }
        k[n85] = new String(charArray22).intern();
        final int n89 = 22;
        final char[] charArray23 = "\u0018hWT\u001f".toCharArray();
        int length23;
        int n91;
        final int n90 = n91 = (length23 = charArray23.length);
        int n92 = 0;
        while (true) {
            Label_2718: {
                if (n90 > 1) {
                    break Label_2718;
                }
                length23 = (n91 = n92);
                do {
                    final char c45 = charArray23[n91];
                    char c46 = '\0';
                    switch (n92 % 5) {
                        case 0: {
                            c46 = 'k';
                            break;
                        }
                        case 1: {
                            c46 = '\u000b';
                            break;
                        }
                        case 2: {
                            c46 = '6';
                            break;
                        }
                        case 3: {
                            c46 = '8';
                            break;
                        }
                        default: {
                            c46 = 'z';
                            break;
                        }
                    }
                    charArray23[length23] = (char)(c45 ^ c46);
                    ++n92;
                } while (n90 == 0);
            }
            if (n90 > n92) {
                continue;
            }
            break;
        }
        k[n89] = new String(charArray23).intern();
        final int n93 = 23;
        final char[] charArray24 = "\u0005j[]".toCharArray();
        int length24;
        int n95;
        final int n94 = n95 = (length24 = charArray24.length);
        int n96 = 0;
        while (true) {
            Label_2838: {
                if (n94 > 1) {
                    break Label_2838;
                }
                length24 = (n95 = n96);
                do {
                    final char c47 = charArray24[n95];
                    char c48 = '\0';
                    switch (n96 % 5) {
                        case 0: {
                            c48 = 'k';
                            break;
                        }
                        case 1: {
                            c48 = '\u000b';
                            break;
                        }
                        case 2: {
                            c48 = '6';
                            break;
                        }
                        case 3: {
                            c48 = '8';
                            break;
                        }
                        default: {
                            c48 = 'z';
                            break;
                        }
                    }
                    charArray24[length24] = (char)(c47 ^ c48);
                    ++n96;
                } while (n94 == 0);
            }
            if (n94 > n96) {
                continue;
            }
            break;
        }
        k[n93] = new String(charArray24).intern();
        final int n97 = 24;
        final char[] charArray25 = "\u0007jO]\b".toCharArray();
        int length25;
        int n99;
        final int n98 = n99 = (length25 = charArray25.length);
        int n100 = 0;
        while (true) {
            Label_2958: {
                if (n98 > 1) {
                    break Label_2958;
                }
                length25 = (n99 = n100);
                do {
                    final char c49 = charArray25[n99];
                    char c50 = '\0';
                    switch (n100 % 5) {
                        case 0: {
                            c50 = 'k';
                            break;
                        }
                        case 1: {
                            c50 = '\u000b';
                            break;
                        }
                        case 2: {
                            c50 = '6';
                            break;
                        }
                        case 3: {
                            c50 = '8';
                            break;
                        }
                        default: {
                            c50 = 'z';
                            break;
                        }
                    }
                    charArray25[length25] = (char)(c49 ^ c50);
                    ++n100;
                } while (n98 == 0);
            }
            if (n98 > n100) {
                continue;
            }
            break;
        }
        k[n97] = new String(charArray25).intern();
        final int n101 = 25;
        final char[] charArray26 = "\u0005~[".toCharArray();
        int length26;
        int n103;
        final int n102 = n103 = (length26 = charArray26.length);
        int n104 = 0;
        while (true) {
            Label_3078: {
                if (n102 > 1) {
                    break Label_3078;
                }
                length26 = (n103 = n104);
                do {
                    final char c51 = charArray26[n103];
                    char c52 = '\0';
                    switch (n104 % 5) {
                        case 0: {
                            c52 = 'k';
                            break;
                        }
                        case 1: {
                            c52 = '\u000b';
                            break;
                        }
                        case 2: {
                            c52 = '6';
                            break;
                        }
                        case 3: {
                            c52 = '8';
                            break;
                        }
                        default: {
                            c52 = 'z';
                            break;
                        }
                    }
                    charArray26[length26] = (char)(c51 ^ c52);
                    ++n104;
                } while (n102 == 0);
            }
            if (n102 <= n104) {
                k[n101] = new String(charArray26).intern();
                q.K = k;
                return;
            }
            continue;
        }
    }
}
