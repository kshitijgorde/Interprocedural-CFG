// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.common.Mat22;
import org.jbox2d.collision.MassData;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.jbox2d.dynamics.joints.JointEdge;
import org.jbox2d.collision.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Sweep;
import org.jbox2d.common.XForm;

public class Body
{
    public static final int e_frozenFlag = 2;
    public static final int e_islandFlag = 4;
    public static final int e_sleepFlag = 8;
    public static final int e_allowSleepFlag = 16;
    public static final int e_bulletFlag = 32;
    public static final int e_fixedRotationFlag = 64;
    public int m_flags;
    public static final int e_staticType = 0;
    public static final int e_dynamicType = 1;
    public static final int e_maxTypes = 2;
    public int m_type;
    public XForm m_xf;
    public Sweep m_sweep;
    public Vec2 m_linearVelocity;
    public float m_angularVelocity;
    public Vec2 m_force;
    public float m_torque;
    public World m_world;
    public Body m_prev;
    public Body m_next;
    public Shape m_shapeList;
    public int m_shapeCount;
    public JointEdge m_jointList;
    public ContactEdge m_contactList;
    public float m_mass;
    public float m_invMass;
    public float m_I;
    public float m_invI;
    public float m_linearDamping;
    public float m_angularDamping;
    public float m_sleepTime;
    public Object m_userData;
    
    public Body(final BodyDef bd, final int type, final World world) {
        assert !world.m_lock;
        assert type < 2;
        this.m_flags = 0;
        if (bd.isBullet) {
            this.m_flags |= 0x20;
        }
        if (bd.fixedRotation) {
            this.m_flags |= 0x40;
        }
        if (bd.allowSleep) {
            this.m_flags |= 0x10;
        }
        if (bd.isSleeping) {
            this.m_flags |= 0x8;
        }
        this.m_type = type;
        this.m_world = world;
        this.m_xf = new XForm();
        this.m_xf.position.set(bd.position);
        this.m_xf.R.set(bd.angle);
        this.m_sweep = new Sweep();
        this.m_sweep.localCenter.set(bd.massData.center);
        this.m_sweep.t0 = 1.0f;
        final Sweep sweep = this.m_sweep;
        final Sweep sweep2 = this.m_sweep;
        final float angle = bd.angle;
        sweep2.a = angle;
        sweep.a0 = angle;
        this.m_sweep.c.set(XForm.mul(this.m_xf, this.m_sweep.localCenter));
        this.m_sweep.c0.set(this.m_sweep.c);
        this.m_jointList = null;
        this.m_contactList = null;
        this.m_prev = null;
        this.m_next = null;
        this.m_linearDamping = bd.linearDamping;
        this.m_angularDamping = bd.angularDamping;
        this.m_force = new Vec2(0.0f, 0.0f);
        this.m_torque = 0.0f;
        this.m_linearVelocity = new Vec2(0.0f, 0.0f);
        this.m_angularVelocity = 0.0f;
        this.m_sleepTime = 0.0f;
        this.m_mass = 0.0f;
        this.m_invMass = 0.0f;
        this.m_I = 0.0f;
        this.m_invI = 0.0f;
        if (this.m_type == 1) {
            this.m_mass = bd.massData.mass;
        }
        if (this.m_mass > 0.0f) {
            this.m_invMass = 1.0f / this.m_mass;
        }
        if ((this.m_flags & 0x40) == 0x0 && this.m_type == 1) {
            this.m_I = bd.massData.I;
        }
        if (this.m_I > 0.0f) {
            this.m_invI = 1.0f / this.m_I;
        }
        this.m_userData = bd.userData;
        this.m_shapeList = null;
        this.m_shapeCount = 0;
    }
    
    public Shape createShape(final ShapeDef def) {
        assert !this.m_world.m_lock;
        if (this.m_world.m_lock) {
            return null;
        }
        final Shape s = Shape.create(def);
        s.m_next = this.m_shapeList;
        this.m_shapeList = s;
        ++this.m_shapeCount;
        s.m_body = this;
        s.createProxy(this.m_world.m_broadPhase, this.m_xf);
        s.updateSweepRadius(this.m_sweep.localCenter);
        return s;
    }
    
    public void destroyShape(final Shape s) {
        assert !this.m_world.m_lock;
        if (this.m_world.m_lock) {
            return;
        }
        assert s.m_body == this;
        s.destroyProxy(this.m_world.m_broadPhase);
        assert this.m_shapeCount > 0;
        Shape node = this.m_shapeList;
        Shape prevNode = null;
        boolean found = false;
        while (node != null) {
            if (node == s) {
                if (prevNode == null) {
                    this.m_shapeList = s.m_next;
                    found = true;
                    break;
                }
                prevNode.m_next = s.m_next;
                found = true;
                break;
            }
            else {
                prevNode = node;
                node = node.m_next;
            }
        }
        assert found;
        s.m_body = null;
        s.m_next = null;
        --this.m_shapeCount;
        Shape.destroy(s);
    }
    
