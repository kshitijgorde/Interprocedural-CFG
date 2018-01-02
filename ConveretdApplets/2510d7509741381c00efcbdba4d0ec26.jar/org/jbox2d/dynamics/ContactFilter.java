// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.collision.Shape;

public interface ContactFilter
{
    public static final ContactFilter DEFAULT_FILTER = new DefaultContactFilter();
    
    boolean shouldCollide(final Shape p0, final Shape p1);
}
