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
    
    public synchronized void setName(final String newName) {
        this.name = newName;
    }
    
    public Object() {
        this("unnamed");
    }
    
    public Object(final String name) {
        this.name = name;
        this.parent = null;
    }
    
    public synchronized boolean setParent(final Object newParent) {
        if (this.parent != null) {
            return false;
        }
        this.parent = newParent;
        return true;
    }
    
    public synchronized Object getParent() {
        return this.parent;
    }
    
    public synchronized void unParent() {
        this.parent = null;
    }
    
    public synchronized void setFlag(final int flag) {
        this.flags |= flag;
    }
    
    public synchronized void unsetFlag(final int flag) {
        this.flags &= ~flag;
    }
    
    public synchronized boolean isFlagSet(final int flag) {
        return (this.flags & flag) == flag;
    }
    
    public synchronized boolean setProperty(final String name, final java.lang.Object value) {
        return false;
    }
    
    public synchronized java.lang.Object getProperty(final String name) {
        return null;
    }
}
