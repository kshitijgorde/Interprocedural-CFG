// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.dependency;

import java.util.List;
import java.util.Collection;

public interface IDependencySorter
{
    void add(final IDependency p0);
    
    void remove(final IDependency p0);
    
    int count();
    
    List<Object> sort(final Collection<Object> p0);
}
