// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.collision.OBB;
import org.jbox2d.collision.Proxy;
import org.jbox2d.collision.Pair;
import org.jbox2d.common.Mat22;
import org.jbox2d.dynamics.joints.PulleyJoint;
import org.jbox2d.dynamics.joints.JointType;
import org.jbox2d.collision.PolygonShape;
import org.jbox2d.collision.CircleShape;
import org.jbox2d.collision.ShapeType;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.XForm;
import org.jbox2d.collision.TOI;
import org.jbox2d.common.Settings;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.dynamics.joints.JointEdge;
import org.jbox2d.collision.Shape;
import org.jbox2d.collision.PairCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.collision.BroadPhase;

public class World
{
    public boolean m_lock;
    public BroadPhase m_broadPhase;
    ContactManager m_contactManager;
    public Body m_bodyList;
    public Contact m_contactList;
    public Joint m_jointList;
    public int m_bodyCount;
    public int m_contactCount;
    public int m_jointCount;
    public Vec2 m_gravity;
    public boolean m_allowSleep;
    public Body m_groundBody;
    public int m_positionIterationCount;
    public static boolean ENABLE_POSITION_CORRECTION;
    public static boolean ENABLE_WARM_STARTING;
    public static boolean ENABLE_TOI;
    public DestructionListener m_destructionListener;
    public BoundaryListener m_boundaryListener;
    public ContactFilter m_contactFilter;
    public ContactListener m_contactListener;
    public DebugDraw m_debugDraw;
    
    public Body getGroundBody() {
        return this.m_groundBody;
    }
    
    public Body getBodyList() {
        return this.m_bodyList;
    }
    
    public Joint getJointList() {
        return this.m_jointList;
    }
    
    public World(final AABB worldAABB, final Vec2 gravity, final boolean doSleep) {
        this.m_destructionListener = null;
        this.m_boundaryListener = null;
        this.m_contactFilter = ContactFilter.DEFAULT_FILTER;
        this.m_contactListener = null;
        this.m_debugDraw = null;
        this.m_bodyList = null;
        this.m_contactList = null;
        this.m_jointList = null;
        this.m_bodyCount = 0;
        this.m_contactCount = 0;
        this.m_jointCount = 0;
        this.m_lock = false;
        this.m_allowSleep = doSleep;
        this.m_gravity = gravity;
        this.m_contactManager = new ContactManager();
        this.m_contactManager.m_world = this;
        this.m_broadPhase = new BroadPhase(worldAABB, this.m_contactManager);
        final BodyDef bd = new BodyDef();
        this.m_groundBody = this.createStaticBody(bd);
    }
    
    public void setListener(final DestructionListener listener) {
        this.m_destructionListener = listener;
    }
    
    public void setListener(final BoundaryListener listener) {
        this.m_boundaryListener = listener;
    }
    
    public void setFilter(final ContactFilter filter) {
        this.m_contactFilter = filter;
    }
    
    public void setListener(final ContactListener listener) {
        this.m_contactListener = listener;
    }
    
    public void setDebugDraw(final DebugDraw debugDraw) {
        this.m_debugDraw = debugDraw;
    }
    
    public Body createStaticBody(final BodyDef def) {
        assert !this.m_lock;
        if (this.m_lock) {
            return null;
        }
        final Body b = new Body(def, 0, this);
        b.m_prev = null;
        b.m_next = this.m_bodyList;
        if (this.m_bodyList != null) {
            this.m_bodyList.m_prev = b;
        }
        this.m_bodyList = b;
        ++this.m_bodyCount;
        return b;
    }
    
    public Body createDynamicBody(final BodyDef def) {
        assert !this.m_lock;
        if (this.m_lock) {
            return null;
        }
        final Body b = new Body(def, 1, this);
        b.m_prev = null;
        b.m_next = this.m_bodyList;
        if (this.m_bodyList != null) {
            this.m_bodyList.m_prev = b;
        }
        this.m_bodyList = b;
        ++this.m_bodyCount;
        return b;
    }
    
