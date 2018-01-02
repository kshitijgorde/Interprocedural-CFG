// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Graphics;

public abstract class Drawable
{
    public abstract void draw(final Graphics p0, final CoordinateRect p1);
    
    public abstract void reset();
    
    void swapInFrame(final int n) {
    }
    
    void releaseResources() {
    }
}
