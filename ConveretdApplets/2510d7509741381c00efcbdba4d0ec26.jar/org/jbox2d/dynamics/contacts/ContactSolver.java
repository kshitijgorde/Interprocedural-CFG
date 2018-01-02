// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.common.Sweep;
import org.jbox2d.common.MathUtils;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.ManifoldPoint;
import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Mat22;
import org.jbox2d.collision.Manifold;
import java.util.ArrayList;
import java.util.List;
import org.jbox2d.dynamics.TimeStep;

public class ContactSolver
{
    public TimeStep m_step;
    public List<ContactConstraint> m_constraints;
    public int m_constraintCount;
    
    public ContactSolver(final TimeStep step, final Contact[] contacts, final int contactCount) {
        this.m_step = step;
        this.m_constraintCount = 0;
        for (int i = 0; i < contactCount; ++i) {
            assert contacts[i].isSolid();
            this.m_constraintCount += contacts[i].getManifoldCount();
        }
        this.m_constraints = new ArrayList<ContactConstraint>();
        for (int i = 0; i < this.m_constraintCount; ++i) {
            this.m_constraints.add(new ContactConstraint());
        }
        int count = 0;
        for (final Contact contact : contacts) {
            final Body b1 = contact.m_shape1.m_body;
            final Body b2 = contact.m_shape2.m_body;
            final int manifoldCount = contact.getManifoldCount();
            final List<Manifold> manifolds = contact.getManifolds();
            final float friction = contact.m_friction;
            final float restitution = contact.m_restitution;
            final Vec2 v1 = b1.m_linearVelocity.clone();
            final Vec2 v2 = b2.m_linearVelocity.clone();
            final float w1 = b1.m_angularVelocity;
            final float w2 = b2.m_angularVelocity;
            for (int k = 0; k < manifoldCount; ++k) {
                final Manifold manifold = manifolds.get(k);
                assert manifold.pointCount > 0 : "Manifold " + k + " has length 0";
                final Vec2 normal = manifold.normal.clone();
                assert count < this.m_constraintCount;
                final ContactConstraint c = this.m_constraints.get(count);
                c.body1 = b1;
                c.body2 = b2;
                c.manifold = manifold;
                c.normal = normal.clone();
                c.pointCount = manifold.pointCount;
                c.friction = friction;
                c.restitution = restitution;
                for (int l = 0; l < c.pointCount; ++l) {
                    final ManifoldPoint cp = manifold.points[l];
                    final ContactConstraintPoint ccp = c.points[l];
                    ccp.normalForce = cp.normalForce;
                    ccp.tangentForce = cp.tangentForce;
                    ccp.separation = cp.separation;
                    ccp.positionImpulse = 0.0f;
                    ccp.localAnchor1.set(cp.localPoint1);
                    ccp.localAnchor2.set(cp.localPoint2);
                    ccp.r1 = Mat22.mul(b1.m_xf.R, cp.localPoint1.sub(b1.getLocalCenter()));
                    ccp.r2 = Mat22.mul(b2.m_xf.R, cp.localPoint2.sub(b2.getLocalCenter()));
                    final float r1Sqr = ccp.r1.x * ccp.r1.x + ccp.r1.y * ccp.r1.y;
                    final float r2Sqr = ccp.r2.x * ccp.r2.x + ccp.r2.y * ccp.r2.y;
                    final float rn1 = Vec2.dot(ccp.r1, normal);
                    final float rn2 = Vec2.dot(ccp.r2, normal);
                    float kNormal = b1.m_invMass + b2.m_invMass;
                    kNormal += b1.m_invI * (r1Sqr - rn1 * rn1) + b2.m_invI * (r2Sqr - rn2 * rn2);
                    assert kNormal > 1.1920929E-7f;
                    ccp.normalMass = 1.0f / kNormal;
                    float kEqualized = b1.m_mass * b1.m_invMass + b2.m_mass * b2.m_invMass;
                    kEqualized += b1.m_mass * b1.m_invI * (r1Sqr - rn1 * rn1) + b2.m_mass * b2.m_invI * (r2Sqr - rn2 * rn2);
                    assert kEqualized > 1.1920929E-7f;
                    ccp.equalizedMass = 1.0f / kEqualized;
                    final Vec2 tangent = Vec2.cross(normal, 1.0f);
                    final float rt1 = Vec2.dot(ccp.r1, tangent);
                    final float rt2 = Vec2.dot(ccp.r2, tangent);
                    float kTangent = b1.m_invMass + b2.m_invMass;
                    kTangent += b1.m_invI * (r1Sqr - rt1 * rt1) + b2.m_invI * (r2Sqr - rt2 * rt2);
                    assert kTangent > 1.1920929E-7f;
                    ccp.tangentMass = 1.0f / kTangent;
                    ccp.velocityBias = 0.0f;
                    if (ccp.separation > 0.0f) {
                        ccp.velocityBias = -60.0f * ccp.separation;
                    }
                    final Vec2 buffer = Vec2.cross(w2, ccp.r2).subLocal(Vec2.cross(w1, ccp.r1)).addLocal(v2).subLocal(v1);
                    final float vRel = Vec2.dot(c.normal, buffer);
                    if (vRel < -1.0f) {
                        final ContactConstraintPoint contactConstraintPoint = ccp;
                        contactConstraintPoint.velocityBias += -c.restitution * vRel;
                    }
                }
                ++count;
            }
        }
        assert count == this.m_constraintCount;
    }
    
