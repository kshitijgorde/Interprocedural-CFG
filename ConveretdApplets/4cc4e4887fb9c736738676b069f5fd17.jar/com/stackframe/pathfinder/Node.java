// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

public interface Node<T extends Node<T>>
{
    double pathCostEstimate(final T p0);
    
    double traverseCost(final T p0);
    
    Iterable<T> neighbors();
}