    public void destroyBody(final Body b) {
        assert this.m_bodyCount > 0;
        assert !this.m_lock;
        if (this.m_lock) {
            return;
        }
        JointEdge jn = b.m_jointList;
        while (jn != null) {
            final JointEdge jn2 = jn;
            jn = jn.next;
            if (this.m_destructionListener != null) {
                this.m_destructionListener.sayGoodbye(jn2.joint);
            }
            this.destroyJoint(jn2.joint);
        }
        Shape s = b.m_shapeList;
        while (s != null) {
            final Shape s2 = s;
            s = s.m_next;
            if (this.m_destructionListener != null) {
                this.m_destructionListener.sayGoodbye(s2);
            }
            s2.destroyProxy(this.m_broadPhase);
            Shape.destroy(s2);
        }
        if (b.m_prev != null) {
            b.m_prev.m_next = b.m_next;
        }
        if (b.m_next != null) {
            b.m_next.m_prev = b.m_prev;
        }
        if (b == this.m_bodyList) {
            this.m_bodyList = b.m_next;
        }
        --this.m_bodyCount;
    }
    
    public Joint createJoint(final JointDef def) {
        assert !this.m_lock;
        final Joint j = Joint.create(def);
        j.m_prev = null;
        j.m_next = this.m_jointList;
        if (this.m_jointList != null) {
            this.m_jointList.m_prev = j;
        }
        this.m_jointList = j;
        ++this.m_jointCount;
        j.m_node1.joint = j;
        j.m_node1.other = j.m_body2;
        j.m_node1.prev = null;
        j.m_node1.next = j.m_body1.m_jointList;
        if (j.m_body1.m_jointList != null) {
            j.m_body1.m_jointList.prev = j.m_node1;
        }
        j.m_body1.m_jointList = j.m_node1;
        j.m_node2.joint = j;
        j.m_node2.other = j.m_body1;
        j.m_node2.prev = null;
        j.m_node2.next = j.m_body2.m_jointList;
        if (j.m_body2.m_jointList != null) {
            j.m_body2.m_jointList.prev = j.m_node2;
        }
        j.m_body2.m_jointList = j.m_node2;
        if (!def.collideConnected) {
            final Body b = (def.body1.m_shapeCount < def.body2.m_shapeCount) ? def.body1 : def.body2;
            for (Shape s = b.m_shapeList; s != null; s = s.m_next) {
                s.resetProxy(this.m_broadPhase, b.m_xf);
            }
        }
        return j;
    }
    
    public void destroyJoint(final Joint j) {
        assert !this.m_lock;
        final boolean collideConnected = j.m_collideConnected;
        if (j.m_prev != null) {
            j.m_prev.m_next = j.m_next;
        }
        if (j.m_next != null) {
            j.m_next.m_prev = j.m_prev;
        }
        if (j == this.m_jointList) {
            this.m_jointList = j.m_next;
        }
        final Body body1 = j.m_body1;
        final Body body2 = j.m_body2;
        body1.wakeUp();
        body2.wakeUp();
        if (j.m_node1.prev != null) {
            j.m_node1.prev.next = j.m_node1.next;
        }
        if (j.m_node1.next != null) {
            j.m_node1.next.prev = j.m_node1.prev;
        }
        if (j.m_node1 == body1.m_jointList) {
            body1.m_jointList = j.m_node1.next;
        }
        j.m_node1.prev = null;
        j.m_node1.next = null;
        if (j.m_node2.prev != null) {
            j.m_node2.prev.next = j.m_node2.next;
        }
        if (j.m_node2.next != null) {
            j.m_node2.next.prev = j.m_node2.prev;
        }
        if (j.m_node2 == body2.m_jointList) {
            body2.m_jointList = j.m_node2.next;
        }
        j.m_node2.prev = null;
        j.m_node2.next = null;
        Joint.destroy(j);
        assert this.m_jointCount > 0;
        --this.m_jointCount;
        if (!collideConnected) {
            final Body b = (body1.m_shapeCount < body2.m_shapeCount) ? body1 : body2;
            for (Shape s = b.m_shapeList; s != null; s = s.m_next) {
                s.resetProxy(this.m_broadPhase, b.m_xf);
            }
        }
    }
    
