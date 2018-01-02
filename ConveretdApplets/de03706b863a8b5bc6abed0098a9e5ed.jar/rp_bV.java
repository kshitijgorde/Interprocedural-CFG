import java.awt.Point;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bV implements Cloneable
{
    private static int r;
    public static int a;
    public static int b;
    private static int s;
    private static int t;
    private static int u;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    private static int v;
    public static int g;
    private static int w;
    private int x;
    public rp_dc a;
    public int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    private rp_dC a;
    private Vector a;
    
    rp_bV(final String s, final rp_dc a, final int h, final int i, final int j, final int k, final int l, final int m) {
        this.a = null;
        this.a = new Vector(1, 1);
        this.x = s.hashCode();
        this.a = a;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
    }
    
    public final Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        }
        catch (CloneNotSupportedException ex) {
            ex.printStackTrace(System.err);
        }
        ((rp_bV)clone).a = new Vector(1, 1);
        return clone;
    }
    
    public final void a(final rp_dC a) {
        this.a = a;
    }
    
    public final boolean a() {
        return this.a != null && this.a.a(2);
    }
    
    public final boolean b() {
        return this.j == this.l;
    }
    
    private boolean a(final rp_bV rp_bV) {
        return this.a == rp_bV.a && this.x == rp_bV.x && rp_bV.h != rp_bV.a && (this.h != rp_bV.a || rp_bV.h == rp_bV.b) && (this.h != rp_bV.r || rp_bV.h == rp_bV.r) && (this.h == rp_bV.r || rp_bV.h != rp_bV.r) && ((this.i == rp_bV.u && rp_bV.i == rp_bV.u) || (this.i == rp_bV.t && rp_bV.i == rp_bV.s) || (this.i == rp_bV.s && rp_bV.i == rp_bV.t) || (this.i == rp_bV.c && (rp_bV.i == rp_bV.d || rp_bV.i == rp_bV.e)) || (this.i == rp_bV.d && rp_bV.i == rp_bV.c) || (this.i == rp_bV.e && rp_bV.i == rp_bV.c));
    }
    
    public final int a(final rp_bV rp_bV, final int n, final int n2, final rp_aY rp_aY) {
        if (!this.a(rp_bV)) {
            return 0;
        }
        if (this.a == rp_dc.b) {
            final int b = rp_aJ.b;
            if (Math.abs(this.o - this.q) < 5 || Math.abs(rp_bV.o - rp_bV.q) < 5) {
                if (Math.abs(this.o - rp_bV.o - n2) > b) {
                    return 0;
                }
                if (Math.max(this.n, this.p) > Math.min(rp_bV.n, rp_bV.p) + b + n && Math.min(this.n, this.p) + b < Math.max(rp_bV.n, rp_bV.p) + n) {
                    rp_aY.g = 0;
                    if (this.o > rp_bV.o + n2) {
                        rp_aY.h = -(rp_bV.o - this.o + b);
                    }
                    else {
                        rp_aY.h = b + this.o - rp_bV.o;
                    }
                    return rp_bV.w;
                }
            }
            if ((Math.abs(this.n - this.p) < 5 || Math.abs(rp_bV.n - rp_bV.p) < 5) && Math.abs(this.n - rp_bV.n - n) <= b && Math.max(this.o, this.q) > Math.min(rp_bV.o, rp_bV.q) + n2 + b && Math.min(this.o, this.q) + b < Math.max(rp_bV.o, rp_bV.q) + n2) {
                rp_aY.h = 0;
                if (this.n > rp_bV.n + n) {
                    rp_aY.g = -(rp_bV.n - this.n + b);
                }
                else {
                    rp_aY.g = b + this.n - rp_bV.n;
                }
                return rp_bV.w;
            }
            return 0;
        }
        int b2 = 0;
        if (this.h == rp_bV.r) {
            int n3 = 0;
            Label_0895: {
                if ((this.a.size() <= 0 || this.a(0) == rp_bV) && (rp_bV.a.size() <= 0 || rp_bV.a(0) == this)) {
                    final Point c = this.c();
                    final Point c2 = rp_bV.c();
                    if (a(c.x, c.y, c2.x + n, c2.y + n2, rp_aJ.b)) {
                        if (a(this.p - this.n + rp_bV.n, this.q - this.o + rp_bV.o, rp_bV.p, rp_bV.q, rp_aJ.c)) {
                            rp_aY.g = this.n - rp_bV.n;
                            rp_aY.h = this.o - rp_bV.o;
                            n3 = rp_bV.v;
                            break Label_0895;
                        }
                        final double n4 = this.p - this.n;
                        final double n5 = this.q - this.o;
                        final double n6 = n4 * n4 + n5 * n5;
                        final double n7 = rp_bV.p - rp_bV.n;
                        final double n8 = rp_bV.q - rp_bV.o;
                        final double n9 = n7 * n7 + n8 * n8;
                        if (Math.abs(Math.sqrt(n6) - Math.sqrt(n9)) < rp_aJ.c) {
                            double acos = Math.acos(n4 / Math.sqrt(n6));
                            if (n5 < 0.0) {
                                acos = -acos;
                            }
                            double acos2 = Math.acos(n7 / Math.sqrt(n9));
                            if (n8 < 0.0) {
                                acos2 = -acos2;
                            }
                            rp_aY.b = acos - acos2;
                            final double n10 = rp_aY.b / 0.017453292519943295;
                            rp_aY.b = n10;
                            rp_aY.a = n10;
                            rp_aY.c = c2.x;
                            rp_aY.d = c2.y;
                            rp_aY.g = c.x - c2.x;
                            rp_aY.h = c.y - c2.y;
                            n3 = rp_bV.v;
                            break Label_0895;
                        }
                    }
                }
                n3 = 0;
            }
            b2 = n3;
        }
        if (this.h == rp_bV.a || this.h == rp_bV.b) {
            b2 = this.b(rp_bV, n, n2, rp_aY);
        }
        if (b2 == 0) {
            this.b(rp_bV, true);
        }
        else {
            this.a(rp_bV, true);
        }
        return b2;
    }
    
    private Point c() {
        return new Point((int)Math.round((this.n + this.p) / 2.0), (int)Math.round((this.o + this.q) / 2.0));
    }
    
    public final Point a() {
        return new Point(this.n, this.o);
    }
    
    public final Point b() {
        return new Point(this.p, this.q);
    }
    
    private int b(final rp_bV rp_bV, final int n, final int n2, final rp_aY rp_aY) {
        final rp_cT rp_cT;
        if (!(rp_cT = new rp_cT(new Point(this.n, this.o), new Point(this.p, this.q))).a()) {
            return 0;
        }
        final Point point = new Point((rp_bV.n + rp_bV.p) / 2 + n, (rp_bV.o + rp_bV.q) / 2 + n2);
        final double a = rp_cT.a(point);
        if (rp_cT.a(point, a) >= rp_aJ.b) {
            return 0;
        }
        final double a2;
        if ((a2 = rp_cT.a(new Point(rp_bV.n + n, rp_bV.o + n2))) < 0.0 || a2 > 1.0) {
            return 0;
        }
        final double a3 = rp_cT.a(new Point(rp_bV.n + n, rp_bV.o + n2), a2);
        final double a4;
        if ((a4 = rp_cT.a(new Point(rp_bV.p + n, rp_bV.q + n2))) < 0.0 || a4 > 1.0) {
            return 0;
        }
        if (Math.abs(a3 - rp_cT.a(new Point(rp_bV.p + n, rp_bV.q + n2), a4)) <= 2.0 && a3 < rp_aJ.b) {
            final int n3 = this.n - (int)(a2 * rp_cT.a) - rp_bV.n;
            final int n4 = this.o - (int)(a2 * rp_cT.b) - rp_bV.o;
            if (rp_aY == null) {
                if (Math.abs(n3) > rp_aJ.d || Math.abs(n4) > rp_aJ.d) {
                    return 0;
                }
            }
            else {
                rp_aY.g = this.n - (int)(a2 * rp_cT.a) - rp_bV.n;
                rp_aY.h = this.o - (int)(a2 * rp_cT.b) - rp_bV.o;
            }
            return rp_bV.g;
        }
        if (rp_aY == null) {
            return 0;
        }
        final double n5 = this.p - this.n;
        final double n6 = this.q - this.o;
        final double n7 = n5 * n5 + n6 * n6;
        final double n8 = rp_bV.p - rp_bV.n;
        final double n9 = rp_bV.q - rp_bV.o;
        final double n10 = n8 * n8 + n9 * n9;
        final double n11 = 0.5 * Math.sqrt(n10) / Math.sqrt(n7);
        if (a < n11 || a > 1.0 - n11) {
            return 0;
        }
        if (rp_aY != null) {
            double acos = Math.acos(n5 / Math.sqrt(n7));
            if (n6 < 0.0) {
                acos = -acos;
            }
            double acos2 = Math.acos(n8 / Math.sqrt(n10));
            if (n9 < 0.0) {
                acos2 = -acos2;
            }
            rp_aY.b = acos - acos2;
            if (rp_aY.b < -1.5707963267948966) {
                rp_aY.b += 3.141592653589793;
            }
            if (rp_aY.b > 1.5707963267948966) {
                rp_aY.b -= 3.141592653589793;
            }
            final double n12 = rp_aY.b / 0.017453292519943295;
            rp_aY.b = n12;
            rp_aY.a = n12;
            rp_aY.c = (int)(this.n + 0.5 + n5 * a);
            rp_aY.d = (int)(this.o + 0.5 + n6 * a);
            rp_aY.g = rp_aY.c - point.x;
            rp_aY.h = rp_aY.d - point.y;
        }
        return rp_bV.g;
    }
    
    public final int a(rp_bV rp_bV) {
        if (!this.a(rp_bV)) {
            return 0;
        }
        if (this.h == rp_bV.r) {
            final rp_bV rp_bV2 = this;
            rp_bV = rp_bV;
            this = rp_bV2;
            if (a(rp_bV2.n, this.o, rp_bV.n, rp_bV.o, rp_aJ.d) && a(this.p - this.n + rp_bV.n, this.q - this.o + rp_bV.o, rp_bV.p, rp_bV.q, rp_aJ.d)) {
                return rp_bV.v;
            }
            return 0;
        }
        else {
            if (this.h == rp_bV.a || this.h == rp_bV.b) {
                final rp_bV rp_bV3 = this;
                rp_bV = rp_bV;
                this = rp_bV3;
                return rp_bV3.b(rp_bV, 0, 0, null);
            }
            return 0;
        }
    }
    
    static int a(final String s) {
        if (s.length() == 0) {
            return 0;
        }
        switch (s.charAt(0)) {
            case 'L': {
                return rp_bV.s;
            }
            case 'R': {
                return rp_bV.t;
            }
            case 'S': {
                return rp_bV.u;
            }
            case 'W': {
                return rp_bV.c;
            }
            case 'D': {
                return rp_bV.d;
            }
            case 'M': {
                return rp_bV.e;
            }
            default: {
                return 0;
            }
        }
    }
    
    static int b(final String s) {
        if (s.length() == 0) {
            return 0;
        }
        switch (s.charAt(0)) {
            case 'F': {
                return rp_bV.r;
            }
            case 'S': {
                return rp_bV.a;
            }
            case 'R': {
                return rp_bV.b;
            }
            default: {
                return 0;
            }
        }
    }
    
    private static boolean a(int n, final int n2, final int n3, final int n4, final int n5) {
        return (n = ((Math.abs(n - n3) < n5 && Math.abs(n2 - n4) < n5) ? 1 : 0)) != 0;
    }
    
    public final int a() {
        return this.a.size();
    }
    
    public final rp_bV a(final int n) {
        if (n < 0 || n >= this.a.size()) {
            return null;
        }
        return this.a.elementAt(n);
    }
    
    public final void a() {
        for (int i = 0; i < this.a.size(); ++i) {
            ((rp_bV)this.a.elementAt(i)).b(this, false);
        }
        this.a.removeAllElements();
    }
    
    private void b(rp_bV rp_bV, boolean b) {
    Label_0000:
        while (true) {
            int i = 0;
            while (i < this.a.size()) {
                if (rp_bV == this.a.elementAt(i)) {
                    this.a.removeElementAt(i);
                    if (b) {
                        final rp_bV rp_bV2 = rp_bV;
                        final rp_bV rp_bV3 = this;
                        b = false;
                        rp_bV = rp_bV3;
                        this = rp_bV2;
                        continue Label_0000;
                    }
                }
                else {
                    ++i;
                }
            }
        }
    }
    
    public final void a(rp_bV rp_bV, boolean b) {
        while (true) {
            for (int i = 0; i < this.a.size(); ++i) {
                if (rp_bV == this.a.elementAt(i)) {
                    return;
                }
            }
            this.a.addElement(rp_bV);
            if (!b) {
                return;
            }
            final rp_bV rp_bV2 = rp_bV;
            final rp_bV rp_bV3 = this;
            b = false;
            rp_bV = rp_bV3;
            this = rp_bV2;
        }
    }
    
    public final String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("[Magnet: ");
        String s;
        if (this.h == rp_bV.r) {
            s = "Fixed";
        }
        else if (this.h == rp_bV.a) {
            s = "Slide";
        }
        else if (this.h == rp_bV.b) {
            s = "Slider";
        }
        else {
            s = "Unknown";
        }
        sb.append("Type=").append(s).append(",");
        String s2;
        if (this.i == rp_bV.d) {
            s2 = "Door";
        }
        else if (this.i == rp_bV.e) {
            s2 = "Mirror";
        }
        else if (this.i == rp_bV.s) {
            s2 = "Left";
        }
        else if (this.i == rp_bV.t) {
            s2 = "Right";
        }
        else if (this.i == rp_bV.u) {
            s2 = "Simple";
        }
        else if (this.i == rp_bV.c) {
            s2 = "Wall";
        }
        else {
            s2 = "Unknown";
        }
        sb.append("MatchTyp=").append(s2).append(",");
        sb.append("PtAX=").append(this.n);
        sb.append(",PtAY=").append(this.o);
        sb.append(",PtBX=").append(this.p);
        sb.append(",PtBY=").append(this.q);
        sb.append("]");
        return sb.toString();
    }
    
    static {
        rp_bV.r = 1;
        rp_bV.a = 2;
        rp_bV.b = 3;
        rp_bV.s = 1;
        rp_bV.t = 2;
        rp_bV.u = 3;
        rp_bV.c = 10;
        rp_bV.d = 11;
        rp_bV.e = 12;
        rp_bV.f = 0;
        rp_bV.v = 1;
        rp_bV.g = 2;
        rp_bV.w = 10;
    }
}