    public void setMass(final MassData massData) {
        assert !this.m_world.m_lock;
        if (this.m_world.m_lock) {
            return;
        }
        if (this.m_type == 0) {
            return;
        }
        this.m_mass = 0.0f;
        this.m_invMass = 0.0f;
        this.m_I = 0.0f;
        this.m_invI = 0.0f;
        this.m_mass = massData.mass;
        if (this.m_mass > 0.0f) {
            this.m_invMass = 1.0f / this.m_mass;
        }
        if ((this.m_flags & 0x40) == 0x0) {
            this.m_I = massData.I;
        }
        if (this.m_I > 0.0f) {
            this.m_invI = 1.0f / this.m_I;
        }
        this.m_sweep.localCenter.set(massData.center);
        this.m_sweep.c.set(XForm.mul(this.m_xf, this.m_sweep.localCenter));
        this.m_sweep.c0.set(this.m_sweep.c);
        for (Shape s = this.m_shapeList; s != null; s = s.m_next) {
            s.updateSweepRadius(this.m_sweep.localCenter);
        }
    }
    
    public void setMassFromShapes() {
        assert !this.m_world.m_lock;
        if (this.m_world.m_lock) {
            return;
        }
        if (this.m_type == 0) {
            return;
        }
        this.m_mass = 0.0f;
        this.m_invMass = 0.0f;
        this.m_I = 0.0f;
        this.m_invI = 0.0f;
        final Vec2 center = new Vec2(0.0f, 0.0f);
        for (Shape s = this.m_shapeList; s != null; s = s.m_next) {
            final MassData massData = new MassData();
            s.computeMass(massData);
            this.m_mass += massData.mass;
            final Vec2 vec2 = center;
            vec2.x += massData.mass * massData.center.x;
            final Vec2 vec3 = center;
            vec3.y += massData.mass * massData.center.y;
            this.m_I += massData.I;
        }
        if (this.m_mass > 0.0f) {
            this.m_invMass = 1.0f / this.m_mass;
            final Vec2 vec4 = center;
            vec4.x *= this.m_invMass;
            final Vec2 vec5 = center;
            vec5.y *= this.m_invMass;
        }
        else {
            this.m_invMass = 0.0f;
            this.m_invI = 0.0f;
        }
        if (this.m_I > 0.0f && (this.m_flags & 0x40) == 0x0) {
            this.m_I -= this.m_mass * Vec2.dot(center, center);
            assert this.m_I > 0.0f;
            this.m_invI = 1.0f / this.m_I;
        }
        else {
            this.m_I = 0.0f;
            this.m_invI = 0.0f;
        }
        this.m_sweep.localCenter.set(center);
        this.m_sweep.c.set(XForm.mul(this.m_xf, this.m_sweep.localCenter));
        this.m_sweep.c0.set(this.m_sweep.c);
        for (Shape s = this.m_shapeList; s != null; s = s.m_next) {
            s.updateSweepRadius(this.m_sweep.localCenter);
        }
    }
    
    public boolean setXForm(final Vec2 position, final float angle) {
        assert !this.m_world.m_lock;
        if (this.m_world.m_lock) {
            return true;
        }
        if (this.isFrozen()) {
            return false;
        }
        this.m_xf.R.set(angle);
        this.m_xf.position.set(position);
        this.m_sweep.c.set(XForm.mul(this.m_xf, this.m_sweep.localCenter));
        this.m_sweep.c0.set(this.m_sweep.c);
        final Sweep sweep = this.m_sweep;
        this.m_sweep.a = angle;
        sweep.a0 = angle;
        boolean freeze = false;
        for (Shape s = this.m_shapeList; s != null; s = s.m_next) {
            final boolean inRange = s.synchronize(this.m_world.m_broadPhase, this.m_xf, this.m_xf);
            if (!inRange) {
                freeze = true;
                break;
            }
        }
        if (freeze) {
            this.m_flags |= 0x2;
            this.m_linearVelocity.setZero();
            this.m_angularVelocity = 0.0f;
            for (Shape s = this.m_shapeList; s != null; s = s.m_next) {
                s.destroyProxy(this.m_world.m_broadPhase);
            }
            return false;
        }
        this.m_world.m_broadPhase.commit();
        return true;
    }
    
    public XForm getXForm() {
        final XForm xf = new XForm();
        xf.set(this.m_xf);
        return xf;
    }
    
    public Vec2 getPosition() {
        return this.m_xf.position.clone();
    }
    
    public float getAngle() {
        return this.m_sweep.a;
    }
    
    public Vec2 getWorldCenter() {
        return this.m_sweep.c.clone();
    }
    
    public Vec2 getLocalCenter() {
        return this.m_sweep.localCenter.clone();
    }
    
    public void setLinearVelocity(final Vec2 v) {
        this.m_linearVelocity.set(v);
    }
    
