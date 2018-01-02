// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.fitness;

import wordle.core.e.b;
import wordle.core.b.c;
import java.util.HashMap;
import java.awt.geom.Dimension2D;
import java.util.Map;

public class HorizontalCenterLine extends AbstractPlacementStrategy
{
    private final Map a;
    
    public HorizontalCenterLine(final Dimension2D dimension2D) {
        super(dimension2D);
        this.a = new HashMap();
    }
    
    public final void a(final c c) {
        c.b(this.a.get(c), this.b(c));
    }
    
    public final void a(final c[] array) {
        this.a.clear();
        for (final c c : array) {
            this.a.put(c, b.a() * this.a() - c.f() / 2.0);
        }
        super.a(array);
    }
}
