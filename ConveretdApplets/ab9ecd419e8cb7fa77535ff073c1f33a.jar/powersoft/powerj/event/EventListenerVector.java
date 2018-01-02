// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.event;

import java.util.Enumeration;
import java.util.Vector;
import java.io.Serializable;

public class EventListenerVector implements Cloneable, Serializable
{
    static final long serialVersionUID = -5541958625958603982L;
    private int _refCount;
    private Vector _listeners;
    
    public EventListenerVector() {
        this._listeners = null;
    }
    
    public final synchronized EventListenerVector addListener(final EventListener listener) {
        EventListenerVector v = this;
        if (this._listeners == null) {
            this._listeners = new Vector(2, 5);
        }
        if (this._refCount > 0) {
            v = (EventListenerVector)v.clone();
        }
        if (v._listeners.contains(listener)) {
            v._listeners.removeElement(listener);
        }
        v._listeners.addElement(listener);
        return v;
    }
    
    public synchronized Object clone() {
        EventListenerVector v = null;
        try {
            v = (EventListenerVector)super.clone();
            v._refCount = 0;
            v._listeners = ((this._listeners != null) ? ((Vector)this._listeners.clone()) : null);
        }
        catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return v;
    }
    
    public synchronized boolean contains(final EventListener listener) {
        return this._listeners != null && this._listeners.contains(listener);
    }
    
    public synchronized Enumeration elements() {
        return new VectorEnumeration(this._listeners);
    }
    
    public synchronized void reference() {
        ++this._refCount;
    }
    
    public final synchronized EventListenerVector removeListener(final EventListener listener) {
        EventListenerVector v = this;
        if (this._listeners != null && this._listeners.contains(listener)) {
            if (this._refCount > 0) {
                v = (EventListenerVector)v.clone();
            }
            v._listeners.removeElement(listener);
        }
        return v;
    }
    
    public int size() {
        return (this._listeners != null) ? this._listeners.size() : 0;
    }
    
    public synchronized void unreference() {
        --this._refCount;
    }
}
