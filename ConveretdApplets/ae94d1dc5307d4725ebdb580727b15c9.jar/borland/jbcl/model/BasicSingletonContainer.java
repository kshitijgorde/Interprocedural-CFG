// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.DispatchableEvent;
import java.util.EventListener;
import borland.jbcl.util.EventMulticaster;

public class BasicSingletonContainer implements WritableSingletonModel
{
    private Object item;
    private EventMulticaster modelListeners;
    private boolean events;
    
    public BasicSingletonContainer() {
        this.modelListeners = new EventMulticaster();
        this.events = true;
    }
    
    public BasicSingletonContainer(final Object data) {
        this.modelListeners = new EventMulticaster();
        this.events = true;
        this.item = data;
    }
    
    public Object get() {
        return this.item;
    }
    
    public Object getCopy() {
        Object newObject = new Object();
        newObject = this.item;
        return newObject;
    }
    
    public void addModelListener(final SingletonModelListener listener) {
        this.modelListeners.add(listener);
    }
    
    public void removeModelListener(final SingletonModelListener listener) {
        this.modelListeners.remove(listener);
    }
    
    public boolean canSet(final boolean startEdit) {
        return true;
    }
    
    public void set(final Object data) {
        this.item = data;
        this.processModelEvent(new SingletonModelEvent(this));
    }
    
    public void touched() {
        this.processModelEvent(new SingletonModelEvent(this));
    }
    
    public void enableModelEvents(final boolean enable) {
        if (this.events != enable && (this.events = enable)) {
            this.processModelEvent(new SingletonModelEvent(this));
        }
    }
    
    protected void processModelEvent(final SingletonModelEvent e) {
        if (this.events && this.modelListeners.hasListeners()) {
            this.modelListeners.dispatch(e);
        }
    }
}