    public Vec2 getLinearVelocity() {
        return this.m_linearVelocity.clone();
    }
    
    public void setAngularVelocity(final float omega) {
        this.m_angularVelocity = omega;
    }
    
    public float getAngularVelocity() {
        return this.m_angularVelocity;
    }
    
    public void applyForce(final Vec2 force, final Vec2 point) {
        if (this.isSleeping()) {
            this.wakeUp();
        }
        this.m_force.addLocal(force);
        this.m_torque += Vec2.cross(point.sub(this.m_sweep.c), force);
    }
    
    public void applyTorque(final float torque) {
        if (this.isSleeping()) {
            this.wakeUp();
        }
        this.m_torque += torque;
    }
    
    public void applyImpulse(final Vec2 impulse, final Vec2 point) {
        if (this.isSleeping()) {
            this.wakeUp();
        }
        final Vec2 linearVelocity = this.m_linearVelocity;
        linearVelocity.x += this.m_invMass * impulse.x;
        final Vec2 linearVelocity2 = this.m_linearVelocity;
        linearVelocity2.y += this.m_invMass * impulse.y;
        this.m_angularVelocity += this.m_invI * Vec2.cross(point.sub(this.m_sweep.c), impulse);
    }
    
    public float getMass() {
        return this.m_mass;
    }
    
    public float getInertia() {
        return this.m_I;
    }
    
    public Vec2 getWorldPoint(final Vec2 localPoint) {
        return XForm.mul(this.m_xf, localPoint);
    }
    
    public Vec2 getWorldVector(final Vec2 localVector) {
        return Mat22.mul(this.m_xf.R, localVector);
    }
    
    public Vec2 getLocalPoint(final Vec2 worldPoint) {
        return XForm.mulT(this.m_xf, worldPoint);
    }
    
    public Vec2 getLocalVector(final Vec2 worldVector) {
        return Mat22.mulT(this.m_xf.R, worldVector);
    }
    
    public boolean isBullet() {
        return (this.m_flags & 0x20) == 0x20;
    }
    
    public void setBullet(final boolean flag) {
        if (flag) {
            this.m_flags |= 0x20;
        }
        else {
            this.m_flags &= 0xFFFFFFDF;
        }
    }
    
    public boolean isStatic() {
        return this.m_type == 0;
    }
    
    public boolean isDynamic() {
        return this.m_type == 1;
    }
    
    public boolean isFrozen() {
        return (this.m_flags & 0x2) == 0x2;
    }
    
    public boolean isSleeping() {
        return (this.m_flags & 0x8) == 0x8;
    }
    
    public void allowSleeping(final boolean flag) {
        if (flag) {
            this.m_flags |= 0x10;
        }
        else {
            this.m_flags &= 0xFFFFFFEF;
            this.wakeUp();
        }
    }
    
    public void wakeUp() {
        this.m_flags &= 0xFFFFFFF7;
        this.m_sleepTime = 0.0f;
    }
    
    public Shape getShapeList() {
        return this.m_shapeList;
    }
    
    public JointEdge getJointList() {
        return this.m_jointList;
    }
    
    public ContactEdge getContactList() {
        return this.m_contactList;
    }
    
    public Body getNext() {
        return this.m_next;
    }
    
    public Object getUserData() {
        return this.m_userData;
    }
    
    public void computeMass() {
    }
    
    public boolean synchronizeShapes() {
        final XForm xf1 = new XForm();
        xf1.R.set(this.m_sweep.a0);
        xf1.position.set(this.m_sweep.c0.sub(Mat22.mul(xf1.R, this.m_sweep.localCenter)));
        boolean inRange = true;
        for (Shape s = this.m_shapeList; s != null; s = s.m_next) {
            inRange = s.synchronize(this.m_world.m_broadPhase, xf1, this.m_xf);
            if (!inRange) {
                break;
            }
        }
        if (!inRange) {
            this.m_flags |= 0x2;
            this.m_linearVelocity.setZero();
            this.m_angularVelocity = 0.0f;
            for (Shape s = this.m_shapeList; s != null; s = s.m_next) {
                s.destroyProxy(this.m_world.m_broadPhase);
            }
            return false;
        }
        return true;
    }
    
    public void synchronizeTransform() {
        this.m_xf.R.set(this.m_sweep.a);
        this.m_xf.position.set(this.m_sweep.c.sub(Mat22.mul(this.m_xf.R, this.m_sweep.localCenter)));
    }
    
    public boolean isConnected(final Body other) {
        for (JointEdge jn = this.m_jointList; jn != null; jn = jn.next) {
            if (jn.other == other) {
                return !jn.joint.m_collideConnected;
            }
        }
        return false;
    }
    
    public void advance(final float t) {
        this.m_sweep.advance(t);
        this.m_sweep.c.set(this.m_sweep.c0);
        this.m_sweep.a = this.m_sweep.a0;
        this.synchronizeTransform();
    }
}
