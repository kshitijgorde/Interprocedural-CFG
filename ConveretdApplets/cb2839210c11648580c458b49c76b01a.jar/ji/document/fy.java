// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.annotate.dg;
import ji.util.d;
import ji.util.i;
import ji.annotate.gj;
import java.awt.Point;
import java.awt.Color;
import ji.awt.bb;

public class fy
{
    String a;
    String b;
    String c;
    String d;
    String e;
    ad f;
    String g;
    bb h;
    int i;
    Color j;
    Color k;
    Point l;
    gj m;
    
    public fy(final ad f, final String g) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = "";
        this.e = "";
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = -1;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.f = f;
        this.g = g;
    }
    
    public final void a() {
        this.f = null;
    }
    
    public final int b() {
        try {
            if (this.f.g != null) {
                return this.f.g.dj();
            }
            return -1;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public final void c() {
        try {
            if (this.f.g != null) {
                this.f.g.k(false);
                this.f.g.dr();
            }
        }
        catch (Exception ex) {}
    }
    
    public final Point a(final String d, Point w) {
        this.d = d;
        if (w == null) {
            w = this.f.w(d);
        }
        return w;
    }
    
    public final void a(final int n, final Color color, final Point point, final int n2, final String s, final String s2, final String s3, final gj gj) {
        this.a(n, color, point, true, n2, s, s2, s3, gj);
    }
    
    public final void a(final int n, final Color color, final Point point, final boolean b, final int n2, final String s, final String s2, final String s3, final gj gj) {
        this.f.g.o();
        if (this.f.g.dj() > 0) {
            boolean b2 = true;
            if (this.d.equals(this.e)) {
                b2 = false;
            }
            if (b2) {
                this.c();
                this.f.g.o();
                this.f.g.a(gj);
            }
        }
        this.e = this.d;
        if (this.f.g.dj() < 0) {
            if (this.b() != n) {
                int n3 = 1;
                if (ji.util.i.c(174)) {
                    n3 = ((n != 15) ? 1 : 0);
                }
                if (n3 != 0) {
                    if (!ji.util.i.c(173)) {
                        if (((n2 & 0x8) > 0 || (n2 & 0x4) > 0 || this.f.cg()) && ji.util.i.c(111)) {
                            this.f.g.k(true);
                        }
                    }
                    else {
                        boolean c = false;
                        if ((n2 & 0x10) > 0 && this.f.cg()) {
                            c = true;
                        }
                        else if ((n2 & 0x8) > 0 || (n2 & 0x4) > 0) {
                            c = ji.util.i.c(111);
                        }
                        else if (n2 == 0) {
                            c = true;
                        }
                        this.f.g.k(c);
                    }
                }
                this.a(n, null, color, point, b, s, s2, s3, gj);
            }
        }
        else {
            this.c();
        }
    }
    
    public final void a(final int n, final Color color, final Color color2, final Point point, final boolean b, final String s, final String s2, final String s3, final gj gj) {
        if (!this.f.d0()) {
            if (b) {
                this.a(n, color, color2, point, s, s2, s3, gj);
            }
            else {
                this.e();
                (this.h = new bb(this.g, new w9(n, color, color2, point, s, s2, s3, gj))).start();
            }
        }
    }
    
    public final void d() {
        try {
            if (this.i >= 0) {
                this.e();
                this.f.g.a(this.m);
                (this.h = new bb(this.g, new w9(this.i, this.j, this.k, this.l, this.a, this.b, this.c, this.m))).start();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void e() {
        try {
            if (this.h != null) {
                for (int n = 30 * 100; n > 0 && this.h.isAlive(); --n) {
                    ji.util.d.b(10, 5, this.g);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final int i, Color j, Color k, final Point l, final String a, final String b, final String c, final gj gj) {
        boolean a2 = false;
        if (this.f.g != null && !this.f.d0() && this.f.f() && this.f.g.at()) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.l = l;
            this.a = a;
            this.b = b;
            this.c = c;
            if (gj != null) {
                this.m = gj.b();
            }
            this.f.d9(false);
            if (this.f.h4 != null) {
                final Color color = (Color)this.f.h4.d(dg.h(i).toLowerCase());
                if (color != null) {
                    j = color;
                }
            }
            if (this.f.h5 != null) {
                final Color color2 = (Color)this.f.h5.d(dg.h(i).toLowerCase());
                if (color2 != null) {
                    k = color2;
                }
            }
            if (i == 9) {
                this.f.g("annstamp", 2);
                this.f.c(l);
            }
            else {
                this.f.t();
                if (this.f.c9() && this.f.ku == null) {
                    this.f.k();
                }
                a2 = this.f.g.a(i, j, k, null, this.f.b9, this.f.ca, 0, a, b, c, gj);
            }
        }
        if (!a2) {
            this.c();
        }
    }
    
    class w9 implements Runnable
    {
        int a;
        Color b;
        Color c;
        Point d;
        String e;
        String f;
        String g;
        gj h;
        
        public w9(final int a, final Color b, final Color c, final Point d, final String e, final String f, final String g, final gj h) {
            this.a = -1;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
            this.g = g;
            this.h = h;
        }
        
        public void run() {
            try {
                fy.this.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            }
            catch (Exception ex) {}
            finally {
                fy.this.h = null;
            }
        }
    }
}
