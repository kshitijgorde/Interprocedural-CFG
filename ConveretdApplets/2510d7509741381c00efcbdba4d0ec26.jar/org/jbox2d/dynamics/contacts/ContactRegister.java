// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.collision.ShapeType;

public class ContactRegister
{
    public ShapeType s1;
    public ShapeType s2;
    public ContactCreateFcn createFcn;
    public boolean primary;
}
