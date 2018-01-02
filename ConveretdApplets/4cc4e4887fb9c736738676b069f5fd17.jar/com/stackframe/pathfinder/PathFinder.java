// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import java.util.List;
import java.util.Collection;

public interface PathFinder<T extends Node>
{
    void cancel();
    
    List<T> findPath(final Collection<T> p0, final T p1, final Collection<T> p2);
    
    void addPathListener(final PathListener<T> p0);
    
    void removePathListener(final PathListener<T> p0);
    
    String name();
}
