// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.fitness;

import wordle.core.b.c;
import java.awt.geom.Dimension2D;

public class Center extends AbstractPlacementStrategy
{
    public Center(final Dimension2D dimension2D) {
        super(dimension2D);
    }
    
    public final void a(final c c) {
        c.b((this.a() - c.f()) / 2.0, (this.b() - c.g()) / 2.0);
    }
    
    public final void a(final c[] array) {
        super.a(array);
    }
}