    public void initVelocityConstraints() {
        for (int i = 0; i < this.m_constraintCount; ++i) {
            final ContactConstraint c = this.m_constraints.get(i);
            final Body b1 = c.body1;
            final Body b2 = c.body2;
            final float invMass1 = b1.m_invMass;
            final float invI1 = b1.m_invI;
            final float invMass2 = b2.m_invMass;
            final float invI2 = b2.m_invI;
            final Vec2 normal = c.normal;
            final Vec2 tangent = Vec2.cross(normal, 1.0f);
            if (World.ENABLE_WARM_STARTING) {
                for (int j = 0; j < c.pointCount; ++j) {
                    final ContactConstraintPoint ccp = c.points[j];
                    final float px = this.m_step.dt * (ccp.normalForce * normal.x + ccp.tangentForce * tangent.x);
                    final float py = this.m_step.dt * (ccp.normalForce * normal.y + ccp.tangentForce * tangent.y);
                    final Body body = b1;
                    body.m_angularVelocity -= invI1 * (ccp.r1.x * py - ccp.r1.y * px);
                    final Vec2 linearVelocity = b1.m_linearVelocity;
                    linearVelocity.x -= px * invMass1;
                    final Vec2 linearVelocity2 = b1.m_linearVelocity;
                    linearVelocity2.y -= py * invMass1;
                    final Body body2 = b2;
                    body2.m_angularVelocity += invI2 * (ccp.r2.x * py - ccp.r2.y * px);
                    final Vec2 linearVelocity3 = b2.m_linearVelocity;
                    linearVelocity3.x += px * invMass2;
                    final Vec2 linearVelocity4 = b2.m_linearVelocity;
                    linearVelocity4.y += py * invMass2;
                }
            }
            else {
                for (int j = 0; j < c.pointCount; ++j) {
                    final ContactConstraintPoint ccp = c.points[j];
                    ccp.normalForce = 0.0f;
                    ccp.tangentForce = 0.0f;
                }
            }
        }
    }
    
    public void solveVelocityConstraints() {
        for (int i = 0; i < this.m_constraintCount; ++i) {
            final ContactConstraint c = this.m_constraints.get(i);
            final Body b1 = c.body1;
            final Body b2 = c.body2;
            float w1 = b1.m_angularVelocity;
            float w2 = b2.m_angularVelocity;
            final Vec2 v1 = b1.m_linearVelocity.clone();
            final Vec2 v2 = b2.m_linearVelocity.clone();
            final float invMass1 = b1.m_invMass;
            final float invI1 = b1.m_invI;
            final float invMass2 = b2.m_invMass;
            final float invI2 = b2.m_invI;
            final Vec2 normal = c.normal.clone();
            final Vec2 tangent = Vec2.cross(normal, 1.0f);
            final float friction = c.friction;
            for (int j = 0; j < c.pointCount; ++j) {
                final ContactConstraintPoint ccp = c.points[j];
                final Vec2 dv = v2.add(Vec2.cross(w2, ccp.r2));
                dv.subLocal(v1);
                dv.subLocal(Vec2.cross(w1, ccp.r1));
                final float vn = dv.x * normal.x + dv.y * normal.y;
                float lambda = -this.m_step.inv_dt * ccp.normalMass * (vn - ccp.velocityBias);
                final float newForce = Math.max(ccp.normalForce + lambda, 0.0f);
                lambda = newForce - ccp.normalForce;
                final Vec2 P = new Vec2(this.m_step.dt * lambda * normal.x, this.m_step.dt * lambda * normal.y);
                final Vec2 vec2 = v1;
                vec2.x -= invMass1 * P.x;
                final Vec2 vec3 = v1;
                vec3.y -= invMass1 * P.y;
                w1 -= invI1 * Vec2.cross(ccp.r1, P);
                final Vec2 vec4 = v2;
                vec4.x += invMass2 * P.x;
                final Vec2 vec5 = v2;
                vec5.y += invMass2 * P.y;
                w2 += invI2 * Vec2.cross(ccp.r2, P);
                ccp.normalForce = newForce;
            }
            for (int j = 0; j < c.pointCount; ++j) {
                final ContactConstraintPoint ccp = c.points[j];
                final Vec2 dv = v2.add(Vec2.cross(w2, ccp.r2));
                dv.subLocal(v1);
                dv.subLocal(Vec2.cross(w1, ccp.r1));
                final float vt = dv.x * tangent.x + dv.y * tangent.y;
                float lambda = this.m_step.inv_dt * ccp.tangentMass * -vt;
                final float maxFriction = friction * ccp.normalForce;
                final float newForce2 = MathUtils.clamp(ccp.tangentForce + lambda, -maxFriction, maxFriction);
                lambda = newForce2 - ccp.tangentForce;
                lambda *= this.m_step.dt;
                final float px = tangent.x * lambda;
                final float py = tangent.y * lambda;
                final Vec2 vec6 = v1;
                vec6.x -= px * invMass1;
                final Vec2 vec7 = v1;
                vec7.y -= py * invMass1;
                w1 -= invI1 * (ccp.r1.x * py - ccp.r1.y * px);
                final Vec2 vec8 = v2;
                vec8.x += px * invMass2;
                final Vec2 vec9 = v2;
                vec9.y += py * invMass2;
                w2 += invI2 * (ccp.r2.x * py - ccp.r2.y * px);
                ccp.tangentForce = newForce2;
            }
            b1.m_linearVelocity.set(v1);
            b1.m_angularVelocity = w1;
            b2.m_linearVelocity.set(v2);
            b2.m_angularVelocity = w2;
        }
    }
    
