// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Vec2;

public class ManifoldPoint
{
    public Vec2 localPoint1;
    public Vec2 localPoint2;
    public float separation;
    public float normalForce;
    public float tangentForce;
    public ContactID id;
    
    public ManifoldPoint() {
        this.localPoint1 = new Vec2();
        this.localPoint2 = new Vec2();
        final float separation = 0.0f;
        this.tangentForce = separation;
        this.normalForce = separation;
        this.separation = separation;
        this.id = new ContactID();
    }
    
    public ManifoldPoint(final ManifoldPoint cp) {
        this.localPoint1 = cp.localPoint1.clone();
        this.localPoint2 = cp.localPoint2.clone();
        this.separation = cp.separation;
        this.normalForce = cp.normalForce;
        this.tangentForce = cp.tangentForce;
        this.id = new ContactID(cp.id);
    }
}
