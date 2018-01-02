// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.util.Vector;
import java.io.Serializable;

public class ObjectPool implements Serializable
{
    private final Class objectType;
    private final Vector freeStack;
    
    public ObjectPool() {
        this.objectType = null;
        this.freeStack = new Vector();
    }
    
    public ObjectPool(final Class type) {
        this.objectType = type;
        this.freeStack = new Vector();
    }
    
    public ObjectPool(final Class type, final int size) {
        this.objectType = type;
        this.freeStack = new Vector(size);
    }
    
    public ObjectPool(final String className) {
        try {
            this.objectType = Class.forName(className);
        }
        catch (ClassNotFoundException cnfe) {
            throw new WrappedRuntimeException(cnfe);
        }
        this.freeStack = new Vector();
    }
    
    public synchronized void freeInstance(final Object obj) {
        this.freeStack.addElement(obj);
    }
    
    public synchronized Object getInstance() {
        if (this.freeStack.isEmpty()) {
            try {
                return this.objectType.newInstance();
            }
            catch (InstantiationException ex) {}
            catch (IllegalAccessException ex2) {}
            throw new RuntimeException("exception creating new instance for pool");
        }
        final Object result = this.freeStack.lastElement();
        this.freeStack.setSize(this.freeStack.size() - 1);
        return result;
    }
    
    public synchronized Object getInstanceIfFree() {
        if (!this.freeStack.isEmpty()) {
            final Object result = this.freeStack.lastElement();
            this.freeStack.setSize(this.freeStack.size() - 1);
            return result;
        }
        return null;
    }
}
