// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.common.Sweep;
import org.jbox2d.common.MathUtils;
import org.jbox2d.dynamics.World;
import org.jbox2d.common.Mat22;
import org.jbox2d.dynamics.TimeStep;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class PulleyJoint extends Joint
{
    public static final float MIN_PULLEY_LENGTH = 2.0f;
    public Body m_ground;
    public Vec2 m_groundAnchor1;
    public Vec2 m_groundAnchor2;
    public Vec2 m_localAnchor1;
    public Vec2 m_localAnchor2;
    public Vec2 m_u1;
    public Vec2 m_u2;
    public float m_constant;
    public float m_ratio;
    public float m_maxLength1;
    public float m_maxLength2;
    public float m_pulleyMass;
    public float m_limitMass1;
    public float m_limitMass2;
    public float m_force;
    public float m_limitForce1;
    public float m_limitForce2;
    public float m_positionImpulse;
    public float m_limitPositionImpulse1;
    public float m_limitPositionImpulse2;
    public LimitState m_state;
    public LimitState m_limitState1;
    public LimitState m_limitState2;
    
    public PulleyJoint(final PulleyJointDef def) {
        super(def);
        this.m_ground = this.m_body1.m_world.m_groundBody;
        this.m_groundAnchor1 = def.groundAnchor1.sub(this.m_ground.m_xf.position);
        this.m_groundAnchor2 = def.groundAnchor2.sub(this.m_ground.m_xf.position);
        this.m_localAnchor1 = def.localAnchor1.clone();
        this.m_localAnchor2 = def.localAnchor2.clone();
        this.m_u1 = new Vec2();
        this.m_u2 = new Vec2();
        assert def.ratio != 0.0f;
        this.m_ratio = def.ratio;
        this.m_constant = def.length1 + this.m_ratio * def.length2;
        this.m_maxLength1 = Math.min(def.maxLength1, this.m_constant - this.m_ratio * 2.0f);
        this.m_maxLength2 = Math.min(def.maxLength2, (this.m_constant - 2.0f) / this.m_ratio);
        this.m_force = 0.0f;
        this.m_limitForce1 = 0.0f;
        this.m_limitForce2 = 0.0f;
    }
    
    public void initVelocityConstraints(final TimeStep step) {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        final Vec2 p1 = b1.m_sweep.c.add(r1);
        final Vec2 p2 = b2.m_sweep.c.add(r2);
        final Vec2 s1 = this.m_ground.m_xf.position.add(this.m_groundAnchor1);
        final Vec2 s2 = this.m_ground.m_xf.position.add(this.m_groundAnchor2);
        this.m_u1 = p1.sub(s1);
        this.m_u2 = p2.sub(s2);
        final float length1 = this.m_u1.length();
        final float length2 = this.m_u2.length();
        if (length1 > 0.005f) {
            this.m_u1.mulLocal(1.0f / length1);
        }
        else {
            this.m_u1.setZero();
        }
        if (length2 > 0.005f) {
            this.m_u2.mulLocal(1.0f / length2);
        }
        else {
            this.m_u2.setZero();
        }
        final float C = this.m_constant - length1 - this.m_ratio * length2;
        if (C > 0.0f) {
            this.m_state = LimitState.INACTIVE_LIMIT;
            this.m_force = 0.0f;
        }
        else {
            this.m_state = LimitState.AT_UPPER_LIMIT;
            this.m_positionImpulse = 0.0f;
        }
        if (length1 < this.m_maxLength1) {
            this.m_limitState1 = LimitState.INACTIVE_LIMIT;
            this.m_limitForce1 = 0.0f;
        }
        else {
            this.m_limitState1 = LimitState.AT_UPPER_LIMIT;
            this.m_limitPositionImpulse1 = 0.0f;
        }
        if (length2 < this.m_maxLength2) {
            this.m_limitState2 = LimitState.INACTIVE_LIMIT;
            this.m_limitForce2 = 0.0f;
        }
        else {
            this.m_limitState2 = LimitState.AT_UPPER_LIMIT;
            this.m_limitPositionImpulse2 = 0.0f;
        }
        final float cr1u1 = Vec2.cross(r1, this.m_u1);
        final float cr2u2 = Vec2.cross(r2, this.m_u2);
        this.m_limitMass1 = b1.m_invMass + b1.m_invI * cr1u1 * cr1u1;
        this.m_limitMass2 = b2.m_invMass + b2.m_invI * cr2u2 * cr2u2;
        this.m_pulleyMass = this.m_limitMass1 + this.m_ratio * this.m_ratio * this.m_limitMass2;
        assert this.m_limitMass1 > 1.1920929E-7f;
        assert this.m_limitMass2 > 1.1920929E-7f;
        assert this.m_pulleyMass > 1.1920929E-7f;
        this.m_limitMass1 = 1.0f / this.m_limitMass1;
        this.m_limitMass2 = 1.0f / this.m_limitMass2;
        this.m_pulleyMass = 1.0f / this.m_pulleyMass;
        if (World.ENABLE_WARM_STARTING) {
            final Vec2 P1 = this.m_u1.mul(step.dt * (-this.m_force - this.m_limitForce1));
            final Vec2 P2 = this.m_u2.mul(step.dt * (-this.m_ratio * this.m_force - this.m_limitForce2));
            b1.m_linearVelocity.addLocal(P1.mul(b1.m_invMass));
            final Body body = b1;
            body.m_angularVelocity += b1.m_invI * Vec2.cross(r1, P1);
            b2.m_linearVelocity.addLocal(P2.mul(b2.m_invMass));
            final Body body2 = b2;
            body2.m_angularVelocity += b2.m_invI * Vec2.cross(r2, P2);
        }
        else {
            this.m_force = 0.0f;
            this.m_limitForce1 = 0.0f;
            this.m_limitForce2 = 0.0f;
        }
    }
    
    public void solveVelocityConstraints(final TimeStep step) {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        if (this.m_state == LimitState.AT_UPPER_LIMIT) {
            final Vec2 v1 = b1.m_linearVelocity.add(Vec2.cross(b1.m_angularVelocity, r1));
            final Vec2 v2 = b2.m_linearVelocity.add(Vec2.cross(b2.m_angularVelocity, r2));
            final float Cdot = -Vec2.dot(this.m_u1, v1) - this.m_ratio * Vec2.dot(this.m_u2, v2);
            float force = -step.inv_dt * this.m_pulleyMass * Cdot;
            final float oldForce = this.m_force;
            this.m_force = Math.max(0.0f, this.m_force + force);
            force = this.m_force - oldForce;
            final Vec2 P1 = this.m_u1.mul(-step.dt * force);
            final Vec2 P2 = this.m_u2.mul(-step.dt * this.m_ratio * force);
            final Vec2 linearVelocity = b1.m_linearVelocity;
            linearVelocity.x += b1.m_invMass * P1.x;
            final Vec2 linearVelocity2 = b1.m_linearVelocity;
            linearVelocity2.y += b1.m_invMass * P1.y;
            final Body body = b1;
            body.m_angularVelocity += b1.m_invI * Vec2.cross(r1, P1);
            final Vec2 linearVelocity3 = b2.m_linearVelocity;
            linearVelocity3.x += b2.m_invMass * P2.x;
            final Vec2 linearVelocity4 = b2.m_linearVelocity;
            linearVelocity4.y += b2.m_invMass * P2.y;
            final Body body2 = b2;
            body2.m_angularVelocity += b2.m_invI * Vec2.cross(r2, P2);
        }
        if (this.m_limitState1 == LimitState.AT_UPPER_LIMIT) {
            final Vec2 v1 = b1.m_linearVelocity.add(Vec2.cross(b1.m_angularVelocity, r1));
            final float Cdot2 = -Vec2.dot(this.m_u1, v1);
            float force2 = -step.inv_dt * this.m_limitMass1 * Cdot2;
            final float oldForce2 = this.m_limitForce1;
            this.m_limitForce1 = Math.max(0.0f, this.m_limitForce1 + force2);
            force2 = this.m_limitForce1 - oldForce2;
            final Vec2 P3 = this.m_u1.mul(-step.dt * force2);
            final Vec2 linearVelocity5 = b1.m_linearVelocity;
            linearVelocity5.x += b1.m_invMass * P3.x;
            final Vec2 linearVelocity6 = b1.m_linearVelocity;
            linearVelocity6.y += b1.m_invMass * P3.y;
            final Body body3 = b1;
            body3.m_angularVelocity += b1.m_invI * Vec2.cross(r1, P3);
        }
        if (this.m_limitState2 == LimitState.AT_UPPER_LIMIT) {
            final Vec2 v3 = b2.m_linearVelocity.add(Vec2.cross(b2.m_angularVelocity, r2));
            final float Cdot2 = -Vec2.dot(this.m_u2, v3);
            float force2 = -step.inv_dt * this.m_limitMass2 * Cdot2;
            final float oldForce2 = this.m_limitForce2;
            this.m_limitForce2 = Math.max(0.0f, this.m_limitForce2 + force2);
            force2 = this.m_limitForce2 - oldForce2;
            final Vec2 P4 = this.m_u2.mul(-step.dt * force2);
            final Vec2 linearVelocity7 = b2.m_linearVelocity;
            linearVelocity7.x += b2.m_invMass * P4.x;
            final Vec2 linearVelocity8 = b2.m_linearVelocity;
            linearVelocity8.y += b2.m_invMass * P4.y;
            final Body body4 = b2;
            body4.m_angularVelocity += b2.m_invI * Vec2.cross(r2, P4);
        }
    }
    
    public boolean solvePositionConstraints() {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 s1 = this.m_ground.m_xf.position.add(this.m_groundAnchor1);
        final Vec2 s2 = this.m_ground.m_xf.position.add(this.m_groundAnchor2);
        float linearError = 0.0f;
        if (this.m_state == LimitState.AT_UPPER_LIMIT) {
            final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
            final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
            final Vec2 p1 = b1.m_sweep.c.add(r1);
            final Vec2 p2 = b2.m_sweep.c.add(r2);
            this.m_u1.set(p1.x - s1.x, p1.y - s1.y);
            this.m_u2.set(p2.x - s2.x, p2.y - s2.y);
            final float length1 = this.m_u1.length();
            final float length2 = this.m_u2.length();
            if (length1 > 0.005f) {
                this.m_u1.mulLocal(1.0f / length1);
            }
            else {
                this.m_u1.setZero();
            }
            if (length2 > 0.005f) {
                this.m_u2.mulLocal(1.0f / length2);
            }
            else {
                this.m_u2.setZero();
            }
            float C = this.m_constant - length1 - this.m_ratio * length2;
            linearError = Math.max(linearError, -C);
            C = MathUtils.clamp(C + 0.005f, -0.2f, 0.0f);
            float impulse = -this.m_pulleyMass * C;
            final float oldImpulse = this.m_positionImpulse;
            this.m_positionImpulse = Math.max(0.0f, this.m_positionImpulse + impulse);
            impulse = this.m_positionImpulse - oldImpulse;
            final Vec2 P1 = this.m_u1.mul(-impulse);
            final Vec2 P2 = this.m_u2.mul(-this.m_ratio * impulse);
            final Vec2 c = b1.m_sweep.c;
            c.x += b1.m_invMass * P1.x;
            final Vec2 c2 = b1.m_sweep.c;
            c2.y += b1.m_invMass * P1.y;
            final Sweep sweep = b1.m_sweep;
            sweep.a += b1.m_invI * Vec2.cross(r1, P1);
            final Vec2 c3 = b2.m_sweep.c;
            c3.x += b2.m_invMass * P2.x;
            final Vec2 c4 = b2.m_sweep.c;
            c4.y += b2.m_invMass * P2.y;
            final Sweep sweep2 = b2.m_sweep;
            sweep2.a += b2.m_invI * Vec2.cross(r2, P2);
            b1.synchronizeTransform();
            b2.synchronizeTransform();
        }
        if (this.m_limitState1 == LimitState.AT_UPPER_LIMIT) {
            final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
            final Vec2 p3 = b1.m_sweep.c.add(r1);
            this.m_u1.set(p3.x - s1.x, p3.y - s1.y);
            final float length3 = this.m_u1.length();
            if (length3 > 0.005f) {
                this.m_u1.mulLocal(1.0f / length3);
            }
            else {
                this.m_u1.setZero();
            }
            float C2 = this.m_maxLength1 - length3;
            linearError = Math.max(linearError, -C2);
            C2 = MathUtils.clamp(C2 + 0.005f, -0.2f, 0.0f);
            float impulse2 = -this.m_limitMass1 * C2;
            final float oldLimitPositionImpulse = this.m_limitPositionImpulse1;
            this.m_limitPositionImpulse1 = Math.max(0.0f, this.m_limitPositionImpulse1 + impulse2);
            impulse2 = this.m_limitPositionImpulse1 - oldLimitPositionImpulse;
            final Vec2 P3 = this.m_u1.mul(-impulse2);
            final Vec2 c5 = b1.m_sweep.c;
            c5.x += b1.m_invMass * P3.x;
            final Vec2 c6 = b1.m_sweep.c;
            c6.y += b1.m_invMass * P3.y;
            final Sweep sweep3 = b1.m_sweep;
            sweep3.a += b1.m_invI * Vec2.cross(r1, P3);
            b1.synchronizeTransform();
        }
        if (this.m_limitState2 == LimitState.AT_UPPER_LIMIT) {
            final Vec2 r3 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
            final Vec2 p4 = b2.m_sweep.c.add(r3);
            this.m_u2.set(p4.x - s2.x, p4.y - s2.y);
            final float length4 = this.m_u2.length();
            if (length4 > 0.005f) {
                this.m_u2.mulLocal(1.0f / length4);
            }
            else {
                this.m_u2.setZero();
            }
            float C2 = this.m_maxLength2 - length4;
            linearError = Math.max(linearError, -C2);
            C2 = MathUtils.clamp(C2 + 0.005f, -0.2f, 0.0f);
            float impulse2 = -this.m_limitMass2 * C2;
            final float oldLimitPositionImpulse = this.m_limitPositionImpulse2;
            this.m_limitPositionImpulse2 = Math.max(0.0f, this.m_limitPositionImpulse2 + impulse2);
            impulse2 = this.m_limitPositionImpulse2 - oldLimitPositionImpulse;
            final Vec2 P4 = this.m_u2.mul(-impulse2);
            final Vec2 c7 = b2.m_sweep.c;
            c7.x += b2.m_invMass * P4.x;
            final Vec2 c8 = b2.m_sweep.c;
            c8.y += b2.m_invMass * P4.y;
            final Sweep sweep4 = b2.m_sweep;
            sweep4.a += b2.m_invI * Vec2.cross(r3, P4);
            b2.synchronizeTransform();
        }
        return linearError < 0.005f;
    }
    
    public Vec2 getAnchor1() {
        return this.m_body1.getWorldPoint(this.m_localAnchor1);
    }
    
    public Vec2 getAnchor2() {
        return this.m_body2.getWorldPoint(this.m_localAnchor2);
    }
    
    public Vec2 getReactionForce() {
        final Vec2 F = this.m_u2.mul(this.m_force);
        return F;
    }
    
    public float getReactionTorque() {
        return 0.0f;
    }
    
    public Vec2 getGroundAnchor1() {
        return this.m_ground.m_xf.position.add(this.m_groundAnchor1);
    }
    
    public Vec2 getGroundAnchor2() {
        return this.m_ground.m_xf.position.add(this.m_groundAnchor2);
    }
    
    public float getLength1() {
        final Vec2 p = this.m_body1.getWorldPoint(this.m_localAnchor1);
        final Vec2 s = this.m_ground.m_xf.position.add(this.m_groundAnchor1);
        final Vec2 d = p.subLocal(s);
        return d.length();
    }
    
    public float getLength2() {
        final Vec2 p = this.m_body2.getWorldPoint(this.m_localAnchor2);
        final Vec2 s = this.m_ground.m_xf.position.add(this.m_groundAnchor2);
        final Vec2 d = p.subLocal(s);
        return d.length();
    }
    
    public float getRatio() {
        return this.m_ratio;
    }
}
