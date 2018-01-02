// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.collision;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.XForm;
import org.jbox2d.dynamics.Body;

public abstract class Shape
{
    public int uid;
    private static int uidcount;
    public ShapeType m_type;
    public Shape m_next;
    public Body m_body;
    public float m_sweepRadius;
    public float m_density;
    public float m_friction;
    public float m_restitution;
    public int m_proxyId;
    public int m_categoryBits;
    public int m_maskBits;
    public int m_groupIndex;
    public boolean m_isSensor;
    public Object m_userData;
    
    static {
        Shape.uidcount = 0;
    }
    
    public Shape(final ShapeDef def) {
        this.uid = Shape.uidcount++;
        this.m_userData = def.userData;
        this.m_friction = def.friction;
        this.m_restitution = def.restitution;
        this.m_density = def.density;
        this.m_body = null;
        this.m_sweepRadius = 0.0f;
        this.m_next = null;
        this.m_proxyId = Integer.MAX_VALUE;
        this.m_categoryBits = def.categoryBits;
        this.m_maskBits = def.maskBits;
        this.m_groupIndex = def.groupIndex;
        this.m_isSensor = def.isSensor;
    }
    
    public ShapeType getType() {
        return this.m_type;
    }
    
    public boolean isSensor() {
        return this.m_isSensor;
    }
    
    public Object getUserData() {
        return this.m_userData;
    }
    
    public Body getBody() {
        return this.m_body;
    }
    
    public Shape getNext() {
        return this.m_next;
    }
    
    public float getSweepRadius() {
        return this.m_sweepRadius;
    }
    
    public abstract boolean testPoint(final XForm p0, final Vec2 p1);
    
    public abstract void computeAABB(final AABB p0, final XForm p1);
    
    public abstract void computeSweptAABB(final AABB p0, final XForm p1, final XForm p2);
    
    public abstract void computeMass(final MassData p0);
    
    public abstract void updateSweepRadius(final Vec2 p0);
    
    public boolean synchronize(final BroadPhase broadPhase, final XForm transform1, final XForm transform2) {
        if (this.m_proxyId == Integer.MAX_VALUE) {
            return false;
        }
        final AABB aabb = new AABB();
        this.computeSweptAABB(aabb, transform1, transform2);
        if (broadPhase.inRange(aabb)) {
            broadPhase.moveProxy(this.m_proxyId, aabb);
            return true;
        }
        return false;
    }
    
    public void resetProxy(final BroadPhase broadPhase, final XForm transform) {
        if (this.m_proxyId != Integer.MAX_VALUE) {
            broadPhase.destroyProxy(this.m_proxyId);
        }
        final AABB aabb = new AABB();
        this.computeAABB(aabb, transform);
        final boolean inRange = broadPhase.inRange(aabb);
        assert inRange;
        if (inRange) {
            this.m_proxyId = broadPhase.createProxy(aabb, this);
        }
        else {
            this.m_proxyId = Integer.MAX_VALUE;
        }
    }
    
    public static Shape create(final ShapeDef def) {
        if (def.type == ShapeType.CIRCLE_SHAPE) {
            return new CircleShape(def);
        }
        if (def.type == ShapeType.BOX_SHAPE || def.type == ShapeType.POLYGON_SHAPE) {
            return new PolygonShape(def);
        }
        assert false;
        return null;
    }
    
    public static void destroy(final Shape s) {
        s.destructor();
    }
    
    public void destructor() {
        assert this.m_proxyId == Integer.MAX_VALUE;
    }
    
    public void createProxy(final BroadPhase broadPhase, final XForm transform) {
        assert this.m_proxyId == Integer.MAX_VALUE;
        final AABB aabb = new AABB();
        this.computeAABB(aabb, transform);
        final boolean inRange = broadPhase.inRange(aabb);
        assert inRange;
        if (inRange) {
            this.m_proxyId = broadPhase.createProxy(aabb, this);
        }
        else {
            this.m_proxyId = Integer.MAX_VALUE;
        }
    }
    
    public void destroyProxy(final BroadPhase broadPhase) {
        if (this.m_proxyId != Integer.MAX_VALUE) {
            broadPhase.destroyProxy(this.m_proxyId);
            this.m_proxyId = Integer.MAX_VALUE;
        }
    }
}
