// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.ContactID;
import org.jbox2d.common.XForm;
import org.jbox2d.collision.CollideCircle;
import org.jbox2d.collision.CircleShape;
import org.jbox2d.collision.PolygonShape;
import org.jbox2d.collision.ManifoldPoint;
import org.jbox2d.dynamics.ContactListener;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.collision.ShapeType;
import org.jbox2d.collision.Shape;
import org.jbox2d.collision.Manifold;

class PolyAndCircleContact extends Contact implements ContactCreateFcn
{
    Manifold m_manifold;
    
    public PolyAndCircleContact(final Shape s1, final Shape s2) {
        super(s1, s2);
        assert this.m_shape1.m_type == ShapeType.POLYGON_SHAPE;
        assert this.m_shape2.m_type == ShapeType.CIRCLE_SHAPE;
        this.m_manifold = new Manifold();
        this.m_manifoldCount = 0;
    }
    
    public PolyAndCircleContact() {
        this.m_manifold = new Manifold();
        this.m_manifoldCount = 0;
    }
    
    public Contact clone() {
        final PolyAndCircleContact newC = new PolyAndCircleContact(this.m_shape1, this.m_shape2);
        newC.m_manifold = new Manifold(this.m_manifold);
        newC.m_manifoldCount = this.m_manifoldCount;
        newC.m_world = this.m_world;
        newC.m_prev = this.m_prev;
        newC.m_next = this.m_next;
        newC.m_node1 = this.m_node1;
        newC.m_node2 = this.m_node2;
        newC.m_friction = this.m_friction;
        newC.m_restitution = this.m_restitution;
        newC.m_flags = this.m_flags;
        return newC;
    }
    
    public Contact create(final Shape shape1, final Shape shape2) {
        return new PolyAndCircleContact(shape1, shape2);
    }
    
    public List<Manifold> getManifolds() {
        final List<Manifold> ret = new ArrayList<Manifold>(1);
        if (this.m_manifold != null) {
            ret.add(this.m_manifold);
        }
        return ret;
    }
    
    public void evaluate(final ContactListener listener) {
        final Body b1 = this.m_shape1.getBody();
        final Body b2 = this.m_shape2.getBody();
        final Manifold m0 = new Manifold(this.m_manifold);
        for (int k = 0; k < this.m_manifold.pointCount; ++k) {
            m0.points[k] = new ManifoldPoint(this.m_manifold.points[k]);
            m0.points[k].normalForce = this.m_manifold.points[k].normalForce;
            m0.points[k].tangentForce = this.m_manifold.points[k].tangentForce;
            m0.points[k].separation = this.m_manifold.points[k].separation;
            m0.points[k].id.features.set(this.m_manifold.points[k].id.features);
        }
        m0.pointCount = this.m_manifold.pointCount;
        CollideCircle.collidePolygonAndCircle(this.m_manifold, (PolygonShape)this.m_shape1, b1.m_xf, (CircleShape)this.m_shape2, b2.m_xf);
        if (this.m_manifold.pointCount > 0) {
            this.m_manifoldCount = 1;
            if (m0.pointCount == 0) {
                final ContactID.Features features = this.m_manifold.points[0].id.features;
                features.flip |= 0x2;
            }
            else {
                final ContactID.Features features2 = this.m_manifold.points[0].id.features;
                features2.flip &= 0xFFFFFFFD;
            }
        }
        else {
            this.m_manifoldCount = 0;
            if (m0.pointCount > 0 && listener != null) {
                final ContactPoint cp = new ContactPoint();
                cp.shape1 = this.m_shape1;
                cp.shape2 = this.m_shape2;
                cp.normal = m0.normal;
                cp.position = XForm.mul(b1.m_xf, m0.points[0].localPoint1);
                cp.separation = m0.points[0].separation;
                cp.normalForce = m0.points[0].normalForce;
                cp.tangentForce = m0.points[0].tangentForce;
                cp.id = new ContactID(m0.points[0].id);
                listener.remove(cp);
            }
        }
    }
}
