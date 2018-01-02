// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.awt.geom.Dimension2D;

public final class D extends Dimension2D
{
    public final double a;
    public final double b;
    
    public D(final double a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public final double getWidth() {
        return this.a;
    }
    
    public final double getHeight() {
        return this.b;
    }
    
    public final void setSize(final double n, final double n2) {
        throw new UnsupportedOperationException("Size is immutable.");
    }
}
