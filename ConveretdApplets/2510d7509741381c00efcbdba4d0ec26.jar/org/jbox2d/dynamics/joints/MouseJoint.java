// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.TimeStep;
import org.jbox2d.common.XForm;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.Vec2;

public class MouseJoint extends Joint
{
    public Vec2 m_localAnchor;
    public Vec2 m_target;
    public Vec2 m_force;
    public Mat22 m_mass;
    public Vec2 m_C;
    public float m_maxForce;
    public float m_beta;
    public float m_gamma;
    
    public MouseJoint(final MouseJointDef def) {
        super(def);
        this.m_force = new Vec2();
        this.m_target = new Vec2();
        this.m_C = new Vec2();
        this.m_mass = new Mat22();
        this.m_target = def.target;
        this.m_localAnchor = XForm.mulT(this.m_body2.m_xf, this.m_target);
        this.m_maxForce = def.maxForce;
        final float mass = this.m_body2.m_mass;
        final float omega = 6.2831855f * def.frequencyHz;
        final float d = 2.0f * mass * def.dampingRatio * omega;
        final float k = mass * omega * omega;
        this.m_gamma = 1.0f / (d + def.timeStep * k);
        this.m_beta = def.timeStep * k / (d + def.timeStep * k);
    }
    
    public void setTarget(final Vec2 target) {
        if (this.m_body2.isSleeping()) {
            this.m_body2.wakeUp();
        }
        this.m_target = target;
    }
    
    public Vec2 getAnchor1() {
        return this.m_target;
    }
    
    public Vec2 getAnchor2() {
        return this.m_body2.getWorldPoint(this.m_localAnchor);
    }
    
    public void initVelocityConstraints(final TimeStep step) {
        final Body b = this.m_body2;
        final Vec2 r = Mat22.mul(b.m_xf.R, this.m_localAnchor.sub(b.getLocalCenter()));
        final float invMass = b.m_invMass;
        final float invI = b.m_invI;
        final Mat22 K1 = new Mat22(invMass, 0.0f, 0.0f, invMass);
        final Mat22 K2 = new Mat22(invI * r.y * r.y, -invI * r.x * r.y, -invI * r.x * r.y, invI * r.x * r.x);
        final Mat22 K3 = K1.add(K2);
        final Vec2 col1 = K3.col1;
        col1.x += this.m_gamma;
        final Vec2 col2 = K3.col2;
        col2.y += this.m_gamma;
        this.m_mass.set(K3);
        this.m_mass = this.m_mass.invert();
        this.m_C.set(b.m_sweep.c.x + r.x - this.m_target.x, b.m_sweep.c.y + r.y - this.m_target.y);
        final Body body = b;
        body.m_angularVelocity *= 0.98f;
        final float Px = step.dt * this.m_force.x;
        final float Py = step.dt * this.m_force.y;
        final Vec2 linearVelocity = b.m_linearVelocity;
        linearVelocity.x += invMass * Px;
        final Vec2 linearVelocity2 = b.m_linearVelocity;
        linearVelocity2.y += invMass * Py;
        final Body body2 = b;
        body2.m_angularVelocity += invI * (r.x * Py - r.y * Px);
    }
    
    public boolean solvePositionConstraints() {
        return true;
    }
    
    public void solveVelocityConstraints(final TimeStep step) {
        final Body b = this.m_body2;
        final Vec2 r = Mat22.mul(b.m_xf.R, this.m_localAnchor.sub(b.getLocalCenter()));
        final Vec2 Cdot = b.m_linearVelocity.add(Vec2.cross(b.m_angularVelocity, r));
        Vec2 force = new Vec2(Cdot.x + this.m_beta * step.inv_dt * this.m_C.x + this.m_gamma * step.dt * this.m_force.x, Cdot.y + this.m_beta * step.inv_dt * this.m_C.y + this.m_gamma * step.dt * this.m_force.y);
        force = Mat22.mul(this.m_mass, force);
        force.mulLocal(-step.inv_dt);
        final Vec2 oldForce = this.m_force.clone();
        this.m_force.addLocal(force);
        final float forceMagnitude = this.m_force.length();
        if (forceMagnitude > this.m_maxForce) {
            this.m_force.mulLocal(this.m_maxForce / forceMagnitude);
        }
        force.set(this.m_force.x - oldForce.x, this.m_force.y - oldForce.y);
        final Vec2 P = new Vec2(step.dt * force.x, step.dt * force.y);
        b.m_linearVelocity.addLocal(P.mul(b.m_invMass));
        final Body body = b;
        body.m_angularVelocity += b.m_invI * Vec2.cross(r, P);
    }
    
    public Vec2 getReactionForce() {
        return this.m_force;
    }
    
    public float getReactionTorque() {
        return 0.0f;
    }
}
