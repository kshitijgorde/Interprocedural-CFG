// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.dynamics.Body;

public class ContactEdge
{
    public Body other;
    public Contact contact;
    public ContactEdge prev;
    public ContactEdge next;
}
