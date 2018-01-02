// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

public abstract class PairCallback
{
    public abstract Object pairAdded(final Object p0, final Object p1);
    
    public abstract void pairRemoved(final Object p0, final Object p1, final Object p2);
}
