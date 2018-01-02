// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

import java.util.List;

public interface IComputeNode
{
    int getID();
    
    void addDependency(final IComputeNode p0);
    
    void removeDependency(final IComputeNode p0);
    
    void clearDependencies();
    
    List<IComputeNode> getDependencies();
    
    boolean hasValue();
    
    boolean hasXHandle();
    
    boolean hasYHandle();
    
    void reset();
    
    void update();
    
    void setValue(final float p0);
    
    float getValue();
}
