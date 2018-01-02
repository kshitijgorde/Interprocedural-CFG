// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics.contacts;

import org.jbox2d.dynamics.Body;
import java.util.Iterator;
import org.jbox2d.collision.ShapeType;
import java.util.ArrayList;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.collision.Shape;
import org.jbox2d.dynamics.World;
import java.util.List;

public abstract class Contact
{
    public static final int e_nonSolidFlag = 1;
    public static final int e_slowFlag = 2;
    public static final int e_islandFlag = 4;
    public static final int e_toiFlag = 8;
    static List<ContactRegister> s_registers;
    static boolean s_initialized;
    public World m_world;
    public Contact m_prev;
    public Contact m_next;
    public ContactEdge m_node1;
    public ContactEdge m_node2;
    public Shape m_shape1;
    public Shape m_shape2;
    public float m_friction;
    public float m_restitution;
    public int m_flags;
    public int m_manifoldCount;
    public float m_toi;
    
    public abstract void evaluate(final ContactListener p0);
    
    public abstract List<Manifold> getManifolds();
    
    public int getManifoldCount() {
        return this.m_manifoldCount;
    }
    
    public boolean isSolid() {
        return (this.m_flags & 0x1) == 0x0;
    }
    
    public Contact() {
        this.m_node1 = new ContactEdge();
        this.m_node2 = new ContactEdge();
    }
    
    public Contact(final Shape s1, final Shape s2) {
        this();
        this.m_flags = 0;
        if (s1.isSensor() || s2.isSensor()) {
            this.m_flags |= 0x1;
        }
        this.m_shape1 = s1;
        this.m_shape2 = s2;
        this.m_manifoldCount = 0;
        this.getManifolds().clear();
        this.m_friction = (float)Math.sqrt(this.m_shape1.m_friction * this.m_shape2.m_friction);
        this.m_restitution = Math.max(this.m_shape1.m_restitution, this.m_shape2.m_restitution);
        this.m_prev = null;
        this.m_next = null;
        this.m_node1.contact = null;
        this.m_node1.prev = null;
        this.m_node1.next = null;
        this.m_node1.other = null;
        this.m_node2.contact = null;
        this.m_node2.prev = null;
        this.m_node2.next = null;
        this.m_node2.other = null;
    }
    
    public Contact getNext() {
        return this.m_next;
    }
    
    public Shape getShape1() {
        return this.m_shape1;
    }
    
    public Shape getShape2() {
        return this.m_shape2;
    }
    
    static void initializeRegisters() {
        Contact.s_registers = new ArrayList<ContactRegister>();
        addType(new CircleContact(), ShapeType.CIRCLE_SHAPE, ShapeType.CIRCLE_SHAPE);
        addType(new PolyAndCircleContact(), ShapeType.POLYGON_SHAPE, ShapeType.CIRCLE_SHAPE);
        addType(new PolyContact(), ShapeType.POLYGON_SHAPE, ShapeType.POLYGON_SHAPE);
    }
    
    static void addType(final ContactCreateFcn createFcn, final ShapeType type1, final ShapeType type2) {
        final ContactRegister cr = new ContactRegister();
        cr.s1 = type1;
        cr.s2 = type2;
        cr.createFcn = createFcn;
        cr.primary = true;
        Contact.s_registers.add(cr);
        if (type1 != type2) {
            final ContactRegister cr2 = new ContactRegister();
            cr2.s2 = type1;
            cr2.s1 = type2;
            cr2.createFcn = createFcn;
            cr2.primary = false;
            Contact.s_registers.add(cr2);
        }
    }
    
    public static Contact createContact(final Shape shape1, final Shape shape2) {
        if (!Contact.s_initialized) {
            initializeRegisters();
            Contact.s_initialized = true;
        }
        final ShapeType type1 = shape1.m_type;
        final ShapeType type2 = shape2.m_type;
        final ContactRegister register = getContactRegister(type1, type2);
        if (register == null) {
            return null;
        }
        if (register.primary) {
            return register.createFcn.create(shape1, shape2);
        }
        final Contact c = register.createFcn.create(shape2, shape1);
        for (int i = 0; i < c.getManifoldCount(); ++i) {
            final Manifold m = c.getManifolds().get(i);
            m.normal.negateLocal();
        }
        return c;
    }
    
    private static ContactRegister getContactRegister(final ShapeType type1, final ShapeType type2) {
        for (final ContactRegister cr : Contact.s_registers) {
            if (cr.s1 == type1 && cr.s2 == type2) {
                return cr;
            }
        }
        return null;
    }
    
    public static void destroy(final Contact contact) {
        assert Contact.s_initialized;
        if (contact.getManifoldCount() > 0) {
            contact.getShape1().getBody().wakeUp();
            contact.getShape2().getBody().wakeUp();
        }
    }
    
    public void update(final ContactListener listener) {
        final int oldCount = this.getManifoldCount();
        this.evaluate(listener);
        final int newCount = this.getManifoldCount();
        final Body body1 = this.m_shape1.getBody();
        final Body body2 = this.m_shape2.getBody();
        if (newCount == 0 && oldCount > 0) {
            body1.wakeUp();
            body2.wakeUp();
        }
        if (body1.isStatic() || body1.isBullet() || body2.isStatic() || body2.isBullet()) {
            this.m_flags &= 0xFFFFFFFD;
        }
        else {
            this.m_flags |= 0x2;
        }
    }
    
    public abstract Contact clone();
}