    public void step(final float dt, final int iterations) {
        this.m_lock = true;
        final TimeStep step = new TimeStep();
        step.dt = dt;
        step.maxIterations = iterations;
        if (dt > 0.0f) {
            step.inv_dt = 1.0f / dt;
        }
        else {
            step.inv_dt = 0.0f;
        }
        this.m_contactManager.collide();
        if (step.dt > 0.0f) {
            this.solve(step);
        }
        if (World.ENABLE_TOI && step.dt > 0.0f) {
            this.solveTOI(step);
        }
        this.drawDebugData();
        this.m_lock = false;
    }
    
    public Shape[] query(final AABB aabb, final int maxCount) {
        final Object[] objs = this.m_broadPhase.query(aabb, maxCount);
        final Shape[] ret = new Shape[objs.length];
        System.arraycopy(objs, 0, ret, 0, objs.length);
        return ret;
    }
    
    public void solve(final TimeStep step) {
        this.m_positionIterationCount = 0;
        final Island island = new Island(this.m_bodyCount, this.m_contactCount, this.m_jointCount, this.m_contactListener);
        for (Body b = this.m_bodyList; b != null; b = b.m_next) {
            final Body body = b;
            body.m_flags &= 0xFFFFFFFB;
        }
        for (Contact c = this.m_contactList; c != null; c = c.m_next) {
            final Contact contact = c;
            contact.m_flags &= 0xFFFFFFFB;
        }
        for (Joint j = this.m_jointList; j != null; j = j.m_next) {
            j.m_islandFlag = false;
        }
        final int stackSize = this.m_bodyCount;
        final Body[] stack = new Body[stackSize];
        for (Body seed = this.m_bodyList; seed != null; seed = seed.m_next) {
            if ((seed.m_flags & 0xE) <= 0) {
                if (!seed.isStatic()) {
                    island.clear();
                    int stackCount = 0;
                    stack[stackCount++] = seed;
                    final Body body2 = seed;
                    body2.m_flags |= 0x4;
                    while (stackCount > 0) {
                        final Body b2 = stack[--stackCount];
                        island.add(b2);
                        final Body body3 = b2;
                        body3.m_flags &= 0xFFFFFFF7;
                        if (b2.isStatic()) {
                            continue;
                        }
                        for (ContactEdge cn = b2.m_contactList; cn != null; cn = cn.next) {
                            if ((cn.contact.m_flags & 0x5) <= 0) {
                                if (cn.contact.getManifoldCount() != 0) {
                                    island.add(cn.contact);
                                    final Contact contact2 = cn.contact;
                                    contact2.m_flags |= 0x4;
                                    final Body other = cn.other;
                                    if ((other.m_flags & 0x4) <= 0) {
                                        assert stackCount < stackSize;
                                        stack[stackCount++] = other;
                                        final Body body4 = other;
                                        body4.m_flags |= 0x4;
                                    }
                                }
                            }
                        }
                        for (JointEdge jn = b2.m_jointList; jn != null; jn = jn.next) {
                            if (!jn.joint.m_islandFlag) {
                                island.add(jn.joint);
                                jn.joint.m_islandFlag = true;
                                final Body other = jn.other;
                                if ((other.m_flags & 0x4) <= 0) {
                                    assert stackCount < stackSize;
                                    stack[stackCount++] = other;
                                    final Body body5 = other;
                                    body5.m_flags |= 0x4;
                                }
                            }
                        }
                    }
                    island.solve(step, this.m_gravity, World.ENABLE_POSITION_CORRECTION, this.m_allowSleep);
                    this.m_positionIterationCount = Math.max(this.m_positionIterationCount, Island.m_positionIterationCount);
                    for (int i = 0; i < island.m_bodyCount; ++i) {
                        final Body b3 = island.m_bodies[i];
                        if (b3.isStatic()) {
                            final Body body6 = b3;
                            body6.m_flags &= 0xFFFFFFFB;
                        }
                    }
                }
            }
        }
        for (Body b4 = this.m_bodyList; b4 != null; b4 = b4.getNext()) {
            if ((b4.m_flags & 0xA) == 0x0) {
                if (!b4.isStatic()) {
                    final boolean inRange = b4.synchronizeShapes();
                    if (!inRange && this.m_boundaryListener != null) {
                        this.m_boundaryListener.violation(b4);
                    }
                }
            }
        }
        this.m_broadPhase.commit();
    }
    
