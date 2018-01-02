// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.dynamics.contacts.ContactConstraintPoint;
import org.jbox2d.collision.ManifoldPoint;
import org.jbox2d.collision.ContactID;
import org.jbox2d.common.XForm;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactConstraint;
import java.util.List;
import org.jbox2d.common.Sweep;
import org.jbox2d.dynamics.contacts.ContactSolver;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.contacts.Contact;

public class Island
{
    public Body[] m_bodies;
    public Contact[] m_contacts;
    public Joint[] m_joints;
    public int m_bodyCount;
    public int m_jointCount;
    public int m_contactCount;
    public int m_bodyCapacity;
    public int m_contactCapacity;
    public int m_jointCapacity;
    public static int m_positionIterationCount;
    public float m_positionError;
    public ContactListener m_listener;
    
    static {
        Island.m_positionIterationCount = 0;
    }
    
    public void clear() {
        this.m_bodyCount = 0;
        this.m_contactCount = 0;
        this.m_jointCount = 0;
    }
    
    void add(final Body body) {
        assert this.m_bodyCount < this.m_bodyCapacity;
        this.m_bodies[this.m_bodyCount++] = body;
    }
    
    void add(final Contact contact) {
        assert this.m_contactCount < this.m_contactCapacity;
        this.m_contacts[this.m_contactCount++] = contact;
    }
    
    void add(final Joint joint) {
        assert this.m_jointCount < this.m_jointCapacity;
        this.m_joints[this.m_jointCount++] = joint;
    }
    
    public Island(final int bodyCapacity, final int contactCapacity, final int jointCapacity, final ContactListener listener) {
        this.m_bodyCapacity = bodyCapacity;
        this.m_contactCapacity = contactCapacity;
        this.m_jointCapacity = jointCapacity;
        this.m_bodyCount = 0;
        this.m_contactCount = 0;
        this.m_jointCount = 0;
        this.m_listener = listener;
        this.m_bodies = new Body[bodyCapacity];
        this.m_contacts = new Contact[contactCapacity];
        this.m_joints = new Joint[jointCapacity];
        Island.m_positionIterationCount = 0;
    }
    
    public void solve(final TimeStep step, final Vec2 gravity, final boolean correctPositions, final boolean allowSleep) {
        for (int i = 0; i < this.m_bodyCount; ++i) {
            final Body b = this.m_bodies[i];
            if (!b.isStatic()) {
                final Vec2 linearVelocity = b.m_linearVelocity;
                linearVelocity.x += step.dt * (gravity.x + b.m_invMass * b.m_force.x);
                final Vec2 linearVelocity2 = b.m_linearVelocity;
                linearVelocity2.y += step.dt * (gravity.y + b.m_invMass * b.m_force.y);
                final Body body = b;
                body.m_angularVelocity += step.dt * b.m_invI * b.m_torque;
                b.m_force.set(0.0f, 0.0f);
                b.m_torque = 0.0f;
                b.m_linearVelocity.mulLocal(MathUtils.clamp(1.0f - step.dt * b.m_linearDamping, 0.0f, 1.0f));
                final Body body2 = b;
                body2.m_angularVelocity *= MathUtils.clamp(1.0f - step.dt * b.m_angularDamping, 0.0f, 1.0f);
                if (Vec2.dot(b.m_linearVelocity, b.m_linearVelocity) > 40000.0f) {
                    b.m_linearVelocity.normalize();
                    b.m_linearVelocity.mulLocal(200.0f);
                }
                if (b.m_angularVelocity * b.m_angularVelocity > 62500.0f) {
                    if (b.m_angularVelocity < 0.0f) {
                        b.m_angularVelocity = -250.0f;
                    }
                    else {
                        b.m_angularVelocity = 250.0f;
                    }
                }
            }
        }
        final ContactSolver contactSolver = new ContactSolver(step, this.m_contacts, this.m_contactCount);
        contactSolver.initVelocityConstraints();
        for (int j = 0; j < this.m_jointCount; ++j) {
            this.m_joints[j].initVelocityConstraints(step);
        }
        for (int j = 0; j < step.maxIterations; ++j) {
            contactSolver.solveVelocityConstraints();
            for (int k = 0; k < this.m_jointCount; ++k) {
                this.m_joints[k].solveVelocityConstraints(step);
            }
        }
        contactSolver.finalizeVelocityConstraints();
        for (int j = 0; j < this.m_bodyCount; ++j) {
            final Body b2 = this.m_bodies[j];
            if (!b2.isStatic()) {
                b2.m_sweep.c0.set(b2.m_sweep.c);
                b2.m_sweep.a0 = b2.m_sweep.a;
                final Vec2 c = b2.m_sweep.c;
                c.x += step.dt * b2.m_linearVelocity.x;
                final Vec2 c2 = b2.m_sweep.c;
                c2.y += step.dt * b2.m_linearVelocity.y;
                final Sweep sweep = b2.m_sweep;
                sweep.a += step.dt * b2.m_angularVelocity;
                b2.synchronizeTransform();
            }
        }
        if (correctPositions) {
            for (int j = 0; j < this.m_jointCount; ++j) {
                this.m_joints[j].initPositionConstraints();
            }
            Island.m_positionIterationCount = 0;
            while (Island.m_positionIterationCount < step.maxIterations) {
                final boolean contactsOkay = contactSolver.solvePositionConstraints(0.2f);
                boolean jointsOkay = true;
                for (int l = 0; l < this.m_jointCount; ++l) {
                    final boolean jointOkay = this.m_joints[l].solvePositionConstraints();
                    jointsOkay = (jointsOkay && jointOkay);
                }
                if (contactsOkay && jointsOkay) {
                    break;
                }
                ++Island.m_positionIterationCount;
            }
        }
        this.report(contactSolver.m_constraints);
        if (allowSleep) {
            float minSleepTime = Float.MAX_VALUE;
            final float linTolSqr = 1.0E-4f;
            final float angTolSqr = 1.2345679E-4f;
            for (int m = 0; m < this.m_bodyCount; ++m) {
                final Body b3 = this.m_bodies[m];
                if (b3.m_invMass != 0.0f) {
                    if ((b3.m_flags & 0x10) == 0x0) {
                        b3.m_sleepTime = 0.0f;
                        minSleepTime = 0.0f;
                    }
                    if ((b3.m_flags & 0x10) == 0x0 || b3.m_angularVelocity * b3.m_angularVelocity > 1.2345679E-4f || Vec2.dot(b3.m_linearVelocity, b3.m_linearVelocity) > 1.0E-4f) {
                        b3.m_sleepTime = 0.0f;
                        minSleepTime = 0.0f;
                    }
                    else {
                        final Body body3 = b3;
                        body3.m_sleepTime += step.dt;
                        minSleepTime = Math.min(minSleepTime, b3.m_sleepTime);
                    }
                }
            }
            if (minSleepTime >= 0.5f) {
                for (int m = 0; m < this.m_bodyCount; ++m) {
                    final Body body4;
                    final Body b3 = body4 = this.m_bodies[m];
                    body4.m_flags |= 0x8;
                    b3.m_linearVelocity = new Vec2(0.0f, 0.0f);
                    b3.m_angularVelocity = 0.0f;
                }
            }
        }
    }
    
