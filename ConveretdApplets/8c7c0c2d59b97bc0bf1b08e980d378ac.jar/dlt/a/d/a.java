// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.d;

import dlt.a.b.h;
import dlt.a.b.g;
import dlt.a.b.c;

public class a extends d
{
    public a(final b b, final double n) {
        super(b, n);
    }
    
    public void a(final c c, final dlt.a.c.b b) {
        final g g = new g(this.if.a(), c);
        g.for();
        final h[] if1 = b.if();
        for (int i = 0; i < if1.length; ++i) {
            final h h = if1[i];
            b.a(h, this.a + (1.0 - this.a) * ((dlt.a.b.g.if(h, g) + 1.0) / 2.0));
        }
    }
}
