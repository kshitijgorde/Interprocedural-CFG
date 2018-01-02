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

public class PrismaticJoint extends Joint
{
    public Vec2 m_localAnchor1;
    public Vec2 m_localAnchor2;
    public Vec2 m_localXAxis1;
    public Vec2 m_localYAxis1;
    public float m_refAngle;
    public Jacobian m_linearJacobian;
    public float m_linearMass;
    public float m_force;
    public float m_angularMass;
    public float m_torque;
    public Jacobian m_motorJacobian;
    public float m_motorMass;
    public float m_motorForce;
    public float m_limitForce;
    public float m_limitPositionImpulse;
    public float m_lowerTranslation;
    public float m_upperTranslation;
    public float m_maxMotorForce;
    public float m_motorSpeed;
    public boolean m_enableLimit;
    public boolean m_enableMotor;
    public LimitState m_limitState;
    
    public PrismaticJoint(final PrismaticJointDef def) {
        super(def);
        this.m_localAnchor1 = def.localAnchor1.clone();
        this.m_localAnchor2 = def.localAnchor2.clone();
        this.m_localXAxis1 = def.localAxis1.clone();
        this.m_localYAxis1 = Vec2.cross(1.0f, this.m_localXAxis1);
        this.m_refAngle = def.referenceAngle;
        (this.m_linearJacobian = new Jacobian()).setZero();
        this.m_linearMass = 0.0f;
        this.m_force = 0.0f;
        this.m_angularMass = 0.0f;
        this.m_torque = 0.0f;
        (this.m_motorJacobian = new Jacobian()).setZero();
        this.m_motorMass = 0.0f;
        this.m_motorForce = 0.0f;
        this.m_limitForce = 0.0f;
        this.m_limitPositionImpulse = 0.0f;
        this.m_lowerTranslation = def.lowerTranslation;
        this.m_upperTranslation = def.upperTranslation;
        this.m_maxMotorForce = def.maxMotorForce;
        this.m_motorSpeed = def.motorSpeed;
        this.m_enableLimit = def.enableLimit;
        this.m_enableMotor = def.enableMotor;
    }
    
