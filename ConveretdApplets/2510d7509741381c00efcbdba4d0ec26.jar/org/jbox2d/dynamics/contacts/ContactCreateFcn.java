// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.collision.Shape;

public interface ContactCreateFcn
{
    Contact create(final Shape p0, final Shape p1);
}
