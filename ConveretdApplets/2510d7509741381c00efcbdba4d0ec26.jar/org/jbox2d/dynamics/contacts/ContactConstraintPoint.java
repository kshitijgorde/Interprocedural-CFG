// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.common.Vec2;

public class ContactConstraintPoint
{
    public Vec2 localAnchor1;
    public Vec2 localAnchor2;
    public Vec2 r1;
    public Vec2 r2;
    public float normalForce;
    public float tangentForce;
    public float positionImpulse;
    public float normalMass;
    public float tangentMass;
    public float equalizedMass;
    public float separation;
    public float velocityBias;
    
    public ContactConstraintPoint() {
        this.localAnchor1 = new Vec2();
        this.localAnchor2 = new Vec2();
        this.r1 = new Vec2();
        this.r2 = new Vec2();
    }
}
