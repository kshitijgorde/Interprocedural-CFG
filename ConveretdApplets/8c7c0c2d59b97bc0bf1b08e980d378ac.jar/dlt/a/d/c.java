// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.d;

import dlt.a.b.h;
import dlt.a.b.g;

public class c extends d
{
    public c(final b b, final double n) {
        super(b, n);
    }
    
    public void a(final dlt.a.b.c c, final dlt.a.c.b b) {
        final g g = new g(this.if.a(), c);
        g.for();
        final h[] if1 = b.if();
        double n = 0.0;
        for (int i = 0; i < if1.length; ++i) {
            n += (dlt.a.b.g.if(if1[i], g) + 1.0) / 2.0;
        }
        final double n2 = this.a + (1.0 - this.a) * (n / if1.length);
        for (int j = 0; j < if1.length; ++j) {
            b.a(if1[j], n2);
        }
    }
}
