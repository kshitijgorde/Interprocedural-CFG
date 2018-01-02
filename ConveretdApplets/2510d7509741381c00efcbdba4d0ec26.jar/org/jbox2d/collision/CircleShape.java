// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Mat22;
import org.jbox2d.common.XForm;
import org.jbox2d.common.Vec2;

public class CircleShape extends Shape
{
    public float m_radius;
    public Vec2 m_localPosition;
    
    public CircleShape(final ShapeDef def) {
        super(def);
        assert def.type == ShapeType.CIRCLE_SHAPE;
        final CircleDef circleDef = (CircleDef)def;
        this.m_type = ShapeType.CIRCLE_SHAPE;
        this.m_localPosition = circleDef.localPosition.clone();
        this.m_radius = circleDef.radius;
    }
    
    public void updateSweepRadius(final Vec2 center) {
        final Vec2 d = this.m_localPosition.sub(center);
        this.m_sweepRadius = d.length() + this.m_radius - 0.04f;
    }
    
    public boolean testPoint(final XForm transform, final Vec2 p) {
        final Vec2 center = transform.position.add(Mat22.mul(transform.R, this.m_localPosition));
        final Vec2 d = p.sub(center);
        return Vec2.dot(d, d) <= this.m_radius * this.m_radius;
    }
    
    public void computeAABB(final AABB aabb, final XForm transform) {
        final Vec2 p = transform.position.add(Mat22.mul(transform.R, this.m_localPosition));
        aabb.lowerBound.set(p.x - this.m_radius, p.y - this.m_radius);
        aabb.upperBound.set(p.x + this.m_radius, p.y + this.m_radius);
    }
    
    public void computeSweptAABB(final AABB aabb, final XForm transform1, final XForm transform2) {
        final Vec2 p1 = transform1.position.add(Mat22.mul(transform1.R, this.m_localPosition));
        final Vec2 p2 = transform2.position.add(Mat22.mul(transform2.R, this.m_localPosition));
        final Vec2 lower = Vec2.min(p1, p2);
        final Vec2 upper = Vec2.max(p1, p2);
        aabb.lowerBound.set(lower.x - this.m_radius, lower.y - this.m_radius);
        aabb.upperBound.set(upper.x + this.m_radius, upper.y + this.m_radius);
    }
    
    public void computeMass(final MassData massData) {
        massData.mass = this.m_density * 3.1415927f * this.m_radius * this.m_radius;
        massData.center = this.m_localPosition.clone();
        massData.I = massData.mass * (0.5f * this.m_radius * this.m_radius + Vec2.dot(this.m_localPosition, this.m_localPosition));
    }
    
    public float getRadius() {
        return this.m_radius;
    }
    
    public Vec2 getLocalPosition() {
        return this.m_localPosition.clone();
    }
}
