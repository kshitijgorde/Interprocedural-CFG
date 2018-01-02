// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.visualnet;

import hypergraph.hyperbolic.ModelPoint;

public interface Renderable
{
    void setPosition(final ModelPoint p0);
    
    ModelPoint getPosition();
    
    void setScaleX(final double p0);
    
    void setScaleY(final double p0);
    
    void setScale(final double p0, final double p1);
    
    void setIconic();
    
    boolean isIconic();
}