    public void initVelocityConstraints(final TimeStep step) {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        final float invMass1 = b1.m_invMass;
        final float invMass2 = b2.m_invMass;
        final float invI1 = b1.m_invI;
        final float invI2 = b2.m_invI;
        final Vec2 ay1 = Mat22.mul(b1.m_xf.R, this.m_localYAxis1);
        final Vec2 e = b2.m_sweep.c.add(r2).subLocal(b1.m_sweep.c);
        this.m_linearJacobian.set(ay1.negate(), -Vec2.cross(e, ay1), ay1, Vec2.cross(r2, ay1));
        this.m_linearMass = invMass1 + invI1 * this.m_linearJacobian.angular1 * this.m_linearJacobian.angular1 + invMass2 + invI2 * this.m_linearJacobian.angular2 * this.m_linearJacobian.angular2;
        assert this.m_linearMass > 1.1920929E-7f;
        this.m_linearMass = 1.0f / this.m_linearMass;
        this.m_angularMass = invI1 + invI2;
        if (this.m_angularMass > 1.1920929E-7f) {
            this.m_angularMass = 1.0f / this.m_angularMass;
        }
        if (this.m_enableLimit || this.m_enableMotor) {
            final Vec2 ax1 = Mat22.mul(b1.m_xf.R, this.m_localXAxis1);
            this.m_motorJacobian.set(ax1.negate(), -Vec2.cross(e, ax1), ax1, Vec2.cross(r2, ax1));
            this.m_motorMass = invMass1 + invI1 * this.m_motorJacobian.angular1 * this.m_motorJacobian.angular1 + invMass2 + invI2 * this.m_motorJacobian.angular2 * this.m_motorJacobian.angular2;
            assert this.m_motorMass > 1.1920929E-7f;
            this.m_motorMass = 1.0f / this.m_motorMass;
            if (this.m_enableLimit) {
                final Vec2 d = e.sub(r1);
                final float jointTranslation = Vec2.dot(ax1, d);
                if (Math.abs(this.m_upperTranslation - this.m_lowerTranslation) < 0.01f) {
                    this.m_limitState = LimitState.EQUAL_LIMITS;
                }
                else if (jointTranslation <= this.m_lowerTranslation) {
                    if (this.m_limitState != LimitState.AT_LOWER_LIMIT) {
                        this.m_limitForce = 0.0f;
                    }
                    this.m_limitState = LimitState.AT_LOWER_LIMIT;
                }
                else if (jointTranslation >= this.m_upperTranslation) {
                    if (this.m_limitState != LimitState.AT_UPPER_LIMIT) {
                        this.m_limitForce = 0.0f;
                    }
                    this.m_limitState = LimitState.AT_UPPER_LIMIT;
                }
                else {
                    this.m_limitState = LimitState.INACTIVE_LIMIT;
                    this.m_limitForce = 0.0f;
                }
            }
        }
        if (!this.m_enableMotor) {
            this.m_motorForce = 0.0f;
        }
        if (!this.m_enableLimit) {
            this.m_limitForce = 0.0f;
        }
        if (World.ENABLE_WARM_STARTING) {
            final Vec2 P1 = new Vec2(step.dt * (this.m_force * this.m_linearJacobian.linear1.x + (this.m_motorForce + this.m_limitForce) * this.m_motorJacobian.linear1.x), step.dt * (this.m_force * this.m_linearJacobian.linear1.y + (this.m_motorForce + this.m_limitForce) * this.m_motorJacobian.linear1.y));
            final Vec2 P2 = new Vec2(step.dt * (this.m_force * this.m_linearJacobian.linear2.x + (this.m_motorForce + this.m_limitForce) * this.m_motorJacobian.linear2.x), step.dt * (this.m_force * this.m_linearJacobian.linear2.y + (this.m_motorForce + this.m_limitForce) * this.m_motorJacobian.linear2.y));
            final float L1 = step.dt * (this.m_force * this.m_linearJacobian.angular1 - this.m_torque + (this.m_motorForce + this.m_limitForce) * this.m_motorJacobian.angular1);
            final float L2 = step.dt * (this.m_force * this.m_linearJacobian.angular2 + this.m_torque + (this.m_motorForce + this.m_limitForce) * this.m_motorJacobian.angular2);
            final Vec2 linearVelocity = b1.m_linearVelocity;
            linearVelocity.x += invMass1 * P1.x;
            final Vec2 linearVelocity2 = b1.m_linearVelocity;
            linearVelocity2.y += invMass1 * P1.y;
            final Body body = b1;
            body.m_angularVelocity += invI1 * L1;
            final Vec2 linearVelocity3 = b2.m_linearVelocity;
            linearVelocity3.x += invMass2 * P2.x;
            final Vec2 linearVelocity4 = b2.m_linearVelocity;
            linearVelocity4.y += invMass2 * P2.y;
            final Body body2 = b2;
            body2.m_angularVelocity += invI2 * L2;
        }
        else {
            this.m_force = 0.0f;
            this.m_torque = 0.0f;
            this.m_limitForce = 0.0f;
            this.m_motorForce = 0.0f;
        }
        this.m_limitPositionImpulse = 0.0f;
    }
    
