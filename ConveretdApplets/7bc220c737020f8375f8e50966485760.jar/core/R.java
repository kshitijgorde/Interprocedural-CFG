// 
// Decompiled by Procyon v0.5.30
// 

package core;

final class R implements J
{
    private final D[] a;
    private final int b;
    private int c;
    private boolean d;
    private V e;
    
    private R(final V e, final String s, final byte b) {
        this.e = e;
        this.c = 255;
        this.d = false;
        final char[] charArray;
        final int length;
        final D[] array = new D[length = (charArray = s.toCharArray()).length];
        int n = 0;
        int b2 = 0;
        for (int i = 0; i < length; ++i) {
            if (e.a(charArray[i])) {
                final D d = V.a(e)[charArray[i]];
                array[n] = d;
                b2 += d.a + 2;
                ++n;
            }
        }
        this.b = b2;
        System.arraycopy(array, 0, this.a = new D[n], 0, n);
    }
    
    public final void b() {
        this.d = true;
    }
    
    public final void c() {
        this.c = 130;
    }
    
    public final void a(final E e, final int n, int n2, final boolean b) {
        final int n3 = n2;
        final int length = this.a.length;
        final int n4 = n3;
        n2 = n;
        if (b) {
            if (this.d) {
                e.a(n2, n4, this.b, this.e.a, this.c);
            }
            ++n2;
            for (int i = 0; i < length; ++i) {
                final D d = this.a[i];
                e.a(n2, n4, d);
                n2 += d.a + 2;
            }
            return;
        }
        if (this.d) {
            e.a(n2 - this.b, n4, this.b, this.e.a, this.c);
        }
        --n2;
        for (int j = length - 1; j >= 0; --j) {
            final D d2 = this.a[j];
            n2 -= d2.a;
            e.a(n2, n4, d2);
            n2 -= 2;
        }
    }
    
    public final int a() {
        return this.b;
    }
    
    R(final V v, final String s) {
        this(v, s, (byte)0);
    }
}
