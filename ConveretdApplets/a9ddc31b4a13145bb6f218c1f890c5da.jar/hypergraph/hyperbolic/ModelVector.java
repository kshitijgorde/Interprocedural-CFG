// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

public interface ModelVector
{
    ModelPoint getBase();
    
    void setBase(final ModelPoint p0);
    
    void scale(final double p0);
    
    void setTo(final ModelVector p0);
    
    Object clone();
}
