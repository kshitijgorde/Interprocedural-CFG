// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.dynamics.contacts.ContactPoint;

public interface ContactListener
{
    void add(final ContactPoint p0);
    
    void persist(final ContactPoint p0);
    
    void remove(final ContactPoint p0);
}
