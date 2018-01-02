// 
// Decompiled by Procyon v0.5.30
// 

package C;

import core.S;
import java.net.URL;
import core.FI;

public final class I extends Z
{
    private final FI e;
    
    public I(final URL url, final int n, final S s) {
        super(n, s);
        this.e = FI.a(url);
    }
    
    public final void a(final float n, final float n2) {
        final S d = super.d;
        final double n3 = n;
        final double n4 = n2;
        final double n5 = n3;
        final S s = d;
        final int n6 = s.a * (int)(n4 * s.b) + (int)(n5 * s.a);
        try {
            final int a = this.e.a;
            final int b = this.e.b;
            final int n7 = -(a >> 1) - (b >> 1) * super.d.a;
            for (int i = 0; i < b; ++i) {
                for (int j = 0; j < a; ++j) {
                    final int a2 = this.e.a(i * a + j);
                    super.d.a(super.a * (a2 >> 16 & 0xFF) >> 8, super.b * (a2 >> 8 & 0xFF) >> 8, super.c * (a2 & 0xFF) >> 8, a2 >>> 24, n6 + j + super.d.a * i + n7);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
    }
}
