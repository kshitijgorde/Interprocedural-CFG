// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;

public class cz extends bZ
{
    public int q;
    public int w;
    public String q;
    public String w;
    public String e;
    public String r;
    public String y;
    public String u;
    public boolean w;
    public int e;
    public int o;
    public boolean e;
    public boolean r;
    public boolean t;
    public String i;
    public String o;
    public String p;
    public int p;
    public int a;
    public int s;
    public int d;
    public int f;
    public int g;
    public int h;
    public int j;
    
    public cz(final int n, final String s) {
        super(n, s);
        this.p = 0;
        this.a = 0;
        this.s = 0;
        this.d = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.e = -999;
        this.t = 0;
        this.o = -999;
        this.e = false;
        this.r = false;
        this.t = false;
    }
    
    public final boolean a_() {
        return this.q(61);
    }
    
    public final boolean b_() {
        return this.j == 2;
    }
    
    public final boolean e() {
        return this.j == 0;
    }
    
    public final boolean r() {
        return this.j == 1;
    }
    
    public final boolean t() {
        return !this.q(65);
    }
    
    public void q(final long n) {
        super.q(n);
        if (this.q(61) && this.q(23)) {
            this.u(3);
            return;
        }
        if (this.q(61)) {
            this.u(1);
            return;
        }
        if (this.q(23)) {
            this.u(2);
        }
    }
    
    public final int w() {
        return this.d;
    }
    
    public final void b_(final int n) {
        this.d = (n & 0xFFFFFF);
    }
    
    public final int e() {
        return this.f;
    }
    
    public final void w(final int n) {
        this.f = (n & 0xFFFFFF);
    }
    
    public final int i() {
        return this.j;
    }
    
    public static Color[] q(final dj dj) {
        if (dj.e) {
            return new Color[] { Color.red, Color.pink };
        }
        return new Color[] { new Color(dj.t), Color.pink };
    }
    
    public final int q(final aF af, final String s) {
        if ("name".equals(s) && af instanceof cz) {
            final cz cz = (cz)af;
            if (this.getName().equalsIgnoreCase("admin") && cz.getName().equalsIgnoreCase("admin")) {
                return 0;
            }
            if (this.getName().equalsIgnoreCase("admin") && !cz.getName().equalsIgnoreCase("admin")) {
                return -1;
            }
            if (!this.getName().equalsIgnoreCase("admin") && cz.getName().equalsIgnoreCase("admin")) {
                return 1;
            }
            if (this.getName().equalsIgnoreCase("chatmaster") && cz.getName().equalsIgnoreCase("chatmaster")) {
                return 0;
            }
            if (this.getName().equalsIgnoreCase("chatmaster") && !cz.getName().equalsIgnoreCase("chatmaster")) {
                return -1;
            }
            if (!this.getName().equalsIgnoreCase("chatmaster") && cz.getName().equalsIgnoreCase("chatmaster")) {
                return 1;
            }
            if (this.g > 0 || cz.g > 0) {
                if (this.g == 0 && cz.g > 0) {
                    return 1;
                }
                if (this.g > 0 && cz.g == 0) {
                    return -1;
                }
                if (this.g - cz.g != 0) {
                    return this.g - cz.g;
                }
            }
            if (this.q(52) && !cz.q(52)) {
                return -1;
            }
            if (!this.q(52) && cz.q(52)) {
                return 1;
            }
            if (this.q(23) && !cz.q(23)) {
                return -1;
            }
            if (!this.q(23) && cz.q(23)) {
                return 1;
            }
            if (this.q(61) && cz.q(61)) {
                if (this.q() > cz.q()) {
                    return -1;
                }
                if (this.q() < cz.q()) {
                    return 1;
                }
            }
            if (this.q(61) && !cz.q(61)) {
                return -1;
            }
            if (!this.q(61) && cz.q(61)) {
                return 1;
            }
            if (this.q(82) && !cz.q(82)) {
                return -1;
            }
            if (!this.q(82) && cz.q(82)) {
                return 1;
            }
            if (!this.q(61) && !cz.q(61) && (this.h > 0 || cz.h > 0)) {
                if (this.h == 0 && cz.h > 0) {
                    return 1;
                }
                if (this.h > 0 && cz.h == 0) {
                    return -1;
                }
                if (this.h - cz.h != 0) {
                    return this.h - cz.h;
                }
            }
        }
        return super.q(af, s);
    }
    
    public final int q(final cz cz) {
        final int q;
        if ((q = super.q(cz)) != 0) {
            return q;
        }
        if (this.q != cz.q) {
            return this.q - cz.q;
        }
        if (this.w != cz.w) {
            return this.w - cz.w;
        }
        if (this.q != null && this.q.compareTo(cz.q) != 0) {
            return this.q.compareTo(cz.q);
        }
        if (this.w != null && this.w.compareTo(cz.w) != 0) {
            return this.w.compareTo(cz.w);
        }
        if (this.e != null && this.e.compareTo(cz.e) != 0) {
            return this.e.compareTo(cz.e);
        }
        if (this.r != null && this.r.compareTo(cz.r) != 0) {
            return this.r.compareTo(cz.r);
        }
        if (this.y != null && this.y.compareTo(cz.y) != 0) {
            return this.y.compareTo(cz.y);
        }
        if (this.u != null && this.u.compareTo(cz.u) != 0) {
            return this.u.compareTo(cz.u);
        }
        if (this.w != cz.w) {
            return 1;
        }
        if (this.e != cz.e) {
            return this.e - cz.e;
        }
        if (this.o != cz.o) {
            return this.o - cz.o;
        }
        if (this.e != cz.e) {
            return 1;
        }
        if (this.r != cz.r) {
            return 1;
        }
        if (this.t != cz.t) {
            return 1;
        }
        if (this.i != null && this.i.compareTo(cz.i) != 0) {
            return this.i.compareTo(cz.i);
        }
        if (this.o != null && this.o.compareTo(cz.o) != 0) {
            return this.o.compareTo(cz.o);
        }
        if (this.p != null && this.p.compareTo(cz.p) != 0) {
            return this.p.compareTo(cz.p);
        }
        if (this.p != cz.p) {
            return this.p - cz.p;
        }
        if (this.a != cz.a) {
            return this.a - cz.a;
        }
        if (this.s != cz.s) {
            return this.s - cz.s;
        }
        if (this.d != cz.d) {
            return this.d - cz.d;
        }
        if (this.f != cz.f) {
            return this.f - cz.f;
        }
        if (this.g != cz.g) {
            return this.g - cz.g;
        }
        if (this.h != cz.h) {
            return this.h - cz.h;
        }
        if (this.j != cz.j) {
            return this.j - cz.j;
        }
        return 0;
    }
}
