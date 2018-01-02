// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.collision.ContactID;
import org.jbox2d.common.Vec2;
import org.jbox2d.collision.Shape;

public class ContactPoint
{
    public Shape shape1;
    public Shape shape2;
    public Vec2 position;
    public Vec2 normal;
    public float separation;
    public float normalForce;
    public float tangentForce;
    public ContactID id;
    
    public ContactPoint() {
        this.position = new Vec2();
        this.normal = new Vec2();
    }
}
