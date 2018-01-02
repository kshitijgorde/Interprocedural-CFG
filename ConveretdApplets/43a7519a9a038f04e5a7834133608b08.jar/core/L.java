// 
// Decompiled by Procyon v0.5.30
// 

package core;

final class L implements J
{
    private final M[] a;
    private final int b;
    private int c;
    private boolean d;
    private P e;
    
    private L(final P e, final String s, final byte b) {
        this.e = e;
        this.c = 255;
        this.d = false;
        final char[] charArray;
        final int length;
        final M[] array = new M[length = (charArray = s.toCharArray()).length];
        int n = 0;
        int b2 = 0;
        for (int i = 0; i < length; ++i) {
            if (e.a(charArray[i])) {
                final M m = P.a(e)[charArray[i]];
                array[n] = m;
                b2 += m.a + 2;
                ++n;
            }
        }
        this.b = b2;
        System.arraycopy(array, 0, this.a = new M[n], 0, n);
    }
    
    public final void b() {
        this.d = true;
    }
    
    public final void c() {
        this.c = 130;
    }
    
    public final void a(final T t, final int n, int n2, final boolean b) {
        final int n3 = n2;
        final int length = this.a.length;
        final int n4 = n3;
        n2 = n;
        if (b) {
            if (this.d) {
                t.a(n2, n4, this.b, this.e.a, this.c);
            }
            ++n2;
            for (int i = 0; i < length; ++i) {
                final M m = this.a[i];
                t.a(n2, n4, m);
                n2 += m.a + 2;
            }
            return;
        }
        if (this.d) {
            t.a(n2 - this.b, n4, this.b, this.e.a, this.c);
        }
        --n2;
        for (int j = length - 1; j >= 0; --j) {
            final M k = this.a[j];
            n2 -= k.a;
            t.a(n2, n4, k);
            n2 -= 2;
        }
    }
    
    public final int a() {
        return this.b;
    }
    
    L(final P p2, final String s) {
        this(p2, s, (byte)0);
    }
}
