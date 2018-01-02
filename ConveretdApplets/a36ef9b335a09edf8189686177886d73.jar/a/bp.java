// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;

public class bp extends ba
{
    public int t;
    public int y;
    public String q;
    public String w;
    public String r;
    public String t;
    public String y;
    public boolean q;
    public int u;
    public int i;
    public boolean w;
    public boolean e;
    public boolean r;
    public String u;
    public String i;
    public String o;
    public int o;
    public int p;
    public int a;
    public int s;
    public int d;
    public int f;
    public int g;
    public int h;
    
    public bp(final int n, final String s) {
        super(n, s);
        this.o = 0;
        this.p = 0;
        this.a = 0;
        this.s = 0;
        this.d = 0;
        this.f = 0;
        this.g = 0;
        this.u = -999;
        this.w = 0;
        this.i = -999;
        this.w = false;
        this.e = false;
        this.r = false;
    }
    
    public final boolean a_() {
        return this.q(61);
    }
    
    public final boolean b_() {
        return this.h == 2;
    }
    
    public final boolean e() {
        return !this.q(65);
    }
    
    public void q(final long n) {
        super.q(n);
        if (this.q(61) && this.q(23)) {
            this.t(3);
            return;
        }
        if (this.q(61)) {
            this.t(1);
            return;
        }
        if (this.q(23)) {
            this.t(2);
        }
    }
    
    public final int y() {
        return this.s;
    }
    
    public final void y(final int n) {
        this.s = (n & 0xFFFFFF);
    }
    
    public final int u() {
        return this.d;
    }
    
    public final void u(final int n) {
        this.d = (n & 0xFFFFFF);
    }
    
    public final int i() {
        return this.h;
    }
    
    public static Color[] q(final bV bv) {
        if (bv.w) {
            return new Color[] { Color.red, Color.pink };
        }
        return new Color[] { new Color(bv.w), Color.pink };
    }
    
    public final int q(final aq aq, final String s) {
        if ("name".equals(s) && aq instanceof bp) {
            final bp bp = (bp)aq;
            if (this.getName().equalsIgnoreCase("admin") && bp.getName().equalsIgnoreCase("admin")) {
                return 0;
            }
            if (this.getName().equalsIgnoreCase("admin") && !bp.getName().equalsIgnoreCase("admin")) {
                return -1;
            }
            if (!this.getName().equalsIgnoreCase("admin") && bp.getName().equalsIgnoreCase("admin")) {
                return 1;
            }
            if (this.getName().equalsIgnoreCase("chatmaster") && bp.getName().equalsIgnoreCase("chatmaster")) {
                return 0;
            }
            if (this.getName().equalsIgnoreCase("chatmaster") && !bp.getName().equalsIgnoreCase("chatmaster")) {
                return -1;
            }
            if (!this.getName().equalsIgnoreCase("chatmaster") && bp.getName().equalsIgnoreCase("chatmaster")) {
                return 1;
            }
            if (this.f > 0 || bp.f > 0) {
                if (this.f == 0 && bp.f > 0) {
                    return 1;
                }
                if (this.f > 0 && bp.f == 0) {
                    return -1;
                }
                if (this.f - bp.f != 0) {
                    return this.f - bp.f;
                }
            }
            if (this.q(52) && !bp.q(52)) {
                return -1;
            }
            if (!this.q(52) && bp.q(52)) {
                return 1;
            }
            if (this.q(23) && !bp.q(23)) {
                return -1;
            }
            if (!this.q(23) && bp.q(23)) {
                return 1;
            }
            if (this.q(61) && bp.q(61)) {
                if (this.q() > bp.q()) {
                    return -1;
                }
                if (this.q() < bp.q()) {
                    return 1;
                }
            }
            if (this.q(61) && !bp.q(61)) {
                return -1;
            }
            if (!this.q(61) && bp.q(61)) {
                return 1;
            }
            if (this.q(82) && !bp.q(82)) {
                return -1;
            }
            if (!this.q(82) && bp.q(82)) {
                return 1;
            }
            if (!this.q(61) && !bp.q(61) && (this.g > 0 || bp.g > 0)) {
                if (this.g == 0 && bp.g > 0) {
                    return 1;
                }
                if (this.g > 0 && bp.g == 0) {
                    return -1;
                }
                if (this.g - bp.g != 0) {
                    return this.g - bp.g;
                }
            }
        }
        return super.q(aq, s);
    }
}
