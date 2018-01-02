// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;

public class ContactConstraint
{
    public ContactConstraintPoint[] points;
    public Vec2 normal;
    public Manifold manifold;
    public Body body1;
    public Body body2;
    public float friction;
    public float restitution;
    public int pointCount;
    
    public ContactConstraint() {
        this.points = new ContactConstraintPoint[2];
        for (int i = 0; i < 2; ++i) {
            this.points[i] = new ContactConstraintPoint();
        }
        this.pointCount = 0;
        this.normal = new Vec2();
        this.manifold = new Manifold();
    }
}
