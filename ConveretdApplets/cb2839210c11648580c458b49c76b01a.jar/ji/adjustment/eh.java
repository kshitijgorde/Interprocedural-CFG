// 
// Decompiled by Procyon v0.5.30
// 

package ji.adjustment;

import ji.util.d;
import ji.image.fl;
import ji.io.h;
import ji.util.i;
import ji.document.ad;
import ji.awt.c;
import ji.image.ds;
import java.awt.Point;

public class eh
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private Point k;
    private double l;
    private Point m;
    private double n;
    private double o;
    private double p;
    private ds q;
    private c r;
    private ad s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    public static final Point x;
    
    public eh() {
        this.e = 5;
        this.f = 5;
        this.g = 0;
        this.h = 0;
        this.i = 2;
        this.j = 2;
        this.k = eh.x;
        this.l = 1.0;
        this.m = eh.x;
        this.n = 1.0;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = false;
        this.v = true;
        this.w = true;
    }
    
    public void a(final ad s) {
        this.s = s;
    }
    
    public ad a() {
        return this.s;
    }
    
    public void a(final boolean t) {
        this.t = t;
    }
    
    public void b(final boolean u) {
        this.u = u;
    }
    
    public boolean b() {
        return this.u;
    }
    
    public boolean c() {
        return this.t;
    }
    
    public void a(final int n, final String s, final double n2, final double n3) {
        int e = this.e;
        final String value = String.valueOf(String.valueOf(new StringBuffer("background position adjusted from:").append(this.a).append(",").append(this.b)));
        switch (n) {
            case 0: {
                if (this.h == 0) {
                    e = (int)(n3 / 100 * this.e);
                }
                this.a += e;
                break;
            }
            case 1: {
                if (this.h == 0) {
                    e = (int)(n3 / 100 * this.e);
                }
                this.a -= e;
                break;
            }
            case 2: {
                if (this.h == 0) {
                    e = (int)(n2 / 100 * this.e);
                }
                this.b += e;
                break;
            }
            case 3: {
                if (this.h == 0) {
                    e = (int)(n2 / 100 * this.e);
                }
                this.b -= e;
                break;
            }
        }
        this.u = true;
        if (ji.util.i.c(61)) {
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(" to:").append(this.a).append(",").append(this.b))));
        }
    }
    
    public static void d() {
        fl.a("ImageAdjust");
    }
    
    public void b(final int n, final String s, final double n2, final double n3) {
        int f = this.f;
        final String value = String.valueOf(String.valueOf(new StringBuffer("foreground position adjusted from:").append(this.c).append(",").append(this.d)));
        switch (n) {
            case 0: {
                if (this.g == 0) {
                    f = (int)(n3 / 100 * this.f);
                }
                this.c += f;
                break;
            }
            case 1: {
                if (this.g == 0) {
                    f = (int)(n3 / 100 * this.f);
                }
                this.c -= f;
                break;
            }
            case 2: {
                if (this.g == 0) {
                    f = (int)(n2 / 100 * this.f);
                }
                this.d += f;
                break;
            }
            case 3: {
                if (this.g == 0) {
                    f = (int)(n2 / 100 * this.f);
                }
                this.d -= f;
                break;
            }
        }
        this.t = true;
        if (ji.util.i.c(61)) {
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(" to:").append(this.c).append(",").append(this.d))));
        }
    }
    
    public void a(final int n, final String s) {
        final String concat = "background rotated from:".concat(String.valueOf(String.valueOf(this.p)));
        if (n == 1) {
            if (this.i == 3) {
                this.p += this.c(this.l);
            }
            else {
                this.p += this.l;
            }
        }
        else {
            if (this.i == 3) {
                this.p -= (int)this.c(this.l);
            }
            else {
                this.p -= this.l;
            }
            if (ji.util.i.c(61)) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append(" to:").append(this.p))));
            }
        }
        this.u = true;
    }
    
    public void b(final int n, final String s) {
        final String concat = "background rotated from:".concat(String.valueOf(String.valueOf(this.o)));
        if (n == 1) {
            if (this.j == 3) {
                this.o += this.c(this.n);
            }
            else {
                this.o += this.n;
            }
        }
        else if (this.j == 3) {
            this.o -= this.c(this.n);
        }
        else {
            this.o -= this.n;
        }
        this.t = true;
        if (ji.util.i.c(61)) {
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append(" to:").append(this.o))));
        }
    }
    
    private double c(final double n) {
        return 180.0 * n / 3.141592653589793;
    }
    
    public void a(final int a, final int b, final double p9, final Point k, final double l, final int h, final int e, final int i, final boolean v) {
        if (i.c(61)) {
            h.d("jiDIBMergeAdjustments", String.valueOf(String.valueOf(new StringBuffer("Setting background properties: ").append(a).append(",").append(b).append(",").append(p9).append(",").append(k).append(",").append(l).append(",").append(h).append(",").append(e).append(",").append(i))));
        }
        if (a != this.a || b != this.b || p9 != this.p) {
            this.u = true;
        }
        this.a = a;
        this.b = b;
        this.p = p9;
        this.k = k;
        this.l = l;
        this.h = h;
        this.e = e;
        this.i = i;
        this.v = v;
    }
    
    public void b(final int c, final int d, final double o, final Point m, final double n, final int g, final int f, final int j, final boolean w) {
        if (ji.util.i.c(61)) {
            ji.io.h.d("jiDIBMergeAdjustments", String.valueOf(String.valueOf(new StringBuffer("Setting foreground properties: ").append(c).append(",").append(d).append(",").append(o).append(",").append(m).append(",").append(n).append(",").append(g).append(",").append(f).append(",").append(j))));
        }
        if (c != this.c || d != this.d || o != this.o) {
            this.t = true;
        }
        this.c = c;
        this.d = d;
        this.o = o;
        this.m = m;
        this.n = n;
        this.g = g;
        this.f = f;
        this.j = j;
        this.w = w;
    }
    
    public boolean e() {
        return this.v;
    }
    
    public boolean f() {
        return this.w;
    }
    
    public int g() {
        return this.i;
    }
    
    public void a(final int i) {
        this.i = i;
    }
    
    public int h() {
        return this.j;
    }
    
    public void b(final int j) {
        this.j = j;
    }
    
    public int i() {
        return this.a;
    }
    
    public int j() {
        return this.b;
    }
    
    public int k() {
        return this.c;
    }
    
    public int l() {
        return this.d;
    }
    
    public int m() {
        return this.e;
    }
    
    public int n() {
        return this.f;
    }
    
    public void c(final int e) {
        this.e = e;
    }
    
    public void d(final int f) {
        this.f = f;
    }
    
    public int o() {
        return this.h;
    }
    
    public int p() {
        return this.g;
    }
    
    public static String c(final int n, final String s) {
        switch (n) {
            case 0: {
                return d.b(1039, s);
            }
            case 1: {
                return d.b(1042, s);
            }
            case 2: {
                return d.b(1040, s);
            }
            case 3: {
                return d.b(1041, s);
            }
            default: {
                return d.b(1039, s);
            }
        }
    }
    
    public static String[] a(final String s) {
        return new String[] { c(0, s), c(1, s) };
    }
    
    public static String[] b(final String s) {
        return new String[] { c(2, s), c(3, s) };
    }
    
    public void e(final int h) {
        this.h = h;
    }
    
    public void f(final int g) {
        this.g = g;
    }
    
    public void a(final String s, final String s2) {
        this.h = c(s, s2);
    }
    
    public void b(final String s, final String s2) {
        this.g = c(s, s2);
    }
    
    public Point q() {
        return this.k;
    }
    
    public Point r() {
        return this.m;
    }
    
    public void a(final Point k, final boolean v) {
        this.k = k;
        this.v = v;
    }
    
    public void b(final Point m, final boolean w) {
        this.m = m;
        this.w = w;
    }
    
    public double s() {
        return this.l;
    }
    
    public double t() {
        return this.n;
    }
    
    public void a(final double l) {
        this.l = l;
    }
    
    public void b(final double n) {
        this.n = n;
    }
    
    public double u() {
        return this.p;
    }
    
    public double v() {
        return this.o;
    }
    
    public static int c(final String s, final String s2) {
        if (s.equals(d.b(1039, s2))) {
            return 0;
        }
        if (s.equals(d.b(1042, s2))) {
            return 1;
        }
        if (s.equals(d.b(1040, s2))) {
            return 2;
        }
        if (s.equals(d.b(1041, s2))) {
            return 3;
        }
        return 0;
    }
    
    public void a(final al al) {
        if (this.r == null) {
            this.r = new c("DIBmergeAdjustmentsListeners", 1);
        }
        if (!this.r.a(al)) {
            this.r.c(al);
        }
    }
    
    public boolean b(final al al) {
        boolean b = false;
        if (this.r != null) {
            b = this.r.b(al);
        }
        return b;
    }
    
    public void a(final fj fj) {
        if (this.r != null) {
            for (int i = 0; i < this.r.b(); ++i) {
                ((al)this.r.b(i)).a(fj);
            }
        }
    }
    
    public void w() {
        this.k = null;
        this.m = null;
        if (this.r != null) {
            this.r.c();
        }
        this.r = null;
    }
    
    static {
        x = new Point(0, 0);
    }
}
