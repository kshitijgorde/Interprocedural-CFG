// 
// Decompiled by Procyon v0.5.30
// 

package b.a.a;

public class a
{
    public static final a a;
    public static final a b;
    public static final a c;
    private double d;
    private double e;
    private double f;
    private double g;
    
    public a(final double n) {
        this.e = 2.0;
        this.f = 2.0;
        this.g = 0.0;
        this.a(n, 0, -1);
    }
    
    public a(final double n, final int n2) {
        this.e = 2.0;
        this.f = 2.0;
        this.g = 0.0;
        if (n2 < 0) {
            this.a(n, 0, n2);
        }
        else {
            this.a(n, n2, -1);
        }
    }
    
    public a(final double n, final int n2, final int n3) {
        this.e = 2.0;
        this.f = 2.0;
        this.g = 0.0;
        this.a(n, n2, n3);
    }
    
    private void a(final double n, final int n2, final int n3) {
        if (n3 == -1) {
            this.d = b.a.a.e.b(b(n, n2), 6.283185307179586);
        }
        else if (n3 == -2) {
            this.d = b.a.a.e.a(b(n, n2), 6.283185307179586);
        }
        else {
            this.d = b(n, n2);
        }
    }
    
    public double a() {
        return this.d;
    }
    
    public double b() {
        return c(this.d, 1);
    }
    
    public double c() {
        if (this.e > 1.0) {
            this.e = Math.sin(this.d);
        }
        return this.e;
    }
    
    public double d() {
        if (this.f > 1.0) {
            this.f = Math.cos(this.d);
        }
        return this.f;
    }
    
    public double e() {
        if (this.d == 0.0) {
            return 0.0;
        }
        if (this.g == 0.0) {
            this.g = Math.tan(this.d);
        }
        return this.g;
    }
    
    public a a(final a a) {
        return new a(this.d + a.d);
    }
    
    public a b(final a a) {
        return new a(this.d + a.d, -2);
    }
    
    public a f() {
        return new a(-this.d);
    }
    
    public a a(final double n, final int n2) {
        return new a(this.d * n, n2);
    }
    
    public static a a(final double n) {
        return new a(Math.asin(n));
    }
    
    public static a a(final double n, final double n2) {
        return new a(Math.atan2(n, n2));
    }
    
    public static a b(final double n, final double n2) {
        return new a(Math.atan2(n, n2), -2);
    }
    
    public String g() {
        return this.a("####°0#.#'");
    }
    
    public String a(final String s) {
        return this.a(s, this.b(), 360);
    }
    
    protected String a(final String s, double abs, final int n) {
        final String[] b = b.a.a.e.b(s);
        if (b != null && b.length == 1) {
            return b.a.a.e.a(s, abs % n);
        }
        if (b == null || b.length < 1 || b.length > 3) {
            return "???";
        }
        double n2 = 1.0;
        int n3;
        if (b.length == 3) {
            abs *= 3600.0;
            n3 = b.a.a.e.a(b[2]);
        }
        else if (b.length == 2) {
            abs *= 60.0;
            n3 = b.a.a.e.a(b[1]);
        }
        else {
            n3 = b.a.a.e.a(b[0]);
        }
        for (int i = 0; i < n3; ++i) {
            n2 *= 10.0;
        }
        abs = b.a.a.e.e(abs * n2) / n2;
        final double[] array = new double[b.length];
        final double f = b.a.a.e.f(abs);
        boolean b2 = false;
        abs = Math.abs(abs);
        final long n4 = (long)Math.floor(abs);
        abs -= n4;
        if (b.length == 3) {
            array[2] = n4 % 60L + abs;
            final long n5 = n4 / 60L;
            array[1] = n5 % 60L;
            array[0] = n5 / 60L % n * f;
            if (f < 0.0 && array[0] == 0.0) {
                array[0] = -1.0;
                b2 = true;
            }
        }
        else if (b.length == 2) {
            array[1] = n4 % 60L + abs;
            array[0] = n4 / 60L * f;
            if (f < 0.0 && array[0] == 0.0) {
                array[0] = -1.0;
                b2 = true;
            }
        }
        else {
            array[0] = (n4 + abs) * f;
        }
        String s2 = "";
        for (int j = 0; j < b.length; ++j) {
            s2 += b.a.a.e.a(b[j], array[j]);
        }
        if (b2) {
            final int index = s2.indexOf(45);
            if (index >= 0) {
                final int index2 = s2.indexOf(49, index + 1);
                if (index2 >= 0) {
                    s2 = s2.substring(0, index2) + "0" + s2.substring(index2 + 1);
                }
            }
        }
        return s2;
    }
    
    public static double b(final double n, final int n2) {
        switch (n2) {
            case 0: {
                return n;
            }
            case 1: {
                return n / 180.0 * 3.141592653589793;
            }
            case 2: {
                return n / 10800.0 * 3.141592653589793;
            }
            case 3: {
                return n / 648000.0 * 3.141592653589793;
            }
            case 4: {
                return n / 12.0 * 3.141592653589793;
            }
            case 5: {
                return n / 720.0 * 3.141592653589793;
            }
            case 6: {
                return n / 43200.0 * 3.141592653589793;
            }
            case 7: {
                return n * 6.283185307179586;
            }
            case 8: {
                return n / 200.0 * 3.141592653589793;
            }
            default: {
                return Double.NaN;
            }
        }
    }
    
    public static double c(final double n, final int n2) {
        switch (n2) {
            case 0: {
                return n;
            }
            case 1: {
                return n * 180.0 / 3.141592653589793;
            }
            case 2: {
                return n * 10800.0 / 3.141592653589793;
            }
            case 3: {
                return n * 648000.0 / 3.141592653589793;
            }
            case 4: {
                return n * 12.0 / 3.141592653589793;
            }
            case 5: {
                return n * 720.0 / 3.141592653589793;
            }
            case 6: {
                return n * 43200.0 / 3.141592653589793;
            }
            case 7: {
                return n / 6.283185307179586;
            }
            case 8: {
                return n * 200.0 / 3.141592653589793;
            }
            default: {
                return Double.NaN;
            }
        }
    }
    
    public boolean equals(final Object o) {
        return o != null && this.getClass() == o.getClass() && (this == o || this.d == ((a)o).d);
    }
    
    public String toString() {
        return this.g();
    }
    
    static {
        a = new a(0.0);
        b = new a(1.5707963267948966);
        c = new a(3.141592653589793);
    }
}
