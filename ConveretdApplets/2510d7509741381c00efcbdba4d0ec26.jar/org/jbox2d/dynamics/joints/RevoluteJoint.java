// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.joints;

import org.jbox2d.common.Sweep;
import org.jbox2d.common.MathUtils;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.TimeStep;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.Vec2;

public class RevoluteJoint extends Joint
{
    public Vec2 m_localAnchor1;
    public Vec2 m_localAnchor2;
    public Vec2 m_pivotForce;
    public float m_motorForce;
    public float m_limitForce;
    public float m_limitPositionImpulse;
    public Mat22 m_pivotMass;
    public float m_motorMass;
    public boolean m_enableMotor;
    public float m_maxMotorTorque;
    public float m_motorSpeed;
    public boolean m_enableLimit;
    public float m_referenceAngle;
    public float m_lowerAngle;
    public float m_upperAngle;
    public LimitState m_limitState;
    
    public RevoluteJoint(final RevoluteJointDef def) {
        super(def);
        this.m_localAnchor1 = def.localAnchor1.clone();
        this.m_localAnchor2 = def.localAnchor2.clone();
        this.m_referenceAngle = def.referenceAngle;
        this.m_pivotForce = new Vec2(0.0f, 0.0f);
        this.m_motorForce = 0.0f;
        this.m_limitForce = 0.0f;
        this.m_limitPositionImpulse = 0.0f;
        this.m_pivotMass = new Mat22();
        this.m_lowerAngle = def.lowerAngle;
        this.m_upperAngle = def.upperAngle;
        this.m_maxMotorTorque = def.maxMotorTorque;
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
        final Mat22 K1 = new Mat22();
        K1.col1.x = invMass1 + invMass2;
        K1.col2.x = 0.0f;
        K1.col1.y = 0.0f;
        K1.col2.y = invMass1 + invMass2;
        final Mat22 K2 = new Mat22();
        K2.col1.x = invI1 * r1.y * r1.y;
        K2.col2.x = -invI1 * r1.x * r1.y;
        K2.col1.y = -invI1 * r1.x * r1.y;
        K2.col2.y = invI1 * r1.x * r1.x;
        final Mat22 K3 = new Mat22();
        K3.col1.x = invI2 * r2.y * r2.y;
        K3.col2.x = -invI2 * r2.x * r2.y;
        K3.col1.y = -invI2 * r2.x * r2.y;
        K3.col2.y = invI2 * r2.x * r2.x;
        final Mat22 K4 = K1.addLocal(K2).addLocal(K3);
        this.m_pivotMass = K4.invert();
        this.m_motorMass = 1.0f / (invI1 + invI2);
        if (!this.m_enableMotor) {
            this.m_motorForce = 0.0f;
        }
        if (this.m_enableLimit) {
            final float jointAngle = b2.m_sweep.a - b1.m_sweep.a - this.m_referenceAngle;
            if (Math.abs(this.m_upperAngle - this.m_lowerAngle) < 0.06981318f) {
                this.m_limitState = LimitState.EQUAL_LIMITS;
            }
            else if (jointAngle <= this.m_lowerAngle) {
                if (this.m_limitState != LimitState.AT_LOWER_LIMIT) {
                    this.m_limitForce = 0.0f;
                }
                this.m_limitState = LimitState.AT_LOWER_LIMIT;
            }
            else if (jointAngle >= this.m_upperAngle) {
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
        else {
            this.m_limitForce = 0.0f;
        }
        if (World.ENABLE_WARM_STARTING) {
            final Vec2 linearVelocity = b1.m_linearVelocity;
            linearVelocity.x -= step.dt * invMass1 * this.m_pivotForce.x;
            final Vec2 linearVelocity2 = b1.m_linearVelocity;
            linearVelocity2.y -= step.dt * invMass1 * this.m_pivotForce.y;
            final Body body = b1;
            body.m_angularVelocity -= step.dt * invI1 * (Vec2.cross(r1, this.m_pivotForce) + this.m_motorForce + this.m_limitForce);
            final Vec2 linearVelocity3 = b2.m_linearVelocity;
            linearVelocity3.x += step.dt * invMass2 * this.m_pivotForce.x;
            final Vec2 linearVelocity4 = b2.m_linearVelocity;
            linearVelocity4.y += step.dt * invMass2 * this.m_pivotForce.y;
            final Body body2 = b2;
            body2.m_angularVelocity += step.dt * invI2 * (Vec2.cross(r2, this.m_pivotForce) + this.m_motorForce + this.m_limitForce);
        }
        else {
            this.m_pivotForce.setZero();
            this.m_motorForce = 0.0f;
            this.m_limitForce = 0.0f;
        }
        this.m_limitPositionImpulse = 0.0f;
    }
    
    public void solveVelocityConstraints(final TimeStep step) {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        final Vec2 pivotCdot = b2.m_linearVelocity.add(Vec2.cross(b2.m_angularVelocity, r2).subLocal(b1.m_linearVelocity).subLocal(Vec2.cross(b1.m_angularVelocity, r1)));
        final Vec2 pivotForce = Mat22.mul(this.m_pivotMass, pivotCdot).mulLocal(-step.inv_dt);
        this.m_pivotForce.addLocal(pivotForce);
        final Vec2 P = pivotForce.mul(step.dt);
        final Vec2 linearVelocity = b1.m_linearVelocity;
        linearVelocity.x -= b1.m_invMass * P.x;
        final Vec2 linearVelocity2 = b1.m_linearVelocity;
        linearVelocity2.y -= b1.m_invMass * P.y;
        final Body body = b1;
        body.m_angularVelocity -= b1.m_invI * Vec2.cross(r1, P);
        final Vec2 linearVelocity3 = b2.m_linearVelocity;
        linearVelocity3.x += b2.m_invMass * P.x;
        final Vec2 linearVelocity4 = b2.m_linearVelocity;
        linearVelocity4.y += b2.m_invMass * P.y;
        final Body body2 = b2;
        body2.m_angularVelocity += b2.m_invI * Vec2.cross(r2, P);
        if (this.m_enableMotor && this.m_limitState != LimitState.EQUAL_LIMITS) {
            final float motorCdot = b2.m_angularVelocity - b1.m_angularVelocity - this.m_motorSpeed;
            float motorForce = -step.inv_dt * this.m_motorMass * motorCdot;
            final float oldMotorForce = this.m_motorForce;
            this.m_motorForce = MathUtils.clamp(this.m_motorForce + motorForce, -this.m_maxMotorTorque, this.m_maxMotorTorque);
            motorForce = this.m_motorForce - oldMotorForce;
            final float P2 = step.dt * motorForce;
            final Body body3 = b1;
            body3.m_angularVelocity -= b1.m_invI * P2;
            final Body body4 = b2;
            body4.m_angularVelocity += b2.m_invI * P2;
        }
        if (this.m_enableLimit && this.m_limitState != LimitState.INACTIVE_LIMIT) {
            final float limitCdot = b2.m_angularVelocity - b1.m_angularVelocity;
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
            final Body body5 = b1;
            body5.m_angularVelocity -= b1.m_invI * P3;
            final Body body6 = b2;
            body6.m_angularVelocity += b2.m_invI * P3;
        }
    }
    
    public boolean solvePositionConstraints() {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        float positionError = 0.0f;
        final Vec2 r1 = Mat22.mul(b1.m_xf.R, this.m_localAnchor1.sub(b1.getLocalCenter()));
        final Vec2 r2 = Mat22.mul(b2.m_xf.R, this.m_localAnchor2.sub(b2.getLocalCenter()));
        final Vec2 p1 = b1.m_sweep.c.add(r1);
        final Vec2 p2 = b2.m_sweep.c.add(r2);
        final Vec2 ptpC = p2.sub(p1);
        positionError = ptpC.length();
        final float invMass1 = b1.m_invMass;
        final float invMass2 = b2.m_invMass;
        final float invI1 = b1.m_invI;
        final float invI2 = b2.m_invI;
        final Mat22 K1 = new Mat22();
        K1.col1.x = invMass1 + invMass2;
        K1.col2.x = 0.0f;
        K1.col1.y = 0.0f;
        K1.col2.y = invMass1 + invMass2;
        final Mat22 K2 = new Mat22();
        K2.col1.x = invI1 * r1.y * r1.y;
        K2.col2.x = -invI1 * r1.x * r1.y;
        K2.col1.y = -invI1 * r1.x * r1.y;
        K2.col2.y = invI1 * r1.x * r1.x;
        final Mat22 K3 = new Mat22();
        K3.col1.x = invI2 * r2.y * r2.y;
        K3.col2.x = -invI2 * r2.x * r2.y;
        K3.col1.y = -invI2 * r2.x * r2.y;
        K3.col2.y = invI2 * r2.x * r2.x;
        final Mat22 K4 = K1.add(K2).add(K3);
        final Vec2 impulse = K4.solve(ptpC.negate());
        final Vec2 c = b1.m_sweep.c;
        c.x -= b1.m_invMass * impulse.x;
        final Vec2 c2 = b1.m_sweep.c;
        c2.y -= b1.m_invMass * impulse.y;
        final Sweep sweep = b1.m_sweep;
        sweep.a -= b1.m_invI * Vec2.cross(r1, impulse);
        final Vec2 c3 = b2.m_sweep.c;
        c3.x += b2.m_invMass * impulse.x;
        final Vec2 c4 = b2.m_sweep.c;
        c4.y += b2.m_invMass * impulse.y;
        final Sweep sweep2 = b2.m_sweep;
        sweep2.a += b2.m_invI * Vec2.cross(r2, impulse);
        b1.synchronizeTransform();
        b2.synchronizeTransform();
        float angularError = 0.0f;
        if (this.m_enableLimit && this.m_limitState != LimitState.INACTIVE_LIMIT) {
            final float angle = b2.m_sweep.a - b1.m_sweep.a - this.m_referenceAngle;
            float limitImpulse = 0.0f;
            if (this.m_limitState == LimitState.EQUAL_LIMITS) {
                final float limitC = MathUtils.clamp(angle, -0.13962635f, 0.13962635f);
                limitImpulse = -this.m_motorMass * limitC;
                angularError = Math.abs(limitC);
            }
            else if (this.m_limitState == LimitState.AT_LOWER_LIMIT) {
                float limitC = angle - this.m_lowerAngle;
                angularError = Math.max(0.0f, -limitC);
                limitC = MathUtils.clamp(limitC + 0.03490659f, -0.13962635f, 0.0f);
                limitImpulse = -this.m_motorMass * limitC;
                final float oldLimitImpulse = this.m_limitPositionImpulse;
                this.m_limitPositionImpulse = Math.max(this.m_limitPositionImpulse + limitImpulse, 0.0f);
                limitImpulse = this.m_limitPositionImpulse - oldLimitImpulse;
            }
            else if (this.m_limitState == LimitState.AT_UPPER_LIMIT) {
                float limitC = angle - this.m_upperAngle;
                angularError = Math.max(0.0f, limitC);
                limitC = MathUtils.clamp(limitC - 0.03490659f, 0.0f, 0.13962635f);
                limitImpulse = -this.m_motorMass * limitC;
                final float oldLimitImpulse = this.m_limitPositionImpulse;
                this.m_limitPositionImpulse = Math.min(this.m_limitPositionImpulse + limitImpulse, 0.0f);
                limitImpulse = this.m_limitPositionImpulse - oldLimitImpulse;
            }
            final Sweep sweep3 = b1.m_sweep;
            sweep3.a -= b1.m_invI * limitImpulse;
            final Sweep sweep4 = b2.m_sweep;
            sweep4.a += b2.m_invI * limitImpulse;
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
    
    public Vec2 getReactionForce() {
        return this.m_pivotForce;
    }
    
    public float getReactionTorque() {
        return this.m_limitForce;
    }
    
    public float getJointAngle() {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        return b2.m_sweep.a - b1.m_sweep.a - this.m_referenceAngle;
    }
    
    public float getJointSpeed() {
        final Body b1 = this.m_body1;
        final Body b2 = this.m_body2;
        return b2.m_angularVelocity - b1.m_angularVelocity;
    }
    
    public boolean isMotorEnabled() {
        return this.m_enableMotor;
    }
    
    public void enableMotor(final boolean flag) {
        this.m_enableMotor = flag;
    }
    
    public float getMotorTorque() {
        return this.m_motorForce;
    }
    
    public void setMotorSpeed(final float speed) {
        this.m_motorSpeed = speed;
    }
    
    public void setMaxMotorTorque(final float torque) {
        this.m_maxMotorTorque = torque;
    }
    
    public boolean isLimitEnabled() {
        return this.m_enableLimit;
    }
    
    public void enableLimit(final boolean flag) {
        this.m_enableLimit = flag;
    }
    
    public float getLowerLimit() {
        return this.m_lowerAngle;
    }
    
    public float getUpperLimit() {
        return this.m_upperAngle;
    }
    
    public void setLimits(final float lower, final float upper) {
        assert lower <= upper;
        this.m_lowerAngle = lower;
        this.m_upperAngle = upper;
    }
}
