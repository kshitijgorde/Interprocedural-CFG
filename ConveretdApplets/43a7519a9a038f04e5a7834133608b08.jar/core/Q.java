// 
// Decompiled by Procyon v0.5.30
// 

package core;

import I.I;
import B.Z;

public final class Q
{
    public final int a;
    public final Z b;
    private final J c;
    private final int d;
    private final int e;
    private final int f;
    private final II g;
    
    public static final Q[] a(final RE re, final N[] array, final boolean b) {
        final Q[] array2 = new Q[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = new Q(re, array[i], b);
        }
        return array2;
    }
    
    private Q(final RE re, final N n, final boolean b) {
        this(re, n.a, n.b, n.d, n.c, n.e, b, 0);
    }
    
    public Q(final RE re, final float n, final float n2, final String s, final String s2, final String s3, final boolean b, final int a) {
        this.a = a;
        final double n3 = n;
        final double n4 = n2;
        final double n5 = 6.283185307179586 * (n3 - 0.75);
        final double n6;
        final double sin = Math.sin(n6 = 3.141592653589793 * (1.0 - n4));
        this.b = new Z(sin * Math.sin(n5), Math.cos(n6), sin * Math.cos(n5));
        String s4 = "";
        if (!s2.equals(I.I(49))) {
            s4 = String.valueOf(s4) + s2;
        }
        if (!s.equals(I.I(49))) {
            final String s5;
            s4 = String.valueOf(((s5 = s4).length() > 0) ? K.d(s5) : s5) + s;
        }
        (this.c = re.e.a(s4)).c();
        this.c.b();
        if (b) {
            this.g = II.a(K.a(re.a(), I.I(51) + s3 + I.I(56)));
            this.e = -(this.c.a() + this.g.a) / 2;
            this.d = this.e + this.g.a + 1;
        }
        else {
            this.g = null;
            this.e = 0;
            this.d = -this.c.a() / 2;
        }
        this.f = -re.e.a - 7;
    }
    
    public final void a(final T t, final RE re) {
        final O a;
        if ((a = re.c.a(this.b)).a < Integer.MAX_VALUE) {
            final int n = a.b + this.f;
            this.c.a(t, a.a + this.d, n, true);
            if (this.g != null) {
                final int n2 = a.a + this.e - 1;
                final int n3 = n - 1;
                int n4 = this.g.a + this.c.a() + 3;
                int n5 = n3;
                int n6 = n2;
                n4 -= 2;
                ++n6;
                t.a(n6, n5, n4);
                t.a(n6, n5 + 13 - 1, n4);
                ++n5;
                t.a(n6 - 1, n5);
                t.a(n6 + n4, n5);
                t.a(a.a + this.d - 1, n);
                t.a(a.a + this.e, n, this.g);
            }
            final int a2;
            final int n7 = (a2 = re.d.a) / 10;
            int n8;
            if ((n8 = 290 - a2) > 255) {
                n8 = 255;
            }
            t.b(a.a, a.b, n7, n8, 255 - a2);
            final int b;
            final int n9 = (b = re.d.b) / 10;
            int n10;
            if ((n10 = 290 - b) > 255) {
                n10 = 255;
            }
            t.b(a.a, a.b, n9, n10, 255 - b);
            final int c;
            final int n11 = (c = re.d.c) / 10;
            int n12;
            if ((n12 = 290 - c) > 255) {
                n12 = 255;
            }
            t.b(a.a, a.b, n11, n12, 255 - c);
        }
    }
}
