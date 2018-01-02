// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.awt.geom.Rectangle2D;
import wordle.core.a.a;

public final class b extends a
{
    private final Rectangle2D.Double a;
    private final Rectangle2D.Double b;
    private final n c;
    private final wordle.core.a.a.b d;
    private final long e;
    private final Rectangle2D.Double f;
    
    public b(final n c, final long e, final wordle.core.a.a.b d, final Rectangle2D frame) {
        this.f = new Rectangle2D.Double();
        this.e = e;
        this.c = c;
        this.d = d;
        (this.b = new Rectangle2D.Double()).setFrame(frame);
        this.a = new Rectangle2D.Double();
    }
    
    public final boolean a(final long n) {
        if (n > this.e) {
            this.c.a(this.b);
            return false;
        }
        this.d.a(n, this.e, this.a, this.b, this.f);
        this.c.a(this.f);
        return true;
    }
    
    public final synchronized void a(final Rectangle2D frame) {
        this.b.setFrame(frame);
        this.c.b(this.a);
        this.c();
    }
    
    public final synchronized void a() {
        this.c.b(this.a);
        this.c.a(this.a);
    }
}
