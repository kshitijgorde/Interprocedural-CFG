// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.common.Sweep;
import org.jbox2d.common.MathUtils;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.common.Mat22;
import org.jbox2d.dynamics.TimeStep;
import org.jbox2d.common.Vec2;

public class DistanceJoint extends Joint
{
    public Vec2 m_localAnchor1;
    public Vec2 m_localAnchor2;
    public Vec2 m_u;
    public float m_force;
    public float m_mass;
    public float m_length;
    
    public DistanceJoint(final DistanceJointDef def) {
        super(def);
        this.m_localAnchor1 = def.localAnchor1;
        this.m_localAnchor2 = def.localAnchor2;
        this.m_length = def.length;
        this.m_force = 0.0f;
        this.m_u = new Vec2();
    }
    
    public Vec2 getAnchor1() {
        return this.m_body1.getWorldPoint(this.m_localAnchor1);
    }
    
    public Vec2 getAnchor2() {
        return this.m_body2.getWorldPoint(this.m_localAnchor2);
    }
    
    public Vec2 getReactionForce() {
        return new Vec2(this.m_force * this.m_u.x, this.m_force * this.m_u.y);
    }
    
    public float getReactionTorque() {
        return 0.0f;
    }
    
    public void initVelocityConstraints(final TimeStep step) {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        this.m_u.x = b2.m_sweep.c.x + r2.x - b1.m_sweep.c.x - r1.x;
        this.m_u.y = b2.m_sweep.c.y + r2.y - b1.m_sweep.c.y - r1.y;
        final float length = this.m_u.length();
        if (length > 0.005f) {
            final Vec2 u = this.m_u;
            u.x *= 1.0f / length;
            final Vec2 u2 = this.m_u;
            u2.y *= 1.0f / length;
        }
        else {
            this.m_u.set(0.0f, 0.0f);
        }
        final float cr1u = Vec2.cross(r1, this.m_u);
        final float cr2u = Vec2.cross(r2, this.m_u);
        this.m_mass = b1.m_invMass + b1.m_invI * cr1u * cr1u + b2.m_invMass + b2.m_invI * cr2u * cr2u;
        assert this.m_mass > 1.1920929E-7f;
        this.m_mass = 1.0f / this.m_mass;
        if (World.ENABLE_WARM_STARTING) {
            final float Px = step.dt * this.m_force * this.m_u.x;
            final float Py = step.dt * this.m_force * this.m_u.y;
            final Vec2 linearVelocity = b1.m_linearVelocity;
            linearVelocity.x -= b1.m_invMass * Px;
            final Vec2 linearVelocity2 = b1.m_linearVelocity;
            linearVelocity2.y -= b1.m_invMass * Py;
            final Body body = b1;
            body.m_angularVelocity -= b1.m_invI * (r1.x * Py - r1.y * Px);
            final Vec2 linearVelocity3 = b2.m_linearVelocity;
            linearVelocity3.x += b2.m_invMass * Px;
            final Vec2 linearVelocity4 = b2.m_linearVelocity;
            linearVelocity4.y += b2.m_invMass * Py;
            final Body body2 = b2;
            body2.m_angularVelocity += b2.m_invI * (r2.x * Py - r2.y * Px);
        }
        else {
            this.m_force = 0.0f;
        }
    }
    
    public boolean solvePositionConstraints() {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        final Vec2 d = new Vec2(b2.m_sweep.c.x + r2.x - b1.m_sweep.c.x - r1.x, b2.m_sweep.c.y + r2.y - b1.m_sweep.c.y - r1.y);
        final float length = d.normalize();
        float C = length - this.m_length;
        C = MathUtils.clamp(C, -0.2f, 0.2f);
        final float impulse = -this.m_mass * C;
        this.m_u = d;
        final float Px = impulse * this.m_u.x;
        final float Py = impulse * this.m_u.y;
        final Vec2 c = b1.m_sweep.c;
        c.x -= b1.m_invMass * Px;
        final Vec2 c2 = b1.m_sweep.c;
        c2.y -= b1.m_invMass * Py;
        final Sweep sweep = b1.m_sweep;
        sweep.a -= b1.m_invI * (r1.x * Py - r1.y * Px);
        final Vec2 c3 = b2.m_sweep.c;
        c3.x += b2.m_invMass * Px;
        final Vec2 c4 = b2.m_sweep.c;
        c4.y += b2.m_invMass * Py;
        final Sweep sweep2 = b2.m_sweep;
        sweep2.a += b2.m_invI * (r2.x * Py - r2.y * Px);
        b1.synchronizeTransform();
        b2.synchronizeTransform();
        return Math.abs(C) < 0.005f;
    }
    
    public void solveVelocityConstraints(final TimeStep step) {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        final Vec2 v1 = b1.m_linearVelocity.add(Vec2.cross(b1.m_angularVelocity, r1));
        final Vec2 v2 = b2.m_linearVelocity.add(Vec2.cross(b2.m_angularVelocity, r2));
        final float Cdot = Vec2.dot(this.m_u, v2.subLocal(v1));
        final float force = -step.inv_dt * this.m_mass * Cdot;
        this.m_force += force;
        final float Px = step.dt * force * this.m_u.x;
        final float Py = step.dt * force * this.m_u.y;
        final Vec2 linearVelocity = b1.m_linearVelocity;
        linearVelocity.x -= b1.m_invMass * Px;
        final Vec2 linearVelocity2 = b1.m_linearVelocity;
        linearVelocity2.y -= b1.m_invMass * Py;
        final Body body = b1;
        body.m_angularVelocity -= b1.m_invI * (r1.x * Py - r1.y * Px);
        final Vec2 linearVelocity3 = b2.m_linearVelocity;
        linearVelocity3.x += b2.m_invMass * Px;
        final Vec2 linearVelocity4 = b2.m_linearVelocity;
        linearVelocity4.y += b2.m_invMass * Py;
        final Body body2 = b2;
        body2.m_angularVelocity += b2.m_invI * (r2.x * Py - r2.y * Px);
    }
}
