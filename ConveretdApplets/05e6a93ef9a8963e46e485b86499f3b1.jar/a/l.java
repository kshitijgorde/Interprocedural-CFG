// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;

public class l extends aK
{
    public int q;
    public int w;
    public String q;
    public String w;
    public String e;
    public String r;
    public String t;
    public boolean q;
    public int e;
    public int r;
    public boolean w;
    public boolean e;
    public boolean r;
    public String y;
    public String u;
    public String i;
    public int t;
    public int y;
    public int u;
    public int i;
    public int o;
    public int p;
    public long q;
    
    public l(final int n, final String s) {
        super(n, s);
        this.t = 0;
        this.y = 0;
        this.u = 0;
        this.i = 0;
        this.o = 0;
        this.q = 0L;
        this.e = -999;
        this.s = 0;
        this.r = -999;
        this.w = false;
        this.e = false;
        this.r = false;
    }
    
    public final boolean q() {
        return !cp.q(this.q, 0);
    }
    
    public void q(final long w) {
        this.w = w;
        if (this.q(61) && this.q(23)) {
            this.o(3);
            return;
        }
        if (this.q(61)) {
            this.o(1);
            return;
        }
        if (this.q(23)) {
            this.o(2);
        }
    }
    
    public static Color[] q(final as as) {
        if (as.w) {
            return new Color[] { Color.red, Color.pink };
        }
        return new Color[] { new Color(as.s), Color.pink };
    }
    
    public final int q(final aX ax, final String s) {
        if ("name".equals(s) && ax instanceof l) {
            final l l = (l)ax;
            if (super.o.equalsIgnoreCase("admin") && l.o.equalsIgnoreCase("admin")) {
                return 0;
            }
            if (super.o.equalsIgnoreCase("admin") && !l.o.equalsIgnoreCase("admin")) {
                return -1;
            }
            if (!super.o.equalsIgnoreCase("admin") && l.o.equalsIgnoreCase("admin")) {
                return 1;
            }
            if (super.o.equalsIgnoreCase("chatmaster") && l.o.equalsIgnoreCase("chatmaster")) {
                return 0;
            }
            if (super.o.equalsIgnoreCase("chatmaster") && !l.o.equalsIgnoreCase("chatmaster")) {
                return -1;
            }
            if (!super.o.equalsIgnoreCase("chatmaster") && l.o.equalsIgnoreCase("chatmaster")) {
                return 1;
            }
            if (this.o > 0 || l.o > 0) {
                if (this.o == 0 && l.o > 0) {
                    return 1;
                }
                if (this.o > 0 && l.o == 0) {
                    return -1;
                }
                if (this.o - l.o != 0) {
                    return this.o - l.o;
                }
            }
            if (this.q(52) && !l.q(52)) {
                return -1;
            }
            if (!this.q(52) && l.q(52)) {
                return 1;
            }
            if (this.q(23) && !l.q(23)) {
                return -1;
            }
            if (!this.q(23) && l.q(23)) {
                return 1;
            }
            if (this.q(61) && l.q(61)) {
                if (this.q() > l.q()) {
                    return -1;
                }
                if (this.q() < l.q()) {
                    return 1;
                }
            }
            if (this.q(61) && !l.q(61)) {
                return -1;
            }
            if (!this.q(61) && l.q(61)) {
                return 1;
            }
            if (!this.q(61) && !l.q(61) && (this.t > 0 || l.t > 0)) {
                if (this.t == 0 && l.t > 0) {
                    return 1;
                }
                if (this.t > 0 && l.t == 0) {
                    return -1;
                }
                if (this.t - l.t != 0) {
                    return -(this.t - l.t);
                }
            }
        }
        return super.q(ax, s);
    }
}
