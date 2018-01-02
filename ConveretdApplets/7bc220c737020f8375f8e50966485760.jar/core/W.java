// 
// Decompiled by Procyon v0.5.30
// 

package core;

import I.I;
import B.Z;

public final class W
{
    public final int a;
    public final Z b;
    private final J c;
    private final int d;
    private final int e;
    private final int f;
    private final FI g;
    
    public static final W[] a(final RevolverEngine revolverEngine, final T[] array, final boolean b) {
        final W[] array2 = new W[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = new W(revolverEngine, array[i], b);
        }
        return array2;
    }
    
    private W(final RevolverEngine revolverEngine, final T t, final boolean b) {
        this(revolverEngine, t.a, t.b, t.d, t.c, t.e, b, 0);
    }
    
    public W(final RevolverEngine revolverEngine, final float n, final float n2, final String s, final String s2, final String s3, final boolean b, final int a) {
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
            s4 = String.valueOf(((s5 = s4).length() > 0) ? E.d(s5) : s5) + s;
        }
        (this.c = revolverEngine.e.a(s4)).c();
        this.c.b();
        if (b) {
            this.g = FI.a(E.a(revolverEngine.a(), I.I(51) + s3 + I.I(65)));
            this.e = -(this.c.a() + this.g.a) / 2;
            this.d = this.e + this.g.a + 1;
        }
        else {
            this.g = null;
            this.e = 0;
            this.d = -this.c.a() / 2;
        }
        this.f = -revolverEngine.e.a - 7;
    }
    
    public final void a(final E e, final RevolverEngine revolverEngine) {
        final U a;
        if ((a = revolverEngine.c.a(this.b)).a < Integer.MAX_VALUE) {
            final int n = a.b + this.f;
            this.c.a(e, a.a + this.d, n, true);
            if (this.g != null) {
                e.a(a.a + this.e - 1, n - 1, this.g.a + this.c.a() + 3);
                e.a(a.a + this.d - 1, n);
                e.a(a.a + this.e, n, this.g);
            }
            final int a2;
            final int n2 = (a2 = revolverEngine.d.a) / 10;
            int n3;
            if ((n3 = 290 - a2) > 255) {
                n3 = 255;
            }
            e.b(a.a, a.b, n2, n3, 255 - a2);
            final int b;
            final int n4 = (b = revolverEngine.d.b) / 10;
            int n5;
            if ((n5 = 290 - b) > 255) {
                n5 = 255;
            }
            e.b(a.a, a.b, n4, n5, 255 - b);
            final int c;
            final int n6 = (c = revolverEngine.d.c) / 10;
            int n7;
            if ((n7 = 290 - c) > 255) {
                n7 = 255;
            }
            e.b(a.a, a.b, n6, n7, 255 - c);
        }
    }
}
