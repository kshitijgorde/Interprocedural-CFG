// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;

public class p extends bp
{
    public int q;
    public int w;
    public String q;
    public String w;
    public String e;
    public String r;
    public String t;
    public String y;
    public boolean q;
    public int e;
    public int r;
    public boolean w;
    public boolean e;
    public boolean r;
    public String u;
    public String i;
    public String o;
    public int t;
    public int y;
    public int u;
    public int i;
    public int o;
    public int p;
    public int a;
    public long q;
    
    public p(final int n, final String s) {
        super(n, s);
        this.t = 0;
        this.y = 0;
        this.u = 0;
        this.i = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0L;
        this.e = -999;
        this.d = 0;
        this.r = -999;
        this.w = false;
        this.e = false;
        this.r = false;
    }
    
    public final boolean a_() {
        return this.a == 0;
    }
    
    public final boolean b_() {
        return this.a == 1;
    }
    
    public final boolean e() {
        return !dI.q(this.q, 0);
    }
    
    public void q(final long e) {
        this.e = e;
        if (this.q(61) && this.q(23)) {
            this.a(3);
            return;
        }
        if (this.q(61)) {
            this.a(1);
            return;
        }
        if (this.q(23)) {
            this.a(2);
        }
    }
    
    public final void b_(final int n) {
        this.i = (n & 0xFFFFFF);
    }
    
    public final void w(final int n) {
        this.o = (n & 0xFFFFFF);
    }
    
    public static Color[] q(final aO ao) {
        if (ao.w) {
            return new Color[] { Color.red, Color.pink };
        }
        return new Color[] { new Color(ao.d), Color.pink };
    }
    
    public final int q(final bJ bj, final String s) {
        if ("name".equals(s) && bj instanceof p) {
            final p p2 = (p)bj;
            if (super.a.equalsIgnoreCase("admin") && p2.a.equalsIgnoreCase("admin")) {
                return 0;
            }
            if (super.a.equalsIgnoreCase("admin") && !p2.a.equalsIgnoreCase("admin")) {
                return -1;
            }
            if (!super.a.equalsIgnoreCase("admin") && p2.a.equalsIgnoreCase("admin")) {
                return 1;
            }
            if (super.a.equalsIgnoreCase("chatmaster") && p2.a.equalsIgnoreCase("chatmaster")) {
                return 0;
            }
            if (super.a.equalsIgnoreCase("chatmaster") && !p2.a.equalsIgnoreCase("chatmaster")) {
                return -1;
            }
            if (!super.a.equalsIgnoreCase("chatmaster") && p2.a.equalsIgnoreCase("chatmaster")) {
                return 1;
            }
            if (this.p > 0 || p2.p > 0) {
                if (this.p == 0 && p2.p > 0) {
                    return 1;
                }
                if (this.p > 0 && p2.p == 0) {
                    return -1;
                }
                if (this.p - p2.p != 0) {
                    return this.p - p2.p;
                }
            }
            if (this.q(52) && !p2.q(52)) {
                return -1;
            }
            if (!this.q(52) && p2.q(52)) {
                return 1;
            }
            if (this.q(23) && !p2.q(23)) {
                return -1;
            }
            if (!this.q(23) && p2.q(23)) {
                return 1;
            }
            if (this.q(61) && p2.q(61)) {
                if (this.w() > p2.w()) {
                    return -1;
                }
                if (this.w() < p2.w()) {
                    return 1;
                }
            }
            if (this.q(61) && !p2.q(61)) {
                return -1;
            }
            if (!this.q(61) && p2.q(61)) {
                return 1;
            }
            if (!this.q(61) && !p2.q(61) && (this.t > 0 || p2.t > 0)) {
                if (this.t == 0 && p2.t > 0) {
                    return 1;
                }
                if (this.t > 0 && p2.t == 0) {
                    return -1;
                }
                if (this.t - p2.t != 0) {
                    return -(this.t - p2.t);
                }
            }
        }
        return super.q(bj, s);
    }
    
    public final int q(final p p) {
        final int q;
        if ((q = super.q(p)) != 0) {
            return q;
        }
        if (this.q != p.q) {
            return this.q - p.q;
        }
        if (this.w != p.w) {
            return this.w - p.w;
        }
        if (this.q != null && this.q.compareTo(p.q) != 0) {
            return this.q.compareTo(p.q);
        }
        if (this.w != null && this.w.compareTo(p.w) != 0) {
            return this.w.compareTo(p.w);
        }
        if (this.e != null && this.e.compareTo(p.e) != 0) {
            return this.e.compareTo(p.e);
        }
        if (this.r != null && this.r.compareTo(p.r) != 0) {
            return this.r.compareTo(p.r);
        }
        if (this.t != null && this.t.compareTo(p.t) != 0) {
            return this.t.compareTo(p.t);
        }
        if (this.y != null && this.y.compareTo(p.y) != 0) {
            return this.y.compareTo(p.y);
        }
        if (this.q != p.q) {
            return 1;
        }
        if (this.e != p.e) {
            return this.e - p.e;
        }
        if (this.r != p.r) {
            return this.r - p.r;
        }
        if (this.w != p.w) {
            return 1;
        }
        if (this.e != p.e) {
            return 1;
        }
        if (this.r != p.r) {
            return 1;
        }
        if (this.u != null && this.u.compareTo(p.u) != 0) {
            return this.u.compareTo(p.u);
        }
        if (this.i != null && this.i.compareTo(p.i) != 0) {
            return this.i.compareTo(p.i);
        }
        if (this.o != null && this.o.compareTo(p.o) != 0) {
            return this.o.compareTo(p.o);
        }
        if (this.t != p.t) {
            return this.t - p.t;
        }
        if (this.y != p.y) {
            return this.y - p.y;
        }
        if (this.u != p.u) {
            return this.u - p.u;
        }
        if (this.i != p.i) {
            return this.i - p.i;
        }
        if (this.o != p.o) {
            return this.o - p.o;
        }
        if (this.p != p.p) {
            return this.p - p.p;
        }
        if (this.a != p.a) {
            return this.a - p.a;
        }
        if (this.q != p.q) {
            return 1;
        }
        return 0;
    }
}