    public void solveTOI(final TimeStep step) {
        final Island island = new Island(this.m_bodyCount, Settings.maxTOIContactsPerIsland, 0, this.m_contactListener);
        final int stackSize = this.m_bodyCount;
        final Body[] stack = new Body[stackSize];
        for (Body b = this.m_bodyList; b != null; b = b.m_next) {
            final Body body = b;
            body.m_flags &= 0xFFFFFFFB;
            b.m_sweep.t0 = 0.0f;
        }
        for (Contact c = this.m_contactList; c != null; c = c.m_next) {
            final Contact contact = c;
            contact.m_flags &= 0xFFFFFFF3;
        }
        while (true) {
            Contact minContact = null;
            float minTOI = 1.0f;
            for (Contact c2 = this.m_contactList; c2 != null; c2 = c2.m_next) {
                if ((c2.m_flags & 0x3) == 0x0) {
                    float toi = 1.0f;
                    if ((c2.m_flags & 0x8) != 0x0) {
                        toi = c2.m_toi;
                    }
                    else {
                        final Shape s1 = c2.getShape1();
                        final Shape s2 = c2.getShape2();
                        final Body b2 = s1.getBody();
                        final Body b3 = s2.getBody();
                        if (b2.isStatic() || b2.isSleeping()) {
                            if (b3.isStatic()) {
                                continue;
                            }
                            if (b3.isSleeping()) {
                                continue;
                            }
                        }
                        float t0 = b2.m_sweep.t0;
                        if (b2.m_sweep.t0 < b3.m_sweep.t0) {
                            t0 = b3.m_sweep.t0;
                            b2.m_sweep.advance(t0);
                        }
                        else if (b3.m_sweep.t0 < b2.m_sweep.t0) {
                            t0 = b2.m_sweep.t0;
                            b3.m_sweep.advance(t0);
                        }
                        assert t0 < 1.0f;
                        toi = TOI.timeOfImpact(c2.m_shape1, b2.m_sweep, c2.m_shape2, b3.m_sweep);
                        assert 0.0f <= toi && toi <= 1.0f;
                        if (toi > 0.0f && toi < 1.0f) {
                            toi = Math.min((1.0f - toi) * t0 + toi, 1.0f);
                        }
                        c2.m_toi = toi;
                        final Contact contact2 = c2;
                        contact2.m_flags |= 0x8;
                    }
                    if (1.1920929E-7f < toi && toi < minTOI) {
                        minContact = c2;
                        minTOI = toi;
                    }
                }
            }
            if (minContact == null || 0.9999881f < minTOI) {
                return;
            }
            final Shape s3 = minContact.getShape1();
            final Shape s4 = minContact.getShape2();
            final Body b4 = s3.getBody();
            final Body b5 = s4.getBody();
            b4.advance(minTOI);
            b5.advance(minTOI);
            minContact.update(this.m_contactListener);
            final Contact contact3 = minContact;
            contact3.m_flags &= 0xFFFFFFF7;
            if (minContact.getManifoldCount() == 0) {
                continue;
            }
            Body seed = b4;
            if (seed.isStatic()) {
                seed = b5;
            }
            island.clear();
            int stackCount = 0;
            stack[stackCount++] = seed;
            final Body body2 = seed;
            body2.m_flags |= 0x4;
            while (stackCount > 0) {
                final Body b6 = stack[--stackCount];
                island.add(b6);
                final Body body3 = b6;
                body3.m_flags &= 0xFFFFFFF7;
                if (b6.isStatic()) {
                    continue;
                }
                for (ContactEdge cn = b6.m_contactList; cn != null; cn = cn.next) {
                    if (island.m_contactCount != island.m_contactCapacity) {
                        if ((cn.contact.m_flags & 0x7) == 0x0) {
                            if (cn.contact.getManifoldCount() != 0) {
                                island.add(cn.contact);
                                final Contact contact4 = cn.contact;
                                contact4.m_flags |= 0x4;
                                final Body other = cn.other;
                                if ((other.m_flags & 0x4) == 0x0) {
                                    if (!other.isStatic()) {
                                        other.advance(minTOI);
                                        other.wakeUp();
                                    }
                                    assert stackCount < stackSize;
                                    stack[stackCount++] = other;
                                    final Body body4 = other;
                                    body4.m_flags |= 0x4;
                                }
                            }
                        }
                    }
                }
            }
            final TimeStep subStep = new TimeStep();
            subStep.dt = (1.0f - minTOI) * step.dt;
            assert subStep.dt > 1.1920929E-7f;
            subStep.inv_dt = 1.0f / subStep.dt;
            subStep.maxIterations = step.maxIterations;
            island.solveTOI(subStep);
            for (int i = 0; i < island.m_bodyCount; ++i) {
                final Body body5;
                final Body b7 = body5 = island.m_bodies[i];
                body5.m_flags &= 0xFFFFFFFB;
                if ((b7.m_flags & 0xA) == 0x0) {
                    if (!b7.isStatic()) {
                        final boolean inRange = b7.synchronizeShapes();
                        if (!inRange && this.m_boundaryListener != null) {
                            this.m_boundaryListener.violation(b7);
                        }
                        for (ContactEdge cn2 = b7.m_contactList; cn2 != null; cn2 = cn2.next) {
                            final Contact contact5 = cn2.contact;
                            contact5.m_flags &= 0xFFFFFFF7;
                        }
                    }
                }
            }
            for (int i = 0; i < island.m_contactCount; ++i) {
                final Contact contact6;
                final Contact c3 = contact6 = island.m_contacts[i];
                contact6.m_flags &= 0xFFFFFFF3;
            }
            this.m_broadPhase.commit();
        }
    }
    