    public void solveTOI(final TimeStep subStep) {
        final ContactSolver contactSolver = new ContactSolver(subStep, this.m_contacts, this.m_contactCount);
        for (int i = 0; i < subStep.maxIterations; ++i) {
            contactSolver.solveVelocityConstraints();
        }
        for (int i = 0; i < this.m_bodyCount; ++i) {
            final Body b = this.m_bodies[i];
            if (!b.isStatic()) {
                b.m_sweep.c0.set(b.m_sweep.c);
                b.m_sweep.a0 = b.m_sweep.a;
                final Vec2 c = b.m_sweep.c;
                c.x += subStep.dt * b.m_linearVelocity.x;
                final Vec2 c2 = b.m_sweep.c;
                c2.y += subStep.dt * b.m_linearVelocity.y;
                final Sweep sweep = b.m_sweep;
                sweep.a += subStep.dt * b.m_angularVelocity;
                b.synchronizeTransform();
            }
        }
        final float k_toiBaumgarte = 0.75f;
        for (int j = 0; j < subStep.maxIterations; ++j) {
            final boolean contactsOkay = contactSolver.solvePositionConstraints(0.75f);
            if (contactsOkay) {
                break;
            }
        }
        this.report(contactSolver.m_constraints);
    }
    
    public void report(final List<ContactConstraint> constraints) {
        final ContactConstraint[] cc = new ContactConstraint[constraints.size()];
        for (int i = 0; i < cc.length; ++i) {
            cc[i] = constraints.get(i);
        }
        this.report(cc);
    }
    
    public void report(final ContactConstraint[] constraints) {
        if (this.m_listener == null) {
            return;
        }
        for (int i = 0; i < this.m_contactCount; ++i) {
            final Contact c = this.m_contacts[i];
            final ContactConstraint cc = constraints[i];
            final ContactPoint cp = new ContactPoint();
            cp.shape1 = c.getShape1();
            cp.shape2 = c.getShape2();
            final Body b1 = cp.shape1.getBody();
            final int manifoldCount = c.getManifoldCount();
            final List<Manifold> manifolds = c.getManifolds();
            for (int j = 0; j < manifoldCount; ++j) {
                final Manifold manifold = manifolds.get(j);
                cp.normal.set(manifold.normal);
                for (int k = 0; k < manifold.pointCount; ++k) {
                    final ManifoldPoint point = manifold.points[k];
                    final ContactConstraintPoint ccp = cc.points[k];
                    cp.position = XForm.mul(b1.getXForm(), point.localPoint1);
                    cp.separation = point.separation;
                    cp.normalForce = ccp.normalForce;
                    cp.tangentForce = ccp.tangentForce;
                    if ((point.id.features.flip & 0x2) != 0x0) {
                        final ContactID.Features features = point.id.features;
                        features.flip &= 0xFFFFFFFD;
                        cp.id = new ContactID(point.id);
                        this.m_listener.add(cp);
                    }
                    else {
                        cp.id = new ContactID(point.id);
                        this.m_listener.persist(cp);
                    }
                }
            }
        }
    }
}
