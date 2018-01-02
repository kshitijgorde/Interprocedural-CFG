// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.profile;

import java.util.Iterator;
import org.jruby.util.collections.IntHashMap;

public class Invocation
{
    private final int methodSerialNumber;
    private int recursiveDepth;
    private Invocation parent;
    private final IntHashMap<Invocation> children;
    private long duration;
    private int count;
    
    public Invocation(final int serial) {
        this(null, serial);
    }
    
    public Invocation(final Invocation parent, final int serial) {
        this.duration = 0L;
        this.count = 0;
        this.parent = parent;
        this.methodSerialNumber = serial;
        this.children = new IntHashMap<Invocation>();
    }
    
    public Invocation(final Invocation parent, final int serial, final IntHashMap<Invocation> children) {
        this.duration = 0L;
        this.count = 0;
        this.parent = parent;
        this.methodSerialNumber = serial;
        this.children = children;
    }
    
    public int getMethodSerialNumber() {
        return this.methodSerialNumber;
    }
    
    public int getRecursiveDepth() {
        return this.recursiveDepth;
    }
    
    public void setRecursiveDepth(final int d) {
        this.recursiveDepth = d;
    }
    
    public Invocation getParent() {
        return this.parent;
    }
    
    public void setParent(final Invocation p) {
        this.parent = p;
    }
    
    public IntHashMap<Invocation> getChildren() {
        return this.children;
    }
    
    public long getDuration() {
        return this.duration;
    }
    
    public void setDuration(final long d) {
        this.duration = d;
    }
    
    public void addDuration(final long d) {
        this.duration += d;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public void setCount(final int c) {
        this.count = c;
    }
    
    public void incrementCount() {
        ++this.count;
    }
    
    public Invocation childInvocationFor(final int serial) {
        Invocation child;
        if ((child = this.children.get(serial)) == null) {
            child = new Invocation(this, serial);
            this.children.put(serial, child);
        }
        return child;
    }
    
    public Invocation copyWithNewSerialAndParent(final int serial, final Invocation newParent) {
        final Invocation newInv = new Invocation(newParent, serial, this.children);
        newInv.setDuration(this.duration);
        newInv.setCount(this.count);
        newInv.setRecursiveDepth(this.recursiveDepth);
        for (final Invocation child : this.children.values()) {
            child.setParent(newInv);
        }
        return newInv;
    }
    
    public void addChild(final Invocation child) {
        this.children.put(child.getMethodSerialNumber(), child);
    }
    
    public long childTime() {
        long t = 0L;
        for (final Invocation inv : this.children.values()) {
            t += inv.getDuration();
        }
        return t;
    }
    
    public long selfTime() {
        return this.duration - this.childTime();
    }
}