    public void drawShape(final Shape shape, final XForm xf, final Color3f color, final boolean core) {
        final Color3f coreColor = new Color3f(229.5f, 153.0f, 153.0f);
        if (shape.m_type == ShapeType.CIRCLE_SHAPE) {
            final CircleShape circle = (CircleShape)shape;
            final Vec2 center = XForm.mul(xf, circle.getLocalPosition());
            final float radius = circle.getRadius();
            final Vec2 axis = xf.R.col1;
            this.m_debugDraw.drawSolidCircle(center, radius, axis, color);
            if (core) {
                this.m_debugDraw.drawCircle(center, radius - 0.04f, coreColor);
            }
        }
        else if (shape.m_type == ShapeType.POLYGON_SHAPE) {
            final PolygonShape poly = (PolygonShape)shape;
            final int vertexCount = poly.getVertexCount();
            final Vec2[] localVertices = poly.getVertices();
            assert vertexCount <= 8;
            final Vec2[] vertices = new Vec2[8];
            for (int i = 0; i < vertexCount; ++i) {
                vertices[i] = XForm.mul(xf, localVertices[i]);
            }
            this.m_debugDraw.drawSolidPolygon(vertices, vertexCount, color);
            if (core) {
                final Vec2[] localCoreVertices = poly.getCoreVertices();
                for (int j = 0; j < vertexCount; ++j) {
                    vertices[j] = XForm.mul(xf, localCoreVertices[j]);
                }
                this.m_debugDraw.drawPolygon(vertices, vertexCount, coreColor);
            }
        }
    }
    