    public void solveVelocityConstraints(final TimeStep step) {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final float invMass1 = b1.m_invMass;
        final float invMass2 = b2.m_invMass;
        final float invI1 = b1.m_invI;
        final float invI2 = b2.m_invI;
        final float linearCdot = this.m_linearJacobian.compute(b1.m_linearVelocity, b1.m_angularVelocity, b2.m_linearVelocity, b2.m_angularVelocity);
        final float force = -step.inv_dt * this.m_linearMass * linearCdot;
        this.m_force += force;
        final float P = step.dt * force;
        final Vec2 linearVelocity = b1.m_linearVelocity;
        linearVelocity.x += invMass1 * P * this.m_linearJacobian.linear1.x;
        final Vec2 linearVelocity2 = b1.m_linearVelocity;
        linearVelocity2.y += invMass1 * P * this.m_linearJacobian.linear1.y;
        final Body body = b1;
        body.m_angularVelocity += invI1 * P * this.m_linearJacobian.angular1;
        final Vec2 linearVelocity3 = b2.m_linearVelocity;
        linearVelocity3.x += invMass2 * P * this.m_linearJacobian.linear2.x;
        final Vec2 linearVelocity4 = b2.m_linearVelocity;
        linearVelocity4.y += invMass2 * P * this.m_linearJacobian.linear2.y;
        final Body body2 = b2;
        body2.m_angularVelocity += invI2 * P * this.m_linearJacobian.angular2;
        final float angularCdot = b2.m_angularVelocity - b1.m_angularVelocity;
        final float torque = -step.inv_dt * this.m_angularMass * angularCdot;
        this.m_torque += torque;
        final float L = step.dt * torque;
        final Body body3 = b1;
        body3.m_angularVelocity -= invI1 * L;
        final Body body4 = b2;
        body4.m_angularVelocity += invI2 * L;
        if (this.m_enableMotor && this.m_limitState != LimitState.EQUAL_LIMITS) {
            final float motorCdot = this.m_motorJacobian.compute(b1.m_linearVelocity, b1.m_angularVelocity, b2.m_linearVelocity, b2.m_angularVelocity) - this.m_motorSpeed;
            float motorForce = -step.inv_dt * this.m_motorMass * motorCdot;
            final float oldMotorForce = this.m_motorForce;
            this.m_motorForce = MathUtils.clamp(this.m_motorForce + motorForce, -this.m_maxMotorForce, this.m_maxMotorForce);
            motorForce = this.m_motorForce - oldMotorForce;
            final float P2 = step.dt * motorForce;
            final Vec2 linearVelocity5 = b1.m_linearVelocity;
            linearVelocity5.x += invMass1 * P2 * this.m_motorJacobian.linear1.x;
            final Vec2 linearVelocity6 = b1.m_linearVelocity;
            linearVelocity6.y += invMass1 * P2 * this.m_motorJacobian.linear1.y;
            final Body body5 = b1;
            body5.m_angularVelocity += invI1 * P2 * this.m_motorJacobian.angular1;
            final Vec2 linearVelocity7 = b2.m_linearVelocity;
            linearVelocity7.x += invMass2 * P2 * this.m_motorJacobian.linear2.x;
            final Vec2 linearVelocity8 = b2.m_linearVelocity;
            linearVelocity8.y += invMass2 * P2 * this.m_motorJacobian.linear2.y;
            final Body body6 = b2;
            body6.m_angularVelocity += invI2 * P2 * this.m_motorJacobian.angular2;
        }
        if (this.m_enableLimit && this.m_limitState != LimitState.INACTIVE_LIMIT) {
            final float limitCdot = this.m_motorJacobian.compute(b1.m_linearVelocity, b1.m_angularVelocity, b2.m_linearVelocity, b2.m_angularVelocity);
            float limitForce = -step.inv_dt * this.m_motorMass * limitCdot;
            if (this.m_limitState == LimitState.EQUAL_LIMITS) {
                this.m_limitForce += limitForce;
            }
            else if (this.m_limitState == LimitState.AT_LOWER_LIMIT) {
                final float oldLimitForce = this.m_limitForce;
                this.m_limitForce = Math.max(this.m_limitForce + limitForce, 0.0f);
                limitForce = this.m_limitForce - oldLimitForce;
            }
            else if (this.m_limitState == LimitState.AT_UPPER_LIMIT) {
                final float oldLimitForce = this.m_limitForce;
                this.m_limitForce = Math.min(this.m_limitForce + limitForce, 0.0f);
                limitForce = this.m_limitForce - oldLimitForce;
            }
            final float P3 = step.dt * limitForce;
            final Vec2 linearVelocity9 = b1.m_linearVelocity;
            linearVelocity9.x += invMass1 * P3 * this.m_motorJacobian.linear1.x;
            final Vec2 linearVelocity10 = b1.m_linearVelocity;
            linearVelocity10.y += invMass1 * P3 * this.m_motorJacobian.linear1.y;
            final Body body7 = b1;
            body7.m_angularVelocity += invI1 * P3 * this.m_motorJacobian.angular1;
            final Vec2 linearVelocity11 = b2.m_linearVelocity;
            linearVelocity11.x += invMass2 * P3 * this.m_motorJacobian.linear2.x;
            final Vec2 linearVelocity12 = b2.m_linearVelocity;
            linearVelocity12.y += invMass2 * P3 * this.m_motorJacobian.linear2.y;
            final Body body8 = b2;
            body8.m_angularVelocity += invI2 * P3 * this.m_motorJacobian.angular2;
        }
    }
    
