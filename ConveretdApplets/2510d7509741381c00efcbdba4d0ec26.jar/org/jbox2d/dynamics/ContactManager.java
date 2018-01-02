// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.dynamics;

import org.jbox2d.collision.ManifoldPoint;
import java.util.List;
import org.jbox2d.collision.ContactID;
import org.jbox2d.common.XForm;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.collision.Shape;
import org.jbox2d.dynamics.contacts.NullContact;
import org.jbox2d.collision.PairCallback;

public class ContactManager extends PairCallback
{
    World m_world;
    NullContact m_nullContact;
    boolean m_destroyImmediate;
    
    public ContactManager() {
        this.m_nullContact = new NullContact();
        this.m_destroyImmediate = false;
    }
    
    public Object pairAdded(final Object proxyUserData1, final Object proxyUserData2) {
        Shape shape1 = (Shape)proxyUserData1;
        Shape shape2 = (Shape)proxyUserData2;
        Body body1 = shape1.getBody();
        Body body2 = shape2.getBody();
        if (body1.isStatic() && body2.isStatic()) {
            return this.m_nullContact;
        }
        if (shape1.m_body == shape2.m_body) {
            return this.m_nullContact;
        }
        if (body2.isConnected(body1)) {
            return this.m_nullContact;
        }
        if (this.m_world.m_contactFilter != null && !this.m_world.m_contactFilter.shouldCollide(shape1, shape2)) {
            return this.m_nullContact;
        }
        final Contact c = Contact.createContact(shape1, shape2);
        if (c == null) {
            return this.m_nullContact;
        }
        shape1 = c.getShape1();
        shape2 = c.getShape2();
        body1 = shape1.getBody();
        body2 = shape2.getBody();
        c.m_prev = null;
        c.m_next = this.m_world.m_contactList;
        if (this.m_world.m_contactList != null) {
            this.m_world.m_contactList.m_prev = c;
        }
        this.m_world.m_contactList = c;
        c.m_node1.contact = c;
        c.m_node1.other = body2;
        c.m_node1.prev = null;
        c.m_node1.next = body1.m_contactList;
        if (body1.m_contactList != null) {
            body1.m_contactList.prev = c.m_node1;
        }
        body1.m_contactList = c.m_node1;
        c.m_node2.contact = c;
        c.m_node2.other = body1;
        c.m_node2.prev = null;
        c.m_node2.next = body2.m_contactList;
        if (body2.m_contactList != null) {
            body2.m_contactList.prev = c.m_node2;
        }
        body2.m_contactList = c.m_node2;
        final World world = this.m_world;
        ++world.m_contactCount;
        return c;
    }
    
    public void pairRemoved(final Object proxyUserData1, final Object proxyUserData2, final Object pairUserData) {
        if (pairUserData == null) {
            return;
        }
        final Contact c = (Contact)pairUserData;
        if (c == this.m_nullContact) {
            return;
        }
        this.destroy(c);
    }
    
    public void destroy(final Contact c) {
        final Shape shape1 = c.getShape1();
        final Shape shape2 = c.getShape2();
        final int manifoldCount = c.getManifoldCount();
        if (manifoldCount > 0 && this.m_world.m_contactListener != null) {
            final ContactPoint cp = new ContactPoint();
            cp.shape1 = c.getShape1();
            cp.shape2 = c.getShape2();
            final Body b1 = cp.shape1.getBody();
            final List<Manifold> manifolds = c.getManifolds();
            for (int i = 0; i < manifoldCount; ++i) {
                final Manifold manifold = manifolds.get(i);
                cp.normal.set(manifold.normal);
                for (int j = 0; j < manifold.pointCount; ++j) {
                    final ManifoldPoint point = manifold.points[j];
                    cp.position = XForm.mul(b1.getXForm(), point.localPoint1);
                    cp.separation = point.separation;
                    cp.normalForce = point.normalForce;
                    cp.tangentForce = point.tangentForce;
                    cp.id = new ContactID(point.id);
                    this.m_world.m_contactListener.remove(cp);
                }
            }
        }
        if (c.m_prev != null) {
            c.m_prev.m_next = c.m_next;
        }
        if (c.m_next != null) {
            c.m_next.m_prev = c.m_prev;
        }
        if (c == this.m_world.m_contactList) {
            this.m_world.m_contactList = c.m_next;
        }
        final Body body1 = shape1.getBody();
        final Body body2 = shape2.getBody();
        if (c.m_node1.prev != null) {
            c.m_node1.prev.next = c.m_node1.next;
        }
        if (c.m_node1.next != null) {
            c.m_node1.next.prev = c.m_node1.prev;
        }
        if (c.m_node1 == body1.m_contactList) {
            body1.m_contactList = c.m_node1.next;
        }
        if (c.m_node2.prev != null) {
            c.m_node2.prev.next = c.m_node2.next;
        }
        if (c.m_node2.next != null) {
            c.m_node2.next.prev = c.m_node2.prev;
        }
        if (c.m_node2 == body2.m_contactList) {
            body2.m_contactList = c.m_node2.next;
        }
        Contact.destroy(c);
        final World world = this.m_world;
        --world.m_contactCount;
    }
    
    public void collide() {
        for (Contact c = this.m_world.m_contactList; c != null; c = c.getNext()) {
            final Body body1 = c.getShape1().getBody();
            final Body body2 = c.getShape2().getBody();
            if (!body1.isSleeping() || !body2.isSleeping()) {
                c.update(this.m_world.m_contactListener);
                if (!c.isSolid() && this.m_world.m_contactListener != null) {
                    final ContactPoint cp = new ContactPoint();
                    cp.shape1 = c.getShape1();
                    cp.shape2 = c.getShape2();
                    cp.normalForce = 0.0f;
                    cp.tangentForce = 0.0f;
                    final Body b1 = cp.shape1.getBody();
                    final int manifoldCount = c.getManifoldCount();
                    final List<Manifold> manifolds = c.getManifolds();
                    for (int i = 0; i < manifoldCount; ++i) {
                        final Manifold manifold = manifolds.get(i);
                        cp.normal.set(manifold.normal);
                        for (int j = 0; j < manifold.pointCount; ++j) {
                            final ManifoldPoint point = manifold.points[j];
                            cp.position = XForm.mul(b1.getXForm(), point.localPoint1);
                            cp.separation = point.separation;
                            if ((point.id.features.flip & 0x2) != 0x0) {
                                final ContactID.Features features = point.id.features;
                                features.flip &= 0xFFFFFFFD;
                                cp.id = new ContactID(point.id);
                                this.m_world.m_contactListener.add(cp);
                            }
                            else {
                                cp.id = new ContactID(point.id);
                                this.m_world.m_contactListener.persist(cp);
                            }
                        }
                    }
                }
            }
        }
    }
}