    public void drawJoint(final Joint joint) {
        final Body b1 = joint.getBody1();
        final Body b2 = joint.getBody2();
        final XForm xf1 = b1.getXForm();
        final XForm xf2 = b2.getXForm();
        final Vec2 x1 = xf1.position;
        final Vec2 x2 = xf2.position;
        final Vec2 p1 = joint.getAnchor1();
        final Vec2 p2 = joint.getAnchor2();
        final Color3f color = new Color3f(127.5f, 204.0f, 204.0f);
        final JointType type = joint.getType();
        if (type == JointType.DISTANCE_JOINT) {
            this.m_debugDraw.drawSegment(p1, p2, color);
        }
        else if (type == JointType.PULLEY_JOINT) {
            final PulleyJoint pulley = (PulleyJoint)joint;
            final Vec2 s1 = pulley.getGroundAnchor1();
            final Vec2 s2 = pulley.getGroundAnchor2();
            this.m_debugDraw.drawSegment(s1, p1, color);
            this.m_debugDraw.drawSegment(s2, p2, color);
            this.m_debugDraw.drawSegment(s1, s2, color);
        }
        else if (type != JointType.MOUSE_JOINT) {
            this.m_debugDraw.drawSegment(x1, p1, color);
            this.m_debugDraw.drawSegment(p1, p2, color);
            this.m_debugDraw.drawSegment(x2, p2, color);
        }
    }
    
