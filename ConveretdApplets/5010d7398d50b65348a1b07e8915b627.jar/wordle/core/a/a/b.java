// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.a.a;

import java.awt.geom.Rectangle2D;

public abstract class b
{
    protected abstract double a(final double p0, final double p1, final double p2, final double p3);
    
    public final void a(final double n, final double n2, final Rectangle2D.Double double1, final Rectangle2D.Double double2, final Rectangle2D.Double double3) {
        double3.setFrame(this.a(n, n2, double1.x, double2.x), this.a(n, n2, double1.y, double2.y), this.a(n, n2, double1.width, double2.width), this.a(n, n2, double1.height, double2.height));
    }
}
