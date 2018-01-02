// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.dynamics.Body;
import org.jbox2d.common.XForm;
import org.jbox2d.collision.ContactID;
import org.jbox2d.collision.CollidePoly;
import org.jbox2d.collision.PolygonShape;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.collision.ManifoldPoint;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.collision.ShapeType;
import org.jbox2d.collision.Shape;
import org.jbox2d.collision.Manifold;

public class PolyContact extends Contact implements ContactCreateFcn
{
    Manifold m_manifold;
    
    public PolyContact(final Shape s1, final Shape s2) {
        super(s1, s2);
        assert this.m_shape1.m_type == ShapeType.POLYGON_SHAPE;
        assert this.m_shape2.m_type == ShapeType.POLYGON_SHAPE;
        this.m_manifold = new Manifold();
        this.m_manifoldCount = 0;
    }
    
    public PolyContact() {
        this.m_manifold = new Manifold();
        this.m_manifoldCount = 0;
    }
    
    public Contact clone() {
        final PolyContact newC = new PolyContact(this.m_shape1, this.m_shape2);
        if (this.m_manifold != null) {
            newC.m_manifold = new Manifold(this.m_manifold);
        }
        newC.m_manifoldCount = this.m_manifoldCount;
        newC.m_world = this.m_world;
        newC.m_toi = this.m_toi;
        newC.m_prev = this.m_prev;
        newC.m_next = this.m_next;
        newC.m_node1 = this.m_node1;
        newC.m_node2 = this.m_node2;
        newC.m_friction = this.m_friction;
        newC.m_restitution = this.m_restitution;
        newC.m_flags = this.m_flags;
        return newC;
    }
    
    public List<Manifold> getManifolds() {
        final List<Manifold> ret = new ArrayList<Manifold>();
        if (this.m_manifold != null) {
            ret.add(this.m_manifold);
        }
        return ret;
    }
    
    public Contact create(final Shape shape1, final Shape shape2) {
        return new PolyContact(shape1, shape2);
    }
    
    public void dumpManifoldPoints() {
        for (int i = 0; i < this.m_manifold.pointCount; ++i) {
            final ManifoldPoint mp = this.m_manifold.points[i];
            System.out.println("Manifold point dump: " + mp.normalForce + " " + mp.tangentForce);
        }
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
        CollidePoly.collidePoly(this.m_manifold, (PolygonShape)this.m_shape1, b1.m_xf, (PolygonShape)this.m_shape2, b2.m_xf);
        final boolean[] match = new boolean[2];
        if (this.m_manifold.pointCount > 0) {
            for (int i = 0; i < this.m_manifold.pointCount; ++i) {
                final ManifoldPoint cp = this.m_manifold.points[i];
                cp.normalForce = 0.0f;
                cp.tangentForce = 0.0f;
                boolean matched = false;
                final ContactID id = new ContactID(cp.id);
                for (int j = 0; j < m0.pointCount; ++j) {
                    if (!match[j]) {
                        final ManifoldPoint cp2 = m0.points[j];
                        final ContactID id2 = new ContactID(cp2.id);
                        final ContactID.Features features = id2.features;
                        features.flip &= 0xFFFFFFFD;
                        if (id2.features.isEqual(id.features)) {
                            match[j] = true;
                            cp.normalForce = cp2.normalForce;
                            cp.tangentForce = cp2.tangentForce;
                            matched = true;
                            break;
                        }
                    }
                }
                if (!matched) {
                    final ContactID.Features features2 = cp.id.features;
                    features2.flip |= 0x2;
                }
            }
            this.m_manifoldCount = 1;
        }
        else {
            this.m_manifoldCount = 0;
        }
        if (listener != null && m0.pointCount > 0) {
            final ContactPoint cp3 = new ContactPoint();
            cp3.shape1 = this.m_shape1;
            cp3.shape2 = this.m_shape2;
            cp3.normal = m0.normal.clone();
            for (int l = 0; l < m0.pointCount; ++l) {
                if (!match[l]) {
                    final ManifoldPoint mp0 = m0.points[l];
                    cp3.position = XForm.mul(b1.m_xf, mp0.localPoint1);
                    cp3.separation = mp0.separation;
                    cp3.normalForce = mp0.normalForce;
                    cp3.tangentForce = mp0.tangentForce;
                    cp3.id = new ContactID(mp0.id);
                    listener.remove(cp3);
                }
            }
        }
    }
}