    public boolean solvePositionConstraints() {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final float invMass1 = b1.m_invMass;
        final float invMass2 = b2.m_invMass;
        final float invI1 = b1.m_invI;
        final float invI2 = b2.m_invI;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        final Vec2 p1 = b1.m_sweep.c.add(r1);
        final Vec2 p2 = b2.m_sweep.c.add(r2);
        final Vec2 d = p2.sub(p1);
        final Vec2 ay1 = Mat22.mul(b1.m_xf.R, this.m_localYAxis1);
        float linearC = Vec2.dot(ay1, d);
        linearC = MathUtils.clamp(linearC, -0.2f, 0.2f);
        final float linearImpulse = -this.m_linearMass * linearC;
        final Vec2 c = b1.m_sweep.c;
        c.x += invMass1 * linearImpulse * this.m_linearJacobian.linear1.x;
        final Vec2 c2 = b1.m_sweep.c;
        c2.y += invMass1 * linearImpulse * this.m_linearJacobian.linear1.y;
        final Sweep sweep = b1.m_sweep;
        sweep.a += invI1 * linearImpulse * this.m_linearJacobian.angular1;
        final Vec2 c3 = b2.m_sweep.c;
        c3.x += invMass2 * linearImpulse * this.m_linearJacobian.linear2.x;
        final Vec2 c4 = b2.m_sweep.c;
        c4.y += invMass2 * linearImpulse * this.m_linearJacobian.linear2.y;
        final Sweep sweep2 = b2.m_sweep;
        sweep2.a += invI2 * linearImpulse * this.m_linearJacobian.angular2;
        float positionError = Math.abs(linearC);
        float angularC = b2.m_sweep.a - b1.m_sweep.a - this.m_refAngle;
        angularC = MathUtils.clamp(angularC, -0.13962635f, 0.13962635f);
        final float angularImpulse = -this.m_angularMass * angularC;
        final Sweep sweep3 = b1.m_sweep;
        sweep3.a -= b1.m_invI * angularImpulse;
        final Sweep sweep4 = b2.m_sweep;
        sweep4.a += b2.m_invI * angularImpulse;
        b1.synchronizeTransform();
        b2.synchronizeTransform();
        final float angularError = Math.abs(angularC);
        if (this.m_enableLimit && this.m_limitState != LimitState.INACTIVE_LIMIT) {
            final Vec2 r1z = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
            final Vec2 r2z = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
            final Vec2 p1z = b1.m_sweep.c.add(r1z);
            final Vec2 p2z = b2.m_sweep.c.add(r2z);
            final Vec2 dz = p2z.sub(p1z);
            final Vec2 ax1 = Mat22.mul(b1.m_xf.R, this.m_localXAxis1);
            final float translation = Vec2.dot(ax1, dz);
            float limitImpulse = 0.0f;
            if (this.m_limitState == LimitState.EQUAL_LIMITS) {
                final float limitC = MathUtils.clamp(translation, -0.2f, 0.2f);
                limitImpulse = -this.m_motorMass * limitC;
                positionError = Math.max(positionError, Math.abs(angularC));
            }
            else if (this.m_limitState == LimitState.AT_LOWER_LIMIT) {
                float limitC = translation - this.m_lowerTranslation;
                positionError = Math.max(positionError, -limitC);
                limitC = MathUtils.clamp(limitC + 0.005f, -0.2f, 0.0f);
                limitImpulse = -this.m_motorMass * limitC;
                final float oldLimitImpulse = this.m_limitPositionImpulse;
                this.m_limitPositionImpulse = Math.max(this.m_limitPositionImpulse + limitImpulse, 0.0f);
                limitImpulse = this.m_limitPositionImpulse - oldLimitImpulse;
            }
            else if (this.m_limitState == LimitState.AT_UPPER_LIMIT) {
                float limitC = translation - this.m_upperTranslation;
                positionError = Math.max(positionError, limitC);
                limitC = MathUtils.clamp(limitC - 0.005f, 0.0f, 0.2f);
                limitImpulse = -this.m_motorMass * limitC;
                final float oldLimitImpulse = this.m_limitPositionImpulse;
                this.m_limitPositionImpulse = Math.min(this.m_limitPositionImpulse + limitImpulse, 0.0f);
                limitImpulse = this.m_limitPositionImpulse - oldLimitImpulse;
            }
            final Vec2 c5 = b1.m_sweep.c;
            c5.x += invMass1 * limitImpulse * this.m_motorJacobian.linear1.x;
            final Vec2 c6 = b1.m_sweep.c;
            c6.y += invMass1 * limitImpulse * this.m_motorJacobian.linear1.y;
            final Sweep sweep5 = b1.m_sweep;
            sweep5.a += invI1 * limitImpulse * this.m_motorJacobian.angular1;
            final Vec2 c7 = b2.m_sweep.c;
            c7.x += invMass2 * limitImpulse * this.m_motorJacobian.linear2.x;
            final Vec2 c8 = b2.m_sweep.c;
            c8.y += invMass2 * limitImpulse * this.m_motorJacobian.linear2.y;
            final Sweep sweep6 = b2.m_sweep;
            sweep6.a += invI2 * limitImpulse * this.m_motorJacobian.angular2;
            b1.synchronizeTransform();
            b2.synchronizeTransform();
        }
        return positionError <= 0.005f && angularError <= 0.03490659f;
    }
    
