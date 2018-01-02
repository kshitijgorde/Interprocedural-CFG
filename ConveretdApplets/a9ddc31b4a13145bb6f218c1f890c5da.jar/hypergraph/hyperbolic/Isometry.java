// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

public interface Isometry
{
    void setToIdentity();
    
    void multiplyRight(final Isometry p0);
    
    void multiplyLeft(final Isometry p0);
    
    void apply(final ModelPoint p0);
    
    void apply(final ModelVector p0);
    
    Isometry getInvers();
    
    void invert();
    
    void setTo(final Isometry p0);
    
    Object clone();
    
    String toString();
}
