// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.common.Sweep;
import org.jbox2d.dynamics.World;
import org.jbox2d.common.Mat22;
import org.jbox2d.dynamics.TimeStep;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class GearJoint extends Joint
{
    public Body m_ground1;
    public Body m_ground2;
    public RevoluteJoint m_revolute1;
    public PrismaticJoint m_prismatic1;
    public RevoluteJoint m_revolute2;
    public PrismaticJoint m_prismatic2;
    public Vec2 m_groundAnchor1;
    public Vec2 m_groundAnchor2;
    public Vec2 m_localAnchor1;
    public Vec2 m_localAnchor2;
    public Jacobian m_J;
    public float m_constant;
    public float m_ratio;
    float m_mass;
    float m_force;
    
    public GearJoint(final GearJointDef def) {
        super(def);
        this.m_J = new Jacobian();
        assert def.joint1.m_type == JointType.PRISMATIC_JOINT;
        assert def.joint2.m_type == JointType.PRISMATIC_JOINT;
        assert def.joint1.m_body1.isStatic();
        assert def.joint2.m_body1.isStatic();
        this.m_revolute1 = null;
        this.m_prismatic1 = null;
        this.m_revolute2 = null;
        this.m_prismatic2 = null;
        this.m_ground1 = def.joint1.m_body1;
        this.m_body1 = def.joint1.m_body2;
        float coordinate1;
        if (def.joint1.m_type == JointType.REVOLUTE_JOINT) {
            this.m_revolute1 = (RevoluteJoint)def.joint1;
            this.m_groundAnchor1 = this.m_revolute1.m_localAnchor1;
            this.m_localAnchor1 = this.m_revolute1.m_localAnchor2;
            coordinate1 = this.m_revolute1.getJointAngle();
        }
        else {
            this.m_prismatic1 = (PrismaticJoint)def.joint1;
            this.m_groundAnchor1 = this.m_prismatic1.m_localAnchor1;
            this.m_localAnchor1 = this.m_prismatic1.m_localAnchor2;
            coordinate1 = this.m_prismatic1.getJointTranslation();
        }
        this.m_ground2 = def.joint2.m_body1;
        this.m_body2 = def.joint2.m_body2;
        float coordinate2;
        if (def.joint2.m_type == JointType.REVOLUTE_JOINT) {
            this.m_revolute2 = (RevoluteJoint)def.joint2;
            this.m_groundAnchor2 = this.m_revolute2.m_localAnchor1;
            this.m_localAnchor2 = this.m_revolute2.m_localAnchor2;
            coordinate2 = this.m_revolute2.getJointAngle();
        }
        else {
            this.m_prismatic2 = (PrismaticJoint)def.joint2;
            this.m_groundAnchor2 = this.m_prismatic2.m_localAnchor1;
            this.m_localAnchor2 = this.m_prismatic2.m_localAnchor2;
            coordinate2 = this.m_prismatic2.getJointTranslation();
        }
        this.m_ratio = def.ratio;
        this.m_constant = coordinate1 + this.m_ratio * coordinate2;
        this.m_force = 0.0f;
    }
    
    public void initVelocityConstraints(final TimeStep step) {
        final Body g1 = this.m_ground1;
        final Body g2 = this.m_ground2;
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        float K = 0.0f;
        this.m_J.setZero();
        if (this.m_revolute1 != null) {
            this.m_J.angular1 = -1.0f;
            K += b1.m_invI;
        }
        else {
            final Vec2 ug = Mat22.mul(g1.m_xf.R, this.m_prismatic1.m_localXAxis1);
            final Vec2 r = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
            final float crug = Vec2.cross(r, ug);
            this.m_J.linear1 = ug.negate();
            this.m_J.angular1 = -crug;
            K += b1.m_invMass + b1.m_invI * crug * crug;
        }
        if (this.m_revolute2 != null) {
            this.m_J.angular2 = -this.m_ratio;
            K += this.m_ratio * this.m_ratio * b2.m_invI;
        }
        else {
            final Vec2 ug = Mat22.mul(g2.m_xf.R, this.m_prismatic2.m_localXAxis1);
            final Vec2 r = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
            final float crug = Vec2.cross(r, ug);
            this.m_J.linear2 = ug.mulLocal(-this.m_ratio);
            this.m_J.angular2 = -this.m_ratio * crug;
            K += this.m_ratio * this.m_ratio * (b2.m_invMass + b2.m_invI * crug * crug);
        }
        assert K > 0.0f;
        this.m_mass = 1.0f / K;
        if (World.ENABLE_WARM_STARTING) {
            final float P = step.dt * this.m_force;
            final Vec2 linearVelocity = b1.m_linearVelocity;
            linearVelocity.x += b1.m_invMass * P * this.m_J.linear1.x;
            final Vec2 linearVelocity2 = b1.m_linearVelocity;
            linearVelocity2.y += b1.m_invMass * P * this.m_J.linear1.y;
            final Body body = b1;
            body.m_angularVelocity += b1.m_invI * P * this.m_J.angular1;
            final Vec2 linearVelocity3 = b2.m_linearVelocity;
            linearVelocity3.x += b2.m_invMass * P * this.m_J.linear2.x;
            final Vec2 linearVelocity4 = b2.m_linearVelocity;
            linearVelocity4.y += b2.m_invMass * P * this.m_J.linear2.y;
            final Body body2 = b2;
            body2.m_angularVelocity += b2.m_invI * P * this.m_J.angular2;
        }
        else {
            this.m_force = 0.0f;
        }
    }
    
    public void solveVelocityConstraints(final TimeStep step) {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final float Cdot = this.m_J.compute(b1.m_linearVelocity, b1.m_angularVelocity, b2.m_linearVelocity, b2.m_angularVelocity);
        final float force = -step.inv_dt * this.m_mass * Cdot;
        this.m_force += force;
        final float P = step.dt * force;
        final Vec2 linearVelocity = b1.m_linearVelocity;
        linearVelocity.x += b1.m_invMass * P * this.m_J.linear1.x;
        final Vec2 linearVelocity2 = b1.m_linearVelocity;
        linearVelocity2.y += b1.m_invMass * P * this.m_J.linear1.y;
        final Body body = b1;
        body.m_angularVelocity += b1.m_invI * P * this.m_J.angular1;
        final Vec2 linearVelocity3 = b2.m_linearVelocity;
        linearVelocity3.x += b2.m_invMass * P * this.m_J.linear2.x;
        final Vec2 linearVelocity4 = b2.m_linearVelocity;
        linearVelocity4.y += b2.m_invMass * P * this.m_J.linear2.y;
        final Body body2 = b2;
        body2.m_angularVelocity += b2.m_invI * P * this.m_J.angular2;
    }
    
    public boolean solvePositionConstraints() {
        final float linearError = 0.0f;
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        float coordinate1;
        if (this.m_revolute1 != null) {
            coordinate1 = this.m_revolute1.getJointAngle();
        }
        else {
            coordinate1 = this.m_prismatic1.getJointTranslation();
        }
        float coordinate2;
        if (this.m_revolute2 != null) {
            coordinate2 = this.m_revolute2.getJointAngle();
        }
        else {
            coordinate2 = this.m_prismatic2.getJointTranslation();
        }
        final float C = this.m_constant - (coordinate1 + this.m_ratio * coordinate2);
        final float impulse = -this.m_mass * C;
        final Vec2 c = b1.m_sweep.c;
        c.x += b1.m_invMass * impulse * this.m_J.linear1.x;
        final Vec2 c2 = b1.m_sweep.c;
        c2.y += b1.m_invMass * impulse * this.m_J.linear1.y;
        final Sweep sweep = b1.m_sweep;
        sweep.a += b1.m_invI * impulse * this.m_J.angular1;
        final Vec2 c3 = b2.m_sweep.c;
        c3.x += b2.m_invMass * impulse * this.m_J.linear2.x;
        final Vec2 c4 = b2.m_sweep.c;
        c4.y += b2.m_invMass * impulse * this.m_J.linear2.y;
        final Sweep sweep2 = b2.m_sweep;
        sweep2.a += b2.m_invI * impulse * this.m_J.angular2;
        b1.synchronizeTransform();
        b2.synchronizeTransform();
        return linearError < 0.005f;
    }
    
    public Vec2 getAnchor1() {
        return this.m_body1.getWorldPoint(this.m_localAnchor1);
    }
    
    public Vec2 getAnchor2() {
        return this.m_body2.getWorldPoint(this.m_localAnchor2);
    }
    
    public Vec2 getReactionForce() {
        return new Vec2(this.m_force * this.m_J.linear2.x, this.m_force * this.m_J.linear2.y);
    }
    
    public float getReactionTorque() {
        final Vec2 r = Mat22.mul(this.m_body2.m_xf.R, this.m_localAnchor2.sub(this.m_body2.getLocalCenter()));
        final Vec2 F = new Vec2(this.m_force * this.m_J.linear2.x, this.m_force * this.m_J.linear2.y);
        final float T = this.m_force * this.m_J.angular2 - Vec2.cross(r, F);
        return T;
    }
    
    public float getRatio() {
        return this.m_ratio;
    }
}