    public Vec2 getAnchor1() {
        return this.m_body1.getWorldPoint(this.m_localAnchor1);
    }
    
    public Vec2 getAnchor2() {
        return this.m_body2.getWorldPoint(this.m_localAnchor2);
    }
    
    public float getJointTranslation() {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 p1 = b1.getWorldPoint(this.m_localAnchor1);
        final Vec2 p2 = b2.getWorldPoint(this.m_localAnchor2);
        final Vec2 d = p2.sub(p1);
        final Vec2 axis = b1.getWorldVector(this.m_localXAxis1);
        final float translation = Vec2.dot(d, axis);
        return translation;
    }
    
    public float getJointSpeed() {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        final Vec2 p1 = b1.m_sweep.c.add(r1);
        final Vec2 p2 = b2.m_sweep.c.add(r2);
        final Vec2 d = p2.sub(p1);
        final Vec2 axis = b1.getWorldVector(this.m_localXAxis1);
        final Vec2 v1 = b1.m_linearVelocity;
        final Vec2 v2 = b2.m_linearVelocity;
        final float w1 = b1.m_angularVelocity;
        final float w2 = b2.m_angularVelocity;
        final float speed = Vec2.dot(d, Vec2.cross(w1, axis)) + Vec2.dot(axis, v2.add(Vec2.cross(w2, r2)).subLocal(v1).subLocal(Vec2.cross(w1, r1)));
        return speed;
    }
    
    public float getReactionTorque() {
        return this.m_torque;
    }
    
    public Vec2 getReactionForce() {
        final Vec2 ax1 = Mat22.mul(this.m_body1.m_xf.R, this.m_localXAxis1);
        final Vec2 ay1 = Mat22.mul(this.m_body1.m_xf.R, this.m_localYAxis1);
        return new Vec2(this.m_limitForce * ax1.x + this.m_force * ay1.x, this.m_limitForce * ax1.y + this.m_force * ay1.y);
    }
    
    public boolean isLimitEnabled() {
        return this.m_enableLimit;
    }
    
    public void enableLimit(final boolean flag) {
        this.m_enableLimit = flag;
    }
    
    public float getLowerLimit() {
        return this.m_lowerTranslation;
    }
    
    public float getUpperLimit() {
        return this.m_upperTranslation;
    }
    
    public void setLimits(final float lower, final float upper) {
        assert lower <= upper;
        this.m_lowerTranslation = lower;
        this.m_upperTranslation = upper;
    }
    
    public boolean isMotorEnabled() {
        return this.m_enableMotor;
    }
    
    public void enableMotor(final boolean flag) {
        this.m_enableMotor = flag;
    }
    
    public void setMotorSpeed(final float speed) {
        this.m_motorSpeed = speed;
    }
    
    public float getMotorSpeed() {
        return this.m_motorSpeed;
    }
    
    public void setMaxMotorForce(final float force) {
        this.m_maxMotorForce = force;
    }
    
    public float getMotorForce() {
        return this.m_motorForce;
    }
}
