// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.WeakIdentityHashMap;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.lang.ref.ReferenceQueue;

public class ObjectSpace
{
    private ReferenceQueue deadReferences;
    private WeakReferenceListNode top;
    private ReferenceQueue deadIdentityReferences;
    private final Map identities;
    private final Map identitiesByObject;
    private static final AtomicLong maxId;
    
    public ObjectSpace() {
        this.deadReferences = new ReferenceQueue();
        this.deadIdentityReferences = new ReferenceQueue();
        this.identities = new HashMap();
        this.identitiesByObject = new WeakIdentityHashMap();
    }
    
    public void registerObjectId(final long id, final IRubyObject object) {
        synchronized (this.identities) {
            this.cleanIdentities();
            this.identities.put(id, new IdReference(object, id, this.deadIdentityReferences));
            this.identitiesByObject.put(object, id);
        }
    }
    
    public static long calculateObjectId(final Object object) {
        return ObjectSpace.maxId.getAndIncrement() * 2L;
    }
    
    public long createAndRegisterObjectId(final IRubyObject rubyObject) {
        synchronized (this.identities) {
            Long longId = this.identitiesByObject.get(rubyObject);
            if (longId == null) {
                longId = this.createId(rubyObject);
            }
            return longId;
        }
    }
    
    private long createId(final IRubyObject object) {
        final long id = calculateObjectId(object);
        this.registerObjectId(id, object);
        return id;
    }
    
    public IRubyObject id2ref(final long id) {
        synchronized (this.identities) {
            this.cleanIdentities();
            final IdReference reference = this.identities.get(id);
            if (reference == null) {
                return null;
            }
            return reference.get();
        }
    }
    
    private void cleanIdentities() {
        IdReference ref;
        while ((ref = (IdReference)this.deadIdentityReferences.poll()) != null) {
            this.identities.remove(ref.id());
        }
    }
    
    @Deprecated
    public long idOf(final IRubyObject rubyObject) {
        return this.createAndRegisterObjectId(rubyObject);
    }
    
    public void addFinalizer(final IRubyObject object, final IRubyObject proc) {
        object.addFinalizer(proc);
    }
    
    public void removeFinalizers(final long id) {
        final IRubyObject object = this.id2ref(id);
        if (object != null) {
            object.removeFinalizers();
        }
    }
    
    public synchronized void add(final IRubyObject object) {
        this.cleanup();
        this.top = new WeakReferenceListNode(object, this.deadReferences, this.top);
    }
    
    public synchronized Iterator iterator(final RubyModule rubyClass) {
        final List objList = new ArrayList();
        for (WeakReferenceListNode current = this.top; current != null; current = current.nextNode) {
            final IRubyObject obj = current.get();
            if (obj != null && rubyClass.isInstance(obj)) {
                objList.add(current);
            }
        }
        return new Iterator() {
            private Iterator iter = objList.iterator();
            
            public boolean hasNext() {
                throw new UnsupportedOperationException();
            }
            
            public Object next() {
                Object obj = null;
                while (this.iter.hasNext()) {
                    final WeakReferenceListNode node = this.iter.next();
                    obj = node.get();
                    if (obj != null) {
                        break;
                    }
                }
                return obj;
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    private synchronized void cleanup() {
        WeakReferenceListNode reference;
        while ((reference = (WeakReferenceListNode)this.deadReferences.poll()) != null) {
            reference.remove();
        }
    }
    
    static {
        maxId = new AtomicLong(1000L);
    }
    
    private class WeakReferenceListNode extends WeakReference
    {
        private WeakReferenceListNode prevNode;
        private WeakReferenceListNode nextNode;
        
        public WeakReferenceListNode(final Object ref, final ReferenceQueue queue, final WeakReferenceListNode next) {
            super(ref, queue);
            this.nextNode = next;
            if (next != null) {
                next.prevNode = this;
            }
        }
        
        public void remove() {
            synchronized (ObjectSpace.this) {
                if (this.prevNode != null) {
                    this.prevNode.nextNode = this.nextNode;
                }
                else {
                    ObjectSpace.this.top = this.nextNode;
                }
                if (this.nextNode != null) {
                    this.nextNode.prevNode = this.prevNode;
                }
            }
        }
    }
    
    private static class IdReference extends WeakReference
    {
        private final long id;
        
        public IdReference(final IRubyObject object, final long id, final ReferenceQueue queue) {
            super(object, queue);
            this.id = id;
        }
        
        public long id() {
            return this.id;
        }
    }
}
