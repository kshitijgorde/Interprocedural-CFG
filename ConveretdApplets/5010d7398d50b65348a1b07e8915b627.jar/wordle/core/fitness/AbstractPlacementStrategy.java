// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.fitness;

import java.util.Comparator;
import java.util.Arrays;
import wordle.core.b.c;
import java.awt.geom.Dimension2D;

public abstract class AbstractPlacementStrategy implements PlacementStrategy
{
    private final Dimension2D a;
    
    protected AbstractPlacementStrategy(final Dimension2D a) {
        this.a = a;
    }
    
    public void a(final c[] array) {
        Arrays.sort(array, c.c);
    }
    
    protected final double a() {
        return this.a.getWidth();
    }
    
    protected final double b() {
        return this.a.getHeight();
    }
    
    protected final double c() {
        return this.a.getHeight() / 2.0;
    }
    
    protected final double d() {
        return this.a.getWidth() / 2.0;
    }
    
    protected final double b(final c c) {
        return RandomUtil.a(1.4) * 1.25 * Math.min(c.g(), c.f()) * RandomUtil.a() + (this.c() - c.g() / 2.0);
    }
    
    protected final double c(final c c) {
        return RandomUtil.a(1.4) * 1.25 * Math.min(c.g(), c.f()) * RandomUtil.a() + (this.d() - c.f() / 2.0);
    }
}