    public void finalizeVelocityConstraints() {
        for (int i = 0; i < this.m_constraintCount; ++i) {
            final ContactConstraint c = this.m_constraints.get(i);
            final Manifold m = c.manifold;
            for (int j = 0; j < c.pointCount; ++j) {
                m.points[j].normalForce = c.points[j].normalForce;
                m.points[j].tangentForce = c.points[j].tangentForce;
            }
        }
    }
    
    public boolean solvePositionConstraints(final float baumgarte) {
        float minSeparation = 0.0f;
        for (int i = 0; i < this.m_constraintCount; ++i) {
            final ContactConstraint c = this.m_constraints.get(i);
            final Body b1 = c.body1;
            final Body b2 = c.body2;
            final float invMass1 = b1.m_mass * b1.m_invMass;
            final float invI1 = b1.m_mass * b1.m_invI;
            final float invMass2 = b2.m_mass * b2.m_invMass;
            final float invI2 = b2.m_mass * b2.m_invI;
            final Vec2 normal = c.normal;
            for (int j = 0; j < c.pointCount; ++j) {
                final ContactConstraintPoint ccp = c.points[j];
                final Vec2 r1 = Mat22.mul(b1.m_xf.R, ccp.localAnchor1.sub(b1.getLocalCenter()));
                final Vec2 r2 = Mat22.mul(b2.m_xf.R, ccp.localAnchor2.sub(b2.getLocalCenter()));
                final float dpx = b2.m_sweep.c.x + r2.x - b1.m_sweep.c.x - r1.x;
                final float dpy = b2.m_sweep.c.y + r2.y - b1.m_sweep.c.y - r1.y;
                final float separation = dpx * normal.x + dpy * normal.y + ccp.separation;
                minSeparation = Math.min(minSeparation, separation);
                final float C = baumgarte * MathUtils.clamp(separation + 0.005f, -0.2f, 0.0f);
                float dImpulse = -ccp.equalizedMass * C;
                final float impulse0 = ccp.positionImpulse;
                ccp.positionImpulse = Math.max(impulse0 + dImpulse, 0.0f);
                dImpulse = ccp.positionImpulse - impulse0;
                final float impulsex = dImpulse * normal.x;
                final float impulsey = dImpulse * normal.y;
                final Vec2 c2 = b1.m_sweep.c;
                c2.x -= invMass1 * impulsex;
                final Vec2 c3 = b1.m_sweep.c;
                c3.y -= invMass1 * impulsey;
                final Sweep sweep = b1.m_sweep;
                sweep.a -= invI1 * (r1.x * impulsey - r1.y * impulsex);
                b1.synchronizeTransform();
                final Vec2 c4 = b2.m_sweep.c;
                c4.x += invMass2 * impulsex;
                final Vec2 c5 = b2.m_sweep.c;
                c5.y += invMass2 * impulsey;
                final Sweep sweep2 = b2.m_sweep;
                sweep2.a += invI2 * (r2.x * impulsey - r2.y * impulsex);
                b2.synchronizeTransform();
            }
        }
        return minSeparation >= -0.0075f;
    }
}
