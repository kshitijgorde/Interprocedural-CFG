// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import org.xmodel.Element;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.xmodel.IModelObject;

public class Mutex extends Access
{
    public Mutex(final FeatureAccess fa, final String targetId) {
        super(fa, targetId);
    }
    
    public Mutex(final IModelObject root) {
        super(root);
    }
    
    protected List<Mutex> lock(final Map<Mutex, Integer> locks) {
        Mutex.log.debug("Acquiring Mutex:" + this);
        if (locks.containsKey(this)) {
            final int count = locks.get(this);
            locks.put(this, count + 1);
            return Collections.emptyList();
        }
        locks.put(this, 1);
        return Collections.singletonList(this);
    }
    
    protected List<Mutex> unlock(final Map<Mutex, Integer> locks) {
        Mutex.log.debug("Releasing Mutex:" + this);
        if (locks.containsKey(this)) {
            int count = locks.get(this);
            if (--count == 0) {
                locks.remove(this);
                return Collections.singletonList(this);
            }
            locks.put(this, count);
        }
        return Collections.emptyList();
    }
    
    protected boolean isLocked(final Map<Mutex, Integer> locks) {
        return locks.containsKey(this);
    }
    
    @Override
    public int hashCode() {
        return this.fa.toString().hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!this.getClass().equals(o.getClass())) {
            return false;
        }
        final Mutex mutex = (Mutex)o;
        return this.fa.equals(mutex.fa) && ((this.targetId == null && mutex.getTargetId() == null) || this.targetId.equals(mutex.getTargetId()));
    }
    
    @Override
    public IModelObject getRoot() {
        final IModelObject root = new Element("en:mutex");
        if (this.fa.hasVariable()) {
            root.setAttribute(this.fa.getVariableName(), this.getTargetId());
        }
        root.setValue(this.fa.getType().name());
        return root;
    }
}
