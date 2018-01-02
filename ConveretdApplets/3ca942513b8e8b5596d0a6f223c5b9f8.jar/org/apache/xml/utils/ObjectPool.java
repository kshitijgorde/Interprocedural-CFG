// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.apache.xml.res.XMLMessages;
import java.util.Vector;
import java.io.Serializable;

public class ObjectPool implements Serializable
{
    static final long serialVersionUID = -8519013691660936643L;
    private final Class objectType;
    private final Vector freeStack;
    
    public ObjectPool(final Class type) {
        this.objectType = type;
        this.freeStack = new Vector();
    }
    
    public ObjectPool(final String className) {
        try {
            this.objectType = ObjectFactory.findProviderClass(className, ObjectFactory.findClassLoader(), true);
        }
        catch (ClassNotFoundException cnfe) {
            throw new WrappedRuntimeException(cnfe);
        }
        this.freeStack = new Vector();
    }
    
    public ObjectPool(final Class type, final int size) {
        this.objectType = type;
        this.freeStack = new Vector(size);
    }
    
    public ObjectPool() {
        this.objectType = null;
        this.freeStack = new Vector();
    }
    
    public synchronized Object getInstanceIfFree() {
        if (!this.freeStack.isEmpty()) {
            final Object result = this.freeStack.lastElement();
            this.freeStack.setSize(this.freeStack.size() - 1);
            return result;
        }
        return null;
    }
    
    public synchronized Object getInstance() {
        if (this.freeStack.isEmpty()) {
            try {
                return this.objectType.newInstance();
            }
            catch (InstantiationException ex) {}
            catch (IllegalAccessException ex2) {}
            throw new RuntimeException(XMLMessages.createXMLMessage("ER_EXCEPTION_CREATING_POOL", null));
        }
        final Object result = this.freeStack.lastElement();
        this.freeStack.setSize(this.freeStack.size() - 1);
        return result;
    }
    
    public synchronized void freeInstance(final Object obj) {
        this.freeStack.addElement(obj);
    }
}
