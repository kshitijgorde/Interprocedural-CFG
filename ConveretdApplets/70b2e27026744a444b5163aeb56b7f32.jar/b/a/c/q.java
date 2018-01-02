// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

import java.util.GregorianCalendar;
import b.a.a.e;
import b.a.a.b;

public class q extends b implements a, Cloneable, Comparable
{
    protected c a;
    protected int b;
    protected int c;
    protected l d;
    protected double e;
    protected b.a.a.a f;
    protected b.a.a.a g;
    protected String h;
    protected double i;
    protected double j;
    protected r k;
    protected int l;
    protected b.a.a.a m;
    protected double n;
    protected static l o;
    protected static r p;
    
    public q() {
        this(0.0, 51.477, 0.0, 0, 0, 0, null);
    }
    
    public q(final double n, final double n2, final double e, final int l, final int n3, final int n4, final String h) {
        this.b = 0;
        this.c = 0;
        this.d = q.o;
        this.k = q.p;
        this.l = 0;
        this.g = new b.a.a.a(n, 1);
        this.f = new b.a.a.a(n2, 1);
        this.e = e;
        this.l = l;
        this.h = h;
        this.b();
        this.e(n3, n4);
    }
    
    public Object clone() {
        return this.a(false);
    }
    
    public Object a(final boolean b) {
        try {
            final Object clone = super.clone();
            if (b) {
                ((q)clone).a();
            }
            return clone;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public void a() {
        this.d = new l();
        this.k = new r();
    }
    
    private void b() {
        final double n = 0.9966471416431749;
        final double a = this.f.a();
        double n2;
        if (b.a.a.b.a(a) > 1.5707915) {
            n2 = a;
        }
        else if (b.a.a.b.a(a) > 1.5707866) {
            final double j = b.a.a.b.j(a);
            n2 = b.a.a.e.a(j * 1.5707915, a, j * 1.5707866, a, b.a.a.b.c(n * b.a.a.b.n(1.5707866)));
        }
        else {
            n2 = b.a.a.b.c(n * this.f.e());
        }
        this.j = n * b.a.a.b.k(n2) + this.e / 6378.14 / 1000.0 * this.f.c();
        this.i = b.a.a.b.e(n2) + this.e / 6378.14 / 1000.0 * this.f.d();
    }
    
    private void e(final int c, final int b) {
        this.c = c;
        this.b = b;
        switch (c) {
            case 1: {
                this.a = new d(this.l);
                break;
            }
            case 2: {
                this.a = new b.a.c.e(this.l, b);
                break;
            }
            default: {
                this.a = new c(this.l);
                break;
            }
        }
    }
    
    public b.a.a.a c() {
        return this.g;
    }
    
    public b.a.a.a d() {
        return this.f;
    }
    
    public int e() {
        return this.l;
    }
    
    public int a(final int n, final int n2, final int n3) {
        return this.a(n, n2, n3, 0, 0, false) ? this.f(n, n2) : 0;
    }
    
    public int f() {
        return this.c;
    }
    
    public int b(final int n, final int n2, final int n3) {
        return this.a.a(n, n2, n3);
    }
    
    public boolean a(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        return this.a.a(n, n2, n3, n4, n5, b);
    }
    
    public int f(final int n, final int n2) {
        return this.a.a(n, n2);
    }
    
    public boolean a(final int[] array, final boolean b, final boolean b2) {
        return this.a.a(array, b, b2);
    }
    
    public String g() {
        return this.h;
    }
    
    public q a(final int n, final Object o) {
        final q q = new q();
        q.g = this.g;
        q.f = this.f;
        q.e = this.e;
        q.l = this.l;
        q.h = this.h;
        int c = this.c;
        int b = this.b;
        final double e = (o instanceof Number) ? ((Number)o).doubleValue() : 0.0;
        final int l = (o instanceof Number) ? ((Number)o).intValue() : 0;
        switch (n) {
            case 1: {
                if (o instanceof b.a.a.a) {
                    q.g = (b.a.a.a)o;
                    break;
                }
                q.g = new b.a.a.a(e, 1);
                break;
            }
            case 2: {
                if (o instanceof b.a.a.a) {
                    q.f = (b.a.a.a)o;
                    break;
                }
                q.f = new b.a.a.a(e, 1);
                break;
            }
            case 3: {
                q.e = e;
                break;
            }
            case 4: {
                q.l = l;
                break;
            }
            case 5: {
                c = l;
                break;
            }
            case 6: {
                b = l;
                break;
            }
            case 7: {
                q.h = o.toString();
                break;
            }
        }
        q.b();
        q.e(c, b);
        return q;
    }
    
    public synchronized b.a.a.a a(final double n, final boolean b) {
        if (this.m == null || this.n != n) {
            double n2;
            if (b) {
                n2 = this.k.s(n);
            }
            else {
                n2 = r.r(n);
            }
            this.m = new b.a.a.a(n2, 1).b(this.g);
            this.n = n;
        }
        return this.m;
    }
    
    public b.a.a.d a(final b.a.a.d d, final double n, final int n2) {
        final double a = this.a(b.a.c.j.c(n), (n2 & 0x4) != 0x0).a();
        final double f = d.f();
        final double n3 = b.a.a.b.k(4.2635096893189924E-5) / f;
        final double a2 = d.b().a();
        final double a3 = d.e().a();
        final double n4 = a - a2;
        final double a4 = b.a.a.b.a(-this.i * n3 * b.a.a.b.k(n4), b.a.a.b.e(a3) - this.i * n3 * b.a.a.b.e(n4));
        return new b.a.a.d(a2 + a4, b.a.a.b.a((b.a.a.b.k(a3) - this.j * n3) * b.a.a.b.e(a4), b.a.a.b.e(a3) - this.i * n3 * b.a.a.b.e(n4)), f);
    }
    
    public b.a.a.d b(final b.a.a.d d, final double n, final int n2) {
        return (b.a.a.d)this.a(d, d.f(), n, n2);
    }
    
    protected b.a.a.c a(final b.a.a.c c, double n, final double n2, final int n3) {
        final double a = this.a(n2, (n3 & 0x4) != 0x0).a();
        final double a2 = c.b().a();
        final double a3 = c.e().a();
        final double n4 = a - a2;
        final double a4 = b.a.a.b.a(b.a.a.b.k(n4), b.a.a.b.e(n4) * this.f.c() - b.a.a.b.n(a3) * this.f.d());
        final double b;
        double q = b = b.a.a.b.b(b.a.a.b.i(this.f.c() * b.a.a.b.k(a3) + this.f.d() * b.a.a.b.e(a3) * b.a.a.b.e(n4)));
        if ((n3 & 0x10) != 0x0) {
            q = b.a.a.b.q(b.a.c.k.r(b.a.a.b.p(q)));
        }
        if (n >= 0.0) {
            if ((n3 & 0x8) != 0x0) {
                n -= Math.sin(b) * (6356.755 + 21.38500000000022 * this.f.d() + this.e / 1000.0) / 1.49597870691E8;
            }
            return new b.a.a.d(a4, q, n);
        }
        return new b.a.a.c(a4, q);
    }
    
    public double a(final int n, final int n2, final int n3, final int n4, final int n5, final double n6) {
        return this.a(n, n2, n3, n4, n5, n6, 0);
    }
    
    public double a(final int n, final int n2, final int n3, final int n4, final int n5, final double n6, final int n7) {
        int e = this.e();
        if (n7 == 2 || (n7 == 0 && this.a(n, n2, n3, n4, n5, false))) {
            e += this.f(n, n2);
        }
        return b.a.c.k.a(n, n2, n3, n4, n5 - e, n6);
    }
    
    public int r(final double n) {
        return this.b(n, false);
    }
    
    public int b(final double n, final boolean b) {
        final int[] a = this.a(n, b, false);
        return b.a.c.k.a(a[0], a[1], a[2]);
    }
    
    public int[] a(final double n, final boolean b, final boolean b2) {
        final int[] a = b.a.c.k.a(n + this.l / 1440.0, b, b2);
        if (this.a(a, false, false)) {
            a[6] = this.f(a[0], a[1]);
        }
        return a;
    }
    
    public String toString() {
        b.a.a.a a = this.g;
        String s = "E";
        b.a.a.a a2 = this.f;
        String s2 = "N";
        final int c = b.a.a.b.c(b.a.a.b.b(b.a.a.e.d(this.e), -99999), 99999);
        String s3 = "C";
        if (a.a() <= 0.0) {
            a = a.a(-1.0, -3);
            s = "W";
        }
        if (a2.a() <= 0.0) {
            a2 = a2.a(-1.0, -3);
            s2 = "S";
        }
        if (this.h != null) {
            for (int i = 0; i < this.h.length(); ++i) {
                final char char1 = this.h.charAt(i);
                if (('\u0080' <= char1 && char1 <= '\u009f') || char1 > '\u00ff') {
                    s3 = "D";
                    break;
                }
            }
        }
        return s3 + a.a("00#d0#m0#s") + s + a2.a("0#d0#m0#s") + s2 + b.a.d.c.a(b.a.a.b.a(c), '0', 5) + ((c < 0) ? "N" : "P") + b.a.d.c.a(b.a.a.b.a(this.l), '0', 3) + ((this.l < 0) ? "N" : "P") + this.c + Integer.toString(this.b, 36).toUpperCase() + ((this.h == null) ? "" : this.h);
    }
    
    public static q a(String string) {
        if (string == null || string.length() < 1) {
            return null;
        }
        final q q = new q();
        final char c = (char)(string.charAt(0) - 'A');
        int n;
        if (c == '\0') {
            n = 27;
        }
        else if (c < '\u0002') {
            n = 28;
        }
        else {
            n = 34;
        }
        if (string.length() < n) {
            return null;
        }
        double n2 = b.a.d.d.a((Object)string.substring(1, 4)) + b.a.d.d.a((Object)string.substring(5, 7)) / 60.0 + b.a.d.d.a((Object)string.substring(8, 10)) / 3600.0;
        if (n2 < 0.0 || n2 > 180.0) {
            return null;
        }
        if (string.charAt(11) == 'W') {
            n2 *= -1.0;
        }
        q.g = new b.a.a.a(n2, 1);
        double n3 = b.a.d.d.a((Object)string.substring(12, 14)) + b.a.d.d.a((Object)string.substring(15, 17)) / 60.0 + b.a.d.d.a((Object)string.substring(18, 20)) / 3600.0;
        if (n3 < 0.0 || n3 > 90.0) {
            return null;
        }
        if (string.charAt(21) == 'S') {
            n3 *= -1.0;
        }
        q.f = new b.a.a.a(n3, 1);
        if (c >= '\u0002') {
            q.e = b.a.d.d.a((Object)string.substring(22, 27));
            if (string.charAt(27) == 'N') {
                final q q2 = q;
                q2.e *= -1.0;
            }
            string = string.substring(0, 22) + string.substring(28);
            n -= 6;
        }
        else {
            q.e = 0.0;
        }
        final String substring = string.substring(22, 25);
        if (substring.equals("XXX")) {
            q.l = new GregorianCalendar().get(15) / 60000;
        }
        else {
            q.l = b.a.d.d.a((Object)substring);
            if (string.charAt(25) == 'N') {
                final q q3 = q;
                q3.l *= -1;
            }
        }
        final char char1 = string.charAt(26);
        int n4 = char1 - '0';
        int n5 = 0;
        if (c == '\0') {
            if (char1 == 'T' || n4 == 1) {
                n4 = 2;
                n5 = 13;
            }
            else if (char1 == 'F') {
                n4 = 0;
            }
            else if (n4 == 2) {
                n4 = 2;
                n5 = 7;
            }
        }
        else {
            n5 = string.charAt(27) - ((string.charAt(27) > '9') ? '7' : '0');
        }
        if (string.length() == n) {
            q.h = null;
        }
        else {
            q.h = string.substring(n);
        }
        q.b();
        q.e(n4, n5);
        return q;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && b.a.d.d.a(this.h, (Object)((q)o).h) && this.a((q)o));
    }
    
    public boolean a(final q q) {
        return this == q || (this.g.equals(q.g) && this.f.equals(q.f) && this.e == q.e && this.c == q.c && this.b == q.b && this.l == q.l);
    }
    
    public int compareTo(final Object o) {
        if (o == null || !(o instanceof q)) {
            return 1;
        }
        return this.h.compareTo(((q)o).h);
    }
    
    static {
        q.o = new l();
        q.p = new r();
    }
}
