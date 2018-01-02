// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.fitness;

import wordle.core.e.b;
import wordle.core.b.c;
import java.util.HashMap;
import java.awt.geom.Dimension2D;
import java.util.Map;

public class VerticalCenterLine extends AbstractPlacementStrategy
{
    private final Map a;
    private int b;
    
    public VerticalCenterLine(final Dimension2D dimension2D) {
        super(dimension2D);
        this.a = new HashMap();
        this.b = 0;
    }
    
    public final void a(final c c) {
        c.b((this.b > 100) ? this.c(c) : (this.d() - c.f() / 2.0), this.a.get(c));
    }
    
    public final void a(final c[] array) {
        this.b = array.length;
        this.a.clear();
        for (final c c : array) {
            this.a.put(c, wordle.core.e.b.a() * this.b() - c.g() / 2.0);
        }
        super.a(array);
    }
}
