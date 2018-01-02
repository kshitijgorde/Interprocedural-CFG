// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

public abstract class Object
{
    protected String name;
    protected Object parent;
    protected int flags;
    public static final int OBJECT_FLAG_LAST = 16;
    
    public synchronized String getName() {
        return this.name;
    }
    
    public synchronized void setName(final String name) {
        this.name = name;
    }
    
    public Object() {
        this("unnamed");
    }
    
    public Object(final String name) {
        this.name = name;
        this.parent = null;
    }
    
    public synchronized boolean setParent(final Object parent) {
        if (this.parent != null) {
            return false;
        }
        this.parent = parent;
        return true;
    }
    
    public synchronized Object getParent() {
        return this.parent;
    }
    
    public synchronized void unParent() {
        this.parent = null;
    }
    
    public synchronized void setFlag(final int n) {
        this.flags |= n;
    }
    
    public synchronized void unsetFlag(final int n) {
        this.flags &= ~n;
    }
    
    public synchronized boolean isFlagSet(final int n) {
        return (this.flags & n) == n;
    }
    
    public synchronized boolean setProperty(final String s, final java.lang.Object o) {
        return false;
    }
    
    public synchronized java.lang.Object getProperty(final String s) {
        return null;
    }
}