    public void drawDebugData() {
        if (this.m_debugDraw == null) {
            return;
        }
        final int flags = this.m_debugDraw.getFlags();
        if ((flags & 0x1) != 0x0) {
            final boolean core = (flags & 0x4) == 0x4;
            for (Body b = this.m_bodyList; b != null; b = b.getNext()) {
                final XForm xf = b.getXForm();
                for (Shape s = b.getShapeList(); s != null; s = s.getNext()) {
                    if (b.isStatic()) {
                        this.drawShape(s, xf, new Color3f(127.5f, 229.5f, 127.5f), core);
                    }
                    else if (b.isSleeping()) {
                        this.drawShape(s, xf, new Color3f(127.5f, 127.5f, 229.5f), core);
                    }
                    else {
                        this.drawShape(s, xf, new Color3f(229.5f, 229.5f, 229.5f), core);
                    }
                }
            }
        }
        if ((flags & 0x2) != 0x0) {
            for (Joint j = this.m_jointList; j != null; j = j.getNext()) {
                if (j.getType() != JointType.MOUSE_JOINT) {
                    this.drawJoint(j);
                }
            }
        }
        if ((flags & 0x20) != 0x0) {
            final BroadPhase bp = this.m_broadPhase;
            final Vec2 invQ = new Vec2(0.0f, 0.0f);
            invQ.set(1.0f / bp.m_quantizationFactor.x, 1.0f / bp.m_quantizationFactor.y);
            final Color3f color = new Color3f(229.5f, 229.5f, 76.5f);
            for (int i = 0; i < 16384; ++i) {
                Pair pair;
                for (int index = bp.m_pairManager.m_hashTable[i]; index != Integer.MAX_VALUE; index = pair.next) {
                    pair = bp.m_pairManager.m_pairs[index];
                    final Proxy p1 = bp.m_proxyPool[pair.proxyId1];
                    final Proxy p2 = bp.m_proxyPool[pair.proxyId2];
                    final AABB b2 = new AABB();
                    final AABB b3 = new AABB();
                    b2.lowerBound.x = bp.m_worldAABB.lowerBound.x + invQ.x * bp.m_bounds[0][p1.lowerBounds[0]].value;
                    b2.lowerBound.y = bp.m_worldAABB.lowerBound.y + invQ.y * bp.m_bounds[1][p1.lowerBounds[1]].value;
                    b2.upperBound.x = bp.m_worldAABB.lowerBound.x + invQ.x * bp.m_bounds[0][p1.upperBounds[0]].value;
                    b2.upperBound.y = bp.m_worldAABB.lowerBound.y + invQ.y * bp.m_bounds[1][p1.upperBounds[1]].value;
                    b3.lowerBound.x = bp.m_worldAABB.lowerBound.x + invQ.x * bp.m_bounds[0][p2.lowerBounds[0]].value;
                    b3.lowerBound.y = bp.m_worldAABB.lowerBound.y + invQ.y * bp.m_bounds[1][p2.lowerBounds[1]].value;
                    b3.upperBound.x = bp.m_worldAABB.lowerBound.x + invQ.x * bp.m_bounds[0][p2.upperBounds[0]].value;
                    b3.upperBound.y = bp.m_worldAABB.lowerBound.y + invQ.y * bp.m_bounds[1][p2.upperBounds[1]].value;
                    final Vec2 x1 = new Vec2(0.5f * (b2.lowerBound.x + b2.upperBound.x), 0.5f * (b2.lowerBound.y + b2.upperBound.y));
                    final Vec2 x2 = new Vec2(0.5f * (b3.lowerBound.x + b3.upperBound.x), 0.5f * (b3.lowerBound.y + b3.upperBound.y));
                    this.m_debugDraw.drawSegment(x1, x2, color);
                }
            }
        }
        final BroadPhase bp = this.m_broadPhase;
        final Vec2 worldLower = bp.m_worldAABB.lowerBound;
        final Vec2 worldUpper = bp.m_worldAABB.upperBound;
        if ((flags & 0x8) != 0x0) {
            final Vec2 invQ2 = new Vec2();
            invQ2.set(1.0f / bp.m_quantizationFactor.x, 1.0f / bp.m_quantizationFactor.y);
            final Color3f color2 = new Color3f(229.5f, 76.5f, 229.5f);
            for (int k = 0; k < 2048; ++k) {
                final Proxy p3 = bp.m_proxyPool[k];
                if (p3.isValid()) {
                    final AABB b4 = new AABB();
                    b4.lowerBound.x = worldLower.x + invQ2.x * bp.m_bounds[0][p3.lowerBounds[0]].value;
                    b4.lowerBound.y = worldLower.y + invQ2.y * bp.m_bounds[1][p3.lowerBounds[1]].value;
                    b4.upperBound.x = worldLower.x + invQ2.x * bp.m_bounds[0][p3.upperBounds[0]].value;
                    b4.upperBound.y = worldLower.y + invQ2.y * bp.m_bounds[1][p3.upperBounds[1]].value;
                    final Vec2[] vs = { new Vec2(b4.lowerBound.x, b4.lowerBound.y), new Vec2(b4.upperBound.x, b4.lowerBound.y), new Vec2(b4.upperBound.x, b4.upperBound.y), new Vec2(b4.lowerBound.x, b4.upperBound.y) };
                    this.m_debugDraw.drawPolygon(vs, 4, color2);
                }
            }
        }
        final Vec2[] vsw = { new Vec2(worldLower.x, worldLower.y), new Vec2(worldUpper.x, worldLower.y), new Vec2(worldUpper.x, worldUpper.y), new Vec2(worldLower.x, worldUpper.y) };
        this.m_debugDraw.drawPolygon(vsw, 4, new Color3f(76.5f, 229.5f, 229.5f));
        if ((flags & 0x10) != 0x0) {
            final Color3f color2 = new Color3f(0.5f, 0.3f, 0.5f);
            for (Body b5 = this.m_bodyList; b5 != null; b5 = b5.getNext()) {
                final XForm xf2 = b5.getXForm();
                for (Shape s2 = b5.getShapeList(); s2 != null; s2 = s2.getNext()) {
                    if (s2.getType() == ShapeType.POLYGON_SHAPE) {
                        final PolygonShape poly = (PolygonShape)s2;
                        final OBB obb = poly.getOBB();
                        final Vec2 h = obb.extents;
                        final Vec2[] vs2 = { new Vec2(-h.x, -h.y), new Vec2(h.x, -h.y), new Vec2(h.x, h.y), new Vec2(-h.x, h.y) };
                        for (int l = 0; l < 4; ++l) {
                            vs2[l] = obb.center.add(Mat22.mul(obb.R, vs2[l]));
                            vs2[l] = XForm.mul(xf2, vs2[l]);
                        }
                        this.m_debugDraw.drawPolygon(vs2, 4, color2);
                    }
                }
            }
        }
        if ((flags & 0x40) != 0x0) {
            for (Body b6 = this.m_bodyList; b6 != null; b6 = b6.getNext()) {
                final XForm xf3 = b6.getXForm();
                xf3.position = b6.getWorldCenter();
                this.m_debugDraw.drawXForm(xf3);
            }
        }
    }
}
