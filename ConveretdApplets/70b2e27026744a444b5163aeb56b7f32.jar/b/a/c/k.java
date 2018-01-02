// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

import java.util.MissingResourceException;
import b.a.d.c;
import b.a.a.e;
import java.util.ResourceBundle;
import java.util.Locale;
import b.a.a.b;

public class k extends b implements a
{
    private static Locale a;
    private static ResourceBundle b;
    protected static final double c;
    private static double d;
    private static double e;
    
    public static int a(int n, int i, final int n2) {
        while (i < 1) {
            i += 12;
            --n;
        }
        while (i > 12) {
            i -= 12;
            ++n;
        }
        if (n > 1582 || (n == 1582 && (i > 10 || (i == 10 && n2 >= 15)))) {
            return 367 * n - 7 * (n + (i + 9) / 12) / 4 - 3 * ((n + (i - 9) / 7) / 100 + 1) / 4 + 275 * i / 9 + n2 + 1721029;
        }
        return 367 * n - b.a.a.b.a(7 * (n + (i + 9) / 12), 4) + 275 * i / 9 + n2 + 1721027;
    }
    
    public static int[] b(int i) {
        int n;
        if (i >= 2299161) {
            n = b.a.a.b.h((i - 1721060.0) / 365.2425);
        }
        else {
            n = b.a.a.b.h((i - 1721058.0) / 365.25);
        }
        if (i < a(n, 1, 1)) {
            --n;
        }
        else if (i > a(n, 12, 31)) {
            ++n;
        }
        int n2;
        int f;
        for (i -= a(n, 1, 0), n2 = 1; i > (f = f(n, n2)); i -= f, ++n2) {}
        return new int[] { n, n2, i };
    }
    
    public static double a(final int n, final int n2, final int n3, final int n4, final int n5, final double n6) {
        return a(n, n2, n3) + (n4 + (n5 + n6 / 60.0) / 60.0) / 24.0 - 0.5;
    }
    
    public static double a(final long n) {
        return n / 8.64E7 + k.c;
    }
    
    public static int[] a(double n, final boolean b, final boolean b2) {
        n += 0.5 + (b ? (b2 ? 5.787037037037037E-6 : 3.4722222222222224E-4) : 0.0);
        final int[] b3 = b(b.h(n));
        final int h = b.h((n - b.g(n)) * 86400.0);
        final int n2 = h / 3600;
        final int n3 = h - n2 * 3600;
        final int n4 = n3 / 60;
        final int n5 = (int)(n3 - n4 * 60.0);
        return new int[] { b3[0], b3[1], b3[2], n2, n4, b2 ? n5 : false, 0 };
    }
    
    public static int e(final int n, final int n2) {
        if (n == 1582 && n2 == 10) {
            return 21;
        }
        if (n2 == 9 || n2 == 4 || n2 == 6 || n2 == 11) {
            return 30;
        }
        if (n2 != 2) {
            return 31;
        }
        return a(n, 3, 1) - a(n, 2, 1);
    }
    
    public static int f(final int n, final int n2) {
        if (n == 1582 && n2 == 10) {
            return 31;
        }
        return e(n, n2);
    }
    
    public static int b(final int n, final int n2, final int n3) {
        return b.a.a.b.d(a(n, n2, n3) + 1, 7);
    }
    
    public static int a(final int n, final int n2, final int n3, final int n4) {
        int i;
        for (i = b.a.a.b.d(n3 - b(n, n2, 1) + 7, 7) + n4 * 7 - 6; i > e(n, n2); i -= 7) {}
        if (n == 1582 && n2 == 10 && i > 4) {
            i += 10;
        }
        return i;
    }
    
    public static double r(final double n) {
        if (n < -4.0) {
            return n;
        }
        final double n2 = n + s(n) - k.d;
        if (n < -2.0) {
            return b.a.a.e.a(-4.0, n, -2.0, n, n2);
        }
        return n2;
    }
    
    private static double s(final double n) {
        return 1.033879 / b.a.a.b.o(n + 10.3 / (n + 5.11)) / 60.0;
    }
    
    private static double t(final double n) {
        return 1.015056 / b.a.a.b.o(n + 7.31 / (n + 4.4)) / 60.0;
    }
    
    public static String a(int n, final boolean b, final boolean b2) {
        String s;
        if (b) {
            s = k.b.getString("ABBREV_DYNAMICAL_TIME");
        }
        else {
            s = k.b.getString("ABBREV_UNIVERSAL_TIME");
        }
        if (n != 0) {
            String s2;
            if (n < 0) {
                s2 = s + (b2 ? " - " : "-");
                n *= -1;
            }
            else {
                s2 = s + (b2 ? " + " : "+");
            }
            s = s2 + n / 60;
            if (n % 60 != 0) {
                s = s + ":" + b.a.d.c.a(n % 60, '0', 2);
            }
        }
        return s;
    }
    
    public static void a(final Locale a) {
        k.a = a;
        k.b = ResourceBundle.getBundle("org-shetline-astronomy", a);
    }
    
    public static ResourceBundle a() {
        if (k.b == null) {
            a(Locale.getDefault());
        }
        return k.b;
    }
    
    public static String a(final String s) {
        String string = null;
        try {
            if (k.b == null) {
                a();
            }
            string = k.b.getString(s);
        }
        catch (MissingResourceException ex) {
            System.err.println("ERROR: Missing resource \"" + s + '\"');
        }
        return string;
    }
    
    public static String b(final String s) {
        final String a = a(s);
        if (a != null) {
            return a;
        }
        return "[" + s + "]";
    }
    
    public static String[] c(final String s) {
        return a(s, '\t');
    }
    
    public static String[] a(final String s, final char c) {
        final String a = a(s);
        if (a != null) {
            return c.a(a, c);
        }
        return new String[] { "[" + s + "]" };
    }
    
    static {
        k.a = Locale.getDefault();
        c = a(1970, 1, 1, 0, 0, 0.0);
        k.d = s(90.0);
        k.e = t(90.0);
    }
}
