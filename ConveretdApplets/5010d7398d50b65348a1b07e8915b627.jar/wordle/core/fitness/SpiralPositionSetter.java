// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.fitness;

import wordle.core.e.b;
import java.awt.geom.Dimension2D;
import wordle.core.b.c;

public class SpiralPositionSetter implements PositionSetter
{
    private final c a;
    private double b;
    private double c;
    private final double d;
    private final double e;
    private final double f;
    private final Dimension2D g;
    private final OverallShape h;
    
    public SpiralPositionSetter(final c a, final Dimension2D g, final OverallShape h) {
        this.a = a;
        this.g = g;
        this.d = a.a.x;
        this.e = a.a.y;
        this.h = h;
        this.b = 1.0;
        this.f = wordle.core.e.b.a() * 2.0 * 3.141592653589793;
        final SpiralPositionSetter spiralPositionSetter = this = this;
        spiralPositionSetter.c = this.f;
    }
    
    public final void a() {
        final double width = this.g.getWidth();
        final double height = this.g.getHeight();
        do {
            this.a.b(this.d + this.b * Math.cos(this.c), this.e + this.b * Math.sin(this.c));
            this.c += 0.04;
            this.b += 0.7;
            if (this.b > width || this.b > height || this.h == OverallShape.b) {
                return;
            }
        } while (this.a.a.x < 0.0 || this.a.a.y < 0.0 || this.a.a.x + this.a.f() > this.g.getWidth() || this.a.a.y + this.a.g() > this.g.getHeight());
    }
}
