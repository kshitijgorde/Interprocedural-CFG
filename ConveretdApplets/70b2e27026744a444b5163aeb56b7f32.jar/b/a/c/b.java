// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

import b.a.d.c;
import b.a.a.e;

public class b implements a, Comparable
{
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected double g;
    protected int h;
    protected int i;
    protected String j;
    protected Object k;
    
    public b() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = -1;
        this.g = 0.0;
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.k = null;
    }
    
    public b(final int a, final int b, final int c, final int d, final int e, final int f, final double g) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = -1;
        this.g = 0.0;
        this.h = 0;
        this.i = 0;
        this.j = null;
        this.k = null;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
    public b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final double n7, final q q) {
        this(n, n2, n3, n4, n5, n6, n7);
        if (q != null) {
            this.h = q.e();
            final int b = q.b(n, n2, n3);
            if (b < 1440) {
                if (q.a(n, n2, n3, this.d, n5, false)) {
                    this.i = 1440 - b;
                    this.e += this.i;
                    if (this.e >= 60) {
                        this.e -= 60;
                        ++this.d;
                    }
                }
                else {
                    this.i = 0;
                }
            }
            else if (b > 1440) {
                if (!q.a(n, n2, n3, this.d, n5, true)) {
                    this.i = 0;
                    this.e -= b - 1440;
                    if (this.e < 0) {
                        this.e += 60;
                        --this.d;
                    }
                }
                else {
                    this.i = b - 1440;
                }
            }
            else if (q.a(n, n2, n3, 12, 0, false)) {
                this.i = q.f(n, n2);
            }
            else {
                this.i = 0;
            }
        }
    }
    
    public double a() {
        return b.a.c.k.a(this.a, this.b, this.c, this.d, this.e - this.h - this.i, 0.0);
    }
    
    public int b() {
        return this.f;
    }
    
    public String toString() {
        return this.a(-536870910) + ";" + this.f + ((this.j == null) ? "" : (";" + this.j)) + ((this.k instanceof String) ? (";" + this.k) : "");
    }
    
    public String a(final int n) {
        return a(n, this.a, this.b, this.c, this.d, this.e, this.i, this.h);
    }
    
    public int compareTo(final Object o) {
        if (o == null || !(o instanceof b)) {
            return 1;
        }
        if (this == o) {
            return 0;
        }
        return (int)b.a.a.e.f(this.a() - ((b)o).a());
    }
    
    public static String a(final int n, final int n2, final int n3, final int n4) {
        return a(n, n2, n3, n4, 0, 0, 0);
    }
    
    public static String a(final int n, final int n2, final int n3) {
        return a(n, 0, 0, 0, n2, n3, 0);
    }
    
    public static String a(final int n, final int n2, final int n3, final boolean b, final int n4) {
        return a(n, 0, 0, 0, n2, n3, b ? n4 : 0);
    }
    
    public static String a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        return a(n, n2, n3, n4, n5, n6, n7, 0);
    }
    
    public static String a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final String[] c = k.c("ABBREV_MONTHS");
        String s = null;
        switch (n & 0x1FFFFFFF) {
            case 0: {
                s = e.a("-000#", n2) + "-" + b.a.d.c.a(n3, '0', 2) + "-" + b.a.d.c.a(n4, '0', 2);
                break;
            }
            case 1: {
                s = a(0, n2, n3, n4) + " " + a(3, n5, n6);
                break;
            }
            case 2: {
                s = a(0, n2, n3, n4) + " " + a(4, n5, n6, true, n7);
                break;
            }
            case 3: {
                s = b.a.d.c.a(n5, '0', 2) + ":" + b.a.d.c.a(n6, '0', 2);
                break;
            }
            case 4: {
                s = a(3, n5, n6) + ((n7 == 0) ? " " : ((n7 == 30) ? "^" : "§"));
                break;
            }
            case 5:
            case 6: {
                s = c[n3 - 1] + " " + b.a.d.c.a(n4, ' ', 2) + " " + a((n == 5) ? 3 : 4, n5, n6, true, n7);
                break;
            }
            case 7: {
                s = b.a.d.c.a((n5 == 0) ? 12 : ((n5 > 12) ? (n5 - 12) : n5), '0', 2) + ":" + b.a.d.c.a(n6, '0', 2) + " " + k.b((n5 < 12) ? "TIME_AM" : "TIME_PM");
                break;
            }
        }
        if (s != null && (n & Integer.MIN_VALUE) != 0x0) {
            final String a = k.a(n8, false, (n & 0x40000000) == 0x0);
            final String b = k.b("TIME_WITH_ZONE_FORMAT");
            String s2 = "";
            if (s.endsWith(" ")) {
                s = s.substring(0, s.length() - 1);
                if ((n & 0x20000000) == 0x0) {
                    s2 = " ";
                }
            }
            s = b.a.d.a.a(b, s, s2, a);
        }
        return s;
    }
}
